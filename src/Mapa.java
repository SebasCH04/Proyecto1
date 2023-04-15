import java.util.ArrayList; 
import java.util.List;

public class Mapa {
    private int ancho;
    private int alto;
    private List<Microorganismo> microorganismos;
    private List<Alimento> particulasAlimento;

    public Mapa(int ancho, int alto) {
        this.ancho = ancho;
        this.alto = alto;
        this.microorganismos = new ArrayList<>();
        this.particulasAlimento = new ArrayList<>();
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public List<Microorganismo> getMicroorganismos() {
        return microorganismos;
    }

    public List<Alimento> getParticulasAlimento() {
        return particulasAlimento;
    }

    public void addMicroorganismo(Microorganismo microorganismo) {
        microorganismos.add(microorganismo);
    }

    public void removeMicroorganismo(Microorganismo microorganismo) {
        microorganismos.remove(microorganismo);
    }

    public void addParticulaAlimento(Alimento alimento) {
        particulasAlimento.add(alimento);
    }

    public void removeParticulaAlimento(Alimento alimento) {
        particulasAlimento.remove(alimento);
    }
}