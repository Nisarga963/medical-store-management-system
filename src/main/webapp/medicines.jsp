
<%@ page import="java.util.List" %>
<%@ page import="com.medicalstore.model.Medicine" %>

<!DOCTYPE html>
<html>
<head>
    <title>Medicine List</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background: #eaf2f8;
            padding: 30px;
        }

        h1 {
            text-align: center;
            color: #087e8b;
        }

        .search-form {
            margin-bottom: 20px;
        }

        input[type="text"] {
            padding: 10px;
            width: 250px;
        }

        button, .btn {
            padding: 10px 18px;
            background: #087e8b;
            color: white;
            border: none;
            border-radius: 5px;
            text-decoration: none;
            cursor: pointer;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            background: white;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 12px;
            text-align: center;
        }

        th {
            background: #087e8b;
            color: white;
        }

        .edit-btn {
            background: #28a745;
        }

        .delete-btn {
            background: #dc3545;
        }

        .action-form {
            display: inline;
        }

        .bottom-link {
            display: inline-block;
            margin-top: 20px;
        }
    </style>

    <script>
        function confirmDelete() {
            return confirm("Are you sure you want to delete this medicine?");
        }
    </script>
</head>

<body>

<h1>Medical Store - Medicine List</h1>

<form class="search-form"
      action="${pageContext.request.contextPath}/medicines"
      method="get">

    <input type="hidden" name="action" value="search">

    <input type="text"
           name="searchName"
           placeholder="Enter medicine name"
           value="${searchName}">

    <button type="submit">Search</button>

    <a class="btn"
       href="${pageContext.request.contextPath}/medicines">
        Show All
    </a>
</form>

<table>
    <tr>
        <th>ID</th>
        <th>Medicine Name</th>
        <th>Category</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Expiry Date</th>
        <th>Action</th>
    </tr>

    <%
        List<Medicine> medicines =
            (List<Medicine>) request.getAttribute("medicines");

        if (medicines != null && !medicines.isEmpty()) {
            for (Medicine medicine : medicines) {
    %>

    <tr>
        <td><%= medicine.getId() %></td>
        <td><%= medicine.getMedicineName() %></td>
        <td><%= medicine.getCategory() %></td>
        <td>
    <% int qty = medicine.getQuantity(); %>
    <%= qty %>
    <br>

    <% if (qty == 0) { %>
        <span style="color:red; font-weight:bold;">Out of Stock</span>
    <% } else if (qty <= 10) { %>
        <span style="color:orange; font-weight:bold;">Low Stock</span>
    <% } else { %>
        <span style="color:green; font-weight:bold;">Available</span>
    <% } %>
</td>


        <td><%= medicine.getPrice() %></td>
        <td>
    <%= medicine.getExpiryDate() %>
    <br>

    <%
        if (medicine.getExpiryDate() != null) {

            java.time.LocalDate expiryDate =
                medicine.getExpiryDate().toLocalDate();

            java.time.LocalDate today =
                java.time.LocalDate.now();

            long daysLeft =
                java.time.temporal.ChronoUnit.DAYS.between(today, expiryDate);

            if (daysLeft < 0) {
    %>
                <span style="color:red; font-weight:bold;">
                    Expired
                </span>
    <%
            } else if (daysLeft <= 30) {
    %>
                <span style="color:orange; font-weight:bold;">
                    Expiring Soon
                </span>
    <%
            } else {
    %>
                <span style="color:green; font-weight:bold;">
                    Valid
                </span>
    <%
            }
        }
    %>
</td>

        <td>
            <a class="btn edit-btn"
               href="${pageContext.request.contextPath}/medicines?action=edit&id=<%= medicine.getId() %>">
                Edit
            </a>

            <form class="action-form"
                  action="${pageContext.request.contextPath}/medicines"
                  method="post"
                  onsubmit="return confirmDelete();">

                <input type="hidden" name="action" value="delete">

                <input type="hidden"
                       name="id"
                       value="<%= medicine.getId() %>">

                <button type="submit" class="delete-btn">
                    Delete
                </button>
            </form>
        </td>
    </tr>

    <%
            }
        } else {
    %>

    <tr>
        <td colspan="7">No medicines found.</td>
    </tr>

    <%
        }
    %>
</table>

<a class="btn bottom-link"
   href="${pageContext.request.contextPath}/index.html">
    Add New Medicine
</a>

</body>
</html>
