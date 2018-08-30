package com.example.maxwillams.cadastropessoa;

public class Person {

    private Integer id;
    private String firstname;
    private String lastname;



    public Person() {

    }

    public Person(Integer id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;


    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getFirstName() {
        return firstname = firstname;
    }

    public void setFirstName(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }


}
