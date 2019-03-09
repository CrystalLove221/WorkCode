--1.

select id
from instructor, advisor
where id = i_id
group by id
having count(s_id) > 40


--2.

select id, name
from student natural join takes
group by id, name
having count(course_id) = 
(
    select min(count)
    from
    (
        select count(course_id) count
        from takes natural join student
        group by id
    )
)

--3.
select course_id, title
from takes natural full outer join course
where id is null

--4.

select course_id, title
from course
where course_id not in (select course_id from takes)

--5.

--include class not taken by anybody
--select distinct course_id
--from course natural full outer join takes

--exclude neglected course ;p
--select distinct course_id
--from course natural join takes


((select course_id
from course)
union
(select course_id
from takes))
minus
(select course_id
from takes
where id is not null)

--6.
--If a null course_id were allowed, the course that nobody takes might not appear in the results

--7.


