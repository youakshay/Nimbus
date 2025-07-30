CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(30) NOT NULL unique,
    full_name VARCHAR(50) NOT NULL,
    email varchar(50) NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    user_role VARCHAR(50) NOT NULL,
    created_at Timestamp default current_timestamp,
    primary key(username)
);
