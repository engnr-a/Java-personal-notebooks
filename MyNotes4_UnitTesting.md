# Course 4--Unit Testing
### Table of Content
| #   | Title                                                                     |
|-----|---------------------------------------------------------------------------|
| 1   | Unit Testing: An Introduction to the JUnit Framework                      |
| 2   | Unit Testing: Assertions & Assumptions in JUnit                           |
| 3   | Unit Testing: Advanced Annotations in JUnit                               |
| 4   | Unit Testing: Parameterized JUnit Tests                                   |
| 5   | Unit Testing: Executing JUnit Tests                                       |
| 6   | Unit Testing with Mocks: Getting Started with Mockito                     |
| 7   | Unit Testing with Mocks: Creating Mocks & Verifying Behavior              |
| 8   | Unit Testing with Mocks: Mocking Exceptions & Using Spies                 |
| 9   | Final Exam: Unit Testing                                                  |
| 10  | Laboratory                                                                |

### Reference: JUnit Annotations and Methods
**Annotations:**

| Annotations            | Remark                                                                                 |
|------------------------|----------------------------------------------------------------------------------------|
| @Test                  | Marks a method as a test case                                                          |
| @BeforeEach            | The marked method will run before each test case                                       |
| @AfterEach             | The marked method will run before each test case                                       |
|                        |                                                                                        |
|                        |                                                                                        |
|                        |                                                                                        |
|                        |                                                                                        |
| @RepeatedTest()        | Used to repeat a test case multiple times                                              |
| **@ParameterizedTest** | Annotates a parameterized test case                                                    |
| @ValueSource()         | Used with @ParameterizedTest to parse the argument value(s) to the test case           |
| @NullSource            | Used with @ParameterizedTest to parse null value to a parameterized test case          |
| @CsvSource()           | Used with @ParameterizedTest to parse hardcoded CSV parameters to a test case          |
| @CsvFileSource         | Used with @ParameterizedTest to parse CSV values from a CSV file to a test case        |
| @MethodSource          | Used with @ParameterizedTest to parse parameters from a method invocation to test case |
|                        |                                                                                        |
|                        |                                                                                        |
|                        |                                                                                        |

**Methods:**

| Methods      | Remark |
|--------------|--------|
| assertTrue   |        |
| assertFalse  |        |
| assertEquals |        |
| assertNull   |        |
|              |        |


## 1: Unit Testing: An Introduction to the JUnit Framework 
JUnit is used for the automation of testing units within an application. Tests are based on a set of specified parameters to ensure\
that the software behaves in line with the expectations and requirements. \
Software testing is implemented by means of collection of **test cases**. A test case is the overall definition of a test which includes\
testing procedure, input arguments supplied to the test procedure, expected results/output, as well as conditions under which the test should be run\
and the exact unit of application in which the test is meant for. A test case defines what should be tested in the application, under what condition\
the test should run, and how the test should be carried out.\
While the focus of the course is Unit testing, there is also integration testing, system testing, and acceptance testing.\
Unit tests are used for units of applications such as functions, modules of an app. e.t.c. These units are tested out individually and not collectively.\
Unit test is meant to model the behavior of the unit(function, module) under test.\
Integration testing involves testing a number of units of an application together as a group. It's useful in testing the communication between two or more\
units within an application. \
**(A) JUnit Intro** \
Open source unit testing framework. The tests are meant to be written in a way that makes them executable repeatedly and automatically.
- Junit have hosts of interfaces that includes methods that allows:
  - the comparison of actual and expected values   
  - setting test conditions for test execution
  - supplying parameter to test cases
- JUnit has built-in test engine which is able to automatically discover and execute tests, and provides the results of the tests.
- Test cases are Java codes which are marked as test case. Example of built-in methods include the assert statement. 
- Updates in **JUnit5** (the version used in this course):
1. lambda functions
2. Three modules to be added as dependencies: JUnit **Platform**(used to launch the testing frameworks on the JVM), JUnit **Jupiter**(includes all \
the methods, classes, and annotation needed to define test cases), JUnit **Vintage**(provides backward compatibility for methods and annotations of \
older version of JUnit older). Hence, while only one dependency is needed for junit 4, we have to add these three dependencies to the\
project pom file if the JUnit 5 is the target version. (see dependency section below)
3. difference in annotation from previous versions
4. several new annotations
5. parameterized test using the dependency **junit-jupiter-params**
6. simplification of concurrent test execution
```xml
<dependencies>
    <dependency>
      <groupId>org.junit.platform</groupId>
      <artifactId>junit-platform-launcher</artifactId>
      <version>1.7.1</version>
      <scope>test</scope>
    </dependency>
  
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-engine</artifactId>
      <version>5.7.1</version>
    </dependency>
  
    <dependency>
      <groupId>org.junit.vintage</groupId>
      <artifactId>junit-vintage-engine</artifactId>
      <version>5.7.1</version>
    </dependency>
  
    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter-params</artifactId>
      <version>5.7.1</version>
    </dependency>
</dependencies>
```
**NOTE:** To avoid conflict between JUnit5 and older versions, avoid (if possible) adding junit-vintage-engine as dependency.\
**(B) Testing with JUnit**\
The typical project structure showing the relation between the src/main and src/test paths is shown below:\
<img src="\images\testProject2.png"> \
An important criteria when using the `@Test` annotation to define a **test case** is that the method must be `public void`.(for older version)\
or void (for JUnit5).
<img src="\images\exampleTest.png"> \
**1. Simple Assertion:**
```java
package com.shola;

import com.shola.AccountDetail.AccountType;
import org.junit.Test; //this is an annotation that marks a particular method in the class a JUnit Test.
import static org.junit.jupiter.api.Assertions.assertTrue; //a method that enables assertion

import org.junit.*;
public class AccountDetailTest {
    AccountDetail accountDetail = new AccountDetail("Anita", 2222, 007, 500.4D, AccountType.COMPANY);
    @Test
    public void validateName(){
        assertTrue(accountDetail.getName().matches("[a-zA-Z]*$")); //PASS
    }

    @Test
    public void validateAccountBalance(){
        assertTrue(accountDetail.getBalance().equals(5000)); //FAIL
    }

    @Test
    public void validateAccountBalance2(){
        assertTrue(accountDetail.getBalance() >=0); //PASS
    }
}
```
Executing tests with maven command.(from the root directory of the project): `mvn test` \

**2. BeforeEach and AfterEach Annotations:**\
The previous test only tests a single parameter. A common case is the testing of methods. BeforeEach and AfterEach helps to avoid repetition\
of codes and helps in setting a constant reference/state(e.g. of an object of a class) for each and all test cases within a test class. \
- The class to be tested.
```java
package com.shola;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDetail {
    private String name;
    private Integer accountNumber;
    private Integer customerID;
    private Double balance;
    enum AccountType{PERSONAL, COMPANY, GOVERNMENT, NGO}
    private AccountType accountType;

    public void deposit(Double depositAmount){
        balance = balance + depositAmount;
    }

    public boolean withdrawal(Double withdrawalAmount){
        if(balance > withdrawalAmount){
            balance = balance - withdrawalAmount;
            return true;
        }else{
            System.err.println("Insufficient Funds.");
            return false;
        }
    }
}
```
- Test: Any method annotated with AfterEach/BeforeEach will be executed after/before each test method/test case within the test class.
```java
package com.shola;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import com.shola.AccountDetail.AccountType;
import org.junit.jupiter.api.Test;

public class AccountDetailTest {
    AccountDetail accountDetail;
    @BeforeEach
    //The method below will be run before each test case/method
    public void initEach(){
        accountDetail = new AccountDetail("John Dick", 5555, 22,50_000D, AccountType.COMPANY);
        System.out.println("@BeforeEach block has been executed");
        System.out.println("account balance: "+accountDetail.getBalance());
    }

    @Test
    public void depositTest() throws NullPointerException {
        accountDetail.deposit(50_000D);
        System.out.println("@Test block for deposit has been executed !");
    }

    @Test
    public void withdrawalTest() throws NullPointerException{
        accountDetail.withdrawal(20_000D);
        System.out.println("@Test block for withdrawal has been executed !");
    }

    @AfterEach
    void balance(){
        System.out.println("@AfterEach has been executed");
        System.out.println("account balance : "+accountDetail.getBalance());
    }
    //All test will pass since there is no assertion in the test cases
    /*
    @BeforeEach block has been executed
    account balance: 55000.0
    @Test block for withdrawal has been executed !
    @AfterEach has been executed
    account balance : 35000.0
    @BeforeEach block has been executed
    account balance: 55000.0
    @Test block for deposit has been executed !
    @AfterEach has been executed
    account balance : 60000.0
     */
}
```
**3. BeforeAll and AfterAll Annotations:**\
BeforeAll and AfterAll should be declared as static, and they are run only once throughout the entirety of the test cases in the class.
A common use case of AfterAll is to destroy any object, close resources or files created during the test run.
```java
package com.shola;

import org.junit.jupiter.api.*;
import com.shola.AccountDetail.AccountType;

public class AccountDetailTest {
  static AccountDetail accountDetail;

  @BeforeAll
  static void initAlls() {
    System.out.println("---Starting the test class.---");

  }

  @BeforeEach
  //The method below will be run before each test case/method
  public void initEach() {
    accountDetail = new AccountDetail("John Dick", 5555, 22, 50_000D, AccountType.COMPANY);
    System.out.println("@BeforeEach block has been executed");
    System.out.println("account balance: " + accountDetail.getBalance());
  }

  @Test
  void depositTest() throws NullPointerException {
    accountDetail.deposit(50_000D);
    System.out.println("@Test block for deposit has been executed !");
  }

  @Test
  void withdrawalTest() throws NullPointerException {
    accountDetail.withdrawal(20_000D);
    System.out.println("@Test block for withdrawal has been executed !");
  }

  @AfterEach
  void balance() {
    System.out.println("@AfterEach has been executed");
    System.out.println("account balance : " + accountDetail.getBalance());
  }

  @AfterAll
  static void teardownAlls() {
    ;
    System.out.println("---The test class has been completed.---");
    accountDetail = null;
    System.err.println(accountDetail);
  }
    /*
    ---Starting the test class.---
    @BeforeEach block has been executed
    account balance: 50000.0
    @Test block for withdrawal has been executed !
    @AfterEach has been executed
    account balance : 30000.0
    @BeforeEach block has been executed
    account balance: 50000.0
    @Test block for deposit has been executed !
    @AfterEach has been executed
    account balance : 100000.0
    null
    ---The test class has been completed.---
     */
}
```

