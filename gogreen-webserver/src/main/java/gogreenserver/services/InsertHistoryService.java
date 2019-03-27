package gogreenserver.services;

import gogreenserver.entity.InsertHistory;
import gogreenserver.repositories.InsertHistoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InsertHistoryService {

    private InsertHistoryRepository insertHistoryRepo;

    @Autowired
    public InsertHistoryService(InsertHistoryRepository insertHistoryRepo) {
        this.insertHistoryRepo = insertHistoryRepo;
    }

    // have to make method to findAllById

    public List<InsertHistory> findAll() {
        return insertHistoryRepo.findAll();
    }

    public Optional<InsertHistory> findById(String userName) {
        return insertHistoryRepo.findById(userName);
    }

    public List<InsertHistory> findAllByUsername(String userName) {
        return this.insertHistoryRepo.findByUserName(userName);
    }


    public InsertHistory createInsertHistory(InsertHistory insertHistory) {
        return insertHistoryRepo.save(insertHistory);
    }
}