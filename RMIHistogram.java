import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.Naming;
import java.util.HashMap;

public class RMIHistogram extends UnicastRemoteObject implements Binder, RemoteHistogram {
    
    @Override
    public synchronized int createHistogram(int bins) throws RemoteException {
        int[] newHistogram = new int[bins];
        histogramMap.put(histogramid, newHistogram);
        histogramid++;
        return histogramid - 1;
    }
    
    @Override
    public synchronized void addToHistogram(int histogramID, int value) throws RemoteException {
        int[] modifiedHistogram = histogramMap.get(histogramID);
        modifiedHistogram[value]++;
        histogramMap.put(histogramID, modifiedHistogram);
    }
    
    @Override
    public synchronized int[] getHistogram(int histogramID) throws RemoteException {
        return histogramMap.get(histogramID);
    }
    
    @Override
    public synchronized void bind(String serviceName) {
        try {
            Naming.bind(serviceName, this);
        } catch(Exception e) {
            System.err.println(e.toString());
        }
    }
    
    public RMIHistogram() throws RemoteException {
        super();
        histogramMap = new HashMap<>(); 
    }
    
    private int histogramid = 0;
    private HashMap<Integer, int[]> histogramMap;
}
