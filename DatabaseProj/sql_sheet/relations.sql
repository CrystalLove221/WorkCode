--populate tables
--Assumptions: all commodities (unbranded), one product per shipment, 
--each supplier makes multiple products; each group_id of products corresponds
--to one product. Recalls go by the group_id of products

--**doesn't make sure suppliers ship what they manufactured

drop table purchase;
drop table shipment;
drop table made_of;
drop table sells_to;
drop table recall;
drop table product;
drop table prodgroup;
drop table factory;
drop table supplier;
drop table generic_product;

create table generic_product
    (product_name varchar(30),
    curr_price numeric(10,2) check (curr_price > 0.00),
    primary key (product_name));

create table supplier
    (supplier_name varchar(30),
    primary key (supplier_name));

create table factory
    (supplier_name varchar(30) not null,
    building_num number(4),
    street varchar(30),
    city varchar(30),
    supp_state char(2),
    primary key (building_num, street, city, supp_state),
    foreign key (supplier_name) references supplier
                        on delete cascade);

create table prodgroup
    (groupid number(4) generated always as identity
                                    minvalue 1
                                    maxvalue 999
                                    increment by 1 start with 1
                                    cache 5,
    supplier_name varchar(30),
    product_name varchar(30),
    primary key (groupid),
    foreign key (product_name) references generic_product
                        on delete cascade,
    foreign key (supplier_name) references supplier
                        on delete cascade);

create table product
    (product_id number(4) generated always as identity
                                        minvalue 1000
                                        maxvalue 1999
                                        increment by 1
                                        cycle
                                        cache 5,
    supplier_name varchar(30),
    groupid number(4),
    product_name varchar(30),
    primary key (product_id),
    foreign key (product_name) references generic_product
                        on delete cascade,
    foreign key (groupid) references prodgroup
                        on delete cascade,
    foreign key (supplier_name) references supplier
                        on delete cascade);

create table recall
    (recall_id number(4) generated always as identity
                                        minvalue 4000
                                        maxvalue 4999
                                        increment by 1
                                        cycle
                                        cache 5,
    groupid number(4),
    primary key (recall_id),
    foreign key (groupid) references prodgroup
                        on delete cascade);


--recursive schema
create table sells_to
    (supplier_name varchar(30),
    buyer_name varchar(30),
    primary key (supplier_name, buyer_name),
    foreign key (supplier_name) references supplier
                        on delete cascade,
    foreign key (buyer_name) references supplier
                         on delete set null);

--recursive relationship on group of physical products
create table made_of
    (groupid number(4),
    part_grpid number(4),
    primary key (groupid, part_grpid),
    foreign key (groupid) references prodgroup
                        on delete cascade,
    foreign key (part_grpid) references prodgroup
                        on delete cascade);


--each shipment has only one product     
create table shipment 
    (shipment_id number(4) generated always as identity
                                        minvalue 2000
                                        maxvalue 2999
                                        increment by 1
                                        cycle
                                        cache 5,
    supplier_name varchar(30),
    target_name varchar(30),
    building_num number(4),
    street varchar(30),
    city varchar(30),
    supp_state char(2),
    groupid number(4),
    quantity integer check (quantity > 0),
    unit_price numeric(10,2) check (unit_price > 0.00),
    time_sent timestamp,
    primary key (shipment_id),
    foreign key (supplier_name) references supplier
                        on delete cascade,
    foreign key (target_name) references supplier
                        on delete cascade,
    foreign key (building_num, street, city, supp_state) references factory
                        on delete cascade,
    foreign key (groupid) references prodgroup
                        on delete cascade);

create table purchase
    (purchase_id number(4) generated always as identity
                                        minvalue 3000
                                        maxvalue 3999
                                        increment by 1
                                        cycle
                                        cache 5,
    shipment_id number(4),
    source_name varchar(30),
    groupid number(4),
    quantity integer, -- must check constraint based on ship_id with JDBC
    unit_cost numeric(10,2), -- must check constraint based on ship_id with JDBC
    time_recvd timestamp,
    primary key (purchase_id),
    foreign key (groupid) references prodgroup
                        on delete cascade,
    foreign key (source_name) references supplier
                        on delete cascade,
    foreign key (shipment_id) references shipment
                        on delete cascade);
                        
