package com.safecharge.safechargesdk.service.EventListeners;

import com.safecharge.safechargesdk.service.model.SafechargeServiceError;
import com.safecharge.safechargesdk.service.model.CardTransactionResultModel;



public interface TokenizeResultObserver {
    void onSuccessfulResult( CardTransactionResultModel transactionResult );
    void onErrorResult( SafechargeServiceError error );
}
