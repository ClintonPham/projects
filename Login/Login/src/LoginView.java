import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;

public class LoginView {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField usernameField;

	/**
	 * Launch the application.
	 */
	 

	/**
	 * Create the application.
	 */
	public LoginView() {
		
 		initialize();
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Login Panel");
		frame.setVisible(true);

		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(103, 76, 87, 23);
		frame.getContentPane().add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setBounds(103, 138, 90, 23);
		frame.getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(272, 136, 129, 26);
		frame.getContentPane().add(passwordField);
		
		usernameField = new JTextField();
		usernameField.setBounds(271, 74, 130, 26);
		frame.getContentPane().add(usernameField);
		usernameField.setColumns(10);
		
		JButton loginButton = new JButton("Login");
		loginButton.setBounds(169, 202, 117, 29);
		frame.getContentPane().add(loginButton);
	}
}


//TODO - Design layout of application, maybe on ipad?
//Look into SQL or something for username and password
// Make login panel look more modern