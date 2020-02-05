
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
// Name: Chong, Chanip
// Student ID: 012548263
// HW4 coding part


/**
 *  it's the code for JDBC (Java Database Connectivity) java - mysql
 */
public class WhichEmployee
{

    public static void main(String[] args)
    {
            //DB user and password
        String user = "root";
        String password = "19960207";
        ArrayList<Employee> results = new ArrayList<Employee>();

    //execute the data
        if (args.length != 2)
        {
            System.out.println("not valid instruction");
            System.exit(1);

        }

        else
        {
            String condition = args[0];
            String target = args[1];
            if (condition.equals("public"))
            {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/hw5",user,password);
                    Statement stmt=con.createStatement();
                    ResultSet rs=stmt.executeQuery("SELECT * FROM PublicEmployee WHERE name LIKE '%"+ target+ "%'\n");
                    while(rs.next())
                    {
                        //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                        String id = rs.getString("id");
                        String name = rs.getString("name");
                        String address = rs.getString("address");
                        String departmentName = rs.getString("departmentName");
                        String supervisorID = rs.getString("supervisorID");
                        float salary = rs.getFloat("salary");
                        Employee one = new Employee(id,name,address,departmentName,supervisorID,salary);
                        results.add(one);
                    }
                    con.close();
                }catch(Exception e){ System.out.println(e);}
            }

            else if (condition.equals("private"))
            {
                try{
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection con=DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/hw5",user,password);
                    Statement stmt=con.createStatement();
                    ResultSet rs=stmt.executeQuery("SELECT * FROM employee WHERE name LIKE '%"+ target+ "%'\n");
                    while(rs.next())
                    {
                        //System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
                        String id = rs.getString("id");
                        String name = rs.getString("name");
                        String address = rs.getString("address");
                        String departmentName = rs.getString("departmentName");
                        String supervisorID = rs.getString("supervisorID");
                        float salary = rs.getFloat("salary");
                        Employee one = new Employee(id,name,address,departmentName,supervisorID,salary);
                        results.add(one);
                    }
                    con.close();
                }catch(Exception e){ System.out.println(e);}
            }
            else
            {
                System.out.println("not valid instruction");

                System.exit(1);
            }
            // sort result

            Collections.sort(results);


            System.out.println("id" +"   " + "name" +"   " + "address" +"   " + "departmentName" +"   " + "supervisorID" +"   " + "salary");
            for (Employee e : results) {
                System.out.println(e.id +"   " + e.name +"   " + e.address +"   " + e.departmentName +"   " + e.supervisorID +"   " + e.salary);
            }
        }





    }


    /**
     *  this is the custom employee class to store one row of the table
     */
    public static class Employee implements Comparable<Employee>
    {
       String id,name,address, departmentName, supervisorID;
       float salary;
        public Employee(String id, String name,String address, String departmentName, String supervisorID, float salary)
        {
            this.id = id;
            this.name = name;
            this.address = address;
            this.departmentName = departmentName;
            this.supervisorID = supervisorID;
            this.salary =salary;
        }

        /**
         * it's a comparator to make employee comparable with it's name in increasing alphabetical order
         * @param o the second Employee to compare with
         * @return the result of comparison
         */
        @Override
        public int compareTo(Employee o)
        {
            String name = ((Employee) o).name;

            //increasing alphabetical order
            return this.name.compareTo(name);

        }
    }
}
