package blockchain;

/**
 *
 * @author chsr
 */
public class ContentBlock {
    public String customer;
    public String details;
    public String date;
    public String place;
    public long total;

    public ContentBlock(String customer, String details,String date,String place, long total) {
        this.customer = customer;
        this.details = details;
        this.date=date;
        this.place= place;
        this.total = total;
    }

    public String getString() {
        return String.format(
                "Customer [%s] has ordered [%s] on [%s] from [%s], total [%d] VND",
                this.customer,
                this.details,
                this.date,
                this.place,
                this.total);
    }
}