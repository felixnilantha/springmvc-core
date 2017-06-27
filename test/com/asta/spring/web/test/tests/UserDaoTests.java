package com.asta.spring.web.test.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.asta.spring.web.dao.User;
import com.asta.spring.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations = {
	"classpath:com/asta/spring/web/config/dao-context.xml",
	"classpath:com/asta/spring/web/config/security-context.xml",
	"classpath:com/asta/spring/web/test/config/datasource.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private DataSource dataSource;

    @Before
    public void init() {

	JdbcTemplate jdbc = new JdbcTemplate(dataSource);
	jdbc.execute("delete from offers");
	jdbc.execute("delete from users");
    }

    @Test
    public void testCreateUser() {

	User user = new User("tom", "Tom Banaby", "midsomer2017",
		"tom@midsomer.com.uk", true, "ROLE_USER");

	assertTrue("User creation should be true", usersDao.create(user));

	List<User> users = usersDao.getAllUsers();

	assertEquals("Number of users should be 1", 1, users.size());

	assertTrue("User should exisit", usersDao.exists(user.getUsername()));

	assertEquals("Created user and retrived user should be identicle",
		user, users.get(0));

    }
}
