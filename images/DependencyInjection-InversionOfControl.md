# Dependency Injection Design Pattern

- An application with two classes.
- The first class, EmailService does the actual sending of the email.
- The second class, `AppUsingEmailService` 
   - implements some logic to validate the content of the email.
   - if the content is valid, it then uses an instance of the `EmailService` class to send the email.




Dependency injection simply means receiving collaborators as constructor parameters instead of fetching them ourselves.