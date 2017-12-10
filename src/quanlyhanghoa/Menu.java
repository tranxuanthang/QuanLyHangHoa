/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlyhanghoa;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

/**
 *
 * @author trant
 */
public class Menu {

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
        } else if (action == 5){
            QuanLy.timkiem();
        }
    }
}