DROP TABLE IF EXISTS user_service;
create table user_service
(
   Id integer not null AUTO_INCREMENT,
   First_Name varchar(255) not null,
   Last_Name varchar(255) not null,
   Email varchar(255) not null,
   PinCode integer,
   Birth_Date date,
   Status varchar(255),
   primary key(id)
);




