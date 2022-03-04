# Apocalypse

Apocalypse application

## Running the application

1.The easiest way to run the application is using docker ,
  if you have docker installed run
  ```bash
     docker-compose build
     docker-compose up 
  ```
2. Building the application from source with maven
  ```bash
     mvn clean package
     java -jar target/apocalypse-0.0.1-SNAPSHOT.jar 
  ```
Remember to rename the example.env to .env and change the MONGO-URL to point to the url of the mongo database .

The default api port is 8080

   


## Api Documentation
1.Adding a survivor 
```http
POST /survivor/add
```
Request
```javascript
{
	"name":"John Doe",
	"age":23,
	"gender":"male",
	"id":"123454289",
	"last_location":[-1.286389, 36.817223],
	"resources":["water","food"]
}
```
Response
```javascript
{
    "status": "success",
    "data": {
        "survivor_system_ref": "00923cb9-2603-4a9e-bf71-42d95d39ab3d"
    }
}
```

2.Updating a survivor .
   Use this endpoint to update Survivor details including the last_location .
   last_location[lat,long]
  
```http
PUT /survivor/update/{survivor_system_ref}
```
Request
```javascript
{
	"name":"Jane Doe",
	"age":30,
	"gender":"female",
	"id":"1234542",
	"last_location":[-1.286319, 36.817213],
	"resources":["water","food","petrol"]
}
```
Response
```javascript
{
    "status": "success",
    "data": {
        "update_status": "success"
    }
}
```

3.Fetch a Survivor Details 

```http
GET /survivor/details{survivor_system_ref}
```
4.List All Survivors

```http
GET /survivor/list
```

5.Report Infection 
```http
POST /infection/report/{survivor_system_ref}
```
the reporter field should be a survivor system ref of the reporting survivor and the field robot_serial_number should be a serial number of a robot

Request
```javascript
{
	"reporter":"8b703292-ad8b-4dba-a45d-13af4685f692",
	"robot_serial_number":"FQW6UXSE6A4IRCB",
	"report_details":"report remarks"
}


```
Response
```javascript
{
    "status": "success",
    "data": {
        "survivor_system_ref": "00923cb9-2603-4a9e-bf71-42d95d39ab3d"
    }
}
```
6.Adding robots to the system
should be an array of robot details
```http
POST /robot/add
```
Sample Request
```javascript
[{"model":"17PJP","serialNumber":"C5XU2SLSM4UQQ6R","manufacturedDate":"2022-03-11T03:46:14.2653147+00:00","category":"Land"}]
```
Response
```javascript
{
    "status": "success",
    "data": {
        "robot_status": "success"
    }
}
```
7.Survivor Report

```http
GET /report/summary
```
Sample Response
```javascript
{
    "status": "success",
    "data": [
        {
            "_id": "non-infected",
            "count": 1,
            "percentage": 50
        },
        {
            "_id": "infected",
            "count": 1,
            "percentage": 50
        }
    ]
}
```
7.Robot Report

```http
GET /report/summary
```
Sample Response
```javascript
{
    "status": "success",
    "data": [
        {
            "_id": "Land",
            "count": 33
        },
        {
            "_id": "Flying",
            "count": 17
        }
    ]
}
```
7.List of infected survivors

```http
GET /report/infected
```
8.List of non-infected survivors

```http
GET /report/non-infected
```


   



