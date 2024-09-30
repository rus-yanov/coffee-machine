create table "order"
(
    id            uuid primary key,
    comment       varchar,
    date_time     timestamp        not null,
    constraint order_coffee_fk foreign key (coffee_id) references coffee (id)
)