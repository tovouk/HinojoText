import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MainFrame extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TextPanel textPanel;
	private Toolbar toolbar;
	private JTextArea textarea;
	
	public MainFrame() {
		
		super("Hino Text Editor - created by Jose Hinojo");
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
		setFont(new Font("Arial",Font.PLAIN,20));
		textPanel = new TextPanel();
		textPanel.setFont();
		toolbar = new Toolbar();
		textarea = textPanel.getTextArea();
		toolbar.setTextArea(textarea);
		
		toolbar.setStringListener(new StringListener() {

			@Override
			public void textEmitted(String text) {
				textPanel.setText(text);
			}
			
		});
		
		add(toolbar,BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		//set length and width of window
		setSize(1500,1000);
		
		//set X button to terminate the application
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
	
}