# 2: Unit Testing: Assertions & Assumptions in JUnit
Assertions are conditions that determines the success or failure of test cases. Assumption enables us to control the flow of execution \
in a test case. Assumptions can be used to check for conditions before executing a block of code. Hence, they serve as control structures. \
**(A) assertEquals, assertTrue, assertFalse, assertSame**
- Class under test: Employee class
```java
package com.shola;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    String firstName;
    String lastName;
    Integer id;
    Character gender;
    String role;
    Double salary;
    String type;

    public void adjustSalary(Double adjAmount){
        salary += adjAmount;
    }

}
```
- **assertEquals**
```java
package com.shola;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeTest {

    static Employee employee;
    @BeforeAll
    static void initEmployee(){
        employee = new Employee("Shola", "Tomas", 1234, 'M', "Very Senior Engineer", 50_000d, "Founder" );
    }

    @Test
    void asserTestExample(){
        employee.adjustSalary(50_000d);
        //the third argument is a message that will be included in the output of test failure scenario
        assertEquals(employee.getSalary(), 100_000D, "Sanity check of 'adjustSalary' method"); //pass
    }
}
```
- **assertTrue** and **assertFalse**
```java
package com.shola;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    static Employee employee1;
    static Employee employee2;
    @BeforeAll
    static void initEmployee(){
        employee1 = new Employee("Jarda", "Ondrej", 1234, 'M', "Very Senior Engineer", 50_000d, "Founder" );
        employee2 = new Employee("Jarda", "Jiri", 1234, 'M', "Very Senior Engineer", 50_000d, "Founder" );
    }

    @Test
    void asserTestExample1(){
        assertTrue(employee1.equals(employee2), "Equality check of two objects of same class"); //fail
    }

    @Test
    void asserTestExample2(){
        assertTrue(employee1.getSalary().equals(employee2.getSalary()), "Equality check of two fields of two objects using assertTrue"); //pass
    }

    @Test
    void asserTestExample3(){
        assertFalse(employee1.getSalary().equals(employee2.getSalary()), "Equality check of two fields of two objects using assertFalse"); //fail
    }
}
```
- **assertSame** VS **assertEquals**
```java
package com.shola;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    static Employee employee1;
    static Employee shallowCopy;
    static Employee noneShallowCopy;

    @BeforeAll
    static void initEmployee(){
        employee1 = new Employee("Jarda", "Ondrej", 1234, 'M', "Very Senior Engineer", 50_000d, "Founder" );

        shallowCopy = employee1; //a shallow opy

        noneShallowCopy = new Employee(
                shallowCopy.getFirstName(), shallowCopy.getLastName(), shallowCopy.getId(), shallowCopy.getGender(),
                shallowCopy.getRole(), shallowCopy.getSalary(), shallowCopy.getType()
        );
    }
    
    //NOTE: the difference between assertSame and assertEquals
    //shallowCopy VS original objet --> assertSame
    @Test
    void assertTest(){
        assertSame(shallowCopy, employee1, "shallowCopy VS original object using assertSame"); //pass
    }

    //noneShallowCopy VS original object -> assertEquals
    @Test
    void assertTest2(){
        assertEquals(shallowCopy, employee1, "shallowCopy VS original object using assertEquals"); //pass
    }

    //noneShallowCopy VS original objet --> assertSame
    @Test
    void assertTest3(){
        assertSame(noneShallowCopy, employee1, "noneShallowCopy VS original object using assertSame"); //fail
    }

    //noneShallowCopy VS original object -> assertEquals
    @Test
    void assertTest4(){
        assertEquals(noneShallowCopy, employee1, "noneShallowCopy VS original object using assertSame"); //pass
    }

}
```

**(B) Testing for Timeouts and Nulls Using JUnit**
- **assertTimeout** \
Modification to the method of the Employee class 
```java
package com.shola;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    String firstName;
    String lastName;
    Integer id;
    Character gender;
    String role;
    Double salary;
    String type;

    public void adjustSalary(Double adjAmount)throws InterruptedException{
        salary += adjAmount;
        Thread.sleep(600000);
    }

}
```
Test class
```java
package com.shola;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    static Employee employee1;
    static Employee shallowCopy;

    @BeforeAll
    static void initEmployee(){
        employee1 = new Employee("Jarda", "Ondrej", 1234, 'M',
                "Very Senior Engineer", 50_000d, "Founder" );
        shallowCopy = employee1;

    }

    @Test
    void assertTest(){
        assertTimeout(
                    Duration.ofSeconds(5), () ->{
                    employee1.getSalary();
                    }
                ); //pass (run time was 3ms)
    }

    //Note that this test case has two assert statement.
    @Test
    void assertTest2(){
        assertTimeout(
                    Duration.ofMillis(5), () ->{
                    assertEquals(shallowCopy, employee1); //pass (runtime was 1ms)
                    }
                );
    }

    @Test
    void assertTest3(){
        assertTimeout(
                Duration.ofMillis(5000), () ->{
                    employee1.adjustSalary(230D); //fail...check the method within the class
                }
        );
    }
    
    //Assert the time that takes all the statement to execute doesn't exceed 50 seconds
    @Test
    void assertTest4(){
        assertTimeout(
                Duration.ofSeconds(50), () ->{
                employee1.adjustSalary(2000D);
                Employee randomEmployee = new Employee();
                randomEmployee.setFirstName("Robert");
                randomEmployee.setSalary(134_000D);
                randomEmployee.adjustSalary(2000D);
                }
        ); //pass (runtime was 12sec 19ms)
    }
}
```
- **assertNull** and **assertNotNull** 
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    static Employee employee1;
    static Employee shallowCopy;

    @BeforeAll
    static void initEmployee(){
        employee1 = new Employee("Jarda", "Ondrej", 1234, 'M',
                "Very Senior Engineer", 50_000d, "Founder" );
        shallowCopy = null;

    }

    @Test
    void assertTest(){
        assertNull(shallowCopy); //pass
    }
    
    @Test
    void assertTest2(){
        employee1.setSalary(null);
        assertNull(employee1.getSalary()); //pass
    }
    
    @Test
    void assertTest3(){
        assertNotNull(employee1.getGender()); //pass
    }
    
    @Test
    void assertTest4(){
        assertNotNull(employee1.getSalary()); //fail
    }

    @Test
    void assertTest5(){
        employee1.setSalary(null);
        assertNull(shallowCopy); //pass
    }
}
```
**(C) Checking for Exceptions Using JUnit** 
- **asserThrow**
Modification to the Employee class
```java
package com.shola;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    String firstName;
    String lastName;
    Integer id;
    Character gender;
    String role;
    Double salary;
    String type;

    public void adjustSalary(Double adjAmount)throws InterruptedException{
        salary += adjAmount;
        Thread.sleep(6000);
    }

    //This method throws a custom runtime exception if the lastname doesn't contain only alphabet(@NotNull @NotString regex)
    public void validateLastName() throws Exception{
        if(!this.lastName.matches("^[a-zA-Z]*$")){
            throw new RuntimeException("The last name can only contain alphabets");
        }

    }

}
```
The test is **passed** if an exception is thrown due to the supplied argument(i.e. if the last name is invalid). However, if the last name\
is valid and an exception is not thrown, then the test **failed**. 
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    static Employee employee1;
    static Employee employee2;

    @BeforeAll
    static void initEmployee(){
        employee1 = new Employee("Jarda#", "Ondrej$", 1234, 'M',
                "Very Senior Engineer", 50_000d, "Founder" );
    }

    //sanity check of the custom RuntimeException defined within the method validateLastName().
    @Test
    void assertTest(){
                        assertThrows(
                                RuntimeException.class,
                                () -> {employee1.validateLastName();},
                        "Validate if the throws RuntimeException in the method works"
                                ); //pass because the last name is invalid and an exception is thrown
    }

    @Test
    void assertTest2(){
       employee2 =  new Employee();
        employee2.setLastName("Ondrej");
        assertThrows(
                Exception.class,
                () -> {employee2.validateLastName();},
                "Validate if the throws RuntimeException in the method works"
        ); //this fails because there was no exception thrown.
    }

}
```

