package net.moonj.admgc.util;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToDoubleConverter implements Converter<String, Double> {

    @Override
    public Double convert(String source) {
    	source  = source.replace(",", "");
    	return Double.valueOf(source);
    }


}
