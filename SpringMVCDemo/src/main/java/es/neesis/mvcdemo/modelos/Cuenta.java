package es.neesis.mvcdemo.modelos;

public class Cuenta {

    private String numCuenta;
    private Sucursal sucursal;
    private String userId;
    private Double balance;

    public Cuenta(String numCuenta, Sucursal sucursal, String userId, Double balance) {
        this.numCuenta = numCuenta;
        this.sucursal = sucursal;
        this.userId = userId;
        this.balance = balance;
    }

    public String getNumCuenta() {
        return numCuenta;
    }

    public void setNumCuenta(String numCuenta) {
        this.numCuenta = numCuenta;
    }

    public Sucursal getSucursal() {
        return sucursal;
    }

    public void setSucursal(Sucursal sucursal) {
        this.sucursal = sucursal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
