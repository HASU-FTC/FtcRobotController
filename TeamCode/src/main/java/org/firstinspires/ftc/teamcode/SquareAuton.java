package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
@Autonomous(name = "SquareAuton")
public class SquareAuton extends LinearOpMode {
    private DcMotor rearLeft;
    private DcMotor frontLeft;
    private DcMotor rearRight;
    private DcMotor frontRight;

    double leftFrontPower;
    double leftBackPower;
    double rightFrontPower;
    double rightBackPower;
    double axial(ElapsedTime running_time) {
        if (running_time.seconds() < 3) {
            return 0.25;
        } else if (running_time.seconds() < 6) {
            return 0;
        } else if (running_time.seconds() < 9) {
            return -0.25;
        } else if (running_time.seconds() < 12) {
            return 0;
        } else {
            return 0;
        }
    }
    double lateral(ElapsedTime running_time) {
        if (running_time.seconds() < 3) {
            return 0;
        } else if (running_time.seconds() < 6) {
            return -0.25;
        } else if (running_time.seconds() < 9) {
            return 0;
        } else if (running_time.seconds() < 12) {
            return 0.25;
        } else {
            return 0;
        }
    }
    public void runOpMode() {
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");

        // Set zero power behaviour
        rearLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rearRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rearLeft.setDirection(DcMotor.Direction.REVERSE);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        rearRight.setDirection(DcMotor.Direction.FORWARD);
        frontRight.setDirection(DcMotor.Direction.FORWARD);

        ElapsedTime runtime = new ElapsedTime();

        telemetry.addData("Status", "Initialized");
        telemetry.update();
        waitForStart();
        runtime.reset();

        while(opModeIsActive()) {
            leftFrontPower = axial(runtime) + lateral(runtime);
            rightFrontPower = axial(runtime) - lateral(runtime);
            leftBackPower = axial(runtime) - lateral(runtime);
            rightBackPower = axial(runtime) + lateral(runtime);
            // Send calculated power to wheels.
            frontLeft.setPower(leftFrontPower);
            frontRight.setPower(rightFrontPower);
            rearLeft.setPower(leftBackPower);
            rearRight.setPower(rightBackPower);
            // Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime);
            telemetry.addData("Front left/Right", JavaUtil.formatNumber(leftFrontPower, 4, 2) + ", " + JavaUtil.formatNumber(rightFrontPower, 4, 2));
            telemetry.addData("Back  left/Right", JavaUtil.formatNumber(leftBackPower, 4, 2) + ", " + JavaUtil.formatNumber(rightBackPower, 4, 2));
            telemetry.update();
        }
    }
}
