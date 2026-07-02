/*
Program: University.java          Last Date of this Revision: April 16,2025

Purpose: A driver class that simulates a university staff registration system, allowing users to 
        input employee details (name, faculty, job title) and interactively view their position, department,
        and calculated salary.

Author: Aryan K, 
School: CHHS
Course: Computer Programming 30
*/
package Mastery.UEmployee;

import java.util.Scanner;

public class University 
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        // Initialize variables for department selection, job title, and user choices
        int department = 0;
        int title = 0;
        String dep_name = null;
        String job_name = null;
        double salary = 0;
        double pay = 0;
        int selection = -1;
        
        // Display program header
        System.out.println("\n============= University =============");
        
        // Prompt user to start registration or quit
        System.out.println("Do you want to Register a Staff member: (Y)es or (N)o");
        
        String createAcct = null;
        // Validate user input (only Y or N accepted)
        do
        {
            createAcct = input.next().toUpperCase();
            
            if(!(createAcct.equals("Y")) && !(createAcct.equals("N")))
            {
                System.out.println("Invalid Input, Try Again.");
            }
        }
        while(!(createAcct.equals("Y")) && !(createAcct.equals("N")));
        
        // Exit if user chooses not to register
        if(createAcct.equals("N"))
        {
            System.out.println("Quitting the University Application");
            System.exit(0);
        }
        
        // Collect basic employee information
        System.out.print("Enter your First Name: ");
        String fname = input.next();
        
        System.out.print("Enter your Last Name: ");
        String lname = input.next();
        
        // Department selection menu with validation
        do {
            System.out.println("Enter the faculty to be joined:");
            System.out.println("\n(1) Faculty of Arts\n(2) Faculty of Science\n(3) Faculty of Business");
            department = input.nextInt();
            
            // Set department name and base salary based on selection
            switch(department)
            {
                case 1: dep_name = "Arts"; salary = 20; break;
                case 2: dep_name = "Science"; salary = 25; break;
                case 3: dep_name = "Business"; salary = 30; break;
                default: System.out.println("Error. Incorrect Input.");
            }
        } while(!(department == 1) && !(department == 2) && !(department == 3));
        
        // Create Faculty object with collected information
        Faculty fac = new Faculty(dep_name, fname, lname, salary);
        
        // Job title selection menu with validation
        do {
            System.out.println("Enter the job title:");
            System.out.println("\n(1)Assistant Professor\n(2)Professor\n(3)Dean");
            title = input.nextInt();
            
            // Set job title and pay multiplier based on selection
            switch(title)
            {
                case 1: job_name = "Assistant Professor"; pay = 1; break;
                case 2: job_name = "Professor"; pay = 2; break;
                case 3: job_name = "Dean"; pay = 3; break;
                default: System.out.println("Error. Incorrect Input.");
            }
        } while(!(title == 1) && !(title == 2) && !(title == 3));
        
        // Create Staff object with collected information
        Staff position = new Staff(job_name, fname, lname, pay);
        
        // Main interaction menu
        do {
            System.out.print("\n========== University Staff Account ==========\n");
            System.out.println(position.getName());
            System.out.println("Choose an Option: ");
            System.out.println("(1)Display Position and Faculty \n(2)Display Salary\n(0)Quit");
            selection = input.nextInt();
            
            // Process user selection
            switch(selection)
            {
                case 0: 
                    System.out.println("Quitting the Application.");
                    System.exit(0); 
                    break;
                case 1: 
                    System.out.println("Position: " + job_name + ", Faculty of " + dep_name);
                    break;
                case 2: 
                    System.out.println("Pay: $" + salary*pay + "/hr ");
                    break;
            }
        } while(selection != 0);
    }
}

/*
 * ScreenDump:
 * Case1:
 * ============= University =============
Do you want to Register a Staff member: (Y)es or (N)o
y
Enter your First Name: Aryan
Enter your Last Name: K
Enter the faculty to be joined:

(1) Faculty of Arts
(2) Faculty of Science
(3) Faculty of Business
2
Enter the job title:

(1)Assistant Professor
(2)Professor
(3)Dean
3

========== University Staff Account ==========
Aryan K
Choose an Option: 
(1)Display Position and Faculty 
(2)Display Salary
(0)Quit
2
Pay: $75.0/hr 

========== University Staff Account ==========
Aryan K
Choose an Option: 
(1)Display Position and Faculty 
(2)Display Salary
(0)Quit
1
Position: Dean, Faculty of Science

========== University Staff Account ==========
Aryan K
Choose an Option: 
(1)Display Position and Faculty 
(2)Display Salary
(0)Quit
0
Quitting the Application.

Case2:

============= University =============
Do you want to Register a Staff member: (Y)es or (N)o
y
Enter your First Name: Rishi
Enter your Last Name: Justin
Enter the faculty to be joined:

(1) Faculty of Arts
(2) Faculty of Science
(3) Faculty of Business
3
Enter the job title:

(1)Assistant Professor
(2)Professor
(3)Dean
2

========== University Staff Account ==========
Rishi Justin
Choose an Option: 
(1)Display Position and Faculty 
(2)Display Salary
(0)Quit
1
Position: Professor, Faculty of Business

========== University Staff Account ==========
Rishi Justin
Choose an Option: 
(1)Display Position and Faculty 
(2)Display Salary
(0)Quit
2
Pay: $60.0/hr 

========== University Staff Account ==========
Rishi Justin
Choose an Option: 
(1)Display Position and Faculty 
(2)Display Salary
(0)Quit
0
Quitting the Application.
 */