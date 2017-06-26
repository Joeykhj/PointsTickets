1. The code is writtin in Java 8, ensure that you have the right JDK installed.
	
	http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

2 To run the code, clone or download it from GitHub
3. Load it using an IDE such as Intellij or Eclipse
4. Go to the Main.java file
5. Run the main method.
6. You should see a welcome message in the console, key in the coordinates in the format shown and Enter
7. You should now see a list of events closest to the coordinates entered. e.g.

	Event: 54, Location: (1.0,2.0), Distance: 2
	Tickets from: $380.66, 35 left
								
8. If you would like to view all the events generated in the grid, un-comment line 14, this will display all the events available after the list of closest events.
9. If you would like to key in the coordinates in the code itself and not through the console, comment out line 11 and un-comment line 12, key in the coordinates in the brackets e.g. (3,2), there will not be a welcome message using this method.



If you do not want to run the code using an IDE, you can run it using any bash or cmd prompt.
 
a. Ensure that your PATH environment variable is also pointing to the bin folder of your JDK.

	https://www.java.com/en/download/help/path.xml

b. Start up your bash or command prompt, change your current directory to the "src" directory from the code
c. Run: 
	javac -cp . com/company/*.java 
   
   this compiles the code using the java compiler and generates *.class files
d. Run:
	java -cp . com/company/Main
   
   you will be greeted by a welcome message as shown in step 6 above.
   If you want to type in the coordinates again, re-run step d.
e. you can repeat steps 8 and 9 by editing the code using a text editor and running javac and java again.


