package ua.challenge.pdf.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDCIDFontType0;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Created by d.bakal on 18.02.2017.
 */
public class PDFBoxTest {
    @Test
    public void createBlankPDF() throws IOException {
        // Create a new empty document
        PDDocument document = new PDDocument();

        // Create a new blank page and add it to the document
        PDPage page = new PDPage();
        document.addPage(page);

        // Save the newly created document
        document.save("BlankPage.pdf");

        // finally make sure that the document is properly closed.
        document.close();
    }

    @Test
    public void createPDFWithBaseFont() throws IOException {
        // Create a document and add a page to it
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Create a new font object selecting one of the PDF base fonts
        PDFont font = PDType1Font.HELVETICA_BOLD;

        // Start a new content stream which will "hold" the to be created content
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Define a text content stream using the selected font, moving the cursor and drawing the text "Hello World"
        contentStream.beginText();
        contentStream.setFont(font, 12);
        contentStream.newLineAtOffset(100, 700);
        contentStream.showText("Hello World!");
        contentStream.endText();

        // Make sure that the content stream is closed:
        contentStream.close();

        // Save the results and ensure that the document is properly closed:
        document.save("Hello World.pdf");
        document.close();
    }

    @Test
    public void createEncryptedPdf() throws IOException {
        String testPath = getClass().getClassLoader().getResource("files/pdf/filename.pdf").getFile();
        File testFile = new File(testPath);
        PDDocument document = PDDocument.load(testFile);

        // Define the length of the encryption key.
        // Possible values are 40 or 128 (256 will be available in PDFBox 2.0).
        int keyLength = 128;

        AccessPermission permissions = new AccessPermission();

        // Disable printing, everything else is allowed
        permissions.setCanPrint(false);

        // Owner password (to open the file with all permissions) is "12345"
        // User password (to open the file but with restricted permissions, is empty here)
        StandardProtectionPolicy protectionPolicy = new StandardProtectionPolicy("12345", "", permissions);
        protectionPolicy.setEncryptionKeyLength(keyLength);
        protectionPolicy.setPermissions(permissions);
        document.protect(protectionPolicy);

        document.save("filename-encrypted.pdf");
        document.close();
    }

    @Test
    public void validatePdfA() {
    }
}
