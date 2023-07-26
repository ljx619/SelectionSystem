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
    public static String sname = null;

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
                default:
                    System.out.println("请重新输入正确的选项");
            }
        }
    }

    public static boolean login1() {
            System.out.println("请输入您的账号");
            Scanner sc = new Scanner(System.in);
            sname = sc.nextLine();
            System.out.println("请输入您的密码");
            String s2 = sc.nextLine();
            JdbcTemplate jt = new JdbcTemplate(JDBCutils.getDataSource());
            String sql = "select * from student where username = ? and password = ?";
            List<Map<String, Object>> list = jt.queryForList(sql, sname, s2);

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
            sname = sc.nextLine();
            System.out.println("请输入您的密码");
            String s2 = sc.nextLine();
            JdbcTemplate jt = new JdbcTemplate(JDBCutils.getDataSource());
            String sql = "select * from teacher where username = ? and password = ?";
            List<Map<String, Object>> list = jt.queryForList(sql, sname, s2);

            if (list.size() != 0) {
                System.out.println("登陆成功");
                return true;
            } else {
                System.out.println("账号或密码错误！");
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
            default:
                System.out.println("请重新输入正确的选项");
        }
    }

    private static void teachersign() {
        while (true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("请设置您的姓名");
                String s1 = sc.nextLine();
                System.out.println("请设置您的手机号");
                String s2 = sc.nextLine();
                System.out.println("请设置您的用户名");
                String s3 = sc.nextLine();

                //账号查重
                String sqluser = "select username from teacher where username = ?";
                List<Map<String, Object>> lists = jt.queryForList(sqluser, s3);
                if (lists.size() != 0) {
                    System.out.println("此账号已存在，请重新输入");
                    continue;
                }

                System.out.println("请设置您的密码");
                String s4 = sc.nextLine();
                String sql = "insert into teacher values (null,?,?,?,?)";
                jt.update(sql, s1, s2, s3, s4);
                System.out.println("注册成功");
                return;
            }catch (Exception e){
                System.out.println("输入有误，请重新输入");
            }
        }
    }

    private static void studentsign() {
        while(true) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("请设置您的姓名");
                String s1 = sc.nextLine();
                System.out.println("请设置您的性别");
                String s2 = sc.nextLine();
                System.out.println("请设置您的班级");
                int s3 = sc.nextInt();
                System.out.println("请设置您的用户名");
                sc.nextLine();
                String s4 = sc.nextLine();

                //账号查重
                String sqluser = "select username from student where username = ?";
                List<Map<String, Object>> lists = jt.queryForList(sqluser, s4);
                if (lists.size() != 0) {
                    System.out.println("此账号已存在，请重新输入");
                    continue;
                }

                System.out.println("请设置您的密码");
                String s5 = sc.nextLine();
                String sql = "insert into student values (null,?,?,?,?,?)";
                jt.update(sql, s1, s2, s3, s4, s5);
                System.out.println("注册成功");
                return;
            }catch (Exception e){
                System.out.println("输入有误，请重新输入");
            }
        }
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
                    String sql = "select * from student where username = ?";
                    List<Map<String, Object>> list = jt.queryForList(sql,sname);
                    for (Map map : list) {
                        Collection values = map.values();
                        System.out.println(values);
                    }
                    break;
                case 2:
                    try {
                        System.out.println("请输入新的姓名");
                        sc.nextLine();
                        String s1 = sc.nextLine();
                        System.out.println("请输入新的性别");
                        String s2 = sc.nextLine();
                        System.out.println("请输入新的班级");
                        int s3 = sc.nextInt();

                        String sql1 = "update student set name = ? where username = ?";
                        jt.update(sql1, s1,sname);

                        String sql2 = "update student set sex = ? where username = ?";
                        jt.update(sql2, s2,sname);

                        String sql3 = "update student set class = ? where username = ?";
                        jt.update(sql3, s3,sname);
                        System.out.println("修改成功");
                        break;
                    }catch (Exception e){
                        System.out.println("输入有误，请重新选择");
                        break;
                    }

                case 3:
                    System.out.println("请输入新的账号");
                    sc.nextLine();
                    String s4 = sc.nextLine();
                    String sql4 = "update student set username = ? where username = ?";
                    jt.update(sql4,s4,sname);
                    sname = s4;
                    System.out.println("请输入新的密码");
                    String s5 = sc.nextLine();
                    String sql5 = "update student set password = ? where username = ?";
                    jt.update(sql5,s5,sname );
                    System.out.println("修改成功");
                    break;
                case 4:
                    stutseleclass();
                    break;
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
                    String sql = "select * from teacher where username = ?";
                    List<Map<String, Object>> list = jt.queryForList(sql,sname);
                    for (Map map : list) {
                        Collection values = map.values();
                        System.out.println(values);
                    }
                    break;
                case 2:
                    try {
                        System.out.println("请输入新的姓名");
                        sc.nextLine();
                        String s1 = sc.nextLine();
                        System.out.println("请输入新的手机号");
//                        sc.nextLine();
                        int s2 = sc.nextInt();

                        sql = "update teacher set name = ? where username = ?";
                        jt.update(sql, s1,sname);

                        sql = "update teacher set phonenumber = ? where username = ?";
                        jt.update(sql, s2,sname);

                        System.out.println("修改成功");
                        break;
                    }catch (Exception e){
                        System.out.println("输入有误，请重新输入");
                        break;
                    }

                case 3:
                    System.out.println("请输入新的账号");
                    sc.nextLine();
                    String s4 = sc.nextLine();
                    String sql4 = "update teacher set username = ? where username = ?";
                    jt.update(sql4,s4,sname);
                    sname = s4;
                    System.out.println("请输入新的密码");
                    String s5 = sc.nextLine();
                    String sql5 = "update teacher set password = ? where username = ?";
                    jt.update(sql5,s5,sname );
                    System.out.println("修改成功");
                    break;
                case 4:
                    teacseleclass();
                    break;
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
                    String sql = "select student.name ,class.classname from conn join student on conn.studentid = student.id join class on conn.classid = class.id where student.username = ? ";
                    List<Map<String, Object>> list = jt.queryForList(sql,sname);
                    for (Map map : list) {
                        Collection values = map.values();
                        System.out.println(values);
                    }
                    break;
                case 2:
                    try {
                        System.out.println("请输入选课名称,1语文，2数学，3外语");
                        sc.nextLine();
                        int i2 = sc.nextInt();

                        String sqlt = "SELECT * FROM conn join class on conn.classid = class.id join student on conn.studentid = student.id where student.username = ? AND classid = ?";
                        List<Map<String, Object>> list2 = jt.queryForList(sqlt, sname, i2);
                        if(list2.size()!=0){
                            System.out.println("您已选择此课，请重新选择");
                            break;
                        }
                        sql = "insert into conn values(null,(select id from student where username = '" + sname + "'),?)";
                        jt.update(sql,i2);
                        System.out.println("选课成功");
                        break;
                    }catch (Exception e){
                        System.out.println("请输入正确选项");
                        break;
                    }
                case 3:
                    System.out.println("请输入退课名称,1语文，2数学，3外语");
                    int i3 = sc.nextInt();

                    String sqlt = "SELECT * FROM conn join class on conn.classid = class.id join student on conn.studentid = student.id where student.username = ? AND classid = ?";
                    List<Map<String, Object>> list2 = jt.queryForList(sqlt, sname, i3);
                    if(list2.size() == 0){
                        System.out.println("您没有选择此课，请重新选择");
                        break;
                    }

                    sql = "delete from conn where studentid = (select id from student where username = " + sname + ") and classid = ?";
                    jt.update(sql,i3);
                    System.out.println("退课成功");
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
                    String sql = "select classname from class join teacher on class.teacherid = teacher.id where teacher.username = ?";
                    List<Map<String, Object>> list1 = jt.queryForList(sql,sname);
                    System.out.println("下列为我的课程");
                    for(Map map:list1){
                        Collection values = map.values();
                        System.out.println(values);
                    }
                    break;
                case 2:
                    sql = "select student.name ,student.class from conn join student on student.id = " +
                            "conn.studentid join class on conn.classid = class.id join teacher on class.teacherid = teacher.id where teacher.username = ?";
                    List<Map<String, Object>> list2 = jt.queryForList(sql, sname);
                    System.out.println("当前选课学生有"+list2.size()+"个");
                    for(Map map:list2){
                        Collection values = map.values();
                        System.out.println(values);
                    }
                    break;
                case 3:
                    System.out.println("当前课程名称");
                    sql = "select class.classname from class join teacher on class.teacherid = teacher.id where username = ?";
                    List<Map<String, Object>> maps = jt.queryForList(sql,sname);
                    for(Map map:maps){
                        System.out.println(map.values());
                    }
                    System.out.println("请输入要修改的课程名称");
                    sc.nextLine();
                    String s = sc.nextLine();

                    String sqlts = "select * from class join teacher on class.teacherid = teacher.id where teacher.username = ? and class.classname = ?";
                    List<Map<String, Object>> list3 = jt.queryForList(sqlts, sname, s);
                    if(list3.size() == 0){
                        System.out.println("您输入的课程名称有误，请重新输入");
                        break;
                    }

                    System.out.println("请输入新的课程名称");
                    String s2 = sc.nextLine();
                    String sql2 = "update class set classname = ? where classname = ?";
                    jt.update(sql2,s2,s);
                    System.out.println("修改成功");
                    break;
                case 0:
                    return;
            }
        }
    }

}
