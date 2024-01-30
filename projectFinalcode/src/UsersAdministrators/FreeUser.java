package UsersAdministrators;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import photoSharing.SharedPhoto;

//Can only do Blurring and Sharpening
public class FreeUser extends Users implements MethodForUsers{
	private String password;
	private String realName;
	private String realSurname;
	private int age;
	private String NICKNAME;
	private String email;
	private String ppPath="./src/Icons/DefaultProfilePhoto_128.png";
	protected String userType="Free";

	public FreeUser(String password, String realName, String realSurname, int age, String NICKNAME, String email,
			String ppPath) {
		super(password, realName, realSurname, age, NICKNAME, email, ppPath);

		// TODO Auto-generated constructor stub
	}
	public String getUserType() {
		return userType;
	}
	
	
	/**
	 * this method returns the information about user which includes their name, username, nickname
	 * email, password, profile photo's path,age,user type joined by a comma
	 * 
	 * @return returns the information of a user in a string
	 */
	public String writeUserInfo() {
		String str=this.getNICKNAME()+","+this.getPassword()+","+this.getEmail()+","+this.getRealName()+","+
	this.getRealSurname()+","+this.getUserType()+","+this.getPp()+","+this.getAge();
	return str;
	}
	
	/**
	 * This method will send a message to otherUser and the message string will contain the user's nickname who sent
	 * that message and the message itself.
	 */
	public void sendMessage(Users otherUser,String message) {
		File commentFile;
		if(this.getNICKNAME().compareTo(otherUser.getNICKNAME())<0) {
			commentFile = new File("./src/Messages/"+this.getNICKNAME()+otherUser.getNICKNAME()+".txt");
		}
		else {
			commentFile = new File("./src/Messages/"+otherUser.getNICKNAME()+this.getNICKNAME()+".txt");
		}
		try {
            FileWriter writer = new FileWriter(commentFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String newLine = this.getNICKNAME()+": "+message+"\n";

            bufferedWriter.write(newLine);
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}


}
