package hack2017.android;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import hack2017.android.interfaces.JSONFetcherListener;
import hack2017.android.interfaces.LoginVerifierListener;
import hack2017.android.texter.R;

public class MainActivity extends AppCompatActivity implements
        JSONFetcherListener,
        LoginVerifierListener
{
    private EditText number;
    private String message;

    private boolean sendSMS = true;

    @SuppressWarnings("ConstantConditions")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextInputEditText
              email = (TextInputEditText) ((TextInputLayout)
                findViewById(R.id.email)).getEditText(),
              password = (TextInputEditText) ((TextInputLayout)
                findViewById(R.id.password)).getEditText();
        final TextInputLayout organization = (TextInputLayout)
                findViewById(R.id.organization);
        email.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override
            public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    String inputText = email.getText().toString();
                    if(!inputText.contains("@"))
                        organization.setVisibility(View.VISIBLE);
                    else
                        organization.setVisibility(View.GONE);
                }
            }
        });

        findViewById(R.id.login).setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
//                String emailInput = email.getText().toString(),
//                       passwordInput = password.getText().toString(),
//                       organizationInput = organization.getEditText().getText().toString();
//                new LoginVerifier(MainActivity.this, MainActivity.this)
//                        .verify(emailInput, passwordInput, organizationInput);

                sendSMS();

                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });
    }

    @Override
    public void checkRequestStatus(boolean isSuccess)
    {
        Toast.makeText(this, isSuccess? "Request Success" : "Request Failed",
                Toast.LENGTH_SHORT).show();
    }

    private void click()
    {
        sendSMS = true;
        fetchJSON();
    }

    private void parse()
    {
        sendSMS = false;
        fetchJSON();
    }

    private void sendSMS()
    {
        message = "Message";

        PendingIntent sentPendingIntent = PendingIntent.getBroadcast
                (this, 0, new Intent("SMS_SENT"), 0);

        registerReceiver(new BroadcastReceiver()
        {
            @Override
            public void onReceive(Context context, Intent intent)
            {
                switch (getResultCode())
                {
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(MainActivity.this, "Generic Failure", Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(MainActivity.this, "No Service", Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(MainActivity.this, "Radio off", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        }, new IntentFilter("SMS_SENT"));

        String number = "09758718327";
        SmsManager smsManager = SmsManager.getDefault();
        try
        {
            smsManager.sendTextMessage(number, null, message, sentPendingIntent, null);
//            Toast.makeText(this, "SMS sent!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception ignored) {}

//        this.number.setText("");
    }

    private void fetchJSON()
    {
        String url = "http://192.168.1.6/Hack2017/database/login.php";
//        new JSONFetcher(this, new ProgressDialog(this)).execute(url);
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

//        HashMap<String, String> items = JSONParser.parse(json, keys);
//        for (HashMap<String, String> item : items)
//        {
//            for(String key : keys)
//                message += item.get(key) + "\n";
//        }

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
