package database;

import UI.FileItem;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
public class Database {
    static DataSource dataSource=null;
    static Connection conn;
    static{
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("src/opr.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取连接池对象

        try {
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取数据库连接conn
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static List<DataItem> readData() throws Exception {
//        Properties prop = new Properties();
//        prop.load(new FileInputStream("src/opr.properties"));
//        //获取连接池对象
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        //获取数据库连接conn
//        Connection conn = dataSource.getConnection();
        String sql = "select * from or1";
        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);

        //执行sql
        ResultSet rs = pstmt.executeQuery();
        DataItem dataItem=null;
        List<DataItem> dataItems=new ArrayList<>();
        while (rs.next()){
            Date time = rs.getTimestamp("time");
            String data1 = rs.getString("data1");
            String data2 = rs.getString("data2");
            String operation = rs.getString("operation");
            dataItem = new DataItem(time,data1,data2,operation);
            //Brand(Integer id, String brandName, String companyName, int ordered, String description, Integer status) {
            System.out.println(dataItem);
            dataItems.add(dataItem);

        }
        System.out.println(dataItems);
        rs.close();
        //pstmt.close();
        //conn.close();
        return dataItems;
    }

    public static void updateData(String data1,String data2,String operation) throws Exception {
        Date c = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Timestamp sqlDate = new Timestamp(c.getTime());
        System.out.println(sqlDate);


//        String time="2021/3/5";
//        String data1="123";
//        String data2="4566";
//        String operation="移动";

//        Properties prop = new Properties();
//        prop.load(new FileInputStream("src/opr.properties"));
//        //获取连接池对象
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        //获取数据库连接conn
//        Connection conn = dataSource.getConnection();
        String sql = "insert into or1(time,data1,data2,operation) values(?,?,?,?)";
        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setTimestamp(1,sqlDate);
        pstmt.setString(2,data1);
        pstmt.setString(3,data2);
        pstmt.setString(4,operation);
        //执行sql
        int count = pstmt.executeUpdate();

        System.out.println(count);
        //pstmt.close();
        //conn.close();
    }

    public static void updateData(List<FileItem> data1, String data2, String operation) throws Exception {
        Date c = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Timestamp sqlDate = new Timestamp(c.getTime());
        System.out.println(sqlDate);


//        String time="2021/3/5";
//        String data1="123";
//        String data2="4566";
//        String operation="移动";

//        Properties prop = new Properties();
//        prop.load(new FileInputStream("src/opr.properties"));
//        //获取连接池对象
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        //获取数据库连接conn
//        Connection conn = dataSource.getConnection();
        String sql = "insert into or1(time,data1,data2,operation) values(?,?,?,?)";
        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (FileItem FI:data1){
            pstmt.setTimestamp(1,sqlDate);
            pstmt.setString(2,FI.toString());
            pstmt.setString(3,data2);
            pstmt.setString(4,operation);
            int count = pstmt.executeUpdate();
            System.out.println(count);
        }

        //执行sql
        //pstmt.close();
        //conn.close();
    }

    public static void updateData(String data1,List<FileItem> data2,String operation) throws Exception {
        Date c = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        Timestamp sqlDate = new Timestamp(c.getTime());
        System.out.println(sqlDate);


//        String time="2021/3/5";
//        String data1="123";
//        String data2="4566";
//        String operation="移动";

//        Properties prop = new Properties();
//        prop.load(new FileInputStream("src/opr.properties"));
//        //获取连接池对象
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        //获取数据库连接conn
//        Connection conn = dataSource.getConnection();
        String sql = "insert into or1(time,data1,data2,operation) values(?,?,?,?)";
        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        for (FileItem FI:data2){
            pstmt.setTimestamp(1,sqlDate);
            pstmt.setString(2,data1);
            pstmt.setString(3,FI.toString());
            pstmt.setString(4,operation);
            int count = pstmt.executeUpdate();
            System.out.println(count);
        }

        //执行sql

        //pstmt.close();
        //conn.close();
    }
    public static void deleteData() throws Exception {
        //清空数据库
//        Properties prop = new Properties();
//        prop.load(new FileInputStream("src/opr.properties"));
//        //获取连接池对象
//        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
//        //获取数据库连接conn
//        Connection conn = dataSource.getConnection();
        String sql = "delete from or1 where 1 = 1";
        //获取pstmt对象
        PreparedStatement pstmt = conn.prepareStatement(sql);
        //执行sql
        int count = pstmt.executeUpdate();

        System.out.println(count);
        System.out.println("数据库已清空");
        //pstmt.close();
        //conn.close();
    }
    public static void CloseDatabase() throws SQLException {
        conn.close();
    }
    public static void main(String[] args) throws Exception {
        //deleteData();
        readData();
    }
}
