package com.sovliv;

import com.sovliv.process.TicketProcessor;

public class Main {
    public static void main(String[] args) {
        TicketProcessor ticketProcessor = new TicketProcessor();
        String avgTimeBetweenVVOAndTLV = ticketProcessor.getAvgTimeBetweenVVOAndTLV().toString();
        String percentile = ticketProcessor.percentile().toString();

        System.out.printf("avg time between VVO and TLV is %s \n percentile is %s%n",
                avgTimeBetweenVVOAndTLV, percentile);
    }
}
