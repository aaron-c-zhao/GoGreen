package gogreenserver.services;

import gogreenserver.entity.InsertHistoryCo2;
import gogreenserver.repositories.InsertHistoryCo2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsertHistoryCo2Service {
    private InsertHistoryCo2Repository insertHistoryCo2Repository;

    @Autowired
    public InsertHistoryCo2Service(InsertHistoryCo2Repository insertHistoryCo2Repository) {
        this.insertHistoryCo2Repository = insertHistoryCo2Repository;
    }

    public List<InsertHistoryCo2> findAll() {
        return insertHistoryCo2Repository.findAll();
    }

    /**
     * find the most recent two insert history of that user.
     * @param userName user name.
     * @return a list of insert history, the list can be empty.
     */
    public List<InsertHistoryCo2> findRecentTwoByUserName(String userName) {
        List<InsertHistoryCo2> list = findAll()
            .stream()
            .filter(InsertHistoryCo2 -> InsertHistoryCo2.getUserName().equals(userName))
            .sorted(Comparator.comparing(InsertHistoryCo2::getInsertDate).reversed())
            .limit(2)
            .collect(Collectors.toList());
        return list;
    }

    /**
     * The all the insert history co2 of that user.
     * @param userName usr name.
     * @return a list of insert history.
     */
    public List<InsertHistoryCo2> findAllByUserName(String userName) {
        List<InsertHistoryCo2> list = findAll()
            .stream()
            .filter(InsertHistoryCo2 -> InsertHistoryCo2.getUserName().equals(userName))
            .collect(Collectors.toList());
        return list;
    }

}
