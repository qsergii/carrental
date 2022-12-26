# Car rental

## Task

There is a list of Cars in the system,for which it is necessary to implement:

- choice by brand;
- choice according to quality class;
- sort by rental price;
- sort by name.

The customer registers in the system, chooses a car and makes a rental order. An unregistered customer cannot place an
order. In the order data the client indicates passport data, option 'with driver' / 'without driver', lease term. The
system generates an Invoice, which the client pays.

The manager reviews the order and may reject it, giving a reason. The manager also registers the return of the car, in
case of car damage he issues an invoice for repairs through the system.

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
- Factory - com.epam.carrental.export.Export

Architectural patterns:

- MVC
- PRG

# Deployment

-Tomcat - create resource to mysql database (for example in file context.xml) with name "jdbc/mysql.carrental"

- Deploy application
- Install MySQL server.
    - Create user and write username and password in file /META-INF/context.xml
    - assign all Object, DDL rights.
    - run scripts from file /extra/sql/create-tables.sql
    - for demo data run scripts from file /extra/sql/{table name}.sql for each table (if needed)
- Make registration as user, in table users change data in column 'role' to 0. This user become admin.
- Under admin get into Driver menu and set Driver price.
- Set environment variables for smtp server:

1. smtp_server
2. smtp_port
3. smtp_username
4. smtp_password
