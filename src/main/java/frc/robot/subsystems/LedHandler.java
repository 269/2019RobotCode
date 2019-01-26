package frc.robot.subsystems;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;

/**
 * Default comment
 */
public class LedHandler {
	SerialPort thePort;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		thePort = new SerialPort(9600,Port.kOnboard);
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void sendData(int colorDesire){
		/*
		1 = Red
		2 = Green
		3 = Blue
		4 = Strobe 
		5 
		*/
		if(colorDesire == 1) {
			//System.out.println("Red");
			thePort.writeString("red");  //Sends successfully, (Neopixels turn red) then crashes robot code
			System.out.println("Sent Red to Arduino!");
		}

		if(colorDesire == 2) {
			//System.out.println("Green");
			thePort.writeString("green"); //Sends successfully, (Neopixels turn Green) then crashes robot code
		}

		if(colorDesire == 3) {
			//System.out.println("Blue");
			thePort.writeString("blue"); //Sends successfully, (Neopixels turn Blue) then crashes robot code
		}
		if(colorDesire == 9){
			thePort.writeString("off");
		}
		//Write more as necessary
	}
	

}