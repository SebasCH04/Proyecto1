import java.util.Random; 

public class Alimento {
    private int x;
    private int y;
    private int tamanio;
    private int energia;
    private int boostVelocidad;
    private int boostVision;
    
    public Alimento(int x, int y) {
        this.x = x;
        this.y = y;
        generarTamanioYTipo();
    }
    
    public void generarTamanioYTipo() {
        Random random = new Random();
        tamanio = random.nextInt(3) + 1;
        energia = tamanio * 10;
        boostVelocidad = random.nextInt(3) + 1;
        boostVision = random.nextInt(3) + 1;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getTamanio() {
        return tamanio;
    }
    
    public int getEnergia() {
        return energia;
    }
    
    public int getBoostVelocidad() {
        return boostVelocidad;
    }
    
    public int getBoosVision() {
        return boostVision;
    }
}