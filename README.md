# Fuelling 
REST Fuel Consumption Management System   
Java 8, Spring Boot, H2 embedded database, Swagger2 used technologies

## How It Works?
The application makes anable to you upload your refuel informations wirh price, volume and date as a file or single rest records.  
You can take detailed reports with urls given below.  
** Fuelling projects supports to show monthly reports for each given year seperately at the same time .

## How to start  
cd fuelling/  
mvn spring-boot:run

## Endpoints  
### Upload Data  
http://localhost:8080/upload/file  
http://localhost:8080/upload/single  

### Reports  
http://localhost:8080/report/month/{monthIndex}    
http://localhost:8080/report/month/{monthIndex}/{driverID}  
http://localhost:8080/report/monthly   
http://localhost:8080/report/monthly/{driverID}  
http://localhost:8080/report/monthly/statistics  
http://localhost:8080/report/monthly/statistics/{driverID}  


### Swagger  
http://localhost:8080/swagger-ui.html#/  

## Testing  
Controller and service unit tests can be testable for fuelling project  
data.txt can be used for testing to upload file.

cetinkaltar@gmail.com






