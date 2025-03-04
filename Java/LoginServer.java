
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.InetSocketAddress;

public class LoginServer {

    public static void main(String[] args) throws Exception {
        // Create an HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/login", new LoginHandler());
        server.start();
        System.out.println("Server started on http://localhost:8080");
    }

    // Handler for the login API
    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Add CORS headers
            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*"); // Allow all origins
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");

            // If the request method is OPTIONS (preflight request), just return a 200 response
            if ("OPTIONS".equals(exchange.getRequestMethod())) {
                exchange.sendResponseHeaders(200, -1); // No body
                return;
            }

            if ("POST".equals(exchange.getRequestMethod())) {
                // Read the body of the POST request
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                StringBuilder requestBody = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    requestBody.append(line);
                }
                // Extract username and password from the body
                String[] credentials = requestBody.toString().split("&");
                String username = credentials[0].split("=")[1];
                String password = credentials[1].split("=")[1];

                // Check login credentials using the authenticate method
                boolean isAuthenticated = Login.authenticate(username, password);

                // Send a response back
                String response = isAuthenticated ? "Login successful" : "Invalid credentials";
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }
}
