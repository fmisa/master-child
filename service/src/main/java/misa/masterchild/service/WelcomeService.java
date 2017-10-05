package misa.masterchild.service;


import org.springframework.stereotype.Component;

@Component
public class WelcomeService {

    public String retrieveWelcomeMessage() {
        //Complex Method
        return "HelloWorld...";
    }
}