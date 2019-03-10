#include "printAllocations.h"
#include "Allocator.h"
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char** argv)
{
	if(argc != 2)
	{
		fprintf(stderr, "need more args: <allocator maxSize>\n");
		return 1;
	}

	struct Allocator_t* allocator = NULL;
	allocator = (struct Allocator_t*) malloc(sizeof(struct Allocator_t));
	size_t capacity = atoi(argv[1]);

	makeAllocator(allocator, capacity);

	int size = 0;
	while(fscanf(stdin, "%d", &size) != EOF)
	{
		void* allocPtr = allocate(allocator, size);

		if(allocPtr == NULL)
		{
			allocPtr = riskyAlloc(allocator, size);
		}

		fprintf(stdout, "Allocation:%p", (struct Allocation_t*) allocPtr);
		printAllocations(allocator, stdout);
	}

		//read each allocation
		for(size_t i = 0; i < numAllocations(allocator); i++)
		{
			struct Allocation_t* temp = getAllocation(allocator, i);
			if(temp == NULL)
			{
				fprintf(stderr, "Invalid Allocation.\n");
			}
		}

		//do some random deallocation to test for bad pointers

	int randIndex = 0;
	while(numAllocations(allocator) > 0)
	{
		randIndex = rand() % numAllocations(allocator);
		if(getAllocation(allocator, randIndex) == NULL)
		{
			fprintf(stderr, "Invalid Allocation. Index:%d\n", randIndex);
		}

		randIndex = rand() % getCapacity(allocator);
		void* ptr = getBase(allocator)+randIndex;
		deallocate(allocator, ptr);
	}

	freeAllocator(allocator);
	free(allocator);
	allocator = NULL;

	return 0;
}
