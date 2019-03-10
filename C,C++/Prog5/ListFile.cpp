/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	Reading raw data from a file, the program will create a linked list of nodes
	based on the data it read.

	If the file data would produce an invalid/broken list in any way
	(too many nodes, too few nodes, file too small, etc), a failure is returned to the user
	and the list is unchanged.

	If the file is valid, readFile overwrites the user's linked list with the list contained
	in the file.

	appendFromFile is similar, but will not clear the list before adding the elements from the file.

	Finally, saveToFile writes the user's existing linked list to a file matching the format required for
	reading. If the file doesn't exist, one is created. If one exists, whatever was in the file will be
	overwritten.

	Program #5

*/

#include "ListFile.h"
#include <string.h>
#include <string>
#include <stdio.h>
#include <iostream>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>

using std::cerr;
using std::endl;

Node_t* findNode(size_t index, const ListFile_t& list, Node_t* head);
int checkFile(const string& filename);
ssize_t insertFile(ListFile_t* list, int fd);
ssize_t readSize_t(int fd);
char* readChars(int fd, size_t amt);

ssize_t writeSize_t(size_t data, int fd);
int writeChars(const char* data, size_t amt, int fd);

int isDataEqual(char* data1, char* data2, size_t size);

ListFile_t::ListFile_t()
{
	this->size = 0;
	this->head = NULL;
}

ListFile_t::ListFile_t(const ListFile_t& src)
{
	if(this != &src)
	{
		this->size = src.getSize();

		Node_t* srcCurr = src.head;
		this->head = new Node_t(srcCurr->getName(), srcCurr->getData(), srcCurr->getNodeSize(), NULL);

		srcCurr = srcCurr->getNext();
		Node_t* curr = this->head;
		while(srcCurr != NULL)
		{
			curr->setNext(new Node_t(srcCurr->getName(), srcCurr->getData(), srcCurr->getNodeSize(), NULL));
			curr = curr->getNext();
			srcCurr = srcCurr->getNext();
		}
	}
}

ListFile_t& ListFile_t::operator=(const ListFile_t& rhs)
{

	this->clear();

	if(this != &rhs)
	{
		this->size = rhs.getSize();

		Node_t* srcCurr = rhs.head;
		this->head = new Node_t(srcCurr->getName(), srcCurr->getData(), srcCurr->getNodeSize(), NULL);

		srcCurr = srcCurr->getNext();
		Node_t* curr = this->head;
		while(srcCurr != NULL)
		{
			curr->setNext(new Node_t(srcCurr->getName(), srcCurr->getData(), srcCurr->getNodeSize(), NULL));
			curr = curr->getNext();
			srcCurr = srcCurr->getNext();
		}
	}

	return *this;
}

ListFile_t::~ListFile_t()
{
	this->clear();
}

//validate contents of file
//-1: invalid, else: file descriptor
int checkFile(const string& filename)
{

	int fd = open(filename.c_str(), O_RDONLY);
	if(fd == -1)
	{
		return -1;
	}

	ssize_t listSize = readSize_t(fd);

	if(listSize == -1)
	{
		return -1;
	}

	for(ssize_t i = 0; i < listSize; i++)
	{

		ssize_t nameLength = readSize_t(fd);

		if(nameLength == -1)
		{
			return -1;
		}

		ssize_t dataLength = readSize_t(fd);

		if(dataLength == -1)
		{
			return -1;
		}

		char* name = readChars(fd, nameLength);
		if(nameLength && !name)
		{
			free(name);
			return -1;
		}

		char* data = readChars(fd, dataLength);

		if(dataLength && !data)
		{
			free(name);
			free(data);
			return -1;
		}

		free(data);
		free(name);
	}

	if(lseek(fd, 0, SEEK_SET) == -1)
	{
		return -1;
	}

	return fd;
}

ssize_t readSize_t(int fd)
{
	ssize_t result = 0;
	size_t toRead = sizeof(size_t);
	size_t haveRead = 0;

	char* buffer = (char*) &result;

	ssize_t dataRead = 0;

	while((dataRead = read(fd, buffer+haveRead, toRead)) != -1)
	{
		if(dataRead == 0)
		{
			return -1;
		}

		toRead -= dataRead;
		haveRead += dataRead;

		if(toRead == 0)
		{
			return result;
		}
	}

	return -1;
}

