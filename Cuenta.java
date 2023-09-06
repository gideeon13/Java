package Administrador;

public class Cuenta {
    private double saldo;
    private String nombre, documento, typecuenta, currency;
    private int numcuenta;
    
    public Cuenta(double s, String num, int numc, String d, String tpc, String cu){
      this.saldo = s;
      this.nombre = num;
      this.numcuenta = numc;
      this.documento = d;
      this.typecuenta = tpc;
      this.currency = cu;   
    } 
    
    public double getsaldo(){     
      return this.saldo;
    }
    
    public String getnombre(){
        return this.nombre;
    }
    public void setnombre(String nom){
        this.nombre = nom;
    }
    
    public int getnumcuenta(){     
      return this.numcuenta;
    }
    public void setnumcuenta(int numc){
        this.numcuenta = numc;
    }
    
    public String getdocumento(){     
      return this.documento;
    }
    public void setdocumento(String d){
        this.documento = d;
    }
    
    public String gettypecuenta(){     
      return this.typecuenta;
    }
    public void settypecuenta(String tpc){
        this.typecuenta = tpc;
    }
    
    public String getcurrency(){     
      return this.currency;
    }
    public void setcurrency(String cu){
        this.currency = cu;
    }
    
    public void deposit(double s){
        this.saldo = saldo+s;
    }
    
    public boolean withdraw(double s){
        if (saldo >= s){
             this.saldo = saldo-s;
            return true;
                    }
        else{
            return false; 
        }
    }
}