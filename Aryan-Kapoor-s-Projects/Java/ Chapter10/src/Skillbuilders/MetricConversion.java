package Skillbuilders;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class MetricConversion {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MetricConversion window = new MetricConversion();
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
	public MetricConversion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 348, 241);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JComboBox ConversionChoice = new JComboBox();
		ConversionChoice.setModel(new DefaultComboBoxModel(new String[] {"Choose Conversion", "Inches to Centimeters", "Feet to Meters", "Gallons to Liters", "Pounds to Kilograms"}));
		ConversionChoice.setBounds(46, 50, 228, 46);
		panel.add(ConversionChoice);
		
		JTextArea txtrChooseAConversion = new JTextArea();
		txtrChooseAConversion.setBackground(UIManager.getColor("Button.background"));
		txtrChooseAConversion.setEditable(false);
		txtrChooseAConversion.setText("Choose a Conversion Type:");
		txtrChooseAConversion.setBounds(46, 11, 228, 31);
		panel.add(txtrChooseAConversion);
		
		JTextArea ConversionText = new JTextArea();
		ConversionText.setBackground(UIManager.getColor("Button.background"));
		ConversionText.setEditable(false);
		ConversionText.setBounds(46, 107, 228, 47);
		panel.add(ConversionText);
		
		ConversionChoice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
		if(ConversionChoice.getSelectedItem().equals("Inches to Centimeters"))
		{
			ConversionText.setText("1 inch = 2.54 centimeters");
		}
		
		else if(ConversionChoice.getSelectedItem().equals("Feet to Meters"))
		{
			ConversionText.setText("1 foot = 0.3048 meters");
		}
		else if(ConversionChoice.getSelectedItem().equals("Gallons to Liters"))
		{
			ConversionText.setText("1 gallon = 4.5461 liters");
		}
		else if(ConversionChoice.getSelectedItem().equals("Pounds to Kilograms"))
		{
			ConversionText.setText("1 pound = 0.4536 kilograms");
		}
	}
});
}
}
