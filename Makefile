all: classes

clean:
	rm -rf classes

classes:
	javac -d classes -sourcepath src src/Main.java

run: classes
	java -cp classes Main

.PHONY: all clean classes run