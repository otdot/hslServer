package com.otdot.hgm.deserializers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.otdot.hgm.dtos.TripRes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TripDeserializer extends JsonDeserializer<TripRes> {

    @Override
    public TripRes deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {

        List<TripRes.TripDto> trips = new ArrayList<>();
        JsonNode json = jsonParser.getCodec().readTree(jsonParser);

        if (!json.has("data")) {
            return null;
        }

        JsonNode jsonData = json.findPath("data").findPath("trips");
        Iterator tripData = jsonData.iterator();
        while (tripData.hasNext()) {
            ObjectNode tripNode = (ObjectNode) tripData.next();
            List<String> gtfsId = tripNode.findValuesAsText("gtfsId");
            List<String> directionId = tripNode.findValuesAsText("directionId");
            TripRes.TripDto trip = new TripRes.TripDto(gtfsId.get(0), Integer.parseInt(directionId.get(0)));

            trips.add(trip);
        }

        return TripRes.builder().data(trips).build();
    }
}
