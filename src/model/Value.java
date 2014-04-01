package model;

public class Value {
    private final String category;
    private String value;

    Value(String category) {
        this(category, null);
    }

    Value(String category, String value) {
        this.category = category;
        this.value = value == null ? "" : value;
    }

    String getCategory() {
        return category;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Value that = (Value) o;

        return (category != null ? !category.equals(that.category) : that.category != null) ? false : !(value != null ? !value.equals(that.value) : that.value != null);
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return value;
    }
}
