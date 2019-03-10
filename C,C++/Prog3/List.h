/*
	CSE 109: Fall 3018
	Tyler Hogue (Ikaros)
	trh221

	Definition of the List_t object to manage List and Free List

	Program #3
*/

#ifndef LIST_T
#define LIST_T

#include "Node.h"
#include <stdio.h>

struct List_t
{
	size_t size; //# of nodes in List
	size_t freeSize; //# of nodes in Free List
	struct Node_t* head; //first node in List
	struct Node_t* freeHead; // first node in Free List

	//metadata to dealloc freeList

	struct Node_t* nodeBlock; //block of nodes to hold List and Free List nodes
	struct Node_t** blockList; //list of pointers to each block of allocated nodes
	size_t blockListSize; //# of node blocks
	size_t blockListCapacity; //max # of node blocks that fit in list

};

struct List_t* makeList(struct List_t* this); //construct List_t struct
void freeList(struct List_t* this); //destroy all nodes and deallocate all blocks of nodes
size_t size(struct List_t* this); //# of nodes in List
size_t freeSize(struct List_t* this); //# of nodes in Free List
struct Node_t* insert(struct List_t*, int data); //insert node to end of List; return pointer
size_t find(struct List_t* this, int data); //return # of nodes found matching data provided
size_t removeItem(struct List_t* this, int data); //# of nodes removed from List
struct Node_t* getHead(struct List_t* this); //return copy of pointer to 1st node in List

#endif
