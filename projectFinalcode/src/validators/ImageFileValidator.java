package validators;

import java.nio.file.Path;

import exceptions.InvalidImageException;

public class ImageFileValidator {

	public ImageFileValidator() {
		// TODO Auto-generated constructor stub
	}
	
    private static final String[] IMAGEFILES = {"jpg", "jpeg", "png", "gif"};
    
    
    /**
     * Checks whether a given path of a photo is valid or not. If it is not,
     * 
     * @param path This is the path of a photo
     * @throws InvalidImageException If file is not an image file, this exception is thrown
     */
    public static void validateImageFile(String path) throws InvalidImageException {
        String fileName = path;
        int nokta = fileName.lastIndexOf('.');
        
        if (nokta == -1) {
            throw new InvalidImageException("File does not have an extension.");
        }

        String extension = fileName.substring(nokta + 1);

        for (String ext : IMAGEFILES) {
            if (extension.equals(ext)) {
                return; 
            }
        }

        throw new InvalidImageException("File is not a recognized as an image file.");
    }

}
