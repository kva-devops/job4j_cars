create table users (
    id serial primary key,
    name varchar (255),
    email varchar (255),
    password varchar (255)
);
create table brands (
    id serial primary key,
    name varchar (255)
);
create table bodies (
    id serial primary key,
    name varchar (255)
);
create table items (
    id serial primary key,
    description varchar (255),
    sold boolean,
    brand_id int references brands(id),
    body_id int references bodies(id),
    user_id int references users(id)
);
create table users_items (
    user_id int references users(id),
    item_id int references items(id)
);
insert into brands (name) values ('Mercedes');
insert into brands (name) values ('BMW');
insert into brands (name) values ('Toyota');
insert into brands (name) values ('Renault');
insert into bodies (name) values ('универсал');
insert into bodies (name) values ('хэтчбек');
insert into bodies (name) values ('седан');
