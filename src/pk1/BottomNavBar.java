package pk1;

import javax.swing.*;
import java.awt.*;

public class BottomNavBar extends JPanel {
    private static final long serialVersionUID = 1L;
    public BottomNavBar(FollowSubject followSubject, FollowersWindow followersWindow, GUIinit mainWindow) {
        //setting the layout to an even size to fit 3 buttons/pozitionare butoane 3 parti egale
    	setLayout(new GridLayout(1, 3));
        setBackground(new Color(240, 240, 240));
        setPreferredSize(new Dimension(400, 50));

        //creating the buttons/creare butoane
        JButton homeButton = new JButton("Home");
        JButton searchButton = new JButton("Search");//search doesnt do anything atm/butonul search nu face nimic momentan
        JButton followingButton = new JButton("Following");

        //getting rid of LAF (look and feel) to get rid of that old style/renuntare tematica LAF
        homeButton.setFocusPainted(false);
        searchButton.setFocusPainted(false);
        followingButton.setFocusPainted(false);
        
        //adding buttons to the frame/adaugare butoane in interfata
        add(homeButton);
        add(searchButton);
        add(followingButton);

        //action for home button to get the user back to the top with an animation/actionare 
        //pentru a aduce utilizatorul in varful paginii implementand si o animatie
        homeButton.addActionListener(e -> {
            mainWindow.scrollToTop();
        });
        //button for opening a new window to show the people i am follwoing
        followingButton.addActionListener(e -> {
            followersWindow.getMainWindow().showFollowersWindow();
        });
    }
}
