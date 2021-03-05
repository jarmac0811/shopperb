INSERT INTO beer (description, external_id, first_brewed, ibu, id_type, image_url, name, tagline, internal_id)
    VALUES ('some descryption', 0, '04/2013,25', 25, 'userInput', null, 'Vice Beer', 'tag', 1);
INSERT INTO food (beer_id, name, id) values (1, 'some pairing', 1);