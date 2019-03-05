package gogreenserver.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import gogreenserver.entity.UserCareer;

public interface UserCareerRepository extends JpaRepository<UserCareer, String> {

}
