package ua.challenge.pdf.itext;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.junit.Test;

import java.io.*;
import java.util.StringTokenizer;

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

    @Test
    public void createPdfA3() throws IOException, DocumentException {
        final String FONT = "/files/pdf/ttf/OpenSans-Regular.ttf";
        final String BOLD = "/files/pdf/ttf/OpenSans-Bold.ttf";
        final String DEST = "pdf_a_3a.pdf";

        String pathFont = getClass().getResource(FONT).toString();
        String pathBold = getClass().getResource(BOLD).toString();

        Font font = new Font(BaseFont.createFont(pathFont, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        Font bold = new Font(BaseFont.createFont(pathBold, BaseFont.IDENTITY_H, BaseFont.EMBEDDED), 10);
        Document document = new Document(PageSize.A4.rotate());
        PdfAWriter writer = PdfAWriter.getInstance(document, new FileOutputStream(DEST), PdfAConformanceLevel.PDF_A_3B);
        writer.createXmpMetadata();
        document.open();

        String pathColorProfile = getClass().getClassLoader().getResource("files/pdf/pdfa/sRGB_CS_profile.icm").getFile();
        File file = new File(pathColorProfile);
        ICC_Profile icc = ICC_Profile.getInstance(new FileInputStream(file));
        writer.setOutputIntents("Custom", "", "http://www.color.org", "sRGB IEC61966-2.1", icc);

        PdfDictionary parameters = new PdfDictionary();
        parameters.put(PdfName.MODDATE, new PdfDate());

        String pathDataFile = getClass().getClassLoader().getResource("files/pdf/pdfa/data/united_states.csv").getFile();
        PdfFileSpecification fileSpec = PdfFileSpecification.fileEmbedded(
                writer, pathDataFile,
                "united_states.csv", null, "text/csv", parameters, 0);
        fileSpec.put(new PdfName("AFRelationship"), new PdfName("Data"));
        writer.addFileAttachment("united_states.csv", fileSpec);

        PdfArray array = new PdfArray();
        array.add(fileSpec.getReference());
        writer.getExtraCatalog().put(new PdfName("AF"), array);

        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);
        table.setWidths(new int[]{4, 1, 3, 4, 3, 3, 3, 3, 1});
        BufferedReader br = new BufferedReader(new FileReader(pathDataFile));
        String line = br.readLine();
        process(table, line, bold);
        table.setHeaderRows(1);
        while ((line = br.readLine()) != null) {
            process(table, line, font);
        }
        br.close();
        document.add(table);
        document.close();
    }

    private void process(PdfPTable table, String line, Font font) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            table.addCell(new Phrase(tokenizer.nextToken(), font));
        }
    }
}
