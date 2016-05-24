package winston.weekendlab_api;

//TODO For Flickr: Implement key: a349634211c1df1171b6a4070099f108 | Secret: 2c9abbdf5789a87b
//TODO For MTG: http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=[1-410064]&type=card

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ScryApi {
    private static ScryApi instance;
    private static ApiResponseHandler responseHandler;

    //private ScryApi(){}

    //implements Response Hndler
    public static ScryApi getInstance(ApiResponseHandler handler){
        responseHandler = handler;
        if (instance == null) {
            instance = new ScryApi();
        } return instance;}//end handler

    public void doRequest(int urlIndex){
        AsyncHttpClient client = new AsyncHttpClient();
        //TODO: retrieve multiverseid &&|| image
        client.get("http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=" + urlIndex + "&type=card", null, new JsonHttpResponseHandler(){
        //TODO: Try/Catch
            public void onSuccess(int statusCode, Header[] headers, JSONObject response){
                try {
                    responseHandler.handleResponse(response.getString("Image"));
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

