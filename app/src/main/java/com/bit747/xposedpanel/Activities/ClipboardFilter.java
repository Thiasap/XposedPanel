package com.bit747.xposedpanel.Activities;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bit747.xposedpanel.R;
import com.bit747.xposedpanel.Utils.FileIO;

public class ClipboardFilter extends Activity {

private String path;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clipboard_filter);
        path=Environment.getExternalStorageDirectory().getPath()+"/Android/hooker/";
        EditText rulesET = findViewById(R.id.rules);
        String rules = FileIO.readData(path,"clipboardfilter.conf");
        rulesET.setText(rules);
        Button bt = findViewById(R.id.goTest);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FileIO.saveData(path,"clipboardfilter.conf",rulesET.getText().toString())==1){
                    Toast.makeText(ClipboardFilter.this,"保存成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ClipboardFilter.this,"保存失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}