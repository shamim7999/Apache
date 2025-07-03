package org.apache.helper.impl;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.helper.interfaces.PdfEncryptor;
import org.apache.params.CryptoOperationParameters;

import java.io.FileOutputStream;

public class ITextEncryptor implements PdfEncryptor {

    @Override
    public void encryptPdf(CryptoOperationParameters parameters) throws Exception {
        PdfReader reader = new PdfReader(parameters.getDocumentPath());
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(parameters.getOutputPath()));
        stamper.setEncryption(
                parameters.getUserPassword().getBytes(),
                parameters.getOwnerPassword().getBytes(),
                PdfWriter.ALLOW_PRINTING,
                PdfWriter.ENCRYPTION_AES_128
        );
        stamper.close();
        reader.close();
    }

    @Override
    public void decryptPdf(CryptoOperationParameters parameters) throws Exception {
        PdfReader reader = new PdfReader(parameters.getDocumentPath(), parameters.getOwnerPassword().getBytes());
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(parameters.getOutputPath()));
        stamper.close();
        reader.close();
    }
}
