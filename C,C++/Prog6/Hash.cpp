/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	Implements a chaining hash table comprised of a vector of vectors (buckets)
	and an iterator to traverse the hash.
	Each element (unsigned int) is stored at bucket index based on the result after applying the 
	hash function to user's data and the size of the hash (# of buckets)

	Elements in buckets are stored in numerical order from least to greatest
	The user may resize the hash. The hash is reapplied to all elements to replace them properly.

	Program #6
*/

#include "Hash.h"
#include <vector>
#include <iostream>
#include <sstream>
#include <ostream>
#include <stdio.h>
#include "sha1.h"
#include <math.h>
#include <iomanip>

using std::setw;
using std::cerr;
using std::endl;
using std::string;
using std::vector;
using std::ostream;
using std::stringstream;


Hash_t::Hash_t()
:buckets(10)
{
	this->_size = 0;
	this->funcy = sha1_hash;
}

Hash_t::Hash_t(size_t buckets)
:buckets(buckets)
{
	this->_size = 0;
	this->funcy = sha1_hash;
}

Hash_t::Hash_t(size_t buckets, int(*angel)(const void* data, size_t size))
:buckets(buckets)
{
	this->_size = 0;
	this->funcy = angel;
}

Hash_t::Hash_t(const Hash_t& hash)
:buckets(hash.buckets)
{
	this->_size = hash._size;
	this->funcy = hash.funcy;
}

Hash_t::~Hash_t()
{
	this->buckets.clear();
	this->_size = 0;
	this->funcy = NULL;
}

Hash_t& Hash_t::operator=(const Hash_t& hash)
{
	if(this == &hash)
	{
		return *this;
	}

	this->buckets = hash.buckets;
	this->_size = hash._size;
	this->funcy = hash.funcy;

	return *this;
}

Hash_t& Hash_t::operator+=(unsigned int data)
{
	this->insert(data);
	return *this;
}

Hash_t& Hash_t::operator-=(unsigned int data)
{
	this->remove(data);
	return *this;
}

ostream& operator<<(ostream& out, const Hash_t& hash)
{
	return out << hash.to_string();
}

bool Hash_t::insert(unsigned int data)
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
			this->buckets[index].push_back(0);
			for(int j = oldSize-1; j >= i; j--)
			{
				this->buckets[index][j+1] = this->buckets[index][j];
			}

			this->buckets[index][i] = data;
			this->_size++;
			return 1;
		}
	}

	this->buckets[index].push_back(data);
	this->_size++;
	return 1;
}

bool Hash_t::resize(size_t size)
{
	if(!size)
	{
		return 0;
	}

	vector<vector<unsigned int>> temp(this->buckets);
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

size_t Hash_t::size() const
{
	return this->_size;
}

string Hash_t::to_string() const
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

bool Hash_t::find(unsigned int data) const
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

bool Hash_t::remove(unsigned int data)
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

size_t Hash_t::getIndex(const unsigned int& data) const
{
	return this->funcy(&data, sizeof(unsigned int)) % this->buckets.size();
}

Hash_t::iterator Hash_t::begin()
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

Hash_t::iterator Hash_t::end()
{
	return iterator(0, this->buckets.size(), this);
}

Hash_t::iterator Hash_t::remove(iterator& it)
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

Hash_t::iterator::iterator(size_t curr, size_t bucket, Hash_t* hash)
:curr(curr), bucket(bucket)
{
	this->hash = hash;
}

Hash_t::iterator& Hash_t::iterator::operator=(const iterator& it)
{
	this->curr = it.curr;
	this->bucket = it.bucket;
	return *this;
}

bool Hash_t::iterator::operator!=(const iterator& it) const
{
	return this->curr != it.curr || this->bucket != it.bucket;
}

bool Hash_t::iterator::operator==(const iterator& it) const
{
	return this->curr == it.curr && this->bucket == it.bucket;
}

Hash_t::iterator Hash_t::iterator::operator+(size_t amt) const
{
	iterator tmp(this->curr, this->bucket, this->hash);
	while(amt)
	{
		++tmp;
		amt--;
	}

	return tmp;
}

Hash_t::iterator Hash_t::iterator::operator-(size_t amt) const
{
	iterator tmp(this->curr, this->bucket, this->hash);
	while(amt)
	{
		--tmp;
		amt--;
	}

	return tmp;
}

Hash_t::iterator& Hash_t::iterator::operator+=(size_t amt)
{
	while(amt)
	{
		++(*this);
		amt--;
	}

	return *this;
}

Hash_t::iterator& Hash_t::iterator::operator-=(size_t amt)
{
	while(amt)
	{
		--(*this);
		amt--;
	}

	return *this;
}

//prefix
Hash_t::iterator& Hash_t::iterator::operator++()
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
Hash_t::iterator& Hash_t::iterator::operator--()
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
Hash_t::iterator Hash_t::iterator::operator++(int)
{
	iterator tmp(this->curr, this->bucket, this->hash);
	++(*this);
	return tmp;
}

//postfix
Hash_t::iterator Hash_t::iterator::operator--(int)
{
	iterator tmp(this->curr, this->bucket, this->hash);
	--(*this);
	return tmp;
}

const unsigned int& Hash_t::iterator::operator*() const
{
	const unsigned int& num = (this->hash)->buckets[this->bucket][this->curr];
	return num;
}
