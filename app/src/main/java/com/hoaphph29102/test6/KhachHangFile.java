package com.hoaphph29102.test6;

import android.content.Context;


import com.hoaphph29102.test6.model.KhachHang;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class KhachHangFile {
    private static final String FILE_NAME = "employee.txt";

    public static void ghiFile(Context context, List<KhachHang> nhanVienList) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(nhanVienList);
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<KhachHang> docFile(Context context) {
        ArrayList<KhachHang> nhanVienList = null;

        try {
            FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            nhanVienList = (ArrayList<KhachHang>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        if (nhanVienList == null) {
            nhanVienList = new ArrayList<>();
        }

        return nhanVienList;
    }
}
