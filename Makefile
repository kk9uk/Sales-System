all:
	javac datafile/*.java db/*.java db/schema/*.java menu/*.java *.java

run:
	java -classpath mysql-jdbc.jar:. Main

clean:
	rm datafile/*.class
	rm db/*.class
	rm db/schema/*.class
	rm menu/*.class
	rm *.class
