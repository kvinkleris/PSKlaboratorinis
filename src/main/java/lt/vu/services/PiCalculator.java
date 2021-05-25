package lt.vu.services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.security.SecureRandom;

@ApplicationScoped
public class PiCalculator implements Serializable {

    public double calculatePi() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }

        double pi=0;
        double s=1;
        int n =1000000;
        for(int i=1;i<n;i=i+2)
        {
            pi+=s/i;
            s=-s;
        }
        double calculatedPi=pi;
        return calculatedPi*4;
    }
}