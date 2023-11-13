CREATE TABLE `UserQuickLink` (
  `id` int PRIMARY KEY,
  `user_id` int, 
   `user_name` varchar(255),
  `link_id` int,
  `url` varchar(255),
  `link_name` varchar(255)
);

CREATE TABLE `LinkClicks` (
  `click_id` INT,
  `user_id` INT,
  `link_id` INT,
  `clicked_at` TIMESTAMP,
  `group` varchar(255)
);

CREATE TABLE `authority` (
  `id` int PRIMARY KEY,
  `user_id` int,
  `authority` varchar(255)
);

CREATE TABLE `User` (
  `id` integer PRIMARY KEY,
  `username` varchar(255),
  `email` varchar(255),
  `password_hash` varchar(255),
  `enabled` int
);

CREATE TABLE `Link` (
  `id` integer PRIMARY KEY,
  `section_id` integer,
  `url` integer,
  `link_name` varchar(255)
);

CREATE TABLE `LinkGroups` (
  `group_id` INT,
  `group_name` varchar(255)
);

CREATE TABLE `UserLinkOrder` (
  `user_id` INTEGER,
  `group_id` INTEGER,
  `link_id` INTEGER
);

CREATE TABLE `authorities` (
  `authority_id` INT PRIMARY KEY,
  `user_id` INT,
  `authority` INT,
  `username` varchar(255)
);

ALTER TABLE `UserQuickLink` ADD CONSTRAINT `FK_UserQuickLink_User` FOREIGN KEY (`username`) REFERENCES `users` (`username`);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

ALTER TABLE `Link` ADD FOREIGN KEY (`id`) REFERENCES `UserQuickLink` (`link_id`);

ALTER TABLE `authority` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`url`) REFERENCES `Link` (`url`);

ALTER TABLE `UserQuickLink` ADD FOREIGN KEY (`link_name`) REFERENCES `Link` (`link_name`);

ALTER TABLE `authorities` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);


-- Insert data into the User table


INSERT INTO users (username, email, password_hash, enabled) VALUES
('user1', 'user1@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', true),
('user2', 'user2@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', true),
('user3', 'user3@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', true),
('user4', 'user4@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', true);


INSERT INTO authorities (user_id, authority, username) VALUES
(1, 'ADMIN', 'user1'),
(2, 'ADMIN', 'user2'),
(3, 'ADMIN', 'user3'),
(4, 'ADMIN', 'user4');


-- Insert data into the UserQuickLink table
INSERT INTO UserQuickLink (id, username, link_id, url, link_name)
VALUES
    ('1', 'user1', 1, 'https://example1.com', 'Link 1'),
    ('2', 'user2', 2, 'https://example2.com', 'Link 2'),
    ('3', 'user1', 3, 'https://example3.com', 'Link 3'),
    ('4', 'user3', 1, 'https://example1.com', 'Link 1'),
    ('5', 'user2', 4, 'https://example4.com', 'Link 4');

-- Insert more links for user3
INSERT INTO UserQuickLink (id, username, link_id, url, link_name)
VALUES
    ('6', 'user3', 2, 'https://example2.com', 'Link 2'),
    ('7', 'user3', 5, 'https://example5.com', 'Link 5');
