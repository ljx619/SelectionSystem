package Main;

import org.springframework.jdbc.core.JdbcTemplate;
import utils.JDBCutils;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
//@SuppressWarnings("all")
public class Main {
    static public JdbcTemplate jt = new JdbcTemplate(JDBCutils.getDataSource());
    public static String s1 = null;

    public static void main(String[] args) {
        while (true) {
            System.out.println("----选课管理系统----");
            System.out.println("1.学生登陆");
            System.out.println("2.教师登录");
            System.out.println("3.注册用户");
            System.out.println("0.退出系统");
            System.out.println("----------------");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    if (login1()) {
                        studentfunction();
                    }
                    break;
                case 2:
                    if (login2()) {
                        teacherfunction();
                    }
                    break;
                case 3:
                    sign();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }

    public static boolean login1() {
            System.out.println("请输入您的账号");
            Scanner sc = new Scanner(System.in);
            s1 = sc.nextLine();
            System.out.println("请输入您的密码");
            String s2 = sc.nextLine();
            JdbcTemplate jt = new JdbcTemplate(JDBCutils.getDataSource());
            String sql = "select * from student where name = ? and password = ?";
            List<Map<String, Object>> list = jt.queryForList(sql, s1, s2);
            if (list.size() != 0) {
                System.out.println("登陆成功");
                return true;
            } else {
                System.out.println("账号或密码错误！");
                return false;
            }
    }

    public static boolean login2() {
            System.out.println("请输入您的账号");
            Scanner sc = new Scanner(System.in);
            s1 = sc.nextLine();
            System.out.println("请输入您的密码");
            String s2 = sc.nextLine();
            JdbcTemplate jt = new JdbcTemplate(JDBCutils.getDataSource());
            String sql = "select * from teacher where name = ? and password = ?";
            List<Map<String, Object>> list = jt.queryForList(sql, s1, s2);
            if (list.size() != 0) {
                System.out.println("登陆成功");
                return true;
            } else {
                return false;
            }
    }

    public static void sign() {
        System.out.println("----选课管理系统----");
        System.out.println("1.学生注册");
        System.out.println("2.教师注册");
        System.out.println("----------------");
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        switch (i) {
            case 1:
                studentsign();
                break;
            case 2:
                teachersign();
                break;
        }
    }

    private static void teachersign() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请设置您的姓名");
        String s1 = sc.nextLine();
        System.out.println("请设置您的手机号");
        String s2 = sc.nextLine();
        System.out.println("请设置您的用户名");
        String s3 = sc.nextLine();
        System.out.println("请设置您的密码");
        String s4 = sc.nextLine();
        String sql = "insert into teacher values (null,?,?,?,?)";
        jt.update(sql, s1, s2, s3, s4);
        System.out.println("注册成功");
    }

