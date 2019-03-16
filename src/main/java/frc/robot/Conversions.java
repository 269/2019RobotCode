package frc.robot;

public class Conversions{
    private static final double TPIELEVATOR = 0;
    private static final double TPIDRIVE = 0;
    private static final double TPIFRONT_INTAKE = 0;
    private static final double TPIBACK_INTAKE = 0;

    public enum Subsystem{
        ELEVATOR, DRIVE, FRONT_INTAKE, BACK_INTAKE
    }

    public static double ticksToInches(int ticks, Subsystem subsystem){   
        double TPI;
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
            default:
            TPI = 0;
            break;
        }
        inches = ticks / TPI;
        return inches;
    }
    public static int inchesToTicks(double inches, Subsystem subsystem){   
        double TPI;
        int ticks;

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
            default:
            TPI = 0;
            break;
        }
        ticks = (int)(TPI * inches);
        return ticks;
    }
}