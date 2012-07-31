Webapp Healthcheck
==================

A easy pluggable Java monitor/healthcheck for your webapp.

Ever been in the situation where you would like to run **diagnostics** on your webapp? Or see some of the **properties** the application uses? Maybe even run the diagnostics **scheduled** and receive **alerts** when something is not how it is supposed to be?

Inspired by [Knut Haugen](https://github.com/knuthaug)s [presentation at Roots 2011](https://vimeo.com/24691568) and corresponding [Ruby Smoketest blogpost](http://blog.knuthaugen.no/2011/04/continuous-delivery-ii-smoketests-in-ruby-and-rails.html) I decided to try to implement it in Java. Never wanting to do the same work all over again at another project I decided to polish it share it on github. Later I see Knut Haugen also has made a [Java example](https://github.com/knuthaug/smoketest-starter-kit), go check it out!

Anyways, this is a light jar-file which you can plug into any webapp project you would like.

# Usage
If you can read code check out the [example](https://github.com/judoole/webapp-healthcheck/tree/master/example) project.

## Spring
For Spring it's as easy as to include a bean of class `no.inspirado.healthcheck.web.HealthcheckController`.
The junit report will now be accessible from `.../your-spring-dispatcher-mapping/healtcheck/xml` and as html on `.../your-spring-dispatcher-mapping/healtcheck/html`

But to make it show something you need to add some healthchecks. Create beans that extends `no.inspirado.healthcheck.internal.HealthcheckCaseRunner` like  [HealthcheckThatIsSuccess](https://github.com/judoole/webapp-healthcheck/blob/master/example/src/main/java/no/inspirado/healthcheck/cases/HealthcheckThatIsSuccess.java) and be sure to **return null if success**

If you'd like to see some properties the application is set up with also, just throw in some beans of `no.inspirado.healthcheck.internal.dto.HealthcheckProperty` like 

````java
@Bean
public HealthcheckProperty mySimpleProperty() {
    return new HealthcheckProperty("My Simple property", "My simple value");
}
````

That's it. You're good to go.