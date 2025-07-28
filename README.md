# Orders Management System

A Spring Boot application for managing product orders and sending notifications to users via Email and SMS. The system supports user account management, product catalog, order placement (simple and compound), and notification tracking.

## Features
- **Account Management**: Register, login, and manage user accounts with balance tracking.
- **Product Management**: Add, delete, and list products; count products per category.
- **Order Management**: Place simple and compound orders, validate orders, ship/cancel shipments, and cancel orders within a time window.
- **Notification System**: Generate and send notifications for order placement and shipment via Email and SMS, with a queue and sent history.
- **Statistics**: Retrieve most notified email/phone and most sent notification template.
- **In-Memory Storage**: Uses in-memory data structures for accounts, products, orders, and notifications.

## Project Structure
```
src/
  main/
    java/
      com/
        management/
          OrderNotificationAPI/
            controller/      # REST controllers for API endpoints
            model/           # Domain models (Account, Product, Order, Notification, etc.)
            repo/            # In-memory database classes
            service/         # Business logic and notification decorators
```

## API Endpoints

### Account
- `POST /api/account/register` — Register a new account.
- `POST /api/account/login` — Login with username and password.
- `GET /api/account/get` — Get all accounts.
- `GET /api/account/get/{username}` — Get account by username.

### Product
- `POST /api/product/add` — Add a product.
- `POST /api/product/addProducts` — Add multiple products.
- `DELETE /api/product/delete/{serialNumber}` — Delete a product.
- `GET /api/product/get` — List all products.
- `GET /api/product/get/{serialNumber}` — Get product by serial number.
- `GET /api/product/countPerCategory` — Count products per category.

### Order
- `GET /api/order/validate/simple` — Validate a simple order.
- `POST /api/order/place/simple` — Place a simple order.
- `POST /api/order/place/compound` — Place a compound order.
- `PUT /api/order/shipment/{ID}` — Ship an order.
- `DELETE /api/order/delete/{id}` — Cancel an order (within allowed time).
- `PUT /api/order/cancelShipment/{ID}` — Cancel a shipment (within allowed time).
- `GET /api/order/get/{ID}` — Get order by ID.
- `GET /api/order/get` — List all orders.

### Notification
- `GET /api/notification/generate/placement` — Generate order placement notification.
- `GET /api/notification/generate/shipment` — Generate order shipment notification.
- `POST /api/notification/send` — Send a notification.
- `GET /api/notification/get/mostNotifiedEmail` — Get most notified email.
- `GET /api/notification/get/mostNotifiedPhone` — Get most notified phone.
- `GET /api/notification/get/mostSentTemplate` — Get most sent template.
- `GET /api/notification/getFromQueue` — Get notifications in queue.
- `GET /api/notification/getSent` — Get sent notifications.

## Technologies Used
- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Scheduling
- In-memory data structures (no external DB)

## Getting Started
### Prerequisites
- Java 17+
- Maven

### Build and Run
```sh
./mvnw clean install
./mvnw spring-boot:run
```
The application will start on `http://localhost:8080`.

## Design Patterns Used
- **Decorator Pattern**: For notification sending channels (Email, SMS).
- **Composite Pattern**: For handling simple and compound orders.

## Authors
- [Fatma Elzahraa Serry](https://github.com/fatmaserry)
- [Rowan Madeeh](https://github.com/RowanMadeeh)
- [Shahd Ahmed](https://github.com/shahdahmmed)
- [Beshoy Hany](https://github.com/beshoy-hany74)
---