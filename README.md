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
Method:POST
Response: 
[
    {
        "id": 3,
        "customerEmail": "customer1@gmail.com",
        "transactionAmount": 150.0,
        "creationDate": "2025-01-02"
    }
]

2.URL: http://localhost:8080/api/retailer/all/rewards
Method:GET
{
    "customer1@gmail.com": {
        "JANUARY": 150
    }
}