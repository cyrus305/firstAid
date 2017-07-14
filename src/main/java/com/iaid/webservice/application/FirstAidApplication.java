package com.iaid.webservice.application;

import com.iaid.webservice.configuration.FirstAidConfiguration;
import com.iaid.webservice.dao.AbstractDao;
import com.iaid.webservice.dao.BloodDonationDao;
import com.iaid.webservice.dao.IDAO;
import com.iaid.webservice.dao.UserDao;
import com.iaid.webservice.dto.Users;
import com.iaid.webservice.filter.AuthorisationFilter;
import com.iaid.webservice.health.TemplateHealthCheck;
import com.iaid.webservice.resources.BloodDonationResource;
import com.iaid.webservice.resources.UserResource;
import com.iaid.webservice.services.BloodDonationService;
import com.iaid.webservice.services.UserService;
import com.iaid.webservice.utils.BeanMapperSnakeCaseFactory;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import org.skife.jdbi.v2.DBI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Crawlers on 3/31/2016.
 */
public class FirstAidApplication extends Application<FirstAidConfiguration> {
  private final Logger logger = LoggerFactory.getLogger(FirstAidApplication.class);

  public static void main(String[] args) throws Exception {
    new FirstAidApplication().run(args);
  }

  @Override
  public String getName() {
    return "first-aid";
  }

  @Override
  public void initialize(Bootstrap<FirstAidConfiguration> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/assets/", "/"));
    bootstrap.addBundle(new ViewBundle());
  }

  @Override
  public void run(FirstAidConfiguration configuration,
                  Environment environment) {
    logger.info("running DropWizard!");

    final DBIFactory factory = new DBIFactory();
    final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
    jdbi.registerMapper(new BeanMapperSnakeCaseFactory());

    //final IDAO idao1 = jdbi.onDemand(IDAO.class);

    environment.jersey().register(new UserResource(new UserService(new UserDao(jdbi))));
    environment.jersey().register(new BloodDonationResource(new BloodDonationService(new BloodDonationDao(jdbi))));

    final TemplateHealthCheck healthCheck = new TemplateHealthCheck(configuration.getTemplate());
    environment.healthChecks().register("template", healthCheck);

    environment.jersey().setUrlPattern("/service/*");

    environment.jersey().register(new AuthorisationFilter());
    //FILTERS
    //environment.servlets().addFilter("Authorisation Filter", new AuthorisationFilter());
        /*environment.getJerseyResourceConfig()
                .getContainerRequestFilters().add(AuthorisationFilter.class);*/

    // MANAGERS /////////////////////////

        /*// Sundial
        SundialManager sm = new SundialManager(configuration.getSundialProperties()); // A DropWizard Managed Object
        environment.lifecycle().manage(sm); // Assign the management of the object to the Service

        // Yank
        YankManager ym = new YankManager(configuration.getYankConfiguration()); // A DropWizard Managed Object
        environment.lifecycle().manage(ym); // Assign the management of the object to the Service
        environment.jersey().register(new YankBookResource());

        // TASKS ////////////////////////////

        // tasks are things that should run triggered by a POST, but don't need to respond
        environment.admin().addTask(new MyJobTask());
        environment.admin().addTask(new SampleJob3Task());
        environment.admin().addTask(new LockSundialSchedulerTask());
        environment.admin().addTask(new UnlockSundialSchedulerTask());

        // RESOURCES ////////////////////////////

        environment.jersey().register(new XChartResource());
        environment.jersey().register(new ViewBookResource());
        environment.jersey().register(new ViewMarkdownResource());
        environment.jersey().register(new RandomNumberResource());*/
  }

}