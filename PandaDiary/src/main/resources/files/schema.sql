drop table Orders;
drop table Users;
drop table Paper_Colors;
drop table Paper_Types;
drop table Cover_Colors;
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

create table Paper_Colors(
	id int primary key,
	description varchar(20)
);

create table Paper_Types(
	id int primary key,
	description varchar(50)
);

create table Cover_Colors(
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
	paper_type int not null references Paper_Types(id),
	paper_color int not null references Paper_Colors(id),
	cover_color int not null references Cover_Colors(id),
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

INSERT INTO Paper_Colors values (1,'Original');
INSERT INTO Paper_Colors values (2,'White');
INSERT INTO Paper_Colors values (3,'Grey');

INSERT INTO Cover_Colors values (1,'Light Blue');
INSERT INTO Cover_Colors values (2,'Pink');
INSERT INTO Cover_Colors values (3,'Gray');
INSERT INTO Cover_Colors values (4,'Red');
INSERT INTO Cover_Colors values (5,'Gold');
INSERT INTO Cover_Colors values (6,'Green');
INSERT INTO Cover_Colors values (7,'Purple');

INSERT INTO Paper_Types values (1,'White cardboard');
INSERT INTO Paper_Types values (2,'Light paper');
INSERT INTO Paper_Types values (3,'Lightweight coated paper');
INSERT INTO Paper_Types values (4,'Coated paper');
INSERT INTO Paper_Types values (5,'Offset printing paper');

INSERT INTO Users VALUES ('c0001','Frank','ys17ygg@gmail.com','12345678','0410503344','915 Collins St','Docklands','3008','Victoria');
INSERT INTO Users VALUES ('c0002','Amy','ad87fiu@gmail.com','12345678','0410503345','883 Collins St','Docklands','3008','Victoria');
INSERT INTO Users VALUES ('a0001','Zoe','zbhu7e9u@gmail.com','12345678','0410503387','100 Harbour','Docklands','3008','Victoria');
INSERT INTO Users VALUES ('a0002','Lisa','87sfgruil@gmail.com','12345678','0410508977','26 Bindiguy St','Bobadah','2877','New South Wales');
INSERT INTO Users VALUES ('c0003','Lucia','7sfubli@gmail.com','12345678','0410504577','16 Quail St','Marla','5724','South Australia');
INSERT INTO Users VALUES ('c0004','Zack','87spfyra@gmail.com','12345678','0410503498','31 Shed Terric Rd','Blackall','4472','Queensland');
INSERT INTO Users VALUES ('c0005','Josh','76svbhb@gmail.com','12345678','0410508732','160 Victoria St','Carlton','3053','Victoria');
INSERT INTO Users VALUES ('c0006','Jack','8zhuvb@gmail.com','12345678','0410506632','127-141 A''Beckett St','Melbourne','3000','Victoria');
INSERT INTO Users VALUES ('c0007','Tommie','7dzhvyz@gmail.com','12345678','0410509987','175 Argyle St','Hobart','7000','Tasmania');
INSERT INTO Users VALUES ('c0008','May','7dsyhb@gmail.com','12345678','0410506743','15 Victoria St','Cue','6640','Western Australia');
INSERT INTO Users VALUES ('c0009','Mike','9z8db@gmail.com','12345678','0410509834','58 Piper Rd','Risdon','7017','Tasmania');
INSERT INTO Users VALUES ('c0010','Mia','87bdvj@gmail.com','12345678','0410509032','52 Burt St','Aramac','4726','Queensland');
INSERT INTO Users VALUES ('c0011','Lucy','dzfhjbh@gmail.com','12345678','0410506744','555 Swanston St','Carlton','3053','Victoria');
INSERT INTO Users VALUES ('c0012','Jade','yug7f8hr@gmail.com','12345678','0410502341','19 St Ann St','Isisford','4731','Queensland');
INSERT INTO Users VALUES ('c0013','Hellen','13g17uf@gmail.com','12345678','0410503445','188 Milford St','Nymagee','2831','New South Wales');
INSERT INTO Users VALUES ('c0014','Sam','vgYU409@gmail.com','12345678','0410509999','132 Wooramel Rd','Wooramel','6701','Western Australia');

INSERT INTO Orders values ('2020032501','c0001',1,1,1,'HBD',1,1,'0410503344','915 Collins St','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-30','Great product');
INSERT INTO Orders values ('2020032502','c0002',1,1,1,'HBD',1,2,'0410503345','883 Collins St','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-30','Good quality');
INSERT INTO Orders values ('2020032503','c0001',1,1,1,'HBD',1,2,'0410503387','100 Harbour','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-31','Happiness');
INSERT INTO Orders values ('2020032504','c0002',1,1,1,'HBD',1,1,'0410508977','26 Bindiguy St','Bobadah','2877','New South Wales','2020-03-25','2020-03-25','2020-03-28',5,'2020-04-01','Very good');
INSERT INTO Orders values ('2020032505','c0003',1,2,1,'HBD',1,2,'0410504577','16 Quail St','Marla','5724','South Australia','2020-03-25','2020-03-25','2020-03-28',3,'2020-04-05','Perfect');
INSERT INTO Orders values ('2020032506','c0004',1,2,1,'HBD',1,1,'0410503498','31 Shed Terric Rd','Blackall','4472','Queensland','2020-03-25','2020-03-25','2020-03-28',1,'2020-04-03','Happiness');
INSERT INTO Orders values ('2020032507','c0005',1,2,2,'Love Diary',1,1,'0410508732','160 Victoria St','Carlton','3053','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-30','Perfect');
INSERT INTO Orders values ('2020032508','c0006',1,2,2,'Love Diary',1,1,'0410506632','127-141 A''Beckett St','Melbourne','3000','Victoria','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-29','Good quality');
INSERT INTO Orders values ('2020032509','c0007',1,1,2,'Love Diary',1,2,'0410509987','175 Argyle St','Hobart','7000','Tasmania','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-29','Perfect');
INSERT INTO Orders values ('2020032510','c0008',1,1,2,'Love Diary',1,1,'0410506743','15 Victoria St','Cue','6640','Western Australia','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-30','Happiness');
INSERT INTO Orders values ('2020032511','c0003',1,1,2,'Love Diary',2,2,'0410509834','58 Piper Rd','Risdon','7017','Tasmania','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-29','So fast');
INSERT INTO Orders values ('2020032512','c0004',1,1,2,'Love Diary',2,1,'0410509032','52 Burt St','Aramac','4726','Queensland','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-29','Bright colors');
INSERT INTO Orders values ('2020032513','c0005',1,1,2,'Love Diary',1,1,'0410506744','555 Swanston St','Carlton','3053','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-04-05','Good quality paper');
INSERT INTO Orders values ('2020032514','c0005',1,1,2,'Love Diary',2,2,'0410502341','19 St Ann St','Isisford','4731','Queensland','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-29','Choice diversity');
INSERT INTO Orders values ('2020032515','c0006',1,1,2,'Love Diary',1,1,'0410503445','188 Milford St','Nymagee','2831','New South Wales','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-30','Happiness');
INSERT INTO Orders values ('2020032516','c0007',2,1,3,'Friendship',1,1,'0410509999','132 Wooramel Rd','Wooramel','6701','Western Australia','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-30','Perfect');
INSERT INTO Orders values ('2020032517','c0008',2,2,3,'Friendship',1,1,'0421092197','900 Collins St','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-31','Good quality');
INSERT INTO Orders values ('2020032518','c0009',2,2,3,'Friendship',2,2,'0428913710','888 Collins St','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',3,'2020-04-01','Perfect');
INSERT INTO Orders values ('2020032519','c0010',2,2,3,'Friendship',1,1,'0421187635','101 Harbour','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-29','Happiness');
INSERT INTO Orders values ('2020032520','c0011',2,2,3,'Friendship',2,2,'0420110288','31 Bindig','Bobadah','2877','New South Wales','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-29','So fast');
INSERT INTO Orders values ('2020032521','c0012',2,3,3,'Friendship',1,1,'0421721427','10 Quail St','Marla','5724','South Australia','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-30','Bright colors');
INSERT INTO Orders values ('2020032522','c0013',2,3,3,'Friendship',1,1,'0424427157','20 Shed Terric Rd','Blackall','4472','Queensland','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-30','Good quality paper');
INSERT INTO Orders values ('2020032523','c0014',2,3,3,'Friendship',1,1,'0420213834','10 Victoria St','Carlton','3053','Victoria','2020-03-25','2020-03-25','2020-03-28',3,'2020-04-05','Choice diversity');
INSERT INTO Orders values ('2020032524','c0001',2,3,3,'Friendship',2,1,'0429127493','120 A''Beckett St','Melbourne','3000','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-30','Bright colors');
INSERT INTO Orders values ('2020032525','c0002',2,3,3,'Friendship',2,1,'0428121429','15 Argyle St','Hobart','7000','Tasmania','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-31','Good quality paper');
INSERT INTO Orders values ('2020032526','c0003',2,2,3,'Friendship',2,1,'0428126218','5 Victoria St','Cue','6640','Western Australia','2020-03-25','2020-03-25','2020-03-28',5,'2020-04-01','Choice diversity');
INSERT INTO Orders values ('2020032527','c0004',2,2,3,'Friendship',2,1,'0421984270','8 Piper Rd','Risdon','7017','Tasmania','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-30','Happiness');
INSERT INTO Orders values ('2020032528','c0005',3,2,4,'Lucky',2,1,'0421264218','5 Burt St','Aramac','4726','Queensland','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-30','Perfect');
INSERT INTO Orders values ('2020032529','c0006',3,2,4,'Lucky',1,1,'0428132637','55 Swanston St','Carlton','3053','Victoria','2020-03-25','2020-03-25','2020-03-28',3,'2020-04-05','Happiness');
INSERT INTO Orders values ('2020032530','c0007',3,1,4,'Lucky',2,2,'0428421641','17 St Ann St','Isisford','4731','Queensland','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-30','So fast');
INSERT INTO Orders values ('2020032531','c0008',3,1,4,'Lucky',1,1,'0421824124','190 Milford St','Nymagee','2831','New South Wales','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-30','Bright colors');
INSERT INTO Orders values ('2020032532','c0003',3,1,4,'Lucky',1,1,'0428421743','136 Wooramel Rd','Wooramel','6701','Western Australia','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-31','Good quality paper');
INSERT INTO Orders values ('2020032533','c0004',3,1,4,'Lucky',1,1,'0428427349','912 Collins S','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',4,'2020-04-01','Choice diversity');
INSERT INTO Orders values ('2020032534','c0005',3,1,4,'Lucky',2,2,'0421824215','881 Collins St','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',4,'2020-03-30','Bright colors');
INSERT INTO Orders values ('2020032535','c0005',3,1,4,'Lucky',1,1,'0424823725','105 Harbour','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',2,'2020-03-30','Good quality paper');
INSERT INTO Orders values ('2020032536','c0006',3,2,4,'Lucky',1,1,'0429325801','19 Bindiguy S','Bobadah','2877','New South Wales','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-30','Choice diversity');
INSERT INTO Orders values ('2020032537','c0007',3,2,4,'Lucky',2,1,'0421944549','20 Quail St','Marla','5724','South Australia','2020-03-25','2020-03-25','2020-03-28',3,'2020-03-30','Happiness');
INSERT INTO Orders values ('2020032538','c0008',4,2,5,'Great Day',1,1,'0412791403','25 Shed Terric Rd','Blackall','4472','Queensland','2020-03-25','2020-03-25','2020-03-28',4,'2020-03-31','Perfect');
INSERT INTO Orders values ('2020032539','c0009',4,2,5,'Great Day',1,1,'0428124825','171 Victoria St','Carlton','3053','Victoria','2020-03-25','2020-03-25','2020-03-28',4,'2020-04-01','Perfect');
INSERT INTO Orders values ('2020032540','c0010',4,2,5,'Great Day',1,1,'0413949274','150 A''Beckett St','Melbourne','3000','Victoria','2020-03-25','2020-03-25','2020-03-28',2,'2020-04-03','Choice diversity');
INSERT INTO Orders values ('2020032541','c0011',4,2,5,'Great Day',2,1,'0422974431','169 Argyle St','Hobart','7000','Tasmania','2020-03-25','2020-03-25','2020-03-28',3,'2020-04-19','Bright colors');
INSERT INTO Orders values ('2020032542','c0012',4,2,5,'Great Day',2,1,'0413928340','18 Victoria St','Cue','6640','Western Australia','2020-03-25','2020-03-25','2020-03-28',3,'2020-04-14','Good quality paper');
INSERT INTO Orders values ('2020032543','c0013',4,2,5,'Great Day',2,2,'0420189428','56 Piper Rd','Risdon','7017','Tasmania','2020-03-25','2020-03-25','2020-03-28',5,'2020-04-16','Choice diversity');
INSERT INTO Orders values ('2020032544','c0014',4,1,5,'Great Day',2,1,'0413242324','54 Burt St','Aramac','4726','Queensland','2020-03-25','2020-03-25','2020-03-28',5,'2020-04-09','Happiness');
INSERT INTO Orders values ('2020032545','c0007',5,1,6,'Best Wishess',1,1,'0429234739','556 Swanston St','Carlton','3053','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-30','Perfect');
INSERT INTO Orders values ('2020032546','c0008',5,3,6,'Best Wishess',2,1,'0412972170','17 St Ann St','Isisford','4731','Queensland','2020-03-25','2020-03-25','2020-03-28',5,'2020-03-31','Bright colors');
INSERT INTO Orders values ('2020032547','c0003',5,1,6,'Best Wishess',1,2,'0419723172','180 Milford St','Nymagee','2831','New South Wales','2020-03-25','2020-03-25','2020-03-28',3,'2020-04-01','Good quality paper');
INSERT INTO Orders values ('2020032548','c0004',5,2,6,'Best Wishess',1,1,'0412792831','135 Wooramel Rd','Wooramel','6701','Western Australia','2020-03-25','2020-03-25','2020-03-28',3,'2020-04-12','Choice diversity');
INSERT INTO Orders values ('2020032549','c0005',5,1,6,'Best Wishess',2,1,'0428424240','920 Collins St','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-04-03','Bright colors');
INSERT INTO Orders values ('2020032550','c0005',5,2,6,'Best Wishess',2,1,'0421741320','889 Collins St','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-04-01','Good quality paper');
INSERT INTO Orders values ('2020032551','c0006',5,3,6,'Best Wishess',2,2,'0411274309','105 Harbour','Docklands','3008','Victoria','2020-03-25','2020-03-25','2020-03-28',5,'2020-04-03','Happiness');