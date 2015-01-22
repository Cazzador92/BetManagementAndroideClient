package de.rocks.jsdevelopment.betmanagement.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import org.apache.commons.lang3.builder.EqualsBuilder;
//import org.apache.commons.lang3.builder.HashCodeBuilder;
//import org.apache.commons.lang3.builder.ToStringBuilder;

public class Bet {

    public String id;
    public String owner;
    public String name;
    public Date start;
    public Date end;
    public Date deadline;
    public List<BetSubscriber> subscriber = new ArrayList<BetSubscriber>();
    public String description;


//    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }

//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder().append(id).append(owner).append(name).append(start).append(end).append(deadline).append(subscriber).append(description).toHashCode();
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//        if ((other instanceof Bet) == false) {
//            return false;
//        }
//        Bet rhs = ((Bet) other);
//        return new EqualsBuilder().append(id, rhs.id).append(owner, rhs.owner).append(name, rhs.name).append(start, rhs.start).append(end, rhs.end).append(deadline, rhs.deadline).append(subscriber, rhs.subscriber).append(description, rhs.description).isEquals();
//    }

    @Override
    public String toString() {
        return name;
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
     * The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     *
     * @param owner
     * The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
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
     * The start
     */
    public Date getStart() {
        return start;
    }

    /**
     *
     * @param start
     * The start
     */
    public void setStart(Date start) {
        this.start = start;
    }

    /**
     *
     * @return
     * The end
     */
    public Date getEnd() {
        return end;
    }

    /**
     *
     * @param end
     * The end
     */
    public void setEnd(Date end) {
        this.end = end;
    }

    /**
     *
     * @return
     * The deadline
     */
    public Date getDeadline() {
        return deadline;
    }

    /**
     *
     * @param deadline
     * The deadline
     */
    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    /**
     *
     * @return
     * The subscriber
     */
    public List<BetSubscriber> getSubscriber() {
        return subscriber;
    }

    /**
     *
     * @return
     * The subscriber
     */
    public String getSubscriberList() {
        String subscriberList = "";

        for (int i = 0; i < subscriber.size(); i++){
            subscriberList += subscriber.get(i).toString();
        }
        return subscriberList;
    }

    /**
     *
     * @param subscriber
     * The subscriber
     */
    public void setSubscriber(List<BetSubscriber> subscriber) {
        this.subscriber = subscriber;
    }

    /**
     *
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}