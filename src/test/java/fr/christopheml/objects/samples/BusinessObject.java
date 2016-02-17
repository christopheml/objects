package fr.christopheml.objects.samples;

public class BusinessObject {
    private final String name;
    private final long count;
    private final double ratio;

    public BusinessObject(String name, long count, double ratio) {
        this.name = name;
        this.count = count;
        this.ratio = ratio;
    }

    public String getName() {
        return name;
    }

    public long getCount() {
        return count;
    }

    public double getRatio() {
        return ratio;
    }
}
