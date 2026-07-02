
/*

Program: StuSemAvgGUI.java          Last Date of this Revision: March 31, 2025

Purpose: An application that allows you to write and compile the data of students to a file and then view the data in a GUI.

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
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.ActionEvent;

public class StuSemAvgGUI {

    // Main application window
    private JFrame frmStudentGradeApplication;
    
    // Input fields for student data
    private JTextField name;          // Student name
    private JTextField grade;         // Grade level
    private JTextField semNo;         // Semester number
    private JTextField grade1;        // Grade 1
    private JTextField grade2;        // Grade 2
    private JTextField grade3;        // Grade 3
    private JTextField grade4;        // Grade 4
    
    // File handling components
    private File dataFile;            // Reference to data file
    private FileReader in;            // File reader
    private String filename;          // File name/path
    private BufferedReader readFile;  // Buffered reader
    private FileWriter out;           // File writer
    private BufferedWriter writeFile; // Buffered writer
    
    // Student information object
    private StuInfo student;

    /**
     * Main method - Launches the application
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StuSemAvgGUI window = new StuSemAvgGUI();
                    window.frmStudentGradeApplication.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor - Initializes the application
     */
    public StuSemAvgGUI() {
        initialize();
    }

    /**
     * Initialize all GUI components
     */
    private void initialize() {
        // Main window setup
        frmStudentGradeApplication = new JFrame();
        frmStudentGradeApplication.setTitle("Student Grade Application");
        frmStudentGradeApplication.setBounds(100, 100, 485, 560);
        frmStudentGradeApplication.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Main content panel
        JPanel panel = new JPanel();
        frmStudentGradeApplication.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null); // Absolute positioning

        // ================= LABELS ================= //
        // Student Name Label
        JLabel lblName = new JLabel("Student Name: ");
        lblName.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblName.setBounds(10, 31, 164, 24);
        panel.add(lblName);
        
        // Grade Level Label
        JLabel lblGradeLevel = new JLabel("Grade Level: ");
        lblGradeLevel.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblGradeLevel.setBounds(10, 66, 164, 24);
        panel.add(lblGradeLevel);
        
        // Semester Number Label
        JLabel lblSemesterNo = new JLabel("Semester Number: ");
        lblSemesterNo.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblSemesterNo.setBounds(10, 101, 164, 24);
        panel.add(lblSemesterNo);
        
        // Grade 1 Label
        JLabel lblGrade1 = new JLabel("Grade 1: ");
        lblGrade1.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblGrade1.setBounds(10, 136, 164, 24);
        panel.add(lblGrade1);
        
        // Grade 2 Label
        JLabel lblGrade2 = new JLabel("Grade 2: ");
        lblGrade2.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblGrade2.setBounds(10, 171, 164, 24);
        panel.add(lblGrade2);
        
        // Grade 3 Label
        JLabel lblGrade3 = new JLabel("Grade 3: ");
        lblGrade3.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblGrade3.setBounds(10, 206, 164, 24);
        panel.add(lblGrade3);
        
        // Grade 4 Label
        JLabel lblGrade4 = new JLabel("Grade 4: ");
        lblGrade4.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblGrade4.setBounds(10, 241, 164, 24);
        panel.add(lblGrade4);
        
        // Average Label
        JLabel lblAverage = new JLabel("Average: ");
        lblAverage.setFont(new Font("Times New Roman", Font.BOLD, 11));
        lblAverage.setBounds(10, 276, 164, 24);
        panel.add(lblAverage);

        // ================= TEXT FIELDS ================= //
        // Student Name Field
        name = new JTextField();
        name.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        name.setBounds(227, 33, 232, 20);
        panel.add(name);
        name.setColumns(10);
        
        // Grade Level Field
        grade = new JTextField();
        grade.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        grade.setBounds(227, 68, 232, 20);
        panel.add(grade);
        grade.setColumns(10);
        
        // Semester Number Field
        semNo = new JTextField();
        semNo.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        semNo.setBounds(227, 103, 232, 20);
        panel.add(semNo);
        semNo.setColumns(10);
        
        // Grade 1 Field
        grade1 = new JTextField();
        grade1.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        grade1.setBounds(227, 138, 232, 20);
        panel.add(grade1);
        grade1.setColumns(10);
        
        // Grade 2 Field
        grade2 = new JTextField();
        grade2.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        grade2.setBounds(227, 173, 232, 20);
        panel.add(grade2);
        grade2.setColumns(10);
        
        // Grade 3 Field
        grade3 = new JTextField();
        grade3.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        grade3.setBounds(227, 208, 232, 20);
        panel.add(grade3);
        grade3.setColumns(10);
        
        // Grade 4 Field
        grade4 = new JTextField();
        grade4.setFont(new Font("Times New Roman", Font.PLAIN, 11));
        grade4.setBounds(227, 243, 232, 20);
        panel.add(grade4);
        grade4.setColumns(10);

        // ================= OUTPUT AREA ================= //
        JTextArea avgText = new JTextArea();
        avgText.setFont(new Font("Times New Roman", Font.PLAIN, 13));
        avgText.setBounds(10, 297, 449, 170);
        panel.add(avgText);
        
        // Average Score Display
        JLabel avgScore = new JLabel("");
        avgScore.setFont(new Font("Times New Roman", Font.BOLD, 11));
        avgScore.setBounds(57, 276, 105, 24);
        panel.add(avgScore);

        // ================= BUTTONS ================= //
        // Save Button
        JButton saveBtn = new JButton("Save to File");
        saveBtn.setBounds(57, 478, 154, 32);
        panel.add(saveBtn);
        
        // View File Button
        JButton fileContentBtn = new JButton("View File Contents");
        fileContentBtn.setBounds(223, 478, 154, 32);
        panel.add(fileContentBtn);

        // ================= FILE SETUP ================= //
        File file = new File("C:\\Users\\Aryan Kapoor\\git\\cs30p32025-ak0122\\Chapter11\\src\\Mastery\\StudentInfo");

        // ================= BUTTON ACTIONS ================= //
        // Save Button Action
        saveBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Create student object with form data
                student = new StuInfo(name.getText(), grade.getText(), semNo.getText(),
                                    grade1.getText(), grade2.getText(), grade3.getText(), grade4.getText());
                
                // Display average
                avgScore.setText(student.getAvg());
                
                // Get formatted student info
                String info = student.getInfo();
                
                try {
                    // Write to file
                    out = new FileWriter(file, true);
                    writeFile = new BufferedWriter(out);
                    writeFile.write(info);
                    writeFile.newLine();
                    writeFile.close();
                    out.close();
                    
                    // Show success message
                    JOptionPane.showMessageDialog(frmStudentGradeApplication,
                            "Successfully written to file!",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                } catch(FileNotFoundException e2) {
                    System.err.println("File was not found.");
                } catch(IOException e2) {
                    System.err.println("Error reading the file.");
                }
                
                // Clear all fields
                name.setText("");
                grade.setText("");
                semNo.setText("");
                grade1.setText("");
                grade2.setText("");
                grade3.setText("");
                grade4.setText("");
                avgScore.setText("");
            }
        });
        
        // View File Button Action
        fileContentBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Read file contents
                    in = new FileReader(file);
                    readFile = new BufferedReader(in);
                    String line;
                    String output = "";
                    
                    while((line = readFile.readLine()) != null) {
                        output += (line + "\n");
                    }
                    avgText.setText(output);
                    
                    readFile.close();
                    in.close();
                    
                } catch (FileNotFoundException e1) {
                    System.err.println("File Not Found.");
                    e1.printStackTrace();
                } catch (IOException e1) {
                    System.err.println("Error reading the file.");
                }
            }
        });
    }
}