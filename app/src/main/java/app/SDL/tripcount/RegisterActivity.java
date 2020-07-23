package app.SDL.tripcount;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCmfPassword;
    Button mButtonRegister;
    TextView mTextviewLogin;
    DataBaseHelper db;
    CheckBox showpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db=new DataBaseHelper(this);
        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mTextCmfPassword=(EditText)findViewById(R.id.edittext_cmf_password);
        mButtonRegister=(Button) findViewById(R.id.button_register);
        mTextviewLogin=(TextView)findViewById(R.id.textview_login);
        showpassword=findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    mTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    mTextCmfPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else
                {
                    mTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    mTextCmfPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
        mTextviewLogin.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view)
            {
                Intent LoginIntent=new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);
            }
        });

        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cmf_pwd = mTextCmfPassword.getText().toString().trim();
                if (user.length() == 0 || pwd.length() == 0 || cmf_pwd.length() == 0) {
                    Toast.makeText(RegisterActivity.this, "username or password may not empty", Toast.LENGTH_SHORT).show();

                } else {



                if (pwd.equals(cmf_pwd)) {
                    long val = db.addUser(user, pwd);
                    if (val > 0) {
                        Toast.makeText(RegisterActivity.this, "Succesfullly Registered", Toast.LENGTH_SHORT).show();
                        Intent movetologin = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(movetologin);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration error", Toast.LENGTH_SHORT).show();

                    }


                } else {
                    Toast.makeText(RegisterActivity.this, "Passwword is not matching", Toast.LENGTH_SHORT).show();


                }
            }
            }
        });


    }
}
