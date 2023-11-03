-- Create the Link table
CREATE TABLE link (
  id INT AUTO_INCREMENT PRIMARY KEY,
  group_id INT,
  url VARCHAR(255)
);

-- Create the User table
CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255),
  email VARCHAR(255),
  password_hash VARCHAR(255),
  authentication_method ENUM('LOCAL', 'SSO'),
  enabled int
);


-- Create the LinkClicks table
CREATE TABLE linkclicks (
  click_id INT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  link_id INT,
  clicked_at TIMESTAMP,
  link_group VARCHAR(255)
);


CREATE TABLE authorities (
   id INT AUTO_INCREMENT PRIMARY KEY,
   user_id INT,
   username VARCHAR(255),
   authority VARCHAR(255)
);

-- Add foreign keys
ALTER TABLE linkclicks
ADD FOREIGN KEY (user_id) REFERENCES users (id);

ALTER TABLE LinkClicks
ADD FOREIGN KEY (link_id) REFERENCES link (id);

-- Insert data into the Link table
INSERT INTO link (group_id, url) VALUES
(1, 'https://www.example.com/page1'),
(1, 'https://www.example.com/page2'),
(2, 'https://www.example.com/page3'),
(2, 'https://www.example.com/page4'),
(3, 'https://www.example.com/page5');

-- Insert data into the User table


INSERT INTO users (username, email, password_hash, authentication_method, enabled) VALUES
('user1', 'user1@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 'LOCAL', true),
('user2', 'user2@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 'SSO', true),
('user3', 'user3@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 'LOCAL', true),
('user4', 'user4@example.com', '$2a$10$xKhiHVXrnRhRftvLJue9O.l.3JnsgN82On5aI/g79Q74lOqB/LOme', 'SSO', true);


INSERT INTO authorities (user_id, authority, username) VALUES
(1, 'ADMIN', 'user1'),
(2, 'ADMIN', 'user2'),
(3, 'ADMIN', 'user3'),
(4, 'ADMIN', 'user4');
