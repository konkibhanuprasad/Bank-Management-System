Steps to access the project 

step 1: clone the project files
step 2 : install postman application
step 3: install data base (MY SQL)

# Database Setup
1. Install MySQL
2. - URL: jdbc:mysql://localhost:3306/bankdb
   - USER: root
   - PASSWORD: your_password
  
   table creation for that project :
   
       CREATE DATABASE banking_app;
       USE banking_app;

       CREATE TABLE customers (
        id INT AUTO_INCREMENT PRIMARY KEY,
        account_holder_name VARCHAR(100) NOT NULL,
        account_number VARCHAR(20) UNIQUE NOT NULL,
        balance DECIMAL(10,2) DEFAULT 0.00,
       );
table creted succesfull;
 
step 4: run the java application
      - Right click on the project run java application
      - open post man what do you want to do operation (create)
      - put your method to POST and enter this 
                                          http://localhost:8080/api/accounts
        then click BODY -> raw -> json format
      - enter the data json format
      ex: "accountholdername" : "bhanu";
          "balance":1000; 
          the send it will completed check the databse , that data is stored

some operations:
  1. put --  http://localhost:8080/api/accounts/transfer   //transfer
  3. Delete -- http://localhost:8080/api/accounts/delete_no    //delete account
  4. get -- http://localhost:8080/api/accounts      #all account details
