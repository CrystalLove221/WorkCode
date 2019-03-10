/*
	CSE 109: Fall 3018
	Tyler Hogue (Ikaros)
	trh221

	Contains implementation of methods used to
	cpnstruct, destruct and connect nodes, as
	well as its setters and getters

	Program #3
*/

#include "Node.h"
#include <string.h>
#include <stdlib.h>

struct Node_t* makeNode1(struct Node_t* this)
{

	this->data = 0;
	this->next = NULL;

	return this;
}

struct Node_t* makeNode2(struct Node_t* this, int data)
{
	this->data = data;
	this->next = NULL;

	return this;
}

struct Node_t* makeNode4(struct Node_t* this, int data, struct Node_t* next)
{
	this->data = data;
	this->next = next;

	return this;
}

void freeNode(struct Node_t* this)
{
	this->data = 0;
	this->next = NULL;

	return;
}

int setData(struct Node_t* this, int data)
{
	this->data = data;
	return this->data;
}

struct Node_t* setNext(struct Node_t* this, struct Node_t* next)
{
	this->next = next;
	return this->next;
}

int getData(struct Node_t* this)
{
	return this->data;
}

struct Node_t* getNext(struct Node_t* this)
{
	return this->next;
}
