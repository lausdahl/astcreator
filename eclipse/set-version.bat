
echo re-setting version for tycho version to work...
cmd /C mvn tycho-versions:set-version -Dtycho.mode=maven -DnewVersion=%1 

echo setting version
cmd /C mvn tycho-versions:set-version -Dtycho.mode=maven -DnewVersion=%2

git status

