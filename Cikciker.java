/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cikciker;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author shest
 */
public class Cikciker {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        ArrayList<String> satirlar = new ArrayList<String>();
        File file=new File("C:/Users/shest/OneDrive/Masaüstü/veriler.txt");
        FileReader fileReader = new FileReader(file);
        BufferedReader br = new BufferedReader(fileReader);
        String line;
        
        while ((line = br.readLine()) != null) {
            satirlar.add(line);
        }
        
        br.close();
        
        for (int i = 0; i < satirlar.size(); i++) {
            String s = satirlar.get(i);
            s = s.substring(1, s.length()-1);
            satirlar.set(i, s);
        }

        Scanner scan = new Scanner(System.in);
        
        System.out.println("Lütfen tarihi giriniz : ÖR:26.01.1997 ");
        
        String tarih = scan.nextLine();

        
        System.out.println("Girilen tarihteki en popüler etiket: "+en_fazla_cikciklenen(tarih, satirlar));
                
        System.out.println("Verilerdeki id numarasının son rakamı 3 olan kullanıcı sayısı: "+sayi_dondur(satirlar));
        
        ulke_bul(satirlar);
        
        System.out.println("En kısa cikcik: "+en_kisa_cikcik(satirlar));
        
        
        
    }
    
    //1.Soru
    public static int sayi_dondur(ArrayList array){
        
        String[] dizi = new String[array.size()*5];
        String tum_veri = "";
        
        for (int i = 0; i < array.size(); i++) {
            tum_veri = tum_veri + array.get(i)+";";
        }
        
        dizi = tum_veri.split(";");
        int sayac =0;
        for (int i = 0; i < dizi.length; i = i+5) {
            String s = dizi[i];
            if(s.charAt(s.length()-1) == '3'){
                sayac++;
            }
        }
        return sayac;
    }
    
    //2.Soru
    public static void ulke_bul(ArrayList array){
        String dondur = "";
        String[][] dizi = new String[array.size()][5];
        for (int i = 0; i < array.size(); i++) {
            String s = (String) array.get(i);
            dizi[i] = s.split(";");
        }
        
        int en_buyuk =0;
        int sayi = 0;
        
        for (int i = 0; i < dizi.length; i++) {
            sayi = Integer.parseInt(dizi[i][4]);
            if(sayi> en_buyuk){
                en_buyuk = sayi;
            }
        }
        
        for (int i = 0; i < dizi.length; i++) {
            int kontrol = Integer.parseInt(dizi[i][4]);
            if(kontrol == en_buyuk){
                dondur =  dizi[i][0].substring(0, 2);
            }  
        }
        switch (dondur){
        
            case "DE":
                System.out.println("En fazla yeniden cikciklenen kullanıcının ülkesi: Almanya");
                break;
            case "TR":
                System.out.println("En fazla yeniden cikciklenen kullanıcının ülkesi: Türkiye");
                break;
            case "FR":
                System.out.println("En fazla yeniden cikciklenen kullanıcının ülkesi: Fransa");
                break;
            
        }
    }
    //3. Soru
    public static String en_fazla_cikciklenen(String tarih,ArrayList array){
        
        String[][] dizi = new String[array.size()][5];
        for (int i = 0; i < array.size(); i++) {
            String s = (String) array.get(i);
            dizi[i] = s.split(";");
        }
        int en_yuksek = 0;
        for (int i = 0; i < dizi.length; i++) {
            if(dizi[i][3].equals(tarih)){
                if(Integer.parseInt(dizi[i][4])>en_yuksek){
                    en_yuksek = Integer.parseInt(dizi[i][4]);
                }
            }
        }
        String dondur = "Girdiğiniz tarihte cikcik bulunamadı.";
        for (int i = 0; i < dizi.length; i++) {
            if (dizi[i][3].equals(tarih) && Integer.parseInt(dizi[i][4])== en_yuksek) {
                dondur= dizi[i][2];
                break;
            }
        }
        return dondur;
    }
    
    //4. Soru
    public static String en_kisa_cikcik(ArrayList array){
        
        String[][] dizi = new String[array.size()][5];
        for (int i = 0; i < array.size(); i++) {
            String s = (String) array.get(i);
            dizi[i] = s.split(";");
        }
        String en_kisa_cikcik = "Hata !!!";
        int en_kisa=140;
        for (int i = 0; i < dizi.length; i++) {
            if (dizi[i][1].length()<en_kisa) {
                en_kisa_cikcik = dizi[i][1];
                en_kisa = dizi[i][1].length();
            }
        }
        return en_kisa_cikcik;
    }
    
}
