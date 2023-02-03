create table if not exists WORKER_NODE
(
    id          bigint      not null auto_increment comment 'auto increment id',
    host_name   varchar(64) not null comment 'host name',
    port        varchar(64) not null comment 'port',
    type        int         not null comment 'node type: actual or container',
    launch_date date        not null comment 'launch date',
    modified    timestamp   not null default current_timestamp comment 'modified time',
    created     timestamp   not null default current_timestamp comment 'created time',
    primary key (id)
) comment = 'db workerid assigner for uid generator', engine = innodb;