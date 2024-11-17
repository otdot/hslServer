package com.otdot.hgm.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.otdot.hgm.OkClient;
import com.otdot.hgm.dtos.StopResGQLDTO;
import com.otdot.hgm.dtos.StopsResponse;
import com.otdot.hgm.documents.Stop;
import com.otdot.hgm.queries.Queries;
import com.otdot.hgm.dtos.StopResponse;
import com.otdot.hgm.services.StopService;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path ="/api")
public class StopController {

    @Autowired
    OkClient okClient;
    ObjectMapper mapper = new ObjectMapper();
    private final StopService stopService;

    @Autowired
    public StopController(StopService stopService) { this.stopService = stopService; }

    private okhttp3.Response queryDb(String query) throws IOException {

        RequestBody body = RequestBody.create(Queries.mediaType, Queries.STOPSQUERY);
        Request request = new Request.Builder()
                .url(okClient.getApiUrl())
                .post(body)
                .build();

        return OkClient.httpClient.newCall(request).execute();
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

    @QueryMapping
    public StopResGQLDTO stops(@Argument int pageNum, @Argument int pageSize){
        Page<Stop> stops = stopService.stops(pageNum, pageSize);
        return new StopResGQLDTO(stops, stops.hasNext());
    }

    @GetMapping("/saveStops")
    public String saveStopsQuery() throws IOException {
        if (stopService.collectionHasData()) {
            stopService.deleteAllStops();
        }
        StopsResponse stops = stopsQuery();
        for (Stop stop : stops.getData().getStops()) {
            stopService.addStop(stop);
            System.out.printf(String.format("add %s \n", stop.getName()));
        }
        return "Ok";
    }

    @QueryMapping
    public List<Stop> findStopByQuery(@Argument String searchStr) {
        if (searchStr.isBlank()) {
            return List.of();
        }
        return stopService.findByRegex(searchStr);
    }

}
