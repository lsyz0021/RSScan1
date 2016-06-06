package com.bandeng2.lilu.rq_scan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {

    private TextView tv_rsult;
    private EditText et_text;
    private ImageView image;
    // 测试2
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_rsult = (TextView) findViewById(R.id.tv_result);
        et_text = (EditText) findViewById(R.id.et_text);
        image = (ImageView) findViewById(R.id.iv_image);

    }
    /**扫描二维码*/
    public void scan(View view){
        // 开启ZXing库中可以扫描的二维码的Activity
        Intent intent = new Intent(this, CaptureActivity.class);
        // 要有返回结果
        startActivityForResult(intent,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK){
            // 获取返回结果
            String result = data.getStringExtra("result");
            // 将结果设置给TextView
            tv_rsult.setText(result);
        }
    }
    /**生产二维码*/
    public void create(View view){
        String result = et_text.getText().toString();
        if (!TextUtils.isEmpty(result)){
            Bitmap bitmap = EncodingUtils.createQRCode(result,500,500,
                    BitmapFactory.decodeResource(getResources(),R.mipmap.ic_launcher));
            image.setImageBitmap(bitmap);
        }else{
            // 提示
            Toast.makeText(this,"输入不能为空",Toast.LENGTH_LONG).show();

        }

    }

}
