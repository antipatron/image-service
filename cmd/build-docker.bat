@echo off
echo.
echo ##############################################################
echo Starting build with docker...
set/p mongopass=Enter de mongo password
cls
echo.
docker build --tag %IMAGE_NAME% .
docker run --name %CONTAINER_NAME% -d -e MONGO_PASSWORD=%mongopass% -p 7977:7977 %IMAGE_NAME%:latest