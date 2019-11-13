public class Link {
    private int id;
    private int companyId;
    private int personId;

    private String startDate;
    private String role;

    public Link() {
    }

    public Link(int id, int companyId, int personId, String startDate, String role) {
        this.id = id;
        this.companyId = companyId;
        this.personId = personId;
        this.startDate = startDate;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}