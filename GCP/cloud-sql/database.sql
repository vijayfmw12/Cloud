
create database weblogic;

show databases;

use weblogic;

create table users (
    id int auto_increment primary key,
    username varchar(50) not null unique,
    password varchar(255) not null,
    role enum('admin','user') not null
);

insert into users (username, password, role) values
('admin','adminpasswordhash','admin'),
('user1','user1passwordhash','user');

select * from users;
insert into users (username, password, role) values
('user2','user2passwordhash','user2'),
('user3','user3passwordhash','user3');

insert into users (username, password, role) values
('vijay','vijaypasswordhash','user');

CREATE TABLE employee (
    emp_id INT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(100) NOT NULL,
    department VARCHAR(50),
    designation VARCHAR(50),
    salary DECIMAL(10,2),
    email VARCHAR(150) UNIQUE,
    phone VARCHAR(15),
    hire_date DATE,
    status VARCHAR(20) DEFAULT 'ACTIVE',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



INSERT INTO employee (emp_id, emp_name, department, salary)
VALUES (101, 'Vijay', 'IT', 125000);
