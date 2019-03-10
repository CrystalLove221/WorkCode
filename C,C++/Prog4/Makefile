persocom: prog42

prog42: prog42.o Allocator.o Allocation.o printAllocations.o
	gcc -g -Wwrite-strings -Wall -Werror -o prog42 prog42.o Allocator.o Allocation.o printAllocations.o

prog42.o: prog42.c
	gcc -g -Wwrite-strings -Wall -Werror -c prog42.c

.PHONY:
disable:
	rm prog42 prog42.o Allocator.o Allocation.o

#--------------------------------------------------

robot: prog4

prog4: prog4.o Allocation.o Allocator.o
	gcc -g -Wwrite-strings -Wall -Werror -o prog4 prog4.o Allocation.o Allocator.o

Allocation.o: Allocation.h Allocation.c
	gcc -g -Wwrite-strings -Wall -Werror -c Allocation.c

Allocator.o: Allocator.h Allocator.c
	gcc -g -Wwrite-strings -Wall -Werror -c Allocator.c

.PHONY:
degauss:
	rm prog4 Allocation.o Allocator.o
