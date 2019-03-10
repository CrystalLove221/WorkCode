/*
	Team CSE109: Fall 2072
	Tyler Hogue (codename "LadyLibido")
	Agent code: trh, agent #221 (Saints ID: trh221)

	This is a simulation designed by the alien race called Zin.
	Zinyak, the leader, has invaded earth to trap intelligent people in it
	to psychologically break them. He traps them inside their worst nightmare
	to accomplish this goal.

	But, team CSE109, led by Commander Loew, fight their way out of the simulation.
	To ensure the team cannot return home when they escape, Zinyak atomized
	the earth. So, the team is forced to take down the simulation and its evil creator.

	After breaking the simulation, fighting many mobs and facing
	many trials,  they make their way to Zinyak's throne room, where Loew takes over and fights
	Zinyak for the last time. Developing a highly sophisticated bot, Loew overthrows Zinyak, lowering]
	his defenses so he can make his finishing move. Loew rips Zinyak's head off and raises it high
	in triumph.

	Program #7000, Grand Finale (Defeat Zinyak!)

	Disclaimer: Zinyak is the main antagonist from Saint's Row IV
				and all references above come from that game.
				LadyLibido is my preferred name in MMORPGs
*/

#include <netdb.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <string>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <sys/socket.h>
#include <iostream>
#include <netinet/in.h>
#include <errno.h>
#include <vector>

using std::vector;
using std::string;
using std::endl;
using std::cerr;

char* readSock(int sock, int& done);
int won(char* data);
size_t readSize(int fd);
char* readData(int fd, size_t amt);
void writeCmd(int sock, char* cmd);

int checkFront(string& data, char face);
void turnR(int& face);
void turnL(int& face);

int main(int argc, char** argv)
{
	if(argc != 4)
	{
		cerr << "Too few or too many args: ./client <seed> <rows> <cols>" << endl;
		return 1;
	}

	if((size_t)atoi(argv[2]) > 5000 || (size_t)atoi(argv[3]) > 5000)
	{
		cerr << "Too many rows and/or cols! Range: 1-5000" << endl;
		return 1;
	}

	if((size_t)atoi(argv[2]) < 1 || (size_t)atoi(argv[3]) < 1)
	{
		cerr << "Too few rows and/or cols! Range: 1-5000" << endl;
		return 1;
	}

	string connection = "../../../merle/CSE109/prog7student/connection.txt";

	int fd = open(connection.c_str(), O_RDONLY);
	if(fd == -1)
	{
		perror("Open failed");
		return 1;
	}

	short port = 0;
	size_t toRead = sizeof(short);
	size_t haveRead = 0;
	ssize_t reading;
	char* buffer = (char*)&port;
	while(toRead && (reading = read(fd, buffer+haveRead, toRead)) != -1)
	{
		toRead -= reading;
		haveRead += reading;
	}

	size_t host_len = readSize(fd);
	char* hostname = readData(fd, host_len);
	close(fd);

	int sock = socket(AF_INET, SOCK_STREAM, 0);
	if(sock == -1)
	{
		perror("Error creating socket");
		return 1;
	}

	sockaddr_in client;
	memset(&client, 0, sizeof(sockaddr_in));

	client.sin_family = AF_INET;
	client.sin_port = htons(port);

	hostent* host = gethostbyname(hostname);
	if(!host)
	{
		perror("hostname bad");
		return 1;
	}

	memmove(&(client.sin_addr.s_addr), host->h_addr_list[0], 4);

	int conn = connect(sock, (sockaddr*)&client, sizeof(sockaddr_in));
	if(conn == -1)
	{
		perror("can't connect");
		return 1;
	}

	free(hostname);

	vector<string> compass(4);
	compass[0] = "'N'";
	compass[1] = "'E'";
	compass[2] = "'S'";
	compass[3] = "'W'";

	vector<char> face(4);
	face[0] = 'N';
	face[1] = 'E';
	face[2] = 'S';
	face[3] = 'W';

	int currFace = 1;

	//load maze for first time
	string seed(argv[1]);
	string rows(argv[2]);
	string cols(argv[3]);

	string load = "L " + seed + " " + rows + " " + cols;
	size_t len = load.size();

	char* cload = (char*)malloc(len+1);
	strcpy(cload, load.c_str());

	char* data = NULL;
	int done = 0;
	data = readSock(sock, done);
	string str(data);

	writeCmd(sock, cload);

	free(cload);
	free(data);

	//fight Zinyak
	char cmd = '\0';
	while(1)
	{
		data = readSock(sock, done);
		str = data;

		if(done)
		{
			free(data);
			break;
		}

		turnL(currFace);

		while(!checkFront(str, face[currFace]))
		{
			turnR(currFace);
		}

		cmd = face[currFace];
		writeCmd(sock, &cmd);
		free(data);
	}

	close(sock);
	return 0;
}

size_t readSize(int fd)
{
	size_t amt = 0;
	size_t toRead = sizeof(size_t);
	size_t haveRead = 0;
	ssize_t reading;
	char* buffer = (char*)&amt;
	while(toRead && (reading = read(fd, buffer+haveRead, toRead)) != -1)
	{
		toRead -= reading;
		haveRead += reading;
	}

	return amt;
}

char* readData(int fd, size_t amt)
{
	char* data = (char*)malloc(amt+1);
	size_t toRead = amt;
	size_t haveRead = 0;
	char* buffer = data;
	ssize_t reading;
	while(toRead && (reading = read(fd, buffer+haveRead, toRead)) != -1)
	{
		toRead -= reading;
		haveRead += reading;
	}

	data[amt] = '\0';
	return data;
}

char* readSock(int sock, int& done)
{
	char* temp = NULL;
	while(1)
	{
		size_t amt = readSize(sock);
		char* data = readData(sock, amt);
//		cerr << data;

		string str(data);
		if(str == "command> ")
		{
			free(data);
			break;
		}

		free(temp);
		temp = (char*)malloc(amt+1);
		strcpy(temp, data);

		if(amt > 50 && won(data))
		{
			done = 1;
			free(data);
			break;
		}

		free(data);
	}

	return temp;
}


int won(char* data)
{
	string str(data);

	if(str.find("You've won", 0) != string::npos)
	{
		cerr << data;
		return 1;
	}

	return 0;
}

void writeCmd(int sock, char* cmd)
{
	size_t len = strlen(cmd);
	size_t toWrite = sizeof(size_t);
	size_t haveWritten = 0;
	ssize_t written;
	char* buffer = (char*)&len;
	while(toWrite && (written = write(sock, buffer+haveWritten, toWrite)) != -1)
	{
		toWrite -= written;
		haveWritten += written;
	}

	toWrite = len;
	haveWritten = 0;
	buffer = cmd;
	while(toWrite && (written = write(sock, cmd, toWrite)) != -1)
	{
		toWrite -= written;
		haveWritten += written;
	}
//	cerr << endl;
}

void turnR(int& face)
{
	if(face == 3)
	{
		face = 0;
	}

	else
	{
		face++;
	}
}

void turnL(int& face)
{
	if(face == 0)
	{
		face = 3;
	}

	else
	{
		face--;
	}
}

int checkFront(string& data, char face)
{
	if(face == 'N' && data.find("You can go 'N'") != string::npos)
	{
		return 1;
	}

	if(face == 'E' && data.find("You can go 'E'") != string::npos)
	{
		return 1;
	}

	if(face == 'S' && data.find("You can go 'S'") != string::npos)
	{
		return 1;
	}

	if(face == 'W' && data.find("You can go 'W'") != string::npos)
	{
		return 1;
	}

	return 0;
}
