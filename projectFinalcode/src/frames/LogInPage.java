package frames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UsersAdministrators.Administrator;
import UsersAdministrators.FreeUser;
import UsersAdministrators.HobbyistUser;
import UsersAdministrators.ProfessionalUser;
import UsersAdministrators.Users;
import sounds.SoundPlay;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class LogInPage extends JFrame {

	private JPanel contentPane;
	private JLabel lblNickName;
	private JLabel lblNewLabel;
	private JTextField tfNickName;
	private JButton btnLogInSubmit;
	private JLabel lblForSıgnUp;
	private JPasswordField pfPassword;


	/**
	 * Create the frame.
	 */
	public LogInPage() {
		this.setResizable(false);
		setTitle("PHOTOCLOUD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(LogInPage.class.getResource("/Icons/LogIn_128.png")));
		
		lblNickName = new JLabel("NICKNAME:");
		lblNickName.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		tfNickName = new JTextField();
		tfNickName.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD:");
		lblPassword.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		btnLogInSubmit = new JButton("LOG IN");
		btnLogInSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it=new Date();
				String nn=tfNickName.getText();
				String pass= String.valueOf(pfPassword.getPassword());
				boolean bl=false;
			       try {
			            File file = new File("./src/UserInfo/UserInfo.txt");
			            Scanner scanner = new Scanner(file);

			            while (scanner.hasNextLine()) {
			                String[] userInfo = scanner.nextLine().split(",");
			                if (userInfo[0].equals(nn) && userInfo[1].equals(pass)) {
			                    JOptionPane.showMessageDialog(LogInPage.this,"Log in is successfull.\nWelcome!","Logged In",JOptionPane.PLAIN_MESSAGE);
			                    SoundPlay.loggedInSound();
			                    DiscoverPage dP=new DiscoverPage(userGetterFromNickName(userInfo[0]));
			                    Date ft=new Date();
			                    String info=String.format("%s logged in to PhotoCloud.",userInfo[0]);
			                    Logging.BaseLogger.info(it, ft,info );
			                    dP.setVisible(true);
			                    dispose();
			                    return;
			                }
			                else if (userInfo[0].equals(nn)) {
			                	bl=true;
			                }
			            }
			            if(!bl==true) {
			            	Date ft=new Date();
							String errorF="User not found";
							Logging.BaseLogger.error(it, ft, errorF);
			            	JOptionPane.showMessageDialog(LogInPage.this,"User not found!\nTry again","No User",JOptionPane.ERROR_MESSAGE);
			            	
			            	
			            }
			            else {
			            	Date ft=new Date();
							String errorF="Password is wrong for "+nn+" !";
							Logging.BaseLogger.error(it, ft, errorF);
			            	JOptionPane.showMessageDialog(LogInPage.this,"Password is wrong!\nTry again","Wrong Password",JOptionPane.ERROR_MESSAGE);
			            }
			            
			        } catch (FileNotFoundException e1) {
			        }
			}
		});
		btnLogInSubmit.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		lblForSıgnUp = new JLabel("I DON'T HAVE AN ACCOUNT");
		lblForSıgnUp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		JButton btnForSıgnUp = new JButton("SIGN UP");
		btnForSıgnUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SignUpFrame signupframe=new SignUpFrame();
				signupframe.setVisible(true);
				dispose();
				
				
			}
		});
		btnForSıgnUp.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		
		pfPassword = new JPasswordField();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(381)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(282)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(lblPassword)
								.addComponent(lblNickName))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(pfPassword, GroupLayout.PREFERRED_SIZE, 222, GroupLayout.PREFERRED_SIZE)
								.addComponent(tfNickName, GroupLayout.PREFERRED_SIZE, 224, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(395)
							.addComponent(btnLogInSubmit)))
					.addContainerGap(282, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(592, Short.MAX_VALUE)
					.addComponent(lblForSıgnUp)
					.addGap(96))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(651, Short.MAX_VALUE)
					.addComponent(btnForSıgnUp)
					.addGap(138))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(27)
					.addComponent(lblNewLabel)
					.addGap(71)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNickName)
						.addComponent(tfNickName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(38)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword)
						.addComponent(pfPassword, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
					.addGap(42)
					.addComponent(btnLogInSubmit)
					.addPreferredGap(ComponentPlacement.RELATED, 94, Short.MAX_VALUE)
					.addComponent(lblForSıgnUp)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnForSıgnUp)
					.addGap(34))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	/**
	 * Takes a nickname and returns the user that has that unique nickname
	 * @param name nickname of a user
	 * @return returns the user corresponding that nickname
	 * @throws FileNotFoundException it is thrown it UserInfo.txt is not found
	 */
	public static Users userGetterFromNickName(String name) throws FileNotFoundException {
		String USeersName=name;
		File file = new File("./src/UserInfo/UserInfo.txt");
		String ui1="";
        String ui2="";
        String ui3="";
        String ui4="";
        String ui5="";
        String ui6="";
        String ui7="";
        String ui8="";
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String[] userInfo = scanner.nextLine().split(",");
            if (userInfo[0].equals(USeersName) ) {
            	System.out.println(userInfo);
                ui1=userInfo[1];
                ui2=userInfo[3];
                ui3=userInfo[4];
                ui4=userInfo[7];
                ui5=userInfo[0];
                ui6=userInfo[2];
                ui7=userInfo[6];
                ui8=userInfo[5];
            }
            
        }
        scanner.close();
        if (ui8.equals("Free")) {
        	FreeUser user=new FreeUser(ui1,ui2,ui3,Integer.parseInt(ui4),ui5,ui6,ui7);
        	return user;
            }
        else if(ui8.equals("Hobbyist")) {
        	HobbyistUser user=new HobbyistUser(ui1,ui2,ui3,Integer.parseInt(ui4),ui5,ui6,ui7);
        	return user;
        }
        else if(ui8.equals("Professional")) {
        	ProfessionalUser user=new ProfessionalUser(ui1,ui2,ui3,Integer.parseInt(ui4),ui5,ui6,ui7);
        	return user;
        }
        else if(ui8.equals("Administrator")) {
        	Administrator user=new Administrator(ui1,ui2,ui3,Integer.parseInt(ui4),ui5,ui6,ui7);
        	return user;
        }
        else {
        	return null;
        }
        
     
	}
}
