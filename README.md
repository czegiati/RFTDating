# RFTDating 

## Backend

1. Install MySQL
2. Open MySQL comand line client and type in the following:
```
mysql> create database db_example;`  -- Creates the new database
mysql> create user 'springuser'@'%' identified by 'ThePassword';` -- Creates the user
mysql> grant all on db_example.* to 'springuser'@'%'; -- Gives all privileges to the new user on the newly created database
  
  ```

You must do the following before you start the application ( because they reset when you shut down your computer):
```
restart mysql from windows services (only if it is not configured to automatically launch the server)

mysql> SET GLOBAL time_zone = '+3:00';
```

3. Start the application
4. http://localhost:9090

## Frontend

1. In the /frontend folder:
```
$ ng install
$ ng serve
```
2. Open in browser: [B Mine Dating App](http://localhost:4200/)
