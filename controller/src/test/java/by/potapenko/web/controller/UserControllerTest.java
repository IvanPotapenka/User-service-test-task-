//package by.potapenko.web.controller;
//
//import by.potapenko.model.dto.UserDto;
//import by.potapenko.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.List;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@AutoConfigureMockMvc
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @MockBean
//    private UserService userService;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void getAllUsers() throws Exception {
//        when(userService.getAll()).thenReturn(List.of(kiraUser, ivanUser));
//        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders.get("/api/users"));
//        perform.andExpect(status().isOk())
//                .andExpect(jsonPath("$[0].email").value("kira@mail.ru"))
//                .andExpect(jsonPath("$[1].email").value("ivan@mail.ru"));
//        ;
//    }
//
//    private UserDto ivanUser = UserDto.builder()
//            .email("ivan@mail.ru")
//            .build();
//    private UserDto kiraUser = UserDto.builder()
//            .email("kira@mail.ru")
//            .build();
//}