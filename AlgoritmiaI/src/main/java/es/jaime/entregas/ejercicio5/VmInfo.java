package es.jaime.entregas.ejercicio5;

import java.util.Calendar;

public final class VmInfo implements Comparable<VmInfo>{
    private String processName;
    private Calendar startDate;
    private Calendar endDate;

    public VmInfo () {}

    public VmInfo(String processName, Calendar startDate, Calendar endDate) {
        this.processName = processName;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(String processName) {
        this.processName = processName;
    }

    public Calendar getStartDate() {
        return startDate;
    }

    public void setStartDate(Calendar startDate) {
        this.startDate = startDate;
    }

    public Calendar getEndDate() {
        return endDate;
    }

    public void setEndDate(Calendar endDate) {
        this.endDate = endDate;
    }

    @Override
    public int compareTo(VmInfo other) {
        long difMilisThis = this.endDate.getTimeInMillis() - this.startDate.getTimeInMillis();
        long difMilisOther = other.endDate.getTimeInMillis() - other.startDate.getTimeInMillis();

        return (int) (difMilisThis - difMilisOther);
    }
}
