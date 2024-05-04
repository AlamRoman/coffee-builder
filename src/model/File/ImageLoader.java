package model.File;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

/**
 * <p>
 * This class provides utility methods for loading and scaling images in the application. It includes functionality for loading images from the classpath and scaling images to desired dimensions.
 * </p>
 */
public class ImageLoader {

	//get image from relative path from src folder
	/**<p>
	 * Retrieves an image from the specified relative path within the src folder of the application.
	 * Loads the image as an ImageIcon object using the class loader, scales it to the desired dimensions,
	 * and returns the final {@link ImageIcon}.
	 * </p>
	 * @param path The relative path of the image file within the src folder.
	 * @return ImageIcon object representing the loaded and scaled image, or null if the image is not found or loading fails.
	 */
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
	
	/**
	 * This method scales the specified {@link ImageIcon} to the desired scale factor.
	 * Calculates the new width and height based on the original dimensions and the scale factor,
	 * and scales the image using {@link Image#SCALE_SMOOTH} algorithm.
	 *
	 * @param icon The ImageIcon to be scaled.
	 * @param scale The scale factor to apply to the image (e.g., 0.5 for 50% scale).
	 * @return {@link ImageIcon} Scaled ImageIcon object.
	 */
	public static ImageIcon scaleImage(ImageIcon icon, double scale) {
        int newWidth = (int) (icon.getIconWidth() * scale);
        int newHeight = (int) (icon.getIconHeight() * scale);

        Image image = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
	
	/**<p>
	 * This method:
	 * </p>
	 * <ul>
	 * <li>Scales the specified {@link ImageIcon} to the specified width and height.</li>
	 * <li>Scales the image using {@link Image#SCALE_SMOOTH} algorithm to ensure smooth resizing.</li>
	 * </ul>
	 * @param icon The ImageIcon to be scaled.
	 * @param newWidth The new width of the scaled image.
	 * @param newHeight The new height of the scaled image.
	 * @return {@link ImageIcon} Scaled ImageIcon object with the specified dimensions.
	 */
	public static ImageIcon scaleImage(ImageIcon icon, int newWidth, int newHeight) {
        Image image = icon.getImage().getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }
}
