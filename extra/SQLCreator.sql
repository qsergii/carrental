create table cars_brands
(
    id   int unsigned auto_increment
        primary key,
    name varchar(100) not null,
    constraint cars_brands_id_uindex
        unique (id),
    constraint cars_brands_name_uindex
        unique (name)
);

create table cars
(
    id            int unsigned auto_increment
        primary key,
    name          varchar(100)         not null,
    description   varchar(200)         null,
    blocked       tinyint(1) default 0 not null,
    price         float                not null,
    quality_class int                  not null,
    brand_id      int unsigned         not null,
    constraint cars_id_uindex
        unique (id),
    constraint brand_id_fk
        foreign key (brand_id) references brands (id)
);

create table users
(
    id        int unsigned not null
        primary key,
    login     varchar(50)  not null,
    person_id int unsigned not null,
    role      int          not null,
    blocked   tinyint(1)   not null,
    constraint id_UNIQUE
        unique (id),
    constraint login_UNIQUE
        unique (login)
);

create table orders
(
    id              int unsigned auto_increment
        primary key,
    user_id         int unsigned not null,
    with_driver     tinyint(1)   null,
    lease_term      int unsigned not null,
    passport_number varchar(50)  not null,
    passport_valid  date         not null,
    car_id          int unsigned not null,
    price           float        not null,
    constraint orders_id_uindex
        unique (id),
    constraint car_id_fk
        foreign key (car_id) references cars (id),
    constraint orders_users_id_fk
        foreign key (user_id) references users (id)
);

create table invoices
(
    id       int unsigned auto_increment
        primary key,
    order_id int unsigned null,
    type     int unsigned not null,
    price    float        null,
    payed    tinyint(1)   null,
    date     date         not null,
    constraint invoices_id_uindex
        unique (id),
    constraint order_id
        foreign key (order_id) references orders (id)
);

create index users_persons_id_fk
    on users (person_id);