char* readChars(int fd, size_t amt)
{
	if(!amt)
	{
		return NULL;
	}

	char* result = (char*) malloc(amt + 1);
	ssize_t toRead = amt;
	ssize_t haveRead = 0;

	char* buffer = result;

	ssize_t dataRead = 0;

	while((dataRead = read(fd, buffer+haveRead, toRead)) != -1)
	{
		if(dataRead == 0)
		{
			free(result);
			return NULL;
		}

		toRead -= dataRead;
		haveRead += dataRead;

		if(toRead == 0)
		{
			return result;
		}
	}

	free(result);
	return NULL;
}

//-1: fail, 0: pass
int ListFile_t::readFile(const string& filename)
{

	int filedesc = checkFile(filename);
	if(filedesc == -1)
	{
		close(filedesc);
		return -1;
	}

	this->clear();
	insertFile(this, filedesc);

	if(close(filedesc) == -1)
	{
		return -1;
	}

	return 0;
}

//-1: failed, >= 0: # of elements added to list
ssize_t ListFile_t::appendFromFile(const string& filename)
{

	int filedesc = checkFile(filename);
	if(filedesc == -1)
	{
		close(filedesc);
		return -1;
	}

	ssize_t inserted = insertFile(this, filedesc);

	if(close(filedesc) == -1)
	{
		return -1;
	}

	return inserted;
}

ssize_t insertFile(ListFile_t* list, int fd)
{
	size_t oldSize = list->getSize();

	size_t listSize = readSize_t(fd);
	for(size_t i = 0; i < listSize; i++)
	{
		size_t nameLength = (size_t) readSize_t(fd);
		size_t dataLength = (size_t) readSize_t(fd);
		char* name = readChars(fd, nameLength);
		char* data = readChars(fd, dataLength);

		string stringName;
		if(name)
		{
			name[nameLength] = '\0';
			stringName = string((const char*)name);
		}

		list->insert(stringName, data, dataLength);

		free(data);
		free(name);
	}

	return (ssize_t) (list->getSize()-oldSize);
}

ssize_t writeSize_t(size_t data, int fd)
{
	size_t toWrite = sizeof(size_t);
	size_t haveWritten = 0;
	ssize_t written = 0;
	char* buffer = (char*)&data;

	while((written = write(fd, buffer+haveWritten, toWrite)) != -1)
	{
		if(written == 0)
		{
			return -1;
		}

		toWrite -= written;
		haveWritten += written;

		if(toWrite == 0)
		{
			return (ssize_t) data;
		}
	}

	return -1;
}

int writeChars(const char* data, size_t amt, int fd)
{

	if(!amt)
	{
		return 1;
	}

	size_t toWrite = amt;
	size_t haveWritten = 0;
	ssize_t written = 0;

	const char* buffer = data;

	while((written = write(fd, buffer+haveWritten, toWrite)) != -1)
	{
		if(written == 0)
		{
			return -1;
		}

		toWrite -= written;
		haveWritten += written;

		if(toWrite == 0)
		{
			return 1;
		}
	}

	return -1;
}

//-1: failed, 1: successful
//create new file with ListFIle_t data
int ListFile_t::saveToFile(const string& filename) const
{

	int filedesc = open(filename.c_str(), O_RDWR | O_CREAT | O_TRUNC, S_IRUSR | S_IWUSR);
	if(filedesc == -1)
	{
		return -1;
	}

	//listsize
	if(writeSize_t(this->getSize(), filedesc) == -1)
	{
		close(filedesc);
		return -1;
	}

	for(size_t i = 0; i < this->getSize(); i++)
	{
		//namelength
		size_t nameLength = this->getElementName(i).size();
		if(writeSize_t(nameLength, filedesc) == -1)
		{
			close(filedesc);
			return -1;
		}

		//datalength
		size_t dataLength = this->getElementSize(i);
		if(writeSize_t(dataLength, filedesc) == -1)
		{
			close(filedesc);
			return -1;
		}

		//name
		if(writeChars(this->getElementName(i).c_str(), nameLength, filedesc) == -1)
		{
			close(filedesc);
			return -1;
		}

		//data
		if(writeChars((char*)this->getElementData(i), dataLength, filedesc) == -1)
		{
			close(filedesc);
			return -1;
		}
	}

	return 1;
}

