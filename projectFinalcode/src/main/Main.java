/************** Pledge of Honor ******************************************
I hereby certify that I have completed this programming project on my own
without any help from anyone else. The effort in the project thus belongs
completely to me. I did not search for a solution, or I did not consult any
program written by others or did not copy any program from other sources. I
read and followed the guidelines provided in the project description.
READ AND SIGN BY WRITING YOUR NAME SURNAME AND STUDENT ID
SIGNATURE: <Muhammet Eren Özoğul, 79294>
*************************************************************************/



package main;

import frames.LogInPage;
import frames.OpenPage;
import sounds.SoundPlay;
public class Main {

	public Main() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * Starts the PhotoCloud application
	 * @param args command line arguments
	 */
	public static void main(String[] args) {
		
		OpenPage open=new OpenPage();
		SoundPlay.soundOpening();
		try {
		
		    Thread.sleep(1500);
		} catch(InterruptedException e) {
		    
		}
		open.dispose();
		LogInPage frame = new LogInPage();
		frame.setVisible(true);
		frame.setResizable(false);
	}

}
