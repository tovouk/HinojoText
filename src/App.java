import javax.swing.SwingUtilities;

public class App {
	
	public static void main(String[] args) {
		//required for application interfaces to run properly during multithreading
		//prevents crashing
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				new MainFrame();
				
				
				
			}
			
		});
		
		
	}
	
}
