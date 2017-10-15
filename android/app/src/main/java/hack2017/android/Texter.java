package hack2017.android;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import test.json.texter.R;

public class Texter extends Fragment
{
    public Texter() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.fragment_texter, container, false);
    }

    private void sendSMS(String number, String message)
    {
        SmsManager smsManager = SmsManager.getDefault();

        try
        {
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(getContext(), "SMS sent!", Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getContext(), "SMS not sent.", Toast.LENGTH_SHORT).show();
        }
    }
}
