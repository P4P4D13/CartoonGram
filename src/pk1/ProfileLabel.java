package pk1;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class ProfileLabel extends JLabel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String DEFAULT_PROFILE_IMAGE = "C:\\path\\to\\default_profile.jpg";
    private Image profileImage;

    public ProfileLabel(String profileName, String profileImagePath) {
        setBounds(10, 10, 45, 45);
        try {
            File file = new File(profileImagePath);
            if (file.exists()) {
                BufferedImage image = ImageIO.read(file);
                this.profileImage = image.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
            } else {
                System.err.println("Profile image not found: " + profileImagePath);
                loadDefaultProfileImage();
            }
        } catch (IOException e) {
            System.err.println("Error loading profile image: " + profileImagePath);
            e.printStackTrace();
            loadDefaultProfileImage();
        }
    }

    private void loadDefaultProfileImage() {
        try {
            BufferedImage placeholder = ImageIO.read(new File(DEFAULT_PROFILE_IMAGE));
            this.profileImage = placeholder.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
        } catch (IOException e) {
            this.profileImage = null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        
        // enable anti-aliasing for smoother drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw the profile image within a circular clip
        if (profileImage != null) {
            Ellipse2D.Double clip = new Ellipse2D.Double(0, 0, getWidth(), getHeight());
            g2.setClip(clip);
            g2.drawImage(profileImage, 0, 0, getWidth(), getHeight(), this);
        } else {
            g2.setColor(Color.GRAY);
            g2.fill(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
        }

        g2.setClip(null); // reset the clip to the default
        g2.setColor(Color.BLACK);
        g2.draw(new Ellipse2D.Double(0, 0, getWidth() - 1, getHeight() - 1));
        g2.dispose();
    }
}
