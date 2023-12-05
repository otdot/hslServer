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
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Objects;

@RestController
@RequestMapping(path ="/api")
public class StopController {

    @Autowired
    OkClient okClient = new OkClient();
    static OkHttpClient httpClient = new OkHttpClient();
    ObjectMapper mapper = new ObjectMapper();


    private okhttp3.Response queryDb(String query) throws IOException {

        RequestBody body = RequestBody.create(Queries.mediaType, Queries.STOPSQUERY);
        Request request = new Request.Builder()
                .url(okClient.getApiUrl())
                .post(body)
                .build();

        return httpClient.newCall(request).execute();
    }

    @GetMapping("/stops")
    public StopsResponse stopsQuery() throws IOException {
        okhttp3.Response response = queryDb(Queries.STOPSQUERY);
        assert response.body() != null;
        return mapper.readValue(response.body().string(), StopsResponse.class);
    }

    @GetMapping("/stop")
    public StopResponse stopQuery(@RequestParam String id) throws IOException {
        okhttp3.Response response = queryDb(Queries.STOPQUERY(id));
        assert response.body() != null;
        return mapper.readValue(response.body().string(), StopResponse.class);
    }

}
