package Handlers;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;

/**
 * Created by Greg on 5/27/2017.
 */



public class eventhandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String uri;

        String id;

        String authid;

        boolean eveid = false;

        boolean success = true;

        try {
            // Determine the HTTP request type (GET, POST, etc.).
            // Only allow POST requests for this operation.
            // This operation requires a POST request, because the
            // client is "posting" information to the server for processing.
            if (exchange.getRequestMethod().toLowerCase().equals("get")) {

                Headers reqHeaders = exchange.getRequestHeaders();
                // Check to see if an "Authorization" header is present
                if (reqHeaders.containsKey("Authorization")) {

                    // Extract the auth token from the "Authorization" header
                    authid = reqHeaders.getFirst("Authorization");

                    // Extract the JSON string from the HTTP request body
                    if (!authid.equals("")) {

                        // Convert
                        Services.EventResult request;
                        Model.Event event;
                        String respData;

                        Services.EventService service = new Services.EventService();
                        uri = exchange.getRequestURI().toString();
                        Gson gson = new Gson();


                        if (uri.contains("/") && uri.charAt(uri.length()-1) != '/') {
                            eveid = true;
                            String[] parts = uri.split("/");
                            id = parts[parts.length - 1];
                            event = service.eventreturn(id, authid );
                            respData = gson.toJson(event);
                            if(event.getEventID()==null){
                                success = false;
                            }
                        }
                        else {
                            eveid = false;
                            request = service.eventreturn(authid);
                            respData = gson.toJson(request);
                            if(request.getarray()==null){
                                success = false;
                            }
                        }

                        if(success) {
                            // Start sending the HTTP response to the client, starting with
                            // the status code and any defined headers.
                            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                            // Get the response body output stream.
                            OutputStream respBody = exchange.getResponseBody();
                            // Write the JSON string to the output stream.
                            writeString(respData, respBody);

                            // output stream, indicating that the response is complete.
                            exchange.getResponseBody().close();
                        }
                    }
                }
            }
            if (!success) {
                String out;

                messageresponse mess = new messageresponse();
                mess.setMessage("Bad Event Request");

                Gson gson = new Gson();

                out = gson.toJson(mess);
                // Get the response body output stream.

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                // Write the JSON string to the output stream.


                OutputStream respBody = exchange.getResponseBody();
                writeString(out, respBody);
                // We are not sending a response body, so close the response body
                // output stream, indicating that the response is complete.
                exchange.getResponseBody().close();
            }
        }
        catch (IOException e) {
            // Some kind of internal error has occurred inside the server (not the
            // client's fault), so we return an "internal server error" status code
            // to the client.
            exchange.sendResponseHeaders(HttpURLConnection.HTTP_INTERNAL_ERROR, 0);
            // We are not sending a response body, so close the response body
            // output stream, indicating that the response is complete.
            exchange.getResponseBody().close();

            // Display/log the stack trace
            e.printStackTrace();
        }

    }



    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }

}
