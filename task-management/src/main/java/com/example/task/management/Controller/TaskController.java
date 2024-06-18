//package com.example.task.management.Controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.ui.Model;
//
//import java.sql.*;
//import java.sql.Date;
//import java.util.*;
//
//@Controller
//public class TaskController {
//    String jdbcurl = "jdbc:mysql://127.0.0.1:3306/taskmanagement";
//    String username;
//    String teamid;
//
//
//    @GetMapping("/start")
//    public String start()
//    {
//        return "login";
//    }
//    @GetMapping ("/sign")
//    public String dashboard()
//    {
//        return "signup";
//    }
//    @PostMapping("/signup")
//    public  String signup(@RequestParam("signupusername")String name, @RequestParam("signuppassword") String password)
//    {
//        System.out.println("Inside signup");
//
//        Connection connection = null;
//
//        try{
//            connection = DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
//            String sql= "insert into signup values(?,?)";
//            PreparedStatement statement= connection.prepareStatement(sql);
//            //when we use varialbles we use prepare statement or we use statement/
//            statement.setString(1,name);/* to replace the quetion mark we use preaparestatement and set the value to ?*/
//            statement.setString(2,password);
//            statement.executeUpdate();
//            System.out.println("updated");
//
//
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//        return "login";
//    }
//    @PostMapping("/login")
//    public String login(Model model, @RequestParam("loginusername") String name, @RequestParam("loginpassword")String password){
//        Connection connection=null;
//        this.username=name;
//        try{
//            connection =DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
//            String sql="SELECT userpassword from signup WHERE username=?";
//            PreparedStatement statement= connection.prepareStatement(sql);
//            statement.setString(1,name);
//            ResultSet rs=statement.executeQuery();
//            while(rs.next())
//            {
//                if(password.equals(rs.getString("userpassword"))){
//                    return "mainpage";
//                }
//                else{
//                    return "alert";
//                }
//            }
//        }
//        catch(SQLException e)
//        {
//            System.out.println(e);
//        }
//
//
//
//        return "login";
//    }
//    @PostMapping ("/join")
//    public String join()
//    {
//        return "join";
//    }
//
//    @PostMapping("/viewtask")
//    public String viewTask(@RequestParam("jointeamid") String teamid) throws SQLException {
//        this.teamid = teamid;
//        System.out.println(teamid);
//        int lastValue = 0;
//        Connection connection = null;
//        try{
//        PreparedStatement statementCheck = null;
//        connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
//        String sql13="SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'taskmanagement' AND table_name = '"+this.teamid+"'";
//        PreparedStatement statement12 = connection.prepareStatement(sql13);
//        ResultSet rs=statement12.executeQuery();
//        System.out.println(rs.getInt(1));
//        if (rs.getInt(1)==1) {
//
//            try{
//            PreparedStatement statement = null;
//
//                connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
//
//                String sqlCheck = "SELECT COUNT(*) FROM " + teamid + " WHERE username = ?";
//                PreparedStatement state = connection.prepareStatement(sqlCheck);
//                state.setString(1, this.username);
//                ResultSet rsCheck = statementCheck.executeQuery();
//                if (rsCheck.next() && rsCheck.getInt(1) > 0) {
//                    System.out.println("Username already exists in the table");
//                    return "justview";
//                } else {
//                    String sql = "SELECT MAX(teamno) FROM " + teamid;
//                    statement = connection.prepareStatement(sql);
//                    ResultSet rs1 = statement.executeQuery();
//                    if (rs1.next()) {
//                        lastValue = rs1.getInt(1) + 1;
//                    }
//                    String sqlInsert = "INSERT INTO " + teamid + " VALUES (?, ?, ?)";
//                    PreparedStatement statement11 = connection.prepareStatement(sqlInsert);
//                    statement11.setString(1, teamid);
//                    statement11.setString(2, this.username);
//                    statement11.setString(3, String.valueOf(lastValue));
//                    statement11.executeUpdate();
//
//                }
//            } catch (SQLException e) {
//                System.out.println(e);
//            } finally {
//                try {
//                    if (statementCheck != null) {
//                        statementCheck.close();
//                    }
//                    if (statement != null) {
//                        statement.close();
//                    }
//                    if (connection != null) {
//                        connection.close();
//                    }
//                } catch (SQLException e) {
//                    System.out.println("Error closing resources: " + e.getMessage());
//                }
//            }
//        }
//        else {
//            return "join";
//        }
//        return "justview";
//    }
//
//    @PostMapping("/create")
//    public String createteam()
//    {
//        return "create";
//    }
//
//    @PostMapping("/createteam")
//    public String createteam( @RequestParam("createteamid") String teamid){
//        this.teamid=teamid;
//        Connection connection=null;
//
//        try{
//            connection=DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
//
//            String sql="create table "+teamid+"(teamid varchar(20),username varchar(20),teamno int)";
//            PreparedStatement statement=connection.prepareStatement(sql);
//            statement.execute();
//            String insertsql="insert into "+teamid+" values(?,?,?)";
//            PreparedStatement statement1=connection.prepareStatement(insertsql);
//            statement1.setString(1,teamid);
//            statement1.setString(2,this.username);
//            statement1.setString(3, String.valueOf(1));
//
//
//            statement1.executeUpdate();
//
//            System.out.println("table created");
//            return "justview";
//
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//        System.out.println("in this name table is already craeated");
//
//        return "create";
//
//    }
//
//    @GetMapping("/justview")
//
//    public String justview() {
//        return "justview";
//    }
//
////    @PostMapping("/justviewtask")
////    public String justviewtask()
////    {
////        return "view";
////    }
//
//    @PostMapping("/justviewtask")
//    public String justviewtask(Model model,@RequestParam("teamid") String teamid) {
//        this.teamid=teamid;
//        System.out.println(teamid);
//        Connection connection=null;
//        try {
//            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
//            DatabaseMetaData metaData = connection.getMetaData();
//            ResultSet tables = metaData.getTables(null, null, teamid + "task", null);
//            String sql1="SELECT COUNT(*) FROM information_schema.tables WHERE table_schema = 'taskmanagement' AND table_name = '"+this.teamid+"task'";
//            PreparedStatement statement1 = connection.prepareStatement(sql1);
//            ResultSet rs=statement1.executeQuery();
//            if (rs.next()) {
//                int count=rs.getInt(1);
//                System.out.println(count);
//                if (count==1){
//                    return "view";
//                }
//            }
//            else{
//                return "join";
//            }
//                String sql = "create table  " + teamid + "task (sender varchar(20), task varchar(100), enddate date, receiver varchar(20),progress int)";
//                PreparedStatement statement = connection.prepareStatement(sql);
//                statement.execute();
//
//
//        }
//        catch(SQLException e)
//        {
//            System.out.println(e);
//        }
//
//
//        List<Map<String,Object>> data=fetchTask(teamid);
//        model.addAttribute("taskdetails",data);
//
//        List<Map<String, Object>> data2 = fetchTask2(teamid);
//        model.addAttribute("taskdetails2", data2);
//
//        List<Map<String, Object>> data3 = fetchTask3(teamid);
//        model.addAttribute("taskdetails3", data3);
//
//        return "view";
//
//    }
//
//    @ModelAttribute("taskdetails")
//    public List<Map<String,Object>> fetchTask(String teamid)
//    {
//        List<Map<String,Object>> listofdetails=new ArrayList<>();
//        Connection connection=null;
//        try {
//            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
//
//
//            String sql = "Select * from "+teamid+"task order by progress desc";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                Map<String, Object> mp = new HashMap<>();
//                mp.put("Sender", rs.getString("sender"));
//                mp.put("Task", rs.getString("task"));
//                mp.put("Receiver", rs.getString("receiver"));
//                mp.put("Enddate", rs.getDate("enddate"));
//                mp.put("Progress", rs.getInt("progress"));
//                listofdetails.add(mp);
//
//            }
//
//        }
//        catch (SQLException e)
//        {
//            System.out.println(e);
//        }
//        return listofdetails;
//
//
//    }
//
//    @ModelAttribute("taskdetails2")
//    public List<Map<String, Object>> fetchTask2(String teamid) {
//        List<Map<String, Object>> listofdetails = new ArrayList<>();
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
//
//            // Example SQL query for fetching specific tasks
//            String sql = "SELECT * FROM " + teamid + "task WHERE receiver='"+this.username+"'";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                Map<String, Object> mp = new HashMap<>();
//                mp.put("Sender", rs.getString("sender"));
//                mp.put("Task", rs.getString("task"));
//                mp.put("Receiver", rs.getString("receiver"));
//                mp.put("Enddate", rs.getDate("enddate"));
//                mp.put("Progress", rs.getInt("progress"));
//                listofdetails.add(mp);
//            }
//            System.out.println(listofdetails);
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return listofdetails;
//    }
//
//    @ModelAttribute("taskdetails3")
//    public List<Map<String, Object>> fetchTask3(String teamid) {
//        List<Map<String, Object>> listofdetails = new ArrayList<>();
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
//
//            // Example SQL query for fetching specific tasks
//            String sql = "SELECT * FROM " + teamid + "task WHERE sender='"+this.username+"'";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            ResultSet rs = statement.executeQuery();
//            while (rs.next()) {
//                Map<String, Object> mp = new HashMap<>();
//                mp.put("Sender", rs.getString("sender"));
//                mp.put("Task", rs.getString("task"));
//                mp.put("Receiver", rs.getString("receiver"));
//                mp.put("Enddate", rs.getDate("enddate"));
//                mp.put("Progress", rs.getInt("progress"));
//                listofdetails.add(mp);
//            }
//            System.out.println(listofdetails);
//        } catch (SQLException e) {
//            System.out.println(e);
//        }
//        return listofdetails;
//    }
//    @GetMapping("/update")
//    public String updatePage(Model model) {
//        List<Map<String, Object>> data = fetchTasks(teamid);
//        model.addAttribute("taskdetail", data);
//        return "updatetask";
//    }
//
//    @ModelAttribute("taskdetail")
//    public List<Map<String, Object>> fetchTasks(String teamid) {
//        List<Map<String, Object>> listofdetails = new ArrayList<>();
//        try  {
//            Connection connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
//            String sql = "SELECT * FROM " + teamid;
//            try (PreparedStatement statement = connection.prepareStatement(sql);
//                 ResultSet rs = statement.executeQuery()) {
//                while (rs.next()) {
//                    Map<String, Object> taskMap = new HashMap<>();
//                    taskMap.put("UserName", rs.getString("username"));
//                    taskMap.put("TeamNo", rs.getString("teamno"));
//                    listofdetails.add(taskMap);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return listofdetails;
//    }
//
//    @PostMapping("/updatetask")
//    public String update( @RequestParam("sender") String sender, @RequestParam("task") String task,@RequestParam("enddate") Date enddate,@RequestParam("receiver") String receiver,@RequestParam("progress") int progress) {
//        System.out.println(teamid);
//
//        Connection connection = null;
//        try {
//            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
//
//            DatabaseMetaData metaData = connection.getMetaData();
//
//
//
//            String insert = "insert into " + teamid + "task values(?,?,?,?,?)";
//            PreparedStatement statement1 = connection.prepareStatement(insert);
//            statement1.setString(1, sender);
//            statement1.setString(2, task);
//            statement1.setDate(3, enddate);
//            statement1.setString(4, receiver);
//            statement1.setInt(5, progress);
//
//            statement1.executeUpdate();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return "justview";
//    }
//    @GetMapping("/about")
//    public String about()
//    {
//        return "about";
//    }
//    @GetMapping("/contact")
//    public String contact()
//    {
//        return "contact";
//    }
//    @GetMapping("/mainpage")
//    public String mainpage()
//    {
//        return "mainpage";
//    }
//
////    @PostMapping("/viewtask")
////    public String view(Model model, @RequestParam("name") String name) {
////        System.out.println("Inside view task");
////        Connection connection = null;
////        List<Map<String, Object>> task = new ArrayList<>();
////        try {
////            connection = DriverManager.getConnection(jdbcurl, "root", "poovarasan");
////            String sql = "select * from task where reciever=?";
////            PreparedStatement statement = connection.prepareStatement(sql);
////            statement.setString(1, name); // Use 1 instead of 4 for setting parameters
////            ResultSet rs = statement.executeQuery();
////
////            while (rs.next()) {
////                Map<String, Object> mp = new HashMap<>();
////                mp.put("sender", rs.getString("sender"));
////                mp.put("task", rs.getString("task"));
////                mp.put("end_date", rs.getString("end_date"));
////                mp.put("reciever", rs.getString("reciever"));
////                mp.put("progress", rs.getString("progress"));
////                task.add(mp);
////            }
////        } catch (SQLException e) {
////            System.out.println(e);
////        } finally {
////            try {
////                if (connection != null) {
////                    connection.close();
////                }
////            } catch (SQLException e) {
////                System.out.println(e);
////            }
////        }
////       /// model.addText("dataList",task); // Change addText to addAttribute
////        return "view";
////    }
//
//
////@PostMapping("/delete")
////    public String delete()
////{
////    Connection connection=null;
////    try{
////
////    }
////    return "delete";
////}
//
//
//    @PostMapping("/delete")
//    public String  delete1(@RequestParam("selectedRow") String selectrow,Model model ){
//        // to get the task fromm the table
////        {username=abc,userid=def}
////        {username,abc
//        String task="";
//        String teamtask[]=selectrow.split(",");
//        for(String keyvalue:teamtask){
//            System.out.println(keyvalue);
//            //keyvlue="sender=name"
//            //keyvalue="task=do this"
//            String keyvaluearr[]=keyvalue.trim().split("=");
//            //{username,abc    but here the task is nnot in begin so we not want to .substring
//            if(keyvaluearr[0].equals("Task")){
//                task=keyvaluearr[1];
//            }
//        }
//
//
//        Connection connection=null;
//        try{
//            connection=DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
//            String sql=" delete from "+teamid+"task where task='"+task+"'";
//            PreparedStatement statement=connection.prepareStatement(sql);
//            statement.executeUpdate();
//
//            System.out.println("deleted");
//
//
//        }
//        catch (Exception e){
//            System.out.println(e);
//        }
//        return "justview";
//
//
//    }
//    @GetMapping("/hello")
//    public String helo(){
//        return "login";
//    }
//
//}
//
//



