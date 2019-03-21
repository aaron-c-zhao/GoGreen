package gogreenserver.services;

import gogreenserver.entity.AddSolarpanels;
import gogreenserver.repositories.AddSolarpanelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddSolarpanelsService {

    private AddSolarpanelsRepository addSolarpanelsRepo;

    @Autowired
    public AddSolarpanelsService(AddSolarpanelsRepository addSolarpanelsRepo){
        this.addSolarpanelsRepo = addSolarpanelsRepo;
    }

    public List<AddSolarpanels> findAll(){
       return addSolarpanelsRepo.findAll();
    }

    public AddSolarpanels createAddSolarpanels(AddSolarpanels addSolarpanels){
        return addSolarpanelsRepo.save(addSolarpanels);
    }

    public Optional<AddSolarpanels> findById(String userName){
        return addSolarpanelsRepo.findById(userName);
    }


}
