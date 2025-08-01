CREATE TABLE IF NOT EXISTS users (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(30) NOT NULL,
    full_name VARCHAR(50) NOT NULL,
    email varchar(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    user_role VARCHAR(50) NOT NULL,
    created_at Timestamp default current_timestamp,
    primary key(id)
);

CREATE TABLE IF NOT EXISTS trips (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    from_location VARCHAR(100) NOT NULL,
    to_location VARCHAR(100) NOT NULL,
    seats_required int NOT NULL,
    departure_time Timestamp NOT NULL,
    user_id BIGINT NOT NULL,
    primary key(id),
    foreign key(user_id) references users(id) on delete cascade
);

CREATE TABLE IF NOT EXISTS pools (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    from_location VARCHAR(100) NOT NULL,
    to_location VARCHAR(100) NOT NULL,
    seats_available int NOT NULL Default 4,
    capacity int NOT NULL Default 4,
    departure_time Timestamp NOT NULL,
    primary key(id)
);

CREATE TABLE IF NOT EXISTS pool_members (
    id BIGINT GENERATED ALWAYS AS IDENTITY,
    pool_id BIGINT NOT NULL,
    trip_id BIGINT NOT NULL,
    primary key(id),
    foreign key(pool_id) references pools(id) on delete cascade,
    foreign key(trip_id) references trips(id) on delete cascade
)