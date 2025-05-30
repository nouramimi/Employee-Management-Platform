@echo off
title Start Angular, Spring Boot, and XAMPP

:: Start XAMPP (Apache and MySQL)
echo Starting XAMPP...
cd /d "C:\xampp"
start xampp_start.exe

:: Wait for XAMPP to start
timeout /t 10 /nobreak >nul

:: Start Angular Development Server
echo Starting Angular...
cd /d "C:\Users\noura\OneDrive\Bureau\Stage\usersmanagementleoni\usermanagement"
start cmd /k "ng serve"

:: Start Spring Boot Application
echo Starting Spring Boot...
cd /d "C:\Users\noura\OneDrive\Bureau\Stage\usersmanagementleoni"
start cmd /k "mvn spring-boot:run"

echo All services started.
pause
