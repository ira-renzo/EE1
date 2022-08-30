rmdir classes /S /Q
mkdir classes
javac -d classes src/*.java
java -cp classes RandomTableSwing