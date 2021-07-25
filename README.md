# Infonote Mockito Demo
This repo contains a tutorial on using [Mockito](https://site.mockito.org/) to create mocks for unit testing a sample note taking application (Infonote).

### About Infonote
Infonote is a simple note taking application. User can create, read, update and delete notes. The intention is to create a series of 'demo' apps that show the gradual evolution of building a fully functional, reliable note taking application. The application is built based on user stories defined in each 'demo' repository


### User Stories

The following is an example set of User Stories for a typical note creation api:

- **Story-1**: If User details are unknown then throw an error message
    ```
    Given user details are unknown or missing
    When I create a note
    Then I should see an error message as `IllegalArgumentException`
    ```

- **Story-2**: Validate the user with read role
    ```
    Given a user with read role
    When I call a method to validate if the user can create a note
    Then I should return false
    ```

- **Story-3**: Validate the user with write role
    ```
    Given a user with write role
    When I call a method to validate if the user can create a note
    Then I should return true
    ```

- **Story-4**: A read only user should not be able to create a note
    ```
    Given a user with 'read' role
    when I create a note
    Then I should see an error message as `UnauthorizedException`
    ```

- **Story-5**: Registered user should be able to create a note successfully
    ```
    Given a user with 'write' role
    when I create a note
    Then I should be able to create a note successfully
    ```

- **Story-6**: Registered user should be able to create a note successfully and should have all relevant information stored in the database
    ```
    Given a user with 'write' role
    when I create a note
    Then I should be able to create a note successfully with `Author`, `createdAt`  fields
    ```

- **Story-7**: Registered user should not be able to create a title containing reserved keywords
    ```
    Given a note title containing reserved keywords
    When I validate the note title
    Then I should return false
    ```

- **Story-8**: Registered user should not be able to create a title of just one character
    ```
    Given a note title with just one character
    When I validate the note title
    Then I should return false
    ```

- **Story-9**: Registered user should not be able to create a valid title
    ```
    Given a note title which does not contain reserved words nor has just one character
    When I validate the note title
    Then I should return true
    ```

Given the above user stories as a base, we will build a simple api to create a note and use Mockito to mock the different layers of the application. We will also see how to use the different features of Mockito (like `ArgumentMatchers`, `thenAnswer()`, `verify()` etc) in our unit testing

The following are the main classes:

`Note` - Base Entity class containing the attributes of a note. E.g. `title`, `body`, `createdAt` etc

`NoteService` - Service class with methods for creating /deleting note

`NoteRepository` - Repository class to store the notes. No Actual implementation required for our use case

`NoteRule` - Set of business rules for note creation.

`NoteUtil` - Static helper methods

`User` - User information available when creating a note
