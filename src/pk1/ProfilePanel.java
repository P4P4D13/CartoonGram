package pk1;

import javax.swing.*;
import java.awt.*;

public class ProfilePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    private String postImagePath;
    private CommentSection commentSection;
    private boolean isCommentSectionVisible = false;
    private Timer slideTimer;
    private int targetHeight;

    public ProfilePanel(String characterName, String userName, String profileImagePath, String photoText, String postImagePath, String captionText, String descriptionText, FollowersWindow followersWindow, FollowSubject followSubject) {
        this.postImagePath = postImagePath;

        setLayout(null);
        setPreferredSize(new Dimension(400, 450)); // initial height without the comment section/inaltime initiala fara sectiunea de comentarii
        setBackground(new Color(255, 255, 255, 0)); // set background to be transparent/setare background transparent 

        ProfileLabel profileLabel = new ProfileLabel(characterName, profileImagePath);
        profileLabel.setBounds(10, 10, 45, 45);
        add(profileLabel);

        UserNameLabel nameLabel = new UserNameLabel(userName); // use UserNameLabel instead
        nameLabel.setBounds(60, 15, 200, 45);
        add(nameLabel);

        FollowButton followButton = new FollowButton(followSubject, followersWindow, characterName);
        followButton.setBounds(260, 20, 90, 30); // adjusted position to align with profile picture
        add(followButton);

        Follower follower = new Follower(characterName, followersWindow);
        followSubject.attach(follower);

        LikeButton likeButton = new LikeButton();
        likeButton.setBounds(10, 380, 50, 50);
        add(likeButton);

        CommentButton commentButton = new CommentButton();
        commentButton.setBounds(50, 395, 100, 30);
        add(commentButton);

        PhotoLabel photoLabel = new PhotoLabel(photoText, postImagePath, likeButton); // pass the LikeButton
        photoLabel.setBounds(0, 70, 400, 300); // adjusted to remove white space
        add(photoLabel);
//never used
        JLabel captionLabel = new JLabel("<html>" + captionText + "</html>");
        captionLabel.setBounds(10, 360, 380, 30);
        add(captionLabel);
        //created but not went further/creat dar nu am mers mai departe
        JLabel descriptionLabel = new JLabel("<html>" + descriptionText + "</html>");
        descriptionLabel.setBounds(10, 430, 380, 30); 
        //sectiune comentarii
        commentSection = new CommentSection();
        commentSection.setBounds(10, 470, 380, 150); // adjust position as needed
        commentSection.setVisible(false);
        add(commentSection);

        commentButton.addActionListener(e -> {
            toggleCommentSection();
        });
    }
//implementation of a timer to change the height liniarly so we get the animation effect
    //implementare cu timer pentru animatie
    private void toggleCommentSection() {
        isCommentSectionVisible = !isCommentSectionVisible;
        commentSection.setVisible(isCommentSectionVisible);
        targetHeight = isCommentSectionVisible ? 650 : 500; // adjust the height based on comment section visibility
        if (slideTimer != null && slideTimer.isRunning()) {
            slideTimer.stop();
        }
        slideTimer = new Timer(10, e -> slideAnimation());
        slideTimer.start();
    }

    private void slideAnimation() {
        Dimension currentSize = getPreferredSize();
        int currentHeight = currentSize.height;
        if (isCommentSectionVisible && currentHeight < targetHeight) {
            setPreferredSize(new Dimension(400, currentHeight + 10));
        } else if (!isCommentSectionVisible && currentHeight > targetHeight) {
            setPreferredSize(new Dimension(400, currentHeight - 10));
        } else {
            slideTimer.stop();
        }
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();

        // enable anti-aliasing for smoother drawing
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // draw rounded rectangle with a white fill
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        // draw rounded rectangle border
        g2.setColor(Color.GRAY);
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 20, 20);

        g2.dispose();
    }

    public String getPostImagePath() {
        return postImagePath;
    }
}
