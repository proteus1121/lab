/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.mycompany.lab5_design.domain.EmployersRepository;
import com.mycompany.lab5_design.domain.EmployersRepositoryImpl;
import com.mycompany.lab5_design.services.ApplicationService;
import com.mycompany.lab5_design.services.ApplicationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

/**
 * @author Артем
 */
@Configuration
@EnableTransactionManagement()
class AppConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean
    public EmployersRepository tranzactionsRepository() {
        return new EmployersRepositoryImpl(em);
    }

    @Bean
    public ApplicationService applicationServiceImpl() {
        return new ApplicationServiceImpl(tranzactionsRepository());
    }

    @Bean(name = "manager")
    public EntityManager getEntityManager() {
        return getEntityManagerFactory().createEntityManager();
    }

    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        return Persistence.createEntityManagerFactory("test");
    }

    @Bean
    public PlatformTransactionManager userTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactory());
        return transactionManager;
    }

}
