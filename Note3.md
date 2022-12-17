# C3
### Table of Content
| #   | Title                                                                        |
|-----|------------------------------------------------------------------------------|
| 1   | Serialization in Java: Getting Started with Object Serialization             |
| 2   | Serialization in Java: Using JSON Simple for Serialization & Parsing         |
| 3   | Serialization in Java: Using JSON in Java for Serialization & Parsing        |
| 4   | HTTP Requests in Java: Sending Simple HTTP Requests                          |
| 5   | HTTP Requests in Java: HTTP Requests with Java's HttpClient                  |
| 6   | Java Database Connectivity (JDBC): An Introduction to JDBC                   |
| 7   | Java Database Connectivity (JDBC): Interacting with Databases using RowSets  |
| 8   | Java Database Connectivity (JDBC): Joining & Filtering Data with RowSets     |
| 9   | Java Database Connectivity (JDBC): Batch Executions & Transactions with JDBC |
| 10  | Building Web Applications with JSP: An Introduction to JSP                   |
| 11  | Building Web Applications with JSP: Handling Errors                          |
| 12  | Building Web Applications with JSP: Customizing Responses with Servlets      |
| 13  | Building Web Applications with JSP: Integrating a JSP App with a Database    |
| 14  | Java Web Services: Getting Started with SOAP-based Web Services              |
| 15  | Java Web Services: Integrating Web Services with a Database                  |
| 16  | Java Web Services: Building REST APIs                                        |
| 17  | Java Web Services: Enabling CRUD Operations with REST APIs                   |


## 1: Serialization in Java: Getting Started with Object Serialization
* Serialization in hava allows the conversion of the internal state of an object to a byte stream. Serialization allows us\
to save the state of Java objects out to files or send objects over a network connection. Deserialization then allows us\
to recreate the original object from the bye stream this enabling data transfer.
* The need for serialization results from the fact that complex applications have several components(front end components,\
backend components, microservices, database components e.t.c.) that needs to talk to each other via data or information\
exchange. **Wire Protocol or Format** is used to control information/data exchange at the application level. For example,\
wire protocol allows the transfer for point of generation(A) to point of use(B). If component A needs to send data to \
another component, the data/object need to be converted into some kind of Wire Protocol/Format before the object can\
be passed to component B. The conversion of the object to a state(byte stream state) that can be transferred over a disk\
or persistent storage/network is known as serialization. Serialization objects are also must be deserializable so that\
their byte stream state can be converted back to its original state.
* In essence, a Java object is converted to byte of steam and sent to another component which converts the byte stream\
back to Java object.

**(A) Exploring Serialization and Deserialization**
* Serializable classes are classes whose objects can be converted to a byte stream. Transient modifier is used to mark\
states that should not be serialized. A serializable class is a class that implements the Java **Serializable interface**.
The serializable interface is a **marker interface**; an interface which contains no data members or methods. 
* Hence, a class that implements the serializable interface doesn't need to override any method. Many of the built-in\
classes in java implements the serializable interface.
* The `ObjectOutputStream` class allows us to write out objects directly. ObjectOutPutStream wraps a `FileOutputStream` 
which allows us to write out in a buffered manner to a file on a local machine. 
* The `ObjectIputStream` and `FileInputStream` serves the reverse purpose mentioned above
**Serialization:** writing a serializable object as byte streams into a file.
```java
package serializationparsing;

import java.io.*;
import java.math.BigInteger;
public class EntryPoint {

    public static void main(String[] args) throws IOException{

        BigInteger [] productIds = {
                new BigInteger("56823879535"),
                new BigInteger("8368356582652"),
                new BigInteger("4894347346252763")
        };

        String [] productNames = {"nike shoes", "adidas", "boats"};
        float [] productPrices = {6.5f, 32.1f, 12.7f};

        System.out.println(productIds[0] instanceof Serializable); //true
        System.out.println(productIds instanceof Serializable); //true

        //Wrapper objects of the primitive data types implements serializable but the primitive types doesn't.
        System.out.println(productPrices instanceof Serializable); //true
        System.out.println(Float.valueOf(productPrices[1]) instanceof Serializable); //true ...array
        //System.out.println(tempArray[1] instanceof Serializable); //"Inconvertible types; cannot cast 'float' to 'java.io.Serializable'"

        //Writing the serializable object as byte streams format into a file.
        String productFile= "product.txt";

        //try with resources
        try(
            ObjectOutput objectOutput = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(productFile)))
        ){
            for(int i=0; i<productIds.length; i++){
                objectOutput.writeObject(productIds[i]);
                objectOutput.writeUTF(productNames[i]);
                objectOutput.writeFloat(productPrices[i]);
            }
        } finally{
            System.out.println("***Completed writing record***");
        }
        
    }
}
```
- The _product.txt_ containing byte stream format.
```
¬í sr java.math.BigIntegerŒüŸ©;û I bitCountI 	bitLengthI firstNonzeroByteNumI lowestSetBitI 
signum[ 	magnitudet [Bxr java.lang.Number†¬•”à‹  xpÿÿÿÿÿÿÿÿÿÿÿþÿÿÿþ   ur [B¬óøTà  
xp    :÷—oxw  nike shoes@Ð  sq ~  ÿÿÿÿÿÿÿÿÿÿÿþÿÿÿþ   uq ~    œhëäüxw adidasB ffsq 
~  ÿÿÿÿÿÿÿÿÿÿÿþÿÿÿþ   uq ~    cb ûÛxw boatsAK33
```
**Deserialization:** converting from a byte stream.
```java
package serializationparsing;

import java.io.*;
import java.math.BigInteger;

public class EntryPoint {

    public static void main(String[] args) throws IOException, ClassNotFoundException{

        //Initialization of arrays
        BigInteger [] productId = new BigInteger[3];
        
        String [] productName = new String[3];

        float []  productPrice = new float[3];

        String productFile= "product.txt";

        //try with resources
        try(
            ObjectInputStream objectInPut = new ObjectInputStream(new BufferedInputStream(new FileInputStream(productFile)))
        ){
            for(int i=0; i<3; i++){

                //Important to cast the objects back to the right type in which they were serialized out.
                productId[i] = (BigInteger) objectInPut.readObject();
                productName[i] = objectInPut.readUTF();
                productPrice[i] = (float) objectInPut.readFloat();
            }
        } finally{
            System.out.println("***Completed reading records.***"); //***Completed writing record***
        }

        for(int i=0; i<productId.length; i++){
        System.out.format("Product ID: %d, Product Name: %s , Product Price: %f %n",
                productId[i], productName[i], productPrice[i]);
        }

        /*
        Product ID: 56823879535, Product Name: nike shoes , Product Price: 6.500000
        Product ID: 8368356582652, Product Name: adidas , Product Price: 32.099998
        Product ID: 4894347346252763, Product Name: boats , Product Price: 12.700000
         */

    }
}
```
**(B) Serializing and Deserializing Custom Objects**
A custom class that implements Serializable has the capability for its object to be converted to byte streams format.
```java
package serializationparsing;


import java.io.Serializable;

public class Account implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public static String accountType = "LOAN";

    private long accountNumber;
    private String customerName;
    private double loanBalance;

    public Account(long accountNumber, String customerName, double loanBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.loanBalance = loanBalance;
    }

    //NOTE the static getters and setters
    public static String getAccountType() {
        return accountType;
    }

    public static void setAccountType(String accountType) {
        Account.accountType = accountType;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getLoanBalance() {
        return loanBalance;
    }

    public void setLoanBalance(double loanBalance) {
        this.loanBalance = loanBalance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerName='" + customerName + '\'' +
                ", loanBalance=" + loanBalance +
                '}';
    }
}
```
* **Serialization** 
```java
package serializationparsing;

import java.io.*;
import java.math.BigInteger;

public class EntryPoint {

    public static void main(String[] args) throws IOException, ClassNotFoundException{

        Account account1 = new Account(1111, "Wole Soyinka", 20f);
        Account account2 = new Account(2222, "Sir Newton", 44f);

        String file = "accountFile.txt";

        try(
          ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))
          ){
           objOut.writeObject(account1);
           objOut.writeObject(account2);
        }
    }
}
```
* **Deserialization**
```java
package serializationparsing;

import java.io.*;
public class EntryPoint {

    public static void main(String[] args) throws IOException, ClassNotFoundException{

        String file = "accountFile.txt";

        //Try with resources block
        try(
        ObjectInputStream objInput = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))
                ){
        int i = 0;
        while(i<2)
            {
            System.out.println( (Account) objInput.readObject() );
            i++;
            }
            /*
            Account{accountNumber=1111, customerName='Wole Soyinka', loanBalance=20.0}
            Account{accountNumber=2222, customerName='Sir Newtwon', loanBalance=44.0}
             */
        }
    }
}
```
**(C) Understanding serialVersionUID**
* Every class that is serializable has to have some kind of version number associated with that class. If a class that has\
been serialized has changed (e.g. variables or method changes) after serialization, the version number helps to keep \
track of these changes associated with a particular class and its objects.\
* The `serialVersionUID` attribute can be declared and modified when a serializable class has been modified. Java generate\
this UID by default if it's not specified within the class. It is used to remember the version of a serializable class\
in order to (e.g.) check/verify that the loaded version and serialized object are the same/compatible.
* Its best practice to explicitly specify the serialVersionUID so that it can be easily modified when the changes are\
made to the class.
* Java will raise a `java.io.InvalidClassException` exception if there is mismatch/incompatibility of serialVersionUID in\
the current version of a  class and its object been read.
* If a new field is added to a serializable class is **backward compatible**. Hence, we can still read the older version\
of Account as long as the serialVersionUID is not changed(changing UID is unnecessary).
* The modification of an already existing field leads to **backward incompatibility**. Backward incompatible changes\
should be accompanied by a change of the serialVersionUID.

**(D) Using Transient Fields**
* When a class implements serializable, all of its variables and methods are convertable to byte streams by default.
* Certain fields(e.g. loanBalance) can be excluded from the conversion to byte streams using the `transient` keyword.
```java
import java.io.Serializable;

public class Account implements Serializable {

    public static final long serialVersionUID = 1L;
    public static String accountType = "LOAN";

    private long accountNumber;
    private String customerName;
    private transient double loanBalance; //a 'transient' field

    public Account(long accountNumber, String customerName, double loanBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.loanBalance = loanBalance;
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerName='" + customerName + '\'' +
                ", loanBalance=" + loanBalance +
                '}';
    }
}
```
```java
import java.io.Serializable;

public class Account implements Serializable {

    public static final long serialVersionUID = 1L;
    public static String accountType = "LOAN";

    private long accountNumber;
    private String customerName;
    private transient double loanBalance; //a 'transient' field

    public Account(long accountNumber, String customerName, double loanBalance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.loanBalance = loanBalance;
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerName='" + customerName + '\'' +
                ", loanBalance=" + loanBalance +
                '}';
    }
}
```

**(E) Serializing Nested Object References**
An outermost class that contains a nested class within it (i.e. contains reference to another use defined class), this\
outermost class can only be marked as Serializable only if the nested classes are Serializable.\
Unless the CreditCard class below is marked as Serializable a `java.io.NotSerializableException` **exception** will be 
encountered in the code below.
```java
package serializationparsing;

public class CreditCard {
    private final String creditCardType;

    public CreditCard(String creditCardType){
        this.creditCardType = creditCardType;
    }

    @Override
    public String toString() {
        return "CreditCard{" +
                "creditCardType='" + creditCardType + '\'' +
                '}';
    }
}
```
```java
package serializationparsing;

import java.io.Serializable;
public class Account implements Serializable {

    public static final long serialVersionUID = 1L;
    public static String accountType = "LOAN";
    private long accountNumber;
    private String customerName;
    private transient double loanBalance; //a 'transient' field
    private CreditCard creditCard;
    
    public Account(long accountNumber, String customerName, double loanBalance, String cardType) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.loanBalance = loanBalance;
        this.creditCard = new CreditCard(cardType);
    }
    
    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerName='" + customerName + '\'' +
                ", loanBalance=" + loanBalance +
                ", creditCard=" + creditCard +
                '}';
    }
}
```
```java
package serializationparsing;
import java.io.*;
public class EntryPoint {

    public static void doDeSerialization(String file) throws IOException, ClassNotFoundException{

        try(
                ObjectInputStream objInput = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)))
        ){
            System.out.println("-----------------");
            int i = 0;
            while(i<2)
            {
                System.out.println((Account)objInput.readObject());
                i++;
            }
            System.out.println("-----------------");
        }
    }

    public static void doSerialization(String file, Account account1, Account account2)throws IOException, ClassNotFoundException{
        try(
                ObjectOutputStream objOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(file)))
        ){
            objOut.writeObject(account1);
            objOut.writeObject(account2);
        }
    }
    
    public static void main(String[] args) throws IOException, ClassNotFoundException{

        String file = "accountFile2.txt";

        Account account1 = new Account(134564, "Trump", 78f, "Amex");
        Account account2 = new Account(37353, "France", 86f, "Visa");

        doSerialization(file, account1, account2);

        doDeSerialization(file);
    }
}
```
**(F) Sending Serialized Data over Sockets**
* Serialization allows the communication between applications running on the same network. The serialized byte streams \
can be passed over sockets. Sockets in network programming refers to a software structure that serves as an endpoint on\ 
a network node for two-way communication.
* The Account class is serialized and passed over the network using the Client class. The serialized object is received\ 
and deserialized by the Server class. Java built-in `Socket` class is used to send the data, while `ServerSocket` class\
is used to receive the data.
```java
package serializationparsing;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.Inet4Address;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Client {
    public static void main(String[] args) throws IOException{

        Account account1 = new Account(134564, "Trump", 78f, "Amex");
        Account account2 = new Account(37353, "France", 86f, "Visa");
        Account account3 = new Account(227799, "England", 85.3f, "MC");

        List<Account> accountList = Arrays.asList(account1, account2, account3);

        ObjectOutputStream objOut = null;

        try{
            //A socket is created by instantiating a socket object. Localhost at 127.0.0.1.
            Socket socket =  new Socket(Inet4Address.getLocalHost(), 8020);

            //This is similar to writing byte stream out to a file. Instead of file, we are writing to the socket.
            objOut = new ObjectOutputStream( new BufferedOutputStream(socket.getOutputStream()));

            //For loop to write-out every account in 'accountList' to the instantiated ObjectOutputStream above.
            for(Account account : accountList){
                System.out.println("Writing Accounts to socket stream: \n" + account);

                objOut.writeObject(account);
                objOut.flush();
                Thread.sleep(5000); //sleeping for 5000 milliseconds before writing next account object
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error during serialization.");
            System.exit(1);
        }finally {
            objOut.close(); //closing the stream
        }
    }
}
```
```java
package serializationparsing;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String [] args) throws IOException {

        Socket socket = null;
        ObjectInputStream  objIn = null;

        try(
                ServerSocket serverSocket = new ServerSocket(8020); //port for incoming connection to listen to
                ){
            System.out.println("waiting for a connection...");

            /*
             * This will wait for a connection to be made to this socket.
             */
            socket = serverSocket.accept(); //it waits around till there is an incoming connection to accept

            //reading object from the input stream
            objIn = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));

            while(true){ //while loop that reads object from the incoming stream as long as their object to be read

                Account account = (Account) objIn.readObject();
                System.out.println("Received account information: \n"+account);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error during deserialization");
            System.exit(1);
        }finally {
            socket.close();
            objIn.close();
        }
    }
}
```
- **IMPORTANT:** Since the Server side has to listen for connection. The Server class should be run first before Client.
- Server.class run output
```
waiting for a connection...
```
- Client.class run output
```
Writing Accounts to socket stream: 
Account{accountNumber=134564, customerName='Trump', loanBalance=78.0, creditCard=CreditCard{creditCardType='Amex'}}
Writing Accounts to socket stream: 
Account{accountNumber=37353, customerName='France', loanBalance=86.0, creditCard=CreditCard{creditCardType='Visa'}}
Writing Accounts to socket stream: 
Account{accountNumber=227799, customerName='England', loanBalance=85.30000305175781, creditCard=CreditCard{creditCardType='MC'}}
```
**(G) Using Externalizable Interface**
* The `Externalizable` interface is an alternative to the `Serializable` interface. This interface provides much control\
over how exactly the internal state of the objects are serialized and deserialized.
* However, unlike the Serializable interface, Externalizable interface is not a marker interface(interface with no method\
or parameter). Hence, we need to implement methods (writeExternal() and )within the interface.
* **NOTE:** A class that implements the Externalizable interface must have a default no argument constructor to construct\
and read-in the internal state of the class.
```java
package serializationparsing;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.util.Arrays;

@Getter @Setter
public class Account implements Externalizable {

    public static final long serialVersionUID = 1L;
    public static String accountType = "LOAN";
    private long accountNumber;
    private String customerName;
    private transient double loanBalance; //a 'transient' field
    private int creditCardScore;
    private CreditCard creditCard;
    private String[] tags;

    //The constructor that java will use to construct the object before reading-in the object during serialization.
    public Account(){}
    
    public Account(long accountNumber, String customerName, double loanBalance,
                   String cardType, int creditCardScore, String... tags) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.loanBalance = loanBalance;
        this.creditCard = new CreditCard(cardType);
        this.creditCardScore = creditCardScore;
        this.tags = tags;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountNumber=" + accountNumber +
                ", customerName='" + customerName + '\'' +
                ", loanBalance=" + loanBalance +
                ", credit card =" + creditCard +
                ", tags" + Arrays.toString(tags) +
                '}';
    }
    
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        
        out.writeLong(accountNumber);
        out.writeUTF(customerName);
        out.writeDouble(loanBalance);
        out.writeUTF(Arrays.toString(tags));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.accountNumber = in.readLong();
        this.customerName = in.readUTF();
        this.loanBalance = in.readDouble();

        //Granular control over how the byte stream objects
        String tagString = in.readUTF(); //instance of readUTF
        //To get rid of the opening and closing bracket in an array...see line 75
        //accessing a substring of the tag from character at index 1 to the character at index-1(to get rid of the
        tagString = tagString.substring(1, tagString.length() -1);
        this.tags = tagString.split(", ");
    }
}
```
```java
public class Main{
    public static void main(String[] args) throws IOException, ClassNotFoundException{

        String file = "accountFile2.txt";

        Account account1 = new Account(134564, "Trump",
                78f, "Amex", 5,
                "Reliable", "Good");

        Account account2 = new Account(68434, "Biden",
                75f, "Visa", 6,
                "Reliable", "Great", "High Potential");

        //Convert the accounts to list
        List<Account> accountList = Arrays.asList(account1,account2);

        try(ObjectOutputStream objOut = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(file))
        )
        ){
            for(Account account : accountList){
                System.out.println("Writing: "+account);
                objOut.writeObject(account);
            }

        }catch(NotSerializableException nse){
            nse.printStackTrace();
        }finally{
            System.out.println("***Completed writing objects.***");
        }
    }
}
```

## 2: Serialization in Java: Using JSON Simple for Serialization & Parsing
* Serialization to JavaScript Object Notation (JSON) format is a human-readable alternative to serialization in byte\
streams format. Another drawback of serializing to byte streams is that it can only be serialized by another java app.\
Hence, to exchange information between several applications in different programming language, serialization to byte\
to byte stream is limiting. Hence, the essence of JSON.
* The JSON Simple library enables serialization of custom objects to JSON by implementing the `JSONAware` or `JSONStreamAware`\
interface.
**(A) Common JSON Structures**
1. Single entity: {field:value}
```json
{
"id" : 1,
"first_name" : "Lebron",
"last_name" : "James",
"gender" : "male",
"active" : true
}
```
2. Multiple entities: [{fields1:values1}, {fields2:values2}] is a single array of multiple entities.
```json
[
  
{
"id" : 1,
"first_name" : "Lebron",
"last_name" : "James",
"gender" : "male",
"active" : true
}, 

{
"id" : 2,
"first_name" : "Michael",
"last_name" : "Jackson",
"gender" : "male",
"active" : false
},
  
{
"id" : 3,
"first_name" : "Isaac",
"last_name" : "Newton",
"gender" : "maale",
"active" : false
}
  
]
```
3. Nested entities: [field: {entity}]. Field(s) with entity(ies)
```json
[
  
{

"id" : 1,
"first_name" : "Elon",
"last_name" : "Musk",
"gender" :"male",
"active" : true,
"companies": {
              "Tesla": "Auto",
              "Space X" : "Aerospace"
              },
"children" : "many"
},

{
"id" : 3,
"first_name" : "Isaac",
"last_name" : "Newton",
"gender" : "male",
"active" : false
}
  
]
```
4.  Nested arrays: [field:[array(s)]] The value is/are array(s)

```json
[
  
{
"id" : 1, 
"first_name" : "Elon",
"last_name" : "Musk",
"gender" :"male",
"active" : true,
"emails" : [
             "1@gmail.com",
             "2@hotmail.com"
           ]
},

{
"id" : 3,
"first_name" : "Isaac",
"last_name" : "Newton",
"gender" : "male",
"active" : false
}
  
]
```
5. Arrays nested entities: [ field:[{},{}] ]. The value is an array(s) of entity(ies)
```json
[
{
"id" : 1,
"first_name" : "Elon",
"last_name" : "Musk",
"gender" :"male",
"active" : true,
"emails" : [
              {
                "work":"1@gmail.com",
                "personal":"2@hotmail.com"
              }
           ],
"facebook" : false
},

{
"id" : 3,
"first_name" : "Isaac",
"last_name" : "Newton",
"gender" : "male",
"active" : false
}
]
```
**(B) Writing and Reading JSON Objects**
* **Writing:**
```java
package jsonparsing;

import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;

public class EntryPoint {

    public static void main(String [] args){

        //Creating JSONObject
        //The json object is a non-generic map interface. Hence, the need@SupressWarnings("unchecked"
        //It has the methods of a map such put etc.
        JSONObject diner = new JSONObject();

        diner.put("name","Frank James");
        diner.put("age", 76);
        diner.put("gender",null);
        diner.put("isAllergic",true);
        diner.put("amountSpent",756.3f);

        System.out.println(diner);
        //{"gender":null,"isAllergic":true,"name":"Frank James","age":76,"amountSpent":756.3}
        
        //The JSONObject method writeJSONString() is used to writeout json
        //try with resources
        try(FileWriter file = new FileWriter("diner.json")){
                    diner.writeJSONString(file);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
}
```
* **Reading:**
```java
package jsonparsing;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import java.io.FileReader;
import java.io.IOException;

public class EntryPoint {

    public static void main(String [] args) throws IOException {

        //try with resources
        try(FileReader reader = new FileReader("diner.json")) {

            JSONObject dinerData = (JSONObject) JSONValue.parse(reader);
            System.out.println(dinerData); //{"gender":null,"isAllergic":true,"name":"Frank James","age":76,"amountSpent":756.3}

            String name = (String) dinerData.get("name");
            String gender = (String) dinerData.get("gender");
            long age = (long) dinerData.get("age");

            System.out.format("Name:%s, Age:%d, Gender:%s",name,age,gender);//Name:Frank James, Age:76, Gender:null
        }
    }
}
```
* One disadvantage of the JSON Simple library is the lack of prettyprint/format of the json output.
  
**(C) Writing and Reading JSON Arrays**
Both the JSONObject and JSONArray class is need to work with JSON Arrays.
* Writing
```java
package jsonparsing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class EntryPoint {

    public static void main(String [] args) throws IOException {

        //Instances of the JSONObject()
        JSONObject diner1 = new JSONObject();
        diner1.put("name","Frank James");
        diner1.put("age", 76);
        diner1.put("gender",null);
        diner1.put("isAllergic",true);
        diner1.put("amountSpent",756.3f);

        JSONObject diner2 = new JSONObject();
        diner2.put("name","John Bold");
        diner2.put("age", 22);
        diner2.put("gender", "male");
        diner2.put("isAllergic",false);
        diner2.put("amountSpent",56.3f);
        
        //Instance of JSONArray
        //JSONArray is a List and has the methods associated with a List. It can use be used with Collections library
        JSONArray dinerList = new JSONArray();
        Collections.addAll(dinerList, diner1, diner2);
        System.out.println(dinerList);
        
        //try with resources
        //writing to a file
        try(FileWriter file = new FileWriter("diner.json")) {
            dinerList.writeJSONString(file);
        }
    }
}
```
* Reading
```java
package jsonparsing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;

public class EntryPoint {

    public static void main(String [] args) throws IOException {

        try(FileReader reader = new FileReader("diner.json")) {

            JSONArray dinerList = (JSONArray) JSONValue.parse(reader);

            System.out.println(dinerList);
            /*
            [
            {"gender":null,"isAllergic":true,"name":"Frank James","age":76,"amountSpent":756.3},
            {"gender":"male","isAllergic":false,"name":"John Bold","age":22,"amountSpent":56.3}
            ]
             */

            for(Object object:dinerList){
                System.out.println("------------------------------------");
                JSONObject diner = (JSONObject) object;
                String name = (String) diner.get("name");
                String gender = (String) diner.get("gender");
                long age = (long) diner.get("age");
                System.out.format("Name:%s, Age:%d, Gender:%s %n",name,age,gender);//Name:Frank James, Age:76, Gender:null
                System.out.println("------------------------------------");
                /*
                ------------------------------------
                Name:Frank James, Age:76, Gender:null
                ------------------------------------
                ------------------------------------
                Name:John Bold, Age:22, Gender:male
                ------------------------------------
                 */
            }
        }
    }
}
```
**NOTE:**
* Small JSON parsing doesn't consume much computing memory. The SAX (Simple API for XML) interface was originally developed\
to parse very large XML files. It can be used to parse JSON files. The JSON Simple library gives a parser that mimics the\
SAX interface.
* The SAX interface is a callback-based interface to parsing documents. The entire document is not loaded in memory. Hence,\
very large documents(terabytes) can be parsed using the SAX interface based parser.
* The ContentHandler gives the SAX like interface to our JSON documents.

**(D) Implementing JSONAware and JSONStreamAware**
The JSON Simple library provides interface for converting custom objects to JSON format. Interface `JSONAware`provides any\
class that implements the ability to be serialized into JSON formats and make use of the methods available in the JSON\
Simple library that are available for JSON formats. The objects of class that implements the JSONAware will be seen as a\
JSONObject.
```java
package jsonparsing;

import org.json.simple.JSONAware;
import java.util.Random;
public class Employee implements JSONAware {
       public Random randomNumberGenerator = new Random();
       public String name;
       public String gender;
       public int age;

       public final int employeeID;

    public Employee(String name, String gender, int age) {
        this.employeeID = Math.abs(randomNumberGenerator.nextInt());
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public String toJSONString() {
        return String.format("{\"name\":\"%s\", \"age\":\"%d\", \"gender\":\"%s\", \"ID\":\"%d\"}",
                            name, age, gender, employeeID);
    }
}
```

```java
package jsonparsing;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
public class EntryPoint {

    public static void main(String [] args) throws IOException {
        
        Employee john = new Employee("John Snow", "Male", 24);
        System.out.println(john.toJSONString());//{"name":"John Snow", "age":"24", "gender":"Male", "ID":"868231797"}
        Employee rebeca = new Employee("Rebeca Rabbit", "Female", 19);
        Employee zoe = new Employee("Zoe Zebra", "Female", 18);

        //Because the Employee class implements the JSONAware interface, it is objects are basically JSONObjects.
        //Hence, we don't need to initiate JSONObjects for these employees before adding them to JSONArray
        JSONArray employeeList = new JSONArray();
        employeeList.add(john);
        employeeList.add(rebeca);
        employeeList.add(zoe);

        try(FileWriter writer = new FileWriter("employees.json")){
            employeeList.writeJSONString(writer);
        }
    }
}
```
The interface `JSONAware` is used for files while `JSONStreamAware` interface provides the method `writeJSONString()` \
which permits the writing of the internal state of custom objects out to a stream in the JSON format. 

## 3:  Serialization in Java: Using JSON in Java for Serialization & Parsing

Another library for JSON serialization in Java is the `org.json` (**JSON-JAVA**) library. It provides more powerful\
functionality than the JSON Simple library; it allows pretty printing of JSON objects and allows the access of values\
in JSON field without having to cast those values to the right type.
```xml
<dependency>
  <groupId>org.json</groupId>
  <artifactId>json</artifactId>
  <version>20220320</version>
</dependency>
```

