package winston.weekendlab_api;

//TODO For Flickr: Implement key: a349634211c1df1171b6a4070099f108 | Secret: 2c9abbdf5789a87b
//TODO For MTG: http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=[1-368970]&type=card

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class api_flickr {
    private static api_flickr instance;
    private static ApiResponseHandler responseHandler;

    //private api_flickr(){}

    //implements Response Hndler
    public static api_flickr getInstance(ApiResponseHandler handler){
        responseHandler = handler;
        if (instance == null) {
            instance = new api_flickr();
        } return instance;}//end handler

    public void jsonRequest(int urlIndex){
        AsyncHttpClient client = new AsyncHttpClient();
        //TODO: retrieve multiverseid &&|| image
        client.get("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + jsonRequest + "&type=card", null, new JsonHttpResponseHandler(){
            //TODO: Try/Catch
            public void onSuccess(int errorCode, Header[] headers, JSONObject response){
                try {
                    responseHandler.handleResponse(response.getString("multiverseid"));
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public interface ApiResponseHandler {
        void handleResponse(String response);
    }
}

