package dao.impl;
import dao.JobDao;
import domain.Department;
import domain.Job;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class JobDaolmpl implements JobDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public List<Job> findAll() {
        String sql="select * from job";
        List<Job> jobList=template.query(sql,new BeanPropertyRowMapper<Job>(Job.class));
        return jobList;
    }

    @Override
    public void add(Job job) {
        String sql="insert into job values(null,?,?)";
        template.update(sql,job.getJobname(),job.getJobdescribe());

    }

    @Override
    public Job getinfo_byID(int id) {
        String sql="select * from job where job_id= ?";
        // template.queryForObject标量子查询,只能传入一个基本类型的包装类的class,并返回一个基本类型对应包装类型的对象.
        return template.queryForObject(sql,new BeanPropertyRowMapper<Job>(Job.class),id);

    }

    @Override
    public void updateJob(Job job) {
        String sql="update job set jobname=?,jobdescribe=? where job_id=?";
        template.update(sql,job.getJobname(),job.getJobdescribe(),job.getJob_id());
    }

    @Override
    public void deleteJob(int id) {
        String sql="delete from job where job_id=?";
        template.update(sql,id);
    }

    @Override
    public List<Job> findByname(String jobname) {
        String sql= "select * from job where jobname like \"%\"? \"%\"";
        List<Job> job=template.query(sql,new BeanPropertyRowMapper<Job>(Job.class),jobname);
        return job;
    }
}
