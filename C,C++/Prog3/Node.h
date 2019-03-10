/*
	CSE 109: Fall 3018
	Tyler Hogue (Ikaros)
	trh221

	Definition of Node_t object that holds meaningful user data in
	a singly-linked List

	Program #3
*/

#ifndef NODE_T
#define NODE_T

struct Node_t
{
	struct Node_t* next;
	int data;
};

struct Node_t* makeNode1(struct Node_t* this); //construct default node
struct Node_t* makeNode2(struct Node_t* this, int data); //construct node with default data
struct Node_t* makeNode4(struct Node_t* this, int data, struct Node_t* next);
void freeNode(struct Node_t* this); //destroy (reset) node data
int setData(struct Node_t* this, int data); //assign data to node
struct Node_t* setNext(struct Node_t* this, struct Node_t* next); //assign node's next node
int getData(struct Node_t* this); //return data of node
struct Node_t* getNext(struct Node_t* this); //return node's next node

#endif
