# brands
INSERT INTO carrental.brands (id, name)
VALUES (5, 'Audi');
INSERT INTO carrental.brands (id, name)
VALUES (40, 'Chevrolet');
INSERT INTO carrental.brands (id, name)
VALUES (1, 'Dodge');
INSERT INTO carrental.brands (id, name)
VALUES (42, 'Ford');
INSERT INTO carrental.brands (id, name)
VALUES (3, 'Hunday');
INSERT INTO carrental.brands (id, name)
VALUES (41, 'Intermediate');
INSERT INTO carrental.brands (id, name)
VALUES (39, 'Jeep');
INSERT INTO carrental.brands (id, name)
VALUES (2, 'Mazda');
INSERT INTO carrental.brands (id, name)
VALUES (37, 'Nissan');
INSERT INTO carrental.brands (id, name)
VALUES (17, 'Tesla');
INSERT INTO carrental.brands (id, name)
VALUES (38, 'Toyota');
INSERT INTO carrental.brands (id, name)
VALUES (16, 'АвтоЗАЗ');

# qualities
INSERT INTO carrental.qualities (id, name)
VALUES (2, '1st class');
INSERT INTO carrental.qualities (id, name)
VALUES (3, 'Regular car');
INSERT INTO carrental.qualities (id, name)
VALUES (1, 'VIP');

