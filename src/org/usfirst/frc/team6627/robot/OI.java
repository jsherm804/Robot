package org.usfirst.frc.team6627.robot;

/*import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;*/
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/*import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.objdetect.Objdetect;
import org.usfirst.frc.team6627.robot.commands.DriveAuto;
import org.usfirst.frc.team6627.robot.commands.ExampleCommand;*/
import org.usfirst.frc.team6627.robot.commands.ToggleLift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	SpeedController frontLeftSpeedController = new Spark(0), rearLeftSpeedController = new Spark(1),
			frontRightSpeedController = new Spark(2), rearRightSpeedController = new Spark(3);
	
	RobotDrive SWRobot = new RobotDrive(frontLeftSpeedController, rearLeftSpeedController,
										frontRightSpeedController, rearRightSpeedController);
	
	Joystick leftJoy = new Joystick(0);
	Joystick rightJoy = new Joystick(1);
	
	DoubleSolenoid testDoubleSol = new DoubleSolenoid(1, 0);
	public boolean pistonIsUp;
	Compressor c = new Compressor(0);
	
	Button button1 = new JoystickButton(leftJoy, 1);
	
	public OI() {
		testDoubleSol.set(DoubleSolenoid.Value.kOff);
		pistonIsUp = false;
		button1.whenPressed(new ToggleLift(testDoubleSol, this));
		
		new Thread(() -> {
            UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
            camera.setResolution(640, 480);
            /*
             * Face detection... Let's not talk about it.
             * 
             */
            /*
            int absoluteFaceSize = 0;
            CvSink cvSink = CameraServer.getInstance().getVideo();
            CvSource outputStream = CameraServer.getInstance().putVideo("Face Detections", 640, 480);
            
            CascadeClassifier faceCascade = new CascadeClassifier();
            faceCascade.load("resources/haarcascades/haarcascade_frontalface_alt.xml");
            
            Mat source = new Mat();
            Mat bwframe = new Mat();
            //Mat output = new Mat();
            
            MatOfRect faceDetections = new MatOfRect();
            
            while(!Thread.interrupted()) {
                cvSink.grabFrame(source);
                Imgproc.cvtColor(source, bwframe, Imgproc.COLOR_BGR2GRAY);
                Imgproc.equalizeHist(bwframe, bwframe);
                
                
             // compute minimum face size (20% of the frame height, in our case)
        		if (absoluteFaceSize == 0)
        		{
        			int height = bwframe.rows();
        			if (Math.round(height * 0.2f) > 0)
        			{
        				absoluteFaceSize = Math.round(height * 0.2f);
        			}
        		}
        		
        		// detect faces
        		faceCascade.detectMultiScale(bwframe, faceDetections, 1.1, 2, 0 | Objdetect.CASCADE_SCALE_IMAGE,
        				new Size(absoluteFaceSize, absoluteFaceSize), new Size());
        				
        		// each rectangle in faces is a face: draw them!
        		Rect[] facesArray = faceDetections.toArray();
        		for (int i = 0; i < facesArray.length; i++)
        			Imgproc.rectangle(source, facesArray[i].tl(), facesArray[i].br(), new Scalar(0, 255, 0), 3);
                outputStream.putFrame(source);
            }*/
        }).start();
	}
	
	public void CheckDrive() {
		SWRobot.arcadeDrive(leftJoy);
	}
}
