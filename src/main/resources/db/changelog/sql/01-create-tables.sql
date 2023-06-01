create table computer
(
    id BIGSERIAL PRIMARY KEY,
    form VARCHAR NOT NULL,
    serial_number INTEGER NOT NULL,
    manufacturer VARCHAR NOT NULL,
    price DECIMAL NOT NULL,
    quantity INTEGER NOT NULL
);

create table hdd
(
    id BIGSERIAL PRIMARY KEY,
    volume_gb INTEGER NOT NULL,
    serial_number INTEGER NOT NULL,
    manufacturer VARCHAR NOT NULL,
    price DECIMAL NOT NULL,
    quantity INTEGER NOT NULL
);

create table monitor
(
    id BIGSERIAL PRIMARY KEY,
    dioganal INTEGER NOT NULL,
    serial_number INTEGER NOT NULL,
    manufacturer VARCHAR NOT NULL,
    price DECIMAL NOT NULL,
    quantity INTEGER NOT NULL
);

create table laptop
(
    id BIGSERIAL PRIMARY KEY,
    dioganal INTEGER NOT NULL,
    serial_number INTEGER NOT NULL,
    manufacturer VARCHAR NOT NULL,
    price DECIMAL NOT NULL,
    quantity INTEGER NOT NULL
);

