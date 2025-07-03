package org.apache.helper.interfaces;

import org.apache.params.CryptoOperationParameters;

public interface PdfEncryptor {
    void encryptPdf(CryptoOperationParameters parameters) throws Exception;
    void decryptPdf(CryptoOperationParameters parameters) throws Exception;
}
