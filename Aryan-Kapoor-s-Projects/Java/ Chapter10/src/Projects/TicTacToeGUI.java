/*

Program: TicTacToeGUI.java          Last Date of this Revision: 23rd February 2025

Purpose: A graphical user interface that allows a user to play the game Tic Tac Toe.

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
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TicTacToeGUI {
    // Define player symbols
    String player1 = "X";
    String player2 = "O";
    String currPlayer = player1; // Current player starts with player1 ("X")
    int movesMade = 0; // Counter to track the number of moves made

    // GUI components
    private JFrame frame; // Main window of the game
    private JButton btn11, btn12, btn13; // Buttons for row 1
    private JButton btn21, btn22, btn23; // Buttons for row 2
    private JButton btn31, btn32, btn33; // Buttons for row 3

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        // Use EventQueue to ensure the GUI is created on the event dispatch thread
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TicTacToeGUI window = new TicTacToeGUI(); // Create an instance of the game
                    window.frame.setVisible(true); // Make the game window visible
                } catch (Exception e) {
                    e.printStackTrace(); // Print any errors that occur
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public TicTacToeGUI() {
        initialize(); // Initialize the GUI components
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        // Create the main window (JFrame)
        frame = new JFrame();
        frame.setResizable(false); // Prevent resizing of the window
        frame.setBounds(100, 100, 499, 518); // Set window size and position
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the window is closed

        // Create a panel to hold the game board
        JPanel board = new JPanel();
        frame.getContentPane().add(board, BorderLayout.CENTER); // Add the panel to the frame
        board.setLayout(null); // Use absolute positioning for components

        // Create and configure the title label
        JLabel title = new JLabel("Tic Tac Toe");
        title.setToolTipText(""); // No tooltip
        title.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
        title.setFont(new Font("Microsoft YaHei", Font.BOLD, 24)); // Set font and size
        title.setBounds(141, 11, 174, 60); // Set position and size
        board.add(title); // Add the title to the panel

        // Create and configure the status label (to show game messages)
        JLabel proIndicator = new JLabel("");
        proIndicator.setFont(new Font("Microsoft YaHei", Font.BOLD, 14)); // Set font and size
        proIndicator.setHorizontalAlignment(SwingConstants.CENTER); // Center-align the text
        proIndicator.setBounds(66, 413, 338, 43); // Set position and size
        board.add(proIndicator); // Add the status label to the panel
        proIndicator.setText("Player 1 is 'X' and Player 2 is 'O' "); // Set initial text

        // Create and configure the buttons for the Tic-Tac-Toe grid
        btn11 = new JButton(""); // Button at row 1, column 1
        btn11.setBounds(56, 68, 100, 100); // Set position and size
        board.add(btn11); // Add the button to the panel

        btn12 = new JButton(""); // Button at row 1, column 2
        btn12.setBounds(180, 68, 100, 100); // Set position and size
        board.add(btn12); // Add the button to the panel

        btn13 = new JButton(""); // Button at row 1, column 3
        btn13.setBounds(304, 68, 100, 100); // Set position and size
        board.add(btn13); // Add the button to the panel

        btn21 = new JButton(""); // Button at row 2, column 1
        btn21.setBounds(56, 179, 100, 100); // Set position and size
        board.add(btn21); // Add the button to the panel

        btn22 = new JButton(""); // Button at row 2, column 2
        btn22.setBounds(180, 179, 100, 100); // Set position and size
        board.add(btn22); // Add the button to the panel

        btn23 = new JButton(""); // Button at row 2, column 3
        btn23.setBounds(304, 179, 100, 100); // Set position and size
        board.add(btn23); // Add the button to the panel

        btn31 = new JButton(""); // Button at row 3, column 1
        btn31.setBounds(56, 291, 100, 100); // Set position and size
        board.add(btn31); // Add the button to the panel

        btn32 = new JButton(""); // Button at row 3, column 2
        btn32.setBounds(180, 290, 100, 100); // Set position and size
        board.add(btn32); // Add the button to the panel

        btn33 = new JButton(""); // Button at row 3, column 3
        btn33.setBounds(304, 290, 100, 100); // Set position and size
        board.add(btn33); // Add the button to the panel

        // Create an ActionListener to handle button clicks
        ActionListener buttonListener = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JButton button = (JButton) e.getSource(); // Get the button that was clicked
                button.setText(currPlayer); // Set the button text to the current player's symbol
                button.setEnabled(false); // Disable the button after it's clicked
                movesMade++; // Increment the move counter

                // Check if the current player has won
                if (checkForWin()) {
                    proIndicator.setText("Player " + currPlayer + " wins!"); // Display win message
                    disableAllButtons(); // Disable all buttons to end the game
                } else if (movesMade == 9) { // Check if the game is a draw
                    proIndicator.setText("It's a draw!"); // Display draw message
                } else {
                    // Switch players
                    if (currPlayer == player1) {
                        currPlayer = player2;
                    } else {
                        currPlayer = player1;
                    }
                }
            }
        };

        // Add the ActionListener to each button
        btn11.addActionListener(buttonListener);
        btn12.addActionListener(buttonListener);
        btn13.addActionListener(buttonListener);
        btn21.addActionListener(buttonListener);
        btn22.addActionListener(buttonListener);
        btn23.addActionListener(buttonListener);
        btn31.addActionListener(buttonListener);
        btn32.addActionListener(buttonListener);
        btn33.addActionListener(buttonListener);
    }

    /**
     * Check if the current player has won the game.
     */
    private boolean checkForWin() {
        // Check rows
        if (rowCheck(btn11, btn12, btn13)) return true; // Check row 1
        if (rowCheck(btn21, btn22, btn23)) return true; // Check row 2
        if (rowCheck(btn31, btn32, btn33)) return true; // Check row 3

        // Check columns
        if (rowCheck(btn11, btn21, btn31)) return true; // Check column 1
        if (rowCheck(btn12, btn22, btn32)) return true; // Check column 2
        if (rowCheck(btn13, btn23, btn33)) return true; // Check column 3

        // Check diagonals
        if (rowCheck(btn11, btn22, btn33)) return true; // Check diagonal 1
        if (rowCheck(btn13, btn22, btn31)) return true; // Check diagonal 2

        return false; // No win condition met
    }

    /**
     * Check if three buttons have the same text (player symbol) and are not empty.
     */
    private boolean rowCheck(JButton btn1, JButton btn2, JButton btn3) {
        String n1 = btn1.getText(); // Get text of button 1
        String n2 = btn2.getText(); // Get text of button 2
        String n3 = btn3.getText(); // Get text of button 3

        // Check if all three buttons have the same text and are not empty
        if (n1.equals(n2) && n1.equals(n3) && !(n1.equals(null))) {
            return true; // Win condition met
        } else {
            return false; // Win condition not met
        }
    }

    /**
     * Disable all buttons to end the game.
     */
    private void disableAllButtons() {
        btn11.setEnabled(false); // Disable button at row 1, column 1
        btn12.setEnabled(false); // Disable button at row 1, column 2
        btn13.setEnabled(false); // Disable button at row 1, column 3

        btn21.setEnabled(false); // Disable button at row 2, column 1
        btn22.setEnabled(false); // Disable button at row 2, column 2
        btn23.setEnabled(false); // Disable button at row 2, column 3

        btn31.setEnabled(false); // Disable button at row 3, column 1
        btn32.setEnabled(false); // Disable button at row 3, column 2
        btn33.setEnabled(false); // Disable button at row 3, column 3
    }
}
