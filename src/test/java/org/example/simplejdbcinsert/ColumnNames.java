package org.example.simplejdbcinsert;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)

public class ColumnNames {
	@Autowired
	private DataSource dataSource;

	@Test
	public void getColumnNamesThatOnlyWorksInSpringBoot_2_0_6() throws SQLException {
		try (Connection connection = dataSource.getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();
			try (ResultSet resultSet = metaData.getColumns(null, null, "persons", null)) {
				assertTrue(resultSet.next());
				assertEquals("firstname", resultSet.getString("COLUMN_NAME"));
				assertFalse(resultSet.next());
			}
		}
	}
	@Test
	public void getColumnNamesThatWorksInSpringBoot_2_0_6And2_1_0() throws SQLException {
		try (Connection connection = dataSource.getConnection()) {
			DatabaseMetaData metaData = connection.getMetaData();
			try (ResultSet resultSet = metaData.getColumns("example1", null, "persons", null)) {
				assertTrue(resultSet.next());
				assertEquals("firstname", resultSet.getString("COLUMN_NAME"));
				assertFalse(resultSet.next());
			}
		}
	}
}
