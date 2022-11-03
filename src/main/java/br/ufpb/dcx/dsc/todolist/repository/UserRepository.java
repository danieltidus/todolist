package br.ufpb.dcx.dsc.todolist.repository;

import br.ufpb.dcx.dsc.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
