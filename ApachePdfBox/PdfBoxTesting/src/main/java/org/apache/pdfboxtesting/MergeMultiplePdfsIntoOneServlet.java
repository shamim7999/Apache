package org.apache.pdfboxtesting;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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
            mergePdfsIntoOne(samplePdfsPath, singlePdfPath);
            logger.debug("Successfully merged PDFs into one page");
            request.getRequestDispatcher("success.jsp").forward(request, response);
        }
        catch(Exception ex){
            logger.error("Error while merging pdfs into one servlet", ex);
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void mergePdfsIntoOne(String samplePdfsPath, String singlePdfPath) throws Exception {
        try {
            List<String> pdfFileNames = Files.list(Paths.get(samplePdfsPath))
                    .filter(path -> path.toString().toLowerCase().endsWith(".pdf"))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toList());


            logger.debug("Pdf Files are: {}", pdfFileNames);

            PDFMergerUtility merger = new PDFMergerUtility();

            for (String pdfFileName : pdfFileNames) {
                merger.addSource(new File(samplePdfsPath + File.separator +pdfFileName));
            }
            File file = new File(singlePdfPath);

            if (file.createNewFile()) {
                logger.debug("File created: {}", file.getAbsolutePath());
            } else {
                logger.debug("File already exists: {}", file.getAbsolutePath());
            }

            merger.setDestinationFileName(singlePdfPath);

            merger.mergeDocuments(null);
            logger.debug("PDFs merged successfully to: {}", singlePdfPath);

        } catch (IOException ex) {
            logger.error("Error while merging pdfs into one servlet", ex);
            throw ex;
        }
    }

}