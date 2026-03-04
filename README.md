Simple Rest API backend app created using Spring Boot for managing a library with PostgreSQL and Spring Data JPA that I created to practice using these technologies.

The app currently has 4 Entities - Authors, Books, Members and Borrowings, with members being able to borrow books written by authors.

The app supports basic CRUD operations with improved search implemented using Spring Data JPA Speciffications for better filtering based on all possible fields.

How to use the app:
- Clone the Repository
- Make sure you have PostgreSQL locally installed
- Create a database named "library" on port 5432(default). If you want to change the port, make sure you also change it in the application.properties file
- Make sure you update your username and password in the application.properties file if you have a different user or a different password (most likely)
- Run the Application and test the API with tools like Postman or cURL

If you do not wish to use PostgreSQL, a simple way of using the app is by using the H2 database, but be aware that this type of database does not actually store
the data after you close the app. To use H2 database instead simply delete all lines from application.properties except for the first line "spring.application.name=LibraryManagement".

Future development:
- Currently the app has only a backend, a nice frontend to display the data graphically would be nice
- Real user and admin authentification using something like Spring Security.
- The app is quite simplistic, a real library might need more entities such as computers, staff, maybe even musical albums and many other

If you wish to contribute to the app, feel free to fork it and add whatever features you want.
