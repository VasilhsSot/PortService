package portservice;

public class Vehicle implements Payable{
//variables=====================================================================
    private String type;
    private double length;
    private int position;
    private String AK;

//constructors==================================================================
    public Vehicle(){}
    public Vehicle(String type, double length){
        this.type=type;
        if (type.equals("moto")){
            this.length=0;
        }else{
        this.length=length;
        AK=null;
        }
    }
    public Vehicle(String type, double length, String AK){
        this.type=type;
        if (type.equals("moto")){
            this.length=0;
        }else{
        this.length=length;
        this.AK=AK;
        }
    }



//setters & getters=============================================================
    public int getPosition(){        
        return position;
    }
    public String getAK() {
        return AK;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setAK(String AK) {
        this.AK = AK;
    }

    public String getType() {        
        return type;
    }
    public double getLength() {
        return length;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setLength(double length) {
        this.length = length;
    }

//methods=======================================================================
    @Override
    public double Pay() {
        double x=0.0;
        if (type.equals("IX"))x=15.0;
        if (type.equals("truck"))x=45.0;
        if (type.equals("moto"))x=5.0;
        return x*VAT;
    }
    
}//~class
