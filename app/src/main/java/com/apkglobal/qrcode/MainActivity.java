package com.apkglobal.qrcode;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;

public class MainActivity extends AppCompatActivity {
EditText et,et1,et2;
Button btn;
ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et=(EditText)findViewById(R.id.et);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);
        btn=(Button)findViewById(R.id.btn);
        iv=(ImageView)findViewById(R.id.iv);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String me=et.getText().toString()+et1.getText().toString()+et2.getText().toString();
                WindowManager wm=(WindowManager)getSystemService(WINDOW_SERVICE);
                Display display=wm.getDefaultDisplay();
                Point point=new Point();
                display.getSize(point);
                int x=point.x;
                int y=point.y;

                int icon=x<y ? x:y;
                icon=icon*3/4;
                QRCodeEncoder qrCodeEncoder=new QRCodeEncoder(me,null,Contents.Type.TEXT, BarcodeFormat.QR_CODE.toString(),
                        icon);

                try {
                    Bitmap bitmap=qrCodeEncoder.encodeAsBitmap();

                    iv.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}