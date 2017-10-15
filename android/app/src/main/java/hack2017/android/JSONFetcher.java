package hack2017.android;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import hack2017.android.interfaces.JSONFetcherListener;

class JSONFetcher extends AsyncTask<String, Void, Void>
{
    private String json;
    private boolean fetched = false;

    private JSONFetcherListener listener;
    private ProgressDialog dialog;

    JSONFetcher(JSONFetcherListener listener, ProgressDialog dialog)
    {
        this.listener = listener;
        this.dialog = dialog;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
        dialog.setMessage("Please wait. Fetching Data...");
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    @Override
    protected Void doInBackground(String... url)
    {
        json = fetchJSON(url[0]);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        dialog.dismiss();

        if(fetched)
            listener.onFetchedJSON(json);
        else
            listener.onFetchFailed();
    }

    private String fetchJSON(String jsonURL)
    {
        String json = null;

        try
        {
            URL url = new URL(jsonURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            json = streamToJson(new BufferedInputStream(connection.getInputStream()));

            fetched = true;
        }
        catch (IOException e)
        {
            Log.d("JSONFetcher", "Cannot fetch JSON");
            e.printStackTrace();
        }

        return json;
    }

    //TODO: Make global static method in utility class
    private String streamToJson(InputStream stream)
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