**(D) Working with Specialized Assert Methods in JUnit** \
- **assertAll** and **assertIterableEquals**  \
Previous section made use of single assert method per test case. A typical test case is to return passed only when several assert \
methods are passed. assertAll combines many assert methods together to form a single test case. \
Modified Employee class:
```java
package com.shola;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    String firstName;
    String lastName;
    Integer id;
    Character gender;
    String role;
    Double salary;
    String type;

    ArrayList<String> projects;
    
    public void adjustSalary(Double adjAmount)throws InterruptedException{
        salary += adjAmount;
        Thread.sleep(6000);
    }

    //This method throws a custom runtime exception if the lastname doesn't contain only alphabet(@NotNull @NotString regex)
    public void validateLastName() throws Exception{
        if(!this.lastName.matches("^[a-zA-Z]*$")){
            throw new RuntimeException("The last name can only contain alphabets");
        }

    }

}
```

Test: the testcase is passed because assert method evaluates to pass
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
  static Employee employee1;
  static Employee employee2;

  @BeforeAll
  static void initEmployee() throws InterruptedException {
    employee1 = new Employee("Jarda", "Ondrej$", 1234, 'M',
            "Very Senior Engineer", 50_000d, "Founder", new ArrayList<String>());
  }

  @Test
  void assertAllTestExample() throws InterruptedException {
    employee1.adjustSalary(50_000D);

    assertAll(
            "Test Employee",//first argument is "heading
            () -> assertEquals(100_000D, employee1.getSalary()),
            () -> assertNotNull(employee1.getFirstName()),
            () -> assertThrows(RuntimeException.class, () ->{employee1.validateLastName();}, "Exception test")
    );
  }
}
```
Test: the testcase is failed because just a single assert method evaluates to fail
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    static Employee employee1;
    static ArrayList<String> employeeProjectHolder = new ArrayList<String>();
    static ArrayList<String> allProject = new ArrayList<String>();

    @BeforeAll
    static void initEmployee() throws InterruptedException {

        employee1 = new Employee("Jarda", "Ondrej$", 1234, 'M',
                "Very Senior Engineer", 100_000d, "Founder", employeeProjectHolder);

        employee1.projects.add("Springboot");
        employee1.projects.add("Spring security");

        Collections.addAll(allProject, "Springboot", "Spring security", "Spring data");
    }

   @Test
   void assertAllTestExample() throws InterruptedException {

        assertAll(
                "Test Employee",//first argument is "heading
                () -> assertEquals(100_000D, employee1.getSalary()),
                () -> assertNotNull(employee1.getFirstName()),
                () -> assertThrows(RuntimeException.class, () ->{employee1.validateLastName();}, "Exception test"),
                () -> assertIterableEquals(employee1.getProjects(), allProject) //the entire test will fail cuz of this assertion
        );
   }
}
```
**(E) Using Assumptions in Test Cases in JUnit** \
Gives the option to ignore or skip test cases based on certain defined conditions. A skipped test will not be evaluated. Hence, it\
can't be known if it's a failed or passed test.
- **assumeTrue** and **assumeFalse**
```java
package com.shola;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    static Employee employee1;
    static ArrayList<String> employeeProjectHolder = new ArrayList<String>();
    static GregorianCalendar gc;
    
    @BeforeAll
    static void initEmployee() throws InterruptedException {
        employee1 = new Employee("Jarda", "Ondrej$", 1234, 'M',
                "Very Senior Engineer", 100_000d, "Founder", employeeProjectHolder);

       Collections.addAll(employee1.projects, "Springboot", "Spring security");
       gc = new GregorianCalendar();
    }

    @Test
    void  assumptionTestExample() throws InterruptedException {
        System.out.println("Current hour: " + gc.get(Calendar.HOUR_OF_DAY));

        Assumptions.assumeTrue(gc.get(Calendar.HOUR_OF_DAY) < 20);

        employee1.adjustSalary(50_000D);
        assertEquals(150_000, employee1.getSalary());

        System.out.println("The assumption about the time of the day was satisfied and the test was run");
    }
    
}
```

- **assumingThat**
The assumeThat method combines the conditional check and the code block/test to be executed. Instead of separating the condition and \
the test/code block as is the case with assumeTrue.
```java
package com.shola;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    static Employee employee1;
    static ArrayList<String> employeeProjectHolder = new ArrayList<String>();
    static GregorianCalendar gc;

    @BeforeAll
    static void initEmployee() throws InterruptedException {

        employee1 = new Employee("Jarda", "Ondrej$", 1234, 'M',
                "Very Senior Engineer", 100_000d, "Founder", employeeProjectHolder);

       Collections.addAll(employee1.projects, "Springboot", "Spring security");

       gc = new GregorianCalendar();

    }

    @Test
    void  assumptionTestExample() throws InterruptedException {
        System.out.println("Current hour: " + gc.get(Calendar.HOUR_OF_DAY));
        
        Assumptions.assumingThat(
                //This is condition
               (gc.get(Calendar.HOUR_OF_DAY) < 20),
                //The block of code to be run or skipped depending on the condition above
                () ->{
                    employee1.adjustSalary(50_000D);
                    assertEquals(150_000, employee1.getSalary());
                    System.out.println("The assumption about the time of the day was satisfied and the test was run");
                }
        );
        
        //This block of code will be executed regardless of the result of the condition
        System.out.println("This test case uses assumingThat to check the time of the day before running the test.");
    }
}
```

## 3: Unit Testing: Advanced Annotations in JUnit
**(A) Setting a Display Name for a Test Case in JUnit**\
The `@DisplayName` annotation provides the mean for defining a custom name that will be a representation of the test case in the test result.\
Class under test: Product
```java
package com.shola;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    String brandName;
    String model;
    Integer id;
    Double price;
    String category;
}
```
- **@DisplayName**
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    @Test
    @DisplayName("Brand Name Check by Shola")
    void brandNameTest(){
        assertEquals("LifeImage", tv.getBrandName(), "TV 'brandName' field test");
    }

    @Test
    @DisplayName("For heaven sake, CHECK price is not zero or null PLS!")
    void priceNonNullCheck(){
        assertAll( "Price sanity check",
                () ->  assertNotNull(tv.getPrice()),
                () ->assertTrue(tv.getPrice() > 0)
        );
    }
}
```
**(B) Running Tests in JUnit Based on the Operating System**\
The `@Disabled` annotation enables us to mark a particular test case as disabled. There is no condition that has to be satisfied before disabling\
a test case that is marked with this annotation. \
- **@Disabled**
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    @Test
    @Disabled
    @DisplayName("Brand Name Check by Shola")
    void brandNameTest(){
        assertEquals("LifeImage", tv.getBrandName(), "TV 'brandName' field test");
    }

    @Test
    @DisplayName("For heaven sake, CHECK price is not zero or null PLS!")
    void priceNonNullCheck(){
        assertAll( "Price sanity check",
                () ->  assertNotNull(tv.getPrice()),
                () ->assertTrue(tv.getPrice() > 0)
        );
    }
}
```
The `EnabledOnOS` and DisabledOnOS annotation enables/disable a test case based on the condition of operating system (i.e. the test will\
run/not run on specified OS)
- **@EnabledOnOS** and **DisabledOnOS**
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }
    
    @Test
    @DisplayName("Model test enabled only on macOS operating system.")
    @EnabledOnOs(OS.MAC)
    void osBasedTest(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @DisplayName("Model test disable only on linux operating system.")
    @DisabledOnOs(OS.LINUX)
    void osBasedTest2(){
        assertEquals(tv.getModel(), "Alpha");
    }
}
```
**(C) Turning Tests on or off Based on JRE Version**\
- **EnabledOnJre**, **DisabledOnJre** and **EnabledForJreRange**, **DisabledOnJre**
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    @Test
    @DisplayName("Model test enabled only on JRE")
    @EnabledOnJre(JRE.JAVA_8)
    void jreBasedTest(){
        assertEquals(tv.getModel(), "Alpha"); 
    }

    @Test
    @DisplayName("Model test enabled only on JRE")
    @DisabledOnJre(JRE.JAVA_8)
    void jreBasedTest2(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @DisplayName("Model test enabled only on JRE")
    @EnabledForJreRange(min=JRE.JAVA_8, max = JRE.JAVA_13)
    void jreBasedTest3(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @DisplayName("Model test enabled only on JRE")
    @DisabledForJreRange(min=JRE.JAVA_8, max = JRE.JAVA_17)
    void jreBasedTest5(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @DisplayName("Model test enabled only on JRE")
    @DisabledForJreRange(min=JRE.JAVA_17)
    void jreBasedTest6(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @DisplayName("Model test enabled only on JRE")
    @EnabledForJreRange(min=JRE.JAVA_17)
    void jreBasedTest4(){
        assertEquals(tv.getModel(), "Alpha");
    }

}
```

