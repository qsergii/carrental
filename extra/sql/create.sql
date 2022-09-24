drop table IF EXISTS invoices;
drop table IF EXISTS orders;
drop table IF EXISTS cars;
drop table IF EXISTS brands;
drop table IF EXISTS qualities;
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

create table qualities
(
    id   int auto_increment primary key,
    name varchar(100) not null,
    constraint id_unique
        unique (id)
);

create table cars
(
    id              int auto_increment primary key,
    name            varchar(100)         not null,
    description     varchar(200)         null,
    blocked         tinyint(1) default 0 not null,
    price           float                not null,
    quality_id      int                  not null,
    brand_id        int                  not null,
    image_file_name varchar(100)         null,
    constraint cars_id_uindex
        unique (id),
    constraint brand_id_fk
        foreign key (brand_id) references brands (id),
    constraint quality_id_fk
        foreign key (quality_id) references qualities (id)
);

create table users
(
    id              int          not null primary key auto_increment,
    login           varchar(50)  not null,
    phone           varchar(20)  null,
    email           varchar(100) null,
    first_name      varchar(100) null,
    last_name       varchar(100) null,
    password        varchar(64)  not null DEFAULT '',
    role            int          not null DEFAULT 2,
    blocked         tinyint(1)   not null DEFAULT 0,
    passport_number varchar(50)  null,
    passport_valid  date         null,
    constraint id_UNIQUE
        unique (id),
    constraint login_UNIQUE
        unique (login)
);

create table orders
(
    id              int auto_increment primary key,
    date            date default (CURRENT_DATE) not null,
    lease_begin     date                        not null,
    lease_finish    date                        not null,
    user_id         int                         not null,
    with_driver     tinyint(1)                  not null,
    lease_term      int                         not null,
    passport_number varchar(50)                 not null,
    passport_valid  date                        not null,
    car_id          int                         not null,
    price           float                       not null,
    rejected        tinyint(1)                  not null,
    reject_reason   varchar(100)                null,
    return_date     datetime                    null,
    return_damage   varchar(100)                null,
    constraint orders_id_uindex
        unique (id),
    constraint car_id_fk
        foreign key (car_id) references cars (id),
    constraint orders_users_id_fk
        foreign key (user_id) references users (id)
);

create table invoices
(
    id         int auto_increment primary key,
    date       date       not null,
    order_id   int        null,
    user_id    int        not null,
    type       int        not null,
    amount     float      null,
    payed      tinyint(1) null,
    payed_date date       null,

    constraint invoices_id_uindex
        unique (id),
    constraint order_id
        foreign key (order_id) references orders (id),
    foreign key (user_id) references users (id)
);