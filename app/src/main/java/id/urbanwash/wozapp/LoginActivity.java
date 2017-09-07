package id.urbanwash.wozapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import id.urbanwash.wozapp.R;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by apridosandyasa on 3/6/16.
 */
public class LoginActivity extends AppCompatActivity {
    AppCompatEditText edt_username, edt_password;
    AppCompatButton btn_username, btn_password, btn_login, btn_register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_login);

        this.edt_username = (AppCompatEditText) findViewById(R.id.edt_username);
        this.edt_password = (AppCompatEditText) findViewById(R.id.edt_password);
        this.btn_username = (AppCompatButton) findViewById(R.id.btn_username);
        this.btn_password = (AppCompatButton) findViewById(R.id.btn_password);
        this.btn_login = (AppCompatButton) findViewById(R.id.btn_login);
        this.btn_register = (AppCompatButton) findViewById(R.id.btn_register);

        this.btn_username.setOnClickListener(new ShowUsernameEditText());
        this.btn_password.setOnClickListener(new ShowPasswordEditText());
        this.btn_login.setOnClickListener(new GoToMainView());
        this.btn_register.setOnClickListener(new GoToRegisterView());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    class ShowUsernameEditText implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            btn_username.setVisibility(View.GONE);
            edt_username.setVisibility(View.VISIBLE);
        }
    }

    class ShowPasswordEditText implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            btn_password.setVisibility(View.GONE);
            edt_password.setVisibility(View.VISIBLE);
        }
    }

    class GoToMainView implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(mainIntent);
            LoginActivity.this.finish();
        }
    }

    class GoToRegisterView implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        }
    }
}
