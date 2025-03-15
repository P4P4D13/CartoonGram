package pk1;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CaptionPanel extends JPanel {
	/** * */
	private static final long serialVersionUID = 1L;
//creating the description for every post
	public CaptionPanel(String captionText) {
		setBounds(0, 430, 400, 40);
		setLayout(new FlowLayout(FlowLayout.LEFT));
		String username = "ThomasTheCar";
		JLabel captionLabel = new JLabel("<html><b>" + username + "</b> " + captionText + "</html>");
		captionLabel.setPreferredSize(new Dimension(350, 30));
		add(captionLabel);
	}
}