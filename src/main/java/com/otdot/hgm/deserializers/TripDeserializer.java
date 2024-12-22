package com.otdot.hgm.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.otdot.hgm.dtos.SimpleDtos;
import com.otdot.hgm.dtos.TripRes;
import com.otdot.hgm.dtos.TripsRes;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Component
public class TripDeserializer extends JsonDeserializer<TripRes> {

    @Override
    public TripRes deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        JsonNode json = jsonParser.getCodec().readTree(jsonParser);

        if (!json.has("data")) {
            return null;
        }

        JsonNode tripNode = json.findPath("data").findPath("trip");
        List<String> gtfsId = tripNode.findValuesAsText("gtfsId");
        List<String> directionId = tripNode.findValuesAsText("directionId");

        JsonNode stopsJson =  tripNode.findPath("stops");

        List<String> tripStops = new ArrayList<>();
        Iterator stopData = stopsJson.iterator();
        while (stopData.hasNext()) {
            ObjectNode stopNode = (ObjectNode) stopData.next();
            List<String> stopGtfsId = stopNode.findValuesAsText("gtfsId");

            tripStops.add(stopGtfsId.get(0));
        }

        SimpleDtos.TripDto tripDto = new SimpleDtos.TripDto(gtfsId.get(0), Integer.parseInt(directionId.get(0)), tripStops);

        return TripRes.builder().data(tripDto).build();
    }
}