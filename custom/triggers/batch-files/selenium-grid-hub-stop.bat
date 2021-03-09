REM To kill process associated with particular port number
(
	REM FOR /F "tokens=5 delims= " %%P IN ('netstat -a -n -o ^| findstr :4444') DO @ECHO TaskKill.exe /PID %%P (to check without kill)
	REM tokens=5 for win7> and 4 for xp
	REM /F for force kill
	FOR /F "tokens=5 delims= " %%P IN ('netstat -a -n -o ^| findstr :4444') DO TaskKill.exe /PID %%P /F
) > "%~dpn0.txt"