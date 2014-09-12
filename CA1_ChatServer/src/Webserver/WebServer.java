package WebServer;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import server.ClientHandler;

/**
 *
 * @author Bettina
 */
public class WebServer {

    static int port = 8080; 
    //static String ip = "127.0.0.1";
    static String ip = "localhost";

    public static void main(String[] args) throws Exception
    {
        HttpServer server = HttpServer.create(new InetSocketAddress(ip, port), 0);

        server.createContext("/startpage", new RequestHandler("index.html"));
        server.createContext("/documentation", new RequestHandler("ca1_documention.txt"));
        
        server.createContext("/online", new OnlineUsers());
        server.createContext("/chatlog", new Chatlog());
        //server.setExecutor(null); // Use the default executor
        server.start();
        System.out.println("Server started, listening on port: " + port);

    }

    static class RequestHandler implements HttpHandler {

        public RequestHandler(String filename)
        {
            this.filename = filename;
        }

        String filename;
        String contentFolder = "public/";

        @Override
        public void handle(HttpExchange he) throws IOException
        {
            File file = new File(contentFolder + filename);
            byte[] bytesToSend = new byte[(int) file.length()];
            try {
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                bis.read(bytesToSend, 0, bytesToSend.length);
            } catch (IOException ie) {
                ie.printStackTrace();
            }
            he.sendResponseHeaders(200, bytesToSend.length);
            try (OutputStream os = he.getResponseBody()) {
                os.write(bytesToSend, 0, bytesToSend.length);
            }
        }

    }

    static class OnlineUsers implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException
        {
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html>\n");
            sb.append("<head>\n");
            sb.append("<title>Online users: CA1 - Chat Server/Client Project</title>\n");
            sb.append("<meta charset='UTF-8'>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
            sb.append("<h1>Online Users</h1>\n");
            
                       
            sb.append("<p>There is currently :  </p>\n");
            sb.append("</body>\n");
            sb.append("</html>\n");
            String response = sb.toString();
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", "text/html");
            he.sendResponseHeaders(200, response.length());
            try (PrintWriter pw = new PrintWriter(he.getResponseBody())) {
                pw.print(response);

            }

        }

    }
    static class Chatlog implements HttpHandler {

        @Override
        public void handle(HttpExchange he) throws IOException
        {
            StringBuilder sb = new StringBuilder();
            sb.append("<!DOCTYPE html>\n");
            sb.append("<html>\n");
            sb.append("<head>\n");
            sb.append("<title>Online users: CA1 - Chat Server/Client Project</title>\n");
            sb.append("<meta charset='UTF-8'>\n");
            sb.append("</head>\n");
            sb.append("<body>\n");
            sb.append("<h1></h1>\n");
            sb.append("</body>\n");
            sb.append("</html>\n");
            String response = sb.toString();
            Headers h = he.getResponseHeaders();
            h.add("Content-Type", "text/html");
            he.sendResponseHeaders(200, response.length());
            try (PrintWriter pw = new PrintWriter(he.getResponseBody())) {
                pw.print(response);

            }

        }

    }
}
