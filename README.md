# NACE
Nomenclature of Economic Activities
Swagger Documentation
http://localhost:8080/swagger-ui/index.html
http://localhost:8080/v2/api-docs
Database:
http://localhost:8080/h2-console

POST: http://localhost/nace/add
{
    "id": 399477,
    "level": "2",
    "code": "A",
    "parent": null,
    "description": "Hello",
    "thisItemIncludes": "This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.",
    "thisItemAlsoIncludes": null,
    "rulings": null,
    "thisItemExcludes": null,
    "referenceToISIC4": "A"
}

GET: http://localhost/nace/1
{
    "id": 1,
    "level": "2",
    "code": "A",
    "parent": null,
    "description": "Hello",
    "thisItemIncludes": "This section includes the exploitation of vegetal and animal natural resources, comprising the activities of growing of crops, raising and breeding of animals, harvesting of timber and other plants, animals or animal products from a farm or their natural habitats.",
    "thisItemAlsoIncludes": null,
    "rulings": null,
    "thisItemExcludes": null,
    "referenceToISIC4": "A"
}

