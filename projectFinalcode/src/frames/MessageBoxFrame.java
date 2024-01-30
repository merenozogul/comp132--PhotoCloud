package frames;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import UsersAdministrators.Users;
import exceptions.InvalidImageException;
import main.StringHandlerr;
import validators.ImageFileValidator;
import javax.swing.JLabel;
import java.awt.Font;

public class MessageBoxFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnSharePhoto;
	private JButton btnDiscover;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MessageBoxFrame frame = new MessageBoxFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MessageBoxFrame(Users currentUser) {
		this.setResizable(false);
		setTitle("PHOTOCLOUD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 600);
		this.setResizable(false);
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
		           
		            }
		            String fileName = selectedFile.getName();
		            int noktaIndex = fileName.lastIndexOf('.');
		            
					if (noktaIndex > 0 && noktaIndex< fileName.length() - 1) {
		                imgName = fileName.substring(0, noktaIndex);
		            }
		            
					if (noktaIndex > 0 && noktaIndex < fileName.length() - 1) {
		                imgExtension = fileName.substring(noktaIndex + 1).toLowerCase();
		                imgExtension="."+imgExtension;
		            }
					try {
		            	 ImageFileValidator.validateImageFile(imgName+imgExtension);
		                currentUser.saveAPhoto(imgName, imgExtension);
		                String info=String.format("%s saved %s.",currentUser.getNICKNAME(),selectedFile.getName());
		                Date ft= new Date();
		                Logging.BaseLogger.info(it, ft, info);
		                JOptionPane.showMessageDialog(MessageBoxFrame.this, "Your photo is shared!\nFor now only you can see it.\nGo to your profile to post it!");
		               
		            } catch (IOException | InvalidImageException e1) {
		            	JOptionPane.showMessageDialog(MessageBoxFrame.this,e1.getMessage());
		            	Date ft=new Date();
						String errorF="Photo sharing failed.";
						Logging.BaseLogger.error(it, ft, errorF);
		            	
		            }

		        }
		    }
		});
		contentPane.setLayout(null);
		
		btnSharePhoto.setBounds(312, 537, 276, 29);
		contentPane.add(btnSharePhoto);
		
		
		JButton btnProfilePage = new JButton("PROFILE PAGE");
		btnProfilePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ProfilePageOwn ppOwn= new ProfilePageOwn(currentUser,currentUser);
					ppOwn.setVisible(true);
					dispose();
				} catch (IOException e1) {
					
				}

				
			}
		});
		btnProfilePage.setBounds(600, 537, 294, 29);
		contentPane.add(btnProfilePage);
		
		
		btnDiscover = new JButton("DISCOVER");
		btnDiscover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DiscoverPage dP=new DiscoverPage(currentUser);
				dispose();
				dP.setVisible(true);
			}
		});
		btnDiscover.setBounds(6, 537, 294, 29);
		contentPane.add(btnDiscover);
		
		
		
		JPanel panel = new JPanel();
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
        GridLayout layout = new GridLayout(10, 1); 
        panel.setLayout(layout);

        try {
            Scanner scanner = new Scanner(new File("./src/UserInfo/UserInfo.txt"));
            while(scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] infoss = line.split(",");
                if(infoss.length > 0){
                    String username = infoss[0];
                    JButton button = new JButton(StringHandlerr.centerString(2, username));
                    button.setPreferredSize(new Dimension(30,40));
                    panel.add(button);
                    button.addActionListener(new ActionListener() {
		                @Override
		                public void actionPerformed(ActionEvent e) {
		                    try {
		                        MessagingFrame mf=new MessagingFrame(currentUser,LogInPage.userGetterFromNickName(username));
		                        dispose();
		                        mf.setVisible(true);
		                    } catch (IOException e1) {
		                        e1.printStackTrace();
		                    }
		                }
		            });
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(50, 50, 800, 470);
		contentPane.add(scrollPane );
		
		

		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("MESSAGES");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		lblNewLabel.setBounds(390, 6, 133, 40);
		contentPane.add(lblNewLabel);
		
		
	}

}
