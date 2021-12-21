@echo off
echo.
echo ##############################################################

IF NOT "%CONTAINER_NAME%"=="" goto stopContainer

call set-env.bat

:stopContainer
echo Stopping container %CONTAINER_NAME%
docker stop %CONTAINER_NAME%



