package com.bit747.xposedpanel.Activities;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.bit747.xposedpanel.R;
import com.bit747.xposedpanel.Utils.PermissionUtils;
import com.bit747.xposedpanel.moduleAdapter;
import com.bit747.xposedpanel.moduleData;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context=MainActivity.this;
        requestPermission();
    }
    public void init(){
        context=getApplicationContext();
        //path= Environment.getExternalStorageDirectory().getPath()+"/Android/hooker/";
        //初始化成员变量
        ListView lv_main;
        lv_main = (ListView) findViewById(R.id.lv_main);
        List<moduleData> modules = new ArrayList<>();
        modules.add(new moduleData("MiuiNotificationBackground","MIUI通知模糊修改"));
        modules.add(new moduleData("ClipboardFilter","剪贴板过滤"));
        modules.add(new moduleData("intentfilter","Intent过滤"));
        moduleAdapter mAdapter = new moduleAdapter(this, modules);
        lv_main.setAdapter(mAdapter);
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String tag = modules.get(position).id;
                try {
                    startActivity(new Intent(MainActivity.this, Class.forName("com.bit747.xposedpanel.Activities."+tag)));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void requestPermission(){
        PermissionUtils.checkAndRequestMorePermissions(context, new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},1,
                new PermissionUtils.PermissionRequestSuccessCallBack() {
            @Override
            public void onHasPermission() {
                // 权限已被授予
                init();
            }
        });
        PermissionUtils.onRequestMorePermissionsResult(context, new String[]{WRITE_EXTERNAL_STORAGE,READ_EXTERNAL_STORAGE},
                new PermissionUtils.PermissionCheckCallBack() {
                    @Override
                    public void onHasPermission() {
                        // 权限已被授予
                        init();
                    }
                    @Override
                    public void onUserHasAlreadyTurnedDown(String... permission) {
                        Toast.makeText(context, "需要内部存储读写权限来修改配置", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onUserHasAlreadyTurnedDownAndDontAsk(String... permission) {
                        Toast.makeText(context, "需要内部存储读写权限来修改配置", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}