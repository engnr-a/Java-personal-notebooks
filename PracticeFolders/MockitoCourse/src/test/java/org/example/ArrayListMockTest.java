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
