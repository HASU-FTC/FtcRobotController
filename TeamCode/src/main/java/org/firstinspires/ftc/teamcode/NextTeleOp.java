package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.rowanmcalpin.nextftc.core.command.Command;
import com.rowanmcalpin.nextftc.ftc.NextFTCOpMode;
import com.rowanmcalpin.nextftc.ftc.driving.MecanumDriverControlled;
import com.rowanmcalpin.nextftc.ftc.hardware.controllables.MotorEx;

@TeleOp(name = "NextFTC TeleOp")
class NextTeleOp extends NextFTCOpMode {
    /*public NextTeleOp() {
        // Add in the subsystems when they are coded in
        super(Claw.INSTANCE, Lift.INSTANCE);
    }*/

    // Setup the drivechain motors
    public MotorEx frontLeftMotor;
    public MotorEx frontRightMotor;
    public MotorEx backLeftMotor;
    public MotorEx backRightMotor;

    public MotorEx[] motors;

    public Command driverControlled;

    @Override
    public void onInit() {
        frontLeftMotor = new MotorEx("frontLeft");
        frontRightMotor = new MotorEx("frontRight");
        backLeftMotor = new MotorEx("rearLeft");
        backRightMotor = new MotorEx("rearRight");

        // Change motor directions to suit robot
        frontLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        backRightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        motors = new MotorEx[] {frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor};
    }

    @Override
    public void onStartButtonPressed() {
        driverControlled = new MecanumDriverControlled(motors, gamepadManager.getGamepad1());
        driverControlled.invoke();

        /*gamepadManager.getGamepad2().getDpadUp().setPressedCommand(Lift.INSTANCE::getToHigh);
        gamepadManager.getGamepad2().getDpadDown().setPressedCommand(Lift.INSTANCE::getToLow);*/

    }
}
