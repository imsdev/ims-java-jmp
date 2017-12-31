# Sample IMS Java message processing (JMP) application

This sample Java application runs in the IMS Java Message Processing (JMP) region and demonstrates how to code the input messages that are required to trigger an IMS transaction in Java. 

After the application is deployed to the IMS environment, a sample client application is provided that sends the input message to the transaction and displays the output message.

## Scenario
The JMP application will retrieve insurance policies information based on the customer number that is specified by the client application. For simplicity, the insurance policies information is coded in the sample so there is no need for any database setup.

## Repository structure
The files in the `src/` directory has the following structure:

* `main/`
  * `java/`: The sample Java application. See the Sample overview section for more information.
  * `resources/`: Contains .jar files for APIs that are used in this sample, including:
     * IMS Connect API for Java: Used by the client application to access IMS Connect 
     * IMS universal drivers: Used by the JMP application for making DL/I calls.
* `test/`
  * `java/client/`: The client application that specifies the customer number for whom to retrieve insurance policies information. The client application uses the IMS Connect API to connect to IMS. 
  * `resources/workflow/`: A z/OSMF workflow (.xml) file that automates the setup of the z/OS environment for the Java application and dependent region.  The workflow creates the PROCLIB dataset members, the JMP startup procedure, the transaction, the program, and starts all of these resources.

## Program flow / Architecture
![flowdiagram](./media/javainims.jpg)


## Samples overview
Sample application code is available in the src/main/java directory.

* `controller/`: The main Java application
* `customer/info`: 
  * `Customer.java`: Provides methods for retrieving customer information 
  * `CustomerService.java`: Contains hard-coded customer data so a database is not required for this sample.

* `message/`:  
  * `InputMessage.java`: Defines the structure of the input message that the Insurance application receives. It  defines the field name, field type, the start position, and the length of each field. It also specifies the length of the entire message in the call to the super constructor.
  * `CustomerOutputMessage.java`:  Defines the customer output message. This code represents the output message structure for the customer segment. It defines the field name, field type, the start position, and the length of each field. It also specifies the length of the entire message in the call to the super constructor.

* `policy/info`
  * `AutoPolicy.java`: Provides methods for retrieving auto insurance policy information
  * `HousePolicy.java`: Provides methods for retrieving house insurance policy information

  
## Pre-requisites

1. The IMS Universal drivers (imsudb.jar) provided through the Java On Demand Feature FMID must be installed during the SMP/E process.
2. The location for the Java Virtual Machine (JVM) and the IMS Java native code (`libT2DLI.so`) must be specified by using either the `//STDENV DD` statement in a JCL to set Java environment variables and options, or by specifying the settings in the DFSJVMEV member of the IMS PROCLIB data set to specify the settings. 
3. The location of the .jar files for the IMS Universal drivers and the Java applications must be specified by setting Java environment variables and options in a `//STDENV DD` statement or by modifying the DFSJVMMS member in the IMS PROCLIB data set.


Note that for items #2 and #3, the sample z/OSMF workflow can be tailored to handle these tasks, easing the process of environment setup as well as application deployment. 

## Steps

1. Import the IMS Universal drivers (imsudb.jar) into your project. 
2. Ask the System Programmer to set up the environment for running Java applications in the JMP region. 
3. Identify the z/OS UNIX System Services (USS) file system location where the application .jar file should be uploaded. The file system location is defined either in the DFSJVMMS member or in the shell script that is referenced by the `//STDENV DD` statement. Obtain the permission to upload files to the identified location. 
4. Obtain from the System Programmer and System Administrator the following information that is required to access IMS for testing:
  * IP address or host name
  * Data store name
  * Port number
  * IMS Connect port number
  * RACF ID
  * RACF group name
5. Modify the IMS Connect API for Java client application with the correct info to access IMS.
6. Compile the client application.
7. Compile the sample Java application.
8. Export the application .jar file. Upload it to the USS file system in binary mode.
9. Work with the system programmer to ensure that the program, the transaction, and the JMP region are started.
10. Test the JMP program with the client application. 


## More information

A similar sample that actually queries an IMS database is available in the Java in IMS solution adoption kit, as described in details in <a href="https://www.ibm.com/support/knowledgecenter/en/SSEPH2_14.1.0/com.ibm.ims14.doc.sk/ims_apmdovr.htm" target="_blank">IBM Knowledge Center</a>.  This database version of Java sample works in similar ways except the `CustomerService.java` file issues SQL queries to a sample database. You can find more information about the sample Java code and the sample code to set up the Java environment in the solution adoption kit's <a href="https://www.ibm.com/support/knowledgecenter/en/SSEPH2_14.1.0/com.ibm.ims14.doc.sk/ims_apmd_samplesovr.htm" target="_blank">Samples overview</a> section.  