package com.example.nd.test3;

import android.support.v7.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout;
import android.widget.HorizontalScrollView;
import java.util.logging.Handler;
import android.widget.AutoCompleteTextView;
import android.graphics.Color;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import java.util.Calendar;
import java.util.ArrayList;
import android.app.Dialog;
import android.widget.TimePicker;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog;

public class MainActivity extends AppCompatActivity {
    public static final int OVERLAY_PERMISSION_REQ_CODE = 2345;
    private ArrayList<String> messages = new ArrayList<>();
    TextView textLi;
    PopupWindow popupMessage;
    HorizontalScrollView horizontalScrollView;
    private DatePicker datePicker;
    private Calendar calendar;
    private int year, month, day;
    private TextView dateView;
    private TimePicker timePicker;
    private int hour, min, apm;
    private TextView timeView;
    private String apmString="";
    String[] location ={"Wean","Home","Hunt","NSH","Doherty","HH","West","North"};


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //setContentView(R.layout.main);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);

//        messages.add("Can you give me a ride this Friday?");
//        messages.add("Send me the links you mentioned yesterday.");
//        messages.add("Shall we meet at this Saturday?");
//        messages.add("Shall we Skype in 20 mins?");
//        messages.add("Let's meet 11530pm.");
//        messages.add("How about 1230?");
//        messages.add("Let's meet at 10:30 tonight");
        Button Test=(Button) findViewById(R.id.test);
        Test.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showPop();
            }
        });
        Button Test2=(Button) findViewById(R.id.test2);
        Test2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showAddress();
            }
        });
        Button Test3=(Button) findViewById(R.id.test3);
        Test3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showFile();
            }
        });
        Button Test4=(Button) findViewById(R.id.test4);
        Test4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showTodo();
            }
        });
        Button Test5=(Button) findViewById(R.id.test5);
        Test5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                showCalendar();
            }
        });


//        timeCheck(messages);

        ////
        //horizontalScrollView=(HorizontalScrollView) findViewById(R.id.todo_scroll);

      //  textLi=(TextView) this.findViewById(R.id.test);
       // textLi.setOnClickListener(new View.OnClickListener() {
         //   @Override
           // public void onClick(View v) {
             //   showPop();
           // }
        //});
    }
    private void showCalendar(){
        setContentView(R.layout.show_calendar);
    }
    private void showTodo(){
        setContentView(R.layout.todo_item);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this,android.R.layout.select_dialog_item,location);
        AutoCompleteTextView actv= (AutoCompleteTextView)findViewById(R.id.location);
        actv.setThreshold(1);//will start working from first character
        actv.setAdapter(adapter);
        actv.setTextColor(Color.BLACK);
        dateView=(TextView)this.findViewById(R.id.date_picker_text);
        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        hour = calendar.get(Calendar.HOUR_OF_DAY);
        min = calendar.get(Calendar.MINUTE);
        apm=calendar.get(Calendar.AM_PM);//0->AM, 1->PM
        timeView=(TextView)this.findViewById(R.id.time_picker_text);
        showDate(year, month+1, day);
        showTime(apm,min,hour);
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(9);
    }
    @SuppressWarnings("deprecation")
    public void setTime(View view) {
        showDialog(10);
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == 9) {
            return new DatePickerDialog(this, myDateListener, year, month, day);
        }
        if(id==10){
            return new TimePickerDialog(this, myTimeListener, hour,min,false);
        }
        return null;
    }
    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                       int year, int month, int day) {

                    showDate(year, month+1, day);
                }
            };
    private TimePickerDialog.OnTimeSetListener myTimeListener=
            new TimePickerDialog.OnTimeSetListener(){
                @Override
                public void onTimeSet(TimePicker arg0, int hour, int min){
                    if(hour>11){
                        apm=1;
                    }else{
                        apm=0;
                    }
                    showTime(apm,min,hour);
                }
            };
    private void showTime(int apm,int min, int hour){
        if(apm==1){
            apmString="PM";
        }
        if (apm==0){
            apmString="AM";
        }
        timeView.setText(new StringBuilder().append(hour).append(":").append(min).append(apmString));
    }
    private void showDate(int year, int month, int day) {
        dateView.setText(new StringBuilder().append(day).append("/")
                .append(month).append("/").append(year));
    }
    private void showFile(){
        setContentView(R.layout.file_retrieval);
    }
    private void showAddress(){
        setContentView(R.layout.address_email);
    }
    private void showPop() {

        setContentView(R.layout.popup_todo);
        // popupMessage.showAtLocation(findViewById(R.id.activity_main), Gravity.CENTER,0,0);
        //this.findViewById(R.id.activity_main).setBackgroundColor(0xAA000000);

        // dim_layout.setAlpha((float) 0.25);
    }
}
