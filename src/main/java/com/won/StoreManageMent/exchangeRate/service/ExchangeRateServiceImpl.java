package com.won.StoreManageMent.exchangeRate.service;

import java.util.Calendar;
import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService{
    
    @Value("${auth.secretKey}")
    private String KEY;
    private String DATA;
    private SimpleDateFormat FORAMT;

    public ExchangeRateServiceImpl(){
        this.DATA = "AP01";
        this.FORAMT = new SimpleDateFormat("yyyyMMdd");
    }

    private String now() {
        Calendar now = Calendar.getInstance();

        // 주말일 경우
        if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            now.add(Calendar.DAY_OF_MONTH, -(now.get(Calendar.DAY_OF_WEEK) - Calendar.FRIDAY));
            return this.FORAMT.format(now.getTime());
        }

        // 12시 장전일 경우
        if (now.get(Calendar.HOUR_OF_DAY) < 12) {
            if (now.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                now.add(Calendar.DAY_OF_MONTH, -3);
            } else {
                now.add(Calendar.DAY_OF_MONTH, -1);
            }
            return this.FORAMT.format(now.getTime());
        }

        return this.FORAMT.format(now.getTime());
    }
}
