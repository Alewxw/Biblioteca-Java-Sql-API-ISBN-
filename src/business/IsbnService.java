package business;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import data.Carte;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class IsbnService {

    public Map<String, Object> getCarte (String isbn )
    {

        Map<String, Object> result = new HashMap<>();
        try {
            URL url = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            String key = "ISBN:" + isbn;
            JsonObject bookData = json.getAsJsonObject(key);

            String titlu = bookData.get("title").getAsString();

            JsonArray authors = bookData.getAsJsonArray("authors");

            String author = authors.get(0).getAsJsonObject().get("name").getAsString();

            String publishDate = bookData.get("publish_date").getAsString();
            String[] parts = publishDate.split(" ");

            int an = Integer.parseInt(parts[parts.length - 1]);

            result.put("title", titlu);
            result.put("author", author);
            result.put("publish_date", an);

        }
        catch (Exception e) {
            System.out.println("Eroare la apel API: " + e.getMessage());
        }

        return result;
    }

    public static void main ( String args[])
    {
        IsbnService isbnService = new IsbnService();
        isbnService.getCarte("9780140328721");
    }
}
