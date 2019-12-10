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
('M001', 'Math101', 5, 1, '2019-09-09', '2019-12-01'), ('P001', 'Physics101', 5, 2, '2019-12-10', '2019-12-30'), ('C001', 'CS101', 5, 3, '2019-12-10', '2019-12-30'),
('B001', 'Biz101', 5, 4, '2019-12-10', '2019-12-30'), ('E001', 'Econs101', 5, 5, '2019-12-10', '2019-12-30'), ('Ch001', 'Chem101', 5, 6, '2019-12-10', '2019-12-30'),
('L001', 'Law101', 5, 7, '2019-12-10', '2019-12-30'), ('A001', 'Acct101', 5, 8, '2020-1-1', '2020-2-13'), ('S001', 'Stats101', 5, 9, '2020-1-1', '2020-2-13');