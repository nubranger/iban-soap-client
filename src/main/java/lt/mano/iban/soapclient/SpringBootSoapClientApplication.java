package lt.mano.iban.soapclient;

import lt.mano.iban.schemas.GetIbanCheckRequest;
import lt.mano.iban.schemas.GetIbanCheckResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;


@SpringBootApplication
public class SpringBootSoapClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSoapClientApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(SOAPConnector soapConnector) {
        return args -> {
            while (true) {
                System.out.println("Enter IBAN:");
                Scanner scanner = new Scanner(System.in);
                String iban = scanner.nextLine();

                if (args.length > 0) {
                    iban = args[0];
                }
                GetIbanCheckRequest request = new GetIbanCheckRequest();
                request.setNumberIBAN(iban);
                GetIbanCheckResponse response = (GetIbanCheckResponse) soapConnector.callWebService("http://localhost:8080/ws", request);
                System.out.println("-----------------------------");
                System.out.println("IBAN : " + response.getIbanCheck().getNumberIBAN());
                System.out.println("status : " + response.getIbanCheck().isStatusIBAN());
                System.out.println("-----------------------------");
            }
        };
    }
}

