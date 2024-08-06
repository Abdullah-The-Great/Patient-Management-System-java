public class Observation {
    private ObservationType observationType;
    private double value;

    public Observation(ObservationType observationType, double value) {
        this.observationType = observationType;
        this.value = value;
    }

    public ObservationType getObservationType() {
        return observationType;
    }

    public double getValue() {
        return value;
    }

    
}