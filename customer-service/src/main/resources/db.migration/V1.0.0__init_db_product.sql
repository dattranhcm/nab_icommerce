
create table customer (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   user_name varchar(200),
   hash_password varchar(100),
   first_name varchar(100),
   last_name varchar(100),
   email varchar(100),
   mobile varchar(20),
   address text,
   status varchar(100) NOT NULL,
   created_time TIMESTAMP NOT NULL DEFAULT NOW(),
   updated_time TIMESTAMP NOT NULL DEFAULT NOW(),
   PRIMARY KEY (id)
);

create table products (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    product_name varchar(200),
    product_code varchar(50),
    status varchar(100) NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);
create table product_price (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    product_id int,
    price decimal,
    is_current_price boolean,
    status varchar(100) NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
	FOREIGN KEY (product_id) REFERENCES products(id)
);

create table order (
   id bigint(20) NOT NULL AUTO_INCREMENT,
   customer_id bigint(20) NOT NULL,
   total_amount decimal,
   ship_address TEXT NOT NULL,
   order_code varchar(50) NOT NULL,
   status varchar(100) NOT NULL,
   created_time TIMESTAMP NOT NULL DEFAULT NOW(),
   updated_time TIMESTAMP NOT NULL DEFAULT NOW(),
   PRIMARY KEY (id)
);

create table order_items (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    order_id int,
    item_id int,
    item_price decimal,
    status varchar(50) NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES order(id)
);

create table shopping_cart (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    customer_id int,
    item_id int,
    item_price decimal,
    status varchar(50) NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);