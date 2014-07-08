package net.ere.tmp.maven_gwt.spring.impl;

import net.ere.tmp.maven_gwt.spring.TimeService;
import org.springframework.stereotype.Service;

import javax.inject.Named;
import java.text.SimpleDateFormat;
import java.util.Date;

// @Named("timeService")
@Service
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
