package portservice;

public class Passenger implements Payable{
//variables=====================================================================
    private String type;    

//constructors==================================================================
public Passenger(){}
public Passenger(String type){
    this.type=type;
}

//setters & getters=============================================================
    public String getType(){
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
        
//methods=======================================================================
    @Override
    public double Pay() {
        double x=0.0;
        if (type.equals("normal"))x=5.0;
        if (type.equals("student"))x=2.5;
        if (type.equals("AMEA"))x=2.0;
        if (type.equals("senior"))x=2.0;
        if (type.equals("baby"))x=0.0;
        return x*VAT;
    }

}//~class
