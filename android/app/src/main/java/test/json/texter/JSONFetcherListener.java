package test.json.texter;

interface JSONFetcherListener
{
    void onFetchedJSON(String json);
    void onFetchFailed();
}
