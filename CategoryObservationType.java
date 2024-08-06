import java.util.Arrays;

public class CategoryObservationType extends ObservationType {
    private String[] categories;

    public CategoryObservationType(String code, String name, String[] categories) {
        super(code, name);
        this.categories = categories;
    }

    public String[] getCategories() {
        return categories;
    }

    @Override
    public String toString() {
        return "code='" + getCode() + '\'' +
                ", name='" + getName() + '\'' +
                ", categories=" + Arrays.toString(categories);
    }
}