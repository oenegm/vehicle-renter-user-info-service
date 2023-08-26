create extension if not exists "uuid-ossp";

create table if not exists users
(
    id         uuid primary key default uuid_generate_v4(),
    role       varchar(20)  not null,
    username   varchar(255) unique not null,
    password   varchar(255),
    created_at timestamp    not null
);

create table if not exists website_settings
(
    id           uuid primary key     default uuid_generate_v4(),
    user_id      uuid references users (id),
    is_dark_mode boolean     not null default true,
    language     varchar(20) not null default 'EN'
);
