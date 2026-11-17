```jsp
<%@ page import="com.medicalstore.model.Medicine" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Medicine</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f2f5f9;
            margin: 0;
            padding: 30px;
        }

        .container {
            width: 450px;
            margin: 30px auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 0 10px #ccc;
        }

        h2 {
            text-align: center;
            color: #333;
        }

        label {
            display: block;
            margin-top: 12px;
            font-weight: bold;
        }

        input {
            width: 100%;
            padding: 10px;
            margin-top: 5px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        button {
            width: 100%;
            padding: 12px;
            margin-top: 20px;
            background: #007bff;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background: #0056b3;
        }

        .back {
            display: block;
            text-align: center;
            margin-top: 15px;
            text-decoration: none;
            color: #007bff;
        }
    </style>
</head>

<body>

<div class="container">

    <h2>Edit Medicine</h2>

    <form action="${pageContext.request.contextPath}/medicines"
          method="post">

        <input type="hidden" name="action" value="update">
<%
    Medicine med = (Medicine) request.getAttribute("medicine");
%>

<input type="hidden" name="id" value="<%= med.getId() %>">
<p>Debug Medicine ID: <%= med.getId() %></p>

<label>Medicine Name</label>
        <input type="text"
               name="medicineName"
               value="${medicine.medicineName}"
               required>

        <label>Category</label>
        <input type="text"
               name="category"
               value="${medicine.category}"
               required>

        <label>Quantity</label>
        <input type="number"
               name="quantity"
               value="${medicine.quantity}"
               min="0"
               required>

        <label>Price</label>
        <input type="number"
               name="price"
               value="${medicine.price}"
               min="0"
               step="0.01"
               required>

        <label>Expiry Date</label>
        <input type="date"
               name="expiryDate"
               value="${medicine.expiryDate}"
               required>

        <button type="submit">Update Medicine</button>

    </form>

    <a class="back"
       href="${pageContext.request.contextPath}/medicines">
        Back to Medicine List
    </a>

</div>

</body>
</html>
```