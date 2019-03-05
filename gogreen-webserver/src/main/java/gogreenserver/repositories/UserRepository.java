package gogreenserver.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import gogreenserver.entity.User;

public interface UserRepository extends JpaRepository<User, String> {


}
