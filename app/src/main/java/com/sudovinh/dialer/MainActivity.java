package com.sudovinh.dialer;
// Vinh Nguyen
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST = 1;

    TextView tel_number;

    Button b_0, b_1, b_2, b_3, b_4, b_5, b_6, b_7, b_8, b_9, b_call,callButton;

    String number = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        Uri phoneUri = intent.getData();


        if( phoneUri != null) {
            number = phoneUri.toString();
        } else {
            Toast.makeText(getApplicationContext(), "PLEASE USE DELIVERY APP", Toast.LENGTH_LONG).show();
        }

        if (intent.ACTION_SEND.equals(action) && type != null) {
             if ("text/plain".equals(type)) {
                 handleSendText(intent);
             }
         }

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST);
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST);
            }
        }


        tel_number = (TextView) findViewById(R.id.tel_number);
        tel_number.setInputType(InputType.TYPE_NULL);
        if (number.length() != 0) {
            tel_number.setText("(***) - *** - "+number.substring(number.length()-4,number.length()));
        }
        else {
            Toast.makeText(getApplicationContext(), "PLEASE USE DELIVERY APP", Toast.LENGTH_LONG).show();
        }

        callButton = (Button) findViewById(R.id.callButton);

        // Dial Pad Stuff
//        b_0 = (Button) findViewById(R.id.b_0);
//        b_1 = (Button) findViewById(R.id.b_1);
//        b_2 = (Button) findViewById(R.id.b_2);
//        b_3 = (Button) findViewById(R.id.b_3);
//        b_4 = (Button) findViewById(R.id.b_4);
//        b_5 = (Button) findViewById(R.id.b_5);
//        b_6 = (Button) findViewById(R.id.b_6);
//        b_7 = (Button) findViewById(R.id.b_7);
//        b_8 = (Button) findViewById(R.id.b_8);
//        b_9 = (Button) findViewById(R.id.b_9);
//        b_call = (Button) findViewById(R.id.b_call);
//
//        b_0.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "0";
//                tel_number.setText(number);
//            }
//        });
//        b_1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "1";
//                tel_number.setText(number);
//            }
//        });
//        b_2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "2";
//                tel_number.setText(number);
//            }
//        });
//        b_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "3";
//                tel_number.setText(number);
//            }
//        });
//        b_3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "3";
//                tel_number.setText(number);
//            }
//        });
//        b_4.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "4";
//                tel_number.setText(number);
//            }
//        });
//        b_5.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "5";
//                tel_number.setText(number);
//            }
//        });
//        b_6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "6";
//                tel_number.setText(number);
//            }
//        });
//        b_7.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "7";
//                tel_number.setText(number);
//            }
//        });
//        b_8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "8";
//                tel_number.setText(number);
//            }
//        });
//        b_9.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                number = number + "9";
//                tel_number.setText(number);
//            }
//        });
//        b_call.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_CALL);
//                intent.setData(Uri.parse("tel:" + number));
//                tel_number.setText("");
//                startActivity(intent);
//
//
//            }
//        });
            callButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(number));
                startActivity(intent);
                tel_number.setText("");

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
                            == PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(MainActivity.this, "Permission granted!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "No permission granted!", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
    }
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText !=null){
            number = sharedText;
        }
    }
}
