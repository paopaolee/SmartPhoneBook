package beans;

public enum Gender {
    MALE("男"), FEMALE("女");
    private final String name;
    Gender(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
