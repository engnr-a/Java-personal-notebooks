package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.nio.charset.StandardCharsets;

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


    public boolean sendWeeklyReport(String reportType, String emailRecipient){

        String reportCopy = "Sales have been going up !";

        return emailSender.sendEmailWithAttachment(
                emailRecipient, String.format("The %s weekly report has been generated", reportType),
                reportCopy.getBytes()
        );

    }


}
