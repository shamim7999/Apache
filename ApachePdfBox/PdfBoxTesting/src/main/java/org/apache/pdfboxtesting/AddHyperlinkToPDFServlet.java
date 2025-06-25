package org.apache.pdfboxtesting;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.params.URLInsertParameters;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.interactive.action.PDActionURI;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDAnnotationLink;
import org.apache.pdfbox.pdmodel.interactive.annotation.PDBorderStyleDictionary;
import org.apache.pdfbox.pdmodel.interactive.annotation.handlers.PDLinkAppearanceHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;

public class AddHyperlinkToPDFServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(AddHyperlinkToPDFServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            URLInsertParameters urlInsertParameters = new URLInsertParameters();

            urlInsertParameters.setXAxis(Float.parseFloat(request.getParameter("xAxis")));
            urlInsertParameters.setYAxis(Float.parseFloat(request.getParameter("yAxis")));
            urlInsertParameters.setHeight(Float.parseFloat(request.getParameter("height")));
            urlInsertParameters.setWidth(Float.parseFloat(request.getParameter("width")));
            urlInsertParameters.setDocumentPath(request.getParameter("documentPath"));
            urlInsertParameters.setOutputPath(request.getParameter("outputPath"));
            urlInsertParameters.setLinkText(request.getParameter("linkText"));
            urlInsertParameters.setUrl(request.getParameter("url"));

            addURLToPdf(urlInsertParameters);

            logger.debug("Successfully inserted URL into PDF");
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }

        catch(Exception ex){
            logger.error("Error while inserting URL into PDF", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void addURLToPdf(URLInsertParameters urlInsertParameters) throws IOException {
        PDDocument document = PDDocument.load(new File(urlInsertParameters.getDocumentPath()));

        PDPage page;
        if (document.getNumberOfPages() == 0) {
            page = new PDPage();
            document.addPage(page);
        } else {
            page = document.getPage(0); // or whichever page you want to add to
        }

        PDRectangle linkRect = new PDRectangle(urlInsertParameters.getXAxis(), urlInsertParameters.getYAxis(), urlInsertParameters.getWidth(), urlInsertParameters.getHeight());

        // Create a clickable link annotation
        PDAnnotationLink link = new PDAnnotationLink();
        link.setRectangle(linkRect);

        // Set the link action (URL)
        PDActionURI action = new PDActionURI();
        action.setURI(urlInsertParameters.getUrl()); // Your URL here
        link.setAction(action);

        // Add the link to the page
        page.getAnnotations().add(link);

        try (PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, true)) {
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.newLineAtOffset(urlInsertParameters.getXAxis(), urlInsertParameters.getYAxis());
            contentStream.showText(urlInsertParameters.getLinkText());
            contentStream.endText();
        }

        // Save the PDF
        document.save(urlInsertParameters.getOutputPath());
        document.close();
    }
}
