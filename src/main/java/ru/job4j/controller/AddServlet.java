package ru.job4j.controller;

import ru.job4j.model.*;
import ru.job4j.store.HbnStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String[] categoryId = req.getParameterValues("categories");
        String[] bodyId = req.getParameterValues("bodies");
        String[] brandId = req.getParameterValues("brands");
        User user = (User) req.getSession().getAttribute("user");
        HbnStore.instOf().saveItem(
                new Item(
                        req.getParameter("descriptionItem"),
                        false,
                        user
                ), categoryId, bodyId, brandId
        );
        resp.sendRedirect(req.getContextPath() + "/cabinet");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        List<CategoryCar> categoryCarList = HbnStore.instOf().findAllCategories();
        List<Body> bodyList = HbnStore.instOf().findAllBodies();
        List<Brand> brandList = HbnStore.instOf().findAllBrands();
        req.setAttribute("categories", categoryCarList);
        req.setAttribute("bodies", bodyList);
        req.setAttribute("brands", brandList);
        req.setAttribute("user", user);
        req.getRequestDispatcher("add.jsp").forward(req, resp);
    }
}
