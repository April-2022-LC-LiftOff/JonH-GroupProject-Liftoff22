public class Main {
    public static void main(String[] args) {
        System.out.println("Area of Triangle");

        Integer firstArea = triArea(3, 2);
        System.out.println(firstArea);
        System.out.println(triArea(7, 4));
        System.out.println(triArea(10, 10));
    }

    public static Integer triArea(Integer base, Integer height) {
        return (base * height) / 2;
    }

}


