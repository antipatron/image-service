@echo off
echo.
echo ##############################################################

IF NOT "%CONTAINER_NAME%"=="" goto removeContainer

call set-env.bat

:removeContainer
echo Removing container %CONTAINER_NAME%
docker rm %CONTAINER_NAME%

