package gogreenserver.repositories;

import gogreenserver.entity.Tree;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreeRepository extends JpaRepository<Tree, String> {
}
