package Skillbuilders;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class DEMOEXAMPLE {

	private JFrame frame;
	private JTextField fn;
	private JTextField ln;
	private JComboBox SchoolN;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DEMOEXAMPLE window = new DEMOEXAMPLE();
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
	public DEMOEXAMPLE() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ImageIcon cr = new ImageIcon("../Chapter10/src/chhs.png");
		ImageIcon em = new ImageIcon("../Chapter10/src/em.png");
		ImageIcon lbp = new ImageIcon("../Chapter10/src/lbp.jpg");
		ImageIcon wc = new ImageIcon("../Chapter10/src/wc.png");
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 604, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel imgA = new JLabel("");
		imgA.setBounds(83, 126, 216, 122);
		panel.add(imgA);
		
		fn = new JTextField();
		fn.setText("First Name");
		fn.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(fn.getText().equals("First Name"))
				fn.setText("");
			}
		});
		fn.setBounds(95, 11, 96, 19);
		fn.setForeground(new Color(0, 0, 64));
		fn.setFont(new Font("Times New Roman", Font.PLAIN, 11));
		panel.add(fn);
		fn.setColumns(10);
		
		ln = new JTextField();
		ln.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(ln.getText().equals("Last Name"))
					ln.setText("");
			}
		});
		ln.setText("Last Name");
		ln.setBounds(201, 10, 86, 20);
		ln.setColumns(10);
		panel.add(ln);
		
		JComboBox GradeLevel = new JComboBox();
		GradeLevel.setModel(new DefaultComboBoxModel(new String[] {"Select grade", "10", "11", "12"}));
		GradeLevel.setBounds(41, 72, 124, 32);
		panel.add(GradeLevel);
		
		SchoolN = new JComboBox();
		SchoolN.setModel(new DefaultComboBoxModel(new String[] {"Select School", "CHHS", "Western", "Ernest Manning", "Pearson"}));
		SchoolN.setBounds(175, 72, 124, 32);
		panel.add(SchoolN);
		
		JTextArea Disp = new JTextArea();
		Disp.setBounds(28, 262, 336, 258);
		panel.add(Disp);
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setForeground(new Color(0, 0, 0));
		btnNewButton.setBackground(new Color(192, 192, 192));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String grade = "";
				String school = "";
				String fName = fn.getText();
				String lName = ln.getText();
				
				if(GradeLevel.getSelectedItem().equals("10"))
				{
					grade = "10";
				}
				
				else if(GradeLevel.getSelectedItem().equals("11"))
				{
					grade = "11";
				}
			
				else
				{
					grade = "12";
				}
				
				
				if(SchoolN.getSelectedItem().equals("CHHS"))
				{
					school = "CHHS";
					imgA.setIcon(cr);
				}
				
				else if(SchoolN.getSelectedItem().equals("Western"))
				{
					school = "Western";
					imgA.setIcon(wc);
				}
				
				else if(SchoolN.getSelectedItem().equals("Ernest Manning"))
				{
					school = "Ernest Manning";
					imgA.setIcon(em);
				}
			
				else{
					school = "Pearson";
					imgA.setIcon(lbp);
			}
				Disp.setText(fName +" "+ lName+" "+ grade+" "+school+" ");
		}
		});
		btnNewButton.setBounds(408, 172, 157, 348);
		panel.add(btnNewButton);
		
		
	}
}
