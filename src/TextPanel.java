import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TextPanel extends JPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textArea;
	public TextPanel() {
		textArea = new JTextArea();
		setLayout(new BorderLayout());
		//JScrollPane adds scrollbars
		add(new JScrollPane(textArea),BorderLayout.CENTER);
		
	}
	public JTextArea getTextArea() {
		return textArea;
	}
	public String getText() {
		return textArea.getText();
	}
	
	public void setText(String str) {
		textArea.append(str);
	}
	
	public void setFont() {
		textArea.setFont(new Font("Arial",Font.PLAIN,25));
	}
	
	
}
