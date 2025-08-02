CREATE TABLE users(
    id uuid PRIMARY KEY ,
    firstname varchar(255) not null ,
    lastname varchar(255) not null,
    email varchar(255) not null unique ,
    password varchar(255) not null ,
    house_number varchar(10),
    street varchar(255),
    city varchar(255),
    postal_code varchar(50),
    country varchar(100),

    created_at timestamp with time zone not null ,
    modified_at timestamp with time zone

);