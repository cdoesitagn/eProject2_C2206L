CREATE TABLE `Student` (
  `student_id` int PRIMARY KEY auto_increment,
  `fullname` varchar(30),
  `birthday` date,
  `gender` varchar(10),
  `address` varchar(30),
  `phoneNumber` varchar(20),
  `email` varchar(30),
  `image_path` varchar(200)
);

CREATE TABLE `Course` (
  `course_id` int PRIMARY KEY auto_increment,
  student_id int,
  semester_id int,
  `course1` varchar(30),
  `course2` varchar(30),
  `course3` varchar(30),
  `course4` varchar(30),
  `course5` varchar(30),
  `teacher_id` int
);

CREATE TABLE `Teacher` (
  `teacher_id` integer PRIMARY KEY auto_increment,
  `fullname` varchar(30),
  `gender` VARCHAR(5),
  `email` varchar(30),
  `phoneNumber` varchar(20)
);

CREATE TABLE `result` (
  `result_id` integer PRIMARY KEY auto_increment,
  `student_id` int,
  `semester_id` int,
  `course_name` varchar(100),
  `lt_point1` float,
  `th_point1` float,
  `lt_point2` float,
  `th_point2` float,
  `total_point1` float,
   `total_point2` float
);

CREATE TABLE `UserAccount` (
  `userAccount_id` int PRIMARY KEY auto_increment,
  `user_id` int,
  `username` varchar(30),
  `password` varchar(30),
  `role` int
);

CREATE TABLE `TotalResult` (
  `totalResult_id` int PRIMARY KEY auto_increment,
  `semester_id` int,
  `student_id` int,
  `examResult_id` int,
  `gpa_point` int,
  `status_result` varchar(10)
);

CREATE TABLE `Class` (
  `class_id` int PRIMARY KEY auto_increment,
  `class_name` varchar(30)
);

CREATE TABLE `Student_Class` (
  `sc_id` int PRIMARY KEY auto_increment,
  `student_id` int,
  `class_id` int
);

CREATE TABLE `Teacher_Course` (
  `tc_id` int PRIMARY KEY auto_increment,
  `teacher_id` int,
  `course_id` int
);

CREATE TABLE `Schedule` (
  `schedule_id` int PRIMARY KEY auto_increment,
  `teacher_id` int,
  `class_id` int,
  `course_name` varchar(100),
  `start_time` date,
  `end_time` date,
  `date_of_week` varchar(100),
  `time_of_day` varchar(100) 
);

CREATE TABLE `Student_Schedule` (
  `std_sch_id` int PRIMARY KEY auto_increment,
  `schedule_id` int,
  `student_id` int
);

ALTER TABLE Result ADD FOREIGN KEY (student_id) REFERENCES Student(student_id);
ALTER TABLE TotalResult ADD FOREIGN KEY (student_id) REFERENCES Student(student_id);
ALTER TABLE TotalResult ADD FOREIGN KEY (examResult_id) REFERENCES `result`(result_id);
ALTER TABLE Student_Class ADD FOREIGN KEY (student_id) REFERENCES Student(student_id);
ALTER TABLE Student_Class ADD FOREIGN KEY (class_id) REFERENCES Class(class_id);
ALTER TABLE Teacher_Course ADD FOREIGN KEY (course_id) REFERENCES Course(course_id);
ALTER TABLE Student_Schedule ADD FOREIGN KEY (student_id) REFERENCES Student(student_id);
ALTER TABLE Student_Schedule ADD FOREIGN KEY (schedule_id) REFERENCES Schedule(schedule_id);
ALTER TABLE Schedule ADD FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id);
ALTER TABLE Schedule ADD FOREIGN KEY (class_id) REFERENCES Class(class_id);
ALTER TABLE Course ADD FOREIGN KEY (teacher_id) REFERENCES Teacher(teacher_id);
