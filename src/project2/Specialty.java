package project2;
/**
 * @author Akshay Madhusudhan
 * @author Aidan Pembleton
 */
public enum Specialty {
    FAMILY(250),
    PEDIATRICIAN(300),
    ALLERGIST(350);

    private final int charge;

    Specialty(int charge) {
        this.charge = charge;
    }

    public int getCharge() {
        return charge;
    }
}
