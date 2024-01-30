package FilterFrames;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Filters.Filter;
import PhotoSize.PhotoSizeDesigner;
import UsersAdministrators.Users;
import exceptions.InvalidImageException;
import frames.DiscoverPage;
import frames.PhotoInteractionFrame;
import frames.ProfilePageOwn;
import validators.ImageFileValidator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSlider;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Window;

public class FilterFrame extends JFrame {

	private JPanel contentPane;
	private JLabel lblBlur;
	private JButton btnSEdgeDetection;
	private JButton btnSSharpen;
	private JButton btnSBlur;
	private JButton btnSGrayScale;
	private JButton btnSBrightness;
	private JButton btnSContrast;
	private JSlider sldBlur;
	private JSlider sldSharpen;
	private JSlider sldGrayScale;
	private JSlider sldEdge;
	private JSlider sldBrightness;
	private JSlider sldContrast;
	private JButton btnDiscover;
	private JButton btnProflePage;
	private JLabel lblNewLabel;
	private JLabel lblNotifier;
	private JLabel lblEditedPicture;
	private JButton btnFullSize;
	private JButton btnSharePhoto;



	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public FilterFrame(final String imgName,final String imgExtension,final Users currentUser) throws IOException {
		this.setResizable(false);
		setTitle("PHOTOCLOUD");
		String uT=currentUser.getUserType();
		
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
		
		lblEditedPicture = new JLabel("");
		lblEditedPicture.setIcon(PhotoSizeDesigner.photoGetterInSize("./src/images/"+imgName+imgExtension,192,192));
		lblEditedPicture.setBounds(29, 165, 190, 192);
		contentPane.add(lblEditedPicture);
		
		lblBlur = new JLabel("BLUR");
		lblBlur.setBounds(245, 154, 61, 16);
		contentPane.add(lblBlur);
		
		JLabel lblNewLabel_1_1 = new JLabel("SHARPEN");
		lblNewLabel_1_1.setBounds(245, 190, 61, 16);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("GRAY SCALE");
		lblNewLabel_1_2.setBounds(245, 231, 81, 16);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("EDGE DETECTION");
		lblNewLabel_1_3.setBounds(245, 269, 110, 16);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("BRIGTNESS");
		lblNewLabel_1_4.setBounds(245, 311, 110, 16);
		contentPane.add(lblNewLabel_1_4);
		
		JLabel lblNewLabel_1_5 = new JLabel("CONTRAST");
		lblNewLabel_1_5.setBounds(245, 351, 110, 16);
		contentPane.add(lblNewLabel_1_5);
		
		btnFullSize = new JButton("SEE THE PHOTO IN FULL SIZE");
		btnFullSize.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnFullSize.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PhotoInteractionFrame.openPhoto(imgName+imgExtension);
			}
		});
		btnFullSize.setBounds(29, 379, 190, 29);
		contentPane.add(btnFullSize);
		
		btnSBlur = new JButton("SAVE");
		btnSBlur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Date it=new Date();
					Filter.blurFilter(imgName,imgExtension,sldBlur.getValue()*2+1,currentUser.getNICKNAME());
					String info=String.format("Blurring filter applied to %s.",imgName+imgExtension);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(FilterFrame.this, "Your photo is edited and saved!");
			}
		});
		btnSBlur.setBounds(660, 149, 140, 29);
		contentPane.add(btnSBlur);
		
		sldBlur = new JSlider();
		sldBlur.setValue(0);
		sldBlur.setForeground(new Color(0, 0, 0));
		sldBlur.setBackground(new Color(165, 178, 206));
		sldBlur.setMaximum(10);
		sldBlur.setBounds(419, 149, 190, 29);
		contentPane.add(sldBlur);
		
		sldSharpen = new JSlider();
		sldSharpen.setValue(0);
		sldSharpen.setMaximum(10);
		sldSharpen.setForeground(Color.BLACK);
		sldSharpen.setBackground(new Color(165, 178, 206));
		sldSharpen.setBounds(419, 190, 190, 29);
		contentPane.add(sldSharpen);
		
		sldGrayScale = new JSlider();
		if(uT.equals("Free")|| uT.equals("Hobbyist")) {
			sldGrayScale.setEnabled(false);
		}
		sldGrayScale.setValue(0);
		sldGrayScale.setMaximum(100);
		sldGrayScale.setForeground(Color.BLACK);
		sldGrayScale.setBackground(new Color(165, 178, 206));
		sldGrayScale.setBounds(419, 226, 190, 29);
		contentPane.add(sldGrayScale);
		
		sldEdge = new JSlider();
		if(uT.equals("Free")|| uT.equals("Hobbyist")) {
			sldEdge.setEnabled(false);
		}
		sldEdge.setValue(0);
		sldEdge.setForeground(Color.BLACK);
		sldEdge.setBackground(new Color(165, 178, 206));
		sldEdge.setBounds(419, 265, 190, 29);
		contentPane.add(sldEdge);
		
		sldBrightness = new JSlider();
		if(uT.equals("Free")) {
			sldBrightness.setEnabled(false);
		}
		sldBrightness.setValue(0);
		sldBrightness.setMaximum(255);
		sldBrightness.setForeground(Color.BLACK);
		sldBrightness.setBackground(new Color(165, 178, 206));
		sldBrightness.setBounds(419, 306, 190, 29);
		contentPane.add(sldBrightness);
		
		sldContrast = new JSlider();
		if(uT.equals("Free")) {
			sldContrast.setEnabled(false);
		}
		sldContrast.setValue(0);
		sldContrast.setMaximum(7);
		sldContrast.setForeground(Color.BLACK);
		sldContrast.setBackground(new Color(165, 178, 206));
		sldContrast.setBounds(419, 347, 190, 29);
		contentPane.add(sldContrast);
		
		btnSSharpen = new JButton("SAVE");
		btnSSharpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date it = new Date();
					Filter.sharpenFilter(imgName, imgExtension, sldSharpen.getValue(), currentUser.getNICKNAME());
					String info=String.format("Sharpening filter applied to %s.",imgName+imgExtension);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(FilterFrame.this, "Your photo is edited and saved!");
			}
		});
		btnSSharpen.setBounds(660, 185, 140, 29);
		contentPane.add(btnSSharpen);
		
		btnSGrayScale = new JButton("SAVE");
		btnSGrayScale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date it =new Date();
					Filter.grayScaleFilter(imgName, imgExtension, sldGrayScale.getValue(), currentUser.getNICKNAME());
					String info=String.format("Grayscale filter applied to %s.",imgName+imgExtension);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(FilterFrame.this, "Your photo is edited and saved!");
			}
		});
		if(uT.equals("Free")|| uT.equals("Hobbyist")) {
			btnSGrayScale.setEnabled(false);
		}
		btnSGrayScale.setBounds(660, 226, 140, 29);
		contentPane.add(btnSGrayScale);
		
		btnSEdgeDetection = new JButton("SAVE");
		btnSEdgeDetection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date it =new Date();
					Filter.edgeDetectionFilter(imgName, imgExtension, sldEdge.getValue(), currentUser.getNICKNAME());
					String info=String.format("Edge detection filter applied to %s.",imgName+imgExtension);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				} catch (IOException e1) {
				
				}
				JOptionPane.showMessageDialog(FilterFrame.this, "Your photo is edited and saved!");
			}
		});
		if(uT.equals("Free")|| uT.equals("Hobbyist")) {
			btnSEdgeDetection.setEnabled(false);
		}
		btnSEdgeDetection.setBounds(660, 264, 140, 29);
		contentPane.add(btnSEdgeDetection);
		
		btnSBrightness = new JButton("SAVE");
		btnSBrightness.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date it = new Date();
					Filter.brightnessFilter(imgName, imgExtension, sldBrightness.getValue(), currentUser.getNICKNAME());
					String info=String.format("Brightness filter applied to %s.",imgName+imgExtension);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				} catch (IOException e1) {
					
				}
				JOptionPane.showMessageDialog(FilterFrame.this, "Your photo is edited and saved!");
			}
		});
		if(uT.equals("Free")) {
			btnSBrightness.setEnabled(false);
		}
		btnSBrightness.setBounds(660, 306, 140, 29);
		contentPane.add(btnSBrightness);
		
		btnSContrast = new JButton("SAVE");
		btnSContrast.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Date it =new Date();
					Filter.contrastFilter(imgName, imgExtension, sldContrast.getValue(), currentUser.getNICKNAME());
					String info=String.format("Contrast filter applied to %s.",imgName+imgExtension);
	                Date ft= new Date();
	                Logging.BaseLogger.info(it, ft, info);
				} catch (IOException e1) {
	
				}
				JOptionPane.showMessageDialog(FilterFrame.this, "Your photo is edited and saved!");
			}
		});
		if(uT.equals("Free")) {
			btnSContrast.setEnabled(false);
		}
		btnSContrast.setBounds(660, 346, 140, 29);
		contentPane.add(btnSContrast);
	
		
		btnSharePhoto = new JButton("SHARE");
		btnSharePhoto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date it=new Date();
		        JFileChooser fileChooser = new JFileChooser();
		        int returnValue = fileChooser.showOpenDialog(null);
		        String imgName="";
		        String imgExtension="";
		        if (returnValue == JFileChooser.APPROVE_OPTION) {
		            File secFile = fileChooser.getSelectedFile();
		            try {
		                Files.copy(secFile.toPath(), Paths.get("./src/images/" + secFile.getName()), StandardCopyOption.REPLACE_EXISTING);
		            }
		            catch(IOException eee) {
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
		                JOptionPane.showMessageDialog(FilterFrame.this, "Your photo is shared!\nFor now only you can see it.\nGo to your profile to post it!");
		               
		            } catch (IOException | InvalidImageException e1) {
		            	JOptionPane.showMessageDialog(FilterFrame.this,e1.getMessage());
		            	Date ft=new Date();
						String errorF="Photo sharing failed.";
						Logging.BaseLogger.error(it, ft, errorF);
		            }


		        }
		    }
		});
		
		btnSharePhoto.setBounds(312, 537, 276, 29);
		contentPane.add(btnSharePhoto);
		
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
					ProfilePageOwn ppOwn=new ProfilePageOwn(currentUser,currentUser);
					dispose();
					ppOwn.setVisible(true);
				} catch (IOException e1) {

				}
			}
		});
		btnProflePage.setBounds(600, 537, 294, 29);
		contentPane.add(btnProflePage);
		
		lblNewLabel = new JLabel("FILTER");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 30));
		lblNewLabel.setBounds(412, 19, 110, 51);
		contentPane.add(lblNewLabel);
		
		lblNotifier = new JLabel("New label");
		lblNotifier.setBounds(50, 118, 824, 16);
		contentPane.add(lblNotifier);
		if(uT.equals("Free")) {
			lblNotifier.setText("Free users can only use this filters: Blur, Sharpen. Only one filter can be applied at a time.");
		}
		else if (uT.equals("Hobbyist")) {
			lblNotifier.setText("Hobbyist users can only use this filters: Blur, Sharpen, Brightness, Contrast. Only one filter can be applied at a time.");
		}
		else if (uT.equals("Professional")) {
			lblNotifier.setText("Professional users can use all filters. Only one filter can be applied at a time.");
		}
		else if (uT.equals("Administrator")) {
			lblNotifier.setText("Administrator can use all filters. Only one filter can be applied at a time.");
		}
		
	}
}
