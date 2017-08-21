# springboot-redis
Create a springboot sample application using two level caches
local: caffeine
distributed: redis

### Run and build
Step-1 : 
./gradlew clean build

Step-2 :
java -jar build/libs/sample-0.0.1-SNAPSHOT.jar

Step-3:
curl http://localhost:{server_port}/api/condition
