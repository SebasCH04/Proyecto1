public class Jugador { 
    private int x;
    private int y;
    private int energia;
    private int velocidad;
    private int vision;
    private int edad;
    
    public Jugador(int x, int y, int energia, int velocidad, int vision, int edad) {
        this.x = x;
        this.y = y;
        this.energia = energia;
        this.velocidad = velocidad;
        this.vision = vision;
        this.edad = edad;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getEnergia() {
        return energia;
    }
    
    public int getVelocidad() {
        return velocidad;
    }
    
    public int getVision() {
        return vision;
    }
    
    public int getEdad() {
        return edad;
    }
    
    public void moverArriba() {
        y--;
    }
    
    public void moverAbajo() {
        y++;
    }
    
    public void moverIzquierda() {
        x--;
    }
    
    public void moverDerecha() {
        x++;
    }
    
    public void consumirAlimento(Alimento alimento) {
        //Comprueba el tipo de Alimento y actualiza las propiedades del Jugador segun corresponda
        switch(alimento.getTamanio()) {
            case 1:
                energia += alimento.getEnergia();
                break;
            case 2:
                velocidad += alimento.getBoostVelocidad();
                break;
            case 3:
                vision += alimento.getBoosVision();
                break;
            default:
                //Si el tipo de Alimento es desconocido, no hace nada
                break;
        }
    }

    public void consumirMicroorganismo(Microorganismo microorganismo) {
        this.energia += microorganismo.getEnergia();
        this.vision += microorganismo.getVision();
        this.velocidad += microorganismo.getVelocidad();
    }
}