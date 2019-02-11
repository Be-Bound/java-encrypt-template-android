package com.bebound.template;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bebound.template.crypto.CryptoUtils;
import com.bebound.template.request.sendtextencrypted.RequestSendTextEncrypted;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private Button buttonEncrypted;
    private TextView textView;

    // It will manage the permission.
    private PermissionsDelegate permissionsDelegate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edit_text);
        buttonEncrypted = findViewById(R.id.button_send_encrypted);
        textView = findViewById(R.id.textView);

        permissionsDelegate = new PermissionsDelegate(this);

        buttonEncrypted.setOnClickListener(view -> {
            // NOTE: When button is clicked and EditText is not empty, a request is sent.
            if (editText.getText().length() == 0){
                Toast.makeText(this, getString(R.string.toast_et_empty), Toast.LENGTH_LONG).show();
                textView.setText("");
            } else {
                String contentEncrypted = CryptoUtils.encryptDefaultKey(editText.getText().toString());
                textView.setText(String.format(getString(R.string.text_view_content), contentEncrypted));
                RequestSendTextEncrypted.sendRequest(contentEncrypted);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (permissionsDelegate.hasPermissions()) {
            buttonEncrypted.setClickable(true);
        } else {
            permissionsDelegate.requestPermissions();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionsDelegate.resultGranted(requestCode, grantResults)) {
            buttonEncrypted.setClickable(true);
        }
    }

}
