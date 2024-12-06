package com.otdot.hgm.dtos;


import com.otdot.hgm.collections.Stop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StopResGQLDTO {
    private Page<Stop> stops;
    private boolean hasNext;
}
