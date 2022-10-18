CREATE USER 'setup'@'localhost';

GRANT ALL PRIVILEGES ON `%`.* TO setup@localhost; 

UPDATE mysql.user SET Grant_priv='Y' WHERE User='setup';

FLUSH PRIVILEGES;