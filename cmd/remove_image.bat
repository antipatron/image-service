@echo off
echo.
echo ##############################################################

IF NOT "%IMAGE_NAME%"=="" goto removeImage

call set-env.bat

:removeImage
echo Removing image %IMAGE_NAME%
docker rmi %IMAGE_NAME%

