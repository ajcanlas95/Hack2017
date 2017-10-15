package hack2017.android.interfaces;

public interface JSONFetcherListener
{
    void onFetchedJSON(String json);
    void onFetchFailed();
}
