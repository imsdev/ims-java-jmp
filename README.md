# Sample IMS Java message processing (JMP) application

The sample Java™ applications run in the IMS™ Java Message Processing (JMP) region and demonstrates how to code the input messages that are required to trigger an IMS transaction in Java.  The samples also show how to define and send the output message. 

After the application is deployed to the IMS environment, a sample client application is provided that sends the input message to the transaction and displays the output message.

Two copies of the sample are provided. The version in the `insurance/` directory requires a sample database, so additional setup steps are required. The version in the `insurancenodb/` directory does not require a database, and has customer data hard-coded in the sample.

## Scenario
A user wants to retrieve the insurance policies information for a specified customer number. The JMP application will retrieve insurance policies information based on the customer number that is specified by the client application. 

## Repository structure
For both versions of the sample, the files in the `src/` directory has the following structure:

* `main/`
  * `java/`: The sample Java application. See the Sample overview section for more information.
  * `resources/`: Contains .jar files for APIs that are used in this sample, including:
     * IMS Connect API for Java: Used by the client application to access IMS Connect.
     * IMS universal drivers: Used by the JMP application for making DL/I calls.
       * The two jars included in the project are for version 14 of IMS.
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
  * `CustomerService.java`: 
     * For the database-version of the sample (`insurance/`): Contains SQL queries to retrieve customer info from the database.
	 * For the no-database-version of the sample (`insurancenodb/`): Contains hard-coded customer data so a database is not required for this sample.

* `message/`:  
  * `InputMessage.java`: Defines the structure of the input message that the Insurance application receives. It  defines the field name, field type, the start position, and the length of each field. It also specifies the length of the entire message in the call to the super constructor.
  * `CustomerOutputMessage.java`:  Defines the customer output message. This code represents the output message structure for the customer segment. It defines the field name, field type, the start position, and the length of each field. It also specifies the length of the entire message in the call to the super constructor.

* `policy/info`
  * `AutoPolicy.java`: Provides methods for retrieving auto insurance policy information
  * `HousePolicy.java`: Provides methods for retrieving house insurance policy information

  
## Pre-requisites
The following configuration steps might require the assistance of a system programmer.

1. The IMS Universal drivers (`imsudb.jar` and `imsutm.jar`) provided through the Java On Demand Feature FMID must be installed during the SMP/E process.
2. The location for the Java Virtual Machine (JVM) and the IMS Java native code (`libT2DLI.so`) must be specified by using either the `//STDENV DD` statement in a JCL to set Java environment variables and options, or by specifying the settings in the DFSJVMEV member of the IMS PROCLIB data set to specify the settings. 
For more information, see [DFSJVMEV (JVM environment settings member)](https://www.ibm.com/support/knowledgecenter/en/SSEPH2_14.1.0/com.ibm.ims14.doc.sdg/ims_dfsjvmev_proclib.htm) in IBM Knowledge Center.
3. The location of the .jar files for the IMS Universal drivers and the Java applications must be specified by setting Java environment variables and options in a `//STDENV DD` statement or by modifying the DFSJVMMS member in the IMS PROCLIB data set. 
For more information, see [DFSJVMMS member of the IMS PROCLIB data set](https://www.ibm.com/support/knowledgecenter/en/SSEPH2_14.1.0/com.ibm.ims14.doc.sdg/ims_dfsjvmms_proclib.htm) in IBM Knowledge Center.


For items #2 and #3 above, the sample z/OSMF workflow can be tailored to handle these tasks, easing the process of environment setup as well as application deployment. 

<b>Note</b>: Start with the version in the `insurancenodb/` directory as it does not require additional database infrastructure setup.  To use the database version of the sample in the `insurance/` directory, there are additional requirements to set up the infrastructure needed for open access to IMS database and to set up the sample IMS insurance database. 
The details are provided in the [Implementing open access for Java applications](https://www.ibm.com/support/knowledgecenter/SSEPH2_14.1.0/com.ibm.ims14.doc.sk/ims_openacc_getstart.htm) topic in the <b>IMS open acess solution adoption kit</b> in IBM Knowledge Center.


## Steps

1. Import the IMS Universal drivers (imsudb.jar) into your project. 
2. Ask your system programmer to set up the environment for running Java applications in the JMP region. 
3. Identify the z/OS® UNIX System Services (USS) file system location where the application .jar file should be uploaded. The file system location is defined either in the DFSJVMMS member or in the shell script that is referenced by the `//STDENV DD` statement. Obtain the permission to upload files to the identified location. 
4. Obtain from your system programmer and system administrator the following information that is required to access IMS for testing:
   * IP address or host name
   * Data store name
   * Port number
   * IMS Connect port number
   * RACF ID
   * RACF group name
5. Modify the IMS Connect API for Java client application with the correct connection info to access IMS.
6. Compile the client application.
7. Compile the sample Java application.
    * Right-click the pom.xml file in the project and select Run As -> Maven install
    * The application jar file will be compiled to the "target" folder of the project. 
8. Export the application .jar file and upload it to the USS file system in binary mode.
9. Work with the system programmer to ensure that the program, the transaction, and the JMP region are started.
10. Test the JMP program with the client application. 


## More information

A Java sample based on the database version that queries an IMS database is available in the [Java in IMS solution adoption kit](https://www.ibm.com/support/knowledgecenter/en/SSEPH2_14.1.0/com.ibm.ims14.doc.sk/ims_apmdovr.htm) in IBM Knowledge Center. 
The solution adoption kit is a set of learning resources such as articles, videos, blog posts, and sample code that helps you and your team through your application modernization journey. 
