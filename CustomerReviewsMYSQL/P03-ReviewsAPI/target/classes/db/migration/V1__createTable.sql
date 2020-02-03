CREATE TABLE product (
id INT AUTO_INCREMENT,
name VARCHAR(100) ,
price Double  ,
description TEXT ,
PRIMARY KEY(id)
);

CREATE TABLE review (
id INT AUTO_INCREMENT,
product_id INT ,
rating INT ,
review_content TEXT ,
review_date VARCHAR(50),
review_user_name VARCHAR(100) ,
PRIMARY KEY(id),
FOREIGN KEY(product_id) REFERENCES product (id)
);


CREATE TABLE comment (
id INT AUTO_INCREMENT,
review_id INT ,
comment_content TEXT ,
comment_date VARCHAR(50) ,
comment_user_name VARCHAR(100) ,
PRIMARY KEY(id),
FOREIGN KEY(review_id) REFERENCES review (id)
);