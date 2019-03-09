@echo off

:entry
cd %userprofile%\Desktop\youtube

set /p type="What type of media is it? (music:M, Video:V, not applicable:(blank), exit:E) "

if /i "%type%" == "V" (cd .\Video)
if /i "%type%" == "M" (cd .\Music)
if /i "%type%" == "E" (exit)

echo Directory: %cd%

set /p url="Enter url: "

%userprofile%\Desktop\youtube\youtube-dl %url%
goto :entry