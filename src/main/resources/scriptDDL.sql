-- Database: Error-Center

-- DROP DATABASE "Error-Center";

CREATE DATABASE "Error-Center"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Portuguese_Brazil.1252'
    LC_CTYPE = 'Portuguese_Brazil.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;

COMMENT ON DATABASE "Error-Center"
    IS 'Repositório central de logs e erros.';
    
---------------------------------------------------------------------

--Drop Table Level;

-- Tabela Level
CREATE TABLE Level(
   id serial PRIMARY KEY,
   name VARCHAR (60) UNIQUE NOT NULL   
);

----------------------------------------------------------------------------------------
--Drop Table Environment;

-- Tabela Environment
CREATE TABLE Environment(
   id serial PRIMARY KEY,
   name VARCHAR (60) UNIQUE NOT NULL   
);

----------------------------------------------------------------------------------------
--Drop Table TBUser;

-- Tabela TBUser
CREATE TABLE TBUser(
   user_id serial PRIMARY KEY,
   name VARCHAR (60), 
   email VARCHAR(60) UNIQUE NOT NULL,  
   password VARCHAR(60) NOT NULL,  
   created_at timestamp
);


----------------------------------------------------------------------------------------
--Drop Table Log;

-- Tabela Log
CREATE TABLE Log(
   log_id serial PRIMARY KEY,
   created_at timestamp,
   level_id int,
   environment_id int,
   origin VARCHAR (60),
   deion  VARCHAR (1000)
);

ALTER TABLE Log 
ADD CONSTRAINT const_fk_level_log FOREIGN KEY (level_id) REFERENCES Level (id);

ALTER TABLE Log 
ADD CONSTRAINT const_fk_envi_log FOREIGN KEY (environment_id) REFERENCES Environment (id);

--------------------------------------------------------------------------------------
-- DROP TABLE  Role;

CREATE TABLE  Role(
     id serial PRIMARY KEY,
     role_name VARCHAR(40)
);

--------------------------------------------------------------------------------------
-- DROP TABLE TBUser;

CREATE TABLE  TBUser_Role(
     user_user_id int,
     roles_id int,
    PRIMARY KEY (user_user_id, roles_id)
);

ALTER TABLE TBUser_Role
ADD CONSTRAINT const_fk_user_id FOREIGN KEY (user_user_id) REFERENCES TBUser(user_id);

ALTER TABLE TBUser_Role
ADD CONSTRAINT const_fk_role_id FOREIGN KEY (roles_id) REFERENCES Role(id);
