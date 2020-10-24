package org.roman.notifier;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ReportService {

    String getResultHtml(ResultSet results) throws SQLException;
}
