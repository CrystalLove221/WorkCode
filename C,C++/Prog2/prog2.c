/*
   CSE 109: Fall 2018
   Tyler Hogue
   trh221

	This program takes a user's input and stores its words (separated by whitespace)
	into a collection of buckets with a capacity set by the user in a command-line
	argument.
	The program will print all buckets that were created by user's input when the user
	enters CTRL-D on a new line to denote the end of input.
	Valid capacity range: 20 to 100000 inclusive.

   Program #2
   */


#include <stdio.h>
#include <string.h>
#include <stdlib.h>

size_t removeDuplicate(char* bucket, size_t length, char* duplicate, size_t dupSize);
size_t* addSize_tElement(size_t* list, size_t* size, size_t* capacity, size_t toAdd);
size_t findSpace(size_t wordSize, size_t numBuckets, size_t* sizeList, size_t capacity);
char* addWord(char* bucket, size_t bucketSize, size_t wordSize, char* word);
char** expandBuckets(char** buckets, size_t* sizeList, size_t* capacity, size_t size);

int main(int argc, char** argv)
{

	if(argc > 2)
	{
		fprintf(stderr, "Too many arguments!\n");
		return 1;
	}

	size_t argNum = 0;

	if(argc == 1)
	{
		argNum = 100;
	}

	else
	{
		argNum = (size_t) atoi(argv[1]);
	}

	if(argNum < 20 || argNum > 100000)
	{
		fprintf(stderr, "Chunksize is invalid\n");
		return 1;
	}

	size_t bucketCapacity = argNum;
	size_t numBuckets = 0;
	size_t maxBuckets = 1;
	size_t sizeListCapacity = 1;

	size_t* sizeList = (size_t*) malloc(sizeListCapacity * sizeof(size_t));
	char** buckets = (char**) malloc(maxBuckets * sizeof(char*));
	char* word = NULL;

	while(fscanf(stdin, "%ms", &word) != EOF)
	{
		size_t wordSize = strlen(word);

		if(wordSize+1 > bucketCapacity)
		{
			free(word);
			word = NULL;
			continue;
		}

		//check for duplicate in buckets list
		size_t removedFlag = 0;
		for(size_t i = 0; i < numBuckets; i++)
		{
			size_t newLength = removeDuplicate(buckets[i], sizeList[i], word, wordSize);

			if(sizeList[i] > newLength)
			{
				sizeList[i] = newLength;
				removedFlag = 1;
				break;
			}
		}

		if(removedFlag)
		{
			free(word);
			word = NULL;
			continue;
		}

		//find bucket with space for word
		size_t foundBucketIndex = findSpace(wordSize, numBuckets, sizeList, bucketCapacity);

		//no bucket with space for word
		if(foundBucketIndex == numBuckets)
		{
			//expand buckets list if necessary
			buckets = expandBuckets(buckets, sizeList, &maxBuckets, numBuckets);

			//create and add new bucket
			char* newBucket = (char*) malloc(bucketCapacity * sizeof(char));
			buckets[foundBucketIndex] = newBucket;

			//expand list of sizes if necessary
			sizeList = addSize_tElement(sizeList, &numBuckets, &sizeListCapacity, wordSize+1);

			//add first word to new bucket
			for(int i = 0; i < wordSize+1; i++)
			{
				buckets[foundBucketIndex][i] = word[i];
			}
		}

		//found bucket with space
		else
		{
			buckets[foundBucketIndex] = addWord(buckets[foundBucketIndex], sizeList[foundBucketIndex], wordSize, word);
			sizeList[foundBucketIndex] += (wordSize+1);
		}

		free(word);
		word = NULL;
	}

	free(word);
	word = NULL;

	//print buckets
	int letterIndex = 0;
	for(int i = 0; i < numBuckets; i++)
	{
		letterIndex = 0;

		fprintf(stdout, "%6ld: ", sizeList[i]);

		while(letterIndex < sizeList[i])
		{
			if(buckets[i][letterIndex] == '\0')
			{
				if(letterIndex < sizeList[i]-1)
				{
					fprintf(stdout, " ");
				}
			}

			else
			{
				fprintf(stdout, "%c", buckets[i][letterIndex]);
			}

			letterIndex++;
		}

		fprintf(stdout, "\n");
	}

	//free all buckets in buckets list
	for(int i = 0; i < numBuckets; i++)
	{
		free(buckets[i]);
		buckets[i] = NULL;
	}

	free(buckets);
	buckets = NULL;
	free(sizeList);
	sizeList = NULL;

	return 0;
}

size_t findSpace(size_t wordSize, size_t numBuckets, size_t* sizeList, size_t capacity)
{
	size_t bucketIndex = 0;

	for(bucketIndex = 0; bucketIndex < numBuckets; bucketIndex++)
	{
		if((capacity - sizeList[bucketIndex]) >= wordSize+1)
		{
			if((capacity - (sizeList[bucketIndex] + wordSize+1)) == 1)
			{
				continue;
			}

			break;
		}
	}

	return bucketIndex;
}

char** expandBuckets(char** buckets, size_t* sizeList, size_t* capacity, size_t size)
{
	if(size == *capacity)
	{
		*capacity = *capacity * 2 + 1;
		char** tempBuckets = (char**) malloc(*capacity * sizeof(char*));

		for(int i = 0; i < size; i++)
		{
			tempBuckets[i] = buckets[i];
		}

		free(buckets);
		buckets = tempBuckets;
	}

	return buckets;
}

char* addWord(char* bucket, size_t bucketSize, size_t wordSize, char* word)
{

	int wordIndex = 0;
	size_t bucketIndex = bucketSize;

	while(wordIndex <= wordSize)
	{
		bucket[bucketIndex] = word[wordIndex];
		wordIndex++;
		bucketIndex++;
	}

	return bucket;
}

size_t removeDuplicate(char* bucket, size_t length, char* duplicate, size_t dupSize)
{

	size_t dupFlag = 0;
	size_t replaceIndex = 0;

	//check all words in bucket
	for(size_t i = 0; i < length; i++)
	{
		if(strcmp(&bucket[i], duplicate) == 0)
		{
			dupFlag = 1;

			//move past word and null byte
			i += strlen(&bucket[i])+1;
		}

		else
		{
			i += strlen(&bucket[i]);
			replaceIndex = i+1;
		}

		if(dupFlag)
		{
			while(i < length)
			{
				bucket[replaceIndex] = bucket[i];
				i++;
				replaceIndex++;
			}

			length -= (dupSize+1);
		}
	}

	return length;
}

size_t* addSize_tElement(size_t* list, size_t* size, size_t* capacity, size_t toAdd)
{

	//Expand list of sizes if no more space
	if(*size >= *capacity)
	{
		*capacity = *capacity * 2 + 1;
		size_t* temp = (size_t*) malloc(*capacity * sizeof(size_t));

		for(int i = 0; i < *size; i++)
		{
			temp[i] = list[i];
		}

		free(list);
		list = temp;

		list[*size] = toAdd;
		*size = *size + 1;
	}

	else
	{
		list[*size] = toAdd;
		*size = *size + 1;
	}

	return list;
}
