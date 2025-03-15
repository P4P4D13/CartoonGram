package pk1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Set;

public class FollowersWindow extends JFrame {
    private static final long serialVersionUID = 1L;
    private Set<String> followersSet;
    private JTextArea followingTextArea;
    private GUIinit mainWindow;

    public FollowersWindow(GUIinit mainWindow) {
        this.mainWindow = mainWindow;
        //creation of following window when neccesary/creaza window atunci cand e necesar 
        //this is the main window to avoid the bug stated in the guinit/e fereastra principala 
        //cand e nevoie sa se evite bugul intampinat
        setTitle("Following List");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        //set made for the follower list, hashset has unique elements
        followersSet = new HashSet<>();
        followingTextArea = new JTextArea();
        followingTextArea.setEditable(false);
        //scroll if mutiple people
        JScrollPane scrollPane = new JScrollPane(followingTextArea);
        add(scrollPane, BorderLayout.CENTER);
        
        //close button to get back to the main window/buton de close sa ne intoarcem la window principal
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                mainWindow.showMainWindow();
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // initial message when there are no followers/mesaj initial cand 
       //nu sunt followeri
        updateFollowingList();
    }

    public GUIinit getMainWindow() {
        return mainWindow;
    }

    public void addFollowing(String name) {
        if (followersSet.add(name)) {
            updateFollowingList();
        }
       
        //debugs print are made because of the bug of the main window i had
        System.out.println("Adding " + name + " to FollowersWindow"); 
        // Debug print
    }

    public void removeFollowing(String name) {
        if (followersSet.remove(name)) {
            updateFollowingList();
        }
        System.out.println("Removing " + name + " from FollowersWindow"); // Debug print
    }

    private void updateFollowingList() {
        followingTextArea.setText(""); // clear the text area
        //message in case you dont follow anyone/mesaj in caz de utilizatorul nu urmareste pe nimeni
        
        if (followersSet.isEmpty()) 
        {
            followingTextArea.append("You aren't following anyone\n");
        } else {
            for (String follower : followersSet) {
                followingTextArea.append(follower + "\n");
            }
        }
    }
    //the method that adds the following peoplle
    public void addMessage(String message) {
        followingTextArea.append(message + "\n");
    }
}
