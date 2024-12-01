package org.firstinspires.ftc.teamcode.tema;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class TeleopTema extends LinearOpMode {
    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initializing.");
        telemetry.update();

        DcMotor motorTeleOP = hardwareMap.get(DcMotor.class, "MotorTeleOP");
        Servo motorServo = hardwareMap.get(Servo.class, "ServoTeleOp"); // TeleOP sau TeleOp ?

        telemetry.addData("Status", "Initialized. Waiting for PLAY.");
        telemetry.update();
        waitForStart();

        telemetry.addData("Status", "Teleop loop started.");
        telemetry.update();

        while (opModeIsActive()) {
            if (gamepad1.right_trigger > 0) {
                telemetry.addData("Status", "Right trigger pressed (Thread blocked for 5s).");
                telemetry.update();

                motorTeleOP.setPower(0.5);
                sleep(3000);
                motorTeleOP.setPower(-0.5);
                sleep(2000);
            }
            if (gamepad1.left_trigger > 0) {
                telemetry.addData("Status", "Left trigger pressed.");

                motorServo.setPosition(0.5);
            }
        }
    }
}
