package org.apache.pdfboxtesting;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.common.enums.PdfBoxLibrary;
import org.apache.params.ImageLoadParameters;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class InsertImageToPdfServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(InsertImageToPdfServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            ImageLoadParameters imageLoadParameters = new ImageLoadParameters();

            imageLoadParameters.setXAxis(Float.parseFloat(request.getParameter("xAxis")));
            imageLoadParameters.setYAxis(Float.parseFloat(request.getParameter("yAxis")));
            imageLoadParameters.setImageFilePath(request.getParameter("imageFilePath"));
            imageLoadParameters.setHeight(Float.parseFloat(request.getParameter("height")));
            imageLoadParameters.setWidth(Float.parseFloat(request.getParameter("width")));
            imageLoadParameters.setDocumentPath(request.getParameter("documentPath"));
            imageLoadParameters.setOutputPath(request.getParameter("outputPath"));

            String library = request.getParameter("pdfLibrary");
            PdfBoxLibrary selectedLibrary = PdfBoxLibrary.valueOf(library);

            switch(selectedLibrary) {
                case ITEXT:
                    addImageToPdfByIText(imageLoadParameters);
                    break;
                case APACHE_PDFBOX:
                    addImageToPdfByApachePdfBox(imageLoadParameters);
                    break;
            }



            logger.debug("Successfully inserted image into pdf");
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }

        catch(Exception ex){
            logger.error("Error while merging pdfs into one servlet", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void addImageToPdfByApachePdfBox(ImageLoadParameters imageLoadParameters) throws IOException {
        // Validate input parameters
        if (imageLoadParameters == null) {
            throw new IllegalArgumentException("ImageLoadParameters cannot be null");
        }
        if (imageLoadParameters.getImageFilePath() == null || imageLoadParameters.getImageFilePath().isEmpty()) {
            throw new IllegalArgumentException("Image file path cannot be null or empty");
        }

        PDDocument document = PDDocument.load(new File(imageLoadParameters.getDocumentPath()));

        BufferedImage bufferedImage = ImageIO.read(new File(imageLoadParameters.getImageFilePath()));
        // Original image dimensions
        float originalWidth = bufferedImage.getWidth();
        float originalHeight = bufferedImage.getHeight();

        // Desired custom width (from user)
        float targetWidth = imageLoadParameters.getWidth();
        float targetHeight = targetWidth * (originalHeight / originalWidth);  // maintain aspect ratio


        PDImageXObject pdImage = LosslessFactory.createFromImage(document, bufferedImage);

        // Get the first page (or create one if none exists)
        PDPage page;
        if (document.getNumberOfPages() == 0) {
            page = new PDPage();
            document.addPage(page);
        } else {
            page = document.getPage(0); // or whichever page you want to add to
        }

        // Add the image to the page
        try (PDPageContentStream contentStream = new PDPageContentStream(
                document,
                page,
                PDPageContentStream.AppendMode.APPEND,
                true,
                true)) {

            contentStream.drawImage(
                    pdImage,
                    imageLoadParameters.getXAxis(),
                    imageLoadParameters.getYAxis(),
                    targetWidth,
                    targetHeight
            );
        }

        document.save(imageLoadParameters.getOutputPath());
        document.close();
    }

    public void addImageToPdfByIText(ImageLoadParameters imageLoadParameters) throws IOException {
        // Validate input parameters
        if (imageLoadParameters == null) {
            throw new IllegalArgumentException("ImageLoadParameters cannot be null");
        }
        if (imageLoadParameters.getImageFilePath() == null || imageLoadParameters.getImageFilePath().isEmpty()) {
            throw new IllegalArgumentException("Image file path cannot be null or empty");
        }

        // Open existing document
        PdfDocument pdfDoc = new PdfDocument(
                new PdfReader(imageLoadParameters.getDocumentPath()),
                new PdfWriter(imageLoadParameters.getOutputPath())
        );

        // Load image
        ImageData imageData = ImageDataFactory.create(imageLoadParameters.getImageFilePath());
        Image image = new Image(imageData);
        image.setFixedPosition(
                imageLoadParameters.getXAxis(),
                imageLoadParameters.getYAxis()
        );
        image.scaleToFit(
                imageLoadParameters.getWidth(),
                imageLoadParameters.getHeight()
        );

        // Add image to the first page (or create one if none exists)
        if (pdfDoc.getNumberOfPages() == 0) {
            pdfDoc.addNewPage();
        }
        PdfPage page = pdfDoc.getPage(1);

        Document doc = new Document(pdfDoc);
        doc.showTextAligned("", 0, 0, TextAlignment.LEFT, VerticalAlignment.BOTTOM, 0); // dummy to keep layout engine happy
        doc.add(image);

        doc.close();
        pdfDoc.close();
    }
}
