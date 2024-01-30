package PhotoSize;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public abstract class PhotoSizeDesigner {
	/**
	 * Takes an image's path, new width and new height and returns an ImageIcon that is resized and will be used in other frames.
	 * Also add white pixels to empty pixels that will protect the ratio of the photo
	 * @param photo Path the path of the photo
	 * @param width required new width of the photo
	 * @param height required new height of the photo
	 * @return an ImageIcon that will be shown in frames
	 * @throws IOException If file does not exist, this exception is thrown
	 */
    public static ImageIcon photoGetterInSize(String photoPath, int width, int height) throws IOException {
		
        BufferedImage fImage = ImageIO.read(new File(photoPath));
        BufferedImage lImage= new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g1 = lImage.createGraphics();
        g1.setColor(Color.WHITE);
        g1.fillRect(0, 0, width, height);
        float ratio = (float) fImage.getWidth() / fImage.getHeight();
        int nWidth = width;
        int nHeight = (int) (nWidth / ratio);
        if (nHeight > height) {
            nHeight = height;
            nWidth = (int) (nHeight * ratio);
        }
        int x = (width - nWidth) / 2;
        int y = (height - nHeight) / 2;
        g1.drawImage(fImage, x, y, nWidth, nHeight, null);
        g1.dispose();
        return new ImageIcon(lImage);
    }
}
