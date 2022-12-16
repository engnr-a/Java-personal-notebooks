# Course 2--Java Apprentice 
### Table of Content
| #   | Title                                                                 | 
|-----|-----------------------------------------------------------------------|
| 1   | Handling Errors: An Introduction to Exceptions                        | 
| 2   | Handling Errors: Handling Exceptions in Java                          | 
| 3   | Handling Errors: Advanced Topics in Exceptions                        | 
| 4   | Collections in Java: Arrays & Non-parameterized ArrayLists            | 
| 5   | Collections in Java: Lists & List Operations                          | 
| 6   | Collections in Java: Sets & Maps                                      | 
| 7   | Generics in Java: Creating Classes and Methods Using Generics         | 
| 8   | Generics in Java: Bounded Type Parameters & Wildcards                 | 
| 9   | Classes in Java: Working with Static Nested, Inner, & Local Classes   |
| 10  | Classes in Java: Creating & Using Anonymous Classes                   |
| 11  | Classes in Java: Implementing Functional Interfaces Using Lambdas     |
| 12  | Java: Getting Started with Reflection                                 | 
| 13  | Java: Accessing Constructors, Methods, & Fields Using Reflection      |
| 14  | Java: Working with Annotations, Generics, & Arrays Using Reflection   | 
| 15  | **Java: Leveraging Reflection to Build Dynamic Proxies & Unit Tests** | 
| 16  | Java Archive (JAR): Building Java Archives                            | 
| 17  | Java Archive (JAR): Packaging Java Apps Using Maven                   | 
| 18  | Final Exam: Java Apprentice                                           | 
| 19  | Java Apprentice Labs                                                  | 

## 1: Handling Errors: An Introduction to Exceptions 
**(A) Types of Exceptions and their Handlers**
- **Types of Exceptions:**
Exception are any interruption to normal flow of a program. The **_main essence_** of handling exception is that with proper handling,\
program continue to run when some sort of error occurs. Only the part of the code with exception is skipped or done away.

However, without an exception handling, the entire program stops/exits as soon as an exception is encountered. Any code block\
afterwards is not run.
  - **1. Checked exceptions:** caught by the java compiler at _compile time._ Compiler forces the developer to define a handler for\
this type of exceptions. e.g. FileNotFoundException.
  - **2. Unchecked exceptions:** not caught by the java compiler but may still occur at _runtime_. Java terminates the program at the\
point when the exception is occurs. e.g. ArrayIndexOutOfBoundsException, ArithmeticException, NumberFormatException, NullPointerException.
  - **3.**  Errors: Makes the program to terminate. Not easily recovered from
  - checked exceptions, unchecked exceptions, and errors all belongs to the java super class **Throwable**. 

  <img src="\images\exceptionshandling.png">

  - **Handlers:** `try-catch` are designed to handled exceptions in a specific way without necessarily interrupting the program.
    - In the example below, the catch block overrides the default exception by java. Hence, the print statement defined inside\
    catch replaces the default trace of the error.  
    ```java
    package notebooks;
    public class Main {
    
           public static void main (String [] args)  {
                  double [] prices = {5.90, 18.93, 57.9, 47.5, 857.3, 43.282};
                  valueGetter(prices, 8); //
           }
    
          public static void valueGetter(double [] arraY, int indeX){
                 try {
                 System.out.format("The Index: %d \nThe Value:%f", indeX, arraY[indeX]);
                 }
                catch (ArrayIndexOutOfBoundsException e){
                 System.out.println("A Horrible and Exception has Occurred !!!!");
                 }
          }
    }
    ```
    ```
    A Horrible and Exception has Occurred !!!!
    ```
    - Making use of the `e` object to display the track trace of the error.`e` is the entire exception object created.
       - Other methods available to the exception object includes `e.getClass()` `e.getCause()` `e.getMessage` e.t.c.
       - Many operations including `e instanceof Object` `e instanceof Throwable` `e instanceof Exception` is possible with the\
         the exception object
    ```java
    public class Main {
      public static void main (String [] args) {
           try {
               String [] num_strs = {"59", "160", "57", "47.4", "857", "282"};
               for (String str:num_strs){
                   int varInt = Integer.parseInt(str);
                   System.out.format("The string: %s has been converted to an integer: %d \n", str, varInt);
               }
           }
          catch (NumberFormatException e){
                e.printStackTrace();
                System.out.println("An exception occurred at this instance.");
          }
      }
    }
    ```
    ```
     The string: 59 has been converted to an integer: 59 
     The string: 160 has been converted to an integer: 160
     The string: 57 has been converted to an integer: 57
     An exception occurred at this instance.
     java.lang.NumberFormatException: For input string: "47.4"
     at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
     at java.lang.Integer.parseInt(Integer.java:580)
     at java.lang.Integer.parseInt(Integer.java:615)
     at notebooks.Main.main(Main.java:7)
    ```
    - The `Exception` is a class belonging to the `java.lang.Exception`. Its a generic exception that includes other exceptions\
    such as the `ArrayIndexOutOfBoundsException`
  - **Handlers:** `throws`. This uses the default or _explicit_ handler. 
 ```java
  package notebooks;
  public class Main {
         public static void main (String [] args)  {
                double [] prices = {5.90, 18.93, 57.9, 47.5, 857.3, 43.282};
                valueGetter(prices, 8); //
         }
         public static void valueGetter(double [] arraY, int indeX) 
               throws ArrayIndexOutOfBoundsException 
        {
        System.out.format("The Index: %d \nThe Value:%f", indeX, arraY[indeX]);
         }
  }
 ```
 - Since we didn't specify the **catch** block we get the full track trace of the exception.
 - **Importantly,** the code execution stops when the exception is thrown. Hence **!!!**, It's advisable to implement a catch block instead.
 ```
  Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 8
	at notebooks.Main.valueGetter(Main.java:11)
	at notebooks.Main.main(Main.java:6)
 ```
- **Handlers:** `finally` block: will execute whether an exception is thrown or not.

**(B) Explict VS Implicit Handlers:**\
The handler (_explicit_) defined at the highest level of the class e.g. public static void main is overridden by the handler (_implicit_)\
defined at the lowest level of the class.\
Hence, if we define both default and catch exception, the catch takes precedence. We obtain ONLY the print statement within the catch block.
```java
package notebooks;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Main {
    public static void main (String [] args)  throws FileNotFoundException {
        try {
            FileReader file = new FileReader("/shola.txt");
        } catch (FileNotFoundException e){
            System.out.println("The file was not found");
        }
    }
}
```
```
The file was not found
```

## 2: Handling Errors: Handling Exceptions in Java 
**(A) How exceptions are handled by Java:**
- Inside the try-catch block, any line of code after an exception has occurred is ignored.\
  Outside the try-catch block, every other line of code or code blocks without an exception is run.
  ```java
  public class Main {
    public static void main (String [] args) {
        String [] num_strs = {"59", "160.3", "57.5", "47.4", "857", "282"};
        try {
            for (String str:num_strs){
                int varInt = Integer.parseInt(str);
                System.out.format("The string: %s has been converted to an integer: %d \n", str, varInt);
            }
            System.out.println("The line will be ignored as soon as an exception is caught.");
        }
        catch (NumberFormatException e){
            System.out.println("An exception occurred at this instance.");

        }
        System.out.println("--An exception has been caught but I can still run any other line of code--");
        System.out.println("The sum of the 1st and last element is: " + (Integer.parseInt(num_strs[0]) + 
         Integer.parseInt(num_strs[num_strs.length-1])) );
    }
  }
  ```
  ```
  The string: 59 has been converted to an integer: 59 
  An exception occurred at this instance.
  --An exception has been caught but I can still run any other line of code--
  The sum of the 1st and last element is: 341
  ```


**(B) Use of Multiple Exceptions:**
1. In-line: multiple try blocks. The try block is run even after the first exception occurs
```java
public class Main {
    public static void main (String [] args) {
        int index =10;
        String [] products = {"Power Bank", "USB", "Hard Disk", "Laptop", "Mouse", "Charger", null};

        //ArrayIndexOutOfBoundsException exception
        try {
            System.out.format("\n The product at index %d: %s\n", index, products[index]);
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("The bound of array has been exceeded.");
            System.out.println(e.toString());
        }

        //NullPointerException
        try {
            System.out.print("Is the last product a mouse ?: "+
                    products[products.length-1].equalsIgnoreCase("mouse"));
        }
        catch (NullPointerException e){
            System.out.println("A null value was being accessed.");
            System.out.println(e.toString());
        }
    }
}
```
```
The bound of array has been exceeded.
java.lang.ArrayIndexOutOfBoundsException: 10
A null value was being accessed.
java.lang.NullPointerException
```

2. Out-line/nested: Single try block with multiple statements. The second statement will be ignored if an exception is 
  encountered in the first statement. 
```java
public class Main {
    public static void main (String [] args) {
        int index =10;
        String [] products = {"Power Bank", "USB", "Hard Disk", "Laptop", "Mouse", "Charger", null};

        //Single try block with multiple statement
        try {
            System.out.format("\n The product at index %d: %s\n", index, products[index]);
            System.out.print("Is the last product a mouse ?: "+
                    products[products.length-1].equalsIgnoreCase("mouse"));
        }
        //ArrayIndexOutOfBoundsException exception
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("The bound of array has been exceeded.");
            System.out.println(e.toString());
        }
        catch (NullPointerException e){
            System.out.println("A null value was being accessed.");
            System.out.println(e.toString());
        }
        System.out.println("End of try-catch block");
    }
}
```

```
The bound of array has been exceeded.
java.lang.ArrayIndexOutOfBoundsException: 10
End of try-catch block
```

3. Combined: Single try and single catch block with multiple try statements
```java
public class Main {
    public static void main (String [] args) {
        int index =10;
        String [] products = {"Power Bank", "USB", "Hard Disk", "Laptop", "Mouse", "Charger", null};

        //Single try block with multiple statement
        try {
            System.out.format("\n The product at index %d: %s\n", index, products[index]);
            System.out.print("Is the last product a mouse ?: "+
                    products[products.length-1].equalsIgnoreCase("mouse"));
        }
        //ArrayIndexOutOfBoundsException exception
        catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            System.out.println("Some form of exception has occurred");
            System.out.println(e.toString());
        }
    }
}
```
```
Some form of exception has occurred
java.lang.ArrayIndexOutOfBoundsException: 10
```

4. Use of generic handler`(Exception e)`to catch and compliment multiple custom handlers
```java
public class Main {
    public static void main (String [] args) {
        int index =10;
        String [] products = {"Power Bank", "USB", "Hard Disk", "Laptop", "Mouse", "Charger", null};

        //Single try block with multiple statement
        try {
            System.out.format("\n The product at index %d: %s\n", index, products[index]);
            System.out.print("Is the last product a mouse ?: "+
                    products[products.length-1].equalsIgnoreCase("mouse"));
        }
        //ArrayIndexOutOfBoundsException exception
        catch (ArrayIndexOutOfBoundsException | NullPointerException e){
            System.out.println("Some form of exception has occurred");
            System.out.println(e.toString());
        }
        catch (Exception e){
            System.out.println("An exception has been raise: "+e.getClass());
      }
    }
}
```

**(C) BufferedReader**\
The `BufferedReader` is the most common and simple way to read a file line by line in Java. It belongs to java.io package.\
Java BufferedReader class provides readLine() method to read a file line by line.
```java
package notebooks;
import java.io.*;
public class Main {
    /*
    The main method uses an explicity exception handling to throw the IOException so that we dont have to define it
    within the BufferedReader code block.
     */
    public static void main (String [] args) throws IOException {

        //We define a file object
        File file = new File("/mnt/c/Users/absuleim/desktop/Repo-Notes/java-notes/sometext.txt");
        System.out.println("The file path is: " + file.getAbsolutePath());
        System.out.println("The total character is: " + file.length());

        //Read the file into the current Java class.
        //readFile is instant. with null and outside the try block cuz the scope of variables inside try is restricted.
        FileReader readFile = null;
        try {
            readFile = new FileReader(file);
        } catch (FileNotFoundException e) {
            System.out.println("The file was not found");
            e.printStackTrace();

        }

        //To read each line in the file object, we pass it to BufferedReader class.
        // Creating an instance of the BufferedReader
        //Buffered reader contains all the content on the file in buffer state
        BufferedReader bufferedReaderObject = new BufferedReader(readFile);

        //Using the created BufferedReader objects to readlines
        //This reads just one line.
        System.out.println("---Reading just the first line---");
        System.out.println(bufferedReaderObject.readLine());

        //This reads all the lines
       String str = bufferedReaderObject.readLine();
       while (str != null){
           System.out.println(str);
           str = bufferedReaderObject.readLine();
       }
       bufferedReaderObject.close();
    }

}
```
- **File** to define a file object
- **FileReader** to read the file into the system. It's first initialized to null cuz if initialized under the **try** block, it will be\
  unavailable elsewhere within the class. The result is a FileReader object containing the content of the file.
- **BufferedReader** enables line-by-line reading of the file content by parsing the entire file into a buffer state from which it can be\
  accessed. The object method readLine() reads a line at a time. While loop is then used to change the current line to another\
  line.(i.e. invocation readLine from the current line). Finally, we close the BufferedReader.

**(D) Try With Resource Block**
```
try(resouse1; resource2; resource3){
    doSomething;}
catch(Exception, e){
    doSomething;
 }  
```
- A technique enabling the automatic closure of any resource that was opened and used in a **try** block. 
- Instead of explicitly stating the `bufferedReaderObject.close()` as we did above, we parse the BufferedReader to the try block. 
- Automatic closure of a resource within the try block is possible with any object/class that implements `java.lang.AutoClosable` interface.
- The example reads texts from one file and writes it into another file.
  ```java
  package notebooks;
  import java.io.*;

  public class Main {
    public static void main (String [] args)  {
         File fileIn = new File("/mnt/c/Users/absuleim/desktop/Repo-Notes/java-notes/sometext.txt");
         File fileOut = new File("/mnt/c/Users/absuleim/desktop/Repo-Notes/java-notes/anothertext.txt");
         //Try with resources implementation
         try(    //Two defined resources
                BufferedReader br = new BufferedReader(new FileReader(fileIn));
                BufferedWriter bw = new BufferedWriter(new FileWriter(fileOut))
                ){
                bw.write("---Start of content copied from another file!---\n");

                String line = null;
                while((line = br.readLine()) != null){
                   System.out.println(line);
                   bw.write(line +"\n");
                }
                
                bw.write("---End of the content copied from another file.---\n");
        }catch(IOException e) {
            System.out.println("Catch and handle exception: "+e);
        }
    }
  }
  ```

## 3:Handling Errors: Advanced Topics in Exceptions
**(A) Proactive Throwing of an Exception**

**throw new**
- It involves the definition of custom exception. 
- Instead of displaying a statement with respect to conditions of a logic, we throw exceptions to terminate the program\
  and then compliment it with a custom message and or define a catch to handle it. 
- We use `throw new` to define custom exception. Note the difference between built-in **throws** and custom defined **throw**.
- typical exceptions that can be used contextually includes _NullPointerException, IllegalArgumentException, IllegalAccessError_ and \
  the more general _RuntimeException_. 
- It is not mandatory to define a `try` block before using the `throw` statement. 
```java
package notebooks;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main (String [] args)  {
        System.out.println("Enter your GPA to check your eligibility:");
        Scanner inputGPA = new Scanner(System.in);
        float gpa = inputGPA.nextFloat();
        inputGPA.close();

        validateGPA(gpa);

    }

    public static void validateGPA(float gpa){
        if(gpa>0 && gpa <=5.0){
            System.out.println("GPA validated. Processing eligibility...");
        }else{
            //Custom defined exceptions.
            throw new IllegalArgumentException("Use your brain!!!. A GPA must be between 0 and 5.0");
        }
    }

}
```
```
Enter your GPA to check your eligibility:
8
Exception in thread "main" java.lang.IllegalArgumentException: Use your brain!!!. A GPA must be between 0 and 5.0
	at notebooks.Main.validateGPA(Main.java:21)
	at notebooks.Main.main(Main.java:12)
```
**(B) Throwing Errors**

Same as the earlier implementation: `throw new SomeKindOfError`
```java
package notebooks;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main (String [] args)  {
        System.out.println("Enter your name:");
        Scanner inputGPA = new Scanner(System.in);
        String username = inputGPA.nextLine();
        System.out.println("Enter the operation you would like to perform:");
        String operation = inputGPA.nextLine();

        userRequest(username, operation);

    }

    public static void userRequest(String username, String operation){
        if(username.startsWith("admin")){
            System.out.println("Signing in...");
        }else{
            throw new IllegalArgumentException("Only admin is permitted.");
        }
        System.out.format("The username: \"%s\" requested the operation \"%s\" \n", username, operation);

        if(operation.equalsIgnoreCase("edit") || operation.equalsIgnoreCase("delete")){
            //Throw
            throw new IllegalAccessError("The user does not have the required permissions.");
        }

    }

}
```
```
Enter your name:
admin
Enter the operation you would like to perform:
delete
Signing in...
The username: "admin" requested the operation "delete" 
Exception in thread "main" java.lang.IllegalAccessError: The user does not have the required permissions.
	at notebooks.Main.userRequest(Main.java:27)
	at notebooks.Main.main(Main.java:13)
```


## 4: Collections in Java: Arrays & Non-parameterized ArrayLists
The Java Collection Framework is a set of classes and interfaces that implement commonly reusable collection data structures. The\
framework provides interfaces that define various collections and classes that implement them. Although `Collection` is referred\
to as a framework, it's actually a library. 

**(A) Creating Arrays of Primitive Types and Objects**

```java
package simplepracticeclasses;

import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.util.Arrays;

public class AppaMain {
    public static void main(String[] args) {
        //Defining an array without instantiating it
        int[] trialArray;

        //Defining an array and initializing it with 4 elements        
        int[] intArray = new int[6];

        //Assigning values to the array using their index. 1st element is indexed 0.
        intArray[0] = 5;
        intArray[1] = 3;

        //Defining an array and instantaneously assigning elements to it.
        float[] intArray2 = {6.3f, 7.9f, 8, 9};

        //Array of an object e.g. String, Date
        String[] stringArray = {"Good", "boy"};

        //Default string representation of an array
        System.out.println(intArray2.toString()); //[F@135fbaa4  //toString() method doesn't print out the elements of an array.

        //Use the "Arrays" utility to print out the elements of the array.
        System.out.println(Arrays.toString(intArray2)); // [6.3, 7.9, 8.0, 9.0]
        System.out.println(Arrays.toString(intArray)); // [5, 3, 0, 0, 0, 0]
        System.out.println(stringArray); // [Ljava.lang.String;@45ee12a7
        System.out.println(Arrays.toString(stringArray)); //[Good, boy]
        
        //Zero element array.
        int[] zeroElementArray = new int[0];
        int[] zeroElementArray2 = {};

        //Length of the array
        int lengthOfString = StringArray.length;

        //Array Accessor
        float secondElement = intArray2[1];
        System.out.println(StringArray[0]); // Good
    }
}
```
**NOTE:** 
* The representation of _Primitive types_ (e.g float and int) `[F@135fbaa4` and _Object_ (e.g. String)`[Ljava.lang.String;@45ee12a7`.
* However, even primitive types are also instances of objects
* It's possible to define an array without assigning variable to it. However, the dimension of an array must be stated at instantiation or variable assignment
* Unlike python, negative indexing of an array is not possible. i.e. `StringArray[-1]` is not permitted.
* When the dimensions of an array is defined, its filled by default with 0 or null.

**Mixed Variable Type Array**

```java
public class AppaMain {
    public static void main(String[] args) {

        Object[] mixedArray = {"sHola", 5, 6.3f, true};

        //"for i" loop
        for (int i = 0; i < mixedArray.length; i++){
            if(String.valueOf(mixedArray[i]).equalsIgnoreCase("shola")){
                System.out.println("Thats my name");
            }else{
                System.out.println(mixedArray[i]);
            }
        }

        
        //"for each" loop
        for (Object i:mixedArray
             ) {
            System.out.format("The class of '%s' is: '%s'. %n", i, i.getClass());
        }

    }

}
```
**NOTE:**
The problem with this type of array, is the loss of all compile time check for object types.

**Multidimensional Arrays**

```java
package simplepracticeclasses;

import java.util.Arrays;

public class AppaMain {
    public static void main(String[] args) {

        int[][] multiDimensionalArray = new int[3][4]; //3 rows and 4 columns
        System.out.println(multiDimensionalArray); //[[I@135fbaa4
        System.out.println(Arrays.toString(multiDimensionalArray)); 
        //[[Ljava.lang.Integer;@266474c2, [Ljava.lang.Integer;@6f94fa3e, [Ljava.lang.Integer;@5e481248]
        
        //counting of the columns and rows starts with index 0
        multiDimensionalArray[0][0] = 7; //first row, first column
        multiDimensionalArray[1][2] = 13;//second row, third column
        for (int[] i : multiDimensionalArray
        ) {
            System.out.println(Arrays.toString(i));
        }
        /*
                [7, null, null, null]
                [null, null, 13, null]
                [null, null, null, null]
         */
    }
}
```

**(B) Iterating over Array Using For Loops**

* Use `for each` loop if the interest is only the element and index is not of relevance
* Use `for i` loop if the index of the element is of relevance

```java
package simplepracticeclasses;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AppaMain {
    public static void main(String[] args) {
        Integer[] tempArrays = {1, 2};

        List<Integer> tempArrays2List = new ArrayList<>(Arrays.asList(tempArrays));

        Collections.addAll(tempArrays2List, 7, 16, 15, 1, 4, 2);

        //"for i" loop
        try {
            for (int i = 0; i < tempArrays2List.size(); i++) {
                if (tempArrays2List.get(i) >= 10) {
                    System.out.println(tempArrays2List.get(i));
                } else {
                    System.out.format("The element at index %d is %d %n", i, tempArrays2List.get(i));
                }

            }
        } catch (Exception e) {
            System.out.println(e.getClass().getSimpleName());
        }

        //"for each" loop
        for (Integer i : tempArrays2List
        ) {
            System.out.println(i);

        }

        // "for i" loop with array
        for (int i = 0; i < tempArrays.length; i++) {
            System.out.format("The element at index %d is %d %n", i, tempArrays[i]);
        }

    }

}
```
**Why a framework ?**

The code below shows the complexity involved with working with pure java array as a _**collection container**_. Hence, the need\
for a library or framework such as `Collection`. 
```java
public class AppaMain {
    public static void main(String[] args) {
    }


    public static void addElement(String element, String [] arrayObject){

        //Initializing the last index to zero. This will be used to check the capacity status of the array.
        int lastIndex = 0;
        //first we check the capacity status of the array
        if(lastIndex >= arrayObject.length){
            System.out.println("The array has reached maximum. Cant add element.");
            return;
        }
        arrayObject[lastIndex++] = element;

    }

    public static void deleteElement(int index, String [] arrayObject){
        //An element of an array can't be really deleted.  Because the declared length of an array can't be changed.
        //However, an element can be (re)set to null or 0.
        if(index >= arrayObject.length){
            System.out.println("The index of the element to delete is out of bound.");
            return;
        }
        //If the check is passed, the element at the particular index is set to 0
        arrayObject[index] = null;
        //

    }

}
```

**(C) ArrayList and LinkedList**

* Every ArrayList is an instance of `List, Collections, Iterable`. 
* Iterable derives from Collections and Collections derive from List.
* An ArrayList or any list is an ordered collection. Hence, they automatically arranged
* This type of list has a reference to the element before it and the element after it.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        ArrayList<Character> charList = new ArrayList<>();
        Collections.addAll(charList, 'A', 'Z', 'D');
        charList.add('J');
        boolean checkEmpty = charList.isEmpty();
        charList.remove(2);
        boolean containsCheck = charList.contains('W');
        int sizeChecker = charList.size();
        char getSomeValue = charList.get(1);


        List<Integer> someLinkedList = new LinkedList<>();
    }
}
```
* All the methods used so far in the above lines of codes are part of the **List** and **Collections** and they are not specific to only\
ArrayLIst. Hence, they available to other types of lists as well. However, they are not all available or part of the **Iterable** interface.


**(C) Non-Parametrized Collections/List...BAD practice but noteworthy**

* It's possible to specify a list without specifying the variable/data type of its element. 
* It can be used to collect/store any kind of element. The elements are stored as variable/data type **Object**.
* Since the elements has variable type Object, all compile time checks are lost. 
* Even though we can always cast from Object type to other types, its bad practice to use non-parametrized collections.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        LinkedList<Character> charList = new LinkedList<>();
        Collections.addAll(charList, 'A', 'Z', 'D');
        charList.add('J');
        boolean checkEmpty = charList.isEmpty();
        charList.remove(2);
        boolean containsCheck = charList.contains('W');
        int sizeChecker = charList.size();
        char getSomeValue = charList.get(1);

        System.out.println(Calendar.getInstance().getTime());


        ArrayList randomList = new ArrayList();
        randomList.add(5);
        randomList.add(8);
        randomList.add("62");
        randomList.add('A');
        randomList.add("Shola");
        randomList.add(Calendar.getInstance().getTime());
        randomList.add(true);

        //This prints ouf the class of each element.
        for (Object i : randomList) {
            System.out.println(i + ":-- " + i.getClass());
        }

        // This is fine without error.
        int sumOfFirstTwo = (int) randomList.get(0) + (int) randomList.get(1);
        System.out.println(sumOfFirstTwo);


        // This will lead to"ClassCastException. Even though it wasn't flagged by the IDE
        int sumOfFirstThree = (int) randomList.get(0) + (int) randomList.get(1) + (int) randomList.get(2);
        System.out.println(sumOfFirstTwo);

    }
}
```

## 5: Collections in Java: Lists & List Operations
**(A) Parametrized list containing custom objects**

```java
package simplepracticeclasses;

import java.util.*;

public class Car {
    private String name;
    private String make;
    private Long price;

    public Car(String name, String make, Long price) {
        this.name = name;
        this.make = make;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", make='" + make + '\'' +
                ", price=" + price +
                '}';
    }
}

```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        ArrayList<Car> carList = new ArrayList<>();
        carList.add(new Car("Q8", "Audi", 565474L));
        carList.add(new Car("Model S", "Tesla", 937362L));
        carList.add(new Car("E class", "Mercedes", 565474L));

        Car e_class = carList.get(1);
        System.out.println(e_class);
        System.out.println(carList);

    }
}

```
**(B) Parametrized list with custom objects and List VS ArrayList**

* Generic lists such as ArrayList or generic classes of any type CAN'T be instantiated with a primitive type such as int.
* Generic list or any generic class, can only be instantiated with an argument type **Objects**(e.g. Integer) and NOT primitive type(e.g. int)
* Every primitive type in java have a reference type known as **wrapper**. e.g. `int --> Integer`

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        // This will lead to compile time error
        ArrayList<int> someList = new ArrayList<>();

        //INstead of using int, we use it wrapper; Integer
        ArrayList<Integer> someList2 = new ArrayList<>();


    }
}
```
**List VS ArrayList (Collections)**

* All methods below are all methods of the _Collection_ framework.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        ArrayList<Integer> someList2 = new ArrayList<>();

        //NUll values can be added to an ArrayList
        someList2.add(null);
        someList2.add(45);
        System.out.println(someList2); //[null, 45]

        ArrayList<Integer> someList3 = new ArrayList<>();
        Collections.addAll(someList3, 93, 27, 13);

        //Combining two lists
        someList2.addAll(someList3);
        System.out.println(someList2); //[null, 45, 93, 27, 13]

        //Checking whether a list contains another list as its subset
        System.out.println(someList2.containsAll(someList3)); //true
        someList3.add(1000);
        System.out.println(someList2.containsAll(someList3)); //false

        //Removing a subset from a list
        someList2.removeAll(someList3);
        System.out.println(someList2.size()); //2
        someList2.removeAll(someList2);
        System.out.println(someList2.size());//0. The list has been removed from itself.

    }
}

```
* All methods available in the _Collection_ framework are also available in the _List_ interface.
* However, not all methods of the _List_ interface are available in the _Collection_ framework
*  Also, List data type can hold duplicate elements. Unlike other collections containers such as sets.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        //All the methods below are available only on the List interface
        List<String> someWords = new ArrayList<>();

        //All methods below will fail it the variable type is changed as below
        //Collections <String> someWords = new ArrayList<>();

        //We can use the Collection framework on a List variable type
        Collections.addAll(someWords, "new", "old", "yes");

        System.out.println(someWords); //[new, old, yes]
        someWords.add("good"); //this added to the end of the list
        System.out.println(someWords); //[new, old, yes, good]

        //Adding elements to a particular index is method available only to the List lib
        someWords.add(1, "JJ");
        System.out.println(someWords); //[new, JJ, old, yes, good]

        //Removing element by index
        someWords.remove(0);

        //Adding duplicate elements
        someWords.add("Name");
        someWords.add("Name");
        someWords.add("Name");
        someWords.add("Name");


        //The index of the first occurrence of the element
        System.out.println(someWords.indexOf("Name")); //4


        //The index of the last occurrence of the element
        System.out.println(someWords.lastIndexOf("Name")); //7

    }
}
```

**(C) Iterable interface**

