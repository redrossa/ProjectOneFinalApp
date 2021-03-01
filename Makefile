run: Main.class 
	# Main.java: initializes frontend 
	java Main movies.csv
	
# Main.class requires FrontendInterface.class
Main.class: Main.java FrontendInterface.java
	javac Main.java
	javac FrontendInterface.java

# FrontEnd Requires that a Backend be initalized
FrontendInterface.class: Backend.java BackendInterface.java
	javac Backend.java
	javac BackendInterface.java
	
# Backend has 3 dependencies:	
Backend.class: MovieInterfacejava HashTableMap.java MapADT.java
	javac MovieInterface.java
	javac MapADT.java
	javac HashTableMap.java
	
MovieInterface.class: Movie.java MovieInterfaceReader.java
	javac Moviejava
	javac MovieInterfaceReader.java
	
	
	
compile:
	@echo "FIXME: *make compile* should compile the code for your project"

test: testData testBackend testFrontend

testFrontend:
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

testBackend:
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

testData:
	@echo "FIXME: *make testFrontend* should compile (when needed) and run all your team's tests for this application"

clean:
	$(RM) *.class
