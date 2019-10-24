import java.util.ArrayList;
import java.util.List;


class MovingAverageStore implements DataStore {
    private List<Double> items = new ArrayList<>();
    private int n;
    private Double movingAverage;
    private int windowStart;
    private int windowEnd;
    private double windowSum;

    MovingAverageStore(int n) {
        this.n = n;
        this.windowStart = 0;
        this.windowEnd = n - 1;
    }

    public void add(Double item) {
        this.items.add(item);
        updateMovingAverage();
    }

    public Double getMovingAverage() {
        return this.movingAverage;
    }

    public Double getIndex(Integer index) {
        return this.items.get(index);
    }

    public Double getLast() {
        int len = this.items.size();
        return this.items.get(len - 1);
    }

    public Integer getSize() {
        return this.items.size();
    }

    public Integer getMovingAverageSize() {
        return this.n;
    }

    public void setMovingAverageSize(int n) {
        this.n = n;
        computeAverage();
    }

    private void updateMovingAverage() {
        if (this.movingAverage == null) {
            computeAverage();
        } else {
            updateAverageWithWindow();
        }
    }

    private void computeAverage() {
        int div = this.n;
        int length = items.size();
        int start = length - this.n;
        if (start < 0) {
            return;
        }

        this.windowStart = start; 
        this.windowEnd = length;

        scan(this.windowStart, this.windowEnd, div);
    }

    private void scan(int start, int end, int divisor) {
        if (start > end) return;
        
        double sum = 0;
        for (int i = start; i < end; i++) {
            sum += this.items.get(i);
        }

        this.windowSum = sum;
        this.movingAverage = sum / divisor;
    }

    private void updateAverageWithWindow(){
        double sum = this.windowSum - this.items.get(this.windowStart) + this.getLast();

        this.windowStart++; 
        this.windowEnd++;

        this.movingAverage = sum / this.n;
        this.windowSum = sum;
    }

}