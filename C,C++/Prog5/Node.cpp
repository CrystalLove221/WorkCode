/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	Each node will be stored in a linked list the user will construct.
	Each node contains the user's data, the node name, and the size of the data,
	in addition to the node it points to in the linked list

	Program #5
*/

#include "Node.h"
#include <string.h>
#include <cstdlib>

Node_t::Node_t(const string& name, void* data, size_t size, Node_t* next)
{
	this->name = name;

	if(data == NULL)
	{
		this->data = NULL;
	}

	else
	{
		this->data = malloc(size);
		memcpy(this->data, data, size);
	}

	this->size = size;
	this->next = next;
}

Node_t::~Node_t()
{

	this->next = NULL;
	this->size = 0;
	this->name = "";

	free(this->data);
	this->data = NULL;
}

string Node_t::getName() const
{
	string copy = this->name;
	return copy;
}

void* Node_t::getData() const
{
	return this->data;
}

size_t Node_t::getNodeSize() const
{
	return this->size;
}

Node_t* Node_t::getNext() const
{
	return this->next;
}

void Node_t::setName(string name)
{
	this->name = name;
}

void Node_t::setData(void* data, size_t length)
{
	free(this->data);
	if(data == NULL)
	{
		this->size = 0;
		this->data = NULL;
		return;
	}

	this->size = length;
	void* temp = malloc(length);
	this->data = memcpy(temp, data, length);
}

void Node_t::setNext(Node_t* node)
{
	this->next = node;
}
