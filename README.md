# RetailerService

Retailer Service Api Collections
1.URL: http://localhost:8080/api/retailer/save/transaction
Method:POST
Request:
{
    "customerEmail": "customer1@gmail.com",
    "transactionAmount": "150.0",
    "creationDate": "2025-01-02"
}

2.http://localhost:8080/api/retailer/all/transactions
Method:GET
Response: 
[
    {
        "id": 1,
        "customerEmail": "customer1@gmail.com",
        "transactionAmount": 150.0,
        "creationDate": "2025-01-02"
    },
    {
        "id": 2,
        "customerEmail": "customer1@gmail.com",
        "transactionAmount": 250.0,
        "creationDate": "2024-01-02"
    },
    {
        "id": 3,
        "customerEmail": "customer2@gmail.com",
        "transactionAmount": 250.0,
        "creationDate": "2025-09-02"
    }
]

2.URL: http://localhost:8080/api/retailer/all/rewards
Method:GET
Response:
{
    "customer1@gmail.com": {
        "JANUARY": 150
    },
    "customer2@gmail.com": {
        "SEPTEMBER": 350
    }
}