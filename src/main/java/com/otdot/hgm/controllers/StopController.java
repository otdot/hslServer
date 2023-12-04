package com.otdot.hgm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otdot.hgm.OkClient;
import com.otdot.hgm.dtos.StopsResponse;
import com.otdot.hgm.queries.Queries;
import com.otdot.hgm.dtos.StopResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(path ="/api")
public class StopController {

    @Autowired
    OkClient okClient = new OkClient();
    static OkHttpClient httpClient = new OkHttpClient();

    @PostMapping
    public StopsResponse stopQuery(String query) throws IOException {

        ObjectMapper mapper = new ObjectMapper();

        RequestBody body = RequestBody.create(Queries.mediaType, query);
        Request request = new Request.Builder()
                .url(okClient.getApiUrl())
                .post(body)
                .build();

        okhttp3.Response response = httpClient.newCall(request).execute();

        assert response.body() != null;
        return mapper.readValue(response.body().string(), StopsResponse.class);
    }

    @GetMapping
    public String hello() {
        return "Hello";
    }
//
//    @QueryMapping
//    public void stops() {
//    }
}
