-- auto-generated definition
create table novel
(
  id       int auto_increment
    primary key,
  title    varchar(100)  null,
  content  text          null,
  next_url text          null,
  url      varchar(1000) null,
  constraint novel_url_uindex
  unique (url)
);