import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteHistogram extends Remote {
	public int createHistogram(int bins) throws RemoteException;
	public void addToHistogram(int histogramID, int value) throws RemoteException;
	public int[] getHistogram(int histogramID) throws RemoteException;
}
