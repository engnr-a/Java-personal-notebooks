# ONE
### TOC
| #   | Title                                                                               |
|-----|-------------------------------------------------------------------------------------|
| 1   | Getting Started with Java: The Fundamentals of Java Programming                     | 
| 2   | Getting Started with Java: Writing & Running Java Programs                          | 
| 3   | Getting Started with Java: Variables & Primitive Type                               | 
| 4   | Getting Started with Java: Operators                                                | 
| 5   | Control Structures in Java: Implementing Java Control Structures                    | 
| 6   | Modeling Entities in Java: Getting Started with Classes & Objects                   | 
| 7   | Modeling Entities in Java: Defining Custom Classes & Objects                        | 
| 8   | Modeling Entities in Java: Methods, Method Overloading,&Constructors                | 
| 9   | Modeling Entities in Java: Static Members, Arguments, & Method Overriding           | 
| 10  | Mapping Relationships in Java: Modeling Is-a Relationships Using Inheritance        | 
| 11  | Mapping Relationships in Java: Constructors & Polymorphism                          | 
| 12  | Mapping Relationships in Java: Overriding Methods and Using Access Modifiers        | 
| 13  | Mapping Relationships in Java: Working with Interfaces & Class Loaders              |
| 14  | Interactive Java & JShell: Writing Java Programs with the Interactive JShell        |
| 15  | Final Exam:                                                                         |
| 16  | Laboratory:                                                                         |


## Java Variables and Primitive Types
**Variable declaration**
```java
public class main {
  public static void main (String[] args){
        //
        int number1 =20;
        int number2;
        String someName = "Shola";
        //Variable reassignment and importance of type.
        number2 = 5;
        System.out.println(number1);
        System.out.println(someName);
        System.out.print(number2 + "\n");
        //variable value reassignment
        number1 = 200;
        System.out.print(number1 + "\n");
        //The "final" ensures that the variable can only assume the current assigned value
        final int number3 =89;
        System.out.println(number3);
        // Multiple variable declaration  and assignment in a single statement
        int x = 5, y=6, z=7;
        System.out.println(x + y + z);
        }}
```
**.println and .format**\
Note: int is lower case cuz its one of primitive types and String is not one of the primitive type but a class 
```java
public class Main{
  public static void main (String[] args){
        String someMessage = "Welcome to my home";
        //Concatenation operations
        System.out.println(someMessage + ", I am " + someName);
        //"format" and place holders. %d = integer, %s = strings 
        System.out.format("The first number is %d, and the second number is %d.", number1, number2);
        System.out.format("%s , I am %s", someMessage, someName);
        //Escaping a double " or single ' quote in a string
        System.out.println("Fiona says \"Hello\"" );
        }}
```
**Exploring primitive data**\
int and String are just few of the data types. They are the most basic data types
* Binary value of either true or false:\
        `boolean isJavaFUn = true;`
* Byte corresponds to 8bits and can store int as well as negative values but has limited range of value\
        `byte numCredits = 30;
        byte numberCredits2 =-30;`
* char stores character datatype, used to represent usually a single character. char is stored using single quote ''\
        `char myGrade = 'B';`
* short can store from -32,768 to +32,767. Good for storing negative integer numbers within specified range.\
        `short winterTemp = -10;`
* Long has much higher range than int.\
        `long tuitionPaid = 237474L;` 
* Float....non-whole numbers....\
        `float gpa = 30.21F;`
* Double is similar to float only differs in the amount of space they occupy in memory\
        `double incomeEarned = 8283.98D;`
* Boolean from comparison: boolean comparison can be explicitly assigned to a variable or used directly
```java
public static void main(String [] args){
        boolean hardCoded = true;
        int num1 =5;
        int num2 = 6;
        boolean comparisonResult = num1>num2;
        System.out.println(comparisonResult);
        System.out.println(num1>num2);
}
```
**Working with integers, floats, and doubles**\
Integer readability: _ is used for integer code readability but does not change in the output formatting
```java
        int someLargeNumber1 = 263736337;
        int someLargeNumber2 = 263_736_337;
        System.out.println(someLargeNumber1 + "\n");
        System.out.println(someLargeNumber2 + "\n");
```
Representing powers
```java
        float someBillion = 1e6f;
        System.out.println(someBillion + "\n");
        double someNano = 10E-4D;
        System.out.println(someNano + "\n");
```

## Basics of Array, List, Dictionaries/Maps
**Array definition within a _main_ class**
```java
        String someName1 = "john";
        String[] namesArray = {"Shola", "Mary"};
        int[] someNumbersString = {100, 504, 463,};
        int[] someNumberArrays;
        someNumberArrays = new int[]{5,6,7};
        //This prints out the entire array as a hash object
        System.out.println(namesArray);

```
**ArrayList**
```java
public class Main{
    public static void main(string [] args){
        //Lists
        List<Integer> someNumbersList = new ArrayList<Integer>(){{add(5);add(6);add(7);}};
        List<String> someNamesList = new ArrayList<>();
        someNamesList.add(someName1);
        someNamesList.add("Aurora");

        //Using Collections to add multiple items to the list
        Collections.addAll(someNamesList, "Betty", "Mirek");

        //for loop
        for(String name:namesArray) {
            System.out.println(name);
        }
        
        for(Integer number:someNumbersList){
            if(number==5) {
                System.out.println(number);
            }
        }

        //Use the Array.toString to print out the content of an array
        String namesArrayToString = namesArray.toString();
        System.out.println(namesArrayToString);

        //Array/list Features
        //.length
        System.out.println(namesArray.length);
        //Index of an element of array or list
        System.out.println(namesArray[0]);
        namesArray[0]="Dupe";}}
```

**Defining Arrays and List in a Separate Class and Utilizing it within a _main class_**
```java
import Arrays;
import List;
public class OtherPracticeClass {
            //normal properties
            int someNumber;
            String someName;
            float temparature;

            //array and list
            String[] someNamesArray;
            int[] someNumbersArray;
            List<Integer> someNamesList;

            // Start of Constructors
            public OtherPracticeClass(int someNumber,
                                      String someName,
                                      float temparature,
                                      int [] someNumbersArray,
                                      String[] someNamesArray,
                                      List<Integer> someNamesList
            ){
                this.someName = someName;
                this.someNumber = someNumber;
                this.temparature = temparature;
                this.someNamesArray =someNamesArray;
                this.someNumbersArray = someNumbersArray;
                this.someNamesList = someNamesList;

            }
            //End of constructors


            // Start of Getters

            public int getSomeNumber(){
                return this.someNumber;
            }

            public String getSomeName() {
                return someName;
            }

            public float getTemparature() {
                return temparature;
            }

            public String[] getSomeNamesArray() {
                return someNamesArray;
            }

            public int[] getSomeNumbersArray() {
                return someNumbersArray;
            }

            public List<Integer> getSomeNumberList() {
                return someNamesList;
            }

            // End of getters

            //Start of methods
            public String nameAndTemperature(){
                return someName + "'s temperature is " + temparature;
            }

            //To string method
            @Override
            public String toString() {
                return "OtherPracticeClass{" +
                        "someNumber=" + someNumber +
                        ", someName='" + someName + '\'' +
                        ", temparature=" + temparature +
                        ", someNamesArray=" + Arrays.toString(someNamesArray) +
                        ", someNumbersArray=" + Arrays.toString(someNumbersArray) +
                        ", someNamesList=" + someNamesList +
                        '}';
            }
        }

```

```java
 import OtherName;
 import ArrayList;
 import List;
 public class MainMainClassPractice2 {
     //Creating an instance of the class in the Otherclass.java
     public static void main(String[] args) {
         OtherPracticeClass datas = new OtherPracticeClass(5,
                 "Betty",
                 30,
                 new int[]{5, 6, 7, 8},
                 new String[]{"John", "Henry"},
                 new ArrayList<Integer>() {{
                     add(10);
                     add(20);
                     add(50);
                 }}
         );

         System.out.println(datas);
         System.out.println();
         String temperatureStatement = datas.nameAndTemperature();
         System.out.println(temperatureStatement);
         System.out.println("------");
         List numbersInList = datas.getSomeNumberList();

         Integer AdditionObject = 0;
         for (Object numbers : numbersInList) {
             System.out.println(numbers);
         }

     }
 }
```

**Arrays, List Review**
```java
public class Collections {
   private static void collections(){
        //Arrays   
        String[] names = {"Tomas", "Shola"};
        int[] ages = {10,20};
        
        //Two types of for loop 
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
        for(String name : names){
            System.out.println(name);
        }

        //List
        List<String> listfOfNames = new ArrayList<>();
        listfOfNames.add("Ondrej");
        listfOfNames.add("Jaroslav");
        listfOfNames.add("Darek");

        //List iterator    
        Iterator<String> namesIterator = listfOfNames.listIterator();
        while (namesIterator.hasNext()){
            String nameToRemove = "Jaroslav";
            if(nameToRemove.equalsIgnoreCase(namesIterator.next())){
                namesIterator.remove();
            }
        }

        //Two types of loop..note the get(i)
        for (int i = 0; i < listfOfNames.size(); i++) {
            System.out.println(listfOfNames.get(i));
        }
        System.out.println("----");
        for(String name : listfOfNames){
            System.out.println(name);
        }
        
        //List Methods
        boolean listEmpty = listfOfNames.size() < 2;
        System.out.println(listEmpty);
        boolean listEmptyCheck = listfOfNames.isEmpty();
        System.out.println(listEmptyCheck);}}
```

**Very Basics of Maps Using HashMap**
```java
public class AppaMain {
    public static void main (String[] args) {
      Map<Integer, String> mapOfBooks = new HashMap<>();
      mapOfBooks.put(1, "Lord of the Rings");
      mapOfBooks.put(2, "1984");
      mapOfBooks.get(2);
      mapOfBooks.remove(2);
      mapOfBooks.clear();   //This removes all items.
      mapOfBooks.size();
      }
    }        
```
* Looping through for **keys**
```java
public class AppaMain {
     public static void main (String[] args) {
       for (Integer i : mapOfBooks.keySet()) {
         System.out.println(i);
       }
     }
}
```
* Looping through for **values**
```java
public class AppaMain {
  public static void main (String[] args) {
    for (String i : mapOfBooks.values()) {
      System.out.println(i);
    }
  }
}
```

* Looping through for both **keys** and **values**
```java
public class AppaMain {
  public static void main (String[] args) {
    for (Integer i : mapOfBooks.keySet()) {
      System.out.println("The key is: "+ i);
      System.out.println("The value is: "+mapOfBooks.get(i))
    }
  }
}
```


* Looping through using Map.Entry<>
```java
public class AppaMain {
    
  public static void main (String[] args) {

    for(Map.Entry<Integer, String> i: mapOfBooks.entrySet()){
        
      System.out.format("%d : %s %n", i.getKey(), i.getValue());
    
    }
      
  }
}
```

**Conversion Between Array and List**
```java
        List<String> stringList = new ArrayList<>();
        stringList.add("Tomas");
        stringList.add("Shola");
        stringList.add("John");
        stringList.add("Patrick");

        String[] arrayOfStrings = stringList.toArray(new String[0]);
        for (String entry : arrayOfStrings){
            System.out.println(entry);
        }
        List<String> listFromArray = new ArrayList<String>(Arrays.asList(arrayOfStrings));
 ```
**Some Array Methods:**

**Some List Methods:**
```java
public class AppaMain {
    public static void main(String[] args){
          List<Float> temperatureList;
          temperatureList = new ArrayList<Float>();
          Collections.addAll(temperatureList, 5.8f, -1.0f);

         temperatureList.size();
         temperatureList.get(1);//List access type
         temperatureList.add(7.8f);
         temperatureList.add(0,5.7f); //Add 5.7 at index 0; It will only replace and won't delete previous element
         Collections.addAll(temperatureList, 5.8f, -1.0f);
         temperatureList.remove(0); //Possible to remove either by index of object
         boolean check = temperatureList.contains(6.0);
         boolean listSubsetCheck = (temperatureList.containsAll(listOfAges));
         temperatureList.indexOf(7.8f);
         temperatureList.lastIndexOf(8);
         temperatureList.set(0, 9f);
         Collections.sort(temperatureList);
    }
}
```
**Some Map Methods:**
## Operators
Operands and operators    + - * / %(to obtain the remainder only).\
NOTE: If numerator is less than denominator then % will give output as the numerator only.)\

