# Taxi drive app

### Run application local
**Start spring boot application with VM options:**
<pre>
-Dspring.profiles.active=local 
-Dspring.data.cassandra.contact-points=
-Dspring.data.cassandra.username=
-Dspring.data.cassandra.password=
</pre>

### DB connection
**Local DB connection (docker full version)**
<pre>
(not need to add parameters)
</pre>

**Local DB connection (docker toolbox)**
<pre>
-Dspring.data.cassandra.contact-points=192.168.99.100
</pre>

**Dev DB connection**
<pre>
-Dspring.data.cassandra.contact-points=vostok.systems
-Dspring.data.cassandra.keyspace-name=tda_dev
-Dspring.data.cassandra.username=tda_dev
-Dspring.data.cassandra.password=some_password
</pre>

### Execute migrations
**Commands:**
<pre>
gradlew migratorDropKeyspace
gradlew migratorExecute
</pre>

**Parameters:**
<pre>
-PmigratorHost=
-PmigratorUser= 
-PmigratorPassword= 
-PmigratorKeyspace=
-PmigratorScript=
</pre>

**Examples:**
<pre>
gradlew migratorDropKeyspace -PmigratorUser=cassandra -PmigratorPassword=cassandra 
gradlew migratorExecute -PmigratorUser=cassandra -PmigratorPassword=cassandra -PmigratorScript=db/src/main/resources/scripts/migrations

gradlew migratorExecute -PmigratorHost=192.168.99.100 -PmigratorScript=db/src/main/resources/scripts/migrations
gradlew migratorDropKeyspace -PmigratorHost=192.168.99.100
</pre>


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


- "JAVA_OPTS=-Dmessaging.user=admin

docker run --restart always -d --name tda --net dockernet --ip 172.18.0.21 -p 8087:8080 -e JAVA_OPTS="-Dspring.profiles.active=prod -Dspring.data.cassandra.contact-points=172.18.0.22" markklim/taxi-drive-app:0.1
docker run -d --name tda --net dockernet --ip 172.18.0.21 -p 8087:8080 -e JAVA_OPTS="-Dspring.profiles.active=prod -Dspring.data.cassandra.contact-points=172.18.0.22" markklim/taxi-drive-app:0.1
docker run --restart always -d --name tda --net dockernet --ip 172.18.0.21 -p 8087:8080 -e JAVA_OPTS="-Dspring.profiles.active=prod" markklim/taxi-drive-app:0.1

docker run --restart always -d --name tda --net dockernet --ip 172.18.0.21 -p 8087:8080 -e JAVA_OPTS="-Dspring.profiles.active=prod -Dspring.data.cassandra.username=XXX -Dspring.data.cassandra.password=XXX" markklim/taxi-drive-app:0.1

CASSANDRA CQLSH:
docker run -it --link cassandra:cassandra --rm cassandra cqlsh cassandra <some commands for example auth -u cassandra -p cassandra>

### Prepare docker

docker run --restart always -d --name cassandra -p 9042:9042 cassandra
docker run --restart always -d --name postgres -p 5432:5432 postgres