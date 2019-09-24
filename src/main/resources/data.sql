INSERT INTO category (name) VALUES ('Java');
INSERT INTO category (name) VALUES ('JavaScript');
INSERT INTO category (name) VALUES ('Spring Boot');
INSERT INTO category (name) VALUES ('ReactJS'); -- 4
INSERT INTO category (name) VALUES ('VueJS');
INSERT INTO category (name) VALUES ('Python3'); -- 6
INSERT INTO category (name) VALUES ('PostgreSQL');
INSERT INTO category (name) VALUES ('GraphQL'); -- 8

INSERT INTO public."user" (role, deleted, first_name, last_name, password, username, full_access) VALUES ('ADMIN', false, 'Milan', 'Miljus', '123123', 'milan123', true);

INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Madeup Nem', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{spring,framework,backend}', 'en', 313, 'Spring boot beginning', 2017, 3, '2019-09-21 13:18:22.065~Beginning Spring Boot 2_ Applications and Microservices with the Spring Framework ( PDFDrive.com ).pdf', 1, '2019-09-21 13:18:22');
INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Stephen Blumenthal', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{beginner,javascript,easy,frontend}', 'en', 115, 'JavaScript: JavaScript For Beginners', 2017, 2, '2019-09-21 13:18:56.822~JavaScript_ JavaScript For Beginners - Learn JavaScript Programming with ease in HALF THE TIME - Everything about the Language, Coding, Programming and Web Pages You need to know ( PDFDrive.com ).pdf', 1, '2019-09-21 13:18:56.822');
INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Guy Some', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{frontend_framework,frontend}', 'en', 532, 'React Quick', 2018, 4, '2019-09-21 13:19:27.779~React Quickly_ Painless Web Apps with React, JSX, Redux and GraphQL ( PDFDrive.com ).pdf', 1, '2019-09-21 13:19:27');
INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Madeup Nem', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{www.it-ebooks.info}', 'en', 280, 'ReactJS by exampele', 2016, 4, '2019-09-21 13:24:29.399~ReactJS by Example_ Building Modern Web Applications with React_ Get up and running with ReactJS by developing five cutting-edge and responsive projects ( PDFDrive.com ).pdf', 1, '2019-09-21 13:24:29');
INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Todd Abel', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{reactjs,frontend,framework,virtual_dom}', 'en', 150, 'ReactJS: Become a professional in web app development (Javascript Framework Book 3)', 2016, 4, '2019-09-21 13:25:03.036~ReactJS_ Become a professional in web app development ( PDFDrive.com ).pdf', 1, '2019-09-21 13:25:03.036');
INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Rogers Cadenhead', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{android,java7,21days}', 'en', 85, 'Sams Teach Yourself Java in 21 Days (Covering Java 7 and Android)', 2012, 1, '2019-09-21 13:25:46.432~Sams Teach Yourself Java in 21 Days (Covering Java 7 and Android).pdf', 1, '2019-09-21 13:25:46');
INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Craig Walls', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{www.it-ebooks.info,backend,framework,spring}', 'en', 266, 'Spring Boot in Action', 2015, 3, '2019-09-21 13:26:03.828~Spring Boot in Action ( PDFDrive.com ).pdf', 1, '2019-09-21 13:26:03');
INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Alex Kyriakidis, Kostas Maniatis and Evan You', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{vuejs,frontend,framework,evan_you}', 'en', 30, 'The Majesty of Vue.js', 2016, 5, '2019-09-21 13:26:27.666~vuejs-sample.pdf', 1, '2019-09-21 13:26:27');
INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Swetha Prasanna', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{vue}', 'en', 31, 'VueJS', 2018, 5, '2019-09-21 13:26:51.822~VueJsTutorial.pdf', 1, '2019-09-21 13:26:51.822');
INSERT INTO public.book (deleted, author, image, keywords, language_code, page_count, title, year, category_id, file_key, owner_id, timestamp) VALUES (false, 'Julian Shapiro', 'https://ibf.org/site_assets/img/placeholder-book-cover-default.png', '{es6,animation,transition,canvas,webgl}', 'en', 402, 'Web Animation using JavaScript: Develop & Design (Develop and Design)', 2015, 2, '2019-09-21 13:27:04.296~Web Animation using JavaScript ( PDFDrive.com ).pdf', 1, '2019-09-21 13:27:04');

INSERT INTO public."user" (role, deleted, first_name, last_name, password, username, full_access) VALUES ('SUB', false, 'Marko', 'Markovic', '123123', 'frontendas99', false);
INSERT INTO public."user" (role, deleted, first_name, last_name, password, username, full_access) VALUES ('SUB', false, 'Jovan', 'Jovanovic', '123123', 'bekendas96', false);

INSERT INTO subs_categories (sub_id, category_id) VALUES (2, 2);
INSERT INTO subs_categories (sub_id, category_id) VALUES (2, 4);
INSERT INTO subs_categories (sub_id, category_id) VALUES (2, 5);
INSERT INTO subs_categories (sub_id, category_id) VALUES (2, 8);
INSERT INTO subs_categories (sub_id, category_id) VALUES (3, 1);
INSERT INTO subs_categories (sub_id, category_id) VALUES (3, 3);
INSERT INTO subs_categories (sub_id, category_id) VALUES (3, 6);
INSERT INTO subs_categories (sub_id, category_id) VALUES (3, 7);