package com.example.task.management.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import java.util.HashMap;


@Controller
public class TaskController {


    String jdbcurl = "jdbc:mysql://127.0.0.1:3306/taskmanagement";
    String username;
    String teamid;

//to start

    @GetMapping("/start")
    public String start()
    {
        return "login";
    }
    @GetMapping ("/sign")
    public String dashboard()
    {
        return "signup";
    }



//to log&sig

    @PostMapping("/login")
    public String login(Model model, @RequestParam("loginusername") String name, @RequestParam("loginpassword")String password){
        Connection connection=null;
        this.username=name;
        try{
            connection =DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
            String sql="SELECT userpassword from signup WHERE username=?";
            PreparedStatement statement= connection.prepareStatement(sql);
            statement.setString(1,name);
            ResultSet rs=statement.executeQuery();

            while(rs.next())
            {
                if(password.equals(rs.getString("userpassword"))){
                    return "mainpage";
                }
                else{
                    return "alert";
                }
            }
        }
        catch(SQLException e)
        {
            System.out.println(e);
        }



        return "login";
    }
    @PostMapping("/signup")
    public  String signup(@RequestParam("signupusername")String name, @RequestParam("signuppassword") String password)
    {
        System.out.println("Inside signup");

        Connection connection = null;

        try{

            connection = DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
            String qq="select count(*) from signup where username=?";
            System.out.println("renned1");
            PreparedStatement statement0= connection.prepareStatement(qq);
            System.out.println("renned2");
            statement0.setString(1,name);
            System.out.println("renned3");
            ResultSet rs1= statement0.executeQuery();
//            if(rs1.getInt(1)>0){
                if(rs1.next() && rs1.getInt(1)>0){
                    System.out.println("user already present");
                    return "alert3";
                }

//                return "alert3";
//
//            }
            String sql= "insert into signup values(?,?)";
            PreparedStatement statement= connection.prepareStatement(sql);
            //when we use varialbles we use prepare statement or we use statement/
            statement.setString(1,name);/* to replace the quetion mark we use preaparestatement and set the value to ?*/
            statement.setString(2,password);
            System.out.println("added");
            statement.executeUpdate();


        }
        catch (Exception e){
            System.out.println(e);
        }
        return "login";
    }



//to call join or create

