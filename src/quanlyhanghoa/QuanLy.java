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
import java.io.Writer;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author trant
 */
class QuanLy {
    protected static String idHangHoa;
    protected static String tenHangHoa;
    protected static Date ngaySanXuat;
    protected static Date hanSuDung;
    protected static int giaThanh;
    
    protected static void them() throws ParseException, IOException, ClassNotFoundException, HsdException{
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap ten hang hoa:");
        tenHangHoa = sc.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        System.out.print("Nhap ngay san xuat (d/m/Y):");
        try {
            ngaySanXuat = dateFormat.parse(sc.nextLine());
        }
        catch (ParseException e){
            System.out.print("Dinh dang ngay thang sai!");
            throw e;
        }
        System.out.print("Nhap ngay het han (d/m/Y):");
        try {
            hanSuDung = dateFormat.parse(sc.nextLine());
        }
        catch (ParseException e){
            System.out.print("Dinh dang ngay thang sai!");
            throw e;
        }
        if(ngaySanXuat.getTime()>hanSuDung.getTime()){
            throw new HsdException();
        }
        System.out.print("Nhap gia thanh:");
        giaThanh = Integer.parseInt(sc.nextLine());
        
        HangHoa hangHoa = new HangHoa(tenHangHoa,ngaySanXuat,hanSuDung,giaThanh);
        
        Gson gson = new Gson();
        JsonReader jsonfile;
        try {
            jsonfile = new JsonReader(new FileReader("D:\\hanghoa.json"));
        } catch (FileNotFoundException e){
            System.out.print("File chua duoc tao!");
            throw e;
        }
        Type founderListType = new TypeToken<ArrayList<HangHoa>>(){}.getType();
        List<HangHoa> danhSach;
        danhSach = gson.fromJson(jsonfile, founderListType);
        if(danhSach==null){
            danhSach = new ArrayList<HangHoa>();
        }
        danhSach.add(hangHoa);
        try (Writer writer = new FileWriter("D:\\hanghoa.json")) {
            gson.toJson(danhSach, writer);
        } catch (FileNotFoundException e) {
            System.out.print("File chua duoc tao!");
        }
        System.out.println("\nDa luu thanh cong!");
        System.out.println("Ten: "+hangHoa.getTen());
        System.out.println("NSX | HSD: "+hangHoa.getNsx()+" | "+hangHoa.getHsd());
        System.out.println("Gia: "+hangHoa.getGia());
    }
    
    protected static void hienthi() throws FileNotFoundException{
        Gson gson = new Gson();
        JsonReader jsonfile;
        try {
            jsonfile = new JsonReader(new FileReader("D:\\hanghoa.json"));
        } catch (FileNotFoundException e){
            System.out.print("File chua duoc tao!");
            throw e;
        }
        
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
        JsonReader jsonfile;
        try {
            jsonfile = new JsonReader(new FileReader("D:\\hanghoa.json"));
        } catch (FileNotFoundException e){
            System.out.print("File chua duoc tao!");
            throw e;
        }
        
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
        JsonReader jsonfile;
        try {
            jsonfile = new JsonReader(new FileReader("D:\\hanghoa.json"));
        } catch (FileNotFoundException e){
            System.out.print("File chua duoc tao!");
            throw e;
        }
        
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
    protected static void timkiem() throws FileNotFoundException{
        Scanner sc = new Scanner(System.in);
        System.out.print("Tim kiem hang hoa theo ten.\nNhap ten muon tim kiem:");
        String tim = sc.nextLine();
        String patternString = ".*"+tim+".*";
        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
        Gson gson = new Gson();
        JsonReader jsonfile;
        try {
            jsonfile = new JsonReader(new FileReader("D:\\hanghoa.json"));
        } catch (FileNotFoundException e){
            System.out.print("File chua duoc tao!");
            throw e;
        }
        
        Type founderListType = new TypeToken<ArrayList<HangHoa>>(){}.getType();
        List<HangHoa> danhSach = gson.fromJson(jsonfile, founderListType);
        System.out.println("\nKet qua tim kiem:");
        int dem = 1;
        for(HangHoa hanghoa : danhSach) {
            Matcher matcher = pattern.matcher(hanghoa.getTen());
            boolean isMatched = matcher.matches();
            if(isMatched==true){
                System.out.println(dem+". "+hanghoa.getTen());
                System.out.println("\tNSX | HSD: "+hanghoa.getNsx()+" | "+hanghoa.getHsd());
                System.out.println("\tGia: "+hanghoa.getGia()+" VND");
            }
            dem++;
        }
    }
}
