#pragma once
#include <iostream>
#include <string>
#include "degreeProgram.h"
using namespace std;
/*D.For the Student class, do the following :
1.  Create the class Student  in the files student.h and student.cpp, which includes each of the following variables :
•  student ID
•  first name
•   last name
•  email address
•  age
•  array of number of days to complete each course
•  degree program
*/
class Student {

public:
	Student();
	Student(string studentIDAcc, string firstNameAcc, string lastNameAcc, string emailAddressAcc, int ageAcc, int daysInCourse1Acc, int daysInCourse2Acc, int daysInCourse3Acc, degreeProgram majorAcc);
	void PrintAllStudentInfo();

	void SetStudentID(string studentIDAcc);
	string GetStudentID() const;

	void SetFirstName(string firstNameAcc);
	string GetFirstName() const;

	void SetLastName(string lastNameAcc);
	string GetLastName() const;

	void SetAge(int ageAcc);
	int GetAge() const;

	void SetdaysInCourseAll(int daysInCourseAcc1, int daysInCourseAcc2, int daysInCourseAcc3);
	void SetdaysInCourse1(int daysInCourseAcc);
	void SetdaysInCourse2(int daysInCourseAcc);
	void SetdaysInCourse3(int daysInCourseAcc);

	void PrintDaysInCourses();
	int GetdaysInCourse1();
	int GetdaysInCourse2();
	int GetdaysInCourse3();

	void SetdegreeProgram(degreeProgram degreeProgramAcc);
	degreeProgram GetdegreeProgram() const;

	void SetemailAddress(string emailAddressAcc);
	string GetemailAddress() const;

	

protected:
	string studentId;
	string firstName;
	string lastName;
	string emailAddress;
	int age;
	int numDaysInEachCourse[3];
	degreeProgram major;
};
