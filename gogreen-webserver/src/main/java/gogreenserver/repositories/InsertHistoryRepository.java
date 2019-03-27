package gogreenserver.repositories;

import gogreenserver.entity.InsertHistory;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InsertHistoryRepository extends JpaRepository<InsertHistory, String> {

    /**
     * Finds all users by username.<br>
     * <b>DO NOT CHANGE THE METHOD NAME</b><br>
     * Spring user this method name to construct the corresponding query.
     * Make a single mistake and the whole program crashes.
     */
    List<InsertHistory> findByUserName(String username);
}
