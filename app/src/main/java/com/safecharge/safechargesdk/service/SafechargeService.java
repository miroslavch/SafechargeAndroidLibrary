package com.safecharge.safechargesdk.service;

import com.safecharge.safechargesdk.service.model.CardTransactionModel;
import com.safecharge.safechargesdk.service.model.SafechargeServiceError;
import com.safecharge.safechargesdk.service.model.SessionAuthModel;
import com.safecharge.safechargesdk.service.model.SessionModel;
import com.safecharge.safechargesdk.service.model.CardTransactionResultModel;
import com.safecharge.safechargesdk.service.EventListeners.AuthenticateResultObserver;
import com.safecharge.safechargesdk.service.EventListeners.TokenizeResultObserver;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class SafechargeService {

    private String   m_baseURL;
    private Retrofit m_retrofit;

    public String UNHANDLED_ERROR_CODE_STRING = "-999";

    public SafechargeService(String baseURL) {
        this.m_baseURL   = baseURL;
        this.m_retrofit = new Retrofit.Builder()
                .baseUrl(this.m_baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(SelfSigningClientBuilder.getUnsafeOkHttpClient())
                .build();
    }

    public void authenticate(SessionAuthModel authModel, final AuthenticateResultObserver observer) {
        try {
            SafechargeRetrofitCallInterface service = m_retrofit.create(SafechargeRetrofitCallInterface.class);
            Call<SessionModel> result = service.authenticate(authModel);
            result.enqueue(new Callback<SessionModel>() {
                @Override
                public void onResponse(Call<SessionModel> call, Response<SessionModel> response) {
                    if ( response.body() != null ) {
                        if( response.body().isError() ) {
                            observer.onErrorResult(response.body().checkAndReturnError());
                        } else {
                            observer.onSuccessfulResult(response.body());
                        }
                    } else {
                        observer.onErrorResult(new SafechargeServiceError("Empty response",UNHANDLED_ERROR_CODE_STRING));
                    }
                }

                @Override
                public void onFailure(Call<SessionModel> call, Throwable t) {
                    observer.onErrorResult(new SafechargeServiceError(t.getMessage(),UNHANDLED_ERROR_CODE_STRING));
                }
            });
        } catch ( Exception e ) {
            observer.onErrorResult(new SafechargeServiceError(e.toString(),UNHANDLED_ERROR_CODE_STRING));
        }
    }

    public void tokenizeCard(CardTransactionModel cardTransactionModel, final TokenizeResultObserver observer) {
        try {
            SafechargeRetrofitCallInterface service = m_retrofit.create(SafechargeRetrofitCallInterface.class);
            Call<CardTransactionResultModel> result = service.tokenizeCard(cardTransactionModel);
            result.enqueue(new Callback<CardTransactionResultModel>() {
                @Override
                public void onResponse(Call<CardTransactionResultModel> call,
                                       Response<CardTransactionResultModel> response) {
                    if ( response.body() != null ) {
                        if( response.body().isError() ) {
                            observer.onErrorResult(response.body().checkAndReturnError());
                        } else {
                            observer.onSuccessfulResult(response.body());
                        }
                    } else {
                        observer.onErrorResult(new SafechargeServiceError("Empty response",UNHANDLED_ERROR_CODE_STRING));
                    }
                }

                @Override
                public void onFailure(Call<CardTransactionResultModel> call, Throwable t) {
                    observer.onErrorResult(new SafechargeServiceError(t.getMessage(),UNHANDLED_ERROR_CODE_STRING));
                }
            });
        } catch ( Exception e ) {
            observer.onErrorResult(new SafechargeServiceError(e.toString(),UNHANDLED_ERROR_CODE_STRING));
        }
    }

}
