package org.apache.params;

public class ImageLoadParameters
{
    public float scaleFactor = 1.0f;
    public float density;
    public float xAxis;
    public float yAxis;
    public float height;
    public float width;
    public String imageFilePath;
    public String documentPath;
    public String outputPath;

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
}