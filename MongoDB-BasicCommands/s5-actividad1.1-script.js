// mongo localhost:27017/test actividad-script.js

db = db.getSiblingDB("StoreDb");
db.createCollection("Sales")
db.createCollection("HumanResources")
db.createCollection("Promotions")
db.HumanResources.insert({
    "Code":  "123",
    "Name":  "Lindsay Briggs",
    "Position":  "Sales Manager"
})
db.HumanResources.insert({
    "Code":  "456",
    "Name":  "Frankie Rowe",
    "Position":  "Sales Representative"
})
db.Promotions.insert({
    "Code":  "12",
    "Name":  "Jeans 2x1",
    "Department":  "Men"
})
db.Promotions.insert({
    "Code":  "34",
    "Name":  "Running Tennis 3x2",
    "Department":  "Women"
})
print(db.getCollectionNames());
var cursor = db.HumanResources.find();
while(cursor.hasNext()) {
    printjsononeline(cursor.next());
}
var cursor = db.Promotions.find();
while(cursor.hasNext()) {
    printjsononeline(cursor.next());
}
db.HumanResources.remove(db.HumanResources.findOne());
db.HumanResources.drop();
db.Promotions.drop();

db.Sales.insert({
    "Date":  "2016-02-13",
    "Customer":  "Josephine Parks",
    "Clerk":  1,
    "TicketID":  1,
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  1,
                         "Product":  "Jean"
                     }
                 ]
})
db.Sales.insert({
    "Date":  "2016-02-13",
    "Customer":  "Latoya Beck",
    "Clerk":  1,
    "TicketID":  2,
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  2,
                         "Product":  "Jean"
                     },
                     {
                         "Price":  150.5,
                         "Quantity":  1,
                         "Product":  "Tennis"
                     }
                 ]
})
db.Sales.insert({
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  2,
                         "Product":  "Jean Slim"
                     },
                     {
                         "Price":  100,
                         "Quantity":  1,
                         "Product":  "Jean"
                     },
                     {
                         "Price":  20,
                         "Quantity":  1,
                         "Product":  "T-Shirt"
                     }
                 ],
    "Promotion":  "Jeans 2x1",
    "Clerk":  2,
    "Customer":  "Ricardo Valdez",
    "Date":  "2016-02-13",
    "TicketID":  3
})
db.Sales.insert({
    "Date":  "2016-02-14",
    "Customer":  "Derek Pittman",
    "Clerk":  1,
    "TicketID":  4,
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  1,
                         "Product":  "Jean"
                     }
                 ]
})
db.Sales.insert({
    "Date":  "2016-02-14",
    "Customer":  "Frankie Hayes",
    "Clerk":  1,
    "TicketID":  5,
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  2,
                         "Product":  "Jean"
                     },
                     {
                         "Price":  150.5,
                         "Quantity":  1,
                         "Product":  "Tennis"
                     }
                 ]
})
db.Sales.insert({
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  2,
                         "Product":  "Jean Slim"
                     },
                     {
                         "Price":  100,
                         "Quantity":  1,
                         "Product":  "Jean"
                     },
                     {
                         "Price":  20,
                         "Quantity":  1,
                         "Product":  "T-Shirt"
                     }
                 ],
    "Promotion":  "Running Tennis 3x2",
    "Clerk":  2,
    "Customer":  "Sergio Meyer",
    "Date":  "2016-02-14",
    "TicketID":  6
})
db.Sales.insert({
    "Date":  "2016-02-15",
    "Customer":  "Ivan Mills",
    "Clerk":  1,
    "TicketID":  7,
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  1,
                         "Product":  "Jean"
                     },
                     {
                         "Price":  8000,
                         "Quantity":  1,
                         "Product":  "Cell Phone"
                     },
                     {
                         "Price":  300,
                         "Quantity":  1,
                         "Product":  "Purse"
                     },
                     {
                         "Price":  100,
                         "Quantity":  1,
                         "Product":  "Shoes"
                     }
                 ]
})
db.Sales.insert({
    "Date":  "2016-02-15",
    "Customer":  "Marianne Williams",
    "Clerk":  1,
    "TicketID":  8,
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  2,
                         "Product":  "Jean"
                     },
                     {
                         "Price":  150.5,
                         "Quantity":  1,
                         "Product":  "Tennis"
                     }
                 ]
})
db.Sales.insert({
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  2,
                         "Product":  "Jean Slim"
                     },
                     {
                         "Price":  100,
                         "Quantity":  1,
                         "Product":  "Jean"
                     },
                     {
                         "Price":  20,
                         "Quantity":  1,
                         "Product":  "T-Shirt"
                     }
                 ],
    "Promotion":  "Jeans 2x1",
    "Clerk":  2,
    "Customer":  "Maureen Gilbert",
    "Date":  "2016-02-16",
    "TicketID":  9
})
db.Sales.insert({
    "Products":  [
                     {
                         "Price":  100,
                         "Quantity":  2,
                         "Product":  "Jean Slim"
                     },
                     {
                         "Price":  100,
                         "Quantity":  1,
                         "Product":  "Jean"
                     },
                     {
                         "Price":  20,
                         "Quantity":  1,
                         "Product":  "T-Shirt"
                     }
                 ],
    "Promotion":  "Running Tennis 3x2",
    "Clerk":  2,
    "Customer":  "Marlene Edwards",
    "Date":  "2016-02-17",
    "TicketID":  10
})
var cursor = db.Sales.find();
while(cursor.hasNext()) {
    printjsononeline(cursor.next());
}

// EOF
