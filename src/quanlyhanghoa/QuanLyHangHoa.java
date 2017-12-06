/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhanghoa;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author trant
 */
public class QuanLyHangHoa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException, FileNotFoundException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Chon hanh dong:\n1. Them mat hang\n2. Xem danh sach\n3. Sua doi\n4. Xoa\n5. Tim kiem");
        int action = Integer.parseInt(sc.nextLine());
        if(action == 1){
            QuanLy.them();
        } else if (action == 2){
            QuanLy.hienthi();
        } else if (action == 3){
            QuanLy.suamathang();
        } else if (action == 4){
            QuanLy.xoamathang();
        }
    }
    
}
class HangHoa {
    protected String tenHangHoa;
    protected Date ngaySanXuat;
    protected Date hanSuDung;
    protected int giaThanh;
    protected HangHoa(String ten, Date nsx,Date hsd, int tien){
        tenHangHoa = ten;
        ngaySanXuat = nsx;
        hanSuDung = hsd;
        giaThanh = tien;
    }
    protected String getTen(){
        return tenHangHoa;
    }
    protected String getNsx (){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String ngaySanXuatFormatted = formatter.format(ngaySanXuat);
        return ngaySanXuatFormatted;
    }
    protected String getHsd (){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String hanSuDungFormatted = formatter.format(hanSuDung);
        return hanSuDungFormatted;
    }
    protected Date getNsxRaw (){
        return ngaySanXuat;
    }
    protected Date getHsdRaw (){
        return hanSuDung;
    }
    protected int getGia (){
        return giaThanh;
    }
    protected void editTen(String newTen){
        tenHangHoa = newTen;
    }
}
class QuanLy {
    protected static String idHangHoa;
    protected static String tenHangHoa;
    protected static Date ngaySanXuat;
    protected static Date hanSuDung;
    protected static int giaThanh;
    
    protected static void them() throws ParseException, FileNotFoundException, IOException, ClassNotFoundException{
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten hang hoa:");
        tenHangHoa = sc.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        System.out.print("Nhap ngay san xuat (d/m/Y):");
        ngaySanXuat = dateFormat.parse(sc.nextLine());
        System.out.print("Nhap ngay het han (d/m/Y):");
        hanSuDung = dateFormat.parse(sc.nextLine());
        System.out.print("Nhap gia thanh:");
        giaThanh = Integer.parseInt(sc.nextLine());
        
        HangHoa hangHoa = new HangHoa(tenHangHoa,ngaySanXuat,hanSuDung,giaThanh);
        
        System.out.println("\nDa luu thanh cong!");
        System.out.println("Ten: "+hangHoa.getTen());
        System.out.println("NSX | HSD: "+hangHoa.getNsx()+" | "+hangHoa.getHsd());
        System.out.println("Gia: "+hangHoa.getGia());
        
        Gson gson = new Gson();
        JsonReader jsonfile = new JsonReader(new FileReader("D:\\hanghoa.json"));
        
        Type founderListType = new TypeToken<ArrayList<HangHoa>>(){}.getType();
        List<HangHoa> danhSach;
        danhSach = gson.fromJson(jsonfile, founderListType);
        if(danhSach==null){
            danhSach = new ArrayList<HangHoa>();
        }
        danhSach.add(hangHoa);
        try (Writer writer = new FileWriter("D:\\hanghoa.json")) {
            gson.toJson(danhSach, writer);
        }
    }
    
    protected static void hienthi() throws FileNotFoundException{
        Gson gson = new Gson();
        JsonReader jsonfile = new JsonReader(new FileReader("D:\\hanghoa.json"));
        
        Type founderListType = new TypeToken<ArrayList<HangHoa>>(){}.getType();
        List<HangHoa> danhSach = gson.fromJson(jsonfile, founderListType);
        System.out.println("\nDanh sach mat hang:");
        int dem = 1;
        for(HangHoa hanghoa : danhSach) {
            System.out.println(dem+". "+hanghoa.getTen());
            System.out.println("\tNSX | HSD: "+hanghoa.getNsx()+" | "+hanghoa.getHsd());
            System.out.println("\tGia: "+hanghoa.getGia()+" VND");
            dem++;
        }
    }
    
