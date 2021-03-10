import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Fuvar {

    private int  taxi_id;
    private LocalDate indulas;
    private int idotartam;
    private double tavolsag;
    private double viteldij;
    private double borravalo;
    private String fizetes_modja;

    public Fuvar(int taxi_id, LocalDate indulas, int idotartam, double tavolsag, double viteldij, double borravalo, String fizetes_modja) {
        this.taxi_id = taxi_id;
        this.indulas = indulas;
        this.idotartam = idotartam;
        this.tavolsag = tavolsag;
        this.viteldij = viteldij;
        this.borravalo = borravalo;
        this.fizetes_modja = fizetes_modja;
    }

    public Fuvar (String sor){
        String [] adatok= sor.split(";");
        this.taxi_id = Integer.parseInt(adatok[0]);
        DateTimeFormatter format= DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.indulas = LocalDate.parse(adatok[1], format);
        this.idotartam = Integer.parseInt(adatok[2]);
        this.tavolsag = Double.parseDouble(adatok[3].replace(',','.'));
        this.viteldij = Double.parseDouble(adatok[4].replace(',','.'));;
        this.borravalo = Double.parseDouble(adatok[5].replace(',','.'));;
        this.fizetes_modja = adatok[6];
 }

    public int getTaxi_id() {
        return taxi_id;
    }

    public LocalDate getIndulas() {
        return indulas;
    }

    public int getIdotartam() {
        return idotartam;
    }

    public double getTavolsag() {
        return tavolsag;
    }

    public double getViteldij() {
        return viteldij;
    }

    public double getBorravalo() {
        return borravalo;
    }

    public String getFizetes_modja() {
        return fizetes_modja;
    }

    @Override
    public String toString() {
        return "taxi id: "+taxi_id +
                ", indulás: " + indulas +
                ", időtartalom: " + idotartam +
                ", távolság: " + tavolsag +
                ", viteldíj: " + viteldij +
                ", borravalo:" + borravalo +
                ", fizetés modja: " + fizetes_modja;
    }
}
