package gogreenserver.repositories;

import gogreenserver.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FriendRepository extends JpaRepository<Friend, Long> {
    List<Friend> findallByUserName(String userName);
    Friend findbyUserNameAndFriendName(String userName, String friendName);
}
