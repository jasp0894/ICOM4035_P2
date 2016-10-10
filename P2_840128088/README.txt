******************************README*****************************

Author: Jesus Antonio Sanchez Perez
File: ICOM4035 Data Structures
Version: 2.0
SN: 840-12-8088

*****************************************************************
This document describes the general behavior of P2_840128088.
The main idea of the project is to populate Random Access Files.
The latter follows a specific formatting for its bytes.
Only files complying with the format, are valid. The format
actually represents a table. The population action is done in
either two ways: by reading the content from a valid file and 
request the user to enter data or starting with an empty file 
and request the user to enter all the data. Finally, the data 
is analyzed to determine some characteristics of it.

The project is a Java coded project which consists of 5 packages. 
The package named p2MainClasses contain the two main classes of the 
project: DataFilePopulator.java and TableAnalyzer.java. 

------------------------------------------------------------------
			GENERAL DESCRIPTION
------------------------------------------------------------------


The project is contained in the folder named P2_4035_840128088_161.zip


P2_4035_840128088_161/P2_840128088      contains all project's contents

  ~/P2_840128088/bin                         contains all class files

  ~/P2_840128088/src                            contains all packages

     ~/dataManagementClasses              This package contains the classes that 
					define the how the data is managed throughout the program.
          ~/Attribute.java
          ~/AttributeInSchema.java
          ~/BooleanDataReader.java
          ~/ByteDataReader.java
          ~/CharDataReader.java
          ~/DateDataReader.java
          ~/DoubleDataReader.java
          ~/FloatDataReader.java
          ~/IntDataReader.java
          ~/LongDataReader.java
          ~/ShortDataReader.java
          ~/BooleanDataWriter.java
          ~/ByteDataWriter.java
          ~/CharDataWriter.java
          ~/DateDataWriter.java
          ~/DoubleDataWriter.java
          ~/FloatDataWriter.java
          ~/IntDataWriter.java
          ~/LongDataWriter.java
          ~/ShortDataWriter.java
          ~/TableSchema.java
          
    ~/generalUtilities                  This package contains all classes 
					that serve as tools to manage data and process.
          ~/DataUtils.java
          ~/TYPE.java
               

    ~/interfaces                    Contains definitions of interfaces 
    				used in the project.   
          ~/DataReader.java         
          ~/DataWriter.java
  

    ~/p2MainClasses			Contains the project's two main classes.   
          ~/DataFilePopulator.java
          ~/TableAnalyzer.java
          
    ~/tableCollectionClasses		Contains the classes that define the Table
    					data type as per the specifications of the project.  
          ~/Record.java
          ~/Table.java
          

    ~/inputData				Contains text files to read from or write to.
	
    

  ~/P2_840128088/doc                     contains Javadoc files	
   



------------------------------------------------------------------
		      EXECUTION INSTRUCTIONS
------------------------------------------------------------------

~/p2MainClasses contains two classes that provide main method.
Therefore, to launch these classes it's necessary to compile and run them.

Steps to run in eclipse IDE:

(1) Create an empty java project.

(2) Import P2_4035_840128088_161.zip and save it on the empty folder.

(3) Go to P1_840128088/src and drag and drop all its content onto
    the default package created automatically for the java project.
    Now called src too.

(4) Go to src of the  created java project and open p2MainClasses

(5) Open, for example, DataFilePopulator.java and go to Run Configurations
	look for Arguments tab and type the name of the text file you wish
	to work on and click run. If the file does not exist, a new one will 
	be created with the given name. The file will be inside InputData folder.


Steps to run from CMD:

(1) Navigate to a workspace or directory that contains the project
    named, say, p2j.

(2) Go to ~/p2j/src/testerClasses from the command line prompt

(3) call the java compiler to compile all source files in that folder.
   
     ~/p1j/src/testerClasses> javac -cp .. *.java
     
    *NOTE: make sure to leave the whitespaces

(5) Go back to ~/p1j/src by typing

     ~/p1j/src/testerClasses> cd ..

(6) From there, execute the JVM to run any of the strategies:
    For example,

    ~/p1j/src>java testerClasses/MapTester

(7) The same concept (steps 2 to 6) applies for the execution of
	 ~/experimentalClasses/ExperimentalTrials.java
    where the package will be experimentalClasses instead of
    testerClasses.
	









