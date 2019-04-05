package site.recomi.studentmanagement.utils;

import java.sql.*;

public class Mysql_connect {

    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://172.96.203.112:3306/campus_management_system";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "1281540128";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    public Mysql_connect(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    //查询并返回结果集
    private ResultSet query(String sql){
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return rs;
        }
    }

    //关闭
    private void close(){
        try {
            if(rs!=null) stmt.close();
            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //测试
    public static void main(String[] args) throws SQLException {
        Mysql_connect mysql_connect = new Mysql_connect();
        ResultSet rs = mysql_connect.query("select * from user_info");
        while(rs.next()){
            // 通过字段检索
            int id  = rs.getInt("id");
            String name = rs.getString("username");
            String password = rs.getString("password");
            String  identity= rs.getString("identity");

            // 输出数据
            System.out.println(id);
            System.out.println(name);
            System.out.println(password);
            System.out.println(identity);
        }
        rs.close();
    }


}