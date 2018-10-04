package com.example.gustavooliveira.empiretitanssociotorcedor.Salesforce;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateSF {

    private SimpleDateFormat formatDate;
    private SimpleDateFormat formatDateTime;

    public DateSF() {
        formatDate = new SimpleDateFormat("yyyy-MM-dd");
        formatDate.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
        formatDateTime = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        formatDateTime.setTimeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
    }

    public Date toDate(String data) throws ParseException {
        return formatDate.parse(data);
    }

    public String fromDate(Date data) {
        return formatDate.format(data);
    }

    public Date toDateTime(String data) throws ParseException {
        return formatDateTime.parse(data);
    }

    public String fromDateTime(Date data) {
        return formatDateTime.format(data);
    }
}
