# Najdi cimer

## Description
This repository is published with the intention to upgrade the initial project found at /dMeshko/WP_Project.
The project represents the source code for a single-page web application intended for simple & easy finding roommates by posting listings.

![Najdi cimer main page](http://i.imgur.com/A4q8DEe.png)

## Development
Several technologies were used for the application development. 

The Spring Web MVC framework to build our RESTful API and the magic of AngularJS to create the user interaction. Other technologies include JPA, MySQL, twitter-bootstrap, working with JSON data and few others.

## How to run?
The web-programming-spring-mvc-starter is a Spring Boot application, so you're one button away of deploying our API! Open the project inside any IDE and run the 'NajdiCimer' application configuration.

Once deployed the api can be found on 

    http://localhost:8080
Next, we move on to the wp-angular-starter which is using the node package manager to install some important modules our application is going to need to start running, like gulp.js. So, using any command line tool, run

    npm install
Now that gulp is installed we can run tasks. Inside the gulp.js file, run the 'build' task, and after it finishes building run the 'default' task which is consisted of a 'serve' and a 'watch' task. The 'serve' deploys our application on the specified port and the 'watch' is there to "spy" for any changes we make to the code and apply them to our deployed instance. The application will be deployed on 

    http://localhost:8000
but you won't be able to see anything yet since we still need our bower components.
So, lastly, we need to install the components angular needs using the bower package manager. Again, using any command line tool run

    bower install
and you should be able to see our welcome screen.

## Who are we?
We are three colleagues --web enthusiasts and are always open to talk about anything web-related.

