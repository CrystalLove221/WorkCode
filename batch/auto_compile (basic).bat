@echo off
color 02
title "These programs are against my will."

echo Let's begin the real tests...
echo I'll cheer you on!

:compile
cd %userprofile%\Desktop\trh221hogue
echo Changed directory to %cd%

echo Compiling...
javac -d . TeachingRecord\TeachingRecord.java

choice /M "Did it compile?"

if %errorlevel% == 1 (echo Good. Let's keep going...)
if %errorlevel% == 2 (
	echo Fix it or I'll destroy you!!
	pause
	goto :compile
)

echo Time to jar this place up and do some execution!
jar cfmv TeachingRecord.jar Manifest.txt TeachingRecord.class

:run
java -jar TeachingRecord.jar

choice /M "Did it run to your liking?"
if %errorlevel% == 1 (
	echo Good. Now, let me go, please. I'm sleepy...
)
if %errorlevel% == 2 (
	echo Well... This is... Ok...
)

choice /C "FRE" /M "Fix or Run again? E to exit."
if %errorlevel% == 1 (
	echo I'll be waiting...
	pause
	goto :compile
)
if %errorlevel% == 2 (goto :run)
if %errorlevel% == 3 (exit)

pause