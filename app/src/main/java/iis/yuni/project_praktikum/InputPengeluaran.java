package iis.yuni.project_praktikum;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InputPengeluaran extends AppCompatActivity {

    //protected Cursor cursor;
    DataHelper dbHelper;
    Button simpan, kembali;
    EditText text1, text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_pengeluaran);

        dbHelper = new DataHelper(this);
        text1= (EditText) findViewById(R.id.nama);
        text2 = (EditText) findViewById(R.id.nominal);
        text3 = (EditText) findViewById(R.id.tanggal);
        simpan = (Button) findViewById(R.id.simpan);
        kembali = (Button) findViewById(R.id.kembali);

        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into pengeluaran( nama, nominal, tanggal) values('" +
                        text1.getText().toString() + "','" +
                        text2.getText().toString() + "','" +
                        text3.getText().toString() + "')");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
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
