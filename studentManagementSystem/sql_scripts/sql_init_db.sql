USE `sms`;

-- Default passwords here are: fun123
-- Create users 
INSERT INTO `user` (id,username,password,first_name,last_name,email, is_active)
VALUES 
('1', 'admin','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','John','Doe','john@luv2code.com', TRUE),
('2', 'mary','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Mary','Public','mary@luv2code.com', TRUE),
('3', 'susan','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Susan','Adams','susan@luv2code.com', TRUE),
('4', 'ted','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Ted','Mosby','ted@luv2code.com', TRUE),
('5', 'barney','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Barney','Stinson','barney@luv2code.com', TRUE),
('6', 'robin','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Robin','Sparkles','robin@luv2code.com', TRUE),
('7', 'marshall','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Marshall','Erikson','marshall@luv2code.com', TRUE),
('8', 'lily','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Lily','Aldrin','lily@luv2code.com', TRUE),
('9', 'chandler','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Chandler','Bing','susan@luv2code.com', TRUE),
('10', 'ross','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','ross','geller','ross@luv2code.com', TRUE),
('11', 'monica','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','monica','geller','monica@luv2code.com', TRUE),
('12', 'joey','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','joey','tribbiani','joey@luv2code.com', TRUE),
('13', 'rachel','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','rachel','green','rachel@luv2code.com', TRUE),
('14', 'phoebe','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','phobe','buffay','rachel@luv2code.com', TRUE);

-- Create user roles 
INSERT INTO `role` (name)
VALUES 
('ROLE_STUDENT'),('ROLE_FACULTY'),('ROLE_ADMIN');

-- Match user to roles 
INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 3),(2, 3),(3, 3),
(4, 2), (5, 2), (6, 2), (7, 2), (8, 2),
(9, 1), (10, 1), (11, 1), (12, 1), (13, 1), (14, 1);

-- Create students 
INSERT INTO `student` (id,address,birth_date,degree,first_name,last_name,gender,mobile,level_id,semester_id,status_id)
VALUES
('9', 'Central Perk', '1995-02-08 23:35:21', 'Business', 'Chandler', 'Bing', 'Male', '91234567', 1, 1, 1),
('10', 'Central Perk', '1996-02-08 23:35:21', 'Business', 'Ross', 'Geller', 'Male', '91234567', 1, 1, 1),
('11', 'Central Perk', '1996-02-08 23:35:21', 'Business', 'Monica', 'Geller', 'Female', '91234567', 1, 1, 1),
('12', 'Central Perk', '1995-02-08 23:35:21', 'Business', 'Joey', 'Tribbiani', 'Male', '91234567', 1, 1, 1),
('13', 'Central Perk', '1996-02-08 23:35:21', 'Business', 'Rachel', 'Green', 'Female', '91234567', 1, 1, 1),
('14', 'Central Perk', '1996-02-08 23:35:21', 'Business', 'Phoebe', 'Buffay', 'Female', '91234567', 1, 1, 1);

-- Create departments 
INSERT INTO `department` (name)
VALUES
('Mathematics'), ('Physics'), ('Computer Science'),
('Business'), ('Economics');

-- Create faculty 
INSERT INTO `faculty` (faculty, department_id, first_name, last_name)
VALUES
('4', 1, 'Ted', 'Mosby'),
('5', 2, 'Barney', 'Stinson'),
('6', 3, 'Robin', 'Sparkles'),
('7', 4, 'Marshall', 'Erikson'),
('8', 5, 'Lily', 'Aldrin');

-- Create courses
INSERT INTO `course` (course_code, course_name, course_unit, department_id, start_date, end_date, faculty_id)
VALUES
-- Current courses 
('1', 'Math101', 5, 1, NOW()-INTERVAL 1 DAY , NOW()+INTERVAL 60 DAY, '4'), 
('2', 'Physics101', 5, 2, NOW()-INTERVAL 1 DAY, NOW()+INTERVAL 60 DAY, '5'), 
('3', 'CS101', 5, 3, NOW() - INTERVAL 1 DAY, NOW()+INTERVAL 60 DAY, '6'),
('4', 'Biz101', 5, 4, NOW()-INTERVAL 1 DAY, NOW()+INTERVAL 60 DAY, '7'),
('5', 'Econs101', 5, 5, NOW()-INTERVAL 1 DAY, NOW()+INTERVAL 60 DAY, '8'),
-- Future courses  
('6', 'Math102', 5, 1, NOW()+INTERVAL 60 DAY, NOW()+INTERVAL 120 DAY, '4'),
('7', 'Physics102', 5, 2, NOW()+INTERVAL 60 DAY, NOW()+INTERVAL 120 DAY, '5'), 
('8', 'CS102', 5, 3, NOW()+INTERVAL 60 DAY, NOW()+INTERVAL 120 DAY, '6'), 
('9', 'Biz102', 5, 4, NOW()+INTERVAL 60 DAY, NOW()+INTERVAL 120 DAY, '7'),
('10', 'Econs102', 5, 5, NOW()+INTERVAL 60 DAY, NOW()+INTERVAL 120 DAY, '8'),
-- Past courses
('11', 'Ethics101', 5, 4, NOW()-INTERVAL 120 DAY, NOW()-INTERVAL 60 DAY, '7'),
('12', 'Intern101', 5, 4, NOW()-INTERVAL 120 DAY, NOW()-INTERVAL 60 DAY, '7');