**(A) Serializing a JSON Object** 
The library has an overloaded put() method whose key is always a String and the value can be any other type. Java will\
automatically infer the correct type when the method is used.\
When indentFactor and indent is specified to the toString or Write() methods of the JSONObject, pretty printing is formatted\
based on these parameters.\
Methods of writing to JSON Object
1. Direct writing to JSONObjec or JSONArray
```java
package org;

import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.

import java.io.FileWriter;
import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args){
        JSONObject employeeJson = new JSONObject();

        //The key is always of type String and the value can be of any other type(i.e. the put method is overloaded)
        employeeJson.put("name", "Peter");
        employeeJson.put("age", 39);
        employeeJson.put("sex", 'M');
        employeeJson.put("married", false);
        employeeJson.put("children", JSONObject.NULL);
        employeeJson.put("salary", 90000);

        System.out.println(employeeJson);
        //{"children":null,"sex":77,"name":"Peter","salary":90000,"married":false,"age":39}

        try(FileWriter fileWriter = new FileWriter("employee.json")){
            employeeJson.write(fileWriter);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
        
    }
}
```
2. Writing to a map and parsing the map to JSONObject
```java
package org;

import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EntryPoint {

    public static void main(String[] args){

        Map<String, Object> employeeMap = new HashMap<>();
        employeeMap.put("name", "Peter");
        employeeMap.put("position", "Vice President");
        employeeMap.put("age", 39);
        employeeMap.put("sex", 'M');
        employeeMap.put("married", false);
        employeeMap.put("children", null);
        employeeMap.put("salary", 90000);

        JSONObject employeeJson = new JSONObject(employeeMap);

        System.out.println(employeeMap);
        //{age=39, children=null, married=false, name=Peter, position=Vice President, salary=90000, sex=M}

        System.out.println(employeeJson);
        //{"name":"Peter","position":"Vice President","salary":90000,"married":false,"age":39,"sex":"M"}


        try(FileWriter fileWriter = new FileWriter("employee.json")){
            employeeJson.write(fileWriter);
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

    }
}
```
**Reading**
```java
package org;

import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        try(BufferedReader reader = new BufferedReader(new FileReader("employee.json"))){

            //StringBuilder is used to build the String representation.
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            //Since every system have different line separator, the line below gets the line separator of the local machine
            String ls = System.getProperty("line.separator");

            while( (line = reader.readLine())!= null){
                //appending content of line
                stringBuilder.append(line);
                //appending the line separator
                stringBuilder.append(ls);
            }

            JSONObject employeeJson = new JSONObject(stringBuilder.toString());
            System.out.println(employeeJson);
            //{"children":null,"sex":77,"name":"Peter","salary":90000,"married":false,"age":39}

            //Obtaining values values using their corresponding keys
            String name = employeeJson.getString("name");
            int age = employeeJson.getInt("age");
            int salary = employeeJson.getInt("salary");
            System.out.format("Name:%s, Age:%d, Salary:%d", name, age, salary);
           // Name:Peter, Age:39, Salary:90000
        }
    }
}
```
**Nested JSON**
- Reading:
```java
package org;

import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.FileWriter;
import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        JSONObject employeeJson = new JSONObject();

        //The key is always of type String and the value can be of any other type(i.e. the put method is overloaded)
        employeeJson.put("name", "Peter");
        employeeJson.put("age", 39);
        employeeJson.put("sex", 'M');
        employeeJson.put("married", false);
        employeeJson.put("children", JSONObject.NULL);
        employeeJson.put("salary", 90000);

        JSONObject contactJson = new JSONObject();
        contactJson.put("email", "peter@yahoo.com");
        contactJson.put("phone number", "1112223334");
        contactJson.put("address", "v parku, Prague, Czechia");

        //We append the JSONObject contactJson to the JSONObject employeeJSON
        employeeJson.put("contact",contactJson);
        System.out.println(employeeJson.toString(1));
        /*
        {
             "children": null,
             "sex": 77,
             "contact": {
              "address": "v parku, Prague, Czechia",
              "phone number": "1112223334",
              "email": "peter@yahoo.com"
             },
             "name": "Peter",
             "salary": 90000,
             "married": false,
             "age": 39
         }
         */

        try(FileWriter fileWriter = new FileWriter("employee.json")){
            employeeJson.write(fileWriter, 2, 1);
        }

    }
}

```
- Writing:
```java
package org;

import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{
        //Instead of reading the file line by line, the entire file is read.
        //Paths.get() accessing the complete path to the file.
        // readAllBytes converts the entire content into a byte stream,
        String employeeString = new String(Files.readAllBytes(Paths.get("employee.json")));
        System.out.println(employeeString);
        /*
        {
           "children": null,
           "sex": 77,
           "contact": {
             "address": "v parku, Prague, Czechia",
             "phone number": "1112223334",
             "email": "peter@yahoo.com"
           },
           "name": "Peter",
           "salary": 90000,
           "married": false,
           "age": 39
        }
         */
        //Accessing specific values using their keys.
        JSONObject employeeJson = new JSONObject(employeeString);

        String name = employeeJson.getString("name");
        double salary = employeeJson.getDouble("salary");

        //Using getJSONObject to get the JSONObject within the nested JSONbject
        JSONObject contactJSON = employeeJson.getJSONObject("contact");

        System.out.print("Name: "+name+"\nContact: "+contactJSON.toString(1));

        /*
        Name: Peter
        Contact: {
            "address": "v parku, Prague, Czechia",
                    "phone number": "1112223334",
                    "email": "peter@yahoo.com"
        }
         */
    }
}
```
**(B) Reading and Writing Array Values** 
Similar to the JSON Simple, this libary also uses JSONArray to implement JSON Array
- Writing
```java
package org;
import org.json.JSONArray;
import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.FileWriter;
import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{
        JSONObject employee1 = new JSONObject();

        //The key is always of type String and the value can be of any other type(i.e. the put method is overloaded)
        employee1.put("name", "Peter");
        employee1.put("age", 39);
        employee1.put("sex", 'M');
        employee1.put("married", false);
        employee1.put("children", JSONObject.NULL);
        employee1.put("salary", 90000);
        

        JSONObject employee2 = new JSONObject();

        //The key is always of type String and the value can be of any other type(i.e. the put method is overloaded)
        employee2.put("name", "Peppa");
        employee2.put("age", 43);
        employee2.put("sex", 'F');
        employee2.put("married", true);
        employee2.put("children", 2);
        employee2.put("salary", 180000);

        JSONArray employeeArray = new JSONArray();
        employeeArray.put(employee1);
        employeeArray.put(employee2);
        System.out.println(employeeArray.toString(1));

        /*
        [
             {
              "children": null,
              "sex": 77,
              "name": "Peter",
              "salary": 90000,
              "married": false,
              "age": 39
             },
             {
              "children": 2,
              "sex": 70,
              "name": "Peppa",
              "salary": 180000,
              "married": true,
              "age": 43
             }
        ]
         */

        try(FileWriter fileWriter = new FileWriter("employee.json")){
            employeeArray.write(fileWriter, 1, 0);
        }
    }
}

```
- Reading
The entire file is read in as bytes and casted into String. The String object is then converted to JSONArray. The JSONArray\
contains two JSONObjects whose contents are accessed using forloop.
```java
package org;
import org.json.JSONArray;
import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        String employeeString = new String(Files.readAllBytes(Paths.get("employee.json")));

        System.out.println(employeeString);
        /*
        [
             {
              "children": null,
              "sex": 77,
              "name": "Peter",
              "salary": 90000,
              "married": false,
              "age": 39
             },
             {
              "children": 2,
              "sex": 70,
              "name": "Peppa",
              "salary": 180000,
              "married": true,
              "age": 43
             }
         ]
         */

        //
        System.out.println(employeeString.length()); //219

        //Conversion of the String (employeeString) to JSONArray. The JSONArray contains two JSONObjects
        JSONArray employeeStringAsJSONArray = new JSONArray(employeeString);
        System.out.println(employeeStringAsJSONArray.length());

        //Using forloop for iterate and extract the content of the JSONObjects contained with the JSONArray
        for(int i=0; i<employeeStringAsJSONArray.length(); i++){

            JSONObject employeeJSONArrayTOJSONObject = employeeStringAsJSONArray.getJSONObject(i);
            String name = employeeJSONArrayTOJSONObject.getString("name");
            Integer age = employeeJSONArrayTOJSONObject.getInt("age");
            Integer salary = employeeJSONArrayTOJSONObject.getInt("salary");

            System.out.println("---------------------------------");
            System.out.format("Name:%s, Age:%d, Salary:%d %n", name, age, salary);
            System.out.println("---------------------------------");

            /*
             ---------------------------------
             Name:Peter, Age:39, Salary:90000
             ---------------------------------
             ---------------------------------
             Name:Peppa, Age:43, Salary:180000
             ---------------------------------
             */

        }
    }
}
```
The type specif methods optInt(), optString() e.t.c can be used to access keys/values that might not be present in all \
the JSONObjects contained within a JSONArray.

**(C) Inserting Arrays into JSON Structures** 
A JSON structure in which a key has a value of array type.
1. First approach: adding a JSONArray to a base JSONObject.
```java
package org;
import org.json.JSONArray;
import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        JSONObject employee1 = new JSONObject();

        employee1.put("name", "Zoe Zebra");
        employee1.put("age", 33);
        employee1.put("sex", 'F');
        employee1.put("married", false);
        employee1.put("children", JSONObject.NULL);
        employee1.put("salary", 90000);

        JSONArray employee1EmailsArray = new JSONArray();
        employee1EmailsArray.put("zoez@gmail.com");
        employee1EmailsArray.put("zoezebra@yahoo.com");

        employee1.put("email address", employee1EmailsArray);

        System.out.println(employee1.toString(1)); //indentFactor:1

        /*
        {
          "children": null,
           "sex": 70,
           "name": "Zoe Zebra",
           "email address": [
                             "zoez@gmail.com",
                             "zoezebra@yahoo.com"
                            ],
          "salary": 90000,
          "married": false,
          "age": 33
        }
         */
    }
}
```
2. Second approach: using **accumulate**.
```java
package org;
import org.json.JSONArray;
import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        JSONObject employee1 = new JSONObject();

        employee1.put("name", "Zoe Zebra");
        employee1.put("age", 33);
        employee1.put("sex", 'F');
        employee1.put("married", false);
        employee1.put("children", JSONObject.NULL);
        employee1.put("salary", 90000);

        //Using accumulate() method
        employee1.accumulate("email", "zoezebra@peppapig.com");
        employee1.accumulate("email", "zoez@gmail.com");
        employee1.accumulate("email", "zoez@yahoo.com");

        System.out.println(employee1.toString(1)); //indentFactor:1

        /*
        {
            "children": null,
                "sex": 70,
                "name": "Zoe Zebra",
                "salary": 90000,
                "married": false,
                "age": 33,
                "email": [
                          "zoezebra@peppapig.com",
                          "zoez@gmail.com",
                          "zoez@gmail.com"
                          ]
        }
         */

    }
}
```
3. Third approach: using **append**
The first using of append method immediately creates an array. 
```java
package org;
import org.json.JSONArray;
import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        JSONObject employee1 = new JSONObject();

        employee1.put("name", "Zoe Zebra");
        employee1.put("age", 33);
        employee1.put("sex", 'F');
        employee1.put("married", false);
        employee1.put("children", JSONObject.NULL);
        employee1.put("salary", 90000);

        //Using append() method
        employee1.append("email", "zoezebra@peppapig.com");
        employee1.append("email", "zoez@gmail.com");
        employee1.append("email", "zoez@yahoo.com");
        
        System.out.println(employee1.toString(1)); //indentFactor:1
        
        /*
        {
            "children": null,
                "sex": 70,
                "name": "Zoe Zebra",
                "salary": 90000,
                "married": false,
                "age": 33,
                "email": [
                          "zoezebra@peppapig.com",
                          "zoez@gmail.com",
                          "zoez@gmail.com"
                          ]
        }
         */

    }
}
```

**Tokenization:**
```java
package org;
import org.json.JSONArray;
import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import org.json.JSONTokener;

import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        JSONTokener stringToken = new JSONTokener("Suzzy Ship is Peppa Pig's best friend.");

        while(!stringToken.end()){
            System.out.print(stringToken.nextClean()); //SuzzyShipisPeppaPig'sbestfriend.
        }
        System.out.println();

        System.out.println(stringToken.skipTo('g'));

    }
}

```
**(D) Serializing Custom Classes in JSON** 
The org.json library is more powerful than the previous library when it comes to serializing custom objects. The custom \
objects to be serialized to JSON format by the library are represented in Java using beans.\
**Beans** are classes that encapsulate many objects into a single objects. Beans have certain specific characteristics:
  - Classes that are beans in Java are typically serializable.
  - Beans have zero argument constructor. They can have additional parametrized constructors but should also have zero constructor.
  - Beans also provide getters and setters methods. 
In order for the org.json library to serialize a custom class, the class must provide getters and setters. 
```java
package org;

import lombok.Getter;
import lombok.Setter;
import java.util.Random;

@Getter
@Setter
public class FigureBeans {
        public Random randomNumberGenerator = new Random();
        public String name;
        public String gender;
        public int age;
        public int figureID;

        //No argument constructor
        public FigureBeans(){}
    
        public FigureBeans(String name, String gender, int age) {
            this.figureID = Math.abs(randomNumberGenerator.nextInt());
            this.name = name;
            this.gender = gender;
            this.age = age;
        }

        @Override
        public String toString() {
            return "Employee{" +
                "randomNumberGenerator=" + randomNumberGenerator +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", employeeID=" + figureID +
                '}';
        }
}
```
- Serializing to JSON format
```java
package org;
import org.json.JSONArray;
import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.FileWriter;
import java.io.IOException;


public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        FigureBeans suzzy = new FigureBeans("Suzzy Sheep", "Female", 2);
        FigureBeans pedro = new FigureBeans("Pedro Ponny", "Male", 3);
        FigureBeans rebeca = new FigureBeans("Rebeca Rabbit", "Female", 2);

        //Serializing to JSON
        JSONObject figureJSON = new JSONObject(suzzy);
        System.out.println(figureJSON.toString(1));
        /*
        {
         "figureID": 840131623,
         "gender": "Female",
         "randomNumberGenerator": "java.util.Random@4dc63996",
         "name": "Suzzy Sheep",
         "age": 2
        }
         */

        try(FileWriter fileWriter = new FileWriter("figure.json")){
            figureJSON.write(fileWriter, 1, 0); //indentFactor:1, indent:0
        }

        JSONObject figure2JSON = new JSONObject(pedro);
        JSONObject figure3JSON = new JSONObject(rebeca);

        //Constructing a JSONArray
        JSONArray figureArray = new JSONArray();
        figureArray.put(figureJSON);
        figureArray.put(figure2JSON);
        figureArray.put(figure3JSON);

        System.out.println("-----");
        System.out.println(figureArray.toString(1));

        /*

        [
        {
            "figureID": 1590560375,
                "gender": "Female",
                "randomNumberGenerator": "java.util.Random@4dc63996",
                "name": "Suzzy Sheep",
                "age": 2
        },
        {
            "figureID": 1246546747,
                "gender": "Male",
                "randomNumberGenerator": "java.util.Random@d716361",
                "name": "Pedro Ponny",
                "age": 3
        },
        {
            "figureID": 758495029,
                "gender": "Female",
                "randomNumberGenerator": "java.util.Random@6ff3c5b5",
                "name": "Rebeca Rabbit",
                "age": 2
        }
        ]
         */
    }
}
```
**(E) Using @JSONPropertyName and JSONPropertyIgnore**
The annotation `@JSONPropertyName` allows us to specify a different name for the parameters of a class when the JSON representation\
of the object of the class is generated. The annotation is used on the **getter()** methods. Getters and setter methods\
has been added to the FiguresBeans class and the lombok annotations removed in order to demonstrate this.
The annotation `@JSONPropertyIgnore` is used to exclude a property from being serialized tp JSON format
```java
package org;

import org.json.JSONPropertyIgnore;
import org.json.JSONPropertyName;
import java.util.Random;

public class FigureBeans {
    
    public Random randomNumberGenerator = new Random();
    public String name;
    public String gender;
    public int age;

    public int figureID;
    public FigureBeans(){}
    public FigureBeans(String name, String gender, int age) {
        this.figureID = Math.abs(randomNumberGenerator.nextInt());
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    @JSONPropertyIgnore
    public Random getRandomNumberGenerator() {
        return randomNumberGenerator;
    }

    public void setRandomNumberGenerator(Random randomNumberGenerator) {
        this.randomNumberGenerator = randomNumberGenerator;
    }

    @JSONPropertyName("TV Name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @JSONPropertyName("Art ID")
    public int getFigureID() {
        return figureID;
    }

    public void setFigureID(int figureID) {
        this.figureID = figureID;
    }
    
    @Override
    public String toString() {
        return "Employee{" +
                "randomNumberGenerator=" + randomNumberGenerator +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", employeeID=" + figureID +
                '}';
    }
}
```
- Serializing
```java
package org;

import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        FigureBeans suzzy = new FigureBeans("Suzzy Sheep", "Female", 2);

        //Serializing to JSON
        JSONObject figureJSON = new JSONObject(suzzy);
        System.out.println(figureJSON.toString(1));

        /*
        {
            "gender": "Female",
            "TV Name": "Suzzy Sheep",
            "Art ID": 809846668,
            "age": 2
        }
         */
    }
}
```
**(F) Using the CDL Class to Parse Comma-delimited Values**
Converting a command separated values into JSON format. The library (org.json.CDL) that helps with this conversion.
```java
package org;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import org.json.JSONTokener;

import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        String nameString = "rebecca rabbit, zoe zebra, pedro pony, gerard giraffe, candy cat";
        //Converts to JSONArray format
        JSONArray namesJSONArray = CDL.rowToJSONArray(new JSONTokener(nameString));


        System.out.println(namesJSONArray.toString(1));
        /*
        [
         "rebecca rabbit",
         "zoe zebra",
         "pedro pony",
         "gerard giraffe",
         "candy cat"
         ]
         */

        JSONObject figureNames = new JSONObject();
        figureNames.put("names",namesJSONArray);
        System.out.println(figureNames.toString(1));

        /*
         {
         "names": [
                     "rebecca rabbit",
                     "zoe zebra",
                     "pedro pony",
                     "gerard giraffe",
                     "candy cat"
                    ]
        }

         */
    }
}
```
- Using both CDL.rowToJSONArray() and CDL.rowToJSONObject()
```java
package org;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject; //NOTE: we are still using JSONObject but from library org.json and NOT from json simple.
import org.json.JSONTokener;
import java.io.IOException;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        JSONArray fieldsArray = CDL.rowToJSONArray(
                new JSONTokener("ID,LoanStatus,LoanAmount,Term,CreditScore")
                );

        JSONObject loanJson = CDL.rowToJSONObject(
                fieldsArray,
                new JSONTokener("353537, FullPaid, ShortTerm, 716")
        );

        System.out.println(loanJson.toString(1));
        /*
        {
         "LoanAmount": "ShortTerm",
         "LoanStatus": "FullPaid",
         "ID": "353537",
         "Term": "716"  
        }
         */
    }
}
```

As long as the number of entries in the fieldsArray match the number of values specified, a JSON object will be constructed.
**(G) Parsing CSV Files and Converting to JSON Format**

The org.json library class  CDL.toJSONArray() is overloaded:
1. it can take 2 arguments: CDL.toJSONArray(headerArray, csv_data)
2. it can take 1 argument:(headerArray_csvData)...when the read-in or object contains both the header(first row) and the csv data
3. 
- loans.csv
```csv
353537, FullPaid, ShortTerm, 716, 4545633, Home Mortgage, 42748254,
98373, FullPaid, ShortTerm, 342, 36473, Rent, 25344,
383633, Charged off,Long Term 342, 632, Car Loan, 1234,
```
- loans_with_header.csv
```csv
ID,Loan Status,Loan Amount,Term,"ID,Loan Status,Loan Amount,Term,Home,Credit Balance
353537, FullPaid, ShortTerm, 716, 4545633, Home Mortgage, 42748254,
98373, FullPaid, ShortTerm, 342, 36473, Rent, 25344,
383633, Charged off,Long Term 342, 632, Car Loan, 1234,
```


1. Specifying the headers separately
```java
package org;

import org.json.CDL;
import org.json.JSONArray;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EntryPoint {

    public static void main(String[] args) throws  IOException{

        JSONArray headerArray = new JSONArray();
        headerArray.put("ID");
        headerArray.put("Loan Status");
        headerArray.put("Loan Amount");
        headerArray.put("Term");
        headerArray.put("Annual Income");
        headerArray.put("Home");
        headerArray.put("Credit Balance");


        String commaDelimitedLoans = new String(Files.readAllBytes(Paths.get("loans.csv")));

        JSONArray loansArray = CDL.toJSONArray(headerArray, commaDelimitedLoans);

        System.out.println(loansArray.toString(1));
        /*
        [
         {
          "Loan Status": "FullPaid",
          "Annual Income": "4545633",
          "ID": "353537",
          "Loan Amount": "ShortTerm",
          "Term": "716",
          "Credit Balance": "42748254",
          "Home": "Home Mortgage"
         },
         {
          "Loan Status": "FullPaid",
          "Annual Income": "36473",
          "ID": "98373",
          "Loan Amount": "ShortTerm",
          "Term": "342",
          "Credit Balance": "25344",
          "Home": "Rent"
         },
         {
          "Loan Status": "Charged off",
          "Annual Income": "632",
          "ID": "383633",
          "Loan Amount": "Long Term",
          "Term": "342",
          "Credit Balance": "1234",
          "Home": "Car Loan"
         }
        ]
       */

    }
}
```
2. File with headers (header is part of the csv file)
```java
package org;

import org.json.CDL;
import org.json.JSONArray;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EntryPoint {

    public static void main(String[] args) throws  IOException, NullPointerException{

        String commaDelimitedLoans = new String(Files.readAllBytes(Paths.get("loans_with_header.csv")));
        
        //One argument version of the CDL.toJSONArray
        JSONArray loansArray = CDL.toJSONArray(commaDelimitedLoans);

        System.out.println(loansArray.toString(1));
        /*
        [
         {
          "Loan Status": "FullPaid",
          "Annual Income": "4545633",
          "ID": "353537",
          "Loan Amount": "ShortTerm",
          "Term": "716",
          "Credit Balance": "42748254",
          "Home": "Home Mortgage"
         },
         {
          "Loan Status": "FullPaid",
          "Annual Income": "36473",
          "ID": "98373",
          "Loan Amount": "ShortTerm",
          "Term": "342",
          "Credit Balance": "25344",
          "Home": "Rent"
         },
         {
          "Loan Status": "Charged off",
          "Annual Income": "632",
          "ID": "383633",
          "Loan Amount": "Long Term",
          "Term": "342",
          "Credit Balance": "1234",
          "Home": "Car Loan"
         }
        ]
       */

    }
}
```
## 4: HTTP Requests in Java: Sending Simple HTTP Requests
A **REST Client** is an application that interacts with a remote server that has implemented REST APIs. An HTTP client\
can be build using a native Java library e.g. `java.net.http`.

```java
package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SimpleHTTPURLConnection {

    public static void main(String [] args) throws MalformedURLException {

        BufferedReader read;

        String text;

        StringBuffer content = new StringBuffer();

        FileWriter  write;

        try{

            //URL object. Using a local running Tomcat just to avoid network issues related to work firewall
            URL url = new URL("http://localhost:7777/");

            //Opening of the connection to the URL
            HttpURLConnection  conn = (HttpURLConnection) url.openConnection();

            //Setting user agent
            conn.setRequestProperty("User-Agent", "Mozilla");

            //Set the method type
            conn.setRequestMethod("GET");

            //Getting the status code
            int statusCode = conn.getResponseCode();

            System.out.println("Status Code:"+statusCode); //Status Code:200

            if(statusCode >= 200 && statusCode <=299){

                //A BufferedReader that takes InputStreamReader as argument...Input stream from the opened connection,
                read = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                write = new FileWriter("html.txt");

                //While the BufferedReader defined above (read) has more line assign each line to String variable(text)
                // and append the line to the StringBuffer(content)
                while((text = read.readLine()) != null){
                    content.append(text);
                    write.append(text);

                }

                //Close the BufferedReader
                read.close();

                //Print the StringBuffer
                System.out.println(content);
            }else{System.out.println("Something went wrong. Response Message is: "+conn.getResponseMessage());}

            //Disconnect from the network
            conn.disconnect();

        } catch (IOException murlx) {
            murlx.printStackTrace();
        }
    }
}
```
**Exploring the Response to a GET Request**
The headers in the incoming response or request typically contains metadata about the data within that message. The headers\
can also contain some other information about the URL endpoint. The methods `getHeaderFields()` and `getHeaderField()` \
comes handy to access the entire header fields and a specific header field.
```java
package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class SimpleHTTPURLConnection {

    public static void main(String[] args) throws MalformedURLException {

        BufferedReader read;
        String text;
        StringBuffer content = new StringBuffer();
        FileWriter write;

        try {

            //URL object. Using a local running Tomcat just to avoid network issues related to work firewall
            URL url = new URL("http://localhost:7777/");

            //Opening of the connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Setting user agent
            conn.setRequestProperty("User-Agent", "Mozilla");

            //Set the method type
            conn.setRequestMethod("GET");

            System.out.println("Status Code: "+conn.getResponseCode()); //Status Code: 200
            System.out.println("Returned Headers: "+conn.getHeaderFields());
            /*
            Returned Headers: {Keep-Alive=[timeout=20], Transfer-Encoding=[chunked], null=[HTTP/1.1 200], Connection=[keep-alive],
            Date=[Mon, 05 Sep 2022 07:35:03 GMT], Content-Type=[text/html;charset=UTF-8]}
             */

            System.out.println("Content Size: "+conn.getHeaderField("Content-Length"));
            System.out.println("Content Type: "+conn.getHeaderField("Content-Type"));
            //Content Type: text/html;charset=UTF-8
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```
**(B) Processing JSON Data in a Response**
- Examining the response for a JSON document type
```java
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        try {

            //URL object. Using a local running Tomcat just to avoid network issues related to work firewall
            URL url = new URL("https://reqres.in/api/users");

            //Opening of the connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Setting user agent
            conn.setRequestProperty("User-Agent", "Mozilla");

            //Set the method type
            conn.setRequestMethod("GET");

            System.out.println("Status Code: "+conn.getResponseCode()); 
            System.out.println("Returned Headers: "+conn.getHeaderFields());
            System.out.println("Content Size: "+conn.getHeaderField("Content-Length"));
            System.out.println("Content Type: "+conn.getHeaderField("Content-Type"));
            //Content Type: text/html;charset=UTF-8
        } catch (ProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```
```
Status Code: 200

Returned Headers: {null=[HTTP/1.1 200 OK], CF-RAY=[745d5f44ff9c291f-ORD], Server=[cloudflare], Access-Control-Allow-Origin=[*], 
Connection=[keep-alive], Date=[Mon, 05 Sep 2022 07:50:06 GMT], Via=[1.1 vegur], Accept-Ranges=[bytes], CF-Cache-Status=[HIT], 
NEL=[{"success_fraction":0,"report_to":"cf-nel","max_age":604800}], Cache-Control=[max-age=14400], 
Etag=[W/"3e4-2RLXvr5wTg9YQ6aH95CkYoFNuO8"], 

Content Size: 996

Content Type: application/json; charset=utf-8
```
- Getting the JSON response
```java
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class Main {

    public static void main(String[] args) throws MalformedURLException {
        BufferedReader read;
        String text;
        StringBuffer content = new StringBuffer();
        FileWriter write;

        try {

            //URL object. Using a local running Tomcat just to avoid network issues related to work firewall
            URL url = new URL("https://reqres.in/api/users?page=2");

            //Opening of the connection to the URL
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Setting user agent
            conn.setRequestProperty("User-Agent", "Mozilla");


            //Set the method type
            conn.setRequestMethod("GET");
            
            int statusCode = conn.getResponseCode();

            if (statusCode >= 200 && statusCode <= 299) {

                //A BufferedReader that takes InputStreamReader as argument...Input stream from the opened connection,
                read = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                

                //While the BufferedReader defined above (read) has more line assign each line to String variable(text)
                // and append the line to the StringBuffer(content)
                while ((text = read.readLine()) != null) {
                    content.append(text);

                }

               System.out.println(content);
        }
        } catch(ProtocolException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    
    }
}
```
```
{"page":1,"per_page":6,"total":12,"total_pages":2,"data":[{"id":1,"email":"george.bluth@reqres.in","first_name":"George",
"last_name":"Bluth","avatar":"https://reqres.in/img/faces/1-image.jpg"},{"id":2,"email":"janet.weaver@reqres.in",
"first_name":"Janet","last_name":"Weaver","avatar":"https://reqres.in/img/faces/2-image.jpg"},{"id":3,"email":"emma.wong@reqres.in",
"first_name":"Emma","last_name":"Wong","avatar":"https://reqres.in/img/faces/3-image.jpg"},{"id":4,"email":"eve.holt@reqres.in",
"first_name":"Eve","last_name":"Holt","avatar":"https://reqres.in/img/faces/4-image.jpg"},{"id":5,"email":"charles.morris@reqres.in",
"first_name":"Charles","last_name":"Morris","avatar":"https://reqres.in/img/faces/5-image.jpg"},{"id":6,"email":"tracey.ramos@reqres.in",
"first_name":"Tracey","last_name":"Ramos","avatar":"https://reqres.in/img/faces/6-image.jpg"}],
"support":{"url":"https://reqres.in/#support-heading","text":"To keep ReqRes free, contributions towards server costs are appreciated!"}}
```
- Getting and formatting the JSON response
```java
package org.example;

import java.io.*;
import java.net.*;

import org.json.JSONObject;

public class SimpleHTTPURLConnection {

    public static void main(String[] args) throws MalformedURLException {

        BufferedReader read;
        String text;
        StringBuffer content = new StringBuffer();
        FileWriter write;

        try {

            //URL object. Using a local running Tomcat just to avoid network issues related to work firewall
            URL url = new URL("https://reqres.in/api/users?page=2");

            System.setProperty("java.net.useSystemProxies", "true");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Setting user agent
            conn.setRequestProperty("User-Agent", "Mozilla");

            //Set the method type
            conn.setRequestMethod("GET");

            int statusCode = conn.getResponseCode();

            if (statusCode >= 200 && statusCode <= 299) {

                //A BufferedReader that takes InputStreamReader as argument...Input stream from the opened connection,
                read = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                //While the BufferedReader defined above (read) has more line assign each line to String variable(text)
                // and append the line to the StringBuffer(content)
                while ((text = read.readLine()) != null) {
                    content.append(text);
                }

               read.close();
               String responseText = content.toString();

               //Check the return type is json and parse it to JSONObject
               if(conn.getHeaderField("Content_Type").contains("json")){
                   JSONObject jsonObject = new JSONObject(responseText);
                   System.out.println("Formatted JSON: \n"+jsonObject.toString(1));
               }
               else{
                   System.out.println(responseText);
               }
        }
        } catch(ProtocolException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
```
**Sending Parameters in an HTTP Request**
The symbol **?** denotes the start of parameters with the scheme: **parameterName1=parameterValue1&parameterName2=parameterValue2**
For example: https://reqres.in/api/users?page=2&delay=5

