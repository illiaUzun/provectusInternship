# Privectus Summer Java Internship test task
<br/>
The application is based on Spring Boot Application and Spring MVC framework + Thymeleaf template. <br/>
As database was used PostgreSQL 11.<br/>
Hibernate is being used as ORM.<br/>
Build is being accomplished with help of Maven.<br/>  
<br/>
I've not found an appropriate way how to integrate .htaccess file into my set-up, <br/>
so i use Spring Security to access the admin web-interface but i still don't store <br/>
admin username and password in database as was mentioned it task description. <br/>
<br/>
Default bootstrap templetes were used as Front-end.
<br/>
Result:<br/>
<br/>
User:
<br/>
To access user in web-interface you should enter the following url:<br/>
locahost:8080/user<br/>
<br/>
<br/>
User can: <br/>
1) Get list of all book <br/>
2) Filter(search) book/books by 3 parametres (title, author, genre) <br/>
3) Check information about each book <br/>
4) Order spicific book by filling an ordering form <br/>
<br/>
Administrator: <br/>
<br/>
To access admin web-interface you should enter the following url:<br/>
localhost:8080<br/>
and then login using following username and password:<br/>
Username: admin
Password: admin
<br/>
<br/>
Administrator can: <br/>
1) Delete books <br/>
2) Edit information about spicific book <br/>
3) Add new book <br/>
4) Check the list of orders made within a site <br/>
<br/>
<br/>
<br/>
To run an application you should:<br/>
1) Have Java JDK 1.8<br/>
2) Have maven installed <br/>
3) Download PostgreSQL 11, run pgAdmin server and create database with the following command: <br/>
createdb -h localhost -p 5432 -U postgres --password 12345 springbootdb <br />
4) Run application using IDE or with following command: <br/>
mvn spring-boot:run<br/>
