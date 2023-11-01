package com.hoaphph29102.test6.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.hoaphph29102.test6.R;
import com.hoaphph29102.test6.model.KhachHang;

import java.util.ArrayList;

public class KhachHangAdapter extends BaseAdapter {
    ArrayList<KhachHang> list;
    Context context;
    private OnEditClickListener editClickListener;
    private OnDeleteClickListener deleteClickListener;

    public KhachHangAdapter(ArrayList<KhachHang> list, Context context) {
        this.list = list;
        this.context = context;
    }


    public void setOnEditClickListener(OnEditClickListener listener) {
        editClickListener = listener;
    }
    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        this.deleteClickListener = listener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v;
        if (convertView == null){
            v = View.inflate(context, R.layout.row_khach_hang,null);
        }
        else {
            v = convertView;
        }

        KhachHang khachHang = list.get(position);
        TextView tvMaNV = v.findViewById(R.id.tv_ma_kh);
        TextView tvTen = v.findViewById(R.id.tv_ten);
        TextView tvLuong = v.findViewById(R.id.tv_tuoi);
        TextView tvSdt = v.findViewById(R.id.tv_sdt);
        ImageView imgEdit = v.findViewById(R.id.img_edit);
        ImageView imgDel = v.findViewById(R.id.img_del);

        tvMaNV.setText("Mã KH: "+khachHang.getMaKH());
        tvTen.setText("Tên khách hàng: "+khachHang.getHoTen());
        tvLuong.setText("Tuổi : "+khachHang.getTuoi());
        tvSdt.setText("SDT: "+khachHang.getSdt());

        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (deleteClickListener!=null){
                    deleteClickListener.onDeleteClick(position);
                }
            }
        });

        imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editClickListener != null){
                    editClickListener.onEditClick(position);
                }
            }
        });

        return v;
    }

    public interface OnEditClickListener{
        void onEditClick(int position);
    }
    public interface OnDeleteClickListener {
        void onDeleteClick(int position);
    }



}
