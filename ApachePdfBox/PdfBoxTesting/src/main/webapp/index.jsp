<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        .form-container {
            width: 50%;
            margin: 0 auto;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: inline-block;
            width: 150px;
            font-weight: bold;
        }
        input[type="text"], input[type="number"] {
            width: 300px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<form action="merge-pdfs-into-one" method="post">
    Source Pdf Files Directory Path: <input type="text" name="sourcePath" /><br/>
    Merged Pdf File Directory Path: <input type="text" name="destinationPath" /><br/>
    <input type="submit" value="Submit" />
</form>



<div class="form-container">
    <h2>Image Load Parameters</h2>
    <form action="insert-image-to-pdf" method="post">
        <div class="form-group">
            <label for="xAxis">X Axis:</label>
            <input type="number" id="xAxis" name="xAxis" value="140" required>
        </div>

        <div class="form-group">
            <label for="yAxis">Y Axis:</label>
            <input type="number" id="yAxis" name="yAxis" value="500" required>
        </div>

        <div class="form-group">
            <label for="imageFilePath">Image File Path:</label>
            <input type="text" id="imageFilePath" name="imageFilePath"
                   value="/home/shamim/Pictures/images.jpeg" required>
        </div>

        <div class="form-group">
            <label for="height">Height:</label>
            <input type="number" id="height" name="height" value="240" required>
        </div>

        <div class="form-group">
            <label for="width">Width:</label>
            <input type="number" id="width" name="width" value="240" required>
        </div>

        <div class="form-group">
            <label for="documentPath">Document Path:</label>
            <input type="text" id="documentPath" name="documentPath"
                   value="/home/shamim/Pdf/MergedPdfs/b.pdf" required>
        </div>

        <div class="form-group">
            <label for="outputPath">Output Path:</label>
            <input type="text" id="outputPath" name="outputPath"
                   value="/home/shamim/Pdf/MergedPdfs/c.pdf" required>
        </div>

        <div class="form-group">
            <input type="submit" value="Submit Parameters">
        </div>
    </form>
</div>

<div class="form-container">
    <h2>URL Insert Parameters</h2>
    <form action="insert-url-to-pdf" method="post">
        <div class="form-group">
            <label for="xAxis">X Axis:</label>
            <input type="number" id="xAxis" name="xAxis" value="140" required>
        </div>

        <div class="form-group">
            <label for="yAxis">Y Axis:</label>
            <input type="number" id="yAxis" name="yAxis" value="500" required>
        </div>

        <div class="form-group">
            <label for="height">Height:</label>
            <input type="number" id="height" name="height" value="240" required>
        </div>

        <div class="form-group">
            <label for="width">Width:</label>
            <input type="number" id="width" name="width" value="240" required>
        </div>

        <div class="form-group">
            <label for="linkText">Link Text:</label>
            <input type="text" id="linkText" name="linkText" value="Click Here" required>
        </div>
        <div class="form-group">
            <label for="url">Action URL:</label>
            <input type="text" id="url" name="url" value="https://www.google.com" required>
        </div>
        <div class="form-group">
            <label for="documentPath">Document Path:</label>
            <input type="text" id="documentPath" name="documentPath"
                   value="/home/shamim/Pdf/MergedPdfs/b.pdf" required>
        </div>

        <div class="form-group">
            <label for="outputPath">Output Path:</label>
            <input type="text" id="outputPath" name="outputPath"
                   value="/home/shamim/Pdf/MergedPdfs/c.pdf" required>
        </div>

        <div class="form-group">
            <input type="submit" value="Submit Parameters">
        </div>
    </form>
</div>

</body>
</html>