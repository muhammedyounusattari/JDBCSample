package younus.attari;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class Demo {

	public static final Logger LOG = Logger.getLogger(Demo.class);

	public static void main(String[] args) {
		LOG.info("inside main method..");

		DataSource ds = MyDataSourceObject.getMySQLDataSource();
		String sql = "select *from employee where empId=?";

		ResultSet rs = null;

		try (Connection con = ds.getConnection(); PreparedStatement pst = con.prepareStatement(sql);) {

			pst.setString(1, "1024");
			rs = pst.executeQuery();
			while (rs.next()) {
				LOG.info(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t");
			}
		} catch (SQLException e) {
			LOG.error("borken pipe need to be fixed, exception occured " + e.getMessage());
		}

	}
}
