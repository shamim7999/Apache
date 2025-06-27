package org.apache.params;

import org.apache.common.enums.PdfBoxLibrary;

public class ImageLoadParameters
{
    private float scaleFactor = 1.0f;
    private float density;
    private float xAxis;
    private float yAxis;
    private float height;
    private float width;
    private String imageFilePath;
    private String documentPath;
    private String outputPath;
    private PdfBoxLibrary pdfBoxLibrary;

    public void setDensity(float density) {
        this.density = density;
    }
    public void setScaleFactor(float scaleFactor) {
        this.scaleFactor = scaleFactor;
    }
    public void setXAxis(float xAxis) {
        this.xAxis = xAxis;
    }
    public void setYAxis(float yAxis) {
        this.yAxis = yAxis;
    }
    public void setHeight(float height) {
        this.height = height;
    }
    public void setWidth(float width) {
        this.width = width;
    }
    public void setImageFilePath(String imageFilePath) {
        this.imageFilePath = imageFilePath;
    }
    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
    public  void setPdfBoxLibrary(PdfBoxLibrary pdfBoxLibrary) {
        this.pdfBoxLibrary = pdfBoxLibrary;
    }


    public float getScaleFactor() {
        return scaleFactor;
    }
    public float getDensity() {
        return density;
    }
    public float getXAxis() {
        return xAxis;
    }
    public float getYAxis() {
        return yAxis;
    }
    public float getHeight() {
        return height;
    }
    public float getWidth() {
        return width;
    }
    public String getImageFilePath() {
        return imageFilePath;
    }
    public  String getDocumentPath() {
        return documentPath;
    }
    public String getOutputPath() {
        return outputPath;
    }
    public PdfBoxLibrary getPdfBoxLibrary() {
        return pdfBoxLibrary;
    }
}