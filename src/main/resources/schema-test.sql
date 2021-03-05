CREATE TABLE BEER (
       internal_id bigint NOT NULL PRIMARY KEY,
        description text varchar(255),
        external_id bigint,
        first_brewed varchar(255),
        ibu integer not null,
        id_type varchar(255),
        image_url varchar(255),
        name varchar(255),
        tagline varchar(255),
        primary key (internal_id)
    );