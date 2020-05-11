INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('1', 'Alice Santos', 'alices@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('2', 'Karina Padilha', 'karinapadilha@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('3', 'Letícia Buss', 'leticiabuss@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('4', 'Marlei Silveira', 'marleis@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('5', 'Natália Suzuki', 'nataliasuzuki@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2011-05-06 21:31:51');
INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('6', 'Ana Lúcia Pereira', 'analuciapereira@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-07 07:03:24');
INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('7', 'Beatriz Lousiana Souza', 'blousianasouza@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-08 11:47:31');
INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('8', 'Rodrigo Valles', 'rodrigovalles@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2017-05-09 13:45:35');
INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('9', 'Wanessa Longue', 'wanessa.longue@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-10 10:01:43');
INSERT INTO PERSON(user_id, name, email, password, created_at) VALUES('10', 'Quevin Flores', 'quevinflores@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2018-05-10 14:30:59');

INSERT INTO ENVIRONMENT(id, name) VALUES('1', 'development');
INSERT INTO ENVIRONMENT(id, name) VALUES('2', 'homologacao');
INSERT INTO ENVIRONMENT(id, name) VALUES('3', 'producao');

INSERT INTO LEVEL(id, name) VALUES('1', 'erro');
INSERT INTO LEVEL(id, name) VALUES('2', 'warm');
INSERT INTO LEVEL(id, name) VALUES('3', 'debug');

INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('1', '2019-05-05 17:22:21', 1, 1 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('2', '2019-05-10 18:43:10', 2, 2 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('3', '2019-05-11 18:50:11', 3, 3 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('4', '2020-05-11 01:53:32', 3, 3 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('5', '2011-05-06 21:31:47', 3, 3 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('6', '2019-05-07 07:03:53', 3, 3 , 'ip 192.000.02', 'name school null');
INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('7', '2019-05-08 11:47:31', 3, 3 , 'ip 192.000.02', 'name school null');
INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('8', '2017-05-09 13:45:35', 3, 3 , 'ip 192.000.02', 'name school null');
INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('9', '2019-05-10 10:01:43', 3, 3 , 'ip 192.000.03', 'name school null');
INSERT INTO LOG(log_id, created_at, level_id, environment_id, origin, description) VALUES('10', '2018-05-10 14:30:59', 3, 3 , 'ip 192.000.03', 'name school null');

INSERT INTO ROLE(id, role_name) VALUES('1', 'Administrador');
INSERT INTO ROLE(id, role_name) VALUES('2', 'Analista Júnior');
INSERT INTO ROLE(id, role_name) VALUES('3', 'Analista Sênior');
INSERT INTO ROLE(id, role_name) VALUES('4', 'Desenvolvedor Júnior');
INSERT INTO ROLE(id, role_name) VALUES('5', 'Desenvolvedor Sênior');

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