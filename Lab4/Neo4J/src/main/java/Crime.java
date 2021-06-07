import org.neo4j.ogm.annotation.GeneratedValue;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Property;

@NodeEntity
public class Crime {
    @Id @GeneratedValue
    private String id;
    @Property
    private String idOffender;
    @Property
    private String idVictim;
    @Property
    private String crimeType;
    @Property
    private String crimeDate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdOffender() {
        return idOffender;
    }

    public void setIdOffender(String idOffender) {
        this.idOffender = idOffender;
    }

    public String getIdVictim() {
        return idVictim;
    }

    public void setIdVictim(String idVictim) {
        this.idVictim = idVictim;
    }

    public String getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(String crimeType) {
        this.crimeType = crimeType;
    }

    public String getCrimeDate() {
        return crimeDate;
    }

    public void setCrimeDate(String crimeDate) {
        this.crimeDate = crimeDate;
    }

    @Override
    public String toString() {
        return "Crime{" +
                "id=" + id +
                ", idOffender=" + idOffender +
                ", idVictim=" + idVictim +
                ", crimeType='" + crimeType + '\'' +
                ", crimeDate='" + crimeDate + '\'' +
                '}';
    }
}
