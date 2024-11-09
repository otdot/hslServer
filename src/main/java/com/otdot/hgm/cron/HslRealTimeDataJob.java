package com.otdot.hgm.cron;

import com.google.transit.realtime.GtfsRealtime.FeedMessage;
import com.otdot.hgm.services.GtfsRtService.HslGtfsRtEndpoints;
import com.otdot.hgm.services.GtfsRtService;
import com.otdot.hgm.utils.GtfsRtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class HslRealTimeDataJob {

    private GtfsRtService gtfsRtService;
    private GtfsRtUtils gtfsRtUtils;

    @Autowired
    public HslRealTimeDataJob(GtfsRtService gtfsRtService, GtfsRtUtils gtfsRtUtils) {
        this.gtfsRtService = gtfsRtService;
        this.gtfsRtUtils = gtfsRtUtils;
    }

    @Scheduled(fixedRate = 30000)
    private void updateHslVechicleInformation() {
        FeedMessage feedMessage = gtfsRtService.getGtfsRtRealTimeData(HslGtfsRtEndpoints.LOCATIONS);
        gtfsRtUtils.parseGtfsRtData(feedMessage);
    }
}
