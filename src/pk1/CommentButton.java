package pk1;

import javax.swing.*;

public class CommentButton extends JButton {
	/** * */
	private static final long serialVersionUID = 1L;
//creating the simple comment button thats is just plain text with no borders
	//creare buton comentariu fara LAF sau border
	public CommentButton() {
		super("Comment");
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
	}
}