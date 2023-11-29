package com.example.myapplication4;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    private EditText pid,pname,pprice;
    private Button pinsert,view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DatabaseHelper(this);
        pid = (EditText) findViewById(R.id.pid);
        pname = (EditText) findViewById(R.id.pname);
        pprice = (EditText) findViewById(R.id.price);
        pinsert = (Button) findViewById(R.id.insert);
        view = (Button) findViewById(R.id.view);
        addData();
        viewData();
    }

    public void addData(){
        pinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isInserted= db.insertData(Integer.parseInt(pid.getText().toString()),pname.getText().toString(),Integer.parseInt(pprice.getText().toString()));
                if(isInserted==true){
                    Toast.makeText(MainActivity.this, "DATA INSERTED", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void viewData(){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = db.viewAll();
                if(res.getCount()==0){
                    showMessages("Error", "Nothing to Display");
                    return;
                }
                StringBuffer buf = new StringBuffer();
                while(res.moveToNext()){
                    buf.append("ID :" + res.getString(0) + "\n");
                    buf.append("Name :" + res.getString(1) + "\n");
                    buf.append("Price :" + res.getString(2) + "\n\n");
                }
                showMessages("Data", buf.toString());
            }
        });
    }

    public void showMessages(String Title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(Title);
        builder.setMessage(Message);
        builder.show();
    }

}
