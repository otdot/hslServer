package com.otdot.hgm.utils;

import com.google.transit.realtime.GtfsRealtime.VehiclePosition;
import com.google.transit.realtime.GtfsRealtime.Alert;
import com.google.transit.realtime.GtfsRealtime.TripUpdate;
import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import org.springframework.stereotype.Component;

@Component
public class GtfsRtUtils {

    public void parseGtfsRtData(FeedMessage gtfsRtMessage) {
        gtfsRtMessage.getEntityList().forEach(feedEntity -> {
            if (feedEntity.hasTripUpdate()) {
                TripUpdate tripUpdate = feedEntity.getTripUpdate();
                // Process trip update data
            } else if (feedEntity.hasVehicle()) {
                VehiclePosition vehiclePosition = feedEntity.getVehicle();
                // Process vehicle position data
            } else if (feedEntity.hasAlert()) {
                Alert alert = feedEntity.getAlert();
                // Process alert data
            }
        });
    }
}
