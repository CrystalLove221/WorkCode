/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	class definition for chaining hash table

	Program #6

*/

#ifndef HASH_T
#define HASH_T

#include <vector>
#include <stdio.h>
#include <string>
#include <ostream>

using std::ostream;
using std::string;
using std::vector;

class Hash_t
{
public:
	Hash_t();
	Hash_t(size_t numBuckets, int (*angeloid)(const void*, size_t));
	Hash_t(size_t numBuckets);
	~Hash_t();


	Hash_t(const Hash_t& hash);
	Hash_t& operator=(const Hash_t& hash);
	Hash_t& operator+=(unsigned int data);
	Hash_t& operator-=(unsigned int data);
	friend ostream& operator<<(ostream& out, const Hash_t& hash);

	bool insert(unsigned int data);
	bool resize(size_t size);
	size_t size() const;
	string to_string() const;
	bool find(unsigned int data) const;
	bool remove(unsigned int data);


	class iterator
	{
	friend class Hash_t;
	public:
		iterator() = delete;
		iterator(size_t curr, size_t bucket, Hash_t* hash);

		iterator& operator=(const iterator&);
		bool operator!=(const iterator& it) const;
		bool operator==(const iterator& it) const;

		iterator operator+(size_t amt) const;
		iterator operator-(size_t amt) const;
		iterator& operator+=(size_t amt);
		iterator& operator-=(size_t amt);

		iterator& operator++(); //prefix
		iterator& operator--(); //prefix
		iterator operator++(int); //postfix
		iterator operator--(int); //postfix

		const unsigned int& operator*() const; //dereference

	private:
		size_t curr;
		size_t bucket;
		Hash_t* hash;
	};

	iterator begin();
	iterator end();
	iterator remove(iterator& it);

private:
	size_t getIndex(const unsigned int& data) const;
	size_t _size;
	vector<vector<unsigned int>> buckets;
	int(*funcy)(const void* data, size_t size);
};

#endif
