CREATE TABLE book_provider (
    book_id BIGINT NOT NULL,
    provider_id BIGINT NOT NULL,
    PRIMARY KEY (book_id, provider_id),
    CONSTRAINT fk_book
        FOREIGN KEY (book_id)
        REFERENCES book (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_provider
        FOREIGN KEY (provider_id)
        REFERENCES provider (id)
        ON DELETE CASCADE
);