**(D) Checking for System and Environment Properties**\
**Refresher on accessing system environment and property variables:**\
The system properties are properties/variables related to Java environment/platform while system environment variables are related OS\
level variables These classes of variables can be accessed as shown below using `System.getProperty` and `System.getenv`. 
```java
package com.shola;

import java.util.HashMap;
import java.util.Map;

public class MainClass {
    public static void main(String [] args) throws InterruptedException {
        System.err.println("System properties: ");
        System.out.println("os.arch: "+System.getProperty("os.arch"));
        System.out.println("os.version: "+System.getProperty("os.version"));
        System.out.println("os.name: "+System.getProperty("os.name"));
        System.out.println("user.name: "+System.getProperty("user.name"));
        /*
        System properties:
        os.arch: amd64
        os.version: 10.0
        os.name: Windows 10
        user.name: absuleim
         */
        Map<String, String> envVariables = new HashMap<String, String>(System.getenv());
        for(Map.Entry<String, String> entry: envVariables.entrySet()){
            System.out.println(entry.getKey() + ": " + entry.getValue());
            /*
            -----sorry, not gonna show outputs containing sensitive information....try it out yourself LOL!
             */
        }
    }
}
```
- **EnabledIfSystemProperty**, **DisabledIfSystemProperty** and  **@EnabledIfEnvironmentVariable**, **DisabledIfEnvironmentVariable**
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;


