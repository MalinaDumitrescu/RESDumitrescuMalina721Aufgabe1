import java.time.LocalDate;

public class Log {
    private  int id;
    private  String charaktername;
    private  Stufe stufe;
    private  String beschreibung;
    private  LocalDate datum;
    private  double kraftpunkte;

    public Log(int id, String charaktername, Stufe stufe, String beschreibung, LocalDate datum, double kraftpunkte) {
        this.id = id;
        this.charaktername = charaktername;
        this.stufe = stufe;
        this.beschreibung = beschreibung;
        this.datum = datum;
        this.kraftpunkte = kraftpunkte;
    }

    public int getId() {
        return id;
    }

    public String getCharaktername() {
        return charaktername;
    }

    public Stufe getStufe() {
        return stufe;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public LocalDate getDatum() {
        return datum;
    }

    public double getKraftpunkte() {
        return kraftpunkte;
    }

    @Override
    public String toString() {
        return datum + ": " + charaktername + " - " + beschreibung + " (KP: " + kraftpunkte + ")";
    }
}
