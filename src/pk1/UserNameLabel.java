package pk1;

import javax.swing.*;
import java.awt.*;
//code for username near the profile picture /cod pentru username de langa pfp 
public class UserNameLabel extends JLabel {
    private static final long serialVersionUID = 1L;

    public UserNameLabel(String userName) {
        super(userName);//extends from label/se extinde din jlabel
        setBounds(60, 15, 200, 45); // adjust the position and size as needed/ajustare pozitie/marime
        setOpaque(false); // make the background transparent
        setBackground(Color.LIGHT_GRAY);
        setHorizontalAlignment(SwingConstants.LEFT);
        setFont(new Font("Arial", Font.BOLD, 14)); // Set font style and size/setare font si marime
    }
}
