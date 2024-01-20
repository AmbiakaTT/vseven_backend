
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
('user1', 'user1@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.h3JnsgN82On5aI/g79Q74lOqB/LOme', 1),
('user2', 'user2@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1),
('user3', 'user3@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1),
('user4', 'user4@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1),
('user5', 'user5@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1),
('user6', 'user6@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 1);


INSERT INTO authorities (user_id, authority, username) VALUES
(1, 'ADMIN', 'user1'),
(2, 'ADMIN', 'user2'),
(3, 'ADMIN', 'user3'),
(4, 'ADMIN', 'user4'),
(5, 'ADMIN', 'user5'),
(6, 'ADMIN', 'user6');


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
  section_index INT,
  section_column INT,
  FOREIGN KEY (user_id) REFERENCES User(user_id),
  FOREIGN KEY (section_id) REFERENCES Section(section_id),
  UNIQUE KEY unique_user_section (user_id, section_id)
);



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
  -- User 1
  (1, 1, 1, 1, 1), (2, 1, 2, 1, 2), (3, 1, 3, 1, 3), (4, 1, 4, 1, 4), (5, 1, 5, 1, 5),
  (6, 1, 6, 2, 1), (7, 1, 7, 2, 2), (8, 1, 8, 2, 3), (9, 1, 9, 2, 4),
  (10, 1, 10, 3, 1), (11, 1, 11, 3, 2), (12, 1, 12, 3, 3), (13, 1, 13, 3, 4), (14, 1, 14, 3, 5),
  (15, 1, 15, 4, 1), (16, 1, 16, 4, 2), (17, 1, 17, 4, 3),
  (18, 1, 18, 5, 1), (19, 1, 19, 5, 2),
  (20, 1, 20, 6, 1), (21, 1, 21, 6, 2), (22, 1, 22, 6, 3), (23, 1, 23, 6, 4), (24, 1, 24, 6, 5), (25, 1, 25, 6, 6), (26, 1, 26, 6, 7), (27, 1, 27, 6, 8),
  (28, 1, 28, 7, 1), (29, 1, 29, 7, 2), (30, 1, 30, 7, 3), (31, 1, 31, 7, 4), (32, 1, 32, 7, 5), (33, 1, 33, 7, 6), (34, 1, 34, 7, 7), (35, 1, 35, 7, 8), (36, 1, 36, 7, 9),
  (37, 1, 37, 8, 1), (38, 1, 38, 8, 2), (39, 1, 39, 8, 3), (40, 1, 40, 8, 4), (41, 1, 41, 8, 5),
  (42, 1, 42, 9, 1), (43, 1, 43, 9, 2),
  (44, 1, 44, 10, 1), (45, 1, 45, 10, 2),

-- User 2
  (46, 2, 1, 1, 1), (47, 2, 2, 1, 2), (48, 2, 3, 1, 3), (49, 2, 4, 1, 4), (50, 2, 5, 1, 5),
  (51, 2, 6, 2, 1), (52, 2, 7, 2, 2), (53, 2, 8, 2, 3), (54, 2, 9, 2, 4),
  (55, 2, 10, 3, 1), (56, 2, 11, 3, 2), (57, 2, 12, 3, 3), (58, 2, 13, 3, 4), (59, 2, 14, 3, 5),
  (60, 2, 15, 4, 1), (61, 2, 16, 4, 2), (62, 2, 17, 4, 3),
  (63, 2, 18, 5, 1), (64, 2, 19, 5, 2),
  (65, 2, 20, 6, 1), (66, 2, 21, 6, 2), (67, 2, 22, 6, 3), (68, 2, 23, 6, 4), (69, 2, 24, 6, 5), (70, 2, 25, 6, 6), (71, 2, 26, 6, 7), (72, 2, 27, 6, 8),
  (73, 2, 28, 7, 1), (74, 2, 29, 7, 2), (75, 2, 30, 7, 3), (76, 2, 31, 7, 4), (77, 2, 32, 7, 5), (78, 2, 33, 7, 6), (79, 2, 34, 7, 7), (80, 2, 35, 7, 8), (81, 2, 36, 7, 9),
  (82, 2, 37, 8, 1), (83, 2, 38, 8, 2), (84, 2, 39, 8, 3), (85, 2, 40, 8, 4), (86, 2, 41, 8, 5),
  (87, 2, 42, 9, 1), (88, 2, 43, 9, 2),
  (89, 2, 44, 10, 1), (90, 2, 45, 10, 2),

  -- User 3
  (91, 3, 1, 1, 1), (92, 3, 2, 1, 2), (93, 3, 3, 1, 3), (94, 3, 4, 1, 4), (95, 3, 5, 1, 5),
  (96, 3, 6, 2, 1), (97, 3, 7, 2, 2), (98, 3, 8, 2, 3), (99, 3, 9, 2, 4),
  (100, 3, 10, 3, 1), (101, 3, 11, 3, 2), (102, 3, 12, 3, 3), (103, 3, 13, 3, 4), (104, 3, 14, 3, 5),
  (105, 3, 15, 4, 1), (106, 3, 16, 4, 2), (107, 3, 17, 4, 3),
  (108, 3, 18, 5, 1), (109, 3, 19, 5, 2),
  (110, 3, 20, 6, 1), (111, 3, 21, 6, 2), (112, 3, 22, 6, 3), (113, 3, 23, 6, 4), (114, 3, 24, 6, 5), (115, 3, 25, 6, 6), (116, 3, 26, 6, 7), (117, 3, 27, 6, 8),
  (118, 3, 28, 7, 1), (119, 3, 29, 7, 2), (120, 3, 30, 7, 3), (121, 3, 31, 7, 4), (122, 3, 32, 7, 5), (123, 3, 33, 7, 6), (124, 3, 34, 7, 7), (125, 3, 35, 7, 8), (126, 3, 36, 7, 9),
  (127, 3, 37, 8, 1), (128, 3, 38, 8, 2), (129, 3, 39, 8, 3), (130, 3, 40, 8, 4), (131, 3, 41, 8, 5),
  (132, 3, 42, 9, 1), (133, 3, 43, 9, 2),
  (134, 3, 44, 10, 1), (135, 3, 45, 10, 2),

  -- User 4
  (136, 4, 1, 1, 1), (137, 4, 2, 1, 2), (138, 4, 3, 1, 3), (139, 4, 4, 1, 4), (140, 4, 5, 1, 5),
  (141, 4, 6, 2, 1), (142, 4, 7, 2, 2), (143, 4, 8, 2, 3), (144, 4, 9, 2, 4),
  (145, 4, 10, 3, 1), (146, 4, 11, 3, 2), (147, 4, 12, 3, 3), (148, 4, 13, 3, 4), (149, 4, 14, 3, 5),
  (150, 4, 15, 4, 1), (151, 4, 16, 4, 2), (152, 4, 17, 4, 3),
  (153, 4, 18, 5, 1), (154, 4, 19, 5, 2),
  (155, 4, 20, 6, 1), (156, 4, 21, 6, 2), (157, 4, 22, 6, 3), (158, 4, 23, 6, 4), (159, 4, 24, 6, 5), (160, 4, 25, 6, 6), (161, 4, 26, 6, 7), (162, 4, 27, 6, 8),
  (163, 4, 28, 7, 1), (164, 4, 29, 7, 2), (165, 4, 30, 7, 3), (166, 4, 31, 7, 4), (167, 4, 32, 7, 5), (168, 4, 33, 7, 6), (169, 4, 34, 7, 7), (170, 4, 35, 7, 8), (171, 4, 36, 7, 9),
  (172, 4, 37, 8, 1), (173, 4, 38, 8, 2), (174, 4, 39, 8, 3), (175, 4, 40, 8, 4), (176, 4, 41, 8, 5),
  (177, 4, 42, 9, 1), (178, 4, 43, 9, 2),
  (179, 4, 44, 10, 1), (180, 4, 45, 10, 2),

  -- User 5
  (181, 5, 1, 1, 1), (182, 5, 2, 1, 2), (183, 5, 3, 1, 3), (184, 5, 4, 1, 4), (185, 5, 5, 1, 5),
  (186, 5, 6, 2, 1), (187, 5, 7, 2, 2), (188, 5, 8, 2, 3), (189, 5, 9, 2, 4),
  (190, 5, 10, 3, 1), (191, 5, 11, 3, 2), (192, 5, 12, 3, 3), (193, 5, 13, 3, 4), (194, 5, 14, 3, 5),
  (195, 5, 15, 4, 1), (196, 5, 16, 4, 2), (197, 5, 17, 4, 3),
  (198, 5, 18, 5, 1), (199, 5, 19, 5, 2),
  (200, 5, 20, 6, 1), (201, 5, 21, 6, 2), (202, 5, 22, 6, 3), (203, 5, 23, 6, 4), (204, 5, 24, 6, 5), (205, 5, 25, 6, 6), (206, 5, 26, 6, 7), (207, 5, 27, 6, 8),
  (208, 5, 28, 7, 1), (209, 5, 29, 7, 2), (210, 5, 30, 7, 3), (211, 5, 31, 7, 4), (212, 5, 32, 7, 5), (213, 5, 33, 7, 6), (214, 5, 34, 7, 7), (215, 5, 35, 7, 8), (216, 5, 36, 7, 9),
  (217, 5, 37, 8, 1), (218, 5, 38, 8, 2), (219, 5, 39, 8, 3), (220, 5, 40, 8, 4), (221, 5, 41, 8, 5),
  (222, 5, 42, 9, 1), (223, 5, 43, 9, 2),
  (224, 5, 44, 10, 1), (225, 5, 45, 10, 2),

  -- User 6 (Default user)
  (226, 6, 1, 1, 1), (227, 6, 2, 1, 2), (228, 6, 3, 1, 3), (229, 6, 4, 1, 4), (230, 6, 5, 1, 5),
  (231, 6, 6, 2, 1), (232, 6, 7, 2, 2), (233, 6, 8, 2, 3), (234, 6, 9, 2, 4),
  (235, 6, 10, 3, 1), (236, 6, 11, 3, 2), (237, 6, 12, 3, 3), (238, 6, 13, 3, 4), (239, 6, 14, 3, 5),
  (240, 6, 15, 4, 1), (241, 6, 16, 4, 2), (242, 6, 17, 4, 3),
  (243, 6, 18, 5, 1), (244, 6, 19, 5, 2),
  (245, 6, 20, 6, 1), (246, 6, 21, 6, 2), (247, 6, 22, 6, 3), (248, 6, 23, 6, 4), (249, 6, 24, 6, 5), (250, 6, 25, 6, 6), (251, 6, 26, 6, 7), (252, 6, 27, 6, 8),
  (253, 6, 28, 7, 1), (254, 6, 29, 7, 2), (255, 6, 30, 7, 3), (256, 6, 31, 7, 4), (257, 6, 32, 7, 5), (258, 6, 33, 7, 6), (259, 6, 34, 7, 7), (260, 6, 35, 7, 8), (261, 6, 36, 7, 9),
  (262, 6, 37, 8, 1), (263, 6, 38, 8, 2), (264, 6, 39, 8, 3), (265, 6, 40, 8, 4), (266, 6, 41, 8, 5),
  (267, 6, 42, 9, 1), (268, 6, 43, 9, 2),
  (269, 6, 44, 10, 1), (270, 6, 45, 10, 2);


INSERT INTO SectionOrder (user_id, section_id, section_column, section_index)
VALUES
-- User 6 (Default user)
  (6, 1, 0, 0),
  (6, 2, 0, 1),
  (6, 3, 1, 0),
  (6, 4, 1, 1),
  (6, 5, 0, 2),
  (6, 6, 2, 0),
  (6, 7, 2, 1),
  (6, 8, 3, 0),
  (6, 9, 3, 1),
  (6, 10, 3, 2),
-- User 1
  (1, 1, 0, 0),
  (1, 2, 0, 1),
  (1, 3, 1, 0),
  (1, 4, 1, 1),
  (1, 5, 0, 2),
  (1, 6, 2, 0),
  (1, 7, 2, 1),
  (1, 8, 3, 0),
  (1, 9, 3, 1),
  (1, 10, 3, 2),
-- User 2
  (2, 1, 0, 0),
  (2, 2, 0, 1),
  (2, 3, 1, 0),
  (2, 4, 1, 1),
  (2, 5, 0, 2),
  (2, 6, 2, 0),
  (2, 7, 2, 1),
  (2, 8, 3, 0),
  (2, 9, 3, 1),
  (2, 10, 3, 2),
-- User 3
  (3, 1, 0, 0),
  (3, 2, 0, 1),
  (3, 3, 1, 0),
  (3, 4, 1, 1),
  (3, 5, 0, 2),
  (3, 6, 2, 0),
  (3, 7, 2, 1),
  (3, 8, 3, 0),
  (3, 9, 3, 1),
  (3, 10, 3, 2),
-- User 4
  (4, 1, 0, 0),
  (4, 2, 0, 1),
  (4, 3, 1, 0),
  (4, 4, 1, 1),
  (4, 5, 0, 2),
  (4, 6, 2, 0),
  (4, 7, 2, 1),
  (4, 8, 3, 0),
  (4, 9, 3, 1),
  (4, 10, 3, 2),
-- User 5
  (5, 1, 0, 0),
  (5, 2, 0, 1),
  (5, 3, 1, 0),
  (5, 4, 1, 1),
  (5, 5, 0, 2),
  (5, 6, 2, 0),
  (5, 7, 2, 1),
  (5, 8, 3, 0),
  (5, 9, 3, 1),
  (5, 10, 3, 2);
