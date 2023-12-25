
CREATE TABLE `User` (
  `user_id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_name` varchar(255),
  `email` varchar(255),
  `password_hash` varchar(255),
  `enabled` int,
  INDEX `idx_user_name` (`user_name`)
);

CREATE TABLE `Link` (
  `link_id` INTEGER PRIMARY KEY AUTO_INCREMENT,
  `section_id` INTEGER,
  `url` TEXT,
  `link_name` VARCHAR(255),
  INDEX `idx_url` (`url`(500)), -- Specify key length for the index
  INDEX `idx_link_name` (`link_name`)
);

CREATE TABLE `UserQuickLink` (
  `user_quicklink_id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `user_name` varchar(255),
  `link_id` int,
  `link_name` varchar(255),
  INDEX `idx_user_name` (`user_name`),
  INDEX `idx_link_name` (`link_name`)
);


CREATE TABLE `authorities` (
  `authority_id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT,
  `authority` varchar(255),
  `username` varchar(255)
);

CREATE TABLE Section (
    section_id INT PRIMARY KEY,
    section_name VARCHAR(255) NOT NULL
);

CREATE TABLE `LinkClicks` (
    link_click_id INT PRIMARY KEY AUTO_INCREMENT,
    link_id INT,
    num_of_clicks INT,
    FOREIGN KEY (link_id) REFERENCES Link(link_id),
    UNIQUE (link_id)
);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`user_name`) REFERENCES `User` (`user_name`);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`link_id`) REFERENCES `Link` (`link_id`);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`link_name`) REFERENCES `Link` (`link_name`);

