# Team11_sms

## Project: To create a student management system

## Project setup Instructions
- Update Maven Project after import
- Open application.properties and change update username & password
- Create a new schema in MySQL called 'sms'
- Run Springboot application
- Open sql_scripts added in the project, and run the script (sql_init_db.sql) in MySQL
- Run Springboot application again to ensure everything is loaded correctly

## Known Errors during setup
- JUnit5 might not be installed properly via Maven. In this case, you can directly add JUnit5 Library to the build path.

## To Login 
**As Administrator Role**
- Username: admin | Password: fun123
**As Faculty Role**
- Username: ted | Password: fun123
- Or any cast member from "How I Met Your Mother" tv show
**As Student Role**
- Username: chandler | Password: fun123
- Or any other cast member from "FRIENDS" tv show

## Features

**Login & Registration**
- Users can login and register, and all users will have their passwords hashed in the database.
- Appropriate error messages will be displayed

**Administrator Role**
- Users can approve pending leaves & course applications
- Users can perform CRUD functions on Students/Faculties/Courses/Departments
- Search and pagination functionality

**Faculty Role**
- Users can apply for new leaves
- Users can view their active/all courses taught
- Users can assign grade to students
- Email functionality
- Extract to CSV functionality

**Student Role**
- Users can apply for courses
- Users view course status
- Users can view their culmulative GPA

