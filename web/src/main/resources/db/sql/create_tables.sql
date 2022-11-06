create sequence event_id_seq;

create sequence manager_id_seq;

create sequence venue_id_seq;

create table if not exists manager
(
    id       bigint default nextval('manager_id_seq'::regclass) not null
        primary key,
    name     varchar(45),
    surname  varchar(45),
    lastname varchar(45),
    fullname varchar(155)                                       not null
        constraint "unique_fullName"
        unique
);

create table if not exists venue
(
    id   bigint default nextval('venue_id_seq'::regclass) not null
        primary key,
    name varchar(255)                                     not null
        constraint unique_name
        unique
);

create table if not exists event
(
    id          bigint      default nextval('event_id_seq'::regclass) not null
        primary key,
    manager_id  bigint                                                not null
        constraint event_manager_fk
        references manager,
    venue_id    bigint                                                not null
        constraint event_venue_fk
        references venue,
    topic       varchar(255)                                          not null,
    description text,
    date        timestamp with time zone                              not null,
    status      varchar(45) default 'ACTIVE'::character varying       not null
);