create database water_billing_management;

use water_billing_management;

create table role(
	id int auto_increment not null,
    name varchar(20) not null unique,
    CONSTRAINT PK_role PRIMARY KEY (id)
);

create table user(
	id int auto_increment not null, 
    username varchar(255) not null unique, 
    passwd varchar(255) not null,
    firstname varchar(255) not null, 
    lastname varchar(255) not null, 
    phone varchar(10) not null,
    email varchar(50),
    role_id integer not null,
    CONSTRAINT FK_userRole FOREIGN KEY (role_id) REFERENCES role(id),
	CONSTRAINT PK_user PRIMARY KEY (id)
);

create table client(
	id varchar(6) not null,
    address varchar(255) not null,
    user_id integer not null,
    CONSTRAINT FK_clientUser FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT PK_client PRIMARY KEY (id)
);

create table admin(
	id varchar(6) not null,
    user_id integer not null,
    CONSTRAINT FK_adminUser FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    CONSTRAINT PK_admin PRIMARY KEY (id)
);

create table bill(
	id int auto_increment not null,
    status boolean not null default 0, -- 0: chua tra, 1: da tra
    month integer not null,
    year integer not null,
    tax float not null,
    environment_fee float not null,
    amount float not null,
    meter_consum int not null,
    client_id varchar(6) not null,
    CONSTRAINT FK_billClient FOREIGN KEY (client_id) REFERENCES client(id) ON DELETE CASCADE,
    CONSTRAINT PK_bill PRIMARY KEY (id)
);

create table config(
	id int auto_increment not null,
    environment_rate int not null,
    tax_rate int not null,
    level1 double,
    level2 double,
    level3 double,
    level4 double,
    primary key(id)
);