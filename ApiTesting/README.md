# Practical Task - REST API Testing
## Test cases 1

1. Send the http request by using the GET method.
   * The URL is https://jsonplaceholder.typicode.com/users﻿
   * Validation: status code of the obtained response is 200 OK
   * Create a test to verify an http response header:

2. Send the http request by using the GET method.
    * The URL is https://jsonplaceholder.typicode.com/users﻿
    * Validation: - the content-type header exists in the obtained response;
    * the value of the content-type header is application/json; charset=utf-8

3. Create a test to verify an http response body:
   * Send the http request by using the GET method: The URL is https://jsonplaceholder.typicode.com/users﻿
   * Validation: the content of the response body is the array of 10 users
   
## Test cases 2

For this exercise, we will use the Automation Exercise API. The API documentation can be found at: automationexercise.com/api_list

The following 3 scenarios need to be automated:

1. User Registration and Validation Flow

   **User Registration (POST /createAccount)**
   - Create a user with complete data
   - Validation: 
   - Status code == 200
   - Response includes "responseCode": 201
   - Response includes "message": "User created!"

   **User Login Verification (POST /verifyLogin)**
   - Validation:
   - Status code == 200
   - Response includes "User exists!"

2. Product Search with Empty Name (Negative Test Case)

   **Product Search (POST /searchProduct)**
   - Search for a product with empty name ""
   - Validation:
   - Status code == 404
   - Response includes "responseCode": 404

3. Account Deletion Flow

   **User Registration (POST /createAccount)**
   - Create a user with complete data
   - Validation:
   - Status code == 200
   - Response includes "responseCode": 201
   - Response includes "message": "User created!"

   **Account Deletion (DELETE /deleteAccount)**
   - Validation:
   - Response includes "message": "Account deleted!"

   **User Login Verification (POST /verifyLogin)**
   - Validation:
   - Response includes "message": "User not found!"
   - Response includes "responseCode": 404

## Acceptance criteria
   * Tests should be created using either Rest Assured or Spring Rest Template.
   * Tests have to include validations that are given. 
   * Implemented tests should be readable. 
   * Tests must be implemented so that they could be launched in parallel. 
   * Naming and Code Conventions should be followed. 
   * As for tests of the bonus task, they should be created to test CRUD operations of the given resource.