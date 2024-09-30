create table "ingredient"
(
    id            uuid primary key,
    name          varchar          not null,
    expense       bigint           not null,
    type          varchar          not null,
    quantity_status varchar not null default 'FULL'
)