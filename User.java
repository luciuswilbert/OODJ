class User{
    private int user_id;
	private String password, role;
    public String email, contact_number;

    public User(int user_id, String password, String email, String contact_number, String role) {
        this.user_id = user_id;
        this.password = password;
        this.email = email;
        this.contact_number = contact_number;
        this.role = role;
    }

    public User() {
        user_id = 0;
        password = "NA";
        email = "NA";
        contact_number = "NA";
        role = "NA";
    }

    public int getUser_id(){
        return user_id;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String Password){
        password = Password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    @ Override
    public String toString(){
        return "Please specify which detail you want.";
    }

}
