public class MeasurementObservation extends Observation {
    public MeasurementObservation(MeasurementObservationType observationType, double value) {
        super(observationType, value);
    }
    @Override
    public String toString() {
        return 
                "observationType: " + getObservationType() +
                ", value=" + getValue() ;
    }
}