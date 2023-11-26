package repository;

import dto.Task;
import enums.Type;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

public class TaskRepository {

    public boolean createTaskRepository(Task task) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbs_db",
                    "jdbc_user", "123456"); // <2>
            Statement statement = con.createStatement(); // <3>
            String sql = "insert into taskManager(content,title,status,created_date) values('%s','%s','%s','" + task.getCreated_date() + "')";
            sql = String.format(sql, task.getContent(), task.getTitle(), task.getType());
            int effectedRows = statement.executeUpdate(sql);
            con.close();
            return effectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Task> getActiveTask() {
        List<Task> dtoList = new LinkedList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbs_db",
                    "jdbc_user", "123456"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select id,content,title,status,created_date from taskManager");
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setContent(rs.getString("content"));
                task.setTitle(rs.getString("title"));
                task.setType(Type.valueOf(rs.getString("status")));
                task.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());
                dtoList.add(task);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtoList;
    }

    public List<Task> getFinishedTask() {
        List<Task> dtoList = new LinkedList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbs_db",
                    "jdbc_user", "123456"); // <2>
            Statement statement = con.createStatement(); // <3>
            ResultSet rs = statement.executeQuery("select id,content,title,status,created_date,finished_date from taskManager where status='DONE'");
            while (rs.next()) {
                Task task = new Task();
                task.setId(rs.getInt("id"));
                task.setContent(rs.getString("content"));
                task.setTitle(rs.getString("title"));
                task.setType(Type.valueOf(rs.getString("status")));
                task.setCreated_date(rs.getTimestamp("created_date").toLocalDateTime());
                task.setFinished_date(rs.getTimestamp("finished_date").toLocalDateTime());
                dtoList.add(task);
            }
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dtoList;
    }


    public boolean updateRepository(int id, Task task) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbs_db",
                    "jdbc_user", "123456"); // <2>
            Statement statement = con.createStatement(); // <3>
//            String sql = "update student set name = '"+dto.getName()+"', surname ='"+dto.getSurname()+"' where id = "+id;
            String sql = "update taskManager set content = '%s', title ='%s',created_date='" + task.getCreated_date() + "' where id=" + id;
            sql = String.format(sql, task.getContent(), task.getTitle());
            int effectedRows = statement.executeUpdate(sql);
            con.close();
            return effectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean delete(Integer id) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbs_db",
                    "jdbc_user", "123456");
            Statement statement = con.createStatement();
            String sql = "delete from taskManager where id = " + id;
            int effectedRows = statement.executeUpdate(sql);
            con.close();
            return effectedRows != 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean doneTaskRepository(int id, Type type) {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbs_db",
                    "jdbc_user", "123456");
            Statement statement = con.createStatement();
            String sql = "update taskManager set status='" + type + "',finished_date='" + LocalDateTime.now() + "' where id=" + id;
            int effectedRows = statement.executeUpdate(sql);
            con.close();
            return effectedRows > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
