insert into user (id, username, password, active)
    values (1, 'admin', 'admin', true);

insert into user_role (user_id, roles)
    values (1, 'USER'), (1, 'ADMIN');

update user set password='$2a$08$xOnV2BqRBmJJlE79eSa/kukr448gweUee9an77EW7au1.tZ.SZdjO' where id=1;