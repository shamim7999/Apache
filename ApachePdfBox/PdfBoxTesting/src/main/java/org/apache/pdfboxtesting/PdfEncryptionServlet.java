package org.apache.pdfboxtesting;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.helper.common.enums.PdfBoxLibrary;
import org.apache.helper.common.enums.CryptoOperation;
import org.apache.helper.factories.PdfEncryptorFactory;
import org.apache.helper.interfaces.PdfEncryptor;
import org.apache.params.CryptoOperationParameters;

import java.io.IOException;

public class PdfEncryptionServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {

            CryptoOperationParameters parameters = new CryptoOperationParameters();
            String library = request.getParameter("pdfLibrary");
            String cryptoOperation = request.getParameter("cryptoOperation");
            PdfBoxLibrary selectedLibrary = PdfBoxLibrary.valueOf(library);
            CryptoOperation selectedCryptoOperation = CryptoOperation.valueOf(cryptoOperation);

            parameters.setDocumentPath(request.getParameter("documentPath"));
            parameters.setOutputPath(request.getParameter("outputPath"));
            parameters.setOwnerPassword(request.getParameter("ownerPassword"));
            parameters.setUserPassword(request.getParameter("userPassword"));
            parameters.setPdfBoxLibrary(selectedLibrary);
            parameters.setPdfCryptoOperation(selectedCryptoOperation);

            cryptoOperation(parameters);

            request.getRequestDispatcher("success.jsp").forward(request, response);
        }
        catch(Exception ex){
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void cryptoOperation(CryptoOperationParameters parameters) throws Exception {
        PdfEncryptor pdfEncryptor = PdfEncryptorFactory.getEncryptor(parameters.getPdfBoxLibrary());
        switch (parameters.getPdfCryptoOperation()) {
            case ENCRYPT:
                pdfEncryptor.encryptPdf(parameters);
                break;
            case DECRYPT:
                pdfEncryptor.decryptPdf(parameters);
                break;
        }
    }
}
