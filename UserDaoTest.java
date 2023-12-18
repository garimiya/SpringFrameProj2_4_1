package springbook.user.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Assertions;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import springbook.user.domain.User;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoFactory.class})
public class UserDaoTest {
//   @Autowired 
//   ApplicationContext context;
   
   @Autowired// 어노테이션 기반의 구성 정보
   private UserDao dao; 
   
   private User user1;
   private User user2;
   private User user3;
   
   @BeforeEach
   public void setUp() {
//      this.dao = this.context.getBean("userDao", UserDao.class);
      
      user1 = new User("user1", "gadonge", "1120");
      user2 = new User("user2", "sundonge", "0521");
      user3 = new User("user3", "sarangdoong", "0324");

   }
   
   @Test 
   public void andAndGet() throws SQLException {      
      dao.deleteAll();
      assertEquals(dao.getCount(), 0);

      dao.add(user1);
      dao.add(user2);
      assertEquals(dao.getCount(), 2);
      
      User userget1 = dao.get(user1.getId());
      assertEquals(userget1.getName(), user1.getName());
      assertEquals(userget1.getPassword(), user1.getPassword());
      
      User userget2 = dao.get(user2.getId());
      assertEquals(userget2.getName(), user2.getName());
      assertEquals(userget2.getPassword(), user2.getPassword());
   }
   
   @Test
   public void count() throws SQLException {
      dao.deleteAll();
      assertEquals(dao.getCount(), 0);
            
      dao.add(user1);
      assertEquals(dao.getCount(), 1);
      
      dao.add(user2);
      assertEquals(dao.getCount(), 2);
      
      dao.add(user3);
      assertEquals(dao.getCount(), 3);
   }
   
   @Test
   public void getUserFailure() throws SQLException, ClassNotFoundException{
      dao.deleteAll();
      assertEquals(dao.getCount(), 0);
      
      Assertions.assertThrows(EmptyResultDataAccessException.class,
            () -> {dao.get("unknown_id");});
   }
}