```java
        int num1=3, num2=4, result;
        result = num1+num2; 
```
**Reassignment and Increments/Decrement Operations**
```java
        //Reassignment Operations
        int num1 =5;
        num1 +=1;
        num1 -=1;
        ++num1;
        --num1;
        num1++;
        num1--;
        
        
        //COMBINED: Increment/Decrement/Multiplication e.t.c AND (RE)Assignment Operations
        int numA = 5;
        int num3 = 3
        num3 = ++numA; // Increment then Assignment --> num3 =6
        num3 += numA;  //Add numA to num3 ...and assign the result to num3
        num3 *= numA;  // Multiply numA by num3 ...and assign the result to num3
        num3 *=2;
        num3 /= numA;   
        int num1 =10, num2 =2;
        num1 /= num2;
        System.out.println(num1);
```
**Comparison Operations with Numerical Values**
```java
        //Comparison operators for numbers(i.e. int, floats, doubles, e.t.c)
        //== can either check whether two values are same or whether a value is true/false
        int num1 = 10, num2 =2;
        boolean compare = num1 == num2;
        System.out.println(compare);//false
        System.out.println(compare == true); //false

        // != (not equals to operator)
        System.out.println(num1 !=num2); //true
        System.out.println(compare != false); //false....i.e is compare not equal false = false, cuz its equal to false

        // >, <, >== and <==
        System.out.println(num1<num2);//false
        System.out.println(num1<=10);//true
```
**Comparison Operations with Strings with .equals and equalsIgnoreCase**
```java
        String lang1 = "Java";
        String lang2 = "Python";
        String lang3 = "python";
        System.out.println(lang1.equals("Java"));//true
        System.out.println(lang1.equals("java"));//false
        System.out.println(lang1.equalsIgnoreCase("java"));//true
        System.out.print(lang2.equals(lang1));//false
        System.out.print(lang2.equals(lang1));//false
        System.out.print("---------");
        System.out.print(lang2.equalsIgnoreCase(lang3));//true
```
**Logical Operations**
```java
        //OR operator || returns true if either of the statements is true
        boolean czechIsEurope = true;
        boolean lagosIsEurope = false;
        boolean germanyIsAfrica = false;
        boolean ilorinIsAfrica = true;
        System.out.print( czechIsEurope || lagosIsEurope);//true
        System.out.print(lagosIsEurope || germanyIsAfrica);//false
        System.out.print(ilorinIsAfrica || germanyIsAfrica);//true
        System.out.print(ilorinIsAfrica || germanyIsAfrica || lagosIsEurope);//true

        //AND operator /// returns true ONLY if both of the statements us true
        System.out.print(czechIsEurope && ilorinIsAfrica);//true
        System.out.print(czechIsEurope && ilorinIsAfrica && lagosIsEurope); //false cuz just one single false leads to false.

        //Combination of AND logical and comparison operators
        boolean watchInStock = true;
        int watchOnDiscount = 30;
        int watchPrice = 200;
        int myBudget = 180;
        //Check whether watch is affordable without discount
        System.out.print(watchInStock && (watchPrice <= myBudget)); // true && false -- false
        //Check whether watch is affordable with discount
        System.out.print(watchInStock && ((watchPrice-watchOnDiscount)<= myBudget));//true && true -- true
```
**String Operations and Methods**
```java
        //Special case of string concatenation.
        String x = "100";
        int y = 50;
        System.out.print(x+y);//10050
        System.out.println();
        //Using the 'new String' to convert an object to string
        char [] ch = {'L', 'O', 'N', 'D', 'O', 'N'};
        String london = new String(ch);
        System.out.println(ch);
        System.out.println(london);
```
```java
        //Basic String methods
        String phrase = "  Live and let us live ! for fuck sake. ";
        String phras2 = "Live and let us live ! for fuck sake.";
        System.out.println(phrase);
        
        //Remove white spaces
        System.out.println(phrase.trim());
        
        //Length
        System.out.println(phrase.length());
        System.out.println(phras2.length());
        
        //to.UpperCase and to.LowerCase.
        System.out.println(phrase.toLowerCase());
        System.out.println(phrase.toUpperCase());
        
        //Using indexOf to search within a string. Returns the first location of a character.
        System.out.println(phrase.indexOf('u'));
        
        //Using lastIndexOf to search within a string. Returns the location of the last occurrence of a character.
        System.out.println(phrase.lastIndexOf('u'));
        
        //Using  charAt to find out what character is in a specific index location
        System.out.println(phrase.charAt(30));//

        //Replacement        
        System.out.println(phrase.replace("e", "a"));
        System.out.println(phrase.replaceAll("i", "o"));
```
**Null Values**\
Generally used to indicate the absence of a value. Not valid for an integer or primitive datatype.\
NullPointerException can be used to catch null values in a program.
```java
        String nullString = null;
        int intValue = 0;
        System.out.println(nullString);
```
**Casting Between Data Types in Java**\
Assignments between data types. Double and float has higher precision than int.\
Hence, from lower precious --> higher precious is smooth\
However, higher precious --> lower precious is not smooth due to lose of precious. This can be overridden/coerced.
```java
        int myInt = 9;
        double myDouble = myInt;
        System.out.println(myInt);
```
Conversion from a char to int and float is possible. It will return the ASCII code of the char.
```java
        char myChar = 'A';
        int charToInt = myChar;
        float charToFloat = myChar;
        System.out.println(myChar);//A
        System.out.println(charToFloat);//65.0
        System.out.println(charToInt);//65.0
```
## Control Structures in Java: Implementing Java Control Structures
**User Input Basics**\
Note: next/nextInt/nextLine/nextFloat e.t.c tells which datatype is expected as input.\
`InputMismatchException` can be used to catch an instance where input value is not of expected type.\
```
        //Initializing a new instance of scanner object.
        Scanner myScannerObject = new Scanner(System.in);
        System.out.println("Enter temperature:");
        int tempInput = myScannerObject.nextInt();
        //Closing the scanner object
        myScannerObject.close();`
```
        
**Basic _if_ and _if-else_ Statement**
```java
        String tempComment =null;

        if (tempInput>=40) {
          tempComment =  "!!!. Its a hot day.";
        }
        if (tempInput<=39 && tempInput<=25){
            tempComment ="Not so warm day!";
        }
        if(tempInput<=25 && tempInput>=18){
            tempComment ="Its a little cold";
        } else if (tempInput == 17 | tempInput == 16) {tempComment="Its cold";
        }else{tempComment="Fucking cold";}
        System.out.println(tempInput + " " +tempComment );
