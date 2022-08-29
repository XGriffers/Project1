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
import java.util.List;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
    UserService service;
    ObjectMapper mapper;

    public void init(){
        this.service = new UserService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        String param = req.getParameter("user-id");

            if (param == null) {
                List<User> userList = service.getAllUsers();
                String json = mapper.writeValueAsString(userList);
                resp.getWriter().println(json);

            } else {
                Integer userId = Integer.parseInt(req.getParameter("user-id"));

                User user = service.getUser(userId);
                String json = mapper.writeValueAsString(user);
                resp.getWriter().println(json);
            }

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder builder = new StringBuilder();

        BufferedReader buffer = req.getReader();

        while(buffer.ready()){
            builder.append(buffer.readLine());
        }
        String json = builder.toString();


        User newUser = mapper.readValue(json, User.class);
        service.save(newUser);
        resp.setStatus(200);

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");
        Integer userId = Integer.parseInt(param);

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = req.getReader();

        while (reader.ready()){
            builder.append(reader.readLine());
        }

        String json = builder.toString();
        User updateUser = mapper.readValue(json, User.class);
        service.updateUser(updateUser, userId);

        resp.setStatus(200);
        resp.getWriter().println("Updated");
        resp.setContentType("application/json; charset=utf-8");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");
        Integer userId = Integer.parseInt(param);
        service.deleteUser(userId);

        resp.setStatus(200);
        resp.setContentType("application/json; charset=utf-8");
        resp.getWriter().println("deleted");
    }

    @Override
    public void destroy() {

    }
}
