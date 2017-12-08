/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab5_design.domain;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.Serializable;
import java.util.List;

/**
 * @author Артем
 */
@Repository
@Transactional
public class EmployersRepositoryImpl implements Serializable, EmployersRepository {

    private EntityManager em;

    org.slf4j.Logger logger = (org.slf4j.Logger) LoggerFactory.getLogger(EmployersRepositoryImpl.class);

    public EmployersRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void create(Employers employers) throws Exception {
        try {
            logger.trace("Creating Employer id=" + employers.getName());
            em.persist(employers);
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void edit(Employers employers) throws Exception {
        logger.trace("Edit Employers id=" + employers.getId());
        try {
            logger.trace("Editing Employers id=" + employers.getId());
            em.merge(employers);
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public void destroy(Integer id) throws Exception {
        try {
            logger.trace("Remove Employers id=" + id);
            Employers employers = (Employers) em.find(Employers.class, id);
            em.remove(employers);
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public List<Employers> findEmployersEntities() {
        try {
            logger.trace("Getting all Employers");
            Query query = em.createNamedQuery("Employers.findAll", Employers.class);
            return query.getResultList();
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }

    @Override
    public Employers findEmployers(Integer id) {
        try {
            Employers employers = (Employers) em.find(Employers.class, id);
            logger.debug("Geting " + employers.getName() + " Employer...");
            return (Employers) em.find(Employers.class, id);
        } catch (RuntimeException e) {
            em.getTransaction().rollback();
            throw e;
        }
    }
}
