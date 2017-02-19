package ua.challenge.pdf.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by d.bakal on 19.02.2017.
 */
public class ITextTest {
    @Test
    public void createPdfA() throws IOException, DocumentException {
        final float MARGIN_OF_ONE_CM = 28.8f;
        final String file = "pdf_a1a_images.pdf";
        final String LOGO = "/files/pdf/images/hero.jpg";

        String pathFont = getClass().getResource("/files/pdf/ttf/OpenSans-Regular.ttf").toString();
        Font font = new Font(BaseFont.createFont(pathFont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        Document document = new Document(PageSize.A4, MARGIN_OF_ONE_CM, MARGIN_OF_ONE_CM, MARGIN_OF_ONE_CM, MARGIN_OF_ONE_CM);

        PdfAWriter pdfWriter = PdfAWriter.getInstance(document, new FileOutputStream(file), PdfAConformanceLevel.PDF_A_1A);
        document.addAuthor("Author");
        document.addSubject("Subject");
        document.addLanguage("nl-nl");
        document.addCreationDate();
        document.addCreator("Creator");
        document.addTitle("title");

        pdfWriter.setTagged();
        pdfWriter.createXmpMetadata();

        document.open();

        String pathColorProfile = getClass().getClassLoader().getResource("files/pdf/pdfa/sRGB.icc").getFile();
        File fileColorProfile = new File(pathColorProfile);
        ICC_Profile iccProfile = ICC_Profile.getInstance(new FileInputStream(fileColorProfile));
        pdfWriter.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", iccProfile);

        Paragraph element = new Paragraph("Hello World iText", font);
        document.add(element);

        Image logoImage = Image.getInstance(getClass().getResource(LOGO));
        logoImage.setAccessibleAttribute(PdfName.ALT, new PdfString("Logo"));
        document.add(logoImage);

        document.close();
    }
}
