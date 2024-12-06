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
                System.out.println(tripUpdate);
            } else if (feedEntity.hasVehicle()) {
                VehiclePosition vehiclePosition = feedEntity.getVehicle();
                System.out.println(vehiclePosition);
            } else if (feedEntity.hasAlert()) {
                Alert alert = feedEntity.getAlert();
                System.out.println(alert);
            }
        });
    }
}
