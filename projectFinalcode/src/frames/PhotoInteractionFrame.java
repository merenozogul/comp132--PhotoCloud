package frames;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import FilterFrames.FilterFrame;
import Filters.Filter;
import PhotoSize.PhotoSizeDesigner;
import UsersAdministrators.Administrator;
import UsersAdministrators.FreeUser;
import UsersAdministrators.HobbyistUser;
import UsersAdministrators.Users;
import exceptions.InvalidImageException;
import photoSharing.SharedPhoto;
import sounds.SoundPlay;
import validators.ImageFileValidator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.Window;

import javax.swing.ImageIcon;
import java.awt.Panel;

public class PhotoInteractionFrame extends JFrame {

	private JPanel contentPane;
	private JButton btnDiscover;
	private JButton btnProflePage;
	private JLabel lblPP;
	private JLabel lblNickname;
	private JLabel lblNewLabel;
	private JLabel lblLikeCounts;
	private JLabel lblNewLabel_1;
	private JLabel lblDislikeCounts;
	private JButton btnFullSize;
	private JButton btnAddComment;
	private JButton btnLike;
	private JButton btnDislike;
	private JLabel lblNewLabel_3;
	private TextArea commentArea;
	private JButton btnSharePhoto;
	private JButton btnDelete;



	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public PhotoInteractionFrame (Users currentUser,String name) throws IOException {
		this.setResizable(false);
		setTitle("PHOTOCLOUD");
	
        Users user=userGetterFromSharedPhoto(name);
        System.out.println(user.getPp());
		String uT=currentUser.getUserType();
		String[] parts = name.split("_");
		String result = String.join("_", java.util.Arrays.copyOfRange(parts, 2, parts.length));
		int lastDot = result.lastIndexOf('.');
		String part1 = result.substring(0, lastDot);
        String part2 = result.substring(lastDot );
       
        SharedPhoto sP=new SharedPhoto(part1,part2,0,0,user);
        
      
		
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
		JLabel lblEditedPicture = new JLabel("");
		lblEditedPicture.setIcon(PhotoSizeDesigner.photoGetterInSize("./src/images/"+sP.getName()+sP.getExtension(),250,250));
		lblEditedPicture.setBounds(66, 139, 250, 250);
		contentPane.add(lblEditedPicture);

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
		
		btnProflePage = new JButton("PROFILE PAGE");
		btnProflePage.addActionListener(new ActionListener() {
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
		btnProflePage.setBounds(600, 537, 294, 29);
		contentPane.add(btnProflePage);
		
		lblPP = new JLabel("");
		System.out.println(user.getPp());
		lblPP.setIcon(PhotoSizeDesigner.photoGetterInSize(user.getPp(),60,60));
		lblPP.setBounds(66, 47, 60, 60);
		contentPane.add(lblPP);
		
		lblNickname = new JLabel("nickname");
		lblNickname.setText(user.getNICKNAME());
		lblNickname.setBounds(149, 71, 182, 16);
		contentPane.add(lblNickname);
		
		lblNewLabel = new JLabel("LIKES:");
		lblNewLabel.setBounds(434, 139, 49, 16);
		contentPane.add(lblNewLabel);
		
		lblLikeCounts = new JLabel("lc");
	
		lblLikeCounts.setBounds(501, 139, 61, 16);
		contentPane.add(lblLikeCounts);
		lblLikeCounts.setText(Integer.toString(sP.getLikes()));
		lblNewLabel_1 = new JLabel("DISLIKES:");
		lblNewLabel_1.setBounds(644, 139, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		lblDislikeCounts = new JLabel("dc");
		lblDislikeCounts.setText(Integer.toString(sP.getDislikes()));
		lblDislikeCounts.setBounds(732, 139, 61, 16);
		contentPane.add(lblDislikeCounts);
		
		btnFullSize = new JButton("SEE THE PHOTO IN FULL SIZE");
		btnFullSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openPhoto(sP.getName()+sP.getExtension());
			}
		});
		btnFullSize.setBounds(66, 427, 250, 29);
		contentPane.add(btnFullSize);
		
		btnAddComment = new JButton("ADD COMMENT");
		btnAddComment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it=new Date();
				String comment1 = JOptionPane.showInputDialog(PhotoInteractionFrame.this, "Write a comment:");
                if (comment1 != null && !comment1.equals("")) {
                    sP.addComment(currentUser.getNICKNAME()+": "+comment1);
                    SoundPlay.commetSound();
                    String commentText2 = readFromFile("./src/SharedPhotoComments/"+sP.getName()+sP.getExtension()+"_comments.txt");
            		commentArea.setText(commentText2);
            		String info=String.format("%s posted a comment:%s to the photo:%s .",currentUser.getNICKNAME(),comment1,name);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
                }
			}
		});
		btnAddComment.setBounds(434, 393, 318, 29);
		contentPane.add(btnAddComment);
		
		btnLike = new JButton("");
		btnLike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				Date it = new Date();
				if(!sP.getLikePersons().contains(currentUser.getNICKNAME())&& !sP.getDislikePersons().contains(currentUser.getNICKNAME())) {
					btnLike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/likeYes_24.png")));
					sP.likeAPhoto(currentUser);
					String info=String.format("%s liked the photo named %s.",currentUser.getNICKNAME(),name);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				}
				else if(!sP.getLikePersons().contains(currentUser.getNICKNAME())&& sP.getDislikePersons().contains(currentUser.getNICKNAME())) {
					btnLike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/likeYes_24.png")));
					btnDislike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/dislikeNo_24.png")));
					sP.likeAPhoto(currentUser);
					String info=String.format("%s liked the photo named %s.",currentUser.getNICKNAME(),name);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
					
				}
				else if(sP.getLikePersons().contains(currentUser.getNICKNAME())&& !sP.getDislikePersons().contains(currentUser.getNICKNAME())) {
					btnLike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/likeNo_24.png")));
					sP.notLikeAPhoto(currentUser);
					System.out.println(11);
					String info=String.format("%s took his like back from the photo named %s.",currentUser.getNICKNAME(),name);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				}
				
				lblLikeCounts.setText(Integer.toString(sP.getLikes()));
				lblDislikeCounts.setText(Integer.toString(sP.getDislikes()));
				SoundPlay.likedDislikedSound();
			}
		});
		if(!sP.getLikePersons().contains(currentUser.getNICKNAME())) {
			btnLike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/likeNo_24.png")));
		}
		else {
			btnLike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/likeYes_24.png")));
		}
		btnLike.setBounds(764, 393, 29, 29);
		contentPane.add(btnLike);
		
		btnDislike = new JButton("");
		btnDislike.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date it =new Date();
				
				if(!sP.getDislikePersons().contains(currentUser.getNICKNAME())&& !sP.getLikePersons().contains(currentUser.getNICKNAME())) {
					btnDislike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/dislikeYes_24.png")));
					sP.dislikeAPhoto(currentUser);
					String info=String.format("%s disliked the photo named %s.",currentUser.getNICKNAME(),name);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
					
				}
				else if(!sP.getLikePersons().contains(currentUser.getNICKNAME())&& sP.getDislikePersons().contains(currentUser.getNICKNAME())) {
					btnDislike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/dislikeNo_24.png")));
					sP.notDislike(currentUser);
					System.out.println(12);
					String info=String.format("%s took his dislike back from the photo named %s.",currentUser.getNICKNAME(),name);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				}
				else if(sP.getLikePersons().contains(currentUser.getNICKNAME())&& !sP.getDislikePersons().contains(currentUser.getNICKNAME())) {
					btnDislike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/dislikeYes_24.png")));
					btnLike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/likeNo_24.png")));
					sP.dislikeAPhoto(currentUser);
					String info=String.format("%s disliked the photo named %s.",currentUser.getNICKNAME(),name);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				}

				lblDislikeCounts.setText(Integer.toString(sP.getDislikes()));
				lblLikeCounts.setText(Integer.toString(sP.getLikes()));
				SoundPlay.likedDislikedSound();
			}
		});
		if(!sP.getDislikePersons().contains(currentUser.getNICKNAME())) {
			btnDislike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/dislikeNo_24.png")));
		}
		else {
			btnDislike.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/dislikeYes_24.png")));
		}
		btnDislike.setBounds(805, 393, 29, 29);
		contentPane.add(btnDislike);
		String commentText = readFromFile("./src/SharedPhotoComments/"+sP.getName()+sP.getExtension()+"_comments.txt");
		
		
		lblNewLabel_3 = new JLabel("COMMENTS:");
		lblNewLabel_3.setBounds(572, 167, 88, 16);
		contentPane.add(lblNewLabel_3);
		
		commentArea=new TextArea();
		commentArea.setEditable(false);
		commentArea.setColumns(47);
		commentArea.setRows(10);
		commentArea.setBounds(434, 187, 395, 195);
		Panel pnlComment = new Panel();
		pnlComment.add(commentArea);
		String commentText2 = readFromFile("./src/SharedPhotoComments/"+sP.getName()+sP.getExtension()+"_comments.txt");
		commentArea.setText(commentText2);
		pnlComment.setBounds(434, 187, 402, 204);
		contentPane.add(pnlComment);
		if (currentUser.getUserType().equals("Administrator")) {
			commentArea.setBackground(new Color(157, 214, 159)); //yeşil
		}
		else if (currentUser.getUserType().equals("Hobbyist")) {
			commentArea.setBackground(new Color(61, 177, 197));  // mavi
		}
		else if(currentUser.getUserType().equals("Free")) {
			commentArea.setBackground(new Color(204, 171, 196)); //mor
		}
		else if(currentUser.getUserType().equals("Professional")) {
			commentArea.setBackground(new Color(194, 33, 46));  //kırmızı
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
		                JOptionPane.showMessageDialog(PhotoInteractionFrame.this, "Your photo is shared!\nFor now only you can see it.\nGo to your profile to post it!");
		               
		            } catch (IOException | InvalidImageException e1) {
		            	Date ft=new Date();
						String errorF="Photo sharing failed.";
						Logging.BaseLogger.error(it, ft, errorF);
		            	JOptionPane.showMessageDialog(PhotoInteractionFrame.this,e1.getMessage());
		            	
		            }

		        }
		    }
		});
		
		btnSharePhoto.setBounds(312, 537, 276, 29);
		contentPane.add(btnSharePhoto);
		
		btnDelete = new JButton("DELETE THE PHOTO");
		btnDelete.setIcon(new ImageIcon(PhotoInteractionFrame.class.getResource("/Icons/Erase_24.png")));
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it = new Date();
				File photoDelete = new File("./src/images/"+name);
	            photoDelete.delete();
	            File likeDelete= new File("./src/SharedphotoComments/"+name+"_likes.txt");
	            likeDelete.delete();
	            File dislikeDelete= new File("./src/SharedphotoComments/"+name+"_dislikes.txt");
	            dislikeDelete.delete();
	            File commentsDelete= new File("./src/SharedphotoComments/"+name+"_comments.txt");
	            commentsDelete.delete();
	            SoundPlay.deleteSound();
	            String info=String.format("%s deleted the photo named %s.",currentUser.getNICKNAME(),name);
                Date ft= new Date();
                Logging.BaseLogger.info(it, ft, info);
	            JOptionPane.showMessageDialog(PhotoInteractionFrame.this, "The photo is deleted!");
	            DiscoverPage dP =new DiscoverPage(currentUser); 
	            
	            dispose();
	            dP.setVisible(true);
			}
		});
		btnDelete.setBounds(655, 60, 178, 41);
		if(!(currentUser.getNICKNAME().equals(user.getNICKNAME()) || uT.equals("Administrator"))) {
			btnDelete.setVisible(false);
		}
		contentPane.add(btnDelete);

	}
    public static void openPhoto(String image) {
        try {
            Desktop.getDesktop().open(new File("./src/images/"+image));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static String readFromFile(String filePath) {
        StringBuilder content = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                content.append(scanner.nextLine()).append("\n");
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return content.toString();
    }
    
    
    /**
	 * Takes an images name and returns the User that owns that image
	 * @param name the name of the given photo
	 * @return returns the user corresponding that nickname
	 * @throws FileNotFoundException it is thrown it UserInfo.txt is not found
	 */
    public static Users userGetterFromSharedPhoto(String name) throws FileNotFoundException {
    	int firstUnderscore =name.indexOf('_');
		String part11 = name.substring(0, firstUnderscore);
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
            if (userInfo[0].equals(part11) ) {
  
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
        if (ui8.equals("Free")) {
        	FreeUser user=new FreeUser(ui1,ui2,ui3,Integer.parseInt(ui4),ui5,ui6,ui7);
        	return user;
        }
        else if (ui8.equals("Hobbyist")) {
        	HobbyistUser user=new HobbyistUser(ui1,ui2,ui3,Integer.parseInt(ui4),ui5,ui6,ui7);
        	return user;
        }
        else if (ui8.equals("Professional")) {
        	FreeUser user=new FreeUser(ui1,ui2,ui3,Integer.parseInt(ui4),ui5,ui6,ui7);
        	return user;
        }
        else if (ui8.equals("Administrator")){
        	Administrator user=new Administrator(ui1,ui2,ui3,Integer.parseInt(ui4),ui5,ui6,ui7);
        	return user;
       
        }
        return null;
        


    }
}
