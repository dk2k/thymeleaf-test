
    drop table if exists USER;

    create table USER (
        ID bigint not null auto_increment,
        NAME varchar(100) not null,
        USERNAME varchar(100) not null,
        PASSWORD varchar(500) not null,
        EMAIL varchar(50) not null,
        GENDER varchar(255),
        AGE integer not null,
        REGISTERED timestamp default now() not null,
        primary key (ID)
    ) ENGINE=InnoDB;

    alter table USER 
        add constraint UK_lb5yrvw2c22im784wwrpwuq06 unique (USERNAME);
