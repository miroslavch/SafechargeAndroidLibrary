package com.safecharge.safechargesdk.service;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import com.safecharge.safechargesdk.service.model.SessionAuthModel;
import com.safecharge.safechargesdk.service.model.SessionModel;

import com.safecharge.safechargesdk.service.model.CardTransactionModel;
import com.safecharge.safechargesdk.service.model.CardTransactionResultModel;

public interface SafechargeRetrofitCallInterface {
    @POST("/ppp/api/v1/getSessionToken.do")
    Call<SessionModel> authenticate(@Body SessionAuthModel sessionAuth);

    @POST("/ppp/api/cardTokenization.do")
    Call<CardTransactionResultModel> tokenizeCard(@Body CardTransactionModel cardTransaction);
}
