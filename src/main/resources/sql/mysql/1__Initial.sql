-- apply changes
create table ls_actions (
  id                            integer auto_increment not null,
  timestamp                     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  unique_user_id                varchar(255),
  type                          varchar(255),
  service                       varchar(255),
  provider                      varchar(255),
  constraint pk_ls_actions primary key (id)
);

create table ls_players (
  id                            integer auto_increment not null,
  unique_user_id                varchar(128),
  last_name                     varchar(16),
  ip_address                    varchar(255),
  password                      varchar(512),
  hashing_algorithm             integer,
  last_login                    datetime(6),
  registration_date             date,
  optlock                       bigint not null,
  constraint uq_ls_players_unique_user_id unique (unique_user_id),
  constraint pk_ls_players primary key (id)
);

