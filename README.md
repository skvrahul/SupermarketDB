# SupermarketDB
Application made for DBMS Mini project to facilitate management of a supermarket DB via an easy to use frontend interface(Native Android).   
Data is stored locally using **Room Persistence Library** which is an abstraction layer over **SQLite**. Raw **SQLite** queries are however used when added functionality
 is required like in the case of triggers(which are not provided out of the box).   
We have also integrated SMS Alerts for the customer once the billing is done using an HTTP API provided by an SMS Gateway.
