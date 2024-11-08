package com.star.galeri_web_app.service;

import com.star.galeri_web_app.dto.CurrencyRatesResponse;

public interface ICurrencyRatesService {

    public CurrencyRatesResponse getCurrencyRatesResponse(String startDate, String endDate);
}
