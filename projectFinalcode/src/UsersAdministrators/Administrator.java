package UsersAdministrators;
//professional+can remove a photo from discovery page
public class Administrator extends ProfessionalUser {
	private String password;
	private String realName;
	private String realSurname;
	private int age;
	private String NICKNAME;
	private String email;
	private String ppPath="./src/Icons/DefaultProfilePhoto_128.png";
	protected String userType="Administrator";
	public Administrator(String password, String realName, String realSurname, int age, String NICKNAME,
			String email, String ppPath) {
		super(password, realName, realSurname, age, NICKNAME, email, ppPath);
	}
	public String getUserType() {
		return userType;
	}
	
	

}
