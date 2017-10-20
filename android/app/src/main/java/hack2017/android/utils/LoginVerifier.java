package hack2017.android.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import hack2017.android.interfaces.LoginVerifierListener;

public class LoginVerifier
{
    private static WeakReference<Context> context;
    private static LoginVerifierListener listener;

    public LoginVerifier(Context context, LoginVerifierListener listener)
    {
        LoginVerifier.context = new WeakReference<>(context);
        LoginVerifier.listener = listener;
    }

    public void verify(String email, String password, String organization)
    {
        new LoginConnection().execute(email, password, organization);
    }

    private static class LoginConnection extends AsyncTask<String, Void, Void>
    {
        private ProgressDialog progress;
        private boolean isSuccess;
        private String response;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progress = new ProgressDialog(context.get());
            progress.setCanceledOnTouchOutside(false);
            progress.setMessage("Please Wait...");
            progress.setIndeterminate(true);
        }

        //TODO: POST method not yet working

        @SuppressWarnings("ConstantConditions")
        @Override
        protected Void doInBackground(String... params)
        {
            URL url;
            HttpURLConnection connection;
            OutputStream output;
            try
            {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("name", params[0]);
                jsonObject.put("password", params[1]);
                JSONObject json = new JSONObject();
                json.put("action", jsonObject);

                Log.d("json", json.toString());

//                if(params[2] != null)
//                    jsonObject.put("organization", params[2]);

                String link = "https://jomillematildo.000webhostapp.com/website/backend/login.without.org.php",
                       jsonString = json.toString();

//                RequestQueue requestQueue = Volley.newRequestQueue(context.get());
//
//                JsonObjectRequest jsonRequest = new JsonObjectRequest(link, jsonObject,
//                        new Response.Listener<JSONObject>()
//                        {
//                            @Override
//                            public void onResponse(JSONObject response)
//                            {
//                                Log.d("response", "server: " + response.toString());
//                                LoginConnection.this.response = response.toString();
//                            }
//                        },
//                        new Response.ErrorListener()
//                        {
//                            @Override
//                            public void onErrorResponse(VolleyError error)
//                            {
//                                response = "error";
//                            }
//                        });
//
//                requestQueue.add(jsonRequest);

//                link += "?"
//                     + "name=" + params[0] + "&"
//                     + "password=" + params[1];
//
//                url = new URL(link);
//                connection = (HttpURLConnection) url.openConnection();
//                connection.setRequestMethod("GET");

//
//                if(params[2] != null)
//                    link += "&organization=" + params[2];
//
//                HttpClient client = new DefaultHttpClient();
//
//                StringEntity entity = new StringEntity(jsonString);
//                entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/jsonObject"));
//
//                HttpPost post = new HttpPost(link);
//                post.setHeader("jsonObject", jsonString);
//                post.setEntity(entity);
//
//                HttpResponse httpResponse = client.execute(post);
//
//
                url = new URL(link);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setFixedLengthStreamingMode(jsonString.getBytes().length);

                connection.setRequestProperty("Content-Type", "application/jsonObject");
                connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

                output = new BufferedOutputStream(connection.getOutputStream());
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output));
                writer.write(jsonString);
                writer.flush();
                writer.close();
                output.close();

                connection.connect();

                response = streamToString(connection.getInputStream());

                Log.d("response", "server: " + response);

                isSuccess = true;
            }
            catch (IOException | JSONException e)
            {
                e.printStackTrace();
                isSuccess = false;
            }
            finally
            {
//                try
//                {
//                    output.close();
//                }
//                catch (IOException e)
//                {
//                    e.printStackTrace();
//                }
//
//                connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            progress.dismiss();
            listener.checkRequestStatus(isSuccess);

//            String message = "";
//            Map<String, String> parsedMap = Parser.parse(response,
//                    new String[] { "email", "password" });
//
//            for(Map.Entry<String, String> entry : parsedMap.entrySet())
//                message += entry.getKey() + ": " + entry.getValue() + "\n";

            new AlertDialog.Builder(context.get())
                           .setTitle("Response")
                           .setMessage(response)
                           .show();
        }
    }

    private static String streamToString(InputStream stream)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

//        try
//        {
//            return reader.readLine();
//        }
//        catch (IOException e)
//        {
//            return "nothing";
//        }

        StringBuilder stringBuilder = new StringBuilder();

        String line;
        try
        {
            while ((line = reader.readLine()) != null)
                stringBuilder.append(line).append('\n');
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                stream.close();
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
