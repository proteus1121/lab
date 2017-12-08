/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.lab5_design.presentetion;

import com.mycompany.lab5_design.domain.Employers;
import com.mycompany.lab5_design.domain.EmployersRepositoryImpl;
import com.mycompany.lab5_design.exceptions.NonexistentEntityException;
import com.mycompany.lab5_design.services.ApplicationService;
import com.mycompany.lab5_design.services.ApplicationServiceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.mycompany.lab5_design.domain.EmployersRepository;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author Артем
 */
public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {

        Logger logger = (Logger) LoggerFactory.getLogger(Main.class);
        logger.info("Starting lab 2 application");

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        EmployersRepository service = context.getBean(EmployersRepository.class);


        logger.info("Create 10 new Employers...");
        for (int z = 0; z < 20; z++) {
            try {
                Employers tr = new Employers();
                tr.setName("A" + z);
                tr.setPosition("administrator");
                service.create(tr);
            } catch (Exception ex) {
                logger.error(ex.getMessage());
            }
        }
        logger.info("Get Repeat Employers...");
        ApplicationService serviceApp = context.getBean(ApplicationService.class);

        ArrayList<Employers> rez2 = serviceApp.getRepeatEntities();
        for (Employers tranzactions : rez2) {
            System.err.println(tranzactions.getName());
        }

        serviceApp.add3ToE();

        List<Employers> rez4 = service.findEmployersEntities();
        logger.info("Get all Employers...");
        rez4.forEach(o -> System.err.println(((Employers) o).getName()));

        rez4.forEach(o -> {
            try {
                service.destroy(((Employers) o).getId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        List<Employers> rez5 = service.findEmployersEntities();

        logger.info("Closing lab 5 application");
        System.exit(0);
    }
}

