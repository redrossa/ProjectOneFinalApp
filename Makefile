run: Main.class 
	# Main.java: initializes frontend 
	#javac -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:
	#commons-beanutils-1.9.4.jar:" *.java
	 java -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:
	 commons-beanutils-1.9.4.jar:" Main movies.csv
	
# Main.class requires FrontendInterface.class
Main.class: Main.java FrontendInterface.java
	javac Main.java 
	javac FrontendInterface.java
	

# FrontEnd Requires that a Backend be initalized
FrontendInterface.class: Backend.java BackendInterface.java
	javac -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:commons-beanutils-1.9.4.jar:" Backend.java
	javac BackendInterface.java
	
# Backend has 3 dependencies:	
Backend.class: MovieInterface.java HashTableMap.java MapADT.java
	javac -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:
	 commons-beanutils-1.9.4.jar:" MovieInterface.java
	javac MapADT.java
	javac HashTableMap.java
	
MovieDataReader.class: Movie.java MovieInterfaceReader.java 
	javac -cp "opencsv-5.3.jar:commons-text-1.9.jar:commons-logging-1.2.jar:commons-lang3-3.11.jar:comomons-collections-3.2.2.jar:commons-collections4-4.4.jar:
	 commons-beanutils-1.9.4.jar:" Movie.java
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
