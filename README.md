![Modsen-logo](https://www.modsen-software.com/images/custom-head/custom-head-home.png)

# Meetup API

---

The application is created for organizing meetings, by person in one meeting place.

### Used technologies
* *Spring Boot*
* *Hibernate*
* *Swagger-UI*
* *JUnit*
* *Mockito*
* *Lombok*

### Endpoints
> About endpoints and api, you can introduce by running the application and following the [link](http://localhost:8080/swagger-ui/ )
> ![Swagger-UI](https://github.com/IDM-MDI/MeetupAPI/blob/master/media/swagger-ui.png)

### Entity description
* **Events** - it's a kind collection of people in one place, for spending time.
* **Manager** - person who organized the event
* **Venue** - the place where the event takes place

### Entity in database
* **Event** {
  * id - bigint
  * topic - varchar(255)
  * description - text
  * date - timestamp with time zone
  * manager_id - bigint(foreign key to manager)
  * venue_id - bigint(foreign key to venue) <br/>
}
* **Manager** {
  * id - bigint
  * name - varchar(45)
  * surname - varchar(45)
  * lastname - varchar(45)
  * fullname - varchar(155) <br/>
  }
* **Venue** {
  * id - bigint
  * name - varchar(255) <br/>
  }

### How to run
* Create database with any name.
* Copy sql script from *web/src/main/resources/db/sql/create_tables.sql*, paste to your script tool and execute.
* Change properties to your own in *web/src/main/resources/application.properties* to connect with your database.
* Build your application into a jar file by command in the root directory: </br>
  ***mvn clean package***
* Change directory to */web/target* and run the application with the command: </br>
  ***java -jar web-1.0.jar***
