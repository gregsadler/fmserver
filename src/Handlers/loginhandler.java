package Handlers;

import java.io.*;
import java.net.*;

import Model.AuthToken;
import Services.LoginRequest;
import Services.LoginService;
import com.sun.net.httpserver.*;
import com.google.gson.*;

/**
 * Created by Greg on 5/27/2017.
 */

public class loginhandler implements HttpHandler  {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        boolean success = false;

        try {
            // Determine the HTTP request type (GET, POST, etc.).
            // Only allow POST requests for this operation.
            // This operation requires a POST request, because the
            // client is "posting" information to the server for processing.
            if (exchange.getRequestMethod().toLowerCase().equals("post")) {

                // Get the HTTP request headers
                Headers reqHeaders = exchange.getRequestHeaders();
                // Extract the JSON string from the HTTP request body

                // Get the request body input stream
                InputStream reqBody = exchange.getRequestBody();
                // Read JSON string from the input stream
                String reqData = readString(reqBody);

                // Display/log the request JSON data
                System.out.println(reqData);

                // TODO: Attempt to Login a user based on data

                Gson gson = new Gson();

                // Convert
                LoginRequest request = gson.fromJson(reqData,Services.LoginRequest.class);

                Services.LoginService service = new Services.LoginService(request);

                Model.AuthToken toke = service.servicelogin();

                if(toke.getAuthToken()==null){
                    success = false;
                }

                else{
                    // Convert object to JSON string
                    String respData = gson.toJson(toke);


                    // Start sending the HTTP response to the client, starting with
                    // the status code and any defined headers.
                    exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

                    // Get the response body output stream.
                    OutputStream respBody = exchange.getResponseBody();
                    // Write the JSON string to the output stream.
                    writeString(respData, respBody);

                    // output stream, indicating that the response is complete.
                    exchange.getResponseBody().close();
                    success = true;
                }
            }

            if (!success) {

                String out;

                messageresponse mess = new messageresponse();
                mess.setMessage("Bad login Request");

                Gson gson = new Gson();

                out = gson.toJson(mess);

                // Get the response body output stream.

                exchange.sendResponseHeaders(HttpURLConnection.HTTP_BAD_REQUEST, 0);

                // Write the JSON string to the output stream.


                OutputStream respBody = exchange.getResponseBody();
                writeString(out, respBody);

                // The HTTP request was invalid somehow, so we return a "bad request"
                // status code to the client.
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


    /*
		The readString method shows how to read a String from an InputStream.
	*/
    private String readString(InputStream is) throws IOException {
        StringBuilder sb = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sb.append(buf, 0, len);
        }
        return sb.toString();
    }


    private void writeString(String str, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(str);
        sw.flush();
    }


}
