package chatclientgui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChatClientGUI {

	private JFrame frame;
	private JTextField tfServer;
	private JTextField tfPort;
	private JTextField tfDisplayName;
	private JTextField tfMsg;
	private JTextArea taMsg;
	
	private ChatClient client;
	private JButton btnLeave;
	private JButton btnJoin;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatClientGUI window = new ChatClientGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ChatClientGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 330);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblServer = new JLabel("Server");
		lblServer.setBounds(38, 32, 52, 9);
		frame.getContentPane().add(lblServer);
		
		JLabel lblPort = new JLabel("Port");
		lblPort.setBounds(38, 70, 31, 9);
		frame.getContentPane().add(lblPort);
		
		JLabel lblDisplayName = new JLabel("Name");
		lblDisplayName.setBounds(38, 104, 37, 11);
		frame.getContentPane().add(lblDisplayName);
		
		tfServer = new JTextField();
		tfServer.setBounds(123, 28, 97, 17);
		frame.getContentPane().add(tfServer);
		tfServer.setColumns(10);
		
		tfPort = new JTextField();
		tfPort.setBounds(123, 66, 97, 17);
		frame.getContentPane().add(tfPort);
		tfPort.setColumns(10);
		
		tfDisplayName = new JTextField();
		tfDisplayName.setBounds(123, 101, 97, 17);
		frame.getContentPane().add(tfDisplayName);
		tfDisplayName.setColumns(10);
		
		btnJoin = new JButton("Join");
		btnJoin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String server = tfServer.getText();
				int port = Integer.parseInt(tfPort.getText());
				String displayName = tfDisplayName.getText();
				
				taMsg.setText("");
				client = new ChatClient(server, port, displayName, taMsg, tfMsg);
				
				btnJoin.setEnabled(false);
				btnLeave.setEnabled(true);
				tfMsg.setEditable(true);
			}
		});
		btnJoin.setBounds(285, 42, 77, 19);
		frame.getContentPane().add(btnJoin);
		
		btnLeave = new JButton("Leave");
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tfMsg.setText(".bye");
				client.send();
				
				btnJoin.setEnabled(true);
				btnLeave.setEnabled(false);
				tfMsg.setEnabled(false);
			}
		});
		btnLeave.setBounds(285, 84, 77, 19);
		frame.getContentPane().add(btnLeave);

		
		tfMsg = new JTextField();
		tfMsg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				client.send();
				tfMsg.requestFocus();
			}
		});
		tfMsg.setBounds(38, 131, 411, 25);
		frame.getContentPane().add(tfMsg);
		tfMsg.setColumns(10);
		
		taMsg = new JTextArea();
		taMsg.setBounds(38, 164, 411, 106);
		frame.getContentPane().add(taMsg);
		btnJoin.setEnabled(true);
		btnLeave.setEnabled(false);
		tfMsg.setEditable(false);
	}
}
