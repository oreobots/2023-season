package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.JavaUtil;

public class AnushkaHWMap {
    DcMotor frontLeft = null;
    DcMotor frontRight = null;
    DcMotor rearLeft = null;
    DcMotor rearRight = null;


    public void init(HardwareMap hw){
        frontLeft  = hw.get(DcMotor.class, "frontLeft");
        frontRight = hw.get(DcMotor.class, "frontRight");
        rearLeft  = hw.get(DcMotor.class, "rearLeft");
        rearRight = hw.get(DcMotor.class, "rearRight");


        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);

    }

    public void move(double fl, double fr, double rl, double rr) {
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        rearLeft.setPower(rl);
        rearRight.setPower(rr);
    }

    public void moveFwd(double power) {
        move(power, power, power, power);
    }

    public void moveBackward(double power) {
        move(-power, -power, -power, -power);
    }

    public void strafeForward(double power) {
        strafe(0,-1,0,0, power);
    }

    public void strafeBackward(double power) {
        strafe(0,1,0,0, power);
    }

    public void strafeLeft(double power) {
        strafe(1,0,0,0, power);
    }

    public void strafeRight(double power) {
        strafe(-1,0,0,0, power);
    }


    /**
     * move fwd (0, -1, 0)
     * move bwd (0, 1, 0)
     * move left (1, 0, 0)
     * move right (-1, 0, 0)
     */
    public void strafe(int X, int Y, int RX, int D, double P){
        double denominator = JavaUtil.maxOfList(JavaUtil.createListWith(JavaUtil.sumOfList(JavaUtil.createListWith(Math.abs(Y), Math.abs(X), Math.abs(RX))), 1));
        double FrontLeftPower = (Y + X + RX) / denominator;
        double RearLeftPower = ((Y - X) + RX) / denominator;
        double FrontRightPower = ((Y - X) - RX) / denominator;
        double RearRightPower = ((Y + X) - RX) / denominator;
        frontLeft.setPower(fabsolute(FrontLeftPower, P));
        rearLeft.setPower(fabsolute(RearLeftPower, P));
        frontRight.setPower(fabsolute(FrontRightPower, P));
        rearRight.setPower(fabsolute(RearRightPower, P));

    }

    private double fabsolute(double value, double f_power) {
        double fabs_value;

        if (value > 0) {
            fabs_value = JavaUtil.minOfList(JavaUtil.createListWith(value, f_power));
        } else if (value < 0) {
            fabs_value = JavaUtil.maxOfList(JavaUtil.createListWith(value, -f_power));
        } else {
            fabs_value = value;
        }
        return fabs_value;
    }
}