* Iterable allows the iteration over the elements in a list.
* Iterable gives access to an iterable object such as a list.
* The Iterator interface can be used on an iterable. 
* Iterator interface has some methods including; `next()` and` hasNext()`
* hasNext() returns true/false
* next() accesses the current element of iteration
* iterator has NoSuchElementException to handle iteration over

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        List<String> names = new ArrayList<>();
        Collections.addAll(names, "Shola", "Betty", "Aurora");

        //Declaring an iterable variable
        Iterable<String> namesIterable = names;
        for (String name : namesIterable) {
            System.out.println(name);
        }

        //Initializing iterator on an iterable
        Iterator<String> namesIterableIterator = namesIterable.iterator();

        //hasNext() and next() methods of the iterator
        System.out.println(namesIterableIterator.hasNext());//true
        System.out.println(namesIterableIterator.next()); //Shola

        System.out.println(namesIterableIterator.hasNext()); //true
        System.out.println(namesIterableIterator.next()); //Betty

        System.out.println(namesIterableIterator.hasNext()); //tre
        System.out.println(namesIterableIterator.next()); //Aurora

        System.out.println(namesIterableIterator.hasNext()); //false
        System.out.println(namesIterableIterator.next()); //Exception in thread "main" java.util.NoSuchElementException


    }
}
```
## 6 Collections in Java: Sets & Maps 
1. HashSet
2. LinkedHashSet
3. TreeSet
4. HashMap
5. LinkedHashMap
6. TreeMap

**(A)**

Sets store values that are unique in an unordered structure. Maps store data in a key-value pair. There are several implementations of\
set in java. The most common is the _HashSet_.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        //Instantiation
        HashSet<String> cars = new HashSet<>();

        //Adding elements in bulk
        Collections.addAll(cars, "Mercedes", "BMW", "BMW", "bmw", null, null, "Tesla", "Prague");
        System.out.println(cars); //[null, Tesla, Mercedes, BMW, bmw]
        System.out.println(cars instanceof Set); //true
        System.out.println(cars instanceof Collection); //true
        System.out.println(cars instanceof Iterable); //true

        //Adding elements individually
        cars.add("Toyota");
        cars.remove("bmw");

        Set<String> cities = new HashSet<>();
        Collections.addAll(cities, "Berlin", "Prague", "Lagos", "BMW");
        System.out.println();

        //Union operation-----adding two sets together and retaining only what is not duplicate
        cars.addAll(cities);
        System.out.println(cars); //[null, Toyota, Berlin, Tesla, Lagos, Mercedes, BMW, Prague]

        //Intersection Operation ---> retains the values that are common to both set
        cars.retainAll(cities);
        System.out.println(cars); //[BMW, Prague]

    }
}

```
**(B) Set Duplicates: Overriding .equals and .hashCode**

* Each object of a class is unique and has their unique hash code regardless of the data/parameter they hold. It is possible to have\
a set containing identical objects. Hence, duplicates. If two objects contains same variable, HashSet is unable to recognize them\
as duplicates.

```java
package simplepracticeclasses;

public class Product {
    public String name;
    public String brand;
    public String category;

    public Product(String name, String brand, String category) {
        this.name = name;
        this.brand = brand;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                "\n" +
                '}';
    }

    public void productInfo() {
        System.out.format("Product Info{Name:%s, Brand:%s, Category:%s %n.}", name, brand, category);
    }

}
```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        //Instantiation
        Product iphone12 = new Product("iPhone12", "Apple", "Smart Phones");
        //iphone12.productInfo(); //Product Info{Name:iPhone12, Brand:Apple, Category:Smart Phones.}
        Product airmax = new Product("airMax", "Nike", "Sports Wear");
        Product noteX = new Product("Galaxy Note X", "Samsung", "Smart Phones");

        //
        HashSet<Product> productSet = new HashSet<>();
        Collections.addAll(productSet, iphone12, airmax, noteX);
        System.out.println(productSet);
      /*
      [Product{name='airMax', brand='Nike', category='Sports Wear'
      }, Product{name='iPhone12', brand='Apple', category='Smart Phones'
      }, Product{name='Galaxy Note X', brand='Samsung', category='Smart Phones'
      }]
       */

        //Adding a new iphone12 but with different variable name
        Product iphone12_2 = new Product("iPhone12", "Apple", "Smart Phones");
        productSet.add(iphone12_2);
        System.out.println("--------");
        System.out.println(productSet);

      /*
      [Product{name='iPhone12', brand='Apple', category='Smart Phones'
        }, Product{name='airMax', brand='Nike', category='Sports Wear'
        }, Product{name='iPhone12', brand='Apple', category='Smart Phones'
        }, Product{name='Galaxy Note X', brand='Samsung', category='Smart Phones'
        }]
     
       */


        System.out.println(iphone12.hashCode()); //325040804
        System.out.println(iphone12_2.hashCode()); //621009875

    }
}
```
* HashSet uses both `.equals` and `hashCode` to compare objects.
* Both methods should be overridden together in most cases.

```java
package simplepracticeclasses;

import java.util.Objects;

public class Product {
    public String name;
    public String brand;
    public String category;

    public Product(String name, String brand, String category) {
        this.name = name;
        this.brand = brand;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                "\n" +
                '}';
    }

    public void productInfo() {
        System.out.format("Product Info{Name:%s, Brand:%s, Category:%s %n.}", name, brand, category);
    }


    //Overridden .eauals method
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        //Checking whether other is an instance of Product
        //We return false if IT IS NOT an instance of Product. !() means not
        if (!(other instanceof Product)) {
            return false;
        }

        //After nullity and instance check, we CAST other to type Product
        Product otherProduct = (Product) other;

        //Finally, implementation of full comparison.
        if (otherProduct.name.equals(this.name) &&
                otherProduct.category.equals(this.category) &&
                otherProduct.brand.equals(this.brand)) {
            return true;
        }
        return false;

    }

    //Overridden .hashcode() method
    @Override
    public int hashCode() {
        return Objects.hash(name, brand, category);
    }
}
```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        //Instantiation
        Product iphone11 = new Product("iPhone11", "Apple", "Smart Phones");
        //iphone12.productInfo(); //Product Info{Name:iPhone12, Brand:Apple, Category:Smart Phones.}
        Product airmax = new Product("airMax", "Nike", "Sports Wear");
        Product noteX = new Product("Galaxy Note X", "Samsung", "Smart Phones");

        //
        HashSet<Product> productSet = new HashSet<>();
        Collections.addAll(productSet, iphone11, airmax, noteX);


        //Adding a new iphone12 but with different variable name
        Product iphone11_2 = new Product("iPhone11", "Apple", "Smart Phones");
        productSet.add(iphone11_2);

        System.out.println(iphone11.hashCode()); //-1728265786
        System.out.println(iphone11_2.hashCode()); //-1728265786
        System.out.println(iphone11.equals(iphone11_2)); // true
        System.out.println(productSet);

        //Duplicate is taken care of.
      /*
      [Product{name='airMax', brand='Nike', category='Sports Wear'
      }, Product{name='iPhone11', brand='Apple', category='Smart Phones'
      }, Product{name='Galaxy Note X', brand='Samsung', category='Smart Phones'
      }]
       */

    }
}
```
**(C) LinkedHashSet and TreeSet**

* The `LinkedHashSet` is a HashSet that maintains a linked a link list of its element. 
* Hence, the _LinkedHashSet_ is an instances of: _Set, Collections, Iterables and HashSet_.
* The `TreeSet` orders its element based on their values. Since it explicitly implements ordering of elements,\
the TreeSet is substantially slower than working with other types of sets.
* The TreeSet is an instance of _Set, Collections, Iterable and SortedSet_.
* TreeSet doesn't accept _null_ values unlike other sets.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        String[] cars = {"Mercedes", "Volvo", "Tesla", "Audi", "BMW"};
        //NOTE: the conversion of array to list and then to set
        Set<String> carsHashSet = new HashSet<>(Arrays.asList(cars));
        Set<String> carsLinkedHashSet = new LinkedHashSet<>(Arrays.asList(cars));
        Set<String> carsTreeSet = new TreeSet<>(Arrays.asList(cars));
        System.out.println(carsHashSet); //Random order:[Volvo, Audi, Tesla, Mercedes, BMW]
        System.out.println(carsLinkedHashSet);//Order of insertion: [Volvo, Audi, Tesla, Mercedes, BMW]
        System.out.println(carsTreeSet);//Alphabetic order:[Audi, BMW, Mercedes, Tesla, Volvo]
    }
}
```

**(D) Comparisons in TreeSet**

* TreeSet uses the comparable interface/methods to `compareTo` objects to determine their order of precedence.
* A default compare interface can be specified inside the class to override the default compare interface.
* Almost all objects has the default `compareTo` method. This method returns -/+ integer.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

    }
}
```
* Exploring the SortedSet

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        TreeSet<String> vowels = new TreeSet<>();
        Collections.addAll(vowels, "A", "E", "I", "O", "U");

        //Upper case has precedence over lower case.
        System.out.println(vowels.last()); //U
        System.out.println(vowels.first()); //A

        //All elements that come before a certain element
        System.out.println(vowels.headSet("I")); //[A, E]

        //All elements that come after a certain element, including that element.
        System.out.println(vowels.tailSet("E"));//[E, I, O, U]

        //All elements that are between two specific elements, including the first specific element.
        System.out.println(vowels.subSet("E", "U")); //[E, I, O]
    }
}
```
**(E) Using Comparable and Comparators in TreeSet**

In order to use TreeSet to store custom objects, the class must meet either of the two conditions:

1. implement the `Comparable` interface 
2. Comparator must be specified for the TreeSet

In essence, only comparable objects can be added to a TreeSet. `ClassCastException` is encountered as casting to comparable will fail. 

**Overriding compareTo method (Implementing a Comparable)**

```java
package simplepracticeclasses;

import java.util.Objects;

//Implementation of the comparable interface
public class Product implements Comparable<Product> {
    public String name;
    public String brand;
    public String category;

    public Product(String name, String brand, String category) {
        this.name = name;
        this.brand = brand;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                "\n" +
                '}';
    }

    //Overridden .eauals method
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        //Checking whether other is an instance of Product
        //We return false if IT IS NOT an instance of Product. !() means not
        if (!(other instanceof Product)) {
            return false;
        }

        //After nullity and instance check, we CAST other to type Product
        Product otherProduct = (Product) other;

        //Finally, implementation of full comparison.
        if (otherProduct.name.equals(this.name) &&
                otherProduct.category.equals(this.category) &&
                otherProduct.brand.equals(this.brand)) {
            return true;
        }

        return false;
    }

    //Overridden .hashcode() method
    @Override
    public int hashCode() {
        return Objects.hash(name, brand, category);
    }


    //Implementation of the compareTo method on the name
    @Override
    public int compareTo(Product o) {
        int compareName = this.name.compareTo(o.name);
        //If the names are different, the result of the comparison is returned.
        if (compareName != 0) {
            return compareName;
        }
        //If  the names of the two products are the same, we compare with category
        return this.category.compareTo(o.category);
    }
}

```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        Set<Product> productTreeSet = new TreeSet<>();
        Product iphone12 = new Product("iPhone12", "Apple", "Smart Phones");
        Product airmax = new Product("airMax", "Nike", "Sports Wear");
        Product noteX = new Product("galaxy Note X", "Samsung", "Smart Phones");
        Product zPhone = new Product("z Phone", "Samsung", "Smart Phones");

        productTreeSet.add(iphone12);
        productTreeSet.add(airmax);
        productTreeSet.add(noteX);
        productTreeSet.add(zPhone);

        System.out.println(productTreeSet);

    }
}
```

```
[  Product{name='airMax', brand='Nike', category='Sports Wear'
}, Product{name='galaxy Note X', brand='Samsung', category='Smart Phones'
}, Product{name='iPhone12', brand='Apple', category='Smart Phones'
}, Product{name='z Phone', brand='Samsung', category='Smart Phones'
}]
```
**Implementing a Comparator**

* If it is impossible to modify the class of the object to be added to a TreeMap, we implement a comparator.
* **Comparable** involves overriding the compareTo within the class itself. Whereas **comparator** is done outside the class.
* A getter is added to the Product class itself 
* A comparator is added to the class that instantiate the object of Product.
* The TreeSet in the instantiating class is modified to include the comparator. The defined comparator is stated explicitly\
when instantiating the TreeSet. The explicit comparator takes precedence even if comparable were to be defined within the\
Product class. Hence, comparator overrides the comparable.

```java
package simplepracticeclasses;

import java.util.Objects;

//Implementation of the compareble interace
public class Product {
    public String name;
    public String brand;
    public String category;

    public Product(String name, String brand, String category) {
        this.name = name;
        this.brand = brand;
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", category='" + category + '\'' +
                "\n" +
                '}';
    }

    //Getters
    
    //Setters

    //Overridden .eauals method
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        //Checking whether other is an instance of Product
        //We return false if IT IS NOT an instance of Product. !() means not
        if (!(other instanceof Product)) {
            return false;
        }

        //After nullity and instance check, we CAST other to type Product
        Product otherProduct = (Product) other;

        //Finally, implementation of full comparison.
        if (otherProduct.name.equals(this.name) &&
                otherProduct.category.equals(this.category) &&
                otherProduct.brand.equals(this.brand)) {
            return true;
        }
        return false;
    }

    //Overridden .hashcode() method
    @Override
    public int hashCode() {
        return Objects.hash(name, brand, category);
    }

}

```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        //A comparator that will be explicitly stated when instantiating the TreeSet
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                int compareName = o1.getName().compareTo(o2.getName());
                if (compareName != 0) {
                    return compareName;
                }
                return o1.getCategory().compareTo(o2.getCategory());

            }
        };

        //We specify that the TreeSet use the defined comparator for its comparison
        //This explicitly stated comparator will take precedence even if comparable is defined within the Product class
        SortedSet<Product> productTreeSet = new TreeSet<>(productComparator);

        //Instances of Product
        Product iphone12 = new Product("iPhone12", "Apple", "Smart Phones");
        Product airmax = new Product("airMax", "Nike", "Sports Wear");
        Product noteX = new Product("galaxy Note X", "Samsung", "Smart Phones");
        Product zPhone = new Product("z Phone", "Samsung", "Smart Phones");

        //Adding the Product objects to the TreeSet
        productTreeSet.add(iphone12);
        productTreeSet.add(airmax);
        productTreeSet.add(noteX);
        productTreeSet.add(zPhone);

        System.out.println(productTreeSet);

    }
}
```

```
[  Product{name='airMax', brand='Nike', category='Sports Wear'
}, Product{name='galaxy Note X', brand='Samsung', category='Smart Phones'
}, Product{name='iPhone12', brand='Apple', category='Smart Phones'
}, Product{name='z Phone', brand='Samsung', category='Smart Phones'
}]
```


**(F) Map Operations**

* The key must be unique but the values doesn't have to be unique
* HashMap is an instance of Map. However, HashMap and other maps are not instances of Collections and Iterable.
* Hence, methods of Collections and Iterables can't be used. 
* Values can have as many null values as needed. However, Keys can only have one null value.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        //Instantiation
        Map<Integer, String> productMap = new HashMap<>();

        //Putting elements
        productMap.put(001, "iPhoneX");
        productMap.put(002, "iPad Mini");
        productMap.put(003, "HP Laptop");
        productMap.put(004, "Samsung s20");
        productMap.put(005, "Ear Pod");
        productMap.put(006, "Sony PS5");

        System.out.println(productMap.isEmpty()); //false
        System.out.println(productMap.size()); //3
        System.out.println(productMap); //{1=iPhoneX, 2=iPad Mini, 3=HP Laptop, 4=Samsung s20, 5=Ear Pod, 6=Sony PS5}

        //Using key to get a value
        String samsungProduct = productMap.get(004);
        System.out.println(samsungProduct);  //Samsung s20

        //Removing element
        productMap.remove(007);

        //Updating a value using "put"
        productMap.put(001, "iPhone 14");
    }
}
```

* There methods of Map that helps us to get a _Collection View_ of the objects of a map. For example, it's possible to have\
a set representation of all the entries in a map.
* The set representation gives either (e.g. for a HashMap; citiesMap)
 - set view of the _key and value_ `citiesMap.entrySet();`
 - set view of the _keys_ `citiesMap.keySet();`
 - set view of just the _values._ `citiesMap.values();`

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        //Instantiation
        Map<Integer, String> citiesMap = new HashMap<>();

        //Putting elements
        citiesMap.put(001, "Prague");
        citiesMap.put(002, "Berlin");
        citiesMap.put(003, "Vienna");
        citiesMap.put(004, "Rome");
        citiesMap.put(005, "Warsaw");
        
        //Set representation of a map......SET VIEW OF KEY+VALUE(ENTRY)
        Set<Map.Entry<Integer, String>> citiesMapAsSet = citiesMap.entrySet();
        System.out.println(citiesMapAsSet); //[1=Prague, 2=Berlin, 3=Vienna, 4=Rome, 5=Warsaw]
        System.out.println(citiesMapAsSet.getClass()); //class java.util.HashMap$EntrySet

        //for each loop for
        for (Map.Entry<Integer, String> entry : citiesMapAsSet) {
            System.out.format("Key:%s, Value:%s %n", entry.getKey(), entry.getValue());
        }
        /*
        Key:1, Value:Prague
        Key:2, Value:Berlin
        Key:3, Value:Vienna
        Key:4, Value:Rome
        Key:5, Value:Warsaw
         */
        
        //Using for loop to set a value
        for (Map.Entry<Integer, String> entry : citiesMapAsSet) {
            if (entry.getValue() == "Berlin") {
                entry.setValue("Berlin (where I work)");
            }
        }

        //Set representation of a map......SET VIEW OF KEY
        Set<Integer> citiesMapAsSet_KEY = citiesMap.keySet();

        //Set representation of a map......SET VIEW OF VALUE(ENTRY)
        Collection<String> citiesMapAsSet_VALUES = citiesMap.values();
        System.out.println(citiesMapAsSet_VALUES); //[Prague, Berlin (where I work), Vienna, Rome, Warsaw]
        System.out.println(citiesMapAsSet_VALUES.getClass()); //class java.util.HashMap$Values
    }
}
```

**(G) Map and Custom Objects**
* Custom objects can be used both as a key as well as a value of map.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        //Map holding Product object as values
        Map<Integer, Product> productMap = new HashMap<>();

        //Adding objects of the Product class to productMap
        productMap.put(01, new Product("Magic Mouse", "Apple", "Electronics"));
        productMap.put(02, new Product("Galaxy Tab", "Samsung", "Mobile Phones"));
        productMap.put(03, new Product("LG neo", "Apple", "Electronics"));

        //Using entrySet() to iterate over the keys and values of the productMap
        for (Map.Entry<Integer, Product> entry : productMap.entrySet()) {
            System.out.format("Key:%d, Value:%s. %n", entry.getKey(), entry.getValue());
        }

        /*
        Key:1, Value:Product{name='Magic Mouse', brand='Apple', category='Electronics'
        }.
        Key:2, Value:Product{name='Galaxy Tab', brand='Samsung', category='Mobile Phones'
        }.
        Key:3, Value:Product{name='LG neo', brand='Apple', category='Electronics'
        }.
         */
    }
}
```
* Map is able to store **duplicate/identical** custom objects.
* The `hashCode` and `equals` methods must be overridden in order for Maps to identify duplicate custom objects. 
* This is similar to the case with sets. 
* Overriding both methods is particular important in order to have values updated. 
```
//Overridden .eauals method
    @Override
    public boolean equals(Object other) {
        if(other == null){
            return false;
        }

        //Checking whether other is an instance of Product
        //We return false if IT IS NOT an instance of Product. !() means not
        if(!(other instanceof Product)){
            return false;
        }

        //After nullity and instance check, we CAST other to type Product
        Product otherProduct = (Product) other;

        //Finally, implementation of full comparison.
        if(otherProduct.name.equals(this.name)&&
           otherProduct.category.equals(this.category)&&
                otherProduct.brand.equals(this.brand)){
            return true;
        }

        return false;

    }

    //Overridden .hashcode() method
    @Override
    public int hashCode() {
        return Objects.hash(name, brand, category);
    }
```

**(H) LinkedHashMap and TreMap**
* LinkedHashMap is an instance of Map and HashMap.
* LinkedHashMap maintains a doubly linked list of all its entries.
* TreeMap, like TreeSet, imposes an explicit ordering of the keys in the map.
* TreeMap is an instance of the Map and SortedMap.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        //Map holding Product object as values

        Map<Integer, String> countriesHashMap = new HashMap<>();
        Map<Integer, String> countriesLinkedHashMap = new LinkedHashMap<>();
        Map<Integer, String> countriesTreeMap = new TreeMap<>();

        countriesHashMap.put(1960, "Nigeria");
        countriesHashMap.put(1776, "United States of America");
        countriesHashMap.put(1957, "Ghana");

        countriesLinkedHashMap.put(1960, "Nigeria");
        countriesLinkedHashMap.put(1776, "United States of America");
        countriesLinkedHashMap.put(1957, "Ghana");

        countriesTreeMap.put(1960, "Nigeria");
        countriesTreeMap.put(1776, "United States of America");
        countriesTreeMap.put(1957, "Ghana");

        System.out.println(countriesHashMap);
        System.out.println(countriesLinkedHashMap);
        System.out.println(countriesTreeMap);
        
        /*
       {1776=United States of America, 1957=Ghana, 1960=Nigeria}
       {1960=Nigeria, 1776=United States of America, 1957=Ghana}
       {1776=United States of America, 1957=Ghana, 1960=Nigeria}
         */
    }
}
```
* The order of elements in HashMap is random.
* The order of elements in LinkedHashMap is by order of insertion.
* The order of elements in the TreeMap is based on the natural order of the keys. It doesn't accept **null** keys.

**(H) Using LinkedHashMap to Implement LRU cache**
* The LinkedHashMap can be used to implement least recently used eviction policy for cache.
* Input/Instantiation **Argument** Explanation:
1. initialCapacity = 16. The initial size of the map.
2. loadFactor = 0.75f. The list capacity will be increased (beyond initial 16) when its reaches 75% of initial capacity.
3. accessOrder = true. Allows iteration over the element in order in which they have been accessed. The most recently\
accessed is moved to the end of the map entries.

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        //Instantiation of the userMap with instantiation arguments
        Map<Integer, String> userMap = new LinkedHashMap<>(16, 0.75f, true);

        userMap.put(1, "Betty");
        userMap.put(2, "Andrew");
        userMap.put(3, "Barbora");
        userMap.put(4, "Camila");

        System.out.println("---Insertion Order----");
        System.out.println(userMap); //{1=Betty, 2=Andrew, 3=Barbora, 4=Camila}

        String betty = userMap.get(1);
        String andrew = userMap.get(2);

        System.out.println("---Access Order----");
        System.out.println(userMap); // {3=Barbora, 4=Camila, 1=Betty, 2=Andrew}
    }
}
```
* Implementation of a CRU cache using LinkedHashMap

```java
package simplepracticeclasses;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache extends LinkedHashMap<Integer, String> {
    private static final long serialVersionUID = 1L;
    private static final long MAX_ENTRIES = 5;

    public LRUCache() {
        //Setting up the constructor based on the arguments of its parent, LinkedHashMap
        //Since no extra argument is specified apart from the parent argument, this will be a default no argument constructor.
        super(5, 0.75f, true);
    }

    //We specify the eviction policy by overriding the removeEldestEntry
    //This specifies when entries from the cache should be evicted. i.e. whenever the size exceeds the MAX_ENTRIES
    @Override
    public boolean removeEldestEntry(Map.Entry<Integer, String> entry) {
        return size() > MAX_ENTRIES;
    }

}
```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        //Instantiation of the userMap with instantiation arguments
        Map<Integer, String> pagesVisitedMap = new LRUCache();

        pagesVisitedMap.put(1, "Home");
        pagesVisitedMap.put(2, "Policy");
        pagesVisitedMap.put(3, "Privacy");
        pagesVisitedMap.put(4, "Car Product");
        pagesVisitedMap.put(5, "EU Policy");
        pagesVisitedMap.put(6, "News");
        pagesVisitedMap.put(7, "World Service");

        System.out.println(pagesVisitedMap); //{3=Privacy, 4=Car Product, 5=EU Policy, 6=News, 7=World Service}
    }
}
```

**(I) TreeMap and Comparator**
* Similar to TreeSet, in order for a TreeMap to store a custom object, it has to either:
1. Use a comparator
2. Use a comparable (in the class)
* Unlike TreeSet, the comparator is used to compare the keys of the map.
* The **comparator** is instantiated as an anonymous class and then parsed as argument while instantiation a TreeMap.
* The comparator can use of lexicographical or numerical order or other methods of hierarchy such as length

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        //A descending order comparator(NUMERICAL order). This is actually an anonymous class
        Comparator<Integer> descendingComparatorInt = new Comparator<Integer>() {
            @Override
            public int compare(Integer i1, Integer i2) {
                return i1.compareTo(i2) * -1;
            }
        };

        //A descending order comparator(ALPHABETICAL order). This is actually an anonymous class
        Comparator<String> descendingComparatorString = new Comparator<String>() {
            @Override
            public int compare(String i1, String i2) {
                return i1.compareTo(i2) * -1;
            }
        };

        //A descending order comparator(ALPHABETICAL order). This is actually an anonymous class
        Comparator<String> lengthComparatorString = new Comparator<String>() {
            @Override
            public int compare(String i1, String i2) {
                return i1.length() - i2.length();
            }
        };


        //Instantiation of the TreeMap with a comparator argument (NUMERICAL order)
        SortedMap<Integer, String> countriesTreeMap = new TreeMap<>(descendingComparatorInt);

        countriesTreeMap.put(6, "United Kingdom");
        countriesTreeMap.put(1, "United States of America");
        countriesTreeMap.put(4, "Germany");
        countriesTreeMap.put(5, "France");
        countriesTreeMap.put(2, "China");
        countriesTreeMap.put(3, "Japan");

        System.out.println(countriesTreeMap); 
        //{6=United Kingdom, 5=France, 4=Germany, 3=Japan, 2=China, 1=United States of America}


        //Instantiation of the TreeMap with a comparator argument (ALPHABETICAL order)
        SortedMap<String, String> artistTreeMap = new TreeMap<String, String>(descendingComparatorString);

        artistTreeMap.put("Da Vinci", "Mona Lisa");
        artistTreeMap.put("Picasso", "Guernica");
        artistTreeMap.put("Salv.Dali", "Persistence of Memory");
        artistTreeMap.put("A-Artist", null);
        artistTreeMap.put("Z-Artist", null);
        
        System.out.println(artistTreeMap); 
        //{Z-Artist=null, Salv.Dali=Persistence of Memory, Picasso=Guernica, Da Vinci=Mona Lisa, A-Artist=null}


        //Instantiation of the TreeMap with a comparator argument (length of the key)
        SortedMap<String, String> artistTreeMap2 = new TreeMap<>(lengthComparatorString);

        artistTreeMap2.put("Da Vinci", "Mona Lisa");
        artistTreeMap2.put("Picasso", "Guernica");
        artistTreeMap2.put("Salv.Dali", "Persistence of Memory");
        artistTreeMap2.put("A-Artistswww", "Idontknow");
        artistTreeMap2.put("Z-Artist", "Idontknow");
        System.out.println(artistTreeMap2); 
        //{Picasso=Guernica, Da Vinci=Idontknow, Salv.Dali=Persistence of Memory, A-Artistswww=Idontknow}
    }
}
```
**(I) TreeMap and Comparable**

TreeMap can hold custom objects as long as comparable or comparator is provisioned. 

**Comparator for Custom Objects**

A comparator is defined and used as argument when instantiating the TreeMap

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        //A comparator for custom objects
        Comparator<Product> productComparator = new Comparator<Product>() {
            @Override
            public int compare(Product prod1, Product prod2) {
                return prod1.getName().toLowerCase().compareTo(prod2.getName().toLowerCase());
            }
        };

        //Using custom object as TreeMap keys
        SortedMap<Product, String> productMap = new TreeMap<>(productComparator);
        productMap.put(new Product("aPhone", "Apple", "Electronics"), "Over Priced");
        productMap.put(new Product("GGALAXY", "Samsung", "Electronics"), "Too many");
        productMap.put(new Product("cPhone", "Apple", "Electronics"), "Over Priced");
        productMap.put(new Product("bGalaxy", "Samsung", "Electronics"), "Too many");
        productMap.put(new Product("FIPHONE", "Samsung", "Electronics"), "Too many");


        for (Map.Entry<Product, String> entry : productMap.entrySet()) {
            System.out.println(entry.getKey().getName());
        }
        /*
        aPhone
        bGalaxy
        cPhone
        FIPHONE
        GGALAXY
         */
    }
}
```
**Comparable for Custom Objects**
* The class implements `Comparable` and override the `compareTo` method.
* A comparator argument doesn't have to be specified.
```java
public class Product implements Comparable<Product> {
  //
  //
  //
  //
  //
  @Override
  public int compareTo(Product o){
    return this.name.compareTo(o.name);
  }
}
```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        SortedMap<Product, Integer> productMap = new TreeMap<>();
        productMap.put(new Product("iPhone", "Apple", "Phone"), 150);
        productMap.put(new Product("galaxy", "Samsung", "Phone"), 120);
        productMap.put(new Product("aPhone", "LG", "Phone"), 80);
        productMap.put(new Product("zPhone", "Google", "Phone"), 140);
        for (Map.Entry<Product, Integer> entry : productMap.entrySet()) {
            System.out.println(entry.getKey().getName());
        }
        /*
        aPhone
        galaxy
        iPhone
        zPhone
         */
    }
}
```

