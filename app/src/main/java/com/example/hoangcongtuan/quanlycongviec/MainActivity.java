package com.example.hoangcongtuan.quanlycongviec;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener
        , AdapterView.OnItemLongClickListener, AdapterView.OnItemClickListener {
    private final static String TAG = MainActivity.class.getName();
    TextView tvNgayHT;
    TextView tvGioHT;
    Button btnDate;
    Button btnTime;
    Button btnThemCV;
    EditText edtCV;
    EditText edtNoiDung;
    ListView lstCongViec;
    Calendar calendar;
    Date dateFinish;
    Date timeFinish;
    ArrayAdapter<JobTodo> adapter;
    ArrayList<JobTodo> lstJob;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvGioHT = (TextView)findViewById(R.id.tvGioHT);
        tvNgayHT = (TextView) findViewById(R.id.tvNgayHT);

        btnDate = (Button) findViewById(R.id.btnDate);
        btnTime = (Button) findViewById(R.id.btnTime);
        btnThemCV = (Button) findViewById(R.id.btnThemCV);

        edtCV = (EditText)findViewById(R.id.edtCongViec);
        edtNoiDung = (EditText)findViewById(R.id.edtNoiDung);
        lstCongViec = (ListView) findViewById(R.id.lstCongViec);


        calendar = Calendar.getInstance();

        lstJob = new ArrayList<>();
        adapter = new ArrayAdapter<JobTodo>(this, android.R.layout.simple_list_item_1, lstJob);
        lstCongViec.setAdapter(adapter);
        lstCongViec.setOnItemLongClickListener(this);
        lstCongViec.setOnItemClickListener(this);

        btnDate.setOnClickListener(this);
        btnTime.setOnClickListener(this);
        btnThemCV.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDate:
                showDatePickerDialog();
                break;
            case R.id.btnTime:
                showTimePickerDialog();
                break;
            case R.id.btnThemCV:
                themCongViec();
                break;
        }
    }

    public void showTimePickerDialog() {
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                calendar.set(Calendar.HOUR, i);
                calendar.set(Calendar.MINUTE, i1);
                int tmpHour = i;
                if (i > 12) {
                    tmpHour = i -12;
                }
                tvGioHT.setText(tmpHour + ":" + i1 + " " + (i > 12 ? "PM" : "AM"));

            }
        };

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        TimePickerDialog pickerDialog = new TimePickerDialog(this, listener, hour, min, true);
        pickerDialog.show();
    }

    public void showDatePickerDialog() {
        DatePickerDialog.OnDateSetListener listener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendar.set(Calendar.YEAR, i);
                calendar.set(Calendar.MONTH, i1 + 1);
                calendar.set(Calendar.DAY_OF_MONTH, i2);
                tvNgayHT.setText(i2 + "/" + i1 + "/" + i);
            }
        };

        Calendar cal = Calendar.getInstance();
        int date = cal.get(Calendar.DATE);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);

        DatePickerDialog pickerDialog = new DatePickerDialog(this, listener, year, month, date);
        pickerDialog.show();
    }

    public void themCongViec() {
        lstJob.add(new JobTodo(
                edtCV.getText().toString(),
                edtNoiDung.getText().toString(),
                calendar.getTime(), calendar.getTime()));
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        lstJob.remove(i);
        adapter.notifyDataSetChanged();
        return true;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, lstJob.get(i).getNoiDung(), Toast.LENGTH_LONG).show();
    }
}
