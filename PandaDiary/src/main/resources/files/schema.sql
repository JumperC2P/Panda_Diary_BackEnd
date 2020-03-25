drop table Orders;
drop table Users;
drop table Delivery_Options;
drop table Purchase_Options;

create table Purchase_Options(
	id int primary key,
	description varchar(20)
);

create table Delivery_Options(
	id int primary key,
	description varchar(20)
);

create table Users(
	id varchar(5) primary key,
	name varchar(50) not null,
	email varchar(50) not null,
	password varchar(256) not null,
	phone varchar(10),
	address_street varchar(256),
	address_surburb varchar(50),
	address_postcode varchar(4),
	address_state varchar(20),
	active boolean default true
);

create table Orders(
	id varchar(10) primary key,
	buyer varchar(5) references Users(id) not null,
	paper_type varchar(10) not null,
	paper_color varchar(20) not null,
	cover_color varchar(20) not null,
	title_on_cover varchar(50),
	purchase_option int references Purchase_Options(id) not null,
	delivery_option int references Delivery_Options(id) not null,
	phone varchar(10) not null,
	delivery_street varchar(256) not null,
	delivery_suburb varchar(50) not null,
	delivery_postcode varchar(4) not null,
	delivery_state varchar(20) not null,
	order_date Date not null,
	delivery_date Date,
	close_date Date,
	review_score int,
	review_date Date,
	review_desc varchar(256)
);

insert into Purchase_Options values( 1, 'PayPal');
insert into Purchase_Options values( 2, 'Credit Card');

insert into Delivery_Options values( 1, 'Standard Delivery');
insert into Delivery_Options values( 2, 'Express Delivery');

insert into Users values( 'C0001', 'Hank', 'adslbbcc@gmail.com', '123456');