**Some SortedMap Operations:**
1. firstKey()
2. lastKey()
3. headMap()
4. tailMap()
5. subMap()

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        SortedMap<Product, Integer> productMap = new TreeMap<>();

        productMap.put(new Product("iPhone", "Apple", "Phone"), 150);
        productMap.put(new Product("galaxy", "Samsung", "Phone"), 120);
        productMap.put(new Product("lgPhone", "LG", "Phone"), 80);
        productMap.put(new Product("zPhone", "Google", "Phone"), 140);

        //firstKey() and lastKey()
        System.out.format("Name: %s, Brand:%s %n", productMap.firstKey().getName(), productMap.firstKey().getBrand()); 
        //Name: galaxy, Brand:Samsung
        System.out.println(productMap.lastKey().getName()); //zPhone

        SortedMap<Integer, String> someMap = new TreeMap<>();
        someMap.put(5, "Apple");
        someMap.put(3, "Orange");
        someMap.put(1, "Banana");
        someMap.put(2, "Grape");
        someMap.put(4, "Lemon");
        System.out.println(someMap); //{1=Banana, 2=Grape, 3=Orange, 4=Lemon, 5=Apple}

        //Sub-setting
        //headMap() returns all keys that are strictly less than specified value(a value corresponding to the key).
        System.out.println(someMap.headMap(6)); //{1=Banana, 2=Grape, 3=Orange, 4=Lemon, 5=Apple}
        System.out.println(someMap.headMap(3)); //{1=Banana, 2=Grape}

        //tailMap() returns all keys that are greater than or equal to a specified value
        System.out.println(someMap.tailMap(3)); //{3=Orange, 4=Lemon, 5=Apple}

        //SubMap().....bracketed sub-setting
        System.out.println(someMap.subMap(2, 5)); //{2=Grape, 3=Orange, 4=Lemon}
    }
}
```

## 7: Generics in Java: Creating Classes and Methods Using Generics
* Generics allow the extension of Java type system to allow a class or method to operate on objects of various types while providing\
compile time safety
* Generics allow for code reuse since same class or methods can be used with different type of data.
* Generics convert runtime check to compile time check, thus making the code less error-prone.
* Re-usability and maintainability(run time --> compile time conversion) are the main advantages of using generics.
* Java generics programming enables types (classes and interfaces) to be parameters when defining other classes, interfaces and methods.

```java
package simplepracticeclasses;

public class ValueHolder {
    private Integer value;

    public ValueHolder(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.format("Integer repository contains %d", value);
    }
}
```
* The code shows the limitation & non re-usability of non-generic codes. The class can't be used for any type that is not Integer.
* While the class can be rewritten to make use of Object, this will lead to loss of compile time check.
* While the class can be rewritten with several constructor, but it will also lead to confusion as to which constructor was invoked.

**(A) Implementing Classes with Generic Type Parameters**
* Generics coding enables using same class to work with data/objects/parameters of different type. A **generic class** is any class\
that supports a **parametrized** type.
1. The notation **<T>** represents parameterization. 
2. The letter **T** is a placeholder for different data types that can be held within the class. It's known as **Type Variable/Type Parameter**.

T is convention, any letter or word can be used. The placeholder will be replaced with the actual data type when instantiating\
the generic class.

```java
package simplepracticeclasses;

//Generic class with a placeholding parameter T
public class GenericValueHolder<T> {
    public T value;

    public void echoValue() {
        System.out.println("Value:" + value + ". Class: " + value.getClass());
    }
}
```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        //Instantiating the generic class "GenericValueHolder"
        GenericValueHolder<String> stringValueInstance = new GenericValueHolder<>();
        stringValueInstance.value = "Aurora";
        stringValueInstance.echoValue(); //Value:Aurora. Class: class java.lang.String

        GenericValueHolder<Integer> integerValueInstance = new GenericValueHolder<>();
        integerValueInstance.value = 200;
        integerValueInstance.echoValue(); //Value:200. Class: class java.lang.Integer

        GenericValueHolder<Date> dateValueInstance = new GenericValueHolder<>();
        dateValueInstance.value = Calendar.getInstance().getTime();
        dateValueInstance.echoValue(); //Value:Wed Aug 10 18:33:53 CEST 2022. Class: class java.util.Date

        //Custom Object from the class "Product"
        GenericValueHolder<Product> productValueInstance = new GenericValueHolder<>();
        productValueInstance.value = new Product("iWatch", "Apple", "Electronics");
        productValueInstance.echoValue(); 
        //Value:Product{name='iWatch', brand='Apple', category='Electronics'}. Class: class practice.Product
        System.out.println(productValueInstance.value.getName()); //iWatch
    }
}
```
**(B) Using Generics Types for Parameters and Return Types** 
* Entire generic class with constructor, getters setters, and overridden toString method.

```java
package simplepracticeclasses;

public class Repository<T> {
    public T value;

    public Repository(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("Value:%s, Class:%s", value, value.getClass());
    }
}

```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        Repository<Integer> integerValue = new Repository<>(5);
        System.out.println(integerValue); //Value:5, Class:class java.lang.
        integerValue.setValue(76);
        System.out.println(integerValue.getValue()); //76

        Repository<Date> dateValue = new Repository<>(Calendar.getInstance().getTime());
        Date now = dateValue.getValue();
        System.out.println(now); //Wed Aug 10 19:12:02 CEST 2022

        //Custom Objects
        Repository<Product> phoneValue = new Repository<>(new Product("Galaxy Z", "Samsung", "Phone"));
        String phoneName = phoneValue.getValue().getName();
        System.out.println(phoneName); //Galaxy Z
    }
}
```
**!!**
1. T is **non-static**. T is associated with an object not the class. Hence, **T** can't be declared as static.
2. T is strictly a **placeholder**. Hence, initial assignment to T is not permitted.`public T intValue = 10` This is not permitted. 
3. If an initial value is very much required, casting can be done as shown below with a suppression annotation.\
This should be avoided though. Type casting should generally be avoided when possible.
```java
public class SomeGenericClass<T> {
  @SuppressWarnings("unchecked")  
  public T intValue = (T) 10; //type casting to generic type parameter "T"
}
```

**(C) Constructing Raw Objects from Generic Classes**

Backward compatibility: It's possible to instantiate objects of generic class without explicitly stating the type parameter at instantiation\
of the object. Generic was introduced in 2004 as part of Java 5. Hence, there are codes that have been written without using generics.\
This gives the need for the possibility of instantiating generic classes without explicitly stating the type of T (i.e. instantiation of\
generic class in same manner a non-generic class. -->raw use of parametrized class.)\
NOTE: While raw use of parametrized class exists because of backward compatibility, it leads to loss of _compile time check_. Hence,\
it should be avoided.


**(D) Generic Methods**

Multiple type parameters can be specified for generic classes.

```java
package simplepracticeclasses;

import java.util.Map;

public class MapHelper<K, V> {

    //Generic method that adds arrays of keys K[] and values V[] to a map<K, V>
    public void addEntries(Map<K, V> map, K[] keys, V[] values) {
        int index = 0;
        for (K key : keys) {
            map.put(key, values[index]);
            index++;
        }
    }

    public void printEntries(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }
}
```


```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        Map<Integer, String> dataHoldingMap = new HashMap<>();
        Integer[] studentIds = {22, 76, 92,};
        String[] studentUserName = {"betty", "shola", "rory", "jarda"};

        MapHelper<Integer, String> mapHelperInstance = new MapHelper<>();
        mapHelperInstance.addEntries(dataHoldingMap, studentIds, studentUserName);
        mapHelperInstance.printEntries(dataHoldingMap);
        
        /*
        22=betty
        76=shola
        92=rory
         */
    }
}
```

**Generic Static Methods Definition:**
1. In the above MapHelper, the entire class is generic. It's possible to make specific methods generic.
2. If the methods of a class are parameterized, there might be no need to have the class itself parameterized.\
Hence, if all methods are made generic, there is no need to make the class itself generic.
3. Note the **static** nature of the generic methods.
4. **NOTE:** If the methods are _static_ generic, we don't need to instantiate the class. (See the main class below)\

```java
package simplepracticeclasses;

import java.util.Map;

public class MapHelper {

    //GENERIC METHODS
    public static <K, V> void addEntries(Map<K, V> map, K[] keys, V[] values) {
        int index = 0;
        for (K key : keys) {
            map.put(key, values[index]);
            index++;
        }
    }

    public static <K, V> void printEntries(Map<K, V> map) {
        for (Map.Entry<K, V> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }
}
```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        Map<Integer, String> dataHoldingMap = new HashMap<>();
        Integer[] studentIds = {22, 76, 92,};
        String[] studentUserName = {"betty", "shola", "rory", "jarda"};

        //NOTE: Since the method is static, we don't need to instantiate the class itself before using its methods.
        MapHelper.addEntries(dataHoldingMap, studentIds, studentUserName);

        MapHelper.printEntries(dataHoldingMap);
        /*
        22=betty
        76=shola
        92=rory
         */
    }
}
```
**(E) Type Inference for Parameterized Methods**
* In the code below, the `displayElement` method, Java is able to infer the type of the parameter. (see main method)
* The drawback is that Java assumes both elements are of same type even when they are not. (See last invocation of the method)
* Hence, it's better to use different placeholders for the parameter type (see `displayElement2` method)

```java
package simplepracticeclasses;

public class MapHelper {
    //GENERIC METHODS
    //Type T applies to both elements(1 and 2). Hence, we expect them to be of same type.
    public static <T> void displayElement(T element1, T element2) {
        System.out.println("\n--Some Elements--");
        System.out.format("Element 1: %s, Element2: %s", element1, element2);
    }

    //A better and more type safe version of the method above.
    public static <T, S> void displayElement2(T element1, S element2) {
        System.out.println("\n--Some Elements--");
        System.out.format("Element 1: %s, Element2: %s", element1, element2);
    }
}
```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        MapHelper.displayElement("Hey", "There");
        
        MapHelper.displayElement(new Date(), Calendar.getInstance().getTime());
        
        MapHelper.displayElement(new Product("iPhone 13", "Apple", "Smart Phone"),
                                new Product("Galaxy Z", "Samsung", "Smart Phone")
                                );

        /*
        --Some Elements--
        Element 1: Hey, Element2: There
        --Some Elements--
           Element 1: Sat Aug 13 10:41:29 CEST 2022, Element2: Sat Aug 13 10:41:29 CEST 2022
        --Some Elements--
        Element 1: Product{name='iPhone 13', brand='Apple', category='Smart Phone'
        }, Element2: Product{name='Galaxy Z', brand='Samsung', category='Smart Phone'}
         */

        MapHelper.displayElement("Hello", 98);
        /*
        --Some Elements--
        Element 1: Hello, Element2: 98
         */

        MapHelper.displayElement2("Hello", 98);
        /*
        --Some Elements--
        Element 1: Hello, Element2: 98
         */
    }
}
```  

* Parametrized/generic methods can also be set to accept input arguments that are of the **varargs** type
* Varargs type are specified by the data type followed by ... (T... elements). The type T can be different for all elements.
* Note the _SafeVarargs_ annotation

```java
package simplepracticeclasses;

public class MapHelper {
    //GENERIC METHODS
    //Variable type T
    @SafeVarargs
    public static <T> void displayElement3(T... elements) {
        for (T element : elements) {
            System.out.println(element);
        }
    }
}
```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        Integer[] tempArray = {26, 13, 28};
        MapHelper.displayElement3(tempArray);
        MapHelper.displayElement3("Shola", 14.6, new Integer[]{5, 6, 7}, new Date(), 'A');

        /*
        ----
        26: class java.lang.Integer
        13: class java.lang.Integer
        28: class java.lang.Integer
        ----
        Shola: class java.lang.String
        14.6: class java.lang.Double
        [Ljava.lang.Integer;@135fbaa4: class [Ljava.lang.Integer;
         Sat Aug 13 11:14:52 CEST 2022: class java.util.Date
        A: class java.lang.Character
        */
    }

}
```
* Another example

```java
package simplepracticeclasses;

import java.util.Map;

public class MapHelper {
    //A generic method that checks for map equality
    public static <K, V> boolean mapEquals(Map<K, V> map1, Map<K, V> map2) {
        //if both maps have different size then immediately return false
        if (map1.entrySet().size() != map2.entrySet().size()) {
            return false;
        }

        for (Map.Entry<K, V> entry : map1.entrySet()) {

            //using the key of map1 to get the corresponding value of map2
            V map2value = map2.get(entry.getKey());

            //comparing (NOT equal comparison) the value of map2 (i.e. V) above to the current value of map1
            if (!entry.getValue().equals(map2value)) {
                return false;
            }

        }
        return true;

    }
}
```

```java
package simplepracticeclasses;

import java.util.*;

public class AppaMain {
    public static void main(String[] args) {

        Map<Integer, String> mapA = new HashMap<>();
        mapA.put(11, "Cars");
        mapA.put(22, "Books");
        mapA.put(33, "Houses");
        mapA.put(44, "Boats");


        Map<Integer, String> mapB = new HashMap<>();
        mapB.put(11, "Cars");
        mapB.put(22, "Books");
        mapB.put(33, "Houses");
        mapB.put(44, "Boat");

        boolean comparisonResult = MapHelper.mapEquals(mapA, mapB);
        System.out.println(comparisonResult); //false
    }

}
```

## 8: Generics in Java: Bounded Type Parameters & Wildcards
* **Unbounded type parameters** in generics have no constraints. The type parameter can be any type of object.
* **Bounded type parameters** have constraints on the type parameter. The constraints can include condition on whether the type\
parameter must derive from a certain base class or implement a certain interface e.t.c.
* **Wild card** is a special kind of type arguments that controls the type safety of generic types. It controls what kind of type\
parameter should be used with a generic method.

**(A) Recognizing Unbounded Type Parameters Disadvantages.**

```java
package simplepracticeclasses;

public class MathUtili {
    public static <T> boolean isEven(T element) {

        //if the input T element is not castable to number, a ClassCastException will occur
        Number number = (Number) element;
        return number.doubleValue() % 2 == 0;
    }

    public static <T> boolean isPrime(T element) {
        Number number = (Number) element;
        
        int num = number.intValue() / 2;
        for (int i = 2; i <= num; i++) {
            //if any of the number before the actual element(number) is divisible(with modulus =0) by any number(i), 
                // then it is not prime
            if (number.intValue() % i == 0) {
                return false;
            }
        }
        return true;
    }
}
```
* The first 5 calls to the generic method succeeded because the provided arguments are castable to type **Number.**
* Both integer and big integer derive from the common base class **Number**. Hence, they are **castable** to **Number**. 
* The invocation to a non-castable data type argument (See UNSUCCESSFUL CALL) e.g. String, results in a **ClassCastException**.
* ClassCastException is a run-time exception and difficult to deal with it. _The compiler is unable to flag this type of error._
* Hence, the loss of compile-time checking. This shows the need for bounded types and wildcards.
* The specification <T> implicitly means that the only requirement is that T extends Object. Hence, T is **Unbounded**.

```java
package simplepracticeclasses;

import java.math.BigInteger;
import java.util.*;

public class AppaMain {
    public static void main(String[] args) {
        //SUCCESSFUL CALLS
        System.out.println(MathUtili.isEven(32)); //true
        System.out.println(MathUtili.isEven(53)); //false
        System.out.println(MathUtili.isEven(BigInteger.valueOf(5000000))); //true
        //UNSUCCESSFUL CALL
        System.out.println(MathUtili.isEven("String")); //ClassCastException

        //SUCCESSFUL CALLS
        System.out.println(MathUtili.isPrime(73)); //true
        System.out.println(MathUtili.isPrime(60)); //false

        //UNSUCCESSFUL CALLS
        System.out.println(MathUtili.isPrime("String")); //ClassCastException
        System.out.println(MathUtili.isEven(new Date())); //ClassCastException
    }
}
```
**(B) Specifying Bounded Type Parameters.**
* Bounded types provides the full power of type safety offered by generic types.
* The **unbounded** type parameter is constrained to obtain a **bounded** type parameter.
* Bounded type parameter provides additional layer for type safety checks. Hence, the compile-time check is retained.
* Note the **extends** in the specification of the type parameter. This specification is known as **bounding**.
* With this change, the compiler and IDE is able to flag this error. 
* Since the type parameter is bounded, the casting in the previous methods can be removed as shown below.

```java
package simplepracticeclasses;

public class MathUtili {
    public static <T extends Number> boolean isEven(T element) {
        return element.doubleValue() % 2 == 0;
    }

    public static <T extends Integer> boolean isPrime(T element) {
        int num = element.intValue() / 2;
        for (int i = 2; i <= num; i++) {
            //if any of the number before the actual element(number) is divisible(with modulus =0) by any number(i), 
            // then it is not prime
            if (element.intValue() % i == 0) {
                return false;
            }
        }
        return true;
    }
}
```
**(C) Defining Classes with Bounded Type Parameters**
* To compare two instances of the generic Box class(e.g. using the _getLarger_ method), the generic class can implement\
the `Comparable` interface.
* Two objects of a class can be compared with one another if the class implements the _Comparable_ interface.
* Hence, the generic class can be **bounded** with a condition that it implements the _Comparable_ interface. 
* Note the use of (**extends**) instead of (**implements**). Also, `Comparable` interface specification is generic bcos the\
comparison has to be made with objects of same type.
* Making the type parameter extend the Comparable interface ensures that only data types that implements the interface\
can be used as type parameter in the Box generic class.

```java
package simplepracticeclasses;

//The specification of type parameter is such that only data/argument types that implements the 'Comparable' interface
//...can be used as type parameter
public class Box<T extends Comparable<T>> {
    private T value;

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("In the box %s %n", value);
    }
}
```
**NOTE:!!** ALL parameters of the Box generic class must implement the Comparable interface. This is bcuz the boundary\
condition is set at the highest level

```java
package simplepracticeclasses;

public class AppaMain {

    //The specification of type parameter is such that only data/argument types that implements the 'Comparable' interface
    //...can be used as type parameter
    private static <T extends Comparable<T>> Box<T> getLarger(Box<T> box1, Box<T> box2) {
        if (box1.getValue().compareTo(box1.getValue()) > 0) {
            return box1;
        } else {
            return box2;
        }
    }

    public static void main(String[] args) {
        Box<Integer> box1 = new Box<>();
        box1.setValue(67);
        Box<Integer> box2 = new Box<>();
        box2.setValue(89);

        Box<Integer> returnedBox = getLarger(box1, box2);
        System.out.println(returnedBox); //In the box 89
    }
}
```
**(D) Using Bounded Types with Custom Objects**
* In **Section C** all the type parameter specified for the custom`Box` class objects are built-in java data types and these data\
types implements `Comparable` interface (i.e. Integers, Double). Hence, it's possible to apply the custom method `getLarger` to them. 
* Any custom objects that will be make use of the `getLarger` methods must also implement the Comparable interface.
* **Reminder:** The Box generic class is able to hold any object (either custom or otherwise) as long as the object implements\
the `Comparable `interface. Hence, the bound of the Box generic class is that its parameters implements the `Comparable` interface.

```java
package simplepracticeclasses;

public class Car implements Comparable<Car> {
    public String name;
    public String make;
    public Long price;

    public Car(String name, String make, Long price) {
        this.name = name;
        this.make = make;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Name:%s, Make:%s, Price:$ %s", name, make, price);
    }

    //Implementation of the generic compareTo
    @Override
    public int compareTo(Car o) {
        return this.price.compareTo(o.price);
    }
}
```
* In the example, the `Car` class needs to implement the generic form of the `Comparable` interface as a requirement for\
it to be holdable as a parameter within the generic `Box` class. The object of the `Box` class holding a comparable object such\
as the `Car` object can then be used with the `getLarge` method.

```java
package simplepracticeclasses;

public class AppaMain {
    //The specification of type parameter is such that only data/argument types that implements the 'Comparable' 
    // interface can be used as type parameter
    private static <T extends Comparable<T>> Box<T> getLarger(Box<T> box1, Box<T> box2) {
        if (box1.getValue().compareTo(box1.getValue()) > 0) {
            return box1;
        } else {
            return box2;
        }
    }

    public static void main(String[] args) {
        Box<Car> carBox1 = new Box<>(new Car("Model Y", "Tesla", 78000L));

        Box<Car> carBox2 = new Box<>(new Car("S-class", "Mercedes", 120000L));

        Box<Car> returnedBox = getLarger(carBox1, carBox2);
        System.out.println(returnedBox); //In the box Name:S-class, Make:Mercedes, Price:$ 120000
    }
}
```
* It is possible to make the Box class itself implement the Comparable interface as shown below.
```java
package simplepracticeclasses;

//The specification of type parameter is such that only data/argument types that implements the 'Comparable' interface
//...can be used as type parameter
public class Box<T extends Comparable<T>> implements Comparable<Box<T>> {
    
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("In the box %s %n", value);
    }

    //Implementation of compareTo method of the Comparable interface
    @Override
    public int compareTo(Box<T> o) {
        if (this.value.compareTo(o.value) > 1) {
            System.out.println(this.getValue());
            return this.value.compareTo(o.value);
        } else {
            System.out.println(o.getValue());
            return this.value.compareTo(o.value);
        }
    }
}
```

```java
package simplepracticeclasses;

public class AppaMain {
    //The specification of type parameter is such that only data/argument types that implements the 'Comparable' interface
    //...can be used as type parameter
    private static <T extends Comparable<T>> Box<T> getLarger(Box<T> box1, Box<T> box2) {
        if (box1.getValue().compareTo(box1.getValue()) > 0) {
            return box1;
        } else {
            return box2;
        }
    }

    public static void main(String[] args) {
        Box<Car> carBox1 = new Box<>(new Car("Model Y", "Tesla", 78000L));

        Box<Car> carBox2 = new Box<>(new Car("S-class", "Mercedes", 120000L));

        //Using the 'Box' class own implementation of the Comparable interface
        System.out.println(carBox1.compareTo(carBox2));
        /*
        Name:S-class, Make:Mercedes, Price:$ 120000
        -1
         */
    }
}
```
* Another example with two parameters. The class itself implements Comparable. While requiring its arguments do the same. 
```java
package org.example;

public class MathUtili < J extends Comparable<J>, K extends Comparable<K> > implements Comparable< MathUtili <J, K> > {

    J data1;
    K data2;
    
    @Override
    public int compareTo(MathUtili<J, K> other){
        
        int result = this.data1.compareTo(other.data1);
        
        if(result != 0){
            return  result;
        }
        
        return this.data2.compareTo(other.data2);

    }

}
```
**(E) Using Multiple Bound Specifications For Bounded**
* A generic class can have parameter(s) type that extends several interfaces/classes.This is known as **multiple bounds.**

```java
package simplepracticeclasses;
//An interface that fine prints the `toString` method of an object of a class.
public interface PrettyPrintable {
    void prettyPrint();
}
```

* The Car class is modified to implement the PrettyPrint interface above.

```java
package simplepracticeclasses;

public class Car implements Comparable<Car>, PrettyPrintable {
    public String name;
    public String make;
    public Long price;

    public Car(String name, String make, Long price) {
        this.name = name;
        this.make = make;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("Name:%s, Make:%s, Price:$ %s", name, make, price);
    }

    //Implementation of the generic compareTo
    @Override
    public int compareTo(Car o) {
        return this.price.compareTo(o.price);
    }

    @Override
    //This prints out the toString representation of a Car object surrounded by +++*
    public void prettyPrint() {
        System.out.println("Pretty Print Implementation of Car Object");
        System.out.println("+++* " + this + " *+++ \n");
    }
}
```
* The generic `Box` class below has type parameter with condition that the type implements both Comparable and PrettyPrintable interfaces.
* The generic `Box` class itself also implements both `Comparable` and `PrettyPrintable` interfaces.

```java
package simplepracticeclasses;

/**
 * The specification of type parameter is such that only data/argument types that implements the 'Comparable' interface
 * ..and also the 'PrettyPrintable' can be used as type parameter.
 * 
 */

public class Box<T extends Comparable<T> & PrettyPrintable> implements Comparable<Box<T>>, PrettyPrintable {
    private T value;

    public Box(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return String.format("In the box %s %n", value);
    }

    //Implementation of compareTo method of the Comparable interface
    @Override
    public int compareTo(Box<T> o) {
        if (this.value.compareTo(o.value) > 1) {
            System.out.println(this.getValue());
            return this.value.compareTo(o.value);
        } else {
            System.out.println(o.getValue());
            return this.value.compareTo(o.value);
        }

    }

    @Override
    //This prints out the toString representation of a Car object surrounded by +++*
    public void prettyPrint() {
        System.out.println("PrettyPrint Implementation of the Box generic class");
        System.out.println("+++*-- " + value + " --*+++ \n");
        value.prettyPrint();
    }
}

```
* Note the specification of multiple bounds using the extends.

```java
package simplepracticeclasses;

public class AppaMain {
    //The specification of type parameter is such that only data/argument types that implements the 'Comparable' 
    // interface can be used as type parameter
    private static <T extends Comparable<T> & PrettyPrintable> void prettyPrintLarger(Box<T> box1, Box<T> box2) {
        if (box1.getValue().compareTo(box1.getValue()) > 0) {
            box1.prettyPrint();
        } else {
            box2.prettyPrint();
        }
    }

    public static void main(String[] args) {

        Box<Car> carBox1 = new Box<>(new Car("Model Y", "Tesla", 78000L));

        Box<Car> carBox2 = new Box<>(new Car("S-class", "Mercedes", 120000L));

        prettyPrintLarger(carBox1, carBox2);

        /*
        PrettyPrint Implementation of the Box generic class
        +++*-- Name:S-class, Make:Mercedes, Price:$ 120000 --*+++

        Pretty Print Implementation of Car object
        +++* Name:S-class, Make:Mercedes, Price:$ 120000 *+++
         */
    }
}
```

**(F) Constraining Types Using Upper-Bounded WildCards**
* In java, the question mark character (**?** ) is referred to as wildcard character. It can be used to represent an unknown type\
parameter in generic class.
* It is useful when the type parameter of a return operation has several possibilities or unknown.
* There are three wildcards used for generics: **Upper bounded**, **Unbounded** and **Lower bounded**. Upper bounded wild cards are\
the most commonly used wildcard.

```java
package simplepracticeclasses;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class AppaMain {

    private static double computeAverageScore(@NotNull Map<String, ? extends Number> studentMap) {
        double totalScore = 0;
        int count = 0;

        for (Map.Entry<String, ? extends Number> entry : studentMap.entrySet()) {
            totalScore += entry.getValue().doubleValue();
            count++;
        }

        return totalScore / count;
    }

    private static <T extends Number> double computerAverageScoreBounded(Map<String, T> studentMap) {

        double totalScore = 0;
        int count = 0;

        for (Map.Entry<String, T> entry : studentMap.entrySet()) {
            totalScore += entry.getValue().doubleValue();
            count++;
        }

        return totalScore / count;

    }


    public static void main(String[] args) {

        Map<String, Number> studentDataInt = new HashMap<>();
        Map<String, Number> studentDataFloat = new HashMap<>();

        studentDataInt.put("Timor", 89);
        studentDataInt.put("Sedar", 53);
        studentDataInt.put("Matthew", 92);
        studentDataInt.put("John", 48);

        System.out.println("Average Scores: " + computeAverageScore(studentDataInt));
        //Average Scores of students : 70.5

        studentDataFloat.put("Timor", 89.6F);
        studentDataFloat.put("Sedar", 53.4F);
        studentDataFloat.put("Matthew", 92.8F);
        studentDataFloat.put("John", 48.1F);

        System.out.println("Average Scores: " + computeAverageScore(studentDataFloat));
        //Average Scores: 70.97500038146973

        System.out.println("Average Scores Using Bounded Type Method: " + computerAverageScoreBounded(studentDataFloat));
        //Average Scores Using Bounded Type Method: 70.97500038146973
    }
}
```
* Wildcard stating that any object that extends number is permitted is specified both in the _function_ and _forloop_ definition.
* This enables the use of the function for type parameters such as float, integer, double e.t.c. Hence, code reuse.
* This kind of wildcard is known as **Upper bounded wildcard**. It's **similar** to the **bounded type parameters**.\
See `computeAverageScoreBounded` for comparison.
* The main **difference** between Upper bounded wildcard and the bounded type parameters is that parameter denoted by **?**\
in the wildcard is unknown. While the parameter **T** in the bounded type parameter is known. The only information **?**\ 
wildcard provides about T is its upper bound.
* The code below aims to show the difference.

```java
package simplepracticeclasses;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AppaMain {

    private static double computeAverageScore(@NotNull Map<String, ? extends Number> studentMap) {
        double totalScore = 0;
        int count = 0;

        for (Map.Entry<String, ? extends Number> entry : studentMap.entrySet()) {
            totalScore += entry.getValue().doubleValue();
            count++;
        }

        return totalScore / count;
    }


    public static <T extends Number> List<T> getScoresList(Map<String, T> studentMap) {
    //public static List<? extends Number> getScoresList(Map<String ? extends Number> studentMap){

        List<T> scoresList = new ArrayList<>();
        //List<? extends Number> scoresList = new ArrayList<>();
        
        for (Map.Entry<String, T> entry : studentMap.entrySet()) {

            //With 'Bounded Parameter Type Parameters', T is known and accessible.
            T score = entry.getValue();
            //Object score = entry.getValue() --> Wildcard method shall reference to an Object type since T is unknown

            scoresList.add(score);
            //With Wildcard, this is not possible. Bcuz the wildcard definition of 'scoresList' requires an Object
        }
        return scoresList;
    }


    public static void main(String[] args) {

        Map<String, Number> studentDataInt = new HashMap<>();

        studentDataInt.put("Timor", 89);
        studentDataInt.put("Sedar", 53);
        studentDataInt.put("Matthew", 92);
        studentDataInt.put("John", 48);

        List<Number> scores = getScoresList(studentDataInt);
        System.out.println(scores); // [53, 92, 48, 89]

    }
}
```
* In most cases, it's preferable to use **Bounded type parameters** instead of **Upper bounded wildcard,** if possible.

