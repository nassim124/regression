#!/bin/bash

# project name
echo -n "Enter project name > "
read projectName

# Revision ID
echo -n "Enter revision ID > "
read projectRevision

# create workspace
mkdir $projectName
cd $projectName
pwd

# create subdir for revision
mkdir $projectName$projectRevision
cd $projectName$projectRevision

# repository path to checkout
echo "Enter the repository path"
read reposPath

# checkout the project
svn checkout -r$projectRevision $reposPath
cd trunk
path="/${PWD#*/}"
echo $path

# run maven test
mvn -fn clean test

# Search the failed tests in the maven surfire repport
test=$(java -cp /udd/naliche/workspace/ SearchAllDir $path)


# delete the .txt extension and separate tests with "," to put it as parameter in mvn test
test2=$(echo "$test" | sed -e 's/.txt/,/g')

# output result in file and delete last ","
value=`echo ${test2::-1}| tr -d "[:space:]"`
echo $value >/udd/naliche/workspace/$projectName/failedTest.txt
echo "find failed test on /udd/naliche/workspace/$projectName/failedTest.txt"

#mvn -fn "$test2" clean test