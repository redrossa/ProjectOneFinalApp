CP := -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:commons-beanutils-1.9.4.jar:"

default: run

run: compile
	# Main.java: initializes frontend 
	#javac -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:
	#commons-beanutils-1.9.4.jar:" *.java
	java $(CP) Main movies.csv


# Main.class requires FrontendInterface.class
Main.class: Main.java FrontendInterface.java
	javac $(CP) Main.java
	javac $(CP) FrontendInterface.java
	

# FrontEnd Requires that a Backend be initalized
FrontendInterface.class: Backend.java BackendInterface.java
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


compile: Main.class FrontendInterface.class Backend.class MovieDataReader.class

test: testData testBackend testFrontend

testFrontend:
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

testBackend:
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

testData:
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

clean:
	$(RM) *.class