**(G) Using Unconstrained Variables and Unbounded WildCards:**
* Unbounded wildcards are very useful under two circumstances:
1. When the method being used can be implemented using functionality from the Object base class. (i.e. when the exact type\
is not needed.)
2. When the code of the method doesn't depend on the exact type of the arguments of the method. (e.g. clear of all\
objects stored in a collections such as list, array e.t.c)
* A case where the type of type being worked with is of no interest. Hence, it is sufficient to work with base class Object.
* The ? specifies that the only requirement is the base class java.lang.Object. ? can also be replaced with just Object.
```java
import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {
  public static List<Object> getScoresList(Map<String, ?> studentMap) {

    List<Objects> scoresList = new ArrayList<>();

    for (Map.Entry<String, ?> entry : studentMap.entrySet()) {

      Object score = entry.getValue();

      scoresList.add(score);
    }
    return scoresList;
  }


  public static void main(String[] args) {

    Map<String, Object>studentDataObject = new HashMap<>();
    
    studentDataObject.put("Timor", 89);
    studentDataObject.put("Sedar", 53);
    studentDataObject.put("Matthew", 92);
    studentDataObject.put("John", 48);
    
    List<Object> scoreList = getScoresList(studentDataObject);
    
    //Java considers the values of the map 'studentDataObject' as objects even though they are specifically integers.

  }
}  
```
* Another example where the type is of no relevance.
```java
import java.util.ArrayList;
import java.util.HashMap;

public class Utilities {
    
  public static void printMapEntries(Map<?, ?> studentMap) {
      
    for (Map.Entry<?, ?> entry : studentMap.entrySet()) {
      System.out.format("Key: %s, Value:%s", entry.getKey(), entry.getValue());
    }
  }
}  
```
* It is possible to combine both Unbounded and Upper Bounded Wildcard. 
* The key of the map can be any type that derives from the base class Object. 
* The value of the map can be any type but must extend the base class Number.
```java
public class Utilities {
  private static double computeAverageScore(@NotNull Map<?, ? extends Number> studentMap) {
    double totalScore = 0;
    int count = 0;

    for (Map.Entry<?, ? extends Number> entry : studentMap.entrySet()) {
      totalScore += entry.getValue().doubleValue();
      count++;
    }

    return totalScore / count;
  }
}
```

**(H) Using Unbounded Wildcards in Class Objects.**
* Every class defined in Java have an accessible class Object.
* The class Object of a class provides metadata information about the class. 
* The class Object of a class can be used in reflective access to other member variables and methods of the class. 
```java
public class AppMain {
  public static void main(String[] args) {

    //This give access to a class object of String.
    Class<String> stringClass = String.class;
    //Class<?> stringClass = String.class;  <-- also valid

    //Some metadata of the class object
    System.out.println("\nName: " + stringClass.getName());
    System.out.println("\nPackage: " + stringClass.getPackage());
    System.out.println("\nSuperclass: " + stringClass.getSuperclass());
    System.out.println("\nIs Interface: " + stringClass.isInterface());

        /*
        Name: java.lang.String
        Package: package java.lang, Java Platform API Specification, version 1.8
        SuperClass: class java.lang.Object
        Is Interface: false
         */


    Class<Date> dateClass = Date.class;
    //Some metadata of the class object
    System.out.println("\nName: " + dateClass.getName());
    System.out.println("\nPackage: " + dateClass.getPackage());
    System.out.println("\nSuperclass: " + dateClass.getSuperclass());
    System.out.println("\nIs Interface: " + dateClass.isInterface());

        /*
        Name: java.util.Date
        Package: package java.util, Java Platform API Specification, version 1.8
        Superclass: class java.lang.Object
        Is Interface: false
         */
  }
}
```
**(I) Lower-bounded WildCards.**
* **Refresher:** ArrayList is an instanceof List, Collections and Iterable. In that hierarchy.
```java
public class MathUtili{
  public static void printMapEntries(Map<String, ? super ArrayList<String>> map){
    for(Map.Entry<String, ? super ArrayList<String>> entry: map.entrySet()){
      System.out.println(entry);
    }
  }
}
```
In the method printMapEntries:
1. The key of the map must be of type String
2. The value is lower-bounded wildcard with condition that the type can either be **(a)** an ArrayList of String or **(b)** any\
class that is a **superclass** or parent class of ArrayList of String. Hence, maps with **Collections**, **Iterables**, **List**\ 
or the overall base class java.lang.Object is a valid type for value of the map.
```java
public class AppMain{
  public static void main(String[] args) {

    ArrayList<String> student1Courses = new ArrayList<>();
    Collections.addAll(student1Courses, "Maths", "Physics");

    ArrayList<String> student2Courses = new ArrayList<>();
    Collections.addAll(student2Courses, "Philosophy", "History", "Economics");

    List<String> student3Courses = new ArrayList<>();
    Collections.addAll(student3Courses, "Biology", "Agriculture");

    ArrayList<String> student4Courses = new ArrayList<>();
    Collections.addAll(student4Courses, "Chemistry", "Biology", "Maths");

    Map<String, ArrayList<String>> courseMap = new HashMap<>();
    courseMap.put("student1", student1Courses);
    courseMap.put("student2", student2Courses);

    MathUtili.printMapEntries(courseMap);
        /*
        student2=[Philosophy, History, Economics]
        student1=[Math, Physics]
         */

    Map<String, List<String>> courseMapList = new HashMap<>();
    //Also possible --> Map<String, Collection<String>> courseMapList = new HashMap<>();
    //Also possible --> Map<String, Iterable<String>> courseMapList = new HashMap<>();
    //Also possible --> Map<String, Object<String>> courseMapList = new HashMap<>();
    courseMapList.put("student3", student3Courses);
    courseMapList.put("student4", student4Courses);

    MathUtili.printMapEntries(courseMapList);
        /*
        student4=[Chemistry, Biology, Maths]
        student3=[Biology, Agriculture]
         */
  }
}
```
* The code below works well bcuz ArrayList was used. 
* It will not work with LinkedList but will work with a List or Collections type by changing the Map definition as shown.
* A **refresher** on unbounded type parameter:
```java
public class Utilities {
  public static <T> void swap(List<T> list, int index1, int index2) {
    //A method that swaps to given elements of a given list.
    T firstElementValue = list.get(index1);
    T secondElementValue = list.get(index2);

    list.set(index1, secondElementValue);
    list.set(index2, firstElementValue);
  }
}
```


**(J) Recognizing Java's use of Wildcard Capture**
* While use of wildcards makes java very powerful, it often results errors that are hard to understand and deal with.
* Most of the errors are associated with wildcard capture.
* **Wildcard capture:** is the ability of java to infer the type of data being worked with, and handle the data correctly.\
This is the _type inference ability_ of the java compiler. 


**(K) Naming conventions for type parameter**
* Generally, use single uppercase letter
* **N** for numerical data, **T** for general
* Type parameter can't start with a digit but digit can be included.
* Not all special character are supported e.g. @ and # are not supported. _, $ is supported.

**(L) Java Compiler's Use of Type Erasure**
* Java uses Type Erasure to support generic programming. 
* **Type Erasure** is the process that the compiler follows where it replaces a generic parameter with an actual type.
* The generic code is only accessible to the java compiler. The JVM knows nothing about generics. Hence, at run time,\
generics doesnt exist.
* The java compiler replaces the generic type parameters with objects or bridge methods before runtime. Hence, the byte code\
produced by generic codes is same as non-generic codes.
* This replacement process is known as Type Erasure.


## 9: Classes in Java: Working with Static Nested, Inner, & Local Classes
* **Static nested** and **Inner** classes are used in cases where the classes have a logical relationship or are intimately associated\
with the outer class which they are defined.\
Inner classes can **ONLY** be instantiated with reference to an object of their outer class. 
* **Local** classes are classes defined within a scope and can only be accessed and used within that scope. They are tools used to\
limit the visibility of a class. Local classes are created within a code block.\
Access modifiers do not apply to local classes as local classes are only visible within the block of code where they are defined.

**(A) Working with Static Nested Classes in Java**
* A static nested class is a nested class defined within an outer class. Thus, logically associated with the outer class.
* Instead of defining the class in a separate _.java_ file, nested classes resides inside another class in thesame _.java_ file.
* Static nested classes are used when classes belong together, or are used together or can logically reside within the\
context of another class.

```java
package simplepracticeclasses;

public class Category {

    private final long id;
    private String name;

    public Category(String name) {
        this.id = Math.round(Math.random() * 1000);
        this.name = name;
    }

    public void updateCategory(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return String.format("Category Name: %s, Category ID: %d", name, id);
    }
}
```

```java
package simplepracticeclasses;

public class Products {
    private final long id;
    private String name;
    private int price;

    private Category category;

    public Products(String name, int price, String categoryName) {
        this.id = Math.round(Math.random() * 10000);
        this.name = name;
        this.price = price;

        //instance of Category class.
        this.category = new Category(categoryName);
    }

    public void updateCategory(String name) {
        //update the particular category instance associated with the particular product instance
        this.category.updateCategory(name);
    }

    public Category getCategory() {
        return category;
    }

    public String toString() {
        return String.format("Product ID:%d, Name:%s, Price:%d, Category: %s", id, name, price, category);
    }
}
```
```java
public class Main{
  public static void main(String[] args) {
      
    Products laptop1 = new Products("HP Elite", 50000, "Laptop Computers");
    Products monitor1 = new Products("Samsung DPX", 3200, "PC Accessories");
    Products printer1 = new Products("LG Printer-Z", 1300, "Media");

    //Every instance/object of product creates an instance/object of the class 'Category'.
    //Hence, there is a toString representation of the class Category within the Product class instance below.
    System.out.println(laptop1);
    //Product ID:1890, Name:HP Elite, Price:50000, Category: Category Name: Laptop Computers, Category ID: 19

    printer1.getCategory().updateCategory("Media Accessory");
    System.out.println(printer1);
    //Product ID:5724, Name:LG Printer-Z, Price:1300, Category: Category Name: Media Accessory, Category ID: 876

    monitor1.updateCategory("PC Peripherals");
    System.out.println(monitor1.getCategory());
    //Category Name: PC Peripherals, Category ID: 861


    //INSTANCES OF CATEGORY
    Category category1 = new Category("Mobile Phones");
    System.out.println(category1);
    //Category Name: Mobile Phones, Category ID: 459
  }
}
```
* In the example above, there is no indication of any relationship/interconnection between the two classes. In essence, both classes\
are standalone classes residing within different files. 
* The example below makes the `Category` class a **Static Nested** class within the `Product` class The `Category` class is inside(inner)\
the `Product` class. Every code in the Main class remains same.

```java
package simplepracticeclasses;

public class Products {
    private final long id;
    private String name;
    private int price;

    private Category category;

    public Products(String name, int price, String categoryName) {
        this.id = Math.round(Math.random() * 10000);
        this.name = name;
        this.price = price;

        //instance of Category class.
        this.category = new Category(categoryName);
    }

    public void updateCategory(String name) {
        //update the particular category instance associated with the particular product instance
        this.category.updateCategory(name);
    }

    public Category getCategory() {
        return category;
    }

    public String toString() {
        return String.format("Product ID:%d, Name:%s, Price:%d, Category Name: %s", id, name, price, category.name);
    }

    public static class Category {

        private final long id;
        private String name;

        public Category(String name) {
            this.id = Math.round(Math.random() * 1000);
            this.name = name;
        }

        public void updateCategory(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Category Name: %s, Category ID: %d", name, id);
        }
    }
}
```
**NOTE:** 
* The `public` modifier of the `Category` class (nested static) makes it accessible and instantiable outside its outer class(`Product`). This\
the creation of a `Category` object without necessarily creating a `Product` object.
* If the access modifier is changed from `public` to `private`, the class will be inaccessible outside the Product class file.
* Default access modifier (**package private**) will allow access to the class within its package.

**(B) Instantiating Static Nested Classes in Java**
* To instantiate an object of the static nested class, reference is made to its outer class. 
```java
public class Main {
  public static void main(String[] args) {

    Products laptop1 = new Products("HP Elite", 50000, "Laptop Computers");
    Products monitor1 = new Products("Samsung DPX", 3200, "PC Accessories");
    Products printer1 = new Products("LG Printer-Z", 1300, "Media");

    //Every instance/object of product creates an instance/object of the class 'Category'.
    //Hence, there is a toString representation of the class Category within the Product class instance below.
    System.out.println(laptop1);
    //Product ID:8081, Name:HP Elite, Price:50000, Category Name: Laptop Computers

    System.out.println(monitor1.getCategory());
    //Category Name: PC Accessories, Category ID: 798

    //INSTANCES OF nested static class that are not associated with any Product instance.
    Products.Category mobiles = new Products.Category("Mobile Phone");
    System.out.println(mobiles); //Category Name: Mobile Phone, Category ID: 165
  }
}    
```
* Apart from the logical grouping of the classes another advantage of nested static classes is the **direct access** that the\
outer class has to the **private parameters** of its inner class. See the `toString` method of the Product class in (A).

**(C) Using Multiple Nested Classes within an Outer Class**
* The access of the **inner class** to the parameters of its **outer class** is not direct. This is because an inner class (`Category`) can\
be a standalone class. Inner classes(`Category`) are not always associated with an outer class(`Product`).(i.e. a Category can exist alone without a Product)
* In order to give an inner class(e.g. `Category`) a **direct access** to the parameters of its outer class (e.g `Products`), changes\
can be made to the _constructor_ of the outer class and a `Product` class is added as parameter to the inner class as shown in the\
example below.
  * `this.category = new Category(categoryName)` is changed to `this.category = new Category(categoryName, this)`
  * `private Product product` a private field added to Category class. Note the changes to the constructor of Category.
* The changes create an explicit reference to the outer class by the inner class. See full code below.
* Having created an explicit reference, it is possible to access parameters of the outer class (`Products`) within the inner\
class(`Category`). See the method `printProductDetails()` within the Category class.

```java
package simplepracticeclasses;

public class Products {
    private final long id;
    private String name;
    private int price;

    private Category category;

    public Products(String name, int price, String categoryName) {
        this.id = Math.round(Math.random() * 10000);
        this.name = name;
        this.price = price;

        //instance of Category class.
        this.category = new Category(categoryName, this);
    }

    public void updateCategory(String name) {
        //update the particular category instance associated with the particular product instance
        this.category.updateCategory(name);
    }

    public Category getCategory() {
        return category;
    }

    public String toString() {
        return String.format("Product ID:%d, Name:%s, Price:%d, Category Name: %s", id, name, price, category.name);
    }


    public static class Category {

        private final long id;
        private String name;

        private Products product;

        public Category(String name, Products product) {
            this.id = Math.round(Math.random() * 1000);
            this.name = name;
            //this refers to the current instance of the Product class(i.e. the outer class itself)
            this.product = product;
        }

        public void updateCategory(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Category Name: %s, Category ID: %d", name, id);
        }

        public void printProductDetails() {
            System.out.format("*** Product Details Associated with Category \"%s\" *** %n", name);
            System.out.println("ID: " + product.id);
            System.out.println("Name " + product.name);
            System.out.println("Price: " + product.price);
            //NOTE this acess the category itself...funny !
            System.out.println("Category: " + product.category);
        }
    }
}
```

```java
public class Main{
    
  public class Products {
    private  final long id;
    private String name;
    private int price;

    private Category category;

    public Products(String name, int price, String categoryName){
      this.id = Math.round(Math.random()*10000);
      this.name = name;
      this.price = price;

      //instance of Category class.
      this.category = new Category(categoryName, this);
    }

    public void updateCategory(String name){
      //update the particular category instance associated with the particular product instance
      this.category.updateCategory(name);
    }

    public Category getCategory(){
      return category;
    }

    public String toString(){
      return String.format("Product ID:%d, Name:%s, Price:%d, Category Name: %s", id, name, price, category.name);
    }


    public static class Category{

      private final long id;
      private String name;

      private Products product;

      public Category(String name, Products product){
        this.id = Math.round(Math.random() *1000);
        this.name = name;
        //this refers to the current instance of the Product class(i.e. the outer class itself)
        this.product = product;
      }

      public void updateCategory(String name) {
        this.name = name;
      }

      @Override
      public String toString() {
        return String.format("Category Name: %s, Category ID: %d", name, id);
      }

      public void printProductDetails(){
        System.out.format("*** Product Details Associated with Category \"%s\" *** %n", name);
        System.out.println("ID: "+ product.id);
        System.out.println("Name "+product.name);
        System.out.println("Price: "+ product.price);
        //NOTE this acess the category itself...funny !
        System.out.println("Category: "+product.category);
      }
    }
  }

}
```
* In general, an outer class can have zero to infinite number of inner classes.

```java
package simplepracticeclasses;

public class Products {
    private final long id;
    private String name;
    private int price;

    //Two instances of inner classes added as private parameters to the outer classes. See the inner classes below.
    private Category category;
    private Rating rating;


    //Outer class constructor showing the inner classes included.
    public Products(String name, int price, String categoryName, double rating) {
        this.id = Math.round(Math.random() * 10000);
        this.name = name;
        this.price = price;

        //instance of Category class.
        this.category = new Category(categoryName, this);
        this.rating = new Rating(rating, this);
    }

    public void updateCategory(String name) {
        //update the particular category instance associated with the particular product instance
        this.category.updateCategory(name);
    }

    public Category getCategory() {
        return category;
    }

    public String toString() {
        return String.format("Product ID:%d, Name:%s, Price:%d, Category Name: %s, Rating: %s", 
                                id, name, price, category.name, rating.getRating()
                            );
    }


    //1st Inner class
    public static class Category {

        private final long id;
        private String name;

        private Products product;

        public Category(String name, Products product) {
            this.id = Math.round(Math.random() * 1000);
            this.name = name;
            //this refers to the current instance of the Product class(i.e. the outer class itself)
            this.product = product;
        }

        public void updateCategory(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return String.format("Category Name: %s, Category ID: %d", name, id);
        }

        public void printProductDetails() {
            System.out.format("*** Product Details Associated with Category \"%s\" *** %n", name);
            System.out.println("ID: " + product.id);
            System.out.println("Name " + product.name);
            System.out.println("Price: " + product.price);
            //NOTE this acess the category itself...funny !
            System.out.println("Category: " + product.category);
        }
    }


    //2nd Inner class
    public static class Rating {
        private Products product;
        private double rating;

        public Rating(double rating, Products product) {
            this.product = product;
            this.rating = rating;
        }

        public double getRating() {
            return this.rating;
        }

        public void printRating() {
            System.out.println("*** Average Product Rating ***");
            //Note the access to the parameters of the outer class from the inner class.
            System.out.println(String.format("Product Name: %s, Rating: %d", product.name, product.rating));
        }

        @Override
        public String toString() {
            return String.format("[Product: %s, Rating: %s]", product.name, rating);
        }
    }
}
```

```java
public class Main{
  public static void main(String[] args) {

    Products laptop1 = new Products("HP Elite", 50000, "Laptop Computers", 7.0);
    Products monitor1 = new Products("Samsung DPX", 3200, "PC Accessories", 5.6);
    Products printer1 = new Products("LG Printer-Z", 1300, "Media", 2.2);


    //INSTANCES OF nested static class(Category) that are not associated with any Product instance.
    Products.Category laptop = new Products.Category("Laptop", laptop1);
    System.out.println(laptop); //Category Name: Mobile Phone, Category ID: 165

    laptop.printProductDetails();
        /*
        *** Product Details Associated with Category "Laptop" ***
        ID: 8330
        Name HP Elite
        Price: 50000
        Category: Category Name: Laptop Computers, Category ID: 431
         */

    System.out.println(monitor1);
    //Product ID:8474, Name:Samsung DPX, Price:3200, Category Name: PC Accessories, Rating: 5.6

    //INSTANCES OF nested static class(Rating) that are not associated with any Product instance.
    Products.Rating ratingMonitor1 = new Products.Rating(10.0, monitor1);
    System.out.println(monitor1);
    //Product ID:8474, Name:Samsung DPX, Price:3200, Category Name: PC Accessories, Rating: 5.6

    System.out.println(ratingMonitor1);
    //[Product: Samsung DPX, Rating: 10.0]
  }
}
```

**(D) Using and Characterizing Inner Classes in Java**

Both nested static class and inner class resides within another class (the outer class). The main **difference** between Nested Static\
class and Inner class:
1. The `static` key word: **Nested Static:** `public static class Orange`. **Inner Class:** `public class Orange`. 
2. While nested static class can be instantiated as standalone without its outer class. An inner class can't *DIRECTLY* exist alone without\
an outer class. The fact that inner class can't be instantiated without an outer class and the **automatic association** between both classes\
solves some related issue with nested static classes.\
Automatic instantiation can be seen in the constructor of `Products2` with respect to  `ReviewsAndRatings`.

```java
package simplepracticeclasses;

import java.util.ArrayList;
import java.util.List;

public class Products2 {

    private final long id;
    private final String name;
    private final int price;

    private final ReviewsAndRatings reviewsAndRatings;

    public Products2(String name, int price, double rating) {
        this.id = Math.round(Math.random() * 10000);
        this.name = name;
        this.price = price;

        //automatic instantiation of the inner class
        this.reviewsAndRatings = new ReviewsAndRatings(rating);
    }

    public ReviewsAndRatings getReviewsAndRatings() {
        return reviewsAndRatings;
    }

    @Override
    public String toString() {
        return String.format("Product Name: %s, Price: %d, Review:%s", name, price, reviewsAndRatings.getReview());
    }

    public class ReviewsAndRatings {

        private final double rating;
        private final List<String> reviews;

        public ReviewsAndRatings(double rating) {
            this.rating = rating;
            this.reviews = new ArrayList<>();
        }

        public void addReview(String review) {
            this.reviews.add(review);
        }

        public List<String> getReview() {
            return reviews;
        }

        @Override
        public String toString() {
            return String.format("Product: [%d, %s, %d], Rating:%f, Reviews:%s",
                    Products2.this.id,
                    Products2.this.name,
                    Products2.this.price,
                    rating,
                    reviews);
        }
    }
}
```
A parameter (`reviewsAndRatings`) and method (`getReviewsAndRatings`)resides within the outer class that links to the inner class. The\
method and parameter are the tools for instantiating and accessing the inner class through the outer class. This is shown further below\
in the main class.
```java
public class Main {
  public static void main(String[] args) {

    Products2 laptop1 = new Products2("HP Laptop", 56000, 78);
    
    laptop1.getReviewsAndRatings().addReview("Not bad");
    laptop1.getReviewsAndRatings().addReview("average laptop");
    
    System.out.println(laptop1);
    //Product Name: HP Laptop, Price: 56000, Review:[Not bad, average laptop]
    System.out.println(laptop1.getReviewsAndRatings());
    //Product: [3479, HP Laptop, 56000], Rating:78.000000, Reviews:[Not bad, average lap top]
  }
}    
```
* Instantiation shown below which is characteristics of nested static class is **NOT** permitted for an inner class. Such instantiation
will lead to compile-time error. 
```java
public class Main {
  public static void main(String[] args) {
    //Instantiation of an inner class is NOT permitted DIRECTLY without its outer class. 
    //Compiler error message: "Products2 is not an enclosing class'
    Products2.ReviewsAndRatings laptop1ReviewsAndRatings = new Products2.ReviewsAndRatings(32);
  }
}    
```
* To create a **standalone instance of the inner class(subclass)** from the exterior class, we forgo automatic instantiation in the constructor\
(of the outer/exterior class) and well introduce a setter as well. See below:

```java
package simplepracticeclasses;

import java.util.ArrayList;
import java.util.List;

public class Products2 {

    private final long id;
    private final String name;
    private final int price;

    private ReviewsAndRatings reviewsAndRatings;

    public Products2(String name, int price) {
        this.id = Math.round(Math.random() * 10000);
        this.name = name;
        this.price = price;
    }

    public ReviewsAndRatings getReviewsAndRatings() {
        return reviewsAndRatings;
    }

    public void setReviewsAndRatings(ReviewsAndRatings reviewsAndRatings) {
        this.reviewsAndRatings = reviewsAndRatings;
    }

    @Override
    public String toString() {
        return String.format("Product Name: %s, Price: %d, Review:%s",
                name,
                price,
                //This take care of null instance of reviewAndRatings.
                //The case when a Product object hasn't been associated with a ReviewAndRatings through setter method
                reviewsAndRatings == null ? "No reviews yet!!" : reviewsAndRatings.getReview());
    }

    //Inner class
    public class ReviewsAndRatings {
        private final double rating;
        private final List<String> reviews;

        public ReviewsAndRatings(double rating) {
            this.rating = rating;
            this.reviews = new ArrayList<>();
        }

        public void addReview(String review) {
            this.reviews.add(review);
        }

        public List<String> getReview() {
            return reviews;
        }

        @Override
        public String toString() {
            return String.format("Product: [%d, %s, %d], Rating:%f, Reviews:%s",
                    Products2.this.id,
                    Products2.this.name,
                    Products2.this.price,
                    rating,
                    reviews);
        }
    }
}
```
* The **standalone** instance of interior class is created by referencing an already existing instance/object of the exterior class. Hence, we reference\
an object (NOT a class) to create an instance of the interior class. In the case of nested static class, we can create its object by
referencing the actual class. 
* Products2 is NOT automatically associated to an instance of ReviewsAndRating. Instead, the defined setter method is used.
* Note how the standalone inner class is instantiated. See the Main class below:...
```java
public class Main{
  public static void main(String[] args) {

    Products2 laptop1 = new Products2("HP Laptop", 56000);
    //An instance of the Product before assignment of reviewAndRating....
    //...the toString method of the Product2 class takes care of the null value instance. See the Products2
    System.out.println(laptop1);
    //Product Name: HP Laptop, Price: 56000, Review:No reviews yet!!


    //STANDALONE INSTANCE OF THE INNER CLASS
    Products2.ReviewsAndRatings laptop1ReviewsAndRating = laptop1.new ReviewsAndRatings(43);
    laptop1ReviewsAndRating.addReview("GREAT !");

    System.out.println(laptop1ReviewsAndRating);
    //Product: [4871, HP Laptop, 56000], Rating:43.000000, Reviews:[GREAT !]

    //Assigning a reviewAndRatings to the Products2 object (laptop1)
    laptop1.setReviewsAndRatings(laptop1ReviewsAndRating);

    //Instance of Product after assignment of reviewAndRating....review is no longer null
    System.out.println(laptop1);
    // Product Name: HP Laptop, Price: 56000, Review:[GREAT !]
  }
}
```

**(E) Working with Iterators Implemented as an Inner Class**
* **Iterators** are the most common use cases of inner class; allowing iteration over element in any collection.
* The foreach loop is the most common example of iterators used to access and iterate over elements.
* An **Iterator class** is defined as an inner class to allow iteration over elements of the custom exterior container class.

```java
package simplepracticeclasses;

import java.util.Iterator;

public class StringContainer implements Iterable<String> {
    private String[] strings;
    private int index = 0;

    //Note the argument of the constructor.
    //... implies variable number of Strings
    public StringContainer(String... strings) {
        this.strings = strings;
    }

    @Override
    public Iterator<String> iterator() {
        //returns the inner class which does the actual iterating implementation.
        return new UpperCaseNamesIterator();
    }


    //The interior(inner) class that is returned by the outer(exterior) class exterior method
    private class UpperCaseNamesIterator implements Iterator<String> {
        @Override
        public boolean hasNext() {
            //returns true if the index of the current element is less than the length of the entire elements
            return StringContainer.this.index < StringContainer.this.strings.length;
        }

        @Override
        public String next() {
            //returns an uppercase of the string at the current index
            return StringContainer.this.strings[StringContainer.this.index++].toUpperCase();
        }
    }
}
```
* A class can either implement `Iterable` (override a method that returns an `Iterator`) OR implement an `Iterator` itself by overriding and\
implementing the methods `.hasNext()` and `next()`. 
* In this particular example, `StringContainer` class has an interior class that does the job of implementing the `Iterable`. The `StringContainer`\
class contains `String` elements within it and an `Iterator` is defined in an interior class to iterate over its String elements.
* The exterior class implementing `Iterable` means it gives access to an `Iterator`. It must have an overridden method named `iterator`.
* The `iterator` method in the exterior class(String Container) returns an interior class(UpperCaseNamesIterator) which does the actual\
implementation of iterating over the String elements.
* The interior class access modifier is `private` to ensure it will always be associated with an exterior class. 
* The exterior class implements the `Iterator` interface. It has to Override two methods: `hasNext` and `next`

```java
public class Main {
  public static void main(String[] args) {

    StringContainer names = new StringContainer("toMas", "Mirek", "aurora", "betTTY");
    System.out.println(names); //practice.StringContainer@330bedb4
    names.printArrayValues();
    //values in the container before calling the iterator method
        /*
         toMas
         Mirek
         aurora
         betTTY
         */

    //Instantiating the Iterator which will contains the two methods of the iterator()
    Iterator<String> namesIterator = names.iterator();

    //Using the two methods of the iterator()
    System.out.println("\nFirst element");
    System.out.println("Has next: " + namesIterator.hasNext());
    System.out.println(namesIterator.next());
        /*
        First element
        Has next: true
        TOMAS
         */

    System.out.println("\nSecond element");
    System.out.println("Has next: " + namesIterator.hasNext());
    System.out.println(namesIterator.next());
        /*
        Second element
        Has next: true
        MIREK
         */

    System.out.println("\nThird element");
    System.out.println("Has next: " + namesIterator.hasNext());
    System.out.println(namesIterator.next());
        /*
        Third element
        Has next: true
        AURORA
         */

    System.out.println("\nFourth element");
    System.out.println("Has next: " + namesIterator.hasNext());
    System.out.println(namesIterator.next());
        /*
        Fourth element
        Has next: true
        BETTTY
        */


    System.out.println("Has next: " + namesIterator.hasNext()); //false
  }
}    
```
* Another example with a generic class
```java
package org.example;

import java.util.Iterator;

public class ContainerClass<T> implements Iterable<T>{

    T [] elements;

    private int index = 0;

    @SafeVarargs
    public ContainerClass(T... providedElements){
        this.elements = providedElements;
    }

    @Override
    public Iterator<T> iterator(){
        return new IteratorImplementation();
    }

    public class IteratorImplementation<T> implements Iterator<T>{

        @Override
        public boolean hasNext(){
            return ContainerClass.this.index < ContainerClass.this.elements.length;
        }

        @Override
        public T next(){
            return (T) ContainerClass.this.elements[ContainerClass.this.index++];
        }

    }
}
```
```java
package org.example;
import lombok.Data;

import java.io.*;;
import java.util.*;

public class App {
    
    public static void main(String[] args) throws IOException {

        ContainerClass containerClass = new ContainerClass<>("Apple", 5, new Date(), 3.14f, 2.17f);

        System.out.println(Arrays.toString(containerClass.elements));

        System.out.println("---");
        while (containerClass.iterator().hasNext()) {
            System.out.println(containerClass.iterator().next());
        }
        System.out.println("---");

    }

}    
```

