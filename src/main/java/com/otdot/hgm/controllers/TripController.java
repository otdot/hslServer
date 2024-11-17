package com.otdot.hgm.controllers;

import com.otdot.hgm.OkClient;
import com.otdot.hgm.queries.Queries;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/trip")
public class TripController {

    @Autowired
    OkClient okClient;

    private Response queryDb() throws IOException {
        RequestBody body = RequestBody.create(Queries.mediaType, Queries.STOPSQUERY);
        Request request = new Request.Builder()
                .url(okClient.getApiUrl())
                .post(body)
                .build();

        return OkClient.httpClient.newCall(request).execute();
    }

    @GetMapping("/saveTrips")
    public void saveTrips() {

    }

}
