public class Restaurante {
    private String diasemana;
    private int horaapertura;
    private int horacierre;

    public Restaurante(String diasemana, int horaapertura, int horacierre) {
        this.diasemana = diasemana;
        this.horaapertura = horaapertura;
        this.horacierre = horacierre;
    }

    public String getDiasemana() {
        return diasemana;
    }

    public void setDiasemana(String diasemana) {
        this.diasemana = diasemana;
    }

    public int getHoraapertura() {
        return horaapertura;
    }

    public void setHoraapertura(int horaapertura) {
        this.horaapertura = horaapertura;
    }

    public int getHoracierre() {
        return horacierre;
    }

    public void setHoracierre(int horacierre) {
        this.horacierre = horacierre;
    }

    @Override
    public String toString() {
        return "Restaurante{" + "diasemana=" + diasemana + ", horaapertura=" + horaapertura + ", horacierre=" + horacierre + '}';
    }


}
