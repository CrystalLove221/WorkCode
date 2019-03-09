@echo off
color 02
title "These programs are against my will."

rem Tried allowing script to find %project% anywhere, but don't know how to put a find result into a 
rem variable, let alone stopping find after first match.
rem (for /r c:\ %i in (.) do echo %i) | find "%project%"

cd %userprofile%\Desktop\trh221hogue

echo Changed directory to %userprofile%\Desktop\trh221hogue.
echo Let's begin the real tests...
echo I'll cheer you on!
rem %userprofile%\Pictures\cheer.gif
rem timeout /t 10 /nobreak > nul

:compile
echo Compiling...
javac -d trh221 -s TeachingRecord TeachingRecord\TeachingRecord.java
echo Saved .class to trh221 and source to TeachingRecord

rem taskkill /F /IM Microsoft.Photos.exe > nul
rem Future: check error code of compilation...
choice /M "Did it compile?"

if %errorlevel% == 1 (echo Good. Let's keep going...)
if %errorlevel% == 2 (
	echo What??!...
	timeout /t 3 /nobreak > nul
	echo This can't be...
	timeout /t 3 /nobreak > nul
	echo Fix it or I'll destroy you!!
	%userprofile%\Pictures\yunokill.gif
	pause
	taskkill /F /IM Microsoft.Photos.exe > nul
	goto :compile
)

echo Time to jar this place up with some elexecution!
%userprofile%\Pictures\RailgunMisaka.gif
timeout /t 1 /nobreak > nul

cd trh221
jar cfmv TeachingRecord.jar Manifest.txt TeachingRecord.class
s
:run
java -jar TeachingRecord.jar
taskkill /F /IM Microsoft.Photos.exe > nul

choice /M "Did it run to your liking?"
if %errorlevel% == 1 (
	echo Good. Now, let me go, please. I'm sleepy...
	%userprofile%\Pictures\sleepy.gif
)
if %errorlevel% == 2 (
	echo ...
	timeout /t 1 /nobreak > nul
	echo Ok...
	timeout /t 1 /nobreak > nul
	%userprofile%\Pictures\yunoscary.gif
	pause
	taskkill /F /IM Microsoft.Photos.exe > nul
)

choice /C "FR" /M "Fix or Run again?"
if %errorlevel% == 1 (
	echo I'll be waiting...
	pause
	cd ..
	goto :compile
)
if %errorlevel% == 2 (goto :run)

pause