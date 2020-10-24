package org.roman.notifier;

import java.sql.*;
import java.time.LocalDate;

public class DataServiceImpl implements DataService {

    private final Connection connection;

    public DataServiceImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public ResultSet getSalaryPayments(
            String departmentId,
            LocalDate dateFrom,
            LocalDate dateTo) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary " +
                "from employee emp left join" +
                "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
                " sp.date >= ? and sp.date <= ? group by emp.id, emp.name")) {
            ps.setString(1, departmentId);
            ps.setDate(2, new Date(dateFrom.toEpochDay()));
            ps.setDate(3, new Date(dateTo.toEpochDay()));

            return ps.executeQuery();
        }
    }
}
