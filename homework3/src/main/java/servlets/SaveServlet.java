package servlets;

import DAO.DataBaseConfig;
import DAO.UserDao;
import entities.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/save")
public class SaveServlet extends HttpServlet {
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        DataSource dataSource = DataBaseConfig.getDataSource();
        this.userDao = new UserDao(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int count = userDao.getCountOfUsers();
        request.setAttribute("usersCount", count);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/save.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        int count = Integer.parseInt(request.getParameter("total_count"));

        List<User> users = new ArrayList<>();
        for (int i = 1; i <= count; i++) {
            User user = new User();
            user.setUser_id(Integer.parseInt(request.getParameter("user_id_" + i)));
            user.setFirst_name(request.getParameter("first_name_" + i));
            user.setLast_name(request.getParameter("last_name_" + i));
            user.setEmail(request.getParameter("email_" + i));
            user.setAge(Integer.parseInt(request.getParameter("age_" + i)));
            user.setCity(request.getParameter("city_" + i));
            user.setRole(request.getParameter("role_" + i));
            users.add(user);
        }
        userDao.insertUsers(users);
        response.sendRedirect("success.jsp");
    }
}
