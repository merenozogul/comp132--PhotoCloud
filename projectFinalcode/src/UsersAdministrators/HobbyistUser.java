package UsersAdministrators;

import java.awt.Image;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//Can only do Blurring, Sharpening, Brigtness and Contrast
public class HobbyistUser extends Users implements MethodForUsers {
	private String password;
	private String realName;
	private String realSurname;
	private int age;
	private String NICKNAME;
	private String email;
	private String ppPath="./src/Icons/DefaultProfilePhoto_128.png";
	protected String userType="Hobbyist";

	public HobbyistUser(String password, String realName, String realSurname, int age, String NICKNAME, String email,
			String ppPath) {
		super(password, realName, realSurname, age, NICKNAME, email, ppPath);

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
		File messagesFile;
		if(this.getNICKNAME().compareTo(otherUser.getNICKNAME())<0) {
			messagesFile = new File("./src/Messages/"+this.getNICKNAME()+otherUser.getNICKNAME()+".txt");
		}
		else {
			messagesFile = new File("./src/Messages/"+otherUser.getNICKNAME()+this.getNICKNAME()+".txt");
		}
		try {
            FileWriter writer = new FileWriter(messagesFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            String newLine = this.getNICKNAME()+": "+message+"\n";

            bufferedWriter.write(newLine);
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	





}
