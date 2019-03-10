#include <Servo.h>
Servo uServo;
Servo lServo;

int CDSPin = 0;
int buttonPin = 2; // Emergency stop button
int emptyTime = 0;
int eLight = 3; //Emergency stoplight
int redLED = 4; //Starving LED
int usStatus = 0; //0=closed, 1=open
int lsStatus = 0;
int thresh = 160; //Sensor threshold

//Threshold 100
//>100 No ball
//<100 Ball
void setup()
{
  uServo.attach(10); //Upper servo is on pin 10
  lServo.attach(9); //Lower servo is on pin 9
  pinMode(eLight, OUTPUT);
  pinMode(buttonPin, INPUT);
  usOpen(); //Open the upper gate (Starts with this open to allow a ball in
  lsClose(); //Close the lower gate
  Serial.begin(9600);

}
void loop()
{
  int time = millis() / 1000; //Time in seconds
  if (time % 5 == 0) // Do every 10 seconds
  {
    if(hasBall() == 1)
    {
      usClose(); //Close upper gate
      discharge(); // Discharge a single ball
      usOpen(); //Re-open the upper gate
    }
  }
  delay(100);
  dataPrint();
  think100();
}

void dataPrint() //Prints all of the data separated by tabs in the order: Raw Sensor Data, Upper Servo Status, Lower Servo Status, Pause
{
  Serial.print(sensorRaw());
  Serial.print("\t");
  Serial.print(usStatus);
  Serial.print("\t");
  Serial.print(lsStatus);
  Serial.print("\t");
  Serial.println(stopPressed());
}

void think100() //Think event that runs every 100ms
{
  //This loop runs every 100 ms so I will use it for priority tasks 
  if(hasBall() == 1) { emptyTime = 0; digitalWrite(redLED, LOW); } else { emptyTime += 100; } //Turn off the LED if the ball is in the chamber or count up if there is no ball
  if(emptyTime > 3000) { digitalWrite(redLED, HIGH); } //Turn on the starving LED after a certain amount of time 
  
  if(stopPressed()) {emergencyStop();} //Enter the emergency stop routine
}

int stopPressed() //returns 1 if an emergency stop is requested
{
  return digitalRead(buttonPin);
}

void emergencyStop() //Subroutine for an emergency stop
{
  lsClose(); //Close lower gate to stop discharging balls
  usClose(); //Close upper servo to stop feeding
  digitalWrite(eLight, HIGH); //Turn on emergency stop light
  while(stopPressed()) {dataPrint(); delay(100);} //Remain in the emergency stop routine until the button isd released
  usOpen(); // Reopen upper servo to allow balls to enter
  digitalWrite(eLight, LOW); // Turn off the emergency stop light when the button is released
}
void feed() // Feeds a single ball
{
  usOpen();
  delayD(1000);
  if (hasBall() == 1) {
    usClose();
    delayD(1000);
  }
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
  if (sensorRaw() >= 160)
  {
    int starving=1;
    return 0;
  }
  else{
    int starving=0;
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
    think100();
  }
}

void usClose() //Closes the upper gate
{
  uServo.write(0); //Close gate
  usStatus = 0;
  delay(200);
}

void usOpen() //Opens the upper gate 
{
  uServo.write(90); //Open gate
  usStatus = 1;
  delay(200);
}

void lsClose() //Closes the lower gate
{
  lServo.write(0); //Close gate
  lsStatus = 0;
  delay(200);
}

void lsOpen() //Opens the lower gate
{
  lServo.write(90); //Open gate
  lsStatus = 1;
  delay(200);
}





























