```
**Basic _for_ loop**
```java
        System.out.println("Enter the number of repeat:");
        Scanner scannerObject = new Scanner(System.in);
        int repeat = scannerObject.nextInt();
        scannerObject.close();
        for (int i = 0; i <repeat; i++) {
            System.out.format("Message %d: Welcome to Java \n", i+1);
```
**Using the _Switch_ Statement**
```java
        System.out.println("Enter a day index:");
        Scanner dayScanner = new Scanner(System.in);
        int dayIndex = dayScanner.nextInt();
        dayScanner.close();
        switch (dayIndex){
            case 1:
                System.out.println("Day: Sunday");
            case 2:
                System.out.println("Day: Monday");
            case 3:
                System.out.println("Day: Tuesday");
            case 4:
                System.out.println("Day: Wednesday");
            case 5:
                System.out.println("Day: Thursday");
            case 6:
                System.out.println("Day: Friday");
            case 7:
                System.out.println("Day: Saturday");
            default:
                System.out.println("Only values from 1-7 are permitted");

        }
```
**Types of _for_ loop Revisited**
```java
        //working on two arrays at the same time.
        String [] productNames = {"Mouse", "Speakers", "Watch", "Earphones", "Charger"};
        double [] productPricesUSD = {9.0, 86.0, 111.0, 42.0, 55.0};
        double USDToEUR = 0.82;
        System.out.println("Products name, prices in USD and prices in Euro");
        //Going over an alternate element. e.g 2 increment use i+=2.
        //The second part of the for loop () can be changed to state the number of iteration. e.g i<3
        for (int i = 0; i <productNames.length ; i++) {
            System.out.format("\n %s costs USD %f or %f EURO", productNames[i], productPricesUSD[i], productPricesUSD[i]*USDToEUR);
        }
        //SOME NOTES: Complete iteration in reverse order: for(int i=productNames.length-1; i>=0; i--)
        //  Leaving out the updated value of i. for(int i=0; i<productPriceUSD.length; ) leads to an infinite loop !!!!
```

**Nested _for_ loops**
```java
        String [] products = {"Mouse", "Speakers", "Watch", "Earphones", "Charger"};
        String [] brands = {"Raganza", "Qenel", "Zoflina"};
        System.out.println("What are the products in the catalog?\n");
        for(int i=0; i<products.length; i++){
            for(int j=0; j<brands.length; j++)
            //i.e. first for loop gives us usable values of i=[0-4] and
                // second for loop gives us usable value of j=[0-3]....in total 5X3 = 15 iter
                //.....first [0,0;0,1,0,2]...second [1,0;1,1;1,2].......until [4,0;4,1;4,2;4,3]
            {
                System.out.format(products[i]+" " + brands[j] +"\n");

            }
        }
```
**_Continue_ and _Break_ Statements, and _IF-NOT_**\
_"break"_ terminates iteration while _"continue"_ skips an iteration based on the specified condition.
```java
        String [] treasureChest = {
          "Book", "gold", "Quil", "Quill", "Document", "silver", "gold", "Book", "Book", "Spoon", "Silver", "Gold",
                "document",
        };
        int usefulItemsCount = 0;
        int unusefulItemsCount = 0;


        for (int i = 0; i < treasureChest.length ; i++) {
            if(treasureChest[i].equalsIgnoreCase("Gold") || treasureChest[i].equalsIgnoreCase("silver") ){
                usefulItemsCount++;}
            else{unusefulItemsCount++;}

        }
        System.out.println("# of Useful items(gold/silver): " + usefulItemsCount +"\n");
        System.out.println("# of Unuseful items(books/spoon/quill): " + unusefulItemsCount +"\n");
        
```
Using "if not" within the if. Continue statement.
```java
        for (int i = 0; i < treasureChest.length ; i++){
            //Note the "!" means if the expression is NOT true. The "continue" statement means
                 // that any code after "continue" should not be executed. Hence, depending on the "if condition", the lines
                // from  152 might or might not be executed.
                //The for loop will be continued unlike "break" which actually stops the for loop.

            if (!(treasureChest[i].equalsIgnoreCase("Gold") || treasureChest[i].equalsIgnoreCase("silver"))){
                System.out.format("\n Junk found, a %s", treasureChest[i]);
                unusefulItemsCount++;
                continue;
            }
            System.out.format("\n I found a treasure, a %s ", treasureChest[i]);
            usefulItemsCount++;
        }

        System.out.println("\n # of Useful items(gold/silver): " + usefulItemsCount);
        System.out.println("# of Unuseful items(books/spoon/quill): " + unusefulItemsCount +"\n");
```
Using the "break" statement to terminate. The "break" statement exits the for loop immediately a specified is satisfied.
```java
        String interestedItem = "document";
        for (int i = 0; i < treasureChest.length ; i++) {
            if(treasureChest[i].equalsIgnoreCase(interestedItem)){
                System.out.format("Interested item \"%s\", found. \n", treasureChest[i].toUpperCase());
                break;
            }else{System.out.format("\"%s\" is not the interested item\n", treasureChest[i].toUpperCase());}

        }
        
```
**_While_ Loop**\
Useful when the maximum number of iteration is known beforehand.
"while" gives the statement that defines the condition for the number of iterations or the conditions when iteration should be done.\
 A for loop is a shorthand of the combination of i=0, while statement, and i++ as shown below.
```
        /*
        int i = 0;
        while(i<treasureChest.length){
            System.out.format("Found %s \n", treasureChest[i]);
            i++;
        }
         */

        /*
        String interestedItem = "document";
        int i = 0;
        boolean itemFound = false;
        //note (!itemFound)....while itemFound is unchanged and remains as false
        while (!itemFound){
            if(treasureChest[i].equalsIgnoreCase(interestedItem)){
                System.out.format("Interested item \"%s\", found. \n", treasureChest[i].toUpperCase());
                itemFound = true;
                int remainder = treasureChest.length - i;
                System.out.println(remainder);
                break;
            }
            System.out.format("\"%s\" is not the interested item\n", treasureChest[i].toUpperCase());
            i++;
        }
```
```
        --[DocumentSearcher.java]---
        String interestedItem = "document";
        int i = 0;
        boolean itemFound = false;
        //note (!itemFound)....while itemFound is unchanged and remains as false
        while (!itemFound){
            if(treasureChest[i].equalsIgnoreCase(interestedItem)){
                System.out.format("Interested item \"%s\", found. \n", treasureChest[i].toUpperCase());
                itemFound = true;
                int lengthOfAll = treasureChest.length;
                int remainder = treasureChest.length - i;
                System.out.format("Total items in the treasure chest is %d \n", lengthOfAll);
                System.out.format("There are %d remaining items not searched.",remainder);
                break;
            }
            System.out.format("\"%s\" is not the interested item\n", treasureChest[i].toUpperCase());
            i++;
        }
```
- **Arguments/Parameters**\
In the main method, `args` represents the arguments that can be passed, `Strings[]` states that type of parameters.
        
        System.out.println("The input args are: " + Arrays.toString(args));
        String interestedItem = "document";
        int i = 0;
        boolean itemFound = false;
        //Unlike the previous implementation, the "interestedItem property will be supplied via intelliJ 
            "program variable" or from shell.
        String interestedItem = args[0];
        System.out.println("Let the search begin for: "+ interestedItem);
        //NOTE: (!itemFound)....while itemFound is unchanged and remains as false
        //The below code (a) assumes that we pass two arguments, one is documents to search for and the second is the maximum number
           //of search iterations to be carried out. Hence, we use the math.min to ensure the search is limit is within the minimum bound
           //limit of the treasureChest.length and the passed maximum number of search iteration
           //while loop was used in the video.
        //(a) NOTE: int searchLimit = Math.min(Integer.valueOf(args[1]), treasureChest.length)
        while (!itemFound){
            if(treasureChest[i].equalsIgnoreCase(interestedItem)){
                System.out.format("Interested item \"%s\", found. \n", treasureChest[i].toUpperCase());
                itemFound = true;
                break;
            }
            System.out.format("\"%s\" is not the interested item\n", treasureChest[i].toUpperCase());
            i++;
        }

## Modeling Entities in Java: Getting Started with Classes & Objects
* Inheritance (is relationship) and Composition(has relationship):A case of two classes, student and university. A university\
(has/composes of) students.  A student(is/inherit) a university ppt.
* A **class** is a _user defined type_ that contains both _data/properties/attributes/fields_ and _function/method/code_.
* Actions are performed on the attributes of a class using its method. Classes are highly stateful.
* Constructor is used to construct a specific instance of a class which is called object of the class. We invoke a constructor to\
instantiate an object of a class.
* A Class is a template while an object is a specific instance of the class(i.e. a specific instance of a template that contains\
actual attributes and actions.). Hence, when we instantiate a class, we are specifically creating an object from that class
* All objects in Java inherit from a common base class known as "Java.lang.object" (base class). This means all objects have some\
attributes and actions in common. For example "this": which is used to refer to a particular attribute of a class within the class. 

## MODELING ENTITIES IN JAVA: DEFINING CUSTOM CLASSES AND OBJECTS
```java
//Date object containing the day, date, time and timezone.
public class Main{
    Date someDate = new Date(); //No argument constructors
    System.out.println(someDate);
    //Random object
    Random someRandom = new Random();
    System.out.println(someRandom); //prints out the full qualify class name&hex memory location of the random object.
}
```
- All objects in inherits the default implementation of some methods such as  `toString()`, `getClass()`, `equals()` from the\
base `java.lang.Object` class.
- The `getClass()` method helps us to identify what class an object belongs to. This is helpful when considering inheritance and\
polymorphism. While default implementation of the `toString()` method prints out the FQCN and hex memory location of the class.
- Each instance of an object has a unique memory location. Hence, `getClass()` can is same if two objects share same class,\
but `toString()` will be different.

```java
public class Student {
        //Properties
        //Better practice is to set the fields as private and use getters and setters to regulate access
        public int id;
        public String name;
        public double gpa;
        //Constructor
        public Student(int id, String name, double gpa) {
                        this.id = id;
                        this.name = name;
                        this.gpa = gpa; }
        //Method
        public void quickInfo(){
                        System.out.println(id + " " + name +" "+gpa);
                        } 
}
```
```java
public class Main{
public static void main(String[] args){
    Student alice = new Student(5,"alice", 4.7);
    alice.quickInfo();
    System.out.println(alice.name);
    //Changing the object properties after initializing it from a class.
    alice.name = "changedAlice";
    alice.quickInfo();
    //Possible to say alice.name only when the property "name" is set to public. 
        // If the property "name" is private, we need a getter.
    System.out.println(alice.name);
}}
```
- **Private fields/properties, getters and setters**

Best practice is to set the fields as private and use getters to access the field and setter to set the field of the class respectively. 
```java
public class Student {
    private int id;
    private String name;
    private double gpa;
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public double getGpa() {
        return gpa;
    }
    public void quickInfo(){
        System.out.println(id + " " + name +" "+gpa);
    }
}
```

```java
public class Main{
    public static void main(String[] args){
        Student alice = new Student(5,"alice", 4.7);
        alice.quickInfo();
        //Using a getter
        System.out.println(alice.getName());
        //Use setter to changing an object's property.
        alice.setName("changedAlice");
        alice.quickInfo();
        System.out.println(alice.getName());
        System.out.println(alice);//prints the class name and memory location.
    }
}
```

## Modeling Entities in Java: Methods, Method Overloading, & Constructors
- Private fields/properties, getters and setters.
```java
public class Employee{
    private String name;
    //Setting an initial value
    private double experience = 0.0;
    private double salary = 0.0;
    public Employee (String name, double experience, double salary) {
        this.name = name;
        this.experience = experience;
        this.salary = salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void quickInfo(){
        System.out.println("Name: " + name + "\n " +"Experience: "+ experience+ "\n " +"Salary: " + salary);
    }

    public String nameInfo(){
        return name;
    }

    //UPDATER..a custom class
    public void upDater (String newname, double newexperience, double newsalary){
        setName(newname);
        setExperience(newexperience);
        setSalary(newsalary);
    }
}
```
Using the defined UpDater method to entirely change the  fields of the object
```java
public class Practice1 {
    
    public static void main(String[] args){
        
        Employee employee1 = new Employee ("Alice", 5, 200);
        Employee employee2 = new Employee ("Robert", 9, 800);
        Employee employee3 = new Employee ("John", 11, 1200);
        
        employee1.setSalary(500);
        employee1.quickInfo();
        
        String employee1Name = employee1.nameInfo();
     
        System.out.println(employee1Name);
        
        
        employee3.upDater("Rachael",15, 1500);
        employee3.quickInfo(); 
    }
}
```

- **Changing fields with the class and method-overload:**

**Method overloading:** Means having duplicates or several versions of the same method (i.e. method with same name )but different\
signature e.g. differing number of input arguments or type of input arguments.
```java
public class Employee {
    
    private String name;
    
    //Setting an initial value
    private double experience = 0.0;
    
    private double salary;

    public Employee(String name, double experience, double salary) {
        this.name = name;
        this.experience = experience;
        this.salary = salary;
    }

    public void quickInfo() {
        System.out.println("Name: " + name + ", " + "Experience: " + experience + ", " + "Salary: " + salary);
    }

    public double getSalary() {
        return salary;
    }

    public double calculateBonus(float performancePercentage) {
        double bonus = (salary * performancePercentage * 0.01);
        return bonus;
    }

    public double calculateIncrementAndSetNewSalary() {
        //Local variable defined inside a method.
        double increment;
        if(salary <200){
            increment = 0.20;
        }else{
            increment = 0.10;
        }
        //This will automatically update the salary. Check the instance on the main method.
        salary = salary + salary * increment;
        return increment;
    }

}
```

``` java
public class Main {
public static void main(String[] args){

    Employee employee1 = new Employee ("Alice", 5, 400);
    
    employee1.quickInfo();
    
    System.out.print("Bonus: " + employee1.calculateBonus((10))+ "\n");
    
    //Note that calling the increment method automatically update the salary. Check the method detail within the class.
    System.out.print("Increment: " + employee1.calculateIncrementAndSetNewSalary() + "\n");
   
    System.out.print("Updated Salary: " + employee1.getSalary());
    
    }
}
```

- **Default no-argument constructors, Parameterized constructors and Method Overload**

We can declare more than one constructor in such a way that we can different objects of the same class that depends on the chosen\
constructor to invoke.
```java
public class Car {
    public String name;
    public String type;
    public float mpg;
    public int price;

    public Car(){

    }
    public Car(String carName){
        name = carName;
    }

    public Car(String carName, String carType){
        name = carName;
        type = carType;
    }
    public Car(String carName, String carType, float carMpg){
        name = carName;
        type = carType;
        mpg = carMpg;
    }
    public Car(String carName, String carType, float carMpg, int carPrice){
        name = carName;
        type = carType;
        mpg = carMpg;
        price = carPrice;
    }

    public void printCarInfo() {
        System.out.format("Name:%s, Type:%s, MPG:%.1f, Price:%d", name, type, mpg, price);
    }}

```
The main class
```java
public class Main{
  public static void main(String[] args){
    //Instance corresponding to the null argument constructor
    Car carInstance0 = new Car();
    System.out.println(carInstance0);
    carInstance0.printCarInfo();
 
    //Instance with one field
    Car carInstance1 = new Car("Honda");
    System.out.println(carInstance1);
    carInstance1.printCarInfo();
  
    //Instance with two fields
    Car carInstance2 = new Car("Tesla", "Sedan");
    System.out.println(carInstance2);
    carInstance2.printCarInfo();

    //Instance with three fields
    Car carInstance3 = new Car("Toyota", "Sedan", 5);
    System.out.println(carInstance3);
    carInstance3.printCarInfo();
 
    }
}
```
## Modeling Entities in Java: Static Members, Arguments, & Method Overriding
**(A) Static fields/variables**
* Java permits associating member variables/properties and methods with **classes** rather than **objects**. These are known\
as **Static variables/methods**. Static properties and methods are shared by all instances/objects of the particular class.(i.e.\
all objects belonging to that class)
* On the other, **Instance** variables are not shared by all objects of the same class. They are specific to particular object/instance\
of the class.
```java
public class Cookie {
    
    // Member variables/fields
    private String id;
    private String userName;
    private String sessionKey;
    private int itemsInCart;

    //A static member variable/fields
    public static String cookieType = "SESSION_COOKIE";

    //Parametrized constructor
    public Cookie(String id, String userName, String sessionKey, int itemsInCart) {
        this.id = id;
        this.userName = userName;
        this.sessionKey = sessionKey;
        this.itemsInCart = itemsInCart;
    }
    
    //Series of getters and setter functions/methods for each of the fields
    public String getId(){
        return id;
    }

    public void setId(String userId){
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public int getItemsInCart() {
        return itemsInCart;
    }

    public void setItemsInCart(int itemsInCart) {
        this.itemsInCart = itemsInCart;
    }

    //toString method
    @Override
    public String toString() {
        return "Cookie{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                ", itemsInCart=" + itemsInCart +
                '}';
    }}

```
- Main class
```java
public class Main{
    public static void main(String[] args){
        Cookie c1 = new Cookie("abc12", "Alice22", "A@22", 2);
        System.out.println("c1: "+ c1);
        System.out.println("Cookie Type(c1): " + c1.cookieType);

        Cookie c2 = new Cookie("efg34", "Betty34", "B@34", 5);
        System.out.println("c2: "+ c2);
        System.out.println("Cookie Type(c2): " + c1.cookieType);
        
        Cookie c3 = new Cookie("hij56", "Cata56", "C@56", 9);
        System.out.println("c3: "+ c3);
        System.out.println("Cookie Type(c3): " + c1.cookieType);

        System.out.println("----");}}
        c1.cookieType = "LOGGED_IN_USER_COOKIE";
        System.out.println("Cookie Type(c1): " + c1.cookieType);
        System.out.println("Cookie Type(c2): " + c1.cookieType);
        System.out.println("Cookie Type(c3): " + c1.cookieType);
} 
```
__NOTE:__

Since cookieType is `public`: we can change its value from another class. Since its `static`, it shared bt all instances/objects of the class.\
Hence, if the `static` field is changed in a particular instance, the change will propagate to all other instances of the class.

However, if a static field is marked as  **final**, the original value assigned to the field can no longer be modified within/by another\
instance/object of the class.

__Recommendation:__\
Access and update a static field via reference to the **class name** (i.e. class reference), instead of via **object**.
```
Cookie.cookieType = "CHANGED_COOKIE";
System.out.println("Cookie Type: " + Cookie.cookieType);
```
This can also be done using a setter. Generally..Non-static methods can't be invoked/referenced from a static context.\
```
//Gettters and setters for the static field
public static String getCookieTyepe(){
    return cookieType;
}
public Static void setCookieTpe(String cookieType){
    //Note the class reference as opposed to object reference in the return statement above
    Cookie.cookieType = cookieType
}
```
**(B) Using a Static Field for Auto-counting Objects**

```java
public class EmployeeCount {
    
    //Use of java.util.Random to generate random number
    private static final Random randomIdGenerator = new Random();

    private static int employeeRandomId;
    
    private String firstName;
    
    private String lastName;
    
    private static int employeeNumber = 0;
    
    private String employeeUniqueId;

    public EmployeeCount(String firstName, String lastName) {
        
        this.firstName = firstName;
        this.lastName = lastName;
        
        //employee++ increment the value of employeeNumber by +1 when an object of this class is instantiated
        //Note: without it being declared as static, the value would have stayed as 1.
        employeeNumber++;
        
        //Set the value of employeeId to "employeeId" + employeeNumber (i.e after it has been incremented)
        this.employeeUniqueId = "employeeId"+employeeNumber;
        
        //Use the already instantiated randomIdGenerator to generate a random number and assign the value to employeeRandomId
        this.employeeRandomId = randomIdGenerator.nextInt();

    }

    public String getFirstName(){
        return firstName;
    }
    public void setFirstName(String firstName){
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    
    public static int getEmployeeNumber() {
        return employeeNumber;
    }
    
    public void setLastName(String lastName){
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "EmployeeCount{" +
                "employeeRandomId=" + employeeRandomId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", employeeUniqueId='" + employeeUniqueId + '\'' +
                '}';
    }
    
}
```

```java
public class Main{
public static void main(String[] args){

        EmployeeCount  e1 = new EmployeeCount("Jaroslav", "Jarda");
        System.out.println(e1);
        System.out.println("This is employee number "+ e1.getEmployeeNumber());

        EmployeeCount  e2 = new EmployeeCount("Tomas", "Tomas");
        System.out.println(e2);
        System.out.println("This is employee number "+ e2.getEmployeeNumber());

        EmployeeCount  e3 = new EmployeeCount("Ondrej", "Ondrej");
        System.out.println(e3);
        System.out.println("This is employee number "+ e3.getEmployeeNumber());


        //Uses object reference to the field "employeeNumber" to get the total number of employees
        System.out.println("The total number of employee is "+ EmployeeCount.getEmployeeNumber());
        for (int i=0; i<10; i++){
            new EmployeeCount("firstname","lastname");
        }

        //Uses class reference to the field "employeeNumber" to get the total number of employees
        System.out.println("The total number of employee is "+ EmployeeCount.getEmployeeNumber());
        
        /*
        EmployeeCount{employeeRandomId=1301452560, firstName='Jaroslav', lastName='Jarda', employeeUniqueId='employeeId1'}
        This is employee number 1
        EmployeeCount{employeeRandomId=-584288445, firstName='Tomas', lastName='Tomas', employeeUniqueId='employeeId2'}
        This is employee number 2
        EmployeeCount{employeeRandomId=-1821726206, firstName='Ondrej', lastName='Ondrej', employeeUniqueId='employeeId3'}
        This is employee number 3
        The total number of employee is 3
        The total number of employee is 13
         */

    }}
