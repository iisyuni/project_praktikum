package iis.yuni.project_praktikum;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button buat;
    String[] daftar;
    ListView ListView01;
    Cursor cursor;
    ArrayList<Saldo> listsaldo;
    DataHelper dbcenter;
    TextView saldo;
    public static MainActivity ma;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //list pengeluaran
        ma = this;
        dbcenter = new DataHelper(this);
        RefreshList();

        buat=findViewById(R.id.buat);
        buat.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg4) {
                // TODO Auto-generated method stub
                Intent i = new Intent(MainActivity.this,InputPengeluaran.class);
                startActivity(i);
            }
        });

//        list=findViewById(R.id.list_);
//        list.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                // TODO Auto-generated method stub
//                Intent j = new Intent(MainActivity.this,list_pengeluaran.class);
//                startActivity(j);
//            }
//        });

        saldo = (TextView) findViewById(R.id.saldo);
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM saldo ",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            String a = cursor.getString(1);
            saldo.setText(a);
            //for (int i=0; i< cursor.getCount();i++){
                //cursor.moveToPosition(i);
                //listsaldo.add(new Saldo(Integer.parseInt(cursor.getString(0)),Integer.parseInt(cursor.getString(1))));
            //}

        }
        else
        {
            saldo.setText("aaaaaaa");
        }

    }

    public void RefreshList(){
        SQLiteDatabase db = dbcenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM pengeluaran order by nama" ,null);
        daftar = new String[cursor.getCount()];
        cursor.moveToFirst();
        for (int cc=0; cc < cursor.getCount(); cc++){
            cursor.moveToPosition(cc);
            daftar[cc] = cursor.getString(1).toString();
        }
        ListView01 = (ListView)findViewById(R.id.ListView1);
        ListView01.setAdapter(new ArrayAdapter(this, android.R.layout.simple_list_item_1, daftar));
        ListView01.setSelected(true);
        ListView01.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = daftar[arg2];
                final CharSequence[] dialogitem = {"Lihat", "Update", "Hapus"};
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch(item){
                            case 0 :
                                Intent i = new Intent(getApplicationContext(), LihatPengeluaran.class);
                                i.putExtra("nama", selection);
                                startActivity(i);
                                break;
                            case 1 :
                                Intent intent = new Intent(getApplicationContext(), UpdatePengeluaran.class);
                                intent.putExtra("nama", selection);
                                startActivity(intent);
                                break;
                            case 2 :
                                SQLiteDatabase db = dbcenter.getWritableDatabase();
                                db.execSQL("delete from pengeluaran where nama = '"+selection+"'");
                                RefreshList();
                                break;
                        }
                    }
                });
                builder.create().show();
            }});
        ((ArrayAdapter)ListView01.getAdapter()).notifyDataSetInvalidated();
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu1, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.menu_in){
            startActivity(new Intent(this, InputSaldo.class));
        }
        return super.onOptionsItemSelected(item);
    }


}

