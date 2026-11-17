
<%@ page import="java.util.List" %>
<%@ page import="com.medicalstore.model.Category" %>

<!DOCTYPE html>
<html>
<head>
    <title>Category Management</title>
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

        .add-form {
            margin-bottom: 25px;
        }

        input {
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

        .delete-btn {
            background: #dc3545;
        }

        .edit-btn {
            background: #28a745;
        }

        .action-form {
            display: inline;
        }
    </style>

    <script>
        function editCategory(id, name) {
            document.getElementById("editId").value = id;
            document.getElementById("editName").value = name;
            document.getElementById("editForm").style.display = "block";
        }

        function confirmDelete() {
            return confirm("Are you sure you want to delete this category?");
        }
    </script>
</head>

<body>

<h1>Medicine Category Management</h1>

<h3>Add New Category</h3>

<form class="add-form"
      action="${pageContext.request.contextPath}/categories"
      method="post">

    <input type="hidden" name="action" value="add">

    <input type="text"
           name="categoryName"
           placeholder="Enter category name"
           required>

    <button type="submit">Add Category</button>
</form>

<div id="editForm" style="display:none; margin-bottom:25px;">
    <h3>Edit Category</h3>

    <form action="${pageContext.request.contextPath}/categories"
          method="post">

        <input type="hidden" name="action" value="update">

        <input type="hidden" name="id" id="editId">

        <input type="text"
               name="categoryName"
               id="editName"
               required>

        <button type="submit">Update Category</button>
    </form>
</div>

<table>
    <tr>
        <th>ID</th>
        <th>Category Name</th>
        <th>Action</th>
    </tr>

    <%
        List<Category> categories =
            (List<Category>) request.getAttribute("categories");

        if (categories != null && !categories.isEmpty()) {
            for (Category category : categories) {
    %>

    <tr>
        <td><%= category.getId() %></td>
        <td><%= category.getCategoryName() %></td>

        <td>
            <button type="button"
                    class="edit-btn"
                    onclick="editCategory(
                        <%= category.getId() %>,
                        '<%= category.getCategoryName()
                            .replace("\\", "\\\\")
                            .replace("'", "\\'") %>'
                    )">
                Edit
            </button>

            <form class="action-form"
                  action="${pageContext.request.contextPath}/categories"
                  method="post"
                  onsubmit="return confirmDelete();">

                <input type="hidden" name="action" value="delete">

                <input type="hidden"
                       name="id"
                       value="<%= category.getId() %>">

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
        <td colspan="3">No categories found.</td>
    </tr>

    <%
        }
    %>
</table>

<br>

<a class="btn"
   href="${pageContext.request.contextPath}/index.html">
    Back to Home
</a>

</body>
</html>
