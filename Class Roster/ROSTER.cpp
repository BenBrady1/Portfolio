#pragma once

#include <iostream>
#include <string>
#include <sstream>
#include "roster.h"
#include "student.h"
#include "degreeProgram.h"
using namespace std;


Roster::Roster(int arrSize) {
	//A.  Modify the “studentData Table” to include your personal information as the last item.
	const string studentData[5] = {
	"A1,John,Smith,John1989@gm ail.com,20,30,35,40,SECURITY",
	"A2,Suzan,Erickson,Erickson_1990@gmailcom,19,50,30,40,NETWORK",
	"A3,Jack,Napoli,The_lawyer99yahoo.com,19,20,40,33,SOFTWARE",
	"A4,Erin,Black,Erin.black@comcast.net,22,50,58,40,SECURITY",
	"A5,Ben,Brady,bbrad61@wgu.edu,31,7,7,17,SOFTWARE" };
	
	this->classRosterArray = new Student * [arrSize];
	//2.a.Parse each set of data identified in the “studentData Table.”
	//https://www.youtube.com/watch?v=lz2uITxbTiY&t=113s
	for (int i = 0; i < arrSize; i++) {
		string data;
		string studentIDAcc, firstNameAcc, lastNameAcc, emailAddressAcc;
		int ageAcc, daysInCourse1Acc, daysInCourse2Acc, daysInCourse3Acc;
		degreeProgram degreeProgramAcc;

		stringstream sFinder(studentData[i]);
		//get strings from data table
		getline(sFinder, data, ',');
		studentIDAcc = data;
		getline(sFinder, data, ',');
		firstNameAcc = data;
		getline(sFinder, data, ',');
		lastNameAcc = data;
		getline(sFinder, data, ',');
		emailAddressAcc = data;
		getline(sFinder, data, ',');

		//make strings int 
		ageAcc = (stoi(data));
		getline(sFinder, data, ',');
		daysInCourse1Acc = (stoi(data));
		getline(sFinder, data, ',');
		daysInCourse2Acc = (stoi(data));
		getline(sFinder, data, ',');
		daysInCourse3Acc = (stoi(data));

		//make string into enum 
		getline(sFinder, data);
		if (data == "SECURITY") {
			degreeProgramAcc = SECURITY;
		}
		if (data == "SOFTWARE") {
			degreeProgramAcc = SOFTWARE;
		}
		if (data == "NETWORK") {
			degreeProgramAcc = NETWORK;
		}
		//2. Create a student object for each student in the data table and populate classRosterArray.
		//b.Add each student object to classRosterArray.
		classRosterArray[i] = new Student(studentIDAcc, firstNameAcc, lastNameAcc, emailAddressAcc, ageAcc, daysInCourse1Acc, daysInCourse2Acc, daysInCourse3Acc, degreeProgramAcc);
	}
}
 //3.  Define the following functions:
 //a.  public void add(string studentID, string firstName, string lastName, string emailAddress, int age, int daysInCourse1, int daysInCourse2, int daysInCourse3, DegreeProgram degreeprogram)  that sets the instance variables from part D1 and updates the roster.
void Roster::add (string studentIDAcc, string firstNameAcc, string lastNameAcc, string emailAddressAcc, int ageAcc, int daysInCourse1Acc, int daysInCourse2Acc, int daysInCourse3Acc, degreeProgram degreeProgramAcc) {
	studentID = studentIDAcc;
    firstName = firstNameAcc;
    lastName = lastNameAcc;
    emailAddress = emailAddressAcc;
    age = ageAcc;
    daysInCourse1 = daysInCourse1Acc;
    daysInCourse2 = daysInCourse2Acc;
    daysInCourse3 = daysInCourse3Acc;
	major = degreeProgramAcc;
}
//c. public void printAll() that prints a complete tab - separated list of student data in the provided format 
void Roster::printAll() {
	for (int i = 0; i < arrSize; ++i) {
		classRosterArray[i]->PrintAllStudentInfo();
	}
}

//b.  public void remove(string studentID)  that removes students from the roster by student ID. If the student ID does not exist, the function prints an error message indicating that the student was not found.
void Roster::remove(string studentID) {

	//Find student that will be remove
	for (int i = 0; i < arrSize; i++) {
		string studentFound;
		studentFound = classRosterArray[i]->GetStudentID();

		//remove student
		if (studentID == studentFound) {
			classRosterArray[i]->SetStudentID("");
			classRosterArray[i]->SetFirstName("");
			classRosterArray[i]->SetLastName("");
			classRosterArray[i]->SetemailAddress("");
			classRosterArray[i]->SetAge(0);
			classRosterArray[i]->SetdaysInCourse1(0);
			classRosterArray[i]->SetdaysInCourse2(0);
			classRosterArray[i]->SetdaysInCourse3(0);
		}
	}
}

 //d.  public void printAverageDaysInCourse(string studentID)  that correctly prints a student’s average number of days in the three courses. The student is identified by the studentID parameter.
void Roster::printAverageDaysInCourse(string studentId) {
	string ID = studentId;
	for (int i = 0; i < arrSize; i++)
	{	//Find student that will be remove
		if (classRosterArray[i]->GetStudentID() == ID){
			string name = classRosterArray[i]->GetFirstName();
			//Get average
			int daysInCourse1 = classRosterArray[i]->GetdaysInCourse1();
			int daysInCourse2 = classRosterArray[i]->GetdaysInCourse2();
			int daysInCourse3 = classRosterArray[i]->GetdaysInCourse3();
			double avgDays = static_cast<double>((daysInCourse1 + daysInCourse2 + daysInCourse3) / 3);
			
			cout << "the average number of days "<<name <<" spends in a course is "<< avgDays << endl;
		}
	}	
}

 //e.  public void printInvalidEmails() that verifies student email addressesand displays all invalid email addresses to the user.
      //Note: A valid email should include an at sign ('@') and period ('.') and should not include a space (' ').
      //https://www.educative.io/edpresso/what-is-stringnpos-in-cpp

void Roster::printInvalidEmails() {
	for (int i = 0; i < arrSize; i++) {
		//Get email
		string scrutinizeEmail = classRosterArray[i]->GetemailAddress();

		//Check for @
		size_t badAt = scrutinizeEmail.find("@");
		if (badAt == string::npos) {
			cout << scrutinizeEmail << " is an invalid email. Please include @ in your email" << endl;
		}
		//Check for .
		size_t badPeroid = scrutinizeEmail.find(".");
		if (badPeroid == string::npos) {
			cout << scrutinizeEmail << " is an invalid email. Please include . in your email" << endl;
		}
		//Check for space
		size_t badSpace = scrutinizeEmail.find(" ");
		if (badSpace != string::npos) {
			cout << scrutinizeEmail << " is an invalid email. Please do not uses spaces in your email" << endl;
		}
	}
}


 //f.  public void printByDegreeProgram(DegreeProgram degreeProgram) that prints out student information for a degree program specified by an enumerated type.
void Roster::printByDegreeProgram(degreeProgram major) {
	//making words
	string words;
	if (major == 0) {
		words = "SECURITY";
	}
	if (major == 1) {
		words = "NETWORK";
	}
	if (major == 2) {
		words = "SOFTWARE";
	}
	cout << "The following students are in the " << words << " degree program are:"<<endl;

	//making list
	for (int i = 0; i < arrSize; i++) {
		degreeProgram studentsInMajor = classRosterArray[i]->GetdegreeProgram();
	
		if (major == studentsInMajor)
		{	
			classRosterArray[i]->PrintAllStudentInfo();
		}
	}
}








