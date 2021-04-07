package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

/**
 * Created by Moussa on 4/3/18.
 */

@TeleOp
public class DriverTimeBaby extends OpMode {

    // define robot's hardware
    HardwareMapping robot = new HardwareMapping();

    // gamepad ONE variables are initialized
    double oneLeftStickY = 0;
    double oneRightStickX = 0;

    double oneLeftTrigger = 0;
    double oneRightTrigger = 0;

    boolean oneB = false;
    boolean oneX = false;

    boolean oneLeftBumper = false;
    boolean oneRightBumper = false;

    // gamepad TWO variables are initialized
    double twoLeftTrigger = 0;
    double twoRightTrigger = 0;

    boolean twoY = false;
    boolean twoA = false;

    boolean twoLeftBumper = false;
    boolean twoRightBumper = false;

    boolean twoDpadLeft = false;
    boolean twoDpadRight = false;

    // runs as soon as the driver hits INIT
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

    }


    @Override
    public void loop() {

        telemetry.addData("Status", "Running");
        telemetry.update();

        robot.init(hardwareMap);
        getGamepadValues();


        if (Math.abs(oneLeftStickY) > 0.1) {

            // front and backward movement
            frontBackMovement(oneLeftStickY);

        }

        if (Math.abs(oneRightStickX) > 0.1) {

            // turning left and right
            turnMovement(oneRightStickX);

        }

        if (oneB) {

            // strafe left
            strafeMovement("left");

        }

        if (oneX) {

            // strafe right
           strafeMovement("right");

        }

        if (twoLeftBumper){

            glyphLMotorMovement("inwards");

        }

        if (twoLeftTrigger > 0.1){

            glyphLMotorMovement("outwards");

        }

        if (twoRightBumper){

            glyphRMotorMovement("inwards");

        }

        if (twoRightTrigger > 0.1){

            glyphRMotorMovement("outwards");

        }



        // brake motors if nothing is being pressed
        brakeMotors();

    }


    // moves the robot either forward or backwards according to the power supplied by the gamepad ONE left stick Y axis
    void frontBackMovement(double oneLeftStickYValue){

        // clips the power of the motor into a maximum of 0.8 and a minimum of -0.8
        double motorPower = Range.clip(oneLeftStickYValue, -0.8, 0.8);

        robot.LFMotor.setPower(motorPower);
        robot.LBMotor.setPower(motorPower);

        robot.RFMotor.setPower(motorPower);
        robot.RBMotor.setPower(motorPower);

    }

    // turns robot according to the power supplied by the gamepad ONE right stick X axis
    void turnMovement(double oneRightStickXValue){

        // clips the power of the motor into a maximum of 0.5 and a minimum of -0.5
        double motorPower = Range.clip(oneRightStickXValue, -0.5, 0.5);

        // multiplying the value of the power by * - * reverses the direction of the motor
        robot.LFMotor.setPower(-motorPower);
        robot.LBMotor.setPower(-motorPower);

        robot.RFMotor.setPower(motorPower);
        robot.RBMotor.setPower(motorPower);

    }

    // strafes robot with a constant speed of 0.8
    void strafeMovement(String direction){


        // checks if the direction wanted is to strafe left or right
        if (direction == "left"){

            robot.LFMotor.setPower(0.8);
            robot.LBMotor.setPower(-0.8);

            robot.RFMotor.setPower(-0.8);
            robot.RBMotor.setPower(0.8);

        }else if (direction == "right"){

            robot.LFMotor.setPower(-0.8);
            robot.LBMotor.setPower(0.8);

            robot.RFMotor.setPower(0.8);
            robot.RBMotor.setPower(-0.8);

        }


    }

    // controls the left Dc Motor responsible for sucking the glyph
    void glyphLMotorMovement(String direction){

        // checks the direction to either "suck" in the glyph or "spit" it out
        if (direction == "inwards"){

            robot.GlyphLMotor.setPower(-1);

        }else if (direction == "outwards"){

            robot.GlyphLMotor.setPower(1);

        }

    }

    // controls the right Dc Motor responsible for sucking the glyph
    void glyphRMotorMovement(String direction){

        // checks the direction to either "suck" in the glyph or "spit" it out
        if (direction == "inwards"){

            robot.GlyphRMotor.setPower(-1);

        }else if (direction == "outwards"){

            robot.GlyphRMotor.setPower(1);

        }

    }

    void getGamepadValues(){

        // get gamepad ONE values
        oneLeftStickY = -this.gamepad1.left_stick_y;
        oneRightStickX = -this.gamepad1.left_stick_x;

        oneLeftTrigger = this.gamepad1.left_trigger;
        oneRightTrigger = this.gamepad1.right_trigger;

        oneB = this.gamepad1.b;
        oneX = this.gamepad1.x;

        oneLeftBumper = this.gamepad1.left_bumper;
        oneRightBumper = this.gamepad1.right_bumper;

        // get gamepad TWO values
        twoLeftTrigger = this.gamepad2.left_trigger;
        twoRightTrigger = this.gamepad2.right_trigger;

        twoY = this.gamepad2.y;
        twoA = this.gamepad2.a;

        twoLeftBumper = this.gamepad2.left_bumper;
        twoRightBumper = this.gamepad2.right_bumper;

        twoDpadLeft = this.gamepad2.dpad_left;
        twoDpadRight = this.gamepad2.dpad_right;

    }

    void brakeMotors(){

        // brakes all DC motors
        robot.LFMotor.setPower(0);
        robot.LBMotor.setPower(0);
        robot.RFMotor.setPower(0);
        robot.RBMotor.setPower(0);

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}


