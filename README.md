Minimum Requirements:

- JavaSE 1.8

- MySQL

- MySQL Java Connector 5.1.47

- Create the DB by running .SQL file on terminal or Work Bench.

- Project build is done on Eclipse IDE.

	
This project demonstrates proficiency in Core Java and (ANSI) SQL. I used Java to build a backend program that performs some basic and advanced database operations on a MySQL RDBMS. By the end of the project, 
I have demonstrated how to add, delete and alter db entries. The main database is created from a SQL script provided in this repository. The connection to the db is achieved through a JDBC Driver.

The java folders and classes are organized as follows:
1. Model Classes
2. DAO (DataAccessObjects)
3. Runner Classes
4. Other supporting files


## Project Requirements are defined below:

### 2.1 Functional Requirements – Operational Database

##### 2.1.1 Transaction Details - Functional Requirements

	1) To display the transactions made by
	customers living in a given zipcode for a
	given month and year. Order by day in
	descending order.
	2) To display the number and total values of
	transactions for a given type.
	3) To display the number and total values of
	transactions for branches in a given state

##### 2.1.2 Customer Details  - 
	
	1) To check the existing account details of a
	customer.
	2) To modify the existing account details of a
	customer
	3) To generate monthly bill for a credit card
	number for a given month and year.
	4) To display the transactions made by a
	customer between two dates. Order by year,
	month, and day in descending order.