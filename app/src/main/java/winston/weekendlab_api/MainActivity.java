package winston.weekendlab_api;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

//TODO: Stretch (Extended): 1) Test cases 2) Material design 3) Accessiblity 4) OAuth

public class MainActivity extends AppCompatActivity implements ScryApi.ApiResponseHandler {

    //TODO: Initialize XML View variables
    TextView textView;
    EditText scryEntry;
    ImageView scryImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: scry button, onClickListener, & inputMethod mgr
        final Button onScry = (Button) findViewById(R.id.scryButton);
        assert onScry != null; //in case an AssertionError is thrown

        onScry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scryEntry = (EditText) findViewById(R.id.scryEntry);

                InputMethodManager iMM = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                //hide the keyboard on search
                iMM.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),iMM.HIDE_NOT_ALWAYS);

                int scryIndex = Integer.parseInt(scryEntry.getText().toString());
                if(scryIndex <= 0 || 410064 < scryIndex){ //last card of the most recent set Shadows over Innistrad
                    onScry.setClickable(false);
                    Toast.makeText(MainActivity.this, "Scry has been bounced. Try scrying again.", Toast.LENGTH_LONG).show(); //Planeswalker fail.
                } else {
                    onScry.setClickable(true);
                    ScryApi.getInstance(MainActivity.this).doRequest(scryIndex);
                }
            }
        });//end OnClickListener
    }

    @Override
    public void handleResponse(String response) {
        //loads scryImage into the ImageView
        scryImage = (ImageView) findViewById(R.id.scryImage);
        Picasso.with(MainActivity.this).load(response).into(scryImage);
    }
}
