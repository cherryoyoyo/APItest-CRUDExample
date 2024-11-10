USE apitest;

CREATE TABLE Products (
  id INT AUTO_INCREMENT PRIMARY KEY,
  Name VARCHAR(50),          
  shortName VARCHAR(30),     
  dataGrouping VARCHAR(20)
);



CREATE TABLE Price (
    priceseq INT AUTO_INCREMENT PRIMARY KEY,
    productid INT NOT NULL,
    date DATE NOT NULL,
    price DECIMAL(10, 2) NOT NULL
);

