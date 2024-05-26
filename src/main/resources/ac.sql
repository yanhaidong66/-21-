drop database db_ac;
create database db_ac;
use db_ac;
create table t_ac(
                     ac_id int primary key auto_increment,
                     ac_room varchar(30) not null,
                     ac_state tinyint not null default 0 /* 0未开启，1已开启*/,
                     ac_user_id int not null default 0,
                     ac_cost int not null default 10,
                     index (ac_room)
);
create table t_user(
                       user_id int primary key auto_increment,
                       user_ac_id int not null,
                       user_username varchar(30) not null ,
                       user_password varchar(30) not null ,
                       user_role varchar(10) not null default 'USER',
                       user_create_time datetime not null default now(),
                       index (user_ac_id),
                       index (user_username),
                       constraint fk_user_ac foreign key t_user(user_ac_id) references t_ac(ac_id)
);

create table t_ac_operation(
    op_id int primary key auto_increment,
    op_user_id int not null,
    op_type tinyint not null default 0,/* 0开启操作，1关闭操作 */
    op_create_time datetime default now(),
    op_ac_id int not null ,
    index (op_create_time),
    index (op_ac_id),
    constraint fk_op_ac foreign key t_ac_operation(op_ac_id) references t_ac(ac_id),
    constraint fk_op_user foreign key t_ac_operation(op_user_id) references t_user(user_id)
);
create table t_bill(
    bill_id int primary key auto_increment,
    bill_state tinyint not null default 0,/* 0是已办理入住，1是已结账 */
    bill_create_time datetime default now(),
    bill_user_id int not null ,
    bill_ac_id int not null,
    index(bill_create_time),
    constraint fk_bill_user foreign key t_bill(bill_user_id) references t_user(user_id),
    constraint fk_bill_ac foreign key t_bill(bill_ac_id) references t_ac(ac_id)
);
