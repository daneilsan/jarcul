package jarcul;

public final class ComparisonFieldResult {
    private final Object field;
    private final double points;

    public ComparisonFieldResult(Object field, double points) {
        this.field = field;
        this.points = points;
    }

    public final Object field() {
        return field;
    }

    public final double points() {
        return points;
    }
}
