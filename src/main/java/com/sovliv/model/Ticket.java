package com.sovliv.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Ticket {
    String origin;
    String origin_name;
    String destination;
    String destination_name;
    String departure_date;
    String departure_time;
    String arrival_date;
    String arrival_time;
    String carrier;
    Long stops;
    Long price;
}
