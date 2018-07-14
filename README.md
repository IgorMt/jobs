# Search Jobs for Worker

This is a simple matching engine that presents Workers with appropriate Jobs.  
Swipejobs provide a REST API for this task – /workers, which provides the list of all available 
Workers and the list of all available /jobs. This application uses the 
REST API to find Job matches which correspond to the selected workerId (userId) and returns the 
maximum of three appropriate Jobs for that Worker. 

The Project is based on a Spring BOOT framework.

## Prerequisites

    - Java version: 1.8.0_162-b12
    - Maven version: Apache Maven 3.5.3
    - IntelliJ Idea

Tech Requirements:
JDK and Maven of the above specified versions (or similar).
The Application gets compiled and started with the Maven commands.

Swipejobs provide the REST API:
http://test.swipejobs.com/api/workers
http://test.swipejobs.com/api/jobs

## Getting Started

To compile the Application in \jobs, run: '**mvn clean install**' (compiled in project)
To start the Application (with compilation), run the Spring BOOT server in ..\jobs in the Console:
'**mvn spring-boot:run**' or **..\jobs\target>java -jar jobs-0.0.1-SNAPSHOT.jar**.

Then go to **http://localhost:8080/jobs?userid=12** for simple check of requests.

## Constraints:

- Jobs are selected with consideration of the requirements, i.e. Drive Licence, 
Certificates, Job Title matching Worker skills and the calculated distance to Job. 
Other values are not checked, as there is no logical connection between Job and Worker 
parameters. 
- All the strings in the application are matched one-to-one, without using similarity 
algorithms, such as 'Levenshtein distance algorithm' or others.
- All values and collections in the Workers and Jobs webservices are considered as mandatory, 
no absent or empty values are allowed.
- Distance parameter is in kilometers only.

## Running the tests

To run the tests only, run '**mvn test**'in \jobs folder 
The JUnit tests cover the following basic functions:
- mock RestController call test
- private methods in Service test
- Util distance method test

## Author

I. Maltsev
03/07/2018