size_t ListFile_t::getSize() const
{
	return this->size;
}

Node_t* findNode(size_t index, const ListFile_t* list, Node_t* head)
{

	size_t currIndex = 0;
	Node_t* currNode = head;
	while(currIndex < index)
	{
		currNode = currNode->getNext();
		currIndex++;
	}

	return currNode;
}


size_t ListFile_t::getElementSize(size_t index) const
{
	Node_t* found = findNode(index, this, this->head);
	return found->getNodeSize();
}

void* ListFile_t::getElementData(size_t index) const
{
	Node_t* found = findNode(index, this, this->head);
	return found->getData();
}

string ListFile_t::getElementName(size_t index) const
{
	Node_t* found = findNode(index, this, this->head);
	return found->getName();
}

const Node_t& ListFile_t::operator[](size_t index) const
{
	const Node_t& found = (const Node_t) *(findNode(index, this, this->head));
	return found;
}

Node_t& ListFile_t::operator[](size_t index)
{
	Node_t& found = *(findNode(index, this, this->head));
	return found;
}

void ListFile_t::clear()
{

	Node_t* curr = this->head;
	Node_t* next = NULL;
	while(curr != NULL)
	{
		next = curr->getNext();
		delete curr;
		curr = next;
	}

	this->head = NULL;
	this->size = 0;
}

//1=found, 0=not found
bool ListFile_t::exists(const string& name) const
{
	Node_t* currNode = this->head;
	while(currNode != NULL)
	{
		if(currNode->getName() == name)
		{
			return 1;
		}

		currNode = currNode->getNext();
	}

	return 0;
}

int isDataEqual(char* data1, char* data2, size_t size)
{

	if(!data1 || !data2)
	{
		return 0;
	}

	for(size_t i = 0; i < size; i++)
	{
		if(data1[i] != data2[i])
		{
			return 0;
		}
	}

	return 1;
}

size_t ListFile_t::count(void* data, size_t size) const
{

	size_t count = 0;
	Node_t* currNode = this->head;
	while(currNode != NULL)
	{
		if(currNode->getNodeSize() == size && isDataEqual((char*)currNode->getData(), (char*)data, size))
		{
			count++;
		}

		currNode = currNode->getNext();
	}

	return count;
}

//1: successful, 0: failed
int ListFile_t::removeByName(const string& name)
{
	Node_t* currNode = this->head;
	Node_t* prevNode = NULL;
	while(currNode != NULL)
	{
		if(!strcmp(currNode->getName().c_str(), name.c_str()))
		{
			if(prevNode == NULL)
			{
				this->head = currNode->getNext();
			}

			else
			{
				prevNode->setNext(currNode->getNext());
			}

			delete currNode;
			this->size--;
			return 1;
		}

		prevNode = currNode;
		currNode = currNode->getNext();
	}

	return 0;
}

//no duplicates
//0: conflict, 1: successful
int ListFile_t::insert(const string& name, void* data, size_t size)
{

	Node_t* currNode = this->head;
	Node_t* prevNode = NULL;
	while(currNode != NULL)
	{
		if(!strcmp(currNode->getName().c_str(), name.c_str()))
		{
			return 0;
		}

		else if(strcmp(currNode->getName().c_str(), name.c_str()) > 0)
		{
			break;
		}

		prevNode = currNode;
		currNode = currNode->getNext();
	}

	Node_t* newNode = new Node_t(name, data, size, currNode);

	if(prevNode == NULL)
	{
		this->head = newNode;
		this->size++;
		return 1;
	}

	prevNode->setNext(newNode);
	this->size++;
	return 1;
}

void ListFile_t::insertInternal(Node_t* data)
{

}
