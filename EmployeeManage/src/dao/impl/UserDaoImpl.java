package dao.impl;
import dao.UserDao;
import domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;
import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    //使用JdbcTemplate Spring MVC内置的对JDBC的一个封装
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    // 查询用户所有信息
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "select * from user";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
        return users;

    }

    @Override
    //用户登录
    public User findUserByUserNameAndPassword(String admname, String password) {
        try {
            String sql="select * from user where loginname=? and password= ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), admname, password);
            return user;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    //添加管理员
    public void add(User user) {
        //1.定义sql
        String sql = "insert into user values(null,?,?,?)";
        //2.执行sql
        template.update(sql,user.getLoginname(),user.getUsername(),user.getPassword());
    }

    @Override
    //删除管理员
    public void delete(int id) {
        String sql = "delete from user where id=?";
        //2.执行sql
        template.update(sql, id);

    }

    @Override
    //通过Id 查询
    public User findById(int id) {
        String sql = "select* from user where id=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),id);
    }

    @Override
    //更新管理员
    public void update(User user) {
        //定义sql
        String sql = "update user set loginname = ?, username = ? where id = ?";
        template.update(sql,user.getLoginname(),user.getUsername(),user.getId());

    }

    @Override
    //管理员名字模糊查询
    public List<User> findByname(String username) {
        String sql= "select * from user where username like \"%\"? \"%\"";
        List<User> user=template.query(sql,new BeanPropertyRowMapper<User>(User.class),username);
        return user;
    }

    @Override
    //管理员计数
    public int count() {
        String sql="select count(*) from user";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    //管理员修改密码
    public void updatePwd(User user) {
        String sql = "update user set password = ?  where id = ?";
        template.update(sql,user.getPassword(),user.getId());
    }


    @Override
    //统计总数
    public int findTotalCount(Map<String, String[]> condition) {
        return 0;
    }
}
