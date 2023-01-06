# Meetup API
![Modsen-logo](https://www.modsen-software.com/images/custom-head/custom-head-home.png)

---

The application is created for organizing meetings, by person in one meeting place.

### Used technologies
* *Java 17*
* *Spring Boot*
* *Hibernate*
* *Swagger-UI*
* *JUnit*
* *Mockito*
* *Lombok*
* *Docker*

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
* Install docker
* Execute command **docker-compose up** in root project
