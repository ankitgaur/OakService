use oak;

--DROP TABLE IF EXISTS keyspace_name.table_name;

DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS blogs;
DROP TABLE IF EXISTS states;
DROP TABLE IF EXISTS incidents;
DROP TABLE IF EXISTS incident_types;
DROP TABLE IF EXISTS counters;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS users;

CREATE TABLE counters(
	name text PRIMARY KEY,
	val counter
);

CREATE TABLE articles (
    category text,
    updatedon bigint,
    approved boolean,
    approvedby text,
    approvedon timestamp,
    content text,
    createdby text,
    createdon bigint,
    displayimage text,
    hits bigint,
    rating int,
    title text,
    updatedby text,
    PRIMARY KEY (category, updatedon)
) WITH CLUSTERING ORDER BY (updatedon DESC);



CREATE TABLE blogs (
    category text,
    updatedon bigint,
    approved boolean,
    approvedby text,
    approvedon bigint,
    content text,
    createdby text,
    createdon bigint,
    displayimage text,
    hits bigint,
    rating int,
    title text,
    updatedby text,
    PRIMARY KEY (category, updatedon)
)WITH CLUSTERING ORDER BY (updatedon DESC);





CREATE TABLE incidents (
    incidenttype text,
    category text,
    createdon bigint,
    createdby text,
    description text,
    govt text,
    image text,
    questions text,
    reportdate bigint,
    state text,
    status text,
    type text,
 PRIMARY KEY (incidenttype,  createdon)
)WITH CLUSTERING ORDER BY (createdon DESC);


CREATE TABLE states (
    id bigint PRIMARY KEY,
    createdby text,
    createdon timestamp,
    currgovt text,
    govts text,
    name text,
    updatedby text,
    updatedon timestamp
);


CREATE TABLE incident_types (
    id bigint PRIMARY KEY,
    createdby text,
    createdon timestamp,
    name text,
    questions text,
    updatedby text,
    updatedon timestamp
);

CREATE TABLE roles (
    name text PRIMARY KEY,
    createdby text,
    updatedby text,
    createdon bigint,
    updateon bigint
);



CREATE TABLE groups (
    name text PRIMARY KEY,
    roles text,
    createdby text,
    updatedby text,
    createdon bigint,
    updateon bigint
);


CREATE TABLE users (
    email text PRIMARY KEY,
    name text,
    username text,
    password text,
    groups text,
    activated boolean,
    forgotpassword boolean,
    sendemail boolean,
    createdby text,
    updatedby text,
    createdon bigint,
    updateon bigint
)WITH CLUSTERING ORDER BY (email DESC);
