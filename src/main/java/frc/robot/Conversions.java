package frc.robot;

public class Conversions{
    private static final double TPIELEVATOR = 1233;
    private static final double TPIDRIVE = 4567;
    private static final double TPIFRONT_INTAKE = 4567;
    private static final double TPIBACK_INTAKE = 4567;

    public enum Subsystem{
        ELEVATOR, DRIVE, FRONT_INTAKE, BACK_INTAKE
    }

    public static double ticksToInches(double ticks, Subsystem subsystem){   
        double TPI = 0;
        double inches;

        switch(subsystem) {
            case ELEVATOR:
             TPI = TPIELEVATOR;
            break;
            case DRIVE:
            TPI = TPIDRIVE;
            break;
            case FRONT_INTAKE:
            TPI = TPIFRONT_INTAKE;
            break;
            case BACK_INTAKE:
            TPI = TPIBACK_INTAKE;
            break;
        }
        inches = ticks / TPI;
        return inches;
    }
    public static double inchesToTicks(double inches, Subsystem subsystem){   
        double TPI = 0;
        double ticks;

        switch(subsystem) {
            case ELEVATOR:
             TPI = TPIELEVATOR;
            break;
            case DRIVE:
            TPI = TPIDRIVE;
            break;
            case FRONT_INTAKE:
            TPI = TPIFRONT_INTAKE;
            break;
            case BACK_INTAKE:
            TPI = TPIBACK_INTAKE;
            break;
        }
        ticks = TPI * inches;
        return ticks;
    }
}