package ru.job4j.controller;

import ru.job4j.store.HbnStore;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckSale extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        HbnStore.instOf().updateSaleStatus(Integer.parseInt(req.getParameter("user")), Integer.parseInt(req.getParameter("id")));
        resp.sendRedirect(req.getContextPath() + "/cabinet");
    }
}
