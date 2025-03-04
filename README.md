# Retailer Service API
This api provides endpoints for customer transactions and rewards



#Api Endpoints

## 1.Save Transaction
- **URI**: '/api/retailer/save/transaction'
- **Method**: 'POST'
- **Request Body**:
{
    "customerEmail": "customer1@gmail.com",
    "transactionAmount": "150.0",
    "creationDate": "2025-01-02"
}

## 2.Find All Transactions
- **URI**: '/api/retailer/all/transactions'
- **Method**: 'GET'
- **Response**: 
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

## 3.Find All Customer Rewards
- **URI**: 'api/retailer/all/rewards'
- **Method**: 'GET'
- **Response**:
{
    "customer1@gmail.com": {
        "JANUARY": 150
    },
    "customer2@gmail.com": {
        "SEPTEMBER": 350
    }
}

## 4.Get Customer Rewards by Email
- **URI**: '/api/retailer/rewards/customer2@gmail.com'
- **Method** 'GET':
- **Response**:
{
    "customer2@gmail.com": {
        "SEPTEMBER": 500
    }
}

## To Run the Application
```bash
./mvnw spring-boot:run
./mvnw clean install
./mvnw test