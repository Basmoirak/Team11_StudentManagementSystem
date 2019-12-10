USE `sms`;

-- Default passwords here are: fun123

INSERT INTO `user` (id,username,password,first_name,last_name,email, is_active)
VALUES 
('1', 'admin','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','John','Doe','john@luv2code.com', TRUE),
('2', 'mary','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Mary','Public','mary@luv2code.com', TRUE),
('3', 'susan','$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K','Susan','Adams','susan@luv2code.com', TRUE);
 
INSERT INTO `role` (name)
VALUES 
('ROLE_STUDENT'),('ROLE_FACULTY'),('ROLE_ADMIN');

INSERT INTO `users_roles` (user_id,role_id)
VALUES 
(1, 3),
(2, 3),
(3, 3);

INSERT INTO `department` (name)
VALUES
('Mathematics'), ('Physics'), ('Computer Science'),
('Business'), ('Economics'), ('Chemistry'), ('Law'),
('Accounting'), ('Statistics');

INSERT INTO `course` (course_code, course_name, course_unit, department_id, start_date, end_date)
VALUES
('1', 'Math101', 5, 1, NOW()-INTERVAL 1 DAY , NOW()+INTERVAL 60 DAY), 
('2', 'Physics101', 5, 2, NOW()-INTERVAL 1 DAY, NOW()+INTERVAL 60 DAY), 
('3', 'CS101', 5, 3, NOW() - INTERVAL 1 DAY, NOW()+INTERVAL 60 DAY),
('4', 'Biz101', 5, 4, NOW()-INTERVAL 1 DAY, NOW()+INTERVAL 60 DAY),
('5', 'Econs101', 5, 5, NOW()-INTERVAL 1 DAY, NOW()+INTERVAL 60 DAY), 
('6', 'Chem101', 5, 6, NOW()+INTERVAL 60 DAY, NOW()+INTERVAL 120 DAY),
('7', 'Law101', 5, 7, NOW()+INTERVAL 60 DAY, NOW()+INTERVAL 120 DAY), 
('8', 'Acct101', 5, 8, NOW()+INTERVAL 60 DAY, NOW()+INTERVAL 120 DAY), 
('9', 'Stats101', 5, 9, NOW()+INTERVAL 60 DAY, NOW()+INTERVAL 120 DAY);