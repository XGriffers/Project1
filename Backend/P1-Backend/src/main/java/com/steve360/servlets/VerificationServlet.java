package com.steve360.servlets;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.steve360.Objects.User;
import com.steve360.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet("/verification")
public class VerificationServlet extends HttpServlet {

    ObjectMapper mapper;
    UserService service;

    @Override
    public void init() throws ServletException {
        this.mapper = new ObjectMapper();
        this.service = new UserService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");

        User validateUser = service.validate(userName, password);
        String json = mapper.writeValueAsString(validateUser);
        resp.getWriter().println(json);

        resp.setStatus(200);
        resp.setContentType("application/json; charset=utf-8");
    }
}