**(C) HttpURLConnection and POST Request**
POST Requests are used to submit a request in order to store data or create a new resource on the remote server.
The 201 status code indicates that a request was successful and as a result, a resource has been created
```java
package org.example;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;

public class SimpleHTTPURLConnection {

    public static void main(String[] args) throws MalformedURLException {

        //BufferedReader read;
        String text;
        StringBuffer content = new StringBuffer();
        FileWriter write;

        try {

            URL url = new URL("https://reqres.in/api/users");

            System.setProperty("java.net.useSystemProxies", "true");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Setting user agent
            conn.setRequestProperty("User-Agent", "Mozilla");

            //Set the method type
            conn.setRequestMethod("POST");

            conn.setDoOutput(true);

            String postData = "{'email':'peppapig@peppa.com',"+
                    "'firstName':'Peppa', 'lastName':'Pig'}";

            //try with resources to write bye data into the output stream of the connection(conn) to the server
            //conn.getOutputStream()
            try(OutputStream outputStream = conn.getOutputStream()){
                byte[] postBytes = postData.getBytes("utf-8");
                System.out.println("----------Bytes------------");
                System.out.println(postBytes); //[B@38364841
                System.out.println("----------Bytes------------");
                //writing to the output stream of the connection in bytes (postBytes)
                outputStream.write(postBytes, 0, postBytes.length);
            }

            System.out.println("Response Code: "+conn.getResponseCode()); //201
            System.out.println("Response message: "+conn.getResponseMessage()); //Created


            //try with resources with conn.getInputStream()
            try(BufferedReader read = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8")
            )){
                StringBuilder responseText = new StringBuilder();

                //while the read(BufferedReader) has more line
                while((text = read.readLine())!=null){
                    responseText.append(text.trim());
                }
                System.out.println(responseText.toString());

                /*
                {"{'email':'peppapig@peppa.com','firstName':'Peppa', 'lastName':'Pig'}"
                :"","id":"217","createdAt":"2022-09-05T13:51:10.475Z"}
                 */
            }
        } catch(ProtocolException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
```

**(D) PUT and DELETE Requests**
- While POST request is to create a new resources/record, PUT request is used to update an existing resource/record.
```java
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;


public class Main {

    public static void main(String[] args) throws MalformedURLException {

        //BufferedReader read;
        String text;
        StringBuffer content = new StringBuffer();
        FileWriter write;

        try {

            URL url = new URL("https://reqres.in/api/users/217");
        
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Set the method type..PUT request is used to update an existing resource/record
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            
            //The email field has been changed. It's possible to update more than one field.
            String postData = "{'email':'peppapig22222@peppa.com',"+
                    "'firstName':'Peppa', 'lastName':'Pig'}";

            //try with resources to write bye data into the output stream of the connection(conn) to the server
            //conn.getOutputStream()
            try(OutputStream outputStream = conn.getOutputStream()){
                byte[] postBytes = postData.getBytes("utf-8");
                System.out.println("----------Bytes------------");
                System.out.println(postBytes);//[B@6c3f5566
                System.out.println("----------Bytes------------");
                //writing to the output stream of the connection in bytes (postBytes)
                outputStream.write(postBytes, 0, postBytes.length);
            }
            System.out.println();
            System.out.println("Response Code: "+conn.getResponseCode()); //200
            System.out.println("Response message: "+conn.getResponseMessage()); //OK
          
            //try with resources with conn.getInputStream()
            try(BufferedReader read = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8")
            )){
                StringBuilder responseText = new StringBuilder();

                //while the read(BufferedReader) has more line
                while((text = read.readLine())!=null){
                    responseText.append(text.trim());
                }
                System.out.println("-------Response Text----");
                System.out.println(responseText);//{"updatedAt":"2022-09-05T14:08:09.930Z"}
            }
            
        } catch(ProtocolException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
```
- The DELETE Request 
```java
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;


public class Main {

    public static void main(String[] args) throws MalformedURLException {;
        String text;
        StringBuffer content = new StringBuffer();
        FileWriter write;

        try {
            URL url = new URL("https://reqres.in/api/users/217");
        
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Set the method type..PUT request is used to update an existing resource/record
            conn.setRequestMethod("DELETE");

            System.out.println();
            System.out.println("Response Code: "+conn.getResponseCode()); //204
            System.out.println("Response message: "+conn.getResponseMessage()); //No Content

            //try with resources with conn.getInputStream()
            try(BufferedReader read = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8")
            )){
                StringBuilder responseText = new StringBuilder();

                //while the read(BufferedReader) has more line
                while((text = read.readLine())!=null){
                    responseText.append(text.trim());
                }
                
                System.out.println("-------Response Text----");
                System.out.println(responseText);//
            }
            
        } catch(ProtocolException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
```
**(E) The HEAD Request**
The HEAD request is useful for example to find out what kind of resources are available at a particular URL endpoint before\
deciding or even proceeding to submit a GET request.
```java
import java.io.*;
import java.net.*;
public class Main {

    public static void main(String[] args) throws MalformedURLException {

        BufferedReader read;
        String text;
        StringBuffer content = new StringBuffer();

        try {

            URL url = new URL("https://reqres.in/api/users?delay=5");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Set the method type
            conn.setRequestMethod("HEAD");


            int statusCode = conn.getResponseCode();
            
            System.out.println(statusCode);
            
            //Accessing the information that comes with HEAD request method
            System.out.println("Headers: \n"+conn.getHeaderFields().toString());
            /*
             {null=[HTTP/1.1 200 OK], CF-RAY=[7460608ae81c2c24-ORD], Server=[cloudflare], Access-Control-Allow-Origin=[*],
              Connection=[keep-alive], Date=[Mon, 05 Sep 2022 16:35:21 GMT], Via=[1.1 vegur], CF-Cache-Status=[DYNAMIC], 
              NEL=[{"success_fraction":0,"report_to":"cf-nel","max_age":604800}], Etag=[W/"3e4-2RLXvr5wTg9YQ6aH95CkYoFNuO8"], 
              Report-To=[{"endpoints":[{"url":"https:\/\/a.nel.cloudflare.com\/report\/v3?s=euky4ERcJzrYK0BsbxUxMqM8zmpQcE
              VEYCrhZsa%2BHkaB5r%2BSKW1qI%2FgKiQJA6kBo4BjuJM%2BY92WlvWoVYq7vAho7cdgV5BMQnEx5tIMMzKeWQX5trUwAfY8k9g%3D%3D"}],
              "group":"cf-nel","max_age":604800}], Content-Length=[996], X-Powered-By=[Express], 
              Content-Type=[application/json; charset=utf-8]}       
             */
            
        } catch(ProtocolException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
```
**(E) Setting Timeouts with HttpURLConnection**
To determine the waiting duration for a response after a request has been sent, timeout can be associated with the request.\
When the time for the response exceeds the set timeout time, a `java.net.SocketTimeoutException` exception is thrown.
```java
import java.io.*;
import java.net.*;
public class Main {

    public static void main(String[] args) throws MalformedURLException {

        BufferedReader read;
        String text;
        StringBuffer content = new StringBuffer();

        try {
            
            //NOTE that parameter delay=5(second) is set in order to simulate the exception.
            URL url = new URL("https://reqres.in/api/users?delay=5");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //Set the method type
            conn.setRequestMethod("HEAD");
            
            //the connection will wait for 2000 millisecond or 2seconds
            conn.setReadTimeout(2000); 


            int statusCode = conn.getResponseCode();
            
            System.out.println(statusCode);
            
            //Accessing the information that comes with HEAD request method
            System.out.println("Headers: \n"+conn.getHeaderFields().toString());
            
        } catch(ProtocolException e){
            throw new RuntimeException(e);
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }
}
```

## 5: HTTP Requests in Java: HTTP Requests with Java's HttpClient
- The previous course made use of the rather simple HttpURLConnection class. However, it's not capable for features such as\
asynchronous requests. HTTPClient is more advanced Java native library. Hence, we don't have to add it as dependency to the project\
pom file.

## 6: Java Database Connectivity (JDBC): An Introduction to JDBC
- Java Database Connectivity is the most fundamental API to connect a Java application to a database. Hence, it allows read,\
write, filter, join and other operations within the Java app.
- MySQL Server and MySQL Workbench, MySQL Command Line Client, has been installed for use throughout the course(part 1-4)
- MySQL is one of the most popular Open Source SQL **database management system**.
- A **database** is a structured collection of data. It may be anything from a simple shopping list to a picture gallery or\
the vast amounts of information in a corporate network. To add, access, and process data stored in a computer database, \
you need a database management system such as MySQL Server.
- A **relational database** stores data in separate tables rather than putting all the data in one big storeroom. The database\
structures are organized into physical files optimized for speed. The logical model, with objects such as databases, tables,\
views, rows, and columns, offers a flexible programming environment. You set up rules governing the relationships between\
different data fields, such as one-to-one, one-to-many, unique, required or optional, and “pointers” between different tables.\
The database enforces these rules, so that with a well-designed database, your application never sees inconsistent, duplicate,\
orphan, out-of-date, or missing data.
- The **SQL** part of “MySQL” stands for “Structured Query Language”. SQL is the most common standardized language used to access\
databases. Depending on your programming environment, you might enter SQL directly (for example, to generate reports), \
embed SQL statements into code written in another language, or use a language-specific API that hides the SQL syntax.
- MySQL database will be used in the entirety of the course.

- Navigate to the server and check the installed version: 
  - `cd Program Files\MySQL\MySQL Server 8.0\bin`
  - `.\mysql --version`
- Add the dependency to the pom.xml file of the project. The `mysql-connector-java` includes a **driver** which allows the\
Java application to connect to a MySQL database server instance. Hence, the implementation of JDBC is included
  - ```xml
    <dependencies>
     <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.30</version>
     </dependency>
    </dependencies>
    ```
**(A) Connecting to a Database Using JDBC**
- This sections deals with using **DriverManager** to set up a connection to an instance of MySQL Server/Database instance. It's\
an intro to connecting to server/database. Another method to connecting to server/database is the **DataSource**(will be covered in section B)
```java
package org.example;

//java.sql.Connection: Represents a specific connection with a database. Any SQL statements which we execute against a
    //database will happen through the Connection object
import java.sql.Connection;

//To set up a connection we need to make use of a registered driver to the database. The java.sql.DriverManager class serves this
    //purpose.
import java.sql.DriverManager;

//A whole host of exception related to connecting to sql server database instance
import java.sql.SQLException;


public class EntryPoint {

    public static void main( String[] args ) {

        //Connection URL pointing to the database server instance. By default, the mysql server runs on port 3305
        //Analysis of the Connection URL
        //jdbc(will be used to setup the connection):mysql(sub-protocol to be used to set up the connection)//url:port
        String url = "jdbc:mysql://localhost:3306";

        //Root credential set up during installation. In normal circumstances, application credential should be used instead of root credential
        String user = "root";
        String password = "Oloyin@@68";
        Connection conn = null;

        try{

            //Loading of the mysql driver class using the FQN of our JDBC driver class for MySQL.
            //The statement loads the JDBC driver for MySQL and register it with our application. This makes the registered
                //JDBC driver available for any class that wants to use it, e.g. the DriverManager.
            Class.forName("com.mysql.cj.jdbc.Driver");

            //The Connection object is used to interact with the database in various ways. 
            //The Connection object is obtained using the DriverManager. In section B it will be obtained using MysqlDatasource
            conn = DriverManager.getConnection(url, user, password);

            //Checking the connection to an instance of MySQL Server
            if(conn!=null){
                System.out.println("Connection has been established.");//Connection has been established.
            }
        }catch (SQLException | ClassNotFoundException  sqlcnf) {
            System.out.println("Connection Error");
            sqlcnf.printStackTrace();
        }

    }
}
```
**(B) Applying a DataSource Instance for Connections**
The overall functionality of the DriverManager is somewhat limited. Lack of connection pooling and the explicit definition\
of credentials are some drawbacks. DataSource helps to overcome these limitations. A JDBC DataSource is an interface which\
includes all the functionality of a DriverManager with support for pooling and distributed transaction.
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class EntryPoint {

    public static void main( String[] args ) {

        MysqlDataSource mysqlDS = null;
        
        try{

            //Instantiating an instance of My
            mysqlDS = new MysqlDataSource();

            mysqlDS.setURL("jdbc:mysql://localhost:3306");
            mysqlDS.setUser( "root");
            mysqlDS.setPassword("Oloyin@@68");

            Class.forName("com.mysql.cj.jdbc.Driver");

            //Connection object using the MysqlDataSource
            Connection conn = mysqlDS.getConnection();
            
            if(conn!=null){
                System.out.println("Connection has been established.");
            }
            
        }catch (SQLException | ClassNotFoundException  sqlcnf) {
            System.out.println("Connection Error");
            sqlcnf.printStackTrace();
        }

    }
}
```
- It is best to place any code related to setting up a database connection in a separate class.
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DBUtils {

    private static final String dbURL = "jdbc:mysql://localhost:3306";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";

    public static Connection getMysqlConnection(String schemaName){

        //Note that a database schema has not been created. Will be done in subsequent section

        MysqlDataSource mysqlDS = null;
        Connection conn = null;

        try{
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(dbURL + schemaName);
            mysqlDS.setUser(username);
            mysqlDS.setPassword(password);

            conn = mysqlDS.getConnection();

        }catch(SQLException e){
          e.printStackTrace();
        }
        return conn;
    }
}
```
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;

public class EntryPoint {
    public static void main( String[] args ) {
        //Making use of the custom DBUtils class
        // Note. The getMysqlConnection() method in DBUtils is a static method. Hence, we don't need to instantiate
            // an object DBUtils before using the method,
        //try with resources.
        try(Connection conn = DBUtils.getMysqlConnection(" ")){
            if(conn!=null){
                System.out.println("Connection has been established.");
            }else{System.out.println("Connection Error");}
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }
}
```
**(C) Running Queries with a Statement Object**
- A schema is created and a table is created within the schema. The table is loaded with data and accessed within the Java application.\
In essence a database can consist of several schema. A schema can consist of several tables.
- The **database client** to be used is the MySQL Workbench. The SQL query below was used to create the scheme and table:
```sql
-- create a new database schema
create database DeliveryService;

-- use the database
use DeliveryService;

-- show tables within the database schema. It show no table since we dont have a table yet.
show tables;

-- create a new table 
-- the table consiste of data about individuals(workers) who will make the delivery for fictious company
create table if not exists delpartners(
id int auto_increment primary key, -- this is a primary keyfor the worker(partner)
first_name varchar(100),
last_name varchar(100),
hourly_rate double,
is_fulltime boolean
);
```
- Populating and accessing the table
```sql
-- insert into the table delpartners in the schema deliveryservice
insert into delpartners values(101, 'Rebecca', 'Rabbit', 18.5, true);
insert into delpartners values(102, 'Zoe', 'Zebra', 44.7, false);
insert into delpartners values(103, 'Emily', 'Elephant', 31.2, true);

-- accessing the content  of the table
select * from delpartners; -- select all from the table

-- crosschecking the autoincrement of the id field
-- An explicit specification is required if the data being supplied doesnt match the column of the table.
insert into delpartners (first_name, last_name, hourly_rate, is_fulltime )values('Pedro', 'Pony', 31.2, true);

select * from delpartners;
```
<img src="\images\workbench.png">

- A Statement object
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EntryPoint {

    public static void main( String[] args ) {

        //Making use of the custom DBUtils class
        // Note. The getMysqlConnection() method in DBUtils is a static method. Hence, we don't need to instantiate
            // an object DBUtils before using the method,
        //try with resources.
        try(Connection conn = DBUtils.getMysqlConnection("/deliveryservice")){

        //An instance of the Statement of the object
        Statement stmt = conn.createStatement();
        String query = "select first_name, last_name, hourly_rate, is_fulltime " + "from delpartners";

        stmt.executeQuery(query);
        System.out.println("Query was successful."); //Query was successful.

        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }
}
```
- **Processing** Query Results with a ResultSet:
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

public class EntryPoint {

