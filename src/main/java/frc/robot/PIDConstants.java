package frc.robot;

public class PIDConstants {
    public final double kP; //the distance between the currentPosition and the goalPosition(error)
	public final double kI; //how long has it taken to get to goalPosition
	public final double kD; //the speed traveling towards the goalPosition
	public final double kF; //EVIL
	public final int kIzone; //the max I
	public final double kPeakOutput; //The max output of the motors
	public final int slotIdx;
	public final int kTimeOutMs;
	public final int kPIDLoopIdx;
	public final int cruiseVelocity;
	public final int acceleration;
	public final int forwardSensorLimit;
	public final int reverseSensorLimit;
	/**
	 * @param forwardSensorLimit
	 * @param reverseSensorLimit
	 * @param cruiseVelocity sensorUnitsPer100ms
	 * @param acceleration sensorUnitsPer100msPerSec
	 * @param kPthe distance between the currentPosition and the goalPosition(error)
	 * @param kI how long has it taken to get to goalPosition
	 * @param kD the speed traveling towards the goalPosition
	 * @param kF EVVVIIILLLL
	 * @param kIzone the max I
	 * @param kPeakOutput The max output of the motors
	 */
	public PIDConstants(int cruiseVelocity, int acceleration, double kP, double kI, double kD, double kF, int kIzone, double kPeakOutput){
		this.forwardSensorLimit = 3456;
		this.reverseSensorLimit = 190;
		this.slotIdx = 0;
		this.kTimeOutMs = 30;
		this.kPIDLoopIdx = 0;
		this.cruiseVelocity = cruiseVelocity;
		this.acceleration = acceleration;
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.kF = kF;
		this.kIzone = kIzone;
		this.kPeakOutput = kPeakOutput;
	}
}