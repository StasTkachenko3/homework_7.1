import java.text.DecimalFormat;

public class Money {
    private long hryvnia;
    private byte kopiyka;


    public Money(long hryvnia, byte kopiyka) {
        this.hryvnia = hryvnia;
        this.kopiyka = kopiyka;
    }

    public long getHryvnia() {
        return hryvnia;
    }

    public void setHryvnia(long hryvnia) {
        this.hryvnia = hryvnia;
    }

    public byte getKopiyka() {
        return kopiyka;
    }

    public void setKopiyka(byte kopiyka) {
        this.kopiyka = kopiyka;
    }
    public void displayAmount() {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        System.out.println(decimalFormat.format(hryvnia) + "," + String.format("%02d", kopiyka));
    }

    public Money add(Money other) {
        long totalHryvnia = this.hryvnia + other.hryvnia;
        int totalKopiyka = this.kopiyka + other.kopiyka;

        if (totalKopiyka >= 100) {
            totalHryvnia += totalKopiyka / 100;
            totalKopiyka %= 100;
        }

        return new Money(totalHryvnia, (byte) totalKopiyka);
    }

    public Money subtract(Money other) {
        long totalHryvnia = this.hryvnia - other.hryvnia;
        int totalKopiyka = this.kopiyka - other.kopiyka;

        if (totalKopiyka < 0) {
            totalHryvnia -= 1;
            totalKopiyka += 100;
        }

        return new Money(totalHryvnia, (byte) totalKopiyka);
    }

    public Money divide(double divisor) {
        double totalAmount = hryvnia + (kopiyka / 100.0);
        double result = totalAmount / divisor;

        long resultHryvnia = (long) result;
        byte resultKopiyka = (byte) ((result - resultHryvnia) * 100);

        return new Money(resultHryvnia, resultKopiyka);
    }

    public Money multiply(double multiplier) {
        double totalAmount = hryvnia + (kopiyka / 100.0);
        double result = totalAmount * multiplier;

        long resultHryvnia = (long) result;
        byte resultKopiyka = (byte) ((result - resultHryvnia) * 100);

        return new Money(resultHryvnia, resultKopiyka);
    }

    public int compare(Money other) {
        double thisAmount = hryvnia + (kopiyka / 100.0);
        double otherAmount = other.hryvnia + (other.kopiyka / 100.0);

        return Double.compare(thisAmount, otherAmount);
    }
}