package appers.com.autocompleteexample;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class TransportCheck extends AppCompatActivity implements View.OnClickListener {
    RadioGroup transport_radio_grp, air_check_grp;
    RadioButton road, rail, air, charter, regular;
    Button dial_us1, dial_us2, next;
    AutoCompleteTextView from, to;
    Toolbar toolbar;
    LinearLayout online, offline;
    private LinearLayout mRoot;
    private TextInputLayout mFrom_Input, mTo_Input;
    View.OnClickListener mListner = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

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
        offline = (LinearLayout) findViewById(R.id.offline_ll);
        online = (LinearLayout) findViewById(R.id.online_ll);
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
        dial_us1 = (Button) findViewById(R.id.dial1);
        dial_us2 = (Button) findViewById(R.id.dial2);
        next = (Button) findViewById(R.id.next);
        mRoot = (LinearLayout) findViewById(R.id.transport_root);
        mFrom_Input = (TextInputLayout) findViewById(R.id.from_input);
        mTo_Input = (TextInputLayout) findViewById(R.id.to_input);
    }

    public boolean onLine() {
        ConnectivityManager conMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);

        {
            NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

            if (netInfo == null) {
                online.setVisibility(View.GONE);
                Toast.makeText(this, "Internet connection not available", Toast.LENGTH_LONG).show();

            } else {
                offline.setVisibility(View.GONE);
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

    private boolean isFrom() {
        return from.getText() == null
                || from.getText().toString() == null
                || from.getText().toString().isEmpty();
    }

    private boolean isTo() {
        return to.getText() == null
                || to.getText().toString() == null
                || to.getText().toString().isEmpty();
    }

    public void nextButtonClick(View view) {
        boolean isFromEmpty = isFrom();
        boolean isToEmpty = isTo();
        if (isFromEmpty && isToEmpty) {

            Snackbar.make(mRoot, "One More Field is Empty", Snackbar.LENGTH_SHORT).setAction("Dismiss", mListner).show();
        } else if (isFromEmpty && !isToEmpty) {
            mFrom_Input.setError("Enter City Name From");
            mTo_Input.setError(null);

        } else if (isTo() && !isFromEmpty) {
            mTo_Input.setError("Enter City Name To");
            mFrom_Input.setError(null);
        } else {
            //do what you want to do
            startActivity(new Intent(this, DiagnosisActivity.class));
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
                Intent callIntent2 = new Intent(Intent.ACTION_CALL, number2);
                startActivity(callIntent2);
                break;
            default:
                Toast.makeText(this, "some kind of error!", Toast.LENGTH_SHORT).show();
        }
    }

    public void getPhoneNumberExample(View view) {
        String mNumber = getMyPhoneNumber();
        Toast.makeText(this, "Your Phone Number Is" + mNumber, Toast.LENGTH_LONG).show();
    }


    private String getMyPhoneNumber() {
        TelephonyManager tMgr = (TelephonyManager) getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
        String mPhoneNumber = tMgr.getLine1Number();
//        TelephonyManager mTelephonyMgr;
//
//        mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        //String imsi = mTelephonyMgr.getSubscriberId();
//        String phnNo = mTelephonyMgr.getLine1Number();
        if (mPhoneNumber == null) {
            mPhoneNumber = "NO PHONE NUmber is available";
        }

        return mPhoneNumber;
    }


}
