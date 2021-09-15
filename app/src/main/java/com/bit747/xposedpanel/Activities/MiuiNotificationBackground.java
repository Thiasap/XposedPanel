package com.bit747.xposedpanel.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bit747.xposedpanel.R;
import com.bit747.xposedpanel.Utils.FileIO;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MiuiNotificationBackground extends AppCompatActivity {
    EditText setBlurRadius,addBlendLayer1,addBlendLayer2;
    String sBR,sBL1,sBL2;
    String path;
    String conf;
    Button saveRules,restartSystemUI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miui_notification_background);
        setBlurRadius = findViewById(R.id.mnb_setBlurRadius);
        addBlendLayer1 = findViewById(R.id.mnb_addBlendLayer1);
        addBlendLayer2 = findViewById(R.id.mnb_addBlendLayer2);
        saveRules.findViewById(R.id.mnb_goTest);
        restartSystemUI.findViewById(R.id.mnb_restartUI);
        init();
    }
    public void init(){
        path = Environment.getExternalStorageDirectory().getPath()+"/Android/hooker/";
        conf = "MiuiNotificationBackground.conf";
        List<String> rules = FileIO.readLine(path,conf);
        if(rules==null||rules.size()<2){
            Toast.makeText(this,"配置文件读取错误，将使用默认配置，若是第一次使用，请无视。",Toast.LENGTH_SHORT).show();
            sBR=setBlurRadius.getText().toString();
            sBL1=addBlendLayer1.getText().toString();
            sBL2=addBlendLayer2.getText().toString();
        }else{
            sBR=rules.get(0);
            sBL1=rules.get(1);
            sBL2=rules.get(2);
            setBlurRadius.setText(sBR);
            addBlendLayer1.setText(sBL1);
            addBlendLayer2.setText(sBL2);
        }
        saveRules.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sBR=setBlurRadius.getText().toString();
                sBL1=addBlendLayer1.getText().toString();
                sBL2=addBlendLayer2.getText().toString();
                String _rules = sBR+"\n"+sBL1+"\n"+sBL2;
                if(FileIO.saveData(path,conf,_rules)==1){
                    Toast.makeText(MiuiNotificationBackground.this,"保存成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MiuiNotificationBackground.this,"保存失败！",Toast.LENGTH_SHORT).show();
                }
            }
        });
        restartSystemUI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MiuiNotificationBackground.this,"需要root，还没做，懒。",Toast.LENGTH_SHORT).show();

            }
        });
    }
}