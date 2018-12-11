package Eliza;

public class Show_ElizaGUI {
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater( new Runnable(){
			@Override
			public void run() {
				ElizaGUI gui = new ElizaGUI();
			}
		});
	}

}
