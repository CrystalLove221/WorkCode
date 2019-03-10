/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	The allocator will handle keeping track of allocations
	the user allocates. The user is given a block of memory
	in which to keep allocations.

	Program #4
*/

#ifndef ALLOCATOR_H
#define ALLOCATOR_H

#include <stdio.h>
#include "Allocation.h"

struct Allocator_t
{
	void* memory; //block of memory in use
	size_t capacity; //max size of allocator (multiple of 16)
	size_t memUsed; //amount of memory used by Allocator

	//metadata to keep track of and deallocate user allocations
	struct Allocation_t** allocList;
	size_t listSize;
	size_t listCapacity;

};


void makeAllocator(struct Allocator_t* this, size_t capacity); //allocate block of memory for user's allocations
void freeAllocator(struct Allocator_t* this); //destroy and free allocations and allocator
void* allocate(struct Allocator_t* this, size_t allocSize); //place requested allocation in allocator, if possible
void deallocate(struct Allocator_t* this, void* alloc); //destroy and free allocation pointed at by void*
void* getBase(struct Allocator_t* this); //return pointer to beginning of memory block
size_t getUsed(struct Allocator_t* this); //return mem used by allocs in allocator
size_t getCapacity(struct Allocator_t* this); //return max size of allocator (bytes)
size_t numAllocations(struct Allocator_t* this);
struct Allocation_t* getAllocation(struct Allocator_t* this, size_t index);
void* riskyAlloc(struct Allocator_t* this, size_t allocSize); //allocate when no space is left in memory block

#endif
