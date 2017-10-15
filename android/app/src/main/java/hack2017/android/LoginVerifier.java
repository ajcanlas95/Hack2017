package hack2017.android;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

class LoginVerifier
{
    void verify(String email, String password, String organization)
    {
        new LoginConnection().execute(email, password, organization);
    }

    private static class LoginConnection extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... params)
        {
            URL url;
            try
            {
                JSONObject json = new JSONObject();
                json.put("email", params[0]);
                json.put("password", params[1]);

                if(params[2] != null)
                    json.put("organization", params[2]);

                String link = "http://192.168.1.28/Hack2017/website/backend/login.php";

                url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                connection.setDoOutput(true);

                DataOutputStream output = new DataOutputStream(connection.getOutputStream());
                output.writeBytes("credentials=" + json.toString());
                output.flush();
                output.close();

                Log.d("connection", "success");
            }
            catch (IOException | JSONException e)
            {
                e.printStackTrace();
            }
            return null;
        }
    }
}
