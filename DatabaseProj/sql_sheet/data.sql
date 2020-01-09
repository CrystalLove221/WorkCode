--delete from supplier;
--delete from generic_product;
--delete from address;
--insert into prodgroup(supplier_name, product_name) values('Hate Inc', 'HateSyrum');
--delete from prodgroup;
--insert into sells_to(supplier_name, buyer_name) values('Love Inc', 'Love Inc');
--delete from sells_to where supplier_name = buyer_name;

--insert shipment samples***
--insert into shipment(supplier_name, target_name, building_num, street, city, supp_state, 
--                                groupid, quantity, unit_price, time_sent)
--values(

--insert into shipment(supplier_name, target_name, building_num, street, city, supp_state, groupid, quantity, unit_price, time_sent)
--values('Masochist Inc', 'Sadist Inc', 427, 'SafetyWord Drive', 'Lickle', 'AR', 11, 10, 9, systimestamp);

--insert into purchase(shipment_id, source_name, groupid, quantity, unit_cost, time_recvd)
--values(2000, 'Yandere Charity', 21, 4, 4.32, systimestamp);

--Regork information--
--insert into supplier values('Regork');


--select count(*)
--from product
--where groupid = 11
--group by groupid

--select * from prodgroup;
--select * from product order by product_id;
--select * from sells_to;
--select * from made_of;
--select * from supplier;
--select * from generic_product;
--select * from factory;
select * from shipment;
select * from purchase;
--select * from recall;


--describe purchase;