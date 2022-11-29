# rb-view-reciept-service


View Receipt Service is used to view receipt based of studentId or txnReferenceId (receipt no)
### API:
* GET - [/rakbank/api/receipt/student/{studentId}](/rakbank/api/receipt/student/{studentId}) (VIEW RECEIPT BY STUDENT_ID - check swagger for more details)
* GET - [/rakbank/api/receipt/{referrenceId}](/rakbank/api/receipt/{referrenceId}) (VIEW RECEIPT BY REFERENCE_ID - check swagger for more details)

#### Useful Links
* Local Swagger URL: [http://localhost:8084/rakbank/api/view-receipt/swagger-ui.html](http://localhost:8084/rakbank/api/view-receipt/swagger-ui.html)
* Swagger File: [https://github.com/getimran/rb-student-fee-collection/blob/master/swagger/rb-view-reciept-service-swagger.json](https://github.com/getimran/rb-student-fee-collection/blob/master/swagger/rb-view-reciept-service-swagger.json)
* Postman Collection: [https://github.com/getimran/rb-student-fee-collection/blob/master/postman/3-rb-view-receipt-service.postman_collection.json](https://github.com/getimran/rb-student-fee-collection/blob/master/postman/3-rb-view-receipt-service.postman_collection.json)

### Tech Stack
* Java 8
* Spring Boot
* Resilience4j

### Installation
**Run locally Using Intellij:**
```
git clone https://github.com/getimran/rb-view-reciept-service.git
git checkout develop
gradle clean build
gradle bootRun
```