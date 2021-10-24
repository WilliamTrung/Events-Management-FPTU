/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import DTO.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author WilliamTrung
 */
@WebServlet(name = "ChangeStatusController", urlPatterns = {"/ChangeStatusController"})
public class ChangeStatusController extends HttpServlet {

    private final String SUCCESS = "userManagement.jsp";
    private final String FAIL = "userManagement.jsp";
    private final String LOGOUT = "LogoutController";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = FAIL;
        try {
            String status = request.getParameter("status");
            String search = request.getParameter("search");
            String userId = request.getParameter("userId");

            HttpSession session = request.getSession();
            UserDTO currentUser = (UserDTO) session.getAttribute("CURRENT_USER");
            if (currentUser.getUserId().equals(userId)) {
                url = LOGOUT;
            } else {
                UserDTO user = new UserDTO(userId, null, null, status, null, true, null, null);
                UserDAO uDao = new UserDAO();
                boolean check = uDao.updateStatus(user);
                if (check == true) {
                    url = SUCCESS;
                } else {
                    url = FAIL;
                }
                List<UserDTO> list = uDao.getListUsers(search);
                request.setAttribute("LIST_USER", list);
            }
        } catch (Exception e) {
            log("Error at UpdateUserController: " + e.toString());
        }
        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
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
     * Handles the HTTP <code>POST</code> method.
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

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
