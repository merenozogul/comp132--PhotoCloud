package frames;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import UsersAdministrators.Users;
import exceptions.InvalidImageException;
import sounds.SoundPlay;
import validators.ImageFileValidator;
import javax.swing.JTextField;
import javax.swing.ImageIcon;

public class MessagingFrame extends JFrame {

	private JPanel contentPane;
	private TextArea messageArea;
	private JButton btnSharePhoto;
	private JButton btnProflePage;
	private JButton btnDiscover;
	private JTextField tfMessageText;
	private JButton btnMessageSend;
	private JLabel lblMessageGetter;



	/**
	 * Create the frame.
	 */
	public MessagingFrame(Users currentUser,Users otherUser) {
		this.setResizable(false);
		File messageFile;
		if(currentUser.getNICKNAME().compareTo(otherUser.getNICKNAME())<0) {
			messageFile = new File("./src/Messages/"+currentUser.getNICKNAME()
			+otherUser.getNICKNAME()+".txt");
		}
		else {
			messageFile  = new File("./src/Messages/"+otherUser.getNICKNAME()+currentUser.getNICKNAME()
			+".txt");
		}
	
        if (messageFile .exists()) {
       
        } else {
            try {
				FileWriter fileWriter = new FileWriter(messageFile );
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
		
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
		String commentText2;
		if(currentUser.getNICKNAME().compareTo(otherUser.getNICKNAME())<0) {
			commentText2 = PhotoInteractionFrame.readFromFile("./src/Messages/"+currentUser.getNICKNAME()
			+otherUser.getNICKNAME()+".txt");
		}
		else {
			commentText2 = PhotoInteractionFrame.readFromFile("./src/Messages/"+otherUser.getNICKNAME()
			+currentUser.getNICKNAME()+".txt");
		}

		contentPane.setLayout(null);

		
		messageArea=new TextArea();
//		contentPane.add(messageArea);
		messageArea.setEditable(false);
		messageArea.setColumns(106);
		messageArea.setRows(28);
		messageArea.setBounds(200, 200, 500, 333);
		Panel pnlMessage=new Panel();
		pnlMessage.add(messageArea);
		messageArea.setText(commentText2);
		pnlMessage.setBounds(21,24,867,469);
		contentPane.add(pnlMessage);
		
		
		
		if (currentUser.getUserType().equals("Administrator")) {
			messageArea.setBackground(new Color(157, 214, 159)); //yeşil
		}
		else if (currentUser.getUserType().equals("Hobbyist")) {
			messageArea.setBackground(new Color(61, 177, 197));  // mavi
		}
		else if(currentUser.getUserType().equals("Free")) {
			messageArea.setBackground(new Color(204, 171, 196)); //mor
		}
		else if(currentUser.getUserType().equals("Professional")) {
			messageArea.setBackground(new Color(194, 33, 46));  //kırmızı
		}
		
		
		
		btnSharePhoto = new JButton("SHARE");
		btnSharePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it =new Date();
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
		                JOptionPane.showMessageDialog(MessagingFrame.this, "Your photo is shared!\nFor now only you can see it.\nGo to your profile to post it!");
		               
		            } catch (IOException | InvalidImageException e1) {
		            	Date ft=new Date();
						String errorF="Photo sharing failed.";
						Logging.BaseLogger.error(it, ft, errorF);
		            	JOptionPane.showMessageDialog(MessagingFrame.this,e1.getMessage());
		            	
		            }

		        }
		    }
		});
		
		btnSharePhoto.setBounds(312, 537, 276, 29);
		contentPane.add(btnSharePhoto);
		
		
		btnProflePage = new JButton("PROFILE PAGE");
		btnProflePage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ProfilePageOwn ppOwn= new ProfilePageOwn(currentUser,currentUser);
					ppOwn.setVisible(true);
					dispose();
				} catch (IOException e1) {
			
				
				}
			}
		});
		btnProflePage.setBounds(600, 537, 294, 29);
		contentPane.add(btnProflePage);
		
		
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
		
		tfMessageText = new JTextField();
		tfMessageText.setBounds(21, 499, 798, 26);
		contentPane.add(tfMessageText);
		tfMessageText.setColumns(10);
		
		btnMessageSend = new JButton("");
		btnMessageSend.setIcon(new ImageIcon(MessagingFrame.class.getResource("/Icons/messagesend_24.png")));
		btnMessageSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it=new Date();
				if(!tfMessageText.getText().equals("")) {
					currentUser.sendMessage(otherUser, tfMessageText.getText());
					String info=String.format("%s send a message to %s message: %s.",currentUser.getNICKNAME(),otherUser.getNICKNAME(),tfMessageText.getText());
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
					SoundPlay.sendMessageSound();
					String messageText2;
					if(currentUser.getNICKNAME().compareTo(otherUser.getNICKNAME())<0) {
						messageText2=PhotoInteractionFrame.readFromFile("./src/Messages/"+currentUser.getNICKNAME()
						+otherUser.getNICKNAME()+".txt");
					}
					else {
						messageText2=PhotoInteractionFrame.readFromFile("./src/Messages/"+otherUser.getNICKNAME()+currentUser.getNICKNAME()
						+".txt");
					}
					
					messageArea.setText(messageText2);
					tfMessageText.setText("");
				}
			}
		});
		btnMessageSend.setBounds(831, 498, 57, 29);
		contentPane.add(btnMessageSend);
		setContentPane(contentPane);
		
		lblMessageGetter = new JLabel("New label");
		lblMessageGetter.setBounds(21, 5, 505, 16);
		contentPane.add(lblMessageGetter);
		lblMessageGetter.setText("Send message to: "+otherUser.getNICKNAME());
		
		
	}
}
