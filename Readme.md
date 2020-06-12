## How to build
mvn clean install on root directory

## How to run


1. execute docker-compose up --build
this will build and bring up a docker container

2. in a seperate command shell execute

docker exec -it ah_access-holding-app_1 /bin/bash

ah_access-holding-app_1 in above refers to image name - it may have different name in certain circumstances, in which case you may have to execute docker ps to see container names

3. Example csv input is available in root folder

copy csv file sample_input.xml to data folder

cp sample_input.csv data/test1.csv

4. outputs related to countries will be generated in out folder

execute the below commands to view contents of output files

* cd out
* ls -ltr
* cat UK.xml
