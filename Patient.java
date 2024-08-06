import java.util.ArrayList;
import java.util.List;

public class Patient {
    private String id;
    private String name;
    private List<Observation> observations;

    public Patient(String id, String name) {
        this.id = id;
        this.name = name;
        this.observations = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void addObservation(Observation observation) {
        observations.add(observation);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Patient{" +
                "id='" + id + '\'' +
                ", name='" + name + "'}\n");

        for (Observation observation : observations) {
            stringBuilder.append(observation).append("\n");
        }

        return stringBuilder.toString();
    }
}