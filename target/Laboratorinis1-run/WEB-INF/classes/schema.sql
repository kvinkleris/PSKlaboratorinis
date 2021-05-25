drop table if exists foresttree;
drop table if exists forest;
drop table if exists tree;
drop table if exists treetype

create table treetype(
id identity,
name varchar(255)
)

create table foresttypetree(
id identity,
tree_id int,
treetype_id int,
foreign key(treetype_id) references treetype(id),
foreign key(tree_id) references tree(id)
)



create table forest(
    id int NOT NULL AUTO_INCREMENT,
    name varchar(255),
    description varchar(255)
);

create table tree(
    id identity,
    name varchar(255),
    description varchar(255),
    existingnumber INT,
    VERSION INT4 default 0
);

create table foresttree(
    id identity,
    forest_id int,
    tree_id int,
    foreign key (forest_id) references forest(id),
    foreign key (tree_id) references tree(id)
);



insert into forestree(forest_id, tree_id) values (1, 1);

select * from PUBLIC.TREE