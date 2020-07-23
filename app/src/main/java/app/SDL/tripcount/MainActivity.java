package app.SDL.tripcount;

import android.content.Intent;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
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

public class MainActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextviewRegister;
    DataBaseHelper db;
    FloatingActionButton ab;
    Button btnt;
    Button btnd;
    CheckBox showpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        db=new DataBaseHelper(this);
        mTextUsername=(EditText)findViewById(R.id.edittext_username);
        mTextPassword=(EditText)findViewById(R.id.edittext_password);
        mButtonLogin=(Button) findViewById(R.id.button_login);
        mTextviewRegister=(TextView)findViewById(R.id.textview_register);
        showpassword=findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    mTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }
                else
                {
                    mTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });
        mTextviewRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent registerIntent=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(registerIntent);


            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                if (user.length() == 0 || pwd.length() == 0) {
                    Toast.makeText(MainActivity.this,"Please enter details",Toast.LENGTH_SHORT).show();

                } else {


                Boolean res = db.checkUser(user, pwd);
                if (res == true) {
                    Toast.makeText(MainActivity.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, home.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(MainActivity.this, "Login error", Toast.LENGTH_SHORT).show();


                }
            }
            }
        });



    }

}
