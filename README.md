# DePaul Event Planner

## Features:
1. Authentication: basic login
2. Home page will show extra button `register event` if user logged in is of type `Organizer`
3. Viewing specific event
   1. `if STUDENT && !alreadyReserved` => Reserve option / pop-up config
      1. `ELSE` => Cancel Reservation option / pop-up config
   2. `if FACULTY && ORGANIZER (of event)` => Cancel Event option / pop-up config
   3. `if FACULTY && !ORGANIZER && alreadyReserved` =>  Cancel Reservation option / pop-up config
      1. `ELSE` => Reserve option / pop-up config
   4. Depending on above scenarios, a button + pop-up confirmation will be filled out according to condition evaluation
4. Reserve / Cancel Reservation
5. Register Event / Cancel Event
6. Query upcoming events related to user

### How to run:
Two Options:
1. Run main class in IDE
2. Run `mvn clean install` && `$ java -jar target/depaul-event-planner-0.0.1-SNAPSHOT.jar`

In browser, go to endpoint : `localhost:8080`

Use credentials below to access application    
    
   

### Startup Credentials
| Username | Secret |
| -------- | -------- |
| alejandro   | password |
| george    | password |
| nermin    | password | 
| faculty   | password |


