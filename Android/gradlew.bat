@ECHO OFF
IF NOT DEFINED JAVA_HOME (
  ECHO Error: JAVA_HOME is not set.
  EXIT /B 1
)
"%JAVA_HOME%\bin\java" -Xmx64m -jar "%~dp0\gradle\wrapper\gradle-wrapper.jar" %*
