
/*

Program: LocalBankGUI.java          Last Date of this Revision: 23rd February 2025

Purpose: A graphical user interface that allows a user to perform a plethora of actions for their bank account, such as adding
and removing accounts, or depositing and withdrawing funds.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class LocalBankGUI {

	private JFrame frame;
	private JTextField accID;
	private JTextField cflow;
	private JTextField Fname;
	private JTextField Lname;
	private JTextField balance;
	private JLabel AccountIDLabel;
	private JLabel CashFlowLabel;
	private JLabel FirstNameLabel;
	private JLabel LastNameLabel;
	private JLabel BalanceLabel;
	private JTextArea AccountInfoLabel;
	private JButton button;
	private Bank myBank;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LocalBankGUI window = new LocalBankGUI();
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
	public LocalBankGUI() {
		initialize();
        myBank = new Bank();
        
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//all GUI components are declared here.
		frame = new JFrame();
		frame.setBounds(100, 100, 431, 683);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		
		JLabel SelectLabel = new JLabel("Select an action: ");
		SelectLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
		SelectLabel.setBounds(10, 32, 193, 14);
		panel.add(SelectLabel);
		
		JLabel GuideLabel = new JLabel("Complete the information in RED: ");
		GuideLabel.setFont(new Font("SansSerif", Font.BOLD, 11));
		GuideLabel.setBounds(10, 100, 193, 14);
		panel.add(GuideLabel);
		
		AccountIDLabel = new JLabel("Account ID: ");
		AccountIDLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		AccountIDLabel.setBounds(10, 125, 185, 14);
		panel.add(AccountIDLabel);
		
		accID = new JTextField();
		accID.setFont(new Font("SansSerif", Font.PLAIN, 11));
		accID.setBounds(10, 145, 395, 20);
		panel.add(accID);
		accID.setColumns(10);
		
		CashFlowLabel = new JLabel("Deposit/Withdrawal Amount: ");
		CashFlowLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		CashFlowLabel.setBounds(10, 185, 185, 14);
		panel.add(CashFlowLabel);
		
		cflow = new JTextField();
		cflow.setFont(new Font("SansSerif", Font.PLAIN, 11));
		cflow.setColumns(10);
		cflow.setBounds(10, 205, 395, 20);
		panel.add(cflow);
		
		FirstNameLabel = new JLabel("First Name: ");
		FirstNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		FirstNameLabel.setBounds(10, 245, 185, 14);
		panel.add(FirstNameLabel);
		
		Fname = new JTextField();
		Fname.setFont(new Font("SansSerif", Font.PLAIN, 11));
		Fname.setColumns(10);
		Fname.setBounds(10, 265, 395, 20);
		panel.add(Fname);
		
		LastNameLabel = new JLabel("Last Name: ");
		LastNameLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		LastNameLabel.setBounds(10, 305, 185, 14);
		panel.add(LastNameLabel);
		
		Lname = new JTextField();
		Lname.setFont(new Font("SansSerif", Font.PLAIN, 11));
		Lname.setColumns(10);
		Lname.setBounds(10, 325, 395, 20);
		panel.add(Lname);
		
		BalanceLabel = new JLabel("Beginning Balance: ");
		BalanceLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		BalanceLabel.setBounds(10, 365, 185, 14);
		panel.add(BalanceLabel);
		
		balance = new JTextField();
		balance.setFont(new Font("SansSerif", Font.PLAIN, 11));
		balance.setColumns(10);
		balance.setBounds(10, 385, 395, 20);
		panel.add(balance);
		
		AccountInfoLabel = new JTextArea("Account Info Displayed Here");
		AccountInfoLabel.setEditable(false);
		AccountInfoLabel.setFont(new Font("SansSerif", Font.PLAIN, 11));
		AccountInfoLabel.setBounds(10, 425, 395, 179);
		panel.add(AccountInfoLabel);
		
		
		button = new JButton("Confirm Transaction");

		button.setBounds(10, 610, 185, 23);
		panel.add(button);
		
		JComboBox choice = new JComboBox();
		choice.setFont(new Font("SansSerif", Font.PLAIN, 11));
		choice.setModel(new DefaultComboBoxModel(new String[] {"Select", "Deposit", "Withdrawal", "Check Balance", "Add an Account", "Remove an Account", "Quit"}));
		choice.setBounds(10, 50, 395, 28);
		panel.add(choice);
		
		//action listener for combo box to designate which labels are red to show which options the user should input.
		choice.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				selection(choice.getSelectedItem());
			}
		});
		
		//action listener for the button to designate the actions to be taken when a transaction goes through.
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AccountAction(choice.getSelectedItem());
			}
		});
		}
	
	//method which decided which labels are red depending on the option selected in combo box
	private void selection(Object obj)
	{
		if (obj.equals("Deposit") || obj.equals("Withdrawal"))
		{
			clear();
			
			isRed(AccountIDLabel);
			isRed(CashFlowLabel);
			
			isNotRed(FirstNameLabel);
			isNotRed(LastNameLabel);
			isNotRed(BalanceLabel);
		}
		
		else if(obj.equals("Check Balance")) 
		{
			clear();
			
			isRed(AccountIDLabel);
			
			isNotRed(FirstNameLabel);
			isNotRed(LastNameLabel);
			isNotRed(CashFlowLabel);
			isNotRed(BalanceLabel);
			
		}
		
		else if(obj.equals("Add an Account"))
		{
			clear();
			
			isRed(FirstNameLabel);
			isRed(LastNameLabel);
			isRed(BalanceLabel);
			
			isNotRed(CashFlowLabel);
			isNotRed(AccountIDLabel);
			
			
		}
		
		else if(obj.equals("Remove an Account"))
		{	
			clear();
			
			isRed(AccountIDLabel);
			
			isNotRed(CashFlowLabel);
			isNotRed(FirstNameLabel);
			isNotRed(LastNameLabel);
			isNotRed(BalanceLabel);
			
			 
		}
		else if(obj.equals("Quit"))
		{
			clear();
			
			isNotRed(AccountIDLabel);
			isNotRed(CashFlowLabel);
			isNotRed(FirstNameLabel);
			isNotRed(LastNameLabel);
			isNotRed(BalanceLabel);
			
			button.setText("Press to Quit");
			
		}
		
		else 
		{
			clear();
			
			isNotRed(AccountIDLabel);
			isNotRed(CashFlowLabel);
			isNotRed(FirstNameLabel);
			isNotRed(LastNameLabel);
			isNotRed(BalanceLabel);
			
		}
	}
	
	//method which decided account actions by calling the bank object we created and presents the change made to the
	//account in the account info label.
	private void AccountAction(Object obj) {
		if (obj.equals("Deposit"))
		{
			AccountInfoLabel.setText(myBank.deposit(accID.getText(),Double.parseDouble(cflow.getText())));
		}
		
		else if(obj.equals("Withdrawal")) 
		{
			AccountInfoLabel.setText(myBank.withdraw(accID.getText(),Double.parseDouble(cflow.getText())));
		}
		
		else if(obj.equals("Check Balance")) 
		{
			AccountInfoLabel.setText(myBank.checkBalance(accID.getText()));
		}
		
		else if(obj.equals("Add an Account"))
		{
			AccountInfoLabel.setText(myBank.AddAccount(Fname.getText(), Lname.getText(),Double.parseDouble(balance.getText()))); 
		}
		
		else if(obj.equals("Remove an Account"))
		{	
			AccountInfoLabel.setText(myBank.removeAccount(accID.getText()));
		}
		else if (obj.equals("Quit"))
		{
			System.exit(0);
		}
	}
	
	//sets text colour to red
	private void isRed(JLabel lbl) {
		lbl.setForeground(Color.RED);
	}
	
	//sets text colour to black
	private void isNotRed(JLabel lbl) {
		lbl.setForeground(Color.black);
	}
	
	//sets all relevant components to original value so multiple actions can be taken.
	private void clear() {
		accID.setText(null);
		cflow.setText(null);
		Fname.setText(null);
		Lname.setText(null);
		balance.setText(null);
		AccountInfoLabel.setText(null);
		button.setText("Confirm Transaction");
		
	}
	
}
