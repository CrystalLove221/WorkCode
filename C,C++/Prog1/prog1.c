/*
	CSE 109: Fall 2018
	Tyler Hogue
	trh221

	This program will read in a string of at least one argument and 
	at most unique arguments (duplicates are ignored).

	For each argument specified, its corresponding transformation will be
	applied to each line of text read in from the user

	The resulting output will be output to the user's console directly
	below the user's input.

	transformations:
	-r: reverse every word in a line
	-o: rotate the first letter of each word to the first letter of the
		folowing word. The first letter of the last word in the
		user's input will be wrapped to the first of the first word
		of the input.
	-n: Remove all digits (0-9) from input
	-t: Toggle letter casing of input (lower to upper, upper to lower)

	Note 1: All non-whitespace characters are treated as part of a word.
	Note 2: Press Ctrl-D on a blank line of input to terminate the
			program.

	Program #1
*/

#include <stdio.h>
#include <ctype.h>
#include <stdlib.h>
#include <string.h>

void reverseWord(char* line, size_t length);
void toggle(char* line, size_t length);
void rotate(char* line, size_t length);
size_t removeDigits(char* line, size_t length);

int main(int argc, char** argv)
{

	char* line = NULL;
	size_t length = 0;

	// initialize flags for reading cli args
	int reverseflag = 0;
	int toggleflag = 0;
	int removeflag = 0;
	int rotateflag = 0;

	for(int i = 1; i < argc; i++)
	{
		if(strcmp(argv[i], "-r") == 0)
		{
			reverseflag = 1;
		}

		else if(strcmp(argv[i], "-o") == 0)
		{
			rotateflag = 1;
		}

		else if(strcmp(argv[i], "-t") == 0)
		{
			toggleflag = 1;
		}

		else if (strcmp(argv[i], "-n") == 0)
		{
			removeflag = 1;
		}

		else
		{
			fprintf(stderr, "Invalid command line option\n");
			return 1;
		}
	}

	int n = 0;
	while(n != -1)
	{
		n = getline(&line, &length, stdin);

		if(n == -1)
		{
			free(line);
			line = NULL;
			break;
		}

		length = strlen(line);

		if(toggleflag == 1)
		{
			toggle(line, length);
		}

		if(removeflag == 1)
        {
            length = removeDigits(line, length);
        }

		if(rotateflag == 1)
        {
            rotate(line, length);
        }

		if(reverseflag == 1)
		{
			reverseWord(line, length);
		}

		fprintf(stdout, "%s", line);

		free(line);
		line = NULL;
	}

	line = NULL;
	return 0;

}

void reverseWord(char* line, size_t length)
{
	int index = 0;

	for(int i = 0; i < length; i++)
	{
		if(isspace(line[i]) || line[i] == '\0' || line[i] == EOF)
       	{
        	for(int j = i - 1; j > index; j--)
			{
				char tmp = line[index];
				line[index] = line[j];
				line[j] = tmp;
				index++;
			}

			index = i + 1;
		}
	}
}

void toggle(char* line, size_t length)
{

	for(int i = 0;i < length; i++)
	{
		if(isupper(line[i]))
		{
			line[i] = line[i] + 32;
		}

		else if(islower(line[i]))
		{
			line[i] = line[i] - 32;
		}
	}
}

void rotate(char* line, size_t length)
{
	char lettercopy; //hold char to be inserted in each word
	char letter; // hold temp char of a word
	int iword = 0;
	int iwrap = 0; // keep index of first letter in a line

	//fin the first nons-whitespace char
	while(isspace(line[iword]))
	{
		iword++;
	}

	lettercopy = line[iword];
	letter = lettercopy;
	iwrap = iword;

	int i;
	for(i = iword; i < length; i++)
	{
		iword = i;

		if(isspace(line[i]))
		{
			continue;
		}

		if(isspace(line[i]) == 0)
		{
			letter = line[i];
			line[i] = lettercopy;
			lettercopy = letter;

			while(isspace(line[i]) == 0)
			{
				i++;
			}
		}
	}

	if(line[i] == '\n' || line[i] == '\0' || line[i] == EOF)
	{
		line[iwrap] = lettercopy;
	}

	return;
}

size_t removeDigits(char* line, size_t length)
{
	int index = 0;
	for(int i = 0; i < length; i++)
	{
		if(isdigit(line[i]))
		{
			continue;
		}

		char tmp = line[index];
		line[index] = line[i];
		line[i] = tmp;

		index++;

	}

	size_t tmp = length;
	for(int j = index; j < length; j++)
	{
		line[j] = '\0';
		tmp--;
	}

	length = tmp;

	return length;
}
