package ninja.shout.desktop.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

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
		setBounds(100, 100, 451, 303);
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
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				sendChat();
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
		
		final JPanel panel_3 = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(panel_3);
		scrollPane.setPreferredSize(new Dimension(400,300));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		panel_3.setLayout(new BoxLayout(panel_3,BoxLayout.Y_AXIS));
		
		//Load data
		ref = new Firebase("https://eakjb-shout-ninja2.firebaseio.com/chats");
		
		ref.addValueEventListener(new ValueEventListener() {
		    public void onDataChange(DataSnapshot snapshot) {
		        System.out.println(snapshot.getValue());
		        for (DataSnapshot chat : snapshot.getChildren()) {
		        	JPanel chatPanel = new JPanel(new BorderLayout());
		        	chatPanel.add(new JLabel("  "+chat.child("text").getValue().toString()),BorderLayout.CENTER);
		        	
		        	JPanel userPanel = new JPanel();
		        	userPanel.setLayout(new BoxLayout(userPanel,BoxLayout.Y_AXIS));
		        	
		        	userPanel.add(new JLabel(chat.child("user").child("username").getValue().toString()));
		        	userPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		        	chatPanel.add(userPanel,BorderLayout.WEST);
		        	panel_3.add(chatPanel);
		        }
		        pack();
		    }

		    public void onCancelled(FirebaseError firebaseError) {
		        System.out.println("The read failed: " + firebaseError.getMessage());
		    }
		});
		
		
	}
	
	private void sendChat() {
		Map<String,Object> user = new HashMap<String,Object>();
		user.put("username", txtUsernamebox.getText());
		user.put("image", "/img/anonymous.jpg");
		
		Map<String,Object> chat = new HashMap<String,Object>();
		chat.put("text", txtChatbox.getText());
		chat.put("user", user);
		
		//ref.
	}

	public Firebase getRef() {
		return ref;
	}
}
