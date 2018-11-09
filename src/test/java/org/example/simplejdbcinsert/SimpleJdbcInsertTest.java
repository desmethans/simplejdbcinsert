package org.example.simplejdbcinsert;

import java.util.Collections;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@JdbcTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class SimpleJdbcInsertTest {
	@Autowired
	private DataSource dataSource;
	private SimpleJdbcInsert insert;

	@Before
	public void before() {
		insert = new SimpleJdbcInsert(dataSource);
	}

	@Test
	public void insertPerson() {
		insert.withTableName("persons");
		insert.execute(Collections.singletonMap("firstname", "joe"));
	}
}
