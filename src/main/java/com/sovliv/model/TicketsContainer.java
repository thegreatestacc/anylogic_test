package com.sovliv.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TicketsContainer {
    List<Ticket> tickets = new ArrayList<>();
}
