/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.tolok.projects.project4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import ua.tolok.projects.project4.model.commands.Command;
import ua.tolok.projects.project4.model.commands.CommandFactory;

/**
 * Servlet provides comunication betwen view and model parts of application
 *
 * @author Sergiy Tolok
 */
public class ParkServlet extends HttpServlet {

    /**
     *
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CommandFactory factory = CommandFactory.createCommandFactory();
        try {
            Command command = factory.createCommand(request, response);
            String url;
            url = command.perform();
            if (url != null) {
                response.sendRedirect(url);
            }
        } catch (Throwable ex) {
             Logger.getLogger(ParkServlet.class.getName()).log(Level.ERROR, null, ex);
             throw new ServletException(ex.toString());
        }

    }

    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
        
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
    }
}
