package hack2017.android;

import android.app.ProgressDialog;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
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
                String emailInput = email.getText().toString(),
                       passwordInput = password.getText().toString(),
                       organizationInput = organization.getEditText().getText().toString();
                new LoginVerifier(MainActivity.this, MainActivity.this).verify(emailInput, passwordInput, organizationInput);
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
        String url = "http://192.168.1.6/Hack2017/database/login.php";
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
