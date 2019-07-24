package murraco.repository;

import murraco.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepistory extends JpaRepository<Role, Integer> {

    List<Role> findByRoleName(String admin);
}
