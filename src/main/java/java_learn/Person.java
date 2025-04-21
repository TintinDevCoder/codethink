package java_learn;

public class Person implements Cloneable {
    private Address address;

    public Person(Address address) {
        this.address = address;
    }

    // 省略构造函数、Getter&Setter方法
    @Override
    public Person clone() {
        try {
            Person person = (Person) super.clone();
            person.setAddress(address.clone());
            return person;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    private void setAddress(Address address) {
        this.address = address;
    }

    public Object getAddress() {
        return this.address;
    }
}