package org.apache.helper.impl;

import org.apache.helper.interfaces.PdfEncryptor;
import org.apache.params.CryptoOperationParameters;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;

import java.io.File;

public class ApachePdfBoxEncryptor implements PdfEncryptor {

    @Override
    public void encryptPdf(CryptoOperationParameters parameters) throws Exception {
        PDDocument document = PDDocument.load(new File(parameters.getDocumentPath()));

        AccessPermission ap = new AccessPermission();
        ap.setCanPrint(false);  // optional restriction

        StandardProtectionPolicy spp = new StandardProtectionPolicy(parameters.getOwnerPassword(), parameters.getUserPassword(), ap);
        spp.setEncryptionKeyLength(128);  // or 256

        document.protect(spp);
        document.save(parameters.getOutputPath());
        document.close();
    }

    @Override
    public void decryptPdf(CryptoOperationParameters parameters) throws Exception {
        PDDocument document = PDDocument.load(new File(parameters.getDocumentPath()), parameters.getOwnerPassword());
        if (document.isEncrypted()) {
            document.setAllSecurityToBeRemoved(true);
        }
        document.save(parameters.getOutputPath());
        document.close();
    }
}
