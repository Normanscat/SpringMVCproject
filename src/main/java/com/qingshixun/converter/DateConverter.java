package com.qingshixun.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * Converter<S, T> S source 源数据类型，T targer 目标数据类型
 */
public class DateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {

		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

}