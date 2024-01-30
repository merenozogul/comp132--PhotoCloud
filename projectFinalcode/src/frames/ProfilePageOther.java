package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FilterFrames.FilterFrame;
import PhotoSize.PhotoSizeDesigner;
import UsersAdministrators.Users;
import exceptions.InvalidImageException;
import main.StringHandlerr;
import photoSharing.SharedPhoto;
import validators.ImageFileValidator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ProfilePageOther extends JFrame {

	private JPanel contentPane;
	private JLabel lblPP;
	private JLabel lblNickName;
	private JLabel lblNameSurname;
	private JLabel lblAge;
	private JLabel lblEmail;
	private JLabel lblUserType;
	private JLabel lblgAge;
	private Users user;
	private JLabel lblgUserType;
	private JLabel lblgEmail;
	private JButton btnDiscoveryPage;
	private JButton btnProfilePage;
	private JLabel lblNewLabel;
	private JButton btnSharePhoto;
	private JButton btnNewButton;
	




	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public ProfilePageOther(Users currentUser,Users user) throws IOException {
		this.setResizable(false);
		setTitle("PHOTOCLOUD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		if (currentUser.getUserType().equals("Administrator")) {
			contentPane.setBackground(new Color(157, 214, 159)); //yeşil
		}
		else if (currentUser.getUserType().equals("Hobbyist")) {
			contentPane.setBackground(new Color(61, 177, 197));  // mavi
		}
		else if(currentUser.getUserType().equals("Free")) {
			contentPane.setBackground(new Color(204, 171, 196)); //mor
		}
		else if(currentUser.getUserType().equals("Professional")) {
			contentPane.setBackground(new Color(194, 33, 46));  //kırmızı
		}

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 265);
		contentPane.add(panel);
		panel.setLayout(null);
		if (currentUser.getUserType().equals("Administrator")) {
			panel.setBackground(new Color(157, 214, 159)); //yeşil
		}
		else if (currentUser.getUserType().equals("Hobbyist")) {
			panel.setBackground(new Color(61, 177, 197));  // mavi
		}
		else if(currentUser.getUserType().equals("Free")) {
			panel.setBackground(new Color(204, 171, 196)); //mor
		}
		else if(currentUser.getUserType().equals("Professional")) {
			panel.setBackground(new Color(194, 33, 46));  //kırmızı
		}
		
		lblPP = new JLabel("");
		lblPP.setBounds(133, 80, 137, 128);
		lblPP.setIcon(PhotoSizeDesigner.photoGetterInSize(user.getPp(),137,128));
		panel.add(lblPP);
		
		lblNameSurname = new JLabel("Name Surname");
		lblNameSurname.setHorizontalAlignment(SwingConstants.CENTER);
		lblNameSurname.setText(user.getRealName()+" "+user.getRealSurname());
		lblNameSurname.setBounds(85, 233, 241, 25);
		panel.add(lblNameSurname);
		
		lblNickName = new JLabel("NickName");
		lblNickName.setText(user.getNICKNAME());
		lblNickName.setBounds(27, 32, 230, 16);
		panel.add(lblNickName);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(456, 6, 438, 254);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		if (currentUser.getUserType().equals("Administrator")) {
			panel_2.setBackground(new Color(157, 214, 159)); //yeşil
		}
		else if (currentUser.getUserType().equals("Hobbyist")) {
			panel_2.setBackground(new Color(61, 177, 197));  // mavi
		}
		else if(currentUser.getUserType().equals("Free")) {
			panel_2.setBackground(new Color(204, 171, 196)); //mor
		}
		else if(currentUser.getUserType().equals("Professional")) {
			panel_2.setBackground(new Color(194, 33, 46));  //kırmızı
		}
		
		
		btnSharePhoto = new JButton("SHARE");
		btnSharePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it=new Date();
		        JFileChooser fileChooser = new JFileChooser();
		        int bool = fileChooser.showOpenDialog(null);
		        String imgName="";
		        String imgExtension="";
		        if (bool == JFileChooser.APPROVE_OPTION) {
		            File secFile = fileChooser.getSelectedFile();
		            try {
		                Files.copy(secFile.toPath(), Paths.get("./src/images/" + secFile.getName()), StandardCopyOption.REPLACE_EXISTING);
		            }
		            catch(IOException eee) {
		                eee.printStackTrace(); 
		            }
		            String fileName = secFile.getName();
		            int noktaIndex = fileName.lastIndexOf('.');
		            
					if (noktaIndex > 0 && noktaIndex < fileName.length() - 1) {
		                imgName = fileName.substring(0,noktaIndex);
		            }
		            
					if (noktaIndex > 0 && noktaIndex < fileName.length() - 1) {
		                imgExtension = fileName.substring(noktaIndex + 1).toLowerCase();
		                imgExtension="."+imgExtension;
		            }
				    try {
		            	 ImageFileValidator.validateImageFile(imgName+imgExtension);
		                currentUser.saveAPhoto(imgName, imgExtension);
		                String info=String.format("%s saved %s.",currentUser.getNICKNAME(),secFile.getName());
		                Date ft= new Date();
		                Logging.BaseLogger.info(it, ft, info);
		                JOptionPane.showMessageDialog(ProfilePageOther.this, "Your photo is shared!\nFor now only you can see it.\nGo to your profile to post it!");
		               
		            } catch (IOException | InvalidImageException e1) {
		            	Date ft=new Date();
						String errorF="Photo sharing failed.";
						Logging.BaseLogger.error(it, ft, errorF);
		            	JOptionPane.showMessageDialog(ProfilePageOther.this,e1.getMessage());
		            	
		            }

		        }
		    }
		});
		
		btnSharePhoto.setBounds(312, 537, 276, 29);
		contentPane.add(btnSharePhoto);
		
		lblAge = new JLabel("Age:");
		lblAge.setBounds(41, 70, 28, 16);
		panel_2.add(lblAge);
		
		lblUserType = new JLabel("User Type:");
		lblUserType.setBounds(41, 109, 75, 16);
		panel_2.add(lblUserType);
		
		lblEmail = new JLabel("Email:");
		lblEmail.setBounds(41, 153, 75, 16);
		panel_2.add(lblEmail);
		
		lblgAge = new JLabel("New label");
		lblgAge.setText(user.getAge());
		lblgAge.setBounds(158, 70, 240, 16);
		panel_2.add(lblgAge);
		
		lblgUserType = new JLabel("New label");
		lblgUserType.setText(user.getUserType());
		lblgUserType.setBounds(158, 109, 240, 16);
		panel_2.add(lblgUserType);
		
		lblgEmail = new JLabel("New label");
		lblgEmail.setText(user.getEmail());
		lblgEmail.setBounds(158, 153, 240, 16);
		panel_2.add(lblgEmail);
		
		btnNewButton = new JButton("SEND A MESSAGE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessagingFrame mF=new MessagingFrame(currentUser,user);
				mF.setVisible(true);
				mF.setResizable(false);
				dispose();
			}
		});
		btnNewButton.setIcon(new ImageIcon(ProfilePageOther.class.getResource("/Icons/messages_24.png")));
		btnNewButton.setBounds(140, 219, 206, 29);
		panel_2.add(btnNewButton);
		
		btnDiscoveryPage = new JButton("DISCOVER");
		btnDiscoveryPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiscoverPage dP= new DiscoverPage(currentUser);
				dispose();
				dP.setVisible(true);
			}
		});
		btnDiscoveryPage.setBounds(6, 537, 276, 29);
		contentPane.add(btnDiscoveryPage);
		
		btnProfilePage = new JButton("PROFILE PAGE");
		btnProfilePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ProfilePageOwn ppOwn= new ProfilePageOwn(currentUser,currentUser);
					ppOwn.setVisible(true);
					dispose();
				} catch (IOException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnProfilePage.setBounds(600, 537, 294, 29);
		contentPane.add(btnProfilePage);
		
		lblNewLabel = new JLabel("SHARED PHOTOS");
		lblNewLabel.setBounds(379, 283, 129, 16);
		contentPane.add(lblNewLabel);
		
		
		
		
		
		File folder = new File("./src/images/");

		File[] listOfFiles = folder.listFiles();

		JPanel panel2 = new JPanel(new GridLayout(0, 2));
		if (currentUser.getUserType().equals("Administrator")) {
			panel2.setBackground(new Color(157, 214, 159)); //yeşil
		}
		else if (currentUser.getUserType().equals("Hobbyist")) {
			panel2.setBackground(new Color(61, 177, 197));  // mavi
		}
		else if(currentUser.getUserType().equals("Free")) {
			panel2.setBackground(new Color(204, 171, 196)); //mor
		}
		else if(currentUser.getUserType().equals("Professional")) {
			panel2.setBackground(new Color(194, 33, 46));  //kırmızı
		}

		JScrollPane scrollPane = new JScrollPane(panel2);
		scrollPane.setBounds(6, 305, 890, 225);

		for (File file : listOfFiles) {
		    if (file.isFile() && file.getName().contains("Shared") &&
		    		file.getName().contains(user.getNICKNAME())) {
		        try {
		            BufferedImage img = ImageIO.read(file);
		            final String name = file.getName();
		            String fileName = file.getName();
		            String userName = fileName.split("_")[0];
		         

		            JButton photoButton = new JButton("", PhotoSizeDesigner.photoGetterInSize("./src/images/"+name,200,200));
		            photoButton.setPreferredSize(new Dimension(210, 210)); 
		            photoButton.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    try {
		                        PhotoInteractionFrame inteact = new PhotoInteractionFrame(currentUser,fileName);
		                        dispose();
		                        inteact.setVisible(true);
		                    } catch (IOException e1) {
		                        e1.printStackTrace();
		                    }
		                }
		            });

		            panel2.add(photoButton);

		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }
		}

		contentPane.add(scrollPane);


	
	

	}


}
