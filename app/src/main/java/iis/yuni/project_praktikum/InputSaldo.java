package iis.yuni.project_praktikum;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputSaldo extends AppCompatActivity {

    DataHelper dbHelper;
    Button simpan, kembali;
    EditText text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_saldo);

        dbHelper = new DataHelper(this);
        text1 = (EditText) findViewById(R.id.nominal_saldo);
        simpan = (Button) findViewById(R.id.simpan);
        kembali = (Button) findViewById(R.id.kembali);
        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update saldo set  mysaldo='"+text1.getText().toString()+"' where id='" +1   +"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                Intent a = new Intent(InputSaldo.this, MainActivity.class);
                startActivity(a);
                finish();
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }
}
