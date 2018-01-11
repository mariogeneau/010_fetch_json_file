//¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬
package com.mariogeneau.a010_fetch_json_file;
//¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬
import android.os.AsyncTask;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
//¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬
public class FetchDataClass extends AsyncTask {
    //~~~~~~~~~~~~~~
    // Strings for displaying JSON data
    String data_for_lines = "";
    String data_parsed = "";
    //~~~~~~~~~~~~~~
    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            // Url object
            URL url = new URL("http://mariogeneau.com/json/emp.json");
            // Connection object
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            // Stream object
            InputStream inputStream = httpURLConnection.getInputStream();
            // Reader object
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // String for reading lines
            String line = "";
            // As long as there's lines...
            while (line != null) {
                // Read lines
                line = bufferedReader.readLine();
                // Add line string for lines
                data_for_lines += line;
            }
            // JSONArray object from string of lines
            JSONArray jsonArray = new JSONArray(data_for_lines);
            // Loop to iterate over each element in JSONArray
            for (int i = 0; i < jsonArray.length(); i++) {
                // JSONObject objects from elements in JSONArray object
                JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                // String concatenation from every properties of JSONObject objects
                data_parsed +=  "Name : " + jsonObject.get("name") + "\n" +
                                "Password : " + jsonObject.get("password") + "\n" +
                                "Contact : " + jsonObject.get("contact") + "\n" +
                                "Country : " + jsonObject.get("country") + "\n\n";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    //~~~~~~~~~~~~~~
    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        // Display the string concatenated
        MainActivity.data_label.setText(data_parsed);
    }
    //~~~~~~~~~~~~~~
}
//¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬¬