package pk1;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//creating the follow button like the instagram one/creare buton follow ca cel al instagramului
public class FollowButton extends JButton implements FollowObserver {
    private static final long serialVersionUID = 1L;
    //seting the color to light blue /setare culoare buton follow in albastru deschis
    private static final Color BUTTON_COLOR = new Color(0, 122, 255);
    private static final Color TEXT_COLOR = Color.WHITE;
    //changing the color when hovering/schimbare culoare la hover peste buton
    private static final Color HOVER_COLOR = new Color(0, 92, 204);
    private boolean isHover;

    public FollowButton(FollowSubject subject, FollowersWindow followersWindow, String followerName) {
        //using super because of Jbutton (the class extends with jbutton)
    	//folosire super deoarece clasa mosteneste jbutton
    	super("Follow");
        //function in followersubject that receives the following request when pressing the button
    	//functie pentru adaugarea la following corespunzatoare pentru profilul atasat
    	
        subject.attach(this);

        setBounds(250, 25, 100, 40);
        setFont(new Font("Arial", Font.BOLD, 14));
        //not using LAF for a minimalist look/renuntare laf
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setForeground(TEXT_COLOR);
        //mouselistener to check if the mouse hovers over the button
        //adaugare mouselistener pentru verificarea functiei de hover peste buton
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                isHover = true;
                repaint();//if true set the collor to a darker one
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                isHover = false;
                repaint();
            }
        });

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Follow button clicked for: " + followerName); // Debug print
                subject.setFollowed(!subject.isFollowed(), followersWindow, followerName);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //changing the collor when hovering
        //schimbare culoare
        if (isHover) {
            g2.setColor(HOVER_COLOR);
        } else {
            g2.setColor(BUTTON_COLOR);
        }
        
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
        super.paintComponent(g);
    }

    @Override
    public void update(FollowSubject subject) {
    	//changing the button text depending of the following state/schimbare buton in functie de starea de follow
    	//schimbare text in functie de state
        setText(subject.isFollowed() ? "Unfollow" : "Follow");
        repaint();
    }
}
