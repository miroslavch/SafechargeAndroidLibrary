package com.safecharge.safechargesdk.service.EventListeners;

import com.safecharge.safechargesdk.service.model.SafechargeServiceError;
import com.safecharge.safechargesdk.service.model.SessionAuthModel;
import com.safecharge.safechargesdk.service.model.SessionModel;

public interface AuthenticateResultObserver {

    void onSuccessfulResult(SessionModel sessionAuthResult);
    void onErrorResult(SafechargeServiceError error);
}
