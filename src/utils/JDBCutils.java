package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/*
* JDBCutils 工具类
* */
public class JDBCutils {
    //定义成员变量
     private static DataSource ds;
     static{
         //加载配置文件
         Properties properties = new Properties();
         try {
             properties.load(JDBCutils.class.getClassLoader().getResourceAsStream("druid.properties"));
             //获取datasource
             ds = DruidDataSourceFactory.createDataSource(properties);


         } catch (IOException e) {
             throw new RuntimeException(e);
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
     }

     public static Connection getConnection() throws SQLException {
        return ds.getConnection();
     }


     public static void close(Statement stmt,Connection conn){
         close(null,stmt,conn);
     }

    public static void close(ResultSet rs, Statement stmt, Connection conn){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }

    //获取连接池
    public static DataSource getDataSource(){
         return ds;
    }


}
