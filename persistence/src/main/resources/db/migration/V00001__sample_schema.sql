CREATE TABLE author
(
    id         UUID                        NOT NULL,
    name       TEXT                        NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE book
(
    id         UUID                        NOT NULL,
    author_id  UUID                        NOT NULL,
    isbn       VARCHAR(13)                 NOT NULL UNIQUE,
    title      VARCHAR(255)                NOT NULL,
    price      FLOAT                       NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

ALTER TABLE ONLY author
    ADD CONSTRAINT author_pkey PRIMARY KEY (id);

ALTER TABLE ONLY book
    ADD CONSTRAINT book_pkey PRIMARY KEY (id);

ALTER TABLE ONLY book
    ADD CONSTRAINT book_author_id_fkey FOREIGN KEY (author_id) REFERENCES author (id);