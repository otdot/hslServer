package com.otdot.hgm.documents;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.scheduling.annotation.Scheduled;

@Document
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stoptime {
    Stop stop;
    Integer scheduledArrival;
}
