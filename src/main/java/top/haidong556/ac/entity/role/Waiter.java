package top.haidong556.ac.entity.role;

public class Waiter extends People {
    public Waiter(){
        super(RoleType.WAITER);
    }
    public Waiter(String username, String password) {
        super(RoleType.WAITER, username, password);
    }
}
