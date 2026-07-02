package Skillbuilders;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Roll {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Roll window = new Roll();
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
	public Roll() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ImageIcon d1 = new ImageIcon("../Chapter10/src/Skillbuilders/Images/die1.jpg");
		ImageIcon d2 = new ImageIcon("../Chapter10/src/Skillbuilders/Images/die2.jpg");
		ImageIcon d3 = new ImageIcon("../Chapter10/src/Skillbuilders/Images/die3.jpg");
		ImageIcon d4 = new ImageIcon("../Chapter10/src/Skillbuilders/Images/die4.jpg");
		ImageIcon d5 = new ImageIcon("../Chapter10/src/Skillbuilders/Images/die5.jpg");
		ImageIcon d6 = new ImageIcon("../Chapter10/src/Skillbuilders/Images/die6.jpg");
		

		
		frame = new JFrame();
		frame.setBounds(100, 100, 633, 421);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 10, 10);
		panel.add(panel_1);
		
		JLabel Die_1 = new JLabel("Die 1");
		Die_1.setBounds(105, 171, 200, 200);
		panel.add(Die_1);
		
		JLabel Die_2 = new JLabel("Die 2");
		Die_2.setBounds(337, 171, 200, 200);
		panel.add(Die_2);
		
		JButton RollDie = new JButton("Roll Dice");
		RollDie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			
				int Roll1,Roll2;
				Roll1 = (int)(6*Math.random()+1);
				
				if(Roll1 == 1)
				{
					Die_1.setIcon(d1);
				}
				else if(Roll1 == 2)
				{
					Die_1.setIcon(d2);
				}
				else if(Roll1 == 3)
				{
					Die_1.setIcon(d3);
				}
				else if(Roll1 == 4)
				{
					Die_1.setIcon(d4);
				}
				else if(Roll1 == 5)
				{
					Die_1.setIcon(d5);
				}
				else if(Roll1 == 6)
				{
					Die_1.setIcon(d6);
				}
				
				Roll2 = (int)(6*Math.random()+1);
				
				if(Roll2 == 1)
				{
					Die_2.setIcon(d1);
				}
				else if(Roll2 == 2)
				{
					Die_2.setIcon(d2);
				}
				else if(Roll2 == 3)
				{
					Die_2.setIcon(d3);
				}
				else if(Roll2 == 4)
				{
					Die_2.setIcon(d4);
				}
				else if(Roll2 == 5)
				{
					Die_2.setIcon(d5);
				}
				else if(Roll2 == 6)
				{
					Die_2.setIcon(d6);
				}
	
			}
		});
		RollDie.setBounds(157, 11, 301, 138);
		panel.add(RollDie);
		

}
}
