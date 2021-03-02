run: Main.class 
	# Main.java: initializes frontend 
	javac -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:
	 commons-beanutils-1.9.4.jar:" *.java
	 java -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:
	 commons-beanutils-1.9.4.jar:" Main movies.csv
	
# Main.class requires FrontendInterface.class
Main.class: Main.java FrontendInterface.java
	javac Main.java movies.csv
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
	javac Movie.java
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