# cars
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (1, 'Rav4', '', 0, 5.9, 1, 38, '0f3fb6a0-7554-48bf-bbb6-ba4689ee8f73.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (2, 'Equinox', 'From USA for your use', 0, 47.4, 2, 40, '773325c1-e84a-46bd-a1d2-a29bfc8c0518.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (3, 'SUV car', 'Get in and get there', 0, 54.3, 2, 41, 'e7f51c30-0044-4875-b4fd-1d83e345daaf.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (7, 'Rogue', 'Just for precise costumer', 0, 49.5, 1, 37, '41ed2461-b53d-4864-b440-ac67c9bfd497.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (11, 'Ford Escape', 'For nature travelers', 0, 59.4, 2, 42, '8665423d-0b3c-4cd4-97cc-ce0b1382541d.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (22, 'Део', 'Ваш багаж завжди перед вами', 0, 0.9, 3, 16, '80b33d3d-622a-40a6-89b8-228815cb003e.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (23, 'CX-5', '', 0, 59.5, 2, 2, 'dd15d9cf-c299-42f0-b83b-638f7663938b.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (43, '3', '3', 0, 3, 2, 5, '53b3a9d4-9f71-490b-b7b6-21e764d41448.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (44, 'Ланос', 'Кар на кожен день', 0, 20.99, 2, 16, 'be633751-b57a-4577-8be3-cdd1f1a6f4fe.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (45, 'Versa', '', 0, 23.5, 3, 37, 'c189f93a-4271-4848-a605-849fdf6ffeea.png');
INSERT INTO carrental.cars (id, name, description, blocked, price, quality_id, brand_id, image_file_name)
VALUES (46, 'Compas', '', 0, 49.5, 1, 39, '8f5f62d8-1ccf-4140-a9a5-d84a57b61738.png');

# users
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (26, 'Admin 1', '+380505005050', 'max@carrental.com', 'Максим', 'Адміненко', '2022-12-31',
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 0, 0, 'AA123456');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (29, 'Manager 2', null, null, null, null, '0000-00-00',
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 1, 0, '');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (30, 'Manager 3', null, null, 'Андрій', 'Іваненко', null,
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 1, 1, '');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (31, 'Client Donald', null, null, 'Super', 'Hero', null,
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 2, 0, '');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (35, 'Client 2', null, null, null, null, '0000-00-00',
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 2, 0, '');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (36, 'Client 3', null, null, null, null, '0000-00-00',
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 2, 0, '');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (37, 'Client 1', '+15005005050', 'poltus@president.gov', 'Барак', 'Обама', '2022-10-27',
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 2, 0, 'AB123456');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (38, 'Manager 1', '+380505005050', 'manager@carrental.com', 'Max', 'Managerenko', '0000-00-00',
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 1, 0, '');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (39, 'Client 4', null, null, null, null, '2022-10-26',
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 2, 0, 'AA123456');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (53, 'Makdak', '3805050050050', 'qsergey@gmail.com', 'Mak', 'Dak', '2022-09-30',
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 2, 0, 'AB123456');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (54, 'Client 8', '380505005050', 'qsergey@gmail.com', 'Sergey', 'Sergii', '2022-09-30',
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 2, 0, 'AB123456');
INSERT INTO carrental.users (id, login, phone, email, first_name, last_name, passport_valid, password, role, blocked,
                             passport_number)
VALUES (56, 'Client 5', '380505005050', 'qsergey@gmail.com', 'First', 'Last', null,
        '6b86b273ff34fce19d6b804eff5a3f5747ada4eaa22f1d49c01e52ddb7875b4b', 2, 0, null);

# orders
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (1, '2022-09-21', '0000-00-00', '0000-00-00', 6, '2022-09-07', 1, 5.9, 1, 'Поганий клієнт', null, null, 26, 1,
        '6');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (2, '2022-09-21', '0000-00-00', '0000-00-00', 30, '2022-09-08', 1, 5.9, 0, '', null, null, 26, 1, '56');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (3, '2022-09-21', '0000-00-00', '0000-00-00', 2, '2022-09-07', 1, 5.9, 0, '', null, null, 26, 1, '435');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (4, '2022-09-21', '0000-00-00', '0000-00-00', 1, '2022-09-09', 2, 20.99, 1, 'Bad', null, null, 26, 1, '56');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (5, '2022-09-21', '0000-00-00', '0000-00-00', 1, '2022-09-10', 1, 5.9, 1, '56', null, null, 37, 1, '1');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (6, '2022-09-21', '0000-00-00', '0000-00-00', 5, '2022-09-15', 1, 5.9, 0, '', null, null, 37, 1, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (7, '2022-09-21', '0000-00-00', '0000-00-00', 6, '2022-09-13', 1, 5.9, 0, '', null, null, 37, 1, '6');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (8, '2022-09-21', '0000-00-00', '0000-00-00', 1, '2022-09-15', 1, 5.9, 0, '', null, null, 37, 1, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (9, '2022-09-21', '0000-00-00', '0000-00-00', 1, '2022-09-05', 1, 5.9, 0, '', null, null, 37, 1, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (10, '2022-09-21', '0000-00-00', '0000-00-00', 5, '2022-09-12', 1, 5.9, 0, '', null, null, 37, 1, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (11, '2022-09-21', '0000-00-00', '0000-00-00', 5, '2022-09-09', 1, 5.9, 0, '', null, null, 37, 1, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (12, '2022-09-21', '0000-00-00', '0000-00-00', 5, '2022-09-09', 2, 20.99, 0, '', null, null, 37, 1, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (13, '2022-09-21', '0000-00-00', '0000-00-00', 5, '2022-09-12', 1, 5.9, 0, '', null, null, 37, 1, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (14, '2022-09-21', '0000-00-00', '0000-00-00', 5, '2022-09-12', 1, 5.9, 0, null, null, null, 38, 1, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (15, '2022-09-21', '0000-00-00', '0000-00-00', 6, '2022-09-05', 1, 5.9, 0, null, null, null, 38, 1, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (16, '2022-09-21', '0000-00-00', '0000-00-00', 2, '2022-09-06', 1, 5.9, 0, null, null, null, 38, 1, '2');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (17, '2022-09-21', '0000-00-00', '0000-00-00', 3, '2022-09-09', 11, 1, 0, null, null, null, 37, 1, '2');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (18, '2022-09-21', '0000-00-00', '0000-00-00', 3, '2022-09-09', 11, 1, 0, null, null, null, 37, 1, '2');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (19, '2022-09-21', '0000-00-00', '0000-00-00', 30, '2022-09-14', 2, 20.99, 0, null, null, null, 37, 0, '5');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (20, '2022-09-21', '0000-00-00', '0000-00-00', 3, '2022-09-30', 3, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (21, '2022-09-22', '2022-09-07', '2022-09-24', 3, '2022-09-30', 2, 20.99, 0, null, null, null, 37, 1,
        'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (22, '2022-09-23', '2022-09-23', '2022-09-29', 6, '2022-10-26', 1, 5.9, 0, null, null, null, 39, 0, 'AA123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (23, '2022-09-23', '2022-09-23', '2022-09-27', 4, '2022-12-31', 1, 5.9, 0, null, null, null, 26, 0, 'AA123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (24, '2022-09-26', '2022-09-26', '2022-09-27', 1, '2022-09-30', 1, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (25, '2022-09-26', '2022-09-26', '2022-09-27', 1, '2022-09-30', 1, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (26, '2022-09-26', '2022-09-26', '2022-09-27', 1, '2022-09-30', 1, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (27, '2022-09-26', '2022-09-26', '2022-09-27', 1, '2022-09-30', 2, 20.99, 0, null, null, null, 37, 1,
        'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (28, '2022-09-27', '2022-09-27', '2022-09-28', 1, '2022-09-30', 1, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (29, '2022-09-27', '2022-09-27', '2022-09-28', 1, '2022-09-30', 1, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (30, '2022-09-27', '2022-09-27', '2022-09-28', 1, '2022-09-30', 22, 0.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (31, '2022-09-27', '2022-09-27', '2022-09-28', 1, '2022-09-30', 22, 0.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (32, '2022-09-27', '2022-09-27', '2022-10-02', 5, '2022-09-30', 1, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (33, '2022-09-28', '2022-09-28', '2022-10-05', 7, '2022-10-27', 1, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (34, '2022-09-28', '2022-10-01', '2022-10-02', 4, '2022-09-30', 3, 5.9, 0, null, null, null, 53, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (35, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-09-30', 3, 5.9, 0, null, null, null, 53, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (36, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-09-30', 3, 5.9, 0, null, null, null, 53, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (37, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-09-30', 3, 5.9, 0, null, null, null, 53, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (38, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-09-30', 3, 5.9, 0, null, null, null, 53, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (39, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-09-30', 3, 5.9, 0, null, null, null, 53, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (40, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-09-30', 3, 5.9, 0, null, null, null, 53, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (41, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-09-30', 3, 5.9, 0, null, null, null, 53, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (42, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-09-30', 3, 5.9, 0, null, null, null, 54, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (43, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-09-30', 3, 5.9, 0, null, null, null, 54, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (44, '2022-09-28', '2022-09-28', '2022-09-29', 1, '2022-10-26', 7, 1, 0, null, null, null, 39, 1, 'AA123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (45, '2022-09-29', '2022-09-29', '2022-09-30', 1, '2022-10-27', 3, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (46, '2022-09-29', '2022-09-29', '2022-09-30', 1, '2022-10-27', 3, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (47, '2022-09-29', '2022-09-29', '2022-09-30', 1, '2022-10-27', 3, 5.9, 0, null, null, null, 37, 1, 'AB123456');
INSERT INTO carrental.orders (id, date, lease_begin, lease_finish, lease_term, passport_valid, car_id, price, rejected,
                              reject_reason, return_damage, return_date, user_id, with_driver, passport_number)
VALUES (48, '2022-09-30', '2022-09-30', '2022-10-01', 1, '2022-10-27', 23, 150, 0, null, null, null, 37, 1, 'AB123456');

# invoices
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (2, 1, 1, 1, 1, '2022-09-14', '2022-09-15', 26);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (3, 1, 2, 1, 1, '2022-09-15', '2022-09-15', 26);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (4, 15, 1, 5.9, 1, '2022-09-15', '2022-09-28', 38);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (5, 16, 1, 5.9, 0, '2022-09-15', null, 38);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (6, 17, 1, 1, 0, '2022-09-21', null, 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (7, 18, 1, 1, 1, '2022-09-21', '2022-09-21', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (8, 19, 1, 20.99, 1, '2022-09-21', '2022-09-21', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (9, 20, 1, 5.9, 0, '2022-09-21', null, 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (10, 21, 1, 20.99, 0, '2022-09-22', null, 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (11, 22, 1, 5.9, 0, '2022-09-23', null, 39);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (12, 23, 1, 5.9, 0, '2022-09-23', null, 26);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (13, 24, 1, 5.9, 0, '2022-09-26', null, 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (14, 25, 1, 5.9, 0, '2022-09-26', null, 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (15, 26, 1, 5.9, 1, '2022-09-26', '2022-09-27', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (16, 27, 1, 20.99, 0, '2022-09-26', null, 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (17, 28, 1, 5.9, 0, '2022-09-27', null, 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (18, 29, 1, 5.9, 1, '2022-09-27', '2022-09-27', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (19, 30, 1, 0.9, 1, '2022-09-27', '2022-09-27', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (20, 31, 1, 0.9, 1, '2022-09-27', '2022-09-27', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (21, 32, 1, 5.9, 0, '2022-09-27', null, 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (22, 33, 1, 5.9, 1, '2022-09-28', '2022-09-28', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (23, 34, 1, 5.9, 0, '2022-09-28', null, 53);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (24, 35, 1, 5.9, 0, '2022-09-28', null, 53);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (25, 36, 1, 5.9, 0, '2022-09-28', null, 53);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (26, 37, 1, 5.9, 0, '2022-09-28', null, 53);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (27, 38, 1, 5.9, 1, '2022-09-28', '2022-09-28', 53);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (28, 39, 1, 5.9, 1, '2022-09-28', '2022-09-28', 53);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (29, 40, 1, 5.9, 0, '2022-09-28', null, 53);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (30, 41, 1, 5.9, 1, '2022-09-28', '2022-09-28', 53);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (31, 42, 1, 5.9, 1, '2022-09-28', '2022-09-28', 54);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (32, 43, 1, 5.9, 0, '2022-09-28', null, 54);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (33, 1, 2, 150, 0, '2022-09-28', null, 26);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (34, 44, 1, 1, 1, '2022-09-28', '2022-09-28', 39);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (35, 45, 1, 5.9, 1, '2022-09-29', '2022-09-29', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (36, 46, 1, 5.9, 1, '2022-09-29', '2022-09-29', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (37, 47, 1, 5.9, 1, '2022-09-29', '2022-09-29', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (38, 48, 1, 150, 1, '2022-09-30', '2022-09-30', 37);
INSERT INTO carrental.invoices (id, order_id, type, amount, payed, date, payed_date, user_id)
VALUES (39, 1, 2, 150, 0, '2022-09-30', null, 26);