```

**(C) Using a Class Reference to Invoke Static Methods**

* _Non-static_ methods are known as **instance methods** because they are associated with a particular instance/object. They are not\
shared by all objects/instances of a class as its with **static methods**.
* _Non-static_ methods and fields can't be referenced from a static context. However, static methods CAN ALWAYS be instantiated/referenced\
within a non-static methods. For example keyword **this** can't be referenced from **static main** method.
* To invoke/reference a non-static method from a static context we need to pass it to a receiver. In order to use a non-static\
methods/fields in **static main** we need to instantiate it by passing it to a receiver as variable.
* Below is an example that explains the points:
```java
public class Employee {

  public static String firstName;

  public static String lastName;

  //Constructor
  public Employee(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }
  
  public void getFullName1() {
    System.out.println("Non-static method");
    System.out.println(firstName + " " + lastName);
  }

  public static void getFullName2() {
    System.out.println("Non-static method");
    System.out.println(firstName + " " + lastName);
  }

}    
```
```java
public class Main {

  public static void main(String[] args){
      
    Employee employee = new Employee("Mary", "John");
    System.out.println(employee);
    
    //Here we passed an instance method into a receiver (employee). Hence, we invoked a non-static method under
        // a static context with the help of a receiver.
    employee.getFullName1();
    
    //Below instantiation of the same method IS NOT possible because it attempts to instantiate a non-static/instance
        // method under the context of a static method (main) 
    getFullName1();
    
    //Below instantiation of a method IS possible because a static method is being instantiated under a static context 
    getFullName2();

  }
}
```
**(D) Exploring Field Reassignments**

**Pass by value vs Pass by reference  semantics**

* Pass by value semantics:
```java
  public class Practice1 {
    
    public static void main(String[] args){
        
        int numberX= 10;
        System.out.println("Inside main() BEFORE function invocation: "+numberX);
        
        incrementNumber(numberX);
        System.out.println("Inside main() AFTER function invocation: "+numberX);
        
        //The printout below shows that the value of numberX did not change
        System.out.println("The final value of numberX is: "+ numberX);
    }
    
    private static void incrementNumber (int numberX){
        ;
        System.out.println("\nOriginal number (inside function): " + numberX);
        
        numberX =  numberX + 5; reassignment operation
                
        System.out.println("Num after increment: "+numberX);
    }
}
```
* The value of the field _numberX_ was not changed in the _main_ (calling function) by the helper function/method _incrementNumber_. Hence,\
the change we made to the variable inside the **helper function** did not reflect back in the **main function**.
* The most important reason why the value of numberX is not altered, is because numberX is a **Primitive type**. double, int and String,\
exhibit **Pass by value** semantics. **String** is an object and **NOT** a **Primitive type**  but yet exhibit this behavior because **String**\
is **Immutable type**.
* Objects that exhibit the **Pass by value** semantics are also usually **Immutable types**. Instances of **Immutable types** are\
actually **copies** of the original and not a new instance.
* All variables/fields of **primitive types** exhibit **Pass by value** semantics. Integer also exhibit **Pass by value** semantics\
An example is shown below to demonstrate that String(s) are immutable 
```java
package org.example;

public class App {
    
    public static void main(String[] args){
        
        String someString = "Shola";

        changeTheString(someString);
        
        System.out.println(someString); //Shola
        
    }


    public static String changeTheString(String stringToChange){
        
        stringToChange = new String("You have been changed");
        
        return stringToChange;
    }
    
}
```

**(E) Exploring Field Reassignments (Part 2)**

* When we pass an **array** from a **main** function (calling) into a **helper** function (called), any **modification** made inside the helper\
function will be persistent. However, the **total reassignment** of the array is **NOT** persistent. 
```java
package notebooks;
import java.util.Arrays;
public class Practice1 {
    
    public static void main(String[] args){
        
        String [] arrayOfFruits = new String[]{"apple", "banana", "kiwi"};
        
        System.out.println("Inside main() BEFORE helper function invocation: "+ Arrays.toString(arrayOfFruits));
        
        updateArray (arrayOfFruits, 1,"orange");
        
        System.out.println("\nInside main() AFTER helper function: "+Arrays.toString(arrayOfFruits));
    
    }
    
    // THIS DOES THE SAME AS IMMUTABLE. IT DOESN'T CHANGE THE VALUE OF THE ARRAY IN THE MAIN FUNCTION.
    public static void reassignArray (String[]  arrayOfFruits){
    
        System.out.println("\nOriginal array (INSIDE helper function): " + Arrays.toString(arrayOfFruits));
        
        arrayOfFruits = new String[] {"watermelon", "orange"};
        
        System.out.println("\nArrays after helper (INSIDE helper function): " + Arrays.toString(arrayOfFruits));
        
    }
     
    //This reassignment of an element in an index of the array. This change is persistent inside the main function.
    public static void updateArray (String[]  arrayOfFruits, int indexToUpdate, String fruit) {
        
        System.out.println("\nOriginal array (INSIDE helper function): " + Arrays.toString(arrayOfFruits));
        
        arrayOfFruits[indexToUpdate] = fruit;
        
        System.out.println("\nArrays after helper (INSIDE helper function): " + Arrays.toString(arrayOfFruits));
    
    }
}
```

**(F) Exploring Field Reassignments (Part 3): Custom Objects**

* Custom objects behave in the same manner as **immutable types** with respect to persistence when reassignment is done in a called\
function outside a main calling function. The so-called **REASSIGNMENT** operation is NOT persistent while **UPDATE** operation IS persistent
```java
public class Car {
    
    private String name;
    private String type;
    private float mpg;
    private int price;
        
    public Car(String name, String type, float mpg, int price) {
        this.name = name;
        this.type = type;
        this.mpg = mpg;
        this.price = price;
    }
    /*
            Getters and Setters here
     */

    
    
   }
```

```java
public class Main{
    
  public static void main(String[] args){
      
    Car car1 = new Car("Tesla X", "Sedan", 56, 90000);
    
    System.out.println("Inside main() BEFORE function invocation: " + car1);
    
    reassignCar(car1);
    
    System.out.println("\nInside main() AFTER helper function invocation: "+car1);
    
    System.out.println("Effect of Update Operation");
    
    updateCar(car1);
    
    System.out.println(car1);
  
  }

    //Reassignment operation is not persistent.
    public static void reassignCar (Car car1) {
      
        System.out.println("\nOriginal (inside main() function"+ car1);
        
        car1 = new Car("Mercedes S-class", "Limo", 67, 1890988);
        
        System.out.println("\nCar after reassignment "+ car1);
    }

    //So-called Update operation IS PERSISTENT.
    public static void updateCar(Car car1){
      
        car1.setName("Tesla Model YYYY");

    }

}
```

**(G) Checking Object Equality with == and .equals()**

**Shallow copy and equality:**
* Shallow copy means two or more objects resides on the **same location on the memory heap**. Shallow copies share the same **hashCode**.
* The **hashCode contract** (SP) means any two or more objects which evaluates to true with == or .equals must share the same hashCode 
```
Product phone1 = new Product(234, "iPhone 13", "Electronics");
Product phone3 = phone1;
```
* Printout of FQN of two shallow copies shows they have same FQN
```
System.out.println(phone1 );
System.out.println(phone3);
```
* Both **==** and **.equals** checks whether the two objects of comparison shares the **_same memory location_**. Hence, they will\
evaluate to true for shallow copies.
```
System.out.println("phone1 == phone3: "+(phone1==phone3));
System.out.println("phone1 == phone3: "+phone1.equals(phone3));
```

**(H) Understanding the HashCode Contract**

**Semantic equality**:
* Two objects with the same properties and considered as **_equal_** even if they have **_different memory location_**. In essence,\
instead of using the memory location to compare if two objects are equal, we use the information/data they hold.
* Expressions **==** and **.equal** will evaluate to **false** for the objects below. They have different heap locations (2 different objects)\
even though they hold same information. 
```java
public class Main{
    
    public static void main(String[] args){
        Product phone1 = new Product(234, "iPhone 13", "Electronics");
        Product phone11 = new Product(234, "iPhone 13", "Electronics");
    
        System.out.println(phone1 );
        System.out.println(phone11);
    
        System.out.println("phone1 == phone11: "+(phone1==phone11));
        System.out.println(phone1.equals(phone11));
        
         /*
        notebooks.Product@677327b6
        notebooks.Product@14ae5a5
        phone1 == phone11: false
        false
         */
    }
}
```
The default implementation of **.equals** and **==** only _compares memory locations_ of two objects and return true if memory\
location is the same. It doesn't consider or compare the value of the information held by the objects.

**The _.hashCode()_:**
* It is used by a large number of java collections, such as hash tables, dictionaries and Maps to store objects and check object equality. 
```java
public class Main{
    
