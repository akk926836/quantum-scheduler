package Skillbuilders;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

public class SemAvg {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SemAvg window = new SemAvg();
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
	public SemAvg() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 590, 443);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JTextArea txtrEnterTheFirst = new JTextArea();
		txtrEnterTheFirst.setEditable(false);
		txtrEnterTheFirst.setBackground(UIManager.getColor("Button.background"));
		txtrEnterTheFirst.setText("Enter the Second Grade: ");
		txtrEnterTheFirst.setBounds(10, 136, 182, 35);
		panel.add(txtrEnterTheFirst);
		
		
		
		JTextArea txtrEnterTheFirst_1 = new JTextArea();
		txtrEnterTheFirst_1.setEditable(false);
		txtrEnterTheFirst_1.setText("Enter the First Grade: ");
		txtrEnterTheFirst_1.setBackground(UIManager.getColor("Button.background"));
		txtrEnterTheFirst_1.setBounds(10, 57, 189, 35);
		panel.add(txtrEnterTheFirst_1);
		
		JTextArea txtrEnterTheThird = new JTextArea();
		txtrEnterTheThird.setEditable(false);
		txtrEnterTheThird.setText("Enter the Third Grade: ");
		txtrEnterTheThird.setBackground(UIManager.getColor("Button.background"));
		txtrEnterTheThird.setBounds(10, 212, 189, 35);
		panel.add(txtrEnterTheThird);
		
		JTextArea Grade1 = new JTextArea();
		Grade1.setBounds(240, 57, 197, 56);
		panel.add(Grade1);
		
		JTextArea Grade2 = new JTextArea();
		Grade2.setBounds(240, 136, 197, 56);
		panel.add(Grade2);
		
		JTextArea Grade3 = new JTextArea();
		Grade3.setBounds(240, 212, 197, 56);
		panel.add(Grade3);
		
		
		JButton AvgButton = new JButton("Average");
		
		AvgButton.setBounds(20, 258, 172, 74);
		panel.add(AvgButton);
		
		JLabel AvgScore = new JLabel("");
		AvgScore.setBounds(240, 300, 229, 74);
		panel.add(AvgScore);
		
		AvgButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				double avg = (Double.parseDouble(Grade1.getText())+Double.parseDouble(Grade2.getText())+Double.parseDouble(Grade3.getText()))/3;
				AvgScore.setText(Double.toString(avg));
			}
			
		});
		
	}
}
