CP := -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:commons-beanutils-1.9.4.jar:"

default: run

run: compile
	java $(CP) Main movies.csv

# Main.class requires Frontend.class
Main.class: Main.java Frontend.java
	javac $(CP) Main.java
	javac $(CP) Frontend.java
	

# FrontEnd Requires that a Backend be initalized
Frontend.class: Backend.java BackendInterface.java
	javac $(CP) Backend.java
	javac $(CP) BackendInterface.java
	
# Backend has 3 dependencies:	
Backend.class: MovieInterface.java HashTableMap.java MapADT.java
	javac $(CP) MovieInterface.java
	javac $(CP) MapADT.java
	javac $(CP) HashTableMap.java

MovieDataReader.class: Movie.java MovieDataReader.java
	javac $(CP) Movie.java
	javac $(CP) MovieDataReader.java

compile: Main.class Frontend.class Backend.class MovieDataReader.class

test: compile testData testBackend testFrontend

testFrontend: 
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

testBackend: Backend.class TestBackend.class
	java TestBackEnd
	
TestBackend.class: TestBackend.java
	javac $(CP) TestBackend.java

testData: MovieDataReader.class testDataReader.class TestMovieAndMovieDataReader.class
	java MovieDataReader
	
testDataReader.class: testDataReader.java
	javac $(CP) testDataReader.java

TestMovieAndMovieDataReader.class: TestMovieAndMovieDataReader.java
	javac $(CP) TestMovieAndMovieDataReader.java
	
clean:
	$(RM) *.class
