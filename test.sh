echo "Compiling Tests..."
if javac -cp .:hamcrest-core-1.3.jar:junit-4.12.jar *.java; then
    echo "Executing Tests..."
    java -cp .:hamcrest-core-1.3.jar:junit-4.12.jar TestRunner
else
    echo "TESTS FAILED TO COMPILE, NO TESTS EXECUTED"
fi
