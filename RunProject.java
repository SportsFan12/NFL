import javax.swing.SwingUtilities; 

public class RunProject implements Runnable {

	public void run () {
		Project p = new Project();
	}
	
	public static void main (String[] args) {
		SwingUtilities.invokeLater(new RunProject());
	}
}