-- Create courseApplicants
 INSERT INTO `course_applicant` (course_id, status, student_id, submitted_date)
 VALUES
-- Past courses applied 
(11, 1, '9', NOW()-INTERVAL 125 DAY), (11, 1, '10', NOW()-INTERVAL 125 DAY), (11, 1, '11', NOW()-INTERVAL 125 DAY), 
(11, 1, '12', NOW()-INTERVAL 125 DAY), (11, 1, '13', NOW()-INTERVAL 125 DAY), (11, 1, '14', NOW()-INTERVAL 125 DAY),
(12, 1, '9', NOW()-INTERVAL 125 DAY), (12, 1, '10', NOW()-INTERVAL 125 DAY), (12, 1, '11', NOW()-INTERVAL 125 DAY), 
(12, 1, '12', NOW()-INTERVAL 125 DAY), (12, 1, '13', NOW()-INTERVAL 125 DAY), (12, 1, '14', NOW()-INTERVAL 125 DAY),
-- Current courses applied
(1, 1, '9', NOW()), (1, 1, '10', NOW()), (1, 1, '11', NOW()), (1, 1, '12', NOW()), (1, 1, '13', NOW()), (1, 1, '14', NOW()),
(2, 1, '9', NOW()), (2, 1, '10', NOW()), (2, 1, '11', NOW()),
(3, 1, '12', NOW()), (3, 1, '13', NOW()), (3, 1, '14', NOW()),
(4, 1, '9', NOW()), (4, 1, '10', NOW()), (4, 1, '11', NOW()), (4, 1, '12', NOW()),
(5, 1, '12', NOW()), (5, 1, '13', NOW()), (5, 1, '14', NOW()),
-- Future courses applied
(6, 0, '9', NOW()), (6, 0, '10', NOW()), (6, 0, '11', NOW()), (6, 0, '12', NOW()), (6, 0, '13', NOW()), (6, 0, '14', NOW()),
(7, 0, '9', NOW()), (7, 0, '10', NOW()), (7, 0, '11', NOW()),
(9, 1, '9', NOW()), (9, 1, '10', NOW()), (9, 1, '11', NOW()), (9, 1, '12', NOW()), (9, 1, '13', NOW()), (9, 1, '14', NOW());

-- Add student grades
 INSERT INTO `student_grades` (course_id, grade, level, semester, student_id)
 VALUES
 -- Past courses
 (11, 'A', 1, 1, '9'), (11, 'A-', 1, 1, '10'), (11, 'B', 1, 1, '11'),
 (11, 'A', 1, 1, '12'), (11, 'B+', 1, 1, '13'), (11, 'A-', 1, 1, '14'),
 (12, 'B+', 1, 1, '9'), (12, 'B+', 1, 1, '10'), (12, 'A-', 1, 1, '11'),
 (12, 'A-', 1, 1, '12'), (12, 'B+', 1, 1, '13'), (12, 'B+', 1, 1, '14'),
 -- Current courses
 (1, 'N/A' , 1, 1, '9'), (1, 'N/A' , 1, 1, '10'), (1, 'N/A' , 1, 1, '11'), 
 (1, 'N/A' , 1, 1, '12'), (1, 'N/A' , 1, 1, '13'), (1, 'N/A' , 1, 1, '14'),
 (2, 'N/A' , 1, 1, '9'), (2, 'N/A' , 1, 1, '10'), (2, 'N/A' , 1, 1, '11'), 
 (3, 'N/A' , 1, 1, '12'), (3, 'N/A' , 1, 1, '13'), (3, 'N/A' , 1, 1, '14'),
 (4, 'N/A' , 1, 1, '9'), (4, 'N/A' , 1, 1, '10'), (4, 'N/A' , 1, 1, '11'), (4, 'N/A', 1, 1, '12'),
 (5, 'N/A' , 1, 1, '12'), (5, 'N/A' , 1, 1, '13'), (5, 'N/A' , 1, 1, '14'),
 (9, 'N/A' , 1, 1, '9'), (9, 'N/A' , 1, 1, '10'), (9, 'N/A' , 1, 1, '11'), 
 (9, 'N/A' , 1, 1, '12'), (9, 'N/A' , 1, 1, '13'), (9, 'N/A' , 1, 1, '14');
