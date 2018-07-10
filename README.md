# Checkout
*A simple Spring Boot Server to sum up prices of different shopping items*

To run the project, checkout the repository, go to the *target/* folder, and then type:

> java -jar checkout-0.1.jar

If you would like to rebuild the project, just go to the main project folder, and type:

> mvn clean install 
This will create a new .jar file.


# How is it working?

You send a POST request to **http://localhost:8080/checkout**  (via curl/PostMan/Insomnia/etc...)

Make sure to add an array to the body of the request, like:
> [apple, apple, orange]

The response will appear like:
> £ 1.45

UPDATE (STEP 2):
> Every second apple, and every 3rd orange is free from now. Customer satisfaction is important.

# Where are the main files?

Source Code of the logic:
> \src\main\java\com\checkout

JUnit tests:
> src\test\java\com\checkout

# Why is it a Java Server? Why not to keep it simple by making it as a Simple Java Application???

> That is easily convertable to a Simple Java Application.
> Shows more about my experience.
> Life is short, I take the risk.
