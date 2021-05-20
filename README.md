# Restaurant voting app
This is a simple voting web app created with Java 
## Table of contents
* [Task](#task)
* [Technologies](#technologies)
* [Setup](#setup)
* [REST api](#rest-api)

## Task
The task is:

Build a voting system for deciding where to have lunch.

2 types of users: admin and regular users
Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)
Menu changes each day (admins do the updates)
Users can vote on which restaurant they want to have lunch at
Only one vote counted per user
If user votes again the same day:
If it is before 11:00 we assume that he changed his mind.
If it is after 11:00 then it is too late, vote can't be changed
Each restaurant provides a new menu each day.
	
## Technologies
Project is created with:
* Java openjdk 15
* Springframework version 5.2.14
* Spring web version 5.2.14
* Spring webmvc version 5.2.14
* Spring test version 5.2.14
* Spring security version 5.4.6
* Spring-data-jpa version 2.3.9
* HSQLDB database version 2.5.1
* Tomcat-servlet-api 9.0.43
* Orm Hibernate version 5.4.30
* Hibernate cache version 5.4.30
* Logback-classic version 1.2.3
* Junit-jupiter-api version 5.4.0
* Jackson-json version 2.12.2
	
## Setup
To run this project: deploy voting.war into tomcat9 servlet or run the command  ```mvn clean verify org.codehaus.cargo:cargo-maven3-plugin:run```
 from project folder with pom.xml located in it (required openjdk 15 and maven3 installed, check that path environment variables have been set)
 
 ## REST api 
 
 #### users requests

* get all restaurants sorted by votes
```curl -v -u "user@yandex.ru:password" http://localhost:8080/voting/rest/restaurants```

* logged user vote for restaurant
```curl -v -X POST -u "user@yandex.ru:password" http://localhost:8080/voting/rest/vote?id=100002```

* get users profile
```curl -v -u "user@yandex.ru:password" http://localhost:8080/voting/rest/profile```

* update users profile
```curl -v  -X PUT  -u "user@yandex.ru:password" -H "Content-Type: application/json" -d "{\"id\":100000,\"name\":\"User\",\"email\":\"newuser@yandex.ru\",\"password\":\"password123\",\"enabled\":true,\"registered\":\"2021-05-12T09:53:43.268+00:00\",\"roles\":[\"USER\"]}" http://localhost:8080/voting/rest/profile``` 

* delete users profile
```curl -v  -X DELETE  -u "user@yandex.ru:password"  http://localhost:8080/voting/rest/profile``` 

 #### admins requests
 
 * get all users
```curl -v -u "admin@gmail.com:admin" http://localhost:8080/voting/rest/admin/users```

* get user by id
```curl -v -u "admin@gmail.com:admin" http://localhost:8080/voting/rest/admin/users/100001```

* get user by email
```curl -v -u "admin@gmail.com:admin" http://localhost:8080/voting/rest/admin/users/by?email=admin@gmail.com```

* create user
```curl -v -X POST -u "admin@gmail.com:admin" -H "Content-Type: application/json" -d "{\"id\":null,\"name\":\"user\",\"email\":\"user@yandex.ru\",\"password\":\"password\",\"enabled\":true,\"registered\":\"2021-05-12T09:53:43.268+00:00\",\"roles\":[\"USER\"]}" http://localhost:8080/voting/rest/admin/users```

* update user
```curl -v -X PUT -u "admin@gmail.com:admin" -H "Content-Type: application/json" -d "{\"id\":100005,\"name\":\"updated\",\"email\":\"user@yandex.ru\",\"password\":\"password\",\"enabled\":true,\"registered\":\"2021-05-12T09:53:43.268+00:00\",\"roles\":[\"USER\"]}" http://localhost:8080/voting/rest/admin/users/100005```

* delete user
```curl -v  -X DELETE -u "admin@gmail.com:admin" http://localhost:8080/voting/rest/admin/users/100005```

* get all restaurants
```curl -u "admin@gmail.com:admin" -v http://localhost:8080/voting/rest/admin/restaurants```

* get restaurant 
```curl -u "admin@gmail.com:admin" -v http://localhost:8080/voting/rest/admin/restaurants/100002```

* create restaurant
```curl -v -X POST -u "admin@gmail.com:admin" -H "Content-Type: application/json" -d "{\"id\":null,\"name\":\"new\",\"created\":\"2021-07-12T12:53:43.268\",\"menu\":[{\"name\":\"soup\",\"price\":500}]}" http://localhost:8080/voting/rest/admin/restaurants```

* update restaurant
 ```curl -v -X PUT -u "admin@gmail.com:admin" -H "Content-Type: application/json" -d "{\"id\":100006,\"name\":\"updated\",\"created\":\"2021-07-12T12:53:43.268\",\"menu\":[{\"name\":\"soup\",\"price\":500}]}" http://localhost:8080/voting/rest/admin/restaurants/100006```

* delete restaurant 
```curl -v  -X DELETE -u "admin@gmail.com:admin" http://localhost:8080/voting/rest/admin/restaurants/100006```
