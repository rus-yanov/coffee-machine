create table "coffee"
(
    id            uuid primary key,
    name          varchar          not null,
    score         bigint           not null,
    constraint coffee_ingredient_fk foreign key (ingredient_id) references ingredient (id)
)