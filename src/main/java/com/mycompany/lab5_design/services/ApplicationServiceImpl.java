/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab5_design.services;

import com.mycompany.lab5_design.domain.Employers;
import com.mycompany.lab5_design.domain.EmployersRepositoryImpl;
import com.mycompany.lab5_design.exceptions.NonexistentEntityException;
import com.mycompany.lab5_design.presentetion.Main;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mycompany.lab5_design.domain.EmployersRepository;

/**
 *
 * @author Артем
 */
@Service
public class ApplicationServiceImpl implements ApplicationService{
    @Autowired
    EmployersRepository repository;
    org.slf4j.Logger logger = (org.slf4j.Logger) LoggerFactory.getLogger(ApplicationServiceImpl.class);
    public ApplicationServiceImpl(EmployersRepository repository)
    {
        this.repository = repository;
    }

    @Override
    public ArrayList<Employers> getRepeatEntities() {
        List<Employers> list = repository.findEmployersEntities();
        Set<Employers> finaleList = new HashSet<>();
       for(int i = 0;i< list.size();i++){
          for(int y = 0;y< list.size();y++){
              if(list.get(i).getName().equals(list.get(y).getName())&&i!=y&&!finaleList.contains(list.get(i))){
                  finaleList.add(list.get(i));
                  logger.info("Found reapeat "+list.get(i).getId()+" Employer");
              }
          }
       }
       return new ArrayList<>(finaleList);
    }

    @Override
    public void add3ToE() {
        List<Employers> list = repository.findEmployersEntities();
       for(Employers employers : list){
           if(employers.getName().charAt(0)=='E'){
               try {
                   employers.setName(employers.getName()+"_3");
                   logger.info("Add _3 to "+employers.getId()+" Employers");
                   repository.edit(employers);
               } catch (Exception ex) {
                   Logger.getLogger(ApplicationServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
       }
    }
}
