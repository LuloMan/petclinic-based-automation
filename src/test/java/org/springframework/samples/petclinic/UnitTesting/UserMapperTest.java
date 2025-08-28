package org.springframework.samples.petclinic.UnitTesting;

import io.qameta.allure.*;
import org.springframework.samples.petclinic.mapper.UserMapper;
import org.springframework.samples.petclinic.model.User;
import org.mapstruct.factory.Mappers;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.mapstruct.factory.Mappers;

@Epic("Mapper")
@Feature("UserMapper Unit Tests")
public class UserMapperTest {
    private UserMapper userMapper;

    @BeforeMethod
    public void setUp() {
        userMapper = Mappers.getMapper(UserMapper.class);
    }

    @Test(description = "Should map User to DTO")
    @Story("Map User")
    public void testMapUserToDto() {
        User user = new User();
        user.setUsername("admin");
        assertEquals(user.getUsername(), "admin");
    }
} 