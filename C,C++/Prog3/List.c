/*
	CSE 109: Fall 3018
	Tyler Hogue (Ikaros)
	trh221

	This file contains the implementation of
	the List and Free List of nodes that is printed
	by the main program file (prog3.c)

	Program #3
*/

#include "List.h"
#include "ListTraverse.h"
#include <stdlib.h>

void nodeBlockRealloc(struct List_t* this); //make new block of nodes
void expandBlockList(struct List_t* this); //make new nodeBlock list of larger size if not enough space

struct List_t* makeList(struct List_t* this)
{
	this->nodeBlock = NULL;
	this->blockList = NULL;
	this->blockListSize = 0;
	this->blockListCapacity = 1;

	this->size = 0;
	this->freeSize = 0;
	this->head = NULL;
	this->freeHead = NULL;

	return this;
}

void freeList(struct List_t* this)
{
	struct Node_t* currNode = this->head;

	//destroy List Nodes
	while(currNode != NULL)
	{
		freeNode(currNode);
		currNode = getNext(currNode);
		this->size--;
	}

	currNode = this->freeHead;
	//destroy Free List nodes
	while(currNode != NULL)
	{
		freeNode(currNode);
		currNode = getNext(currNode);
		this->freeSize--;
	}

	//deallocate blocks of nodes
	for(size_t i = 0; i < this->blockListSize; i++)
	{
		free(this->blockList[i]);
		this->blockList[i] = NULL;
	}

	//free block list
	free(this->blockList);
	this->blockList = NULL;

	//reset block list metadata
	this->blockListSize = 0;
	this->blockListCapacity = 1;

	this->head = NULL;
	this->freeHead = NULL;

	return;
}

//# of nodes in List
size_t size(struct List_t* this)
{
	return this->size;
}

//# of nodes in Free List
size_t freeSize(struct List_t* this)
{
	return this->freeSize;
}

void expandBlockList(struct List_t* this)
{
	//block list was never allocated
	if(this->blockList == NULL)
	{
		return;
	}

	if(this->blockListSize >= this->blockListCapacity)
	{
		this->blockListCapacity = this->blockListCapacity * 2 + 1;
		struct Node_t** newBlockList = NULL;
		newBlockList = (struct Node_t**) malloc(this->blockListCapacity * sizeof(struct Node_t*));

		//copy node block pointers to expanded block list
		for(size_t i = 0; i < this->blockListSize; i++)
		{
			newBlockList[i] = this->blockList[i];
		}

		free(this->blockList);
		this->blockList = newBlockList;
	}

	return;
}

void nodeBlockRealloc(struct List_t* this)
{

	if(this->blockList == NULL)
	{
		this->blockList = (struct Node_t**) malloc(this->blockListCapacity * sizeof(struct Node_t*));
	}

	//Allocate block of nodes
	if(this->nodeBlock == NULL)
	{
		expandBlockList(this);

		this->nodeBlock = (struct Node_t*) malloc(256 * sizeof(struct Node_t));
		this->freeSize = 256;

		this->blockList[this->blockListSize] = this->nodeBlock;
		this->blockListSize++;

		//construct and connect nodes
		struct Node_t* currNode = this->nodeBlock;
		for(int i = 0; i < this->freeSize; i++)
		{
			currNode = makeNode1(currNode);
			setNext(currNode, (this->nodeBlock)+i+1);
			currNode = (this->nodeBlock)+i+1;
		}

		setNext(currNode-1, NULL);
		this->freeHead = this->nodeBlock;
    }

	//ran out of space, allocate another block
	if(!this->freeSize)
	{
		this->nodeBlock = NULL;
		nodeBlockRealloc(this);
	}

	return;
}

struct Node_t* insert(struct List_t* this, int data)
{
	nodeBlockRealloc(this);

	struct Node_t* newNode = this->freeHead;
	struct Node_t* currNode = this->head;

	//List is empty
	if(this->head == NULL)
	{
		this->head = newNode;
	}

	//Insert at end of List
	else
	{
		while(getNext(currNode) != NULL)
		{
			currNode = getNext(currNode);
		}

		setNext(currNode, newNode);
	}

	this->freeHead = getNext(newNode);
	setNext(newNode, NULL);
	setData(newNode, data);

	this->size++;
	this->freeSize--;

	return newNode;
}

//return # of nodes found with data
size_t find(struct List_t* this, int data)
{

	struct Node_t* currNode = this->head;
	size_t count = 0; //number of nodes found
	while(currNode != NULL)
	{
		if(getData(currNode) == data)
		{
			count++;
		}

		currNode = getNext(currNode);
	}

	return count;
}

//Remove node with data, return # of nodes removed
size_t removeItem(struct List_t* this, int data)
{

	if(this->head == NULL)
	{
		return 0;
	}

	struct Node_t* currNode = this->head;
	struct Node_t* nextNode = getNext(currNode);
	struct Node_t* prevNode = NULL;
	size_t count = 0; //# of nodes removed

	while(currNode != NULL)
	{
		nextNode = getNext(currNode);

		//currNode has data to be removed
		if(getData(currNode) == data)
		{
			count++;
			this->size--;
			this->freeSize++;

			//remove and reassign head
			if(currNode == this->head)
			{
				this->head = nextNode;
			}

			else
			{
				setNext(prevNode, nextNode);
			}

			//reassign freeHead to removed node
			setNext(currNode, this->freeHead);
			this->freeHead = currNode;

			currNode = nextNode;
		}

		else
		{
			prevNode = currNode;
			currNode = getNext(currNode);
		}
	}

	return count;
}

//return copy of the pointer to first node in List
struct Node_t* getHead(struct List_t* this)
{
	struct Node_t* copyHead = this->head;
	return copyHead;
}
