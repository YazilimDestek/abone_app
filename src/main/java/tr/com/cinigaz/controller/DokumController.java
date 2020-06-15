package tr.com.cinigaz.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import org.springframework.stereotype.Controller;
import tr.com.cinigaz.dto.Tahsilat;
import tr.com.cinigaz.util.FaturaIslem;
import tr.com.cinigaz.util.QrOlustur;
import tr.com.cinigaz.util.dosyaYer;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import static java.awt.Font.SANS_SERIF;


 @Controller
public class DokumController implements Initializable {

    @FXML private    ImageView  imgAltLogo , imgQR , imgQR1 , imgQR2 ;
    @FXML private    TextField  ftextfield, ftextfield1, ftextfield12;
    @FXML private    Pane       paneDiger , paneAdilFatura, paneMakbuz ;

    FaturaIslem faturaIslem ;

    private void faturaVerileri() {
        String metin1;
        String metin2;
        String metin3;

        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        String ad = new String("ÖZGÜR GÖLPUNARŞÇÖÜ");
        // ad= String.valueOf( ad.getBytes("UTF-8") );
        Tahsilat tahsilat = new Tahsilat();
        tahsilat.setMakbuzNo("A2020-0152");
        tahsilat.setTahsilatTarihi(new Date());
        tahsilat.setTahsilatTutari(100.18d);
        metin1 = new String("Abone : " + ad +
                "\n Makbuz No       : " + tahsilat.getMakbuzNo() +
                "\n Tahsilat Tarihi : " + df.format(tahsilat.getTahsilatTarihi()) +
                "\n Tahsilat Tutarı : " + String.format("%.2f", tahsilat.getTahsilatTutari()) +
                "\n  ---------------------------------------------------------------------  ");

        imgQR.setImage(new Image("https://openclipart.org/image/400px/svg_to_png/28780/ikabezier-flower-people.png"));
        imgQR1.setImage(  new Image("qr_ornek2.png") );
        imgQR2.setImage(new Image("https://openclipart.org/image/400px/svg_to_png/1030/johnny-automatic-bag-of-money.png"));

        ftextfield.setFont(Font.font(SANS_SERIF) );
        ftextfield.setText( metin1 );
        tahsilat.setMakbuzNo("456753657") ;
        ////diğerleri
        metin1 = new String("Abone : " + ad +
                 "  Makbuz No       : " + tahsilat.getMakbuzNo() +
                "\n Tahsilat Tarihi : " + df.format(tahsilat.getTahsilatTarihi()) +
                "\n Tahsilat Tutarı : " + String.format("%.2f", tahsilat.getTahsilatTutari()) +
                "\n ---------------------------------------------------------------------  " );
        // C:\kiosk_qr\qtest1.jpg   içinde QR dosyası olutur

        String yazi = new String(" Çinigaz Ödeme Makbuzu = "+tahsilat.getMakbuzNo() + "Tahsilat Tutarı : " + String.format("%.2f", tahsilat.getTahsilatTutari()) ) ;
        QrOlustur.qrKodDosya (yazi ,"qtest1.jpg"); //   <dosyadadi>.jpg

        imgQR.setImage(new Image(dosyaYer.QR_CODE_LOCATION));

        ftextfield1.setFont(Font.font(SANS_SERIF )  );
       /* ftextfield1.setText( metin1 );
        ftextfield2.setText( metin1 );*/

    }


    public void faturalariYazdir()  {

        faturaVerileri() ;
        faturaIslem.paneYazdir(  paneDiger, false) ;
        faturaIslem.paneYazdir(  paneAdilFatura,false ) ;
        faturaIslem.paneYazdir(  paneMakbuz,true) ;
     }


    @Override
    public void initialize(URL location, ResourceBundle resources)

    {
        try {
            faturaIslem = new FaturaIslem(paneDiger);


        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        faturalariYazdir() ; // yazdırma

    }

}
