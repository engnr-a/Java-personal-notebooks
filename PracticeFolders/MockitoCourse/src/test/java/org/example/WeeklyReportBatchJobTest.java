package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) //with this annotation, we get rid of the AutoCloseable and the setup and teardown methods
public class WeeklyReportBatchJobTest {

    @Mock
    private EmailSender emailSenderMock; //the mock object

    @InjectMocks //This instantiates the object under test and automatically inject it with the mock object
    WeeklyReportBatchJob batchJob; //the object under test

    @Test
    @DisplayName("Method inter-usage check.")
    public void testSomeMethod(){

        batchJob.generateWeeklyReport("TestReport", "recipient@email.com");
        //the argyment to emailSenderMock must match the argument parsed to the first invocation of the sendEmail()
        verify(emailSenderMock).sendEmail("recipient@email.com", "The TestReport weekly report has been generated");
    }

}