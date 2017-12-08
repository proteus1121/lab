/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab5_design.domain;

import com.mycompany.lab5_design.exceptions.NonexistentEntityException;
import com.mycompany.lab5_design.exceptions.PreexistingEntityException;
import java.util.List;

/**
 *
 * @author Артем
 */
public interface EmployersRepository {
    public void create(Employers employers) throws PreexistingEntityException, Exception;
    public void edit(Employers employers) throws NonexistentEntityException, Exception;
    public void destroy(Integer id) throws NonexistentEntityException, Exception;
    public List <Employers> findEmployersEntities();
    public Employers findEmployers(Integer id);
}
