package younus.attari;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.Logger;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public interface MyDataSourceObject {

	Logger LOG = Logger.getLogger(MyDataSourceObject.class);
	Properties prop = new Properties();
	String pathname = System.getProperty("user.dir") + "/src/main/resources/db.properties";

	public static DataSource getMySQLDataSource() {
		MysqlDataSource mySQLDS = null;
		LOG.info("this is from getMySQLDataSource() " );

		try (FileInputStream fis = new FileInputStream(new File(pathname))) {
			prop.load(fis);
			LOG.info("loading properties file form path " + pathname);
			mySQLDS = new MysqlDataSource();
			mySQLDS.setUrl(prop.getProperty("url"));
			mySQLDS.setUser(prop.getProperty("username"));
			mySQLDS.setPassword(prop.getProperty("password"));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return mySQLDS;
	}
}