import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    @Test
    @EnabledIfSystemProperty(named="os.arch", matches ="amd64")
    void systemPptBasedTest(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @EnabledIfSystemProperty(named="os.version", matches ="10\\..*")
    void systemPptBasedTest2(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @EnabledIfEnvironmentVariable(named="HOMEDRIVE", matches="C:")
    void systemPptBasedTest3(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @EnabledIfEnvironmentVariable(named="PWD", matches = ".*Testing.*") //regex: if the curred directory contains Testing
    void systemPptBasedTest4(){
        assertEquals(tv.getModel(), "Alpha");
    }
    
}
```
**(E) Composed Annotations in JUnit**\
All previous Enabled annotations checks only one condition. There are cases requiring multiple Disable/Enable annotations:
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;


import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {

    static Product tv;

    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    @Test
    //The Disabled annotation will take precedence
    @DisabledForJreRange(min=JRE.JAVA_12)
    @EnabledIfSystemProperty(named="os.arch", matches ="amd64")
    void systemPptBasedTest(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    //The test will only be enabled if BOTH "Enable" annotations evaluate to true.
    @EnabledIfEnvironmentVariable(named="HOMEDRIVE", matches="C:")
    @EnabledForJreRange(max=JRE.JAVA_12)
    void systemPptBasedTest2(){
        assertEquals(tv.getModel(), "Alpha");
    }
    
}
```
- Multiple Enable/Disable conditions can be handled using composed or meta annotations which are composed of multiple individual annotations.\
```java
package com.shola;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ProductTest {

    static Product tv;
    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    //This is a composed annotation interface used to specify a composite of four different 'EnabledIf conditions'.
        // It also encompasses the @Test annotation.
    @Retention(RetentionPolicy.RUNTIME) //The annotation will be retained until runtime.
    @Test
    @EnabledIfEnvironmentVariable(named="HOMEDRIVE", matches="C:")
    @EnabledForJreRange(max=JRE.JAVA_12)
    @EnabledIfSystemProperty(named="os.version", matches ="10\\..*")
    @EnabledIfSystemProperty(named="os.arch", matches ="amd64")
    public @ interface MyCustomTestAnnotation{

    }

    @MyCustomTestAnnotation
    void systemPptBasedTest(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @MyCustomTestAnnotation
    @DisabledForJreRange(min=JRE.JAVA_12) //This annotation will take precedence if evaluated to true.
    void systemPptBasedTest2(){
      assertEquals(tv.getModel(), "Alpha");
    }

}
```
**(F) Ordering Test Case Executions Using JUnit** \
The default order of execution of the test cases is random. However, the order of execution of test cases can be configured using annotation.\
```
@TestMethodOrder(MethodOrderer.DisplayName.class) //orders the test by the DisplayName of the test cases
@TestMethodOrder(MethodOrderer.MethodName.class)  //based on the name of the test methods.
@TestMethodOrder(MethodOrderer.Random.class) //the order changes every time the test class is run
```
- Example
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TestMethodOrder(MethodOrderer.DisplayName.class)
public class ProductTest {

    static Product tv;
    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    //This is a composed annotation interface used to specify a composite of four different 'EnabledIf conditions'.
        // It also encompasses the @Test annotation.
    @Retention(RetentionPolicy.RUNTIME) //The annotation will be retained until runtime.
    @Test
    @EnabledIfEnvironmentVariable(named="HOMEDRIVE", matches="C:")
    @EnabledForJreRange(max=JRE.JAVA_12)
    @EnabledIfSystemProperty(named="os.version", matches ="10\\..*")
    @EnabledIfSystemProperty(named="os.arch", matches ="amd64")
    public @ interface MyCustomTestAnnotation{

    }

    @MyCustomTestAnnotation
    @DisplayName("A")
    void systemPptBasedTest(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @MyCustomTestAnnotation
    @DisplayName("B")
    @DisabledForJreRange(min=JRE.JAVA_12) //This annotation will take precedence if evaluated to true.
    void systemPptBasedTest2(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @DisplayName("C")
    void systemPptBasedTest3(){
        assertEquals(tv.getBrandName(), "LifeImage");
    }

    @Test
    @DisplayName("D")
    void systemPptBasedTest4(){
        assertEquals(tv.getBrandName(), "LifeImage");
    }

}
```
- Custom ordering: Note the argument of the annotation @TestMethodOrder @Order annotation
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProductTest {

    static Product tv;
    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    //This is a composed annotation interface used to specify a composite of four different 'EnabledIf conditions'.
        // It also encompasses the @Test annotation.
    @Retention(RetentionPolicy.RUNTIME) //The annotation will be retained until runtime.
    @Test
    @EnabledIfEnvironmentVariable(named="HOMEDRIVE", matches="C:")
    @EnabledForJreRange(max=JRE.JAVA_12)
    @EnabledIfSystemProperty(named="os.version", matches ="10\\..*")
    @EnabledIfSystemProperty(named="os.arch", matches ="amd64")
    public @ interface MyCustomTestAnnotation{

    }

    @MyCustomTestAnnotation
    @Order(2)
    void systemPptBasedTest(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @MyCustomTestAnnotation
    @Order(3)
    @DisabledForJreRange(min=JRE.JAVA_12) //This annotation will take precedence if evaluated to true.
    void systemPptBasedTest2(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @Order(4)
    void systemPptBasedTest3(){
        assertEquals(tv.getBrandName(), "LifeImage");
    }

    @Test
    @Order(1)
    void systemPptBasedTest4(){
        assertEquals(tv.getBrandName(), "LifeImage");
    }
    
}
```

**(G) Working with Concurrent Tests and Timeouts in JUnit** \
All previous test cases ran separately and sequentially (i.e. one test begins after the preceding test had been executed). However, it's possible\
to have them run concurrently (i.e. all tests runs at the same time in parallel). This makes the test execution faster.
1. Create junit properties file: SimpleSmallPracticeProjects/TestingCourse/src/main/resources/**junit-platform.properties**. This file\
will be used to enable concurrent execution in JUnit.
```
junit.jupiter.execution.parallel.enabled = true
```
2. Annotate the test class with: `@Execution(ExecutionMode.CONCURRENT)`
- **@Execution** 
Modification to the Product class to show concurrency and timeout using Thread.sleep
```java
package com.shola;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Product {

    String brandName;
    String model;
    Integer id;
    Double price;
    String category;

    double increasePrice(Double increment) throws InterruptedException {
        price += increment;
        double newprice  = price + increment;
        Thread.sleep(2000);
        return newprice;
    }
}
```
The test class
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT) //This annotation enables concurrent execution.
public class ProductTest {

    static Product tv;
    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    //This is a composed annotation interface used to specify a composite of four different 'EnabledIf conditions'.
        // It also encompasses the @Test annotation.
    @Retention(RetentionPolicy.RUNTIME) //The annotation will be retained until runtime.
    @Test
    @EnabledIfEnvironmentVariable(named="HOMEDRIVE", matches="C:")
    @EnabledForJreRange(max=JRE.JAVA_12)
    @EnabledIfSystemProperty(named="os.version", matches ="10\\..*")
    @EnabledIfSystemProperty(named="os.arch", matches ="amd64")
    public @ interface MyCustomTestAnnotation{

    }

    @MyCustomTestAnnotation
    @Order(2)
    void systemPptBasedTest() throws InterruptedException {
        assertNotEquals(tv.increasePrice(23.0), tv.getPrice());
    }

    @MyCustomTestAnnotation
    @Order(3)
    @DisabledForJreRange(min=JRE.JAVA_12) //This annotation will take precedence if evaluated to true.
    void systemPptBasedTest2(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @Order(4)
    void systemPptBasedTest3(){
        assertEquals(tv.getBrandName(), "LifeImage");
    }

    @Test
    @Order(1)
    void systemPptBasedTest4(){
        assertEquals(tv.getBrandName(), "LifeImage");
    }

}
```
The maximum duration for a test execution can be set using the @Timeout annotation
- **@Timeout** 
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT) //This annotation enables concurrent execution.
public class ProductTest {

    static Product tv;
    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    //This is a composed annotation interface used to specify a composite of four different 'EnabledIf conditions'.
        // It also encompasses the @Test annotation.
    @Retention(RetentionPolicy.RUNTIME) //The annotation will be retained until runtime.
    @Test
    @EnabledIfEnvironmentVariable(named="HOMEDRIVE", matches="C:")
    @EnabledForJreRange(max=JRE.JAVA_12)
    @EnabledIfSystemProperty(named="os.version", matches ="10\\..*")
    @EnabledIfSystemProperty(named="os.arch", matches ="amd64")
    public @ interface MyCustomTestAnnotation{

    }

    @MyCustomTestAnnotation
    @Timeout(3)
    void systemPptBasedTest() throws InterruptedException {
        assertNotEquals(tv.increasePrice(23.0), tv.getPrice());
    }
    
}
```
## 4: Unit Testing: Parameterized JUnit Tests
**(A) Running Tests Repeatedly in JUnit**\
The @RepeatedTest annotation can be used to make a particular test case run repeatedly. 
- **@RepeatedTest**
```java
package com.shola;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {
    static Employee employee1;
    static ArrayList<String> employeeProjectHolder = new ArrayList<String>();
    static GregorianCalendar gc;

    static double defaultSalary;

    @BeforeAll
    static void initEmployee() throws InterruptedException {

        defaultSalary =100_000d;
        employee1 = new Employee("Jarda", "Ondrej$", 1234, 'M',
                "Very Senior Engineer", 100_000d, "Founder", employeeProjectHolder);

       Collections.addAll(employee1.projects, "Springboot", "Spring security");

       gc = new GregorianCalendar();

    }

    @RepeatedTest(3)//The test will run 3 times.
    @DisplayName("salary Update")
    void salaryUpdateTest() throws InterruptedException {

        Double salaryIncrement = 20000D;
        employee1.adjustSalary(salaryIncrement);
        defaultSalary += salaryIncrement;
        System.out.println(defaultSalary);
        assertEquals(defaultSalary, employee1.getSalary(), "Test salary updates");
        ;
    }
    
}
```
**(B) Running Parameterized Tests with a Sources** \
Add the dependency to the pom.xml file: `<artifactId>junit-jupiter-params</artifactId>`\
Modification to the Employee class(check the adjustSalary method):
```java
package com.shola;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    String firstName;
    String lastName;
    Integer id;
    Character gender;
    String role;
    Double salary;
    String type;

    ArrayList<String> projects;

    public void adjustSalary(Double adjAmount)throws InterruptedException{

        if(adjAmount >5000){
            System.err.println("The adjustment amount is greater than upper limit of 5000. The adjustment is set to 5000");
            adjAmount = 5000D;
        }
        System.err.println("Updating salary from "+salary+ " to "+(salary+adjAmount));
        salary += adjAmount;
        Thread.sleep(60);
    }

    //This method throws a custom runtime exception if the lastname doesn't contain only alphabet(@NotNull @NotString regex)
    public void validateLastName() throws Exception{
        if(!this.lastName.matches("^[a-zA-Z]*$")){
            throw new RuntimeException("The last name can only contain alphabets");
        }

    }

}
```
- **@ParameterizedTest** and **@ValueSource**  
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

public class EmployeeTest {
    static Employee employee1;
    static ArrayList<String> employeeProjectHolder = new ArrayList<String>();
    static GregorianCalendar gc;

    static double salary;

    @BeforeAll
    static void initEmployee() throws InterruptedException {
        salary =100_000d;
        employee1 = new Employee("Jarda", "Ondrej$", 1234, 'M',
                "Very Senior Engineer", salary, "Founder", employeeProjectHolder);

       Collections.addAll(employee1.projects, "Springboot", "Spring security");
       gc = new GregorianCalendar();
    }

    @ParameterizedTest
    @DisplayName("Salary Update")
    //note that 'doubles' represents the data type. Any data type that corresponds to the intended parameter can be used.
    @ValueSource(doubles = {500, 1000, 2000, 5500}) //the test case will execute 4 times(i.e. length of this array)
    void salaryUpdateTest(double salaryIncrement) throws InterruptedException {
        employee1.adjustSalary(salaryIncrement);
        salary += salaryIncrement;
        assertEquals(salary, employee1.getSalary(), "Test salary updates!");
    }
}
```
- **@ParameterizedTest** and **@NUllSource**
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class EmployeeTest {
    static Employee employee1;
    static ArrayList<String> employeeProjectHolder = new ArrayList<String>();
    static GregorianCalendar gc;

    static double salary;

    @BeforeAll
    static void initEmployee() throws InterruptedException {

        salary =100_000d;
        employee1 = new Employee("Jarda", "Ondrej$", 1234, 'M',
                "Very Senior Engineer", salary, "Founder", employeeProjectHolder);

       Collections.addAll(employee1.projects, "Springboot", "Spring security");

       gc = new GregorianCalendar();

    }
    
    @ParameterizedTest
    @DisplayName("Salary Update2")
    @NullSource
    void salaryUpdateTest2(String nullName) throws InterruptedException {
        Employee nullEmployee = new Employee ();
        //even though the parameter type is String when parsed into the test, the type is changed to null
        nullEmployee.setLastName(nullName);
        assertNull(nullEmployee.getLastName()); //pass because the
    }
}
```

- **@ParameterizedTest**, **@CsvSource** and **CsvFileSource**\
An array of comma separated string can be parsed to a test case. Below is an example of hardcoded CSV values
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class EmployeeTest {
    static Employee employee1;
    static ArrayList<String> employeeProjectHolder = new ArrayList<String>();
    static GregorianCalendar gc;

    static double salary;

    @BeforeAll
    static void initEmployee() throws InterruptedException {

        salary =100_000d;
        employee1 = new Employee("Jarda", "Ondrej$", 1234, 'M',
                "Very Senior Engineer", salary, "Founder", employeeProjectHolder);
       Collections.addAll(employee1.projects, "Springboot", "Spring security");

       gc = new GregorianCalendar();

    }
    
    @ParameterizedTest
    @DisplayName("Diaplay Name")
    @CsvSource({"Brian, Jarda, Lukas, Robert"})//hardcoded csv valyes
    void salaryUpdateTest4(String fName, String lName) throws InterruptedException {
        Employee NonnullEmployee = new Employee (fName.trim(), lName.trim(), 1011,
                'M', "Special OPS",  salary, "Founder", employeeProjectHolder );
        //even though the parameter type is String when parsed into the test, the type is changed to null

        assertEquals(lName.trim(), NonnullEmployee.getLastName());
        assertEquals(fName.trim(), NonnullEmployee.getFirstName());
    }
    
}
```
Parsing CSV values from an external CSV file. 
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;

public class EmployeeTest {
    static Employee employee1;
    static ArrayList<String> employeeProjectHolder = new ArrayList<String>();
    static GregorianCalendar gc;

    static double salary;

    @BeforeAll
    static void initEmployee() throws InterruptedException {

        salary =100_000d;
        employee1 = new Employee("Jarda", "Ondrej$", 1234, 'M',
                "Very Senior Engineer", salary, "Founder", employeeProjectHolder);

       Collections.addAll(employee1.projects, "Springboot", "Spring security");

       gc = new GregorianCalendar();
    }
    
    @ParameterizedTest
    @DisplayName("Parameterized test using values from external CSV file as parameters")
    @CsvFileSource(resources = "/empnames.csv", numLinesToSkip = 1) //path to the CSV file
    void salaryUpdateTest5(String fName, String lName) throws InterruptedException {
        Employee NonnullEmployee = new Employee (fName.trim(), lName.trim(), 1011,
                'M', "Special OPS",  salary, "Founder", employeeProjectHolder );
        //even though the parameter type is String when parsed into the test, the type is changed to null
        assertEquals(lName.trim(), NonnullEmployee.getLastName());
        assertEquals(fName.trim(), NonnullEmployee.getFirstName());
    }
}
```
- **@ParameterizedTest**, **@MethodSource** and **SimpleArgumentConverter** \
The @MethodSource allows us to obtain values to be parsed to a test case from a method invocation. Also note the new argument for the\
@ParameterizedTest annotation which can be used to set the name for the test.
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.stream.Stream;

public class EmployeeTest {
    
    static Stream<String> getLastName(){
        return Stream.of("Ald4", "8Jagd", "$mith");
    }

    @ParameterizedTest(name ="Test #{index} - Last name: {0}")
    @MethodSource("getLastName") //The parameters to be passed to the test will come from the method 'getLastName'
    @DisplayName("Name Check from a method souce")
    void nameTest(String lName){
        Employee employee2 = new Employee();
        employee2.setLastName(lName);

        assertThrows(RuntimeException.class,
                () -> {employee2.validateLastName();},
                "Throws Exception test"
        );
    }
    
}
```

## 5: Unit Testing: Executing JUnit Tests
**(A) Refreshers: Running Tests Using Maven**\
Maven **maven-surefire-plugin** plugin does the magic. Run the following from project path:\
* **Executing All Tests within a Project Using Maven**
- `mvn clean compile` to compile/build project source codes w/o the test classes(i.e. only .java files in the src/main path)
- `mvn test` to compile and run the test classes.(i.e. the .java files in the src/test path)\
* **Running Specific Tests Class Using Maven** 
- `mvn -Dtest=AccountDetailTest test` where AccountDetailTest corresponds to a test class.
- `mvn -Dtest=AccountDetailTest,EmployeeTest test` where AccountDetailTest and EmployeeTest are tests classes.\
* **Running Specific Tests Case within A Test Class Using Maven** 
- `mvn -Dtest=AccountDetailTest#withdrawalTest test `where withdrawalTest is a method/test case inside the test class.
- `mvn -Dtest=AccountDetailTest#withdrawalTest,EmployeeTest#salaryUpdateTest5 test`


**(B) Grouping Tests Together Using Tag**\
Modification to the ProductTest and AccountDetailsTest classes.
- ProductTest class
```java
package com.shola;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.*;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT) //This annotation enables concurrent execution.
public class ProductTest {

    static Product tv;
    @BeforeAll
    static void initAll(){
        tv = new Product("LifeImage", "Alpha", 1111, 63.5, "Electronics");
    }

    //This is a composed annotation interface used to specify a composite of four different 'EnabledIf conditions'.
        // It also encompasses the @Test annotation.
    @Retention(RetentionPolicy.RUNTIME) //The annotation will be retained until runtime.
    @Test
    @EnabledIfEnvironmentVariable(named="HOMEDRIVE", matches="C:")
    @EnabledForJreRange(max=JRE.JAVA_12)
    @EnabledIfSystemProperty(named="os.version", matches ="10\\..*")
    @EnabledIfSystemProperty(named="os.arch", matches ="amd64")
    public @ interface MyCustomTestAnnotation{

    }

    @MyCustomTestAnnotation
    @Timeout(3)
    @Order(2)
    void systemPptBasedTest() throws InterruptedException {
        assertNotEquals(tv.increasePrice(23.0), tv.getPrice());
    }

    @MyCustomTestAnnotation
    @Tag("DEVShola")
    @Order(3)
    @DisabledForJreRange(min=JRE.JAVA_12) //This annotation will take precedence if evaluated to true.
    void systemPptBasedTest2(){
        assertEquals(tv.getModel(), "Alpha");
    }

    @Test
    @Order(4)
    void systemPptBasedTest3(){
        assertEquals(tv.getBrandName(), "LifeImage");
    }

    @Test
    @Order(1)
    void systemPptBasedTest4(){
        assertEquals(tv.getBrandName(), "LifeImage");
    }

}
```
- AccountDetailTest class
```java
package com.shola;

import org.junit.jupiter.api.*;
import com.shola.AccountDetail.AccountType;
import com.shola.AccountDetail.*;
public class AccountDetailTest {
    static AccountDetail accountDetail;

    @BeforeAll
    static void initAlls(){
        System.out.println("---Starting the test class.---");

    }
    @BeforeEach
    //The method below will be run before each test case/method
    public void initEach(){
        accountDetail = new AccountDetail("John Dick", 5555, 22,50_000D, AccountType.COMPANY);
        System.out.println("@BeforeEach block has been executed");
        System.out.println("account balance: "+accountDetail.getBalance());
    }

    @Test
    @Tag("DEVShola")
    void depositTest() throws NullPointerException {
        accountDetail.deposit(50_000D);
        System.out.println("@Test block for deposit has been executed !");
    }

    @Test
    void withdrawalTest() throws NullPointerException{
        accountDetail.withdrawal(20_000D);
        System.out.println("@Test block for withdrawal has been executed !");
    }

    @AfterEach
    void balance(){
        System.out.println("@AfterEach has been executed");
        System.out.println("account balance : "+accountDetail.getBalance());
    }

    @AfterAll
    static void teardownAlls(){;
        System.out.println("---The test class has been completed.---");
        accountDetail = null;
        System.err.println(accountDetail);
    }
    /*
    ---Starting the test class.---
    @BeforeEach block has been executed
    account balance: 50000.0
    @Test block for withdrawal has been executed !
    @AfterEach has been executed
    account balance : 30000.0
    @BeforeEach block has been executed
    account balance: 50000.0
    @Test block for deposit has been executed !
    @AfterEach has been executed
    account balance : 100000.0
    null
    ---The test class has been completed.---
     */
}
```
- To run all tests cases with the custom tag(DEVShola):  `mvn -Dgroups=DEVShola test`

**(C) Running Tests with the JUnit ConsoleLauncher** \
Maybe not really necessary LOL....

## 6: Unit Testing with Mocks: Getting Started with Mockito
**Object under test VS Mocks** \
When performing unit test on an object, we often have other complex objects that the object under test depends upon. Rather than \
actually creating/using those complex objects, we can use **Mockito** to create **mocks** of those complex objects and then stub methods\
on them in order to simulate expected behavior.\
It is important to stress that the object being mocked is usually NOT the object under test but another complex object upon which the object under test depends upon.\
Hence, the mock objects (mocks) are dummy objects or interface that stand in for other complex object. The mocks are not being tested directly
- Mocks are ideal when the actual object is non-deterministic(varies). Hence, the variation will affect the outcome of the unit tests
- Mocks are also ideal for states or object that are difficult to obtain. (e.g. network error)
- Mocks are ideal for objects or state which has slow process of instantiation. 
The mock objects are usually provided to the class or object under test as form of an injected dependency 
Mockito is a framework used to create mocks. It has one way to create mocks and only one kind of mock. It takes care of injecting the mock as dependency\
into the test and also deals with the stubbing of the methods of the mock(i.e. specify how the methods should behave in terms of its input and output).
Mockito has different types of entities: **Mocks** create an entirely new dummy objects. **Spies** define behavior for specific methods. **Argument captors** are \
used to verify input arguments. **Argument matchers** are used to specify input arguments. These entities are combined to create mocks of complex objects. \
Conclusively, the essence of mock is to create a lightweight version of an object which will be used by (or is a dependent of) an object under test. 
**(A) Using Mocks in UNit Tests** \
The dependencies' section of the current pom.xml file reflects the fact that JUnit in encompassed in Mockito.
```xml
<dependencies>

    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-core</artifactId>
      <version>3.9.0</version>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.mockito</groupId>
      <artifactId>mockito-junit-jupiter</artifactId>
      <version>3.9.0</version>
      <scope>test</scope>
    </dependency>

</dependencies>
```
- An example of mocking the java.util.ArrayList class. Mockito creates a derived class that extends the class being mocked.
```java
package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import java.util.*;
public class ArrayListMock {

    @Test
    @DisplayName("Mocking of ArrayList")
    public void mockArrayList() {
        ArrayList someArrayList = mock(ArrayList.class);
        System.out.println("toString() of mock: " + someArrayList); //toString() of mock: Mock for ArrayList, hashCode: 1058876963
        System.out.println("getClass() of mock: " + someArrayList.getClass()); //getClass() of mock: class org.mockito.codegen.ArrayList$MockitoMock$177180350
        System.out.println("getClass().getSuperClass() of mock: " + someArrayList.getClass().getSuperclass()); //class java.util.ArrayList

        System.out.println("someArrayList instanceof ArrayList: " + (someArrayList instanceof ArrayList)); //true
        System.out.println("someArrayList instanceof List: " + (someArrayList instanceof List)); //true
        System.out.println("someArrayList instanceof Collection: " + (someArrayList instanceof Collection)); //true
        System.out.println("someArrayList instanceof Iterable: " + (someArrayList instanceof Iterable)); //true
        System.out.println("someArrayList instanceof Object: " + (someArrayList instanceof Object)); //true
    }
}
```
**(B) Stubbing Methods Using when().thenReturn()** \
Stubbing refers to specifying the return values from a specific method of a mock. Strict stubbing means that a call (by object under test)\
to an unstubbed method of a mock will return 'undefined'.
```java
package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.*;
public class ArrayListMockTest {

    @Test
    @DisplayName("Mocking of ArrayList")
    public void mockArrayList() {
        ArrayList someArrayList = mock(ArrayList.class);

        //'stubbing': specifying the return value for methods of the mock object
        when(someArrayList.isEmpty()).thenReturn(true);
        when(someArrayList.size()).thenReturn(23);
        when(someArrayList.toArray()).thenReturn(new Object[]{5,1,6});

        //using JUnit to test the return value from the stub
        assertTrue(someArrayList.isEmpty()); //pass
        assertEquals(someArrayList.size(), 23); //pass
        assertArrayEquals(new Object[]{5,1,6}, someArrayList.toArray()); //pass

        //Parameterized stubbing
        when(someArrayList.contains("Alice")).thenReturn(true);
        when(someArrayList.contains("Bob")).thenReturn(false);
        when(someArrayList.get(4)).thenReturn("Shola");

        assertTrue(someArrayList.contains("Alice")); //pass
        assertFalse(someArrayList.contains("Bob")); //pass
        assertEquals("Shola", someArrayList.get(4)); //pass


        when(someArrayList.subList(100,102)).thenReturn(Arrays.asList("Peppa", "Mary"));
        when(someArrayList.subList(101,104)).thenReturn(Arrays.asList("Peppa", "Mary", "Zoe", "Rebeca", "Emily"));
        assertLinesMatch(Arrays.asList("Peppa", "Mary"),someArrayList.subList(100,102) ); //pass
        assertLinesMatch(Arrays.asList("Peppa", "Mary", "Zoe"),someArrayList.subList(101,104) ); //fail

    }
}
```
**(C) Mocking Interfaces** \
Instance and class check 
```java
package org.example;

import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;

import java.util.Comparator;

public class ComparatorMock {
    
    @Test
    public void comparatorMock() {
        Comparator<String> comparatorMock = mock(Comparator.class);
        System.out.println("toString() of comparatorMock: "+ comparatorMock); //toString() of comparatorMock: Mock for Comparator, hashCode: 403174823
        System.out.println("getClass() of comparatorMock: "+ comparatorMock.getClass()); //getClass() of comparatorMock: class org.mockito.codegen.Comparator$MockitoMock$1688444240
        System.out.println("comparatorMock instance of Comparator: "+ (comparatorMock instanceof Comparator));//toString() of comparatorMock: true
        
    }
}
```
- Mocking the Java Comparator interface
```java
package org.example;

import org.junit.jupiter.api.Test;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ComparatorMock {
    
    @Test
    public void comparatorMock() {
        Comparator<String> comparatorMock = mock(Comparator.class);

        when(comparatorMock.compare("Tomas", "Jarda")).thenReturn(1);
        when(comparatorMock.compare("Jarda", "Tomas")).thenReturn(-1);
        when(comparatorMock.compare("Tomas", "Tomas")).thenReturn(0);

        assertEquals(comparatorMock.compare("Tomas", "Jarda"),  1); //true
        assertTrue(comparatorMock.compare("Jarda", "Tomas") < 0); //true
        assertEquals(comparatorMock.compare("Jarda", "Tomas"), -1); //true
    }
}
```
**(D) Creating an Object Depending on a Mock** \
The aim is to **mock** a `Comparator` object and **stub** its methods. This mock is then passed to an instance of `TreeSet` to be used\
for ordering its element. Note the unexpected behavior(only Zoe and Alena was printed out)
```java
package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest {

  //This property type will be used to instaiate the Comparator mock
  private Comparator<String> comparatorMock;

  @BeforeEach
  public void setupAndStockTheMock(){
    comparatorMock = mock(Comparator.class);

    when(comparatorMock.compare("Jarda", "Tomas")).thenReturn(-1);
    when(comparatorMock.compare("Alena", "Zoe")).thenReturn(1); //reverse order
    when(comparatorMock.compare("Tomas", "Tomas")).thenReturn(0);
  }

  @AfterEach
  public void releaseMock(){
    comparatorMock = null;
  }

  @Test
  public void testTreeSet(){
    //we instantiate a TreeSet object and passing the mock as a Comparator
    TreeSet<String> treeSet = new TreeSet<>(comparatorMock);
    treeSet.add("Zoe");
    treeSet.add("Tomas");
    treeSet.add("Tomas");
    treeSet.add("Jarda");
    treeSet.add("Alena");

    for(String element: treeSet){
      System.out.println(element);
    }
       /*
       Zoe
       Alena
        */
  }
}
```
## 7: Unit Testing with Mocks: Creating Mocks & Verifying Behavior    
**Skip to section (C) if you are in hurry LOL**\
Mockito can also be used to simulate and verify specific aspects of **interaction** between the mock and the object under test. The @Mock and @ExtendWith\
annotations are can be used to build mocks. @InjectMocks is used to perform dependency injection into the object under test. The dependency inject has\
three variants: constructor injection, setter property injection, and field injection. The behavior of the object under test and  its interaction \
with the mock can then be verified using **constructs**  (e.g. verify(), veryNoInteractions()), and **argument matchers** (e.g anyString(), anyInt()) \
as well as **static methods**(atLeastOnce(), only(), never(), times()). \
**(A) Using the @Mock Annotation to Create Mocks** \
The static mock method has been used previously to create mocks. This section will deal with defining mocks using the `@Mock` annotation\
instead of the static mock method. The @Mock annotation is used in conjunction with a property/variable of `AutoCloseable` interface type. AutoCloseable\
is generally used to hold any variable that has resources such has files or socket that needs to be closed. Hence, it will be used to with the method `openMocks`.\
openMocks initializes mocks on all objects(member variables and methods) within the class. 
```java
package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.Comparator;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class TreeSetTestWithMockAnnotation {

    @Mock
    //Mockito performs the invocation of the static 'mock' method. See previous section for use of static 'mock' method
    private Comparator<String> comparatorMock;
    private AutoCloseable closeable; //

    //Setup method
    @BeforeEach
    public void initializeMocks(){

        closeable = openMocks(this);

        when(comparatorMock.compare("Alice", "Bob")).thenReturn(1);
        when(comparatorMock.compare("Bob", "Charles")).thenReturn(1);
        when(comparatorMock.compare("Alice", "Charles")).thenReturn(1);


        when(comparatorMock.compare("Bob", "Alice")).thenReturn(-1);
        when(comparatorMock.compare("Charles", "Bob")).thenReturn(-1);
        when(comparatorMock.compare("Charles", "Alice")).thenReturn(-1);

        when(comparatorMock.compare("Alice", "Alice")).thenReturn(0);
        when(comparatorMock.compare("Bob", "Bob")).thenReturn(0);
        when(comparatorMock.compare("Charles", "Charles")).thenReturn(0);


    }

    @AfterEach
    public void releaseMocks() throws Exception{
        closeable.close();
    }

    @Test
    public void testTreeSet(){

        //we parse the comparatorMock onto TreeSet for use in its hierarchical ordering of this element.
            //The comparatorMock is reverse order implementation.
            // And elements not included in the stubbing will be disregarded. See the printout below.

        TreeSet<String> treeSet = new TreeSet<>(comparatorMock);
        //Adding the elements to the treeSet
        Collections.addAll(treeSet, "Bob", "Alice", "Charles", "Tomas", "Ondrej" );
        for(String entry: treeSet){
            System.out.println(entry);
        }
        /*
        Charles
        Bob
        Alice
         */
        assertEquals("Charles", treeSet.first()); //pass
        assertEquals("Alice", treeSet.last());   //pass
        assertEquals("Bob", treeSet.higher("Charles")); //pass
        assertEquals("Bob", treeSet.lower("Alice"));    //pass
    }
}
```

**(B) Creating Objects to be Mocked and Using the @ExtendWith Annotation**
So far the objects that have  been mocked has been built-in objects. This section will deal with mocking custom objects(EmailSender).\
The custom object will be combined with an object under test(WeeklyReportBatchJob). In essence, the WeeklyReportBatchJob is an object\
to be tested. However, it depends on another complex/difficult object (EmailSender).
- The object to be mocked: src/main/java/org/example/**EmailSender**.java
```java
package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class EmailSender {
    private String username;
    private String password;
    private String emailServer;

    public boolean sendEmail(String toAddress, String emailText){
        return true;
    }

    public boolean sendEmailToMultipleRecipients(String [] toAddresses, String emailText){
        return true;
    }

    public boolean sendEmailWithAttachment(String toAddress, String emailText, byte[] attachmentBytes){
        return true;
    }

}
```
- The object under test: src/main/java/org/example/**WeeklyReportBatchJob**.java
```java
package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeeklyReportBatchJob {

    private EmailSender emailSender;

    public boolean generateWeeklyReport(String reportType, String emailRecipient){

        return emailSender.sendEmail(
                emailRecipient, String.format("The %s weekly report has been generated", reportType)
        );

    }

  public boolean generateWeeklyReport(String reportType, String[] emailRecipients){

    String reportCopy = "Sales have been going up !.";
    return emailSender.sendEmailToMultipleRecipients(
            emailRecipients, String.format("The %s weekly report has been generated", reportType)
    );

  }
  
  public boolean sendReport(String reportType, String emailRecipient){

    String reportCopy = "Sales have been going up !.";
    return emailSender.sendEmailWithAttachment(
            emailRecipient, String.format("The %s weekly report has been generated", reportType),
            reportCopy.getBytes()
    );
  }
  
}
```
- The test class: src/test/java/org/example/**WeeklyReportBatchJobTest**.java
When using JUnit5, by annotating the test class with @ExtendWith annotation, we can get rid of the AutoCloseable and in turn the\
entire setup and teardown methods.
```java
package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //with this annotation, we get rid of the AutoCloseable and the setup and teardown methods
public class WeeklyReportBatchJobTest {

  @Mock
  private EmailSender emailSenderMock;

  @Test
  public void testGenerateWeekly_singleRecipient(){
    WeeklyReportBatchJob batchJob = new WeeklyReportBatchJob(emailSenderMock);
    when(emailSenderMock.sendEmail("roberjohn@yes.com",
            "The Sales weekly report has been generated")).thenReturn(true);

    assert(batchJob.generateWeeklyReport("Sales", "roberjohn@yes.com")); //pass

  }

  @Test
  public void testGenerateWeekly_multipleRecipients(){
    WeeklyReportBatchJob batchJob = new WeeklyReportBatchJob(emailSenderMock);
    when(emailSenderMock.sendEmailToMultipleRecipients(
            new String [] {"roberjohn@yes.com", "roberjohn2@no.com"},
            "The Revenues weekly report has been generated")).thenReturn(true);

    assert(batchJob.generateWeeklyReport("Revenues", new String [] {"roberjohn@yes.com", "roberjohn2@no.com"})); //pass
  }

  @Test
  public void testWeeklyReport_withAttachment(){
    WeeklyReportBatchJob batchJob = new WeeklyReportBatchJob(emailSenderMock);
    when(emailSenderMock.sendEmailWithAttachment(
            "roberjohn@yes.com",
            "The Profits weekly report has been generated",
            "Sales have been going up !".getBytes())).thenReturn(true);

    assert(batchJob.sendWeeklyReport("Profits", "roberjohn@yes.com")); //pass
  }

}
```

**(C) Working with the @InjectMocks Annotation**
- When the object under test have several dependencies to be mocked, it can be tiresome to create individually instantiate the object\
under test with the mock object parameter. The `@InjectMocks` annotation can be used to automatically inject mocks into the object under test.
- In the previous section, we had to individually instantiate the object under test(WeeklyReportBatchJob) and individually parse the\
mock(emailSenderMock) object into it as follows:  `WeeklyReportBatchJob batchJob = new WeeklyReportBatchJob(emailSenderMock);`. 
- Instead, we can annotate the object under test(WeeklyReportBatchJob) with @InjectMocks, and injection will be automatic for all dependencies. Hence,
there will be no need to directly instantiate the object under test with the mock object parameter has done above.
- The test class re-written with @InjectMocks annotation: All instantiation of the object under test are discarded.
- In order for the @InjectMocks to inject the mocks, the object under test must have member variables of the type of the object to be mocked. (i.e the class\
WeeklyReportBatchJob has the member variable: `EmailSender emailSender`). Since the object under test will have this variable in its constructor, this is 
type mock injection is known as **constructor injection**. 
- If the object under test doesn't have a parameterized constructor, Mockito will automatically use its setters to implement the \
mock injection. This is known as **setters property injection**. 
- The last type of the injection mode in Mockito is the **field injection**. Mockito automatically uses the fields/member variables to implement\
the mock injection if it can't find neither parameterized constructor nor setters
- All the three injection methods are automatic as long as the object under test is annotated with @InjectMocks and has member variable types\
of the intended mock object
```java
package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //with this annotation, we get rid of the AutoCloseable and the setup and teardown methods
public class WeeklyReportBatchJobTest {
    @Mock
    private EmailSender emailSenderMock; //the mock object

    @InjectMocks //This instantiates the object under test and automatically inject it with the mock object
    WeeklyReportBatchJob batchJob; //the object under test

    @Test
    public void testGenerateWeekly_singleRecipient(){
        when(emailSenderMock.sendEmail("roberjohn@yes.com",
                "The Sales weekly report has been generated")).thenReturn(true);
        assert(batchJob.generateWeeklyReport("Sales", "roberjohn@yes.com")); //pass
    }

    @Test
    public void testGenerateWeekly_multipleRecipients(){
        when(emailSenderMock.sendEmailToMultipleRecipients(
                new String [] {"roberjohn@yes.com", "roberjohn2@no.com"},
                "The Revenues weekly report has been generated")).thenReturn(true);
        assert(batchJob.generateWeeklyReport("Revenues", new String [] {"roberjohn@yes.com", "roberjohn2@no.com"})); //pass
    }

    @Test
    public void testWeeklyReport_withAttachment(){
        when(emailSenderMock.sendEmailWithAttachment(
                "roberjohn@yes.com",
                "The Profits weekly report has been generated",
                "Sales have been going up !".getBytes())).thenReturn(true);
        assert(batchJob.sendWeeklyReport("Profits", "roberjohn@yes.com")); //pass
    }
}
```
**(D) Using verify() and veryNoInteraction() to Verify Method Invocation** \
The annotation can be used to verify whether the object under test has/hasn't invoked a specific method from the mock object. It can\
also be used to verify the order of invocation of methods.
```java

package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) 
public class WeeklyReportBatchJobTest {

    @Mock
    private EmailSender emailSenderMock; //the mock object

    @InjectMocks 
    WeeklyReportBatchJob batchJob; //the object under test

    @Test
    @DisplayName("Method inter-usage check.")
    public void testSomeMethod(){
            
      batchJob.generateWeeklyReport("TestReport", "recipient@email.com");
      
      verify(emailSenderMock).sendEmail(
              "recipient@email.com", 
              "The TestReport weekly report has been generated"
      ); //pass

      //Argument parsed to emailSenderMock.sendEmail() below must match the argument parsed to the first invocation of
        // the sendEmail() within the object under test(i.e. in this case the first invocation was inside the class).
      verifyNoInteraction(emailSenderMock).sendEmail(
              "recipient@email.com", 
              "The TestReport weekly report has been generated");//fail
    }
}
```
- The generateWeeklyReport() method of the object under test (WeeklyReportBatchJob) make use of the sendEmail method of the mock object (EmailSender).\
In order to verify this interaction, we invoke both methods within the test case and use `verify()` method to do the verification. The call to verify() \
method must pass the same parameter(as used by the object under test in its class) to the mock object. `verifyNoInteraction()` is the opposite of verify()
- By default, verify() will throw an exception if the object under test invokes a particular method of the mock object more than once. It requires exactly\
one invocation of the method of the mock object by the object under test. 


## 8: Unit Testing with Mocks: Mocking Exceptions & Using Spies
Mocks have no inherent state/behavior, and it has to be stubbed. Spies are built atop a real object of the class being mocked.
**(A) Stubbing Methods to Throw Exceptions**\
Only a method that returns a value can be mocked. A method that returns void and exceptions cannot be mocked using the constructs we have used so far.\
- Book and BookService classes, BookRepository(interface) 
```java
package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Book {
    private String id;
    private String name;
    private String authorName;

}
```
```java
package org.example;

import java.util.List;

public interface BookRepository {

    Book getBook(String id);

    List<Book> getBooks(String query);

    void addBook(Book book);

    void  updateBook(Book book);

    void deleteBook(String id);
}
```
```java
package org.example;

import java.util.List;

public class BookService {

    private BookRepository repository;

    public Book getBook(String id){
        return repository.getBook(id);
    }

    public List<Book> getBooks(String query){
        return repository.getBooks(query);
    }

    public void addBook(Book book){
        repository.addBook(book);
    }

    public void updateBook(Book book){
        repository.updateBook(book);
    }

    public void deleteBook(String id){
        repository.deleteBook(id);
    }
}
```
- Test of the BookService
```java
package org.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock; //mock object

    @InjectMocks
    //the annotation does:
    // 1.marks the property as the object under test
    // 2.search the object for all member variables that can be injected with mocks and inject them
    private BookService bookService;

    @Test
    public void testGetBook(){
        when(bookRepositoryMock.getBook(anyString())).thenThrow(new IllegalArgumentException("Wrongly formed id"));

        try{
            Book book = bookService.getBook("asd123"); //Instead of returning a book, the repositro
            fail();
        }catch(Exception ex){
            assertTrue(ex instanceof IllegalArgumentException); //pass
            System.out.println(ex.getMessage());
            assertEquals("Wrongly formed id", ex.getMessage()); //pass
        }

        /*
    - bookService is the object under test. bookService depends on bookRepository(check the BookService class).
    - Hence, bookRepository is the mock object.
    - The getBook() method of the mock object is stubbed such that it returns an exception instead of Book object
    - When the object under test(bookService) calls the getBook() method which uses
     */
    }
}

```

**(B) Throwing Exceptions from Stubbed Methods**
The following custom exception(checked exception) class is added to the BookService class. 
```java
package org.example;

public class BookNotFoundException extends Exception{

    public BookNotFoundException(String message){
        super(message);
    }
}
```
```java
package org.example;

import java.util.List;

public interface BookRepository {

    Book getBook (String id) throws BookNotFoundException;

    void deleteBook(String id) throws BookNotFoundException;
    
}
```
```java
package org.example;

import java.util.List;

public class BookService {

    private BookRepository repository;

    public Book getBook(String id) throws BookNotFoundException{
        return repository.getBook(id);
    }

  public void deleteBook(String id) throws BookNotFoundException{
    repository.deleteBook(id);
  }
    
}
```
- Test
```java
package org.example;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetBook() throws BookNotFoundException{
        when(bookRepositoryMock.getBook(anyString())).thenThrow(new BookNotFoundException("Book not found!"));

        try{
            Book book = bookService.getBook("asd123");
            fail();
        }catch(Exception ex){
            assertTrue(ex instanceof BookNotFoundException);
            System.out.println(ex.getMessage()); //Book not found!
            assertEquals("Book not found!", ex.getMessage()); //pass
        }

    }
}
```

**(C) Throwing Exceptions from Void Methods**
- Test
```java
package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepositoryMock;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testGetBook() throws BookNotFoundException{

        doThrow(new BookNotFoundException("Book not found once again !"))
                .when(bookRepositoryMock)
                        .deleteBook(anyString());
        try{
            bookService.deleteBook("asd123");
            fail();
        }catch(Exception ex){
            assertTrue(ex instanceof BookNotFoundException); //pass
            System.out.println(ex.getMessage()); //Book not found once again !
            assertEquals("Book not found once again !", ex.getMessage()); //pass
        }

    }
}
```
**(D) Creating Partial Mocks Using Spies**