package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.ClassroomUser;

public interface UserRepository extends JpaRepository<ClassroomUser, String> {
    
}
