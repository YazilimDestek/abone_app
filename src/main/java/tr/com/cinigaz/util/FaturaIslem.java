package tr.com.cinigaz.util;

import com.sun.javafx.print.PrintHelper;
import com.sun.javafx.print.Units;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.scene.layout.Pane;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class FaturaIslem {


    private  javafx.print.PrinterJob printerJob ;
    private PageLayout pageLayout ;


    private void printGenel(Pane paneBas ) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        printerJob = javafx.print.PrinterJob.createPrinterJob(); //

        if (printerJob != null) {
            Constructor<Paper> c = javafx.print.Paper.class.getDeclaredConstructor(String.class,
                    double.class, double.class, Units.class);
            c.setAccessible(true);
            // javafx.print.Paper photo2 = c.newInstance("10x15", 80, 100, Units.MM);
            // javafx.print.Paper photo2 = c.newInstance("10x15",  paneBas.getWidth()+2 , paneBas.getHeight() , Units.POINT);
            double nWidth = paneBas.getBoundsInParent().getWidth();
            double nHeight = paneBas.getBoundsInParent().getHeight();

            Printer printer = printerJob.getPrinter().getDefaultPrinter();
            pageLayout = printer.getDefaultPageLayout();

            double pWidth = pageLayout.getPrintableWidth();
            double pHeight = pageLayout.getPrintableHeight();
            System.out.println("Pagelayout dimensions are " + pWidth + " width and " + pHeight + " height");

            System.out.println("Node's dimensions are " + nWidth + " width and " + nHeight + " height");

            // javafx.print.Paper photo2 = c.newInstance("10x15", nWidth+150, nHeight , Units.POINT );
            //  javafx.print.Paper photo = PrintHelper.createPaper("ornek1", 80, 100, Units.MM);
            javafx.print.Paper photo2 = c.newInstance("ornek1", nWidth + 150, nHeight + 150, Units.POINT);
            javafx.print.Paper photo3 = PrintHelper.createPaper("ornek3", nWidth * 2, nHeight * 2, Units.POINT);
            // paneBas.setPrefSize(400,400);
            //   paneBas.getTransforms().add(new Scale(0.7, 0.7)); // En , boy

            // PageLayout pageLayout  =printerJob.getPrinter().createPageLayout(photo, PageOrientation.PORTRAIT, Printer.MarginType.EQUAL);

            pageLayout = printerJob.getPrinter().createPageLayout(photo3 //javafx.print.Paper.LEGAL// JIS_B4
                    , PageOrientation.PORTRAIT, 0, 0, 0, 0);
            //
            System.out.printf("pane yer: %s %s    \n\n", paneBas.getWidth(), paneBas.getHeight());
            System.out.printf("Ayarlar : WH  %s  %s  leftM %s topM %s ", pageLayout.getPrintableWidth(), pageLayout.getPrintableHeight(), pageLayout.getLeftMargin(), pageLayout.getTopMargin());
            paneBas.setLayoutX(2);
            paneBas.setLayoutY(2);

        }
    }
    //////////////////////////////////
    public    void paneYazdir(Pane paneBas , boolean sonIslem)
    {
        printerJob.getJobSettings().setJobName("KIOSK Printer "   );
        double paneX = paneBas.getBoundsInParent().getMinX() ;
        double paneY = paneBas.getBoundsInParent().getMinY();

        boolean success = printerJob.printPage( pageLayout, paneBas );

        if (success || sonIslem ) {
            printerJob.endJob();
              System.exit(0);
        }
        // Eski yerine taşııı
        paneBas.setLayoutX(paneX);
        paneBas.setLayoutY(paneY);
    }



//////////////////////////////////////
   public  FaturaIslem(Pane paneYazilacak ) // Printer için ölçüleri alınacak Pane
           throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {

       printGenel(paneYazilacak ); // sayfa ayarları

   }

}
