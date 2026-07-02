
/*

Program: StuInfo.java          Last Date of this Revision: March 31, 2025

Purpose: A class that stores and processes information for the StuSemAvgGUI program.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
 

*/
package Mastery;

import java.text.DecimalFormat;


public class StuInfo {
    // Student information fields
    String Name;    // Student name
    String gr1;     // Grade 1
    String gr2;     // Grade 2
    String gr3;     // Grade 3
    String gr4;     // Grade 4
    String grl;     // Grade level
    String semNo;   // Semester number
    
    // Formatter for displaying averages with 2 decimal places
    DecimalFormat df = new DecimalFormat("0.00");
    
    /**
     * Constructor - Initializes a new StuInfo object with all student data
     * 
     * @param name Student name
     * @param gl Grade level
     * @param sem Semester number
     * @param g1 Grade 1
     * @param g2 Grade 2
     * @param g3 Grade 3
     * @param g4 Grade 4
     */
    public StuInfo(String name, String gl, String sem, String g1, String g2, String g3, String g4) 
    {
        Name = name;
        gr1 = g1;
        gr2 = g2;
        gr3 = g3;
        gr4 = g4;
        grl = gl;
        semNo = sem;
    }
    
    /**
     * Calculates and returns the average of the four grades
     * 
     * @return Formatted average as a percentage string (e.g., "85.25%")
     */
    public String getAvg() 
    {
        // Convert grades to doubles and calculate weighted average
        double avg = 0.25 * (
            Double.parseDouble(gr1) + 
            Double.parseDouble(gr2) + 
            Double.parseDouble(gr3) + 
            Double.parseDouble(gr4)
        );
        
        // Format to 2 decimal places and add percentage sign
        return df.format(avg) + "%";
    }
    
    /**
     * Generates a formatted string containing all student information
     * 
     * @return String in format: "Name: [name], Grade Level: [level], Semester: [sem], 
     *         Grades: [g1], [g2], [g3], [g4], Average: [avg]"
     */
    public String getInfo()
    {
        return "Name:" + Name + 
               ", Grade Level: " + grl + 
               " , Semester: " + semNo +
               " , Grades: " + gr1 + ", " + gr2 + ", " + gr3 + ", " + gr4 + 
               ", Average: " + getAvg();
    }
}