1. Mockon tool was downloaded
2. Selected Impor/Export->Mockoons Format->Import from file (Json)
3. Selected provided JSON file
4. The server was started by clicking on start server button
5. Imported Project as maven project in Eclipse
6. The project can be run via Application.Java file
7. Visit to http://localhost:8080/graphiql in browser
8. In GraphiQL Editor testing has been done.

a) It will provide vehicle info with id 1

{
  vehicle(id: 1) {
    name
    brand
    msidn
    countryOfOperation
    chassisNumber
    cassisSeries
    fleet
    engineStatus
  }
}

b) Then again Paste below string which will give vehicles which name matches partially or fully with "truck"

{
  vehicles(name: "truck") {
    name
    brand
    msidn
    countryOfOperation
    chassisNumber
    cassisSeries
    fleet
    engineStatus
  }
}


c) Finally Paste below string which will give vehicles which status is "ACTIVE"

{
  vehiclesByStatus(status: "ACTIVE") {
    name
    brand
    msidn
    countryOfOperation
    chassisNumber
    cassisSeries
    fleet
    engineStatus
  }
}


