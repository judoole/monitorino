[![Build Status](https://secure.travis-ci.org/judoole/monitorino.png)](http://travis-ci.org/judoole/monitorino)

Webapp Monitorino
==================

An easy pluggable Java monitor/healthcheck for your webapp.

Ever been in the situation where you would like to run **diagnostics** on your webapp? Or see some of the **properties** the application uses? Maybe even run the diagnostics **scheduled** and receive **alerts** when something is not how it is supposed to be?

Inspired by [Knut Haugen](https://github.com/knuthaug)s [presentation at Roots 2011](https://vimeo.com/24691568) and corresponding [Ruby Smoketest blogpost](http://blog.knuthaugen.no/2011/04/continuous-delivery-ii-smoketests-in-ruby-and-rails.html) I decided to try to implement it in Java. Never wanting to do the same work all over again at another project I decided to polish it share it on github. Later I see Knut Haugen also has made a [Java example](https://github.com/knuthaug/smoketest-starter-kit), go check it out!

Anyways, this is a light jar-file which you can plug into any webapp project you would like.

# Usage
If you like reading code check out the [example](https://github.com/judoole/monitorino/tree/master/example) project. Simplest way to get the example started is running [start-example-webapp.sh](https://github.com/judoole/monitorino/blob/master/start-example-webapp.sh) and checking the result at [http://localhost:8090/spring/monitor/](http://localhost:8090/spring/monitor/) or [http://localhost:8090/spring/monitor/junit](http://localhost:8090/spring/monitor/junit)

## Spring
For Spring it's as easy as to include a bean of class `com.github.judoole.monitorino.web.MonitorinoController`.
The junit report will now be accessible from `.../your-spring-dispatcher-mapping/healtcheck/xml` and as html on `.../your-spring-dispatcher-mapping/healtcheck/html`

But to make it show something you need to add some healthchecks. Create beans that extends `com.github.judoole.monitorino.internal.MonitorinoRunner` like  [EverythingIsOk](https://github.com/judoole/monitorino/blob/master/example/src/main/java/com/github/judoole/monitorino/cases/EverythingIsOk.java) and be sure to **return null if success**

If you'd like to see some properties add them to your Controller, perhaps like this from the example apps [SpringApplicationContext](https://github.com/judoole/monitorino/blob/master/example/src/main/java/com/github/judoole/monitorino/SpringApplicationContext.java)

````java
@Bean
public MonitorinoController monitorinoController() throws IOException {
    MonitorinoController controller = new MonitorinoController();
    controller.setProperties(mavenBuildProperties());
    return controller;
}

@Bean
public Properties mavenBuildProperties() throws IOException {
    PropertiesFactoryBean factory = new PropertiesFactoryBean();
    factory.setLocation(new ClassPathResource("mavenbuild.properties"));
    factory.afterPropertiesSet();
    return factory.getObject();
}
````

That's it. You're good to go.

Test example by git cloning/forking and run [start-example-webapp.sh](https://github.com/judoole/monitorino/blob/master/start-example-webapp.sh).
Results for html and junit should be accessable from [http://localhost:8090/spring/monitor](http://localhost:8090/spring/monitor)

## Jenkins
The real power lies in getting Jenkins to give you quick feedback. For setup look at Knut Haugens [Ruby Smoketest blogpost](http://blog.knuthaugen.no/2011/04/continuous-delivery-ii-smoketests-in-ruby-and-rails.html)

## HttpServlet
TODO

## What else?

Go see the [Project Documentation](http://judoole.github.com/monitorino/site/)

The project has a Procfile and the example is reguarly deployed to Heroku on [http://gentle-dawn-7825.herokuapp.com/](http://gentle-dawn-7825.herokuapp.com/)