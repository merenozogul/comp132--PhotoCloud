package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UsersAdministrators.FreeUser;
import UsersAdministrators.HobbyistUser;
import UsersAdministrators.ProfessionalUser;
import UsersAdministrators.Users;
import exceptions.EmptyStringException;
import exceptions.InvalidAgeException;
import exceptions.InvalidEmailException;
import exceptions.InvalidImageException;
import exceptions.InvalidNickNameException;
import exceptions.InvalidPasswordException;
import exceptions.InvalidUserTypeException;
import exceptions.InvalideNameException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.naming.InvalidNameException;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import validators.AgeValidator;
import validators.EmailValidator;
import validators.ImageFileValidator;
import validators.NameSurnameValidator;
import validators.NickNameValidator;
import validators.PasswordValidator;
import validators.UserTypeValidator;


public class SignUpFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblNewLabel;
	private JLabel lblForLogInPage;
	private JButton btnForLogIn;
	private JLabel btnName;
	private JLabel btnSurname;
	private JLabel lblAge;
	private JLabel lblUserType;
	private JLabel lblPP;
	private JLabel lblEmail;
	private JLabel lblNickName;
	private JLabel lblPassword;
	private JTextField tfName;
	private JTextField tfSurname;
	private JTextField tfAge;
	private JTextField tfEmail;
	private JTextField tfNickName;
	private JPasswordField pfPassword;
	private JRadioButton rdbtnHobbyist;
	private JRadioButton rdbtnFree;
	private JRadioButton rdbtnPro;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnSignUpReal;
	private JButton btnForProfilePicture;
	private String ppPath="./src/Icons/DefaultProfilePhoto_128.png";



	/**
	 * Create the frame.
	 */
	public SignUpFrame() {
		this.setResizable(false);
		Date it=new Date();
		setTitle("PHOTOCLOUD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblNewLabel = new JLabel("SIGN UP");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 36));
		
		lblForLogInPage = new JLabel("I ALREADY HAVE AN ACCOUNT");
		lblForLogInPage.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		btnForLogIn = new JButton("LOG IN");
		btnForLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LogInPage loginp=new LogInPage();
				loginp.setVisible(true);
				loginp.setResizable(false);
				dispose();

			}
		});
		
		btnName = new JLabel("NAME:");
		btnName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		btnSurname = new JLabel("SURNAME:");
		btnSurname.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		lblAge = new JLabel("AGE:");
		lblAge.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		lblUserType = new JLabel("USER TYPE:");
		lblUserType.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		lblPP = new JLabel("PROFILE PHOTO (OPTIONAL):");
		lblPP.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		lblEmail = new JLabel("EMAIL:");
		lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		lblNickName = new JLabel("NICNAME:");
		lblNickName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		tfName = new JTextField();
		tfName.setColumns(10);
		
		tfSurname = new JTextField();
		tfSurname.setColumns(10);
		
		tfAge = new JTextField();
		tfAge.setColumns(10);
		
		tfEmail = new JTextField();
		tfEmail.setColumns(10);
		
		tfNickName = new JTextField();
		tfNickName.setColumns(10);
		
		pfPassword = new JPasswordField();
		
		rdbtnFree = new JRadioButton("Free");
		buttonGroup.add(rdbtnFree);
		rdbtnFree.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		
		rdbtnHobbyist = new JRadioButton("Hobbyist");
		buttonGroup.add(rdbtnHobbyist);
		
		rdbtnPro = new JRadioButton("Pro");
		buttonGroup.add(rdbtnPro);

		btnForProfilePicture = new JButton("CHOOSE A PROFILE PHOTO");
		btnForProfilePicture.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);

		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            String profilePhotoPath = selectedFile.getAbsolutePath();
		            ppPath=profilePhotoPath;
		            btnForProfilePicture.setText("CHOSEN. CLICK TO CHOOSE ANOTHER");
		        }
		        
			}
		});

		
		btnSignUpReal = new JButton("SIGN UP");
		btnSignUpReal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String errors="";
				String uT="";
				for (Enumeration<AbstractButton> buttons = buttonGroup.getElements(); buttons.hasMoreElements();) {
			        AbstractButton button = buttons.nextElement();

			        if (button.isSelected()) {
			           uT=button.getText();
			        }
			    }
				try {
					NameSurnameValidator.validateNameSurname(tfName.getText(),"Name");
				} catch (EmptyStringException|InvalideNameException e1) {
					errors+=e1.getMessage()+"\n";
				} 
				try {
					NameSurnameValidator.validateNameSurname(tfSurname.getText(),"Surname");
				} catch (EmptyStringException|InvalideNameException e1) {
					errors+=e1.getMessage()+"\n";
				} 
				try {
					AgeValidator.validateAge(tfAge.getText());
				}catch (EmptyStringException|InvalidAgeException e2) {
					errors+=e2.getMessage()+"\n";
				} 
				try {
					UserTypeValidator.validateUserType(uT);
				}
				catch(InvalidUserTypeException e2_5){
					errors+=e2_5.getMessage()+"\n";
				}
				
				try {
					ImageFileValidator.validateImageFile(ppPath);
				}
				catch(InvalidImageException e2_75) {
					errors+=e2_75.getMessage()+"\n";
				}
				try {
					NickNameValidator.validateNickName(tfNickName.getText());
				}
				catch(EmptyStringException|InvalidNickNameException | FileNotFoundException e3) {
					errors+=e3.getMessage()+"\n";
				}
				try {
					EmailValidator.validateEmail(tfEmail.getText());
						
					}
				catch(EmptyStringException|InvalidEmailException | FileNotFoundException e4) {
					errors+=e4.getMessage()+"\n";
				}
				try {
					PasswordValidator.validatePassword(String.valueOf(pfPassword.getPassword()));
				}
				catch(InvalidPasswordException e5) {
					errors+=e5.getMessage()+"\n";
				}
				
				
				if (errors.length()!=0) {
					
					
					Date ft=new Date();
					String errorF=String.format("Someone tried to sign up but failed due to this reasons:\n%s\n",errors);
					
					Logging.BaseLogger.error(it, it, errorF);
					JOptionPane.showMessageDialog(SignUpFrame.this,errors,"Sign up falied", JOptionPane.ERROR_MESSAGE);
				}
				else {
					JOptionPane.showMessageDialog(SignUpFrame.this, "Sign Up is succesfull.\nWelcome to our app!");
					if (uT.equals("Free")){
						FreeUser user=new FreeUser(String.valueOf(pfPassword.getPassword()),tfName.getText(),tfSurname.getText(),
								Integer.parseInt(tfAge.getText()),tfNickName.getText(),tfEmail.getText(),ppPath);
						try {
							File file = new File("./src/UserInfo/UserInfo.txt");
							Scanner scanner = new Scanner(file);
							String lines="";
					        lines+=user.writeUserInfo()+"\n";
							
							FileWriter writer = new FileWriter("./src/UserInfo/UserInfo.txt", true); 
							writer.write(lines);
						    writer.close();
						
						}
					    catch (IOException e2) {
						}
						String info=String.format("User with nickname %s signed up to PhotoCloud",tfNickName.getText());
						Date ft=new Date();
						Logging.BaseLogger.info(it, ft, info);
						DiscoverPage dP=new DiscoverPage(user);
						dP.setVisible(true);
						dispose();
					}
					else if(uT.equals("Hobbyist")) {
						HobbyistUser user=new HobbyistUser(String.valueOf(pfPassword.getPassword()),tfName.getText(),tfSurname.getText(),
								Integer.parseInt(tfAge.getText()),tfNickName.getText(),tfEmail.getText(),ppPath);
						try {
							File file = new File("./src/UserInfo/UserInfo.txt");
							Scanner scanner = new Scanner(file);
							String lines="";
					        lines+=user.writeUserInfo()+"\n";
							
							FileWriter writer = new FileWriter("./src/UserInfo/UserInfo.txt", true); 
							writer.write(lines);
						    writer.close();
						
						}
					    catch (IOException e2) {
						}
						String info=String.format("User with nickname %s signed up to PhotoCloud",tfNickName.getText());
						Date ft=new Date();
						Logging.BaseLogger.info(it, ft, info);
						DiscoverPage dP=new DiscoverPage(user);
						dP.setVisible(true);
						dispose();
					}
					else if(uT.equals("Pro")) {
						ProfessionalUser user =new ProfessionalUser(String.valueOf(pfPassword.getPassword()),tfName.getText(),tfSurname.getText(),
								Integer.parseInt(tfAge.getText()),tfNickName.getText(),tfEmail.getText(),ppPath);
						try {
							File file = new File("./src/UserInfo/UserInfo.txt");
							Scanner scanner = new Scanner(file);
							String lines="";
					        lines+=user.writeUserInfo()+"\n";
							
							FileWriter writer = new FileWriter("./src/UserInfo/UserInfo.txt", true); 
							writer.write(lines);
						    writer.close();
						
						}
					    catch (IOException e2) {
						}
						String info=String.format("User with nickname %s signed up to PhotoCloud",tfNickName.getText());
						Date ft=new Date();
						Logging.BaseLogger.info(it, ft, info);
						DiscoverPage dP=new DiscoverPage(user);
						dP.setVisible(true);
						dispose();
					}

				

				}
				
			}
		});
		

		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(378)
					.addComponent(lblNewLabel)
					.addContainerGap(363, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(408, Short.MAX_VALUE)
					.addComponent(btnSignUpReal)
					.addGap(388))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(146)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAge)
						.addComponent(btnSurname)
						.addComponent(btnName)
						.addComponent(lblUserType)
						.addComponent(lblPP)
						.addComponent(lblEmail)
						.addComponent(lblNickName)
						.addComponent(lblPassword))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addComponent(pfPassword)
							.addComponent(tfName, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
							.addComponent(tfSurname)
							.addComponent(tfAge)
							.addComponent(tfEmail, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
							.addComponent(tfNickName, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(btnForProfilePicture, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(rdbtnFree, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnHobbyist, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(rdbtnPro, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)))
					.addGap(254))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(662, Short.MAX_VALUE)
					.addComponent(btnForLogIn)
					.addGap(140))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(592, Short.MAX_VALUE)
					.addComponent(lblForLogInPage)
					.addGap(77))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(18)
					.addComponent(lblNewLabel)
					.addGap(74)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnName)
						.addComponent(tfName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnSurname)
						.addComponent(tfSurname, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblAge)
						.addComponent(tfAge, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblUserType)
						.addComponent(rdbtnFree)
						.addComponent(rdbtnPro)
						.addComponent(rdbtnHobbyist))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPP)
						.addComponent(btnForProfilePicture))
					.addGap(12)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEmail)
						.addComponent(tfEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNickName, Alignment.TRAILING)
						.addComponent(tfNickName, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(13)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(pfPassword, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(btnSignUpReal)
					.addGap(28)
					.addComponent(lblForLogInPage)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnForLogIn)
					.addGap(38))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
