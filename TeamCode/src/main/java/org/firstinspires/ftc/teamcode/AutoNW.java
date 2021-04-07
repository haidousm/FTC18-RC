package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by moussa on 4/4/18.
 */

public class AutoNW extends LinearOpMode {

    // define robot's hardware
    HardwareMapping robot = new HardwareMapping();

    int zAccumulated;  // total rotation to the left or the right
    int straightTarget = 0;  // angle where the robot is straight

    @Override
    public void runOpMode() throws InterruptedException {

        robot.init(hardwareMap);


        // make sure that the gyro sensor has calibrated
        while(robot.gyroSensor.isCalibrating()){

        }

        straightTarget = robot.gyroSensor.getIntegratedZValue();

        while(opModeIsActive()){



        }

    }

    // drives the robot straight according to the power supplied
    public void driveStraight(Double power, Double distance){

        // motor powers
        double leftPower;
        double rightPower;

        while(robot.frontUltra.getUltrasonicLevel() < distance) {

            // current direction
            zAccumulated = robot.gyroSensor.getIntegratedZValue();

            // calculate the power for each side
            leftPower = power + (zAccumulated - straightTarget) / 100;
            rightPower = power - (zAccumulated - straightTarget) / 100;

            leftPower = Range.clip(leftPower, -1, 1);
            rightPower = Range.clip(rightPower, -1, 1);

            // set the power of the motors
            robot.LBMotor.setPower(leftPower);
            robot.LFMotor.setPower(leftPower);

            robot.RBMotor.setPower(rightPower);
            robot.RFMotor.setPower(leftPower);

        }

        brakeMotors();
    }

    // turns a number of degrees compared to where the robot was when the program started ( + turns left)
    public void turnRobot(int target) {

        // current direction
        zAccumulated = robot.gyroSensor.getIntegratedZValue();
        double turnSpeed = 0.15;


        // keeps turning while the robot direction is further than three degrees from the target (margin of inaccuracy = 3/-3)
        while (Math.abs(zAccumulated - target) > 3) {

            // if gyro is negative the robot will turn to the right
            if (zAccumulated > target) {
                robot.LFMotor.setPower(turnSpeed);
                robot.LBMotor.setPower(turnSpeed);
                robot.RFMotor.setPower(-turnSpeed);
                robot.RBMotor.setPower(-turnSpeed);
            }

            // if gyro is positive the robot will turn to the left
            if (zAccumulated < target) {
                robot.LFMotor.setPower(-turnSpeed);
                robot.LBMotor.setPower(-turnSpeed);
                robot.RFMotor.setPower(turnSpeed);
                robot.RBMotor.setPower(turnSpeed);
            }

            zAccumulated = robot.gyroSensor.getIntegratedZValue();  //Set variables to gyro readings
        }

        brakeMotors();

    }

    void brakeMotors(){

        // brakes all DC motors
        robot.LFMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RFMotor.setPower(0);
        robot.RBMotor.setPower(0);

    }

}
