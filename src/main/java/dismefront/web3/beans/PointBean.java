package dismefront.web3.beans;

import dismefront.web3.data.Attempt;
import dismefront.web3.util.HitChecker;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@Named
@SessionScoped
public class PointBean implements Serializable {

    private double x = 0;
    private String y = "";
    private boolean r1 = false;
    private boolean r2 = false;
    private boolean r3 = false;
    private boolean r4 = false;
    private boolean r5 = false;

    public double getR() {
        if (r1)
            return 1;
        if (r2)
            return 2;
        if (r3)
            return 3;
        if (r4)
            return 4;
        return 5;
    }

    public void process() {
        long ex_time = System.nanoTime();
        HitChecker check;
        try {
            check = new HitChecker(getNormalizedX(), Double.parseDouble(y), getR());
        }
        catch (Exception ex) {
            return;
        }
        Attempt p = new Attempt();
        p.setResult(check.result());
        p.setX(getNormalizedX());
        p.setY(Double.parseDouble(y));
        p.setR(getR());
        p.setDate(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
        System.out.println(p.getResult() + " " + p.getX() + " " + p.getY() + " " + p.getR() + " " + p.getDate() );
    }

    public double getNormalizedX() {
        return x / 10;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public boolean getR1() {
        return r1;
    }

    public void setR1(boolean r1) {
        this.r1 = r1;
    }

    public boolean getR2() {
        return r2;
    }

    public void setR2(boolean r2) {
        this.r2 = r2;
    }

    public boolean getR3() {
        return r3;
    }

    public void setR3(boolean r3) {
        this.r3 = r3;
    }

    public boolean getR4() {
        return r4;
    }

    public void setR4(boolean r4) {
        this.r4 = r4;
    }

    public boolean getR5() {
        return r5;
    }

    public void setR5(boolean r5) {
        this.r5 = r5;
    }

}
