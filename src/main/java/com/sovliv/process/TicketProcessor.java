package com.sovliv.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sovliv.model.Ticket;
import com.sovliv.model.TicketsContainer;
import lombok.SneakyThrows;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Напишите программу на языке программирования java, которая прочитает файл tickets.json и рассчитает:
 * - среднее время полета между городами Владивосток и Тель-Авив
 * - 90-й процентиль времени полета между городами Владивосток и Тель-Авив
 * Программа должна вызываться из командной строки Linux, результаты должны быть представлены в текстовом виде.
 * В качестве результата нужно прислать ответы на поставленные вопросы и ссылку на исходный код.
 */

public class TicketProcessor {

    private final SimpleDateFormat format = new SimpleDateFormat("dd.MM.yy HH:mm");
    private final ObjectMapper mapper = new ObjectMapper();
    private final List<Long> result = new ArrayList<>();

    @SneakyThrows
    public TicketsContainer readFile() {
        return mapper.readValue(new File("src/main/resources/tickets.json"), TicketsContainer.class);
    }

    @SneakyThrows
    public Double getAvgTimeBetweenVVOAndTLV() {
        TicketsContainer ticketsContainer = readFile();

        List<String> arrivalDate = ticketsContainer.getTickets().stream()
                .map(Ticket::getArrival_date)
                .collect(Collectors.toList());

        List<String> arrivalTime = ticketsContainer.getTickets().stream()
                .map(Ticket::getArrival_time)
                .collect(Collectors.toList());

        List<String> departureDate = ticketsContainer.getTickets().stream()
                .map(Ticket::getDeparture_date)
                .collect(Collectors.toList());

        List<String> departureTime = ticketsContainer.getTickets().stream()
                .map(Ticket::getDeparture_time)
                .collect(Collectors.toList());

        for (int i = 0; i < arrivalDate.size(); i++) {
            String departure = departureDate.get(i) + " " + departureTime.get(i);
            String arrive = arrivalDate.get(i) + " " + arrivalTime.get(i);

            Date departureParse = format.parse(departure);
            Date arriveParse = format.parse(arrive);

            long diff = arriveParse.getTime() - departureParse.getTime();
            long minutes = TimeUnit.MILLISECONDS.toMinutes(diff);

            result.add(minutes);
        }

        return result.stream()
                .mapToInt(Long::intValue)
                .average().orElse(0);
    }

    public Long percentile() {
        Collections.sort(result);
        int index = (int) Math.ceil(90 / 100.0 * result.size());
        return result.get(index - 1);
    }

}
