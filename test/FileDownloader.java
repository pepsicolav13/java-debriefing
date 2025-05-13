import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class FileDownloader {

    public static void downloadFile(String fileURL, String saveDir) throws IOException {
        // Create a URL object from the file URL
        URL url = new URL(fileURL);
        
        // Open a connection to the URL
        URLConnection urlConnection = url.openConnection();
        
        // Get the input stream of the file
        try (InputStream inputStream = urlConnection.getInputStream()) {
            // Get the file name from the URL
            String fileName = "java-logo.svg";
            
            // Create the file output stream
            try (FileOutputStream outputStream = new FileOutputStream(saveDir + File.separator + fileName)) {
                // Buffer to read the file in chunks
                byte[] buffer = new byte[4096];
                int bytesRead;
                
                // Read the file and write it to disk
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }
        }
    }

    public static void main(String[] args) {
        // URL of the file to download
        String fileURL = "https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg";
        
        // current directory
        String saveDir = "./";
        
        try {
            downloadFile(fileURL, saveDir);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
