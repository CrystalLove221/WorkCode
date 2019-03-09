--select title, semester, year, building
--from course natural join section
--where dept_name = 'Accounting'
--order by year, semester, title

--#1

select course_id c_id, sec_id s_id
from section
where building like 'W%' and (year = 2001 or year = 2002)
order by c_id, s_id

--#2

select to_char(student.id, '00000'), student.name
from student, advisor, instructor
where student.id = s_id and instructor.id = i_id and salary > 128000
order by student.name


--#3

select name, title
from instructor natural join teaches natural join course
where dept_name = 'Math' and semester = 'Fall'
union
select instructor.name, title
from instructor natural join teaches natural join course
where dept_name = 'Math' and semester = 'Spring'


--#4

select distinct to_char(id, '00000'), name, course.course_id, title
from student natural join takes, course
where student.dept_name <> course.dept_name and name like 'Fre%' and course.course_id = takes.course_id
order by name, title


--#5

select distinct to_char(s1.id, '00000'), s1.name
from student s1, student s2
where s1.name like '%e' and s1.dept_name = 'Math' and s2.dept_name = 'Physics' and s1.tot_cred > s2.tot_cred
order by s1.name