# NotificationSystem

Technologies:
 Spring Boot - 2.0
 Maven
 Simpleslackapi
 spring-boot-email-tools
 lombok
 
  Setting:
      configure email and slack in properties file
 
 Installation:
  Run maven bootRun to start the server manually.
  To access Rest API : http://localhost:8081/api/v1
  
  eg: http://localhost:8081/api/v1/notification/notifyAll
   body:
       
      {  
       "body": "message",  
       "from": "sender email id",  
       "subject": "subject",  
       "to": "receiver email id"  
      }
      

  
 
