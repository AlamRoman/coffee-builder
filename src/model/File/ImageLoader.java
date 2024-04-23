package model.File;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

public class ImageLoader {

	//get image from relative path from src folder
	public ImageIcon getImageFrom(String path) {
		ImageIcon image;
		try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(path)) {
            if (inputStream != null) {
                
                image = new ImageIcon(javax.imageio.ImageIO.read(inputStream));
                
                return image;
            } else {
                System.out.println("Error : Image not found");
            }
        } catch (IOException e) {
            System.out.println("Error loading image: " + e.getMessage());
        }
		return null;
	}
	
	public static ImageIcon scaleImage(ImageIcon icon, double scale) {
        int newWidth = (int) (icon.getIconWidth() * scale);
        int newHeight = (int) (icon.getIconHeight() * scale);

        Image image = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
	
	public static ImageIcon scaleImage(ImageIcon icon, int newWidth, int newHeight) {
        Image image = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
