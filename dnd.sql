CREATE DATABASE DnD;
USE DnD;

-- Users Table (for login/signup)
CREATE TABLE Users (
    UserID INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(255) UNIQUE NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Password VARCHAR(255) NOT NULL -- Plaintext for now (but not recommended for real apps)
);

-- Characters Table (linked to Users)
CREATE TABLE Characters (
    CharacterID INT AUTO_INCREMENT PRIMARY KEY,
    UserID INT, -- Links characters to a user
    Name VARCHAR(255) NOT NULL,
    CharacterClass VARCHAR(255) NOT NULL,
    Subclass VARCHAR(255),
    Age INT,
    Money INT DEFAULT 0,
    Inventory TEXT,
    FOREIGN KEY (UserID) REFERENCES Users(UserID) ON DELETE CASCADE
);

-- Sample User (Password is plaintext for now)
INSERT INTO Users (Name, Email, Password) 
VALUES ('JohnDoe', 'john@example.com', 'mypassword123');

-- Sample Character (Linked to UserID 1)
INSERT INTO Characters (UserID, Name, CharacterClass, Subclass, Age, Money, Inventory) 
VALUES (1, 'John', 'Fighter', 'Champion', 25, 100, 'Sword, Shield, Armor');
INSERT INTO Characters (UserID, Name, CharacterClass, Subclass, Age, Money, Inventory) 
VALUES (2, 'George', 'Fighter', 'Paladin', 45, 1000, 'Sword, Shield, Armor');
INSERT INTO Characters (UserID, Name, CharacterClass, Subclass, Age, Money, Inventory) 
VALUES (2, 'George', 'Fighter', 'Paladin', 45, 1000, 'Sword, Shield, Armor');

