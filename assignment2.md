# Automated Code Generation and Unit Testing Assignment 2

#### Case Study Learning Management System

Student 1 name and FAN: Tran Anh Truc Vuong (Hazel Vuong) - vuon0044

Student 2 name and FAN: Pronsuda Ruangsuwan - ruan0031

## 1. List of prompts used to generate the code

1. Initial prompt:
   > Gather all needed requirements for the system so chatGPT can generate the skeleton structure and classes.

"Overall requirements for the Learning management system:
Every student and staff should be able to login LMS.

Students:

- Can access their course materials
- An academic calendar with all the assignment and exam schedules
- Can upload assignments
- Can take part in online exams
- Can check grades through LMS

Academic staff:

- Can upload contents
- Can communicate with students through open student discussion forum

Both:

- Login
- Logout
- Request authentication after any inactivity of ten minutes after opening the LMS page

Write Java code to satisfy above requirements. Details will be given in further prompt. Give detail explanation."

2. Second requirement prompt:
   > From second prompt, we will focus on the functionalities that need to implement in the first generated code. Starting from **Authentication** function for both users.

"Write Java code for a Learning Management System. The system will have 2 main user role which are Student and Academic Staff. Every student and academic staff should be authenticated while entering the LMS. The student and Staff will both have login prompt and depend on their role, they will have different functionality.
We will do unit test case later so make every function public for the sake of testing process."

3. Third requirement prompt:
   > The next function we implemented was **view/access course materials** function for students.

Write Java code for student. After they successfully login using their credential, they will be able to access all their course materials through the learning management system. 4. Fourth requirement prompt:

> The function we implemented was **view academic calendar** function for students.

Write Java code based on previous prompt. An academic calendar will highlight all the assignment and exam schedules 5. Fifth requirement prompt:

> The function we implemented was **upload assignment** function for students.

Write Java code based on previous prompt. Students have the ability to upload assignments on the LMS

6. Sixth requirement prompt:
   > The function we implemented was **take online exam** function for students.

Write Java code based on previous prompt. Students will be able to take part in online exams through LMS 7. Seventh requirement prompt:

> The function we implemented was **check grade** function for students.

Write Java code based on previous prompt. Students will be able to check their grades through LMS

8. Eight requirement prompt:
   > The function we implemented was **upload content** function for academic staff.

Write Java code based on previous prompt. Academics should be able to upload contents. 9. Ninth requirement prompt:

> The function we implemented was **communicate wtih students** function for academic staff.

Write Java code based on previous prompt. Academics can also be able to communicate with students through open student discussion forums

10. Tenth requirement prompt:
    > The function we implemented was **reauthentication** function both users.

Write Java code based on previous prompt. The LMS will request authentication after any inactivity of ten minutes after opening the LMS page

11. Final requirement prompt:
    > The function we implemented was **log out** function both users.

Write Java code based on previous prompt. Users will be able to logout at any time from the LMS page.

## Documentation of unit test cases

Test case notation:

- A: Related test cases in _Assignment_ class
- C: Related test cases in _Course_ class
- L: Related test cases in _LMS_ class
- SF: Related test cases in _Staff_ class
- ST: Related test cases in _Student_ class
- U: Related test cases in _User_ class

| Test case identifier | Method                        | Description                                                | Expected                                                                                                   | Actual                                                                    | Pass/Fail status |
|----------------------|-------------------------------|------------------------------------------------------------|------------------------------------------------------------------------------------------------------------|---------------------------------------------------------------------------|------------------|
| C05                  | addAssignment()               | Method to add multiple assignments to the courses.         | Add assignment to the specific course in the system.                                                       | Assignment is added to the specific course with due date.                 | Pass             |
| C06                  | addExamSchedule()             | Method that allow user to add a scheduled exam on LMS      | ExamSchedules list to not be null and initially empty.                                                     | examSchedules list is not null and initially empty                        | Pass             | 
|                      |                               |                                                            | After adding two exam dates (examDate1 and examDate2), the examSchedules list should contain two elements. | the examSchedules list contains two elements                              | Pass             |
|                      |                               |                                                            | The first element should be examDate1, and the second element should be examDate2.                         | The first element is examDate1, the second element is examDate2           | Pass             |
| L01                  | authenticateSuccess()         | Method to check valid user authentication.                 | Valid user can login with their username and password.                                                     | User can login with their username and password.                          | Pass             |
| L03                  | studentAccessCourseMaterial() | Method that allow student access the course material.      | Student can access the course material that proof by their last activity time.                             | Last activity time indicates that student can access the course material. | Pass             |
| L05                  | displayAcademicCalendar()     |                                                            |                                                                                                            |                                                                           |                  |
| L06                  | studentCheckGrades()          | Method that allow student to check their grades.           | Student can view their grades on their assignments and enrolled courses.                                   | Student can view their grades.                                            | Pass             |
| L09                  | checkUserInactivity()         | 1.Method to check user inactivity more than 10 minutes.    | 1.1. User will be asked reconnected to the system.                                                         | 1.1. User is asked to reconnected to the system.                          | Pass             |
|                      |                               | 2.Check user active in the timeframe less than 10 minutes. | 2.1. User still connecting to the system.                                                                  | 2.1. User is not asked to reconnected to the system.                      | Pass             |
| L11                  | logOut()                      | Method that allow user to log out of the system            | null                                                                                                       | User@1cab0bfb                                                             | Fail             |
| SF02                 | communicateWithStudents()     |                                                            |                                                                                                            |                                                                           |                  |
| ST03                 | uploadAssignment()            |                                                            |                                                                                                            |                                                                           |                  |
| ST04                 | takeOnlineExam()              | 1. Method that allow student take an online exam.          | 1.1. Student can take online exam in their enrolled courses.                                               | 1.1. Student allow to take online exam in enrolled courses.               | Pass             |
|                      |                               | 2. Student can take online exam in the exam date.          | 2.1. Thrown argument if student attempt to take online exam before exam date.                              | 2.1. Nothing was thrown.                                                  | Fail             |
|                      |                               |                                                            | 2.2. Thrown argument if student attempt to take online exam after exam date.                               | 2.2. Nothing was thrown.                                                  | Fail             |
|                      |                               |                                                            |                                                                                                            |                                                                           |                  |




## Coverage of Test cases


## Recommendations