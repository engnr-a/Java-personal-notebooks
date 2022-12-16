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
        assertTrue(comparatorMock.compare("Jarda", "Tomas") < 0);
        assertEquals(comparatorMock.compare("Jarda", "Tomas"), -1);

    }
}
