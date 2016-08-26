package ua.challenge.hibernate.examples.inheritance.concrete.classes.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Created by Денис on 26.08.2016.
 */
@Entity
@Table(name = "concrete_owners")
public class ConcreteOwner extends ConcretePerson{
    @Column(name="stocks")
    private Long stocks;

    @Column(name="partnership_stake")
    private Long partnershipStake;

    public ConcreteOwner() {
    }

    public ConcreteOwner(String firstName, String lastName, Long stocks, Long partnershipStake) {
        super(firstName, lastName);
        this.stocks = stocks;
        this.partnershipStake = partnershipStake;
    }

    public Long getStocks() {
        return stocks;
    }

    public void setStocks(Long stocks) {
        this.stocks = stocks;
    }

    public Long getPartnershipStake() {
        return partnershipStake;
    }

    public void setPartnershipStake(Long partnershipStake) {
        this.partnershipStake = partnershipStake;
    }

    @Override
    public String toString() {
        return "ConcreteOwner{" +
                "stocks=" + stocks +
                ", partnershipStake=" + partnershipStake +
                '}';
    }
}
