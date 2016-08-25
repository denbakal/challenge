package ua.challenge.hibernate.examples.inheritance.joined.subclass.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 * Created by Денис on 25.08.2016.
 */
@Entity
@Table(name = "join_owners")
@PrimaryKeyJoinColumn(name = "owner_id", referencedColumnName = "person_id")
public class JoinOwner extends JoinPerson {
    @Column(name="stocks")
    private Long stocks;

    @Column(name="partnership_stake")
    private Long partnershipStake;

    public JoinOwner() {
    }

    public JoinOwner(String firstname, String lastName, Long stocks, Long partnershipStake) {
        super(firstname, lastName);
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
        return "JoinOwner{" +
                "stocks=" + stocks +
                ", partnershipStake=" + partnershipStake +
                '}';
    }
}
