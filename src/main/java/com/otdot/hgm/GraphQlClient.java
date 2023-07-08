package com.otdot.hgm;

import com.apollographql.apollo.ApolloClient;
import okhttp3.OkHttpClient;

public class GraphQlClient {

    private static final String BASE_URL = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";

    private static OkHttpClient httpClient = new OkHttpClient.Builder().build();

    public static ApolloClient apolloClient = ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(httpClient).build();

}
