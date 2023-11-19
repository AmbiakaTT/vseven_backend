CREATE TABLE `UserQuickLink` (
  `user_quicklink_id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` int,
  `user_name` varchar(255),
  `link_id` int,
  `url` varchar(255),
  `link_name` varchar(255)
);

CREATE TABLE `User` (
  `user_id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_name` varchar(255),
  `email` varchar(255),
  `password_hash` varchar(255),
  `enabled` int
);

CREATE TABLE `Link` (
  `link_id` integer PRIMARY KEY AUTO_INCREMENT,
  `section_id` integer,
  `url` varchar(255),
  `link_name` varchar(255)
);


CREATE TABLE `authorities` (
  `authority_id` integer PRIMARY KEY AUTO_INCREMENT,
  `user_id` INT,
  `authority` varchar(255),
  `username` varchar(255)
);


CREATE INDEX idx_user_name ON User (user_name);

CREATE INDEX idx_url ON Link (url);

CREATE INDEX idx_link_name ON Link (link_name);


ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`user_name`) REFERENCES `User` (`user_name`);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`user_id`);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`link_id`) REFERENCES `Link` (`link_id`);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`url`) REFERENCES `Link` (`url`);

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

INSERT INTO Link (section_id, url, link_name) VALUES
   (1, 'https://example1.com', 'Link 1'),
   (1, 'https://example2.com', 'Link 2'),
   (1, 'https://example3.com', 'Link 3'),
   (1, 'https://example4.com', 'Link 4'),
   (1, 'https://example5.com', 'Link 5');


-- Insert data into the UserQuickLink table
INSERT INTO UserQuickLink (user_quicklink_id, user_id, user_name, link_id, url, link_name)
VALUES
    (1, 1, 'user1', 1, 'https://example1.com', 'Link 1'),
    (2, 2, 'user2', 2, 'https://example2.com', 'Link 2'),
    (3, 1, 'user1', 3, 'https://example3.com', 'Link 3'),
    (4, 3, 'user3', 1, 'https://example1.com', 'Link 1'),
    (5, 2, 'user2', 4, 'https://example4.com', 'Link 4');


