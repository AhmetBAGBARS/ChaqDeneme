package com.android.chaqdeneme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.chaquo.python.PyObject;
import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class MainActivity extends AppCompatActivity {


    TextView textView;
    Button button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView=(TextView) findViewById(R.id.textView);
        button=(Button) findViewById(R.id.button);
        editText=(EditText) findViewById(R.id.editText);


        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }

        // python nesne instancesi
        Python py = Python.getInstance();

        // şimdi python nesnesi ve py dosya ismi
        PyObject pyobj=py.getModule("myscript");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // python def'i çağrılıyor....
                PyObject obj =pyobj.callAttr("main",editText.getText().toString());

                //def return'u almak
                textView.setText(obj.toString());

            }
        });





    }
}