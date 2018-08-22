![](https://raw.githubusercontent.com/hurryfox/docs/master/img/header.gif)

# Taxi drive app

[![Build status](https://travis-ci.org/hurryfox/taxi-drive-app.svg?branch=master)](https://travis-ci.org/hurryfox/taxi-drive-app)

Management solutions for taxi business needs  

## Used technologies
![](https://raw.githubusercontent.com/hurryfox/docs/master/img/tech.gif)

- - - -

## Run application local
Start docker services
```
    docker-compose up
```

Execute 'application db' migrations
```
    gradlew :migrations-db-application:flywayMigrate
```

Execute 'support db' migrations
```
    gradlew :migrations-db-support:migratorExecute
```

Build Avro classes
```
    gradlew :avro:build
```

Start spring boot application via **spring boot plugin** or gradle
```
     gradlew :application:bootRun
```

- - - -

## Features
### Operation executor 
[Diagram](https://raw.githubusercontent.com/hurryfox/docs/master/img/tech.gif)
- Core operation executor
- Groovy executor(scripts loaded from DB) TBD
- Spring function executor TBD
#### Usage 
Execute operation by name in **sync** mode (POST request - api/operation). Operation will be executed internally using the same service.
```
{
    "operationName": "OPERATION_NAME",
    "direction": "enroll",
    "body": {:}
 }
```
Execute operation by name in **async** mode (POST request - api/operation). Operation will be executed using Kafka stream and any idle service.
```
{
    "operationName": "OPERATION_NAME",
    "direction": "enroll",    
    "async": true,
    "body": {:}
 }
```
Rollback operation execution (POST request - api/operation)
```
{
    "operationName": "OPERATION_NAME",
    "direction": "rollback",
    "body": {
        "id" : "51ae64c4-3327-4b73-9498-1fa3347d2a15"
    }
}
```

### Query filters and sorters
Criteria API
#### Filters
```
?filter=[
    {"parameter" : "ridesAmount", "operator" : ">", "value" : 5}, 
    {"parameter" : "firstName", "operator" : "LIKE", "value" : "%Mon%"}
]
```

Supported operators:
```
    '='          
    '!='         
    '>'          
    '>='         
    '<'          
    '<='         
    'IS NULL'    
    'IS NOT NULL'
    'LIKE'   
```    

#### Sorters
```
?sorter=[
    {"parameter" : "ridesAmount", "order" : "ASC"},
    {"parameter" : "firstName", "order" : "DESC"},
]
```

Supported orders:
```
    'ASC'
    'DESC'
```

**Examples**
```
http://localhost:8080/api/client
    ?filter=[
                {"parameter" : "ridesAmount", "operator" : ">", "value" : 5}, 
                {"parameter" : "firstName", "operator" : "LIKE", "value" : "%a%"}
            ]
    &sorter=[
                {"parameter" : "ridesAmount", "order" : "DESC"}
            ]
```

### Pagination
```
    ?pagination={"firstResult": 0, "maxResults": 50} 
```

**Examples**
```
http://localhost:8087/api/client
        ?pagination={"firstResult": 0, "maxResults": 50} 
```

### Get row num
```
    api/{entityType}/count
```

### Extended search
Apache Lucene
 
**Examples**
```
http://localhost:8087/api/address/search
        ?parameters={
            "fields": ["city", "street", "building"], 
            "request": "Vladivostok Svetlanskaya"
        }
```

- - - -

## Useful information 
### Test Ride Workflow
**Create client**
```
{
	"login":"+79147654321",
	"firstName":"Polly",
	"lastName":"Crocodile",
	"ridesAmount": 5
}
```

**Create address from**
```
{
		"country":"Russia",
		"state":"Primorskiy",
		"city":"Vladivostok",
		"street":"Svetlanskaya",
		"building":"64"
}
```

**Create address to**
```
{
		"country":"Russia",
		"state":"Primorskiy",
		"city":"Vladivostok",
		"street":"Lugovaya",
		"building":"3"
}
```

**Create ride**
```
{
	"client":"+79147654321",
	"fromAddress": -13275131,
	"toAddress": 1437201388,
	"dateIn": "2017-11-13T12:45:30",
	"rideIn": "2017-11-13T12:45:30",
	"menInCar": 2
}
```

### Check ride price
**Check ctc price**
```
{
	"client":"+79147654321",
	"rawFromAddress": {
		"city":"Спасск-Дальний"
		
	},
	"rawToAddress": {
		"city":"Бусевка"
		
	}
}
```

**Check dtd price**
```
{
	"client":"+79147654320",
	"rawFromAddress": {
		"city":"Спасск-Дальний",
		"street":"Парковая",
		"building": "5"
	},
	"rawToAddress": {
		"city":"Спасск-Дальний",
		"street":"Новая",
		"building": "125"
	}
}
```

