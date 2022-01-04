#pragma once
#include <iostream>
#include <string>
#include "student.h";
using namespace std;

//Setters
Student::Student() {}

Student::Student(string studentIDAcc, string firstNameAcc, string lastNameAcc, string emailAddressAcc, int ageAcc, int daysInCourse1Acc, int daysInCourse2Acc, int daysInCourse3Acc, degreeProgram majorAcc) {
	this->studentId = studentIDAcc;
	this->firstName = firstNameAcc;
	this->lastName = lastNameAcc;
	this->emailAddress = emailAddressAcc;
	this->age = ageAcc;

	this->numDaysInEachCourse[0] = daysInCourse1Acc;
	this->numDaysInEachCourse[1] = daysInCourse2Acc;
	this->numDaysInEachCourse[2] = daysInCourse3Acc;
	this->major = majorAcc;
}
void Student::SetStudentID(string studentIDAcc) {
	this->studentId = studentIDAcc;
}
void Student::SetFirstName(string firstNameAcc) {
	this->firstName = firstNameAcc;
}
void Student::SetLastName(string lastNameAcc) {
	this->lastName = lastNameAcc;
}
void Student::SetemailAddress(string emailAddressAcc) {
	this->emailAddress = emailAddressAcc;
}
void Student::SetAge(int ageAcc) {
	this->age = ageAcc;
}
void Student::SetdaysInCourseAll(int daysInCourseAcc1, int daysInCourseAcc2, int daysInCourseAcc3) {
	this->numDaysInEachCourse[0] = daysInCourseAcc1;
	this->numDaysInEachCourse[1] = daysInCourseAcc2;
	this->numDaysInEachCourse[2] = daysInCourseAcc3;
}
void Student::SetdaysInCourse1(int daysInCourseAcc) {
	this->numDaysInEachCourse[0] = daysInCourseAcc;
}
void Student::SetdaysInCourse2(int daysInCourseAcc) {
	this->numDaysInEachCourse[1] = daysInCourseAcc;
}
void Student::SetdaysInCourse3(int daysInCourseAcc) {
	this->numDaysInEachCourse[2] = daysInCourseAcc;
}

void Student::SetdegreeProgram(degreeProgram majorAcc) {
	this->major = majorAcc;
}

//Getters
void Student::PrintAllStudentInfo() {
	//Deleted student
	if (studentId == "", firstName == "", lastName == "", emailAddress == "")	{
cout << "Student was not found."<<endl;
	}
	else {
		//Print information:  A1[tab] First Name : John[tab] Last Name : Smith[tab] Age : 20[tab]daysInCourse : {35, 40, 55} Degree Program : Security.The printAll() function should loop through all the students in classRosterArrayand call the print() function for each student.
		cout << studentId << " ";
		cout << "firstName : " << firstName << "     ";
		cout << "lastName : " << lastName << "     ";
		cout << "emailAddress : " << emailAddress << "     ";
		cout << "age : " << age << "     ";
		
		//print days in course
		cout << "daysInCourse : {";
			for (int i = 0, x = 0; i < 3; i++){
				cout << numDaysInEachCourse[i];
				if (x <2){
					cout << ", ";
					x++;
				}
			}
			cout << "}";
			
	
		//Convert to text
		if (major == SECURITY) {
			cout << " Degree Program : SECURITY" << endl;
		}
		else if (major == NETWORK) {
			cout << " Degree Program : NETWORK" << endl;
		}
		else if (major == SOFTWARE) {
			cout << " Degree Program : SOFTWARE" << endl;
		}
		else
		{
			cout << "No major delcared" << endl;
		}
	}
	
}
string Student::GetStudentID() const {
	return studentId;
}
string Student::GetFirstName() const {
	return firstName;
}
string Student::GetLastName()const {
	return lastName;
}
string Student::GetemailAddress() const {
	return emailAddress;
}
int Student::GetAge()const {
	return age;
}
int Student::GetdaysInCourse1() {
	return numDaysInEachCourse[1];
}
int Student::GetdaysInCourse2() {
	return numDaysInEachCourse[2];
}
int Student::GetdaysInCourse3() {
	return numDaysInEachCourse[3];
}
void Student::PrintDaysInCourses() {
	cout << "daysInCourse : {";
	for (int i = 0, x = 0; i < 3; i++) {
		cout << numDaysInEachCourse[i];
		if (x < 2) {
			cout << ", ";
			x++;
		}
	}
	cout << "}";
}
degreeProgram Student::GetdegreeProgram() const {
	return major;
}


//test functions
//	Student test;
//	test.SetStudentID("a1");
//	test.SetFirstName("ben");
//	test.SetLastName("brady");
//	test.SetemailAddress("fake@donttryit.com");
//	test.SetAge(31);
//	test.SetdaysInCourseAll(7, 14, 23);
//	test.SetdegreeProgram(SOFTWARE);
//	test.PrintAll();
//
//	Student Kali ("1 of 3", "kali", "cat", "queenk@emailforcats.com", 6, 7, 6, 5,SECURITY);
//	Kali.PrintAll();
//
//	Student Rick("2 of 3", "Ricky", "Park", "behindgreeneyes@emailforcats.com", 5, 7, 6, 5, NETWORK);
//	Rick.PrintAll();
//
//	Student Theon("3 of 3", "Theon", "Graycat", "Unseen@emailforcats.com", 2, 7, 6, 5, SOFTWARE);
//	Rick.PrintAll();

