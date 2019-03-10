/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	The templated chaining hash table. Takes three template arguments at compile time.

	T: type of data to be stored in the hash
	D: function used to assign a pointer to data and calculate size of data
	H: hash function

	Program #6

*/

#ifndef HASHGENERIC_CPP
#define HASHGENERIC_CPP

#include <vector>
#include <iostream>
#include <sstream>
#include <ostream>
#include <stdio.h>
#include "sha1.h"
#include <math.h>
#include <iomanip>
#include "HashGeneric.h"

using std::setw;
using std::cerr;
using std::endl;
using std::string;
using std::vector;
using std::ostream;
using std::stringstream;


template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
Hash_t<T,D,H>::Hash_t()
:buckets(10), _size(0)
{
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
Hash_t<T,D,H>::Hash_t(size_t buckets)
:buckets(buckets), _size(0)
{
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
Hash_t<T,D,H>::Hash_t(const Hash_t& hash)
:buckets(hash.buckets), _size(hash._size)
{
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
Hash_t<T,D,H>::~Hash_t()
{
	this->buckets.clear();
	this->_size = 0;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
Hash_t<T,D,H>& Hash_t<T,D,H>::operator=(const Hash_t& hash)
{
	if(this == &hash)
	{
		return *this;
	}

	this->buckets = hash.buckets;
	this->_size = hash._size;
	return *this;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
Hash_t<T,D,H>& Hash_t<T,D,H>::operator+=(T data)
{
	this->insert(data);
	return *this;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
Hash_t<T,D,H>& Hash_t<T,D,H>::operator-=(T data)
{
	this->remove(data);
	return *this;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
ostream& operator<<(ostream& out, const Hash_t<T,D,H>& hash)
{
	return out << hash.to_string();
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
bool Hash_t<T,D,H>::insert(T data)
{

	size_t index = this->getIndex(data);
	int oldSize = (int) this->buckets[index].size();

	for(int i = 0; i < oldSize; i++)
	{
		//no duplicates
		if(this->buckets[index][i] == data)
		{
			return 0;
		}

		else if(this->buckets[index][i] > data)
		{
			this->buckets[index].insert(this->buckets[index].begin()+i, data);
			this->_size++;
			return 1;
		}
	}

	this->buckets[index].push_back(data);
	this->_size++;
	return 1;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
bool Hash_t<T,D,H>::resize(size_t size)
{
	if(!size)
	{
		return 0;
	}

	vector<vector<T>> temp(this->buckets);
	this->_size = 0;
	this->buckets.clear();
	this->buckets.resize(size);

	for(size_t i = 0; i < temp.size(); i++)
	{
		for(size_t j = 0; j < temp[i].size(); j++)
		{
			this->insert(temp[i][j]);
		}
	}

	return 1;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
size_t Hash_t<T,D,H>::size() const
{
	return this->_size;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
string Hash_t<T,D,H>::to_string() const
{
	stringstream hash;
	int indexWidth = (int) (log10(this->buckets.size()-1) + 1);

	for(size_t i = 0; i < this->buckets.size(); i++)
	{
		hash << setw(indexWidth) << i << ": ";

		if(this->buckets[i].empty())
		{
			hash << "(Empty)" << endl;
			continue;
		}

		for(size_t j = 0; j < this->buckets[i].size(); j++)
		{
			hash << this->buckets[i][j];
			if(j < this->buckets[i].size()-1)
			{
				hash << " ";
			}
		}

		hash << endl;
	}

	return hash.str();
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
bool Hash_t<T,D,H>::find(T data) const
{
	size_t index = getIndex(data);
    for(size_t i = 0; i < this->buckets[index].size(); i++)
    {
        if(this->buckets[index][i] == data)
        {
            return 1;
        }
    }

	return 0;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
bool Hash_t<T,D,H>::remove(T data)
{
	size_t index = getIndex(data);

	for(size_t i = 0; i < this->buckets[index].size(); i++)
	{
		if(this->buckets[index][i] == data)
		{
			this->buckets[index].erase(this->buckets[index].begin()+i);
			this->_size--;
			return 1;
		}
	}

	cerr << "remove " << data << " failed" << endl;
	return 0;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
size_t Hash_t<T,D,H>::getIndex(const T& data) const
{
	pair<const void*, size_t> funcData = D(data);
	return H(funcData.first, funcData.second) % this->buckets.size();
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator Hash_t<T,D,H>::begin()
{
	for(size_t i = 0; i < this->buckets.size(); i++)
	{
		if(!this->buckets[i].empty())
		{
			return iterator(0, i, this);
		}
	}

	return this->end();
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator Hash_t<T,D,H>::end()
{
	return iterator(0, this->buckets.size(), this);
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator Hash_t<T,D,H>::remove(iterator& it)
{
	iterator tmp(it.curr, it.bucket, this);

	if(it == this->begin())
	{
		this->remove(*it);
		return it = this->begin();
	}

	tmp--;
	this->remove(*it);
	tmp++;
	return it = tmp;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
Hash_t<T,D,H>::iterator::iterator(size_t curr, size_t bucket, Hash_t* hash)
:curr(curr), bucket(bucket)
{
	this->hash = hash;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator& Hash_t<T,D,H>::iterator::operator=(const iterator& it)
{
	this->curr = it.curr;
	this->bucket = it.bucket;
	return *this;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
bool Hash_t<T,D,H>::iterator::operator!=(const iterator& it) const
{
	return this->curr != it.curr || this->bucket != it.bucket;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
bool Hash_t<T,D,H>::iterator::operator==(const iterator& it) const
{
	return this->curr == it.curr && this->bucket == it.bucket;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator Hash_t<T,D,H>::iterator::operator+(size_t amt) const
{
	iterator tmp(this->curr, this->bucket, this->hash);
	while(amt)
	{
		++tmp;
		amt--;
	}

	return tmp;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator Hash_t<T,D,H>::iterator::operator-(size_t amt) const
{
	iterator tmp(this->curr, this->bucket, this->hash);
	while(amt)
	{
		--tmp;
		amt--;
	}

	return tmp;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator& Hash_t<T,D,H>::iterator::operator+=(size_t amt)
{
	while(amt)
	{
		++(*this);
		amt--;
	}

	return *this;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator& Hash_t<T,D,H>::iterator::operator-=(size_t amt)
{
	while(amt)
	{
		--(*this);
		amt--;
	}

	return *this;
}

//prefix
template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator& Hash_t<T,D,H>::iterator::operator++()
{

	if(*this == (this->hash)->end())
	{
		return *this;
	}

	if(this->curr == (this->hash)->buckets[this->bucket].size()-1)
	{
		for(size_t i = this->bucket+1; i < (this->hash)->buckets.size(); i++)
		{
			if(!(this->hash)->buckets[i].empty())
			{
				this->curr = 0;
				this->bucket = i;
				return *this;
			}
		}

		*this = (this->hash)->end();
		return *this;
	}

	this->curr++;
	return *this;
}

//prefix
template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator& Hash_t<T,D,H>::iterator::operator--()
{

	if(*this == (this->hash)->begin())
	{
		return *this;
	}

	if(this->curr == 0)
	{
		for(int i = (int) this->bucket-1; i >= 0; i--)
		{
			if(!(this->hash)->buckets[i].empty())
			{
				this->curr = (this->hash)->buckets[i].size()-1;
				this->bucket = (size_t) i;
				return *this;
			}
		}
	}

	this->curr--;
	return *this;
}

//postfix
template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator Hash_t<T,D,H>::iterator::operator++(int)
{
	iterator tmp(this->curr, this->bucket, this->hash);
	++(*this);
	return tmp;
}

//postfix
template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
typename Hash_t<T,D,H>::iterator Hash_t<T,D,H>::iterator::operator--(int)
{
	iterator tmp(this->curr, this->bucket, this->hash);
	--(*this);
	return tmp;
}

template <typename T, pair<const void*, size_t>(*D)(const T&), int(*H)(const void*, size_t)>
const T& Hash_t<T,D,H>::iterator::operator*() const
{
	const T& data = (this->hash)->buckets[this->bucket][this->curr];
	return data;
}

#endif
