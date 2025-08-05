CREATE TABLE users
(
    guid       UUID PRIMARY KEY NOT NULL,
    username   VARCHAR(100)     NOT NULL,
    role       VARCHAR(20)      NOT NULL,
    status     VARCHAR(20)      NOT NULL,
    email      VARCHAR(100),
    first_name VARCHAR(200),
    language   VARCHAR(20),
    timezone   INT,
    created_at TIMESTAMP        NOT NULL DEFAULT current_timestamp,
    updated_at TIMESTAMP        NOT NULL DEFAULT current_timestamp,

    CONSTRAINT timezone_valid CHECK (timezone >= -12 AND timezone <= 12)
);