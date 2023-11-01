package com.hoaphph29102.test6;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.hoaphph29102.test6.adapter.KhachHangAdapter;
import com.hoaphph29102.test6.model.KhachHang;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<KhachHang> listKH;


    ListView lvKH;
    KhachHangAdapter khachHangAdapter;
    Button btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvKH = findViewById(R.id.lv_kh);
        btnAdd = findViewById(R.id.btn_add);


// vua cap nhat
        listKH.add(new KhachHang("1","hoaa",20,"0393911183"));
        listKH.add(new KhachHang("2","hoang",20,"0393911183"));
        listKH.add(new KhachHang("3","linh",20,"0393911183"));
        listKH.add(new KhachHang("4","hung",20,"0393911183"));
        listKH.add(new KhachHang("5","hanh",20,"0393911183"));

        listKH = KhachHangFile.docFile(this);
        khachHangAdapter = new KhachHangAdapter(listKH,this);
        lvKH.setAdapter(khachHangAdapter);

        btnAdd.setOnClickListener(v -> showDialogAdd());

        khachHangAdapter.setOnEditClickListener(new KhachHangAdapter.OnEditClickListener() {
            @Override
            public void onEditClick(int position) {
                showDialogUpdate(listKH.get(position),position);
            }
        });

        khachHangAdapter.setOnDeleteClickListener(new KhachHangAdapter.OnDeleteClickListener() {
            @Override
            public void onDeleteClick(int position) {
                listKH.remove(position);
                khachHangAdapter.notifyDataSetChanged();


                KhachHangFile.ghiFile(MainActivity.this, listKH);
            }
        });

    }

    public void showDialogAdd(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_add,null);
        builder.setView(v);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();

        EditText edMa = v.findViewById(R.id.ed_ma_kh);
        EditText edTen = v.findViewById(R.id.ed_ten_kh);
        EditText edTuoi = v.findViewById(R.id.ed_ten_kh);
        EditText edSdt = v.findViewById(R.id.ed_sdt);
        Button btnSave = v.findViewById(R.id.btn_save);
        Button btnCancel = v.findViewById(R.id.btn_cancel);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maKH = edMa.getText().toString();
                String ten = edTen.getText().toString();
                int tuoi = Integer.parseInt(edTuoi.getText().toString());
                String sdt = edSdt.getText().toString();
                if (maKH.isEmpty() | ten.isEmpty() | sdt.isEmpty()){
                    Toast.makeText(MainActivity.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    KhachHang khachHang = new KhachHang(maKH, ten, tuoi, sdt);
                    listKH.add(khachHang);

                    KhachHangFile.ghiFile(MainActivity.this, listKH);
                    Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();

                    khachHangAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(v1 -> {dialog.dismiss();});

        dialog.show();

    }
    public void showDialogUpdate(KhachHang khachHang, int position){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_update,null);
        builder.setView(v);
        builder.setCancelable(true);
        AlertDialog dialog = builder.create();

        EditText edMa = v.findViewById(R.id.ed_ma_kh);
        EditText edTen = v.findViewById(R.id.ed_ten_kh);
        EditText edTuoi = v.findViewById(R.id.ed_tuoi);
        EditText edSdt = v.findViewById(R.id.ed_sdt);
        Button btnSave = v.findViewById(R.id.btn_save);
        Button btnCancel = v.findViewById(R.id.btn_cancel);



        edMa.setText(khachHang.getMaKH());
        edTen.setText(khachHang.getHoTen());
        edTuoi.setText(khachHang.getTuoi()+"");
        edSdt.setText(khachHang.getSdt());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String maKH = edMa.getText().toString();
                String ten = edTen.getText().toString();
                int tuoi = Integer.parseInt(edTuoi.getText().toString());
                String sdt = edSdt.getText().toString();
                if (maKH.isEmpty() | ten.isEmpty() | sdt.isEmpty()){
                    Toast.makeText(MainActivity.this, "Hãy nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
                else {
                    KhachHang khachHang_new = new KhachHang(maKH, ten, tuoi, sdt);
                    listKH.set(position, khachHang_new);

                    KhachHangFile.ghiFile(MainActivity.this, listKH);
                    Toast.makeText(MainActivity.this, "Thành công", Toast.LENGTH_SHORT).show();

                    khachHangAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });

        btnCancel.setOnClickListener(v1 -> {dialog.dismiss();});

        dialog.show();


    }
}