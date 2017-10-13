/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.parvez.Users;

import java.util.Objects;

/**
 *
 * @author Parvez
 */
class Company {

    private String name;
    private String catchPhrase;
    private String bs;

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchPhrase() {
        return catchPhrase;
    }

    public void setCatchPhrase(String catchPhrase) {
        this.catchPhrase = catchPhrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.name);
        hash = 29 * hash + Objects.hashCode(this.catchPhrase);
        hash = 29 * hash + Objects.hashCode(this.bs);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Company other = (Company) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.catchPhrase, other.catchPhrase)) {
            return false;
        }
        if (!Objects.equals(this.bs, other.bs)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Company{" + "name=" + name + ", catchPhrase=" + catchPhrase + ", bs=" + bs + '}';
    }

}
