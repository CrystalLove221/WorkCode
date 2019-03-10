/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	Allocations are the objects that will keep track of what
	space the user has allocated. They are defined by the
	start and end positions in the allocator, which
	defines its size.

	Program #4
*/

#ifndef ALLOCATION_H
#define ALLOCATION_H

#include <stdio.h>

struct Allocation_t
{
	size_t size;
	size_t startOffset;

};

//create allocation for user at index passed by startOffset in the allocator
void makeAllocation(struct Allocation_t* this, size_t startOffset, size_t size);

void freeAllocation(struct Allocation_t* this); //destroy allocation metadata
size_t getSize(struct Allocation_t* this); //return size of allocation (bytes)
size_t getStart(struct Allocation_t* this); //return start index in memory block
size_t getEnd(struct Allocation_t* this); //return 1st available byte after allocation

//check if passed allocation conflicts with desired allocation
int doesOverlap(struct Allocation_t* this, size_t start, size_t size);

#endif
