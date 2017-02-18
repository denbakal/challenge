package ua.challenge.pdf.pdfbox;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.color.PDOutputIntent;
import org.apache.pdfbox.preflight.Format;
import org.apache.pdfbox.preflight.PreflightDocument;
import org.apache.pdfbox.preflight.ValidationResult;
import org.apache.pdfbox.preflight.exception.SyntaxValidationException;
import org.apache.pdfbox.preflight.parser.PreflightParser;
import org.apache.xmpbox.XMPMetadata;
import org.apache.xmpbox.schema.DublinCoreSchema;
import org.apache.xmpbox.schema.PDFAIdentificationSchema;
import org.apache.xmpbox.type.BadFieldValueException;
import org.apache.xmpbox.xml.XmpSerializer;
import org.junit.Test;

import java.io.*;

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
    public void validatePdfA1b() throws IOException { // PDF/A-1b validation
        validatePdfFormat(true);
    }

    @Test
    public void validatePdfA1a() throws IOException { // PDF/A-1a validation
        validatePdfFormat(false);
    }

    private void validatePdfFormat(boolean isFormatB) throws IOException {
        ValidationResult result;
        String currentFormat = null;

        String testPath = getClass().getClassLoader().getResource("files/pdf/filename.pdf").getFile();
        File testFile = new File(testPath);
        PreflightParser parser = new PreflightParser(testFile);

        try {
            /* Parse the PDF file with PreflightParser that inherits from the NonSequentialParser.
             * Some additional controls are present to check a set of PDF/A requirements.
             * (Stream length consistency, EOL after some Keyword...)
             */

            if (isFormatB) {
                parser.parse();
                currentFormat = "PDF/A-1b";
            } else {
                parser.parse(Format.PDF_A1A);
                currentFormat = "PDF/A-1a";
            }

            /* Once the syntax validation is done,
             * the parser can provide a PreflightDocument
             * (that inherits from PDDocument)
             * This document process the end of PDF/A validation.
             */
            PreflightDocument document = parser.getPreflightDocument();
            document.validate();

            // Get validation result
            result = document.getResult();
            document.close();
        } catch (SyntaxValidationException e) {
            /* the parse method can throw a SyntaxValidationException
             * if the PDF file can't be parsed.
             * In this case, the exception contains an instance of ValidationResult
             */
            result = e.getResult();
        }

        // display validation result
        if (result.isValid()) {
            System.out.println("The file " + testFile + " is a valid " + currentFormat + " file");
        } else {
            System.out.println("The file" + testFile + " is not valid, error(s) :");
            result.getErrorsList()
                    .forEach(error -> System.out.println(error.getErrorCode() + " : " + error.getDetails()));
        }
    }

    @Test
    public void createPdfA1b() throws Exception {
        String file = "PdfA1b.pdf";

        PDDocument document = new PDDocument();

        PDPage page = new PDPage();
        document.addPage(page);

        // load the font used in the document (from dependency)
        // PDF/A specification enforces that the fonts used in the document are present in the PDF File
        String pathFontFile = getClass().getClassLoader().getResource("files/pdf/ttf/LiberationSans-Regular.ttf").getFile();
        File fontFile = new File(pathFontFile);
        PDFont font = PDType0Font.load(document, fontFile);

        if (!font.isEmbedded()) {
            throw new IllegalStateException("PDF/A compliance requires that all fonts used for"
                    + " text rendering in rendering modes other than rendering mode 3 are embedded.");
        }

        // create a page with the message
        PDPageContentStream contents = new PDPageContentStream(document, page);
        contents.beginText();
        contents.setFont(font, 12);
        contents.newLineAtOffset(100, 700);
        contents.showText("PDF/A Creation");
        contents.endText();
        contents.saveGraphicsState();
        contents.close();

        // include XMP metadata block
        // It is required to have XMP metadata defined in the PDF
        XMPMetadata xmp = XMPMetadata.createXMPMetadata();

        try {
            DublinCoreSchema dc = xmp.createAndAddDublinCoreSchema();
            dc.setTitle(file);

            PDFAIdentificationSchema id = xmp.createAndAddPFAIdentificationSchema();
            id.setPart(1);
            id.setConformance("B");

            XmpSerializer serializer = new XmpSerializer();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            serializer.serialize(xmp, baos, true);

            PDMetadata metadata = new PDMetadata(document);
            metadata.importXMPMetadata(baos.toByteArray());
            document.getDocumentCatalog().setMetadata(metadata);
        } catch(BadFieldValueException e) {
            // won't happen here, as the provided value is valid
            throw new IllegalArgumentException(e);
        }

        // include color profile
        // It is mandatory to include the color profile used by the document
        String pathColorProfile = getClass().getClassLoader().getResource("files/pdf/pdfa/sRGB.icc").getFile();
        File colorProfileFile = new File(pathColorProfile);
        InputStream colorProfile = new FileInputStream(colorProfileFile);

        PDOutputIntent intent = new PDOutputIntent(document, colorProfile);
        intent.setInfo("sRGB IEC61966-2.1");
        intent.setOutputCondition("sRGB IEC61966-2.1");
        intent.setOutputConditionIdentifier("sRGB IEC61966-2.1");
        intent.setRegistryName("http://www.color.org");
        document.getDocumentCatalog().addOutputIntent(intent);

        // save document
        document.save(file);
        document.close();
    }
}
