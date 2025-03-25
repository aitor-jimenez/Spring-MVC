package es.neesis.mvcdemo.dtos;

public class OutCuentaDTO {

    private String numCuenta;
    private Double balance;

    public OutCuentaDTO(String numCuenta, Double balance) {
        this.numCuenta = numCuenta;
        this.balance = balance;
    }

}
