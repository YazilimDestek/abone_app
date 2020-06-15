package tr.com.cinigaz.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Hashtable;


public class QrOlustur {

    private static void generateQRCode(String text, int width, int height, String fileName)
            throws WriterException, IOException {

        Hashtable<EncodeHintType, String> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        QRCodeWriter writer = new QRCodeWriter();
        BitMatrix matrix = writer.encode (  text, BarcodeFormat.QR_CODE, width, height,hints );

        Path path = FileSystems.getDefault().getPath(dosyaYer.QR_CODE_LOCATION) ;
       // Hazır dosyayaya yazmak için  MatrixToImageWriter.writeToPath(matrix, "JPG", path);//

        MatrixToImageWriter.writeToFile(matrix, "JPG", new File(fileName)) ;
    }


    public static void qrKodDosya(String str, String dosyaAdi) {
        try {
            generateQRCode(str, 200, 200, dosyaYer.QR_CODE_LOCATION);

        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}