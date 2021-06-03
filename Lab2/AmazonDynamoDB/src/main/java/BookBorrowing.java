import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="BookBorrowing")
public class BookBorrowing {
    private String id;
    private String memberId;
    private String ISBN;
    private Integer numberOfDays;

    public BookBorrowing() { }

    public BookBorrowing(String id, String memberId, String ISBN, Integer numberOfDays) {
        this.id = id;
        this.memberId = memberId;
        this.ISBN = ISBN;
        this.numberOfDays = numberOfDays;
    }

    @DynamoDBHashKey(attributeName="id")
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @DynamoDBAttribute(attributeName="memberId")
    public String getMemberId() {
        return memberId;
    }
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    @DynamoDBAttribute(attributeName="ISBN")
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    @DynamoDBAttribute(attributeName="numberOfDays")
    public Integer getNumberOfDays() {
        return numberOfDays;
    }
    public void setNumberOfDays(Integer numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    @Override
    public String toString() {
        return "BookBorrowing{" +
                "id='" + id + '\'' +
                ", memberId='" + memberId + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", numberOfDays=" + numberOfDays +
                '}';
    }
}
