--Tyler Hogue
--CSE 241
--2-28-2019

create or replace procedure teachingRecord (p_id in instructor.id%type) is

--Declarations
cursor id_exist (x in instructor.id%type) is
    select count(*) from instructor where id = x;
cursor teachRecord (x in instructor.id%type) is
    select dept_name Department, course_id CNO, title, sec_id sec, semester sem, year, count(*) Enrollment
    from teaches join takes using (course_id, sec_id, semester, year) natural join course
	where teaches.id = x
	group by dept_name, course_id, title, sec_id, semester, year
	order by dept_name, course_id, year, semester desc;
    
dept instructor.dept_name%type;
cno teaches.course_id%type;
title course.title%type;
sec teaches.sec_id%type;
sem teaches.semester%type;
year teaches.year%type;
enrolled integer;
n integer;

--Main program

begin
if not regexp_like(p_id, '[0-9]+') or p_id <= 0 or p_id > 99999 then
    dbms_output.put_line('Invalid id. Range: 1 to 99999 (inclusive)');
    return;
end if;

open id_exist (p_id);
dbms_output.put_line('Start!');

fetch id_exist into n;
if n = 0 then
    dbms_output.put_line('Instructor not found. ID: ' || p_id);
    close id_exist;

else
    close id_exist;
    open teachRecord (p_id);
    fetch teachRecord into dept, cno, title, sec, sem, year, enrolled;
    if teachRecord%notfound then
        dbms_output.put_line('Instructor does not teach a course.');
        
    else
        dbms_output.put_line(rpad('Department', 15, ' ') || rpad('CNO', 9, ' ') || 
                                rpad('Title', 35, ' ') || rpad('Sec', 5, ' ') || rpad('Sem', 6, ' ') || 
                                rpad('Year', 5, ' ') || 'Enrolled');
        
        while teachRecord%found
        loop
            dbms_output.put_line(rpad(dept, 15, ' ') || rpad(cno, 9, ' ') || 
                                rpad(title, 35, ' ') || rpad(sec, 5, ' ') || rpad(sem, 6, ' ') || 
                                rpad(year, 5, ' ') || enrolled);
            fetch teachRecord into dept, cno, title, sec, sem, year, enrolled;
        end loop;
    end if;
end if;
end;