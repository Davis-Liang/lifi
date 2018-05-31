package dao.test;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nike.lifi.dao.TestDao;
import com.nike.lifi.entity.TestBean;

@RunWith(SpringJUnit4ClassRunner.class) // 指定测试用例的运行器 这里是指定了Junit4
@ContextConfiguration("classpath:spring-context.xml")
public class UserDaoTest {
	@Autowired
	private TestDao testDao;

	@Test
	public void TestBeanDao() {
		int i = testDao.insert();

		TestBean testBean = testDao.getById(1);

		List<TestBean> list = testDao.list();
		// assertEquals(0, list.size());
	}
}
