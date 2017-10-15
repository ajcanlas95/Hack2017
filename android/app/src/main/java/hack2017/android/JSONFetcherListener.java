package hack2017.android;

interface JSONFetcherListener
{
    void onFetchedJSON(String json);
    void onFetchFailed();
}
