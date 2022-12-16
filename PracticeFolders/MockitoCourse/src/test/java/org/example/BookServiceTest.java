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
            assertTrue(ex instanceof BookNotFoundException);
            System.out.println(ex.getMessage()); //Book not found once again !
            assertEquals("Book not found once again !", ex.getMessage()); //pass
        }

    }
}
