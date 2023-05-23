CREATE TABLE `Student` (
  `student_id` int PRIMARY KEY,
  `fullname` varchar(30),
  `birthday` date,
  `gender` enum,
  `address` varchar(30),
  `phoneNumber` varchar(20),
  `email` varchar(30)
);

CREATE TABLE `Course` (
  `course_id` int PRIMARY KEY,
  `course_name` varchar(30),
  `teacher_id` int
);

CREATE TABLE `Teacher` (
  `teacher_id` integer PRIMARY KEY,
  `fullname` varchar(30),
  `gender` enum,
  `email` varchar(30),
  `phoneNumber` varchar(20)
);

CREATE TABLE `ExamResults` (
  `examResult_id` integer PRIMARY KEY,
  `student_id` int,
  `course_id` int,
  `lt_point1` float,
  `th_point1` float,
  `lt_point2` float,
  `th_point2` float,
  `total_point` int
);

CREATE TABLE `UserAccount` (
  `user_id` int PRIMARY KEY,
  `username` varchar(30),
  `password` varchar(30),
  `role` enum
);

CREATE TABLE `Semester` (
  `semester_id` int PRIMARY KEY,
  `semester_name` varchar(20)
);

CREATE TABLE `TotalResult` (
  `totalResult_id` int PRIMARY KEY,
  `semester_id` int,
  `student_id` int,
  `examResult_id` int,
  `gpa_point` int,
  `status_result` varchar(10)
);

CREATE TABLE `Class` (
  `class_id` int PRIMARY KEY,
  `class_name` varchar(30)
);

CREATE TABLE `Student_Class` (
  `sc_id` int PRIMARY KEY,
  `student_id` int,
  `class_id` int
);

CREATE TABLE `Teacher_Course` (
  `tc_id` int PRIMARY KEY,
  `teacher_id` int,
  `course_id` int
);

CREATE TABLE `Schedule` (
  `schedule_id` int PRIMARY KEY,
  `teacher_id` int,
  `class_id` int,
  `course_id` int,
  `start_time` date,
  `end_time` date,
  `date_of_week` date
);

CREATE TABLE `Attendance` (
  `attend_id` int PRIMARY KEY,
  `schedule_id` int,
  `student_id` int,
  `status` varchar(5),
  `date` date
);

ALTER TABLE `Student` ADD FOREIGN KEY (`student_id`) REFERENCES `ExamResults` (`student_id`);

ALTER TABLE `ExamResults` ADD FOREIGN KEY (`course_id`) REFERENCES `Course` (`course_id`);

ALTER TABLE `Student` ADD FOREIGN KEY (`student_id`) REFERENCES `TotalResult` (`student_id`);

ALTER TABLE `TotalResult` ADD FOREIGN KEY (`semester_id`) REFERENCES `Semester` (`semester_id`);

ALTER TABLE `ExamResults` ADD FOREIGN KEY (`examResult_id`) REFERENCES `TotalResult` (`examResult_id`);

ALTER TABLE `Student` ADD FOREIGN KEY (`student_id`) REFERENCES `Student_Class` (`student_id`);

ALTER TABLE `Class` ADD FOREIGN KEY (`class_id`) REFERENCES `Student_Class` (`class_id`);

ALTER TABLE `Teacher_Course` ADD FOREIGN KEY (`course_id`) REFERENCES `Course` (`course_id`);

ALTER TABLE `Student` ADD FOREIGN KEY (`student_id`) REFERENCES `Attendance` (`student_id`);

ALTER TABLE `Teacher` ADD FOREIGN KEY (`teacher_id`) REFERENCES `Schedule` (`teacher_id`);

ALTER TABLE `Class` ADD FOREIGN KEY (`class_id`) REFERENCES `Schedule` (`class_id`);

ALTER TABLE `Teacher_Course` ADD FOREIGN KEY (`course_id`) REFERENCES `Schedule` (`course_id`);

ALTER TABLE `Teacher_Course` ADD FOREIGN KEY (`teacher_id`) REFERENCES `Schedule` (`teacher_id`);

ALTER TABLE `Course` ADD FOREIGN KEY (`teacher_id`) REFERENCES `Teacher` (`teacher_id`);

ALTER TABLE `Schedule` ADD FOREIGN KEY (`schedule_id`) REFERENCES `Attendance` (`schedule_id`);