    private static void studentsign() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请设置您的姓名");
        String s1 = sc.nextLine();
        System.out.println("请设置您的性别");
        String s2 = sc.nextLine();
        System.out.println("请设置您的班级");
        int s3 = sc.nextInt();
        System.out.println("请设置您的用户名");
        String s4 = sc.nextLine();
        System.out.println("请设置您的密码");
        String s5 = sc.nextLine();
        String sql = "insert into student values (null,?,?,?,?,?)";
        jt.update(sql, s1, s2, s3, s4, s5);
        System.out.println("注册成功");
    }

    public static void studentfunction() {
        while (true) {
            System.out.println("----学生信息系统----");
            System.out.println("1.个人信息查询");
            System.out.println("2.个人信息修改");
            System.out.println("3.账户密码修改");
            System.out.println("4.选课与退课");
            System.out.println("0.退出当前页面");
            System.out.println("----------------");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    String sql = "select * from student where name = ?";
                    List<Map<String, Object>> list = jt.queryForList(sql, s1);
                    for (Map map : list) {
                        Collection values = map.values();
                        System.out.println(values);
                    }
                    break;
                case 2:
                    System.out.println("请输入新的姓名");
                    String sname = sc.nextLine();
                    sql = "update student set name = ? where username = ?";
                    jt.update(sql, sname,s1);
                    System.out.println("请输入新的性别");
                    String s2 = sc.nextLine();
                    sql = "update student set sex = ?";
                    jt.update(sql, s2);
                    System.out.println("请输入新的班级");
                    int s3 = sc.nextInt();
                    sql = "update student set class = ?";
                    jt.update(sql, s3);
                    System.out.println("修改成功");
                    break;
                case 3:
                    System.out.println("请输入新的账号");
                    String s4 = sc.nextLine();
                    sql = "update student set username = ?";
                    jt.update(sql, s4);
                    System.out.println("请输入新的密码");
                    String s5 = sc.nextLine();
                    sql = "update student set password = ?";
                    jt.update(sql, s5);
                    break;
                case 4:
                    stutseleclass();
                case 0:
                    return;
            }
        }
    }

    public static void teacherfunction() {
        while (true) {
            System.out.println("----教师信息系统----");
            System.out.println("1.个人信息查询");
            System.out.println("2.个人信息修改");
            System.out.println("3.账户密码修改");
            System.out.println("4.选课查询");
            System.out.println("0.退出当前页面");
            System.out.println("----------------");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    String sql = "select * from student where name = ?";
                    System.out.println(s1);
                    List<Map<String, Object>> list = jt.queryForList(sql, s1);
                    for (Map map : list) {
                        Collection values = map.values();
                        System.out.println(values);
                    }
                    break;
                case 2:
                    System.out.println("请输入新的姓名");
                    String s1 = sc.nextLine();
                    sql = "update student set name = ?";
                    jt.update(sql, s1);
                    System.out.println("请输入新的性别");
                    String s2 = sc.nextLine();
                    sql = "update student set sex = ?";
                    jt.update(sql, s2);
                    System.out.println("请输入新的班级");
                    int s3 = sc.nextInt();
                    sql = "update student set class = ?";
                    jt.update(sql, s3);
                    break;
                case 3:
                    System.out.println("请输入新的账号");
                    String s4 = sc.nextLine();
                    sql = "update student set username = ?";
                    jt.update(sql, s4);
                    System.out.println("请输入新的密码");
                    String s5 = sc.nextLine();
                    sql = "update student set password = ?";
                    jt.update(sql, s5);
                    break;
                case 4:
                    teacseleclass();
                case 0:
                    return;
            }
        }
    }

    public static void stutseleclass() {
//        int classnumber = 0;
        while (true) {
            System.out.println("----学生信息系统----");
            System.out.println("1.查看选课情况");
            System.out.println("2.选课");
            System.out.println("3.退课");
            System.out.println("0.退出当前页面");
            System.out.println("----------------");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    String sql = "select class.classname from conn join student on conn.studentid = student.id join teacher on conn.classid = class.id ";
                    List<Map<String, Object>> list = jt.queryForList(sql);
                    for (Map map : list) {
                        Collection values = map.values();
                        System.out.println(values);
                    }
                    break;
                case 2:
                    System.out.println("请输入选课名称,1语文，2数学，3外语");
                    int i2 = sc.nextInt();
                    sql = "insert into conn values(null,(select id from student where name = " + s1 + ")," + i2 + ")";
                    jt.update(sql);
                    System.out.println("选课成功");
                    break;
                case 3:
                    System.out.println("请输入退课名称,1语文，2数学，3外语");
                    int i3 = sc.nextInt();
                    sql = "drop table conn where studentid = (select id from student where name = " + s1 + ") and classid = i3";
                    jt.update(sql);
                    System.out.println("退课成功");
                    ;
                    break;
                case 0:
                    return;
            }
        }
    }

    public static void teacseleclass() {
        while (true) {
            System.out.println("----教师信息系统----");
            System.out.println("1.查看我的课程");
            System.out.println("2.查看选课学生");
            System.out.println("3.修改课程信息");
            System.out.println("0.退出当前页面");
            System.out.println("----------------");
            Scanner sc = new Scanner(System.in);
            int i = sc.nextInt();
            switch (i) {
                case 1:
                    String sql = "select classname from class join teacher on teacherid = teaacher.id";
                    Map<String, Object> stringObjectMap = jt.queryForMap(sql);
                    Collection<Object> values = stringObjectMap.values();
                    System.out.println(values);
                    break;
                case 2:
                    sql = "select class.classname from conn join student on student.id = " +
                            "studentid join class on classid = class.id where class.teacherid = (select id from teacher where username ="+s1+" )";
                    Map<String, Object> stringObjectMap1 = jt.queryForMap(sql);
                    Collection<Object> values1 = stringObjectMap1.values();
                    System.out.println(values1);
                    break;
                case 3:
                    System.out.println("请输入要修改的");
                    sql = "update class set class = ? where teacherid = (select id from teacher where username = ?)";

                    break;
                case 0:
                    return;
            }
        }
    }

}
