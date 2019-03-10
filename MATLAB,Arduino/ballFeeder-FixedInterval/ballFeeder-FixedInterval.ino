#include <Servo.h>
Servo uServo;
Servo lServo;

int CDSPin = 0;
int usStatus = 0; //0=closed, 1=open
int lsStatus = 0;

//Threshold 100
//>100 No ball
//<100 Ball
void setup()
{
  uServo.attach(10); //Upper servo is on pin 10
  lServo.attach(9); //Lower servo is on pin 9
  usClose(); //Close the upper gate
  lsClose(); //Close the lower gate
  Serial.begin(9600);
}

void loop()
{
  int time = millis() / 1000; //Time in seconds
  if (time % 10 == 0) // Do every 10 seconds
  {
    feed(); //Feed a single ball
    discharge(); //Discharge a single ball
  }
  delay(100);
  dataPrint();
}

void dataPrint() //Prints all of the data separated by tabs in the order: Raw Sensor Data, Upper Servo Status, Lower Servo Status
{
  Serial.print(sensorRaw());
  Serial.print("\t");
  Serial.print(usStatus);
  Serial.print("\t");
  Serial.println(lsStatus);
}

void feed() // Feeds a single ball
{
  usOpen();
  delayD(1000);
  usClose();
  delayD(1000);
}

void discharge() //Discharges a single ball
{
  lsOpen();
  delayD(1000);
  lsClose();
  delayD(1000);
}

int hasBall()
{
  if (sensorRaw() > 100)
  {
    return 0;
  } else {
    return 1;
  }
}

int sensorRaw()
{
  return analogRead(CDSPin);
}

void delayD(int t) //A delay but printing data every 100ms: Takes( t = time in ms )
{
	int time = t/100;
	for (int i = 1; i < time; i++) { //Continues printing data every 100ms for 1 second
		delay(100);
		dataPrint();
	}
}

void usClose() //Closes the upper gate
{
  uServo.write(0); //Close gate
  usStatus = 0;
}

void usOpen() //Opens the upper gate 
{
  uServo.write(90); //Open gate
  usStatus = 1;
}

void lsClose() //Closes the lower gate
{
  lServo.write(90); //Close gate
  lsStatus = 0;
}

void lsOpen() //Opens the lower gate
{
  lServo.write(0); //Open gate
  lsStatus = 1;
}

