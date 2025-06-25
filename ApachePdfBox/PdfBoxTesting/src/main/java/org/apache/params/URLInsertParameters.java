package org.apache.params;

public class URLInsertParameters {
    private float xAxis;
    private float yAxis;
    private float height;
    private float width;
    private String documentPath;
    private String outputPath;
    private String linkText;
    private String url;

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
    public void setDocumentPath(String documentPath) {
        this.documentPath = documentPath;
    }
    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }
    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }
    public void setUrl(String url) {
        this.url = url;
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
    public String getDocumentPath() {
        return documentPath;
    }
    public String getOutputPath() {
        return outputPath;
    }
    public String getLinkText() {
        return linkText;
    }
    public String getUrl() {
        return url;
    }
}
