INSERT INTO PERSON(name, email, password, created_at) VALUES('Alice Santos', 'alices@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO PERSON(name, email, password, created_at) VALUES('Karina Padilha', 'karinapadilha@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO PERSON(name, email, password, created_at) VALUES('Letícia Buss', 'leticiabuss@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO PERSON(name, email, password, created_at) VALUES('Marlei Silveira', 'marleis@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO PERSON(name, email, password, created_at) VALUES('Natália Suzuki', 'nataliasuzuki@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2011-05-06 21:31:51');
INSERT INTO PERSON(name, email, password, created_at) VALUES('Ana Lúcia Pereira', 'analuciapereira@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-07 07:03:24');
INSERT INTO PERSON(name, email, password, created_at) VALUES('Beatriz Lousiana Souza', 'blousianasouza@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-08 11:47:31');
INSERT INTO PERSON(name, email, password, created_at) VALUES('Rodrigo Valles', 'rodrigovalles@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2017-05-09 13:45:35');
INSERT INTO PERSON(name, email, password, created_at) VALUES('Wanessa Longue', 'wanessa.longue@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-10 10:01:43');
INSERT INTO PERSON(name, email, password, created_at) VALUES('Quevin Flores', 'quevinflores@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2018-05-10 14:30:59');

INSERT INTO ENVIRONMENT(name) VALUES('development');
INSERT INTO ENVIRONMENT(name) VALUES('homologacao');
INSERT INTO ENVIRONMENT(name) VALUES('producao');

INSERT INTO LEVEL(name) VALUES('erro');
INSERT INTO LEVEL(name) VALUES('warm');
INSERT INTO LEVEL(name) VALUES('debug');

INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2019-05-05 17:22:21', 1, 1 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2019-05-10 18:43:10', 2, 2 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2019-05-11 18:50:11', 3, 3 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2020-05-11 01:53:32', 3, 3 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2011-05-06 21:31:47', 3, 3 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2019-05-07 07:03:53', 3, 3 , 'ip 192.000.02', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2019-05-08 11:47:31', 3, 3 , 'ip 192.000.02', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2017-05-09 13:45:35', 3, 3 , 'ip 192.000.02', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2019-05-10 10:01:43', 3, 3 , 'ip 192.000.03', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2018-05-10 14:30:59', 3, 3 , 'ip 192.000.03', 'name school null');

INSERT INTO ROLE(role_name) VALUES('Administrador');
INSERT INTO ROLE(role_name) VALUES('Analista Júnior');
INSERT INTO ROLE(role_name) VALUES('Analista Sênior');
INSERT INTO ROLE(role_name) VALUES('Desenvolvedor Júnior');
INSERT INTO ROLE(role_name) VALUES('Desenvolvedor Sênior');

INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(1,1);
INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(2,1);
INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(3,1);
INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(4,1);
INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(5,1);
INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(6,2);
INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(7,3);
INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(8,4);
INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(9,2);
INSERT INTO PERSON_ROLES(user_user_id,roles_id) VALUES(10,5);