package test;


import java.util.Date;

public class Membership {
    private int membershipId;
    private int customerId;
    private Date startDate;
    private Date endDate;
    private String type;

    // Constructor
    public Membership( int membershipId,int customerId,  Date startDate, Date endDate, String type) {
        this.membershipId = membershipId;
        this.customerId = customerId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }
    	
    @Override
    public String toString() {
        return "Membership ID: " + membershipId +
                ", Customer ID: " + customerId +
                ", Start Date: " + startDate +
                ", End Date: " + endDate +
                ", Type: " + type;
    }

	// Getters and setters
    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
