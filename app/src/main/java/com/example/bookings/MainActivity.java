package com.example.bookings;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import static com.example.bookings.R.color.teal_200;

public class MainActivity extends AppCompatActivity {
    private String date;
    private TextView selectDate;
    TextView t1,t2,t3;
    private Calendar calendar;
    private DatePickerDialog datePickerDialog;
    DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectDate = (TextView) findViewById(R.id.bookAppointment_selectDate);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t3=findViewById(R.id.t3);
        ref=FirebaseDatabase.getInstance().getReference().child("Bookings");




        selectDate.setText(date);

        String date1 = new SimpleDateFormat("dd-mm-yyyy", Locale.getDefault()).format(new Date());
        selectDate.setText(date1);        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                int month = calendar.get(Calendar.MONTH);
                int year = calendar.get(Calendar.YEAR);

                datePickerDialog = new DatePickerDialog(MainActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        date = dayOfMonth +"-"+ (month+1) +"-"+ year;
                        Toast.makeText(MainActivity.this, date , Toast.LENGTH_SHORT).show();
                        selectDate.setText(date);
                        onStart();
                        t1.setVisibility(View.VISIBLE);
                        t2.setVisibility(View.VISIBLE);
                        t3.setVisibility(View.VISIBLE);

                        check();



                    }
                },day,month,year);
                datePickerDialog.updateDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() + (3 * 60 * 60 * 1000));
                datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() + (15 * 24 * 60 * 60 * 1000));
                datePickerDialog.show();
            }
        });
        t1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String time= t1.getText().toString();
                        AlertDialog ad= new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Book")
                                .setMessage("Confirm Booking "+ time+"?")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
                                        String id= reference.push().getKey();
                                        HashMap<String,Object> hashmap= new HashMap<>();
                                        hashmap.put("time",time);
                                        hashmap.put("date",date);
                                        reference.child("Bookings").child(id).setValue(hashmap).addOnCompleteListener(
                                                new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                        );
                                    }
                                })
                                .create();

                        ad.show();
                    }
                }
        );
        t2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String time= t2.getText().toString();
                        AlertDialog ad= new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Book")
                                .setMessage("Confirm Booking "+ time+"?")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
                                        String id= reference.push().getKey();
                                        HashMap<String,Object> hashmap= new HashMap<>();
                                        hashmap.put("time",time);
                                        hashmap.put("date",date);
                                        reference.child("Bookings").child(id).setValue(hashmap).addOnCompleteListener(
                                                new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                        );
                                    }
                                })
                                .create();

                        ad.show();
                    }
                }
        );
        t3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String time= t3.getText().toString();
                        AlertDialog ad= new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Book")
                                .setMessage("Confirm Booking "+ time+"?")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();

                                        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
                                        String id= reference.push().getKey();
                                        HashMap<String,Object> hashmap= new HashMap<>();
                                        hashmap.put("time",time);
                                        hashmap.put("date",date);
                                        reference.child("Bookings").child(id).setValue(hashmap).addOnCompleteListener(
                                                new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        Toast.makeText(MainActivity.this, "success", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                        );
                                    }
                                })
                                .create();

                        ad.show();
                    }
                }
        );

    }

    private void check() {
        ref.addValueEventListener(
                new ValueEventListener() {
                    @SuppressLint("ResourceAsColor")
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(DataSnapshot dataSnapshot:snapshot.getChildren()) {

                            Book book = dataSnapshot.getValue(Book.class);
                            String dates = book.getDate();
                            String times = book.getTime();

                            String t1time = t1.getText().toString();
                            String t2time = t2.getText().toString();
                            String t3time = t3.getText().toString();

                            if (date.equals(dates) && times.equals(t1time)) {
                                t1.setVisibility(View.GONE);
                            }
                            else if(date.equals(dates) && times.equals(t2time)){
                                t2.setVisibility(View.GONE);
                            }
                            else if(date.equals(dates) && times.equals(t3time)){
                                t3.setVisibility(View.GONE);
                            }

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                }
        );
    }

}