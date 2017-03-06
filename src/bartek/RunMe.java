package bartek;

import java.util.ArrayList;
import java.util.List;

public class RunMe {
    public static void main(String[] args) {
        List<UniqueElement> elements = new ArrayList<>(2);
        elements.add(new UniqueElement(1, 2));
        elements.add(new UniqueElement(10, 20));
        boolean contains = elements.contains(new UniqueElement(1, 2));

        System.out.println(contains ? "yes" : "no");


        List<EqualsElement> elements2 = new ArrayList<>(2);
        elements2.add(new EqualsElement(1, 2));
        elements2.add(new EqualsElement(10, 20));
        boolean contains2 = elements2.contains(new EqualsElement(1, 2));

        System.out.println(contains2 ? "yes" : "no");
    }
}

class UniqueElement {
    private int x, y;

    UniqueElement(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class EqualsElement {
    private int x, y;

    EqualsElement(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;  // jeśli o jest nullem to jasne że nie jest tym obiektem
        if (this == o) return true; // jeśli obiekty FAKTYCZNIE są te same, to return true;

        if (o instanceof EqualsElement) { // jeżeli to jest obiekt tej klasy to porównujemy pola
            EqualsElement that = (EqualsElement) o;
            return x == that.x && y == that.y;  // jak pola są równe, to mówimy że obiekty są "takie same".
        } else {
            return false; // jak nie to false.
        }
    }
}
