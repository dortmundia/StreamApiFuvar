import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;


public class Feladatok {
    private ArrayList<Fuvar> fuvarLista;
    private ArrayList<Fuvar> szurtLista;


    public Feladatok(){
        Beolvas();

        //System.out.println("Berendi Barnabás");
        System.out.println("1. Feladat: Fuvarok száma: " + fuvarLista.size());
        //egyik barátom aki már szoftverfejlesztő mondta hogy a var-t érdemes lenne kiprobálni javában azért most probálkoztam a használatával


        double kettes = fuvarLista.stream()
                .filter(k -> k.getTaxi_id() == 6185)
                .mapToDouble(k -> k.getViteldij()).sum();
        kettes = kettes + fuvarLista.stream()
                .filter(k -> k.getTaxi_id() == 6185)
                .mapToDouble(k -> k.getBorravalo()).sum();
        long kettesFuvarja = fuvarLista.stream()
                .filter(k -> k.getTaxi_id() == 6185)
                .count();


        System.out.println("2. Feladat: azonosito: 6185, " +kettes +"$ bevétel, "+ kettesFuvarja+" fuvar alatt"  );


        System.out.printf("3. Feladat: %.2f km\n", fuvarLista.stream().mapToDouble(k -> k.getTavolsag()).sum() * 1.6D);


        fuvarLista.stream()
                .max(Comparator.comparingInt(k -> k.getIdotartam()))
                .ifPresent(k -> System.out.printf("4. Feladat: %d mp, azonosito: %d, távolság: %.2f km, díj: %.2f$\n", k.getIdotartam(), k.getTaxi_id(), k.getTavolsag(), k.getViteldij()));


     
     


        System.out.printf("6. Feladat: %.2f km\n", fuvarLista.stream()
                .filter(i->i.getTaxi_id() ==4261)
                .mapToDouble(k -> k.getTavolsag()).sum() * 1.6D);


        String header = "taxi_id;indulas;idotartam;tavolsag;viteldij;borravalo;fizetes_modja\n";
        var hibasAdatok = fuvarLista.stream()
                .filter(k -> k.getIdotartam() > 0 && k.getViteldij() > 0F && k.getTavolsag() == 0F)
                .sorted(Comparator.comparing(k -> k.getIndulas()))
                .map(k -> k.getTaxi_id() + ";" + k.getIndulas() + ";" + k.getIdotartam() + ";" + k.getTavolsag() + ";" + k.getViteldij() + ";" + k.getBorravalo() + ";" + k.getFizetes_modja())
                .collect(Collectors.joining("\n"));


        try {
            FileWriter writer = new FileWriter("hibak.txt");
            writer.write(header + hibasAdatok);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


        long nyolcas = fuvarLista.stream()
                .filter(k -> k.getTaxi_id() == 1452)
                .count();


        if(nyolcas>0) {
            System.out.println("8. feladat: 1452 azonosítójú taxis szerepel az adatokban!");
        }
        else{System.out.println("8. feladat: 1452 azonosítójú taxis nem szerepel az adatokban!");}


        System.out.println("9. feladat: ");
        Legrovidebb(3);


        //Date tizes = new Date(2016-12-24);
        //Date el = new Date(2016-12-31);

        long tizesF =   fuvarLista.stream().filter(p-> p.getIndulas().isEqual(LocalDate.of(2016, Month.DECEMBER,24))).count();
        System.out.println("10. feladat: "+tizesF+" fuvar volt");

        double elA = fuvarLista.stream().filter(p-> p.getIndulas().isEqual(LocalDate.of(2016, Month.DECEMBER,31)))
                                .mapToDouble(k -> k.getBorravalo()).sum();
        double elB = fuvarLista.stream().filter(p-> p.getIndulas().isEqual(LocalDate.of(2016, Month.DECEMBER,31)))
                .mapToDouble(k -> k.getViteldij()).sum();

        System.out.println("11.feladat:  "+elB+"/"+elA);  //pár számmal probálkoztam, de ennél jobb megoldást nem találtam



    }


    private void Legrovidebb(int db){
        fuvarLista.stream()
                .sorted((A, B)-> Integer.compare(A.getIdotartam(),B.getIdotartam()))     // 0-kat fognak kidobni a hibás adatok miatt, viszont erre nem tért ki a feladat hogy ez hiba lenne, és 0mp is időtartalom
                .limit(db)
                .forEach(i-> System.out.println(i.toString()));
    }


    private void Beolvas() {
        fuvarLista = new ArrayList<>();

        try{
            BufferedReader r= new BufferedReader(new FileReader("fuvar.csv"));
            r.readLine();
            String sor= r.readLine();
            while(sor != null){
                fuvarLista.add(new Fuvar(sor));
                sor=r.readLine();
            }

        }catch(IOException e){
            e.getMessage();
        }
    }


}


