package com.safecharge.safechargesdk.service.model;


import android.support.annotation.NonNull;
import android.support.annotation.Size;

import java.security.MessageDigest;

import com.safecharge.safechargesdk.service.ServiceConstants;


public class SessionAuthModel {

    public SessionAuthModel(String merchantId,
                            String merchantSiteId,
                            String clientRequestId,
                            String secretKey) {
        this.merchantId = merchantId;
        this.merchantSiteId = merchantSiteId;
        this.clientRequestId = clientRequestId;
        this.secretKey = secretKey;
        postBuild();
    }


    @NonNull
    public static String SHA256(String base) throws RuntimeException {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public void postBuild() {
        this.timeStamp = android.text.format.DateFormat.format("yyyyMMddhhmmss", new java.util.Date()).toString();

        try {
            this.checksum = SHA256(new String()
                    .concat(this.merchantId)
                    .concat(this.merchantSiteId)
                    .concat(this.clientRequestId)
                    .concat(this.timeStamp)
                    .concat(this.secretKey));
        } catch (RuntimeException checkSumException) {
            System.out.print(checkSumException.toString());
        }
    }

    @Size(max = ServiceConstants.MERCHANT_ID_MAX_LENGTH)        private String merchantId;
    @Size(max = ServiceConstants.MERCHANT_SITE_ID_MAX_LENGTH)   private String merchantSiteId;
    @Size(max = ServiceConstants.CLIENT_REQUEST_ID_MAX_LENGTH)  private String clientRequestId;
    @Size(max = ServiceConstants.USER_ID_MAX_LENGTH)            private String userToken;
    @Size(max = ServiceConstants.SECRECT_KEY_MAX_LENGTH)        private String secretKey;

    private String checksum;
    private String timeStamp;


    public String getSecretKey()
    {
        return this.secretKey;
    }

    public void setSecretKey(String secretKey)
    {
        this.secretKey = secretKey;
    }

    public String getUserToken()
    {
        return this.userToken;
    }

    public void setUserToken(String userToken)
    {
        this.userToken = userToken;
    }


    public String getTimeStamp ()
    {
        return timeStamp;
    }

    public void setTimeStamp (String timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public String getClientRequestId ()
    {
        return clientRequestId;
    }

    public void setClientRequestId (String clientRequestId)
    {
        this.clientRequestId = clientRequestId;
    }

    public String getMerchantId ()
    {
        return merchantId;
    }

    public void setMerchantId (String merchantId)
    {
        this.merchantId = merchantId;
    }

    public String getChecksum ()
    {
        return checksum;
    }

    public void setChecksum (String checksum)
    {
        this.checksum = checksum;
    }

    public String getMerchantSiteId ()
    {
        return merchantSiteId;
    }

    public void setMerchantSiteId (String merchantSiteId)
    {
        this.merchantSiteId = merchantSiteId;
    }

    @Override
    public String toString()
    {
        return "SessionAuthModel [timeStamp = " + timeStamp +
                ", clientRequestId = " + clientRequestId +
                ", merchantId = " + merchantId +
                ", checksum = " + checksum +
                ", merchantSiteId = " + merchantSiteId + "]";
    }

}
