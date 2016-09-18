
public class Wait extends Thread {

    final public static int TIME = 100;

    public void run() {
        System.out.print( "Processing" );

        while ( true ) {
            try {
                Thread.sleep( TIME );
            } catch ( InterruptedException e ) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            String a = "|";
            String b = "-";
            System.out.print(a+'\b');
            System.out.print(b+'\b');
            //System.out.printf("\r", " -");
            //System.out.printf("\r", " \\");
            // System.out.flush();
        }
    }
}