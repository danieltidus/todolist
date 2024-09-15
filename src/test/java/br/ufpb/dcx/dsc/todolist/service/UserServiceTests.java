package br.ufpb.dcx.dsc.todolist.service;

import br.ufpb.dcx.dsc.todolist.exception.ItemNotFoundException;
import br.ufpb.dcx.dsc.todolist.model.Board;
import br.ufpb.dcx.dsc.todolist.model.Photo;
import br.ufpb.dcx.dsc.todolist.model.User;
import br.ufpb.dcx.dsc.todolist.repository.BoardRepository;
import br.ufpb.dcx.dsc.todolist.repository.PhotoRepository;
import br.ufpb.dcx.dsc.todolist.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PhotoRepository photoRepository;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;
    private User sharedUser;
    private Board board;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setNome("Test User");
        user.setEmail("test@example.com");
        user.setUsername("testuser");
        user.setPassword("password");

        sharedUser = new User();
        sharedUser.setId(2L);
        sharedUser.setNome("Shared User");
        sharedUser.setEmail("shared@example.com");
        sharedUser.setUsername("testshareduser");
        sharedUser.setPassword("passwordshared");

        board = new Board();
        board.setId(1L);
        board.setNome("Test Board");
        board.setUser(user);
        board.setUsers(new ArrayList<>(Arrays.asList(sharedUser)));

        user.setBoards(new ArrayList<>(Arrays.asList(board)));
        user.setBoardsShared(new ArrayList<>(Arrays.asList(board)));

        sharedUser.setBoards(new ArrayList<>());
        sharedUser.setBoardsShared(new ArrayList<>(Arrays.asList(board)));
    }

    @Test
    void testListUsers() {
        when(userRepository.findAll()).thenReturn(Arrays.asList(user));
        assertEquals(1, userService.listUsers().size());
    }

    @Test
    void testGetUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(user, userService.getUser(1L));
    }

    @Test
    void testGetUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ItemNotFoundException.class, () -> userService.getUser(1L));
    }

    @Test
    void testCreateUser() {
        when(bCryptPasswordEncoder.encode(any())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(photoRepository.save(any(Photo.class))).thenReturn(new Photo("www.exemplo.com/foto.png"));

        User createdUser = userService.createUser(user);
        assertNotNull(createdUser);
        assertEquals("encodedPassword", createdUser.getPassword());
    }

    @Test
    void testUpdateUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updatedUser = userService.updateUser(1L, user);
        assertNotNull(updatedUser);
        assertEquals("Test User", updatedUser.getNome());
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(any(User.class));
        userService.deleteUser(1L);
        verify(userRepository, times(1)).delete(user);
    }

    @Test
    void testShare() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User sharedUser = userService.share(1L, 1L);
        assertNotNull(sharedUser);
        assertTrue(sharedUser.getBoardsShared().contains(board));
    }

    @Test
    void testUnshare() {
        board.setUsers(new ArrayList<>(Arrays.asList(user)));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(boardRepository.findById(1L)).thenReturn(Optional.of(board));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User unsharedUser = userService.unshare(1L, 1L);
        assertNotNull(unsharedUser);
        assertFalse(unsharedUser.getBoardsShared().contains(board));
    }
}