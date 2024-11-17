package com.otdot.hgm.dtos;

import com.otdot.hgm.documents.Stop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopInnerResponse {
    Stop stop;
}
