package org.apache.helper.factories;

import org.apache.helper.common.enums.PdfBoxLibrary;
import org.apache.helper.impl.ApachePdfBoxEncryptor;
import org.apache.helper.impl.ITextEncryptor;
import org.apache.helper.interfaces.PdfEncryptor;


public class PdfEncryptorFactory {
    public static PdfEncryptor getEncryptor(PdfBoxLibrary library) {
        switch (library) {
            case APACHE_PDFBOX:
                return new ApachePdfBoxEncryptor();
            case ITEXT:
                return new ITextEncryptor();
            default:
                throw new IllegalArgumentException("Unsupported PDF library: " + library);
        }
    }
}
