package beans;

public interface Validator<T> {
    boolean validate(T val);
}
