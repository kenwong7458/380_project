package ouhk.comps380f.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ouhk.comps380f.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

