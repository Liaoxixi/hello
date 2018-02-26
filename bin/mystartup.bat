title Hello World
@echo off
cd /d %~dp0
cd ..

set JAVA_OPTS=-Xms64m -Xmx512m
set CLASSPATH=.;.\lib\*;.\config;.\bin\bootstrap.jar

if exist "%JAVA_HOME%\bin\java.exe" goto okHome


echo The JAVA_HOME environment variable is not defined correctly
echo JAVA_HOME Œ¥…Ë÷√ªÚ…Ë÷√¥ÌŒÛ
@pause
goto end

:okhome
echo Using JAVA_HOME	%JAVA_HOME%
echo %cd%
echo "%JAVA_HOME%\bin\java" %JAVA_OPTS% -cp %CLASSPATH% %1% liaoxixi.App
"%JAVA_HOME%\bin\java" %JAVA_OPTS% -cp %CLASSPATH% %1% liaoxixi.App

:end
@pause






::java -cp .;c:\classes\myClass.jar;d:\classes\*.jar packname.mainclassname