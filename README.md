# Taxi drive app

Travis
Test Travis stage for new repo
<pre>
{
	"personLogin":"89147217660",
	"firstName":"Mark",
	"lastName":"Klimenko",
	"dateOfBirth": "1994-05-26",
	"ridesAmount": "5"
}


{
	"personLogin":"89147217660",
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
	"time": "1994-05-26T12:45:30",
	"carId": 5,
	"menInCar": 2
}
</pre>

- "JAVA_OPTS=-Dmessaging.user=admin

docker run --restart always -d --name tda -p 8087:8080 -e JAVA_OPTS="-Dspring.profiles.active=prod" markklim/taxi-drive-app:0.1