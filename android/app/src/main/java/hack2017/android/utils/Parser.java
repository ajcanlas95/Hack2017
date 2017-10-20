package hack2017.android.utils;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

class Parser
{
    static HashMap<String, String> parse(String json, String[] keys)
    {
        HashMap<String, String> map = new HashMap<>();

        if(json != null)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(json);

                for(String key : keys)
                    map.put(key, jsonObject.getString(key));
            }
            catch (JSONException e)
            {
                Log.d("Parser", "Cannot parse JSON");
                e.printStackTrace();
            }
        }

        return map;
    }
}
