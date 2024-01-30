package frames;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FilterFrames.FilterFrame;
import PhotoSize.PhotoSizeDesigner;
import UsersAdministrators.FreeUser;
import UsersAdministrators.Users;
import exceptions.InvalidImageException;

import main.StringHandlerr;
import sounds.SoundPlay;
import validators.ImageFileValidator;

import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class DiscoverPage extends JFrame{

	private JPanel contentPane;
	private JButton btnSharePhoto;
	private JTextField tfSearch;
	private JButton btnSearch;
	private JLabel lblNewLabel_1;



	/**
	 * Create the frame.
	 */
	public DiscoverPage(Users currentUser) {
		this.setResizable(false);
		setTitle("PHOTOCLOUD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		contentPane = new JPanel();



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

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel2 = new JPanel();
		panel2.setBounds(6, 6, 888, 29);
		contentPane.add(panel2);
		panel2.setLayout(null);
		
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
		
		JLabel lblNewLabel = new JLabel("DISCOVER");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 21));
		lblNewLabel.setBounds(45, 6, 107, 23);
		panel2.add(lblNewLabel);
		
		lblNewLabel_1 = new JLabel("Go to another user's profile:");
		lblNewLabel_1.setBounds(220, 6, 177, 22);
		panel2.add(lblNewLabel_1);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(409, 3, 177, 23);
		panel2.add(tfSearch);
		tfSearch.setColumns(10);
		
		btnSearch = new JButton("");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it=new Date();
				try {
					if(LogInPage.userGetterFromNickName(tfSearch.getText())!=null && !tfSearch.getText().equals(currentUser.getNICKNAME())) {
						ProfilePageOther ppOther=new ProfilePageOther(currentUser,LogInPage.userGetterFromNickName(tfSearch.getText()));
						dispose();
						ppOther.setVisible(true);
					}
					else if(LogInPage.userGetterFromNickName(tfSearch.getText())!=null &&
						tfSearch.getText().equals(currentUser.getNICKNAME())) {
						ProfilePageOwn ppOwn= new ProfilePageOwn(currentUser,LogInPage.userGetterFromNickName(tfSearch.getText()));
						dispose();
						ppOwn.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(DiscoverPage.this, "No user found!\nTry another nickname.");
						Date ft=new Date();
						String errorF=String.format("%s named user couldn't found.",tfSearch.getText());
						Logging.BaseLogger.error(it, ft, errorF);
					}
				}
				catch(FileNotFoundException e1){
					
				} catch (IOException e1) {
					

				}
			}
		});
		btnSearch.setIcon(new ImageIcon(DiscoverPage.class.getResource("/Icons/Search_24.png")));
		btnSearch.setBounds(598, 3, 40, 26);
		panel2.add(btnSearch);
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SoundPlay.logoutSound();
				Date it =new Date ();
				LogInPage lIP=new LogInPage();
				Date ft=new Date();
                String info=String.format("%s logged out from PhotoCloud.",currentUser.getNICKNAME());
                Logging.BaseLogger.info(it, ft,info );
				JOptionPane.showMessageDialog(DiscoverPage.this, "Logged Out!");
				lIP.setVisible(true);
				dispose();
			}
			
		});
		btnLogOut.setIcon(new ImageIcon(DiscoverPage.class.getResource("/Icons/LogOut_24.png")));
		btnLogOut.setBounds(721, 0, 117, 29);
		panel2.add(btnLogOut);
		
		JButton btnDiscoverPage = new JButton("DISCOVER");
		btnDiscoverPage.setContentAreaFilled(true);
		btnDiscoverPage.setBackground(Color.BLUE);
		btnDiscoverPage.setBounds(6, 537, 294, 29);
		contentPane.add(btnDiscoverPage);
		
		JButton btnProfilePage = new JButton("PROFILE PAGE");
		btnProfilePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ProfilePageOwn ppOwn= new ProfilePageOwn(currentUser,currentUser);
					ppOwn.setVisible(true);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
			}
		});
		btnProfilePage.setBounds(600, 537, 294, 29);
		contentPane.add(btnProfilePage);
		
		File folder = new File("./src/images/");

		File[] listOfImages = folder.listFiles();

		JPanel panel = new JPanel(new GridLayout(0, 2)); 
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

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(50, 50, 800, 470);

		for (File file : listOfImages) {
		    if (file.isFile() && file.getName().toLowerCase().contains("shared")) {
		        try {
		            BufferedImage img = ImageIO.read(file);
		            final String name = file.getName();

		            String fileName = file.getName();
		            String userName = fileName.split("_")[0];
		            String textButton = StringHandlerr.centerString(60, userName);

		            JButton photoButton = new JButton("", PhotoSizeDesigner.photoGetterInSize("./src/images/"+name,220,220));
		            photoButton.setPreferredSize(new Dimension(300, 275));
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

		            panel.add(photoButton);

		            JButton nameButton = new JButton(textButton);
		            nameButton.setPreferredSize(new Dimension(300, 275)); 
		            nameButton.addActionListener(new ActionListener() {
		            	 public void actionPerformed(ActionEvent e) {
			                    try {
			                    	if (currentUser.getNICKNAME().equals(PhotoInteractionFrame.userGetterFromSharedPhoto(fileName).getNICKNAME())) {
			                    		ProfilePageOwn pp = new ProfilePageOwn(currentUser,PhotoInteractionFrame.userGetterFromSharedPhoto(fileName));
			                    		dispose();
				                        pp.setVisible(true);
			                    	}
			                    	else {
			                    		ProfilePageOther pp=new ProfilePageOther(currentUser,PhotoInteractionFrame.userGetterFromSharedPhoto(fileName));
			                    		dispose();
				                        pp.setVisible(true);
			                    	}
			                        
			                        
			                    } catch (IOException e1) {
			                        e1.printStackTrace();
			                    }
			                }
		            	
		            });

		            panel.add(nameButton);

		        } catch (IOException ex) {
		            ex.printStackTrace();
		        }
		    }
		}

		contentPane.add(scrollPane);
		
        contentPane.setLayout(null); 
    

		
		btnSharePhoto = new JButton("SHARE");
		btnSharePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it=new Date();
		        JFileChooser fileChooser = new JFileChooser();
		        int bool = fileChooser.showOpenDialog(null);
		        String imgName="";
		        String imgExtension="";
		        if (bool == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            try {
		                Files.copy(selectedFile.toPath(), Paths.get("./src/images/" + selectedFile.getName()), StandardCopyOption.REPLACE_EXISTING);
		            }
		            catch(IOException eee) {
		                eee.printStackTrace(); 
		            }
		            String fileName = selectedFile.getName();
		            int noktaIndex = fileName.lastIndexOf('.');
		            
					if (noktaIndex> 0 && noktaIndex < fileName.length() - 1) {
		                imgName = fileName.substring(0, noktaIndex);
		            }
		            
					if (noktaIndex > 0 && noktaIndex < fileName.length() - 1) {
		                imgExtension = fileName.substring(noktaIndex + 1);
		                imgExtension="."+imgExtension;
		            }
					try {
		            	 ImageFileValidator.validateImageFile(imgName+imgExtension);
		                currentUser.saveAPhoto(imgName, imgExtension);
		                String info=String.format("%s saved %s.",currentUser.getNICKNAME(),selectedFile.getName());
		                Date ft= new Date();
		                Logging.BaseLogger.info(it, ft, info);
		                JOptionPane.showMessageDialog(DiscoverPage.this, "Your photo is shared!\nFor now only you can see it.\nGo to your profile to post it!");
		               
		            } catch (IOException | InvalidImageException e1) {
		            	JOptionPane.showMessageDialog(DiscoverPage.this,e1.getMessage());
		            	Date ft=new Date();
						String errorF="Photo sharing failed.";
						Logging.BaseLogger.error(it, ft, errorF);
		            	
		            }

		        }
		    }
		});
		
		btnSharePhoto.setBounds(312, 537, 276, 29);
		contentPane.add(btnSharePhoto);
		
		
	}
}
