package portservice;

public class Ship {
//variables=====================================================================
    private String name;
    public double[]vehicles;
    private int v_index;
    private double max;
    public Vehicle[]motorbikes;
    private int m_capacity;
    private int m_index;
    public Passenger[]passengers;
    private int p_capacity;
    private int p_index;
    private int count;
    private double total;

//constructors==================================================================
    public Ship(){}
    
    public Ship(String name,int v_capacity, int m_capacity, int p_capacity, double max){
        this.name=name;
        this.m_capacity=m_capacity;
        this.p_capacity=p_capacity;
        vehicles=new double[v_capacity];
        motorbikes=new Vehicle[m_capacity];
        passengers=new Passenger[p_capacity];
        v_index=0;
        m_index=0;
        p_index=0;
        this.max=max;
        count=0;
        total=0;
        
    }

    
//setters & getters=============================================================
    public void setName(String name) {
        this.name = name;
    }
    public void setV_index(int v_index) {
        this.v_index = v_index;
    }
    

    public void setM_capacity(int m_capacity) {
        this.m_capacity = m_capacity;
    }

    public void setM_index(int m_index) {
        this.m_index = m_index;
    }

    public void setP_capacity(int p_capacity) {
        this.p_capacity = p_capacity;
    }

    public void setP_index(int p_index) {
        this.p_index = p_index;
    }

    public String getName() {
        return name;
    }

    public int getM_capacity() {
        return m_capacity;
    }

    public int getV_index() {
        return v_index;
    }
    public int getM_index() {
        return m_index;
    }

    public int getP_capacity() {
        return p_capacity;
    }

    public int getP_index() {
        return p_index;
    }
    public double getMax(){
        return max;
    }
    public void setMax(double max){
        this.max=max;
    }
    public void setCount(int count){
        this.count=count;
    }
    public int getCount(){
        return count;
    }
    public double getTotal(){
        return total;
    }
    
    public void setTotal(double total){
        this.total=total;
    }

//methods=======================================================================
    public void addPas(Passenger pas){
        if (p_index==p_capacity){
            System.out.println("FULL OF PASSENGERS.");
        }
        else {
            passengers[p_index]=pas;
            p_index++;
        }
    }
    
    public boolean addVeh(Vehicle veh){
        boolean is=false;
        if (veh.getType().equals("moto")){
            if(m_index==m_capacity){ 
                System.out.println("FULL OF MOTORBIKES.");
            } else {
                motorbikes[m_index]=veh;
                m_index++;
                count++;
                is=true;
            }
        } else {for (int i=0;i<vehicles.length;i++){
                    if(!(vehicles[v_index]==(this.getMax()) || (vehicles[v_index]+ veh.getLength())>(this.getMax()))) {
                        vehicles[v_index]+=veh.getLength();
                        is=true;
                        veh.setPosition(v_index);
                        count++;
                        break;
                    }
                    if(v_index==vehicles.length){v_index=0;} else {v_index++;}  
                    }
                }
        return is;
    }
    
    @Override
    public String toString(){
        return this.name;
    }


}//~class
