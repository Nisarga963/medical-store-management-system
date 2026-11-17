<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="com.medicalstore.model.Supplier" %>

<%
    List<Supplier> suppliers =
        (List<Supplier>) request.getAttribute("suppliers");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Supplier Management</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #f4f6f9;
            margin: 0;
            padding: 30px;
        }

        .container {
            max-width: 1000px;
            margin: auto;
            background: white;
            padding: 25px;
            border-radius: 10px;
        }

        h2 {
            color: #234;
        }

        input, textarea {
            width: 100%;
            padding: 10px;
            margin: 7px 0 15px;
            box-sizing: border-box;
        }

        button {
            padding: 9px 15px;
            background: #087f5b;
            color: white;
            border: none;
            cursor: pointer;
            border-radius: 4px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 25px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 10px;
            text-align: left;
        }

        th {
            background: #087f5b;
            color: white;
        }

        .delete {
            background: #d62828;
        }

        .edit {
            background: #2563eb;
        }
    </style>
</head>

<body>
<div class="container">

    <h2>Supplier Management</h2>

    <h3>Add New Supplier</h3>

    <form action="${pageContext.request.contextPath}/suppliers"
          method="post">

        <input type="hidden" name="action" value="add">

        <label>Supplier Name</label>
        <input type="text" name="supplierName" required>

        <label>Phone Number</label>
        <input type="text" name="phone">

        <label>Email</label>
        <input type="email" name="email">

        <label>Address</label>
        <textarea name="address" rows="3"></textarea>

        <button type="submit">Add Supplier</button>
    </form>

    <hr>

    <h3>Supplier Details</h3>
<table>
    <tr>
        <th>ID</th>
        <th>Supplier Name</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Address</th>
        <th>Actions</th>
    </tr>

    <% if (suppliers != null) {
        for (Supplier s : suppliers) { %>

    <tr>
        <td><%= s.getId() %></td>

        <td colspan="4">
            <form action="${pageContext.request.contextPath}/suppliers"
                  method="post">

                <input type="hidden" name="action" value="update">
                <input type="hidden" name="id" value="<%= s.getId() %>">

                <input type="text" name="supplierName"
                       value="<%= s.getSupplierName() %>" required>

                <input type="text" name="phone"
                       value="<%= s.getPhone() == null ? "" : s.getPhone() %>">

                <input type="email" name="email"
                       value="<%= s.getEmail() == null ? "" : s.getEmail() %>">

                <input type="text" name="address"
                       value="<%= s.getAddress() == null ? "" : s.getAddress() %>">

                <button type="submit" class="edit">Update</button>
            </form>
        </td>

        <td>
            <form action="${pageContext.request.contextPath}/suppliers"
                  method="post"
                  onsubmit="return confirm('Delete this supplier?');">

                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="id" value="<%= s.getId() %>">

                <button type="submit" class="delete">Delete</button>
            </form>
        </td>
    </tr>

    <%  }
       } %>
</table>

    <br>
    <a href="${pageContext.request.contextPath}/">Back to Home</a>

</div>
</body>
</html>