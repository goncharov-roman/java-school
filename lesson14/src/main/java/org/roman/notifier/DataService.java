package org.roman.notifier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface DataService {

    ResultSet getSalaryPayments(String departmentId, LocalDate dateFrom, LocalDate dateTo) throws SQLException;
}
