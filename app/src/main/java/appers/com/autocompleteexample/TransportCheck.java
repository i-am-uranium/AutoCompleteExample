package appers.com.autocompleteexample;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TransportCheck extends AppCompatActivity implements View.OnClickListener {
    RadioGroup transport_radio_grp, air_check_grp;
    RadioButton road, rail, air, charter, regular;
    TextView offline_error;
    Button dial_us1, dial_us2, next;
    AutoCompleteTextView from, to;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        init();
        onLine();
        road.setOnClickListener(this);
        rail.setOnClickListener(this);
        air.setOnClickListener(this);
        String[] cities = getResources().getStringArray(R.array.cities);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        from.setAdapter(arrayAdapter);
        to.setAdapter(arrayAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_transport_check, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void init() {
        transport_radio_grp = (RadioGroup) findViewById(R.id.transport);
        air_check_grp = (RadioGroup) findViewById(R.id.charter_or_regular);
        air_check_grp.setVisibility(View.GONE);
        road = (RadioButton) findViewById(R.id.road);
        rail = (RadioButton) findViewById(R.id.rail);
        air = (RadioButton) findViewById(R.id.air);
        charter = (RadioButton) findViewById(R.id.charter);
        regular = (RadioButton) findViewById(R.id.regular);
        from = (AutoCompleteTextView) findViewById(R.id.from);
        to = (AutoCompleteTextView) findViewById(R.id.to);
        offline_error = (TextView) findViewById(R.id.offline_text);
        dial_us1 = (Button) findViewById(R.id.dial1);
        dial_us2 = (Button) findViewById(R.id.dial2);
        next = (Button) findViewById(R.id.next);


    }

    public boolean onLine() {
        ConnectivityManager conMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        {
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

            if (netInfo == null) {
                offline_error.setVisibility(View.VISIBLE);
                next.setVisibility(View.GONE);
                dial_us1.setVisibility(View.VISIBLE);
                dial_us2.setVisibility(View.VISIBLE);
                Toast.makeText(this, "Internet connection not available", Toast.LENGTH_LONG).show();

            } else {
                dial_us1.setVisibility(View.GONE);
                dial_us2.setVisibility(View.GONE);
                offline_error.setVisibility(View.GONE);
                Toast.makeText(this, "Internet connection available", Toast.LENGTH_LONG).show();

            }
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        boolean check = ((RadioButton) v).isChecked();
        switch (v.getId()) {
            case R.id.road:
                if (check && air_check_grp.getVisibility() == View.VISIBLE)
                    air_check_grp.setVisibility(View.GONE);
                break;
            case R.id.rail:
                if (check && air_check_grp.getVisibility() == View.VISIBLE)
                    air_check_grp.setVisibility(View.GONE);
                break;
            case R.id.air:
                if (check && air_check_grp.getVisibility() == View.GONE)
                    air_check_grp.setVisibility(View.VISIBLE);
                break;
            default:
                Toast.makeText(this, "Some Kind OF Error!", Toast.LENGTH_SHORT).show();
        }

    }

    public void nextButtonClick(View view) {
        if (road.isChecked() || rail.isChecked()) {
            if (!from.getText().toString().equals("") && !to.getText().toString().equals("")) {
                startActivity(new Intent(this, DiagnosisActivity.class));
                Log.d("from case", "its working");
            } else Toast.makeText(this, "One More Fields are empty", Toast.LENGTH_LONG).show();
        } else if (air.isChecked()) {

            if (charter.isChecked() || regular.isChecked()) {
                if (!from.getText().toString().equals("") && !to.getText().toString().equals(""))
                    startActivity(new Intent(this, DiagnosisActivity.class));
            } else
                Toast.makeText(this, "One More Fields are empty", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "One More Fields are empty", Toast.LENGTH_LONG).show();
            Log.d("from case3", "its working");
        }


    }

    public void dialUs(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.dial1:
                Uri number1 = Uri.parse("tel:97173525494");
                Intent callIntent1 = new Intent(Intent.ACTION_DIAL, number1);
                startActivity(callIntent1);
                break;
            case R.id.dial2:
                Uri number2 = Uri.parse("tel:97173525494");
                Intent callIntent2 = new Intent(Intent.ACTION_DIAL, number2);
                startActivity(callIntent2);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        System.exit(0);
    }
}
