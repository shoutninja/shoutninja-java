package ninja.shout.desktop.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ShoutNinjaMain extends JFrame {
	private static final long serialVersionUID = 3142368401514367216L;
	
	private JPanel contentPane;
	private JTextField txtChatbox;
	private JTextField txtUsernamebox;
	
	private final Firebase ref;

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
		
		final JPanel panel_3 = new JPanel();
		contentPane.add(panel_3, BorderLayout.NORTH);
		
		JLabel lblTest = new JLabel("Test");
		panel_3.add(lblTest);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel_3.add(new JLabel("What up, dawg?"));
				panel_3.revalidate();
			}
		});
		panel.add(btnSend, BorderLayout.EAST);
		
		JPanel panel_2 = new JPanel();
		panel_1.add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		txtUsernamebox = new JTextField();
		txtUsernamebox.setText("Guest");
		panel_2.add(txtUsernamebox);
		txtUsernamebox.setColumns(10);

		
		//Load data
		ref = new Firebase("https://eakjb-shout-ninja2.firebaseio.com/chats");
		
		ref.addValueEventListener(new ValueEventListener() {
		    public void onDataChange(DataSnapshot snapshot) {
		        System.out.println(snapshot.getValue());
		        for (DataSnapshot chat : snapshot.getChildren()) {
		        	JPanel chatPanel = new JPanel(new BorderLayout());
		        	chatPanel.add(new JLabel(chat.child("text").toString()),BorderLayout.CENTER);
		        	panel_3.revalidate();
		        	chatPanel.add(new JLabel(chat.child("user").child("username").toString()),BorderLayout.EAST);
		        	panel_3.add(chatPanel);
		        }
		    }

		    public void onCancelled(FirebaseError firebaseError) {
		        System.out.println("The read failed: " + firebaseError.getMessage());
		    }
		});
		
		
	}

	public Firebase getRef() {
		return ref;
	}
}
