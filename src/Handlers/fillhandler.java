package Handlers;

import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import com.sun.net.httpserver.*;

/**
 * Created by Greg on 5/27/2017.
 */


public class fillhandler implements HttpHandler  {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String uri;

        String username;

        String generations ="4";

        String respData;

        int numpersonsadded;

        int numeventsadded;

        boolean success = false;

        try {
            // Determine the HTTP request type (GET, POST, etc.).
            // Only allow POST requests for this operation.
            // This operation requires a POST request, because the
            // client is "posting" information to the server for processing.
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                Headers reqHeaders = exchange.getRequestHeaders();
                // Check to see if an "Authorization" header is present

                uri = exchange.getRequestURI().toString();

                String[] parts = uri.split("/");
                if(parts.length == 4) {
                    generations = parts[3];
                }
                username = parts[2];


                Services.FillRequest request = new Services.FillRequest();
                request.setGenerations(generations);
                request.setUserName(username);

                Services.FillService service = new Services.FillService(request);
                success = service.fillgenerations();

            }
            if (!success) {
                respData = "Fill Error";
                String out;

                messageresponse mess = new messageresponse();
                mess.setMessage(respData);

                Gson gson = new Gson();

                out = gson.toJson(mess);
                // The HTTP request was invalid somehow, so we return a "bad request"
                // status code to the client.
                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                OutputStream respBody = exchange.getResponseBody();
                // Write the JSON string to the output stream.
                writeString(out, respBody);
                // We are not sending a response body, so close the response body
                // output stream, indicating that the response is complete.
                exchange.getResponseBody().close();
            }
            else{
                numpersonsadded = numanceestors(Integer.parseInt(generations));
                numeventsadded = numpersonsadded*2;
                respData = "Successfully added " + numpersonsadded  + " persons and " + numeventsadded + " events to the database";
                // Start sending the HTTP response to the client, starting with
                // the status code and any defined headers.

                String out;

                messageresponse mess = new messageresponse();
                mess.setMessage(respData);

                Gson gson = new Gson();

                out = gson.toJson(mess);

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                // Get the response body output stream.
                OutputStream respBody = exchange.getResponseBody();
                // Write the JSON string to the output stream.
                writeString(out, respBody);

                // output stream, indicating that the response is complete.
                exchange.getResponseBody().close();
                success = true;
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

    private int numanceestors(int generations){
        int sum = 0;
        for(int i = 0; i < generations; i++){
            sum += Math.pow(2,i+1);
        }
        sum = sum + 1;
        return sum;
    }


    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }



}


