package com.Dex.Servlets;

import com.Dex.Aquarium;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AquariumServlet extends HttpServlet {
    public static Aquarium aquarium;
    public static ServletContext context;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (aquarium == null )
        {

            context = this.getServletContext();
            aquarium = new Aquarium();
            aquarium.init();

        }


        request.setAttribute("cells",aquarium.getCells());
        request.setAttribute("basepath","http://localhost:8080/AquariumSystem/");
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (aquarium != null) {
            //System.out.println(request.getParameter("redraw"));
            if (request.getParameter("redraw") != null) {
                aquarium = new Aquarium();
                aquarium.init();
            }
            else
            {
                for (int i = 0; i < (request.getParameter("iterations") == null ? 1 : Integer.parseInt(request.getParameter("iterations"))); i++) {
                    aquarium.newIteration();
                }
            }
            request.setAttribute("cells", aquarium.getCells());
            request.setAttribute("basepath", "http://localhost:8080/AquariumSystem/");
            //request.getRequestDispatcher("/index.jsp").forward(request, response);
            response.sendRedirect("/AquariumSystem/Aquarium");
        }
        else
        {
            response.sendRedirect("/AquariumSystem/Aquarium");
        }
    }
}
