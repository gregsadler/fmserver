package Handlers;


import java.io.*;
import java.net.*;
import com.sun.net.httpserver.*;


/**
 * Created by Greg on 5/26/2017.
 */

public class server {
    // The maximum number of waiting incoming connections to queue.
    // While this value is necessary, for our purposes it is unimportant.
    // Take CS 460 for a deeper understanding of what it means.
    private static final int MAX_WAITING_CONNECTIONS = 12;

    // Java provides an HttpServer class that can be used to embed
    // an HTTP server in any Java program.
    // Using the HttpServer class, you can easily make a Java
    // program that can receive incoming HTTP requests, and respond
    // with appropriate HTTP responses.
    // HttpServer is the class that actually implements the HTTP network
    // protocol (be glad you don't have to).
    // The "server" field contains the HttpServer instance for this program,
    // which is initialized in the "run" method below.
    private HttpServer server;

    // This method initializes and runs the server.
    // The "portNumber" parameter specifies the port number on which the
    // server should accept incoming client connections.
    private void run(String portNumber) {

        // Since the server has no "user interface", it should display "log"
        // messages containing information about its internal activities.
        // This allows a system administrator (or you) to know what is happening
        // inside the server, which can be useful for diagnosing problems
        // that may occur.
        System.out.println("Initializing HTTP Server");

        try {
            // Create a new HttpServer object.
            // Rather than calling "new" directly, we instead create
            // the object by calling the HttpServer.create static factory method.
            // Just like "new", this method returns a reference to the new object.
            server = HttpServer.create(
                    new InetSocketAddress(Integer.parseInt(portNumber)),
                    MAX_WAITING_CONNECTIONS);
        }
        catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // Indicate that we are using the default "executor".
        // This line is necessary, but its function is unimportant for our purposes.
        server.setExecutor(null);

        // Log message indicating that the server is creating and installing
        // its HTTP handlers.
        // The HttpServer class listens for incoming HTTP requests.  When one
        // is received, it looks at the URL path inside the HTTP request, and
        // forwards the request to the handler for that URL path.
        System.out.println("Creating contexts");


        // Create and install the HTTP handler for the "/login" URL path.
        // When the HttpServer receives an HTTP request containing the
        // "/login" URL path, it will forward the request to loginhandler
        // for processing.
        server.createContext("/user/login", new loginhandler());



        // Create and install the HTTP handler for the "/register" URL path.
        // When the HttpServer receives an HTTP request containing the
        // "/register" URL path, it will forward the request to registerhandler
        // for processing.
        server.createContext("/user/register", new registerhandler());



        // Create and install the HTTP handler for the "/clear" URL path.
        // When the HttpServer receives an HTTP request containing the
        // "/clear" URL path, it will forward the request to clearhandler
        // for processing.
        server.createContext("/clear", new clearhandler());


        // Create and install the HTTP handler for the "/fill" URL path.
        // When the HttpServer receives an HTTP request containing the
        // "/fill" URL path, it will forward the request to fillhandler
        // for processing.
        server.createContext("/fill/", new fillhandler());


        // Create and install the HTTP handler for the "/load" URL path.
        // When the HttpServer receives an HTTP request containing the
        // "/load" URL path, it will forward the request to loadhandler
        // for processing.
        server.createContext("/load", new loadhandler());


        // Create and install the HTTP handler for the "/person" URL path.
        // When the HttpServer receives an HTTP request containing the
        // "/person" URL path, it will forward the request to personhandler
        // for processing.
        server.createContext("/person", new personhandler());


        // Create and install the HTTP handler for the "/event" URL path.
        // When the HttpServer receives an HTTP request containing the
        // "/event" URL path, it will forward the request to eventhandler
        // for processing.
        server.createContext("/event", new eventhandler());


        // Create and install the HTTP handler for the "/" URL path.
        // When the HttpServer receives an HTTP request containing the
        // "/" URL path, it will forward the request to filehandler
        // for processing.
        server.createContext("/", new filehandler());



        // Log message indicating that the HttpServer is about the start accepting
        // incoming client connections.
        System.out.println("Starting server");

        // Tells the HttpServer to start accepting incoming client connections.
        // This method call will return immediately, and the "main" method
        // for the program will also complete.
        // Even though the "main" method has completed, the program will continue
        // running because the HttpServer object we created is still running
        // in the background.
        server.start();

        // Log message indicating that the server has successfully started.
        System.out.println("Server started");
    }

    // "main" method for the server program
    // "args" should contain one command-line argument, which is the port number
    // on which the server should accept incoming client connections.
    public static void main(String[] args) {
        String portNumber = args[0];
        String duration = args[1];
        Long dur = Long.parseLong(duration);
        Model.AuthToken.setduration(dur);
        new server().run(portNumber);
    }

}
