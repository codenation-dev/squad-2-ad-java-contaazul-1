INSERT INTO USER(name, email, password, created_at) VALUES('fulano',   'fulano@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO USER(name, email, password, created_at) VALUES('ciclano', 'ciclano@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO USER(name, email, password, created_at) VALUES('juliano', 'juliano@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO USER(name, email, password, created_at) VALUES('anapaula',  'paula@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');
INSERT INTO USER(name, email, password, created_at) VALUES('joãovitor', 'vitor@email.com', '$2a$10$nZCXsSOiZEyKK2xlY0NMw.y.71CDR02PkMNTRf76.M2vWBQQUdo5e', '2019-05-05 18:00:00');

INSERT INTO ENVIRONMENT(name) VALUES('development');
INSERT INTO ENVIRONMENT(name) VALUES('homologacao');
INSERT INTO ENVIRONMENT(name) VALUES('producao');

INSERT INTO LEVEL(name) VALUES('erro');
INSERT INTO LEVEL(name) VALUES('warm');
INSERT INTO LEVEL(name) VALUES('debug');

INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2019-05-05 18:00:00', 1, 1 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2019-05-10 18:00:00', 2, 2 , 'ip 192.000.01', 'name school null');
INSERT INTO LOG(created_at, level_id, environment_id, origin, description) VALUES('2019-05-11 18:00:00', 3, 3 , 'ip 192.000.01', 'name school null');



