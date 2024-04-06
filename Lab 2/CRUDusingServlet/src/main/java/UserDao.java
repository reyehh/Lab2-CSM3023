import java.util.*;
import java.sql.*;

public class UserDao {
    
    public static Connection getConnection(){
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CSM3023", "root", "");
        } catch (ClassNotFoundException | SQLException e){
            e.printStackTrace();
        }
        return con;
    }
    
    public static int save(user e){
        int status = 0;
        try{
            Connection con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("insert into users(username,password,roles) values (?,?,?)");
            ps.setString(1, e.getUsername());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getRole());
            
            status = ps.executeUpdate();
            
            con.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return status;
    }
        
    public static int update(user e){
        int status = 0;
        try{
            Connection con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("update users set username=?, password=?, roles=? where id=?");
            ps.setString(1, e.getUsername());
            ps.setString(2, e.getPassword());
            ps.setString(3, e.getRole());
            ps.setInt(4, e.getId());
                
            status = ps.executeUpdate();
                
            con.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
            
        return status;
    }
        
    public static int delete(int id){
        int status = 0;
        try{
            Connection con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from users where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();
                
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
            
        return status;
    }
        
    public static user getUserById(int id){
        user e = new user();
            
        try{
            Connection con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                e.setId(rs.getInt(1));
                e.setUsername(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setRole(rs.getString(4));
            }
                
            con.close();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
            
        return e;
    }
        
    public static List<user> getAllUsers(){
        List<user> list = new ArrayList<>();
            
        try{
            Connection con = UserDao.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                user e = new user();
                e.setId(rs.getInt(1));
                e.setUsername(rs.getString(2));
                e.setPassword(rs.getString(3));
                e.setRole(rs.getString(4));
                list.add(e);
            }
            con.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
            
        return list;
    }
}