    @PostMapping ("/join")
    public String join()
    {
        return "join";
    }
    @PostMapping("/create")
    public String createteam()
    {
        return "create";
    }




//create task


    @PostMapping("/createteam")
    public String createteam( @RequestParam("createteamid") String teamid){
        this.teamid=teamid;
        Connection connection=null;

        try{
            connection=DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, this.teamid , null);
            if(tables.next()){
                System.out.println("table already available");
                return "alert2";
            }

            String sql="create table "+teamid+"(teamid varchar(20),username varchar(20),teamno int)";
            PreparedStatement statement=connection.prepareStatement(sql);

            String insertsql="insert into "+teamid+" values(?,?,?)";
            PreparedStatement statement1=connection.prepareStatement(insertsql);
            statement1.setString(1,teamid);
            statement1.setString(2,this.username);
            statement1.setString(3, String.valueOf(1));

            statement.executeUpdate();
            statement1.executeUpdate();

            System.out.println("table created");
            return "view";

        }
        catch (Exception e){
            System.out.println(e);
        }
        System.out.println("in this name table is already craeated");

        return "create";

    }




//join to task
    @PostMapping("/jointask")
    public String viewTask(@RequestParam("jointeamid") String teamid) {
        this.teamid = teamid;
        System.out.println(teamid);
        int lastValue = 0;
        Connection connection = null;
        PreparedStatement statementCheck = null;
        PreparedStatement statement = null;
        try {
            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");

            String sqlCheck = "SELECT COUNT(*) FROM " + teamid + " WHERE username = ?";
            statementCheck = connection.prepareStatement(sqlCheck);
            statementCheck.setString(1, this.username);
            ResultSet rsCheck = statementCheck.executeQuery();
            if (rsCheck.next() && rsCheck.getInt(1) > 0) {
                System.out.println("Username already exists in the table");
                return "justview";
            } else {
                String sql = "SELECT MAX(teamno) FROM " + teamid;
                statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                if (rs.next()) {
                    lastValue = rs.getInt(1) + 1;
                }
                String sqlInsert = "INSERT INTO " + teamid + " VALUES (?, ?, ?)";
                PreparedStatement statement1 = connection.prepareStatement(sqlInsert);
                statement1.setString(1, teamid);
                statement1.setString(2, this.username);
                statement1.setString(3, String.valueOf(lastValue));
                statement1.executeUpdate();

            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                if (statementCheck != null) {
                    statementCheck.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
        return "justview";
    }



// add team &task


    @GetMapping("/update")
    public String updatePage(Model model) {
        List<Map<String, Object>> data = fetchTasks(this.teamid);
        model.addAttribute("taskdetail", data);
        return "updatetask";
    }

    @ModelAttribute("taskdetail")
    public List<Map<String, Object>> fetchTasks(String teamid) {
        List<Map<String, Object>> listofdetails = new ArrayList<>();
        try  {
            Connection connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
            String sql = "SELECT * FROM " + teamid;
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Map<String, Object> taskMap = new HashMap<>();
                    taskMap.put("UserName", rs.getString("username"));
                    taskMap.put("TeamNo", rs.getString("teamno"));
                    listofdetails.add(taskMap);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listofdetails;
    }


//create task table

@PostMapping("/updatetask")
public String update( @RequestParam("sender") String sender, @RequestParam("task") String task,@RequestParam("enddate") Date enddate,@RequestParam("receiver") String receiver,@RequestParam("progress") int progress) {
    System.out.println(this.teamid);

    Connection connection = null;
    try {
        connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");

        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet tables = metaData.getTables(null, null, this.teamid + "task", null);

        if (!tables.next()) {

            String sql = "create table  " + this.teamid + "task (sender varchar(20), task varchar(100), enddate date, receiver varchar(20),progress int)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.execute();
        }


        String insert = "insert into " + this.teamid + "task values(?,?,?,?,?)";
        PreparedStatement statement1 = connection.prepareStatement(insert);
        statement1.setString(1, sender);
        statement1.setString(2, task);
        statement1.setDate(3, enddate);
        statement1.setString(4, receiver);
        statement1.setInt(5, progress);

        statement1.executeUpdate();

    } catch (Exception e) {
        System.out.println(e);
    }
    return "justview";
}



// delete task


    @PostMapping("/delete")
    public String  delete1(@RequestParam("selectedRow") String selectrow,Model model ){
        // to get the task fromm the table
//        {username=abc,userid=def}
//        {username,abc
        String task="";
        String teamtask[]=selectrow.split(",");
        for(String keyvalue:teamtask){
            System.out.println(keyvalue);
            //keyvlue="sender=name"
            //keyvalue="task=do this"
            String keyvaluearr[]=keyvalue.trim().split("=");
            //{username,abc    but here the task is nnot in begin so we not want to .substring
            if(keyvaluearr[0].equals("Task")){
                task=keyvaluearr[1];
            }
        }


        Connection connection=null;
        try{
            connection=DriverManager.getConnection(jdbcurl,"root","Logesh@121!");
            String sql=" delete from "+this.teamid+"task where task='"+task+"'";
            PreparedStatement statement=connection.prepareStatement(sql);
            statement.executeUpdate();

            System.out.println("deleted");


        }
        catch (Exception e){
            System.out.println(e);
        }
        return "/justview";
    }
//viewing task in oru team


    @PostMapping("/justviewtask")
    public String justviewtask(Model model,@RequestParam("teamid") String teamid) {
        this.teamid=teamid;
        System.out.println(teamid);
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");
            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, this.teamid + "task", null);
            if (!tables.next()) {
//                String sql = "create table  " + this.teamid + "task (sender varchar(20), task varchar(100), enddate date, receiver varchar(20),progress int)";
//                PreparedStatement statement = connection.prepareStatement(sql);
//                statement.execute();
                System.out.println("table not here");
                return "alert1";
            }

        }
        catch(SQLException e)
        {
            System.out.println(e);
        }


        List<Map<String,Object>> data=fetchTask(teamid);
        model.addAttribute("taskdetails",data);

        List<Map<String, Object>> data2 = fetchTask2(teamid);
        model.addAttribute("taskdetails2", data2);

        List<Map<String, Object>> data3 = fetchTask3(teamid);
        model.addAttribute("taskdetails3", data3);

        return "view";

    }

    @ModelAttribute("taskdetails")
    public List<Map<String,Object>> fetchTask(String teamid)
    {
        List<Map<String,Object>> listofdetails=new ArrayList<>();
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");


            String sql = "Select * from "+teamid+"task order by progress desc";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> mp = new HashMap<>();
                mp.put("Sender", rs.getString("sender"));
                mp.put("Task", rs.getString("task"));
                mp.put("Receiver", rs.getString("receiver"));
                mp.put("Enddate", rs.getDate("enddate"));
                mp.put("Progress", rs.getInt("progress"));
                listofdetails.add(mp);

            }

        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return listofdetails;


    }

    @ModelAttribute("taskdetails2")
    public List<Map<String, Object>> fetchTask2(String teamid) {
        List<Map<String, Object>> listofdetails = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");

            // Example SQL query for fetching specific tasks
            String sql = "SELECT * FROM " + teamid + "task WHERE receiver='"+this.username+"'";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> mp = new HashMap<>();
                mp.put("Sender", rs.getString("sender"));
                mp.put("Task", rs.getString("task"));
                mp.put("Receiver", rs.getString("receiver"));
                mp.put("Enddate", rs.getDate("enddate"));
                mp.put("Progress", rs.getInt("progress"));
                listofdetails.add(mp);
            }
            System.out.println(listofdetails);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listofdetails;
    }

    @ModelAttribute("taskdetails3")
    public List<Map<String, Object>> fetchTask3(String teamid) {
        List<Map<String, Object>> listofdetails = new ArrayList<>();
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(jdbcurl, "root", "Logesh@121!");

            // Example SQL query for fetching specific tasks
            String sql = "SELECT * FROM " + teamid + "task WHERE sender='"+this.username+"'";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Map<String, Object> mp = new HashMap<>();
                mp.put("Sender", rs.getString("sender"));
                mp.put("Task", rs.getString("task"));
                mp.put("Receiver", rs.getString("receiver"));
                mp.put("Enddate", rs.getDate("enddate"));
                mp.put("Progress", rs.getInt("progress"));
                listofdetails.add(mp);
            }
            System.out.println(listofdetails);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listofdetails;
    }
    @GetMapping("/hello")
    public String about()
    {
        return "login";
    }
    @GetMapping("/join")
    public String contact()
    {
        return "join";
    }
    @GetMapping("/trynewuser")
    public String mainpage()
    {
        return "signup";
    }

    @GetMapping("/gotocreate")
    public String gotocreate()
    {
        return "create";
    }

