package org.firstinspires.ftc.teamcode.tema;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Autonomous Tema", group="Autonomous")
public class AutoTema extends LinearOpMode {

    DcMotor leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
    DcMotor leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
    DcMotor rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
    DcMotor rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

    @Override
    public void runOpMode() {
        // init
        telemetry.addData("Status", "Initializing.");
        telemetry.update();

        initMove();

        telemetry.addData("Status", "Initialized. Starting execution.");

        // merge in fata
        move(1, 0, 0);
        sleep(2000);

        telemetry.addData("Status", "Moved forward.");
        telemetry.update();

        // se roteste 90 de grade
        move(0, 0, -1);
        sleep(1000); // aici trebuie facut debug, nu stiu cat de rapid se roteste robotul

        telemetry.addData("Status", "(might have) Rotated.");
        telemetry.update();

        move(-1, 0, 0);
        sleep(2000);

        telemetry.addData("Status", "Moved back.");
        telemetry.update();
    }

    private void initMove() {
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
    }

    // copiat de la scriptul pt mecanum TeleOP, adjustat cu parametrii in loc de gamepad
    private void move(float leftY, float leftX, float rightX) {
        double max;

        // POV Mode uses left joystick to go forward & strafe, and right joystick to rotate.
        double axial   = -leftY;  // Note: pushing stick forward gives negative value
        double lateral =  leftX;
        double yaw     =  rightX;

        // Combine the joystick requests for each axis-motion to determine each wheel's power.
        // Set up a variable for each drive wheel to save the power level for telemetry.
        double leftFrontPower  = axial + lateral + yaw;
        double rightFrontPower = axial - lateral - yaw;
        double leftBackPower   = axial - lateral + yaw;
        double rightBackPower  = axial + lateral - yaw;

        // Normalize the values so no wheel power exceeds 100%
        // This ensures that the robot maintains the desired motion.
        max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
        max = Math.max(max, Math.abs(leftBackPower));
        max = Math.max(max, Math.abs(rightBackPower));

        if (max > 1.0) {
            leftFrontPower  /= max;
            rightFrontPower /= max;
            leftBackPower   /= max;
            rightBackPower  /= max;
        }

        // Send calculated power to wheels
        leftFrontDrive.setPower(leftFrontPower);
        rightFrontDrive.setPower(rightFrontPower);
        leftBackDrive.setPower(leftBackPower);
        rightBackDrive.setPower(rightBackPower);

        // Show the elapsed game time and wheel power.
        telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
        telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
        telemetry.update();
    }
}
