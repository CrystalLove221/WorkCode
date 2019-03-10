/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	(read header file)

	Program #4
*/

#include "Allocator.h"
#include "Allocation.h"
#include <stdio.h>
#include <stdlib.h>

struct Allocation_t** expandAllocList(struct Allocation_t** list, size_t size, size_t* capacity);
void makeAllocator(struct Allocator_t* this, size_t capacity)
{
	if(capacity % 16 != 0)
	{
		capacity = capacity + (16 - capacity % 16);
	}

	this->capacity = capacity;
	this->memUsed = 0;
	this->memory = malloc(capacity);

	this->allocList = NULL;
	this->listSize = 0;
	this->listCapacity = 1;

	return;
}

void freeAllocator(struct Allocator_t* this)
{
	for(int i = 0; i < this->listSize; i++)
	{
		free(this->allocList[i]);
		this->allocList[i] = NULL;
	}

	free(this->allocList);
	this->allocList = NULL;

	free(this->memory);
	this->memory = NULL;

	return;
}

//resize allocation tracker
struct Allocation_t** expandAllocList(struct Allocation_t** list, size_t size, size_t* capacity)
{
	if(size >= *capacity)
	{
		*capacity = *capacity*2+1;

		struct Allocation_t** temp = NULL;
		temp = (struct Allocation_t**) malloc(*capacity*sizeof(struct Allocation_t*));

		for(int i = 0; i < size; i++)
		{
			temp[i] = list[i];
		}

		free(list);
		list = temp;
	}

	return list;
}

void* allocate(struct Allocator_t* this, size_t allocSize)
{

	if(this->allocList == NULL)
	{
		this->allocList = (struct Allocation_t**) malloc(this->listCapacity*sizeof(struct Allocation_t*));
	}

	if(allocSize % 16 != 0)
	{
		allocSize = allocSize + (16-allocSize%16);
	}

	if(allocSize > this->capacity || allocSize > this->capacity-this->memUsed)
	{
		return NULL;
	}

	this->allocList = expandAllocList(this->allocList, this->listSize, &this->listCapacity);

	//look for spot in allocator to place allocation
	int start = 0;
	int overlap = 0;
	void* tempMem = this->memory;
	for(start = 0; start < this->capacity; start+=16)
	{
		for(int i = 0; i < this->listSize; i++)
		{
			if(doesOverlap(this->allocList[i], start, allocSize))
			{
				overlap = 1;
				break;
			}
		}

		if(!overlap)
		{
			break;
		}

		overlap = 0;
		tempMem = tempMem+16;
	}

	if(!overlap)
	{
		//boundary check
		if(start+allocSize > this->capacity)
		{
			return NULL;
		}

		struct Allocation_t* newAlloc = (struct Allocation_t*) malloc(sizeof(struct Allocation_t));
		makeAllocation(newAlloc, start, allocSize);
		this->allocList[this->listSize] = newAlloc;
		this->memUsed += allocSize;
		this->listSize++;
		return tempMem;
	}

	return NULL;
}

void deallocate(struct Allocator_t* this, void* alloc)
{

	if(alloc == NULL)
	{
		return;
	}

	//remove allocation, reassign list pointers, decrement size of list
	struct Allocation_t* structAlloc = (struct Allocation_t*) alloc;
	for(int i = 0; i < this->listSize; i++)
	{
		if(this->memory+getStart(this->allocList[i]) == alloc)
		{
			this->memUsed -= getSize(structAlloc);
			free(this->allocList[i]);
			this->allocList[i] = NULL;

			int j = 0;
			for(j = i; j < this->listSize-1; j++)
			{
				this->allocList[j] = this->allocList[j+1];
			}

			this->allocList[j] = NULL;
			this->listSize--;
			return;
		}
	}

	fprintf(stderr, "Bad free. -.- Why did you give me garbage? Whyyyyyyy?\n");
	exit(1);
}

//return pointer to memory
void* getBase(struct Allocator_t* this)
{
	return this->memory;
}

//return total memory used by allocations
size_t getUsed(struct Allocator_t* this)
{
	return this->memUsed;
}

//return max size of allocator
size_t getCapacity(struct Allocator_t* this)
{
	return this->capacity;
}

//return # of allocations in tracked by allocator
size_t numAllocations(struct Allocator_t* this)
{
	return this->listSize;
}

//returns the allocation at given index
struct Allocation_t* getAllocation(struct Allocator_t* this, size_t index)
{

	if(index >= this->listSize || index < 0)
	{
		return NULL;
	}

	return this->allocList[index];
}

void* riskyAlloc(struct Allocator_t* this, size_t allocSize)
{

	void* tempMem = this->memory; //save copy in case expanded allocator does not overlap the old allocator
	size_t allocStart = this->capacity;
	void* allocPtr = this->memory+this->capacity;

	//resize allocator
	this->capacity += this->capacity*2 + allocSize;
	this->memory = realloc(this->memory, this->capacity);

	//checking for invalid pointers
	if(this->memory != tempMem)
	{
		fprintf(stderr, "Bad realloc\n");
		return NULL;
	}

	struct Allocation_t* newAlloc = (struct Allocation_t*) malloc(sizeof(struct Allocation_t));
	makeAllocation(newAlloc, allocStart, allocSize);
	this->allocList[this->listSize] = newAlloc;
	this->memUsed += allocSize;
	this->listSize++;

	return allocPtr;
}
