# Taxi drive app

Travis
Test Travis stage for new repo
<pre>
{
	"clientLogin":"89147217660",
	"firstName":"Mark",
	"lastName":"Klimenko",
	"ridesAmount": "5"
}

{
	"clientLogin":"+79147654321",
	"fromAddress":{
		"country":"Russia",
		"state":"Primorskiy",
		"city":"Vladivostok",
		"street":"Svetlanskaya",
		"building":"64"
	},
	"toAddress":{
		"country":"Russia",
		"state":"Primorskiy",
		"city":"Vladivostok",
		"street":"Lugovaya",
		"building":"74/8"
	},
	"dateIn": "2017-11-13T12:45:30",
	"carId": 5,
	"menInCar": 2
}
</pre>

- "JAVA_OPTS=-Dmessaging.user=admin





docker run --restart always -d --name tda --net dockernet --ip 172.18.0.21 -p 8087:8080 -e JAVA_OPTS="-Dspring.profiles.active=prod -Dspring.data.cassandra.contact-points=172.18.0.22" markklim/taxi-drive-app:0.1
docker run -d --name tda --net dockernet --ip 172.18.0.21 -p 8087:8080 -e JAVA_OPTS="-Dspring.profiles.active=prod -Dspring.data.cassandra.contact-points=172.18.0.22" markklim/taxi-drive-app:0.1

docker run --restart always -d --name tda --net dockernet --ip 172.18.0.21 -p 8087:8080 -e JAVA_OPTS="-Dspring.profiles.active=prod" markklim/taxi-drive-app:0.1

CASSANDRA CQLSH:
docker run -it --link cassandra:cassandra --rm cassandra cqlsh cassandra <some commands for example auth -u cassandra -p cassandra>


gradlew migratorDropKeyspace -PmigratorUser=cassandra -PmigratorPassword=cassandra 
gradlew migratorExecute -PmigratorUser=cassandra -PmigratorPassword=cassandra -PmigratorScript=db/src/main/resources/scripts/migrations
gradlew migratorExecute -PmigratorUser=cassandra -PmigratorPassword=cassandra -PmigratorScript=db/src/main/resources/scripts/dictionaries
