package dismefront.web3.util;

public class HitChecker {

    private double x, y, r;

    public HitChecker(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public boolean checkTriangle() {
        return this.y <= 0 && this.x <= 0 && this.y >= -x - this.r;
    }

    public boolean checkCircle() {
        return this.x >= 0 && this.y <= 0 &&
                this.x * this.x + this.y * this.y <= this.r * this.r;
    }

    public boolean checkSquare() {
        return this.x >= 0 && this.y >= 0 &&
                this.y <= r && this.x <= r;
    }

    public boolean result() {
        return this.checkCircle() || this.checkTriangle() || this.checkSquare();
    }

}
