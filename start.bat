@echo off
start java -jar D:\expo\expo-1.0.jar
ping 127.0.0.1 -n 10 >nul
start http://localhost:80/
:exit
exit