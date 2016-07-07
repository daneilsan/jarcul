package jarcul;

public final class ComparisonRowResult {
    private final ComparisonFieldResult[] comparisonFieldResults;
    private double points = 0;

    public ComparisonRowResult(int fieldsNumber) {
        comparisonFieldResults = new ComparisonFieldResult[fieldsNumber];
    }

    public final double points() {
        return points;
    }

    public final double normalizedPoints(RowSetup rowSetup) {

        return points / rowSetup.getMaximumPossiblePoints();
    }

    public final void addFieldResult(int fieldPosition, ComparisonFieldResult comparisonFieldResult) {
        this.comparisonFieldResults[fieldPosition] = comparisonFieldResult;

        points = points + comparisonFieldResult.points();
    }

    public final void resetPoints() {
        points = 0.0;
    }

    public final ComparisonFieldResult getFieldResult(int fieldPosition) {
        return comparisonFieldResults[fieldPosition];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[ ");

        for (ComparisonFieldResult comparisonFieldResult : comparisonFieldResults) {
            sb.append(String.format("[%s|%.2f] ", comparisonFieldResult.field(), comparisonFieldResult.points()));
        }

        sb.append(String.format("] = %.4", points));

        return sb.toString();
    }

}
