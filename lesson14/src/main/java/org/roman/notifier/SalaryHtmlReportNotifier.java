package org.roman.notifier;

import javax.mail.MessagingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class SalaryHtmlReportNotifier {

    private final MailService mailService;
    private final DataService dataService;
    private final ReportService reportService;

    public SalaryHtmlReportNotifier(
            MailService mailService,
            DataService dataService,
            ReportService reportService) {
        this.mailService = mailService;
        this.dataService = dataService;
        this.reportService = reportService;
    }

    public void generateAndSendHtmlSalaryReport(
            String departmentId,
            LocalDate dateFrom,
            LocalDate dateTo,
            String recipients) {
        try {
            ResultSet results = dataService.getSalaryPayments(departmentId, dateFrom, dateTo);
            String resultHtml = reportService.getResultHtml(results);
            mailService.sendMail(resultHtml, recipients);
        } catch (SQLException | MessagingException e) {
            e.printStackTrace();
        }
    }
}
