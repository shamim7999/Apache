package org.apache.pdfboxtesting;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.params.ImageLoadParameters;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

            addImageToPdf(imageLoadParameters);

            logger.debug("Successfully inserted image into pdf");
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }

        catch(Exception ex){
            logger.error("Error while merging pdfs into one servlet", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void addImageToPdf(ImageLoadParameters imageLoadParameters) throws IOException {
        // Validate input parameters
        if (imageLoadParameters == null) {
            throw new IllegalArgumentException("ImageLoadParameters cannot be null");
        }
        if (imageLoadParameters.getImageFilePath() == null || imageLoadParameters.getImageFilePath().isEmpty()) {
            throw new IllegalArgumentException("Image file path cannot be null or empty");
        }

        PDDocument document = PDDocument.load(new File(imageLoadParameters.getDocumentPath()));

        // Load the image file
        PDImageXObject pdImage = PDImageXObject.createFromFile(
                imageLoadParameters.getImageFilePath(),
                document // assuming 'document' is a PDDocument instance available in your class
        );

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
                    imageLoadParameters.getWidth(),
                    imageLoadParameters.getHeight()
            );
        }

        document.save(imageLoadParameters.getOutputPath());
        document.close();
    }
}
