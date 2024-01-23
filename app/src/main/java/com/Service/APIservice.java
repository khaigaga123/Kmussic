package com.Service;

public class APIservice {
    private static String base_url="https://nguyenkhaiorion.000webhostapp.com/sever/";
    public static Dataservice Getservice()
    {
        return APIRetrofitClinet.getClient(base_url).create(Dataservice.class);
    }

}
