create table IF NOT EXISTS TBL_URL_ENCODED (
id int, 
URL_ORIGINAL varchar,
URL_ENCODED varchar,
URL_HASHCODE bigint,
CREATED_DATE TIMESTAMP 
);


create table IF NOT EXISTS TBL_ENCODING_MAPPER (
ID int, 
URL_TOKEN_KEY varchar,
URL_TOKEN_VALUE varchar,
URL_HASHCODE bigint
);