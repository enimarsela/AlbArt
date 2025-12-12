public class Produkt {

    private int produktId;
    private String emri;
    private String pershkrimi;
    private double cmimi;
    private String fotoProdukti;
    private String watermark;
    private String statusi;
    private double vleresimiTotal;

    public Produkt(int produktId, String emri, String pershkrimi, double cmimi,
                   String fotoProdukti, String watermark, String statusi, double vleresimiTotal) {
        this.produktId = produktId;
        this.emri = emri;
        this.pershkrimi = pershkrimi;
        this.cmimi = cmimi;
        this.fotoProdukti = fotoProdukti;
        this.watermark = watermark;
        this.statusi = statusi;
        this.vleresimiTotal = vleresimiTotal;
    }

    public void applyWatermark() {
        System.out.println("Watermark u aplikua: " + watermark);
    }

    public void updateDetails(String emri, String pershkrimi, double cmimi) {
        this.emri = emri;
        this.pershkrimi = pershkrimi;
        this.cmimi = cmimi;
    }

    public String getArtist() {
       
        return "Artist i panjohur";
    }

     
}