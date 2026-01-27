create table users (
    id int auto_increment primary key;
    username varchar(50) not null unique;
    password varchar(255) not null,
    role enum('admin','user') not null
);

insert into users (username, password, role) values
('admin','adminpasswordhash','admin',),
('user1','user1passwordhash','user');