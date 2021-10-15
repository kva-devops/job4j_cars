package ru.job4j.controller;

import ru.job4j.model.Item;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Item> itemList = HbnStore.instOf().findAllItems();
        req.setAttribute("items", itemList);
        req.getRequestDispatcher("all.jsp").forward(req, resp);
    }
}
