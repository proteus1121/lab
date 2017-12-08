import com.mycompany.lab5_design.domain.Employers;
import com.mycompany.lab5_design.domain.EmployersRepository;
import com.mycompany.lab5_design.services.ApplicationServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Artem
 */
public class ApplicationServicesTest {

    private ApplicationServiceImpl service;

    @Mock
    private EmployersRepository repository;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
        List<Employers> test = new ArrayList<>();
        for (int z = 1; z < 10; z++) {
            Employers tr = new Employers();
            tr.setId(z);
            tr.setName("E_" + z);
            tr.setPosition("administrator");
            test.add(tr);
        }
        Employers tr = new Employers();
            tr.setId(1);
            tr.setName("E_" + 1);
            tr.setPosition("administrator");
            test.add(tr);
        Mockito.when(repository.findEmployersEntities()).thenReturn(test);
        service = new ApplicationServiceImpl(repository);
    }

    @Test
    public void getRepeatEntities() {
        ArrayList<Employers> rez = service.getRepeatEntities();
        Assert.assertEquals(rez.size(), 1);
    }
    
    @Test
    public void getRepeatEntitiesTimes() {
        ArrayList<Employers> rez = service.getRepeatEntities();
        Mockito.times(2);
    }
    
    @Test
    public void getRepeatEntitiesVerifity() {
        ArrayList<Employers> rez = service.getRepeatEntities();
        Mockito.verify(repository).findEmployersEntities();
    }
    
    @Test
    public void add3ToETest() throws Exception {
        service.add3ToE();
        Mockito.verify(repository).findEmployersEntities();
    }  
    
   

    @Test
    public void add3ToETimes() {
        service.add3ToE();
        Mockito.times(10);
    }
}
