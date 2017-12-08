import com.mycompany.lab5_design.domain.Employers;
import com.mycompany.lab5_design.domain.EmployersRepository;
import com.mycompany.lab5_design.exceptions.NonexistentEntityException;
import com.mycompany.lab5_design.services.ApplicationService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;

public class AppIntegrationTest
{
  private EmployersRepository service;
  ApplicationService serviceApp;

  @Before
  public void init()
  {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    service = context.getBean(EmployersRepository.class);
    serviceApp = context.getBean(ApplicationService.class);

    cleanDB();
    for (int z = 1; z < 10; z++) {
      Employers tr = new Employers();
      tr.setName("E_" + z);
      tr.setPosition("administrator");
      try {
        service.create(tr);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
      Employers employers = new Employers();
      employers.setName("E_" + 1);
      employers.setPosition("administrator");
      try
      {
        service.create(employers);
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }

  }

  public void cleanDB()
  {
    service.findEmployersEntities().forEach(o ->
    {
      try
      {
        service.destroy(((Employers) o).getId());
      }
      catch (NonexistentEntityException e)
      {
        e.printStackTrace();
      } catch (Exception e) {
        e.printStackTrace();
      }
    });
  }

  @Test
  public void getRepeatEntities()
  {
    ArrayList<Employers> rez = serviceApp.getRepeatEntities();
    Assert.assertEquals(rez.size(), 2);
  }

  @Test
  public void getRepeatEntitiesTimes()
  {
    ArrayList<Employers> rez = serviceApp.getRepeatEntities();
    Mockito.times(11);
  }

  @Test
  public void add3ToETimes()
  {
    serviceApp.add3ToE();
    Mockito.times(10);
  }
}
