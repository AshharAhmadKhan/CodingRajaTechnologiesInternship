-- Create Books table
CREATE TABLE Books (
  book_id INT PRIMARY KEY AUTO_INCREMENT,
  title VARCHAR(255) NOT NULL,
  author VARCHAR(255) NOT NULL,
  genre_id INT,
  ISBN VARCHAR(13),
  available BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (genre_id) REFERENCES Genres(genre_id)
);

-- Create Genres table (optional for storing genre categories)
CREATE TABLE Genres (
  genre_id INT PRIMARY KEY AUTO_INCREMENT,
  genre_name VARCHAR(255) NOT NULL
);

-- Create Patrons table
CREATE TABLE Patrons (
  patron_id INT PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(255) NOT NULL,
  phone_number VARCHAR(20),
  email VARCHAR(255)
);

-- Create BorrowingRecords table
CREATE TABLE BorrowingRecords (
  record_id INT PRIMARY KEY AUTO_INCREMENT,
  book_id INT NOT NULL,
  patron_id INT NOT NULL,
  borrow_date DATE NOT NULL,
  return_date DATE,
  FOREIGN KEY (book_id) REFERENCES Books(book_id),
  FOREIGN KEY (patron_id) REFERENCES Patrons(patron_id)
);