    public static void main(String[] args) {

        Product phone1 = new Product(234, "iPhone 13", "Electronics");
        Product phone11 = new Product(234, "iPhone 13", "Electronics");

        //A copy of phone1
        Product phone1Copy = phone1;

        //Checking the hashcode of each of the three objects
        System.out.println("phone1 HashCode: " + phone1.hashCode());
        System.out.println("phone1Copy HashCode: " + phone1Copy.hashCode());
        System.out.println("phone1 HashCode: " + phone11.hashCode());

        //Comparing the hashcode of each of the three objects
        System.out.println("phone1 == phone11: " + (phone1.hashCode() == phone11.hashCode()));
        System.out.println("phone1 == phone1Copy: " + (phone1.hashCode() == phone1Copy.hashCode()));
        
        /*
        phone1 hashCode: 1735600054
        phone1Copy hashCode: 1735600054
        phone1 hashCode: 21685669
        phone1 hashCode == phone11 hashCode: false
        phone1 hashCode == phone1Copy hashCode: true
        */
    }
}
```

**(I) Overriding the .equals() Method**

* The aim is to check for **_semantic equality_**: when two objects are identical information-wise.
  - Two different objects of same class. Both objects hold identical information. If we use built-in .equals or == for comparison,\
     their memory location will be checked and evaluated to false. 
  - Starting with the @Override helps ensure SOP and also helps to catch syntax errors. 
  - The input argument is of the type **Object** (from java.lang.object) because if we specify another class as the input type,\
    then override implementation only work for instances of that class.
  - We first check if the other object is null, and then we return false.
  - Secondly, we check if other == other. 
  - Thirdly, we check if other is not an instance of the Product class. We return false. 
  - Fourth, we conduct **down-casting**. We change it to class product. We convert it to class product while preserving its information.
  - We can safely downcast because we have already checked in third step that the object other belongs to the class Product. 
  - With the downcast, we create a new variable named _product_ which is identical to _other_. 
  - Then we perform **element-wise equality check** across all elements of the objects product and other. this represents other. True is\
     return if all elements are equal. 
  - We have to keep the override **up-to-date** if we make any changes to the actual class Product that we are comparing.

```java
public Product{
    /*
       Fields, contructors and methods goes here.
     */


@Override
public boolean equals(Object other){

        if(other==null){
            return false;
        }

        if(this==other){
            return true;
        }

        if(!(other instanceof Product)){
            return false;
        }

        //Downcasting the Object "other" to an instance of Product
        Product product = (Product) other;
        
        //NOTE: Element-wise comparison of the two objects. Primitive types (int) are compared using == 
                // Objects(String) are compared using .equals().
        if(
            this.id==product.id &&
            this.name.equals(product.name) &&
            this.type.equalsIgnoreCase(product.type)
        ){
            return true;
        }
        
        return false;
}
```

```java
public static void main(String[] args){
    
    Product phone1 = new Product(234, "iPhone 13", "Electronics");
    Product newPhone = new Product(234, "iPhone 13", "Electronics");

    System.out.println("phone1 "+phone1);
    
    System.out.println("newPhone "+newPhone);
    
    //Using the override .equals() for comparison
    System.out.println("phone1-newPhone comparison with overridden equals : "+(phone1.equals(newPhone)));
    
    //Using == for comparison
    System.out.println("phone1-newPhone comparison with == : "+(phone1==newPhone));
    
    /*
    phone1 Product{id=234, name='iPhone 13', type='Electronics'}
    newPhone Product{id=234, name='iPhone 13', type='Electronics'}
    phone1-newPhone comparison with overridden equals : true
    phone1-newPhone comparison with == : false
    false
     */

    }
```

**(J) Overriding the .hashCode() Method**
```
@Override
public int hashCode(){

    //Hashing each elements of the object
    return Objects.hash(id, name, type);
}
```

## Mapping Relationships in Java: Modeling Is-a Relationships Using Inheritance
**(A) Overview**

Java is an OOP language, meaning Java programs and frameworks are built on the pillars of **inheritance** and **runtime polymorphism**.\
Inheritance is used to model **is-a** relationships between classes; such relationships could include either **behavior and state** or just **behavior** alone.
This part of the course focused on:
1. How classes can be used to model an entity's **state and behavior** and **inheritance** to model **is-a** relationships between two classes.
2. The use of **instanceof** operator and the **getClass** method.
3. Creating an **inheritance hierarchy** out of custom classes.
4. Concept of **up-casting** and **down-casting** to explore the relationship between variable types, object types, and inheritance.
5. Importance of **inheritance** as a powerful mechanism for code re-use and modeling **is-a** relationships in Java.

**(B) Utilizing Classes and the Inheritance Hierarchy**

**The Concept of Inheritance and _is-a_ Relationship:**
* All java classes inherits from a common base class **java.lang.Object**. This means that all built-in and user defined classes have\
origin in `java.lang.Object`. Hence, all objects **inherits** from `java.lang.Object`. 
* **Inheritance** enables the _functionality_ present in a **base class**, to be available in **objects** of  a **derived class**. It enables and\
maximizes code reuse. 
* A good **inheritance hierarchy** places all common aspects in _high level base classes_. This will then be replicated to low level _derived classes._ 
This practice ensure that the implementation of derived classes contains aspects that are only specific to them. 
* In essence, **inheritance** is meant for expressing **is-a** relationship: --> every object of a java class **is-a** object of the `java.lang.Object`.
* All custom classes and their objects inherits all the built-in methods of the `java.lang.Object`. These methods can be **overriden** as has been shown.
i.e. a newly defined class with no custom method or fields, by default, inherits some built-in methods from `java.lang.Object`.
* Inheritance helps to model relationship between objects that are sub-categories of each other.

**Composition VS Inheritance: Examples**
* The concept of _**member variables**_ of one class contained within another class is known as _**composition**_. The concept of having a class\
that **extend**  another class is known as _**inheritance**_. Composition is used for **_has-a_** relationship while inheritance is used to model\
an _**is-a**_ relationship
* **Example:** The famous **Dogs** entity:
    - **Common** attributes/features of _ALL_ dogs: tail, - bark, -ears, -loved in europe...--> all these will reside in the **base class**.
    - **Specific** attributes/features of dog:
        - A Pug: short,  fat
        - A Doberman: tall, skinny
        - all these will reside in the **derived class** of each breed of the dog. 
    - Which means the **derived classes** will be lean since they already **inherit** from the **base class**. 
    - Each of the **derived class** will share a common **is-a** relationship with the **base class.** Apug **is-a** dog
* **Example:** The **Employees**  entity:
  - **Common** attributes/features of _ALL_ employees: -Employee ID, -Employee name, -Employee Sex, --> all these will reside in the **base class**.
  - **Specific** attributes/features of types of employees:
     - An Engineering Employee: performance bonus, engineering field, stock option,
     - A Sales Employee: sales commission, level of sales, overtime pay,
     - all these will reside in the **derived class** of each of the engineering and sales employees.
  - Which means the **derived classes** will be lean since they already **inherit** from the **base class**.
    - Each of the **derived class** will share a common **is-a** relationship with the **base class.** 
    - An engineeringEmployee **is-a** employee
* Another principle that is good practice is the **open-closed principle**: classes should be **open** for **extension** but **closed** for **modification**.
* Composition, inheritance, and open-closed principles lead to code re-usability. 
<img src="\images\Inheritance-Composition.png">

**(C) Features of Object-Oriented Programming**

The three core building blocks of OOP are- **inheritance**, **polymorphism** and **encapsulation**. 
* **Inheritance** is the enabler of the other two building blocks
* **Polymorphism:** concept of having methods with the same name exhibit different behavior. Ability of an object to behave in a\
different/multiple ways depending on the situation.
  - The method name is the same but the implementation depends on the variable parsed during runtime.
  - For example, a custom class Shape.java has several version of the method `getArea()`. This method has different implementations for\
    circle, rectangle, e.t.c. Hence, the version of the method `getArea` that will be implemented will depend on the runtime variable passed to it.
  - Another example, Employee.java class that has several version of `getPay()` method. However, the actual implementation depends on\
    the argument passed to the derived class.

* **Encapsulation:** To have a _single object_ hold _data_ and _behavior_. It means every class has its state internally. It has to do with composition than inheritance. 
  - A _base class_ that abstracts away many features & methods used by other derives classes. This implies the base class is a unit of abstraction that **encapsulates** all common functionalities. 
  - It emphasizes that the data associated with an object of a class should be hidden within the class and should only be accessed using the method of the class.
  - Hence, it implies the state of an object should not be split around but should be contained within its class.

* The main advantage of the three concepts...easy to maintain and reuse code, faster prototyping and development, ease of catching and fixing bugs. 


**(D) Overriding/Extending Inherited Methods/Implementations of java.lang.Object**

**An example of how other classes can override the inherited methods from java.lang.Objects or any other object**

* As we have seen earlier, the default implementation of `equals()` only checks whether two objects reside in same memory location but\
doesn't check the contents of the objects. Hence, `equals()`  returns **true** ONLY if two objects shares the same memory location. 
* In this example. The three instances of `String` **doesn't** share same memory location but yet comparison with `equals()` evaluates to **true.**
* This behavior is to due the fact that `String` object has _overridden_ the implementation of the built-in  `equals()` method associated with `java.lang.Object`.
   - In the new implementation, the methods `equals()` and `hashCode()` is overridden such that **element-wise comparison** is obtained\
     instead of memory location comparison. String objects with the same content has same hashCode 
   - However, and once again, they dont share the same memory location. This is shown by `System.identityHashCode()`.
```java
public class Main{
    public static void main(String[] args){
    
        String string1 = new String("Apple");
        
        String string2 = new String("Apple");
        
        String string3 = "Apple";
    
        //The check of the hashCode shows they have same hashCode
        System.out.println("hashCode for String1 "+string1.hashCode());
        System.out.println("hashCode for String2 "+string2.hashCode());
        System.out.println("hashCode for String3 "+string3.hashCode());
    
        //A check of the memory locations
        System.out.println("Memory location of String1 "+System.identityHashCode(string1));
        System.out.println("Memory location of String2 "+System.identityHashCode(string2));
        System.out.println("Memory location of String3 "+System.identityHashCode(string3));
    
        //Since they share different memory location. We expect the .equals() to evaluate to false
            // but since its overridden, it evaluates to true
        System.out.println(string1.equals(string2));
        
        /*
        hashCode for String1 63476538
        hashCode for String2 63476538
        hashCode for String3 63476538
        Memory location of String1 1735600054
        Memory location of String2 21685669
        Memory location of String3 2133927002
        true
         */
    }
}
```

* Another class that inherits methods/implementations from the `java.lang.Object` is the `java.util.Date`.
```java
public class Main{
    
    public static void main(String[] args){
        
        Date someDate = new Date("01/05/1808");
        
        //It inherits tpString(), getClass()...e.t.c
        System.out.println("String representation of date: "+someDate.toString());
        System.out.println("Class: "+someDate.getClass());
        System.out.println("HashCode: "+someDate.hashCode());
        
        /*
        String representation of date: Tue Jan 05 00:00:00 CET 1808
        Class: class java.util.Date
        HashCode: 934916825
         */
    }
}
```

* Another example with a custom class that has overriden the `equals()` and `hashCode()` methods. Since these two methods has been\
overriden, the `equals()` and `hasCode()` will return true and same integer respectively for objects of same class holding same data. However,
their memory location will NOT be the same if the `new` keyword is used in the instantiation of the object. 
```java
package org.example;

public class App {

    public static void main(String[] args){

       Car carOne = new Car("Tesla", "Tesla");
       Car carTwo = new Car("Tesla", "Tesla");

       System.out.println(carOne.hashCode() == carTwo.hashCode()); //true
        
       System.out.println(carOne.equals(carTwo)); //true 
        
      //memory location is NOT the same
       System.out.println( (System.identityHashCode(carOne)) == (System.identityHashCode(carTwo)) ); //false

    }
}
```


**(E) The _instanceof_ Operator, _extends_, Variable Type and Class Type**
* **The instanceof**
  - Used to check whether a particular object is an instanceof a particular class. 
  - Also helps to proof that all objects in java is an instance of the java.lang.Object class.
  - ```java
    public class Main{
        public static void main(String[] args){
    
             Book book1 = new Book();
             Book book2 = new Book();
             Date date1 = new Date ("5/6/2020");
             
             System.out.println((book1 instanceof Book)); //true
             System.out.println((date1 instanceof Date)); //true
             
             //Below will lead to Compile-time error.
             System.out.println((book1 instanceof Date));
             
             //Proof that both book1 and date1 are an instance of the java.lang.Object
             System.out.println((book1 instanceof Object)); //true
             System.out.println((date1 instanceof Object)); //true 
        }
    }
    ```

**Run-time type** versus **Compile-time type**:...e.g. methods and variables of the **derived class** can't be invoked on the **base class**.--> compile time error.

**Extends**
   - A class _Book_ inherits (`public class Book extends Object`) the Object class by default(even if `extends` keyword is not used). 
   - Assuming, another class _FictionBook_ inherits the _Book_ class(`public class FictionBook extends Books`) 
   - Consequentially, the _FictionBook_ class will inherit the _Object_ class that the _Book_ class inherits.
   - Assuming the _FictionBook_ class contains the method _getSubject()_.
   - All methods and member variables of the _Object_ and _Book_ class is available to the **right instance** of the class _FictionBook._
   - In general, all member variables(state) and methods of the **base** class is available to the **derived** class. 
   - Methods/fields of a **derived** class are only **available** to a **derived** class **instance** not a **base** class instance.
     - Hence, an instance of a **base** class can't implement/invoke a method contained in a **derived** class
     - The key to understanding the precedence of this **availability** is the concept of _variable type and object type_. 

**Variable Type VS Object Type: Down-Casting.**
  - In the object instantiation below: First _FictionBook_ is the **type of variable** and Second _FictionBook_ is **type of object**.
    - `FictionBook bookX = new FictionBook()`
  - The class _FictionBook_ extends _Book_. _NonFictionBook_ extends _FictionBook_. The instantiation uses _Book_ as the **type of variable** while\
    the **type of object** is _NonFictionBook_. Even though class _NonFictionBook_ has the method `salesStatus()`. Due to the fact that its\
    **type of variable** is a base class(Book), it has no access to this method.
    - ```java
      public class Main{
      
        public static void main(String[] args){
      
           //No argument constructor
           Book book1 = new NonFictionBook();
           
           System.out.println(book1.getClass()); //class notebooks.NonFictionBook
           
           book1.salesStatus(); //NOT permitted 
           
           ( (NonFictionBook) book1).salesStatus(); //DOWN-CASTING to a lower class to make the method accessible.
        }
      }
       ```
    - The print statement shows that _book1_ is an **object type** of the class _NonFictionBook_. Hence, we would expect it to be able to access methods within its class. 
    - Statement `book1.salesStatus()` is NOT permitted cuz the **variable type** (Book) in its instantiation is a **parent class** to the class that contains `salesStatus()`.
    - This shows the importance of **variable type** and its distinction from the **object type**. 
  - To make _methods_ of **derived** class available to objected constructed by a **base** variable type we perform **_down-cast_**. 
    - `((NonFictionBook) book1).salesStatus();`
    - We convert the **VARIABLE TYPE** from  **base** class to a **derived** class. (i.e. we convert the _variable type_ _Book_ to type _NonFictionBook_)
  - **NOTES:** 
    - The consideration when down-casting/up-casting is the change associated with **variable type**. 
    - The actual **class type** is NOT changed and _class consistency_ must be maintained in **down-casting** operation.
    - `Book book1 = new NonFictionBook();`
       - book1: Variable type = Book. Class/object type = NonFictionBook. 
       - Hence, methods residing in NonFiction is not available to it. Because the _variable name/types_ determines the availability of methods.
    - `( (NonFictionBook) book1).salesStatus();`
       - A **down-casting** operation changes the **variable type** in order to make the _methods_ in _derived_ class available for use.
    - `Object book2 = new NonFictionBook();`
       - A _variable_ (book2) of **base** class (Object) **variable type** can hold a **derived** class type (NonFictionBook)
    - class **down-casting** is not safe and should rarely be used. 

* **Inherited class: Factor of shallow copies, variable type and object type**
  - ```java
    public class Main{
        public static void main(String[] args){
                 
             NonFictionBook book1 = new NonFictionBook();
    
             Book book2 = new Book();
             
             Book book4 = book1;
             
             System.out.println(book4.getClass()); //class notebooks.NonFictionBook
             
             book4.salesStatus();//NOT permitted cuz the variable name is of base class.
             
        }
    }
     ```
  - book4 is shallow copy of book1. The **variable type** associated with its direct instance is Book. 
  - The **Variable type** associated with the direct instantiation of the shallow copy will take precedence.
  - Hence, methods in NonFictionBook is not available to it even though its **class type** is still NonFiction book.
  - `NonFictionBook book5 = book2; //NOT permitted.` book2 has class type Book.
  - NOT permissible to instantiate an object with a _derived class_ **variable type** (NonFiction) while assigning it a **base class type.**
* **Access Modifiers:**
  - The _access modifier_ for fields&methods in a base class must be public in order to enable access for derived class


## Mapping Relationships in Java: Constructors & Polymorphism
**(A) Types of Constructors and their (NON) Inheritance**
- In _general_, a derived class **CAN'T** really inherit the constructor of a base class. Whenever a derived class  extends a base class, the base\
class/subclass inherits state and behavior in the form of variables and methods, **_but it does NOT inherit constructor of the base class_**.
- The base class, though inherits the fields and methods, it still needs to define its own constructor which can be chosen from the fields inherited from base class.

**(1)** The **base** class contains _only_ a **_default no-argument constructor_**:\
The **derived** class doesnt need to explicitly define it's own constructor. In fact, the derived class can be empty as shown in the example.
```java
package notebooks;
public class Book{
          
    private String bindingType = "Paperback";
      
    //A default no argument constructor
    public Book(){
         System.out.println("Default no argument constructor.");
    }
}
```
Derived class
```java
package notebooks;
public class NonFictionBook extends Book {
      
}
```
Main class
```java
public class Main{
    
    public static void main(String[] args){
      
       //Instantiation of an object with a default no argument constructor with just a print statement.
        Book book1 = new Book(); //OUTPUT-Default no argument constructor.
      
        //The NonFictionBook class is currently empty. However, it automatically inherits the constructor 
             // of the class it extends.
        NonFictionBook book2 = new NonFictionBook(); //OUTPUT-Default no argument constructor.
         
    }
      
}
```
**(2)** The base class contains ONLY **parametrized constructor** and NO **default no-args constructor** :\
The derived class need to create a constructor and pass its own parameter. The `super` keyword helps to invoke/take a parametrized\
constructor (residing in a base class) into a derived class for manipulation or replication. Call to `super` must be the first line\
in the constructor of a derived class.
```java
package notebooks;
public class Book {
    
    public String bindingType;
        
    //Parametrized constructor
    public Book(String bindingType){
        this.bindingType = bindingType;
        System.out.println("Parametrized constructor."); 
    }
      
}
```

```java
package notebooks;
public class NonFictionBook extends Books {
    
    //Parametrized constructor for the derived class.
    public NonFictionBook(String bindingType) {
        super(bindingType);
        System.out.println("Parametrized constructor from derived class.");
    }

}
```

```java
public class Main{
    public static void main(String[] args){
        
        //Instantiation of an object with an argument constructor.
        Book book1 = new Book("Soft Back");
        
        NonFictionBook book2 = new NonFictionBook("Hard Back);
                
        System.out.println(book1.bindingType); //OUTPUT-Soft Back
        System.out.println(book2.bindingType); //OUTPUT-Hard Back
    }
}
```
**(3)** In the case that the base class consists of both _default no-argument constructor_ and a _parametrized constructor_.\
If the derived class doesn't specify a parameterized constructor, the **default no-argument constructor** of the base class is invoked. For\
example, a parameterized constructor can't be passed to the derived class within the main class. Cuz only the **default no argument constructor**\
is available to it there. To pass a parameter to the derived class, a _parametrized constructor_ has to be specified for the derived class using **super**.

```java
package notebooks;
public class Book {
    //This base class contains one default no-argument and one parametrized constructor.
    public String bindingType = "Paperback";
    
    //This default no-argument constructor is overriden by the parametrized constructor. 
    public Book(){
        System.out.println("Default no argument constructor.");
    }
                
    //This parametrized constructor takes precedence. 
    public Book(String bindingType){
        this.bindingType = bindingType;
        System.out.println("Parametrized constructor.");
    }
      
}
```

```java
package notebooks;
public class NonFictionBook extends SmartSpeaker {
    
    //Default no-argument constructor is kept for safety.
    public NonFictionBook(){
                  super();
    }
            
    //Parametrized constructor for an inherited constructor from a base class
    public NonFictionBook(String bindingType2) {
                     super(bindingType2);
    }
    
}
```
      

      
```java
public class Main{
    public static void main(String[] args){
        Book book1 = new Book("Soft Back");//Parametrized constructor.
        NonFictionBook book2 = new NonFictionBook("Hard Hard book");//Parametrized constructor.
        System.out.println(book1.bindingType); //OUTPUT-Soft Back
        System.out.println(book2.bindingType); //OUTPUT-Hard Hard boook
      }
} 
```
      
- A good practice is that in the derived class (e.g. NonFictionBook), both the no-argument and parametrized constructors is kept. This\
ensures that there is a constructor in place to match any constructor scenario from the base class.
A General example:
```java
package notebooks;
import java.time.Period;
public class AntiqueBook extends SmartSpeaker {
    
    //Derived class specific field.
    public LocalDate currentDate = LocalDate.now();
          
    int fiveStarRating;
            
    //Parametrized constructor for the derived class.
    public AntiqueBook(String title, String author, LocalDate releaseDate, String genre, 
                                 int numberOfPages, String bindingType, int fiveStarRating) 
                                 {
                              super(title, author, releaseDate, genre, numberOfPages, bindingType);
                              //Derived class specific field.
                              this.fiveStarRating = fiveStarRating; 
                                 }
                                 
    //Derived-class-specific method.
    public void ageOfBook(){
                System.out.format("The Book \"%s\" by \"%s\" with a rating of %d/5 \n", title, author, fiveStarRating);
                System.out.println("Released on: "+ releaseDate + ", Age: " + 
                        Period.between(releaseDate, currentDate).getYears()+" years old");
            
    } 
}
        
```
        
```java
package notebooks;
import java.time.LocalDate;
public class Book extends Object {
    public String title;
    public String author;
    public LocalDate releaseDate;
    //public LocalDate localDateFormatted = LocalDate.parse(releaseDate);
    public String genre;
    public int numberOfPages;
    public String bindingType;
    //Constructor
    public Book(String title, String author, LocalDate releaseDate,
                String genre, int numberOfPages, String bindingType) {
                  this.title = title;
                  this.author = author;
                  this.releaseDate = releaseDate;
                  this.genre = genre;
                  this.numberOfPages = numberOfPages;
                  this.bindingType = bindingType;
    }
    
    public String getBindingType() {
        return bindingType;
    }
              public void setBindingType(String bindingType) {
                  this.bindingType = bindingType;
              }
              public String getTitle() {
                  return title;
              }
              public void setTitle(String title) {
                  this.title = title;
              }
              public String getAuthor() {
                  return author;
              }
              public void setAuthor(String author) {
                  this.author = author;
              }
              public LocalDate getReleaseDate() {
                  return releaseDate;
              }
              public void setReleaseDate(LocalDate releaseDate) {
                  this.releaseDate = releaseDate;
              }
              public String getGenre() {
                  return genre;
              }
              public void setGenre(String genre) {
                  this.genre = genre;
              }
              public int getNumberOfPages() {
                  return numberOfPages;
              }
              public void setNumberOfPages(int numberOfPages) {
                  this.numberOfPages = numberOfPages;
              }
          @Override
          public String toString() {
          return "Book{" +
                  "title='" + title + '\'' +
                  ", author='" + author + '\'' +
                  ", releaseDate=" + releaseDate +
                  ", genre='" + genre + '\'' +
                  ", numberOfPages=" + numberOfPages +
                  ", bindingType='" + bindingType + '\'' +
                  '}';
          }
}
```
        
```java
package notebooks;
import java.time.LocalDate;
public class Practice1 {
    public static void main(String[] args){
                  
        AntiqueBook book1 = new AmazonEcho("Shola the GREAT!", "Tomas Hampl",
                                          LocalDate.of(1901,01,01),"great",5000, "extra hard", 5);
        
        book1.ageOfBook();
                  
        book1.ageOfBook();
                  
        System.out.println(book1.getBindingType());
        
        /*
        The Book "Shola the GREAT!" by "Tomas Hampl" with a rating of 5/5 
        The Book was released on: 1901-01-01 and its: 121 years old
        Binding Type: extra hard
         */
        
    }
}
```

**(B) Run-time Polymorphism**
```java
package notebooks;
public class SmartSpeaker  {
    
    private String productName = "defaultSpeaker";
    
    //Constructor
    public SmartSpeaker(String productName){
        this.productName = productName;
        //Embedded print statement to notify when a constructor is being generated.
        System.out.println("Generating base class constructor....Product name: "+getProductName());
    }
    
}
```

```java
package notebooks;
public class AmazonEcho extends SmartSpeaker {
    
       public AmazonEcho(String productName){
           super(productName);
           //Embedded print statement to notify when a constructor is being generated.
           System.out.println("Generating derived class constructor...Product name: "+getProductName());
       }
}
```


```java
package notebooks;
public class Practice1 {
    
    public static void main(String[] args){
        
          //Instantiating objects of base and derived class.
          SmartSpeaker speaker1 = new SmartSpeaker("Default speaker");
          AmazonEcho amazonspeaker = new AmazonEcho("Alexa");
          
          //The getProductName() method is defined in the base class.
          System.out.println(speaker1.getProductName());
          System.out.println(amazonspeaker.getProductName());
          
          /*
          Generating base class constructor....Product name: Default speaker
          Generating base class constructor....Product name: Alexa
          Generating derived class constructor...Product name: Alexa
          Default speaker
          Alexa
           */
    }
}
```
- The output shows that the object of the derived class passed through both the constructor of the base class and its own constructor.
- However, the instance of the derived class finally assumed the state that is specific/particular to it. 
- In case of **multiplicity**, an instance of derived class will override methods and fields of the base class if it has its own defined.\
    When both base and derive class have a method with same name&signature(identical also with parameters). If that method is invoked on an instance of
    the derived class, precedence will be given to the copy of the method residing in the derived class.\
    Hence, the copy of the method in the base class will be _**overridden**_. Adding the `@Override` annotation helps with compiling error check and code readability. 
  - This is the fundamentals of **Runtime Polymorphism**.
    - ```java
      package notebooks;
      public class SmartSpeaker  {
            private String productName = "defaultSpeaker";
            //Constructor
            public SmartSpeaker(String productName){
                    this.productName = productName;;
            }
            public String getProductName() {
                   return productName;
            }
            public void wakePhrase(){
                  System.out.println("This is a random speaker. It has no wake phrase");
            }
      }
      ```
      ```java
      package notebooks;
      public class AmazonEcho extends SmartSpeaker {
             public AmazonEcho(String productName){
                    super(productName);
             }
             @Override
             public void wakePhrase(){
                  System.out.format("Hello %s ! \n", getProductName());
             }
      }
      ```
      ```java
      package notebooks;
      public class AppleHomePod extends SmartSpeaker {
            public AppleHomePod(String productName){
                 super(productName);
            }
            @Override
            public void wakePhrase(){
                 System.out.format("Hello %s ! \n", getProductName());
            }
      }
      ```
      ```java
      package notebooks;
      public class Practice1 {
             public static void main(String[] args){
              //Instantiating objects of base and derived class.
              SmartSpeaker speaker1 = new SmartSpeaker("Default speaker");
              SmartSpeaker amazonspeaker = new AmazonEcho("Alexa");
              SmartSpeaker applespeaker = new AppleHomePod("Siri");

              speaker1.wakePhrase();
              amazonspeaker.wakePhrase();
              applespeaker.wakePhrase();
      }
      }
      ```
    - The three classes have the method (wakePhrase()) defined in them and Java knows when to invoke each method\
      based on the precedence of polymorphism.
    - **NOTE:**\
      In the _main class_, the instances are of the **variable type** of the **base class**(SmartSpeaker). Since the **variable type** determines\
      the availability of inherited methods to an object, the method had to be defined in each of the derived classes. However, this\
      creates flexibility associated with polymorphism.
    
      **Run time polymorphism** enables having a variable of the _base class type_ holding objects of the _derived class type_. Java\
      determines at runtime, the method to actually implement. 
    - This concept of knowing what to run at runtime, is also referred to as **Dynamic Method Dispatch.** 

**(C) Compile-time Polymorphism** \
Two types of compile-time polymorphism: method overloading and type conversion. 
 - **(1) Method Overloading**
   - Methods with same name but different signature and number of parameters
   - **Erasure** is used by Java to decide whether a particular method is unique, overloaded, or duplicated.
   - The _method signature_ consists of two parts: the method’s _name_ and the _parameter list_.
   - The _parameter list_ includes the _number_, _type_, and _order_ of parameters but NOT the _name_ of the parameter.
   - _Return types_ and _exception_ lists are not part of the method signature.
   - A method is unique ONLY when it has different method name & or different number of param, sequence/order of parem, types of param.
   - ```java
     package org.example;
     public class ErasureExample {
            
            public void hello(String name){}
            
            protected String hello(String firstName) throws Exception{ return "hello";}
    
            char hello(String character){ return 'A';}
           
            int hello(String lastName){ return 5;}
     }
     ```
     All four methods are considered to be the **same method**. Hence, duplicated methods are not permissible. 
     ```java
     package org.example;
     public class ErasureExample {
     
           public String hello(String name)
     
           public String hello(CharSequence name)
     }
     ```
     The two methods are considered to be different just as a result of the type of parameter.
   - Method overloading involves changing the number, type and or order of parameters in order to create different versions of a method. 
   - Method overloading form/type of compile-time polymorphism uses erasure to create several version of a method. 
   ```java
     package notebooks;
     public class Measure {
   
            //Perimeter methods with different erasure based on # of parameter(i.e. method overloading)
            public void perimeter(int a, int b){
                   System.out.println("Perimeter of a rectangle.");
                   int per = (2*(a + b));
                   System.out.println("The calculated perimeter is: "+per);
            }
   
            public void parameter (int a, int b, int c){
                   System.out.println("Perimeter of a triangle.");
                   int per = a+b+c;
                   System.out.println("The calculated perimeter is: "+per);
            }
            public void parameter (int a){
                   System.out.println("Perimeter of square.");
                   int per = 4*a;
                   System.out.println("The calculated perimeter is: "+per);
            }
   
     }
    ```
    ```java
      package notebooks;
      public class Practice1 {
             public static void main(String[] args){
   
                    // Default no-argument constructor
                    Property measure = new Property();
   
                    //Use of compile-time polymorphism to create versions of same method.
                    measure.parameter(5,6,7);
                    measure.perimeter(5,6);
                    measure.perimeter(5);
             }
      }
    ```
 - **(2) Type Conversion**\
 Similar to the method of overloading. Instead of different number of parameter, **erasure** is modified: -- by changing the type of\
the parameter. -- switching the sequence of the parameters.

 - **Input argument promotion:**
When exact argument match is not possible and passed argument can be converted to the defined argument in the method.(e.g. int/double/float)\
_type promotion_ or will occur. The less accurate (int) type is converted to a more accurate type (float/double).
_type coercion_ is NOT permitted. `int` cant be converted to `short` or `String` e.t.c
   ```java
   package notebooks;
   public class Measure {
   
          //The class contains no constructor. The default no argument constructor is invoked by java.
          public void area(int a, int b){
                 System.out.println("Perimeter of a rectangle-(int args)");
                 int area = (a * b);
                 System.out.println("The calculated perimeter is: "+area);
          }
   
          public void area (double a, double b){
                 System.out.println("Perimeter of a triangle-(double args).");
                 double area = a*b;
                 System.out.println("The calculated perimeter is: "+area);
          }
   
          public void area (float a, float b){
                 System.out.println("Perimeter of a triangle-(float args)");
                 float area = a*b;
                 System.out.println("The calculated perimeter is: "+area);
          }
   }
   ```
   ```java
   package notebooks;
   public class Practice1 {
   
         public static void main(String[] args){
         // Default no-argument constructor
         
         Property measure = new Property();
         //Use of compile-time polymorphism to create versions of same method.
         
         //All int version of the method
         measure.area(5,6);
         
         //int and double version of the method. To simulate type promotion.-int is promoted to double 
            // and matched with according method.
         measure.area(5,6.0);
         
         //int and float version of the method. To simulate type promotion- -int is promoted to 
            // float and matched with according method.
         measure.area(5, 6.0f);
         
         }
   }
   ```
   ```
   Perimeter of a rectangle-(int args)
   The calculated perimeter is: 30
   Perimeter of a triangle-(double args).
   The calculated perimeter is: 30.0
   Perimeter of a triangle-(float args)
   The calculated perimeter is: 30.0
   ```
 - **Ambiguous method invocation:**
   When method promotion is possible but there are two methods that satisfied same condition.\
   `Error: java: reference to area is ambiguous `

## Mapping Relationships in Java: Overriding Methods and Using Access Modifiers
**(A) Overriding Methods in Derived Class**
- The `super` keyword can be used NOT just within a constructor but also to invoke any arbitrary method of _base class_ within the\
_derived class_. It's generally used to achieve code reuse when used with methods of base class. 
  - Use of `@Override` overrides the printDetails() method from base class. However, it uses `super` keyword to bring in the base class\
    implementation of the method into its own implementation. 
    ```java
     //BASE CLASS
     package notebooks;
     public class Property {
          private String propertyType = "unknown";
            
          private final String projectName;
            
          private final int propertySize;
            
          //Constructor
          public Property(String propertyType, String projectName, int propertySize) {
              this.propertyType = propertyType;
              this.projectName = projectName;
              this.propertySize = propertySize;
          }
           
          //Method 
          public void printDetails(){
               System.out.format("Property {type=%s, projectName=%s, propertySize=%s} %n", 
                                                propertyType, projectName, propertySize);
          }
     }
    ```

    ```java
    package notebooks;
    //DERIVED CLASS
    public class ResidentialProperty extends Property {
    
          //Properties/fields of the derived class.
          public enum Type { //this is object type enum(enumerator)
                SINGLE_FAMILY_HOME,
                CONDO,
                TOWNHOUSE
          }
    
          private Type residentialPropertyType = Type.CONDO;
    
          private float hoaFees;
          
          /
          public ResidentialProperty(String projectName, int propertySize,Type residentialPropertyType, float hoaFees){
                 
                 //propertyType is directly modified(hard-coded) while the other two fields are invoked indirectly.
                 super("Residential", projectName, propertySize); //this applies to the constructor of the base class.
                 this.residentialPropertyType = residentialPropertyType;
                 this.hoaFees = hoaFees;
    
          }
  
          @Override
          public void printDetails(){
    
              super.printDetails();
              System.out.format("Residential Property{type=%s, HPA fees=%.1f} %n", residentialPropertyType, hoaFees);
    
          }
     }
    ```
  
    ```java
    package notebooks;
    public class Practice1 {
       public static void main(String[] args){
    
           //Instance of the derived class
           //Note how the Type is used in the instantiation.
           ResidentialProperty alpha = new ResidentialProperty("Alpha", 1200, ResidentialProperty.Type.TOWNHOME, 4000);
    
           //Invoking a method from the base class on an object of the base class.
           alpha.printDetails();
    
           //Instance of the base class
           Property beta = new Property("plot", "beta", 2000);
           beta.printDetails();
       }
    }
    ```
    ```
    Property {type=Residential, projectName=Alpha, propertySize=1200}
    Residential Property{type=TOWNHOME, HPA fees=4000.0}
    Property {type=plot, projectName=beta, propertySize=2000}
    ```

**(B) Static Methods Revisited** 
* Static method is shared by all instances of particular class.
* Static methods **CAN'T** be overridden. 
* Static methods are not governed by _dynamic method dispatch_ (run-time polymorphism).
* The recommendation is to access static methods via _class reference_ and not _object reference_. 
* If two different objects are contained in an instance level access and class level access, the class level will prevail.
 - `alpha.getPropertyType()`   instance level access 
 - `ResidentialProperty.getPropertyType()`  class level access

**(C) The _final_ Keyword Revisited**
* Final has subtle difference when used in fields, methods and class. 

**final Field/Variable**
 - When a field is set as `final` within its class definition, a setter method can't be declared.
 - A field that is set as `final` and not yet initialized can be changed in the constructor of its class definition.
 - Fields marked as final can be instantiated:
   - @ declaration: `private final long id = Math.round(Math.random()*1000);`
   - @ the constructor: `this.id = Math.round(Math.random()*1000);`
   - @ Using an _initialization code block_ with the class file:  `{id = Math.round(Math.random()*1000);}`
   - @ When instantiating an object of the class.  
 
**final Methods and Classes**
 - A method that marked as final _can't obe overridden_ in any class: `public final String getPropertyName()` can't be overridden. 
 - A class marked as final _can't be extended_ by a derived class: `public final class Property` is not extendable. 

**(D) Characterizing Abstract Class and Method**
**Abstract Class**
- Abstract classes **can't** be directly instantiated. It can only be extended into a derived class which can then be instantiated. 
- Abstract classes often contain _abstract methods_.
  ```java
  package notebooks;
  public abstract class Phone {
      private final String operatingSystem;
      private final String brandName;
      private final float basePrice;
      public Phone(String operatingSystem, String brandName, float basePrice) {
            this.operatingSystem = operatingSystem;
            this.brandName = brandName;
            this.basePrice = basePrice;
      }
      @Override
      //We Override the default toString method from the java.lang.Object
      public String toString() {
            return String.format("Operating System %s, Brand: %s. Base Price: %s ", operatingSystem, brandName, basePrice);
      }
  }
  ```
  ```java
  package notebooks;
  public class IPhone extends Phone  {
         private boolean airpods;
         public IPhone(float basePrice, boolean airpods){
                super("iOS", "iPhone 13", basePrice);
                this.airpods = airpods;
         }
        @Override
        public String toString(){
               String superString = super.toString();
               return String.format("%s, Airpods:%s", superString, airpods);
        }
  }
  ```
  **NOTE:**
    - In the IPhone class, we override the toString method from base class (Phone) and parse it as variable.
    - Direct instantiation of abstract class is NOT permitted.`Phone nokia = new Phone("Symbian", "Nokia", 3000);`
    -  However, we can instantiate any object that extends the abstract class. It will be an instance of the abstract class. 
  ```java
  package notebooks;
  public class Practice1 {
         public static void main(String[] args){
                IPhone iphone = new IPhone(5000, true);
                System.out.println(iphone);
                System.out.println(iphone instanceof Phone); //true
                System.out.println(iphone instanceof IPhone); //true
         }
  }
  ```
**Abstract Method**: 
- a method without implementation--> `public abstract double computeTotalPrice();`
- Abstract method can only exist in an abstract class. 
- When a derived class extends an _abstracts class_ which contains an _abstract method_ ->
  - the derived class must be marked as abstract as well
  - the derived must implement that abstract method
  ```java
  package notebooks;
  public abstract class Phone {
         private final String operatingSystem;
         private final String brandName;
         private final float basePrice;
         public Phone(String operatingSystem, String brandName, float basePrice) {
            this.operatingSystem = operatingSystem;
            this.brandName = brandName;
            this.basePrice = basePrice;
         }
         protected float getBasePrice(){
            return basePrice;
         }
         //ABSTRACT METHOD WITHIN THE BASE CLASS
         public abstract double computeTotalPrice();
         @Override
         //We Override the default toString method from the java.lang.Object
         public String toString() {
            return String.format("Operating System %s, Brand: %s. Base Price: %s ", operatingSystem, brandName, basePrice);
         }
  }
  ```
  ```java
  package notebooks;
  public class IPhone extends Phone  {
         private boolean airpods;
         public IPhone(float basePrice, boolean airpods){
                super("iOS", "iPhone 13", basePrice);
                this.airpods = airpods;
         }
        
        //Implementation of the abstract method defined within the base class
        @Override
        public double computeTotalPrice(){
               return getBasePrice() + getBasePrice() * 0.05;
        }
  }
  ```
  ```java
  package notebooks;
  public class Practice1 {
  public static void main(String[] args){
         IPhone iphone = new IPhone(5000, true);
         System.out.println("The total price is: "+iphone.computeTotalPrice());
  }
  }
  ```

**(E) Access Modifiers Revisited**
- **public:** 
  - accessible to any object outside its class 
  - accessible to any object outside the package. No limitation. 
- **private:** 
  - accessible only within the defining class. NOT accessible to any other class including derived classes.
  - can't be accessed even by an object of the class in the main or any other class. 
  - can't be accessed by a derived class of a base class. 
  - a **_private constructors_** WON'T permit instantiation of an object of the class or use of **_super_** outside the class.
    - **Factory Patter** and **Singleton Pattern** are an example usage of **private constructors**.
      - Factory pattern has a private constructor. 
      - A static method e.g. `getPropertyInstance()` is invoked as an indirect constructor. 
      - The static method takes a return type of the class using `new`, and same input argument as the constructor.
      - The static method is known as _factor method_. 
      ```java
      public class Property{
             public String propertyType;
             private long id;
             private String projectName;
             private int projectSize;
      
             //PRIVATE CONSTRUCTOR.
             private Property(String propertyType, String projectName, int propertySize){
                     this.id = Math.round(Math.random()*1000);
                     this.propertyType = propertyType;
                     this.projectName = projectName;
                     this.projectSize = projectSize;
             }
      
             //A STATIC METHOD THAT IS USED AS INDIRECT CONSTRUCTOR.
             public static Property getPropertyInstance(String propertyType, String projectName, int propertySize){
                    return new Property(propertyType,projectName, propertySize);
             }
      }
      ```
      - The method is used in the main class to construct and instantiate an object of the class.
      ```java
      public class Main{
           public static void main(String[] args){
                 Property property1 = Property.getPropertyInstance("Residential", "Alpha", 500);
           }
      }
      ```
- **protected:** 
  - in-between public and private. 
  - within same package:  protected == public.
  - outside the package: accessible by a _derived class_ that imports (using the FQCN) and extends the _base class_. 
  - the point directly above is valid for the main class of another package. 
  - all points above is a valid for **protected constructor**.  
  
- **package-private:** 
  - default access modifier. Used when no access modifier is specified. 
  - within same package: public 
  - outside the package: private(absolute), _even when its imports and extends the base class._ 
 

## Mapping Relationships in Java: Working with Interfaces & Class Loaders
**(A) Interface Methods**
**Interface:** 
- an **_abstract class_** with its methods set as abstract and public. Means by which interaction or comm. is achieved. vastly used in APIs.
- multiple inheritances of interfaces is recommended while multiple inheritance of other base classes is disallowed. 
- unlike classes, the default **access modifiers** of _all_ methods inside an interface is public.
- interfaces are not meant to be instantiated. The earlier characteristic points about _abstract classes_ is valid:
  - the methods specified in an abstract class must be implemented by a _base class_. Hence, we used a class to `implement` an **interface.**
  - unlike `extends` keyword, `implements` can have multiple arguments. Multiple interfaces can be implemented by a class. 
  - the class that implements an interface is `instanceof` of that interface.
  ```java
  package notebooks;
  public interface Automobile {
         String getMake();
         String getModel();
         Double getPrice();
  }
  ```
  
  ```java
  package notebooks;
  public class Sedan implements Automobile {
         private String make;
         private String model;
         private Double price;
  
         public Sedan (String make, String model, Double price){
            this.make = make;
            this.model = model;
            this.price = price;
         }

        //Implementation of methods inside the interface.
        @Override
        public String getMake() {
            return make;
        }

        @Override
        public String getModel() {
            return model;
        }

        @Override
        public Double getPrice() {
           return price;
        }
  }

  ```
  - An object can have a **variable type** of the interface and a **class type** of the class that implements the interface.
  - This is highly-recommended as it replicates the concept of **run-time polymorphism** and is the basis of **interface-driven** programming.
  ```java
  package notebooks;
  public class Practice1 {
    public static void main(String[] args){
       //----an object with variable type of the interface and class type of the base class.---
       Automobile car1 = new Lamborghini("Honda", "Civic", 50000.0);

          System.out.println("Sedan make: "+ car1.getMake());
          System.out.println("Sedan model :"+car1.getModel());

          System.out.println("Is car1 an instance of class Sedan : " + (car1 instanceof Lamborghini));//True
          System.out.println("Is car1 an instance of interface Automobile : " + (car1 instanceof Automobile));//True
    }
  }
  ```
  - A class that implements an interface must implements **_ALL_** the methods defined within the interface. 
  - The access modifiers for any of the class defined in the interface can't be changed from the default public by the implementing class.\
    Not specifying the access modifier in the implementing class will make it private-protected. It is strictly public
  NOTE on 

**(B) Interface Fields and Hierarchy of Interfaces**
* **Member Variables**
- Member variables of an interface is by default **public**, **static**, **final**,
- Member variables in an interface can't be reassigned. 
- Interface member variables can be access via _instance reference, object reference_, as well as interface reference.
* **Hierarchy of Interfaces**
- An interface can `extends` another interface
- If a class `implements` an interface that `extends` a different interface, that class must implement **ALL** methods in both interfaces.\
  However, the derived interface can be empty and not define any abstract method or field.
- Java permits `implements` of more than one interfaces. Java doesn't permit `extends` of one more than one class. 
- When two interfaces have the same methods, it is sufficient to implement just one in the implementing class.\
  Java uses **erasure**(method name, parameter characteristics) along with return type to determine the uniqueness of the method of an interface.
```java
package notebooks;

public interface Automobile {
    String getMake();
    String getModel();
    Double getPrice();
}
```

```java
package notebooks;
public interface SportsCar extends Automobile{
    //Field of an interface
    char grade ='A';
    //Method of an interface.
    float getTopSpeed();
}
```

```java
package notebooks;

public class Lamborghini implements Automobile, SportsCar{
    //Method of the base interface
   private String make;
   private String model;
   private Double price;

   //Method of the derived interface
   private float topSpeed;

   public Lamborghini(String model, Double price, float topSpeed){
       this.make = "Lamborghini";
       this.model = model;
       this.price = price;
       this.topSpeed = topSpeed;
   }

    //Implementation of methods inside the interface.
    @Override
    public String getMake() {
        return make;
    }

    @Override
    public String getModel() {
        return model;
    }

    @Override
    public Double getPrice() {
        return price;
    }

    @Override
    public float getTopSpeed() {
        return topSpeed;
    }
    
    public char getGrade(){
       return grade;
    }

    @Override
    public String toString() {
        return "Lamborghini{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", price=" + price +
                ", topSpeed=" + topSpeed +
                '}';
    }
}
```

```java
package notebooks;
public class Main {
    public static void main (String [] args){
        Automobile lambo = new Lamborghini("Urus", 100000.0, 180);

        System.out.println(lambo);
        //Accessing variables of an interface via interface.
        System.out.println(SportsCar.grade); //A
        //Accessing variables of an interface via class
        System.out.println(Lamborghini.grade); //A

        //Checking that the object belongs to both interfaces and its class.
        System.out.println((lambo instanceof Automobile)); //true
        System.out.println((lambo instanceof SportsCar));  //true
        System.out.println((lambo instanceof Lamborghini)); //true
    }
}
```

**Interface Method Default Implementations**
- When an interface has been implemented by a class, adding a new method to that interface requires modification of all associated classes. 
- Adding a default method implementation to the new method, allows any class that has old implementation of the interface to automatically get updated. 
```java
package notebooks;

import java.util.Calendar;

public interface Automobile {
    String getMake();
    String getModel();
    Double getPrice();
    
    //NEW METHOD AFTER THIS INSTANCE HAS BEEN IMPLEMENTED BY SOME CLASS.
    static String getFormattedCalenderString(){
        Calendar now = Calendar.getInstance();
        return String.format("%s-%s-%s", now.get(Calendar.DAY_OF_MONTH), now.get(Calendar.MONTH), now.get(Calendar.YEAR));
    }
    default String getReleaseDate(){
        return getFormattedCalenderString();
    }
}
```  
 - we define a new method in the interface (getFormattedCalendarString()) and then
 - define a method with a `default` modifier (getReleaseDate())
 - the(actual method that will be implemented by any class that implements the interface) to act as a wrapper
 - The new default `getReleaseDate()` method is available to any class that had implemented the interface prior and post addition of the class. 


```java
public class Main{
   public static void main (String [] args) {
            String str= "Java Novice Lab";
            char lch= str.charAt(str.length()-3);
            System.out.println("The character is :"+lch); // L
    }}
```

**(C) Defining Variables of Interface Types**
- Once again, direct instantiation of an interface is not permissible. However, we can instantiate a class which implements an\
interface and use a _variable type_ of _interface_. It's then possible to use the methods of the interface.
- The `getReleaseDate()` method is a default method of interface _Automobile_. This method was defined after the class _Lamborghini_\
had implemented the interface. Hence, the class _Lamborghini_ doesn't have this method. However, by using the _variable type_ of\
interface(Automobile) and class type of _Lamborghini_, we can have access to this method. 
  ```java
  public class Main {
    public static void main (String [] args) {
  
            Automobile huracan = new Lamborghini("Huracan", 80000.0, 320.0f);
            System.out.println(huracan);//Lamborghini{make='Lamborghini', model='Huracan', price=80000.0, topSpeed=320.0}
            System.out.println(huracan.getReleaseDate()); //26-5-2022
  
    }
  }
  ```
**(D) Class Loaders**
**Hierarchy of Class Loaders**
- Java codes in .java files are compiled into byte codes in .class files. At run time, the byte codes in the .class file are made\
available to the Java run time. Class loaders, a component that abstracts the java run time from the .class files.
- java.lang.ClassLoader is an abstract class which is an object responsible for loading classes. Every object as a reference to\
the class loader that defined it. 
 ```java
  public class Main {
    public static void main (String [] args) {
        System.out.println("--System or Application class loader--");
        System.out.println("Employee class loader: "+Employee.class.getClassLoader());
        
        /*
        --System or Application class loader--
        Employee class loader: sun.misc.Launcher$AppClassLoader@232204a1
         */
    }
  }
 ```
- The output show that Employee class was loaded by some class loader of the type:sun.misc.Launcher$AppClassLoader@232204a1.\
It shows us that we are using an **app class loader.** It is the lowest level of class loaders. User defined classes are loaded\
  by application class loaders (**AppClassLoader**).
- **platform class loaders** are used to load java libraries and not the java core libraries.
- **bootstrap class loaders** are used to load core java functionalities.
- In essence, when loading a class we must use the class loader that corresponds to it according to the hierarchy. e.g platform class loader\
shouldn't be used to load a user-defined class loader and vice-versa. 

**Loading an external classes** \
An external class can be loaded/imported into a current project in intellij: File-->Project Structure-->Modules-->Dependencies\
After manually importing the jar(e.g. shola201.jar) file containing external classes into intellij. It can be included in current code as:
- _Note:_ `org.shola` is the name of a package within the imported jar file, shola201.jar. While JSONObeject is the class.
  ```java
  package notebooks;
  import org.shola.JSONObject; // .
  public class Main {
    public static void main(String[] args) {
        System.out.println("--System or Application class loader--");
        System.out.println("External class loading: " + JSONObject.class.getClassLoader());
    }
  }
  ```
**Manually Loading Classes**
- It is permissible to define an object of `ClassLoader` variable type. This object can then be sequenced to load different classes hierarchically\
using the `getParent()`, `getClassLoader()`, `loadClass()` methods.
- We use the `getParent()` to convert an application class loader object into a platform class loader.
 ```java
  package notebooks;
  public class Main {
  public static void main (String [] args) throws ClassNotFoundException {

    System.out.println("--Application class loaders--");
    ClassLoader applicationClassLoader = Employee.class.getClassLoader();
    System.out.println("Result(using application class loader): " + applicationClassLoader);

    System.out.println("--Platform class loaders from an app class loader object--");
    //Converting an application class loader into a platform class loader
    ClassLoader platformClassLoader = applicationClassLoader.getParent();
    System.out.println("Platform class loader: " + applicationClassLoader);


    System.out.println("--Loading a platform class.--");
    Class<?> somePlatformClass = platformClassLoader.loadClass("java.sql.ResultSet");
    System.out.println("Loading a platform class named: " + somePlatformClass);
  }
  }
  ```
  ```
   --Application class loaders--
   Result(using application class loader): sun.misc.Launcher$AppClassLoader@232204a1
   --Platform class loaders from an app class loader object--
   Platform class loader: sun.misc.Launcher$AppClassLoader@232204a1
   --Loading a platform class.--
   Loading a platform class named: interface java.sql.ResultSet
  ```
- A jar file was downloaded (**json-2020.jar**) and loaded into intellij. This file was then manually\
loaded without the import statement as shown below:
  - First we define the class loaders, then we use them to load the external class from a library.
   ```java
  package notebooks;
  public class Main {
  public static void main (String [] args) throws ClassNotFoundException {

        //Definition of the class loaders to be later used.
        ClassLoader applicationClassLoader = Employee.class.getClassLoader();
        ClassLoader platformClassLoader = applicationClassLoader.getParent();

        System.out.println("--Loading some external library using platform class loader--");
        //Using the defined class loaders to load a class.

        Class<?> someApplicationClass = applicationClassLoader.loadClass("org.json.JSONObject");
        
        System.out.println("Loaded External Class: " + someApplicationClass);
        System.out.println("Loaded External Class: " + someApplicationClass.getName());
        System.out.println("Loaded External Class: " + someApplicationClass.getSimpleName());

    }
    }
  
   ```
   ```
   --Loading some external library using platform class loader--
   Loaded External Class: class org.json.JSONObject
   Loaded External Class: org.json.JSONObject
   Loaded External Class: JSONObject
   ```
<img src="\images\IntelliJ-ClassLoading.png">

**Creating Custom Class Loaders**
**Loading CLasses Using a Custom Class Loader**



## Interactive Java & JShell: Writing Java Programs with the Interactive JShell
**(A) Running Commands and Snippets on JShell**
- Allows the instant and iterative running of chunks or snippets of java codes to get immediate result and catch error instantaneously.\
  It provides a REPL environment for java dev...i.e. Read Evaluate Print Loop
- Full list of commands `/help`
- **Scratch variables** are created whenever jshell evaluates an expression and a variable wasnt provided to store the result of the evaluation. 
  ```
   jshell> int i = 10
   jshell> int j = 20
   jshell> i + j 
   $4 ==> 30      //$4 is the Scracth variable created by jshell.
  ``` 
  Accessing scratch variable
  ```
   jshell>$4
   30
  ```
**(B) Defining and Calling Functions in JShell**  
Function definitions:
```
jshell> int returnSum(int x, int y){
   ...>     return x + y;}
|  created method returnSum(int,int)
jshell>
```
Function calls:
```
jshell> returnSum(5,6)
$2 ==> 11
```
* List of all variables that has been defined including the scratch variables:`/vars`
* List of all entire executions during the current jshell session:`/list`
* List of all methods/function defined during the current jshell sessions:`/methods`

**(C) Feedback Modes in JShell**
- There are four feedback modes supported by JShell
- Normal Mode: the default. `set feedback normal`
- Silent Mode: `/set feedback silent`. it switches off instant feedback and requires System.out...to printout return values of any command operations. 
- Verbose Mode: `set feedback verbose` most detailed of the feedback modes in JShell. 
- Concise Mode: feedback at a level less than the normal mode but higher than the silent mode. `set feedback concise`. 