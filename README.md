# Taxi drive app

## Run application local
**Start spring boot application via spring boot plugin**

## Prepare environment

**Launch docker containers**
<pre>
    docker run --restart always -d --name postgres -p 5432:5432 postgres
</pre>

**Execute DB migrations**
- Execute Postgres migrations (info in module ./db)
- Execute Cassandra migrations (info in module ./db-support)

## Useful information 
### Test Ride Workflow
**Create client**
<pre>
{
	"login":"+79147654321",
	"firstName":"Polly",
	"lastName":"Crocodile",
	"ridesAmount": 5
}
</pre>

**Create address from**
<pre>
{
		"country":"Russia",
		"state":"Primorskiy",
		"city":"Vladivostok",
		"street":"Svetlanskaya",
		"building":"64"
}
</pre>

**Create address to**
<pre>
{
		"country":"Russia",
		"state":"Primorskiy",
		"city":"Vladivostok",
		"street":"Lugovaya",
		"building":"3"
}
</pre>

**Create ride**
<pre>
{
	"client":"+79147654321",
	"fromAddress": -13275131,
	"toAddress": 1437201388,
	"dateIn": "2017-11-13T12:45:30",
	"rideIn": "2017-11-13T12:45:30",
	"menInCar": 2
}
</pre>

### Check ride price
**Check ctc price**
<pre>
{
	"client":"+79147654321",
	"rawFromAddress": {
		"city":"Спасск-Дальний"
		
	},
	"rawToAddress": {
		"city":"Бусевка"
		
	}
}
</pre>

**Check dtd price**
<pre>
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
</pre>

## Query filters and sorters
### Filters
<pre>
filter=[
    {"parameter" : "ridesAmount", "operator" : ">", "value" : 5}, 
    {"parameter" : "firstName", "operator" : "LIKE", "value" : "%Mon%"}
]
</pre>

**Supported operators:**
- '='          
- '!='         
- '>'          
- '>='         
- '<'          
- '<='         
- 'IS NULL'    
- 'IS NOT NULL'
- 'LIKE'       

### Sorters
<pre>
sorter=[
    {"parameter" : "ridesAmount", "order" : "ASC"},
    {"parameter" : "firstName", "order" : "DESC"},
]
</pre>

**Supported orders:**
- 'ASC'
- 'DESC'

### Examples
<pre>
http://localhost:8087/api/client
    ?filter=[
                {"parameter" : "ridesAmount", "operator" : ">", "value" : 5}, 
                {"parameter" : "firstName", "operator" : "LIKE", "value" : "%a%"}
            ]
    &sorter=[
                {"parameter" : "ridesAmount", "order" : "DESC"}
            ]
</pre>

## Pagination
<pre>
    pagination={"firstResult": 0, "maxResults": 50} 
</pre>

### Examples
<pre>
http://localhost:8087/api/client
        ?pagination={"firstResult": 0, "maxResults": 50} 
</pre>

**Get row num**
<pre>
    api/{entityType}/count
</pre>

## Extended search
TBD
 
### Examples
<pre>
http://localhost:8087/api/address/search
        ?parameters={
            "fields": ["city", "street", "building"], 
            "request": "Vladivostok Svetlanskaya"
        }
</pre>