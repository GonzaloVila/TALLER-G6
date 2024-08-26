public class Mesa {
    private int numMesa;
    private int ubicacion;
    private int capacidad;
    private boolean disponible;
    private Reserva reserva;

    public Mesa(int numMesa, int ubicacion, int capacidad, boolean disponible) {
        this.numMesa = numMesa;
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.disponible = disponible;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public int getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(int ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }



    @Override
    public String toString() {
        return "Mesa{" + "numMesa=" + numMesa + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + ", disponible=" + disponible + '}';
    }



}
