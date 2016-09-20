/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  patmo016
 * Created: 8/08/2016
 */

create table products (
    productID integer not null,
    name varchar not null,
    description varchar,
    category varchar,
    price decimal(4,2) not null,
    quantityStock Integer,
    constraint pk_id primary key (productID)
    );

create table customers (
    userName varchar not null,
    name varchar not null,
    email varchar not null,
    address varchar not null,
    creditcard varchar not null,
    password varchar not null,
    
    constraint pk_username primary key (userName)
    );
