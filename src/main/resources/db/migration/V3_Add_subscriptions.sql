create table user_subscriptions (
    channel_id bigint not null references user (id),
    subscriber_id bigint not null references user (id),
    primary key (channel_id, subscriber_id)
)

alter table user_subscriptions add constraint user_channel_fk foreign key (channel_id) references user (id);
alter table user_subscriptions add constraint user_subscriber_fk foreign key (subscriber_id) references user (id);