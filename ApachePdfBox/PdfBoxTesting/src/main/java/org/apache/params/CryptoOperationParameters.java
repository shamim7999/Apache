package org.apache.params;

import org.apache.helper.common.enums.PdfBoxLibrary;
import org.apache.helper.common.enums.CryptoOperation;

public class CryptoOperationParameters {
    private String documentPath;
    private String outputPath;
    private String ownerPassword;
    private String userPassword;
    private PdfBoxLibrary pdfBoxLibrary;
    private CryptoOperation cryptoOperation;

    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
    public void setPdfBoxLibrary(PdfBoxLibrary pdfBoxLibrary) {
        this.pdfBoxLibrary = pdfBoxLibrary;
    }
    public void setPdfCryptoOperation(CryptoOperation cryptoOperation) {
        this.cryptoOperation = cryptoOperation;
    }
    public void setOwnerPassword(String ownerPassword) {
        this.ownerPassword = ownerPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }



    public String getDocumentPath() {
        return documentPath;
    }
    public String getOutputPath() {
        return outputPath;
    }
    public PdfBoxLibrary getPdfBoxLibrary() {
        return pdfBoxLibrary;
    }
    public CryptoOperation getPdfCryptoOperation() {
        return cryptoOperation;
    }
    public String getOwnerPassword() {
        return ownerPassword;
    }
    public String getUserPassword() {
        return userPassword;
    }
}
