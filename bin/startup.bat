title ga-mass-gacenter
@echo off

cd /d %~dp0
cd ..

rem set JAVA_HOME=D:\Java\jdk1.8.0_101
set JAVA_OPTS=-Xms64m -Xmx512m
set MAINWAR=.\bin\bootstrap.jar
set MODEL=-Dspring.profiles.active=prod -Dloader.path=lib,config

if exist "%JAVA_HOME%\bin\java.exe" goto okHome

echo The JAVA_HOME environment variable is not defined correctly
echo JAVA_HOME 未设置或设置错误
@pause
goto end

:okhome
echo Using JAVA_HOME	%JAVA_HOME%
echo "%JAVA_HOME%\bin\java" -jar %JAVA_OPTS% %MODEL% %MAINWAR%
"%JAVA_HOME%\bin\java" -jar %JAVA_OPTS% %MODEL% %MAINWAR%

:end
@pause
