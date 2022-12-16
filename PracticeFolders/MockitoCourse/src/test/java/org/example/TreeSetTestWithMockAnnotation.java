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

    @Mock //the invocation of the static 'mock' method will be performed by mockito. Check previous section to see the use of the 'mock' method
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
