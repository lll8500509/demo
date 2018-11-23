-- auto-generated definition
create table post
(
  id              bigint auto_increment
    primary key,
  title           varchar(200)                        null,
  url             varchar(1000)                       null,
  author          varchar(30)                         null,
  author_url      varchar(1000)                       null,
  author_url_md5  varchar(200)                        null,
  type            int                                 null,
  reply_num       bigint                              null,
  last_reply_time varchar(30)                         null,
  add_time        timestamp                           null,
  update_time     timestamp default CURRENT_TIMESTAMP not null,
  constraint Post_url_uindex
  unique (url)
);


