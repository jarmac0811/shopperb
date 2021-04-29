--INSERT INTO beer (description, external_id, first_brewed, ibu, id_type, price, name, tagline, internal_id)
--    VALUES ('some descryption', 0, '04/2013,25', 25, 'userInput', null, 'Vice Beer', 'tag', 1);
--INSERT INTO food (beer_id, name, id) values (1, 'some pairing', 1);

insert into category (category_Id, category_Name) values ('bread', 'Bread');
insert into category (category_Id, category_Name) values ('dairy', 'Dairy');
insert into category (category_Id, category_Name) values ('fruits', 'Fruits');
insert into category (category_Id, category_Name) values ('seasonings', 'Seasonings and Spices');
insert into category (category_Id, category_Name) values ('vegetables', 'Vegetables');

insert into user (user_Id, external_User_Id, admin, email, user_Name, roles,password) values (112121221212, '104308707124955157902', 'true', 'jarmac0811@gmail.com', 'Jarosław Maciejczak','ADMIN','$2y$12$CQPbTehPHRwIjuR64UdGJO.YvCwUg5IJt9yE1dRnXAxbvGmvTCUq.');
insert into user (user_Id, external_User_Id, admin, email, user_Name, roles,password) values (223232323233, '2', 'false', 'user123@gmail.com', 'user123','USER','$2y$12$TXXxjlQ4mkJN2XvkduvCBeDa0pFk4TqpdczODJuQkvkKoMfGd0SfK');


insert into product (id, category, price, image_url, title)
values (1,'vegetables',2,'http://www.publicdomainpictures.net/pictures/170000/velka/spinach-leaves-1461774375kTU.jpg','Spinach');
insert into product (id, category, price, image_url, title)
values (2,'bread',3,'https://static.pexels.com/photos/2434/bread-food-healthy-breakfast.jpg','Freshly Baked Bread');
insert into product (id, category, price, image_url, title)
values (3,'fruits',1,'https://pixnio.com/free-images/2017/03/17/2017-03-17-09-15-56.jpg','Avocado');
insert into product (id, category, price, image_url, title)
values (4,'vegetables',2,'https://static.pexels.com/photos/8390/food-wood-tomatoes.jpg','Tomato');
/*
insert into product (id, category, price, image_url, title)
values (5,'vegetables',1,'https://upload.wikimedia.org/wikipedia/commons/7/7f/Lettuce_Mini_Heads_%287331119710%29.jpg','Lettuce');
insert into product (id, category, price, image_url, title)
values (6,'vegetables',1.75,'https://upload.wikimedia.org/wikipedia/commons/thumb/1/11/Cauliflowers_-_20051021.jpg/1280px-Cauliflowers_-_20051021.jpg','Cauliflower');
insert into product (id, category, price, image_url, title)
values (7,'fruits',1.25,'https://upload.wikimedia.org/wikipedia/commons/thumb/4/4c/Bananas.jpg/1024px-Bananas.jpg','Banana');
insert into product (id, category, price, image_url, title)
values (8,'fruits',1.7,'https://upload.wikimedia.org/wikipedia/commons/c/c4/Orange-Fruit-Pieces.jpg','Orange');
insert into product (id, category, price, image_url, title)
values (9,'fruits',2,'https://upload.wikimedia.org/wikipedia/commons/1/15/Red_Apple.jpg','Apple');
insert into product (id, category, price, image_url, title)
values (10,'fruits',2,'https://upload.wikimedia.org/wikipedia/commons/3/36/Kyoho-grape.jpg','Grape');
insert into product (id, category, price, image_url, title)
values (11,'fruits',2,'https://upload.wikimedia.org/wikipedia/commons/9/9e/Autumn_Red_peaches.jpg','Peach');
insert into product (id, category, price, image_url, title)
values (12,'fruits',1.95,'https://upload.wikimedia.org/wikipedia/commons/e/e1/Strawberries.jpg','Strawberry');
insert into product (id, category, price, image_url, title)
values (13,'seasonings',0.5,'https://upload.wikimedia.org/wikipedia/commons/8/8c/Cinnamon-other.jpg','Cinnamon Sticks');
insert into product (id, category, price, image_url, title)
values (14,'seasonings',3,'https://upload.wikimedia.org/wikipedia/commons/4/48/Saffron_Crop.JPG','Saffron');
insert into product (id, category, price, image_url, title)
values (15,'seasonings',0.75,'http://maxpixel.freegreatpicture.com/static/photo/1x/Seasoning-Powder-Curry-Spice-Ingredient-Turmeric-2344157.jpg','Ground Turmeric');
insert into product (id, category, price, image_url, title)
values (16,'seasonings',0.5,'http://maxpixel.freegreatpicture.com/static/photo/1x/Ingredient-Herb-Seasoning-Seeds-Food-Coriander-390015.jpg','Coriander Seeds');
insert into product (id, category, price, image_url, title)
values (17,'bread',1.25,'https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Fabrication_du_lavash_%C3%A0_Noravank_%286%29.jpg/1280px-Fabrication_du_lavash_%C3%A0_Noravank_%286%29.jpg','Lavash Bread');
insert into product (id, category, price, image_url, title)
values (18,'bread',1,'https://upload.wikimedia.org/wikipedia/commons/1/1d/Bagel-Plain-Alt.jpg','Bagel Bread');
insert into product (id, category, price, image_url, title)
values (19,'bread',1.25,'https://static.pexels.com/photos/416607/pexels-photo-416607.jpeg','Baguette Bread');
insert into product (id, category, price, image_url, title)
values (20,'dairy',1.25,'https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/d88e7e0a-7a50-475e-8d3a-df3327566ad8/Derivates/8af1b129-efb9-4ff3-89bd-ff6e8031aba0.jpg','Milk');
*/
