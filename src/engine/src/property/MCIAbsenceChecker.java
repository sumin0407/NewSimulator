package property;

import log.Log;
import log.Snapshot;
import property.pattern.ExistenceChecker;
import java.util.StringTokenizer;

public class MCIAbsenceChecker extends ExistenceChecker {
    
    public MCIAbsenceChecker() {
        super();
    }
    
    @Override
    protected boolean evaluateState(Snapshot snapshot, Property verificationProperty) {
        StringTokenizer st = new StringTokenizer(snapshot.getSnapshotString(), " ");
        while(st.hasMoreTokens()) {
            if(st.nextToken().equals("RescuedRate:"))
                break;
        }
        
        double rescueRate = Double.parseDouble(st.nextToken());
        System.out.println(rescueRate);
        
        if(rescueRate > verificationProperty.getValue()){
            return true;
        }
        else{
            return false;
        }
    }
    
    @Override
    public boolean check(Log log, Property verificationProperty, int until) {
        return false;
    }
    
    @Override
    public boolean check(Log log, Property verificationProperty, double prob, int T) {
        return false;
    }
    
    @Override
    public boolean check(Log log, Property verificationProperty, double prob, int t, int T) {
        return false;
    }
    
    @Override
    public boolean check(Log log, Property verificationProperty, int t, int T) {
        return false;
    }
}