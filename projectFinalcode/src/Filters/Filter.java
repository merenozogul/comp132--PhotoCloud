package Filters;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Filter {

	public Filter() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) throws IOException {
//
//		edgeDetectionFilter("",".jpg",45);
//		blurFilter("ErenJeager",".jpg",13);
//		sharpenFilter("ErenJeager",".jpg",3);
//		grayScaleFilter("ErenJeager",".jpg",38);
//		brightnessFilter("ErenJeager",".jpg",30);
//		contrastFilter("ErenJeager",".jpg",3);
//		
	
	}
	
	/**
	 * Takes an ImageMatrix and by calculating the RGB average of each pixel with its neighbour pixel it blurs the image and returns
	 * the blurred matrices
	 * @param imgM an ImageMatrix object that smoothing will be applied
	 * @param degree the degree of smoothing filter
	 * @return the matrix that blurred
	 */
	public static ImageMatrix smoother(ImageMatrix imgM, int  degree) {
		int edeg=(degree-1)/2;
		ImageMatrix nImgM=new ImageMatrix(imgM.getWidth(),imgM.getHeight());
		for (int h=degree;h<imgM.getHeight()-degree;h++) {
			for (int w=degree;w<imgM.getWidth()-degree;w++) {
				int rSum=0;
				int gSum=0;
				int bSum=0;
				for (int ww=w-edeg;ww<=edeg+w;ww++) {
					for (int hh=h-edeg;hh<=h+edeg;hh++) {
						rSum+=imgM.getRed(ww, hh);
						gSum+=imgM.getGreen(ww, hh);
						bSum+=imgM.getBlue(ww, hh);
					}
				}
				int rAverage=rSum/(degree*degree);
				int gAverage=gSum/(degree*degree);
				int bAverage=bSum/(degree*degree);
				int newRGB=ImageMatrix.convertRGB(rAverage,gAverage,bAverage);
				nImgM.setRGB(w, h, newRGB);
			}
		}
		
		return nImgM;
	}
	
	/**
	 * Takes a matrix and applies sharpening on that matrix and returns the sharpened matrix
	 * 
	 * @param imgM an ImageMatrix object that sharpening will be applied
	 * @param degree the degree of sharpening filter
	 * @return the matrix that is sharpened
	 */
	public static ImageMatrix sharpener(ImageMatrix imgM,int degree) {
		ImageMatrix smoothedImgM=smoother(imgM,degree);
		ImageMatrix nImgM=new ImageMatrix(imgM.getWidth(),imgM.getHeight());
		for (int h=0;h<imgM.getHeight();h++) {
			for (int w=0;w<imgM.getWidth();w++) {
				int rNew=2*imgM.getRed(w, h)-smoothedImgM.getRed(w, h);
				int gNew=2*imgM.getGreen(w, h)-smoothedImgM.getGreen(w, h);
				int bNew=2*imgM.getBlue(w, h)-smoothedImgM.getBlue(w, h);
				int newRGB=ImageMatrix.convertRGB(rNew,gNew,bNew);
				nImgM.setRGB(w, h, newRGB);
			}
		}
		return nImgM;
	}
	
	/**
	 * Takes a matrix and make its pixels more grayed according to degree , 0 does't change 100 makes it fully grayscaled,
	 * and returns new matrix
	 * @param imgM an ImageMatrix object that grayscaling will be applied
	 * @param degree the degree of grayscaling filter
	 * @return the matrix that is grayscaled
	 */
	public static ImageMatrix grayer(ImageMatrix imgM,int degree) {
		double degree2=(double) degree/100;
		ImageMatrix nImgM=new ImageMatrix(imgM.getWidth(),imgM.getHeight());
		for (int h=0;h<imgM.getHeight();h++) {
			for (int w=0;w<imgM.getWidth();w++) {
				int r=imgM.getRed(w, h);
				int g=imgM.getGreen(w, h);
				int b=imgM.getBlue(w, h);
				int rgbAverage=(int)(0.3*r+0.59*g+0.11*b);
				int rNew=(int)(r*(1-degree2)+rgbAverage*degree2);
				int gNew=(int)(g*(1-degree2)+rgbAverage*degree2);
				int bNew=(int)(b*(1-degree2)+rgbAverage*degree2);
				int newRGB=ImageMatrix.convertRGB(rNew,gNew,bNew);
				nImgM.setRGB(w, h, newRGB);
			}
		}
		return nImgM;
		
	}
	/**
	 * Takes a matrix and by increasing its RGB values according to degree returns the new brightened matrix
	 * @param imgM an ImageMatrix object that brighter filter will be applied
	 * @param degree the degree of brighter filter
	 * @return the matrix that is brightened
	 */
	public static ImageMatrix brighter(ImageMatrix imgM, int degree) {

		ImageMatrix nImgM=new ImageMatrix(imgM.getWidth(),imgM.getHeight());
		for (int h=0;h<imgM.getHeight();h++) {
			for (int w=0;w<imgM.getWidth();w++) {
				int r=imgM.getRed(w, h);
				int g=imgM.getGreen(w, h);
				int b=imgM.getBlue(w, h);
				int rNew=Math.min(Math.max(r+degree, 0), 255);
				int gNew=Math.min(Math.max(g+degree, 0), 255);
				int bNew=Math.min(Math.max(b+degree, 0), 255);
				int newRGB=ImageMatrix.convertRGB(rNew,gNew,bNew);
				nImgM.setRGB(w, h, newRGB);
			}
		}
		return nImgM;
	}
	
	
	/**
	 * Creates a new ImageMatrix based on the provided image, then adjusts the contrast 
	 * of each pixel in the new ImageMatrix according to the provided degree of contrast.
	 * The contrast is applied by adjusting the red, green, and blue values of each pixel. These
	 * values are each scaled around a central value (128), multiplied by the degree of contrast, 
	 * and then clamped to the valid RGB range of 0-255.
	 * @param imgM the original image represented as an ImageMatrix
	 * @param degree the degree of contrast filter
	 * @return a new ImageMatrix with the contrast effect applied
	 */
	public static ImageMatrix contraster(ImageMatrix imgM,double degree) {
		ImageMatrix nImgM=new ImageMatrix(imgM.getWidth(),imgM.getHeight());
		for (int h=0;h<imgM.getHeight();h++) {
			for (int w=0;w<imgM.getWidth();w++) {
				int r=imgM.getRed(w, h);
				int g=imgM.getGreen(w, h);
				int b=imgM.getBlue(w, h);
				int rNew1=(int)(((r-128)*degree)+128);
				int gNew1=(int)(((g-128)*degree)+128);
				int bNew1=(int)(((b-128)*degree)+128);
				int rNew=Math.max(0, Math.min(rNew1, 255));
				int gNew=Math.max(0, Math.min(gNew1, 255));
				int bNew=Math.max(0, Math.min(bNew1, 255));
				int newRGB=ImageMatrix.convertRGB(rNew,gNew,bNew);
				nImgM.setRGB(w, h, newRGB);
			}
		}
		return nImgM;
	}
	
	/**
	 * 
	 * The edge detection is performed using a simple gradient method that compares the red color 
	 * values of neighboring pixels. If the gradient magnitude is larger than or equal to the threshold,
	 * the pixel is considered part of an edge and is set to white . If the gradient
	 * magnitude is less than the threshold, the pixel is set to black.(uses sobel)
	 * @param imgM the original image represented as an ImageMatrix
	 * @param degree the threshold value
	 * @return a new ImageMatrix with the edge detection filter applied
	 */
	public static ImageMatrix edgeDetecter(ImageMatrix imgM, int degree) {
		double threshold=degree;
		ImageMatrix nImgM=new ImageMatrix(imgM.getWidth(),imgM.getHeight());
		for (int h=1;h<imgM.getHeight()-1;h++) {
			for (int w=1;w<imgM.getWidth()-1;w++) {
				int xside=imgM.getRed(w+1, h-1)+imgM.getRed(w+1, h)*2+imgM.getRed(w+1, h+1)-
						imgM.getRed(w-1, h-1)-imgM.getRed(w-1, h)*2-imgM.getRed(w-1, h+1);
				int yside=imgM.getRed(w-1, h+1)+imgM.getRed(w, h+1)*2+imgM.getRed(w+1, h+1)-
						imgM.getRed(w-1, h-1)-imgM.getRed(w, h-1)*2-imgM.getRed(w-1, h-1);
				double grad=Math.sqrt((xside*xside+yside*yside));
				if (grad>=threshold) {
					int newRGB=ImageMatrix.convertRGB(255, 255, 255);
					nImgM.setRGB(w,h,newRGB);
				}
				else {
					int newRGB=ImageMatrix.convertRGB(0, 0, 0);
					nImgM.setRGB(w,h,newRGB);
				}
			}
		}
		return nImgM;
	}
	
	
	
	
	/**
	 * By using smoother method, applies blurring on that image and adds user's nickname to images name and returns true if 
	 * the process is successful
	 * @param imName name of the image that the filter will be applied
	 * @param extension extension of the filter will be applied
	 * @param degree the degree of blurring filter
	 * @param userNickName the user who applies this filter
	 * @return new image file that grayscale filter is applied
	 * @throws IOException this exception is thrown when the file is not found
	 */
	public static void blurFilter(String imName, String extension,int degree,String userNickName) throws IOException {
		ImageMatrix image=ImageSecretary.readResourceImage(imName, extension);
		ImageMatrix smoothedImg=smoother(image,degree);
		boolean bool=ImageSecretary.writeFilteredImageToResources(smoothedImg,userNickName+"_"+ imName+"_blur_"+Integer.toString(degree), extension);
	}
	
	/**
	 * By using sharpener method, applies sharpening on that image and adds user's nickname to images name and returns true if 
	 * the process is successful
	 * @param imName name of the image that the filter will be applied
	 * @param extension extension of the filter will be applied
	 * @param degree the degree of sharpen filter
	 * @param userNickName the user who applies this filter
	 * @return new image file that grayscale filter is applied
	 * @throws IOException this exception is thrown when the file is not found
	 */
	public static boolean sharpenFilter(String imName, String extension,int degree,String userNickName) throws IOException {
		ImageMatrix image=ImageSecretary.readResourceImage(imName, extension);
		ImageMatrix smoothedImg=sharpener(image,degree);
		boolean bool=ImageSecretary.writeFilteredImageToResources(smoothedImg,userNickName+"_"+ imName+"_sharpen_"+Integer.toString(degree), extension);
		return bool;
	}
	
	/**
	 * By using grayer method, applies grayscaling on that image and adds user's nickname to images name and returns true if 
	 * the process is successful
	 * @param imName name of the image that the filter will be applied
	 * @param extension extension of the filter will be applied
	 * @param degree the degree of grayscale filter
	 * @param userNickName the user who applies this filter
	 * @return new image file that grayscale filter is applied
	 * @throws IOException this exception is thrown when the file is not found
	 */
	public static boolean grayScaleFilter(String imName, String extension,int degree,String userNickName) throws IOException {
		ImageMatrix image=ImageSecretary.readResourceImage(imName, extension);
		ImageMatrix smoothedImg=grayer(image,degree);
		boolean bool=ImageSecretary.writeFilteredImageToResources(smoothedImg,userNickName+"_"+  imName+"_grayScale_"+Integer.toString(degree), extension);
		return bool;
	}
	
	/**
	 * By using brighter method, applies brightness on that image and adds user's nickname to images name and returns true if 
	 * the process is successful
	 * @param imName name of the image that the filter will be applied
	 * @param extension extension of the filter will be applied
	 * @param degree the degree of brightness filter
	 * @param userNickName the user who applies this filter
	 * @return new image file that brightness filter is applied
	 * @throws IOException this exception is thrown when the file is not found
	 */
	public static boolean brightnessFilter(String imName, String extension,int degree,String userNickName) throws IOException {
		ImageMatrix image=ImageSecretary.readResourceImage(imName, extension);
		ImageMatrix smoothedImg=brighter(image,degree);
		boolean bool=ImageSecretary.writeFilteredImageToResources(smoothedImg,userNickName+"_"+  imName+"_brightness_"+Integer.toString(degree), extension);
		return bool;
	}
	
	/**
	 * By using contraster method, applies contrasting on that image and adds user's nickname to images name and returns true if 
	 * the process is successful
	 * @param imName name of the image that the filter will be applied
	 * @param extension extension of the filter will be applied
	 * @param degree the degree of contrast filter
	 * @param userNickName the user who applies this filter
	 * @return new image file that contrast filter is applied
	 * @throws IOException this exception is thrown when the file is not found
	 */
	public static boolean contrastFilter(String imName, String extension,double degree,String userNickName) throws IOException {
		ImageMatrix image=ImageSecretary.readResourceImage(imName, extension);
		ImageMatrix smoothedImg=contraster(image,degree);
		boolean bool=ImageSecretary.writeFilteredImageToResources(smoothedImg,userNickName+"_"+  imName+"_contrast_"+Double.toString(degree), extension);
		return bool;
	}
	
	/**
	 * By using edge detecter method, applies edge detection on that image and adds user's nickname to images name and returns true if 
	 * the process is successful. Also to increase efficiency, applies graying and smoothing on that image.(uses soble)
	 * @param imName name of the image that the filter will be applied
	 * @param extension extension of the filter will be applied
	 * @param degree the degree of edge detecttion filter
	 * @param userNickName the user who applies this filter
	 * @return new image file that edge detection is applied
	 * @throws IOException this exception is thrown when the file is not found
	 */
	public static boolean edgeDetectionFilter(String imName, String extension,int degree,String userNickName) throws IOException {
		ImageMatrix image=ImageSecretary.readResourceImage(imName, extension);
		ImageMatrix image2=grayer(image,100);
		ImageMatrix image3=smoother(image2,3);
		ImageMatrix smoothedImg=edgeDetecter(image3,degree);
		boolean bool=ImageSecretary.writeFilteredImageToResources(smoothedImg,userNickName+"_"+ imName+"_edgeDetection_"+Integer.toString(degree), extension);
		return bool;
	}


}