    public static void main( String[] args ) {

        int id;
        String firstName = null;
        String lastName;
        try(Connection conn = DBUtils.getMysqlConnection("/deliveryservice")) {

            //An instance of the Statement of the object
            Statement stmt = conn.createStatement();
            String query = "select first_name, last_name, hourly_rate, is_fulltime " + "from delpartners";


            //This captures what is returned after the statement executes successfully.
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(
                        rs.getString("first_name")
                                + "\t" + rs.getString("last_name")
                                + "\t" + rs.getDouble("hourly_rate")
                                + "\t" + rs.getBoolean("is_fulltime")
                        );
            }

        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }
}
/*
Rebecca	Rabbit	18.5	true
Zoe	Zebra	44.7	false
Emily	Elephant	31.2	true
Pedro	Pony	31.2	true
 */
```
**(D) Parameterizing Queries with a PreparedStatement**
The `Statement` interface is deal for **static** SQL queries. The `PreparedStatement` interface is appropriate in instances\
where an applications which needs to execute similar queries several times only with a few of the parameters modified. \
The ability to parse argument into the SQL query through an object of PreparedStatement turns a static query into a\
parameterized or **dynamic** query.
- DBUtils: A custom class with a single method that returns a Connection object
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

public class DBUtils {

    private static final String dbURL = "jdbc:mysql://localhost:3306";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";

    public static Connection getMysqlConnection(String schemaName){

        //Note that a database schema has not been created. Will be done in subsequent section

        MysqlDataSource mysqlDS = null;
        Connection conn = null;

        try{
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(dbURL + schemaName);
            mysqlDS.setUser(username);
            mysqlDS.setPassword(password);

            conn = mysqlDS.getConnection();

        }catch(SQLException e){
          e.printStackTrace();
        }
        return conn;
    }
}
```
- DeliveryPartnerQueries: A custom class with two methods each returning a ResultSet Object(first method uses Statement,
Second method uses PreparedStatement).
```java
package org.example;


import java.sql.*;

/**
 * This class represents different types of SQL queries which can be executed against the SQL database table.
 *
 * @UsedDataBaseTable: delpartners
 * @Author: Shola
 */
public class DeliveryPartnerQueries {

    public ResultSet getAllDelPartners(Connection conn) {
        try {

            //The connection object is used to execute a statement
            Statement stmt = conn.createStatement();
            String query = "select first_name, last_name, hourly_rate, is_fulltime " + "from delpartners";

            //This captures what is returned after the statement executes successfully.
            return stmt.executeQuery(query);

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return null;
        }

    }

    public ResultSet getDelPartnerById(Connection conn, int id){
        try{

            //The '?' serves as a placeholder, which the PreparedStatement will substitute later on. Hence, this is a \
                //parametrized query
            String query = "select first_name, last_name, hourly_rate, is_fulltime " + "from delpartners where id = ?";

            //Use of PreparedStatement
            PreparedStatement ps = conn.prepareStatement(query);
            //we wish to populate the value of the 'id' in the query. 1: the 1st placeholder, id: substitute with id.
            ps.setInt(1, id);
            //
            return ps.executeQuery();

        }catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return null;
        }
    }
}
```
- Executable class (The consumer of the utility classes)
```java
package org.example;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class EntryPoint {

    public static void main( String[] args )  {

        //An instance of the custom class DeliveryPartnerQueries
        DeliveryPartnerQueries dpq = new DeliveryPartnerQueries();

        //try-with-resources
        try(
            //Using the custom class DBUtils to instantiate a connection object
             Connection conn = DBUtils.getMysqlConnection("/deliveryservice");
             ){

            //Using the methods within the object 'dpq' of the custom class
            //Static query
            ResultSet rs = dpq.getAllDelPartners(conn);

            System.out.println("-------Result of A Static Query-------");
            while (rs.next()) {
                System.out.println(
                        rs.getString("first_name")
                                + "\t" + rs.getString("last_name")
                                + "\t" + rs.getDouble("hourly_rate")
                                + "\t" + rs.getBoolean("is_fulltime")
                );
            }
            System.out.println("--------------------------------------");

            /*
            -------Result of A Static Query-------
            Rebecca	Rabbit	18.5	true
            Zoe	Zebra	44.7	false
            Emily	Elephant	31.2	true
            Pedro	Pony	31.2	true
            --------------------------------------
             */
          
            //Dynamic Query
            rs = dpq.getDelPartnerById(conn, 103);

            System.out.println("-------Result of A Dynamic Query-------");
            while (rs.next()) {
                System.out.println(
                        rs.getString("first_name")
                                + "\t" + rs.getString("last_name")
                                + "\t" + rs.getDouble("hourly_rate")
                                + "\t" + rs.getBoolean("is_fulltime")
                );
            }
            System.out.println("--------------------------------------");

            /*
            -------Result of A Dynamic Query-------
            Emily	Elephant	31.2	true
            ----------------------------------------
             */
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
```
**(E) Executing Inserts with a PreparedStatement**
The Prepared queries can make use of multiple placeholders, multiple set methods in order to parse multiple parameters.

- DeliveryPartnerQueries utility class consisting of insert query
```java
package org.example;

import java.sql.*;

public class DeliveryPartnerQueries {
    
    public int addNewDelPartner(Connection conn, String fName, String lName, double hourlyRate, Boolean isFT){
        int status = 0;
        try {
            String query = "insert into delpartners "
                    + "(first_name, last_name, hourly_rate, is_fulltime) "
                    + "values (?, ?, ?, ?)";

            PreparedStatement ps = conn.prepareStatement(query);
            //Replacing the placeholders in the query with actual values from the arguments parsed to the method.
            ps.setString(1, fName);
            ps.setString(2, lName);
            ps.setDouble(3, hourlyRate);
            ps.setBoolean(4, isFT);

            //Note that we are using executeUpdate() method instead of executeQuery() method
            //The return value depends on the database being used. In MySQL, the number of rows affected by the operation
                //is returned
            status = ps.executeUpdate();

        }catch (SQLException sqlx){
            sqlx.printStackTrace();
        }
        return status;
    }
}
```

- EntryPoint: consumer class
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class EntryPoint {

    public static void main( String[] args )  {

        //An instance of the custom class DeliveryPartnerQueries
        DeliveryPartnerQueries dpq = new DeliveryPartnerQueries();

        //try-with-resources
        try(
            //Using the custom class DBUtils to instantiate a connection object
             Connection conn = DBUtils.getMysqlConnection("/deliveryservice");
             ){

            int st = dpq.addNewDelPartner(conn,"Gerrard", "Giraffe", 65D, true);

            //Using the methods within the object 'dpq' of the custom class
            //Static query
            ResultSet rs = dpq.getAllDelPartners(conn);

            System.out.println("-------Result of A Static Query-------");
            while (rs.next()) {
                System.out.println(
                        rs.getString("first_name")
                                + "\t" + rs.getString("last_name")
                                + "\t" + rs.getDouble("hourly_rate")
                                + "\t" + rs.getBoolean("is_fulltime")
                );
            }
            System.out.println("--------------------------------------");

            System.out.println(st); //1
            /*
           -------Result of A Static Query-------
            Rebecca	Rabbit	18.5	true
            Zoe	Zebra	44.7	false
            Emily	Elephant	31.2	true
            Pedro	Pony	31.2	true
            Suzzy	Ship	31.2	false
            Gerrard	Giraffe	65.0	true
            ---------------------------------------
             */
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
```
**(F) Updating and Deleting Queries Using JDBC**
* **Update**
- Utility class
```java
package org.example;


import java.sql.*;

/**
 * This class represents different types of SQL queries which can be executed against the SQL database table.
 *
 * @UsedDataBaseTable: delpartners
 * @Author: Shola
 */
public class DeliveryPartnerQueries {

    public ResultSet getAllDelPartners(Connection conn) {
        try {

            //The connection object is used to execute a statement
            Statement stmt = conn.createStatement();
            String query = "select first_name, last_name, hourly_rate, is_fulltime " + "from delpartners";

            //This captures what is returned after the statement executes successfully.
            return stmt.executeQuery(query);

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return null;
        }

    }
    
    
    public int applyPayAdjustment(Connection conn, double adjAmount, boolean isFT){
        int status = 0;

        try{
            //Adjust hourly_rate for the specified is_fulltime
            String query = "update delpartners "
                    + "set hourly_rate = hourly_rate + ?"
                    + "where is_fulltime = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, adjAmount);
            ps.setBoolean(2, isFT);

            status = ps.executeUpdate();
        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }
        return status;
    }

}
```
- Consuming class
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class EntryPoint {

    public static void main( String[] args )  {

        //An instance of the custom class DeliveryPartnerQueries
        DeliveryPartnerQueries dpq = new DeliveryPartnerQueries();

        //try-with-resources
        try(
            //Using the custom class DBUtils to instantiate a connection object
             Connection conn = DBUtils.getMysqlConnection("/deliveryservice");
             ){

            int st = dpq.applyPayAdjustment(conn, 450D, false);

            //Using the methods within the object 'dpq' of the custom class
            //Static query
            ResultSet rs = dpq.getAllDelPartners(conn);

            System.out.println("-------Result of A Static Query-------");
            while (rs.next()) {
                System.out.println(
                        rs.getString("first_name")
                                + "\t" + rs.getString("last_name")
                                + "\t" + rs.getDouble("hourly_rate")
                                + "\t" + rs.getBoolean("is_fulltime")
                );
            }
            System.out.println("--------------------------------------");

            System.out.println(st); //2
            /*
           -------Result of A Static Query-------
            Rebecca	Rabbit	18.5	true
            Zoe	Zebra	539.7	false
            Emily	Elephant	31.2	true
            Pedro	Pony	31.2	true
            Suzzy	Ship	526.2	false
            Gerrard	Giraffe	65.0	true
            Gerrard	Giraffe	65.0	true
            --------------------------------------
             */
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
```
* **Delete**
- Utility class
```java
package org.example;

public class DeliveryPartnerQueries {

    public ResultSet getAllDelPartners(Connection conn) {
        try {

            //The connection object is used to execute a statement
            Statement stmt = conn.createStatement();
            String query = "select first_name, last_name, hourly_rate, is_fulltime " + "from delpartners";

            //This captures what is returned after the statement executes successfully.
            return stmt.executeQuery(query);

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            return null;
        }

    }
    
    public int deleteDeliveryPartner(Connection conn, int id){
        int status =0;

        try{
            String query = "delete from delpartners "
                           + "where id = ?";

            PreparedStatement ps = conn.prepareStatement(query);
            ps.setDouble(1, id);
            status = ps.executeUpdate();
        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }

        return status;
    }
}
```
- Consumer class
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;

public class EntryPoint {

    public static void main( String[] args )  {

        //An instance of the custom class DeliveryPartnerQueries
        DeliveryPartnerQueries dpq = new DeliveryPartnerQueries();

        //try-with-resources
        try(
            //Using the custom class DBUtils to instantiate a connection object
             Connection conn = DBUtils.getMysqlConnection("/deliveryservice");
             ){

            int st = dpq.deleteDeliveryPartner(conn, 107);

            //Using the methods within the object 'dpq' of the custom class
            //Static query
            ResultSet rs = dpq.getAllDelPartners(conn);

            System.out.println("-------Result of A Static Query-------");
            while (rs.next()) {
                System.out.println(
                        rs.getString("first_name")
                                + "\t" + rs.getString("last_name")
                                + "\t" + rs.getDouble("hourly_rate")
                                + "\t" + rs.getBoolean("is_fulltime")
                );
            }
            System.out.println("--------------------------------------");

            System.out.println(st); //1
            /*
            --------Result of A Static Query-------
            Rebecca	Rabbit	18.5	true
            Zoe	Zebra	989.7	false
            Emily	Elephant	31.2	true
            Pedro	Pony	31.2	true
            Suzzy	Ship	976.2	false
            Gerrard	Giraffe	65.0	true
            --------------------------------------
             */
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
```



## 7: Java Database Connectivity (JDBC): Interacting with Databases using RowSets
* A RowSer doesn't just represent the results of a query execution, but includes methods to connect and run queries against\
a database as well.
* The **JdbcRowSet** retains a connection to the database throughout its life and case be used to read data from database, insert\
rows into table, as well as update and delete existing rows.
* The **CachedRowSet** operation disconnected from the database. It is similar to the **JdbcRowSet** in many regards but also differs\
due to their disconnected nature.

**(A) USing the Connected JdbcRowSet**
* The JdbcRowSet is an extension of the ResultSet interface. This means that it has all the capabilities of a ResultSet. There\
are many types of RowSet, and they can be classified as disconnected or connected, depending on whether they maintain continuous\
a connection to the database. The JdbcRowSet is similar to ResultSet because both maintains connection to the database\
even after it has been created and loaded with data.
* The `Connection` object for the `JdbRowSet` is embedded within the JdbcRowSet itself, and it's a `DataSource` Connection. 
The JdbcRowSet is obtained through `RowSetProvider`. Compare with `getMysqlConnection()` which needed to specify its Connection \
object and obtained it from `MysqlDataSource`, JdbcRowSet doesnt need to explicitly declare a connection object
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtils {

    private static final String dbURL = "jdbc:mysql://localhost:3306";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";

    public static Connection getMysqlConnection(String schemaName){

        //Note that a database schema has not been created. Will be done in subsequent section

        MysqlDataSource mysqlDS = null;
        Connection conn = null;

        try{
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(dbURL + schemaName);
            mysqlDS.setUser(username);
            mysqlDS.setPassword(password);

            conn = mysqlDS.getConnection();

        }catch(SQLException e){
          e.printStackTrace();
        }
        return conn;
    }

    public static  JdbcRowSet getJdbcRowSet(String schemaName){
        
        JdbcRowSet jdbcRS = null;

        try{
            //We use the RowSetProvider class(which has implemented the methods defined within JdbcRowSet) to object JdbcRowSet
            jdbcRS = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcRS.setUrl(dbURL + schemaName);
            jdbcRS.setUsername(username);
            jdbcRS.setPassword(password);

        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }
        return jdbcRS;
    }
}
```
- Another advantage of the `JdbcRowSet` is that a `Statement` or `PreparedStatement` is not needed to execute SQL commands.\
Also, we don't need to explicitly declare a `ResultSet` object in order to access the returned data from SQL command execution.\
Since ResultSet is already embedded in the JdbcRowSet we can directly access the data returned from a database.
```java
package org.example;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

public class EntryPoint {

  public static void main( String[] args ) {
    //Try with resource
    try (JdbcRowSet jdbcrRS = DBUtils.getJdbcRowSet("/deliveryservice")) {

      //Set the command
      jdbcrRS.setCommand("select *  from delpartners where id between ? and ?;");
      //Below also works
      //jdbcrRS.setCommand("select *  from delpartners where id between 102 and 105;");

      jdbcrRS.setInt(1, 102);
      jdbcrRS.setInt(2,105);

      //Running the command against the database
      jdbcrRS.execute();

      //NOTE: There is no need to declare a ResultSet object in order to access returned data from SQL command
      while (jdbcrRS.next()) {
        System.out.println(
                jdbcrRS.getString("first_name")
                        + "\t" + jdbcrRS.getString("last_name")
                        + "\t" + jdbcrRS.getDouble("hourly_rate")
                        + "\t" + jdbcrRS.getBoolean("is_fulltime")
        );
      }
            /*
            Zoe	Zebra	989.7	false
            Emily	Elephant	31.2	true
            Pedro	Pony	31.2	true
            Suzzy	Ship	976.2	false
             */

    } catch (SQLException sqlx) {
      sqlx.printStackTrace();
    }
  }
}
```
In essence, the JdbcRowSet encapsulate the following objects among others:
1. Connection object (DataSource Connection object)
2. Statement and Prepared State
3. ResultSet
4. 
**(B) Exploring the Properties of JdbcRowSet**
```java
package org.example;

import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

public class EntryPoint {

    public static void main( String[] args ) {
        //Try with resource
        try (JdbcRowSet jdbcrRS = DBUtils.getJdbcRowSet("/deliveryservice")) {

            //Set the command
            jdbcrRS.setCommand("select *  from delpartners");

            //Running the command against the database
            jdbcrRS.execute();

            //Some properties associated with the JdbcRowSert
            System.out.println("Read-only?: "+jdbcrRS.isReadOnly());//true
            System.out.println("Auto-commit?: "+jdbcrRS.getAutoCommit());//true
            System.out.println("Fetch direction: "+jdbcrRS.getFetchDirection()); //Fetch direction: 1000
            System.out.println("Code for FETCH_FORWARD: "+jdbcrRS.FETCH_FORWARD); //Code for FETCH_FORWARD: 1000
            System.out.println("Command: "+jdbcrRS.getCommand()); //Command: select *  from delpartners

            System.out.println("-------");
            System.out.println("Metadata: \n"+jdbcrRS.getMetaData());
            /*
            com.mysql.cj.jdbc.result.ResultSetMetaData@23f7d05d - Field level information:
	        com.mysql.cj.result.Field@1e730495[dbName=deliveryservice,tableName=delpartners,originalTableName=delpartners,columnName=id,originalColumnName=id,mysqlType=3(FIELD_TYPE_INT),sqlType=4,flags= AUTO_INCREMENT PRIMARY_KEY, charsetIndex=63, charsetName=ISO-8859-1]
	        com.mysql.cj.result.Field@7d3a22a9[dbName=deliveryservice,tableName=delpartners,originalTableName=delpartners,columnName=first_name,originalColumnName=first_name,mysqlType=253(FIELD_TYPE_VARCHAR),sqlType=12,flags=, charsetIndex=255, charsetName=UTF-8]
	        com.mysql.cj.result.Field@1d082e88[dbName=deliveryservice,tableName=delpartners,originalTableName=delpartners,columnName=last_name,originalColumnName=last_name,mysqlType=253(FIELD_TYPE_VARCHAR),sqlType=12,flags=, charsetIndex=255, charsetName=UTF-8]
	        com.mysql.cj.result.Field@60704c[dbName=deliveryservice,tableName=delpartners,originalTableName=delpartners,columnName=hourly_rate,originalColumnName=hourly_rate,mysqlType=5(FIELD_TYPE_DOUBLE),sqlType=8,flags=, charsetIndex=63, charsetName=ISO-8859-1]
	        com.mysql.cj.result.Field@6b19b79[dbName=deliveryservice,tableName=delpartners,originalTableName=delpartners,columnName=is_fulltime,originalColumnName=is_fulltime,mysqlType=1(FIELD_TYPE_BIT),sqlType=-7,flags=, charsetIndex=63, charsetName=ISO-8859-1]
             */

        } catch (SQLException sqlx) {
            sqlx.printStackTrace();
        }
    }
}
```

**(C) Navigating the Rows in a JdbcRowSet**
- The cursor is moved to certain row in the database table using methods of the JdbcRowSet such as relative(), previous(), e.t.c
```java
package org.example;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

public class EntryPoint {

    public static void displayRow(String label, RowSet rowSet)throws SQLException{
        String fName = rowSet.getString("first_name");
        String lName = rowSet.getString("last_name");
        double hourlyRate = rowSet.getDouble("hourly_rate");
        boolean isFT = rowSet.getBoolean("is_fulltime");

        String stdData = "\n%s: %s | %s | %.2f | %s \n";
        //The label is just a String to help inform where the current cursor is at. It is not a method, just a String
        System.out.format(stdData, label, fName, lName, hourlyRate, isFT);
    }

    public static void main( String[] args ) {
        //Try with resource
        try (JdbcRowSet jdbcrRS = DBUtils.getJdbcRowSet("/deliveryservice")) {

            //Set the command
            jdbcrRS.setCommand("select *  from delpartners");
            jdbcrRS.execute();

            //The current order of the database table for referemce
            /*
            Rebecca
            Zoe
            Emily
            Pedro
            Suzzy
            Gerrard
            Danny
            Madam
            MS
             */

            jdbcrRS.first(); //move cursor to the first row
            System.out.println(jdbcrRS.first()); //true --the cursor is currently at first element
            displayRow("The position is -->first()", jdbcrRS); //The position is -->first(): Rebecca | Rabbit | 18.50 | true

            jdbcrRS.relative(2); //move the cursor to second row from first row..move two rows down from first row
            displayRow("Position is --> relative(2)", jdbcrRS ); //Position is --> relative(2): Emily | Elephant | 31.20 | true

            jdbcrRS.previous();//"Previous position before relative"....move one row up from last location of cursor
            displayRow("Previous position before relative", jdbcrRS);//Previous position before relative: Zoe | Zebra | 989.70 | false

            jdbcrRS.absolute(4);
            displayRow("4 rows down from first row", jdbcrRS);//4 rows down from first row: Pedro | Pony | 31.20 | true

            jdbcrRS.last(); //move the curson to the last row
            displayRow("last row", jdbcrRS); //last row: Ms | Rabbit | 10000.40 | true

            jdbcrRS.relative(-1);//move one row up from last position of cursor (remember that position was last)
            displayRow("1 row up from last position", jdbcrRS); //1 row up from last position: Madam | Gazelle | 19.40 | true
          
        } catch (SQLException sqlx) {
            sqlx.printStackTrace();
        }
    }
}
```
**NOTE:** A JdbcRowSet is not dynamically updated. If a row was updated during the operations of the RowSet, the update\
will not be picked up. However, `JdbcRowSet.refreshRow()` method can be used to manually pickup updates.


**(D) Updating Table Data with a JdbcRowSet**
- Instead of sending an SQL command to the server and letting the server do the job. It is possible to do the task within\
the Java app and just send the update to the server.
- The update of the row is done within the Java program and the updated row is sent to the database instead of having\
the database server carryout the update operation. In essence, instead of using the SQL `update` query, we let the \
JdbcRowSet use its own built-in methods such as `updateDouble()` and `upDateRow()`. The JdbcRowSet will update the row\
and set the updated data to the database directly.
```java
package org.example;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

public class EntryPoint {

  public static void displayRow(String label, RowSet rowSet)throws SQLException{
    String fName = rowSet.getString("first_name");
    String lName = rowSet.getString("last_name");
    double hourlyRate = rowSet.getDouble("hourly_rate");
    boolean isFT = rowSet.getBoolean("is_fulltime");

    String stdData = "\n%s: %s | %s | %.2f | %s \n";
    //The label is just a String to help inform where the current cursor is at. It is not a method, just a String
    System.out.format(stdData, label, fName, lName, hourlyRate, isFT);
  }

  public static void main( String[] args ) {
    //Try with resource
    try (JdbcRowSet jdbcrRS = DBUtils.getJdbcRowSet("/deliveryservice")) {

      //Set the command
      jdbcrRS.setCommand("select *  from delpartners");
      jdbcrRS.execute();

      int updatedRows = 0;

      while(jdbcrRS.next()){
        //if the column "is_fulltime" is false
        if(!jdbcrRS.getBoolean("is_fulltime")){
          //update the rows that satisfy the condition above
          jdbcrRS.updateDouble("hourly_rate", 33);
          //send the update to the database
          jdbcrRS.updateRow();

          displayRow("Updated record: ", jdbcrRS);
          updatedRows++;
        }
      }

      //the line below is needed only if 'autocommit=false' is set within the database
//            jdbcrRS.commit();

      System.out.println("\nNumber of updated rows: "+updatedRows);

            /*
            Updated record: : Zoe | Zebra | 33.00 | false

            Updated record: : Suzzy | Ship | 33.00 | false

            Updated record: : Danny | Dog | 33.00 | false

            Number of updated rows: 3
             */

    }catch(SQLException sqlx){
      sqlx.printStackTrace();
    }
  }
}
```
**(E) Adding and Deleting Rows with a JdbcRowSet**
As seen in previous section, JdbcRowSet can be directly used without an SQL query to update rows within a database. This\
can also be done to add and delete rows.
- Adding a row using JdbcRowSet
```java
package org.example;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

public class EntryPoint {

    public static void displayRow(String label, RowSet rowSet)throws SQLException{
        String fName = rowSet.getString("first_name");
        String lName = rowSet.getString("last_name");
        double hourlyRate = rowSet.getDouble("hourly_rate");
        boolean isFT = rowSet.getBoolean("is_fulltime");

        String stdData = "\n%s: %s | %s | %.2f | %s \n";
        //The label is just a String to help inform where the current cursor is at. It is not a method, just a String
        System.out.format(stdData, label, fName, lName, hourlyRate, isFT);
    }

    public static void main( String[] args ) {
        //Try with resource
        try (JdbcRowSet jdbcrRS = DBUtils.getJdbcRowSet("/deliveryservice")) {

            //Set the command
            jdbcrRS.setCommand("select *  from delpartners where is_fulltime=false");
            jdbcrRS.execute();


            jdbcrRS.last(); //move the cursor to the last row
            int numpT = jdbcrRS.getRow(); //Get the number of rows that satisfies the selectCommand..row count of the last row.
            System.out.println("Number of part-time partners: "+numpT); //Number of part-time partners: 3

            //We only chose to add a new part-time worker if number of part-time worker is less than 5.
            if(numpT <5){
                //moving the RowSet cursor to InsertRow; it's a special row associated with updatable RowSet. Its where
                    // a new row can be added.
                jdbcrRS.moveToInsertRow();
                //Now that we are at the special row for inserting rows, we populate the row with update..() method
                jdbcrRS.updateString("first_name", "Daddy");
                jdbcrRS.updateString("last_name", "Pig");
                jdbcrRS.updateDouble("hourly_rate", 83);
                jdbcrRS.updateBoolean("is_fulltime", false);

                //Then we insert the special row that has just been updated into the database table
                jdbcrRS.insertRow();

                jdbcrRS.last(); //move the cursor to the last row
                displayRow("Added part-time partner: ", jdbcrRS); //Added part-time partner: : Daddy | Pig | 83.00 | false
            }

        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }
    }
}
```
- Deleting a row using JdbcRowSet
```java
package org.example;

import javax.sql.RowSet;
import javax.sql.rowset.JdbcRowSet;
import java.sql.SQLException;

public class EntryPoint {

    public static void displayRow(String label, RowSet rowSet)throws SQLException{
        String fName = rowSet.getString("first_name");
        String lName = rowSet.getString("last_name");
        double hourlyRate = rowSet.getDouble("hourly_rate");
        boolean isFT = rowSet.getBoolean("is_fulltime");

        String stdData = "\n%s: %s | %s | %.2f | %s \n";
        //The label is just a String to help inform where the current cursor is at. It is not a method, just a String
        System.out.format(stdData, label, fName, lName, hourlyRate, isFT);
    }

    public static void main( String[] args ) {
        //Try with resource
        try (JdbcRowSet jdbcrRS = DBUtils.getJdbcRowSet("/deliveryservice")) {

            //Set the command
            jdbcrRS.setCommand("select *  from delpartners");
            jdbcrRS.execute();

            int removedRows = 0;

            while(jdbcrRS.next()){
                //Subsetting the rows returned by the setCommand() above
                if(
                        jdbcrRS.getBoolean("is_fulltime")==true
                        && jdbcrRS.getDouble("hourly_rate") >= 1000
                    ){
                    //display the row that will be deleted
                    displayRow("Removing row: ", jdbcrRS);//Removing row: : Ms | Rabbit | 10000.40 | true
                    //finally delete the row that matches both the setCommand and the if-statement
                    jdbcrRS.deleteRow();
                    removedRows++;
                }
            }
            System.out.println("Number of deleted rows: "+ removedRows);//Number of deleted rows: 1 

        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }
    }
}
```
**(F) Using the Disconnected CachedRowSet**
The `javax.sql.rowset.CachedRowSet` is another type of RowSet in Java. However, unlike the JdbcRowSet, it doesn't maintain\
its connection to the database. It has several advantaged; we only need to connect to the database in order to first\
retrieve the data and then the RowSet is then disconnected from the database. Processing can be applied offline. Hence,\
the overhead of maintaining a database connection doesn't really exist.
- The DBUtils now including a CacheRowSet getter that is also obtained from RowSetProvider
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtils {

    private static final String dbURL = "jdbc:mysql://localhost:3306";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";
    
    public static Connection getMysqlConnection(String schemaName){
        
        MysqlDataSource mysqlDS = null;
        Connection conn = null;

        try{
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(dbURL + schemaName);
            mysqlDS.setUser(username);
            mysqlDS.setPassword(password);

            conn = mysqlDS.getConnection();

        }catch(SQLException e){
          e.printStackTrace();
        }
        return conn;
    }

    public static  JdbcRowSet getJdbcRowSet(String schemaName){

        JdbcRowSet jdbcRS = null;

        try{
            //We use the RowSetProvider class(which has implemented the methods defined within JdbcRowSet) to object JdbcRowSet
            jdbcRS = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcRS.setUrl(dbURL + schemaName);
            jdbcRS.setUsername(username);
            jdbcRS.setPassword(password);

        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }

        return jdbcRS;

    }

    public static CachedRowSet getCachedRowSet(String schemaName){
        CachedRowSet cachedRS = null;
        try{
            cachedRS = RowSetProvider.newFactory().createCachedRowSet();
            cachedRS.setUrl(dbURL + schemaName);
            cachedRS.setUsername(username);
            cachedRS.setPassword(password);
        } catch (SQLException sqlx) {
            sqlx.printStackTrace();
        }
        return cachedRS;
    }
}
```
- Used of CacheRowSet
```java
package org.example;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;

public class EntryPoint {

    public static void displayRow(String label, RowSet rowSet)throws SQLException{
        String fName = rowSet.getString("first_name");
        String lName = rowSet.getString("last_name");
        double hourlyRate = rowSet.getDouble("hourly_rate");
        boolean isFT = rowSet.getBoolean("is_fulltime");

        String stdData = "\n%s: %s | %s | %.2f | %s \n";
        //The label is just a String to help inform where the current cursor is at. It is not a method, just a String
        System.out.format(stdData, label, fName, lName, hourlyRate, isFT);
    }

    public static void main( String[] args ) {
        
        //Try with resource
        try (CachedRowSet cachedRS = DBUtils.getCachedRowSet("/deliveryservice")) {

            //Set the command
            cachedRS.setCommand("select *  from delpartners");
            cachedRS.execute();

            int rowNum = 1;

            //print first 3 rows
            while(cachedRS.next() && rowNum <=3) {
                displayRow("Row: " + rowNum, cachedRS);
                rowNum++;
            }
            /*
            Row: 1: Rebecca | Rabbit | 18.50 | true
            Row: 2: Zoe | Zebra | 33.00 | false
            Row: 3: Emily | Elephant | 31.20 | true
             */
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
```
_Note The Update, Inserting and Deleting Operations (Sections C,D, E) is somewhat similar(with some minor modifications)\
for the CacheRowSet. Hence, no need to revisit same for CachedRowSet.


## 8: Java Database Connectivity (JDBC): Joining & Filtering Data with RowSets
The `JoinedRowSet()` and `FilteredRowSet()` are a specialized implementation of the CachedRowSet. They enables offline\
join and filtering operations.
Multiple relational tables needs to be setup in order to demonstrate these operations.

**(A) Creating Tables for Join Operations**
Performing filtering and joining operations within the database helps to take the load off the database server.
- The pid field of delpartners table no longer has autoincrement feature.
- The delvehicles holds the data about types of delivery vehicles used by delivery partners (delpartners).
- The deliveries tables holds data about a delivery including the vehicle and partner that made the delivery. This table
has a foreign key reference to pid and vid fields of the delpartners and delvehicles.
- SQL commands for the three tables is shown below
```sql
-- create table
create table if not exists delpartners(
pid int primary key, -- this is a primary keyfor the worker(partner)
first_name varchar(100),
last_name varchar(100),
hourly_rate double,
is_fulltime boolean
);

insert into delpartners values(101, 'Rebecca', 'Rabbit', 18.5, true);
insert into delpartners values(102, 'Zoe', 'Zebra', 44.7, false);
insert into delpartners values(103, 'Emily', 'Elephant', 31.2, true);
insert into delpartners values (104,'Danny', 'Dog', 36.2, false);
insert into delpartners  values (105, 'Pedro', 'Pony', 53.2, true);
insert into delpartners values(106, 'Peppa', 'Pig', 71.2, true);
insert into delpartners values(107, 'Gerrard', 'Giraffe', 23.5, true);
insert into delpartners values(108, 'Candy', 'Cat', 94.2, true);

select * from delpartners; 

create table if not exists delvehicles(
vid int primary key, -- this is a primary keyfor the worker(partner)
color varchar(20),
vehicle_type varchar(20),
licence_plate varchar(10)
);

insert into delvehicles values(11, 'Red', 'Pickup','R11');
insert into delvehicles values(12, 'Orange', 'Van','O12');
insert into delvehicles values(13, 'Blue', 'Truck','B13');
insert into delvehicles values (14,'Purple', 'Truck','P14');
insert into delvehicles  values (15, 'Yellow', 'Sedan', 'Y15');

select * from delvehicles; 

create table if not exists deliveries(
did int primary key, -- deliver id
pid int, -- partner id
vid int, -- vehicle id,
destination varchar(20), -- destination
foreign key(pid) references delpartners(pid) on delete cascade, -- setting of a column as foreign key
foreign key(vid) references delvehicles(vid) on delete cascade); -- setting of a column as foreign key

insert into deliveries values(1, 102, 13, 'Prague');
insert into deliveries values(2, '104', 11,'London');
insert into deliveries values(3, '107', 12, 'Abuja');
insert into deliveries values (4,'103', 14,'Brno');
insert into deliveries  values (5, '101', 15, 'Bonn');

select * from deliveries
```

**(B) Joining Tables with a JDBC JoinRowSet**
* To join two or more tables, a common field between the two tables is usually needed. 
* The deliveries table contains the fields pid and vid which are the id fields from the delpartners and delvehicles respectively.\
These fields are also set as foreign keys. This means that 
* **Inner Join:** only rows from both tables that has matching value of the specified foreign key(e.g. vid, pid) will be\
part of the result (i.e. Only the rows from delpartners with same pid as in deliveries will be selected. 
* Inner join is the _ONLY_ join operation provided by `RowSetProvider`.\
`select p.first_name, p.is_fulltime, d.destination from delpartners p join deliveries d on p.pid=d.pid;` \

- Utility class
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtils {

    private static final String dbURL = "jdbc:mysql://localhost:3306";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";
    
    public static Connection getMysqlConnection(String schemaName){
        
        MysqlDataSource mysqlDS = null;
        Connection conn = null;
        try{
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(dbURL + schemaName);
            mysqlDS.setUser(username);
            mysqlDS.setPassword(password);

            conn = mysqlDS.getConnection();

        }catch(SQLException e){
          e.printStackTrace();
        }
        return conn;
    }

    public static  JdbcRowSet getJdbcRowSet(String schemaName){

        JdbcRowSet jdbcRS = null;

        try{
            //We use the RowSetProvider class(which has implemented the methods defined within JdbcRowSet) to object JdbcRowSet
            jdbcRS = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcRS.setUrl(dbURL + schemaName);
            jdbcRS.setUsername(username);
            jdbcRS.setPassword(password);

        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }

        return jdbcRS;

    }

    public static CachedRowSet getCachedRowSet(String schemaName){
        CachedRowSet cachedRS = null;
        try{
            cachedRS = RowSetProvider.newFactory().createCachedRowSet();
            cachedRS.setUrl(dbURL + schemaName);
            cachedRS.setUsername(username);
            cachedRS.setPassword(password);
        } catch (SQLException sqlx) {
            sqlx.printStackTrace();
        }
        return cachedRS;
    }
}
```
- The Consumer class
```java
package org.example;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.SQLException;

public class EntryPoint {

  public static void main( String[] args ) {

    //Try with resource
    try (Connection conn = DBUtils.getMysqlConnection("/deliveryservice")) {

      //We select all rows from each table and passed as CacheRowSet.

      //Select all rows from delpartners table.
      CachedRowSet partnersRS = DBUtils.getCachedRowSet("");
      partnersRS.setCommand("select * from delpartners");
      partnersRS.execute(conn); //execute using the Connection object defined in the try-with-resources
      //Select all rows from deliveries table.
      CachedRowSet deliveriesRS = DBUtils.getCachedRowSet("");
      deliveriesRS.setCommand("select * from deliveries");
      deliveriesRS.execute(conn); //execute using the Connection object defined in the try-with-resources

      //Join the two CachedRowSets

      //Create a JoinRowSet that will hold the data that will be joined.
      JoinRowSet joinRS = RowSetProvider.newFactory().createJoinRowSet();
      //Add each of the CachedRowSet to the JoinRowSet object.
      joinRS.addRowSet(partnersRS, "pid");
      joinRS.addRowSet(deliveriesRS, "pid");

      int rowNum=1;

      while(joinRS.next()){
        String fName = joinRS.getString("first_name");
        boolean isFT = joinRS.getBoolean("is_fulltime");
        String destination = joinRS.getString("destination");

        String stdData = "\nRow #%d: %s | %s | %s";
        System.out.format(stdData, rowNum, fName, isFT, destination);
        rowNum++;

      }
            /*
            Row #1: Gerrard | true | Abuja
            Row #2: Danny | false | London
            Row #3: Emily | true | Brno
            Row #4: Zoe | false | Prague
            Row #5: Rebecca | true | Bonn
             */
      //closing the connections.
      joinRS.close();
      partnersRS.close();
      deliveriesRS.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

  }
}
```
**(C) Exploring Join Types in a JoinRowSet**

- Another example of **Inner Join** operation: joining vehicle and delivery table on the foreignkey vid.
```java
package org.example;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.SQLException;

public class EntryPoint {
    
    public static void main( String[] args ) {
        try (Connection conn = DBUtils.getMysqlConnection("/deliveryservice")) {

            //We select all rows from each table and passed as CacheRowSet.

            //Select all rows from delpartners table.
            CachedRowSet vehiclesRS = DBUtils.getCachedRowSet("");
            vehiclesRS.setCommand("select * from delvehicles");
            vehiclesRS.execute(conn); //execute using the Connection object defined in the try-with-resources
            //Select all rows from deliveries table.
            CachedRowSet deliveriesRS = DBUtils.getCachedRowSet("");
            deliveriesRS.setCommand("select * from deliveries");
            deliveriesRS.execute(conn); //execute using the Connection object defined in the try-with-resources

            //Join the two CachedRowSets

            //Create a JoinRowSet that will hold the data that will be joined.
            JoinRowSet joinRS = RowSetProvider.newFactory().createJoinRowSet();
            //Add each of the CachedRowSet to the JoinRowSet object.
            joinRS.addRowSet(vehiclesRS, "vid");
            joinRS.addRowSet(deliveriesRS, "vid");

            int rowNum=1;

            while(joinRS.next()){
                String color = joinRS.getString("color");
                String vehicleType = joinRS.getString("vehicle_type");
                String destination = joinRS.getString("destination");

                String stdData = "\nRow #%d: %s | %s | %s";
                System.out.format(stdData, rowNum, color, vehicleType, destination);
                rowNum++;

            }
            /*
            Row #1: Yellow | Sedan | Bonn
            Row #2: Purple | Truck | Brno
            Row #3: Blue | Truck | Prague
            Row #4: Orange | Van | Abuja
            Row #5: Red | Pickup | London
             */
            //closing the connections
            joinRS.close();
            vehiclesRS.close();
            deliveriesRS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```
- An example of joining three tables
The SQL command for the join
```sql
-- select all fields/columns neeed, first join delvehicles with deliveries on 'vid', 
-- then join the resulting (delvehicles_deliveries) with delpartners on 'pid' field.
select p.first_name, v.color, v.vehicle_type, d.destination 
from delvehicles v join deliveries d on v.vid = d.vid
				   join delpartners p on p.pid = d.pid;
```
The Java code 
```java
package org.example;

import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.Connection;
import java.sql.SQLException;

public class EntryPoint {
    
    public static void main( String[] args ) {
        
        try (Connection conn = DBUtils.getMysqlConnection("/deliveryservice")) {
            
            CachedRowSet partnersRS = DBUtils.getCachedRowSet("");
            partnersRS.setCommand("select * from delpartners");
            partnersRS.execute(conn);

            CachedRowSet vehiclesRS = DBUtils.getCachedRowSet("");
            vehiclesRS.setCommand("select * from delvehicles");
            vehiclesRS.execute(conn);

            CachedRowSet deliveriesRS = DBUtils.getCachedRowSet("");
            deliveriesRS.setCommand("select * from deliveries");
            deliveriesRS.execute(conn);
            
            JoinRowSet joinRSPartnersDelivery = RowSetProvider.newFactory().createJoinRowSet();
            JoinRowSet joinRSVehicleDelivery = RowSetProvider.newFactory().createJoinRowSet();
            JoinRowSet joinRS = RowSetProvider.newFactory().createJoinRowSet();

            //Adding vehicles and deliveries together
            joinRSVehicleDelivery.addRowSet(vehiclesRS, "vid");
            joinRSVehicleDelivery.addRowSet(deliveriesRS, "vid");

            //First add Vehicles to joinRSPartnersDelivery then add the devlieriesRS
            joinRSPartnersDelivery.addRowSet(partnersRS, "pid");
            //A deep copy of deliveriesRS is created because we already used deliveryRs in line 49.
            joinRSPartnersDelivery.addRowSet(deliveriesRS.createCopyNoConstraints(), "pid");

            //Add both joinRSPartnersDelivery and joinRSVehicleDelivery to joinRS
            //Both of them should now have 'did' field since both has been joined with deliveries
            joinRS.addRowSet(joinRSVehicleDelivery, "did");
            joinRS.addRowSet(joinRSPartnersDelivery, "did");
            
            int rowNum=1;

            while(joinRS.next()){
                String fName = joinRS.getString("first_name");
                String color = joinRS.getString("color");
                String vehicleType = joinRS.getString("vehicle_type");
                String destination = joinRS.getString("destination");

                String stdData = "\nRow #%d: %s | %s | %s | %s";
                System.out.format(stdData, rowNum, fName, color, vehicleType, destination);
                rowNum++;
            }
            /*
            Row #1: Danny | Red | Pickup | London
            Row #2: Gerrard | Orange | Van | Abuja
            Row #3: Zoe | Blue | Truck | Prague
            Row #4: Emily | Purple | Truck | Brno
            Row #5: Rebecca | Yellow | Sedan | Bonn
             */
            //closing the connections
            joinRS.close();
            joinRSPartnersDelivery.close();
            joinRSVehicleDelivery.close();
            partnersRS.close();
            vehiclesRS.close();
            deliveriesRS.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
```

**(D) Defining a Filter with a Predicate**
* FilteredRowSet is used to apply a filter on the contents of a RowSet, effectively implementing a where clause. A separate
Java class file is needed to implement it. This part of the course is skipped.

**Collection SQL Commands Used JBDC 1-4 Course**
```sql
-- create database schema
create database DeliveryService;

-- use database schema
use DeliveryService;

-- show tables within a database schema
show tables;

-- create table
create table if not exists delpartners(
id int auto_increment primary key, -- this is a primary keyfor the worker(partner)
first_name varchar(100),
last_name varchar(100),
hourly_rate double,
is_fulltime boolean
);

-- insert statement
insert into delpartners values(101, 'Rebecca', 'Rabbit', 18.5, true);
insert into delpartners (first_name, last_name, hourly_rate, is_fulltime ) values ('Pedro', 'Pony', 31.2, true);

-- select statement
select * from delpartners; -- select all from the table
select first_name, last_name, hourly_rate, is_fulltime from delpartners where id = 105;
select *  from delpartners where id between 102 and 105;

-- update statement
update delpartners set hourly_rate = hourly_rate + 50 where is_fulltime = true;

-- delete statement
delete from delpartners where id = 107;

-- drop(delete) a table
drop table delpartners

-- a tabe with foreign keys
create table if not exists deliveries(
                                       did int primary key, -- deliver id
                                       pid int, -- partner id
                                       vid int, -- vehicle id,
                                       destination varchar(20), -- destination
  foreign key(pid) references delpartners(pid) on delete cascade, -- setting of a column as foreign key
  foreign key(vid) references delvehicles(vid) on delete cascade); -- setting of a column as foreign key
  
-- inner join ooperation involving two tables
-- p. means select from delivery partners  d. means deliveries
select p.first_name, p.is_fulltime, d.destination from delpartners p join deliveries d on p.pid=d.pid;

-- inner join operation involving three tables
-- select all fields/columns neeed, first join delvehicles with deliveries on 'vid', 
-- then join the resulting (delvehicles_deliveries) with delpartners on 'pid' field.
select p.first_name, v.color, v.vehicle_type, d.destination
from delvehicles v join deliveries d on v.vid = d.vid
                   join delpartners p on p.pid = d.pid;
```

## 9: Java Database Connectivity (JDBC): Batch Executions & Transactions with JDBC
Batch executions: optimization of the execution of multiple similar queries by aggregating them together in a batch and\
them execute them as a unit.
**(A) Running Insert Queries in a Batch**
- Batch Update: instead of transmitting each query one at a time, several update queries can be combined in a batch. This\
lead to optimization and reduction of network overhead.
- The process involves:
  1. Instantiating a Connection object
  2. Instantiate a Statement object from the Connection object
  3. Add several individual commands to the Statement object to form a batch
  4. Execute the batch within the statement
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class JDBCAdvanced {
     public static void main(String [] args){

         //Individual SQL commands will be added to the Statement object to form a batch.
         Statement stmnt;

         //try-with-resources
         try(Connection conn = DBUtils.getMysqlConnection("/deliveryservice")){

             stmnt = conn.createStatement();

             //Adding queries into tyhe statement. All of these happened without a network connection.
             stmnt.addBatch("insert into delvehicles values(16, 'Black', 'Sedan', 'B16')");
             stmnt.addBatch("insert into delvehicles values(17, 'White', 'Truck', 'W17')");
             stmnt.addBatch("insert into delvehicles values(18, 'Blue', 'Van', 'B18')");
             stmnt.addBatch("insert into delvehicles values(19, 'Red', 'Shola', 'R19')");

             //Final we call executeBatch() on the Statement object. This is the point when a network connection happens.
             int [] count = stmnt.executeBatch();

             System.out.print("Batch execution successful: \n"+ Arrays.toString(count));
             /*
             Batch execution successful:
             [1, 1, 1, 1]
              */
         }catch(SQLException sqlx){
             sqlx.printStackTrace();
         }
     }
}
```
**(B) Parameterized Batch Inserts**
- Another example **un-parameterize** batch insert involving delete
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class JDBCAdvanced {
     public static void main(String [] args){

         //Individual SQL commands will be added to the Statement object to form a batch.
         Statement stmnt;

         //try-with-resources
         try(Connection conn = DBUtils.getMysqlConnection("/deliveryservice")){

             stmnt = conn.createStatement();

             //Adding queries into the statement. All of these happened without a network connection.
             stmnt.addBatch("delete from delvehicles where vid between 1 and 10");

             //Final we call executeBatch() on the Statement object. This is the point when a network connection happens.
             int [] count = stmnt.executeBatch();

             System.out.print("Batch execution successful: \n"+ Arrays.toString(count));
             /*
             Batch execution successful:
             [4]
              */
         }catch(SQLException sqlx){
             sqlx.printStackTrace();
         }
     }
}
```
- Example of **parameterized** batch insert
- The DBUtil class has been modified to include two methods:
  1. getInsertVehiclePS() returns a PreparedStatement object from a Connection object
  2. addToInsertVehicleBatch() takes a PreparedStatement object along with fields of the table and calls various set methods\
     from the PreparedStatement before finally calling addBatch() method of the PreparedStatement
```java
package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

public class DBUtils {

    private static final String dbURL = "jdbc:mysql://localhost:3306";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";
    
    public static Connection getMysqlConnection(String schemaName){
        //Note that a database schema has not been created. Will be done in subsequent section
        MysqlDataSource mysqlDS = null;
        Connection conn = null;
        
        try{
            mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(dbURL + schemaName);
            mysqlDS.setUser(username);
            mysqlDS.setPassword(password);

            conn = mysqlDS.getConnection();

        }catch(SQLException e){
          e.printStackTrace();
        }
        return conn;
    }

    public static  JdbcRowSet getJdbcRowSet(String schemaName){

        JdbcRowSet jdbcRS = null;
        try{
            //We use the RowSetProvider class(which has implemented the methods defined within JdbcRowSet) to object JdbcRowSet
            jdbcRS = RowSetProvider.newFactory().createJdbcRowSet();
            jdbcRS.setUrl(dbURL + schemaName);
            jdbcRS.setUsername(username);
            jdbcRS.setPassword(password);

        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }
        return jdbcRS;
    }

    public static CachedRowSet getCachedRowSet(String schemaName){
        CachedRowSet cachedRS = null;
        try{
            cachedRS = RowSetProvider.newFactory().createCachedRowSet();
            cachedRS.setUrl(dbURL + schemaName);
            cachedRS.setUsername(username);
            cachedRS.setPassword(password);
        } catch (SQLException sqlx) {
            sqlx.printStackTrace();
        }
        return cachedRS;
    }
    
    //This method will return PreparedStatement object from Connect object
    public static PreparedStatement getInsertVehiclePS(Connection conn){
        try{
            return conn.prepareStatement("insert into delvehicles value(?, ?, ?, ?)");
        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }
        return null;
    }

    public static boolean addToInsertVehicleBatch(PreparedStatement ps, int id,
                                                  String color, String vehicle_type, String licence_plate){
        boolean addedQuery = false;
        try{
            ps.setInt(1, id);
            ps.setString(2, color);
            ps.setString(3, vehicle_type );
            ps.setString(4, licence_plate );
            ps.addBatch();
            addedQuery = true;

        }catch(SQLException sqlx){
            sqlx.printStackTrace();
        }
        return addedQuery;
    }
}
```
- The consumer class
```java
package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

public class JDBCAdvanced {
     public static void main(String [] args){

         //try-with-resources
         try(Connection conn = DBUtils.getMysqlConnection("/deliveryservice")){

            PreparedStatement prStmnt = DBUtils.getInsertVehiclePS(conn);

            DBUtils.addToInsertVehicleBatch(prStmnt, 20, "Purple", "Truck", "P20");
            DBUtils.addToInsertVehicleBatch(prStmnt, 21, "Green", "Van", "G21");
            DBUtils.addToInsertVehicleBatch(prStmnt, 22, "Grey", "SUV", "G22");
            DBUtils.addToInsertVehicleBatch(prStmnt, 23, "Pink", "Van", "P23");
            DBUtils.addToInsertVehicleBatch(prStmnt, 24, "White", "Sedan", "W24");
            DBUtils.addToInsertVehicleBatch(prStmnt, 25, "Black", "Bus", "B25");

            int [] count = prStmnt.executeBatch();

            System.out.print("Insertion successful: \n"+Arrays.toString(count));
            /*
            Insertion successful:
            [1, 1, 1, 1, 1, 1]
             */

         }catch(SQLException sqlx){
             sqlx.printStackTrace();
         }
     }
}
```
**NOTE:**
- If any of the insert queries above is erroneous, the queries before and after the erroneous query will be executed successfully.\
Only the erroneous query will be ignored.
- Modification to this behavior can be accomplished by using **Transactions**. For example to model a behavior in which all\
insert goes through or none at all. Updating of multiple rows in multiple tables is a common real-life operations with tables\
and their could exist dependency between tables in such a way that the value from one table is dependent on another. 

**(C) Implementing a Transaction**\
Atomic level transaction means that all the queries specified should run or non should run.(i.e. if an erroneous command\
exists, all other commands should be ignored.)...all-or-nothing behavior.
- Implementing transaction with a sequence statement:
  1. Autocommit is disabled
  2. commit() method is explicitly called only at the end of the line when all insertion goes through
  3. Hence, even if any of the previous or proceeding query were to succeed, they won't be committed. Due to its position\
     within the lines of code, the commit method will only get invoked when there is no error or exception.
In the code below, even though the first insert command is correct, it will not go through to the database
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCAdvanced {
  public static void main(String [] args){

    Statement stmnt;
    String query;
    Connection conn = null;

    try{

      conn = DBUtils.getMysqlConnection("/deliveryservice");
      //We set AutoCommit to false
      conn.setAutoCommit(false);

      stmnt =  conn.createStatement();

      query = "insert into delpartners values (114, 'Ms', 'Rabbit', 94.2, true)";
      stmnt.executeUpdate(query);

      query =   "insert into delvehicles values (25, 'Golden!', 'Truck', 'Gd28')"; //there is already a row with id=25
      stmnt.executeUpdate(query);

      conn.commit();
      System.out.print("Rows have been successfully inserted.");

    }catch(SQLException sqlx){
      System.out.print("An exception was thrown. Rolling back the Tx....\n");
      conn.rollback(); // The entire operation is rollback
      sqlx.printStackTrace();
    }
         /*
         An exception was thrown. Rolling back the Tx....
        java.sql.SQLIntegrityConstraintViolationException: Duplicate entry '25' for key 'delvehicles.PRIMARY'
            at com.mysql.cj.jdbc.exceptions.SQLError.createSQLException(SQLError.java:117)
            at com.mysql.cj.jdbc.exceptions.SQLExceptionsMapping.translateException(SQLExceptionsMapping.java:122)
            at com.mysql.cj.jdbc.StatementImpl.executeUpdateInternal(StatementImpl.java:1334)
            at com.mysql.cj.jdbc.StatementImpl.executeLargeUpdate(StatementImpl.java:2084)
            at com.mysql.cj.jdbc.StatementImpl.executeUpdate(StatementImpl.java:1245)
            at org.example.JDBCAdvanced.main(JDBCAdvanced.java:26)
          */
  }
}
```
**(D) Using Savepoints in a Transaction (Sequence Operation)**\
Savepoint can be used to determine the point at which rollback should be done instead of discarding the entire when a single\
command is erroneous.
```java
package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

public class JDBCAdvanced {
     public static void main(String [] args) throws SQLException {

         Statement stmnt;
         String query;
         Connection conn = null;
         Savepoint sp = null;

         try{

             conn = DBUtils.getMysqlConnection("/deliveryservice");
             //We set AutoCommit to false
             conn.setAutoCommit(false);

             stmnt =  conn.createStatement();

            query = "insert into delpartners values (114, 'Ms', 'Rabbit', 94.2, true)"; //valid
            stmnt.executeUpdate(query);

            query =   "insert into delvehicles values (28, 'Golden!', 'Truck', 'Gd28')"; //valid
            stmnt.executeUpdate(query);

            //creating/defining a Savepoint
             sp = conn.setSavepoint("PointOne");
             System.out.print("save point created: "+sp.getSavepointName());


             query = "insert into delpartners values (115, 'GrandPa', 'Pig', 65.1, true)"; //valid
             stmnt.executeUpdate(query);

             query =   "insert into delvehicles values (28, 'Golden!', 'Truck', 'Gd28')"; //invalid(same as above)
             stmnt.executeUpdate(query);

             //another savepoint
             sp = conn.setSavepoint("PointTwo");
             System.out.print("save point created: "+sp.getSavepointName()); //save point created: PointOne

             //Even though the two insertions below are valid, they wont execute successfully since the preceding savepoint failed.
             query = "insert into delpartners values (116, 'GrandMa', 'Pig', 43.1, false)"; //valid
             stmnt.executeUpdate(query);

             query =   "insert into delvehicles values (29, 'Silver!', 'Truck', 'Sv28')"; //valid
             stmnt.executeUpdate(query);

             conn.commit();
             System.out.print("Rows have been successfully inserted.");

         }catch(SQLException sqlx){
             //if sp contains at least one Savepoint (i.e. if sp is not null)
             if(sp != null ){
                 System.err.println("An exception was thrown. Rolling back back to "+sp.getSavepointName());
                 //save point created: PointOne
                 conn.rollback(sp);
                 conn.commit();
             }else{
                 //if sp contains no Savepoint...(i.e. if sp is null)
                 System.err.println("Errors detected. Rolling back everything.");
                 conn.rollback();
             }

         }
         
     }
}
```
**(E) Applying Savepoints to Batch Operations**\
This section deals with applying Savepoints to batch operations.
```java
package org.example;

import java.sql.*;
import java.util.Arrays;

public class JDBCAdvanced {
     public static void main(String [] args) throws SQLException {

         int [] count = {};
         Connection conn = null;
         Savepoint sp = null;

         try{

             conn = DBUtils.getMysqlConnection("/deliveryservice");
             //We set AutoCommit to false
             conn.setAutoCommit(false);

             PreparedStatement prStmnt = DBUtils.getInsertVehiclePS(conn);

             //1st batch
             DBUtils.addToInsertVehicleBatch(prStmnt, 29, "Orange", "Truck", "O29");//valid
             DBUtils.addToInsertVehicleBatch(prStmnt, 30, "Yellowish", "Pickup", "G30");//valid
             count = prStmnt.executeBatch();
             //Savepoint on a batch
             sp = conn.setSavepoint("BatchOne");
             System.out.print("First batch executed: "+ Arrays.toString(count)); //First batch executed: [1, 1]

             //2nd batch
             DBUtils.addToInsertVehicleBatch(prStmnt, 31, "Pink", "Van", "P31");//valid
             DBUtils.addToInsertVehicleBatch(prStmnt, 21, "Green", "Van", "G21");//invalid
             count = prStmnt.executeBatch();
             System.out.print("Second batch executed: "+ Arrays.toString(count));
             
             conn.commit();

         }catch(SQLException sqlx){
             //if sp contains at least one Savepoint (i.e. if sp is not null)
             if(sp != null ){
                 System.err.println("An exception was thrown. Rolling back back to "+sp.getSavepointName()); 
                 //An exception was thrown. Rolling back back to BatchOne
                 conn.rollback(sp);
                 conn.commit();
             }else{
                 //if sp contains no Savepoint...(i.e. if sp is null)
                 System.err.println("Errors detected. Rolling back everything");
                 conn.rollback(); //rolling back everything
             }
         }
     }
}
```
## 10: Building Web Applications with JSP: An Introduction to JSP
JavaServer pages (JSP) is a collection of technologies which greatly simplifies the building of dynamic websites. The web\
application resource file (**WAR**) is the distribution file for a JSP application. WAR file can be seen as JAR file for\
web applications.

**(A) Creating a Web Application USing Maven**\
All web application built using JSP requires a certain structure. This includes a directory structure as well as a web.xml file.\
Maven provides project archetypes(template) that adhere to the requirement structure of JSP(e.g. maven-archetype-webapp).\
The archetype provides the package element and plugin for packing WAR files to be deployed to Apache Tomcat webserver.
<img src="\images\jsp_projectstructure.png">
From Tomcat10 onward javax.* should be referenced as jakarta.* 
1. Build the web app project using the archetype and ensure the packaging element on the pom.xml file is set to WAR.
2. Package: `mvn clean package` command from the prject home
3. Deploy: copy the packaged war file(e.g MyWARproject.war) to the webapps (tomcat/webapps) folder inside tomcat path. 
4. Start Tomcat: cd to tomcat/bin and run `./catalina.sh start` 
   - We can also change(if not yet done) the file to executable using `chmod +x startup.sh` and `chmod +x catalina.sh` 
   - Verbose startup: `./startup.sh && tail -f ../logs/catalina.out`
5. Navigate: localhost:portnumber/MyWARproject

**(B) Writing and Deploying a JSP App**\
JSP file combines Java codes and html. The section within html tags that includes a Java code is known as scriplet.
```jsp
<html>
<body>
<h2>Hello World!</h2>
    <% int num =10; %>
    The number is <%
        for (int i = 1; i <10 ; i++) {
            out.println("Current number is: "+num);
        }
    %>
</body>
</html>
```
There are two types of JSP tags: declaration `<%! %>` and expression tag `<%= %>`. Functions/methods are declared in the declaration\
tag while expression tags invokes an object that can be directly sent to the output stream without explicitly invoking print\prinln.
```jsp
<html>
<body>
<h2>Hello World!</h2>

<%--declaration tag--%>
 <%!
    String makeUpperCase(String string){
        return string.toUpperCase();
    }
 %>

<%--expression tag--%>
His name is: <%= makeUpperCase("Shola")%>

</body>
</html>
```
**Controller-View Design**\
The previous codes are for demonstration purpose because purely mixing Java codes and HTML is bad practice. Best practice\
is to separate the Java code and place it in a separate the Java code and reference it from JSP. This makes the Java code\
the **controller** of the app and JSP serves as the **view** of the application. This is compliant with MVC pattern.
- Controller class
```java
package org.shola;
public class NameUtils{
    public static String makeItUpper(String character){
        return character.toUpperCase();
    }
}
```
- View. Note **<@** is used for import
```jsp
<%@ page import="org.shola.NameUtils" %>

<html>
<head><title>Hey there </title> </head>
<body>
<h2>Hello World!</h2>

His name is: <%= NameUtils.makeItUpper("Hello wworld!") %>
</body>
</html>
```
Accessing the information included in the REQUEST object from the client.\
**(C) Accessing the Request Object**
```html
<html>
<head><title>Hey there </title> </head>
<body>
<h2>Hello World!</h2>

<b>Request user agent:</b>
<%= request.getHeader("User-Agent")%>

<br/><br/>
<b>Request locale:</b>
<%=request.getLocale() %>

<br/><br/>
<%= request.getLocalAddr()%>
<%=request.getLocalPort()%>
</body>
</html>
```
**(D) Referencing Other JSP Pages**\
The aim is to define a single JSP page out of multiple JSP files using` <jsp:include></jsp:include>`
- header.jsp
```jsp
<h1 align="center">Welcome to JSP</h1>
```
- footer.jsp
```jsp
<p align = "center">
Last viewed: <%= new java.util.Date()%>
</p>
```
- index.jsp
```jsp

<html>
<head><title>Hey there </title> </head>
<body>

<%--The path of the included page is relative to index.jsp(i.e. webapp/jsp/header.jsp   webapp/index.jsp--%>
<jsp:include page="jsp/header.jsp"></jsp:include>

JavaServer Pages(JSP) is a server-side programming technology.

<%--The path of the included page is relative to index.jsp(i.e. webapp/jsp/footer.jsp   webapp/index.jsp--%>
<jsp:include page="jsp/footer.jsp"></jsp:include>

</body>
</html>
```

## 11: Building Web Applications with JSP: Handling Errors
**(A) Integrating an Error Page**
- The index.jsp file contains form element with process.jsp as the intended target and error.jsp as error handling mechanism.
- The built-in **errorPage** definition helps to handle error by using the directive (**<@**) tag to reference a dedicated\
page for error. The dedicated jsp file handling error, is co-referenced/confirmed with **isErrorPage** reference.
- error.jsp
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:14 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true"  contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Title</title>
  </head>
  <body>
      <h2>An Exception has occurred !</h2>
      Exception is: <%= exception.getLocalizedMessage() %>
  </body>
</html>
```
- process.jsp
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:15 PM
  To change this template use File | Settings | File Templates.
--%>

<%--import the error page--%>
<%@ page errorPage="error.jsp" %>

<%
    String firstNum = request.getParameter("num1");
    String secNum = request.getParameter("num2");

    int num1 = Integer.parseInt(firstNum);
    int num2 = Integer.parseInt(secNum);
    int div = num1 /num2;
    int rem = num1%num2;

    out.print("Quotient of "+num1+"/"+num2 +":"+div);
    out.print("<br />Remainder of "+num1+"/"+num2 +":"+rem);
    
%>
```
- index.jsp
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
      <title>Title</title>
  </head>
  <body>
  
      <form action="jsp/process.jsp">
          Num1: <input type="text" name="num1">
          <br/>
          <br/>
          Num2: <input type="text" name = "num2">
          <br/>
          <br/>
          <input type="submit" value="Divide">
      </form>
  
  </body>
</html>
```
**(B) Using the JSP Expression Language**\
The attribute `isELIgnored` (is Expression Language Ignored) is used to flag on/off. Expression language is used to define\
the location of the image in the modified error page below (see the img tag):

```html
<%--
Created by IntelliJ IDEA.
User: absuleim
Date: 9/9/2022
Time: 3:14 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true"  %>
<%@ page isELIgnored="false" %>
<html>

    <head>
        <title>Title</title>
    </head>

    <body>
        <h2>An Exception has occurred !</h2>

        Exception is: <%= exception.getLocalizedMessage() %>
        
        <%-- The pageContext...constructs a path to the webapp folder (instead of hard coding it)--%>
        <img src="${pageContext.request.contextPath}/img/error.png" alt="Error"><br/>

        Image sourced from
        <a href="https://wwww.pexels.com/photo.man-people-art-sign-4439425">This Location at pexels.com</a>
    </body>

</html>
```
**(C) Setting an Application-level Error Page**
- If defining custom error page for each jsp page, an application level error page can be defined to handle all sort of errors\
from several jsp page within the application. The errorPage attribute is removed from process.jsp and web.xml file is \
modified by adding the <error-page> tag.
- Multiple error-page tags can be defined within the web.xml. This helps to catch multiple error domains.(e.g. error-code,
exception-type)
- web.xml 
```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Archetype Created Web Application</display-name>
  
  <error-page>
    <exception-type>java.lang.Exception</exception-type>
    <location>/jsp/error.jsp</location>
  </error-page>

  <error-page>
    <error-code>404</error-code>
    <location>/jsp/error404.jsp</location>
  </error-page>
  
</web-app>
```

## 12: Building Web Applications with JSP: Customizing Responses with Servlets
**(A) Combining Java CoreServlet and JSP**
- Java core servlet is the underlying technology behind JS.
- Since javax.servlet has been modified to jakar.servlet for apache tomcat 10 upward, the jakarta servlet dependency has\
been added to the pom.xml from the immediately preceding course (course 11). 

```xml
 <dependencies>
    <dependency>
      <groupId>jakarta.servlet</groupId>
      <artifactId>jakarta.servlet-api</artifactId>
      <version>5.0.0</version>
<!-- 'provided' means that dependency doesnt need to be packaged with the WAR file. It will be 'provided' by Tomcat-->
      <scope>provided</scope>
    </dependency>
</dependencies>
```
- Modification has been made to source file to include the following servlet classes: 
1. **{ProjectHome}/src/main/java/org.shola/MyServlet.java**: A simple servlet that outputs some message. It is referenced within\
index.jsp.
```java
package org.shola;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

//An annotation used to declare a servlet class. It can be used to set a name and also to map a servlet to a url pattern
@WebServlet(name ="myServlet", urlPatterns = "/my-servlet")
public class MyServlet extends HttpServlet {

    private String message;

    public void init(){
        message = "Hello ! I am a servlet and here to serve.";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        response.setContentType("text/html");

        //Writing html into the resonse
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>"+message+"</h1>");
        out.println("</html></body>");
    }

    public void destroy(){}
}
```
2. **{ProjectHome}/src/main/java/org.shola/RegisterServlet.java**: This servlet is referenced fromm within index.jsp, and it\
handles the processing of data submitted with the form. 
```java
package org.shola;

import java.io.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "register", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    //Since HTTPServlet implements the Serializable interface, we explicitly set the serialVersionUID to track any changes
    //to the servlet class after it has been serialized.(see the course on serialization).
    public static long serialVersionUID = 1L;

    protected  void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{

        //Obtaining the data embedded in the request object
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String member = request.getParameter("membership");
        String date = request.getParameter("date");
        //Since checkbox can have multiple parameters, an array of the parameter values will be returned.
        String [] subscription = request.getParameterValues("subs");

        //Using the response object to give a response back
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();

        pw.write("<html><body align=\"center\">");
        pw.write("<h1 align=\"center\">Registration Information</h1>");
        pw.write("<br><b>Name: </b>" + name);
        pw.write("<br><b>Email: </b>" + email);
        pw.write("<br><b>Membership: </b>" + member);
        pw.write("<br><b>Name: </b>" + date);
        pw.write("<br><b>Subscriptions:("+subscription.length+")</b>");
        int count = 0;
        while(count<subscription.length){
                pw.write("<br><b>"+count+ ": </b> " + subscription[count]);
            count++;
        }
        pw.write("</html></body>");
    }
}
```
3. **{ProjectHome}/src/main/webapp/index.jsp**: Contains the form
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Servlet - Hello World</title>
</head>
    <body align="center">

        <h1 align="center">  Registration Page </h1>

        <form action="register" align="center">
            Name: <label>
                    <input type="text" name="name"><br><br>
                  </label>

            Email: <label>
                        <input type="text" name="email"><br><br>
                   </label>
            Membership:
                   <input type="radio" name="membership" value="regular">Regular
                   <input type="radio" name="membership" value="premium" checked>Premium
                   <input type="radio" name="membership" value="elite">ELite
                    <br><br>
            Subscription:
                   <input type="checkbox" name="subs" value="news">Newsletter
                   <input type="checkbox" name="subs" value="offers">Offers
                   <input type="checkbox" name="subs" value="recs">Recommendations
                    <br><br>
            End Date:
            <input type="date" name="date">
            <br><br>
            <input type="submit" value="Submit">
            <br><br>
            <input type="reset">
            <br><br>
            <a href="my-servlet">Link to A Simple Servlet</a>

        </form>

    </body>
</html>
```

**(B) Using the RequestDispatcher**\
A requestDispatcher allows partially processing an incoming request in one servlet and then passing(dispatch) it to another\
servlet where more processing can be performed.\
The web.xml permits the mapping of servlets to URL endpoints. This functionality is analogous in many ways to the @WebServlet\
annotation used previously in the servlet classes. Hence, servlets that has been mapped using the web.xml doesn't't need to\
have the @WebServlet annotation.
- The **web.xml** file has been modified to reflect the new servlet classes and there URL mapping
```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  
  <display-name>Archetype Created Web Application</display-name>
  
<!--  Servlets declaration-->
  <servlet>
    <servlet-name>bookservlet</servlet-name>
    <servlet-class>org.shola.BookInfoServlet</servlet-class>
  </servlet>
  
  <servlet>
    <servlet-name>bookconfirmservlet</servlet-name>
    <servlet-class>org.shola.SubmissionServlet</servlet-class>
  </servlet>
  
<!--  Servlet-URL mapping-->
  <servlet-mapping>
    <servlet-name>bookservlet</servlet-name>
    <url-pattern>/bookinfo</url-pattern>
  </servlet-mapping>
  
  <servlet-mapping>
    <servlet-name>bookconfirmservlet</servlet-name>
    <url-pattern>/booksubmitconfirm</url-pattern>
  </servlet-mapping>
  
  <error-page>
    <exception-type>java.lang.Exception</exception-type>
    <location>/jsp/error.jsp</location>
  </error-page>
  <error-page>
    <error-code>404</error-code>
    <location>/jsp/error404.jsp</location>
  </error-page>
  
  
</web-app>

```
1. The **index.jsp** now has form which collects book data
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Servlet - Hello World</title>
</head>
    <body align="center">

        <h1 align="center">  Registration Page </h1>

        <form action="bookinfo" method = get align="center">
            Enter book name: <input type="text" name="bookname"><br><br>
            Enter author name: <input type="text" name="authorname"><br><br>
            Enter date: <input type="date" name="date"><br><br>
            <input type="submit"> <input type="reset">
        </form>

    </body>
</html>
```
2. **{ProjectHome}/src/main/java/org/shola/BookInfoServlet.java** --> This servlet takes the input data directly from index.jsp,\
preprocess it, before dispatching it another servlet(SubmissionServlet.java)
```java
package org.shola;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
public class BookInfoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException {
        String bookName = req.getParameter("bookname");
        String authorName = req.getParameter("authorname");
        String date = req.getParameter("date");
        
        req.setAttribute("updatedBookName", bookName.trim().toLowerCase());
        req.setAttribute("updatedAuthorName", authorName);

        //RequestDispatcher
        //This sends the entire request as well as the updatedBookName e.t.c to the second servlet class "booksubmitconfirm"
        RequestDispatcher redis = req.getRequestDispatcher("booksubmitconfirm");
        redis.forward(req, resp);
    }
}
```
3. **{ProjectHome}/JSP/src/main/java/org/shola/SubmissionServlet.java** --> this servlet displays the processed information.
```java
package org.shola;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class SubmissionServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        //Note the naming of the request object as compared to BookInfoServlet. This is to show that each servlet has it
            //own request and response object. The current request object has access to the data dispatched to it through
            // the HttpServletRequest getAttribute() method
        String updatedBookName = (String) request.getAttribute("updatedBookName");
        String updatedAuthorName = (String) request.getAttribute("updatedAuthorName");
        String date = (String) request.getAttribute("date");
        
        PrintWriter pw = response.getWriter();

        pw.println("<html><body align=\"center\">");
        pw.write("<h1 align=\"center\">Updated Book Information</h1>");
        pw.write("<br><b>Author Name: </b>" + updatedAuthorName);
        pw.write("<br><b>Book Name: </b>" + updatedBookName);
        pw.write("<br><b>Date: </b>" +date);
        pw.write("</html></body>");
    }
}
```
The @WebServlet annotation is a more recent addition to Java and its more favorable.\
**(C) Forwarding a Request from a Servlet to a JSP Page**\
- The use of servlet to generate HTML content as done in the previous section isn't of best practice. It is best to provide a\
JSP page for handling the view aspect of application. Hence, the modification to the BookInfoServlet class.
```java
package org.shola;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns ="/bookinfo")
public class BookInfoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException, ServletException {
        String bookName = req.getParameter("bookname");
        String authorName = req.getParameter("authorname");
        String date =  req.getParameter("date");

        req.setAttribute("updatedBookName", bookName.trim().toLowerCase());
        req.setAttribute("updatedAuthorName", authorName);
        req.setAttribute("updatedDate", authorName);
        
        //RequestDispatcher
        RequestDispatcher redis = req.getRequestDispatcher("/jsp/booksubmitconfirm.jsp");
        redis.forward(req, resp);
    }
}
```
- **{ProjectHome}/JSP/src/main/webapp/jsp/booksubmitconfirm.jsp** --> This is the new JSP page as presentation/view layer.\
Note the **isELIgnored**since expression language is used to add the data from the Servlet to the JSP page.
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Servlet - Hello World</title>
</head>
<body align="center">

<h3 align="center">  Information Submitted Successfully! </h3>

    //Expression language
    <b>Book Title:</b> ${updatedBookName } <br>
    <b>Author Name:</b> ${ updatedAuthorName } <br>
    <b>Date</b> ${ updatedDate} <br>

</body>
</html>
```
**(D) Working with ServletConfig and ServletContext**\
Both ServletConfig and ServletContext gives servlet specific information. ServletConfig is servlet-specific. While ServletContext\
has to do with the entire application as whole. Hence, the ServletContext provides information about all servlet within the\
application
```java
package org.shola;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Iterator;

@WebServlet(name="info-servlet", urlPatterns = "/servletinfo")
public class ServletInfo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        PrintWriter out = response.getWriter();
        out.write("Servlet details:");

        ServletConfig cfg = getServletConfig();
        out.write("\nServlet name: "+cfg.getServletName());
        out.write("\nConfig params--: ");
        Enumeration<String> paramIterator = cfg.getInitParameterNames();
        while (paramIterator.hasMoreElements()) {
            String name = paramIterator.nextElement();
            out.write(name);
        }

        ServletContext ctx = getServletContext();
        out.write("\nContext name: "+ctx.getServletContextName()); //This is the name set inside the web.xml file.
        out.write("\nContext path: "+ctx.getContextPath());
        out.write("\nServer info: "+ctx.getServerInfo());

        //Attributes set within the ServletContext applies to and is available to all servlet within the application.
        ctx.setAttribute("rootuseer", "myadmin");

    }
}
```

**(E) Setting and Accessing Attributes at Different Scopes**\
Previous sections on requestDispatcher dealt with accessing attributes set at application level. This section deals with\
different scope of attributes and their access persistence.
- **{ProjectHome}/src/main/webapp/index.jsp** 
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>JSP Servlet - Hello World</title>
</head>
    <body align="center">

        <h2 align="center">  Sign in </h2>

        <form action="signin" align="center">
            Username: <input type="text" name="username"><br><br>
            Password: <input type="text" name="password"><br><br>
            <br><br>
            <input type="submit" value="Sign In">
        </form>

    </body>
</html>
```
- **{ProjectHome}/src/main/java/org/shola/SignInServlet.java** --> this servlet sets the attribute at different levels. This\
attributes will be explored for their persistence and access using the jsp pages.\
**Note:** the servlet forwards its request to the jsp page **signinconfirm.jsp**.
```java
package org.shola;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(urlPatterns = "/signin")
public class SignInServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        //Setting request attribute..request level access is accessible only within the scope of http request.
        //A request level attribute will no longer be accessible when the request has been processed.
        request.setAttribute("username", "request_"+username);

        //HttpSession object. False--> if a session doesnt exist, it shouldn't be created and null should be returned.
        HttpSession session = request.getSession(false);
        //Setting session attribute. Session level attribute is persists longer than the request level.
        //Hence when a request level is no longer accessible, the session level attribute can be used
        session.setAttribute("username", "session_"+username);

        ServletContext ctx = getServletContext();
        //Setting servlet context attribute. The context level(application-wide level) is the most persistent, and it is accessible
            //for as long as the application is up and running
        ctx.setAttribute("username", "servletcontext_"+username);

        try{
            //Dispatching the request to the JSP page 'signinconfirm.jsp
            RequestDispatcher rd = request.getRequestDispatcher("/jsp/signinconfirm.jsp");
            rd.forward(request, response);
        }catch(ServletException svx){
            svx.printStackTrace();
        }
    }
}
```
- **{ProjectHome}/src/main/webapp/jsp/signinconfirm.jsp** --> note that this page forwards/links to **userhoeme.jsp**.
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Sign in successful--signinconfirm</title>
</head>
    <body align="center">

        <h3 align="center">  Welcome aboard ! </h3>

        <b>Request level username: </b> <%= request.getAttribute("username") %> <br><br>

        <b>Session level username: </b> <%= request.getSession().getAttribute("username") %> <br><br>

        <b>ServletContext level username: </b> <%= request.getServletContext().getAttribute("username") %> <br><br>

        Click <a href="${pageContext.request.contextPath}/jsp/userhome.jsp">Here</a> to go to your home page.

    </body>
</html>
```
- **{ProjectHome}/JSP/src/main/webapp/jsp/userhome.jsp** --> note that this page forwards/links to **logout.jsp**.
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Home</title>
        </head>
        <body align="center">

        <h3 align="center">  Hello there ! Here are your details--userhome</h3>

        <b>Request level username: </b> <%= request.getAttribute("username") %> <br><br>

        <b>Session level username: </b> <%= request.getSession().getAttribute("username") %> <br><br>

        <b>ServletContext level username: </b> <%= request.getServletContext().getAttribute("username") %> <br><br>

        Click <a href="${pageContext.request.contextPath}/jsp/logout.jsp">Here</a> to Logout.
        
     </body>
</html>
```
- **{ProjectHome}/src/main/webapp/jsp/logout.jsp**
```html
<%--
  Created by IntelliJ IDEA.
  User: absuleim
  Date: 9/9/2022
  Time: 3:16 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>GoodBye</title>
</head>
<body align="center">

<h3 align="center">  GoodBye !--logout </h3>
<%--    Invalidating the session--%>
    <% session.invalidate(); %>

    <b>Request level username: </b> <%= request.getAttribute("username") %> <br><br>

    <b>Session level username: </b> <%= request.getSession().getAttribute("username") %> <br><br>

    <b>ServletContext level username: </b> <%= request.getServletContext().getAttribute("username") %> <br><br>

</body>
</html>
```
* **Inference:**
1. index.jsp parses the request data to siginconfirm.jsp --> all levels of attributes are accessible
2. signinconfirm.jsp passed the data to userhome.jsp --> request level attribute is lost. Session and ServletContext persists.
3. userhome.jsp passed the data to logout.jsp --> Session level attributes is lost. ServletContext attributes persists

## 13: Building Web Applications with JSP: Integrating a JSP App with a Database
The focus of the course is connecting a JSP website to a database server and allowing end users to perform database operations\
from the web UI. **JSTL library** is a tool that help in iterating over the data returned from a database and rendering them on a web\
page.
JSP **action tags** such **useBean** and **setProperty** as helps in accepting user input and adding them to rows of a database table.\
**(A) Creating the Database Schema and Table**
- Creating an instance of database for the JSP application: 
```sql
-- create a database
create database JSPDatabase;

-- use the database
use JSPDatabase;

-- create Customer database table 
create table Customers(
 id int NOT NULL auto_increment,
 name varchar(100) NOT NULL,
 email varchar(100) NOT NULL,
 gender varchar(100) NOT NULL,
 country varchar(100) NOT NULL,
 PRIMARY KEY (id)
 );

-- insert some data row into the data table
insert into Customers value(101, 'Albert Einstein', 'einstein@gmail.com', 'M', 'DEU');
insert into Customers value(102, 'Isaac Newton', 'newton@gmail.com', 'M', 'UK');
insert into Customers value(103, 'Marie Curie', 'marie@outlook.com', 'F', 'PL');
insert into Customers value(104, 'Leo Euler', 'euler@apple.com', 'M', 'CHE');
insert into Customers value(105, 'Carl Gauss', 'gauss@apple.com', 'M', 'DEU');

-- show the table
select * from Customers;
```
**(B) Connecting a JSP App to a SQL Database**
- The section of the pom.xml for the new Project JSP2JDBC showing the jakarta servlet and MySQL dependencies
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>JSP2JDBC</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>JSP2JDBC Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>jakarta.servlet</groupId>
      <artifactId>jakarta.servlet-api</artifactId>
      <version>5.0.0</version>
      <scope>provided</scope>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.22</version>
    </dependency>
  </dependencies>

</project>
```
- **JSP2JDBC/src/main/webapp/index.jsp** connects to the database and printout the rows of the Customer table as html table
```html
<%--Setting the programming language and the contentType--%>
<%@ page language="java" contentType="text/html; ISO-8859-1; charset=UTF-8" pageEncoding="UTF-8" %>

<%--Importing necessary library--%>
<%@ page import ="java.sql.*" %>
<%@ page import="javax.xml.transform.Result" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html"; charset="ISO-8859-1">
        <title>JSP and Databases</title>
    </head>
    <body>

        <%
//          Check The JDBC course for explanation of establishing a Connection, obtaining a Statement object and ResultSet.
            String url = "jdbc:mysql://localhost:3306/JSPDatabase";
            String user = "root"; //Connecting through root user is not good practice. A user account should exist for each app.
            String password = "Oloyin@@68";
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn =conn = DriverManager.getConnection(url, user, password);

            String query ="select * from Customers";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

        %>


        <center>
            <h3>The customers in the database</h3>
            <table border="2" align="center">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Country</th>
                </tr>

                <%
                    while(rs.next()){
                %>
                    <tr>
                        <td>  <%= rs.getInt("id")%>  </td>
                        <td>  <%= rs.getString("name")%>  </td>
                        <td>  <%= rs.getString("email")%>  </td>
                        <td>  <%= rs.getString("country")%>  </td>
                    </tr>

                   <% }
                      rs.close();
                   %>
            </table>
        </center>
    </body>
</html>
```
**(D) Working with a Model Class and JSTL**
Using a custom class to improve the previous code by implementing the MVC pattern.
- **JSP2JDBC/pom.xml** has been modified to add JSTL as dependency.
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>JSP2JDBC</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>JSP2JDBC Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>jakarta.servlet</groupId>
      <artifactId>jakarta.servlet-api</artifactId>
      <version>5.0.0</version>
      <scope>provided</scope>
    </dependency>

    <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>8.0.22</version>
    </dependency>

    <dependency>
      <groupId>org.glassfish.web</groupId>
      <artifactId>jakarta.servlet.jsp.jstl</artifactId>
      <version>2.0.0</version>
    </dependency>
    
  </dependencies>

  
</project>
```
- **JSP2JDBC/src/main/webapp/index.jsp** modified to include css and reflect the new model class. MVC
```html
<!DOCTYPE html>
<html>
  <head>
    <title>CRUD Operation with MVC Pattern</title>
    <meta http-equiv="Content-Type" content="text/html"; charset="ISO-8859-1">
  </head>
  <style>
    .btn{
      border: 3px solid black;
      color:white;
      padding: 14px 28px;
      text-align: center;
      text-decoration: none;
      display: inline-block;
      font-size: 16px;
      margin: 4px 2px;
      transition-duration:  0.4s;
      cursor:pointer;
    }
  
    .center{
      margin: 0;
      position: absolute;
      top:50%;
      left: 50%;
      -ms-transform: translate(-50%, -50%);
      transform: translate(-50%, -50%);
    }
  
    .info{
      background-color: #2196F3;
    }
  </style>

  <body>
    <h3 style="text-align: center">Working with Users</h3>
    
    <div class="center">
    
      <button class="btn info" onclick="window.location.href='jsp/adduserform.jsp'">
        Add User
      </button>
    
      <button class="btn info" onclick="window.location.href='jsp/viewusers.jsp'">
        View Users
      </button>
    </div>
  </body>

</html>
```
- **JSP2JDBC/src/main/java/org/shola/model/User.java** User Object acts as model template for the database
```java
package org.shola.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class User {

    private int id;
    private String name;
    private String email;
    private String gender;
    private String country;

}
```
- **JSP2JDBC/src/main/java/org/shola/model/UserDAO.java** This class acts has a link and interconversion/inter-working mechanism\
between the custom Java class('User') and rows-fields of the database table(Customers). It provides method for querying the\
database table, as well as inset, update and delete operations.
```java
/**
 * This class is link between the custom class (User) and the database table Customer. The fields of the table represents
 * the parameters/properties of the java class.
 * It provides interconversion and inter-working between the rows of the database table and objects of the custom class('User')
 * @Author Shola
 */


package org.shola.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

  public static Connection getConnection() {
    Connection conn = null;
    try {
      String url = "jdbc:mysql://localhost:3306/JSPDatabase";
      String user = "root"; //Connecting through root user is not good practice. A user account should exist for each app.
      String password = "Oloyin@@68";

      Class.forName("com.mysql.cj.jdbc.Driver");
      conn = DriverManager.getConnection(url, user, password);
      conn.setAutoCommit(true);
    } catch (Exception e) {
      System.err.print(e);
    }
    return conn;
  }

  public static List<User> getAllRecord(){
    //a list to hold the rows of the data from the database
    List<User> userList = new ArrayList<>();
    try{
      Connection conn = getConnection(); //this is a call to the method above
      PreparedStatement ps = conn.prepareStatement("select * from Customers");
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        //Instance of the User class and setting of its properties using the ResultSet
        User user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setGender(rs.getString("gender"));
        user.setCountry(rs.getString("country"));
        userList.add(user);
      }
      conn.close();
    }catch(Exception e){
      System.err.println(e.getMessage());
    }

    return userList;
  }

  public static User getRecordById(int id){

    User user = null;
    try{
      Connection conn = getConnection(); //this is a call to the method above
      PreparedStatement ps = conn.prepareStatement("select * from Customers where id = ?");
      ps.setInt(1, id);
      ResultSet rs = ps.executeQuery();
      while(rs.next()){
        //Instance of the User class and setting of its properties using the ResultSet
        user = new User();
        user.setId(rs.getInt("id"));
        user.setName(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setGender(rs.getString("gender"));
        user.setCountry(rs.getString("country"));
      }
      conn.close();
    }catch(Exception e){
      System.err.println(e.getMessage());
    }
    return user;
  }


  public static int addUser(User u){
    int status = 0;
    try{
      Connection conn = getConnection(); //this is a call to the method above

      PreparedStatement ps = conn.prepareStatement(
              "insert into Customers (name, email, gender, country) values(?,?,?,?)"
      );
      ps.setString(1, u.getName());
      ps.setString(2, u.getEmail());
      ps.setString(3, u.getGender());
      ps.setString(4, u.getCountry());

      status = ps.executeUpdate(); //this returns an integer representing the number of rows affected by the query
    }catch(Exception e){
      System.err.println(e);
    }
    return status;
  }


  public static int updateRecord(User u){

    int status = 0;
    try{
      Connection con = getConnection();
      PreparedStatement ps = con.prepareStatement("update Customers set name=?,email=?,gender=?,country=? where id=?");
      ps.setString(1, u.getName());
      ps.setString(2, u.getEmail());
      ps.setString(3, u.getGender());
      ps.setString(4, u.getCountry());
      ps.setInt(5, u.getId());
      status = ps.executeUpdate();
      con.close();

    }catch(Exception e){
      System.out.println(e);
    }
    return status;
  }

  public static int deleteUser(User usr){
    int status = 0;

    try{
      Connection conn = getConnection();
      PreparedStatement ps = conn.prepareStatement("delete from Customers where id =?");
      ps.setInt(1, usr.getId());
      status = ps.executeUpdate();
      conn.close();
    }catch(Exception e){
      System.err.println(e);
    }
    return status;
  }

}
```
**Displaying Data Using JSP Standard Tag Library**-->JSP2JDBC/src/main/webapp/jsp/viewusers.jsp
Prefix value of **c** distinguishes the JSTL tags from the core JSP tags.
```jsp
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <title>View Users</title>
        <meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
    </head>

    <body>
        <%--importing necesary custom classes--%>
        <%@ page import="org.shola.model.User,
                        org.shola.model.UserDAO,
                        java.util.*"
        %>

        <%--import of the standard tag library--%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <h2 style ="text-align: center">User Details</h2>


        <%
            List<User> us = UserDAO.getAllRecord();
            request.setAttribute("us", us);
        %>

        <table border="2" align="center"  class="center" style="background-color: #FEF9E7; width: 90%">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Country</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>

            <%--iterating over the list created above using c:forEach tag--%>
            <c:forEach items="${us}" var="us">

                <tr>
                    <td>${us.getId()}</td>
                    <td>${us.getName()}</td>
                    <td>${us.getEmail()}</td>
                    <td>${us.getGender()}</td>
                    <td>${us.getCountry()}</td>
                    <td>
                        <a href="${pageContext.request.contextPath}/jsp/edituserform.jsp?id=${us.getId()}">Edit</a>
                    </td>
                    <td>
                        <a href="${pageContext.request.contextPath}/jsp/deleteuser.jsp?id=${us.getId()}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
                <br/>
                <div>
<%--                    Note: 'window.location; refers to current location of the current file.--%>
                    <button class="btn info" onclick="window.location.href='adduserform.jsp'">
                        Add UserS
                    </button>
                </div>
        </table>

    </body>

</html>
```
**Adding New Data to the SQL Table(Working with _**jsp:useBean**_ and _**jsp:setProperty**_ Tags):**
- PracticeFolders/JSP2JDBC/src/main/webapp/jsp/adduserform.jsp
```jsp
<%@ page  language="java" contentType="text/html; ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add User</title>
    <meta http-equiv="Content-Type" content="text/html"; charset="ISO-8859-1">
</head>
<style>
    table, th, td{
        border: 2px solid black;
        padding: 5px
    }

    table.center{
       margin-left:auto;
        margin-right:auto;
    }
</style>

<body>
<h3 style="text-align: center">Add User</h3>

<div class="center">

    <form action="adduser.jsp" method="post">

        <table class="center" style="background-color: #FEF9E7; width: 50%;">

            <tr>
                <td>Name: </td>
                <td>
                    <input type="text" name="name">
                </td>
            </tr>

            <tr>
                <td>Email: </td>
                <td>
                    <input type="email" name="email">
                </td>
            </tr>

            <tr>
                <td>Sex: </td>
                <td>
                    <input type="radio" name="gender" value="M">Male
                    <input type="radio" name="gender" value="F">Female
                    <input type="radio" name="gender" value="NB">Non-binary

                </td>
            </tr>

            <tr>
                <td>Country: </td>
                <td>
                    <select name="country" style="width: 155px">
                        <option>Nigeria</option>
                        <option>Czechia</option>
                        <option>Germany</option>
                        <option>Poland</option>
                        <option>Other</option>
                    </select>
                </td>
            </tr>
        </table>
    <br> <input type="submit" value="submit">
    </form>
    <br>
    <br>
    <div>
        <button class="btn info" onclick="window.location.href='jsp/viewusers.jsp'">
            View Users
        </button>
    </div>
</div>
</body>

</html>
```
- A **bean** is a special type of Java class that conforms to the specifications of the Java Beans API and encapsulates a number of objects\
into a single object which is called the bean.
- The **adduserform.jsp** (above) sends the data filled in to the **adduser.jsp** (below). On adduser.jsp, **jsp:userBean** tag is used create\
an instance of the **User** class and **userInstance** is the variable containing the instantiated object. 
- All the parameters/properties of the instantiated object is set using the **jsp:setProperty**. The properties are set to the data passed on\
by the adduserform.jsp. 
- The userInstance is then passed as argument to UserDAO.addUser() for insertion into the database table.
- PracticeFolders/JSP2JDBC/src/main/webapp/jsp/adduser.jsp
```jsp
<%@ page import="org.shola.model.UserDAO" %>
<%@ page isELIgnored="false" %>

<%--JSP tags userBean and setProperty--%>
<jsp:useBean id="userInstance" class="org.shola.model.User"></jsp:useBean>
<jsp:setProperty name="userInstance" property="*"></jsp:setProperty>

<%
    int i = UserDAO.addUser(userInstance);
    if(i > 0){
        out.print("<h3 align=center> Success! The user has been added. </h3><br><br>");
    }else{
    out.print("<h3 align=center> Oh No...! There was an error when adding the user. </h3><br><br>");
    }
%>
<a href="${pageContext.request.contextPath}/jsp/viewusers.jsp">View Users</a>
```

**Updating the Database Table**
- The page **viewuser.jsp** has reference to **edituserform.jsp**:`<a href="${pageContext.request.contextPath}/jsp/edituserform.jsp?id=${us.getId()}">`
The link used the User method getId() to include the id of the User object in the reference link as query string parameter.
- PracticeFolders/JSP2JDBC/src/main/webapp/jsp/edituserform.jsp
```jsp
<!DOCTYPE html>
<html>
<head>
    <title>Edit User</title>
    <meta http-equiv="Content-Type" content="text/html" charset="ISO-8859-1">
</head>

<body>

<%@ page import="org.shola.model.User, org.shola.model.UserDAO" %>


<h2 style ="text-align: center">Edit User</h2>


<%
    String id = request.getParameter("id");
    User usr = UserDAO.getRecordById(Integer.parseInt(id));
%>

<h3>Edit Form</h3>
<form action="edituser.jsp" method="post">
    <input type="hidden" name="id" value="<%=usr.getId()%>" />
    <table>
        <tr>
            <td>Name: </td>
            <td><input type="text" name="name" value="<%= usr.getName()%>" />  </td>
        </tr>

        <tr>
            <td>Name: </td>
            <td><input type="text" name="email" value="<%= usr.getEmail()%>" />  </td>
        </tr>

        <tr>
            <td>Sex: </td>
            <td>
                <input type="radio" name="gender" value="M">Male
                <input type="radio" name="gender" value="F">Female
                <input type="radio" name="gender" value="NB">Non-binary
            </td>
        </tr>

        <tr>
            <td>Country: </td>
            <td>
                <select name="country" style="width: 155px">
                    <option>Nigeria</option>
                    <option>Czechia</option>
                    <option>Germany</option>
                    <option>Poland</option>
                    <option>Other</option>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit"  align="center" value="Edit User" /></td>
        </tr>
    </table>

</form>

</body>

</html>
```
- PracticeFolders/JSP2JDBC/src/main/webapp/jsp/edituser.jsp
```jsp
<%@ page isELIgnored="false" %>
<%@ page import="org.shola.model.UserDAO" %>


<%--NOTE: "usr" in useBean comes from edituserform.jsp--%>
<jsp:useBean id="usr" class="org.shola.model.User"></jsp:useBean>
<jsp:setProperty name="usr" property="*"></jsp:setProperty>


<%
    if(!(i>0)){

        out.print("Unable to update user:"+usr.getName()+", "+usr.getId()+", "+ usr.getEmail()+", "+usr.getGender()+", 
        "+usr.getCountry());
    }else {
        out.print("The following info user has been deleted:"+usr.getName()+", "+usr.getId()+", "+ usr.getEmail()+", 
        "+usr.getGender()+", "+usr.getCountry());
    }
%>

<a href="${pageContext.request.contextPath}/jsp/viewusers.jsp">View UserS</a>
```
**Deleting Row from the Database Table**
```jsp
<%@ page import ="org.shola.model.UserDAO" %>
<%@ page isELIgnored="false" %>
<jsp:useBean id="usr" class="org.shola.model.User"></jsp:useBean>
<jsp:setProperty name="usr" property="*"></jsp:setProperty>


<%
    int i = UserDAO.deleteUser(usr);

    if(!(i>0)){

        out.print("Unable to delete user."+"User with ID: "+usr.getId());
    }else {
        out.print("User with ID: ."+usr.getId()+", has been deleted.");
    }
%>
<a href="${pageContext.request.contextPath}/jsp/viewusers.jsp">View UserS</a>
```

# 14: Java Web Services: Getting Started with SOAP-based Web Services
- Services usually mean data and data transformation offering. The most wildly adopted technologies when building such services\
are SOAP and REST protocol. They allow the communication with external applications in a standardized scalable manner which also\
independent of their programming language. 
- The **JAX-WS** library can be used build a web service classes and methods which can be invoked by a remote client application.\
The built service can then be deployed to a URL endpoint from where clients can access it. 
- The project for the current course will be based on the same maven archetype from previous course: `org.apache.maven.archetypes:maven-archetype-webapp`
- Below is the section of pom file showing the dependencies:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>SOAPWebServices</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>SOAPWebServices Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <dependencies>

    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.11</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>com.sun.xml.ws</groupId>
      <artifactId>jaxws-rt</artifactId>
      <version>3.0.0</version>
    </dependency>
    
  </dependencies>

</project>
```

**(A) Defining a Web Service**
- A webservice can represent one endpoint in a web application. The webservice endpoint can have multiple web methods. A webservice\
can also be considered as a way to invoke a function. This function can be invoked from a client application which is entirely\
different from this server app(different from the location in which the application is located.)
- Before the webservice can be invoked from a client app, the webservice needs to be published to an endpoint. This can be done\
using a publisher class. The publisher class states where a webservice will be available as well as the webserice itself.
- A webservice class: **PracticeFolders/SOAPWebServices/src/main/java/com/shola/MyWebServiceImplementation.java**
```java
package com.shola;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
@WebService
public class MyWebServiceImplementation {

    @WebMethod
    public  String myGreetingMessage(String name){
        return String.format("Hello %s, Welcome to my first web service !", name);
    }
}
```
- A publisher class: **PracticeFolders/SOAPWebServices/src/main/java/com/shola/MyWebServicePublisher.java**
```java
package com.shola;

import jakarta.xml.ws.Endpoint;
public class MyWebServicePublisher {

    public static void main(String[] args){
        Endpoint.publish("http://localhost:9999/webservice/greeting", new MyWebServiceImplementation());
    }
}
```
- After running the publisher and navigating to the endpoint of the webservice the page is shown below. The **WSDL**(Web Service\
Description Language File) is a standard format for describing a web service. The WSDL file can be used to create a **client application**\
<img src="\images\webservice1.png"> \
The message element corresponds to the function/method defined within the Java webservice class. portType element points to the \
webservice class itself. (see wsdl file content below.)
```xml
<defintions>
  <types>
    <xsd:schema>
        <xsd:import namespace="http://shola.com/" schemaLocation="http://localhost:9999/webservice/greeting?xsd=1"/>
    </xsd:schema>
  </types>
  <message name="myGreetingMessage">
      <part name="parameters" element="tns:myGreetingMessage"/>
  </message>
  <message name="myGreetingMessageResponse">
      <part name="parameters" element="tns:myGreetingMessageResponse"/>
  </message>
  <portType name="MyWebServiceImplementation">
    <operation name="myGreetingMessage">
      <input wsam:Action="http://shola.com/MyWebServiceImplementation/myGreetingMessageRequest" 
             message="tns:myGreetingMessage"/>
      <output wsam:Action="http://shola.com/MyWebServiceImplementation/myGreetingMessageResponse" 
              message="tns:myGreetingMessageResponse"/>
    </operation>
  </portType>
  <binding name="MyWebServiceImplementationPortBinding" type="tns:MyWebServiceImplementation">
    <soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
    <operation name="myGreetingMessage">
    <soap:operation soapAction=""/>
    <input>
      <soap:body use="literal"/>
    </input>
    <output>
      <soap:body use="literal"/>
    </output>
    </operation>
  </binding>
  <service name="MyWebServiceImplementationService">
    <port name="MyWebServiceImplementationPort" binding="tns:MyWebServiceImplementationPortBinding">
    <soap:address location="http://localhost:9999/webservice/greeting"/>
    </port>
  </service>
</definitions>
```
**(B) Creating a Web Service Client Application**
- A new project(new Maven project SOAPWebServiceClient) is created to implement a client application: **PracticeFolders/SOAPWebServiceClient** 
- The webclient application requires a number of artifacts in order to enable its communication with a service using\
the SOAP protocol (ability to construct SOAP messages, send the constructed SOAP message to the webservice and then process any\
response from the SOAP-based webservice). This can be achieved using the `WSImport` from the `JAX WS` package. 
- To generate the **artifacts** required for this client application to communicate with the webservice:
1. cd to the appropriate directory of the clientservice app (i.e. PracticeFolders/SOAPWebServiceClient/src/main/java)
2. check if wsimport is available `which wsimport` ---> /usr/bin/wsimport. If available as shown, then proceed to 3 below.
3. run  `wsimport -keep -p client http://localhost:9999/webservice/greeting?wsdl` running above should populate the directory as shown below: These are java classes and byte codes. 
<img src="\images\generatedartifcats.png"> \
4. Add dependencies `jaxws-rt` and `jakarta.activation-api` to the pom.xml file of the client application. These dependencies
are same as the webservice application.
5. Create the consumer class inside the directory of the artifact(i.e. inside PracticeFolders/SOAPWebServiceClient/src/main/java/client).
The class is based on the **ServiceName** and **PortName** (see the picture from section A). In essence, the class from the
webservice is invoked or consumed remotely by the client application
```java
package client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class GreetingClient {

    public static void main(String [] args) throws Exception{
        //Client application is pointed to the URL of the wsdl file
        URL wslUrl = new URL("http://localhost:9999/webservice/greeting?wsdl");
        
        //QName means qualified name.It points to the spefic implemientation of a webservice using the Service Name
        QName qname = new QName("http://shola.com/", "MyWebServiceImplementationService");

        //Using the QName and the URL to create a Service object 
        Service service = Service.create(wslUrl, qname);

        //Service instance is used to get a proxy to the webs
        MyWebServiceImplementation myService = service.getPort(MyWebServiceImplementation.class);

        String greetAlice = myService.myGreetingMessage("Alice");
        System.out.println(greetAlice); //Hello Alice, Welcome to my first web service !
    }
}
```
**(C) Setting Up a Service Endpoint Interface**\
Instead of the web service exposing/providing class with methods, an interface can be provided instead. Any class that implements\
the defined interface(PracticeFolders/SOAPWebServices/src/main/java/com/shola/MyWebServiceInterface.java) will automatically become\
a webservice. Provision of an interface has the advantage of allowing different and several implementations. This design technique\
is known as Service Endpoint Interface(SEI).
- The interface: **PracticeFolders/SOAPWebServices/src/main/java/com/shola/MyWebServiceInterface.java**
```java
package com.shola;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

@WebService
public interface MyWebServiceInterface {

    @WebMethod
    String myGreetingMessage(String name);
}
```
- The implementing class: **PracticeFolders/SOAPWebServices/src/main/java/com/shola/MyWebServiceImplementation.java**
```java
package com.shola;

import jakarta.jws.WebService;

@WebService(endpointInterface = "com.shola.MyWebServiceInterface")
public class MyWebServiceImplementation implements MyWebServiceInterface{

    @Override
    public  String myGreetingMessage(String name){
        return String.format("Hello %s, Welcome to my first web service !. This is done through interface.", name);
    }
}
```
The portType in the WSDL file now points to `<portType name="MyWebServiceInterface">`. Hence, the client app need to be reflect\
this change. The `wsimport` command should be rerun on the client app to generate new artifact(WSDL etc.) and the consumer\
class modified to reflect the new portType name element.
```java
package client;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;

public class GreetingClient {

    public static void main(String [] args) throws Exception{
        URL wslUrl = new URL("http://localhost:9999/webservice/greeting?wsdl");

        QName qname = new QName("http://shola.com/", "MyWebServiceImplementationService");

        Service service = Service.create(wslUrl, qname);

        MyWebServiceInterface myService = service.getPort(MyWebServiceInterface.class);

        String greetAlice = myService.myGreetingMessage("Alice");
        System.out.println(greetAlice); //Hello Alice, Welcome to my first web service !
    }
}
```

## 15: Java Web Services: Integrating Web Services with a Database
This course will build on course 14. The aim is to create SOAP-based web service app for a fictitious book store which enable client\
apps to view, add, update and delete books whose details are stored in the database.
**(A) The Book Store Project**
A new maven project is created for the Book project **PracticeFolders/SOAP-BookStoreApp** with **jaxws-rt** and **jakarta.activation-api** added\
as dependencies.
- The Book class: **PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationentities/Book.java**
```java
package com.applicationentities;

//This will come handy when communicating book instances between server and client application
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

    //To keep track of the version and changes to the class before and after it has been serialized
    private static final long serialVersionUID = 1L;
    private String book_id;
    private String book_title;
    private String book_author;
    private float book_price;

    //Default no argument constructor is important for the 'jaxws' library. It will be first used to create a new book
    // instance and then the setter methods will be used to set the value for each book.
    public Book(){
        super();
    }

    public Book(String book_id, String book_title, String book_author, float book_price) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_price = book_price;
    }
}
```
- The Book data access object: **PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationdao/BookDAO.java**. This class\
will later be modified to include database connection and various database operations methods.
```java
package com.applicationdao;

import com.applicationentities.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDAO {

    public static List<Book> getAllBooks(){
        List<Book> result = new ArrayList<>();

        result.add(new Book("ISBN101", "The LameBook", "Zoe Zebra", (float) 7.3));
        result.add(new Book("ISBN102", "The River", "Emily Elephant", (float) 21.4));
        result.add(new Book("ISBN103", "What is this", "Pedro Pony", (float) 6.1));
        result.add(new Book("ISBN104", "Get Better", "Suzy Ship", (float) 8.5));
        return result;
    }
}
```
- The first goal is to make the class containing the book (BookDAO) available to a client via the webservice. Database will then \
be integrated later on. 
- A web service interface: **PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationservice/BookInterface.java**
```java
package com.applicationservice;

import com.applicationentities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;

import java.util.List;

@WebService //Any class that implements this interface will automatically become a webservice class. The only
public interface BookInterface {

  @WebMethod
  public List<Book> getAllBooks(); //The only contract is to return a List object containing the Book class.

}
```
- An implementing class(Web service): **PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationservice/BookServiceImplementation.java**\
The implementing class is a web service class. The web services needs a publisher class to make it available.
```java
package com.applicationservice;

import com.applicationdao.BookDAO;
import com.applicationentities.Book;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "com.applicationservice.BookServiceInterface")
public class BookServiceImplementation implements BookInterface{

    @Override
    public List<Book> getAllBooks(){
        return BookDAO.getAllBooks();
    }

}
```
- A publisher class: **PracticeFolders/SOAP-BookStoreApp/src/main/java/BookServicePublisher.java**
```java
import com.applicationservice.*;

import javax.xml.ws.Endpoint;

public class BookServicePublisher {
    public static void main(String[] args){
        try{
            Endpoint.publish("http://localhost:8899/books", new BookServiceImplementation());
            System.out.println("........The endpoint has been published successfully!");
        }catch(Exception e){
            System.err.println(e.getMessage());
        }
    }
}
```
The project structure is shown below:\
<img src="\images\SOAPBookProjectStructure1.png"> \

**(B) Writing a Client App to Retrieve Java objects**
The previous client application (SOAPWebService mini project from course 14) will be used to test and work the book store web service.\
The built artifacts from previous web service has been deleted from the SOAPWebservice client app. A new WSDL file corresponding to the current\
Book store web service by: 
1. running the command from src/java `wsimport -keep -p bookclient http://localhost:8899/books?wsdl` (- keep: keeps the generated \
artifacts, -p: create new package with name bookclient)
2. **Replace** all `javax.xml` with `jakarta.xml` and `javax.jws` with `jakarta.jws`. Replace all, **except** `javax.xml.namespace.QName;`
<img src="Replacement.png"> \
3. Create the client (consumer class): **PracticeFolders/SOAPWebServiceClient/src/main/java/bookclient/BookServiceClient.java**
```java
package bookclient;
import java.util.List;

public class BookServiceClient {

    public static void main(String[] args) {

        try {
            BookServiceImplementationService bookServiceImplementationService = new BookServiceImplementationService();
            BookServiceInterface bookServiceInterface = bookServiceImplementationService.getBookServiceImplementationPort();

            List<Book> listBook = bookServiceInterface.getAllBooks();
            for (Book b: listBook) {
                System.err.println(b.getBookAuthor());
                /*
                    Zoe Zebra
                    Emily Elephant
                    Pedro Pony
                    Suzy Ship    
                 */
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
**(C) Setting Up and Integrating a Database for the Web Service**
- **SQL :** creating a table and pre-populating the table.
```sql
-- create a database
create database BookService;

-- use the database
use BookService;

-- create Customer database table 
create table if not exists books(
 book_id varchar(20) primary key,
 book_title varchar(100),
 book_author varchar(100),
 book_price float
 );
 
insert into books values ('ISBN222', 'Muddy Puddles', 'Pepa Pig', 5.4); 
insert into books values ('ISBN101', 'The LameBook', 'Zoe Zebra', 7.3);
insert into books values ('ISBN102', 'The River', 'Emily Elephant', 21.4);
insert into books values ('ISBN103', 'What is this', 'Pedro Pony', 6.1);
insert into books values ('ISBN104', 'Get Better', 'Suzy Ship', 8.5);

select * from books
```
- Modification to pom.xml and the data access object:
**pom.xml:** Add the database driver dependency.
```xml
<dependencies>
   <dependency>
     <groupId>mysql</groupId>
     <artifactId>mysql-connector-java</artifactId>
     <version>8.0.22</version>
   </dependency>
</dependencies>
```
**PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationdao/BookDAO.java** \
```java
package com.applicationdao;

import com.applicationentities.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {


    private static Connection myDB = null;
    private static final String url = "jdbc:mysql://localhost:3306/BookService";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";


    private BookDAO(){}


    public static Connection getConnection(){
        try{
            if(myDB == null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                myDB = DriverManager.getConnection(url, username, password);
            }
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return myDB;

    }

    public static Book getBookById(String book_id){
        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("select * from books where book_id=?");
            ps.setString(1, book_id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Book bookInstance = new Book();
            bookInstance.setBook_id(rs.getString("book_id"));
            bookInstance.setBook_author(rs.getString("book_author"));
            bookInstance.setBook_title(rs.getString("book_title"));
            bookInstance.setBook_price(rs.getFloat("book_price"));

            return bookInstance;
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return null;
    }
    public static List<Book> getAllBooks(){
        List<Book> bookList = new ArrayList<>();

        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("select * from books");
            ResultSet rs = ps.executeQuery();


            while(rs.next()){

                Book bookInstance = new Book();
                bookInstance.setBook_id(rs.getString("book_id"));
                bookInstance.setBook_author(rs.getString("book_author"));
                bookInstance.setBook_title(rs.getString("book_title"));
                bookInstance.setBook_price(rs.getFloat("book_price"));

                bookList.add(bookInstance);
            }

            return bookList;

        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return null;
    }
}

```
- Modification to the BookServiceInterface and BookServiceImplementation to reflect the new(2nd) method in the BookDAO.
BookService interface: **PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationservice/BookServiceInterface.java**\
Note: getBookById() method in BookDAO requires a parameter. Hence, the need for the WebParam annotation in the corresponding method\
in the interface below.
```java
package com.applicationservice;

import com.applicationentities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;

@WebService
public interface BookServiceInterface {

    @WebMethod
    public List<Book> getAllBooks(); //The only contract is to return a List object containing the Book class.

    @WebMethod
    public Book getBookById(@WebParam(name="book_id") String book_id);
}
```
BookServiceImplementation class: **PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationservice/BookServiceImplementation.java**
```java
package com.applicationservice;

import com.applicationentities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface BookServiceInterface {

    @WebMethod
    public List<Book> getAllBooks(); //The only contract is to return a List object containing the Book class.

    @WebMethod
    public Book getBookById(@WebParam(name="book_id") String book_id);
}
```
- The WSDL file would change to include the new method after running the publisher class. This means that we need to rebuild the\
artifacts for the client application to make the new method accessible to the client application. The new consumer class of the client\
application is shown below with the call to the new method (getBookById()).
```java
package bookclient;

import java.util.List;

public class BookServiceClient {
    public static void main(String[] args) {
        try {

            BookServiceImplementationService bookServiceImplementationService = new BookServiceImplementationService();
            BookServiceInterface bookServiceInterface = bookServiceImplementationService.getBookServiceImplementationPort();

            List<Book> listBook = bookServiceInterface.getAllBooks();
            for (Book b: listBook) {
                System.err.println("BookId "+b.getBookId()+", Author: "+b.getBookAuthor()+", Title: "+b.getBookTitle());
                /*
                BookId ISBN101, Author: Zoe Zebra, Title: The LameBook
                BookId ISBN102, Author: Emily Elephant, Title: The River
                BookId ISBN103, Author: Pedro Pony, Title: What is this
                BookId ISBN104, Author: Suzy Ship, Title: Get Better
                BookId ISBN107, Author: Emily Elephant, Title: Animal World
                BookId ISBN222, Author: Pepa Pig, Title: Muddy Puddles
                 */
            }

            Book singleBook = bookServiceInterface.getBookById("ISBN101");
            System.out.println("BookId "+singleBook.getBookId()+", Author: "+singleBook.getBookAuthor()+", Title: "+singleBook.getBookTitle());
            //BookId ISBN101, Author: Zoe Zebra, Title: The LameBook
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
**(D) Incorporating Add/Insert Operation to the Web Service**
The previous communication between the client and the web service has been from the web service to the client. This section deals\
with the transmission of data from the client application to the web service application.  client application sends a request\
for the addition of new book to the database.
- Updated BookDAO, new method addBook(): **PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationdao/BookDAO.java** 
```java
package com.applicationdao;

import com.applicationentities.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {


    private static Connection myDB = null;
    private static final String url = "jdbc:mysql://localhost:3306/BookService";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";


    private BookDAO(){}


    public static Connection getConnection(){
        try{
            if(myDB == null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                myDB = DriverManager.getConnection(url, username, password);
            }
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return myDB;

    }

    public static Book getBookById(String book_id){
        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("select * from books where book_id=?");
            ps.setString(1, book_id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Book bookInstance = new Book();
            bookInstance.setBook_id(rs.getString("book_id"));
            bookInstance.setBook_author(rs.getString("book_author"));
            bookInstance.setBook_title(rs.getString("book_title"));
            bookInstance.setBook_price(rs.getFloat("book_price"));

            return bookInstance;
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return null;
    }
    public static List<Book> getAllBooks(){
        List<Book> bookList = new ArrayList<>();

        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("select * from books");
            ResultSet rs = ps.executeQuery();


            while(rs.next()){

                Book bookInstance = new Book();
                bookInstance.setBook_id(rs.getString("book_id"));
                bookInstance.setBook_author(rs.getString("book_author"));
                bookInstance.setBook_title(rs.getString("book_title"));
                bookInstance.setBook_price(rs.getFloat("book_price"));

                bookList.add(bookInstance);
            }

            return bookList;

        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return null;
    }

    public static String addBook(Book book){
        int status =0;
        String responseMessage = "";

        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement(
                    "insert into books (book_id, book_title, book_author, book_price) values(?,?,?,?)");

            ps.setString(1, book.getBook_id());
            ps.setString(2, book.getBook_title());
            ps.setString(3, book.getBook_author());
            ps.setFloat(4, book.getBook_price());

            status = ps.executeUpdate();

            String successMsg = "Book added successfully: "+book.getBook_id();
            String errorMsg = "Error adding book: "+book.getBook_id();

            //Note the conditional assignment of responseMessage below
            responseMessage = (status ==1) ? successMsg : errorMsg;

        }catch(Exception e){
            e.printStackTrace();
        }
        
        System.err.println("Returning response message: "+responseMessage);
        return responseMessage;
    }
}
```
- Updated BookServiceInterface: **PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationservice/BookServiceInterface.java**
```java
package com.applicationservice;

import com.applicationentities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface BookServiceInterface {

  @WebMethod
  public List<Book> getAllBooks(); //The only contract is to return a List object containing the Book class.

  @WebMethod
  public Book getBookById(@WebParam(name = "book_id") String book_id);

  @WebMethod
  public String addBook(Book book);
}
```
- Updated BookServiceImplementation class: **PracticeFolders/SOAP-BookStoreApp/src/main/java/com/applicationservice/BookServiceImplementation.java**
```java
package com.applicationservice;

import com.applicationdao.BookDAO;
import com.applicationentities.Book;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "com.applicationservice.BookServiceInterface")
public class BookServiceImplementation implements BookServiceInterface{
    @Override
    public List<Book> getAllBooks(){
        return BookDAO.getAllBooks();
    }

    @Override
    public Book getBookById(String book_id ){
        return BookDAO.getBookById(book_id);
    }

    @Override
    public String addBook(Book book){
        return BookDAO.addBook(book);
    }
}
```

- Update to the consumer class of the client:**PracticeFolders/SOAPWebServiceClient/src/main/java/bookclient/BookServiceClient.java**
The artifacts for the client application must be regenerated. 
```java
package bookclient;

import java.util.List;

public class BookServiceClient {
    public static void main(String[] args) {
        try {

            BookServiceImplementationService bookServiceImplementationService = new BookServiceImplementationService();
            BookServiceInterface bookServiceInterface = bookServiceImplementationService.getBookServiceImplementationPort();

            List<Book> listBook = bookServiceInterface.getAllBooks();
            for (Book b: listBook) {
                System.err.println("BookId "+b.getBookId()+", Author: "+b.getBookAuthor()+", Title: "+b.getBookTitle());
                /*
                BookId ISBN101, Author: Zoe Zebra, Title: The LameBook
                BookId ISBN102, Author: Emily Elephant, Title: The River
                BookId ISBN103, Author: Pedro Pony, Title: What is this
                BookId ISBN104, Author: Suzy Ship, Title: Get Better
                BookId ISBN107, Author: Emily Elephant, Title: Animal World
                BookId ISBN222, Author: Pepa Pig, Title: Muddy Puddles
                 */
            }

            Book singleBook = bookServiceInterface.getBookById("ISBN101");
            System.out.println("BookId "+singleBook.getBookId()+", Author: "+singleBook.getBookAuthor()+", Title: "+singleBook.getBookTitle());
            //BookId ISBN101, Author: Zoe Zebra, Title: The LameBook


            //Instantiating a new object of the Book class
            Book newBookInstance = new Book();
            newBookInstance.setBookId("ISBNXYZ");
            newBookInstance.setBookAuthor("Tomas Jarda");
            newBookInstance.setBookTitle("We Created Java");
            newBookInstance.setBookPrice(57575.2f);
            
            //Invoking the add operation from the web service and passing the instantiated book object
            String bookAddOperationMessage = bookServiceInterface.addBook(newBookInstance);
            System.err.println(bookAddOperationMessage); //Book added successfully: ISBNXYZ

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
**(E) Incorporating Delete and Update Operations to the Web Service**
- Updated **BookDAO**:
```java
package com.applicationdao;

import com.applicationentities.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {


    private static Connection myDB = null;
    private static final String url = "jdbc:mysql://localhost:3306/BookService";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";


    private BookDAO(){}


    public static Connection getConnection(){
        try{
            if(myDB == null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                myDB = DriverManager.getConnection(url, username, password);
            }
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return myDB;

    }

    public static Book getBookById(String book_id){
        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("select * from books where book_id=?");
            ps.setString(1, book_id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Book bookInstance = new Book();
            bookInstance.setBook_id(rs.getString("book_id"));
            bookInstance.setBook_author(rs.getString("book_author"));
            bookInstance.setBook_title(rs.getString("book_title"));
            bookInstance.setBook_price(rs.getFloat("book_price"));

            return bookInstance;
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return null;
    }
    public static List<Book> getAllBooks(){
        List<Book> bookList = new ArrayList<>();

        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("select * from books");
            ResultSet rs = ps.executeQuery();


            while(rs.next()){

                Book bookInstance = new Book();
                bookInstance.setBook_id(rs.getString("book_id"));
                bookInstance.setBook_author(rs.getString("book_author"));
                bookInstance.setBook_title(rs.getString("book_title"));
                bookInstance.setBook_price(rs.getFloat("book_price"));

                bookList.add(bookInstance);
            }

            return bookList;

        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return null;
    }

    public static String addBook(Book book){
        int status =0;
        String responseMessage = "";

        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement(
                    "insert into books (book_id, book_title, book_author, book_price) values(?,?,?,?)");

            ps.setString(1, book.getBook_id());
            ps.setString(2, book.getBook_title());
            ps.setString(3, book.getBook_author());
            ps.setFloat(4, book.getBook_price());

            status = ps.executeUpdate();

            String successMsg = "Book added successfully: "+book.getBook_id();
            String errorMsg = "Error adding book: "+book.getBook_id();

            //Note the conditional assignment of responseMessage below
            responseMessage = (status ==1) ? successMsg : errorMsg;

        }catch(Exception e){
            e.printStackTrace();
        }
        System.err.println("Returning response message: "+responseMessage);
        return responseMessage;
    }


    public static String removeBook(String book_id){
        int status = 0;
        String responseMessage = "";
        try {
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("delete from books where book_id =?");
            ps.setString(1, book_id);

            status = ps.executeUpdate();

            String successMsg = "Book removed successfully: " + book_id;
            String errorMsg = "Error deleting book: " + book_id;

            responseMessage = (status == 1) ? successMsg : errorMsg;

        }catch(Exception e){
            e.printStackTrace();
        }
        System.err.println("Returning response message: "+responseMessage);
        return responseMessage;
    }


    public static String updateBook(Book book){
        int status = 0;
        String responseMessage = "";
        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("update books set book_title =?, book_author=?, book_price=? where book_id=?");
            ps.setString(1, book.getBook_title());
            ps.setString(2, book.getBook_author());
            ps.setFloat(3, book.getBook_price());
            ps.setString(4, book.getBook_id());

            status = ps.executeUpdate();

            String successMsg = "Book updated successfully: " + book.getBook_id();
            String errorMsg = "Error updating book: " + book.getBook_id();

            responseMessage = (status == 1) ? successMsg : errorMsg;

        }catch(Exception e){
            e.printStackTrace();
        }

        System.err.println("Returning response message: "+ responseMessage);
        return responseMessage;
    }
}
```

- Updated **BookServiceInterface**:
```java
package com.applicationservice;

import com.applicationentities.Book;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;

import java.util.List;

@WebService
public interface BookServiceInterface {

    @WebMethod
    public List<Book> getAllBooks(); //The only contract is to return a List object containing the Book class.

    @WebMethod
    public Book getBookById(@WebParam(name="book_id") String book_id);

    @WebMethod
    public String addBook(@WebParam(name="book") Book book);

    @WebMethod
    public String removeBook(@WebParam(name="book_id") String book_id);

    @WebMethod
    public String updateBook(@WebParam(name="book") Book book);
}
```
- Updated **BookServiceImplementation**:
```java
package com.applicationservice;

import com.applicationdao.BookDAO;
import com.applicationentities.Book;
import jakarta.jws.WebService;

import java.util.List;

@WebService(endpointInterface = "com.applicationservice.BookServiceInterface")
public class BookServiceImplementation implements BookServiceInterface{
    @Override
    public List<Book> getAllBooks(){
        return BookDAO.getAllBooks();
    }

    @Override
    public Book getBookById(String book_id ){
        return BookDAO.getBookById(book_id);
    }

    @Override
    public String addBook(Book book){
        return BookDAO.addBook(book);
    }

    @Override
    public String removeBook(String book_id){
        return BookDAO.removeBook(book_id);
    }

    @Override
    public String updateBook(Book book){
        return BookDAO.updateBook(book);
    }
}
```
- The WSD file showing all available web service methods and other details:
```xml
<!--  Published by JAX-WS RI (https://github.com/eclipse-ee4j/metro-jax-ws). 
RI's version is JAX-WS RI 3.0.0 git-revision#af8101a.  -->
<!--  Generated by JAX-WS RI (https://github.com/eclipse-ee4j/metro-jax-ws). 
RI's version is JAX-WS RI 3.0.0 git-revision#af8101a.  -->
<definitions xmlns:wsu="http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd" 
             xmlns:wsp="http://www.w3.org/ns/ws-policy" xmlns:wsp1_2="http://schemas.xmlsoap.org/ws/2004/09/policy" 
             xmlns:wsam="http://www.w3.org/2007/05/addressing/metadata" xmlns:soap="http://schemas.xmlsoap.org/wsdl/soap/" 
             xmlns:tns="http://applicationservice.com/" xmlns:xsd="http://www.w3.org/2001/XMLSchema" 
             xmlns="http://schemas.xmlsoap.org/wsdl/" targetNamespace="http://applicationservice.com/" 
             name="BookServiceImplementationService">
<types>
<xsd:schema>
<xsd:import namespace="http://applicationservice.com/" schemaLocation="http://localhost:8899/shola?xsd=1"/>
</xsd:schema>
</types>
<message name="updateBook">
<part name="parameters" element="tns:updateBook"/>
</message>
<message name="updateBookResponse">
<part name="parameters" element="tns:updateBookResponse"/>
</message>
<message name="getBookById">
<part name="parameters" element="tns:getBookById"/>
</message>
<message name="getBookByIdResponse">
<part name="parameters" element="tns:getBookByIdResponse"/>
</message>
<message name="addBook">
<part name="parameters" element="tns:addBook"/>
</message>
<message name="addBookResponse">
<part name="parameters" element="tns:addBookResponse"/>
</message>
<message name="removeBook">
<part name="parameters" element="tns:removeBook"/>
</message>
<message name="removeBookResponse">
<part name="parameters" element="tns:removeBookResponse"/>
</message>
<message name="getAllBooks">
<part name="parameters" element="tns:getAllBooks"/>
</message>
<message name="getAllBooksResponse">
<part name="parameters" element="tns:getAllBooksResponse"/>
</message>
<portType name="BookServiceInterface">
<operation name="updateBook">
<input wsam:Action="http://applicationservice.com/BookServiceInterface/updateBookRequest" message="tns:updateBook"/>
<output wsam:Action="http://applicationservice.com/BookServiceInterface/updateBookResponse" message="tns:updateBookResponse"/>
</operation>
<operation name="getBookById">
<input wsam:Action="http://applicationservice.com/BookServiceInterface/getBookByIdRequest" message="tns:getBookById"/>
<output wsam:Action="http://applicationservice.com/BookServiceInterface/getBookByIdResponse" message="tns:getBookByIdResponse"/>
</operation>
<operation name="addBook">
<input wsam:Action="http://applicationservice.com/BookServiceInterface/addBookRequest" message="tns:addBook"/>
<output wsam:Action="http://applicationservice.com/BookServiceInterface/addBookResponse" message="tns:addBookResponse"/>
</operation>
<operation name="removeBook">
<input wsam:Action="http://applicationservice.com/BookServiceInterface/removeBookRequest" message="tns:removeBook"/>
<output wsam:Action="http://applicationservice.com/BookServiceInterface/removeBookResponse" message="tns:removeBookResponse"/>
</operation>
<operation name="getAllBooks">
<input wsam:Action="http://applicationservice.com/BookServiceInterface/getAllBooksRequest" message="tns:getAllBooks"/>
<output wsam:Action="http://applicationservice.com/BookServiceInterface/getAllBooksResponse" message="tns:getAllBooksResponse"/>
</operation>
</portType>
<binding name="BookServiceImplementationPortBinding" type="tns:BookServiceInterface">
<soap:binding transport="http://schemas.xmlsoap.org/soap/http" style="document"/>
<operation name="updateBook">
<soap:operation soapAction=""/>
<input>
<soap:body use="literal"/>
</input>
<output>
<soap:body use="literal"/>
</output>
</operation>
<operation name="getBookById">
<soap:operation soapAction=""/>
<input>
<soap:body use="literal"/>
</input>
<output>
<soap:body use="literal"/>
</output>
</operation>
<operation name="addBook">
<soap:operation soapAction=""/>
<input>
<soap:body use="literal"/>
</input>
<output>
<soap:body use="literal"/>
</output>
</operation>
<operation name="removeBook">
<soap:operation soapAction=""/>
<input>
<soap:body use="literal"/>
</input>
<output>
<soap:body use="literal"/>
</output>
</operation>
<operation name="getAllBooks">
<soap:operation soapAction=""/>
<input>
<soap:body use="literal"/>
</input>
<output>
<soap:body use="literal"/>
</output>
</operation>
</binding>
<service name="BookServiceImplementationService">
<port name="BookServiceImplementationPort" binding="tns:BookServiceImplementationPortBinding">
<soap:address location="http://localhost:8899/shola"/>
</port>
</service>
</definitions>

```
- Updated consumer class of the client application:
```java
package bookclient;

import java.util.List;

public class BookServiceClient {
    public static void main(String[] args) {
        try {

            BookServiceImplementationService bookServiceImplementationService = new BookServiceImplementationService();
            BookServiceInterface bookServiceInterface = bookServiceImplementationService.getBookServiceImplementationPort();

            //SELECT OPERATION
            List<Book> listBook = bookServiceInterface.getAllBooks();
            for (Book b: listBook) {
                System.err.println("BookId "+b.getBookId()+", Author: "+b.getBookAuthor()+", Title: "+b.getBookTitle());
                /*
                BookId ISBN101, Author: Zoe Zebra, Title: The LameBook
                BookId ISBN102, Author: Emily Elephant, Title: The River
                BookId ISBN103, Author: Pedro Pony, Title: What is this
                BookId ISBN104, Author: Suzy Ship, Title: Get Better
                BookId ISBN107, Author: Emily Elephant, Title: Animal World
                BookId ISBN222, Author: Pepa Pig, Title: Muddy Puddles
                 */
            }

            Book singleBook = bookServiceInterface.getBookById("ISBN101");
            System.out.println("BookId "+singleBook.getBookId()+", Author: "+singleBook.getBookAuthor()+", Title: "+singleBook.getBookTitle());
            //BookId ISBN101, Author: Zoe Zebra, Title: The LameBook


            //INSERT OPERATION
            //Instantiating a new object of the Book class
            Book newBookInstance = new Book();
            newBookInstance.setBookId("ISBNXYZ");
            newBookInstance.setBookAuthor("Tomas Jarda");
            newBookInstance.setBookTitle("We Created Java");
            newBookInstance.setBookPrice(57575.2f);
            //Invoking the add operation from the web service and passing the instantiated book object
            String bookAddOperationMessage = bookServiceInterface.addBook(newBookInstance);
            System.err.println(bookAddOperationMessage); //Book added successfully: ISBNXYZ


           // DELETE OPERATION
            String bookDeleteOperationMessage = bookServiceInterface.removeBook("ISBN101");
            System.err.println(bookDeleteOperationMessage); //Book removed successfully: ISBN101


            //UPDATE OPERATION
            Book singleBook2 = bookServiceInterface.getBookById("ISBN102");
            System.err.println(singleBook2.getBookAuthor());
            singleBook2.setBookTitle("Java To Infinity 2");
            singleBook2.setBookPrice((float) 1500);
            String bookUpdateOperationMessage = bookServiceInterface.updateBook(singleBook2);
            System.err.println(bookUpdateOperationMessage); //Book updated successfully: ISBN102


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
```
**(F) Publishing the Web Service to Tomcat Server**
The previous section publishes the web service to an Endpoint publish server(i.e.we used the lightweight server from the
API `jakarta.xml.ws.Endpoint`) This section is devoted to publishing the web service to a Tomcats server. This involves packaging the \
web service as WAR file and deploying to Tomcats.
1. Confirm that the pom file has war packaging. 
2. Create config file: **PracticeFolders/SOAP-BookStoreApp/src/main/webapp/WEB-INF/sun-jaxws.xml**
```xml
<?xml version="1.0" encoding="UTF-8" ?>
<endpoints xmlns="http://java.sun.com/xml/ns/jax-ws/ri/runtime" version="2.0">
    <endpoint
        name="BookServiceEndPoint"
        implementation="com.applicationservice.BookServiceImplementation"
        url-patter="/ws/bookservice/" />
    
</endpoints>
```
3. Modify the web.xml to add the listener, servlet and servlet-mapping elements:
```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Book Store</display-name>
  
  <listener>
    <listener-class>com.sun.xml.ws.transport.http.servlet.WSServletContextListener</listener-class>
  </listener>
  
  <servlet>
    <servlet-name>BookServiceServlet</servlet-name>
    <servlet-class>com.sun.xml.ws.transport.http.servlet.WSServlet</servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  
  <servlet-mapping>
    <servlet-name>BookServiceServlet</servlet-name>
    <url-pattern>/ws/*</url-pattern>
  </servlet-mapping>
  
  
</web-app>
```
4. Run `mvn clean package` from project directory
5. Copy/deploy the generated war file to the webapp directory of Tomcat: `cp SOAP-BookStoreApp.war /mnt/c/apachetomcat10/webapps`
6. localhost:7777/SOAP-BookStoreApp/wsl/



## 16: Java Web Services: Building REST APIs
**(A) Configuring a RESTful Application and Defining REST API Method**\
REST protocol is often considered a newer version of SOAP protocol. However, SOAP is protocol, while REST is an architectural\
style which can implement SOAP as the underlying protocol. \
The path for this course sample project/source code: **PracticeFolders/JavaRESTAPI.**
1. The pom.xml file of the project is modified to add the necessary dependencies. A section of the pom file is shown below:
```xml
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>JavaRESTAPI</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>war</packaging>

  <name>JavaRESTAPI Maven Webapp</name>
  <!-- FIXME change it to the project's website -->
  <url>http://www.example.com</url>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <maven.compiler.source>1.7</maven.compiler.source>
    <maven.compiler.target>1.7</maven.compiler.target>
  </properties>

  <dependencies>
    
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.1</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.glassfish.jersey.containers</groupId>
      <artifactId>jersey-container-servlet</artifactId>
      <version>3.0.1</version>
    </dependency>

    <dependency>
      <groupId>org.glassfish.jersey.inject</groupId>
      <artifactId>jersey-hk2</artifactId>
      <version>3.0.1</version>
    </dependency>

    <dependency>
      <groupId>jakarta.activation</groupId>
      <artifactId>jakarta.activation-api</artifactId>
      <version>2.0.0</version>
    </dependency>
    
  </dependencies>

</project>
```
The **Jersey** framework is used to build the RESTful application. The framework itself is an implementation of the **JAX RS API** specification.\
The jersey framework consists of several libraries, and we are making use of `jersey-container-servlet`(which enables the deployment\
of the application in a servlet container. i.e. to enable it to be hosted for example in Apache Tomcat server). `jersey-hk2` is the \
dependency injection system provided by the jersey framework. \
2. Configuring the web.xml file: **PracticeFolders/JavaRESTAPI/src/main/webapp/WEB-INF/web.xml**: The `<param-value>` element \
corresponds to the custom package name of the REST API app that contains java classes. Check section 3 below.
```xml
<!DOCTYPE web-app PUBLIC
 "-//Sun Microsystems, Inc.//DTD Web Application 2.3//EN"
 "http://java.sun.com/dtd/web-app_2_3.dtd" >

<web-app>
  <display-name>Java REST API</display-name>
  
  <servlet>
    <servlet-name>My JAX-RS Servlet</servlet-name>
    <servlet-class>org.glassfish.jersey.servlet.ServletContainer</servlet-class>
    <init-param>
      <param-name>jersey.config.server.provider.packages</param-name>
<!--      This is the custom java package that corresponds to the custom classes in this REST API application-->
      <param-value>com.jaxrswebservice</param-value>
    </init-param>
    <load-on-startup>1</load-on-startup>
  </servlet>
  
  <servlet-mapping>
    <servlet-name>My JAX-RS Servlet</servlet-name>
    <url-pattern>/*</url-pattern>
  </servlet-mapping>
</web-app>
```
3. A simple REST API method: **PracticeFolders/JavaRESTAPI/src/main/java/com/jaxrswebservice/HelloWorldService.java**
```java
package com.jaxrswebservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/greeting")
public class HelloWorldService {

  @GET
  public String myGreeting(){
    return "Welcome to the world of REST APIs in Java--by Shola";
  }
}

```
4. Start Tomcat server: `./startup.sh && tail -f ../logs/catalina.out`.
5. Generate the war file and deploy it to Tomcat server: `mvn clean package` and `cp JavaRESTAPI.war /mnt/c/apachetomcat10/webapps`\
To unpack the war file, run:`tar tvf target/JavaRESTAPI.war ` 
6. Access the API class/method at **http://localhost:7777/JavaRESTAPI/greeting**  
7. Alternatively, the API can be accessed using curl command :\
View response body: `curl http://localhost:7777/JavaRESTAPI/greeting` \
View response header: `curl -I http://localhost:7777/JavaRESTAPI/greeting` OR `curl -I http://localhost:7777/JavaRESTAPI/ws/greeting`
```
HTTP/1.1 200 
Content-Type: text/plain                                                                                        
Content-Length: 51                                                                                              
Date: Sat, 17 Sep 2022 18:18:40 GMT
```
**Defining Separate URL path for Individual Methods Within a Class**
```java
package com.jaxrswebservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;

@Path("/ws")
public class HelloWorldService {

    @GET
    @Path("/greeting")
    //The method now return a Response object. The object have the 'message' incorporated within it.
    public Response myGreeting(){
        System.err.println("Welcome");
        //Unlike the previous implementation, String "Welcome..." is sent as part of a body of response.
        String message= "Welcome to the world of REST APIs in Java--by Shola";
        return Response.status(200).entity(message).build();
    }
}
```
Now the method is accessible at: `http://localhost:7777/JavaRESTAPI/ws/greeting`

**(B) Building Responses to HTTP Requests**
1. Handling **request parameters** that is passed along with the URL path
```java
package com.jaxrswebservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("/ws")
public class HelloWorldService {

    @GET
    @Path("/greeting")
    //The method now return a Response object. The object have the 'message' incorporated within it.
    public Response myGreeting(){
        System.err.println("Welcome");
        //Unlike the previous implementation, String "Welcome..." is sent as part of a body of response.
        String message= "Welcome to the world of REST APIs in Java--by Shola";
        //Construction of the response object
        return Response.status(200).entity(message).build();
    }

    //The method parameter is obtained from the request parameter that comes from the URL
    @GET
    @Path("/greeting/{param}")
    public Response myGreeting(@PathParam("param") String name){
        String personalizedMessage = String.format("Hello %s !. Welcome to REST APIs in Java. %nAuthor: Shola", name);
        //construction of the response obje ct
        return Response.status(200).entity(personalizedMessage).build();
    }
    /*
    Sample URL: http://localhost:7777/JavaRESTAPI/ws/greeting/Zoe Zebra
    Sample output: Hello Zoe Zebra !. Welcome to REST APIs in Java. Author: Shola
     */
}
```
Curl command and response:
```
curl -I http://localhost:7777/JavaRESTAPI/ws/greeting/Zoe Zebra

HTTP/1.1 200 
Content-Type: text/plain
Content-Length: 57
Date: Sun, 18 Sep 2022 16:46:12 GMT
```
2. Setting the **Content-Type** of the Response \
The previous curl header command shows that the Content-Type returned by the response is text/plain. The response can be modified\
to return other contents such as HTML and JSON. This can be done using the `jakarta.ws.rs.core.MediaType` and `jakarta.ws.rs.Produces` APIs.\
Setting the media type to HTML means we can use html tags within the response definition. 
```java
package com.jaxrswebservice;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType; //Content-Type is defined in terms of a media type
import jakarta.ws.rs.Produces; //An annotation that allows us to define the Content-Type produced by the HTTP method

@Path("/ws")
public class HelloWorldService {

    @GET
    @Path("/greeting")
    @Produces({MediaType.TEXT_PLAIN})
    //The method now return a Response object. The object have the 'message' incorporated within it.
    public Response myGreeting(){
        System.err.println("Welcome");
        //Unlike the previous implementation, String "Welcome..." is sent as part of a body of response.
        String message= "Welcome to the world of REST APIs in Java--by Shola";
        //Construction of the response object
        return Response.status(200).entity(message).build();
    }

    //The method parameter is obtained from the request parameter that comes from the URL
    @GET
    @Path("/greeting/{param}")
    @Produces(MediaType.TEXT_HTML)
    public Response myGreeting(@PathParam("param") String name){
        
        String personalizedMessage = String.format("<p>Hello <b>%s </b>!. Welcome to REST APIs in Java.</p> " +
                "Author: Shola", name);
        //construction of the response obje ct
        return Response.status(200).entity(personalizedMessage).build();
    }
}
```
**(C) Transmitting Java Objects Over HTTP**\
The content, structure and purpose of the proceeding packages and classes is the same as the SOAP course. The aim is to create a RESTful\
web service for a fictitious book store. The later part of the course will be devoted to integrating a database to the application. 
- Custom Book class: **PracticeFolders/JavaRESTAPI/src/main/java/com/applicationentities/Book.java**
```java
package com.applicationentities;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class Book implements Serializable {

    private static final long serialversionUID = 1L;

    private String book_id;
    private String book_title;
    private String book_author;
    private float book_price;

    public Book(){
        super();
    }

    public Book(String book_id, String book_title, String book_author, float book_price) {
        this.book_id = book_id;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_price = book_price;
    }
}
```
- Data Access Object class for Book class: **PracticeFolders/JavaRESTAPI/src/main/java/com/applicationdao/BookDAO.java**
```java
package com.applicationdao;

import com.applicationentities.Book;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {

    public static List<Book> getAllBooks(){

            List<Book> result = new ArrayList<>();

            result.add(new Book("ISBN101", "The LameBook", "Zoe Zebra", (float) 7.3));
            result.add(new Book("ISBN102", "The River", "Emily Elephant", (float) 21.4));
            result.add(new Book("ISBN103", "What is this", "Pedro Pony", (float) 6.1));
            result.add(new Book("ISBN104", "Get Better", "Suzy Ship", (float) 8.5));
            return result;
    }
}
```
- Service class inside the package,jaxrswebservice:**PracticeFolders/JavaRESTAPI/src/main/java/com/jaxrswebservice/BookService.java**
```java
package com.jaxrswebservice;

import com.applicationdao.BookDAO;
import com.applicationentities.Book;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/bookservice")
public class BookService {
    @GET
    @Path("/getbooks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBook() {
        List<Book> listOfBooks = BookDAO.getAllBooks();

        return Response.status(Response.Status.OK).entity(listOfBooks).build();
    }
}
```
The `jersey-media-json-jackson` library is required as a dependency to enable jersey framework to convert objects of a custom\
class(e.g. Book class) to a JSON format (and vice-versa).
```xml
<dependencies>
    <dependency>
      <groupId>junit</groupId>
      <artifactId>junit</artifactId>
      <version>4.13.1</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>javax.servlet</groupId>
      <artifactId>javax.servlet-api</artifactId>
      <version>4.0.1</version>
      <scope>provided</scope>
    </dependency>
  
    <dependency>
      <groupId>org.glassfish.jersey.containers</groupId>
      <artifactId>jersey-container-servlet</artifactId>
      <version>3.0.1</version>
    </dependency>

    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.24</version>
    </dependency>

    <dependency>
      <groupId>org.glassfish.jersey.inject</groupId>
      <artifactId>jersey-hk2</artifactId>
      <version>3.0.1</version>
    </dependency>

    <dependency>
      <groupId>jakarta.activation</groupId>
      <artifactId>jakarta.activation-api</artifactId>
      <version>2.0.0</version>
    </dependency>

    <dependency>
      <groupId>org.glassfish.jersey.media</groupId>
      <artifactId>jersey-media-json-jackson</artifactId>
      <version>3.0.1</version>
    </dependency>
</dependencies>
```
The class and its web methods are available at: http://localhost:7777/JavaRESTAPI/bookservice/getbooks \
Pretty print to the **terminal**:\
install jq: `pip install jq` OR `sudo apt install jq`
access the endpoint and pipe it to jq : `curl http://localhost:7777/JavaRESTAPI/bookservice/getbooks | jq` \

## 17: Java Web Services: Enabling CRUD Operations with REST APIs 
CRUD: Create, Read, Update and Delete Operations. The goal is to continue from previous course and integrate the built web service \
with a relational database in order to perform. \
**(A) Integrating a RESTful App with a Database** \
The same database table  (books) created on MySQL used for SOAP API will be used (BookService). The pom.xml of the current course source code\
is modified to add the `mysql-connector-java library`. The implementation of the BookDAO is very much the same as the SOAP API application. \
The complete class is shown below and various methods of the class will be used in succession.
- Book data access object : **PracticeFolders/JavaRESTAPI/src/main/java/com/applicationdao/BookDAO.java**
```java
package com.applicationdao;

import com.applicationentities.Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {


    private static Connection myDB = null;
    private static final String url = "jdbc:mysql://localhost:3306/BookService";
    private static final String username = "root";
    private static final String password = "Oloyin@@68";

    private BookDAO(){}

    public static Connection getConnection(){
        try{
            if(myDB == null){
                Class.forName("com.mysql.cj.jdbc.Driver"); //registering msql driver
                myDB = DriverManager.getConnection(url, username, password);
            }
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return myDB;

    }

    public static Book getBookById(String book_id){
        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("select * from books where book_id=?");
            ps.setString(1, book_id);
            ResultSet rs = ps.executeQuery();
            rs.next();

            Book bookInstance = new Book();
            bookInstance.setBook_id(rs.getString("book_id"));
            bookInstance.setBook_author(rs.getString("book_author"));
            bookInstance.setBook_title(rs.getString("book_title"));
            bookInstance.setBook_price(rs.getFloat("book_price"));

            return bookInstance;
        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return null;
    }
    public static List<Book> getAllBooks(){
        List<Book> bookList = new ArrayList<>();

        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("select * from books");
            ResultSet rs = ps.executeQuery();


            while(rs.next()){

                Book bookInstance = new Book();
                bookInstance.setBook_id(rs.getString("book_id"));
                bookInstance.setBook_author(rs.getString("book_author"));
                bookInstance.setBook_title(rs.getString("book_title"));
                bookInstance.setBook_price(rs.getFloat("book_price"));

                bookList.add(bookInstance);
            }

            return bookList;

        }catch(Exception e){
            System.err.println(e.getStackTrace());
        }
        return null;
    }

    public static String addBook(Book book){
        int status =0;
        String responseMessage = "";

        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement(
                    "insert into books (book_id, book_title, book_author, book_price) values(?,?,?,?)");

            ps.setString(1, book.getBook_id());
            ps.setString(2, book.getBook_title());
            ps.setString(3, book.getBook_author());
            ps.setFloat(4, book.getBook_price());

            status = ps.executeUpdate();

            String successMsg = "Book added successfully: "+book.getBook_id();
            String errorMsg = "Error adding book: "+book.getBook_id();

            //Note the conditional assignment of responseMessage below
            responseMessage = (status ==1) ? successMsg : errorMsg;

        }catch(Exception e){
            e.printStackTrace();
        }
        System.err.println("Returning response message: "+responseMessage);
        return responseMessage;
    }


    public static String removeBook(String book_id){
        int status = 0;
        String responseMessage = "";
        try {
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("delete from books where book_id =?");
            ps.setString(1, book_id);

            status = ps.executeUpdate();

            String successMsg = "Book removed successfully: " + book_id;
            String errorMsg = "Error deleting book: " + book_id;

            responseMessage = (status == 1) ? successMsg : errorMsg;

        }catch(Exception e){
            e.printStackTrace();
        }
        System.err.println("Returning response message: "+responseMessage);
        return responseMessage;
    }


    public static String updateBook(Book book){
        int status = 0;
        String responseMessage = "";
        try{
            Connection myDB = getConnection();
            PreparedStatement ps = myDB.prepareStatement("update books set book_title =?, book_author=?, book_price=? where book_id=?");
            ps.setString(1, book.getBook_title());
            ps.setString(2, book.getBook_author());
            ps.setFloat(3, book.getBook_price());
            ps.setString(4, book.getBook_id());

            status = ps.executeUpdate();

            String successMsg = "Book updated successfully: " + book.getBook_id();
            String errorMsg = "Error updating book: " + book.getBook_id();

            responseMessage = (status == 1) ? successMsg : errorMsg;

        }catch(Exception e){
            e.printStackTrace();
        }

        System.err.println("Returning response message: "+ responseMessage);
        return responseMessage;
    }
}
```

**(B) Submitting Parameters in a GET Request**
- The service class : **PracticeFolders/JavaRESTAPI/src/main/java/com/jaxrswebservice/BookService.java**
```java
package com.jaxrswebservice;

import com.applicationdao.BookDAO;
import com.applicationentities.Book;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/bookservice")
public class BookService {

    @GET
    @Path("/getbooks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBook() {
        List<Book> listOfBooks = BookDAO.getAllBooks();

        return Response.status(Response.Status.OK).entity(listOfBooks).build();

        /*
        http://localhost:7777/JavaRESTAPI/bookservice/getbooks

        [
        {"book_id":"ISBN102","book_title":"Java To Infinity 2","book_author":"Emily Elephant","book_price":1500.0},
        {"book_id":"ISBN103","book_title":"What is this","book_author":"Pedro Pony","book_price":6.1},
        {"book_id":"ISBN104","book_title":"Get Better","book_author":"Suzy Ship","book_price":8.5},
        {"book_id":"ISBN107","book_title":"Animal World","book_author":"Emily Elephant","book_price":38.5},
        {"book_id":"ISBN222","book_title":"Muddy Puddles","book_author":"Pepa Pig","book_price":5.4},
        {"book_id":"ISBNXYZ","book_title":"We Created Java","book_author":"Tomas Jarda","book_price":57575.2}
        ]
         */
    }

    @GET
    @Path("/getbookbyid/{params}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("params") String bookId) {

        //A call to the parameterized method of the BookDAO. The query parameter from then URL is passed to the method
        Book book = BookDAO.getBookById(bookId);
        if(book == null){

            String altResponse = String.format("{ \"message\" : \"Book with ID %s is not found\"}", bookId);

            return Response.status(Response.Status.NOT_FOUND).entity(altResponse).build();

        }
        return Response.status(Response.Status.OK).entity(book).build();

        /*
        http://localhost:7777/JavaRESTAPI/bookservice/getbookbyid/ISBN102
        {"book_id":"ISBN102","book_title":"Java To Infinity 2","book_author":"Emily Elephant","book_price":1500.0}

         http://localhost:7777/JavaRESTAPI/bookservice/getbookbyid/ISBN1099
         { "message" : "Book with ID ISBN105 is not found"}
         */
    }
}
```
**(C) Processing POST request**\
This can be considered as request to insert a new Book entry into the data. The service method will be implemented using the `addBook()` \
method of the BookDAO class. The `@Produces` annotation is used to define the Content-Type of the response. While the `@Consumes` annotation \
is used to define the Content-Type passed along with the POST request. 
- The service class : **PracticeFolders/JavaRESTAPI/src/main/java/com/jaxrswebservice/BookService.java**
```java
package com.jaxrswebservice;


import com.applicationdao.BookDAO;
import com.applicationentities.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/bookservice")
public class BookService {

    @GET
    @Path("/getbooks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBook() {
        List<Book> listOfBooks = BookDAO.getAllBooks();

        return Response.status(Response.Status.OK).entity(listOfBooks).build();

        /*
        http://localhost:7777/JavaRESTAPI/bookservice/getbooks

        [
        {"book_id":"ISBN102","book_title":"Java To Infinity 2","book_author":"Emily Elephant","book_price":1500.0},
        {"book_id":"ISBN103","book_title":"What is this","book_author":"Pedro Pony","book_price":6.1},
        {"book_id":"ISBN104","book_title":"Get Better","book_author":"Suzy Ship","book_price":8.5},
        {"book_id":"ISBN107","book_title":"Animal World","book_author":"Emily Elephant","book_price":38.5},
        {"book_id":"ISBN222","book_title":"Muddy Puddles","book_author":"Pepa Pig","book_price":5.4},
        {"book_id":"ISBNXYZ","book_title":"We Created Java","book_author":"Tomas Jarda","book_price":57575.2}
        ]
         */
    }

    @GET
    @Path("/getbookbyid/{params}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("params") String bookId) {

        //A call to the parameterized method of the BookDAO. The query parameter from then URL is passed to the method
        Book book = BookDAO.getBookById(bookId);
        if(book == null){

            String altResponse = String.format("{ \"message\" : \"Book with ID %s is not found\"}", bookId);

            return Response.status(Response.Status.NOT_FOUND).entity(altResponse).build();

        }
        return Response.status(Response.Status.OK).entity(book).build();

        /*
        http://localhost:7777/JavaRESTAPI/bookservice/getbookbyid/ISBN102
        {"book_id":"ISBN102","book_title":"Java To Infinity 2","book_author":"Emily Elephant","book_price":1500.0}

         http://localhost:7777/JavaRESTAPI/bookservice/getbookbyid/ISBN1099
         { "message" : "Book with ID ISBN105 is not found"}
         */
    }

    @POST
    @Path("/addBook")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book){

        //The String message returned by the method is assigned to addMsg.
        String addMsg = BookDAO.addBook(book);
        //Conditional check of the String object returned by the addBook() method.
        if(addMsg.startsWith("Error")){
            String altResponse = String.format("{ \"error\" : \"Book could not be added....%s\"}", addMsg);

            return Response.status(Response.Status.CONFLICT).entity(altResponse).build();
        }
        
        return Response.status(Response.Status.CREATED).entity(book).build();
    }

}
```
**Using curl for POST request:**\
The parameter `-X POST` specifies the request type as a POST request. `-H` sets some request header information while `-d` specified the\
data/information to be passed along with the POST request. The json-jackson-media- library takes care of converting the String to a custom Java class.
```
curl -X POST \
-H "Content-Type: application/json" \
-d '{"book_id": "ISBNXYZ99", "book_title":"World Best", "book_author":"Shola", "book_price":5.66}' \
http://localhost:7777/JavaRESTAPI/bookservice/addBook
```
**(D) Handling PUT Requests**\
Update method is similar to the POST only that the PUT request modifies an already existing record in the database table. \
It makes use of the updateBook() method of the BookDAO class.
```java
package com.jaxrswebservice;


import com.applicationdao.BookDAO;
import com.applicationentities.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/bookservice")
public class BookService {

    @GET
    @Path("/getbooks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBook() {
        List<Book> listOfBooks = BookDAO.getAllBooks();

        return Response.status(Response.Status.OK).entity(listOfBooks).build();
    }

    @GET
    @Path("/getbookbyid/{params}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("params") String bookId) {

        //A call to the parameterized method of the BookDAO. The query parameter from then URL is passed to the method
        Book book = BookDAO.getBookById(bookId);
        if(book == null){

            String altResponse = String.format("{ \"message\" : \"Book with ID %s is not found\"}", bookId);

            return Response.status(Response.Status.NOT_FOUND).entity(altResponse).build();

        }
        return Response.status(Response.Status.OK).entity(book).build();

    }

    @POST
    @Path("/addBook")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book){

        //The String message returned by the method is assigned to addMsg.
        String addMsg = BookDAO.addBook(book);
        //Conditional check of the String object returned by the addBook() method.
        if(addMsg.startsWith("Error")){
            String altResponse = String.format("{ \"error\" : \"Book could not be added....%s\"}", addMsg);

            return Response.status(Response.Status.CONFLICT).entity(altResponse).build();
        }

        return Response.status(Response.Status.CREATED).entity(book).build();

    }

    @PUT
    @Path("/updateBook")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book){

        //The String message returned by the method is assigned to addMsg.
        String addMsg = BookDAO.updateBook(book);
        //Conditional check of the String object returned by the addBook() method.
        if(addMsg.startsWith("Error")){
            String altResponse = String.format("{ \"error\" : \"Book could not be updated....%s\"}", addMsg);

            return Response.status(Response.Status.NOT_FOUND).entity(altResponse).build();
        }
        return Response.status(Response.Status.CREATED).entity(book).build();
    }
}
```
**Using curl for POST request:**\
```
curl -X PUT \
-H "Content-Type: application/json" \
-d '{"book_id": "ISBNXYZ99", "book_title":"I am the Universe Best!!!", "book_author":"Shola", "book_price":5.66}' \
http://localhost:7777/JavaRESTAPI/bookservice/updateBook
```

**(E) Handling DELETE Requests**\
The removeBook() method of the BookDAO is used to implement a DELETE request.
```java
package com.jaxrswebservice;


import com.applicationdao.BookDAO;
import com.applicationentities.Book;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;


@Path("/bookservice")
public class BookService {

    @GET
    @Path("/getbooks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBook() {
        List<Book> listOfBooks = BookDAO.getAllBooks();

        return Response.status(Response.Status.OK).entity(listOfBooks).build();

        /*
        http://localhost:7777/JavaRESTAPI/bookservice/getbooks

        [
        {"book_id":"ISBN102","book_title":"Java To Infinity 2","book_author":"Emily Elephant","book_price":1500.0},
        {"book_id":"ISBN103","book_title":"What is this","book_author":"Pedro Pony","book_price":6.1},
        {"book_id":"ISBN104","book_title":"Get Better","book_author":"Suzy Ship","book_price":8.5},
        {"book_id":"ISBN107","book_title":"Animal World","book_author":"Emily Elephant","book_price":38.5},
        {"book_id":"ISBN222","book_title":"Muddy Puddles","book_author":"Pepa Pig","book_price":5.4},
        {"book_id":"ISBNXYZ","book_title":"We Created Java","book_author":"Tomas Jarda","book_price":57575.2}
        ]
         */
    }

    @GET
    @Path("/getbookbyid/{params}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBookById(@PathParam("params") String bookId) {

        //A call to the parameterized method of the BookDAO. The query parameter from then URL is passed to the method
        Book book = BookDAO.getBookById(bookId);
        if(book == null){

            String altResponse = String.format("{ \"message\" : \"Book with ID %s is not found\"}", bookId);

            return Response.status(Response.Status.NOT_FOUND).entity(altResponse).build();

        }
        return Response.status(Response.Status.OK).entity(book).build();

        /*
        http://localhost:7777/JavaRESTAPI/bookservice/getbookbyid/ISBN102
        {"book_id":"ISBN102","book_title":"Java To Infinity 2","book_author":"Emily Elephant","book_price":1500.0}

         http://localhost:7777/JavaRESTAPI/bookservice/getbookbyid/ISBN1099
         { "message" : "Book with ID ISBN105 is not found"}
         */
    }

    @POST
    @Path("/addBook")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(Book book){

        //The String message returned by the method is assigned to addMsg.
        String addMsg = BookDAO.addBook(book);
        //Conditional check of the String object returned by the addBook() method.
        if(addMsg.startsWith("Error")){
            String altResponse = String.format("{ \"error\" : \"Book could not be added....%s\"}", addMsg);

            return Response.status(Response.Status.CONFLICT).entity(altResponse).build();
        }

        return Response.status(Response.Status.CREATED).entity(book).build();

    }

    @PUT
    @Path("/updateBook")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateBook(Book book){

        //The String message returned by the method is assigned to addMsg.
        String addMsg = BookDAO.updateBook(book);
        //Conditional check of the String object returned by the addBook() method.
        if(addMsg.startsWith("Error")){
            String altResponse = String.format("{ \"error\" : \"Book could not be updated....%s\"}", addMsg);
            return Response.status(Response.Status.NOT_FOUND).entity(altResponse).build();
        }
        return Response.status(Response.Status.CREATED).entity(book).build();
    }
    
    @DELETE
    @Path("/deleteBook/{param}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response deleteBook(@PathParam("param") String book_id){

        //The String message returned by the method is assigned to addMsg.
        String addMsg = BookDAO.removeBook(book_id);
        //Conditional check of the String object returned by the addBook() method.
        if(addMsg.startsWith("Error")){
            String altResponse = String.format("{ \"error\" : \"Book could not be deleted....%s\"}", addMsg);
            return Response.status(Response.Status.NOT_FOUND).entity(altResponse).build();
        }
        return Response.status(Response.Status.CREATED).entity("Book deleted. ID: "+book_id).build();
    }

}
```
Curl command: `curl -X DELETE http://localhost:7777/JavaRESTAPI/bookservice/deleteBook/ISBNXYZ99`