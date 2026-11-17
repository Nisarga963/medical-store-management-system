
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Medical Store Dashboard</title>

    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: Arial, sans-serif;
        }

        body {
            background: #f4f6f9;
        }

        /* Sidebar */
        .sidebar {
            width: 230px;
            height: 100vh;
            background: #173b57;
            color: white;
            padding: 25px 15px;
            position: fixed;
            top: 0;
            left: 0;
        }

        .sidebar h2 {
            text-align: center;
            margin-bottom: 35px;
            font-size: 21px;
        }

        .sidebar a {
            display: block;
            color: white;
            text-decoration: none;
            padding: 14px;
            margin-bottom: 10px;
            border-radius: 5px;
        }

        .sidebar a:hover {
            background: #28658c;
        }

        /* Main Content */
        .main {
            margin-left: 230px;
            padding: 30px;
            width: calc(100% - 230px);
        }

        /* Welcome and Logout */
        .welcome-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 15px 20px;
            background: #173b57;
            color: white;
            border-radius: 8px;
            margin-bottom: 25px;
        }

        .logout-btn {
            display: inline-block;
            background: #dc3545;
            color: white;
            padding: 10px 18px;
            text-decoration: none;
            border-radius: 5px;
            font-weight: bold;
        }

        .logout-btn:hover {
            background: #b52a37;
        }

        /* Dashboard Heading */
        .header {
            margin-bottom: 30px;
        }

        .header h1 {
            color: #173b57;
            margin-bottom: 8px;
        }

        .header p {
            margin-top: 10px;
        }

        /* Dashboard Cards */
        .cards {
            display: grid;
            grid-template-columns: repeat(3, 1fr);
            gap: 20px;
            margin-bottom: 35px;
        }

        .card {
            padding: 25px;
            border-radius: 10px;
            color: white;
            box-shadow: 0 3px 8px rgba(0,0,0,0.1);
        }

        .card h3 {
            font-size: 16px;
            margin-bottom: 15px;
        }

        .card h1 {
            font-size: 32px;
        }

        .blue { background: #2878b5; }
        .green { background: #269b69; }
        .purple { background: #7654b5; }
        .orange { background: #e68a28; }
        .red { background: #d94b4b; }
        .teal { background: #249c9c; }

        /* Recent Medicines Table */
        .table-container {
            background: white;
            padding: 25px;
            border-radius: 10px;
            box-shadow: 0 3px 8px rgba(0,0,0,0.08);
            overflow-x: auto;
        }

        .table-container h2 {
            color: #173b57;
            margin-bottom: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 13px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background: #173b57;
            color: white;
        }

        tr:hover {
            background: #f1f5f8;
        }

        .empty {
            text-align: center;
            padding: 20px;
        }

        @media (max-width: 900px) {
            .cards {
                grid-template-columns: repeat(2, 1fr);
            }
        }

        @media (max-width: 600px) {
            .sidebar {
                width: 160px;
            }

            .main {
                margin-left: 160px;
                width: calc(100% - 160px);
                padding: 15px;
            }

            .cards {
                grid-template-columns: 1fr;
            }

            .welcome-bar {
                flex-wrap: wrap;
                gap: 12px;
            }
        }
    </style>
</head>

<body>

    <!-- Sidebar -->
    <div class="sidebar">

        <h2>Medical Store</h2>

        <a href="${pageContext.request.contextPath}/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/medicines">Medicines</a>
        <a href="${pageContext.request.contextPath}/categories">Categories</a>
        <a href="${pageContext.request.contextPath}/suppliers">Suppliers</a>
        <a href="${pageContext.request.contextPath}/">Home</a>

    </div>

    <!-- Main Content -->
    <div class="main">

        <!-- Welcome and Logout -->
        <div class="welcome-bar">

            <h3>
                Welcome, ${sessionScope.userName}!
            </h3>

            <a class="logout-btn"
               href="${pageContext.request.contextPath}/logout">
                Logout
            </a>

        </div>

        <!-- Dashboard Heading -->
        <div class="header">

            <h1>Medical Store Dashboard</h1>

            <p>Welcome to the Medical Store Management System</p>

        </div>

        <!-- Dashboard Cards -->
        <div class="cards">

            <div class="card blue">
                <h3>Total Medicines</h3>
                <h1>${totalMedicines}</h1>
            </div>

            <div class="card green">
                <h3>Total Categories</h3>
                <h1>${totalCategories}</h1>
            </div>

            <div class="card purple">
                <h3>Total Suppliers</h3>
                <h1>${totalSuppliers}</h1>
            </div>

            <div class="card orange">
                <h3>Low Stock Medicines</h3>
                <h1>${lowStockMedicines}</h1>
            </div>

            <div class="card red">
                <h3>Expired Medicines</h3>
                <h1>${expiredMedicines}</h1>
            </div>

            <div class="card teal">
                <h3>Near Expiry Medicines</h3>
                <h1>${nearExpiryMedicines}</h1>
            </div>

        </div>

        <!-- Recent Medicines Table -->
        <div class="table-container">

            <h2>Recent Medicines</h2>

            <table>

                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Medicine Name</th>
                        <th>Category</th>
                        <th>Quantity</th>
                        <th>Price</th>
                        <th>Expiry Date</th>
                    </tr>
                </thead>

                <tbody>

                    <c:forEach var="medicine" items="${recentMedicines}">
                        <tr>
                            <td>${medicine.id}</td>
                            <td>${medicine.medicineName}</td>
                            <td>${medicine.category}</td>
                            <td>${medicine.quantity}</td>
                            <td>₹ ${medicine.price}</td>
                            <td>${medicine.expiryDate}</td>
                        </tr>
                    </c:forEach>

                    <c:if test="${empty recentMedicines}">
                        <tr>
                            <td colspan="6" class="empty">
                                No medicines available.
                            </td>
                        </tr>
                    </c:if>

                </tbody>

            </table>

        </div>

    </div>

</body>
</html>
