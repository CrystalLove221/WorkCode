# Default make is to make struct
all: List

List: Node.o List.o prog3.o ListTraverse.o
	gcc -g -Wwrite-strings -Wall -Werror -o List Node.o List.o prog3.o ListTraverse.o

Node.o: Node.h Node.c
	gcc -g -Wwrite-strings -Wall -Werror -c Node.c

List.o: List.c List.h
	gcc -g -Wwrite-strings -Wall -Werror -c List.c

prog3.o: prog3.c
	gcc -g -Wwrite-strings -Wall -Werror -c prog3.c

.PHONY:
clean:
	rm out List.o Node.o prog3.o

.PHONY:
tar:
	tar -czvf ../Prog3.tgz ../Prog3

