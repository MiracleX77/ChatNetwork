package ChatNetwork.ChatNetwork;

import ChatNetwork.ChatNetwork.entity.User;
import ChatNetwork.ChatNetwork.exception.BaseException;
import ChatNetwork.ChatNetwork.repository.UserRepository;
import ChatNetwork.ChatNetwork.service.ChatService;
import ChatNetwork.ChatNetwork.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class Test {
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;
    @Autowired
    private UserRepository userRepository;

    @Order(1)
    @org.junit.jupiter.api.Test
    void testCreate1() throws BaseException {
        User user = userService.create(
                TestData1.email,
                TestData1.password,
                TestData1.name
        );
        //check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        //check equals
        Assertions.assertEquals(TestData1.email,user.getEmail());
        Assertions.assertEquals(TestData1.password,user.getPassword());
        Assertions.assertEquals(TestData1.name,user.getName());
    }
    @Order(2)
    @org.junit.jupiter.api.Test
    void testCreate2() throws BaseException {
        User user = userService.create(
                TestData2.email,
                TestData2.password,
                TestData2.name
        );
        //check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        //check equals
        Assertions.assertEquals(TestData2.email,user.getEmail());
        Assertions.assertEquals(TestData2.password,user.getPassword());
        Assertions.assertEquals(TestData2.name,user.getName());
    }
    @Order(3)
    @org.junit.jupiter.api.Test
    void testCreate3() throws BaseException {
        User user = userService.create(
                TestData3.email,
                TestData3.password,
                TestData3.name
        );
        //check not null
        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        //check equals
        Assertions.assertEquals(TestData3.email,user.getEmail());
        Assertions.assertEquals(TestData3.password,user.getPassword());
        Assertions.assertEquals(TestData3.name,user.getName());
    }
    @Order(4)
    @org.junit.jupiter.api.Test
    void testCreateRoom() throws BaseException {
        Optional<User>  user = userRepository.findByEmail(TestData1.email);
        String s = chatService.createRoom(user.get().getId(),TestData2.name);
        Assertions.assertNotNull(user.get().getRooms());
        //check not null
        //check equals
        Assertions.assertNotNull(s);


    }
    @Order(5)
    @org.junit.jupiter.api.Test
    void testCreateRoom2() throws BaseException {
        Optional<User>  user = userRepository.findByEmail(TestData1.email);
        Assertions.assertTrue(user.isPresent());
        String a = chatService.createRoom(user.get().getId(),TestData3.name);
        //check not null
        //check equals
        Assertions.assertNotNull(a);

    }


//    @Order(8)
//    @org.junit.jupiter.api.Test
//    void testDelete1() {
//        Optional<User> opt = userService.findByEmail(TestData1.email);
//        Assertions.assertTrue(opt.isPresent());
//        User user = opt.get();
//
//        userService.deleteById(user.getId());
//
//        Optional<User> optDelete = userService.findByEmail(TestData1.email);
//        Assertions.assertTrue(optDelete.isEmpty());
//
//    }
//    @Order(9)
//    @org.junit.jupiter.api.Test
//    void testDelete2() {
//        Optional<User> opt = userService.findByEmail(TestData2.email);
//        Assertions.assertTrue(opt.isPresent());
//
//        User user = opt.get();
//        userRepository.deleteAll();
//
//        Optional<User> optDelete = userService.findByEmail(TestData2.email);
//        Assertions.assertTrue(optDelete.isEmpty());
//
//
//
//    }




    interface TestData1 {
        String email = "test1";
        String password = "1";
        String name = "test1";
    }
    interface TestData2 {
        String email = "test2";
        String password = "1";
        String name = "test2";
    }
    interface TestData3 {
        String email = "test3";
        String password = "1";
        String name = "test3";
    }

}
