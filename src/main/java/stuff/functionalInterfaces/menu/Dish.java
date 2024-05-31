package stuff.functionalInterfaces.menu;

public class Dish {
    private String name;
    private Type type;
    private CaloricLevel level;

    public Dish(String name, Type type, CaloricLevel level) {
        this.name = name;
        this.type = type;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public CaloricLevel getLevel() {
        return level;
    }

    public void setLevel(CaloricLevel level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return  name;
    }
}
