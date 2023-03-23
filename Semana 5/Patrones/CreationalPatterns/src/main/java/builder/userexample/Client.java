package builder.userexample;

public class Client {

    public static void main(String[] args) {

        User user1 = new User.UserBuilder()
                .firstName("Jane")
                .lastName("Doe")
                .age(30)
                .phone("1234567")
                .address("Fake address 1234")
                .gender("M")
                .build();

        System.out.println(user1);

        User user2 = new User.UserBuilder()
                .firstName("Mary")
                .lastName("Jane")
                .age(40)
                .phone("5655")
                //no address
                .build();

        System.out.println(user2);

        User user3 = new User.UserBuilder()
                .firstName("Jane")
                .lastName("Doe")
                //No age
                //No phone
                //no address
                .build();

        System.out.println(user3);
    }
}
