package net.ere.tmp.maven_gwt.spring.impl;

import net.ere.tmp.maven_gwt.spring.TimeService;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named("timeService")
public class TimeServiceImpl implements TimeService {

    @Override
    public Date getTime() {
        return new Date();
    }

    @Override
    public String getTimeAsString() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        return format.format(new Date());
    }
}
