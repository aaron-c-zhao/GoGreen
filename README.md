# CSE1105 #GoGreen-group82

# **About our Project** :

### **Name :** 
Project #GoGreen
	
### **Our goal:** 
Environmental issues are harmful effects of human activity on the biophysical environment. Environmental protection is a practice of protecting the natural environment on individual, organizational or governmental levels, for the benefit of both the environment and humans. Our project is focused on each individual person and especially on the ecological footprint per person, as its level in the Netherlands is particularly high. We are building Java application that enables users to enter their ecological choices. They get an overview of what they can do and see how much greener they can still go. To stimulate more green activities, we are integrating a gamification aspect to the application. Through it the users will be able to compare their progress with their friends. Based on your score and your achievements you will be placed in a rank group, which differentiate from each other on specially created badges.

### Structure

├── gogreen-client
│   ├── pom.xml
│   ├── src
│       ├── main
│       │   ├── java
│       │   │   └── gogreenclient
│       │   │       ├── GoGreenApplication.java
│       │   │       ├── config
│       │   │       ├── datamodel
│       │   │       └── screens
│       │   │           └── window
│       │   └── resources
│       └── test
│           └── java
│               └── gogreenclient
│                   ├── GoGreenApplicationTest.java
│                   └── datamodel
├── gogreen-webserver
│   ├── gogreen-webserver.iml
│   ├── pom.xml
│   ├── src
│       ├── main
│       │   ├── java
│       │   │   └── gogreenserver
│       │   │       ├── GogreenApplication.java
│       │   │       ├── config
│       │   │       ├── controllers
│       │   │       ├── entity
│       │   │       ├── repositories
│       │   │       ├── security
│       │   │       └── services
│       │   ├── profile_pictures
│       │   └── resources
│       └── test
│           ├── java
│           │   └── gogreenserver
│           └── resources
├── jacoco-aggregate
    └── pom.xml

Our project is consisted of three separate modules as follows:

#### gogreen-client



#### gogreen-webserver

This module provides a RESTful service for our client, the endpoints are listed here [endpoints](doc/endpoints.md).The responses will be in JSON format. 

One way to make the server work, you need to install the dependencies first by:

```po
mvn install
```

if you have maven installed. Otherwise, you need to use the maven wrapper by:

```po
./mvnw install
```

After all dependencies have been installed, you can either run the main class in your IDE.

You can also package the application into a fat jar by:

```po
mvn package
```



```p
java -jar gogreen-webserver/target/gogreen-webserver-1.0.0.jar
```



#### jacoco-aggregate




# **Our team on this project :**

Note : For a more in-depth view about each of our members please refer to doc/personal_development_plan

* ### **Alin Prundeanu**
	* Romanian Project Manager
	* NetID: aprundeanu  
    ![](doc/member_images/Alin.png)


* ### **Atanas Marinov**
	* Bulgarian Front-end Developer  
	* NetID: aimarinov
	
	![](doc/member_images/aimarinov.png)


* ### **Chengrui Zhao**
	* Chinese Back-end Developer
	* NetID: aaronzhao

	![](doc/member_images/zhao.jpeg)


* ### **Giovanni Fincato de Loureiro** 
	* Italian Front-end Developer 
	* NetID: gfincatodelour

	![](doc/member_images/Giovanni.jpeg)


* ### **Jan Pieter Kroeb** 
	* Dutch Back-end Developer 
	* NetID: jkroeb

	![](doc/member_images/jpkroeb.jpeg)


* ### **Nik Kapitonenko**
	* Ukrainan Database Engineer
	* NetID: nkapitonenko  

	![](doc/member_images/nkapitonenko.png)


* ### **Rahul Kalaria** 
	* Indian Database Engineer 
	* NetID: rkalaria

	![](doc/member_images/Rahul.jpeg)

