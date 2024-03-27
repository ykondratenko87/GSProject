package by.tms.gsproject.config;

import by.tms.gsproject.service.order.OrderService;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Scheduler {
    private static boolean isSchedulerStarted = false;
    private static ScheduledExecutorService scheduler;

    public static void startScheduler(OrderService orderService) {
        if (!isSchedulerStarted) {
            scheduler = Executors.newSingleThreadScheduledExecutor();
            scheduler.scheduleAtFixedRate(orderService::clean, 0, 24, TimeUnit.HOURS);
            isSchedulerStarted = true;
        }
    }
}