package cn.authing.api.demo.ut;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import cn.authing.api.demo.LoadingButton;
import cn.authing.api.data.AuthResponse;
import cn.authing.api.demo.R;
import cn.authing.api.demo.ut.v3.HttpV3Util;


public class UTTestActivity extends AppCompatActivity implements IHttpCallBack {

    private TestCase testCase;
    private int type;
    private TextView resultText;
    private LoadingButton button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ut_test);

        Intent intent = getIntent();
        if (intent != null) {
            testCase = intent.getParcelableExtra("data");
            type = intent.getIntExtra("type", 2);
        }

        if (testCase != null) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(testCase.getModuleName());
                actionBar.setSubtitle(testCase.getCaseName());
            }
            TextView apiName = findViewById(R.id.text_api_name);
            apiName.setText(testCase.getApiName());
            TextView apiMethod = findViewById(R.id.text_api_method);
            apiMethod.setText(testCase.getMethod());
            TextView url = findViewById(R.id.text_url);
            url.setText(testCase.getUrl());
            TextView params = findViewById(R.id.text_params);
            params.setText(testCase.getParams());
        }

        resultText = findViewById(R.id.text_result);
        button = findViewById(R.id.btn_test);
        button.setOnClickListener(v -> sync());

    }


    private void sync() {
        HttpV3Util.sync(testCase, this);
        button.startLoadingVisualEffect();
    }

    @Override
    public void showV3Result(String apiName, AuthResponse response) {
        runOnUiThread(() -> {
            button.stopLoadingVisualEffect();
            if (response.getStatusCode() == 200) {
                resultText.setTextColor(Color.GREEN);
            } else {
                resultText.setTextColor(Color.RED);
            }
            CharSequence text = resultText.getText();
            if (!TextUtils.isEmpty(text)) {
                text += "\n\n";
            }
            String result = text + "code : " + response.getStatusCode()
                    + "\nmessage : " + response.getMessage()
                    + "\ndata : " + response.getData();
            resultText.setText(result);
        });
    }

}
