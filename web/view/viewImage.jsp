<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>View Image</title>
    <script type="text/javascript">
        function goBack() {
            window.history.back();  
        }
    </script>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f6fa; 
            color: #333333; 
            text-align: center; 
            padding: 20px;
        }

        h1 {
            color: #007bff;
            font-size: 2em;
        }

        img {
            max-width: 80%; 
            height: auto;
            border: 2px solid #333333; 
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); 
            margin-bottom: 20px; 
        }

        button {
            background-color: #007bff;
            color: white; 
            border: none;
            padding: 10px 20px;
            font-size: 1em;
            border-radius: 5px;
            cursor: pointer; 
            transition: background-color 0.3s ease; 
        }

        button:hover {
            background-color: #0056b3; 
        }

        button:active {
            background-color: #004080; 
            transform: scale(0.98); 
        }
    </style>
</head>
<body>
    <h1>Image Viewer</h1>

    <%
        byte[] imageBytes = (byte[]) request.getAttribute("imageBytes");  

        if (imageBytes != null) {
            String base64Image = java.util.Base64.getEncoder().encodeToString(imageBytes);  
    %>

    <img src="data:image/jpeg;base64,<%= base64Image %>" alt="Image" /> 

    <%
        } else {
            out.println("No image found.");
        }
    %>

    <br>
    <button onclick="goBack()">Go Back</button>
</body>
</html>
