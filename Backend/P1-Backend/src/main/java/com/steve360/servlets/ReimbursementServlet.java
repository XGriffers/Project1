package com.steve360.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.steve360.Objects.Reimbursements;
import com.steve360.Services.ReimbursementService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/reimbursements")
public class ReimbursementServlet extends HttpServlet {
    ReimbursementService service;
    ObjectMapper mapper;

    public void init(){
        this.service = new ReimbursementService();
        this.mapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");


        if(param == null) {
            List<Reimbursements> reimbursementList = service.getAllReimbursements();
            String json = mapper.writeValueAsString(reimbursementList);
            resp.setStatus(200);
            resp.getWriter().println(json);
            resp.setContentType("Application/Json; charset=utf-8");
        }
        else {
            Integer userId = Integer.parseInt(req.getParameter("user-id"));
            List <Reimbursements> reimbursement = service.getReimbursement(userId);

            String json = mapper.writeValueAsString(reimbursement);
            resp.setStatus(200);
            resp.setContentType("application/json; charset=utf-8");
            resp.getWriter().println(json);
        }


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while(buffer.ready()){
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        Reimbursements newReimbursement = mapper.readValue(json, Reimbursements.class);

        service.save(newReimbursement);
        resp.setStatus(200);
        System.out.println("Posted");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer reimbursementId = Integer.parseInt(req.getParameter("reimbursement-id"));
        Integer userId = Integer.parseInt(req.getParameter("user-id"));

        StringBuilder builder = new StringBuilder();
        BufferedReader reader = req.getReader();
        while (reader.ready()){
            builder.append(reader.readLine());

        }
        String json = builder.toString();
        Reimbursements updateReimbursements = mapper.readValue(json, Reimbursements.class);
        service.updateReimbursements(updateReimbursements, reimbursementId, userId);

        resp.setStatus(200);
        resp.setContentType("application/json; charset=utf-8");
        System.out.println("Here");

        service.updateReimbursements(updateReimbursements, reimbursementId, userId);

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String param = req.getParameter("user-id");
        Integer userId = Integer.parseInt(param);
        service.deleteReimbursement(userId);

        resp.setStatus(200);
        resp.getWriter().println("deleted");
        System.out.println("Deleted");
    }

    @Override
    public void destroy() {
    }
}
