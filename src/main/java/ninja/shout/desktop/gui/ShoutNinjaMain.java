package ninja.shout.desktop.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSplitPane;

public class ShoutNinjaMain extends JFrame {

	private JPanel contentPane;
	private JTextField txtChatbox;
	private JTextField txtUsernamebox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoutNinjaMain frame = new ShoutNinjaMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ShoutNinjaMain() {
		setTitle("Shout.Ninja");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		panel_1.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel_1.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		txtChatbox = new JTextField();
		txtChatbox.setText("Don't ninja and drive.");
		panel.add(txtChatbox);
		txtChatbox.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		panel.add(btnSend, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		txtUsernamebox = new JTextField();
		txtUsernamebox.setText("Guest");
		panel_2.add(txtUsernamebox);
		txtUsernamebox.setColumns(10);
	}

}
