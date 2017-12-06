/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhanghoa;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        System.out.println("Chon hanh dong:\n1. Them\n2. Sua\n3. Xoa\n4. Tim kiem");
        int action = Integer.parseInt(sc.nextLine());
        if(action == 1){
            QuanLy.them();
        } else if (action == 2){
            QuanLy.lay();
        }
    }
    
}
class HangHoa implements Serializable{
    protected String tenHangHoa;
    protected Date ngaySanXuat;
    protected Date hanSuDung;
    protected int giaThanh;
    protected void add(String ten, Date nsx,Date hsd, int tien){
        tenHangHoa = ten;
        ngaySanXuat = nsx;
        hanSuDung = hsd;
        giaThanh = tien;
    }
    protected String getTen(){
        return tenHangHoa;
    }
    protected Date getNsx (){
        return ngaySanXuat;
    }
    protected Date getHsd (){
        return hanSuDung;
    }
    protected int getGia (){
        return giaThanh;
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("d/m/Y");
        System.out.print("Nhap ngay san xuat (d/m/Y):");
        ngaySanXuat = dateFormat.parse(sc.nextLine());
        System.out.print("Nhap ngay het han (d/m/Y):");
        hanSuDung = dateFormat.parse(sc.nextLine());
        System.out.print("Nhap gia thanh:");
        giaThanh = Integer.parseInt(sc.nextLine());
        //System.out.println("NSX: "+ngaySanXuat+"\nHSD: "+hanSuDung);
        HangHoa hangHoa = new HangHoa();
        hangHoa.add(tenHangHoa,ngaySanXuat,hanSuDung,giaThanh);
        System.out.println("Da luu thanh cong!");
        System.out.println("Ten: "+hangHoa.getTen());
        System.out.println("Ngay San Xuat: "+hangHoa.getNsx());
        System.out.println("Han Dung: "+hangHoa.getHsd());
        System.out.println("Gia: "+hangHoa.getGia());
        File fs = new File("student.dat");
        FileOutputStream f = new FileOutputStream("D:\\hanghoa.dat");
        ObjectOutputStream oStream;
        if(hasObject(fs) == true){
            oStream = new ObjectOutputStream(f) {
                @Override
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            };
        }else {
            oStream = new ObjectOutputStream(f);
        }
       
        oStream.writeObject(hangHoa);
        oStream.close();
    }
    protected static void lay() throws FileNotFoundException, IOException, ClassNotFoundException{
        try {
            File f = new File("hanghoa.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream inStream = new ObjectInputStream(fis);
            Object s;
            int i = 0;
            while (true) {
                s = inStream.readObject();
                System.out.println(++i + ":" + s.toString());
            }
        } catch (ClassNotFoundException e) {
        } catch (IOException e) {
        }try {
            File f = new File("student.dat");
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream inStream = new ObjectInputStream(fis);
            Object s;
            int i = 0;
            while (true) {
                //s = inStream.readObject();
                //System.out.println(++i + ":" + s.toString());
                HangHoa detme = (HangHoa) inStream.readObject();
                System.out.println(detme.getNsx());
                i++;
            }
        } catch (ClassNotFoundException e) {
        } catch (IOException e) {
        }
    }
    
    public static boolean hasObject(File f) {
        // thu doc xem co object nao chua
        FileInputStream fi;
        boolean check = true;
        try {
            fi = new FileInputStream(f);
            ObjectInputStream inStream = new ObjectInputStream(fi);
            if (inStream.readObject() == null) {
                check = false;
            }
            inStream.close();
 
        } catch (FileNotFoundException e) {
            check = false;
        } catch (IOException e) {
            check = false;
        } catch (ClassNotFoundException e) {
            check = false;
            e.printStackTrace();
        }
        return check;
    }
}