**(F) Using and Characterizing Local Classes in Java**
* **Local classes** are defined within a block of java code. Block of code refers to `{}`; within a method blocks, instance initialization\
blocks e.t.c. A local class can only be accessed within the code block it resides/declared.

```java
package simplepracticeclasses;

//Note the imports...which are used the local class for regex
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Customer {

    private final long id;
    private final String name;
    private final String contactInfo;

    private String validPhoneNumber;
    private String validEmailAddress;

    public Customer(long id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }


    //A method that contains a class within it.
    public void validateCustomer() {

        //A Local class within the method
        class ContactValidator {

            //Parameters of the local class
            private final String contactInfo;

            //Constructor of the local class
            public ContactValidator(String contactInfo) {
                this.contactInfo = contactInfo;
            }

            //Methods of the local class
            //a method that defines('compile') and then extracts('matcher' and 'find') a phone number pattern as a
            // sub string of the string. It returns null if the defined phone number pattern isn't found('find')
            String getPhoneNumber() {
                Pattern pattern = Pattern.compile("^[1-9]\\d{2}-\\d{3}-\\d{4}");
                //valid format is 555-555-55555
                Matcher phoneMatcher = pattern.matcher(contactInfo);

                if (     //phoneMatcher.find() returns true if the patter defined is found.
                        phoneMatcher.find()
                ) {
                    return phoneMatcher.group();
                }
                return null;
            }

            //Same as getPhone number but has a patter for email address instead
            String getEmailAddress() {
                Pattern pattern = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]\\.[A-Za-z]{2,6}");
                Matcher emailMatcher = pattern.matcher(contactInfo);

                if (     //emailMatcher.find() returns true if the patter defined is found.
                        emailMatcher.find()
                ) {
                    return emailMatcher.group();
                }
                return null;

            }
        }//END OF THE LOCAL CLASS

        //An instance of the local class defined within the same method holding it.
        ContactValidator contactValidator = new ContactValidator(contactInfo);

        //Assign the value of the current(this) validPhoneNumber to the output of the method defined within local class
        this.validPhoneNumber = contactValidator.getPhoneNumber();
        this.validEmailAddress = contactValidator.getEmailAddress();

    }//END OF THE validateCustomer METHOD

    @Override
    public String toString() {
        return String.format("ID:%d, name:%s, phone%s, email:%s", id, name, validPhoneNumber, validEmailAddress);
    }
}
```

```java
public class Main{
  public static void main(String[] args) {

    Customer jason = new Customer(1111, "Json", "222-333-4444, json@gmail.com");
    //Calling the validateCustomer method which resides in the outer Customer class leads to a call to the Local class.
    //The Local class (ContactValidator) does the cleaning of contactInfo String and returns
    //...the cleaned values as validEmail and validPhone number
    jason.validateCustomer();
    System.out.println(jason);//ID:1111, name:Json, phone:222-333-4444, email:json@gmail.com

    Customer lisa = new Customer(2222, "Lisa", "8883334444, json@gmail.com");
    lisa.validateCustomer();
    System.out.println(lisa); //ID:2222, name:Lisa, phone:null, email:json@gmail.com
  }
}
```
**(G) Accessing Final and Local Fields from Outer Classes**
* In preceding section, the local class(`ContactValidator`) resides within a method code block within the outer `Customer` class. 
* In the local class, instead of returning the match(as String), we can immediately assign the result of the match to the required\
parameters of the outer class. See the example below:

```java
package simplepracticeclasses;
//Note the imports...which are used the local class for regex

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;

public class Customer {

    private final long id;
    private final String name;
    private final String contactInfo;

    private String validPhoneNumber;
    private String validEmailAddress;

    public Customer(long id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }


    //A method that contains a class within it.
    public void validateCustomer() {

        //Member variable within the method containing the local class

        Logger log = Logger.getLogger(Customer.class.getName());

        //A Local class within the method
        class ContactValidator {

            //Parameters of the local class
            private final String contactInfo;

            //Constructor of the local class
            public ContactValidator(String contactInfo) {
                this.contactInfo = contactInfo;
            }

            //Methods of the local class
            //a method that defines('compile') and then extracts('matcher' and 'find') a phone number pattern as a
            // sub string of the string. It returns null if the defined phone number pattern isn't found('find')
            void getPhoneNumber() {
                Pattern pattern = Pattern.compile("^[1-9]\\d{2}-\\d{3}-\\d{4}");
                //valid format is 555-555-55555
                Matcher phoneMatcher = pattern.matcher(contactInfo);

                if (     //phoneMatcher.find() returns true if the patter defined is found.
                        phoneMatcher.find()
                ) {
                    validPhoneNumber = phoneMatcher.group();
                    log.info("Valid phone number found: " + validPhoneNumber);
                } else {
                    log.warning("No valid phone number found for: " + name);
                }

            }

            //Same as getPhone number but has a patter for email address instead
            void getEmailAddress() {
                Pattern pattern = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
                Matcher emailMatcher = pattern.matcher(contactInfo);

                if (     //emailMatcher.find() returns true if the patter defined is found.
                        emailMatcher.find()
                ) {
                    validEmailAddress = emailMatcher.group();
                    log.info("Valid email address found: " + validEmailAddress);
                } else {
                    log.warning("No valid email address found for: " + name);
                }

            }
        }//END OF THE LOCAL CLASS

        //An instance of the local class defined within the same method holding it.
        ContactValidator contactValidator = new ContactValidator(contactInfo);

        //Assign the value of the current(this) validPhoneNumber to the output of the method defined 
        // within the local class
        contactValidator.getPhoneNumber();
        contactValidator.getEmailAddress();

    }//END OF THE validateCustomer METHOD

    @Override
    public String toString() {
        return String.format("ID:%d, name:%s, phone:%s, email:%s", id, name, validPhoneNumber, validEmailAddress);
    }
}
```
* Local classes have access to the member variables of their outer class. Local class have access to member variables of the method\
within which its defined based on a caveat. 
* Caveat: A local class can access the value of a local variable, usually if the local variable is **declared as final** or if the\
local variable is **effectively final**.
* See the logger member variable above for an example of local variable access by the local class.
* Running the Main class with the changes made to the `Customer` class outputs the following to the console.
```
Aug 17, 2022 1:14:06 PM practice.Customer$1ContactValidator getPhoneNumber
INFO: Valid phone number found: 222-333-4444
Aug 17, 2022 1:14:06 PM practice.Customer$1ContactValidator getEmailAddress
INFO: Valid email address found: json@gmail.com
Aug 17, 2022 1:14:06 PM practice.Customer$1ContactValidator getPhoneNumber
WARNING: No valid phone number found for: Lisa
Aug 17, 2022 1:14:06 PM practice.Customer$1ContactValidator getEmailAddress
INFO: Valid email address found: json@gmail.com


ID:1111, name:Json, phone:222-333-4444, email:json@gmail.com
ID:2222, name:Lisa, phone:null, email:json@gmail.com
```

**IMPORTANT NOTES:**
* Local classes can't be defined using access **ANY** access modifiers. Hence, `public class ContactValidator` is not permissible.
* It makes no sense to have any modifier since it is **ONLY** accessible within the defining code block `{}` e.g. method.
* If a **method** that contains a **local class** is defined as **static**, the local class will lose its access to its 
parameters of the **outer class**.!!! Unless those parameters are defined as static as well. !!!
* Local classes can't have a static declaration of parameter 

**(H) Using Local Classes Defined in Initialization Blocks**
* ContactValidator interface

```java
package simplepracticeclasses;

public interface ContactValidator {

    void populateValidPhoneNumber();

    void populateValidEmailAddress();

}
```
* Customer class

```java
package simplepracticeclasses;
//Note the imports...which are used the local class for regex

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.logging.Logger;

public class Customer {

    private final long id;
    private final String name;
    private final String contactInfo;

    //The interface ContactValidator
    private final ContactValidator contactValidator;
    private String validPhoneNumber;
    private String validEmailAddress;


    //START of Instance Initialization block
    //....It contains the local class USContactValidator
    {

        Logger log = Logger.getLogger(Customer.class.getName());

        //A Local class which implements the ContactValidator interface
        class USContactValidator implements ContactValidator {

            //Methods of the local class
            //a method that defines('compile') and then extracts('matcher' and 'find') a phone number pattern as a
            // sub string of the string. It returns null if the defined phone number pattern isn't found('find')
            public void populateValidPhoneNumber() {
                Pattern pattern = Pattern.compile("^[1-9]\\d{2}-\\d{3}-\\d{4}");
                //valid format is 555-555-55555
                Matcher phoneMatcher = pattern.matcher(contactInfo);

                if (     //phoneMatcher.find() returns true if the patter defined is found.
                        phoneMatcher.find()
                ) {
                    validPhoneNumber = phoneMatcher.group();
                    log.info("Valid phone number found: " + validPhoneNumber);
                } else {
                    log.warning("No valid phone number found for: " + name);
                }

            }

            //Same as getPhone number but has a patter for email address instead
            public void populateValidEmailAddress() {
                Pattern pattern = Pattern.compile("[A-Z0-9a-z._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}");
                Matcher emailMatcher = pattern.matcher(contactInfo);

                if (     //emailMatcher.find() returns true if the patter defined is found.
                        emailMatcher.find()
                ) {
                    validEmailAddress = emailMatcher.group();
                    log.info("Valid email address found: " + validEmailAddress);
                } else {
                    log.warning("No valid email address found for: " + name);
                }

            }
        }//END OF THE LOCAL CLASS

        //INSTANTIATION OF AN OBJECT OF THE LOCAL CLASS DEFINED ABOVE AND PASSING IT TO THE PARAMETER OF THE OUTER CLASS.
        contactValidator = new USContactValidator();
    }

    //constructor of the outer class
    public Customer(long id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;

        //use of the methods of local class to populate the validPhoneNumber and validEmailAddress parameter of the outer class.
        contactValidator.populateValidPhoneNumber();
        contactValidator.populateValidEmailAddress();

    }

    @Override
    public String toString() {
        return String.format("ID:%d, name:%s, phone:%s, email:%s", id, name, validPhoneNumber, validEmailAddress);
    }
}
```
* Main class
```java
public class Main {
  public static void main(String[] args) {

    Customer jason = new Customer(1111, "Json", "222-333-4444, json@gmail.com");
    //jason.validateCustomer();  NOT NEEDED ANYMORE.
    System.out.println(jason);//ID:1111, name:Json, phone:222-333-4444, email:json@gmail.com


    Customer lisa = new Customer(2222, "Lisa", "8883334444, json@gmail.com");
    // lisa.validateCustomer(); NOT NEEDED ANYMORE
    System.out.println(lisa); //ID:2222, name:Lisa, phone:null, email:json@gmail.com
  }
}
```
* Console output
```
Aug 17, 2022 2:24:44 PM practice.Customer$1USContactValidator populateValidPhoneNumber
INFO: Valid phone number found: 222-333-4444
Aug 17, 2022 2:24:44 PM practice.Customer$1USContactValidator populateValidEmailAddress
INFO: Valid email address found: json@gmail.com
Aug 17, 2022 2:24:44 PM practice.Customer$1USContactValidator populateValidPhoneNumber
WARNING: No valid phone number found for: Lisa
Aug 17, 2022 2:24:44 PM practice.Customer$1USContactValidator populateValidEmailAddress
INFO: Valid email address found: json@gmail.com

ID:1111, name:Json, phone:222-333-4444, email:json@gmail.com
ID:2222, name:Lisa, phone:null, email:json@gmail.com
```

## 10: Classes in Java: Creating & Using Anonymous Classes
**Anonymous classes** allows the definition of codes that are use-and-throw. They are _defined_ and _instantiated_ at\
the same time (defined and used right away). They are called anonymous because they do not have a name\ 
The most common use case is when a class that implements an interface has no further use. A particular and popular\
use case is to implement an object that implements the `Comparator` interface.\
**(A) Using Anonymous Classes in Java**
* An anonymous class is nested inner class that is defined without a name for the class.
* Interfaces are the most popular method for instantiating anonymous classes.
* After an anonymous class has been instantiated, any value it returns or method is exposes can be used in same manner\
as we will use a declared variable assigned to an object of regular class.
* The entirety of an anonymous class is considered a single java statement. Hence, it must end with `;`
* An example is shown below with the `Cars` interface:

```java
package simplepracticeclasses;

public interface Cars {

    String getMake();

    String getModel();

    int getPrice();

    int getMileage();

}
```
* The `Cars` `anonymous class` is instantiated using the `Cars` _interface_. It must implement the methods of the interface.
* The variable type is `Cars` interface. Variable name can be chosen freely to reflect the essence/meaning of the class.
```java
public class Main{
  public static void main(String[] args) {

    //Instantiation of the anonymous class  with variable name 'tesla' using the 'Car' interface
    Cars tesla = new Cars(){

      @Override
      public String getMake() {
        return "Tesla";
      }

      @Override
      public String getModel() {
        return "Model J";
      }

      @Override
      public int getPrice() {
        return 89000;
      }

      @Override
      public int getMileage() {
        return 23;
      }

      //We can as well Override the toString, hashCodee and equals methods of the base Object class
      @Override
      public String toString(){
        return String.format("Make: %s, Model: %s, Price:%d, Mileage:%d", getMake(), getMake(), getPrice(), getMileage());
      }
      
      @Override
      public int hashCode(){
        return Objects.hash(getMake(), getModel(), getMileage(), getPrice());
      }
      
      @Override
      public boolean equals(Object other){
        if(other == null){
          return false;
        }

        //checking that other is not an instance of Car class
        if(!(other instanceof Cars)){
          return false;
        }

        //casting other which is Object instance to a Cars instance...to be used for comparison
        Cars otherCars = (Cars) other;

        if(otherCars.getMake().equals(getMake()) &&
                otherCars.getModel().equals(getModel()) &&
                otherCars.getPrice() == getPrice() &&
                otherCars.getMileage() == getMileage()
        ){
          return true;
        }
        return false;
      }

    };//important to remember to finish the statement with ';' //END OF ANONYMOUS CLASS
    
    System.out.println("Make: "+tesla.getMake()); //Make: Tesla
    System.out.println("Model: "+tesla.getModel()); //Model: Model J
    System.out.println("Price: "+tesla.getPrice()); //Price: 89000
    System.out.println(tesla); //Make: Tesla, Model: Tesla, Price:89000, Mileage:23
    System.out.println(tesla.getClass()); //class practice.Main$1 //'$1':it's the 1st anonymous class of Main class.
    System.out.println(tesla.getClass().isAnonymousClass());//true
    System.out.println(tesla.getClass().isInterface());//false
    System.out.println(tesla instanceof Object); //true
  }
}
```
* The checks in the Main class using `.isInterface()` shows that the anonymous class is **NOT** an interface. It only implements an interface.
* The anonymous class can also override `toString`, `hashcode` and other methods of the base class `java.lang.Object`.
  
**(B) Exploring Nuances of Anonymous Classes in Java**
* Anonymous classes can also have member variables. However, member variables defined within an anonymous class **CAN'T** be accessed\
outside the anonymous class. Not even inside the method that contains the anonymous class. Ths is true even if access modifier is set to public.
* The _variables_ and _methods_ that are part of the _interface_ implemented by the anonymous class **OR/AND** _Overridden_ methods\
from `java.lang.Object` class are the **ONLY** accessible objects of the anonymous class that can be accessed outside the anonymous class. 
* Anonymous class can access local variables defined within the entire file (class) they are defined in as long as those\
variables are either **final** or **effectively final**. Effectively final means that the variables was never reassigned.
* Anonymous class can also be created from an actual class and not just from interface. 

```java
package simplepracticeclasses;

public class Condo {

    public String city;
    public String architecturalStyle;
    public int price;

    public Condo(String city, String architecturalStyle, int price) {
        this.city = city;
        this.architecturalStyle = architecturalStyle;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("City:%s, Style:%s, Price:$%d", city, architecturalStyle, price);
    }
}
```
```java
public class Main{
  public static void main(String[] args) {
    //anonymous class created  within the main method
    Condo pragueCondo = new Condo("Prague", "Bohemian", 10000);
    System.out.println(pragueCondo); //City:Prague, Style:Bohemian, Price:$10000
    System.out.println(pragueCondo.price); //10000
  }
}
```

**(C) Implementing Interfaces with Anonymous Classes in Java**
* Anonymous classes are typically used to implement single method interfaces. Also called **functional interfaces** because\
they typically have a single method that needs implementation. E.g. Runnable interface which is used to execute Threads,\
Comparable interface (which implements the `compareTo()` method) e.t.c A typical example is a Comparator implemented as anonymous class.

**Anonymous Classes Implementing the Comparator Interface**

```java
package simplepracticeclasses;

public class Condo {
    public String city;
    public String architecturalStyle;
    public int price;

    public Condo(String city, String architecturalStyle, int price) {
        this.city = city;
        this.architecturalStyle = architecturalStyle;
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("City:%s, Style:%s, Price:$%d", city, architecturalStyle, price);
    }
}
```

```java
public class Main{
  public static void main(String[] args) {
    List<Integer> numberList = new ArrayList<>();
    Collections.addAll(numberList, 234, 647, 100, 43, 0, -24);
    System.out.println("Before sorting: "+numberList); //Before sorting: [234, 647, 100, 43, 0, -24]

    //Defining a Comparator object as an anonymous class
    Comparator<Integer> descendingComparator = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2) * -1;
      }
    };
    //Specification of a custom comparator
    Collections.sort(numberList, descendingComparator);
    System.out.println("After sorting: "+numberList); //After sorting: [647, 234, 100, 43, 0, -24]


    //Defining a Comparator for custom object
    List<Condo> condoList = new ArrayList<>();
    Collections.addAll(condoList,
            new Condo("Prague", "Boh", 90000),
            new Condo("New York", "Mod", 800000),
            new Condo("Sweden", "Nord", 10000)
    );
    System.out.println(condoList);

//[City:Prague, Style:Boh, Price:$90000, City:New York, Style:Mod, Price:$800000, City:Sweden, Style:Nord, Price:$10000]

    Comparator<Condo> customOjectComparator = new Comparator<Condo>() {
      @Override
      public int compare(Condo o1, Condo o2) {
        return o1.price - o2.price;
      }
    };

    Collections.sort(condoList, customOjectComparator);
    System.out.println(condoList);
//[City:Sweden, Style:Nord, Price:$10000, City:Prague, Style:Boh, Price:$90000, City:New York, Style:Mod, Price:$800000]
    
  }
}
```
**Anonymous Classes Implementing the Runnable Interface**
* Programming instructions are read within threads.
* A **thread** of execution is the smallest sequence of programming instructions that can be managed by computer scheduler.
```java
public class AppMain{
  public static void main(String[] args) {

    System.out.println("Current thread name:"+ Thread.currentThread().getName());//Current thread name:main
    System.out.println("Current thread priority:"+ Thread.currentThread().getPriority());//Current thread priority:5
    System.out.println(Thread.currentThread().getThreadGroup());//java.lang.ThreadGroup[name=main,maxpri=10]
    
  }
}
```
* According to the snippet above, the current thread is main. However, code execution is not limited to just the main\
thread of execution. Java gives classes allowing to create other threads to run codes. 
* To create a new thread to run a specific code, define a class that extends the `Thread` class and then specify the codes\
to be run by the new thread. See snippet below:
```java
public class AppMain{
  public static void main(String[] args) {

    //This is a local class  
    class LocalThread extends Thread {
      public void run(){
        System.out.format("Thread name: %s, Thread priority: %d %n",
                Thread.currentThread().getName(), Thread.currentThread().getPriority()
        );
      }
    }

    Thread thread = new LocalThread();
    //This is the custom thread defined above
    thread.start(); //Thread name: Thread-0, Thread priority: 5 

    //This accesses the current thread ('main') running the main method. 
    System.out.format("Current thread name: %s, Current thread priority: %d %n",
            Thread.currentThread().getName(), Thread.currentThread().getPriority()
    ); //Current thread name: main, Current thread priority: 5 
  }
}
```
* Instead of having a local class extend the `Thread` base class as shown above, it's common to create new threads by passing in an implementation\
of the `Runnable` interface. This is particular use of anonymous class is common.
```java
public class Main{
  public static void main(String[] args) {
     
    //This is an anonymous class  
    Thread customThread = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("------------------------------------------");

        String statement = String.format("Thread name: %s, Thread priority: %d",
                Thread.currentThread().getName(), Thread.currentThread().getPriority());
        System.out.println(statement);
        System.out.println("------------------------------------------");
      }
    });

    customThread.start();
        /*
        ------------------------------------------
        Thread name: Thread-0, Thread priority: 5
        ------------------------------------------
         */
  }
}
```

**(D) Using Local and Anonymous Classes to Filter Data in Java**\
**An Implementation Without Local and Anonymous Classes**
```java
public class Main{

  private static List<Home> filterByPrice(List<Home> homesList, int low, int high){

    List<Home> filteredList = new ArrayList<>();

    for (Home home:
            homesList) {
      if(home.getPrice() >= low && home.getPrice() < high){
        filteredList.add(home);
      }
    }

    return filteredList;
  }
  
  
  public static void main(String[] args) {

    Home home1 = new Home("browstone", "New York", 1200, 3_200_000);
    Home home2 = new Home("townhome", "Prague", 2200, 200_000);
    Home home3 = new Home("cottage", "Vienna", 800, 800_000);
    Home home4 = new Home("villa", "Paris", 6200, 4_600_000);
    
    List<Home> homeList = new ArrayList<>();
    Collections.addAll(homeList, home1, home2, home3, home4);

    System.out.println(homeList);
        /*
        [
        Type:browstone, City:New York, Area:1200, Price:3200000,
        Type:townhome, City:Prague, Area:2200, Price:200000,
        Type:cottage, City:Vienna, Area:800, Price:800000,
        Type:villa, City:Paris, Area:6200, Price:4600000
        ]
        */

    List<Home> filteredHomeList = filterByPrice(homeList, 100_000, 1_000_000);
    System.out.println(filteredHomeList);
        /*
        [
        Type:townhome, City:Prague, Area:2200, Price:200000,
        Type:cottage, City:Vienna, Area:800, Price:800000
        ]
         */
  }
}
```
* While the code is correct, it quite unmanageable/not maintainable/not scalable (e.g. requires a new method to filter\ 
by area e.t.c) and needs improvements.
* An interface and a local class that implements it can simplify (by eliminating the method) the code and solve the\
related issues mentioned above.

```java
package simplepracticeclasses;

public interface HomeFilter {

    boolean test(Home home);

}
```
**NOTE:** Combination of local and anonymous class; the local class is later instantiated as an anonymous class. 

```java
public class Main {
  private static List<Home> filterAnything(List<Home> homeList, HomeFilter homeFilterImpl) {
    //Note homeFilterImpl must be an object/class that implements the HomeFilter interface
    List<Home> filteredList = new ArrayList<>();
    for (Home home : homeList) {
      if (homeFilterImpl.test(home)) {
        filteredList.add(home);
      }
    }
    return filteredList;
  }

  public static void main(String[] args) {

    Home home1 = new Home("browstone", "New York", 1200, 3_200_000);
    Home home2 = new Home("townhome", "Prague", 2200, 200_000);
    Home home3 = new Home("cottage", "Vienna", 800, 800_000);
    Home home4 = new Home("villa", "Paris", 6200, 4_600_000);


    List<Home> homeList = new ArrayList<>();
    Collections.addAll(homeList, home1, home2, home3, home4);


    //DEFINTION OF LOCAL CLASS 'PriceHomeFilter'
    class PriceHomeFilter implements HomeFilter {
      //The test method defined the HomeFilter interface provides boolean return value for the result of the
      // implementation of the filtering logic
      @Override
      public boolean test(Home home) {
        return (home.getPrice() >= 100_000 && home.getPrice() < 1000_000);
      }
    }

    List<Home> filteredList = filterAnything(homeList, new PriceHomeFilter());
    System.out.println(filteredList);
        /*
        [
        Type:townhome, City:Prague, Area:2200, Price:200000,
        Type:cottage, City:Vienna, Area:800, Price:800000
        ]
         */
  }
}
```
## 11: Classes in Java: Implementing Functional Interfaces Using Lambdas
Lambda expressions allows the definition of **classes** that implement single method interfaces in a very concise and\
compact manner. It's a very short block of code which takes in input arguments and performs an action or returns a value.\
It can be seen as a very short method/function that does an operation and or returns a value.\
Lambda expressions are very useful for implementing single-method interfaces(i.e. functional interfaces).

**(A) Introducing Lambda Expressions**
```java
package simplepracticeclasses;

public interface HomeFilter {

    boolean test(Home home);
}
```
* The first part of a lambda expression is the input argument or parameter. The actual body(logic/calculations/engine) comes after `->`,\
followed by an expression or code block: `(inputArgument) -> {expression}`.
```java
public class Main{

  private static List<Home> filterAnything(List<Home> homeList, HomeFilter homeFilterImpl){
    //Note homeFilterImpl must be an object/class that implements the HomeFilter interface
    List<Home> filteredList = new ArrayList<>();
    for (Home home: homeList) {
      if(homeFilterImpl.test(home)){
        filteredList.add(home);
      }
    }
    return filteredList;
  }
  public static void main(String[] args) {

    Home home1 = new Home("browstone", "New York", 1200, 3_200_000);
    Home home2 = new Home("townhome", "Prague", 5000, 200_000);
    Home home3 = new Home("cottage", "Vienna", 240, 800_000);
    Home home4 = new Home("villa", "Paris", 6200, 4_600_000);

    List<Home> homeList = new ArrayList<>();
    Collections.addAll(homeList, home1, home2, home3, home4);

    List<Home> filteredList = filterAnything(homeList,
            //Lambda expression
            (home) -> (home.getPrice() >= 100_000 && home.getPrice() < 1000_000));
    System.out.println(filteredList);
        /*
        [
        Type:townhome, City:Prague, Area:2200, Price:200000,
        Type:cottage, City:Vienna, Area:800, Price:800000
        ]
         */
  }
    
}
```
**(B) Creating Lambda Statements and Lambda Expressions**

**Functional interface implementation using anonymous class VS lambda expression**

```java
package simplepracticeclasses;

@FunctionalInterface
public interface Greeting {
    String simpleGreetingByName(String name);
}
```
* **greet()** method within the Main class accepts a String object and any object that implements the Greeting interface.\
It then invokes the simpleGreetingByName() method in argument object that implements the Greeting interface.
**Greeting** interface implementation can either be done via anonymous class or via a lambda expression. Both can be passed
directly as arguments to greet() method. Both methods of implementation are presented below:
```java
public class Main {
  public static void greet(String name, Greeting greeting) {
    System.out.println(greeting.simpleGreetingByName(name));
  }

  public static void main(String[] args) {

    //ANONYMOUS CLASS call OF greet() method
    greet("Jarda",
            // START of anonymous class
            new Greeting() {
              @Override
              public String simpleGreetingByName(String name) {
                return ("Hello " + name + "!!!");
              }
            }// END of anonymous class
    ); //Hello Jarda!!!

    //LAMBDA EXPRESSION call of greet() method
    greet("Bascal",
            //START-END of LAMBDA EXPRESSION. It directly implements the interface without need to specify a class.
            (name) -> ("Hi " + name + ", How are you doing?")); //Hi Bascal, How are you doing?


    //If there is just one input argument to the lambda, the parenthesis can be foregone.
    greet("John",
            //START-END of LAMBDA EXPRESSION.It directly implements the interface without need to specify a class.
            name -> "Whatsup " + name + " ?"); //Whatsup John ?
  }
}
```

* A lambda that is possible to implement in a single line of code is known as **lambda expression**. A lambda requiring\
multiple lines of code is known as **lambda statement**.
* Lambda statement need to be enclosed in {}.

```java
public class Main{
    
  public static void greet(String name, String customGreeting, int numTimes, Greeting greeting){
    System.out.println(greeting.greetMultipleTimes(name, customGreeting, numTimes ));
  }
  
  public static void main(String[] args) {

    //ANONYMOUS CLASS call OF greet() method
    greet("Jarda", "Jak se maz", 3,
            // START of anonymous class
            new Greeting() {
              @Override
              public String greetMultipleTimes(String name, String customGreeting, int numTimes) {
                String greeting = String.format("%s, %s %n", customGreeting, name);
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < numTimes; i++) {
                  sb.append(greeting);
                }
                return sb.toString();
              }

            });

        /*
        Jak se maz, Jarda
        Jak se maz, Jarda
        Jak se maz, Jarda
         */


    //LAMBDA STATEMENT call of greet() method
    greet("Bascal", "Welcome Here !", 4,
            //START of LAMBDA STATEMENT.
            (name, customGreeting, numTimes) -> {
              String greeting = String.format("*** %s, %s *** %n", customGreeting, name);
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < numTimes; i++) {
                sb.append(greeting);
              }
              return sb.toString();

            } //EMD of LAMBDA STATEMENT.
    );
    /*
     *** Welcome Here !, Bascal ***
     *** Welcome Here !, Bascal ***
     *** Welcome Here !, Bascal ***
     *** Welcome Here !, Bascal ***
     */

    //LAMBDA  EXPRESSSION call of greet() method
    greet("Rober", "Ahoj", 4,
            ((name, customGreeting, numTimes) -> String.format("--%s, %s --%n", customGreeting, name).repeat(numTimes))
    );
  }
}
```

