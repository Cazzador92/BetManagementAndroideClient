package de.rocks.jsdevelopment.betmanagement.model;

//import org.apache.commons.lang3.builder.EqualsBuilder;
//import org.apache.commons.lang3.builder.HashCodeBuilder;
//import org.apache.commons.lang3.builder.ToStringBuilder;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.io.Serializable;

public class BetSubscriber
    implements Serializable
{

    public String id;
    public String name;
    public String mail;
    public String tel;
    public String mFirstname;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).append(name).append(mail).append(tel).toHashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof BetSubscriber) == false) {
            return false;
        }
        BetSubscriber rhs = ((BetSubscriber) other);
        return new EqualsBuilder().append(id, rhs.id).append(name, rhs.name).append(mail, rhs.mail).append(tel, rhs.tel).isEquals();
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The mail
     */
    public String getMail() {
        return mail;
    }

    /**
     *
     * @param mail
     * The mail
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     *
     * @return
     * The tel
     */
    public String getTel() {
        return tel;
    }

    /**
     *
     * @param tel
     * The tel
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

}
