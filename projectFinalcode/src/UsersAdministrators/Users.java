package UsersAdministrators;

import java.awt.Image;
import java.io.IOException;
import java.util.ArrayList;

import Filters.ImageSecretary;
import frames.ProfilePageOwn;
import photoSharing.SharedPhoto;
public abstract class Users implements MethodForUsers  {
	private String password;
	private String realName;
	private String realSurname;
	private int age;
	private final String NICKNAME;
	private String email;
	private String ppPath="./src/Icons/DefaultProfilePhoto_128.png";
//	private ArrayList<SharedPhoto> photos=new ArrayList<>();
	protected String userType;
//	private ProfilePageOwn profilePage=new ProfilePageOwn();
	
	public Users(String password,String realName,String realSurname,int age,String NICKNAME,String email,String ppPath) {
		this.password=password;
		this.realName=realName;
		this.realSurname=realSurname;
		this.age=age;
		this.NICKNAME=NICKNAME;
		this.email=email;
		this.ppPath=ppPath;
	}

	public String getPassword() {
		return password;
	}
	public String getRealName() {
		return realName;
	}
	public String getRealSurname() {
		return realSurname;
	}
	public String getAge() {
		return Integer.toString(age);
	}
	public  String getNICKNAME() {
		return NICKNAME;
	}
	public String getEmail() {
		return email;
	}
	public String getPp() {
		return ppPath;
	}
//	public ArrayList<SharedPhoto> getPhotos() {
//		return photos;
//	}
	public String getUserType() {
		return userType;
	}
	
	/**
	 * This method takes a photo and creates a new SharedPhoto object and that object 
	 * includes the name of the user who shared it
	 * 
	 * @param name This is the name of the photo
	 * @param extension This is the extension of the photo
	 * @throws IOException If the photo is not found , this is thrown
	 */
	public void shareAPhoto(String name,String extension) throws IOException {
		SharedPhoto shaPho=new SharedPhoto(name,extension,0,0,this);

	}
	
	/**
	 * This method takes a photo and saves that photo for the user
	 * 
	 * @param name This is the name of the photo
	 * @param extension This is the extension of the photo
	 * @throws IOException If the photo is not found , this is thrown
	 */
	public void saveAPhoto(String name,String extension) throws IOException {
		ImageSecretary.writeFilteredImageToResources(ImageSecretary.readResourceImage(name, extension), this.NICKNAME+"_Saved_"+name, extension);
	}
	/**
	 * this method returns the information about user which includes their name, username, nickname
	 * email, password, profile photo's path,age,user type joined by a comma
	 * 
	 * @return returns the information of a user in a string
	 */
//	public abstract String writeUserInfo();
	
//	public abstract void sendMessage(Users otherUser,String message);
	
	
	
	

}
