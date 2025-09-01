# Budget Tracker API

A Java Spring Boot REST API for tracking expenses and monthly budgets.

## Features
- Expenses: CRUD + total spending
- Budgets: CRUD + monthly overview + usage for specific category/month + total budget
- Overview: All categories for a month with spent & remaining budget
- Input validation & error handling
- Auto API docs via Swagger

## Built with
- Java
- Spring Boot
- JPA
- H2
- REST

## How To Run
1. Clone this repository
```
git clone https://github.com/Boothu/budget-tracker-api.git
```
2. Navigate to directory
```
cd budget-tracker-api
```
3. Run the application
```
.\mvnw spring-boot:run
```

4. Access the API locally:

- API Root: http://localhost:8080

- Swagger UI: http://localhost:8080/swagger-ui.html (See all endpoints & example inputs)

## Key Endpoints
POST /expenses - add expense

GET /expenses - list expenses

GET /expenses/total - total spending

POST /budgets - add budget

GET /budgets - list budgets

GET /budgets/usage?category=Food&budgetMonth=2025-08 - budget usage for specific month/category

GET /budgets/overview?budgetMonth=2025-08 - monthly overview

## Sample JSON
Expense
```
{ "category": "Groceries", "description": "Supermarket", "amount": 42.30, "date": "2025-08-11" }
```
Budget
```
{ "category": "Groceries", "limitAmount": 250, "budgetMonth": "2025-08" }
```

## Testing with Postman
You can test the API using [Postman](https://www.postman.com/).

1. Run the API ([How To Run](#how-to-run))

2. In Postman, create requests to the endpoints above

3. Use the sample JSON bodies when testing POST requests

4. Check results via GET requests

## Preview
<img src="https://raw.githubusercontent.com/Boothu/boothu.github.io/main/images/BT%20PREVIEW%201.png" alt="Preview" width="650">
<img src="https://raw.githubusercontent.com/Boothu/boothu.github.io/main/images/BT%20PREVIEW%202.png" alt="Preview" width="650">
<img src="https://raw.githubusercontent.com/Boothu/boothu.github.io/main/images/BT%20PREVIEW%203.png" alt="Preview" width="650">
<img src="https://raw.githubusercontent.com/Boothu/boothu.github.io/main/images/BT%20PREVIEW%204.png" alt="Preview" width="650">

