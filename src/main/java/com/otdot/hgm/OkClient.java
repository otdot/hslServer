package com.otdot.hgm;

import com.apollographql.apollo.ApolloClient;
import org.springframework.stereotype.Component;
import okhttp3.OkHttpClient;

@Component
public class OkClient {

    public String getApiUrl() {
        return "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql?digitransit-subscription-key=" + System.getenv("API_KEY");
    }

    private static OkHttpClient httpClient = new OkHttpClient();

    public ApolloClient apolloClient = ApolloClient.builder().serverUrl(getApiUrl()).okHttpClient(httpClient).build();

    public static OkHttpClient getHttpClient() {
        return httpClient;
    }

}