//    @PostMapping("/viewtask")
//    public String view(Model model, @RequestParam("name") String name) {
//        System.out.println("Inside view task");
//        Connection connection = null;
//        List<Map<String, Object>> task = new ArrayList<>();
//        try {
//            connection = DriverManager.getConnection(jdbcurl, "root", "poovarasan");
//            String sql = "select * from task where reciever=?";
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, name); // Use 1 instead of 4 for setting parameters
//            ResultSet rs = statement.executeQuery();
//
//            while (rs.next()) {
//                Map<String, Object> mp = new HashMap<>();
//                mp.put("sender", rs.getString("sender"));
//                mp.put("task", rs.getString("task"));
//                mp.put("end_date", rs.getString("end_date"));
//                mp.put("reciever", rs.getString("reciever"));
//                mp.put("progress", rs.getString("progress"));
//                task.add(mp);
//            }
//        } catch (SQLException e) {
//            System.out.println(e);
//        } finally {
//            try {
//                if (connection != null) {
//                    connection.close();
//                }
//            } catch (SQLException e) {
//                System.out.println(e);
//            }
//        }
//       /// model.addText("dataList",task); // Change addText to addAttribute
//        return "view";
//    }


//@PostMapping("/delete")
//    public String delete()
//{
//    Connection connection=null;
//    try{
//
//    }
//    return "delete";
//}









}

