package portservice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
//variables=====================================================================
    public static Scanner sc=new Scanner(System.in);
    public static Scanner sc1=new Scanner(System.in);
    public static Database db=new Database();
    public static final String URL="jdbc:mysql://127.0.0.1:3306/port";
    public static final String USR="admin";
    public static final String PSD="admin";
    static Ship sh;
    static List<Ship>list=new ArrayList<>();

//methods=======================================================================
    public static List<Ship> createShipList() throws SQLException{
        List<Ship> l1=new ArrayList<>();
        String q="select name,ship_row,moto_cap,passenger_cap,length, from ships;";
        try{
        db.stm=db.connection.createStatement();
        ResultSet rs=db.stm.executeQuery(q);
        while(rs.next()){
            Ship b= new Ship(rs.getString("name"),rs.getInt("ship_row"),rs.getInt("moto_cap"),rs.getInt("passenger_cap"),rs.getDouble("length"));
            l1.add(b);
            }
        } catch (SQLException e){
            System.out.println("Problem with your query. ");
        }
     return l1;
    }
    public static int extractInt(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);

        if (!matcher.find())
            throw new NumberFormatException("For input string [" + str + "]");

        return Integer.parseInt(matcher.group());
    }
    
    public static Ship chooseSh(){
        String k;        
        int l=0;
        boolean b=true;
        Ship s1=new Ship();
        System.out.println("Welcome to the port service. \nSelect the ship you want to board. (1-"+list.size()+") ");
        while (b){
            int j=1;
            for (Ship s: list){
                System.out.println(j+""+s);
                j++;
            }
            k=sc.nextLine();
            l=extractInt(k);
            if (l<1 || l>list.size()) {System.out.println ("Invalid input. Please choose (1-"+list.size()+")..");}
            else { b=false;}
        }
        int j=1;
        for (Ship s:list){
            if (l==j) {
                s1=s;
            }
            j++;
        }
        return s1; 
    }
    
    
    
    public static boolean showMenu(){
        boolean flag=true, bool=true;
        while(bool){
            System.out.println("1. Buy passenger ticket(s). \n2.Buy vehicle ticket(s). \n3.Exit.");
            String ch=sc.nextLine();
            Vehicle v1;
            Passenger p1;
            double total=0.0;
            switch (ch) {
                case "1":if (sh.getP_index()==sh.getP_capacity()){System.out.println("FULL of passengers. "); break;}
                        System.out.println("What category if the ticket for? Please select one of the following: \n\"normal\"\n\"student\"\n\"AMEA\"\n\"senior\"\n\"baby\" ");
                        String kl=sc.nextLine();
                        p1=new Passenger(kl);
                        sh.addPas(p1);
                        sh.setTotal(sh.getTotal()+p1.Pay());
                        total+=p1.Pay();
                        break;
                case "2":if (sh.getV_index()==sh.getMax()) {System.out.println ("FULL of vehicles."); break;} 
                        System.out.println("What type is it? \nMotorbike? \nCar? \nTruck?");
                        String c=sc.nextLine().toLowerCase();
                        switch (c){
                            case "motorbike": v1=new Vehicle("moto",0); 
                                sh.addVeh(v1);
                                sh.setTotal(sh.getTotal()+v1.Pay()); 
                                total+=v1.Pay(); 
                                break;
                            case "car": System.out.println("What length does it have? (in meters)");
                                double l1=sc1.nextDouble();
                                System.out.println("What the plate number of the car? ");
                                String pln1=sc.nextLine();
                                v1=new Vehicle("car",l1,pln1);
                                sh.addVeh(v1);
                                sh.setTotal(sh.getTotal()+v1.Pay());
                                total+=v1.Pay();
                                break;
                            case "Truck":System.out.println("What length does it have? (in meters)");
                                double l2=sc1.nextDouble();
                                System.out.println("What the plate number of the car? ");
                                String pln2=sc.nextLine();
                                v1=new Vehicle("truck",l2,pln2);
                                sh.addVeh(v1);
                                sh.setTotal(sh.getTotal()+v1.Pay());
                                total+=v1.Pay();
                                break;
                            default : System.out.println("Invalid vehicle input. ");
                                break;
                        }
                        break;
                case "3": bool=false;
                        flag=false;
                        System.out.println("Total payment= "+total+" ! Enjoy your trip. ");
                        break; 
                default : System.out.println("Invalid input. Please select 1-3. ");
                        break;
        }
        
    }return flag;
    }
    
    
    

//main==========================================================================
    public static void main(String args[]) throws SQLException{
        boolean flag=true;
        db.connect(URL, USR, PSD);
        list=createShipList();
        double price =0.0;
        while (flag){
            sh=chooseSh();
            flag=showMenu();
        }
        
        db.connection.close();
        sc.close();
        sc1.close();
    }
}//~class
