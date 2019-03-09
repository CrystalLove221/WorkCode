
--1.
--select year, month, day, shares_traded, rank() over (order by shares_traded desc) MostShares
--from nyse

--2.
--select year, month, day, sum(shares_traded) Shares, sum(num_trades) trades, sum(dollar_volume) volume
--from nyse
--group by rollup(year, month, day)

--3.
--2477 because (year), (year, day), (month, day), (month), and (day) grouping combinations will be included
--query below
--select year, month, day, sum(shares_traded) Shares, sum(num_trades) trades, sum(dollar_volume) volume
--from nyse
--group by cube(year, month, day)

--4.
--
--select year, month, sum(monthdv) over (partition by year order by month 
--                                                                        rows between 2 preceding and current row) totaldv, 
--                                                                        avg_volume.avgdv
--from (
--            select year, month, sum(dollar_volume) monthdv
--            from nyse
--            group by year, month
--        ) natural join (
--                            select year, month, avg(totaldv) over (partition by year order by month 
--                                                            rows between 2 preceding and current row) avgdv
--                            from (
--                                        select year, month, sum(dollar_volume) totaldv
--                                        from nyse
--                                        group by year, month
--                                        order by year
--                                    )
--                            ) avg_volume

--5.
--group by rollup(a), rollup(b), rollup(c), rollup(d)

--example:
select year, month, day, sum(shares_traded) Shares, sum(num_trades) trades, sum(dollar_volume) volume
from nyse
group by rollup(year), rollup(month), rollup(day)