"# RFTDating" 


1,Install mysql



2,Open MySQL comand line client and type in the followings:

  mysql> create database db_example; -- Creates the new database
  
  mysql> create user 'springuser'@'%' identified by 'ThePassword'; -- Creates the user
  
  mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
  
  
  
  You must do the following before you start the application ( because they reset when you shut down your computer):
  
  restart mysql from windows servces (only if it is not configured to automatically launch the server)
  
  mysql> SET GLOBAL time_zone = '+3:00';
 
 
 
3,Start the application 
