@echo off
cd ..
echo.

echo Step 0: Add environment variable.
call cmd/set-env.bat

echo.
echo Step 1: Stop container
call cmd/stop_container.bat

echo.
echo Step 2: Remove container
call cmd/remove_container.bat

echo.
echo Step 3: Remove image
call cmd/remove_image.bat

echo.
echo Step 4: Update jar
call .\mvnw clean install -DskipTests

echo Step 5: Start application
call cmd/build-docker.bat

