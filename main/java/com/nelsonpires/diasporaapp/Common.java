package com.nelsonpires.diasporaapp;

import com.nelsonpires.diasporaapp.Remote.IGoogleAPIService;
import com.nelsonpires.diasporaapp.Remote.RetrofitClient;
import com.nelsonpires.diasporaapp.model.MyPlaces;
import com.nelsonpires.diasporaapp.model.Results;

public class Common {

    public static Results currentResult;

    private static final String GOOGLE_API_URL = "https://maps.googleapis.com/";

    public static IGoogleAPIService getGoogleAPIService()
    {
        return RetrofitClient.getClient(GOOGLE_API_URL).create(IGoogleAPIService.class);
    }

}
