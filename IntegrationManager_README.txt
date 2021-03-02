IntegrationManager README for Project One (CS400 @ UW Madison)
========================================================

Name of IntegrationManager: Mayank Reddy Dornala
@wisc.edu Email of IntegrationManager: dornala@wisc.edu
Group: JF
Team: blue

Complete List of Files:
-----------------------
Lily Boyd - Data Wrangler: 
Movie.java
MovieDataReader.java
testDataReader.java
TestMovieAndMovieDataReader.java

Yuven Sundaramoorthy - Frontend: 
Frontend.java
Main.java

Reno Raksi - Backend
Backend.java
BackendDummyjava
TestBackend.java

Mayank Dornala - Integration
HashTableMap.java

*Remaining Files are .jar files, ReadMe's, or provided interfaces.

Instructions to Build, Run and Test your Project:
-------------------------------------------------
"make compile" then "make run"  or just "make run" will build and run the program

"make test" will show test outputs for DataInput and BackEnd 
**Front end tests were ommited by Developers because they were in the project proposal and weren't changed since original submission.**

Instructions for using Program: 
1. command: "make run". This will bring you to the "Main Menu" 
2. Typing a number at this point will bring up the movie listed at that number (ranking) and the next two movies in the list (no specified genres or ratings at this point)
3. To search by genre, type G (or g) at the main menu and press enter. This will take you into "Genre Selection Mode":
   
   3a: To select a specific genre, type the number it corresponds to. The Capitalized genre is the genre you are currently searching by. 
    To deselect this genre, type its number again, press enter, and it will go back to lower caps indicating that is has been deselected. 
   3b: You can also search by multiple genres: Type two numbers one at a time. Then two genres will be selected. You can do this for any number of genres. 
   3c: type x to go back to search mode (Main Menu). There you can search all movies in your specified genre(s). 
   
4. To search by rating: enter R (or r) and press enter. This will take you into "Rating Selection Mode":
   4a: Ratings surrounded by ()'s are selected ratings. If you were to go back to search mode here (by pressing x), it would let you search movies of 
   your specified rating values (and genres if you selected that). 
   4b: Typing a ratings value number will deselect it (or select it if it is deselected). You can do this for multiple ratings. For example, if I wanted to search movies 
   with ratings above 5, I would deselect ratings 1-4 by typing them one at a time then going back to search mode by entering "x". 
   
By specifying a Genre (using G) and then ratings (using R), you can search by specific genres and ratings. 

Team Member Contributions:
--------------------------
 No Team Members failed to meet expectations. 
 Reno Raksi did go above expectations. He assisted with fixing Frontend bugs, helped me finish up the makefile. and provided neccesary fixes for the 
 HashTableMap class.
 Lily and Yuven also did a great job learning on the fly and making sure they were staying on track. They also 
 helped with Backend bugs and editing the makefile. 
 Everyone filled their roles well, delivered their parts on time and correctly, and collaborated well to deliver the finished product.  

Signature:
----------
Mayank Reddy Dornala
 
