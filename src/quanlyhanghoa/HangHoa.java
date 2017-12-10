/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhanghoa;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author trant
 */
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