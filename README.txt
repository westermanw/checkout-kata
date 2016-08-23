***BUILD AND RUN INSTRUCTIONS FOR KATA REST API***

BUILD:

1.change directory to the top level directory where the pom is
2.Use maven to build the jar: 'mvn clean install'

RUNNING THE REST API:

1.cd into the target directory
2.From a terminal run java -jar kata-1.0-SNAPSHOT.jar
3.Providing that port 8080 is not being used the application will now be available on http://localhost:8080/
4.From postman or your favourite request builder you can now perform the following http requests:
NOTE: Please remember to add the JSON header to the POST requests (Content-Type application/json)

Validation to take note of:
1. Only capital A-Z are allowed as SKUs
2. Negative prices are not allowed, however 0 is allowed just in case the store is feeling generous
3. Multibuy offers require numberOfUnits to be 2 or greater.
4. Any offer or item with the same SKU will overwrite any existing offer or item respectively.
5. You can't ad ann SKU to your basket if it doesn't exist in the store.

*************************ITEMS*********************************:

POST http://localhost:8080/item
example json body required:
{
    "stockKeepingUnit":"D",
    "priceInPence":30
}
p.s. there is a bit of validation, only capitals (A-Z) are allowed for SKUs

DELETE http://localhost:8080/item
This will delete all items registered in the store
****************************************************************

************************OFFERS*********************************:

POST http://localhost:8080/offer
example json body required:
{
    "itemStockKeepingUnit":"C",
    "numberOfUnits":3,
    "priceInPence":500
}

DELETE http://localhost:8080/offer
This will delete all offers registered in the store
*****************************************************************

************************BASKET*********************************:

POST http://localhost:8080/basket/item
example json body required:
{
    "stockKeepingUnit":"Z"
}

GET http://localhost:8080/basket/total
This will return the total value of the basket in pence

DELETE http://localhost:8080/basket
This will delete all items currently in the basket
*****************************************************************

