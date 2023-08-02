public class Controller {
    public static void main(String[] args) {
        CashHolder client = new CashHolder("Client");
        CashHolder owner = new CashHolder("Owner");

        new GUI(client, owner);
    }
}
