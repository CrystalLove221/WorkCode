clc
clear all
close all

data=[386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
386	1	0
385	1	0
385	1	0
386	1	0
383	1	0
381	1	0
380	1	0
373	1	0
368	1	0
369	1	0
370	1	0
369	1	0
375	1	0
373	1	0
373	1	0
378	1	0
374	1	0
374	1	0
374	1	0
374	1	0
374	1	0
374	1	0
384	1	0
686	1	0
797	1	0
807	1	0
807	1	0
815	1	0
812	1	0
807	1	0
802	1	0
797	1	0
736	1	0
500	1	0
418	1	0
388	1	0
373	1	0
368	1	0
365	1	0
365	1	0
364	1	0
362	1	0
361	1	0
364	1	0
361	1	0
366	1	0
367	1	0
369	1	0
362	1	0
361	1	0
360	1	0
362	1	0
361	1	0
361	1	0
361	1	0
77	1	0
28	1	0
24	1	0
21	1	0
20	1	0
19	1	0
19	1	0
18	1	0
18	1	0
18	1	0
19	1	0
18	1	0
17	1	0
18	1	0
352	0	1
354	0	1
357	0	1
358	0	1
359	0	1
359	0	1
360	0	1
360	0	1
360	0	1
354	0	0
361	0	0
361	0	0
360	0	0
361	0	0
360	0	0
361	0	0
361	0	0
362	0	0
35	1	0
32	1	0
31	1	0
29	1	0
28	1	0
29	1	0
29	1	0
28	1	0
28	1	0
28	1	0
27	1	0
27	1	0
28	1	0
27	1	0
27	1	0
27	1	0
27	1	0
28	1	0
27	1	0
27	1	0
28	1	0
27	1	0
28	1	0
27	1	0
27	1	0
27	1	0
27	1	0
26	1	0
27	1	0
26	1	0
27	1	0
28	1	0
26	1	0
27	1	0
27	1	0
27	1	0
27	1	0
27	1	0
27	1	0
26	1	0
27	1	0
27	1	0
27	1	0
26	1	0
28	1	0
26	1	0
27	1	0
27	1	0
27	1	0
27	1	0
26	1	0
27	1	0
28	1	0
26	1	0
27	1	0
26	1	0
27	1	0
28	1	0
28	1	0
23	1	0
27	1	0
27	1	0
27	1	0
28	1	0
27	1	0
27	1	0
27	1	0
27	1	0
27	1	0
28	1	0
27	1	0
26	1	0
26	1	0
28	1	0
342	0	1
350	0	1
352	0	1
355	0	1
357	0	1
357	0	1
355	0	1
359	0	1
359	0	1
357	0	0
360	0	0
361	0	0
360	0	0
361	0	0
362	0	0
363	0	0
362	0	0
362	0	0
34	1	0
31	1	0
29	1	0
29	1	0
29	1	0
28	1	0
27	1	0
27	1	0
27	1	0
28	1	0
27	1	0
27	1	0
28	1	0
27	1	0
27	1	0
27	1	0
27	1	0
26	1	0
27	1	0
27	1	0
26	1	0
27	1	0
27	1	0
27	1	0
27	1	0
26	1	0
27	1	0
25	1	0
30	1	0
27	1	0
27	1	0
27	1	0
27	1	0
27	1	0
26	1	0
26	1	0
27	1	0
27	1	0
26	1	0
27	1	0
26	1	0
26	1	0
26	1	0
27	1	0
26	1	0
27	1	0
27	1	0
26	1	0
26	1	0
26	1	0
26	1	0
26	1	0
27	1	0
27	1	0
26	1	0
26	1	0
27	1	0
27	1	0
27	1	0
26	1	0
27	1	0
26	1	0
26	1	0
26	1	0
27	1	0
26	1	0
26	1	0
26	1	0
27	1	0
25	1	0
26	1	0
26	1	0
26	1	0
344	0	1
349	0	1
354	0	1
356	0	1
357	0	1
358	0	1
359	0	1
359	0	1
359	0	1
360	0	0
361	0	0
361	0	0
361	0	0
361	0	0
361	0	0
362	0	0
362	0	0
360	0	0
43	1	0
40	1	0
38	1	0
38	1	0
37	1	0
37	1	0
36	1	0
37	1	0
36	1	0
36	1	0
37	1	0
36	1	0
36	1	0
33	1	0
36	1	0
34	1	0
34	1	0
36	1	0
36	1	0
35	1	0
35	1	0
36	1	0
35	1	0
35	1	0
35	1	0
35	1	0
34	1	0
35	1	0
35	1	0
33	1	0
36	1	0
35	1	0
36	1	0
34	1	0
34	1	0
35	1	0
35	1	0
35	1	0
35	1	0
34	1	0
37	1	0
35	1	0
34	1	0
35	1	0
34	1	0
35	1	0
35	1	0
35	1	0
34	1	0
35	1	0
35	1	0
35	1	0
35	1	0
36	1	0
34	1	0
36	1	0
35	1	0
35	1	0
35	1	0
35	1	0
33	1	0
88	1	0
37	1	0
36	1	0
35	1	0
34	1	0
35	1	0
35	1	0
35	1	0
35	1	0
36	1	0
34	1	0
33	1	0
32	1	0
347	0	1
355	0	1
357	0	1
361	0	1
361	0	1
361	0	1
362	0	1
363	0	1
363	0	1
360	0	0
364	0	0
389	0	0
365	0	0
366	0	0
365	0	0
365	0	0
362	0	0
365	0	0
38	1	0
32	1	0
33	1	0
32	1	0
31	1	0
31	1	0
30	1	0
30	1	0
30	1	0
30	1	0
30	1	0
30	1	0
29	1	0
29	1	0
29	1	0
31	1	0
31	1	0
30	1	0
30	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
30	1	0
30	1	0
30	1	0
29	1	0
28	1	0
29	1	0
30	1	0
30	1	0
29	1	0
29	1	0
29	1	0
28	1	0
28	1	0
30	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
27	1	0
29	1	0
29	1	0
28	1	0
28	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
29	1	0
28	1	0
29	1	0
31	1	0
29	1	0
29	1	0
29	1	0
29	1	0
28	1	0
29	1	0
28	1	0
29	1	0
28	1	0
349	0	1
355	0	1
358	0	1
359	0	1
360	0	1
362	0	1
362	0	1
363	0	1
363	0	1
399	0	0
364	0	0
364	0	0
364	0	0
364	0	0
365	0	0
365	0	0
365	0	0
364	0	0
43	1	0
40	1	0
39	1	0
38	1	0
37	1	0
36	1	0
36	1	0
36	1	0
36	1	0
36	1	0
36	1	0
36	1	0
35	1	0
35	1	0
35	1	0
35	1	0
36	1	0
35	1	0
36	1	0
35	1	0
35	1	0
35	1	0
36	1	0
35	1	0
35	1	0
35	1	0
35	1	0
35	1	0
35	1	0
34	1	0
35	1	0
35	1	0
34	1	0
35	1	0
35	1	0
35	1	0
35	1	0
35	1	0
35	1	0
35	1	0
35	1	0
34	1	0
35	1	0
35	1	0
35	1	0
35	1	0
34	1	0
34	1	0
37	1	0
35	1	0
35	1	0
35	1	0
35	1	0
34	1	0
35	1	0
35	1	0
36	1	0
35	1	0
35	1	0
35	1	0
35	1	0
35	1	0
33	1	0
35	1	0
35	1	0
34	1	0
35	1	0
34	1	0
34	1	0
34	1	0
34	1	0
34	1	0
35	1	0
33	1	0
348	0	1
354	0	1
358	0	1
360	0	1
360	0	1
361	0	1
362	0	1
362	0	1
361	0	1
363	0	0
363	0	0
364	0	0
363	0	0
364	0	0
363	0	0
364	0	0
364	0	0
365	0	0
364	1	0
364	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
366	1	0
359	1	0
365	1	0
365	1	0
366	1	0
365	1	0
366	1	0
365	1	0
365	1	0
366	1	0
364	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
347	1	0
365	1	0
365	1	0
365	1	0
361	1	0
367	1	0
365	1	0
365	1	0
365	1	0
366	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
366	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
363	1	0
365	1	0
365	1	0
365	1	0
365	1	0
367	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
364	1	0
365	1	0
366	1	0
365	1	0
365	1	0
365	1	0
365	1	0
366	1	0
365	1	0
365	1	0
365	1	0
365	1	0
364	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
366	1	0
366	1	0
365	1	0
368	1	0
365	1	0
363	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
366	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
367	1	0
365	1	0
366	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
368	1	0
365	1	0
364	1	0
364	1	0
364	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
366	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
366	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
365	1	0
366	1	0
365	1	0
146	1	0
38	1	0
358	0	1
362	0	1
364	0	1
364	0	1
365	0	1
365	0	1
365	0	1
365	0	1
366	0	1
361	0	0
365	0	0
365	0	0
366	0	0
363	0	0
365	0	0
365	0	0
366	0	0
365	0	0
33	1	0
29	1	0
28	1	0
27	1	0
26	1	0
27	1	0
25	1	0
25	1	0
24	1	0
24	1	0
25	1	0
24	1	0
25	1	0
25	1	0
25	1	0
25	1	0
24	1	0
24	1	0
25	1	0
25	1	0
25	1	0
24	1	0
24	1	0
24	1	0
23	1	0
24	1	0
25	1	0
25	1	0
24	1	0
25	1	0
25	1	0
24	1	0
24	1	0
24	1	0
26	1	0
25	1	0
25	1	0
24	1	0
23	1	0
25	1	0
25	1	0
25	1	0
24	1	0
25	1	0
24	1	0
24	1	0
24	1	0
24	1	0
24	1	0
24	1	0
24	1	0
24	1	0
25	1	0
24	1	0
24	1	0
25	1	0
24	1	0
24	1	0
25	1	0
25	1	0
25	1	0
25	1	0
24	1	0
24	1	0
24	1	0
24	1	0
24	1	0
24	1	0
25	1	0
24	1	0
24	1	0
24	1	0
24	1	0
346	0	1
354	0	1
356	0	1
358	0	1
361	0	1
361	0	1
362	0	1
362	0	1
363	0	1
361	0	0
363	0	0
364	0	0
364	0	0
365	0	0
365	0	0
365	0	0
365	0	0
365	0	0
363	1	0
365	1	0
363	1	0
365	1	0
365	1	0
365	1	0
366	1	0
365	1	0
364	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
366	1	0
364	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
367	1	0
366	1	0
367	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
367	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
367	1	0
366	1	0
366	1	0
366	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
365	1	0
366	1	0
366	1	0
366	1	0
365	1	0
367	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
367	1	0
366	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
366	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
369	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
365	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
359	1	0
358	1	0
366	1	0
365	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
366	1	0
367	1	0
366	1	0
365	1	0
366	1	0
366	1	0
365	1	0
372	1	0
380	1	0
389	1	0
387	1	0
386	1	0
386	1	0
385	1	0
385	1	0
385	1	0
384	1	0
384	1	0
385	1	0
384	1	0
384	1	0
385	1	0
385	1	0
385	1	0
386	1	0
385	1	0
384	1	0
385	1	0
385	1	0
385	1	0
384	1	0
384	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
384	1	0
384	1	0
385	1	0
384	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0
385	1	0];

t=0:.1:108.8;

data(:,1)=data(:,1)/2+200;
data(:,2)=data(:,2)*90+100;
data(:,3)=data(:,3)*90;

plot(t,data(:,1),'black');
hold on;
plot(t,data(:,2),'red');
hold on;
plot(t,data(:,3),'blue');
hold on;

title('Sensor, Upper Servo and Lower Servo vs. Time (Tyler Hogue and Karl Calden)');
legend('Sensor value','upper servo','lower servo');
xlabel('Time (seconds)');
ylabel('Sensor and upper/lower servo value');