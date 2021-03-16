CREATE TABLE BEER (
--       internal_id IDENTITY NOT NULL PRIMARY,
        internal_id PRIMARY KEY AUTO_INCREMENT,
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
 --   FOR, UNSIGNED, INVISIBLE, VISIBLE, NOT, NULL, AS, DEFAULT, GENERATED, ON, NOT, NULL, AUTO_INCREMENT, BIGSERIAL, SERIAL,
 --IDENTITY, NULL_TO_DEFAULT, SEQUENCE, SELECTIVITY, COMMENT, CONSTRAINT, PRIMARY, UNIQUE, NOT, NULL, COMMENT, CHECK, REFERENCES,

    CREATE TABLE FOOD (
       id IDENTITY not null PRIMARY,
        name varchar(255),
        beer_id bigint,
        primary key (id),
        foreign key (beer_id)
        references beer
    );