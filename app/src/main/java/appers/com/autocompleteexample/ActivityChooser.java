package appers.com.autocompleteexample;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.WindowManager;

public class ActivityChooser extends AppCompatActivity {
    final String PREFS = "Myprefes";

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chooser);
        SharedPreferences preferences = getSharedPreferences(PREFS, 0);
        if (preferences.getBoolean("my_first_time", true)) {
            Log.d("first time", "For the first time splash screen is opened");
            startActivity(new Intent(this, SplashScreenActivity.class));
            preferences.edit().putBoolean("my_first_time", false).apply();

        } else {
            startActivity(new Intent(this, TransportCheck.class));
        }
        finish();
    }

}

