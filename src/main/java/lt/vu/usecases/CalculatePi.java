package lt.vu.usecases;

import lt.vu.interceptors.LoggedInvocation;
import lt.vu.services.PiCalculator;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import javax.enterprise.inject.Model;

@SessionScoped
@Named
//@Model
public class CalculatePi implements Serializable {
    @Inject
    private PiCalculator piCalculator;

    private CompletableFuture<Double> PiCalcTask = null;


    @LoggedInvocation
    public String calculateNewPi() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();



        PiCalcTask = CompletableFuture.supplyAsync(() -> piCalculator.calculatePi());
        return "secondforestpage?faces-redirect=true&forestId=" + requestParameters.get("forestId");

    }


    public boolean isPiCalculationRunning() {
        return PiCalcTask != null && !PiCalcTask.isDone();
    }

    public String getPiCalcStatus() throws ExecutionException, InterruptedException {
        if ( PiCalcTask == null) {
            return "Pi calculation is not in progress...";
        } else if (isPiCalculationRunning()) {
            return "Pi calculating is in progress...";
        }
        return "Calcualted Pi is: " + PiCalcTask.get();
    }
}