# Score Card

### Introduction

This application is a Player's score card application. A player can store all of his scores with date. 
A player can also logically delete(isDelete = true/false) his scores but can not update score once inserted.
Player can find records by name, id, top sore, low score, average, scores in between of time, before date, after date etc.

### Technical Specs

Below tools, technologies and frameworks has been used to build this application:

- Java-8
- SpringBoot 2.5.1
- Gradle
- MySql
- OpenApiSpecification3
- Mokito & Junit
- Lombok

#### Configure Lombok

- Go to file -> settings -> plugins -> browse repository
- search for Lombok -> Install plugins
- restart IntelliJ
- Enable annotation processing
  - Goto IntelliJ -> preferences -> Build, Execution, Deployments -> Compiler -> Annotation processor -> Enable Annotation processing

### Steps to Build and Run the application
 
- Create Database in my sql by name *PlayerScore*
- Open src/main/resources/application.properties and spring.jpa.hibernate.ddl-auto=*none* to *create*. When you run application for first
time, it will create table for you.
  
- If you want to keep data in table and do not want to recreate table, change spring.jpa.hibernate.ddl-auto=*create* to *none*.
- When application is up and running go to [API Documentation] (http://localhost:8082/swagger-ui.html).
    - Here using API documentation you may get complete information about API.
    - You may also test application using Swagger API documentation.
    
- If you are facing any issue with top, low or avg score change sql mode *SET GLOBAL sql_mode=(SELECT REPLACE(@@sql_mode,'ONLY_FULL_GROUP_BY',''));*
- Unit test case can be run without any issues, by selecting the test class or right click the test class and run the test class.
