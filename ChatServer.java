import java.io.IOException;
import java.net.URI;

class ChatHandler implements URLHandler {
  String chatHistory = "";

  public String handleRequest(URI url) {

    // expect /add-message?s=<string>&user=<string>
    if (url.getPath().equals("/add-message")) {
      String[] params = url.getQuery().split("&");
      String[] shouldBeString = params[0].split("=");
      String[] shouldBeUser = params[1].split("=");
      if (shouldBeString[0].equals("s") && shouldBeUser[0].equals("user")) {
        String string = shouldBeString[1];
        String user = shouldBeUser[1];
        this.chatHistory += user + ": " + string + "\n";
        return this.chatHistory;
      } else {
        return "Invalid parameters: " + String.join("&", params);
      }
    }
    return this.chatHistory;
  } 
}

class ChatServer {
  public static void main(String[] args) throws IOException {
    int port = Integer.parseInt(args[0]);
    Server.start(port, new ChatHandler());
  }
}
