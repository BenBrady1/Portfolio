#pragma once
#include <iostream>
#include <string>
#include "student.h"
#include "degreeProgram.h"
using namespace std;


class Roster : public Student {
public:
    Roster();
    ~Roster(){} //destructor
    Roster(int arrSize);
    void add(string studentIDAcc, string firstNameAcc, string lastNameAcc, string emailAddressAcc, int ageAcc, int daysInCourse1Acc, int daysInCourse2Acc, int daysInCourse3Acc, degreeProgram degreeProgramAcc);
    void remove(string studentID);
    void printAll();
    void printAverageDaysInCourse(string studentId);
    void printInvalidEmails();
    void printByDegreeProgram(degreeProgram major);
    void parse();

    
protected:
    string studentID;
    string firstName;
    string lastName;
    string emailAddress;
    int age;
    int daysInCourse1;
    int daysInCourse2;
    int daysInCourse3;
    degreeProgram major;

private:

    //E1.  Create an array of pointers, classRosterArray, to hold the data provided in the “studentData Table.”
    int arrSize = 5;
    Student** classRosterArray;
};
 