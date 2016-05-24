package winston.weekendlab_api;

//For Flickr: Implement key: a349634211c1df1171b6a4070099f108 | Secret: 2c9abbdf5789a87b
//For MTG: http://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=[1-410064]&type=card

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class ScryApi {
    private static ScryApi instance;
    private static ApiResponseHandler responseHandler;
    //private RelativeLayout relativeLayout; //part of LoadBackground class

    //private ScryApi(){}

    //implements Response Handler
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
                    responseHandler.handleResponse(response.getString("multiverseid"));
                } catch (JSONException e){
                    e.printStackTrace(); //returns error on exception
                }
            }
        });
    }//end doRequest Async search

    public interface ApiResponseHandler {
        void handleResponse(String response);
    }

/*TODO: Folded comments: Attempts to implement LoadBackground due to overtaxing of emulator Drawable; Working State: Inoperative.*/
//    private class LoadBackground extends AsyncTask<String, Void, Drawable> {
//        private String imageUrl, imageName;
//        public LoadBackground(String url, String file_name){
//            this.imageUrl = url;
//            this.imageName = file_name;
//        }
//
//        @Override
//        protected void onPreExecute(){
//            super.onPreExecute();
//        }
//
//        @Override
//        protected Drawable doInBackground(String... urls){
//            try{
//                InputStream iS = (InputStream) this.fetch(this.imageUrl);
//                Drawable draw = Drawable.createFromStream(iS, this.imageUrl);
//                return draw;
//            } catch (MalformedURLException e){
//                e.printStackTrace();
//                return null;
//            } catch (IOException e){
//                e.printStackTrace();
//                return null;
//            }
//        }
//        private Object fetch(String address) throws MalformedURLException,IOException {
//            URL url = new URL(address);
//            Object content = url.getContent();
//            return content;
//        }
//
//        @Override
//        protected void onPostExecute(Drawable result) {
//            super.onPostExecute(result);
//            relativeLayout.setBackground(result);
//        }
//    }//end LoadBackground
//
}

