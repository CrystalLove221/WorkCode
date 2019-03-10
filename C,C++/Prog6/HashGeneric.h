/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	template definition for hash

	Program #6

*/

#ifndef HASHGENERIC_T
#define HASHGENERIC_T

#include <vector>
#include <stdio.h>
#include <string>
#include <ostream>
#include <algorithm>

using std::pair;
using std::ostream;
using std::string;
using std::vector;

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
class Hash_t
{
public:
	Hash_t();
	Hash_t(size_t numBuckets);
	~Hash_t();

	Hash_t(const Hash_t<T,D,H>& hash);
	Hash_t<T,D,H>& operator=(const Hash_t<T,D,H>& hash);
	Hash_t<T,D,H>& operator+=(T data);
	Hash_t<T,D,H>& operator-=(T data);

	template<typename T1, pair<const void*, size_t>(*D1)(const T1&), int(*H1)(const void*, size_t)>
	friend ostream& operator<<(ostream& out, const Hash_t<T1,D1,H1>& hash);

	bool insert(T data);
	bool resize(size_t size);
	size_t size() const;
	string to_string() const;
	bool find(T data) const;
	bool remove(T data);

	class iterator
	{
	friend class Hash_t;
	public:
		iterator() = delete;
		iterator(size_t curr, size_t bucket, Hash_t<T,D,H>* hash);

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

		const T& operator*() const; //dereference

	private:
		size_t curr;
		size_t bucket;
		Hash_t* hash;
	};

	iterator begin();
	iterator end();
	iterator remove(iterator& it);

private:
	size_t getIndex(const T& data) const;
	vector<vector<T>> buckets;
	size_t _size;
};

#include "HashGeneric.cpp"
#endif