**(C) Implementing Functional Interfaces Using Lambdas**

**NOTE:** Only a functional interface (an interface with exactly one method) can be implemented using a lambda.
Result/return value from the implementation of an interface method can be assigned to a **parameter/variable**.
1. A functional interface with no input argument

```java
package simplepracticeclasses;

public interface Greeting {
    String simpleGreeting();
}
```
```java
public class Main {
  public static void main(String[] args) {
    //Implementing the 'Greeting' functional interface and assigning the result of the method to a variable

    //Anonymous class method
    Greeting greetingAnonymous = new Greeting() {
      @Override
      public String simpleGreeting() {
        return "Hello from an anonymous function";
      }
    };

    //Lambda expression method
    Greeting greetingLambdas = () -> "Hello from a lambdas function";

    System.out.println(greetingAnonymous); // practice.AppaMain$1@4dd8dc3
    System.out.println(greetingLambdas); // practice.AppaMain$$Lambda$1/999966131@6d03e736

    //both objects have access to the method defined within the interface
    System.out.println(greetingLambdas.simpleGreeting()); //Hello from a lambdas function
    System.out.println(greetingAnonymous.simpleGreeting()); //Hello from an anonymous function
  }
}
```
2. A functional interface with a single input argument

```java
package simplepracticeclasses;

public interface Greeting {
    //Functional interface with a single input argument
    String simpleGreeting(String name);
}
```

```java
public class Main{
  public static void main(String[] args) {

    //Implementing the 'Greeting' functional interface and assigning the result of the method to a variable

    //Anonymous class method
    Greeting greetingAnonymous = new Greeting() {
      @Override
      public String simpleGreeting(String name) {
        return String.format("Hello to %s from anonymous class.",name);
      }
    };

    //Lambda expression method
    Greeting greetingLambdas = (String name) -> {
      name = name.toUpperCase();
      return String.format("Hello to %s from Lambda.",name);};

    //both objects have access to the method defined within the interface
    System.out.println(greetingLambdas.simpleGreeting("robert")); //Hello to ROBERT from Lambda.
    System.out.println(greetingAnonymous.simpleGreeting("john")); //Hello to John from anonymous class.
  }
}
```

**(D) Implementing Generic Interfaces Using Lambda**

A generic functional interface
```java
package simplepracticeclasses;

//generic interface with generic type parameter
public interface Filter<T> {
    boolean test(T element);
}
```
A Home class

```java
package simplepracticeclasses;

public class Home {

    private String type;
    private String city;
    private int areaSqFt;
    private int price;

    public Home(String type, String city, int areaSqFt, int price) {
        this.type = type;
        this.city = city;
        this.areaSqFt = areaSqFt;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAreaSqFt() {
        return areaSqFt;
    }

    public void setAreaSqFt(int areaSqFt) {
        this.areaSqFt = areaSqFt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%nType:%s, City:%s, Area:%d, Price:%d", type, city, areaSqFt, price);
    }
}

```

A generic method that takes in two arguments; a generic list, and any object that implements the generic functional interface.\
The implementation is done via lambda expression.
```java
public class Main{
  
  //generic method  
  private static <T> List<T> filterAnything(List<T> elementList, Filter<T> filterImpl){
    //Note homeFilterImpl must be an object/class that implements the HomeFilter interface
    List<T> filteredList = new ArrayList<>();
    for (T elements: elementList) {
      if(filterImpl.test(elements)){
        filteredList.add(elements);
      }
    }
    return filteredList;
  }
  
  //main method
  public static void main(String[] args) {
      
    Home home1 = new Home("browstone", "New York", 1200, 3_200_000);
    Home home2 = new Home("townhome", "Prague", 5000, 200_000);
    Home home3 = new Home("cottage", "Vienna", 240, 800_000);
    Home home4 = new Home("villa", "Paris", 6200, 4_600_000);

    List<Home> homeList = new ArrayList<>();
    Collections.addAll(homeList, home1, home2, home3, home4);

    List<Home> filteredHomeList = filterAnything(homeList,
            //Any object that implements the generic 'Filter' interface
            //Lambda implementation of the generic functional interface 'Filter'
            (home) -> (home.getPrice() >= 100_000 &&
                    home.getPrice() < 1000_000 &&
                    home.getCity().equalsIgnoreCase("prague")));
    System.out.println(filteredHomeList);//[Type:townhome, City:Prague, Area:5000, Price:200000]

  }
}
```

**(E) Using @FunctionalInterface Annotation**
A built-in annotation in java that only serves information purpose

```java
package simplepracticeclasses;

//generic interface with generic type parameter
@FunctionalInterface
public interface Filter<T> {
    boolean test(T element);
}
```
While the interface can't have more than one abstract method, it can many methods of the java.lang.Object base class.

```java
package simplepracticeclasses;

@FunctionalInterface
public interface Greeting {

    //Custom method.
    String simpleGreeting(String name);

    //Methods of the base class; java.lang.Object
    String toString();

    int hashCode();

    boolean equals(Object obj);
}
```
**(F) Predicate, Consumer, Function, Supplier**
**Types of Functional Interfaces:**
A Predicate Interface is a functional interface used to filter a collection of objects. It performs a test/conditional\
check on the object. The Filter interface from previous sections is a typical example of predicates.
A Consumer Interface is used to consume data, acts as a sink for the filtered or processed data. The final data will be
consumed: printout to screen, store in database e.t.c. Its method is usually named accept().
The Functional interface is the function/method that is typically used to transform data from e.g. type T to type R. Its\
apply method typically does transformation operations.
Below is the use of both Predicate, Functional, and Consumer Functional interface by a method to perform filtering,\
transforming and consuming operation.
1. Predicate interface
```java
public interface Predicate<T>{
    boolean test(T t);
}
```
2. Consumer interface
```java
public interface Consumer <T>{
    void accept(T t);
}
```
3. Function interface
```java
public interface Function<T, R>{
    R apply(T t);
}
```
4. A filtering operation method and the main method
```java
public class Main {
  public static void main(String[] args) {

    Home home1 = new Home("browstone", "New York", 1200, 3_200_000);
    Home home2 = new Home("townhome", "Prague", 5000, 200_000);
    Home home3 = new Home("cottage", "Vienna", 240, 800_000);
    Home home4 = new Home("villa", "Paris", 6200, 4_600_000);

    List<Home> homeList = new ArrayList<>();
    Collections.addAll(homeList, home1, home2, home3, home4);

    filterPredFunCon(homeList,
            //PREDICATE
            (home) -> (home.getPrice() >= 100_000 && home.getPrice() < 1000_000),
            //FUNCTION
            home -> {
              home.setCity(home.getCity().toUpperCase());
              home.setType(home.getType().toUpperCase());
              return home;
            },
            //CONSUMER
            (home) -> System.out.println(home)
    );
        /*
        Type:TOWNHOME, City:PRAGUE, Area:5000, Price:200000
        Type:COTTAGE, City:VIENNA, Area:240, Price:800000
         */
  }
}
```
The Supplier Interface is used to supply/get objects on which an operation will be performed.
```java
public interface Supplier{
    T get();
}
```

All the functions java package java.util.function package contains all the four packages explained. Hence, instead of\
going through the trouble of defining it as above, they can be used from this package. 

**(G) Method References: Static and Instance Methods**
Method references makes the lambda expressions and statement more readable and compact.
The most commonly used method reference is static method reference.
1. Example of method reference on the **SSystem class**
```java
public class Main{
  public static void main(String[] args) {

    List<String> writers = Arrays.asList("Ernest Hemingway", "Chinua Achebe", "Wole Soyinka", "J.K. Rowling");


    //USING FOR EACH LOOP
    System.out.println("--Using forEach Loop--");
    for(String writer:writers){
      System.out.println(writer);
    }
        /*
        --Using forEach Loop--
        Ernest Hemingway
        Chinua Achebe
        Wole Soyinka
        J.K. Rowling
         */


    //USING LAMBDA
    System.out.println("***Using Lambda***");
    writers.forEach((writer) -> System.out.println(writer));
        /*
        ***Using Lambda with Method Reference***
        Ernest Hemingway
        Chinua Achebe
        Wole Soyinka
        J.K. Rowling
         */


    //USING METHOD REFERENCE
    System.out.println("+++Using Method Reference+++");
    writers.forEach(System.out::println);
        /*
        +++Using Method Reference+++
        Ernest Hemingway
        Chinua Achebe
        Wole Soyinka
        J.K. Rowling
         */
  }
}
```

2. Method Reference with a **Static Method** of a Custom Class

```java
package simplepracticeclasses;

public class Home {

    private String type;
    private String city;
    private int areaSqFt;
    private int price;

    public Home(String type, String city, int areaSqFt, int price) {
        this.type = type;
        this.city = city;
        this.areaSqFt = areaSqFt;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getAreaSqFt() {
        return areaSqFt;
    }

    public void setAreaSqFt(int areaSqFt) {
        this.areaSqFt = areaSqFt;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("%nType:%s, City:%s, Area:%d, Price:%d", type, city, areaSqFt, price);
    }

    public static int compareByPrice(Home home1, Home home2) {
        return Integer.valueOf(home1.getPrice()).compareTo(home1.getPrice());
    }
}
```

```java
public class Main{
    public static void main(String[] args){

        Home home1=new Home("browstone","New York",1200,3_200_000);
        Home home2=new Home("townhome","Prague",5000,200_000);
        Home home3=new Home("cottage","Vienna",240,800_000);
        Home home4=new Home("villa","Paris",6200,4_600_000);

        List<Home> homeList=new ArrayList<>();
        Collections.addAll(homeList,home1,home2,home3,home4);


        //Sort operation using Lambda expression along with custom comparator from the class to sort
        Collections.sort(homeList,
        (o1,o2)->Home.compareByPrice(o1,o2)
        );

        System.out.println(homeList);
        /*
        [Type:townhome, City:Prague, Area:5000, Price:200000,
        Type:cottage, City:Vienna, Area:240, Price:800000,
        Type:browstone, City:New York, Area:1200, Price:3200000,
        Type:villa, City:Paris, Area:6200, Price:4600000]
         */


        //Sort operation using STATIC METHOD REFERENCE along with custom comparator from the class to sort
        Collections.sort(homeList,Home::compareByPrice);
        }
}
```

3. Method Reference with **Instance Method** of a Custom Class
```java
public class Main {
  public static void main(String[] args) {

    Home home1 = new Home("browstone", "New York", 1200, 3_200_000);
    Home home2 = new Home("townhome", "Prague", 5000, 200_000);
    Home home3 = new Home("cottage", "Vienna", 240, 800_000);
    Home home4 = new Home("villa", "Paris", 6200, 4_600_000);

    List<Home> homeList = new ArrayList<>();
    Collections.addAll(homeList, home1, home2, home3, home4);


    //START OF LOCAL CLASS
    class ComparisonUtility {
      public int compareByPrice(Home h1, Home h2) {
        return Integer.valueOf(h1.getPrice()).compareTo((h2.getPrice())) * -1;
      }
    }//END OF LOCAL CLASS

    //AN INSTANCE OF THE LOCAL CLASS
    ComparisonUtility comparisonUtilityInstance = new ComparisonUtility();

    //USING THE method of the local class as argument for the sort
    //Instance reference to the method of the local class
    Collections.sort(homeList, comparisonUtilityInstance::compareByPrice);
    System.out.println(homeList);
        /*
        [Type:villa, City:Paris, Area:6200, Price:4600000,
        Type:browstone, City:New York, Area:1200, Price:3200000,
        Type:cottage, City:Vienna, Area:240, Price:800000,
        Type:townhome, City:Prague, Area:5000, Price:200000]
        */
  }
}
```

## 12: Java: Getting Started with Reflection
* Reflection is a programming technique that accesses and modifies class and object information at runtime.
* Java have some powerful set of Reflection APIs which reflects the emphasis that java place on type safety.
* All instances of class in java are represented by the `java.lang.Class`
* All instances of objects in java re represented by the `java.lang.Object`
* Employee Class

```java
package simplepracticeclasses;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter //Use of lombok library to reduce boilerplate codes(getters and setters).
public class Employee {

    public static final String organization = "DHL";
    private static final Random employeeIdGenerator = new Random();

    private int employeeId;
    public String name;
    public String title;
    private double salary;

    //Individual construction of a single parameter
    public Employee() {
        this.employeeId = Math.abs(employeeIdGenerator.nextInt());
    }

    //NOTE the constructor chaining
    public Employee(String name, String title, double salary) {
        //Constructor chaining with default constructor: Makes the the default no-argument constructor available
        this(); //default constructor
        this.name = name;
        this.title = title;
        this.salary = salary;
    }

    private double computerBonusInternal(float bonusPercentage) {
        return salary * bonusPercentage;
    }

    private double computeTotalSalary(float bonusPercentage) {
        return salary + computerBonusInternal(bonusPercentage);
    }

    @Override
    public String toString() {
        return String.format("Employee ID:%d, Name:%s, Title:%s, Salary:%f", employeeId, name, title, salary);
    }
}
```
* Main
```java
public class Main{
  public static void main(String[] args) {

    //An object of the Employee class using the default no-argument constructor
    Employee jack = new Employee();
    System.out.println(jack); //Employee ID:77593495, Name:null, Title:null, Salary:0.000000

    //An object of the Employee class using a parameterized constructor

    Employee henry = new Employee("Henry", "Senior Engineer", 10);
    System.out.println(henry); //Employee ID:1227223275, Name:Henry, Title:Senior Engineer, Salary:10.000000

    //Invoccation of member methods on the object
    jack.setName("Jack");
    jack.setTitle("Trainee");
    System.out.println(jack); //Employee ID:811101237, Name:Jack, Title:Trainee, Salary:0.000000
    
  }
}
```
**(A) Extracting Class Information from Objects**
* Two _retrospection_ methods used for obtaining metadata of class (types/class that an object belongs to) 
* Every **class** is both an **object** and a **variable**.
* Hence, it's possible to invoke method directly on a class (i.e. _Employee.class, Employee.class instanceof)_
* All class java are instance of java.lang.Class 
```java
public class Main {
  public static void main(String[] args) {
    
    Employee henry = new Employee("Henry", "Senior Engineer", 10);

    //Getting the class information/metadata
    //Package name always comes before the class name
    System.out.println(henry.getClass()); //class practice.Employee
    System.out.println(Employee.class); //class practice.Employee

    System.out.println(henry.getClass() instanceof java.lang.Class); //true
    System.out.println(Employee.class instanceof java.lang.Class); //true

    //All class java are instance of java.lang.Class
    //This evaluates to true cuz they are objects of the java.lang.Class
    System.out.println(henry.getClass().equals(Employee.class)); //true
  }
}
```

**(B) Viewing Class Fields, Methods, Constructors and Their Modifiers**
Making use of the java _Reflection API_ to access fields, methods, and constructors of a class/objects
1. Accessing the **fields** of a class/object
The `getFields()` method returns only the _public_ fields of class.
The `getDeclaredFields()` method returns both _private_ and _protected_ fields of a class.

```java
package simplepracticeclasses;
//important import

import java.lang.reflect.Field;

public class Main {
    public static void main(String[] args) {

        Employee henry = new Employee("Henry", "Senior Engineer", 10);

        //"wildcard ?" to denote a placeholder for the type parameter.
        Class<?> employeeClass = henry.getClass();
        System.out.println(employeeClass); //class practice.Employee

        //FIELDS OF A CLASS/OBJECT

        //Returns an array of Field object
        Field[] employeeClassFields = employeeClass.getFields();
        System.out.println(employeeClassFields); //[Ljava.lang.reflect.Field;@29453f44

        //Iterating over the array of field stored in employeeClassFields
        for (Field fieldObject : employeeClassFields) {
            System.out.println(fieldObject);
        }
        /*
        public static final java.lang.String practice.Employee.organization
        public java.lang.String practice.Employee.name
        public java.lang.String practice.Employee.title
         */


        Field[] employeeClassDeclaredFields = employeeClass.getDeclaredFields();
        for (Field fieldObject : employeeClassDeclaredFields) {
            System.out.println(fieldObject);
        }
        /*
        public static final java.lang.String practice.Employee.organization
        private static final java.util.Random practice.Employee.employeeIdGenerator
        private int practice.Employee.employeeId
        public java.lang.String practice.Employee.name
        public java.lang.String practice.Employee.title
        private double practice.Employee.salary
         */
    }
}
```
2. Accessing the **methods** of a class/object.
The `getMethods()` method returns only the _public_ methods of class.
The `getDeclaredMethod()` method returns both _private_ and _protected_ methods of a class.
Note that the printedout of getMethods methods in this example is rather long due to the lombok library. While the other\
methods accesses only private and protected methods particular to the Employee class itself. 
```java
import java.lang.reflect.Method; //Important import

public class Main{
  public static void main(String[] args) {

    Employee henry = new Employee("Henry", "Senior Engineer",10);

    //"wildcard ?" to denote a placeholder for the type parameter.
    Class<?> employeeClass = henry.getClass();
    System.out.println(employeeClass); //class practice.Employee

    //METHODS OF A CLASS/OBJECT

    //Returns an array of Method object
    Method[] employeeClassMethod = employeeClass.getMethods();
    System.out.println(employeeClassMethod); //[Ljava.lang.reflect.Method;@29453f44

    //Iterating over the array of Method stored in  employeeClassMethod
    for(Method methodObject : employeeClassMethod){
      System.out.println(methodObject);
    }
        /*
        public java.lang.String practice.Employee.toString()
        public java.lang.String practice.Employee.getName()
        public void practice.Employee.setName(java.lang.String)
        public int practice.Employee.getEmployeeId()
        public void practice.Employee.setSalary(double)
        public void practice.Employee.setTitle(java.lang.String)
        public double practice.Employee.getSalary()
        public java.lang.String practice.Employee.getTitle()
        public void practice.Employee.setEmployeeId(int)
        public final void java.lang.Object.wait() throws java.lang.InterruptedException
        public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
        public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
        public boolean java.lang.Object.equals(java.lang.Object)
        public native int java.lang.Object.hashCode()
        public final native java.lang.Class java.lang.Object.getClass()
        public final native void java.lang.Object.notify()
        public final native void java.lang.Object.notifyAll()
         */
    
    Method[] employeeClassDeclaredMethod = employeeClass.getDeclaredMethods();
    System.out.println(employeeClassDeclaredMethod); //[Ljava.lang.reflect.Method;@5cad8086

    //Iterating over the array of field stored in employeeClassFields
    for(Method methodObject : employeeClassDeclaredMethod){
      System.out.println(methodObject);
    }
        /*
        [Ljava.lang.reflect.Method;@5cad8086
        public java.lang.String practice.Employee.toString()
        public java.lang.String practice.Employee.getName()
        public void practice.Employee.setName(java.lang.String)
        public void practice.Employee.setTitle(java.lang.String)
        public void practice.Employee.setEmployeeId(int)
        public java.lang.String practice.Employee.getTitle()
        public void practice.Employee.setSalary(double)
        public double practice.Employee.getSalary()
        public int practice.Employee.getEmployeeId()
        private double practice.Employee.computerBonusInternal(float)
        private double practice.Employee.computeTotalSalary(float)
         */
  }
}
```

3. Accessing the **constructors** of a class/object.
```java
import java.lang.reflect.Constructor; //important import
public class Main{
  public static void main(String[] args) {

    Employee henry = new Employee("Henry", "Senior Engineer",10);

    //"wildcard ?" to denote a placeholder for the type parameter.
    Class<?> employeeClass = henry.getClass();
    System.out.println(employeeClass); //class practice.Employee

    //CONSTRUCTORS OF A CLASS/OBJECT

    //Returns an array of Constructor objects
    //Note that constructor is a parametrized object. Hence, the use of wildcard?.
    Constructor<?>[] employeeClassConstructors = employeeClass.getConstructors();
    System.out.println(employeeClassConstructors); //[Ljava.lang.reflect.Constructor;@7ea987ac

    //Iterating over the array of Constructors stored in employeeClassConstructors
    for(Constructor<?> constructorObject : employeeClassConstructors){
      System.out.println(constructorObject);
    }
        /*
        public practice.Employee()
        public practice.Employee(java.lang.String,java.lang.String,double)
         */
  }
}
```

**(C) Examining Class Objects for Different Data Types**
**Refresher:**
* A relevant classification of types related to reflections in java is the **Primitive** and **Reference** Types
* All _Reference Types_ **inherits** from a common universal base class(`java.lang.Object`). The non-reference types are\
are they _primitive types_. The primitive types **DON'T** _inherit_ from the common base class(`java.lang.Object`). Hence,
they are not an Object as such.
* Primitive types **DON'T** have methods/functions. Impossible to invoke methods on primitive types.
* Example of reference types are the wrapper types: Double, Integer, String e.t.c.
* Example of primitive types are double, int, boolean, float.
```java
public class Main{
  public static void main(String[] args) {

    Object object = new Object();
    System.out.println(object.getClass()); //class java.lang.Object
    System.out.println(Object.class); //class java.lang.Object

    Integer integerObject = 123;
    System.out.println(integerObject.getClass()); //class java.lang.Integer
    System.out.println(Integer.class); //class java.lang.Integer

    Double doubleObject = 12.3;
    System.out.println(doubleObject.getClass()); //class java.lang.Double
    System.out.println(Double.class); //class java.lang.Double

    String stringObject = "Shola";
    System.out.println(stringObject.getClass()); //class java.lang.String
    System.out.println(String.class); //class java.lang.String

    System.out.println((String.class instanceof java.lang.Object)); //true
    System.out.println((Integer.class instanceof java.lang.Object)); //true
    System.out.println((String.class instanceof java.lang.Object)); //true

  }
}
```
* An ArrayList is a _generic container_ which means it can be either instantiated with/without a parameter.However, use\
of un-parameterized (raw) objects usually lead to lose of type safety.
* However, as shown below, the getClass() method doesn't return the type parameter. The getClass() output of the\
parametrized and raw object of ArrayList is same.

```java
public class Main{
  public static void main(String[] args) {

    ArrayList arrayList = new ArrayList<>();
    System.out.println(arrayList.getClass()); //class java.util.ArrayList

    ArrayList<Integer> integerArrayList = new ArrayList<>();
    System.out.println(integerArrayList.getClass()); //class java.util.ArrayList
    
    //Variable type:List interface, Actual class: ArrayList class
    List<Double> doubleList = new ArrayList<>();
    System.out.println(doubleList.getClass()); //class java.util.ArrayList

  }
}
```