ALTER TABLE `authorities` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`);

-- Insert data into the User table
INSERT INTO User (user_name, email, password_hash, enabled) VALUES
('user1', 'user1@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1),
('user2', 'user2@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1),
('user3', 'user3@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1),
('user4', 'user4@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1),
('user5', 'user5@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1);


INSERT INTO authorities (user_id, authority, username) VALUES
(1, 'ADMIN', 'user1'),
(2, 'ADMIN', 'user2'),
(3, 'ADMIN', 'user3'),
(4, 'ADMIN', 'user4'),
(5, 'ADMIN', 'user5');


-- Inserting data into the section table
INSERT INTO Section (section_id, section_name) VALUES
('1', 'Orientation and On-boarding'),
('2', 'Enrollment and Registration'),
('3', 'Study'),
('4', 'Global Exchange Study'),
('5', 'Research Opportunities'),
('6', 'Student Support and Resources'),
('7', 'Student Life'),
('8', 'Career Management'),
('9', 'Start-up and Entrepreneurship'),
('10', 'Keep-in-touch');


INSERT INTO Link (link_id, section_id, link_name, url) VALUES
(1, '1', 'SIS Tutorials', ''),
(2, '1', 'Canvas Tutorial', 'https://statics.teams.cdn.office.net/evergreen-assets/safelinks/1/atp-safelinks.html'),
(3, '1', 'Important Contacts', 'https://vinuniversity.sharepoint.com/sites/HomeSite/SitePages/Contact-Information.aspx'),
(4, '1', 'VinUni Maps', 'https://vinuni.edu.vn/visit/'),
(5, '1', 'Tuition Fee and Financial Support', 'https://vinuni.edu.vn/undergraduate/tuition-fee-and-financial-support/'),

-- Section 2
(6, '2', 'Academic Calendar', 'https://vinuni.edu.vn/vinuni-academic-calendar/'),
(7, '2', 'Curriculum Information', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Registrar-and-Academic.aspx#curriculum-information'),
(8, '2', 'Registration and Scheduling', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Scheduling-Resources.aspx'),
(9, '2', 'Academic Requirements', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Registrar-and-Academic.aspx#academic-requirements'),

-- Section 3
(10, '3', 'Classes, Grades, and Evaluation', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Classes,-Grades-and-Evaluation.aspx'),
(11, '3', 'Declaring a Major / Minor / Concentration', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Registrar-and-Academic.aspx#declaring-a-major-minor-concentration'),
(12, '3', 'Declaring An Individually Designed Concentration', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Registrar-and-Academic.aspx#declaring-an-individually-designed-concentration-or-minor-(idcm)'),
(13, '3', 'Guidelines for Program Change Request', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Registrar-and-Academic.aspx#guidelines-for-program-change-request'),
(14, '3', 'Academic Integrity', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Academic-Integrity.aspx'),

-- Section 4
(15, '4', 'Global Exchange Information', 'https://vinuni.edu.vn/global-exchange/'),
(16, '4', 'Outbound Student Exchange Procedure', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Registrar-and-Academic.aspx#outbound-student-exchange-program'),
(17, '4', 'Course Exchange Options (Transfer Credit, Proficiency Exam, Independent Study)', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Registrar-and-Academic.aspx#outbound-student-exchange-program'),

-- Section 5
(18, '5', 'AY2022-2023 Seed Grant', 'https://vinuni.edu.vn/vinuni-research/seed-funding-program/'),
(19, '5', 'VinUni-Illinois Research Funding Program', 'https://smarthealth.vinuni.edu.vn/'),
(20, '6', 'Regulation & Policies', 'https://policy.vinuni.edu.vn/academic-affairs/'),
(21, '6', 'Personal Information', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Transcripts-and-Records.aspx#personal-information'),
-- Continuing with Section 6
(22, '6', 'Privacy Access and Records', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Transcripts-and-Records.aspx#records-privacy-and-access'),
(23, '6', 'IT Service', 'https://vinuniversity.sharepoint.com/sites/IT/SitePages/IT-Services-for-Students.aspx'),
(24, '6', 'Library', 'https://library.vinuni.edu.vn/'),
(25, '6', 'Registry Service Forms', 'https://vinuniversity.sharepoint.com/sites/OfficeofRegistrar/SitePages/Registry-Service-Forms.aspx'),
(26, '6', 'Room Booking', 'https://library.vinuni.edu.vn/room-booking/'),
(27, '6', 'Student Counseling Service', 'https://outlook.office365.com/book/HealthandWellbeing@vinuni.edu.vn/?'),

-- Section 7
(28, '7', 'Our Campus', 'https://vinuni.edu.vn/our-campus/'),
(29, '7', 'Life at VinUni', 'https://vinuni.edu.vn/life-at-vinuni/'),
(30, '7', 'First Year Experience', 'https://vinuni.edu.vn/first-year-experience/'),
(31, '7', 'Student Clubs & Associations', 'https://vinuni.edu.vn/student-clubs-and-associations/'),
(32, '7', 'Health and Wellbeing', 'https://vinuniversity.sharepoint.com/sites/VinUniStudentsSite2/SitePages/Health-and-Wellbeing.aspx'),
(33, '7', 'Lost & Found', 'https://vinuniversity.sharepoint.com/sites/VinUniStudentsSite2/SitePages/LOST-%26-FOUND.aspx'),
(34, '7', 'Guest Visit Request', 'https://forms.office.com/Pages/ResponsePage.aspx?link_id=iSf4WJVmSk-r2zV2aNVc_4LJoD-CFhJHiHKzJB8C2mxUOTc0QTc0TUc1VjA1S1VMUzUxUVkwSU9SVC4u'),
(35, '7', 'Re-issue Student link_id Card', 'https://vinuniversity.sharepoint.com/sites/VinUniStudentsSite2/Shared%20Documents/Forms/AllItems.aspx?link_id=%2Fsites%2FVinUniStudentsSite2%2FShared%20Documents%2FAdministrative%20Support%2FProcedures%20after%20losing%20student%20card%2Epdf&parent=%2Fsites%2FVinUniStudentsSite2%2FShared%20Documents%2FAdministrative%20Support'),
(36, '7', 'Meet VinUnians', 'https://vinuni.edu.vn/meet-vinunians/'),

-- Section 8
(37, '8', 'Career Services', 'https://vinuni.edu.vn/career-services/'),
(38, '8', 'Career Connect', 'https://jobs.vinuni.edu.vn/'),
(39, '8', 'Career Readiness Topics', 'https://vinuniversity.sharepoint.com/sites/VinUniStudentsSite2/SitePages/Career-Readiness-2021-2022.aspx'),
(40, '8', 'VinUniversity\'s Strategic Collaborators', 'https://vinuni.edu.vn/about-vinuniversity/strategic-collaborators-and-partners/'),
(41, '8', 'Job Opportunities', 'https://vinuni.edu.vn/job-opportunities/'),

-- Section 9
(42, '9', 'VinUni Entrepreneurship Lab', 'https://smarthealth.vinuni.edu.vn/'),
(43, '9', 'VinUniversity\'s Partners', 'https://eship.vinuni.edu.vn/start-up-internship/'),

-- Section 10
(44, '10', 'General Enquiries', 'https://vinuni.edu.vn/contact/'),
(45, '10', 'Alumni Network', '');

-- Insert data into the LinkClicks table
INSERT INTO LinkClicks (link_click_id, link_id, num_of_clicks) VALUES
(1, 1, 20),
(2, 2, 15),
(3, 3, 10),
(4, 4, 25),
(5, 5, 18),
(6, 6, 21),
(7, 7, 10),
(8, 8, 12);

CREATE TABLE SectionOrder (
  section_order_id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT,
  section_id INT,
  section_order INT,
  FOREIGN KEY (user_id) REFERENCES User(user_id),
  FOREIGN KEY (section_id) REFERENCES Section(section_id),
  UNIQUE KEY unique_user_section (user_id, section_id)
);


INSERT INTO SectionOrder (section_order_id, user_id, section_id, section_order)
VALUES
  (1, 1, 1, 1),
  (2, 2, 2, 2),
  (3, 3, 1, 3),
  (4, 4, 3, 1),
  (5, 5, 2, 2),
  (6, 1, 2, 4),
  (7, 2, 3, 3),
  (8, 3, 2, 1),
  (9, 4, 1, 2),
  (10, 5, 3, 4);


 CREATE TABLE LinkOrder (
       link_order_id INT PRIMARY KEY AUTO_INCREMENT,
       user_id INT,
       link_id INT,
       link_order INT,
       section_id INT,
       FOREIGN KEY (user_id) REFERENCES User(user_id),
       FOREIGN KEY (section_id) REFERENCES Section(section_id),
       FOREIGN KEY (link_id) REFERENCES Link(link_id),
       UNIQUE KEY unique_user_section (user_id, link_id)

   );


   INSERT INTO LinkOrder (link_order_id, user_id, link_id, section_id,  link_order) VALUES
   (1, 1, 1, 1, 2),
   (2, 1, 2, 1, 3),
   (3, 1, 3, 1, 1);
