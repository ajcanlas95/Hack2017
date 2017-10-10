package test.json.texter;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

class JSONParser
{
    static ArrayList<HashMap<String, String>> parse(String json, String[] keys)
    {
        ArrayList<HashMap<String, String>> list = new ArrayList<>();

        if(json != null)
        {
            try
            {
                JSONObject jsonObject = new JSONObject(json);

                HashMap<String, String> pair = new HashMap<>();

                for(String key : keys)
                    pair.put(key, jsonObject.getString(key));


                list.add(pair);
            }
            catch (JSONException e)
            {
                Log.d("JSONParser", "Cannot parse JSON");
                e.printStackTrace();
            }
        }

        return list;
    }
}
