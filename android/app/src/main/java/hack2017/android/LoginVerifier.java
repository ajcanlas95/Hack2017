package hack2017.android;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;

import hack2017.android.interfaces.LoginVerifierListener;

class LoginVerifier
{
    private static WeakReference<Context> context;
    private static LoginVerifierListener listener;

    LoginVerifier(Context context, LoginVerifierListener listener)
    {
        LoginVerifier.context = new WeakReference<>(context);
        LoginVerifier.listener = listener;
    }

    void verify(String email, String password, String organization)
    {
        new LoginConnection().execute(email, password, organization);
    }

    private static class LoginConnection extends AsyncTask<String, Void, Void>
    {
        private ProgressDialog progress;
        private boolean isSuccess;

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
            progress = new ProgressDialog(context.get());
            progress.setCanceledOnTouchOutside(false);
            progress.setMessage("Please Wait...");
            progress.setIndeterminate(true);
        }

        @SuppressWarnings("ConstantConditions")
        @Override
        protected Void doInBackground(String... params)
        {
            URL url;
            HttpURLConnection connection = null;
            OutputStream output = null;
            InputStream input = null;
            try
            {
                JSONObject json = new JSONObject();
                json.put("email", params[0]);
                json.put("password", params[1]);

//                if(params[2] != null)
//                    json.put("organization", params[2]);

                String link = "http://192.168.1.28/Hack2017/website/backend/login.php",
                       jsonString = json.toString();

                url = new URL(link);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setFixedLengthStreamingMode(jsonString.getBytes().length);

                connection.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                connection.setRequestProperty("X-Requested-With", "XMLHttpRequest");

                connection.connect();

                output = new BufferedOutputStream(connection.getOutputStream());
                output.write(jsonString.getBytes());
                output.flush();

                input = connection.getInputStream();

                //TODO: Do something with response
                String response = streamToString(input);

                isSuccess = true;
            }
            catch (IOException | JSONException e)
            {
                e.printStackTrace();
                isSuccess = false;
            }
            finally
            {
                try
                {
                    output.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }

                connection.disconnect();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            super.onPostExecute(aVoid);
            progress.dismiss();
            listener.checkRequestStatus(isSuccess);
        }
    }

    private static String streamToString(InputStream stream)
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
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
