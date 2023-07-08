Feature: Validating add place API

@AddPlace
Scenario Outline:
Verify if place is added successfully using AddPlaceAPI
Given Add place payload with "<name>" "<language>" "<address>"
When User calls "addPlaceAPI" with "POST" request
Then The API call got success with status code 200
And The "status" in response body is "OK"
And The "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "getPlaceAPI"

Examples:
|name        |language|address|
|Sagar House |Odia    |BBSR   |
#|Sankar House|English |BLR    |
#|Liku        |Hindi   |Chennai|

@DeletePlace
Scenario:
Verify if delete place functionality is working

Given DeletePlace API payload
When User calls "deletePlaceAPI" with "POST" request
Then The API call got success with status code 200
And The "status" in response body is "OK"


