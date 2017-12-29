import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        Person person = (Person) getPerson(teacher);
        person.printOccupation();
    }

    public static Object getPerson(Object target) {
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), (proxy, method, args) -> {
            if (method.isAnnotationPresent(Logger.class)) {
                System.out.println("Before invocation");
            }

            Object ret = method.invoke(target, args);

            if (method.isAnnotationPresent(Logger.class)) {
                System.out.println("After invocation");
            }

            return ret;
        });
    }
}
