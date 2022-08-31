drop table IF EXISTS invoices;
drop table IF EXISTS orders;
drop table IF EXISTS cars;
drop table IF EXISTS brands;
drop table IF EXISTS users;

create table brands
(
    id   int auto_increment primary key,
    name varchar(100) not null,
    constraint cars_brands_id_uindex
        unique (id),
    constraint cars_brands_name_uindex
        unique (name)
);

create table cars
(
    id            int auto_increment primary key,
    name          varchar(100)         not null,
    description   varchar(200)         null,
    blocked       tinyint(1) default 0 not null,
    price         float                not null,
    quality_class int                  not null,
    brand_id      int                  not null,
    constraint cars_id_uindex
        unique (id),
    constraint brand_id_fk
        foreign key (brand_id) references brands (id)
);

create table users
(
    id       int         not null primary key auto_increment,
    login    varchar(50) not null,
    password varchar(50) not null DEFAULT '',
    role     int         not null DEFAULT 2,
    blocked  tinyint(1)  not null DEFAULT 0,
    constraint id_UNIQUE
        unique (id),
    constraint login_UNIQUE
        unique (login)
);

create table orders
(
    id              int auto_increment primary key,
    user_id         int         not null,
    with_driver     tinyint(1)  null,
    lease_term      int         not null,
    passport_number varchar(50) not null,
    passport_valid  date        not null,
    car_id          int         not null,
    price           float       not null,
    constraint orders_id_uindex
        unique (id),
    constraint car_id_fk
        foreign key (car_id) references cars (id),
    constraint orders_users_id_fk
        foreign key (user_id) references users (id)
);

create table invoices
(
    id       int auto_increment primary key,
    order_id int        null,
    type     int        not null,
    price    float      null,
    payed    tinyint(1) null,
    date     date       not null,
    constraint invoices_id_uindex
        unique (id),
    constraint order_id
        foreign key (order_id) references orders (id)
);