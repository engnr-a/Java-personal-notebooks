package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;

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

    public static void main(String [] args){
        Double [] doubleHolder = {5.6, 5.91};
        System.out.println(Arrays.toString(doubleHolder));
    }

}
