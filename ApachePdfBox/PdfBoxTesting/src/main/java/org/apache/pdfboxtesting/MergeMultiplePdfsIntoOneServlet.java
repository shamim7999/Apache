package org.apache.pdfboxtesting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.params.ImageLoadParameters;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.apache.pdfbox.multipdf.PDFMergerUtility;


public class MergeMultiplePdfsIntoOneServlet extends HttpServlet {
    private static final Logger logger = LoggerFactory.getLogger(MergeMultiplePdfsIntoOneServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            response.setContentType("text/html");
            logger.debug("Started to merge pdfs into one");
            String samplePdfsPath = request.getParameter("sourcePath");
            String singlePdfPath = request.getParameter("destinationPath");
            logger.debug("Sample PDFs Path: {}, Merged Pdf Path {}",samplePdfsPath, singlePdfPath);
            List<ByteArrayInputStream> pdfStreams = getPdfStreams(samplePdfsPath);
            // 2. Merge all streams into one PDF
            mergePdfs(pdfStreams, singlePdfPath);


            logger.debug("Successfully merged PDFs into one page");
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }
        catch(Exception ex){
            logger.error("Error while merging pdfs into one servlet", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    public static List<ByteArrayInputStream> getPdfStreams(String directory) throws IOException {
        List<ByteArrayInputStream> streams = new ArrayList<>();
        Files.walk(Paths.get(directory))
                .filter(Files::isRegularFile)
                .filter(path -> path.toString().toLowerCase().endsWith(".pdf"))
                .forEach(pdfPath -> {
                    try {
                        byte[] pdfBytes = Files.readAllBytes(pdfPath);
                        streams.add(new ByteArrayInputStream(pdfBytes));
                    } catch (IOException e) {
                        System.err.println("Skipping " + pdfPath + ": " + e.getMessage());
                    }
                });

        if (streams.isEmpty()) {
            throw new IOException("No PDF files found in " + directory);
        }

        return streams;
    }

    public static void mergePdfs(List<ByteArrayInputStream> pdfStreams, String outputPath) throws IOException {
        PDFMergerUtility merger = new PDFMergerUtility();
        merger.setDestinationFileName(outputPath);

        try (PDDocument mergedDoc = new PDDocument()) {
            for (ByteArrayInputStream stream : pdfStreams) {
                try (PDDocument doc = PDDocument.load(stream)) {
                    merger.appendDocument(mergedDoc, doc);
                }
            }
            mergedDoc.save(outputPath);
        }
    }

}