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
