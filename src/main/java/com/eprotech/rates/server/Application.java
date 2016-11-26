package com.eprotech.rates.server;

import com.eprotech.rates.server.domain.Rate;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:63342")
@SpringBootApplication
@RestController
@RequestMapping(value = "/rs/fx/")
public class Application extends SpringBootServletInitializer implements ErrorController{

    private static final String ERROR_PATH = "/error";
    public static final Main MAIN = new Main();

    @RequestMapping(method = RequestMethod.GET, value = ERROR_PATH)
    public String error() {
        return "Sorry, we don't recognize your request.";
    }

    @RequestMapping(method = RequestMethod.GET, value = "hello")
    public String home() {
        return "Hello  World 1";
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    public Application(){
    }

    @PostConstruct
    private void init() {
        try {
            MAIN.start();
        } catch (Exception e) {
            System.out.println("Error - "+e.getMessage());;
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "ccyPairs")
    public List<String> getCcyPairs() throws Exception {
        return MAIN.getCcyPairs();
    }

    @RequestMapping(method = RequestMethod.GET,value = "askRate/{ccyPair}")
    public String getAskRate(@PathVariable("ccyPair") String ccyPair){
        System.out.println("getAskRate"+ccyPair);
         return MAIN.getRate(ccyPair).getAskPrice().toEngineeringString();
    }

    @RequestMapping(method = RequestMethod.GET,value = "rate/{ccyPair}")
    public Rate getRate(@PathVariable("ccyPair") String ccyPair){
        System.out.println("rate"+ccyPair);
        return MAIN.getRate(ccyPair);
    }
}
