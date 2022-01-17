
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

create table product (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    product_name varchar(200),
    product_code varchar(50),
	price decimal(10,2),
    status varchar(100) NOT NULL,
	metadata text,
    created_time TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

create table purchase_order (
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

create table purchase_order_detail (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    purchase_order_id bigint(20),
    product_id bigint(20),
    product_price decimal,
    status varchar(50) NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (purchase_order_id) REFERENCES purchase_order(id)
);

create table shopping_cart (
    id bigint(20) NOT NULL AUTO_INCREMENT,
    customer_id bigint(20),
    product_id bigint(20),
    product_price decimal,
    status varchar(50) NOT NULL,
    created_time TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_time TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);