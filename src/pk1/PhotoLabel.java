package pk1;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class PhotoLabel extends JLabel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_IMAGE_PATH = "C:\\path\\to\\placeholder.jpg";//not used/neutilizat
    public PhotoLabel(String photoText, String photoPath, LikeButton likeButton) {
        if (photoPath != null) {
            try {
                File file = new File(photoPath);
                if (file.exists()) {
                    BufferedImage photo = ImageIO.read(file);
                    Image scaledImage = getScaledImage(photo, 380, 300); // Adjusted to fit the window
                    setIcon(new ImageIcon(scaledImage));
                } else {
                    System.err.println("Image not found at path: " + photoPath);
                    loadDefaultImage(photoText);
                }
            } catch (IOException e) {
                System.err.println("Error loading image at path: " + photoPath);
                e.printStackTrace();
                loadDefaultImage(photoText);
            }
        } else {
            loadDefaultImage(photoText);
        }
        setBounds(0, 70, 400, 300); // Adjusted to remove white space
        setBorder(BorderFactory.createLineBorder(Color.BLACK));

        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                if (e.getClickCount() == 2) {
                    likeButton.doClick(); // Simulate a click on the LikeButton
                }
            }
        });
    }
//adding the placeholder picture
    private void loadDefaultImage(String text) {
        try {
            BufferedImage placeholder = ImageIO.read(new File(DEFAULT_IMAGE_PATH));
            Image scaledImage = getScaledImage(placeholder, 380, 300); // adjusted to fit the window
            setIcon(new ImageIcon(scaledImage));
        } catch (IOException e) {
            setText(text);
            setHorizontalAlignment(SwingConstants.CENTER);
            setBackground(Color.WHITE);
            setOpaque(true);
        }
    }
//adding the image
    private Image getScaledImage(Image srcImg, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(srcImg, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }
}
