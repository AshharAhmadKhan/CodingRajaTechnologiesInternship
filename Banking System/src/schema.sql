CREATE TABLE Users (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  pin_hash TEXT NOT NULL
);

CREATE TABLE Transactions (
  id INTEGER PRIMARY KEY AUTOINCREMENT,
  user_id INTEGER NOT NULL,
  timestamp INTEGER NOT NULL,
  amount DOUBLE NOT NULL,
  source_account TEXT NOT NULL,
  destination_account TEXT NOT NULL,
  status TEXT NOT NULL DEFAULT 'pending' -- pending, completed
  FOREIGN KEY (user_id) REFERENCES Users(id)
);