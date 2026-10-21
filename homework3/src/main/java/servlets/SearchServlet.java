package servlets;

import DAO.DataBaseConfig;
import DTO.UserDto;
import entities.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    UserDto userDto;

    @Override
    public void init() throws ServletException {
        DataSource dataSource = DataBaseConfig.getDataSource();
        this.userDto = new UserDto(dataSource);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("search_form.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        List<User> users = null;

        if (request.getParameter("city") != null) {
            String city = request.getParameter("city");
            users = userDto.getUsersByCity(city);
        }
        if (request.getParameter("age") != null) {
            int age = Integer.parseInt(request.getParameter("age"));
            users = userDto.getUsersByAge(age);
        }
        if (request.getParameter("role") != null) {
            String role = request.getParameter("role");
            users = userDto.getUsersByRole(role);
        }

        HttpSession session = request.getSession();
        session.setAttribute("usersList", users);
        response.sendRedirect("result.jsp");
    }
}
