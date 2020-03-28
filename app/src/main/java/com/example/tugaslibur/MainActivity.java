package com.example.tugaslibur;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.service.autofill.RegexValidator;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Calendar myCalender;
    DatePickerDialog.OnDateSetListener date;
    TextInputEditText txt_tgl;
    EditText namaDpn, namaBlkg, tglLahir, tmptLahir, almt, tlpn, eml, pswd;

    Button btn2;
    AwesomeValidation awesomeValidation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_tgl = (TextInputEditText) findViewById(R.id.tglLahir);

        myCalender = Calendar.getInstance();
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                myCalender.set(Calendar.YEAR, year);
                myCalender.set(Calendar.MONTH, month);
                myCalender.set(Calendar.DAY_OF_MONTH, day);
                updateLabel();
            }

        };
        txt_tgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(MainActivity.this, date,
                        myCalender.get(Calendar.YEAR), myCalender.get(Calendar.MONTH),
                        myCalender.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        namaDpn = findViewById(R.id.namaDpn);
        namaBlkg = findViewById(R.id.namaBlkg);
        tmptLahir = findViewById(R.id.tmptLhr);
        tglLahir = findViewById(R.id.tglLahir);
        almt = findViewById(R.id.almt);
        eml = findViewById(R.id.eml);
        tlpn = findViewById(R.id.tlpn);
        pswd = findViewById(R.id.pswd);
        btn2 = findViewById(R.id.btn2);

        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

        awesomeValidation.addValidation(this,R.id.namaDpn,
                RegexTemplate.NOT_EMPTY,R.string.invalid_nama);
        awesomeValidation.addValidation(this,R.id.namaBlkg,
                RegexTemplate.NOT_EMPTY,R.string.invalid_namaBlkg);
        awesomeValidation.addValidation(this,R.id.tmptLhr,
                RegexTemplate.NOT_EMPTY,R.string.invalid_tmptLahir);
        awesomeValidation.addValidation(this,R.id.tglLahir,
                RegexTemplate.NOT_EMPTY,R.string.invalid_tglLahir);
        awesomeValidation.addValidation(this,R.id.almt,
                RegexTemplate.NOT_EMPTY,R.string.invalid_almt);
        awesomeValidation.addValidation(this,R.id.tlpn,
                RegexTemplate.NOT_EMPTY,R.string.invalid_tlpn);

        awesomeValidation.addValidation(this,R.id.eml, //nambahin format email hrus ada @ sama .com
                Patterns.EMAIL_ADDRESS,R.string.invalidEml);
        awesomeValidation.addValidation(this, R.id.pswd,//nambahin format password spesifiknya gimna
                    RegexTemplate.NOT_EMPTY, R.string.invalid_pswd);

//            String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
//            awesomeValidation.addValidation(this, R.id.pswd,//nambahin format password spesifiknya gimna
//                    regexPassword, R.string.invalid_pswd);

        btn2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (awesomeValidation.validate()) {
                    Toast.makeText(getApplicationContext(),
                            "Berhasil Didaftarkan",Toast.LENGTH_SHORT).show();
                    openHasil(
                    );
                }else {
                    Toast.makeText(getApplicationContext(),
                            "Data faild",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void openHasil(){
        Intent intent = new Intent(this,Data_Peserta.class);
        startActivity(intent);
    }

    private void updateLabel (){
        String myFormat = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        txt_tgl.setText(sdf.format(myCalender.getTime()));
    }
}


