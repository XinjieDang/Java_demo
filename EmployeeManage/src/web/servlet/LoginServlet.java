package web.servlet;
import domain.User;
import domain.Staff;
import service.StaffService;
import service.UserService;
import service.imp.StaffServicelmpl;
import service.imp.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        //2.获取数据
        //2.1获取用户填写验证码
        String verifycode = request.getParameter("verifycode");
        String role=request.getParameter("role");
        //3.验证码校验
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码一次性,再次登录不重复
        if(!checkcode_server.equalsIgnoreCase(verifycode)){
            //验证码不正确
            //提示信息
            request.setAttribute("login_msg","验证码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //判断用户类型
        if("1".equals(role)){
            adminlogin(request,response);
        }else {
            stafflogin(request,response);
        }
    }
    protected void adminlogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        //4.封装User对象
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.调用Service查询
        HttpSession session = request.getSession();
        UserService service = new UserServiceImpl();
        User loginUser = service.login(user);
        //6.判断是否登录成功
        if(loginUser != null){
            //登录成功
            System.out.println("Login successful...");
            //将用户存入session
            session.setAttribute("user",loginUser);
            //统计管理员人数
            int count_admin=service.conunt();
            //统计职工人数
            StaffService staffService=new StaffServicelmpl();
            int count_staff=staffService.count_staff();
            //将统计管理员信息和职工信息存入session
            session.setAttribute("user_count",count_admin);
            session.setAttribute("count_staff",count_staff);
            System.out.println("统计系统管理员人数"+count_admin+"");
            System.out.println("统计职工人数"+count_staff+"");
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else{
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);

        }

    }
    protected void stafflogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> map = request.getParameterMap();
        //4.封装User对象
        Staff staff = new Staff();
        try {
            BeanUtils.populate(staff,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //5.调用Service查询
        HttpSession session = request.getSession();
        StaffService staffService=new StaffServicelmpl();
        Staff loginStaff = staffService.login(staff);
        //6.判断是否登录成功
        if(loginStaff != null){
            //登录成功
            System.out.println("Login successful...");
            //将用户存入session
            session.setAttribute("staff",loginStaff);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index_staff.jsp");
        }else{
            //登录失败
            //提示信息
            request.setAttribute("login_msg","用户名或密码错误！");
            //跳转登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