    protected static void xoamathang() throws FileNotFoundException, IOException{
        Scanner sc = new Scanner(System.in);
        Gson gson = new Gson();
        JsonReader jsonfile = new JsonReader(new FileReader("D:\\hanghoa.json"));
        
        Type founderListType = new TypeToken<ArrayList<HangHoa>>(){}.getType();
        List<HangHoa> danhSach = gson.fromJson(jsonfile, founderListType);
        System.out.println("\nDanh sach mat hang:");
        int dem = 1;
        for(HangHoa hanghoa : danhSach) {
            System.out.println(dem+". "+hanghoa.getTen());
            System.out.println("\tNSX | HSD: "+hanghoa.getNsx()+" | "+hanghoa.getHsd());
            System.out.println("\tGia: "+hanghoa.getGia()+" VND");
            dem++;
        }
        System.out.print("Nhap thu tu cua mat hang can xoa:");
        int xoa = Integer.parseInt(sc.nextLine());
        danhSach.remove(xoa-1);
        try (Writer writer = new FileWriter("D:\\hanghoa.json")) {
            gson.toJson(danhSach, writer);
            System.out.println("Xoa mat hang "+xoa+"thanh cong!");
        }
    }
    
    protected static void suamathang() throws FileNotFoundException, IOException, ParseException{
        Scanner sc = new Scanner(System.in);
        Gson gson = new Gson();
        JsonReader jsonfile = new JsonReader(new FileReader("D:\\hanghoa.json"));
        
        Type founderListType = new TypeToken<ArrayList<HangHoa>>(){}.getType();
        List<HangHoa> danhSach = gson.fromJson(jsonfile, founderListType);
        System.out.println("\nDanh sach mat hang:");
        int dem = 1;
        for(HangHoa hanghoa : danhSach) {
            System.out.println(dem+". "+hanghoa.getTen());
            System.out.println("\tNSX | HSD: "+hanghoa.getNsx()+" | "+hanghoa.getHsd());
            System.out.println("\tGia: "+hanghoa.getGia()+" VND");
            dem++;
        }
        System.out.print("Nhap thu tu cua mat hang can sua:");
        int sua = Integer.parseInt(sc.nextLine());
        HangHoa suahanghoa = danhSach.get(sua-1);
        System.out.println("Nhap cac thong tin can sua. Bo trong (an enter) de giu nguyen noi dung.");
        System.out.println("Nhap ten hang hoa: ("+suahanghoa.getTen()+")");
        tenHangHoa = sc.nextLine();
        if(tenHangHoa.isEmpty() == true) tenHangHoa = suahanghoa.getTen();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        System.out.println("Nhap ngay san xuat (d/m/Y): ("+suahanghoa.getNsx()+")");
        String inputNsx = sc.nextLine();
        if(inputNsx.isEmpty() == true) ngaySanXuat = suahanghoa.getNsxRaw();
        else ngaySanXuat = dateFormat.parse(inputNsx);
        System.out.println("Nhap ngay het han (d/m/Y): ("+suahanghoa.getHsd()+")");
        String inputHsd = sc.nextLine();
        if(inputHsd.isEmpty() == true) hanSuDung = suahanghoa.getNsxRaw();
        else hanSuDung = dateFormat.parse(inputHsd);
        System.out.println("Nhap gia thanh: ("+suahanghoa.getGia()+")");
        String inputGia = sc.nextLine();
        if(inputGia.isEmpty() == true) giaThanh = suahanghoa.getGia();
        else giaThanh = Integer.parseInt(inputGia);
        HangHoa thaythe = new HangHoa(tenHangHoa,ngaySanXuat,hanSuDung,giaThanh);
        danhSach.set(sua-1, thaythe);
        try (Writer writer = new FileWriter("D:\\hanghoa.json")) {
            gson.toJson(danhSach, writer);
            System.out.println("Sua mat hang "+sua+"thanh cong!");
        }
    }
}