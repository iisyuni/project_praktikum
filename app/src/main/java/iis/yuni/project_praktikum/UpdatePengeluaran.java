package iis.yuni.project_praktikum;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UpdatePengeluaran extends AppCompatActivity {
    protected Cursor cursor;
    DataHelper dbHelper;
    Button kembali, update;
    TextView text1;
    EditText text2, text3, text4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pengeluaran);

        dbHelper = new DataHelper(this);
        text1 = findViewById(R.id.no);
        text2 = findViewById(R.id.nama);
        text3 = findViewById(R.id.nominal);
        text4 = findViewById(R.id.tanggal);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pengeluaran WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
            text2.setText(cursor.getString(1).toString());
            text3.setText(cursor.getString(2).toString());
            text4.setText(cursor.getString(3).toString());
        }

        update = findViewById(R.id.update);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("update pengeluaran set nama='"+
                        text2.getText().toString() +"', nominal='" +
                        text3.getText().toString()+"', tanggal='"+
                        text4.getText().toString() +"' where no='" +
                        text1.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Berhasil", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });

        kembali = (Button) findViewById(R.id.kembali);
        kembali.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                finish();
            }
        });
    }


}