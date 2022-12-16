# Exercise One
Write a Java program to add two binary numbers.
### Solution

```java
package simplepracticeclasses;

import java.util.*;

public class AppMain {
    public static void main(String[] args) {
        int bina1 = 100010;
        int bina2 = 110010;
        BinaryAdder(bina1, bina2);  //[1, 0, 1, 0, 1, 0, 0]
    }


    public static Integer[] BinaryAdder(int bina1, int bina2) {

        //Convert the input int (binaries) to String
        String bina1ToString = Integer.toString(bina1);
        String bina2ToString = Integer.toString(bina2);

        //Initialize the arrays that will hold the values of the binaries in integer type.
        Integer[] bina1Array = new Integer[bina1ToString.length()];
        Integer[] bina2Array = new Integer[bina2ToString.length()];

        //Convert the Strings to Integer Array
        for (int i = 0; i < bina1ToString.length(); i++) {
            //Conversion of the entire integer
            bina1Array[i] = Integer.parseInt(String.valueOf(bina1ToString.charAt(i)));
        }
        for (int i = 0; i < bina2ToString.length(); i++) {
            bina2Array[i] = Integer.parseInt(String.valueOf(String.valueOf(bina2ToString).charAt(i)));
        }

        //An array that will hold the final result. Its length is the max of the input binaries + 1
        int maxLengthOfResult = bina1ToString.length();
        Integer[] resultHolderArray = new Integer[maxLengthOfResult + 1];

        //An integer that holds the number to use to probe the two 
        int numToPlugIntoArray = maxLengthOfResult - 1;

        //Holding the number to carryover if binary value is 1+1
        int carryOver = 0;

        //An iterating values to be used to stop the while loop
        int lengthChecker = 0;

        while (maxLengthOfResult > lengthChecker) {

            if (bina1Array[numToPlugIntoArray] + bina2Array[numToPlugIntoArray] + carryOver == 1) {
                resultHolderArray[numToPlugIntoArray + 1] = 1;
                carryOver = 0;
            } else if (bina1Array[numToPlugIntoArray] + bina2Array[numToPlugIntoArray] + carryOver == 0) {
                resultHolderArray[numToPlugIntoArray + 1] = 0;
                carryOver = 0;
            } else if (resultHolderArray[0] == null && bina1Array[numToPlugIntoArray] + bina2Array[numToPlugIntoArray] + carryOver == 2) {
                resultHolderArray[numToPlugIntoArray + 1] = 0;
                resultHolderArray[0] = 1;
                carryOver = 0;
            } else if (resultHolderArray[0] == null && bina1Array[numToPlugIntoArray] + bina2Array[numToPlugIntoArray] + carryOver == 1) {
                resultHolderArray[numToPlugIntoArray + 1] = 1;
                resultHolderArray[0] = 0;
                carryOver = 0;
            } else if (resultHolderArray[0] == null && bina1Array[numToPlugIntoArray] + bina2Array[numToPlugIntoArray] + carryOver == 0) {
                resultHolderArray[numToPlugIntoArray + 1] = 0;
                resultHolderArray[0] = 0;
                carryOver = 0;
            }

            if (bina1Array[numToPlugIntoArray] + bina2Array[numToPlugIntoArray] + carryOver == 2) {
                resultHolderArray[numToPlugIntoArray + 1] = 0;
                carryOver = 1;
            }

            numToPlugIntoArray = numToPlugIntoArray - 1;
            lengthChecker = lengthChecker + 1;

        }

        System.out.println(Arrays.toString(resultHolderArray));
        return resultHolderArray;

    }


}



```