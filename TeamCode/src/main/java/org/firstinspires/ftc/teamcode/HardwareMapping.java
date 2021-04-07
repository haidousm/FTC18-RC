package org.firstinspires.ftc.teamcode;


import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.UltrasonicSensor;

/**
 * Created by Moussa on 4/4/18.
 */

public class HardwareMapping {

    // instantiate Dc Motors and Servos
    public DcMotor LFMotor;
    public DcMotor LBMotor;
    public DcMotor RFMotor;
    public DcMotor RBMotor;

    public DcMotor ARMotor;

    public DcMotor GlyphLMotor;
    public DcMotor GlyphRMotor;


    public Servo BallServo;

    // instantiate sensors
    public GyroSensor configGyro; // a variable that holds a reference to the gyro sensor
    public ModernRoboticsI2cGyro gyroSensor; // the actual gyro sensor which allows us to .getIntegratedZValue()

    public UltrasonicSensor frontUltra;
    public UltrasonicSensor backUltra;

    // a hardware map object is instantiated
    HardwareMap robotMap;

    public void init(HardwareMap robotMap){

        // hardware variables are initialized
        LFMotor = robotMap.get(DcMotor.class, "LFMotor");
        LBMotor = robotMap.get(DcMotor.class, "LBMotor");
        RFMotor = robotMap.get(DcMotor.class, "RFMotor");
        RBMotor = robotMap.get(DcMotor.class, "RBMotor");

        ARMotor = robotMap.get(DcMotor.class, "ARMotor");

        GlyphLMotor = robotMap.get(DcMotor.class, "GlyphLMotor");
        GlyphRMotor = robotMap.get(DcMotor.class, "GlyphRMotor");

        BallServo = robotMap.get(Servo.class, "BallServo");

        configGyro = robotMap.gyroSensor.get("gyroSensor");
        gyroSensor = (ModernRoboticsI2cGyro) configGyro;

        // calibrate gyro sensor
        gyroSensor.calibrate();

        // reverse the direction of all right sided motors
        RFMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        RBMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        GlyphRMotor.setDirection(DcMotorSimple.Direction.REVERSE);


        // set zero power behavior of the motors
        LFMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RFMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        ARMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        GlyphLMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        GlyphRMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);



    }


}
