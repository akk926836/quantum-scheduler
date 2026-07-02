# include <iostream> 
# include <cmath>

bool isValidMax(double num)
{
    if(num <= 0)
    {
        return false;
    }

    else{
        double remainder = fmod(num, 1.0);

        if (remainder == 0.0)
        {
            return true;
        }

        else
        {
            return false;
        }
    }
}

int main() 
{
    double final_max{0};
    do
    {
        std::cout << "Enter the maximum grade of the final examination: ";
        std::cin >> final_max;

    } while (!isValidMax(final_max)); 


    double finalExamGrade{0};

    do
    {
    std::cout << "Enter your final examination grade: ";
    std::cin >> finalExamGrade;
    } while (finalExamGrade > final_max || finalExamGrade < 0);
    

    double final_avg = (100*finalExamGrade)/final_max; 




    double midterm_max{0};
    do
    {
        std::cout << "Enter the maximum grade of the mid-term examination: ";
        std::cin >> midterm_max;
        

    } while (!isValidMax(midterm_max)); 


    double midtermExamGrade{0};

    do
    {
    std::cout << "Enter your mid-term examination grade: ";
    std::cin >> midtermExamGrade;
    } while (midtermExamGrade > midterm_max || midtermExamGrade < 0);
    

    double midterm_avg = (100*midtermExamGrade)/midterm_max;
    if(midterm_avg < final_avg)
    {
        midterm_avg = final_avg;
    }


    double proj1_max{0};
    do
    {
        std::cout << "Enter the maximum grade for project 1: ";
        std::cin >> proj1_max;

    } while (!isValidMax(proj1_max)); 


    double proj1Grade{0};

    do
    {
    std::cout << "Enter your project 1 grade: ";
    std::cin >> proj1Grade;
    } while (proj1Grade > proj1_max || proj1Grade < 0);
    

    double proj1_avg = (100*proj1Grade)/proj1_max;
    if(proj1_avg < final_avg)
    {
        proj1_avg = final_avg;
    }


    double proj2_max{0};
    do
    {
        std::cout << "Enter the maximum grade for project 2: ";
        std::cin >> proj2_max;
        

    } while (!isValidMax(proj2_max)); 


    double proj2Grade{0};

    do
    {
    std::cout << "Enter your project 2 grade: ";
    std::cin >> proj2Grade;
    } while (proj2Grade > proj2_max || proj2Grade < 0);
    

    double proj2_avg = (100*proj2Grade)/proj2_max; 
    if(proj2_avg < final_avg)
    {
        proj2_avg = final_avg;
    }


    double proj3_max{0};
    do
    {
        std::cout << "Enter the maximum grade for project 3: ";
        std::cin >> proj3_max;
        

    } while (!isValidMax(proj3_max)); 


    double proj3Grade{0};

    do
    {
    std::cout << "Enter your project 3 grade: ";
    std::cin >> proj3Grade;
    } while (proj3Grade > proj3_max || proj3Grade < 0);
    

    double proj3_avg = (100*proj3Grade)/proj3_max; 
    if(proj3_avg < final_avg)
    {
        proj3_avg = final_avg;
    }


    double proj4_max{0};
    do
    {
        std::cout << "Enter the maximum grade for project 4: ";
        std::cin >> proj4_max;
        
    } while (!isValidMax(proj4_max)); 


    double proj4Grade{0};

    do
    {
    std::cout << "Enter your project 4 grade: ";
    std::cin >> proj4Grade;
    } while (proj4Grade > proj4_max || proj4Grade < 0);
    

    double proj4_avg = (100*proj4Grade)/proj4_max; 
    if(proj4_avg < final_avg)
    {
        proj4_avg = final_avg;
    }


    double proj5_max{0};
    do
    {
        std::cout << "Enter the maximum grade for project 5: ";
        std::cin >> proj5_max;
        
    } while (!isValidMax(proj5_max)); 


    double proj5Grade{0};

    do
    {
    std::cout << "Enter your project 5 grade: ";
    std::cin >> proj5Grade;
    } while (proj5Grade > proj5_max || proj5Grade < 0);
    

    double proj5_avg = (100*proj5Grade)/proj5_max;

    if(proj5_avg < final_avg)
    {
        proj5_avg = final_avg;
    }

    double project_grade = 0.2*(proj1_avg+proj2_avg+proj3_avg+proj4_avg+proj5_avg);


    double course_grade;
    double exam_grade = 0.75*final_avg + 0.25*midterm_avg;
    if(final_avg <= 40)
    {
        course_grade = exam_grade;
    }

    else if (final_avg >= 60)
    {
       course_grade = (2*exam_grade)/3 + project_grade/3 ;
    }
    
    else if(40 < final_avg && final_avg < 60)
    {
        course_grade = (-1.0/60.0)*exam_grade*exam_grade + (5.0/3.0)*exam_grade+(1.0/60.0)*project_grade*exam_grade+(-2.0/3.0)*project_grade;
    }

    std::cout << "Final Grade: " << round(course_grade);

}

