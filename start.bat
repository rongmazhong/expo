@echo off
start java -jar D:\expo\expo-1.0.jar
ping 127.0.0.1 -n 15>nul
start iexplore.exe -k "http://localhost:80/"
:exit
exit