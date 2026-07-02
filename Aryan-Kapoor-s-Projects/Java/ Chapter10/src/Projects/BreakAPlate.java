/*

Program: BreakAPlate.java          Last Date of this Revision: 23rd February 2025

Purpose: A graphical user interface that allows a user to play the game where they need to break as many plates as
 they can to win a prize.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.event.ActionEvent;

public class BreakAPlate {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BreakAPlate window = new BreakAPlate();
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
	public BreakAPlate() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		Random random = new Random();
		
		ImageIcon p = new ImageIcon("../Chapter10/src/Mastery/Ch10 Mastery Img/plates.gif");
		ImageIcon bp = new ImageIcon("../Chapter10/src/Mastery/Ch10 Mastery Img/plates_all_broken.gif");
		ImageIcon tbp = new ImageIcon("../Chapter10/src/Mastery/Ch10 Mastery Img/plates_two_broken.gif");//define all images
		ImageIcon tp = new ImageIcon("../Chapter10/src/Mastery/Ch10 Mastery Img/tiger_plush.gif");
		ImageIcon s = new ImageIcon("../Chapter10/src/Mastery/Ch10 Mastery Img/sticker.gif");
		ImageIcon ph = new ImageIcon("../Chapter10/src/Mastery/Ch10 Mastery Img/placeholder.gif");
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 366);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel Plate_img = new JLabel("");
		Plate_img.setBounds(79, 11, 270, 71);
		panel.add(Plate_img);
		
		JLabel Prize_img = new JLabel("");
		Prize_img.setBounds(152, 160, 111, 111);
		panel.add(Prize_img);
		
		JButton PlayBtn = new JButton("Play");
		PlayBtn.setBounds(129, 93, 159, 56);
		panel.add(PlayBtn);
		
		Prize_img.setIcon(ph);
		Plate_img.setIcon(p);
		
		PlayBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (PlayBtn.getText().equals("Play"))
				{
				int n1 = random.nextInt(2);
				int n2 = random.nextInt(2);		//generate 3 random 0s or 1s.
				int n3 = random.nextInt(2);
				int sum = n1+n2+n3;		//calculate sum
				
				if (sum == 3)
				{
					
					Prize_img.setIcon(tp);		//if sum is 3 all plates broken, they win.
					Plate_img.setIcon(bp);		//winning images
				}
				else if (sum == 2)
				{
					Prize_img.setIcon(s);		//if sum is 2, 2 plates broken, consolation prize
					Plate_img.setIcon(tbp);
				}
				
				else
				{
					Prize_img.setIcon(ph);		// otherwise they lose. nothing changes
					Plate_img.setIcon(p);
				}
				
				PlayBtn.setText("Play Again");	//after they play, "Play" button becomes a "Play Again" button
				
				}
				else if(PlayBtn.getText().equals("Play Again")) 
				{
					PlayBtn.setText("Play");	//button changes to a play button when they press play again and goes back to
					Prize_img.setIcon(ph);		//original state
					Plate_img.setIcon(p);
				}
			}
		});
	}
}
