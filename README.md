# Mifinity Code Test - Getting Started & Questions

### Running the application
1. Current config runs for mySQL/MariaDB. Create schema and update application.properties according to your db settings.
2. Extract mariadb10.4.10 from MifinityCodeAssign_Runtime.zip

  2.1. Open a command prompt
  
  2.2. cd extracted_path/mariadb10.4.10/bin
  
  2.3. run mysqld.exe from command prompt
  
3. Run java -jar mifinity_codeassign.jar as a Spring Boot app
4. Open [http://localhost:8080/static/login.html](http://localhost:8080/static/login.html)
5. Note: Sample prepared username = Ahmet , password = 1234

## Application capabilities
1. Log in
2. Register a new user
3. Logout
4. Search cards by card number
5. Add a new credit card
6. Edit credit card expiry date
7. Delete a credit card


### Extra information
1. Database schema script & sample data: sql/dumpCardUser.sql

