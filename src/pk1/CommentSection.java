package pk1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//creare sectiune de comentarii
public class CommentSection extends JPanel {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea commentTextArea;
	//panel separat unde o sa fie introduse comentariile scrise
    private JPanel commentsPanel;

    public CommentSection() {
        setLayout(new BorderLayout());
        setBounds(10, 510, 380, 150); 

        commentsPanel = new JPanel();
        commentsPanel.setLayout(new BoxLayout(commentsPanel, BoxLayout.Y_AXIS));
        //implementation of a key adaptor to listen to the text and convert it in a non-copyable text
        //adding also the "You:" to mark its user's commentary
        //implementare keyadaptor pentru a converti textul in jpanel astfel incat textul o sa devina un obiect in sine necopiabil;
        //ne permite astfel ca textul va ramane in loc;
        commentTextArea = new JTextArea(2, 30);
        commentTextArea.setLineWrap(true);
        commentTextArea.setWrapStyleWord(true);
        commentTextArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
            	//waits to the "enter" key/asteapta pentru tasta enter
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	//gets the comment and excludes the whitespaces at the end of the line
                	//ia comentariul si exclude spatiile de la final
                    String comment = commentTextArea.getText().trim();
                 
                    if (!comment.isEmpty()) {
                    	//adding the comment if not empty/adaugare comentariu daca exista
                        addComment("You: " + comment);
                        commentTextArea.setText(""); // clear text area after submission/curata textarea-ul dupa apasare enter si trimitere comentariu
                        e.consume(); // prevent the newline character from being added to the text area/prevenire adaugare caracter new line
                    }
                }
            }
        });
        //adaugare elemente cu scroll in caz de mai multe comentarii
        add(new JScrollPane(commentTextArea), BorderLayout.NORTH);
        add(new JScrollPane(commentsPanel), BorderLayout.CENTER);
    }
    //adding the comment in the commentpanel
    private void addComment(String comment) {
        JLabel commentLabel = new JLabel(comment);
        commentsPanel.add(commentLabel);
        //verify that jtextfield is set to true
        commentsPanel.revalidate();
        //set non-copyable;
        commentsPanel.repaint();
    }
}
