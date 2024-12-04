all: classes

clean:
	rm -rf classes

classes:
	javac -d classes -sourcepath src src/Main.java

run: classes
	java -cp classes Main

test:
	javac -d classes -cp src/test/junit.jar -sourcepath src src/test/Tests.java
	java -jar src/test/junit.jar -cp classes -c test.Tests


.PHONY: all clean classes run test