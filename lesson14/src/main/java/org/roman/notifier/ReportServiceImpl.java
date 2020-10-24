package org.roman.notifier;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportServiceImpl implements ReportService {

    @Override
    public String getResultHtml(ResultSet results) throws SQLException {
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;

        while (results.next()) {
            resultingHtml.append("<tr>");
            resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>");
            resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>");
            resultingHtml.append("</tr>");
            totals += results.getDouble("salary");
        }

        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");

        return resultingHtml.toString();
    }
}
