package test.json.texter;

import android.app.ProgressDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements JSONFetcherListener
{
    private EditText number;
    private String message;

    private boolean sendSMS = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        number = (EditText) findViewById(R.id.number);
    }

    public void click(View v)
    {
        sendSMS = true;
        fetchJSON();
    }

    public void parse(View view)
    {
        sendSMS = false;
        fetchJSON();
    }

    private void sendSMS()
    {
        String number = this.number.getText().toString();

        SmsManager smsManager = SmsManager.getDefault();

        try
        {
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(this, "SMS sent!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this, "SMS not sent.", Toast.LENGTH_SHORT).show();
        }

        this.number.setText("");
    }

    private void fetchJSON()
    {
        String url = "http://192.168.1.6/Hack2017/database/test.php";
        new JSONFetcher(this, new ProgressDialog(this)).execute(url);
    }

    @Override
    public void onFetchedJSON(String json)
    {
        message = "";

        String[] keys = new String[]
        {
            "test_id",
            "test_name",
            "test_message",
            "test_contact",
            "user_id"
        };

        ArrayList<HashMap<String, String>> items = JSONParser.parse(json, keys);
        for (HashMap<String, String> item : items)
        {
            for(String key : keys)
                message += item.get(key) + "\n";
        }

        Log.d("Parsed JSON", message);

        new AlertDialog.Builder(this)
                .setTitle("Parsed JSON")
                .setMessage(message)
                .show();

        if(sendSMS)
            sendSMS();
    }

    @Override
    public void onFetchFailed()
    {
        Toast.makeText(this, "Cannot fetch data. SMS not sent.", Toast.LENGTH_SHORT).show();
    }
}
