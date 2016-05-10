use oak;

--DROP TABLE IF EXISTS keyspace_name.table_name;

DROP TABLE IF EXISTS articles;
DROP TABLE IF EXISTS article_categories;
DROP TABLE IF EXISTS blog_entries;
DROP TABLE IF EXISTS blogs;
DROP TABLE IF EXISTS blog_categories;
DROP TABLE IF EXISTS states;
DROP TABLE IF EXISTS incidents;
DROP TABLE IF EXISTS incident_types;
DROP TABLE IF EXISTS counters;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS groups;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS images;
DROP TABLE IF EXISTS placements;
DROP TABLE IF EXISTS videos;
DROP TABLE IF EXISTS pages;
DROP TABLE IF EXISTS sections;

CREATE TABLE article_categories(
	id bigint PRIMARY KEY,
    name text,
    createdon bigint,
    description text,
    createdby text,
    updatedby text,
    updatedon bigint,
    displayimage text      
);

CREATE TABLE placements(
 page text,
 section text,
 position int,
 title text,
 intro text,
 img text,
 link text,
 createdby text,
 createdon bigint,
 updatedby text,
 updatedon bigint,
 PRIMARY KEY ((page,section),position)
)WITH CLUSTERING ORDER BY (position ASC);

CREATE TABLE counters(
	name text PRIMARY KEY,
	val counter
);

CREATE TABLE images (
  prefix text,  
  name text,
  kbsize bigint,
  img blob, 
  createdby text,
  createdon bigint,
  PRIMARY KEY (prefix,createdon)
) WITH CLUSTERING ORDER BY (createdon DESC);

CREATE TABLE articles (
    category text,
    updatedon bigint,
    approved boolean,
    approvedby text,
    approvedon bigint,
    content text,
    intro text,
    createdby text,
    createdon bigint,
    displayimage text,
    hits bigint,
    rating int,
    title text,
    updatedby text,
    PRIMARY KEY (category, createdon)
) WITH CLUSTERING ORDER BY (createdon DESC);

CREATE TABLE blog_categories(
	id bigint PRIMARY KEY,
    name text,
    createdon bigint,
    description text,
    createdby text,
    updatedby text,
    updatedon bigint,
    displayimage text      
);

CREATE TABLE blogs (
    category text,
    createdon bigint,
    title text,
    description text,
    blogHash text,
    createdby text,
    updatedby text,
    updatedon bigint,
    displayimage text,    
    rating bigint, 
    hits bigint,
    PRIMARY KEY (category, createdon)
) WITH CLUSTERING ORDER BY (createdon DESC);


CREATE TABLE blog_entries (
    blog text,
    updatedon bigint,
    approved boolean,
    approvedby text,
    approvedon bigint,
    content text,
    createdby text,
    createdon bigint,
    displayimage text,    
    rating int,
    title text,
    updatedby text,
    hits bigint,
    PRIMARY KEY (blog, createdon)
)WITH CLUSTERING ORDER BY (createdon DESC);

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
    createdon bigint,
    currgovt text,
    govts text,
    name text,
    abbr text,
    updatedby text,
    updatedon bigint
);


CREATE TABLE incident_types (
    id bigint PRIMARY KEY,
    createdby text,
    createdon bigint,
    name text,
    questions text,
    updatedby text,
    updatedon bigint
);

CREATE TABLE roles (
    name text PRIMARY KEY,
    createdby text,
    updatedby text,
    createdon bigint,
    updatedon bigint
);



CREATE TABLE groups (
    name text PRIMARY KEY,
    roles text,
    createdby text,
    updatedby text,
    createdon bigint,
    updatedon bigint
);


CREATE TABLE users (
    email text,
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
    updatedon bigint,
    PRIMARY KEY (email)
);


CREATE TABLE pages (
    name text,
    link text,
    parent text,
    createdby text,
    updatedby text,
    createdon bigint,
    updatedon bigint,
    PRIMARY KEY (name)
);

CREATE TABLE sections (
    page text,
	name text,    
    createdby text,
    updatedby text,
    createdon bigint,
    updatedon bigint,
    PRIMARY KEY (page,name)
);

CREATE TABLE videos (
    category text,     
    title text,
    intro text,    
    videourl text,
    videoimgurl text,
    hits bigint,
    rating int,
    approved boolean,
    approvedby text,
    approvedon bigint,
    createdby text,
    updatedby text,
    createdon bigint,
    updatedon bigint,
    PRIMARY KEY (category, createdon)
) WITH CLUSTERING ORDER BY (createdon DESC);

CREATE TABLE comments (
    service text,     
    service_id text,
    createdon bigint,
    comment text,     
    createdby text,
    updatedby text,    
    updatedon bigint,
    PRIMARY KEY ((service,service_id), createdon)
) WITH CLUSTERING ORDER BY (createdon DESC);

CREATE TABLE forum_categories(
	id bigint PRIMARY KEY,
    name text,
    createdon bigint,
    description text,
    createdby text,
    updatedby text,
    updatedon bigint,
    displayimage text      
);

CREATE TABLE forum_topics (
    category text,
    createdon bigint,
    title text,
    description text,
    createdby text,
    updatedby text,
    updatedon bigint,
    displayimage text,    
    rating bigint, 
    hits bigint,
    PRIMARY KEY (category, createdon)
) WITH CLUSTERING ORDER BY (createdon DESC);

CREATE TABLE forum_posts (
    topic text,
    updatedon bigint,
    approved boolean,
    approvedby text,
    approvedon bigint,
    content text,
    createdby text,
    createdon bigint,
    displayimage text,    
    rating int,
    title text,
    updatedby text,
    hits bigint,
    PRIMARY KEY (topic, createdon)
)WITH CLUSTERING ORDER BY (createdon DESC);
