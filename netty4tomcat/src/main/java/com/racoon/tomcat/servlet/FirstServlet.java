package com.racoon.tomcat.servlet;

import com.racoon.tomcat.http.GPRequest;
import com.racoon.tomcat.http.GPResponse;
import com.racoon.tomcat.http.GPServlet;

public class FirstServlet extends GPServlet {

    public void doPost(GPRequest request, GPResponse response) throws Exception {
        response.write("This is First Servlet");
    }

    public void doGet(GPRequest request, GPResponse response) throws Exception{
        this.doPost(request, response);
    }
}
