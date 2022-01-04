#pragma once

#include <iostream>
#include <string>
#include <sstream>
#include "roster.h"
#include "student.h"
#include "degreeProgram.h"
#include "student_datatable.h"
using namespace std;



void main() {
	// F1 Print out to the screen, via your application, the course title, the programming language used, your WGU student ID, and your name.
	cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << endl;
	cout << "|| C867: Scripting and Programming Applications ||" << endl;
	cout << "|| Language used: C++                           ||" << endl;
	cout << "|| ID: ????                                     ||" << endl;
	cout << "|| Benjamin Brady                               ||" << endl;
	cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" << endl;
	cout << endl;



	
//	//2.  Create an instance of the Roster class called classRoster.
//	//3.  Add each student to classRoster.
	Roster* classRoster = new Roster(5);

	//4.  Convert the following pseudo code to complete the rest of the  main() function :
	classRoster->printAll();
	classRoster->printInvalidEmails();

//	//loop through classRosterArray and for each element:
	 //classRoster.printAverageDaysInCourse(/*current_object's student id*/);
	classRoster->printAverageDaysInCourse("A1");
	classRoster->printAverageDaysInCourse("A2");
	classRoster->printAverageDaysInCourse("A3");
	classRoster->printAverageDaysInCourse("A4");
	classRoster->printAverageDaysInCourse("A5");
	
	//print by degree program
	classRoster->printByDegreeProgram(SECURITY);
	classRoster->printByDegreeProgram(SOFTWARE);
	classRoster->printByDegreeProgram(NETWORK);
 
	//remove from roster
	classRoster->remove("A3");
	classRoster->printAll();
//	//should print a message saying such a student with this ID was not found.
	classRoster->remove("A3");
// 
//	//5.  Implement the destructor to release the memory that was allocated dynamically in Roster.
	delete classRoster;


}

