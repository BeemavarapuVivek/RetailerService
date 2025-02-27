# RetailerService

Retailer Service Api Collections
1.URL: http://localhost:8080/api/retailer/save/transaction
Method:POST
Request:
{
    "cust_email": "customer3@gmail.com",
    "tran_amount": "80.0",
    "creation_date": "11-02-2025"
}


2.URL: http://localhost:8080/api/retailer/all/rewards
Method:GET
Response:
{
    "customer1@gmail.com": {
        "FEBRUARY": 210
    },
    "customer3@gmail.com": {
        "FEBRUARY": 30
    }
}