package frames;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FilterFrames.FilterFrame;
import PhotoSize.PhotoSizeDesigner;
import UsersAdministrators.Administrator;
import UsersAdministrators.FreeUser;
import UsersAdministrators.HobbyistUser;
import UsersAdministrators.ProfessionalUser;
import UsersAdministrators.Users;
import exceptions.EmptyStringException;
import exceptions.InvalidAgeException;
import exceptions.InvalidEmailException;
import exceptions.InvalidImageException;
import exceptions.InvalidPasswordException;
import main.StringHandlerr;
import photoSharing.SharedPhoto;
import sounds.SoundPlay;
import validators.AgeValidator;
import validators.EmailValidator;
import validators.ImageFileValidator;
import validators.NameSurnameValidator;
import validators.PasswordValidator;
import exceptions.InvalideNameException;

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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class ProfilePageOwn extends JFrame {

	private JPanel contentPane;
	private JLabel lblPP;
	private JLabel lblNickName;
	private JLabel lblNameSurname;
	private JLabel lblAge;
	private JLabel lblEmail;
	private JLabel lblUserType;
	private JLabel lblPassword;
	private JLabel lblgAge;
	private Users user;
	private JLabel lblgUserType;
	private JLabel lblgEmail;
	private JButton btnChangeAge;
	private JButton btnChangeEmail;
	private JButton btnChangePassword;
	private JButton btnChangeSurname;
	private JButton btnDiscoveryPage;
	private JButton btnProfilePage;
	private JLabel lblNewLabel;
	private JButton btnSharePhoto;
	private JButton btnChangeName;
	private JButton btnChangePP;
	private JButton btnMessageBox;
	


	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public ProfilePageOwn(Users currentUser,Users user) throws IOException {
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
		panel.setBounds(6, 6, 438, 288);
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
//		System.out.println(user.getPp());
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
		
		btnChangeSurname = new JButton("");
		btnChangeSurname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it=new Date();
				String name3 = JOptionPane.showInputDialog("Enter your new surname:");
				try {
					 NameSurnameValidator.validateNameSurname(name3,"Surname");
					 if (currentUser.getUserType().equals("Free")) {
				        FreeUser user2=new FreeUser(currentUser.getPassword(),currentUser.getRealName(),name3,
				        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
				        try {
							changeUser(user2);
							ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
							String info=String.format("%s changed their surname from %s to %s.",currentUser.getNICKNAME(),currentUser.getRealSurname(),name3);
			                Date ft= new Date();
			                Logging.BaseLogger.info(it, ft, info);
							ppOwnN.setVisible(true);
							dispose();
						} catch (IOException e1) {
				
						}
		             }
			         else if(currentUser.getUserType().equals("Hobbyist")) {
			        	 HobbyistUser user2=new HobbyistUser(currentUser.getPassword(),currentUser.getRealName(),name3,
			        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
			        	 try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their surname from %s to %s.",currentUser.getNICKNAME(),currentUser.getRealSurname(),name3);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
			         }
			         else if(currentUser.getUserType().equals("Professional")) {
			             ProfessionalUser user2=new ProfessionalUser(currentUser.getPassword(),currentUser.getRealName(),name3,
			        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
			             try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their surname from %s to %s.",currentUser.getNICKNAME(),currentUser.getRealSurname(),name3);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
			         }
			         else {
			         	Administrator user2=new Administrator(currentUser.getPassword(),currentUser.getRealName(),name3,
			        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
			         	try {
							changeUser(user2);
							ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
							String info=String.format("%s changed their surname from %s to %s.",currentUser.getNICKNAME(),currentUser.getRealSurname(),name3);
			                Date ft= new Date();
			                Logging.BaseLogger.info(it, ft, info);
							ppOwnN.setVisible(true);
							dispose();
						} catch (IOException e1) {
				
						}
			         }
					 
					
				}
				catch(InvalideNameException| EmptyStringException e2 ) {
					Date ft=new Date();
					String errorF=String.format("%s couldn't changed their name due to:\n%s\n",currentUser.getNICKNAME(),e2.getMessage());
					Logging.BaseLogger.error(it, ft, errorF);
					JOptionPane.showMessageDialog(ProfilePageOwn.this, e2.getMessage());
				}
			}
		});
		btnChangeSurname.setIcon(new ImageIcon(ProfilePageOwn.class.getResource("/Icons/Modify_16.png")));
		btnChangeSurname.setBounds(381, 236, 28, 22);
		panel.add(btnChangeSurname);
		
		btnChangeName = new JButton("");
		btnChangeName.addActionListener(new ActionListener() {
			Date it=new Date();
			public void actionPerformed(ActionEvent e) {
			String name2 = JOptionPane.showInputDialog("Enter your new name:");
			try {
				 NameSurnameValidator.validateNameSurname(name2,"Name");
				 if (currentUser.getUserType().equals("Free")) {
			        	FreeUser user2=new FreeUser(currentUser.getPassword(),name2,currentUser.getRealSurname(),
			        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
			        	try {
							changeUser(user2);
							ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
							String info=String.format("%s changed their name from %s to %s.",currentUser.getNICKNAME(),currentUser.getRealName(),name2);
			                Date ft= new Date();
			                Logging.BaseLogger.info(it, ft, info);
							ppOwnN.setVisible(true);
							dispose();
						} catch (IOException e1) {
				
						}
			            }
			        else if(currentUser.getUserType().equals("Hobbyist")) {
			        	HobbyistUser user2=new HobbyistUser(currentUser.getPassword(),name2,currentUser.getRealSurname(),
			        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
			        	try {
							changeUser(user2);
							ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
							String info=String.format("%s changed their name from %s to %s.",currentUser.getNICKNAME(),currentUser.getRealName(),name2);
			                Date ft= new Date();
			                Logging.BaseLogger.info(it, ft, info);
							ppOwnN.setVisible(true);
							dispose();
						} catch (IOException e1) {
				
						}
			        }
			        else if(currentUser.getUserType().equals("Professional")) {
			        	ProfessionalUser user2=new ProfessionalUser(currentUser.getPassword(),name2,currentUser.getRealSurname(),
			        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
			        	try {
							changeUser(user2);
							ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
							String info=String.format("%s changed their name from %s to %s.",currentUser.getNICKNAME(),currentUser.getRealName(),name2);
			                Date ft= new Date();
			                Logging.BaseLogger.info(it, ft, info);
							ppOwnN.setVisible(true);
							dispose();
						} catch (IOException e1) {
				
						}
			        }
			        else {
			        	Administrator user2=new Administrator(currentUser.getPassword(),name2,currentUser.getRealSurname(),
			        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
			        	try {
							changeUser(user2);
							ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
							String info=String.format("%s changed their name from %s to %s.",currentUser.getNICKNAME(),currentUser.getRealName(),name2);
			                Date ft= new Date();
			                Logging.BaseLogger.info(it, ft, info);
							ppOwnN.setVisible(true);
							dispose();
						} catch (IOException e1) {
				
						}
			        }
			}
			catch(InvalideNameException| EmptyStringException e2 ) {
				Date ft=new Date();
				String errorF=String.format("%s couldn't changed their surname due to:\n%s\n",currentUser.getNICKNAME(),e2.getMessage());
				Logging.BaseLogger.error(it, ft, errorF);
				JOptionPane.showMessageDialog(ProfilePageOwn.this, e2.getMessage());
			}
		}
	});
		btnChangeName.setIcon(new ImageIcon(ProfilePageOwn.class.getResource("/Icons/Modify_16.png")));
		btnChangeName.setBounds(338, 236, 28, 22);
		panel.add(btnChangeName);
		
		btnChangePP = new JButton("");
		btnChangePP.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
		        int bool = fileChooser.showOpenDialog(null);
		        Date it=new Date();

		        if (bool == JFileChooser.APPROVE_OPTION) {
		        	
		            File secFile = fileChooser.getSelectedFile();
		            String profilePhotoPath =  secFile .getAbsolutePath();
		            
		        	try {
						ImageFileValidator.validateImageFile(profilePhotoPath);
						 if (currentUser.getUserType().equals("Free")) {
					        	FreeUser user2=new FreeUser(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
					        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),profilePhotoPath);
					        	try {
									changeUser(user2);
									ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
									String info=String.format("%s changed their profile photo from %s to %s.",currentUser.getNICKNAME(),currentUser.getPp(),profilePhotoPath);
					                Date ft= new Date();
					                Logging.BaseLogger.info(it, ft, info);
									ppOwnN.setVisible(true);
									dispose();
								} catch (IOException e1) {
						
								}
					            }
					        else if(currentUser.getUserType().equals("Hobbyist")) {
					        	HobbyistUser user2=new HobbyistUser(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
					        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),profilePhotoPath);
					        	try {
									changeUser(user2);
									ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
									String info=String.format("%s changed their profie photo from %s to %s.",currentUser.getNICKNAME(),currentUser.getPp(),profilePhotoPath);
					                Date ft= new Date();
					                Logging.BaseLogger.info(it, ft, info);
									ppOwnN.setVisible(true);
									dispose();
								} catch (IOException e1) {
						
								}
					        }
					        else if(currentUser.getUserType().equals("Professional")) {
					        	ProfessionalUser user2=new ProfessionalUser(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
					        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),profilePhotoPath);
					        	try {
					        		System.out.println(user2.writeUserInfo());
					        		System.out.println(currentUser.getPassword());
									changeUser(user2);
									ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
									String info=String.format("%s changed their profile photo from %s to %s.",currentUser.getNICKNAME(),currentUser.getPp(),profilePhotoPath);
					                Date ft= new Date();
					                Logging.BaseLogger.info(it, ft, info);
									ppOwnN.setVisible(true);
									dispose();
								} catch (IOException e1) {
						
								}
					        }
					        else {
					        	Administrator user2=new Administrator(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
					        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),profilePhotoPath);
					        	try {
									changeUser(user2);
									ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
									String info=String.format("%s changed their profile photo from %s to %s.",currentUser.getNICKNAME(),currentUser.getPp(),profilePhotoPath);
					                Date ft= new Date();
					                Logging.BaseLogger.info(it, ft, info);
									ppOwnN.setVisible(true);
									dispose();
								} catch (IOException e1) {
						
								}
					        }
						
						
		        	}
		        	catch(InvalidImageException ee) {
		        		JOptionPane.showMessageDialog(ProfilePageOwn.this, "Invalid image");
		        		String info=String.format("%s couldn'n changed their profile photo because the new one is an invalid image.",currentUser.getNICKNAME(),currentUser.getPp(),profilePhotoPath);
		                Date ft= new Date();
		                Logging.BaseLogger.info(it, ft, info);
		        		
		        	}
		        	
		        
	
				
				
		        }
			}
		});
		btnChangePP.setIcon(new ImageIcon(ProfilePageOwn.class.getResource("/Icons/Modify_16.png")));
		btnChangePP.setBounds(298, 128, 28, 22);
		panel.add(btnChangePP);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(456, 6, 438, 288);
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
		            File secilmisImage = fileChooser.getSelectedFile();
		            try {
		                Files.copy(secilmisImage.toPath(), Paths.get("./src/images/" + secilmisImage.getName()), StandardCopyOption.REPLACE_EXISTING);
		            }
		            catch(IOException eee) {
		                eee.printStackTrace(); 
		            }
		            String fileName = secilmisImage.getName();
		            int noktaIndex = fileName.lastIndexOf('.');
		            
					if (noktaIndex > 0 && noktaIndex < fileName.length() - 1) {
		                imgName = fileName.substring(0, noktaIndex);
		            }
		            
					if (noktaIndex > 0 && noktaIndex < fileName.length() - 1) {
		                imgExtension = fileName.substring(noktaIndex + 1).toLowerCase();
		                imgExtension="."+imgExtension;
		            }
		            try {
		            	 ImageFileValidator.validateImageFile(imgName+imgExtension);
		                currentUser.saveAPhoto(imgName, imgExtension);
		                String info=String.format("%s saved %s.",currentUser.getNICKNAME(),secilmisImage.getName());
		                Date ft= new Date();
		                Logging.BaseLogger.info(it, ft, info);
		                JOptionPane.showMessageDialog(ProfilePageOwn.this, "Your photo is shared!\nFor now only you can see it.\nGo to your profile to post it!");
		                ProfilePageOwn ppNN=new ProfilePageOwn(currentUser,currentUser);
		                ppNN.setVisible(true);
		                ppNN.setResizable(false);
		                dispose();
		               
		            } catch (IOException | InvalidImageException e1) {
		            	Date ft=new Date();
						String errorF="Photo sharing failed.";
						Logging.BaseLogger.error(it, ft, errorF);
		            	JOptionPane.showMessageDialog(ProfilePageOwn.this,e1.getMessage());
		            	
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
		
		lblPassword = new JLabel("Password:");
		lblPassword.setBounds(41, 199, 75, 16);
		panel_2.add(lblPassword);
		
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
		
		JLabel lblgPassword = new JLabel("New label");
		lblgPassword.setText(user.getPassword());
		lblgPassword.setBounds(158, 199, 240, 16);
		panel_2.add(lblgPassword);
		
		btnChangeAge = new JButton("");
		btnChangeAge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it= new Date();
				String age = JOptionPane.showInputDialog("Enter a value to change your age:");
				try {
					 AgeValidator.validateAge(age);
					 if (currentUser.getUserType().equals("Free")) {
				        	FreeUser user2=new FreeUser(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(age),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their age from %s to %s.",currentUser.getNICKNAME(),currentUser.getAge(),age);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				            }
				        else if(currentUser.getUserType().equals("Hobbyist")) {
				        	HobbyistUser user2=new HobbyistUser(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(age),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their age from %s to %s.",currentUser.getNICKNAME(),currentUser.getAge(),age);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				        }
				        else if(currentUser.getUserType().equals("Professional")) {
				        	ProfessionalUser user2=new ProfessionalUser(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(age),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
				        	try {
				        		System.out.println(user2.writeUserInfo());
				        		System.out.println(currentUser.getPassword());
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their age from %s to %s.",currentUser.getNICKNAME(),currentUser.getAge(),age);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				        }
				        else {
				        	Administrator user2=new Administrator(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(age),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their age from %s to %s.",currentUser.getNICKNAME(),currentUser.getAge(),age);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				        }
				}
				catch(InvalidAgeException| EmptyStringException e2 ) {
					Date ft=new Date();
					String errorF=String.format("%s couldn't changed their age due to:\n%s\n",currentUser.getNICKNAME(),e2.getMessage());
					Logging.BaseLogger.error(it, ft, errorF);
					JOptionPane.showMessageDialog(ProfilePageOwn.this,e2.getMessage());
				}
			}
		});
		btnChangeAge.setSelectedIcon(null);
		btnChangeAge.setIcon(new ImageIcon(ProfilePageOwn.class.getResource("/Icons/Modify_16.png")));
		btnChangeAge.setBounds(404, 70, 28, 22);
		panel_2.add(btnChangeAge);
		
		btnChangeEmail = new JButton("");
		btnChangeEmail.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it= new Date();
				String email = JOptionPane.showInputDialog("Enter your new mail:");
				try {
					 EmailValidator.validateEmail(email);
					 if (currentUser.getUserType().equals("Free")) {
				        	FreeUser user2=new FreeUser(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),email,currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their email from %s to %s.",currentUser.getNICKNAME(),currentUser.getEmail(),email);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				            }
				        else if(currentUser.getUserType().equals("Hobbyist")) {
				        	HobbyistUser user2=new HobbyistUser(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),email,currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their email from %s to %s.",currentUser.getNICKNAME(),currentUser.getEmail(),email);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				        }
				        else if(currentUser.getUserType().equals("Professional")) {
				        	ProfessionalUser user2=new ProfessionalUser(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),email,currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their email from %s to %s.",currentUser.getNICKNAME(),currentUser.getEmail(),email);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				        }
				        else {
				        	Administrator user2=new Administrator(currentUser.getPassword(),currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),email,currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their email from %s to %s.",currentUser.getNICKNAME(),currentUser.getEmail(),email);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				        }
				}
				catch(InvalidEmailException| EmptyStringException | FileNotFoundException e2 ) {
					Date ft=new Date();
					String errorF=String.format("%s couldn't changed their email due to:\n%s\n",currentUser.getNICKNAME(),e2.getMessage());
					Logging.BaseLogger.error(it, ft, errorF);
					JOptionPane.showMessageDialog(ProfilePageOwn.this, e2.getMessage());
				}
			}
		});
		btnChangeEmail.setIcon(new ImageIcon(ProfilePageOwn.class.getResource("/Icons/Modify_16.png")));
		btnChangeEmail.setBounds(404, 147, 28, 22);
		panel_2.add(btnChangeEmail);
		
		btnChangePassword = new JButton("");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it= new Date();
				String password = JOptionPane.showInputDialog("Enter your new password:");
				try {
					 PasswordValidator.validatePassword(password);
					 if (currentUser.getUserType().equals("Free")) {
				        	FreeUser user2=new FreeUser(password,currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their password from %s to %s.",currentUser.getNICKNAME(),currentUser.getPassword(),password);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				            }
				        else if(currentUser.getUserType().equals("Hobbyist")) {
				        	HobbyistUser user2=new HobbyistUser(password,currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their password from %s to %s.",currentUser.getNICKNAME(),currentUser.getPassword(),password);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				        }
				        else if(currentUser.getUserType().equals("Professional")) {
				        	ProfessionalUser user2=new ProfessionalUser(password,currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their password from %s to %s.",currentUser.getNICKNAME(),currentUser.getPassword(),password);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				        }
				        else {
				        	Administrator user2=new Administrator(password,currentUser.getRealName(),currentUser.getRealSurname(),
				        			Integer.parseInt(currentUser.getAge()),currentUser.getNICKNAME(),currentUser.getEmail(),currentUser.getPp());
				        	try {
								changeUser(user2);
								ProfilePageOwn ppOwnN=new ProfilePageOwn(user2,user2);
								String info=String.format("%s changed their password from %s to %s.",currentUser.getNICKNAME(),currentUser.getPassword(),password);
				                Date ft= new Date();
				                Logging.BaseLogger.info(it, ft, info);
								ppOwnN.setVisible(true);
								dispose();
							} catch (IOException e1) {
					
							}
				        }
				}
				catch(InvalidPasswordException e2 ) {
					JOptionPane.showMessageDialog(ProfilePageOwn.this, e2.getMessage());
				}
			}
		});
		btnChangePassword.setIcon(new ImageIcon(ProfilePageOwn.class.getResource("/Icons/Modify_16.png")));
		btnChangePassword.setBounds(404, 194, 28, 22);
		panel_2.add(btnChangePassword);
		
		btnMessageBox = new JButton("Message Box");
		btnMessageBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageBoxFrame mbf= new MessageBoxFrame(currentUser);
				mbf.setVisible(true);
				dispose();
			}
		});
		btnMessageBox.setIcon(new ImageIcon(ProfilePageOwn.class.getResource("/Icons/messages_24.png")));
		btnMessageBox.setBounds(169, 253, 117, 29);
		panel_2.add(btnMessageBox);
		
		btnDiscoveryPage = new JButton("DISCOVER");
		btnDiscoveryPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiscoverPage dP= new DiscoverPage(user);
				dispose();
				dP.setVisible(true);
			}
		});
		btnDiscoveryPage.setBounds(6, 537, 294, 29);
		contentPane.add(btnDiscoveryPage);
		
		btnProfilePage = new JButton("PROFILE PAGE");
		btnProfilePage.setBounds(600, 537, 294, 29);
		contentPane.add(btnProfilePage);
		
		lblNewLabel = new JLabel("SHARED PHOTOS");
		lblNewLabel.setBounds(182, 306, 129, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("SAVED PHOTOS");
		lblNewLabel_1.setBounds(634, 306, 118, 16);
		contentPane.add(lblNewLabel_1);
		
		
		
		
		
		File folder = new File("./src/images/");

		File[] listOfImages = folder.listFiles();

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
		scrollPane.setBounds(6, 334, 438, 176);

		for (File file : listOfImages) {
			String uName ="";
			int underscore1 = file.getName().indexOf("_");
			if (underscore1  != -1) {
				uName = file.getName().substring(0, underscore1 );
			}
		    if (file.isFile() && file.getName().contains("Shared") &&
		    		uName.equals(user.getNICKNAME())) {
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


		
		
		
		
		
		
		
		JPanel panel3 = new JPanel(new GridLayout(0, 3));

		JScrollPane scrollPane_1 = new JScrollPane(panel3);
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(456, 334, 438, 176);
		if (currentUser.getUserType().equals("Administrator")) {
			panel3.setBackground(new Color(157, 214, 159)); //yeşil
		}
		else if (currentUser.getUserType().equals("Hobbyist")) {
			panel3.setBackground(new Color(61, 177, 197));  // mavi
		}
		else if(currentUser.getUserType().equals("Free")) {
			panel3.setBackground(new Color(204, 171, 196)); //mor
		}
		else if(currentUser.getUserType().equals("Professional")) {
			panel3.setBackground(new Color(194, 33, 46));  //kırmızı
		}
		
		
		
		
		for (File file : listOfImages) {
			String uName2 ="";
			int underscore2 = file.getName().indexOf("_");
			if (underscore2  != -1) {
				uName2 = file.getName().substring(0, underscore2 );
			}
		    if (file.isFile() && file.getName().contains("Saved") &&
		    		uName2.equals(user.getNICKNAME()) &&  !file.getName().contains("Shared")) {
		        try {
		            BufferedImage img = ImageIO.read(file);
		            final String name = file.getName();
		            String fileName = file.getName();
		            String userName = fileName.split("_")[0];

		            JButton photoButton = new JButton("", PhotoSizeDesigner.photoGetterInSize("./src/images/"+name,90,90));
		            photoButton.setPreferredSize(new Dimension(100, 100)); 
		            photoButton.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                  
	                        PhotoInteractionFrame.openPhoto(fileName);
	                
		                        
		                   
		                }
		            });

		            panel3.add(photoButton);
		            int nokta = fileName.lastIndexOf('.');
		            String imgbfrnokta = fileName.substring(0, nokta);
		            String imgaffternokta = fileName.substring(nokta);

		            JButton editButton = new JButton("Edit");
		            editButton.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    try {
								FilterFrame fF=new FilterFrame(imgbfrnokta,imgaffternokta,user);
								dispose();
								fF.setVisible(true);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                }
		            });
		            panel3.add(editButton);

		            JButton shareButton = new JButton("Post");
		            shareButton.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                	File folder6 = new File("./src/images/");

		            		File[] imagesLisst = folder6.listFiles();
		                	ArrayList<String> imagesShared=new ArrayList<>();
		            		for (File file6 :imagesLisst ) {
		            			imagesShared.add(file6.getName());
		            		}
		                	Date it= new Date();
		                    try {
		                    	
		                    	if ( imagesShared.contains(currentUser.getNICKNAME()+"_Shared_"+fileName)) {
		                    		JOptionPane.showMessageDialog(ProfilePageOwn.this,"This photo is already posted!");
		                    		String info=String.format("%s tried to post a photo that is already posted name %s.",
		                    				currentUser.getNICKNAME(),fileName);
					                Date ft= new Date();
					                Logging.BaseLogger.error(it, ft, info);
		                    	}
		                    	else {
									user.shareAPhoto(imgbfrnokta,imgaffternokta);
									SoundPlay.postSound();
									JOptionPane.showMessageDialog(ProfilePageOwn.this,"Your photo is posted");
									ProfilePageOwn ppOwn2=new ProfilePageOwn(currentUser ,user);
									String info=String.format("%s posted the photo named %s.",currentUser.getNICKNAME(),fileName);
					                Date ft= new Date();
					                Logging.BaseLogger.info(it, ft, info);
									dispose();
									ppOwn2.setVisible(true);
		                    	}
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
		                }
		            });
		            panel3.add(shareButton);

		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }
		}

		contentPane.add(scrollPane_1);
	}
	
	/**
	 * Takes a new user with one of its properties is changed except from their nickname and writes their information 
	 * to UserInfo.txt
	 * @param user5 this is the new user with one of its properties is changed except from their nickname, e.g. name, age...
	 * @throws IOException this is thrown when UserInfo.txt is not found
	 */
	public static void changeUser(Users user5) throws IOException {
		File file = new File("./src/UserInfo/UserInfo.txt");
		Scanner scanner = new Scanner(file);
		String lines="";
     
        while (scanner.hasNextLine()) {
            String[] userInfo = scanner.nextLine().split(",");
//            System.out.println(userInfo[0]+","+userInfo[1]+","+userInfo[2]+","+userInfo[3]+","+
//                    userInfo[4]+","+userInfo[5]+","+userInfo[6]+","+userInfo[7]);
            if (userInfo[0].equals(user5.getNICKNAME()) ) {
                lines+=user5.getNICKNAME()+","+user5.getPassword()+","+user5.getEmail()+","+user5.getRealName()+","+
                		user5.getRealSurname()+","+user5.getUserType()+","+user5.getPp()+","+user5.getAge()+"\n";
            }
            else if(!userInfo[0].equals("") && !userInfo[7].equals("")) {
            	lines+=userInfo[0]+","+userInfo[1]+","+userInfo[2]+","+userInfo[3]+","+
            userInfo[4]+","+userInfo[5]+","+userInfo[6]+","+userInfo[7]+"\n";
            	
            }
        
        }
        System.out.println(lines);
        scanner.close();
//        FileWriter writer = new FileWriter("./src/UserInfo/UserInfo.txt");
//    	writer.write(lines);
    	try (BufferedWriter writer = new BufferedWriter(new FileWriter("./src/UserInfo/UserInfo.txt"))) {
    	    writer.write(lines);
    	} 

        
	}
}
