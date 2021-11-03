package net.lumae.core.data.entities;

public class JoinLeaveFormat extends Format {

    private String leaveFormat;
    public JoinLeaveFormat() {

    }

    public JoinLeaveFormat(String name, String permission, String joinFormat, String leaveFormat, Integer priority) {
        super(name, permission, joinFormat, priority);
        this.leaveFormat = leaveFormat;
    }

    /**
     * Override method returns both formats seperated by :
     * @return
     */
    @Override
    public String getMessageFormat() {
        return super.getMessageFormat() + ":" + getLeaveFormat();
    }

    public String getJoinFormat() {
        return super.getMessageFormat();
    }

    public String getLeaveFormat() {
        return leaveFormat;
    }

    public void setLeaveFormat(String leaveFormat) {
        this.leaveFormat = leaveFormat;
    }
}
