package com.usa.ciclo3.retociclo3.service;

import com.usa.ciclo3.retociclo3.model.Computer;
import com.usa.ciclo3.retociclo3.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    public List<Computer> getAll(){
        return computerRepository.getAll();
    }

    public Optional<Computer> getComputer(int id){
        return computerRepository.getComputer(id);
    }

    public Computer save(Computer computer){
      if(computer.getId()==null){
         return computerRepository.save(computer);
      }else{
          Optional<Computer> tmpComputer = computerRepository.getComputer(computer.getId());
          if(tmpComputer.isEmpty()) {
              return computerRepository.save(computer);
          }else{
              return computer;
          }
      }
    }

    public Computer update(Computer computer){
        if(computer.getId()!=null){
            Optional<Computer> e= computerRepository.getComputer(computer.getId());
            if(!e.isEmpty()){
                if(computer.getName()!=null){
                    e.get().setName(computer.getName());
                }
                if(computer.getBrand()!=null){
                    e.get().setBrand(computer.getBrand()); ;
                }
                if(computer.getComputers() !=null){
                    e.get().setComputers(computer.getComputers());
                }
                if(computer.getDescription()!=null){
                    e.get().setDescription(computer.getDescription());
                }
                if(computer.getCategory()!=null){
                    e.get().setCategory(computer.getCategory());
                }
                computerRepository.save(e.get());
                return e.get();
            }else{
                return computer;
            }
        }else{
            return computer;
        }
    }

    public boolean deleteComputer(int id){
        Boolean aBoolean = getComputer(id).map(computer -> {
            computerRepository.delete(computer);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
