set APK=%1
set DEST=%2
set IS_CHANNEL=%3
echo %APK%
echo "------ jiagu  running..."

md %DEST%
java -jar ../jiagu/jiagu.jar -login FantasySkyland zdy19941209
java -jar ../jiagu/jiagu.jar -importsign ../app/keystore.jks 123456 kljz 123456
java -jar ../jiagu/jiagu.jar -showmulpkg
java -jar ../jiagu/jiagu.jar -config -crashlog -x86 -msg
if %IS_CHANNEL% ==1 (java -jar ../jiagu/jiagu.jar -jiagu %APK% %DEST% -autosign -pkgparam ../jiagu/channels.txt -automulpkg) else (java -jar ../jiagu/jiagu.jar -jiagu %APK% %DEST% -autosign)

echo "------ jiagu  finished!"
