import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

public class Main {

    public static void main(String[] args) {

        Connection conn = null;

        try {

            String dbURL = "jdbc:sqlserver://10.0.1.15:1433;instanceName=MSSQLSERVER;databaseName=babajob";
            String user = "bjserver2008";
            String pass = "BjS3rver2oo8";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {

                Statement sta = conn.createStatement();
              //  Uncomment the below line and comment out next line to download below 12000 images.
              // String Sql = "select top 20000 case When u.salary < u.ExpectedSalary Then u.salary Else u.ExpectedSalary End As MinSalary, u.salary,u.ExpectedSalary,u.jobCategory, u.userid, u.pictureId,p.Image from UserInfo u with (NOLOCK)  join Picture p on u.userId = p.UserId where (u.salary between 1 and 12000 or u.ExpectedSalary between 1 and 12000) and u.salary>1000 and u.ExpectedSalary>1000 and u.pictureId != 15 and p.Image IS NOT NULL order by u.userId desc";
                String Sql = "select top 20000 case When u.salary < u.ExpectedSalary Then u.salary Else u.ExpectedSalary End As MinSalary, u.salary,u.ExpectedSalary,u.userid, u.pictureId,p.Image from UserInfo u with (NOLOCK)  join Picture p on u.userId = p.UserId where u.salary>12000 and u.ExpectedSalary>12000 and u.pictureId!=15 and p.image IS NOT NULL order by u.userId desc";
                ResultSet rs = sta.executeQuery(Sql);

                while (rs.next()) {
                    InputStream in = new ByteArrayInputStream(rs.getBytes("Image"));
                    BufferedImage bImageFromConvert = ImageIO.read(in);
                    try {
                        //Will need to have the folder tf_files/flower_photos/above_12000/ in the system to download the files.
                        //In case of below_12000 the folder should be tf_files/flower_photos/below_12000/                       
                        ImageIO.write(bImageFromConvert, "jpg", new File("tf_files/flower_photos/above_12000/Salary_" + rs.getString("MinSalary") + "_UserId_" +rs.getString("UserId")+"_PictureId_" +rs.getString("pictureId")+ ".jpg"));

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
               }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

