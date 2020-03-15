**Application for matching technicians (e.g. electricians, plumbers etc)
with clients.**

**Back-end:** Java EE 8, Java SE 11

**Front-end:** bootstrap 4, JavaScript

**To run the application:**
1. Download the .tar folder (my-mysql.tar) with docker image of mySQL database.
2. Load the image to local docker repository (docker load < my-mysql.tar)
2. Start the container with the database image (docker run -it -e MYSQL_ROOT_PASSWORD=password -p 3306:3306 -d my-mysql)
3. Deploy fachmann.war on WildFly server in standalone mode.


**Features to be added (work in progress):** 
* Uploading a profile picture of the technician once he signs up.
* Fixing bugs in the messaging module.
* Rating system.