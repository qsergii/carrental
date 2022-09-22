# Car rental
## Task 
There is a list of Cars in the system,for which it is necessary to implement:
- choice by brand;
- choice according to quality class;
- sort by rental price;
- sort by name.

The customer registers in the system, chooses a car and makes a rental order. An unregistered customer cannot place an order. In the order data the client indicates passport data, option 'with driver' / 'without driver', lease term. The system generates an Invoice, which the client pays.

The manager reviews the order and may reject it, giving a reason. The manager also registers the return of the car, in case of car damage he issues an invoice for repairs through the system.

The system administrator has the rights:
- adding, deleting cars, editing car information;
- blocking / unblocking- the user;
- registration of managers in the system.

## Implementation

Design patterns:
- Builder - com.epam.carrental.dao.mysql.MysqlCarDAO
- Singleton - com.epam.carrental.dao.Database
- AbstractFactory - com.epam.carrental.dao.DAOFactory
- FrontController - com.epam.carrental.controllers.FrontController

Architectural patterns:
- MVC
- PRG