**(D) Identifying Classes Using Objects**
Every object holds information about its class.
**Reminder:**`Array` can hold both primitive types (int, double) and wrapper/reference types(Integer, Double). On the\
other hand,`ArrayList` can hold only the wrapper/reference types.
```java
public class Main {
  public static void main(String[] args) {

    List<Integer> integersList = new ArrayList<>();
    List<Float> floatsList = new ArrayList<>();
    System.out.println(integersList.getClass()); //class java.util.ArrayList
    System.out.println(floatsList.getClass()); //class java.util.ArrayList

    //Arrays of wrapper type
    Integer[] integersArray = {5, 67, 8};
    System.out.println(integersArray.getClass()); //class [Ljava.lang.Integer;
    System.out.println(Integer[].class); //class [Ljava.lang.Integer;
    System.out.println(Integer.class); //class java.lang.Integer

    Float[] floatsArray = {5.4f, 3.1f, 2.4f};
    System.out.println(floatsArray.getClass()); //class [Ljava.lang.Float;
    System.out.println(Float[].class); //class [Ljava.lang.Float;
    System.out.println(Float.class); //class [Ljava.lang.Float;

    System.out.println("-----");
    //Arrays of the primitive type
    int[] integerArray2 = {5, 9, 11};
    System.out.println(integerArray2.getClass()); //class [I
    System.out.println(int[].class); //class [I

    float[] floatArrays2 = {5.6f};
    System.out.println(floatArrays2.getClass()); //class [F

  }
}
```
* In the output for _Array of wrapper types_ --> **[**: represents an array. More [[ would mean 2-D array e.t.c
* In the output for _Array of primitive types:_ --> **[I**: 1-D array of the primitive type int.

```java
public class AppaMain{
  public static void main(String[] args) {
    enum Weekdays{
      Monday, Tuesday, Wednesday, Thursday, Friday
    }

    Weekdays day = Weekdays.Friday;

    System.out.println(day.getClass()); // class practice.AppaMain$Weekday

  }
    
}
```
**NOTE:**
Primitive variables/types are **NOT** objects. They have NO objects or methods. Hence, we if we have a variable of the\
primitive types(such as shown below) we cant invoke any method including getClass() on them.
However, even the primitive types are represented by classes. Hence, we are able to use .class
```java
public class Main{
  public static void main(String[] args) {
    int intval = 23;
    float floatval = 23f;
    double doubleval = 3.0;
    boolean booleanval = true;
    //THIS DONT WORK......System.out.print(intval.getClass())

    System.out.println(int.class); //int
    System.out.println(float.class); //float
    System.out.println(double.class); // double
    System.out.println(boolean.class); //boolean
  }
}
```
**(D) Viewing Fully Qualified Class Names**
It is possible to mark a class as final
**Reminder:** 
An **abstract** class is a class with at least one unimplemented method which its children class must implement. Abstract\
classes **CAN'T** be instantiated directly. (e.g Department class below)
A **Final** class is a class that can't be extended. The leaf node in an inheritance hierrarchy tree.

```java
package simplepracticeclasses;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public final class Organization {
    private String name;

    //default constructor
    public Organization() {
    }

    @Override
    public String toString() {
        return "Organization{" +
                "name='" + name + '\'' +
                '}';
    }
}
```

```java
package simplepracticeclasses;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Department {

    public String name;

    public Department() {

    }

    public Department(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}

```

```java
public class AppaMain{
  //static inner class  
  public static class HR extends Department{

    //The Constructor of the HR class invokes the constructor of its parent using the 'super' keyword
    public HR(){
      super("HR");
    }

  }
  public static void main(String[] args) {

    Organization organization = new Organization();
    Employee employee = new Employee();
    HR hr = new HR();
    //Department class is abstract and so cannot be instantiated.

    Class<?> organizationClass = organization.getClass();
    Class<?> employeeClass = employee.getClass();
    Class<?> hrClass = hr.getClass();
    Class<?> depatmentClass = Department.class;


    System.out.println(organizationClass); //class practice.Organization
    System.out.println(employeeClass); //class practice.Employee
    System.out.println(hrClass); //class practice.AppaMain$HR   //inner class
    System.out.println(depatmentClass); //class practice.Department


  }
}
```

The static method class forName() is third way of getting the class of an object by providing its FQCN. This serves,\
the purpose of compile-time check with the ClassNotFound Exception.\
The Methods takes the FQCN and returns the class. Hence, does same as `Employee.class` or `employeeInstance.getClass()`

```java
public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class<?> organizationClass = Class.forName("simplepracticeclasses.Organization");
        Class<?> employeeClass = Class.forName("simplepracticeclasses.Employee");
        Class<?> hrClass = Class.forName("simplepracticeclasses.AppaMain$HR");
        Class<?> departmentClass = Class.forName("simplepracticeclasses.Department");
    }
}
```
**(E) Viewing Modifiers of Class Members**
**Reminder:** example of modifiers; static, final, private e.t.c
The method `getModifiers()` of the `java.lang.reflect` API returns an integer value that represents the modifier of the\ 
class. This int value is not very informative. However, the API provides other informative methods that works in\
conjunction with the getModifiers() method.
```java
import java.lang.reflect.Modifier;
public class Main {
  public static final class HR extends Department {

    //The Constructor of the HR class invokes the constructor of its parent using the 'super' keyword
    public HR() {
      super("HR");
    }

  }

  public static void main(String[] args) throws ClassNotFoundException {

    int organizationClass = Organization.class.getModifiers(); //final class
    Class<?> employeeClass = Employee.class; //normal class
    Class<?> hrClass = HR.class; //inner final class
    Class<?> departmentClass = Department.class; //abstract class

    System.out.println(organizationClass); //17
    System.out.println(Integer.toBinaryString(organizationClass)); //10001

    System.out.println(Modifier.isPublic(organizationClass)); //true
    System.out.println(Modifier.isFinal(organizationClass)); //true
    System.out.println(Modifier.isAbstract(organizationClass)); ///false
    
  }
}
```

**(F) Anonymous, Local, Member Classes, and Interfaces**
Reminder: **Interfaces**(e.g. Deliverables interface) can't be directly instantiated.

```java
package simplepracticeclasses;

public interface Deliverables {

    String getProjectName();

    String getProjectStatus();
}

```
```java
public class Main {
  public static void main(String[] args) throws ClassNotFoundException {


    System.out.println(Cars.class.isAnonymousClass()); //false
    System.out.println(Cars.class.isLocalClass()); //false
    System.out.println(Cars.class.isInterface());//false
  }
}
```
Implementation of the Deliverables **Interface** using an **Anonymous class**.
```java
public class AppaMain{
  public static void main(String[] args) throws ClassNotFoundException{

    //Implementation of the Deliverables interface using anonymous class
    Deliverables deliverablesInstance = new Deliverables() {
      @Override
      public String getProjectName() {
        return "Mobile App";
      }

      @Override
      public String getProjectStatus() {
        return "Completed";
      }
    };

    System.out.println(deliverablesInstance.getProjectName()); //Mobile Appp
    System.out.println(deliverablesInstance.getClass()); //class practice.AppaMain$1
    System.out.println(deliverablesInstance.getClass().isInterface()); //false
    System.out.println(deliverablesInstance.getClass().isLocalClass());//false
    System.out.println(deliverablesInstance.getClass().isAnonymousClass()); //true

  }
}
```
Implementation of the Deliverables **Interface** using a **Local class**. Local means its local to a particular method.\
In the case, the class `MobileAppClass` local to the method `main` inside the `AppaMain` class.
```java
public class Main {
  public static void main(String[] args) throws ClassNotFoundException {

    //A local class
    class MobileAppClass implements Deliverables {
      String appName;

      //Constructor
      public MobileAppClass(String appName) {
        this.appName = appName;
      }

      //Overriden methods from the interface
      @Override
      public String getProjectName() {
        return "Mobile App";
      }

      @Override
      public String getProjectStatus() {
        return "Completed";
      }
    }

    //An instance of the inner class
    MobileAppClass android = new MobileAppClass("Chess game");
    System.out.println(android.appName); //Chess game
    System.out.println(android.getClass().isAnonymousClass()); //false
    System.out.println(android.getClass().isLocalClass()); //true
    System.out.println(android.getClass().isInterface()); //false

  }
}
```

Implementation of the Deliverables **Interface** using a **Inner/Member class**. The difference is that a local class is\
defined within the body of a method(e.g. main method...see above) while an inner class resides within the broad body\
of another class(e.e.g defined inside the AppaMain class) NOT in a method.
```java
public class AppaMain{
  public static class MobileClass2 implements Deliverables{

    //Overriden methods from the interface
    @Override
    public String getProjectName() {
      return "Mobile App";
    }

    @Override
    public String getProjectStatus() {
      return "Completed";
    }
  }

  public static void main(String[] args) throws ClassNotFoundException{
    System.out.println(MobileClass2.class.isMemberClass());//true
    System.out.println(MobileClass2.class.isLocalClass());//false
  }
}
```

**(G) Identifying Arrays and Primitives**
```java
public class Main {
  public static void main(String[] args) throws ClassNotFoundException {

    int[] integerArray = {5, 6, 9};
    System.out.println(integerArray.getClass().isArray()); //true
    System.out.println(integerArray.getClass().isPrimitive());//false cuz it's an 'array object' holding primitives types
  }
}
```
**(H) Viewing Class Package, Superclass, Declaring Class**
```java
public class AppaMain{
  public static void main(String[] args) throws ClassNotFoundException{

    Class<?> organizationClass = Organization.class;
    Class<?> employeeClass = Employee.class;
    Class<?> hrClass = HR.class;
    Class<?> departmentClass = Department.class;

    //local class
    class MobileApp implements Deliverables{
      @Override
      public String getProjectName() {
        return "Booking App";
      }

      @Override
      public String getProjectStatus() {
        return "UAT";
      }
    }

    Class<?> mobileAppClass = MobileApp.class;

    System.out.println(organizationClass.toGenericString()); //public final class practice.Organization
    System.out.println(employeeClass.toGenericString()); //public class practice.Employee
    System.out.println(hrClass.toGenericString()); //public static final class practice.AppaMain$HR
    System.out.println(departmentClass.toGenericString()); //public abstract class practice.Department
    System.out.println(mobileAppClass.toGenericString()); //class practice.AppaMain$1MobileApp

    System.out.println(organizationClass.getPackage()); //package practice

    System.out.println(mobileAppClass.getSuperclass());//class java.lang.Object
    System.out.println(hrClass.getSuperclass()); //class practice.Department

    System.out.println(hrClass.getDeclaringClass());//class practice.AppaMain
    System.out.println(hrClass.getEnclosingClass());//class practice.AppaMain

    System.out.println(organizationClass.getDeclaringClass()); //null
    System.out.println(organizationClass.getEnclosingClass()); //null

  }
}
```
## 13: Java: Accessing Constructors, Methods, & Fields Using Reflection
**(A) Accessing Constructors Using Reflection**
The `java.lang.reflect.Constructor` uses the reflection API to construct new instances of a class.\
Some relevant modifications have been made to Employee class: there are now 4 different constructors and some member 
fields/properties have initial values.

```java
package simplepracticeclasses;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter //Use of lombok library to reduce boilerplate codes(getters and setters).
public class Employee {
    public static final String organization = "DHL";
    private static final Random employeeIdGenerator = new Random();
    private int employeeId;
    public String name = "Unknown";
    public String title = "Unknown";
    private double salary = 0.0;

    //Individual construction of a single parameter. It's also a 'no argument' constructor  but not default.
    public Employee() {
        this.employeeId = Math.abs(employeeIdGenerator.nextInt());
    }

    private Employee(String name) {
        this();
        this.name = name;
    }

    protected Employee(String name, String title) {
        this(name);
        this.title = title;
    }

    public Employee(String name, String title, double salary) {
        this(name, title);
        this.salary = salary;
    }

    private double computerBonusInternal(float bonusPercentage) {
        return salary * bonusPercentage;
    }

    private double computeTotalSalary(float bonusPercentage) {
        return salary + computerBonusInternal(bonusPercentage);
    }

    @Override
    public String toString() {
        return String.format("Employee ID:%d, Name:%s, Title:%s, Salary:%f", employeeId, name, title, salary);
    }

}
```
The signature of the main method contains the following exceptions:
1. The `InstantiationException` deals with instantiation of abstract class(abstract class aren't meant to be instantiated).
2. The `NoSuchMethodException` deals with calling a non-existent method in a class.
3. The `InvocationTargetException` thrown when the underlying constructor throws any exception in general.
4. The `IllegalAccessException` thrown when the constructor object needs to enforced access control based on modifiers.
Access to ALL (private, public, protected e.t.c) is provided by `getDeclaredConstructors()` while `getConstructors()`\
gives access to only the public constructor.
```java
public class AppaMain{
  public static void main(String[] args)
          throws
          ClassNotFoundException,
          NoSuchMethodException,
          IllegalAccessException,
          InvocationTargetException,
          InstantiationException
  {
    //The class object metadata
    Class<?> employeeClass = Employee.class;

    //Obtaining the public constructor from the class metadata information.
    Constructor<?>[] constructorObjectsContainer = employeeClass.getConstructors();
    System.out.println(constructorObjectsContainer); //[Ljava.lang.reflect.Constructor;@4b67cf4d

    for(Constructor constructorObject : constructorObjectsContainer){
      System.out.println(constructorObject);
    }
        /*
        public practice.Employee(java.lang.String,java.lang.String,double)
        public practice.Employee()
         */


    System.out.println("-----");

    //Obtaining ALL constructor from the class metadata information.
    Constructor<?>[] constructorObjectsContainer2 = employeeClass.getDeclaredConstructors();
    //Nested forloop
    for(Constructor constructorObject : constructorObjectsContainer2){
      //System.out.println("-------");
      System.out.println(constructorObject);

      //Accessing the parameters for the current constructor
      Class<?>[] parameters = constructorObject.getParameterTypes();
      for(Class<?>parameter : parameters){
        System.out.println(parameter);
      }
      System.out.println("-------");
    }
        /*
        -----
        public practice.Employee(java.lang.String,java.lang.String,double)
        class java.lang.String
        class java.lang.String
        double
        -------
        protected practice.Employee(java.lang.String,java.lang.String)
        class java.lang.String
        class java.lang.String
        -------
        private practice.Employee(java.lang.String)
        class java.lang.String
        -------
        public practice.Employee()
        -------
         */
  }
}
```

**(B) Creating Objects Using Constructor Handles**
The method `getConstructors()` gives access to metadata information. However `getConstructor()` enables the creation of\
a Constructor object from the available constructors(s) of a class. 
The Employee class, has 3 different parametrized constructors and 1 no-argument constructor. A new constructor object\ 
can then be created from the obtained constructor (`getConstructor()`)object using the `newInstance()` method.\
The constructor object created, even though is an instance of Employee class, has no direct access to the methods or\ 
parameters of the Employee class. It instead has access only to the methods of the `java.lang.Object`.\
The constructor object can be **down casted** to the employee class. This provides access to the methods and variables\
of the Employee class.\
```java
public class Main {
  public static void main(String[] args)
          throws
          ClassNotFoundException,
          NoSuchMethodException,
          IllegalAccessException,
          InvocationTargetException,
          InstantiationException {
    //The class object metadata
    Class<?> employeeClass = Employee.class;

    //The no-argument constructor of the employee class
    Constructor<?> noArgumentConstructor = employeeClass.getConstructor();
    System.out.println(noArgumentConstructor); //public practice.Employee()

    //Creating a constructor instance from the obtained constructor instance above.
    Object objectOfNoArgumentConstructor = noArgumentConstructor.newInstance();
    System.out.println(objectOfNoArgumentConstructor); //Employee ID:1180547091, Name:Unknown, Title:Unknown, Salary:0.00
    System.out.println(objectOfNoArgumentConstructor instanceof Employee); //true

    //Down casting to Employee class
    Employee employee1 = (Employee) noArgumentConstructor.newInstance();
    System.out.println(employee1); //Employee ID:254463353, Name:Unknown, Title:Unknown, Salary:0.00

    employee1.setName("Shola");
    employee1.setTitle("Senior DevOps Engineer");
    employee1.setSalary(1_200_000);
    System.out.println(employee1);//Employee ID:60498197, Name:Shola, Title:Senior DevOps Engineer, Salary:1200000.00


    //Three arguments constructor (public constructor)
    Constructor<?> threeArgumentsConstructor = employeeClass.getConstructor(String.class, String.class, double.class);
    Employee employee2 = (Employee) threeArgumentsConstructor.newInstance("Soyinka", "Poet", 56000);
    System.out.println(employee2); //Employee ID:154610169, Name:Soyinka, Title:Poet, Salary:56000.00


    //One arguments constructor (private constructor)
    Constructor<?> oneArgumentPrivateConstructor = employeeClass.getDeclaredConstructor(String.class);
    //Change the accessibility level of the constructor
    oneArgumentPrivateConstructor.setAccessible(true);
    Employee employee3 = (Employee) oneArgumentPrivateConstructor.newInstance("Achebe");
    System.out.println(employee3); //Employee ID:1641497877, Name:Achebe, Title:Unknown, Salary:0.00
  }
}
```

**(B) Accessing Field Metadata**
Fields can refer to both member parameters/variables and methods/function. However, this section covers fields in terms\
of member variables using the `java.lang.reflect.Field` API.\
The modifier `transient` is relevant to serialization of objects. It prohibits java from serializing the object.\
The keyword `volatile` is used for atomic access. Variables are marked as such to reduce the risk of memory consistency\
errors for multithreaded applications.

```java
package simplepracticeclasses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Random;

@Getter
@Setter //Use of lombok library to reduce boilerplate codes(getters and setters).
public class Employee {

    enum contactType {
        FULLTIME,
        PARTTIME,
        CONTRACT
    }

    public static final String organization = "DHL";
    private static final transient Random employeeIdGenerator = new Random();
    private int employeeId;
    public String name = "Unknown";
    public String title = "Unknown";
    private double salary = 0.0;

    private Department department; //a member variable of the abstract class type 'Department'

    public volatile List<String> committees;

    //Individual construction of a single parameter. It's also a 'no argument' constructor  but not default.
    public Employee() {
        this.employeeId = Math.abs(employeeIdGenerator.nextInt());
    }

    private Employee(String name) {
        this();
        this.name = name;
    }

    protected Employee(String name, String title) {
        this(name);
        this.title = title;
    }

    public Employee(String name, String title, double salary) {
        this(name, title);
        this.salary = salary;
    }

    private double computerBonusInternal(float bonusPercentage) {
        return salary * bonusPercentage;
    }

    private double computeTotalSalary(float bonusPercentage) {
        return salary + computerBonusInternal(bonusPercentage);
    }

    @Override
    public String toString() {
        return String.format("Employee ID:%d, Name:%s, Title:%s, Salary:%.2f", employeeId, name, title, salary);
    }
}
```
* -->
```java
public class AppaMain{

  public static void main(String[] args)
          throws
          ClassNotFoundException,
          NoSuchFieldException

  {

    //Constructing the Class metadata info holder.
    Class<?> employeeClass = Employee.class;

    Field[] fieldObjectsContainer = employeeClass.getDeclaredFields();
    System.out.println(fieldObjectsContainer); //[Ljava.lang.reflect.Field;@12a3a380

    for(Field field : fieldObjectsContainer){
      System.out.println("---");
      System.out.println(field);
      System.out.println("Name: "+field.getName());
      System.out.println("Field: "+field.getType());
      System.out.println("Generic Type: "+field.getGenericType());
      System.out.println("---");
    }

        /*
        ---
        public static final java.lang.String practice.Employee.organization
        Name: organization
        Field: class java.lang.String
        Generic Type: class java.lang.String
        ---
        ---
        private static final transient java.util.Random practice.Employee.employeeIdGenerator
        Name: employeeIdGenerator
        Field: class java.util.Random
        Generic Type: class java.util.Random
        ---
        ---
        private int practice.Employee.employeeId
        Name: employeeId
        Field: int
        Generic Type: int
        ---
        ---
        public java.lang.String practice.Employee.name
        Name: name
        Field: class java.lang.String
        Generic Type: class java.lang.String
        ---
        ---
        public java.lang.String practice.Employee.title
        Name: title
        Field: class java.lang.String
        Generic Type: class java.lang.String
        ---
        ---
        private double practice.Employee.salary
        Name: salary
        Field: double
        Generic Type: double
        ---
        ---
        private practice.Department practice.Employee.department
        Name: department
        Field: class practice.Department
        Generic Type: class practice.Department
        ---
        ---
        public volatile java.util.List practice.Employee.committees
        Name: committees
        Field: interface java.util.List
        Generic Type: java.util.List<java.lang.String>
        ---
         */


    //Accessing specific field(public)
    Field nameField = employeeClass.getField("name");
    System.out.println(nameField); //public java.lang.String practice.Employee.name

    //Accessing specific field(private)
    Field salaryField = employeeClass.getDeclaredField("salary");
    System.out.println(salaryField); //private double practice.Employee.salary
  }
}
```
**(C) Getting and Setting Field Values**
**Refresher on Modifiers:**
```java
public class AppaMain {
  public static void main(String[] args)
          throws
          ClassNotFoundException,
          NoSuchFieldException {

    //Constructing the Class metadata info holder.                                                       
    Class<?> employeeClass = Employee.class;

    Field[] fieldObjectsContainer = employeeClass.getDeclaredFields();
    System.out.println(fieldObjectsContainer); //[Ljava.lang.reflect.Field;@12a3a380                     

    for (Field field : fieldObjectsContainer) {
      int modifier = field.getModifiers();
      System.out.println("---");
      System.out.println(field);
      System.out.println(Integer.toBinaryString(modifier));
      System.out.println(Modifier.toString(modifier));
      System.out.println("---");
    }                                                                                                    
                                                                                                         
    /*                                                                                                   
    ---                                                                                                  
    public static final java.lang.String practice.Employee.organization                                  
    11001                                                                                                
    public static final                                                                                  
    ---                                                                                                  
    ---                                                                                                  
    private static final transient java.util.Random practice.Employee.employeeIdGenerator                
    10011010                                                                                             
    private static final transient                                                                       
    ---                                                                                                  
    ---                                                                                                  
    private int practice.Employee.employeeId                                                             
    10                                                                                                   
    private                                                                                              
    ---                                                                                                  
    ---                                                                                                  
    public java.lang.String practice.Employee.name                                                       
    1                                                                                                    
    public                                                                                               
    ---                                                                                                  
    ---                                                                                                  
    public java.lang.String practice.Employee.title                                                      
    1                                                                                                    
    public                                                                                               
    ---                                                                                                  
    ---                                                                                                  
    private double practice.Employee.salary                                                              
    10                                                                                                   
    private                                                                                              
    ---                                                                                                  
    ---                                                                                                  
    private practice.Department practice.Employee.department                                             
    10                                                                                                   
    private                                                                                              
    ---                                                                                                  
    ---                                                                                                  
    public volatile java.util.List practice.Employee.committees                                          
    1000001                                                                                              
    public volatile                                                                                      
    ---                                                                                                  
     */

  }
}
```
* Field objects have methods get and set that can invoked on them. 
```java
public class Main {
  public static void main(String[] args)
          throws
          ClassNotFoundException, NoSuchFieldException,
          NoSuchMethodException, IllegalAccessException,
          InstantiationException, InvocationTargetException {

    //Constructing the Class metadata info holder.
    Class<?> employeeClass = Employee.class;

    //Using the reflect API to create an object of the Employee class with no-argument constructor.
    Employee employee1 = (Employee) employeeClass.getConstructor().newInstance();

    //employee1 before setting any field.
    System.out.println(employee1); 
    //Employee ID:792437512, Name:Unknown, Title:Unknown, Salary:0.00, Dept:null, Committee List:null, Contract:FULLTIME

    //Using the field API to 'get' and 'set' the fields of the object of the Employee class
    Field nameField = employeeClass.getField("name");
    //set method on a field object
    nameField.set(employee1, "Elon Musk");

    Field titleField = employeeClass.getField("title");
    titleField.set(employee1, "Janitor");

    Field salaryField = employeeClass.getDeclaredField("salary"); //'salary' is a private field
    salaryField.setAccessible(true); //'salary' is a private field
    salaryField.set(employee1, 500_000);

    //Getting and getting an abstract class
    //Using the field API to 'get' and 'set' the fields of the object of the Employee class
    Field departmentField = employeeClass.getDeclaredField("department");
    //Using a local class to extend the Department abstract class and implements its unimplemented method.
    class Engineering extends Department{
      Engineering(){
        super("Engineering");
      }
    }
    departmentField.setAccessible(true); //department field is 'private'
    departmentField.set(employee1, new Engineering());

    List<String> committeesList = new ArrayList<>();
    Collections.addAll(committeesList, "Billionaires Club", "Tweeter Freak");

    Field committeeField = employeeClass.getDeclaredField("committees");//List type
    committeeField.setAccessible(true);
    committeeField.set(employee1, committeesList);

    Field typeField = employeeClass.getDeclaredField("employeeType"); //enum type
    typeField.setAccessible(true);
    typeField.set(employee1, Employee.contractType.CONTRACT);

    System.out.println(employee1);
    //Employee ID:792437512, Name:Elon Musk, Title:Janitor,
    // Salary:500000.00, Dept:Department{name='Engineering'},
    // Committee List:[Billionaires Club, Tweeter Freak], Contract:CONTRACT
  }
}
```
**(D) Accessing Public, Private, and Protected Methods**
The current section deals with Fields in term of member functions/methods. A method can also be abstract.\
As previous sections; getMethods returns only public methods while getDeclaredMethods returns ALL.\
The last three methods have the word **"native"**. Native indicates that a method is implemented in a language other than\
java such as C, C++ e.t.c.
Historically, this methods are known as JNI(Java Native Interface).
```java
public  class Main{
  public static void main(String[] args)
          throws
          ClassNotFoundException, NoSuchFieldException,
          NoSuchMethodException, IllegalAccessException,
          InstantiationException, InvocationTargetException {

    //Constructing the Class metadata info holder.
    Class<?> employeeClass = Employee.class;

    //Using the reflect API to create an object of the Employee class with no-argument constructor.
    Employee employee1 = (Employee) employeeClass.getConstructor().newInstance();

    //employee1 before setting any field.
    System.out.println(employee1);
    //Employee ID:792437512, Name:Unknown, Title:Unknown, Salary:0.00, Dept:null, Committee List:null, Contract:FULLTIME

    Method[] methodsObjectContainer = employeeClass.getMethods();
    System.out.println(methodsObjectContainer); //[Ljava.lang.reflect.Method;@29453f44

    for(Method method : methodsObjectContainer){
      System.out.println(method);
    }

        /*
public java.lang.String practice.Employee.toString()
public java.lang.String practice.Employee.getName()
public void practice.Employee.setName(java.lang.String)
public void practice.Employee.setSalary(double)
public java.lang.String practice.Employee.getTitle()
public int practice.Employee.getEmployeeId()
public practice.Department practice.Employee.getDepartment()
public java.util.List practice.Employee.getCommittees()
public practice.Employee$contractType practice.Employee.getEmployeeType()
public double practice.Employee.getSalary()
public void practice.Employee.setEmployeeId(int)
public void practice.Employee.setTitle(java.lang.String)
public void practice.Employee.setCommittees(java.util.List)
public void practice.Employee.setDepartment(practice.Department)
public void practice.Employee.setEmployeeType(practice.Employee$contractType)
public final void java.lang.Object.wait() throws java.lang.InterruptedException
public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
public boolean java.lang.Object.equals(java.lang.Object)
public native int java.lang.Object.hashCode()
public final native java.lang.Class java.lang.Object.getClass()
public final native void java.lang.Object.notify()
public final native void java.lang.Object.notifyAll()
         */

  }    
}
```
* Example of the methods of the Method object.
```java
public class Main {
  public static void main(String[] args)
          throws
          ClassNotFoundException, NoSuchFieldException,
          NoSuchMethodException, IllegalAccessException,
          InstantiationException, InvocationTargetException {

    //Constructing the Class metadata info holder.
    Class<?> employeeClass = Employee.class;

    //Using the reflect API to create an object of the Employee class with no-argument constructor.
    Employee employee1 = (Employee) employeeClass.getConstructor().newInstance();

    //employee1 before setting any field.
    System.out.println(employee1);
    //Employee ID:792437512, Name:Unknown, Title:Unknown, Salary:0.00, Dept:null, Committee List:null, Contract:FULLTIME

    Method[] methodsObjectContainer = employeeClass.getMethods();
    System.out.println(methodsObjectContainer); //[Ljava.lang.reflect.Method;@29453f44

    for (Method method : methodsObjectContainer) {
      System.out.println("---");
      System.out.println(method);
      System.out.println("Parameter count: " + method.getParameterCount());
      System.out.println("Parameter Types: " + Arrays.toString(method.getParameterTypes()));
      System.out.println("Return Types: " + method.getReturnType());
      System.out.println("---");
    }

        /*
        ---
public void practice.Employee.setDepartment(practice.Department)
Parameter count: 1
Parameter Types: [class practice.Department]
Return Types: void
---
---
public int practice.Employee.getEmployeeId()
Parameter count: 0
Parameter Types: []
Return Types: int
---
---
public void practice.Employee.setCommittees(java.util.List)
Parameter count: 1
Parameter Types: [interface java.util.List]
Return Types: void
---
---
public double practice.Employee.getSalary()
Parameter count: 0
Parameter Types: []
Return Types: double
---
---
public void practice.Employee.setTitle(java.lang.String)
Parameter count: 1
Parameter Types: [class java.lang.String]
Return Types: void
---
---
public java.lang.String practice.Employee.getTitle()
Parameter count: 0
Parameter Types: []
Return Types: class java.lang.String
---
---
public void practice.Employee.setEmployeeId(int)
Parameter count: 1
Parameter Types: [int]
Return Types: void
---
---
public void practice.Employee.setEmployeeType(practice.Employee$contractType)
Parameter count: 1
Parameter Types: [class practice.Employee$contractType]
Return Types: void
---
---
public void practice.Employee.setSalary(double)
Parameter count: 1
Parameter Types: [double]
Return Types: void
---
---
public practice.Department practice.Employee.getDepartment()
Parameter count: 0
Parameter Types: []
Return Types: class practice.Department
---
---
public java.util.List practice.Employee.getCommittees()
Parameter count: 0
Parameter Types: []
Return Types: interface java.util.List
---
---
public practice.Employee$contractType practice.Employee.getEmployeeType()
Parameter count: 0
Parameter Types: []
Return Types: class practice.Employee$contractType
---
---
public java.lang.String practice.Employee.toString()
Parameter count: 0
Parameter Types: []
Return Types: class java.lang.String
---
---
public java.lang.String practice.Employee.getName()
Parameter count: 0
Parameter Types: []
Return Types: class java.lang.String
---
---
public void practice.Employee.setName(java.lang.String)
Parameter count: 1
Parameter Types: [class java.lang.String]
Return Types: void
---
---
public final void java.lang.Object.wait() throws java.lang.InterruptedException
Parameter count: 0
Parameter Types: []
Return Types: void
---
---
public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
Parameter count: 2
Parameter Types: [long, int]
Return Types: void
---
---
public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
Parameter count: 1
Parameter Types: [long]
Return Types: void
---
---
public boolean java.lang.Object.equals(java.lang.Object)
Parameter count: 1
Parameter Types: [class java.lang.Object]
Return Types: boolean
---
---
public native int java.lang.Object.hashCode()
Parameter count: 0
Parameter Types: []
Return Types: int
---
---
public final native java.lang.Class java.lang.Object.getClass()
Parameter count: 0
Parameter Types: []
Return Types: class java.lang.Class
---
---
public final native void java.lang.Object.notify()
Parameter count: 0
Parameter Types: []
Return Types: void
---
---
public final native void java.lang.Object.notifyAll()
Parameter count: 0
Parameter Types: []
Return Types: void
---
        */
  }
}
```
Another important method of the Method field are:
1. `getExceptionTypes()`.
2. `getAnnotation()`
3. `getAnnotation(Deprecated.class)`
4. `getDeclaredAnnotations()`
The three possible values of annotation based on annotation retention policy are:**source**, **class** and, **runtime**. Only anno\
tations with retention policy set to runtime is accessible/retained for reflection. Override and SuppressWarning are not
accessible to the reflects API. The Deprecated annotation has runtime retention policy, hence, available for reflection.
```java
public class Main {
  public static void checkAnnotations(String method, Class annotation)
          throws
          ClassNotFoundException, NoSuchFieldException,
          NoSuchMethodException, IllegalAccessException,
          InstantiationException, InvocationTargetException {
    Class<?> employeeClass = Employee.class;
    //Using the reflect API to create an object of the Employee class with no-argument constructor.
    Employee employee1 = (Employee) employeeClass.getConstructor().newInstance();

    Method methodsObject = employeeClass.getMethod(method);
    System.out.println(methodsObject.getAnnotation(annotation));
  }

  public static void main(String[] args)
          throws
          ClassNotFoundException, NoSuchFieldException,
          NoSuchMethodException, IllegalAccessException,
          InstantiationException, InvocationTargetException {
    //Override and SuppressWarning are not accessible to the reflects API....Hence, null
    checkAnnotations("toString", Override.class);//null
    checkAnnotations("printEmployeeDetail", Deprecated.class);//@java.lang.Deprecated()
  }
}
```
* Accessing class and methods of an external library
```java
public class Main{
  public static void main(String[] args)
          throws
          ClassNotFoundException, NoSuchFieldException,
          NoSuchMethodException, IllegalAccessException,
          InstantiationException, InvocationTargetException {
    //Using getMethod with methods of other libraries

    Method [] strictMathMethods = StrictMath.class.getDeclaredMethods();
    System.out.println(strictMathMethods.length); //68 There are 68 methods;
    //Printout first 10 methods

    for(int i=0; i<10; i++ ){
      System.out.println(strictMathMethods[i]);
    }
        /*
        public static double java.lang.StrictMath.abs(double)
        public static float java.lang.StrictMath.abs(float)
        public static int java.lang.StrictMath.abs(int)
        public static long java.lang.StrictMath.abs(long)
        public static native double java.lang.StrictMath.sin(double)
        public static native double java.lang.StrictMath.cos(double)
        public static native double java.lang.StrictMath.tan(double)
        public static native double java.lang.StrictMath.atan2(double,double)
        public static native double java.lang.StrictMath.sqrt(double)
        public static native double java.lang.StrictMath.log(double)
        */

    Method strictMathObject = StrictMath.class.getMethod("sqrt", double.class);
  }
}
```
* Invoking reflection on a Method object.
```java
public class Main{
  public static boolean isGetter(Method method){
    if(!method.getName().startsWith("get")){
      return false;
    }
    if(!((method.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC)){
      //.getModifiers() returns bytes values which corresponds to the access modifiers.
      return false;
    }
    if(method.getReturnType().equals(void.class)){
      return false;
    }
    if(method.getParameterCount() !=0){
      return false;
    }
    return true;
  }


  public static boolean isSetter(Method method){
    if(!method.getName().startsWith("set")){
      return false;
    }
    if(!((method.getModifiers() & Modifier.PUBLIC) == Modifier.PUBLIC)){
      //.getModifiers() returns bytes values which corresponds to the access modifiers.
      return false;
    }
    if(method.getReturnType().equals(void.class)){
      return false;
    }
    if(method.getParameterCount() != 1){
      return false;
    }
    return true;
  }
  public static void main(String[] args)
          throws
          ClassNotFoundException, NoSuchFieldException,
          NoSuchMethodException, IllegalAccessException,
          InstantiationException, InvocationTargetException {

    Class<?> employeeClass = Employee.class;
    Method [] methods= employeeClass.getDeclaredMethods();

    for(Method method : methods){
      if(isGetter(method)){
        System.out.println(method.getName() + "is a getter");
      } else if (isSetter(method)) {
        System.out.println(method.getName() + "is a setter" );
      }
    }
        
        /*
        getNameis a getter
        getSalaryis a getter
        getEmployeeTypeis a getter
        getDepartmentis a getter
        getEmployeeIdis a getter
        getTitleis a getter
        getCommitteesis a getter
         */
  }
}
```
## 14: Java: Working with Annotations, Generics, & Arrays Using Reflection
**(A)**
The java annotation API import `java.lang.annotation.Annotation` .
Not all annotations can picked up by reflection. According to the _retention policy_, only annotations with retention\
policy if runtime can be accessed via reflection. Hence, `@Override` and `@SuppressWarning` are not available at runtime,
while `@Deprecated` is available at runtime.
```java
public class Main {
  public static void main(String[] args)
          throws
          ClassNotFoundException, NoSuchFieldException,
          NoSuchMethodException, IllegalAccessException,
          InstantiationException, InvocationTargetException {


    Class<?> employeeClass = Employee.class;

    //Annotations on Fields
    Field[] fieldsObjectContainer = employeeClass.getDeclaredFields();

    for (Field field : fieldsObjectContainer) {
      Annotation[] annotationsObjectContainer = field.getAnnotations();
      if (annotationsObjectContainer.length > 0) {
        System.out.println("-----------------");
        System.out.println(field.getName());
        System.out.println("Annotations: " + Arrays.toString(annotationsObjectContainer));
        System.out.println("-----------------");
      }
    }
        /*
        -----------------
        department
        Annotations: [@java.lang.Deprecated()]
        -----------------
         */


    //Annotations on Fields
    Method[] fieldsObjectContainer2 = employeeClass.getDeclaredMethods();

    for (Method field : fieldsObjectContainer2) {
      Annotation[] annotationsObjectContainer = field.getAnnotations();
      if (annotationsObjectContainer.length > 0) {
        System.out.println("-----------------");
        System.out.println(field.getName());
        System.out.println("Annotations: " + Arrays.toString(annotationsObjectContainer));
        System.out.println("-----------------");
      }
    }
        /*
        -----------------
        saySomething
        Annotations: [@java.lang.Deprecated()]
        -----------------
        -----------------
        printEmployeeDetail
        Annotations: [@java.lang.Deprecated()]
        -----------------
        -----------------
        getDepartment
        Annotations: [@java.lang.Deprecated()]
        -----------------
        -----------------
        setDepartment
        Annotations: [@java.lang.Deprecated()]
        -----------------
         */

  }
}
```
**(B) Configuring Custom Annotations for Reflective Access**
Every annotation has to have a **retention** and **target** policy. If the retention policy is not specified, the\
default is used. Hence, a custom annotation with no retention policy specified will not be picked up by reflective access.\
The default target policy allows the annotation to be applied to any object except type parameter.
1. SOURCE: annotation will be discarded by the compiler.
2. CLASS: annotation will be present the in the .class file. The code will be present in byte codes compiled by the\
compiler but will be lost when the JVM converts the native machine code at runtime. Hence, it wont be available for\
reflective access.
3. RUNTIME: annotations are available in the .class file and also will be available at runtime.

```java
package simplepracticeclasses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
}
```
**(C) Scenario: Accessing Annotations to Perform Checks**
Declared annotations can be used to impose constraints on a field in a class. Hence, a combination of annotations and\
reflections can be used to insert checks/controls. The annotation specifies the constraints while reflection enforces it.

```java
package simplepracticeclasses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Required {
}
```
* -->

```java
package simplepracticeclasses;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface InRange {

    double minValue();

    double maxValue();

}
```
* -->
```java
public class Main {
  private static void validateEmployeeObjectField(Youth youth) throws IllegalAccessException {

    //initial checker
    boolean valid = true;
    //Obtain the fields as array
    Field[] fieldObjectContainer = youth.getClass().getDeclaredFields();
    for (Field field : fieldObjectContainer) {
      Annotation[] annotationObjectContainer = field.getAnnotations();

      for (Annotation annotation : annotationObjectContainer) {
        if (annotation instanceof Repository) {
          //setting the field to accessible
          field.setAccessible(true);
          //field.get() with the youth object and nullity check of the field that is instanceof Required
          if (field.get(youth) == null) {
            valid = false;
            System.out.println("**Field is null: " + field.getName());
          }
        }

        if (annotation instanceof InRange) {
          //InRange has a state(max and min values) which we need to access. Downcasting enables the access
          //The downcase is safe since the if statement above checks instanceof
          InRange inRange = (InRange) annotation;
          int value = field.getInt(youth);
          if (value < inRange.minValue() || value > inRange.maxValue()) {
            valid = false;
            System.out.println(String.format(
                    "**Field is not in range: %s(%.0f, %.0f)**",
                    field.getName(), inRange.minValue(), inRange.maxValue()
            ));
          }

        }
      }
    }
  }


  public static void main(String[] args) throws IllegalAccessException {

    Youth james = new Youth("James Brown", 900, Youth.gender.MALE);
    System.out.println(james); //Name: James Brown, Age: 900, Gender: MALE.

    Class<?> youthClass = Youth.class;
    Field[] fields = youthClass.getDeclaredFields();

    for (Field field : fields) {
      Annotation[] annotations = field.getAnnotations();
      for (Annotation annotation : annotations)
        System.out.println(annotation);
    }
        
        /*
        @practice.Required()
        @practice.InRange(minValue=14.0, maxValue=30.0)
         */

    validateEmployeeObjectField(james); //**Field is not in range: age(14, 30)**
  }
}
```
**(D) Using Reflection with Generic Classes and Methods**
_Limitations of reflections in terms of type parameters and parametrized classes:_
* Generic class loses some information that are rather available to non-generic classes. 
* The information on type parameter of a generic class is not accessible to the reflects API. 
* Hence, concrete and exact data type of the type parameter can't be obtained using reflections. 
* The most informative method is `getGenericSuperClass()`. It returns class information with type parameter for a child\
class of a generic class (i.e. class that extends a generic class)


## 15: Java: Leveraging Reflection to Build Dynamic Proxies & Unit Tests
**Refresher:** 
**Reflection:** is a programming technique that access and modifies class and object information at runtime. 
It is based on the principle that every class, interface, method or field, is itself represented by an object.
**Dynamic proxy:** class is a class that implements a list of interfaces specified at runtime in such a way that a method\
invocation through any of the interfaces on an instance of that dynamic proxy class will be encoded and dispatched to\
another object via a uniform interface.
Useful Further knowledge from --> www.Baeldung.com   
**Proxies** are fronts or wrappers that pass function invocation through their own facilities (usually onto real methods)\
– potentially adding some functionality.
**Dynamic proxy:** Dynamic proxies allow one single class with one single method to service multiple method calls to\
arbitrary classes with an arbitrary number of methods. A dynamic proxy can be thought of as a kind of Facade, but one\
that can pretend to be an implementation of any interface. Under the cover, it routes all method invocations to a \
single handler – the `invoke()` method.
While it's not a tool meant for everyday programming tasks, dynamic proxies can be quite useful for framework writers.\
It may also be used in those cases where concrete class implementations won't be known until run-time.\
**(A) Using Reflection API to implement Dynamic Proxies**
* A dynamic proxy can be considered as object that implements any chosen/specified interface.
* Invocation handler is an **interface** provided by the `java.lang.reflect.InvocationHandler` class of the reflect API.
* The invocation handler interface has a single method `invoke()` that must be implemented/overridden.
  - The 1st argument is the proxy class object: Object proxy. 
  - The 2nd argument is proxy the method/s of the proxy interface/cass object being proxied: Method method
  - The 3rd is an array of arguments that will be passed into the proxy method(2nd argument above): Object [] args.
  - The return value of the method is _Object_. Hence, any arbitrary object can be returned. The return of Object makes\
   debugging quite difficult. and comes with the limitation of working with raw Object.
* The class `java.lang.reflect.Proxy` provides the necessary mechanism for the invocation handler to accept method \
invocations meant for a custom interface. It is the super class for all dynamic proxies.

```java
package simplepracticeclasses;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class ListInvocationHandler implements InvocationHandler {

    private final List<String> list; //member variable

    public ListInvocationHandler(List<String> list) {
        this.list = list;
    }

    @Override
    public Object invoke(
            Object proxy,
            Method method,
            Object[] args
    ) throws Throwable {
        System.out.println("---------");
        System.out.println("Method invoked: " + method);
        System.out.println("Arguments: " + Arrays.toString(args));
        System.out.println("---------");

        //we return the 'method' passed to 'invoke()' and use it to perform some task on the 'list'.
        return method.invoke(list, args);
    }

}
```

```java
public class AppaMain{
  public static void main(String[] args)  {

    List<String> progLangList = new ArrayList<>();

    List proxyList = (List) Proxy.newProxyInstance(
            AppaMain.class.getClassLoader(),
            //The interface that we will be working with. Its an array that can hold several interfaces but only
            //one interface (List) is on current interest.
            new Class[] {List.class},
            //the invocation handler for the interface takes 'progLangList' (defined above) as a parameter.
            new ListInvocationHandler(progLangList)
    );

    //We invoke the 'add' of the 'List' interface. However, this method goes through the defined invocation handler.
    //In the invocation handler, the print statements is come before the method is used to work on the variable.
    //Hence, the everytime 'add' method is invoked, the print statement is executed and displayed.
    //In essence, we are INDIRECTLY working on the 'progLangList' INDIRECTLY using methods of the 'List' interface by
    //DIRECTLY invoking the method inside the invocation handler 'ListInvocationHandler'.
    //The task of the Proxy 'proxyList' is to use the current class("AppaMain') as launchpad to connect the
    // interface(List), the argument ('progLangList'), and the invocation handler('ListInvocationHandler')
    proxyList.add("Java");
        /*
        ---------
        Method invoked: public abstract boolean java.util.List.add(java.lang.Object)
        Arguments: [Java]
        ---------.
         */
    proxyList.add("Python");
        /*
        ---------
        Method invoked: public abstract boolean java.util.List.add(java.lang.Object)
        Arguments: [Python]
        ---------
         */
    proxyList.add("C#");
        /*
        ---------
        Method invoked: public abstract boolean java.util.List.add(java.lang.Object)
        Arguments: [C#]
        ---------
         */
    proxyList.add("C++");
        /*
        ---------
        Method invoked: public abstract boolean java.util.List.add(java.lang.Object)
        Arguments: [C++]
        ---------
         */
    
    //printout of the proxyList
    System.out.println(proxyList);
        /*
        ===========================
        ---------
        Method invoked: public java.lang.String java.lang.Object.toString()
        Arguments: null
        ---------
        [Java, Python, C#, C++]
         */
    
    System.out.println(progLangList); //[Java, Python, C#, C++]
    
    proxyList.remove("C#");
        /*
        ---------
        Method invoked: public abstract boolean java.util.List.remove(java.lang.Object)
        Arguments: [C#]
        ---------
         */
    System.out.println(progLangList); //[Java, Python, C++]
  }
}
```
**(B) Creating Annotations for a Unit Testing FrameWork**\
??----???

## 16: Java Archive (JAR): Building Java Archives

Deals with packing the codes into a form that can be deployed and distributed.
Java archives(JAR) files is built using the jar utility which can be run from the command line.
JAR files can either be directly executable or can be used as dependency in order project(when not directly executable).

**(A) Creating the Contents of a JAR file and Building and Running an Executable JAR file**\
JAR files are effectively a package which include some java class files as well as associated resources such as texts,\
images,e.t.c.\
Java Hotspot refers to the JVM which is the requirement for running the java byte codes.\
The **manifest** is an important component of any JAR file. It is a metadata file which is part of the JAR which includes\
several key-value pairs that conveys significant information about the archive.\
There is a required one line (minimum) spacing between lines of entries in the manifest file.\
Example of manifest file 
```
Main-Class: EntryPoint
```
The class file the mandatory file to required or the essence of creating a JAR file. Hence, cd to the location of the\
specific class files of interest.\
**1.** The scheme of the jar command to create the jar file.
```
jar {ctxui}[vfmn0PMe] [jar-file] [manifest-file] [entry-point] [-C dir] files
Options:                                          
-c  create new archive                        
-t  list table of contents for archive        
-x  extract named (or all) files from archive
-u  update existing archive                   
-v  generate verbose output on standard output
-f  specify archive file name
-m  include manifest information from specified manifest file
-n  perform Pack200 normalization after creating a new archive
-e  specify application entry point for stand-alone application bundled into an executable jar file
-0  store only; use no ZIP compression
-P  preserve leading '/' (absolute path) and ".." (parent directory) components from file names
-M  do not create a manifest file for the entries
-i  generate index information for the specified jar files
-C  change to the specified directory and include the following file
If any file is a directory then it is processed recursively.
The manifest file name, the archive file name and the entry point name are
specified in the same order as the 'm', 'f' and 'e' flags.

Example 1: to archive two class files into an archive called classes.jar:
jar cvf classes.jar Foo.class Bar.class
Example 2: use an existing manifest file 'mymanifest' and archive all the
files in the foo/ directory into 'classes.jar':
jar cvfm classes.jar mymanifest -C foo/ .
```
- Create jar with manifest
`jar cvfm MyFirstJar.jar MyFirstManifest.txt MyFirstClass.class`

- Create jar without manifest
`jar cvfe MyFirstJar.jar MyMainClass.class Otherclasses.class`
c:create, v:verbose mode, f:create a jar file, m:use specified manifest file e:explicit specification of main class.

**2.** The scheme of the command to run the created jar file. 
`java -jar MyFirstJar.jar` 

**3.** View the content of jar file.\
`jar tvf MyFirstJar.jar` or `tar tvf MyFirstJar.jar`. To extract the jar file, use `tar xvf MyFirstJar.jar`
----
```
0 Mon Aug 29 13:53:10 CEST 2022 META-INF/
87 Mon Aug 29 13:53:10 CEST 2022 META-INF/MANIFEST.MF
925 Mon Aug 29 13:51:14 CEST 2022 Main.class
```
**Example Manifest File**
- MyFirstManifest.txt
```
Main-Class: EntryP
Specification-Version: 9.0
Specification-Vendor: Shola
Implementation-Title: TopApp
Implementation-Version: buildx
Implementation-Vendor: Shola
```
- Content of the extracted MANIFEST.MF
```
Manifest-Version: 1.0
Implementation-Title: TopApp
Implementation-Version: buildx
Specification-Vendor: Shola
Specification-Version: 9.0
Created-By: 1.8.0_311 (Oracle Corporation)
Main-Class: EntryP
Implementation-Vendor: Shola
```

**(B) Building JAR Files with External Dependencies**
- Dependency to be used(download): A logging tool. https://logging.apache.org/log4j/2.x/download.html
  1. Setting up the dependency within the project file/structure
      - Download the zip files of the needed **dependencies** and place them under a chosen path.\
        \ProjectHome\jars\lib\log4j-api-2.18.0.jar\
        \ProjectHome\jars\lib\log4j-core-2.18.0.jar\
      - Use **manifest** to reference the dependencies' path.\ 
         manifest path:
        \ProjectHome\jars\manifest.txt
         manifest content: 
         ```
         Main-Class: Main
         Specification-Version: 9.0
         Specification-Vendor: Shola
         Implementation-Title: TopApp
         Implementation-Version: buildx
         Implementation-Vendor: Shola
         Class-Path: **lib\log4j-api-2.18.0.jar lib\log4j-core-2.18.0.jar**
         ```
      - Use IDE(e.g. Intellij) to add the **dependencies to path !!**. File --> Project Structure --> Libraries --> +
  2. Create configuration path and file for the dependencies.
     - The config files specifies the layout, the location of log files(if any), level of log, console log config, e.t.c
     - config file path: C:\Users\absuleim\Desktop\ProjectHome\src\main\resources\log4j2.xml
     - Config file content:
        ```xml
       <?xml version="1.0" encoding="UTF-8"?>
       
       <Configuration status="INFO">
       
          <Appenders>
            <Console name="ConsoleAppender" target="SYSTEM_OUT">
                <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n" />
           </Console>
           <File name="FileAppender" fileName="application-${date:yyyyMMdd}.log" immediateFlush="false" append="true">
                 <PatternLayout pattern="%d{yyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
           </File>
         </Appenders>
       
         <Loggers>
           <Root level="debug">
            <AppenderRef ref="ConsoleAppender" />
            <AppenderRef ref="FileAppender"/>
          </Root>
        </Loggers>
       
       </Configuration>
       ```
  3. The simple class that makes use of the dependencies:
  ```java
  import org.apache.logging.log4j.LogManager;
  import org.apache.logging.log4j.Logger;
  public class Main {
      
    private static Logger logger; //logger instance  
    private String mode;

    public Main(String mode) {
        this.mode = mode;
        //wiring the logger to the system/class
        System.setProperty("log4j.configurationFile", 
                           "C:\\Users\\absuleim\\Desktop\\JarDependecy\\src\\main\\resources\\log4j2.xml");
        //the constructor has a logger instance for every object of this class.
        this.logger = LogManager.getLogger(Main.class);
    }

    public void printMode() throws NullPointerException{
        logger.info("Application is currently running in mode: " + this.mode + " mode.");
        logger.error("Fake Error !");

    }

    public static void main(String[] args) {
        Main ep = new Main("DEV");
        ep.printMode();
        logger.warn(ep.getClass());
    }
    }
  ```
  4. Compile the class and build the jar file. Run the jar file. 
     This jar build command assumes the .class file has been copied to the same path as the manifest.txt.\
     Build jar file: `jar cvfm first.jar manifest.txt Main.class`\
     Run jar file:  `java -jar first.jar`
     ```
     10:26:23.070 [main] INFO  Main - Application is currently running in mode: DEV mode.
     10:26:23.070 [main] ERROR Main - Fake Error !
     10:26:23.086 [main] WARN  Main - class Main
     ```

**(C) Constructing JAR files with Multiple Main Classes**
- The two non-executable class
```java
public class Person {
    private String name;

    enum gender{
        MALE, FAMALE, NONBINAR,
    }

    private gender genderType;

    private Integer age;

    public Person(String name){
        this.name = name;
    }
    public Person(String name, gender genderType, Integer age) {
        this.name = name;
        this.genderType = genderType;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public gender getGenderType() {
        return genderType;
    }

    public void setGenderType(gender genderType) {
        this.genderType = genderType;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String toString(){
        return String.format("Name: %s, ID: %s, Age: %d", name, genderType, age);
    }
}

```
```java
public class Employee extends Person{
    private Long id;

    private  String role;

    public Employee(String name,  Long id, String role){
        super(name);
        this.id = id;
        this.role = role;
    }
    public Employee(String name, gender genderType, Integer age, Long id, String role){
        super(name, genderType, age);
        this.id = id;
        this.role = role;
    }
    
}
```
- The two executable classes
```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InitPerson {

    public static void  main(String [] args){

        System.setProperty("log4j.configurationFile",
                "C:\\Users\\absuleim\\Desktop\\JarDependecy\\src\\main\\resources\\log4j2.xml");
        //the constructor has a logger instance for every object of this class.
        Logger logger = LogManager.getLogger(Main.class);

        Employee alice = new Employee("Alice", Person.gender.FAMALE, 24, 17363L, "DevOps");
        logger.info("Initialized employee: "+alice.getName());

        Employee jarda = new Employee("Jarda", Person.gender.MALE, 24, 1234L, "Senior Engineer");
        logger.info("Initialized employee: "+jarda.getName());
    }
}
```
```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
public class Main {

    private static Logger logger; //logger instance
    private String mode;

    public Main(String mode) {
        this.mode = mode;
        //wiring the logger to the system/class
        System.setProperty("log4j.configurationFile", 
                "C:\\Users\\absuleim\\Desktop\\JarDependecy\\src\\main\\resources\\log4j2.xml");
        //the constructor has a logger instance for every object of this class.
        this.logger = LogManager.getLogger(Main.class);
    }

    public void printMode() throws NullPointerException{
        logger.info("Application is currently running in mode: " + this.mode + " mode.");
        logger.error("Fake Error !");

    }

    public static void main(String[] args) {
        Main ep = new Main("DEV");
        ep.printMode();
        logger.warn(ep.getClass());
        System.out.println("--------------------------------------------");
        Employee admin = new Employee("Shola", 707L, "admin");
        System.out.println("New admin user created from Main class : "+admin.getName());
        System.out.println("--------------------------------------------");
    }
}
```
* Copy all files to the chosen path(same as previous section) and run: `jar cvfm first.jar manifest.txt *.class`
```
\JarDependecy\jars>jar cvfm first.jar manifest.txt *.class
added manifest
adding: Employee.class(in = 879) (out= 445)(deflated 49%)
adding: InitPerson.class(in = 1664) (out= 898)(deflated 46%)
adding: Main.class(in = 1983) (out= 1076)(deflated 45%)
adding: Person$gender.class(in = 991) (out= 561)(deflated 43%)
adding: Person.class(in = 1494) (out= 653)(deflated 56%)
```
* Runing JAR file without specifying a Main class in the command: `java -jar first.jar`
Without modification to the jar running command, only the executable class(Only one entrypoint class is used by default)\
referenced in the manifest.txt will be executed i.e. the Main class. This is shown in the result of the command below.
```
11:13:33.462 [main] INFO  Main - Application is currently running in mode: DEV mode.
11:13:33.462 [main] ERROR Main - Fake Error !
11:13:33.478 [main] WARN  Main - class Main
--------------------------------------------
New admin user created from Main class : Shola
--------------------------------------------
```
* Running JAR file with specific class (i.e. InitPerson.class) in the command: `java -cp first.jar InitPerson`
```
11:27:10.144 [main] INFO  Main - Initialized employee: Alice
11:27:10.144 [main] INFO  Main - Initialized employee: Jarda
```

**(C) Constructing JAR files with Multiple Packages**
- The updated project structure is given below(the source):
<img src="\images\JARprojectStructure.png">
- The two new classes inside two new packages are:
```java
package carspackage;

public class Cars {

    public String name;
    public String make;
    public Cars(String name, String make){
        this.name = name;
        this.make = make;
    }

    @Override
    public String toString() {
        return String.format("Name:%s, Make:%s, Price:$ %s", name, make);
    }
}
```
```java
package smallpackage;

import carspackage.Cars;

public class CarPrice extends Cars {

    public Double price;

    public CarPrice(String name, String make, Double price){
        super(name, make);
        this.price = price;
    }

    public Double getPrice(){
        return price;
    }
}
``` 


- Modification to the entry point class(Main.java)
```java
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//Import classes from the new packages
import carspackage.Cars;
import smallpackage.CarPrice;

public class Main {

    private static Logger logger; //logger instance
    private String mode;

    public Main(String mode) {
        this.mode = mode;
        //wiring the logger to the system/class
        System.setProperty("log4j.configurationFile", 
                "C:\\Users\\absuleim\\Desktop\\JarDependecy\\src\\main\\resources\\log4j2.xml");
        //the constructor has a logger instance for every object of this class.
        this.logger = LogManager.getLogger(Main.class);
    }

    public void printMode() throws NullPointerException{
        logger.info("Application is currently running in mode: " + this.mode + " mode.");
    }

    public static void main(String[] args) {

        Cars teslaz = new Cars("Model Z", "Tesla");
        CarPrice mercedes = new CarPrice("E-Class", "Mercedes", 17363D);


        Main ep = new Main("DEV");
        ep.printMode();
        logger.warn(ep.getClass());
        System.out.println("--------------------------------------------");
        logger.info("Cars instantiated:--> "+ teslaz.toString() + ";  "+ mercedes.toString());
        System.out.println("--------------------------------------------");
    }
}
```
- Updated project structure after building the classes(target/out):
<img src="\images\JARprojectStructur2.png">
- The goal is two create JAR files containing both packages. `jar cvfm first.jar manifest.txt ../target/*/*class`


## 17: Java Archive (JAR): Packaging Java Apps Using Maven
**(A)Building a Simple JAR file with Maven**
* Maven offers a lot of flexibilities in how java archives (JAR files) are created. Maven is a build tools used to manage\
the building of applications and their dependencies. An **Uber JAR** is a single packaged app that contains JAR files\
along with their dependencies. To run the maven command within the command line :
1. download and copy/extract maven file to a specific location. (e.g. Program Files)
2. add the maven home direct(location above) to path directory. MAVEN_HOME 
3. check version to cross-check installation `mvn --version`
* An important feature of Maven is that any dependency included in a maven project will be downloaded to a\
local repository located at: `~/.m2/repository`
* Maven configuration files can be found at `/mavenpath(e.g. Program Files)/apache-maven-3.85/conf/` \
**Project Object Model (pom):**
* **ArcheTypes** are templates for project structure. e.g. `org.apache.maven.archetypes:maven-archetype-quickstart`
* The pom.xml file is the main configuration and metadata file for the project.  
```xml  
<?xml version="1.0" encoding="UTF-8"?>

<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>JARWithMaven</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging> //package type: WAR or JAR files

  //some metadata information
  <name>JARWithMaven</name>
  <description>Example of Packing JAR Files with Maven</description>
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
  </dependencies>

  //build is the section for defining various plugin or tools to be used when building the application
  //pluginManagement tag comes to play when we have hierarchy of Maven projects where pom files might inherit from other
      //pom files.
  //plugin elements defined inside the pluginManagement are used to configure the builds of child projects or child pom.
  //maven clean plugin cleans any previously generated build artifacts. e.g. previous/old jar file. This bcuz old
       //old artifacts should be removed before new ones are generated.
  //maven resources plugin is helpful when copying over project resources such as config files to build output path.
  //maven compiler plugin is used to compile java sources in a project.
  //maven jar plugin is used to create jar files.
  <build>
    <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
      <plugins>
        <!-- clean lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#clean_Lifecycle -->
        <plugin>
          <artifactId>maven-clean-plugin</artifactId>
          <version>3.1.0</version>
        </plugin>
        <!-- package type: WAR or JAR files  -->
        <plugin>
          <artifactId>maven-resources-plugin</artifactId>
          <version>3.0.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-compiler-plugin</artifactId>
          <version>3.8.0</version>
        </plugin>
        <plugin>
          <artifactId>maven-surefire-plugin</artifactId>
          <version>2.22.1</version>
        </plugin>

        //the maven jar plugin
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-jar-plugin</artifactId>
          <configuration>
            <archive>
              <manifest>
                <addClasspath>true</addClasspath>
                <mainClass>
                  org.example.App
                </mainClass>
              </manifest>
            </archive>
          </configuration>
        </plugin>


        <plugin>
          <artifactId>maven-install-plugin</artifactId>
          <version>2.5.2</version>
        </plugin>
        <plugin>
          <artifactId>maven-deploy-plugin</artifactId>
          <version>2.8.2</version>
        </plugin>
        <!-- site lifecycle, see https://maven.apache.org/ref/current/maven-core/lifecycles.html#site_Lifecycle -->
        <plugin>
          <artifactId>maven-site-plugin</artifactId>
          <version>3.7.1</version>
        </plugin>
        <plugin>
          <artifactId>maven-project-info-reports-plugin</artifactId>
          <version>3.0.0</version>
        </plugin>
      </plugins>
    </pluginManagement>
  </build>
</project>
```
Packing the classes: `mvn clean package` \
The package command compiles the classes and package them in the format specified in the pom file.\
**(B)Building a JAR file with Dependencies using Maven**
1. The log4j2 library is once again used as example dependency. The config file path: `src/main/resources/log4j2.xml`
2. The dependencies used in the class is added to the dependency section of the pom file as shown below:
```xml
<project>
  ......
  ......
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
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-api</artifactId>
      <version>2.18.0</version>
    </dependency>
    <dependency>
      <groupId>org.apache.logging.log4j</groupId>
      <artifactId>log4j-core</artifactId>
      <version>2.8.2</version>
    </dependency>


  </dependencies>
 
  <build>
    
    
  </build>
    ......
    ......

</project>
```
- The maven central repo and any other repo uses the groupId, artifactId and version to identify the correct version.
- The dependencies are by default downloaded/searched for under ~/.m2/repository. 
- The default path for external dependencies can be overriden by specifying **classpathPrefix** in the jar plugin
```
        <plugin>
          <groupId>org.apache.maven.plugins</groupId>
          <artifactId>maven-jar-plugin</artifactId>
          <configuration>
            <archive>
              <manifest>
                <addClasspath>true</addClasspath>
                <classpathPrefix>libs/<classpathPrefix>
                <mainClass>
                  org.example.App
                </mainClass>
              </manifest>
            </archive>
          </configuration>
        </plugin>

```

**(C) Building Uber JAR Using the Maven Assembly Plugin**
* The previous packaging of the JAR files assumes that the end user will have the dependencies on their system(log4j).
* However, it's possible and better to package the dependencies(dependent) JAR files along with the entire package.
* A package that includes all dependencies with the application in the JAR package is referred to as **Uber JAR** .
* A difference between Uber and thin JAR is that the Uber should normally be executable since it has everything included.
* Maven assembly plugin can be used to build a fat/Uber JAR.
  - The `<packaging></packaging>` is removed from the metadata section(top of the pom file).
  - The packaging information is specified within the maven assembly plugin element of the pom file.
  - The `maven-assembly-plugin` is specified outside the `pluginManagement` section. pluginManagement is used for plugins\
    that can be inherited. However, the maven-assembly-plugin should be specified within the build element.
  - The `maven-assembly-plugin` used its `descriptorRef` element along with its `executions` section to enable the build\
    of Uber/fat JAR. The executions provides information on when the Uber should be created(phase)
  ```xml
  <project>  
  <build>
  
  <pluginManagement>
      <plugins>
      </plugins>
  </pluginManagement>
    
  <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-assembly-plugin</artifactId>
        <version>3.3.0</version>
  
        <configuration>
          <descriptorRefs>jar-with-dependencies</descriptorRefs>
          <archive>
            <manifest>
              <mainClass>org.example.App</mainClass>
            </manifest>
          </archive>
        </configuration>
  
        <executions>
            <execution>
            <id>make-assembly</id>
            <phase>package</phase>
            <goals>
                <goal>single</goal>
            </goals>
            </execution>
        </executions>
  
      </plugin>
  </plugins>
  
  </build>
  </project>
  ```
* 