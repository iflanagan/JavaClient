
Clone the repo
Open the project in intellij (its the best IDE anyway)
Click on file->Project Structure->libraries->click on + -> Click on Java and browse to all the included jar files 
Click apply and click save 
Re-build the project and make sure all errors are gone 

Create a rollbar project and while waiting for the data to show up make note of the token
Open the MyConfig.java file and paste the token where it says '<post_client_item>'
Save the file 

In order to run the client make sure the server is running 

Open the main3.java file right click in the class and click run Main3.main() 

Enter some text and hit return 

See the error generated locally and in Rollbar.




