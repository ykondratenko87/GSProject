package by.tms.gsproject.controller;

import by.tms.gsproject.config.Scheduler;
import by.tms.gsproject.service.order.OrderService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

public class InitServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        super.init();
        Scheduler.startScheduler(new OrderService());
    }
}