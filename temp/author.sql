-- auto-generated definition
create table author
(
  author_id      bigint auto_increment
    primary key,
  name           varchar(30)                         null,
  author_url     varchar(1000)                       not null,
  author_url_md5 varchar(200)                        not null,
  post_num       int                                 null,
  post_age       int                                 null,
  update_time    timestamp default CURRENT_TIMESTAMP not null,
  constraint author_author_url_md5_uindex
  unique (author_url_md5)
);

