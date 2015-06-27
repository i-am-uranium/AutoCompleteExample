package appers.com.autocompleteexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class DiagnosisActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;
    EditText p_d, p_s;
    RadioButton ven, no_vent, med_yes, med_no, doc_yes, doc_no;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        init();
        button = (Button) findViewById(R.id.next_to_contact);
        button.setOnClickListener(this);
    }

    private void init() {
        p_d = (EditText) findViewById(R.id.patient_diagnosis);
        p_s = (EditText) findViewById(R.id.present_state);
        ven = (RadioButton) findViewById(R.id.ventilator_yes);
        no_vent = (RadioButton) findViewById(R.id.ventilator_no);
        doc_yes = (RadioButton) findViewById(R.id.doctor_yes);
        doc_no = (RadioButton) findViewById(R.id.doctor_no);
        med_yes = (RadioButton) findViewById(R.id.medication_yes);
        med_no = (RadioButton) findViewById(R.id.medication_no);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_diagnosis, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (!p_d.getText().toString().equals("") && !p_s.getText().toString().equals("")) {
            if (ven.isChecked() || no_vent.isChecked())
                if (doc_yes.isChecked() || doc_no.isChecked())
                    if (med_yes.isChecked() || med_no.isChecked())
                        startActivity(new Intent(this, Contact.class));
        } else Toast.makeText(this, "One More Fields Are Empty.", Toast.LENGTH_SHORT).show();
    }
}
