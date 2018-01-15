# Call-Center-App
A Sample application for a Call Center.

# Index
1. [Design](#1-design)  
   1.1 [Classes](#11-classes)  
   1.2 [Collaboration](#12-collaboration)  
2. [Extras](#2-extras)  
   2.1 [No Operator is available to a call](#21-no-operator-is-available-to-a-call)  
   2.2 [More than 10 call at the same time](#22-more-than-10-call-at-the-same-time)  
3. [ How to run the app](#3-how-to-run-the-app)  
   3.1 [Running locallly](#31-running-locallly)  
   3.2 [Running with Docker to have Sonar analysis](#32-running-with-docker-to-have-sonar-analysis)
4. [How to test it](#4-how-to-test-it)

---

# 1. Design
Base on the requirements of this application, a proposal desing has been formulated. Please find down below a desing diagrams for the appplication.

### 1.1 Classes

![Alt text](docs/Classes-Diagram.png?raw=true)

### 1.2 Collaboration

![Alt text](docs/Collaboration-Diagram.png?raw=true)

---

# 2. Extras

There are two questions about what happens with the calls. These are the solutions:

### 2.1 No Operator is available to a call.

`Q`: What happned in the application where is no operator free to attend the call?  
`A`: The application uses an implemnetation of a Queue, ConcurrentLinkedQueue, so all calls that came in into the system are put in there waiting for an operator to be availabe to attend the call. Calls never get lost if there is no operator free.

### 2.2 More than 10 call at the same time.

`Q`: What happend to a call when there is more that 10 consurrent calls?  
`A`: Same as before. Because of the implementation of a Queue, calls are just `queue` until an operator is available to attend the call.

---

# 3. How to run the app

### 3.1 Running locallly

1. Clone repository to a local folder in your machine.  
   `git clone https://github.com/davidokun/Call-Center-App.git`
   
2. `cd` into `Call-Center-App` folder and compile the project with:  
   `mvn clean install`
   
   It should return a compilation success.
   ```bash
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time: 28.529 s
   [INFO] Finished at: 2018-01-15T11:48:07-05:00
   [INFO] Final Memory: 29M/74M
   [INFO] ------------------------------------------------------------------------
   ```

3. Then run the application with:  
   `mvn spring-boot:run`
   
### 3.2 Running with Docker to have Sonar analysis

1. Clone repository to a local folder in your machine.  
   `git clone https://github.com/davidokun/Call-Center-App.git`
   
2. `cd` into `Call-Center-App` folder and compile the project with:  
   `mvn clean install`
   
   It should return a compilation success.
   ```bash
   [INFO] ------------------------------------------------------------------------
   [INFO] BUILD SUCCESS
   [INFO] ------------------------------------------------------------------------
   [INFO] Total time: 28.529 s
   [INFO] Finished at: 2018-01-15T11:48:07-05:00
   [INFO] Final Memory: 29M/74M
   [INFO] ------------------------------------------------------------------------
   ```
   
3. Now, `cd` into `docker` folder and run:  
   `docker-compose up`
   
4. It will set up application on port 8080 and Sonar on port 9000.

5. To create the sonar analysis just run `mavn sonar:sonar`.

6. Access to Sonar can be done in `http://localhost:9000` wiht user `admin` and password `admin`.


# 4. How to test it
   
1. After running the project, check the console for a message like:  
   `2018-01-15 11:50:05 [pool-1-thread-1] INFO c.a.c.s.impl.CallDispatcherImpl - Calls in Queue: 0`
   
2. Send a request to the app. Could be done int 2 ways. Use a client app like Postman or use Curl command with files atteched in the project.

3. A sample request is like this:

   ```json
   {
     "subject" : "Subject 1",
     "user": {
       "name" : "Aaron",
       "phoneNumber" : 55123
     }
   }
   ```

4. Using `curl`. `cd` into `files` folder and either run:
   
`curl --data @1-call.json -H "Content-Type: application/json" -X POST http://localhost:8080/call` for 1 call.  
or  
`curl --data @12-calls.json -H "Content-Type: application/json" -X POST http://localhost:8080/call/bulk`, for 12 call concurrently.

3. Check either console or log folder to the the result of the executions.

5. Have fun!
