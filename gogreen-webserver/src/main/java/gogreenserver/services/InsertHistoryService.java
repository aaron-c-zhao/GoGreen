package gogreenserver.services;

import gogreenserver.entity.InsertHistory;
import gogreenserver.entity.InsertHistoryCo2;
import gogreenserver.repositories.InsertHistoryCo2Repository;
import gogreenserver.repositories.InsertHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InsertHistoryService {

    private InsertHistoryRepository inputRepo;
    private InsertHistoryCo2Repository outputRepo;

    @Autowired
    public InsertHistoryService(InsertHistoryRepository insertHistoryRepo,
            InsertHistoryCo2Repository outputrepo) {
        this.inputRepo = insertHistoryRepo;
        this.outputRepo = outputrepo;
    }

    public InsertHistory createInsertHistory(InsertHistory insertHistory) {
        return inputRepo.save(insertHistory);
    }

    public List<InsertHistoryCo2> findAll() {
        return outputRepo.findAll();
    }

    /**
     * find the most recent two insert history of that user.
     * 
     * @param username user name.
     * @return a list of insert history, the list can be empty.
     */
    public List<InsertHistoryCo2> findRecentByUsername(String username, int limit) {
        limit = limit == -1 ? Integer.MAX_VALUE : limit; //-1 for all entries.
        return outputRepo.findByUserName(username).stream()
                .sorted(Comparator.comparing(InsertHistoryCo2::getInsertDate).reversed())
                .limit(limit).collect(Collectors.toList());
    }

    /**
     * The all the insert history co2 of that user.
     * 
     * @param username users name.
     * @return a list of insert history.
     */
    public List<InsertHistoryCo2> findAllByUserName(String username) {
        return outputRepo.findByUserName(username);
    }
}