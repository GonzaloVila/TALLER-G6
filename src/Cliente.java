public class Cliente {
    private String nombre;
    private String correo;
    private long telefono;
    private String contrasenia;
    private Reserva reserva;

    public Cliente(String nombre, String correo, long telefono, String contrasenia) {
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public long getTelefono() {
        return telefono;
    }

    public void setTelefono(long telefono) {
        this.telefono = telefono;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }


    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + ", correo=" + correo + ", telefono=" + telefono + ", contrasenia=" + contrasenia + '}';
    }


}
