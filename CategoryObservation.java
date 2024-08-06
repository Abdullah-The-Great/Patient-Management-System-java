public class CategoryObservation extends Observation {
    private String category;

    public CategoryObservation(CategoryObservationType observationType, int value, String category) {
        super(observationType, value);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "CategoryObservation{" +
                "observationType=" + getObservationType() +
                
                ", category='" + category + '\'' +
                '}';
    }
}
