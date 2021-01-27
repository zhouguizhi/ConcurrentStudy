public class Main {
    public static void main(String[] args) {
        String method = Thread.currentThread().getStackTrace()[1].getMethodName();
        System.out.println("me:="+method);
    }
}
