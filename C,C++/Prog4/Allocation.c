/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	(read header file)

	Program #4
*/

#include "Allocation.h"
#include <stdio.h>

void makeAllocation(struct Allocation_t* this, size_t startOffset, size_t size)
{
	this->startOffset = startOffset;
	this->size = size;

	return;
}

void freeAllocation(struct Allocation_t* this)
{
	this->size = 0;
	this->startOffset = 0;
	return;
}

size_t getSize(struct Allocation_t* this)
{
	return this->size;
}

size_t getStart(struct Allocation_t* this)
{
	return this->startOffset;
}

size_t getEnd(struct Allocation_t* this)
{
	return this->startOffset+this->size;
}

//0=false, 1: true
//start and size: range to be allocated
int doesOverlap(struct Allocation_t* this, size_t start, size_t size)
{

	if(start < getStart(this))
	{
		if(start+size > getStart(this))
		{
			return 1;
		}
	}

	if(start >= getStart(this) && start < getEnd(this))
	{
		return 1;
	}

	return 0;
}
