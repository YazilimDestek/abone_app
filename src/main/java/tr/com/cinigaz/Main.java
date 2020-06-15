package tr.com.cinigaz;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tr.com.cinigaz.controller.DokumController;

import java.awt.print.PrinterException;
import java.io.IOException;

public class Main {
    public static void getIslemi2 (Stage stage) throws IOException {

      //  private void getIslemi2() throws PrinterException, IOException {
            System.out.println("Fatura ekranına geçilecek...");
      //  D:\\kiosk-printer\\src\\main\\resources\\
            String ekrAdi = "dokumEkrani.fxml" ;

            FXMLLoader loader = new FXMLLoader(Main.class.getResource( ekrAdi) );
            loader.setLocation( Main.class.getResource( ekrAdi) );
            Parent anaekran= loader.load() ;
            Scene scene = new Scene(anaekran) ;

           // Stage stage = (Stage)  anaekran.getScene().getWindow();

            javafx.geometry.Rectangle2D bounds =  new javafx.geometry.Rectangle2D(50,50, 1000,1000);
            stage.setHeight(bounds.getHeight());
            stage.setWidth(bounds.getWidth());

            stage.setX(0);  stage.setY(0);
            stage.setScene(scene) ; /// IHBAR ekranını aç
          //  stage.show();

        }


}
