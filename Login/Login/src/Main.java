import java.awt.EventQueue;

public class Main {

	public static void main(String[] arg) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					LoginView window = new LoginView();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
